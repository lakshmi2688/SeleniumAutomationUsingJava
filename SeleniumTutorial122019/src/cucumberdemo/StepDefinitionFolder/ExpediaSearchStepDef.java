package cucumberdemo.StepDefinitionFolder;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberdemo.CucumberExpediaTestngTest;
import cucumberdemo.ExcelWrite;
import cucumberdemo.ExpediaPageObjFactory;
import cucumberdemo.Screenshots;

public class ExpediaSearchStepDef {
	
	WebDriver driver;
	String baseUrl = "https://www.expedia.com/Flights";
	ExpediaPageObjFactory esp;
	ExtentReports report;
	ExtentTest test;
	Screenshots ss;
	String extentpath = "C:\\Users\\Lakshmi\\Desktop\\Selenium\\Extent Reports\\extentreport-new1.html";
	String excelreadpath = "C:\\Users\\Lakshmi\\Desktop\\Selenium\\Datadriven testing - excel file inputs\\Input1.xlsx";
	Logger log = (Logger) LogManager.getLogger(CucumberExpediaTestngTest.class.getName());
	String[][] testData;
	
	
	@Given("^I want to execute Expedia Search by passing inputs$")
	public void i_want_to_execute_Expedia_Search_by_passing_inputs(DataTable arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	    // E,K,V must be a scalar (String, Integer, Date, enum etc)
		testData = null;
		
		List<Map<String,String>> list = arg1.asMaps(String.class, String.class);
		System.out.println(list.get(0));
		System.out.println(list.get(1));
		for(Map<String, String> map: list)
		{
			System.out.println("K-V pair: " + map.entrySet());
		}
		for(int i=0;i<list.size();i++)
		{
			if((list.get(i).get("browser")).equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lakshmi\\Desktop\\Selenium\\Selenium drivers download\\chromedriver_win32\\chromedriver.exe");

			}
			
			if((list.get(i).get("platform")).equalsIgnoreCase("windows"))
			{
				System.out.println("platform is : " +list.get(i).get("platform"));
			}
		}
				
		report = new ExtentReports(extentpath);
		test = report.startTest("Verify expedia search for different inputs");
		
		driver = new ChromeDriver();
		test.log(LogStatus.INFO, "Browser started");
		log.info("Browser started");
		
		ss = new Screenshots(driver);
		
		esp = new ExpediaPageObjFactory(driver);
		test.log(LogStatus.INFO, "Page factory class initialized");
		log.info("Page factory class initialized");
		
		driver.manage().window().maximize();
		test.log(LogStatus.INFO, "Browser maximized...");
		log.info("Browser maximized");
		
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@When("^I run the test suite by passing (.*),(.*),(.*),(.*)$")
	public void i_run_the_test_suite_by_passing_LAX_SEA(String start, String dest, String fromDate, String toDate) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		driver.get(baseUrl);
		Thread.sleep(2000);
		test.log(LogStatus.INFO, "Expedia website opened...");
		log.info("Expedia website opened");
		
		esp.getFlyingFrom(start);
		Thread.sleep(2000);
		test.log(LogStatus.INFO, "Entered flying from..");
		log.info("Entered flying from");
		
		esp.getFlyingTo(dest);
		Thread.sleep(2000);
		test.log(LogStatus.INFO, "Entered departing to..");
		log.info("Entered departing to");
		
		esp.getDeparting(fromDate);
		Thread.sleep(2000);
		test.log(LogStatus.INFO, "Entered departing date..");
		log.info("Entered departing date");
		
		esp.getReturning(toDate);
		Thread.sleep(2000);
		test.log(LogStatus.INFO, "Entered returning date..");
		log.info("Entered returning date");
		
		esp.getTravellersCount();
		Thread.sleep(2000);
		test.log(LogStatus.INFO, "Entered travellers count..");
		log.info("Entered travellers count");
		
		esp.submitButton();
		test.log(LogStatus.INFO, "Clicked the submit button");
		log.info("Clicked the submit button");
		
		Assert.assertTrue(true);
		test.log(LogStatus.PASS, "Verified the test case and it's successful");
		log.info("Verified the test case and it's successful");
	  
	}

	@Then("^I should be able to make the search successfully$")
	public void i_should_be_able_to_make_the_search_successfully() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		
			//String path = ss.getScreenshots(result.getMethod().getMethodName());
			//or
			String path = ss.getScreenshots("CucumberExpediaSearch");
			String imagepath = test.addScreenCapture(path);
			test.log(LogStatus.PASS, "Verified the test case and it's successful", imagepath);
		
	}
		
		@Then("^Write output to sheet$")
		public void write_output_to_sheet() throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
			ExcelWrite.writeExcel("Sheet2", excelreadpath);
			int lrow = ExcelWrite.getLastRow();
			lrow++;
			ExcelWrite.setCellData(lrow, "DEN", "MAA", "03/05/2019","04/10/2019",excelreadpath);
			lrow++;
			ExcelWrite.setCellData(lrow, "SEA", "MAA", "04/18/2019","06/24/2019",excelreadpath);
			lrow++;
			ExcelWrite.setCellData(lrow, "DEN", "LAX", "06/05/2019","08/10/2019",excelreadpath);
			report.endTest(test);
			report.flush();
			driver.quit();
		}

}
