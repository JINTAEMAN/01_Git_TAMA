package com.inswave.template.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.inswave.template.dao.LoginDao;
import com.inswave.template.dao.MemberDao;
import com.inswave.template.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Resource(name = "loginDao")
	private LoginDao loginDao;

	@Resource(name = "memberDao")
	private MemberDao memberDao;
	
	@Value("${system.admin.id}")
	private String adminId;

	/**
	 * 사용자 정보 조회 (로그인 체크용도로 사용 )
	 */
	@Override
	public Map selectMemberInfoForLogin(Map param) {
		System.out.println("[/LoginServiceImpl.java] [selectMemberInfoForLogin()] ==> [TEST_01] [사용자 정보 조회]" ); 
		
		Map memberMap = loginDao.selectMemberInfoForLogin(param);		// 사용자 정보 조회
		System.out.println("[/LoginServiceImpl.java] [selectMemberInfoForLogin()] ==> [TEST_02] [memberMap.size()]"+ memberMap.size() ); 
 
		if (memberMap == null) {		// 사용자가 존재하지 않을 경우
			memberMap = new HashMap();
			memberMap.put("LOGIN", "notexist");
			System.out.println("[/LoginServiceImpl.java] [selectMemberInfoForLogin()] ==> [TEST_51] [memberMap.size()]"+ memberMap.size() ); 
			 
		} else {		// 사용자가 존재할 경우
			String PASSWORD = (String) memberMap.get("PASSWORD");
			String reqPASSWORD = (String) param.get("PASSWORD");
			System.out.println("[/LoginServiceImpl.java] [selectMemberInfoForLogin()] ==> [TEST_52] [요청_비번]"+ reqPASSWORD +"[DB_비번]"+ PASSWORD ); 
			 
			if (PASSWORD.equals(reqPASSWORD)) { 	// 패스워드 일치
				memberMap.put("PASSWORD", "");
				memberMap.put("LOGIN", "success");
			} else { 	// 패스워드 불일치
				memberMap.put("LOGIN", "error");
			}
		}
		System.out.println("[/LoginServiceImpl.java] [selectMemberInfoForLogin()] ==> [TEST_91] [memberMap]"+ memberMap ); 
		 
		return memberMap; 
	}

	/**
	 * 해당 사용자 아이디가 관리자 아이디인지를 검사한다.
	 */
	@Override
	public boolean isAdmin(String userId) {
		String[] adminIdList = adminId.split(",");
		
		for (String adminId : adminIdList) {
			if (adminId.trim().equals(userId)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 사용자의 비밀번호를 업데이트한다.
	 */
	@Override
	public int updatePassword(Map param) {
		return loginDao.updatePassword(param);
	}

}
