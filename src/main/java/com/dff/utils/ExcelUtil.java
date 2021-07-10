package com.dff.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dff.configs.GlobalVariables;

public class ExcelUtil implements GlobalVariables{

	private XSSFWorkbook excelWorkbook;
	private static final Logger log = LogManager.getLogger(ExcelUtil.class);

	public synchronized void setExcelFile(String sheetPath) {
		try{
			FileInputStream excelFile = new FileInputStream(sheetPath);
			excelWorkbook = new XSSFWorkbook(excelFile);			
		} catch(Exception exp){
			log.info("Exception occured in setExcelFile: "+ exp);
		}		
	}

	public synchronized int getNumberOfRows(String sheetName) {
		XSSFSheet excelSheet = excelWorkbook.getSheet(sheetName);
		int numberOfRows = excelSheet.getPhysicalNumberOfRows();
		//log.info("Number Of Rows: "+numberOfRows);
		return numberOfRows;
	}

	public synchronized String getCellData(int rowNumb, int colNumb, String sheetName) throws Exception{
		try{
			XSSFSheet excelSheet = excelWorkbook.getSheet(sheetName);
			XSSFCell cell = excelSheet.getRow(rowNumb).getCell(colNumb);
			//log.info("Getting cell data.");
			if(cell.getCellType() == CellType.NUMERIC) {
				cell.setCellType(CellType.STRING);
			}
			String cellData = cell.getStringCellValue();
			return cellData;
		}
		catch(Exception exp){
			return "";
		}
	}

	public synchronized void clearColumnData(String sheetName, int colNumb, String excelFilePath) {
		int rowCount = getNumberOfRows(sheetName);
		XSSFRow row;
		XSSFSheet excelSheet = excelWorkbook.getSheet(sheetName);
		for(int i=1; i< rowCount; i++) {
			XSSFCell cell = excelSheet.getRow(i).getCell(colNumb);
			if(cell==null){
				row = excelSheet.getRow(i);
				cell = row.createCell(colNumb);
			}
			cell.setCellValue("");			
		}
		log.debug("Clearing column "+colNumb+" of Sheet: "+sheetName);
		writingDataIntoFile(excelFilePath);
	}

	public synchronized void setCellData(String result, int rowNumb, int colNumb, String excelFilePath, String sheetName) {	
		XSSFSheet excelSheet = excelWorkbook.getSheet(sheetName);
		XSSFRow row = excelSheet.getRow(rowNumb);
		XSSFCell cell = row.getCell(colNumb);
		log.debug("Setting results into the excel sheet.");
		if(cell==null){
			cell = row.createCell(colNumb);
		}
		cell.setCellValue(result);
		log.debug("Setting value into cell["+rowNumb+"]["+colNumb+"]: "+result);
		writingDataIntoFile(excelFilePath);		
	}

	private synchronized void writingDataIntoFile(String excelFilePath) {
		try{
			FileOutputStream fileOut = new FileOutputStream(excelFilePath);
			excelWorkbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		}catch(Exception exp){
			log.error("Exception occured in setCellData: ",exp);
		}
	}

	public synchronized List<String> getScenariosToRun(String sheetName, int runModeColumn, int testCaseColumn) {
		List<String> testList = new ArrayList<String>();
		try {
			int rowCount = getNumberOfRows(sheetName);
			String testCase;
			for(int i=1; i< rowCount; i++) {
				testCase = getTestScenario(i, runModeColumn, testCaseColumn, sheetName);
				if(testCase != null) {
					testList.add(testCase);
				}
			}
		}catch (Exception e) {
			log.error("Exeception Occured while adding data to List:\n", e);
		}
		return testList;
	}

	private String getTestScenario(int row, int runModeColumn, int testCaseColumn, String sheetName) {
		String testCaseName = null;
		try{
			if(getCellData(row, runModeColumn, sheetName).equalsIgnoreCase(RUN_MODE_YES)){
				testCaseName = getCellData(row, testCaseColumn, sheetName).trim();
				log.debug("Test Case to Run: "+testCaseName);
			} 
		} catch(Exception exp){
			log.error("Exception occured in getTestCaseRow: ", exp);
		}
		return testCaseName;
	}

	public Map<String, String> getData(String sheetName, int row) throws Exception{
		Map<String, String> dataMap = new HashMap<String, String>();
		XSSFSheet excelSheet = excelWorkbook.getSheet(sheetName);
		short lastColumn = excelSheet.getRow(row).getLastCellNum();
		for(int i=0; i<lastColumn; i++) {
			dataMap.put(getCellData(0, i, sheetName), getCellData(row, i, sheetName));
		}
		return dataMap;
	}
	
	public static void main(String[] args) throws Exception {		
		ExcelUtil excel = new ExcelUtil();
		excel.setExcelFile(DATA_FOLDER + WORKBOOK);
		System.out.println(excel.getScenariosToRun(SCENARIO_SHEET_NAME, RUN_MODE_COLUMN, TEST_CASE_COLUMN));
		for(String scenario : excel.getScenariosToRun(SCENARIO_SHEET_NAME, RUN_MODE_COLUMN, TEST_CASE_COLUMN)) {
			int testRow = excel.getNumberOfRows(scenario);
			for(int i=1; i<testRow; i++) {
				System.out.println(excel.getData(scenario, i));
			}
		}
	}
}
