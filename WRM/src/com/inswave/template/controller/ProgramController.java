package com.inswave.template.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inswave.template.service.ProgramService;
import com.inswave.util.Result;

@Controller
public class ProgramController {

	@Autowired
	private ProgramService programService;

	/**
	 * searchProgram - 조회조건에 따른 프로그램관리 리스트를 조회한다.
	 * 
	 * @date 2017.12.22
	 * @param {} dma_search { TYPE:"프로그램명 또는 프로그램코드 또는 부모프로그램명 또는 프로그램레벨", CONTENTS:"검색어", IS_USE:"사용여부" }
	 * @returns mv dlt_program ( 프로그램관리 리스트 ) author InswaveSystems
	 * @example
	 */
	@RequestMapping("/program/searchProgram")
	public @ResponseBody Map<String, Object> searchProgram(@RequestBody Map<String, Object> param) {
		Result result = new Result();
		try {
			result.setData("dlt_program", programService.selectProgram((Map) param.get("dma_search")));
			result.setMsg(result.STATUS_SUCESS, "프로그램 리스트가 조회되었습니다.");
		} catch (Exception ex) {
			ex.printStackTrace();
			result.setMsg(result.STATUS_ERROR, "프로그램 리스트를 가져오는 도중 오류가 발생하였습니다.", ex);
		}
		return result.getResult();
	}

	/**
	 * updateProgram - 프로그램관리 리스트를 등록 수정 삭제 한다.
	 * 
	 * @date 2017.12.22
	 * @param {} dlt_program ( 프로그램관리 상태인( C,U,D ) 리스트 ), dma_search { TYPE:"프로그램명 또는 프로그램코드 또는 부모프로그램명 또는 프로그램레벨", CONTENTS:"검색어" }
	 * @returns mv dlt_result ( 입력,수정,삭제된 건수 및 상태 ), dlt_program ( 프로그램관리 리스트 ) author InswaveSystems
	 * @example
	 */
	@RequestMapping("/program/updateProgram")
	public @ResponseBody Map<String, Object> updateProgram(@RequestBody Map<String, Object> param) {
		Result result = new Result();
		try {
			Map hash = programService.saveProgram((List) param.get("dlt_program"));
			result.setData("dma_result", hash);
			result.setData("dlt_program", programService.selectProgram((Map) param.get("dma_search")));
			result.setMsg(result.STATUS_SUCESS, "프로그램관리 정보가 저장 되었습니다. 입력 : " + (String) hash.get("ICNT") + "건, 수정 : " + (String) hash.get("UCNT") + "건, 삭제 : "
					+ (String) hash.get("DCNT") + "건");
		} catch (Exception ex) {
			result.setMsg(result.STATUS_ERROR, "프로그램관리 정보 저장도중 오류가 발생하였습니다.", ex);
		}
		return result.getResult();
	}

	/**
	 * updateProgram - 프로그램관리 리스트를 등록 수정 삭제 한다.
	 * 
	 * @date 2017.12.22
	 * @param {} dlt_program ( 상태인( C,U,D ) 프로그램 리스트 ), dlt_programAuthority ( 상태인( C,U,D ) 프로그램별 권한 리스트 )
	 * @returns mv dlt_result ( 입력,수정,삭제된 건수 및 상태 ), dlt_program ( 프로그램관리 리스트 ) author InswaveSystems
	 * @example
	 */
	@RequestMapping("/program/saveProgramAll")
	public @ResponseBody Map<String, Object> saveProgramAll(@RequestBody Map<String, Object> param) {
		Result result = new Result();
		try {
			Map hash = programService.saveProgramAll((List) param.get("dlt_program"), (List) param.get("dlt_programAuthority"));

			result.setData("dma_result_All", hash);
			result.setMsg(result.STATUS_SUCESS,
					"프로그램 별 권한 정보가 저장 되었습니다. 입력 프로그램 건수: " + (String) hash.get("ICNT_MENU") + "건  ::  입력 프로그램 권한 건수: " + (String) hash.get("ICNT_ACCESS")
							+ "건 :: 수정 프로그램 건수: " + (String) hash.get("UCNT_MENU") + "건  ::  수정 프로그램 권한 건수: " + (String) hash.get("UCNT_ACCESS")
							+ "건  ::  삭제 프로그램 건수: " + (String) hash.get("DCNT_MENU") + "건  ::  삭제 프로그램 권한 건수: " + (String) hash.get("DCNT_ACCESS") + "건");
		} catch (Exception ex) {
			result.setMsg(result.STATUS_ERROR, "권한 및 권한별 사원정보 삭제도중 오류가 발생하였습니다.", ex);
		}
		return result.getResult();
	}

