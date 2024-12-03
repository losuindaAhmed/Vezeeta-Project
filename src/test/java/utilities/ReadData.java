package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ReadData {
	
	@Test(priority = 1,groups = {"Saintay","regration"}, dependsOnMethods = "login" )
	public void setup() throws IOException {
		//to read from exel we use 
		FileInputStream file=new FileInputStream("Location of excel file");
		//excellfill--workbook--sheet--row--cell
		
		XSSFWorkbook workbook=new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		
		//xsswork
	//	xssfsheet
		int rows= sheet.getLastRowNum();
		int cell=sheet.getRow(0).getLastCellNum();
		int cell =sheet.getRow(0).getLastCellNum();
		
		SoftAssert sa=new SoftAssert();
		
		SoftAssert dc=new SoftAssert();
		
		//hard assertion comper betw
		
		
		
	}

}
