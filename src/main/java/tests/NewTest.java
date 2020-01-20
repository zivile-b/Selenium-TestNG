package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import pageobject.*;

public class NewTest {

	public WebDriver driver;

	@Test
	public void loginToQaontime() throws InterruptedException {
		driver.get("http://qaontime.com/register/client/index.php?folder=5");
		WebElement addIssue = driver.findElement(By.linkText("Add Issue"));
		addIssue.click();

		Thread.sleep(1500);

		WebElement issueNameField = driver.findElement(By.id("field-issues-issueName"));
		issueNameField.sendKeys("zivile");

		WebElement descriptionTextField = driver.findElement(By.id("field-issues-descriptionText"));
		issueNameField.sendKeys("neisgyvens");

		WebElement okSubmitButton = driver.findElement(By.id("field-issues-okSubmit"));
		okSubmitButton.click();
		Thread.sleep(1500);
	}

	@Test

	public void Should_NotAbleCreateIssue_when_issueNotProvided() throws InterruptedException {

		driver.get("http://qaontime.com/register/client/index.php?folder=5");
		WebElement addIssue = driver.findElement(By.linkText("Add Issue"));
		addIssue.click();

		Thread.sleep(1500);

		WebElement okSubmitButton = driver.findElement(By.id("field-issues-okSubmit"));
		okSubmitButton.click();

		WebElement requireValueIsMissingError = driver.findElement(By.xpath("//*[@id=\"body\"]/div/p[1]"));
		Assert.assertEquals(requireValueIsMissingError.getText(), "Some of the values you entered are incorrect.");

	}

	@Test(timeOut = 1000)
	public void Should_severity_valid() {

		driver.get("http://qaontime.com/register/client/issues/addissue.php?type=2");
		driver.findElement(By.id("field-issues-value4")).sendKeys("8");
		WebElement okButton = driver.findElement(By.id("field-issues-okSubmit"));
		okButton.click();
		Assert.assertTrue(driver.findElement(By.id("infobar-left")).isDisplayed(), "nepavyko surasti");
	}

	@Test(expectedExceptions = ArithmeticException.class)
	public void dividedByZero() {
		int i = 1 / 0;
	}

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://qaontime.com/register");
		Login login = new Login(driver);
		login.getLoginField().sendKeys(utils.getUserName());
		login.getpassField().sendKeys(utils.getPassword());
		login.getloginSubmitButton().click();

	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}