	/**
	 * searchProgramAuthority - 조회조건에 따른 프로그램별 접근 프로그램 리스트를 조회한다.
	 * 
	 * @date 2017.12.22
	 * @param {} dma_program ( 프로그램관리 리스트 )
	 * @returns mv dlt_programAuthority ( 프로그램별 권한 리스트 ) author InswaveSystems
	 * @example
	 */
	@RequestMapping("/program/searchProgramAuthority")
	public @ResponseBody Map<String, Object> searchProgramAuthority(@RequestBody Map<String, Object> param) {
		Result result = new Result();
		try {
			result.setData("dlt_programAuthority", programService.selectProgramAuthority((Map) param.get("dma_program")));
			result.setMsg(result.STATUS_SUCESS, "프로그램별 권한 리스트가 조회되었습니다.");
		} catch (Exception ex) {
			ex.printStackTrace();
			result.setMsg(result.STATUS_ERROR, "프로그램별 권한 리스트를 가져오는 도중 오류가 발생하였습니다.", ex);
		}
		return result.getResult();
	}

	/**
	 * updateProgramAuthority - 프로그램별 접근 프로그램 리스트를 등록 수정 삭제 한다.
	 * 
	 * @date 2017.12.22
	 * @param {} dlt_programAuthority ( 프로그램관리 상태인( C,U,D ) 리스트 ), dma_program { MENU_CD:"프로그램코드" }
	 * @returns mv dlt_result ( 입력,수정,삭제된 건수 및 상태 ), dlt_programAuthority ( 프로그램별 접근 프로그램 리스트 ) author InswaveSystems
	 * @example
	 */
	@RequestMapping("/program/updateProgramAuthority")
	public @ResponseBody Map<String, Object> updateProgramAuthority(@RequestBody Map<String, Object> param) { 
		Result result = new Result();
		try {
			Map hash = programService.saveProgramAuthority((List) param.get("dlt_programAuthority"));
			result.setData("dma_result", hash);
			result.setData("dlt_programAuthority", programService.selectProgramAuthority((Map) param.get("dma_program")));
			result.setMsg(result.STATUS_SUCESS, "프로그램별 권한 리스트가 저장 되었습니다. 입력 : " + (String) hash.get("ICNT") + "건, 수정 : " + (String) hash.get("UCNT")
					+ "건, 삭제 : " + (String) hash.get("DCNT") + "건");
		} catch (Exception ex) {
			result.setMsg(result.STATUS_ERROR, "프로그램별 권한 리스트 저장도중 오류가 발생하였습니다.", ex);
		}
		return result.getResult();
	}

	/**
	 * excludeSelectProgramAuthority - 조회조건에 따른 프로그램별 접근 프로그램 등록 리스트를 조회한다.
	 * 
	 * @date 2017.12.22
	 * @param {} dma_program { TYPE:"권한명 또는 권한코드", CONTENTS:"검색어", MENU_CD:"프로그램코드" }
	 * @returns mv dlt_programAuthority ( 프로그램별 접근 프로그램 리스트 ) author InswaveSystems
	 * @example
	 */
	@RequestMapping("/program/excludeSelectProgramAuthority")
	public @ResponseBody Map<String, Object> excludeSelectProgramAuthority(@RequestBody Map<String, Object> param) {
		Result result = new Result();
		try {
			result.setData("dlt_programAuthority", programService.excludeSelectProgramAuthority((Map) param.get("dma_search")));
			result.setMsg(result.STATUS_SUCESS, "프로그램별 권한 리스트가 조회되었습니다.");
		} catch (Exception ex) {
			ex.printStackTrace();
			result.setMsg(result.STATUS_ERROR, "프로그램별 권한 리스트를 가져오는 도중 오류가 발생하였습니다.", ex);
		}
		return result.getResult();
	}
}
