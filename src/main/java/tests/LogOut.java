package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageobject.Login;
import utils.Utils;

public class LogOut {
	public WebDriver driver;
	public Login login;
	public AddIssueTest issue;
	Utils utils = new Utils();
	
	@Test
	public void Should_find_cookies() throws InterruptedException {
		Login login = new Login(driver);
		login.getLogOut().click();
		Cookie cookie = driver.manage().getCookieNamed("WebIssuesSID");
		
		driver.manage().deleteAllCookies();
		
		boolean noCookieFound = false;
		
		try {
			cookie.getValue();
		} catch (Exception e) {
			noCookieFound = true;
		}
		
		Assert.assertTrue(noCookieFound,"cookie was found");
	}
			
	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://qaontime.com/register");

		
		Login login = new Login(driver);
		login.login(utils.getUserName(), utils.getPassword());

		Assert.assertTrue(driver.manage().getCookies().toString().contains("WebIssuesSID"), "WebIssues cookies does exist");
	}
	

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}
}
