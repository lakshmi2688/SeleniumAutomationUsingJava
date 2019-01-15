package performancetesting;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.commons.*;


public class SearchPageTestng {
	
	long startTime;
	long endTime;
	long duration;
	double seconds;
	
	WebDriver driver;
	String baseUrl = "https://www.expedia.com/Flights";
	ExpediaSearchPage esp;
	static Logger log = (Logger) LogManager.getLogger(SearchPageTestng.class.getName());

	@BeforeClass
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lakshmi\\Desktop\\Selenium\\Selenium drivers download\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		esp = new ExpediaSearchPage(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test
	public void test() throws InterruptedException {
		startTime = System.nanoTime();
		driver.get(baseUrl);
		Thread.sleep(2000);
		esp.getFlyingFrom("SEA");
		Thread.sleep(2000);
		esp.getFlyingTo("MAA");
		Thread.sleep(2000);
		esp.getDeparting("01/28/2019");
		Thread.sleep(2000);
		esp.getReturning("04/26/2019");
		Thread.sleep(2000);
		esp.getTravellersCount();
		Thread.sleep(2000);
		esp.submitButton();
		endTime = System.nanoTime();
		duration = endTime - startTime;
		seconds = (double)duration / 1000000000.0;
		log.info("*****************************************");
		log.info("Time taken to execute this method: " + seconds + " seconds");
		System.out.println("Time taken to execute this method: " + seconds + " seconds");
	}

	
	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(5000);
		driver.quit();
	}
}
