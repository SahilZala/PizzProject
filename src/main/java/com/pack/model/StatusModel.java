package com.pack.model;

import org.springframework.http.HttpStatus;

public class StatusModel  {
	String timestamp;
	HttpStatus status;
	Object error;
	String path;
	Object data;
	public StatusModel(String timestamp, HttpStatus status, Object error, String path, Object data) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.path = path;
		this.data = data;
	}
	public StatusModel() {
		super();
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public Object getError() {
		return error;
	}
	public void setError(Object error) {
		this.error = error;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
