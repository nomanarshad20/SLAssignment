package com.system.test.assignment.common;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDTO implements Serializable {

	private static final long serialVersionUID = 5674263053801361234L;

	private transient Object data;

	/** The message. */
	private String message;

	public ResponseDTO(Object data, String message) {
		super();
		this.data = data;
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static ResponseDTO success(Object data, String message) {
		return new ResponseDTO(data, message);
	}

	public static ResponseDTO success(Object data) {
		return new ResponseDTO(data, "");
	}

	public static ResponseDTO failed(String message) {
		return new ResponseDTO(null, message);
	}

	public static ResponseDTO failed(Object data, String message) {
		return new ResponseDTO(data, message);
	}

}
