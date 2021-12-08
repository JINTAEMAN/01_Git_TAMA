package com.inswave.template.service;

import java.util.List;
import java.util.Map;

public interface MainService {
	/**
	 * Release 게시판 항목을 가져온다. selectType으로 실행할 query를 분기처리.
	 * 
	 * @date 2016.08.26
	 * @param param selectType, #{IS_USE}, #{START_NUM} , #{END_NUM}, SEQ_ORDER
	 * @author InswaveSystems
	 * @example selectType == "S" : 요약본.
	 */
	public List selectRelease(Map param);

	/**
	 * Release 게시판 항목을 수정한다. selectType으로 실행할 query를 분기처리.
	 * 
	 * @date 2016.09.09
	 * @param param
	 * @author InswaveSystems
	 * @example selectType == "U" : 요약본.
	 */
	public Map saveRelease(List param);

	/**
	 * Release 게시판의 총 건수를 구한다.
	 * 
	 * @date 2016.09.12
	 * @param param
	 * @author InswaveSystems
	 * @example
	 */
	public Map selectReleaseCnt();
}
