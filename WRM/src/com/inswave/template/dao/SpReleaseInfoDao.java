package com.inswave.template.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("spReleaseInfoDao")
public interface SpReleaseInfoDao {
	/**
	 * release 게시판용
	 * 
	 * @date 2016. 8. 25.
	 * @param <Map> #{IS_USE}, #{START_NUM} , #{END_NUM}, SEQ_ORDER
	 * @returns <List> 반환 변수 및 객체
	 * @author InswaveSystems
	 */
	List selectReleaseForSummary(Map param);

	// 메뉴관리 C, U, D
	public int insertRelease(Map param);

	public int deleteRelease(Map param);

	public int updateRelease(Map param);

	/**
	 * release 게시판용
	 * 
	 * @date 2016. 9. 12.
	 * @param <Map>
	 * @returns <List> 총 건수
	 * @author InswaveSystems
	 */
	public Map selectReleaseCnt();
}