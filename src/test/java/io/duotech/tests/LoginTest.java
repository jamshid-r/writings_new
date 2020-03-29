package io.duotech.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.duotech.pages.LoginPage;
import io.duotech.utils.ConfigReader;

public class LoginTest extends TestBase {
	
	@Test
	public void testRun() {
		System.out.println("Test run");
	}
	
	@Test
	public void login() {
		
		driver.findElement(By.xpath("//input[@class='txt'][@type='text']")).sendKeys(ConfigReader.getConfiguration("usrname"));
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(ConfigReader.getConfiguration("password"));
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		Assert.assertTrue(driver.getTitle().contains("Web Orders"));	
		
	}
	
	
	@Test
	public void loginPOMPattern() {
		logger = reporter.createTest("Positive Login Test");
		
		LoginPage loginPage = new LoginPage();
		loginPage.positiveLogin();
		Assert.assertTrue(driver.getTitle().contains("Web Orders"));
		
		logger.pass("Verified that the title contains \"Web Orders\"");
		
	}
	
	@Test
	public void loginPOMPatternNegative() {
		LoginPage loginPage = new LoginPage();
		loginPage.usernameField.sendKeys(new StringBuilder(ConfigReader.getConfiguration("usrname")).reverse().toString());
		loginPage.passwordField.sendKeys(new StringBuilder(ConfigReader.getConfiguration("password")).reverse().toString());
		loginPage.loginButton.click();
		String expected = "Invalid Login or Password.";
		String actual = loginPage.errorMessage.getText();
		Assert.assertEquals(actual, expected);
	}
	
	
	
	
	

}
