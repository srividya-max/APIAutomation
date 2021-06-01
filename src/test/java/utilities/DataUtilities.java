package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import base.BaseTest;

public class DataUtilities {
	
	public String ReadAccountProperties(String AccountFeildType) throws IOException {
		BaseTest.sProperties = new Properties();
		FileInputStream fis = new FileInputStream(AppConstants.USERACCOUNTS_PROPERTYFILE_PATH);
		BaseTest.sProperties.load(fis);
		return BaseTest.sProperties.getProperty(AccountFeildType);
		
	}
	
	public String ReadPageURLproperties(String PageURLKey) throws IOException {
		BaseTest.sProperties = new Properties();
		FileInputStream fis = new FileInputStream(AppConstants.PAGE_URL_PROPERTYFILE_PATH);
		BaseTest.sProperties.load(fis);
		return BaseTest.sProperties.getProperty(PageURLKey);
		
	}
	
	public String ReadConstantsproperties(String sConstant) throws IOException {
		BaseTest.sProperties = new Properties();
		FileInputStream fis = new FileInputStream(AppConstants.CONSTANT_PROPERTYFILE_PATH);
		BaseTest.sProperties.load(fis);

		return BaseTest.sProperties.getProperty(sConstant);
		
	}
	
	public static String[][] readXlData(String path, String sheetName) throws IOException {
		FileInputStream fs=new FileInputStream(AppConstants.USERDETAILS_EXCELFILE_PATH);
		HSSFWorkbook wb=new HSSFWorkbook(fs);
		HSSFSheet sheet=wb.getSheet(sheetName);
		int rowCount=sheet.getLastRowNum()+1;
		int colCount=sheet.getRow(0).getLastCellNum();
		String[][] data=new String[rowCount][colCount];
		for(int i=0;i<rowCount;i++){
			for(int j=0;j<colCount;j++){
				int cellType=sheet.getRow(i).getCell(j).getCellType();
				if(cellType== HSSFCell.CELL_TYPE_NUMERIC){
					int val=(int)sheet.getRow(i).getCell(j).getNumericCellValue();
					data[i][j]=String.valueOf(val);
				}
				else
					data[i][j]=sheet.getRow(i).getCell(j).getStringCellValue();
			}
		}
		return (data);
	}

}
