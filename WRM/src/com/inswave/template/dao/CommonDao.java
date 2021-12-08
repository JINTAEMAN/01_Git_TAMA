package com.inswave.template.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface CommonDao {

	// 메뉴 조회 (로그인 사용자에게 권한이 있는 메뉴만 조회함)
	public List<HashMap<String, Object>> selectMenuList(Map param);
	
	// 사용자별 프로그램 권한 리스트 조회 (로그인 사용자에게 권한이 있는 프로그램 권한만 조회함)
	public List selectProgramAuthorityList(Map param);

	public List selectCommonSearchItem();

	// 공통코드 및 코드 그룹 조회
	public List selectCommonGroup(Map param);

	public List selectCommonCode();

	public List selectCommonCodeList(Map param);

	// 공통코드 그룹 C, U, D
	public int deleteCommonGrp(Map param);

	public int insertCommonGrp(Map param);

	public int updateCommonGrp(Map param);

	// 공통코드 C, U, D
	public int deleteCommonCode(Map param);

	public int insertCommonCode(Map param);

	public int updateCommonCode(Map param);

	// 공통코드
	public List<Map> selectCodeList(Map param);

	// 사용자별 즐겨찾기 리스트
	public List<Map> selectFavListByEmpCd(String empCd);

	/**
	 * insert bmFavorite
	 * 
	 * @date 2016. 8. 24.
	 * @param <MAP> #{EMP_CD}, #{MENU_CD}
	 * @author InswaveSystems
	 */
	public int insertBmFavorite(Map param);

	/**
	 * delete bmFavorite
	 * 
	 * @date 2016. 8. 24.
	 * @param <MAP> #{EMP_CD}, #{MENU_CD}
	 * @author InswaveSystems
	 */
	public int deleteBmFavorite(Map param);

	/**
	 * update bmFavorite
	 * 
	 * @date 2016. 8. 24.
	 * @param <MAP> #{EMP_CD}, #{MENU_CD}
	 * @author InswaveSystems
	 */
	public int updateBmFavorite(Map param);

	// MAIN SETTING 관리
	public int insertBmMainSetting(Map param);

	public int updateBmMainSetting(Map param);

	/**
	 * select BM_MAIN_SETTING by EMP_CD
	 * 
	 * @date 2016. 8. 10.
	 * @param param EMP_CD가 담긴 MAP
	 * @returns <Map> 단건 BM_MAIN_SETTING - FAVORITE_STORAGE, MAIN_LAYOUT_PAGE_CODE
	 * @author InswaveSystems
	 */
	public Map selectBmMainSetting(Map param);

	/**
	 * 그룹코드로 세부코드 정보 한번에 삭제하기
	 * 
	 * @date 2016. 12. 05.
	 * @param
	 * @returns
	 * @author InswaveSystems
	 */
	public int deleteCommonCodeAll(Map param);

	/**
	 * 프로그램 코드로 단축키 조회하기
	 * 
	 * @date 2019. 03. 21.
	 * @param
	 * @returns
	 * @author InswaveSystems
	 */
	public List selectShortcutList(String programCd);

	/**
	 * 프로그램 코드로 단축키 조회하기
	 * 
	 * @date 2019. 03. 21.
	 * @param
	 * @returns
	 * @author InswaveSystems
	 */
	public int insertShortcut(Map param);

	/**
	 * 프로그램 코드로 단축키 조회하기
	 * 
	 * @date 2019. 03. 21.
	 * @param
	 * @returns
	 * @author InswaveSystems
	 */
	public int updateShortcut(Map param);

	/**
	 * 프로그램 코드로 단축키 조회하기
	 * 
	 * @date 2019. 03. 21.
	 * @param
	 * @returns
	 * @author InswaveSystems
	 */
	public int deleteShortcut(Map param);
}