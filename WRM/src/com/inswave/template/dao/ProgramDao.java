package com.inswave.template.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("programDao")
public interface ProgramDao {

	// 프로그램관리 조회
	public List<Map> selectProgram(Map param);

	// 프로그램관리 C, U, D
	public int insertProgram(Map param);

	public int deleteProgram(Map param);

	public int updateProgram(Map param);

	// 프로그램별 접근프로그램 조회
	public List<Map> selectProgramAuthority(Map param);

	public List<Map> excludeSelectProgramAuthority(Map param);

	// 프로그램별 접근프로그램 C, D
	public int insertProgramAuthority(Map param);
	
	public int updateProgramAuthority(Map param);

	public int deleteProgramAuthority(Map param);
}
