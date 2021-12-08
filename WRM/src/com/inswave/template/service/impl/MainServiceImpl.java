package com.inswave.template.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inswave.template.dao.SpReleaseInfoDao;
import com.inswave.template.service.MainService;

@Service("mainService")
public class MainServiceImpl implements MainService {

	@Resource(name = "spReleaseInfoDao")
	private SpReleaseInfoDao spReleaseDao;

	/**
	 * selectType=="S" : 요약본
	 */
	public List selectRelease(Map param) {

		List rs = null;
		String selectType = (String) param.get("selectType");

		if (selectType == "S") {
			rs = spReleaseDao.selectReleaseForSummary(param);
		}
		return rs;
	}

	/**
	 * 여러 건의 Release관리 데이터를 변경(등록, 수정, 삭제)한다.
	 * 
	 * @param param Client 전달한 데이터 맵 객체
	 */
	@Override
	public Map saveRelease(List param) {
		int iCnt = 0;
		int uCnt = 0;
		int dCnt = 0;

		for (int i = 0; i < param.size(); i++) {

			Map data = (Map) param.get(i);
			String rowStatus = (String) data.get("rowStatus");
			if (rowStatus.equals("C")) {
				iCnt += spReleaseDao.insertRelease(data);
			} else if (rowStatus.equals("U")) {
				uCnt += spReleaseDao.updateRelease(data);
			} else if (rowStatus.equals("D")) {
				dCnt += spReleaseDao.deleteRelease(data);
			}
		}
		Map result = new HashMap();
		result.put("STATUS", "S");
		result.put("ICNT", String.valueOf(iCnt));
		result.put("UCNT", String.valueOf(uCnt));
		result.put("DCNT", String.valueOf(dCnt));
		return result;
	}

	public Map selectReleaseCnt() {
		return spReleaseDao.selectReleaseCnt();
	}
}
