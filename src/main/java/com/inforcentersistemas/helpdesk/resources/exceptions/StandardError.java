package com.inforcentersistemas.helpdesk.resources.exceptions;

import java.io.Serializable;

public class StandardError implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long timestamp;
	private Integer status;
	private String error;
	private String mensage;
	private String path;

	public StandardError() {
		super();
	}

	public StandardError(long timestamp, Integer status, String error, String mensage, String path) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.mensage = mensage;
		this.path = path;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMensage() {
		return mensage;
	}

	public void setMensage(String mensage) {
		this.mensage = mensage;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
