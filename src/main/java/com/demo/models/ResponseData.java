package com.demo.models;

public class ResponseData<T> {
	public int code;
	public boolean success;
	public String message;
	public T data;
	
	public ResponseData(int code, boolean success, String message)
	{
		this.code = code;
		this.success = success;
		this.message = message;
	}
	
	public ResponseData(int code, boolean success, String message, T data)
	{
		this.code = code;
		this.success = success;
		this.message = message;
		this.data = data;
	}
}