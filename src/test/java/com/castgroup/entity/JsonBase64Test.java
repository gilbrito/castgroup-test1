package com.castgroup.entity;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import com.castgroup.utils.Constants.DiffType;

/**
 * Unit {@link JsonBase64}
 *
 */
public class JsonBase64Test{
	private JsonBase64 jsonBase64 = new JsonBase64();
	private static final Integer ID = 1;
	private static final String PAYLOAD_LEFT = "payloadLeft";
	private static final String PAYLOAD_RIGHT = "payloadRight";

	@Before
	public void setup()
	{
		jsonBase64.setId(ID);
		jsonBase64.setPayloadLeft(PAYLOAD_LEFT);
		jsonBase64.setPayloadRight(PAYLOAD_RIGHT);
	} 

	@Test	
	public void getId_shouldReturnId() {
		Integer id = jsonBase64.getId();
		assertThat(id, equalTo(ID));
	}
	
	@Test
	public void setId_shoulSetId() {
		JsonBase64 jsonBase64Test = new JsonBase64();
		jsonBase64Test.setId(ID);
		assertThat(jsonBase64Test.getId(), equalTo(ID));
	}
	
	@Test
	public void getPayloadLeft_shouldReturnPaylodLeft() {
		String payloadLeft = jsonBase64.getPayloadLeft();
		assertThat(payloadLeft , equalTo(PAYLOAD_LEFT ));
	}
	
	@Test
	public void setPayloadLeft_shouldSetPaylodLeft() {
		JsonBase64 jsonBase64Test = new JsonBase64();
		jsonBase64Test.setPayloadLeft(PAYLOAD_LEFT);
		assertThat(jsonBase64Test.getPayloadLeft(), equalTo(PAYLOAD_LEFT));
	}

	@Test
	public void getPayloadRight_shouldReturnPaylodRight() {
		String payloadRight = jsonBase64.getPayloadRight();
		assertThat(payloadRight , equalTo(PAYLOAD_RIGHT));
	}

	@Test
	public void setPayloadRight_shouldSetPaylodRight() {
		JsonBase64 jsonBase64Test = new JsonBase64();
		jsonBase64Test.setPayloadRight(PAYLOAD_RIGHT);
		assertThat(jsonBase64Test.getPayloadRight(), equalTo(PAYLOAD_RIGHT));
	}
	
	
	@Test
	public void createJsonBase64_shouldReturnJsonBase64WithLeft (){
		JsonBase64 jsonBase64Test = new JsonBase64(PAYLOAD_LEFT , ID, DiffType.LEFT);
		assertThat(jsonBase64Test.getPayloadRight() , nullValue());
		assertThat(jsonBase64Test.getPayloadLeft() , equalTo(PAYLOAD_LEFT ));

		assertThat(jsonBase64Test.getId() , equalTo(ID));
	}

	@Test
	public void createJsonBase64_shouldReturnJsonBase64WithRight (){
		JsonBase64 jsonBase64Test = new JsonBase64(PAYLOAD_RIGHT , ID, DiffType.RIGHT);
		assertThat(jsonBase64Test.getPayloadLeft() , nullValue());
		assertThat(jsonBase64Test.getPayloadRight() , equalTo(PAYLOAD_RIGHT ));

		assertThat(jsonBase64Test.getId() , equalTo(ID));
	}

	
}
