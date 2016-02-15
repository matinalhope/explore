package com.biostime.sqlManage.freemarker;

public class FreeMarkerException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public FreeMarkerException(String msg) {
		super(msg);
	}
	public FreeMarkerException(String msg,Throwable throwable) {
		super(msg, throwable);
	}
}
