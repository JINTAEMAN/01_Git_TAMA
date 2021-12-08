package com.inswave.sample.beans;

import java.util.List;

public class DataBean<T> {
	
	private HeaderBean header;
	
	private List<T> data;

	public HeaderBean getHeader() {
		return header;
	}

	public void setHeader(HeaderBean header) {
		this.header = header;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}