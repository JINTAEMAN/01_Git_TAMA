package com.inswave.sample.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inswave.util.WqLargeResultHandler;

@Repository("zipCodeStreetDao")
public interface ZipCodeStreetDao {

	public List selectZipCodeStreetByStreetPaging(Map param);

	public int selectZipCodeStreetByStreetTotalCnt(Map param);

}
