package com.inswave.sample.beans;

public class ParamBean<T> {
	
	private HeaderBean header;
	
	private T param;

	public HeaderBean getHeader() {
		return header;
	}

	public void setHeader(HeaderBean header) {
		this.header = header;
	}	
	
	public T getParam() {
		return param;
	}

	public void setParam(T param) {
		this.param = param;
	}


}
