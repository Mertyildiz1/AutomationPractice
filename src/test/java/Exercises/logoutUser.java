package Exercises;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class logoutUser {
    @Test
    public void test04(){
        String url = "http://automationexercise.com";
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        // Navigate to url 'http://automationexercise.com'
        driver.get(url);

        //Verify that home page is visible successfully
        WebElement featuresItemsText = driver.findElement(By.xpath("//h2[@class='title text-center']"));
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(featuresItemsText));
        Assert.assertTrue(featuresItemsText.isDisplayed());

        String expectedTitle = "Automation Exercise";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);

        // Click on 'Signup / Login' button

        WebElement signUpLoginButton = driver.findElement(By.xpath("//*[text()=' Signup / Login']"));
        signUpLoginButton.click();

        //Verify 'Login to your account' is visible
        WebElement loginToYourAccText = driver.findElement(By.xpath("//*[text()='Login to your account']"));
        wait.until(ExpectedConditions.visibilityOf(loginToYourAccText));
        Assert.assertTrue(loginToYourAccText.isDisplayed());

        //Enter correct email address and password
        WebElement emailArea = driver.findElement(By.xpath("//*[@type='email']"));
        emailArea.sendKeys("1yildizmert@gmail.com", Keys.TAB,"test123456",Keys.TAB,Keys.ENTER);

        //Verify that 'Logged in as username' is visible
        WebElement loggedInAs = driver.findElement(By.xpath("//*[text()=' Logged in as ']"));
        wait.until(ExpectedConditions.visibilityOf(loggedInAs));
        Assert.assertTrue(loggedInAs.isDisplayed());

        //Click 'Logout' button
        WebElement logoutButton = driver.findElement(By.xpath("//*[@class='fa fa-lock']"));
        logoutButton.click();

        //Verify that user is navigated to login page
        WebElement homeButton = driver.findElement(By.xpath("//*[text()=' Home']"));
        Assert.assertTrue(homeButton.isDisplayed());

        String expectedLoginPageTitle = "Automation Exercise - Signup / Login";
        Assert.assertEquals(expectedLoginPageTitle,driver.getTitle());
    }
}
