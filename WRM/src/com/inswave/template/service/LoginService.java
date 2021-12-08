package com.inswave.template.service;

import java.util.Map;

public interface LoginService {

	// 사용자 정보 조회 (로그인 체크용도로 사용 )
	public Map selectMemberInfoForLogin(Map param);

	// 해당 사용자 아이디가 관리자 아이디인지를 검사한다.
	public boolean isAdmin(String userId);
	
	// 사용자의 비밀번호를 업데이트한다.
	public int updatePassword(Map param);
}