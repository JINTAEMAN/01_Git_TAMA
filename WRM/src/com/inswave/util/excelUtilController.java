package com.inswave.util;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.json.JSONObject;
import org.json.XML;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

//poi를 이용한 엑셀 다운로드시 필요한 내용들 
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Controller
public class excelUtilController {
	
	// Null 체크
	public static Boolean empty(Object obj) {
        if (obj instanceof String) return obj == null || "".equals(obj.toString().trim());
        else if (obj instanceof List) return obj == null || ((List) obj).isEmpty();
        else if (obj instanceof Map) return obj == null || ((Map) obj).isEmpty();
        else if (obj instanceof Object[]) return obj == null;
        else return obj == null;
    }
	
	// poi를 이용한 엑셀 다운로드 기능 
	@RequestMapping("/util/genExcel.do")
	public @ResponseBody Map genExcel(@RequestBody Map param, HttpServletRequest request, HttpServletResponse response) {
		Result result = new Result();
		Map hash = (Map) param.get("dma_request");
		String req = hash.get("req").toString();
		
		try {
			JSONObject xmlJSONObj = XML.toJSONObject(req);
			
			ObjectMapper mapper = new ObjectMapper();
			
			Map<Object,Object> map = mapper.readValue(xmlJSONObj.toString(), Map.class);
			
			//Workbook 생성
			Workbook xlsWb = new HSSFWorkbook(); // Excel 2007 이전 버전
			Workbook xlsxWb = new XSSFWorkbook(); // Excel 2007 이상
			
			// *** Sheet-------------------------------------------------
			// Sheet 생성
			Sheet sheet1 = xlsWb.createSheet("firstSheet");
			
			// 컬럼 너비 설정
			sheet1.setColumnWidth(0, 10000);
			sheet1.setColumnWidth(1, 10000);
			sheet1.setColumnWidth(2, 10000);
			sheet1.setColumnWidth(3, 10000);
			sheet1.setColumnWidth(4, 10000);
			// -----------------------------------------------------------
			 
			// *** Style--------------------------------------------------
			// Cell 스타일 생성
			CellStyle cellStyle1 = xlsWb.createCellStyle();
			CellStyle cellStyle2 = xlsWb.createCellStyle();
			 
			// 줄 바꿈
			cellStyle1.setWrapText(true);
			cellStyle2.setWrapText(true);
			 
			// Cell 색깔, 무늬 채우기
			// th 테두리
			cellStyle1.setBorderTop(CellStyle.BORDER_THIN);
			cellStyle1.setBorderLeft(CellStyle.BORDER_THIN);
			cellStyle1.setBorderRight(CellStyle.BORDER_THIN);
			cellStyle1.setBorderBottom(CellStyle.BORDER_THIN);
			// th 배경색
			cellStyle1.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
			cellStyle1.setFillPattern(CellStyle.SOLID_FOREGROUND);
			
			// th 정렬
			cellStyle1.setAlignment(CellStyle.ALIGN_CENTER);
			
			// td 테두리
			cellStyle2.setBorderTop(CellStyle.BORDER_THIN);
			cellStyle2.setBorderLeft(CellStyle.BORDER_THIN);
			cellStyle2.setBorderRight(CellStyle.BORDER_THIN);
			cellStyle2.setBorderBottom(CellStyle.BORDER_THIN);
			// td 배경색
			cellStyle2.setFillForegroundColor(HSSFColor.WHITE.index);
			cellStyle2.setFillPattern(CellStyle.SOLID_FOREGROUND);
			
			// td 정렬
			cellStyle2.setAlignment(CellStyle.ALIGN_LEFT);
			
			Row row = null;
			Cell cell = null;
			//----------------------------------------------------------
			
			int rowCount = 0;
			int cellCount = 0;
			
			List _tableList = (List) map.get("table");
			int tableCnt = _tableList.size();
			
			for(int i1=0; i1<tableCnt; i1++){
				Map _tableMap = (Map) _tableList.get(i1);
				List _tbodyList = (List) _tableMap.get("tbody");
				int tbodyCnt = _tbodyList.size();
				
				for(int i2=0; i2<tbodyCnt; i2++){
					Map _tbodyMap = (Map) _tbodyList.get(i2);
					
					if(!empty(_tbodyMap.get("style"))){
						String _tbodyStyle = _tbodyMap.get("style").toString();
						
						//if(_tbodyStyle.equals("display: none; visibility: hidden;")){
						//}
					}else{
						List _tr = (List) _tbodyMap.get("tr");
						int cnt = _tr.size();
						
						for(int i3=0; i3<cnt; i3++){
							//System.out.println("tr count ==> " + cnt + " ::: table 순번 ====>> " + i1 + " :: tbody 순번 ==>" + i2 + " :: tr 순번 ==>" + i3 + ":: rowCount==>" + rowCount);
							
							row = sheet1.createRow(rowCount);
							Map _trMap = (Map) _tr.get(i3);
							
							//System.out.println("tr count ==> " + cnt + " ::aaaa tr 순번 ==>>" + i3 + ":: rowCount==>" + rowCount);
							
							if(!empty(_trMap.get("style"))){
								String _trStyle = _trMap.get("style").toString();
								
								//if(_trStyle.equals("display: none; visibility: hidden;")){
								//}
							}else{
								if(!empty(_trMap.get("th"))){
									List _th = (List) _trMap.get("th");
									int thCnt = _th.size();
									cellCount = 0;
									for(int j=0; j<thCnt; j++){
										Map _thMap = (Map) _th.get(j);
										cell = row.createCell(cellCount);
										
										String thStrVal = _thMap.get("content").toString();
										
										cell.setCellValue(thStrVal);
										cell.setCellStyle(cellStyle1); // 셀 스타일 적용
										
										//System.out.println("table 순번 ====>> " + i1 + " :: tbody 순번 ==>" + i2 + " :: tr 순번 ==>" + i3 + ":: th 순번 ===>>" + j);
										
										if(!empty(_thMap.get("colspan"))){
											String _strColMerge = _thMap.get("colspan").toString();
											int colSpan = Integer.parseInt(_strColMerge)-1;
											int colEnd = j + colSpan;
											
											cellCount++;
											cell = row.createCell(cellCount);
											cell.setCellStyle(cellStyle1);
											sheet1.addMergedRegion(new CellRangeAddress(rowCount, rowCount, j, colEnd));
										}
										cellCount++;
									}
								}
								
								if(!empty(_trMap.get("td"))){
									List _td = (List) _trMap.get("td");
									int tdCnt = _td.size();
									cellCount = 0;
									
									for(int j=0; j<tdCnt; j++){
										Map _tdMap = (Map) _td.get(j);
										Map _tdDivMap = (Map) _tdMap.get("div");
										
										String _tdStr = _tdDivMap.get("content").toString();
										
										cell = row.createCell(cellCount);
										cell.setCellValue(_tdStr);
										cell.setCellStyle(cellStyle2); // 셀 스타일 적용
										
										cellCount++;
									}
								}
								rowCount++;	
							}
						}
					}
				}
			}
			
			try {
			    //Local은 아래와 같이 로컬경로로 변경하여 테스트
				File xlsFile = new File("C:\\WRM\\RESOURCE\\doubleGrid\\doubleGridExcel.xls");
			    //서버는 서버 경로로 테스트 
			    //File xlsFile = new File("/xvfs/ser-poc/webapps/ROOT/template/down/testExcel.xls");
				FileOutputStream fileOut = new FileOutputStream(xlsFile);
			    xlsWb.write(fileOut);
			} catch (FileNotFoundException e) {
			    e.printStackTrace();
			} catch (IOException e) {
			    e.printStackTrace();
			}
			
			result.setMsg( result.STATUS_SUCESS,"Excel Down Success");
		}catch(Exception ex){
			result.setMsg( result.STATUS_ERROR, "Excel Down Fail", ex);
		}
		return result.getResult();
	}
	
}
