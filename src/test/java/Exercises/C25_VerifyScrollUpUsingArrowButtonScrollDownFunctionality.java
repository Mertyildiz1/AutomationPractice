package Exercises;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.TestBase;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class C25_VerifyScrollUpUsingArrowButtonScrollDownFunctionality extends TestBase {
    @Test
    public void test25() throws AWTException, InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Robot robot = new Robot();
        Actions actions = new Actions(driver);

        // Navigate to url 'http://automationexercise.com'
        String url = "http://automationexercise.com";
        driver.get(url);

        //Verify that home page is visible successfully
        WebElement featuresItemsText = driver.findElement(By.xpath("//h2[@class='title text-center']"));
        wait.until(ExpectedConditions.visibilityOf(featuresItemsText));
        Assert.assertTrue(featuresItemsText.isDisplayed());

        String expectedTitle = "Automation Exercise";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);

        // Scroll down page to bottom - Verify 'SUBSCRIPTION' is visible
        Long initialY = (Long) js.executeScript("return window.pageYOffset;");

        robot.keyPress(KeyEvent.VK_DOWN);
        Thread.sleep(500);
        robot.keyRelease(KeyEvent.VK_DOWN);
        Thread.sleep(1000);

        Long bottomY = (Long) js.executeScript("return window.pageYOffset;");
        if (bottomY > initialY) {
            robot.keyPress(KeyEvent.VK_END);
            robot.keyRelease(KeyEvent.VK_END);
            WebElement subscription = driver.findElement(By.xpath("//*[text()='Subscription']"));
            Assert.assertTrue(subscription.isDisplayed());
        } else {
            System.out.println("Arrow Down key did not scroll down the page");
        }

        Thread.sleep(3000);

        // Click on arrow at bottom right side to move upward
        robot.keyPress(KeyEvent.VK_UP);
        Thread.sleep(500);
        robot.keyRelease(KeyEvent.VK_UP);
        Thread.sleep(1000);
        // Verify that page is scrolled up and 'Full-Fledged practice website for Automation Engineers' text is visible on screen
        Long topY = (Long) js.executeScript("return window.pageYOffset;");
        if (topY > bottomY) {
            robot.keyPress(KeyEvent.VK_HOME);
            robot.keyRelease(KeyEvent.VK_HOME);
            WebElement fledgedWE = driver.findElement(By.xpath("//*[text()='Full-Fledged practice website for Automation Engineers']"));
            wait.until(ExpectedConditions.visibilityOf(fledgedWE));
            Assert.assertTrue(fledgedWE.isDisplayed());
        } else {
            System.out.println("Arrow Up key did not scroll up the page");
        }
    }
}
