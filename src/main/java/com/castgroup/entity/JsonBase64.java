package com.castgroup.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.castgroup.utils.Constants.DiffType;

@Entity
public class JsonBase64 implements Serializable{
	private static final long serialVersionUID = 0x62A6DA99AABDA8A8L;
	
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer id;

	@Column
	private String payloadLeft;
	
	@Column
	private String payloadRight;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPayloadLeft() {
		return payloadLeft;
	}

	public void setPayloadLeft(String payloadLeft) {
		this.payloadLeft = payloadLeft;
	}

	public String getPayloadRight() {
		return payloadRight;
	}

	public void setPayloadRight(String payloadRight) {
		this.payloadRight = payloadRight;
	}
	
	public JsonBase64() {		
	}
	
	public JsonBase64(String payload, int id, DiffType diffType) {
		this.id = id;
		if(diffType.equals(DiffType.LEFT)) {
		 this.payloadLeft = payload;
		}
		if(diffType.equals(DiffType.RIGHT)) {
			 this.payloadRight = payload;
		}
	}
	
}
