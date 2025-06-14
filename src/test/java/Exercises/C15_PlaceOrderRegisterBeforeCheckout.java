package Exercises;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import utilities.TestBase;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.time.Duration;

public class C15_PlaceOrderRegisterBeforeCheckout extends TestBase {

    @Test
    public void test15() throws AWTException {

        Robot robot = new Robot();
        Faker faker = new Faker();
        Actions actions = new Actions(driver);

        // Navigate to url 'http://automationexercise.com'
        String url = "http://automationexercise.com";
        driver.get(url);

        // Verify that home page is visible successfully
        String pageExpectedTitle = "Automation Exercise";
        String pageActualTitle = driver.getTitle();
        Assert.assertEquals(pageExpectedTitle, pageActualTitle);

        WebElement homePageTestCasesButton = driver.findElement(By.xpath("//*[.=' Test Cases']"));
        Assert.assertTrue(homePageTestCasesButton.isDisplayed());

        // Click on 'Signup / Login' button
        WebElement signUpLoginButton = driver.findElement(By.xpath("//i[@class='fa fa-lock']"));
        signUpLoginButton.click();

        // Fill all details in Signup and create account
        WebElement nameArea = driver.findElement(By.xpath("//input[@name='name']"));
        nameArea.sendKeys("Mert",
                Keys.TAB, faker.internet().emailAddress());

        WebElement signUp = driver.findElement(By.xpath("//button[.='Signup']"));
        signUp.click();

        WebElement mrsMrSelect = driver.findElement(By.id("id_gender1"));
        mrsMrSelect.click();
        mrsMrSelect.sendKeys(Keys.TAB, Keys.TAB, faker.internet().password(), Keys.TAB, "30", Keys.TAB, "July", Keys.TAB, "2001");

        WebElement sufons = driver.findElement(By.id("newsletter"));
        sufons.click();
        sufons.sendKeys(Keys.TAB, Keys.SPACE);

        String firstNameInput = faker.name().firstName();
        String lastNameInput = faker.name().lastName();
        String componyName = faker.company().name();
        String fullAddress = faker.address().fullAddress();
        String addressCountry = faker.address().country();
        String addressState = faker.address().state();
        String addressCity = faker.address().city();
        String addressZipCode = faker.address().zipCode();
        String mobileNumber = faker.phoneNumber().phoneNumber();

        WebElement firstName = driver.findElement(By.id("first_name"));
        firstName.sendKeys(firstNameInput, Keys.TAB, lastNameInput, Keys.TAB, componyName, Keys.TAB, fullAddress, Keys.TAB,
                Keys.TAB, addressCountry, Keys.TAB, addressState, Keys.TAB, addressCity, Keys.TAB, addressZipCode);
        WebElement mobileNumberInput = driver.findElement(By.id("mobile_number"));
        mobileNumberInput.sendKeys(mobileNumber);

        //Click 'Create Account button'
        WebElement createAccButton = driver.findElement(By.xpath("//*[.='Create Account']"));
        createAccButton.click();

        // Verify 'ACCOUNT CREATED!' and click 'Continue' button
        WebElement accCreated = driver.findElement(By.xpath("//*[.='Account Created!']"));
        Assert.assertTrue(accCreated.isDisplayed());
        Assert.assertEquals("Automation Exercise - Account Created", driver.getTitle());

        WebElement continueButton = driver.findElement(By.xpath("//a[@class='btn btn-primary']"));
        continueButton.click();

        //Verify that 'Logged in as username' is visible
        WebElement loggedInAsUser = driver.findElement(By.xpath("//*[text()=' Logged in as ']"));
        Assert.assertTrue(loggedInAsUser.isDisplayed());

        // Add products to cart
        WebElement productsButton = driver.findElement(By.xpath("//i[@class='material-icons card_travel']"));
        productsButton.click();

        WebElement brandBıba = driver.findElement(By.xpath("(//span[@class='pull-right'])[8]"));
        actions.scrollToElement(brandBıba).perform();

        WebElement blueTopAddToCartButton = driver.findElement(By.xpath("(//a[@class='btn btn-default add-to-cart'])[1]"));
        blueTopAddToCartButton.click();

        // Click 'Cart' button
        WebElement viewCartButton = driver.findElement(By.xpath("//*[text()='View Cart']"));
        viewCartButton.click();

        // Verify that cart page is displayed
        WebElement shoppingCartText = driver.findElement(By.xpath("//*[.='Shopping Cart']"));
        Assert.assertTrue(shoppingCartText.isDisplayed());

        // Click Proceed To Checkout
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//*[.='Proceed To Checkout']"));
        proceedToCheckoutButton.click();

        // Verify Address Details and Review Your Order
        String name = driver.findElement(By.xpath("//li[@class='address_firstname address_lastname']")).getText();
        String expectedBillName = "Mr. " + firstNameInput + " " + lastNameInput;
        Assert.assertEquals(expectedBillName, name);

        String compony = driver.findElement(By.xpath("(//li[@class='address_address1 address_address2'])[1]")).getText();
        Assert.assertEquals(componyName, compony);

        String address = driver.findElement(By.xpath("(//li[@class='address_address1 address_address2'])[2]")).getText();
        Assert.assertEquals(fullAddress, address);

        String stateCityZip = driver.findElement(By.xpath("(//li[@class='address_city address_state_name address_postcode'])[1]")).getText();
        String expectedBillStateCityZip = addressCity + " " + addressState + " " + addressZipCode;
        Assert.assertEquals(expectedBillStateCityZip, stateCityZip);

        String BillCellNumber = driver.findElement(By.xpath("//li[@class='address_phone']")).getText();
        Assert.assertEquals(mobileNumber, BillCellNumber);

        // Enter description in comment text area and click 'Place Order'
        robot.keyPress(KeyEvent.VK_END);
        robot.keyRelease(KeyEvent.VK_END);

        WebElement textArea = driver.findElement(By.xpath("//textarea[@class='form-control']"));
        textArea.sendKeys(faker.lorem().sentence(10), Keys.TAB, Keys.ENTER);

        // Enter payment details: Name on Card, Card Number, CVC, Expiration date
        WebElement nameOnCard = driver.findElement(By.name("name_on_card"));
        nameOnCard.sendKeys(name, Keys.TAB, faker.business().creditCardNumber(), Keys.TAB, "311", Keys.TAB, "02", Keys.TAB, "2030", Keys.TAB);

        // Click 'Pay and Confirm Order' button
        WebElement payAndConfirmButton = driver.findElement(By.id("submit"));
        payAndConfirmButton.click();

        // Verify success message 'Congratulations! Your order has been confirmed!'
        WebElement verifySuccess = driver.findElement(By.xpath("(//div//p)[1]"));
        Assert.assertTrue(verifySuccess.isDisplayed());
        String expectedVerifySuccessText = "Congratulations! Your order has been confirmed!";
        Assert.assertEquals(expectedVerifySuccessText, verifySuccess.getText());

        // Click 'Delete Account' button
        robot.keyPress(KeyEvent.VK_HOME);
        robot.keyRelease(KeyEvent.VK_HOME);

        WebElement deleteAccButton = driver.findElement(By.xpath("//*[text()=' Delete Account']"));
        deleteAccButton.click();

        // Verify 'ACCOUNT DELETED!' is visible and click 'Continue' button
        WebElement accDeletedText = driver.findElement(By.xpath("//*[.='Account Deleted!']"));
        Assert.assertTrue(accDeletedText.isDisplayed());
        WebElement DeleteAccContinueButton = driver.findElement(By.xpath("//a[.='Continue']"));
        DeleteAccContinueButton.click();
    }
}
