package com.inswave.template.provider;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.parser.JSONParser;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.w3c.dom.Document;

import com.inswave.util.MapUtil;

import websquare.http.WebSquareContext;
import websquare.http.controller.grid.excel.write.IExternalSplitProvider;
import websquare.util.XMLUtil;
 

public class ExcelSplitDown implements IExternalSplitProvider {

	int offsetRow = 0;
	boolean isEnd = false;
	
	
	/**
	 * getData에서 데이터 생성시 OutOfMemory 가 발생하지 않도록 적정선의 데이터를 생성하여 리턴한다.
	 * 
	 * @param requestObj The Document Object of the Request Object
	 * @return String[] 문자열 배열
	 */
	public String[] getData(Document requestObj) throws Exception {

		// Get WebApplicationContext
		WebSquareContext context = WebSquareContext.getContext();
		HttpServletRequest request = context.getRequest();
		HttpSession httpSession = request.getSession();
		ServletContext sc = httpSession.getServletContext();
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(sc);
		
		List<HashMap <String, String>> resultList = null;
		String keyMap = null;
		
		try {
			// Loading Parameter
			// System.out.println(XMLUtil.indent(requestObj));
			String serviceId = XMLUtil.getText(requestObj, "service");
			String methodId = XMLUtil.getText(requestObj, "method");
			keyMap = XMLUtil.getText(requestObj, "keyMap");
			
			JSONParser parse = new JSONParser();
			Map paramData = (Map) parse.parse(XMLUtil.getText(requestObj, "param"));
			paramData.put("OFFSET_ROW", offsetRow);
			paramData.put("PAGE_SIZE", 1000);
			
			// Call the method of the service
			Object service = wContext.getBean(serviceId);
			Method method = service.getClass().getMethod(methodId, Map.class);
			resultList = (List<HashMap <String, String>>) method.invoke(service, paramData);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		if (resultList.size() <= 0 || offsetRow >= 9000) {
			isEnd = true;
		} else {
			offsetRow += 1000;
		}
		
		if ((resultList != null) && (resultList.size() > 0)) {
			if (keyMap != null) {
				return MapUtil.hashMapValuesToArray(resultList, keyMap);
			} else {
				return MapUtil.hashMapValuesToArray(resultList);
			}
		} else {
			return null;
		}
		
	}

	/**
	 * sendCompleted 가 true를 리턴하면 getData() 를 더 호출하지 않고 종료된다
	 */
	public boolean sendCompleted() throws Exception {
		return isEnd;
	}
}
