package com.inswave.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result {

	private Map<String, Object> resultMap = new HashMap<String, Object>();

	// 성공메세지
	public final static String STATUS_SUCESS = "S";

	// 성공 메세지
	public final static String STATUS_SUCESS_MESSAGE = "정상 처리되었습니다.";

	// 오류메세지
	public final static String STATUS_ERROR = "E";

	// 기본 에러 상세 코드
	public final static String STATUS_ERROR_DEFAULT_DETAIL_CODE = "E9999";

	// 오류메세지
	public final static String STATUS_ERROR_MESSAGE = "처리 도중 오류가 발생되었습니다.";

	// 경고메세지
	public final static String STATUS_WARNING = "W";

	// 경고메세지
	public final static String STATUS_WARNING_MESSAGE = "처리 도중 오류가 발생되었습니다.";

	// 기본(map 타입) 웹스퀘어 view
	public final static String VIEW_DEFAULT = "wqView";

	// 결과값에 대한 메세지 key명
	public final static String MESSAGE_KEY = "rsMsg";

	// viewType이 VIEW_STRING 일 경우 참조하는 key
	public final static String RESULT_KEY_DEFAULT = "result";

	public void setData(String id, String data) {
		resultMap.put(id, data);
	}

	public void setData(String id, Map data) {
		resultMap.put(id, data);
	}

	public void setData(String id, List data) {
		resultMap.put(id, data);
	}

	public Map<String, Object> getResult() {
		if (resultMap.get(MESSAGE_KEY) == null) {
			setMsg(STATUS_SUCESS);
		}

		return resultMap;
	}

	/**
	 * 메세지 처리 - 상태 기본 메세지 처리
	 * 
	 * @date 2017.12.02
	 * @memberOf
	 * @param {} status : 메세지 상태
	 * @returns void
	 * @author Inswave
	 * @example WqModel.setMsg( STATUS_SUCCESS );
	 */
	public void setMsg(String status) {
		String msg = "";
		if (status == STATUS_ERROR) {
			msg = STATUS_ERROR_MESSAGE;
		} else if (status == STATUS_SUCESS) {
			msg = STATUS_SUCESS_MESSAGE;
		} else if (status == STATUS_WARNING) {
			msg = STATUS_WARNING_MESSAGE;
		}
		setMsg(status, msg);
	}

	/**
	 * 메세지 처리
	 * 
	 * @date 2017.12.02
	 * @memberOf
	 * @param {} status : 메세지 상태, message : 메세지 내용
	 * @returns void
	 * @author Inswave
	 * @example WqModel.setMsg( STATUS_SUCCESS, "정상 처리되었습니다." );
	 */
	public void setMsg(String status, String message) {
		//System.out.println("[/Result..java] [setMsg()] ==> [TEST_91] [status]"+ status +"[message]"+ message );
		
		setMsg(status, message, null);
	}

	/**
	 * 메세지 처리
	 * 
	 * @date 2017.12.02
	 * @memberOf
	 * @param {} status : 메세지 상태, message : 메세지 내용
	 * @returns void
	 * @author Inswave
	 * @example WqModel.setMsg(returnData, MsgUtil.STATUS_SUCCESS, "정상 처리되었습니다." , exception 객체);
	 */
	public void setMsg(String status, String message, Exception ex) {
		//System.out.println("[/Result..java] [setMsg()] ==> [TEST_01] [status]"+ status +"[message]"+ message );
		
		Map<String, Object> result = new HashMap<String, Object>();

		if (status.equals(STATUS_SUCESS)) {
			result.put("statusCode", STATUS_SUCESS);
			result.put("message", getDefaultStatusMessage(message, STATUS_SUCESS_MESSAGE));
		} else if (status.equals(STATUS_WARNING)) {
			result.put("statusCode", STATUS_WARNING);
			result.put("message", getDefaultStatusMessage(message, STATUS_WARNING_MESSAGE));
		} else if (status.equals(STATUS_ERROR)) {
			setErrorMsg(STATUS_ERROR_DEFAULT_DETAIL_CODE, message, ex);
			return;
		}

		if (ex != null) {
			result.put("messageDetail", ex.getMessage());
		}
		System.out.println("[/Result..java] [setMsg()] ==> [TEST_91] [MESSAGE_KEY]"+ result  );
		
		resultMap.put(MESSAGE_KEY, result);
	}

	/**
	 * 오류 메세지 처리
	 * 
	 * @date 2017.12.02
	 * @memberOf
	 * @param {} errorCode : 오류코드, message : 메세지 내용
	 * @returns void
	 * @author Inswave
	 * @example WqModel.setErrorMsg("E0001", "세션이없습니다." );
	 */
	public void setErrorMsg(String errorCode, String message) {
		setErrorMsg(errorCode, message, null);
	}

	/**
	 * 오류 메세지 처리
	 * 
	 * @date 2017.12.02
	 * @memberOf
	 * @param {} errorCode : 오류코드, message : 메세지 내용
	 * @returns void
	 * @author Inswave
	 * @example WqModel.setErrorMsg("E0001", "세션이없습니다." , exception 객체);
	 */
	public void setErrorMsg(String errorCode, String message, Exception ex) {
		Map<String, Object> result = new HashMap<String, Object>();

		result.put("statusCode", STATUS_ERROR);
		result.put("errorCode", errorCode);
		result.put("message", getDefaultStatusMessage(message, STATUS_ERROR_MESSAGE));

		if (ex != null) {
			result.put("messageDetail", "" + ex.getMessage());
		}
		resultMap.put(MESSAGE_KEY, result);
	}

	public String getDefaultStatusMessage(String message, String defMessage) {
		if (message == null) {
			return defMessage;
		}
		return message;
	};
}