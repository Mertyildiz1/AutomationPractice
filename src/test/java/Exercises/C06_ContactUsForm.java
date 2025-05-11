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

public class C06_ContactUsForm extends TestBase {
    @Test
    public void test06() throws InterruptedException {
        Faker faker = new Faker();
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
        Assert.assertEquals(expectedTitle,actualTitle);

        // Click on 'Contact Us' button
        WebElement contactUsButton = driver.findElement(By.xpath("//i[@class='fa fa-envelope']"));
        contactUsButton.click();

        // Verify 'GET IN TOUCH' is visible
        WebElement getInTouch = driver.findElement(By.xpath("//*[.='Get In Touch']"));
        Assert.assertTrue(getInTouch.isDisplayed());

        String expectedText = "GET IN TOUCH";
        String actualText = driver.findElement(By.xpath("//*[.='Get In Touch']")).getText();
        Assert.assertEquals(expectedText,actualText);
        Thread.sleep(1000);

        // Enter name, email, subject and message
        WebElement nameInputArea = driver.findElement(By.cssSelector(".form-control"));
        String subject = faker.lorem().sentence(4);
        String message = faker.lorem().sentence(15);
        nameInputArea.sendKeys(faker.name().firstName(), Keys.TAB,faker.internet().emailAddress(),Keys.TAB,subject,Keys.TAB,message);

        // Upload file
        String everyoneOwnPath = System.getProperty("user.home");
        String commonFilePath = "\\Pictures\\Screenshots\\Ekran görüntüsü 2025-05-10 142810.png";
        String filePath = everyoneOwnPath + commonFilePath;
        WebElement fileUploadInput = driver.findElement(By.xpath("(//input[@class='form-control'])[4]"));
        fileUploadInput.sendKeys(filePath);

        // Click 'Submit' button
        WebElement submitButton = driver.findElement(By.xpath("//input[@class='btn btn-primary pull-left submit_form']"));
        Thread.sleep(1000);
        submitButton.click();

        // Click OK button (HTML Alert)
        driver.switchTo().alert().accept();

        // Verify success message 'Success! Your details have been submitted successfully.' is visible
        WebElement successMessage = driver.findElement(By.xpath("//div[@class='status alert alert-success']"));
        Assert.assertTrue(successMessage.isDisplayed());

        String expectedSuccessMessage = "Success! Your details have been submitted successfully.";
        String actualSuccessMessage = successMessage.getText();
        Assert.assertEquals(expectedSuccessMessage,actualSuccessMessage);

        //Click 'Home' button and verify that landed to home page successfully
        WebElement homeButton = driver.findElement(By.xpath("//i[@class='fa fa-angle-double-left']"));
        homeButton.click();
        WebElement featuresItemsText2 = driver.findElement(By.xpath("//h2[@class='title text-center']"));
        Assert.assertTrue(featuresItemsText2.isDisplayed());
        Assert.assertEquals(expectedTitle,actualTitle);
    }
}
