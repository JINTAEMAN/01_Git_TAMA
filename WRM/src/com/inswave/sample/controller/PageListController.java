package com.inswave.sample.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.inswave.sample.service.PageListService;
import com.inswave.sample.service.ZipCodeService;
import com.inswave.util.Result;
import com.inswave.util.UserInfo;

@Controller
public class PageListController {

	@Autowired
	private UserInfo userInfo; // 로그인된 사용자의 정보를 사용하기 위한 객체

	@Autowired
	private PageListService pageListService;

	/**
	 * 페이징 처리에서 사용하는 조회
	 * 
	 * @date 2017.12.22
	 * @param argument 파라미터 정보
	 * @returns <ModelAndView> 반환 변수 및 객체
	 * @author InswaveSystems
	 * @example
	 */
	@RequestMapping("/page/selectPageInfo")
	public @ResponseBody Map<String, Object> selectPageInfo(@RequestBody Map<String, Object> param) {
		String empCd = null;
		Map updateCnt = null;
		List updateList = null;
		Result result = new Result();
		try {
			empCd = userInfo.getUserId(); // 로그인 된 사용자 코드를 가져온다.
			param.put("EMP_CD", empCd);

			Map searchMap = (Map) param.get("dma_search");
			String totalSearch_Yn = (String) searchMap.get("TOTAL_YN"); // 총건수 조회 여부

			if (totalSearch_Yn.equals("Y")) {
				updateCnt = pageListService.selectTotalCNT();
				result.setData("TOTAL_CNT", updateCnt);
			} else {
				Map initMap = null;
				result.setData("TOTAL_CNT", initMap);
			}

			updateList = pageListService.select(searchMap);

			result.setData("dlt_update", updateList);
			result.setMsg(result.STATUS_SUCESS, "리스트가 조회 되었습니다.");
		} catch (Exception ex) {
			result.setMsg(result.STATUS_ERROR, "리스트를 가져오는 도중 오류가 발생하였습니다,", ex);
		}
		return result.getResult();
	}

	/**
	 * 전체 데이터 한번에 조회
	 * 
	 * @date 2017.12.22
	 * @param argument 파라미터 정보
	 * @returns <ModelAndView> 반환 변수 및 객체
	 * @author InswaveSystems
	 * @example
	 */
	@RequestMapping("/page/selectPageList")
	public @ResponseBody Map<String, Object> selectPageList() {
		Result result = new Result();
		String empCd = null;
		Map updateCnt = null;
		List updateSelectList = null;
		Map<String, Object> param = new HashMap<String, Object>();

		try {
			empCd = userInfo.getUserId(); // 로그인 된 사용자 코드를 가져온다.
			param.put("EMP_CD", empCd);

			updateSelectList = pageListService.selectList();

			result.setData("dlt_update", updateSelectList);
			result.setMsg(result.STATUS_SUCESS, "리스트가 조회 되었습니다.");
		} catch (Exception ex) {
			result.setMsg(result.STATUS_ERROR, "리스트를 가져오는 도중 오류가 발생하였습니다,", ex);
		}
		return result.getResult();
	}
}
