package Exercises;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.TestBase;

import java.time.Duration;

public class C07_VerifyTestCasesPage extends TestBase {
    @Test
    public void test07() {
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

        // Click on 'Test Cases' button
        WebElement testCasesbutton = driver.findElement(By.xpath("//*[text()=' Test Cases']"));
        testCasesbutton.click();

        // Verify user is navigated to test cases page successfully
        String expectedTestCaseTitle = "Test Cases";
        String actualTestCaseTitle = driver.getTitle();
        Assert.assertTrue(actualTestCaseTitle.contains(expectedTestCaseTitle));

        WebElement testCasesText = driver.findElement(By.xpath("//b[text()='Test Cases']"));
        Assert.assertTrue(testCasesText.isDisplayed());
    }
}
