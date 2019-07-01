package selenium.testngdemo;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

public class TestNGDemo {
	public WebDriver driver;
	private static String driverPath = File.separator + System.getProperty("user.home") + File.separator + "selenium"
			+ File.separator;

	@BeforeMethod
	public void initChrome() {
		System.out.println(System.getProperty("user.home"));
		System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
		driver = new ChromeDriver();
		long id = Thread.currentThread().getId();
		System.out.println("Before test method. Thread id is: " + id);
	}

	// @BeforeTest
	public void initFirefox() {

		System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver.exe");
		driver = new FirefoxDriver();
	}

	@Parameters({ "url" })
	@Test
	public void demoTest(String url) {
		try {
			long id = Thread.currentThread().getId();
			System.out.println("demoTest method. Thread id is: " + id);
			driver.get(url);
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void demoTest2() {
		long id = Thread.currentThread().getId();
		System.out.println("demoTest2 method. Thread id is: " + id);
		try {
			driver.get("http://www.google.com");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test(dataProvider = "dp")
	public void demoTest3(String url) {
		long id = Thread.currentThread().getId();
		System.out.println("demoTest3 method. Thread id is: " + id);
		try {
			driver.get(url);
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterMethod(alwaysRun = true)
	public void close() {
		long id = Thread.currentThread().getId();
		System.out.println("close-method. Thread id is: " + id);
		driver.quit();
	}

	@DataProvider(name = "dp")
	private Object[][] getData() {
		return new Object[][] {
			{ "http://www.facebook.com" }, 
			{ "http://www.messenger.com" }, 
		};
	}

}
