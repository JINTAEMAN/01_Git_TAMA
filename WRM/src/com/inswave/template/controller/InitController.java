package com.inswave.template.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.inswave.template.service.MemberService;
import com.inswave.util.PageURIUtil;
import com.inswave.util.UserInfo;

@Controller
public class InitController {
	
	@Autowired
	private MemberService service;

	@Autowired
	private UserInfo userInfo;

	public InitController() {
	}

	/**
	 * 다국어 처리 Root Url 처리
	 * 
	 * @date 2017.12.22
	 * @author Inswave
	 * @example websquare 진입 후 세션과 설정 값에 따른 화면 xml 분기를 위한 controller. 고려 대상은 websquare.jsp와 I18N.jsp. 화면 페이지의 정보는 properties파일에서 일괄 관리.
	 * @todo 차후 interceptor에서 일괄 처리 가능한지 체크 해야 함.
	 */
	@RequestMapping("/I18N")
	public String indexMultiLang(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		model.addAttribute("movePage", getLoginPage(request.getParameter("w2xPath")));
		return "websquare/I18N";
	}

	/**
	 * 
	 * 기본 Root Url 처리
	 * 
	 * @date 2017.12.22
	 * @author Inswave
	 * @todo url의 경로가 /(root)인 경우 웹스퀘어 엔진에서 하위 컨텐츠 로딩 부분의 특이사항이 발견되어 redirect로 처리.수정 및 개선 필요.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String IndexBase(HttpServletRequest request, Model model) throws Exception {
		System.out.println("\n\n 		[/InitController.java] [IndexBase()] ==> [######################### [01. WTM(교육) Start] #########################]  "); 
		System.out.println("[/InitController.java] [IndexBase()] ==> [TEST_01] [기본 Root Url 처리]" ); 
		
		model.addAttribute("movePage", getLoginPage(request.getParameter("w2xPath")));		// 로그인 페이지 Url 반환 처리 요청
		
		return "websquare/websquare";
	}
	
	/**
	 * 
	 * WebSquare Url 처리한다.
	 * 
	 * @date 2017.12.22
	 * @author Inswave
	 * @todo url의 경로가 /(root)인 경우 웹스퀘어 엔진에서 하위 컨텐츠 로딩 부분의 특이사항이 발견되어 redirect로 처리.수정 및 개선 필요.
	 */
	@RequestMapping(value = "/ws", method = RequestMethod.GET)
	public String IndexWebSquare(HttpServletRequest request, Model model) throws Exception {
		System.out.println("[/InitController.java] [IndexWebSquare()] ==> [TEST_01] [WebSquare Url 처리]" ); 
		
		return "websquare/websquare";
	}
	
	/**
	 * SPA IFrame에서 호출하는 Blank 페이지를 반환하다.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/blank.xml", method = RequestMethod.GET)
	public String callBlankPage(HttpServletRequest request, Model model) throws Exception {
		System.out.println("[/InitController.java] [callBlankPage()] ==> [TEST_01] [Blank 페이지 반환]" ); 
		
		return "websquare/blank";
	}

	/**
	 * 로그인 페이지 Url을 반환한다.
	 * 
	 * @param w2xPath w2xPath 파라미터
	 * @return 로그인 페이지 Url
	 */
	private String getLoginPage(String w2xPath) {
		System.out.println("[/InitController.java] [getLoginPage()] ==> [TEST_01] [로그인 페이지 Url 반환] [1. 로그인 여부]"+ userInfo.isLogined() +"[w2xPath]"+ w2xPath );
		
		String movePage = w2xPath;
	 
		// session이 없을 경우 login 화면으로 이동.
		if (!userInfo.isLogined()) {
			// session이 있고 w2xPath가 없을 경우 index화면으로 이동.
			movePage = PageURIUtil.getLoginPage();		// w5xml.login=/cm/main/login.xml
			System.out.println("[/InitController.java] [getLoginPage()] ==> [TEST_51] [1. session이 없을 경우] [movePage]"+ movePage );
			
		} else {
			if (movePage == null) {
				// DB 설정조회 초기 page 구성
				movePage = PageURIUtil.getIndexPageURI(userInfo.getMainLayoutCode());
				System.out.println("[/InitController.java] [getLoginPage()] ==> [TEST_52] [2. session아 존재할 경우] [getMainLayoutCode]"+ userInfo.getMainLayoutCode() );
				
				// DB에 값이 저장되어 있지 않은 경우 기본 index화면으로 이동
				if (movePage == null) {
					movePage = PageURIUtil.getIndexPageURI();	
				}
			}
		}
		System.out.println("[/InitController.java] [getLoginPage()] ==> [TEST_91] [movePage]"+ movePage ); 
		
		return movePage;
	}
}
