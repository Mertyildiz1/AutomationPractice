package Exercises;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.TestBase;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class C26_VerifyScrollUpWithoutArrowButtonScrollDownFunctionality extends TestBase {
    @Test
    public void test26() throws InterruptedException, AWTException {
        {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Robot robot = new Robot();

            // Navigate to url 'http://automationexercise.com'
            String url = "http://automationexercise.com";
            driver.get(url);

            // Verify that home page is visible successfully
            WebElement featuresItemsText = driver.findElement(By.xpath("//h2[@class='title text-center']"));
            wait.until(ExpectedConditions.visibilityOf(featuresItemsText));
            Assert.assertTrue(featuresItemsText.isDisplayed());

            String expectedTitle = "Automation Exercise";
            String actualTitle = driver.getTitle();
            Assert.assertEquals(expectedTitle, actualTitle);

            // Scroll down page to bottom - Verify 'SUBSCRIPTION' is visible
            Long initialY = (Long) js.executeScript("return window.pageYOffset;");

            int scrollAmountDown = 3;
            for (int i = 0; i < scrollAmountDown; i++) {
                robot.mouseWheel(1);
                Thread.sleep(200);
            }
            Thread.sleep(1000);

            Long bottomY = (Long) js.executeScript("return window.pageYOffset;");
            if (bottomY > initialY) {
                robot.keyPress(KeyEvent.VK_END);
                robot.keyRelease(KeyEvent.VK_END);
                WebElement subscription = driver.findElement(By.xpath("//*[text()='Subscription']"));
                Assert.assertTrue(subscription.isDisplayed());
            } else {
                System.out.println("Scrolling down with mouse wheel fails");
            }

            Thread.sleep(3000);

            // Scroll up page to top
            // Verify that page is scrolled up and 'Full-Fledged practice website for Automation Engineers' text is visible on screen
            int scrollAmountUp = 3;
            for (int i = 0; i < scrollAmountUp; i++) {
                robot.mouseWheel(-1);
                Thread.sleep(200);
            }
            Thread.sleep(1000);

            Long topY = (Long) js.executeScript("return window.pageYOffset;");
            if (topY > bottomY) {
                robot.keyPress(KeyEvent.VK_HOME);
                robot.keyRelease(KeyEvent.VK_HOME);
                WebElement fledgedWE = driver.findElement(By.xpath("//*[text()='Full-Fledged practice website for Automation Engineers']"));
                wait.until(ExpectedConditions.visibilityOf(fledgedWE));
                Assert.assertTrue(fledgedWE.isDisplayed());

            } else {
                System.out.println("Scrolling up with mouse fails");
            }
        }
    }
}
