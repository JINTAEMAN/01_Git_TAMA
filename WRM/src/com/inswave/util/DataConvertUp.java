package com.inswave.util;

import websquare.http.controller.grid.excel.read.ICellDataProvider;

public class DataConvertUp implements ICellDataProvider {

	public String convertValue(String cellvalue) throws Exception {
		// System.out.println("cellvalue ==>>" + cellvalue);
		// down받은 data중에서 변경할 부분을 replace한다.
		return cellvalue.replace("u00_Convert_", "u00");
	}
}
