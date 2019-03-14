package com.castgroup.service.impl;

import com.castgroup.utils.Constants.DiffType;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;

import com.castgroup.entity.JsonBase64;
import com.castgroup.repository.DiffRepository;
import com.castgroup.service.DiffService;

import org.springframework.stereotype.Service;

@Service
public class DiffServiceImpl implements DiffService{
	@Autowired
	DiffRepository diffRepository;
	
	@Override
	public void save(String payload, Integer id, DiffType diffType)
	{
		if(diffRepository.existsById(id)) {
			JsonBase64 oldEntity = diffRepository.getOne(id);
			if(diffType.equals(DiffType.LEFT))
			{
				oldEntity.setPayloadLeft(payload);
			}
			else if(diffType.equals(DiffType.RIGHT))
			{
				oldEntity.setPayloadRight(payload);
			}
			diffRepository.save(oldEntity);
		}
		else {
			JsonBase64 entity = new JsonBase64(payload,id, diffType);
			System.out.println("entity: " + entity);
			diffRepository.save(entity);
		}
	}
	
	@Override
	public String getDiff(Integer id) {
		return checkDiff(diffRepository.getOne(id));
	}
	
	private String checkDiff(JsonBase64 json)
	{
		byte[] payloadLeftBytes = json.getPayloadLeft().getBytes();
		byte[] payloadRightBytes = json.getPayloadRight().getBytes();

		String offsets = "";

		if (Arrays.equals(payloadLeftBytes, payloadRightBytes)) {
			return "true";
		} else if (payloadLeftBytes.length != payloadRightBytes.length) {
			return "The size from payload left is different from payload right.";
		} else {
			offsets = calculateOffSet(payloadLeftBytes, payloadRightBytes, offsets);
		}
		return "Offsets:" + offsets;
	}

	private String calculateOffSet(byte[] payloadLeftBytes, byte[] payloadRightBytes, String offsets) {
		byte different = 0;
		for (int index = 0; index < payloadLeftBytes.length; index++) {
			different = (byte) (payloadLeftBytes[index] ^ payloadRightBytes[index]);
			if (different != 0) {
				offsets = offsets + " " + index;
			}
		}
		return offsets;
	}
}
