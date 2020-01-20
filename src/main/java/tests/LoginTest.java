package tests;

import java.io.IOException;

import org.openqa.selenium.By;
//import java.util.concurrent.TimeUnit;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import bsh.util.Util;

import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import pageobject.*;
import utils.*;

public class LoginTest {

	public WebDriver driver;
	public Login login;
	public AddIssueTest issue;
	public Utils utils;
	
	@Test(groups = {"smooke", "checkintest"} )
	public void Should_Work_1() throws InterruptedException {
	
		utils = new Utils();
		String userName = utils.generatedString();
		Login login = new Login(driver);
		login.login(utils.getUserName(), utils.getPassword());

		Assert.assertTrue(driver.manage().getCookies().toString().contains("WebIssuesSID"), "WebIssues cookies does exist");
	}
	
	@Test
	public void Should_Not_Work_With_Bad_User_Name() throws InterruptedException {
		Login login = new Login(driver);
		login.login("zivile", utils.getPassword());

		Assert.assertEquals(login.getLoginMessage().getText(),"Incorrect value: Invalid login or password.", "ok");
		}

	@Test
	public void Should_Not_Work_With_Bad_Pass() throws InterruptedException {
		Login login = new Login(driver);
		login.login(utils.getUserName(), "yahoo.com");

		Assert.assertEquals(login.getLoginMessage().getText(),"Incorrect value: Invalid login or password.", "ok");
		}
	
	@Test
	public void Should_Not_Work_With_Bad_Pass_chine() throws InterruptedException {
		Login login = new Login(driver);
		login.login(utils.getUserName(), "晚上");

		Assert.assertEquals(login.getLoginMessage().getText(),"Incorrect value: Invalid login or password.", "ok");
		}
	
	@Test
	public void Should_Not_Work_With_Bad_Pass_php() throws InterruptedException {
		Login login = new Login(driver);
		login.login(utils.getUserName(), "<?php echo \"My first PHP script!\";?>");

		Assert.assertEquals(login.getLoginMessage().getText(),"Incorrect value: Invalid login or password.", "ok");
		}
	
	@Test
	public void Should_Not_Work_With_Bad_Pass_html() throws InterruptedException {
		Login login = new Login(driver);
		login.login(utils.getUserName(), "<html><title>Page Title</title></html>");
		Assert.assertEquals(login.getLoginMessage().getText(),"Incorrect value: Invalid login or password.", "ok");
		}
	
	@Test
	public void Should_Work_With_good_Pass_xlsx() throws InterruptedException {
		XLSXreader xlsx = new XLSXreader("test-data\\example.xlsx");
		Login login = new Login(driver);
		login.login(xlsx.getItem("username", 0), xlsx.getItem("password", 0));
		Assert.assertTrue(driver.findElement(By.cssSelector("#infobar-left")).isDisplayed());
		}
	
	@Test
	public void Should_Work_With_bad_Pass_xlsx() throws InterruptedException {
		XLSXreader xlsx = new XLSXreader("test-data\\example.xlsx");
		Login login = new Login(driver);
		login.login(xlsx.getItem("username", 1), xlsx.getItem("password", 1));
		Assert.assertEquals(login.getLoginMessage().getText(),"Incorrect value: Invalid login or password.", "ok");
	}
		
	@BeforeClass (groups = {"smooke", "checkintest"} )
	public void beforeClass() {
		utils = new Utils();
		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get(utils.getUrl());
	}


	@AfterClass (groups = {"smooke", "checkintest"} )
	public void afterClass() {
		// driver.quit();
	}

}