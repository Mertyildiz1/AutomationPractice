package Exercises;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.TestBase;

import java.time.Duration;

public class C05_RegisterUserWithExistingEmail extends TestBase {
    @Test
    public void test05() {

        // Navigate to url 'http://automationexercise.com'
        String url = "http://automationexercise.com";
        driver.get(url);

        // Verify that home page is visible successfully
        WebElement featuresItemsText = driver.findElement(By.xpath("//h2[@class='title text-center']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(featuresItemsText));
        Assert.assertTrue(featuresItemsText.isDisplayed());

        String expectedTitle = "Automation Exercise";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);

        // Click on 'Signup / Login' button
        WebElement signUpLoginButton = driver.findElement(By.xpath("//i[@class='fa fa-lock']"));
        signUpLoginButton.click();

        // Verify 'New User Signup!' is visible
        String newUserSıgnUpText = driver.findElement(By.xpath("//*[text()='New User Signup!']")).getText();
        String expectedText = "New User Signup!";
        Assert.assertEquals(expectedText, newUserSıgnUpText);

        // Enter name and already registered email address
        WebElement nameInputArea = driver.findElement(By.xpath("//input[@type='text']"));
        nameInputArea.sendKeys("Mert", Keys.TAB, "1yildizmert@gmail.com", Keys.TAB, Keys.ENTER);

        // Verify error 'Email Address already exist!' is visible
        String expectedExistText = "Email Address already exist!";
        WebElement actualExistText = driver.findElement(By.xpath("//*[.='Email Address already exist!']"));
        Assert.assertTrue(actualExistText.isDisplayed());
        Assert.assertEquals(expectedExistText, actualExistText.getText());
    }
}
