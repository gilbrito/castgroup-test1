package com.castgroup.service.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;

import com.castgroup.entity.JsonBase64;
import com.castgroup.repository.DiffRepository;
import com.castgroup.utils.Constants.DiffType;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DiffServiceImplTest {
	private static final Integer ID = 1;
	private static final String PAYLOAD_LEFT = "payloadLeft";
	private static final String PAYLOAD_LEFT2 = "payloadLefg";
	private static final String PAYLOAD_RIGHT = "payloadRight";
	
	@InjectMocks
	DiffServiceImpl diffServiceImpl;
	
	@Mock
	DiffRepository diffRepository;

	@Test
	public void save_withExistingJsonBase64WithLeftPayloadWithLeftPayload_shouldUpdate()	{
		when(diffRepository.existsById(ID)).thenReturn(true);
		JsonBase64 jsonBase64 = createPayload(null, PAYLOAD_RIGHT);
		when(diffRepository.getOne(ID)).thenReturn(jsonBase64);

		diffServiceImpl.save(PAYLOAD_LEFT, ID, DiffType.LEFT);
 
		verify(diffRepository).save(any(JsonBase64.class));
	}

	@Test
	public void save_withExistingJsonBase64WithRightPayload_shouldUpdate()	{
		when(diffRepository.existsById(ID)).thenReturn(true);
		JsonBase64 jsonBase64 = createPayload(PAYLOAD_LEFT, null);
		when(diffRepository.getOne(ID)).thenReturn(jsonBase64);

		diffServiceImpl.save(PAYLOAD_RIGHT, ID, DiffType.RIGHT);

		verify(diffRepository).save(any(JsonBase64.class));
	}

	@Test
	public void save_withoutJsonBase64_shouldCreate()	{
		when(diffRepository.existsById(ID)).thenReturn(false);

		diffServiceImpl.save(PAYLOAD_RIGHT, ID, DiffType.RIGHT);

		verify(diffRepository).save(any(JsonBase64.class));
	}
	
	@Test
	public void getDiff_withPayloadLeftDifferentFromRight_shouldReturnMessage() {
		JsonBase64 jsonBase64 = createPayload(PAYLOAD_LEFT, PAYLOAD_RIGHT);
		when(diffRepository.getOne(ID)).thenReturn(jsonBase64);
	
		String result = diffServiceImpl.getDiff(ID);

		assertThat(result, equalTo("The size from payload left is different from payload right."));
	}

	@Test
	public void getDiff_withPayloadLeftEqualFromRight_shouldReturnTrue() {
		JsonBase64 jsonBase64 = createPayload(PAYLOAD_LEFT, PAYLOAD_LEFT);
		when(diffRepository.getOne(ID)).thenReturn(jsonBase64);

		String result = diffServiceImpl.getDiff(ID);

		assertThat(result, equalTo("true"));
	}

	@Test
	public void getDiff_withPayloadLeftDifferentFromRight_shouldReturnOffSet() {
		
		JsonBase64 jsonBase64 = createPayload(PAYLOAD_LEFT, PAYLOAD_LEFT2);
		when(diffRepository.getOne(ID)).thenReturn(jsonBase64);
		
		String result = diffServiceImpl.getDiff(ID);

		assertThat(result, equalTo("Offsets: 10"));
	}

	private JsonBase64 createPayload(String left, String right) {
		JsonBase64  jsonBase64  = new JsonBase64();	
		jsonBase64.setId(ID);
		jsonBase64.setPayloadLeft(left);
		jsonBase64.setPayloadRight(right);
		return jsonBase64;
	}	
}
