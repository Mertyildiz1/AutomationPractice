package Exercises;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.TestBase;

import java.time.Duration;

public class C03_LoginWithIncorrectEmailAndPassword extends TestBase {
    @Test
    public void test03(){
        Faker faker = new Faker();
        driver.get("http://automationexercise.com");

        //Verify that home page is visible successfully
        WebElement featuresItemsText = driver.findElement(By.xpath("//h2[@class='title text-center']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
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

        //Enter incorrect email address and password
        WebElement emailArea = driver.findElement(By.xpath("//*[@type='email']"));
        emailArea.sendKeys(faker.internet().emailAddress(), Keys.TAB,faker.internet().password(),Keys.TAB,Keys.ENTER);

        //Verify error 'Your email or password is incorrect!' is visible
        WebElement errorText = driver.findElement(By.xpath("//*[text()='Your email or password is incorrect!']"));
        String expectedErrorText = "Your email or password is incorrect!";

        Assert.assertTrue(errorText.isDisplayed());
        Assert.assertEquals(expectedErrorText,errorText.getText());
    }
}
