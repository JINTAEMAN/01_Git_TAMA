package com.inswave.template.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("authorityDao")
public interface AuthorityDao {

	// 권한이 부여되지 않은 직원 리스트
	public List<Map> excludeSelectAuthorityMemberList(Map param);

	// 권한리스트 조회
	public List<Map> selectAuthorityList(Map param);

	public List<Map> selectAuthoritySearchItem();

	// 권한이 부여된 사용자 리스트 조회
	public List<Map> selectAuthorityMemberList(Map param);

	// 권한 등록
	public int insertAuthority(Map param);

	// 권한 삭제
	public int deleteAuthority(Map param);

	// 권한 수정
	public int updateAuthority(Map param);

	// 권한별 등록사원 입력
	public int insertAuthorityMember(Map param);

	// 권한별 등록사원 삭제
	public int deleteAuthorityMember(Map param);

	/**
	 * 권한코드로 권한별 등록사원 정보 한번에 삭제하기
	 * 
	 * @date 2016. 12. 05.
	 * @param
	 * @returns
	 * @author InswaveSystems
	 */
	public int deleteAuthorityMemberAll(Map param);
}
