package base;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import utilities.AppConstants;
//import utilities.CommonUtilities;
import utilities.DataUtilities;

public class BaseTest {
	
	public static WebDriver driver=null;

	public static ExtentReports extent = null;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentTest test = null; //logger

	public static String sReportTime;
	public static Properties sProperties;
	
	//public static CommonUtilities oCommonUtilities = new CommonUtilities();
	public static DataUtilities oDataUtils = new DataUtilities();
	
	public static String sToken;

	public SoftAssert sa = new SoftAssert();
	
	
	
	@BeforeTest
	public void setup() {
		Initializereports();
	}
	
	@AfterTest
	public void tearDown() {
		extent.flush();
	}

	public void Initializereports() {
		sReportTime = new SimpleDateFormat("yyyymmddhhmm").format(new Date());
		extent = new ExtentReports();
		htmlReporter = new ExtentHtmlReporter(AppConstants.EXTENT_HTML_REPORT_PATH +"\\"+sReportTime +".html");
		extent.attachReporter(htmlReporter);
	}
	
	public void hashMap() {
		Map<String,String> map = new HashMap();
		map.put("token", sToken);
	}
	

	}
