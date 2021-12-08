package com.inswave.template.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("menuDao")
public interface MenuDao {

	// 메뉴관리 조회
	public List<Map> selectMenu(Map param);
	
	// 메뉴관리 C, U, D
	public int insertMenu(Map param);

	public int deleteMenu(Map param);

	public int updateMenu(Map param);
}
