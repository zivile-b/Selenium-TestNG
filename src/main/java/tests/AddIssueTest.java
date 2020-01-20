package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import pageobject.*;
import utils.Random;
import utils.Utils;

import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
public class AddIssueTest {
public WebDriver driver;
public CreateIssue createIssue;
Utils utils = new Utils();

	@Test
	public void Should_Create_Issue_Good_One() throws InterruptedException {
		createIssue.add("zi", utils.getPassword(), "Active", "Fixed", "1", "ba");
		Assert.assertTrue(createIssue.getBug().isDisplayed(), "ok");
	}		

	@Test
	public void Should_Not_Create_Issue_Without_Name() throws InterruptedException {
		createIssue.add("", " ", " ", " ", "1", "ba");
		Assert.assertEquals(createIssue.getCommentValueIncorect().getText(),"Some of the values you entered are incorrect.", "ok");
	}

	@Test
	public void Should_Create_Issue_Whith_Space_Before_Name() throws InterruptedException {
		createIssue.add("          laba", "", "Active", "", "1", "susiskure su tarpu name");
		Assert.assertTrue(createIssue.getBug().isDisplayed(), "ok");
	}
	
	@Test
	public void Should_not_Create_Issue_with_severity_bigger_3() throws InterruptedException {
		createIssue.add("bfds5", " ", " ", " ", "5", "Incorrect value: Number is too big.");
		Assert.assertEquals(createIssue.getSeverityIncorect().getText(),"Incorrect value: Number is too big.", "ok");
	}

	@Test
	public void Should_random_write_asignedTo() throws InterruptedException {
		Random r = new Random();
		
		createIssue.add(r.getAlphaNumericString(15), " ", "Active", " ", r.getRandomInteger(1, 3), r.getAlphaNumericString(45));
		Assert.assertTrue(driver.findElement(By.cssSelector("#body > table > tbody > tr:nth-child(1) > td.top-pane > div > h2")).isDisplayed());
	}

	@Test
	public void Should_random_write() throws InterruptedException {
		Random r = new Random();
		createIssue.add(r.getAlphaNumericString(15), " ", "Active", " ", r.getRandomInteger(1, 3), r.getAlphaNumericString(45));
		Assert.assertTrue(driver.findElement(By.cssSelector("#body > table > tbody > tr:nth-child(1) > td.top-pane > div > h2")).isDisplayed());
	}
 
	@Test
	public void Should_NotBeAble_ToCreateIssue_When_AsignedTo_isInvalid() throws InterruptedException {
		createIssue.add("zi", "invalid", "Active", "Fixed", "1", "ba");
		Assert.assertEquals(createIssue.getAsignedToError().getText(),"Incorrect value: No matching item is selected.", "Ok");
	}

	@Test
	public void Should_NotBeAble_ToCreateIssue_When_Status_isInvalid() throws InterruptedException {
		createIssue.add("zi", utils.getPassword(), "invalid", "Fixed", "1", "ba");
		Assert.assertEquals(createIssue.getStatusError().getText(), "Incorrect value: No matching item is selected.", "ok");
	}

	@Test
	public void Should_NotBeAble_ToCreateIssue_When_Reason_isInvalid() throws InterruptedException {
		createIssue.add("zi", utils.getPassword(), "Active", "blabla", "1", "ba");
		Assert.assertEquals(createIssue.getReasonError().getText(), "Incorrect value: No matching item is selected.", "ok");
	}

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Utils utils = new Utils();
		Login login = new Login(driver);
		login.login(utils.getUserName(), utils.getPassword());

		createIssue = new CreateIssue(driver);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
