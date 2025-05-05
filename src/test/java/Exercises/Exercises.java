package Exercises;

import com.github.javafaker.Faker;
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

public class Exercises {

    @Test
    public void RegisterUser() {
        Faker faker = new Faker();

        // Navigate to url 'http://automationexercise.com'
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        String url = "http://automationexercise.com";
        driver.get(url);

        // Verify that home page is visible successfully
        String pageExpectedTitle = "Automation Exercise";
        String pageActualTitle = driver.getTitle();
        Assert.assertEquals(pageExpectedTitle,pageActualTitle);

        WebElement homePageTestCasesButton = driver.findElement(By.xpath("//*[.=' Test Cases']"));
        Assert.assertTrue(homePageTestCasesButton.isDisplayed());

        // Click on 'Signup / Login' button
        WebElement signUpLoginButton = driver.findElement(By.xpath("//i[@class='fa fa-lock']"));
        signUpLoginButton.click();

        //Verify 'New User Signup!' is visible

        String expectedLoginTitle = "Automation Exercise - Signup / Login";
        String actualLoginTitle = driver.getTitle();
        Assert.assertEquals(expectedLoginTitle,actualLoginTitle);

        //Enter name and email address
        WebElement nameArea = driver.findElement(By.xpath("//input[@name='name']"));
        nameArea.sendKeys(faker.name().firstName(),
                Keys.TAB,faker.internet().emailAddress());

        //Click 'Signup' button
        WebElement signUp = driver.findElement(By.xpath("//button[.='Signup']"));
        signUp.click();

        //Verify that 'ENTER ACCOUNT INFORMATION' is visible
        WebElement entAccInfoText = driver.findElement(By.xpath("//*[.='Enter Account Information']"));
        Assert.assertTrue(entAccInfoText.isDisplayed());

        //Fill details: Title, Name, Email, Password, Date of birth
        WebElement mrsMrSelect = driver.findElement(By.id("id_gender1"));
        mrsMrSelect.click();
        mrsMrSelect.sendKeys(Keys.TAB,Keys.TAB,faker.internet().password(),Keys.TAB,"30",Keys.TAB,"July",Keys.TAB,"2001");

        //Select checkbox 'Sign up for our newsletter!'
        WebElement sufons = driver.findElement(By.id("newsletter"));
        sufons.click();
        sufons.sendKeys(Keys.TAB,Keys.SPACE);

        // Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
        WebElement firstName = driver.findElement(By.id("first_name"));
        firstName.sendKeys(faker.name().firstName(),Keys.TAB,faker.name().lastName(),Keys.TAB,faker.company().name(),Keys.TAB,faker.address().fullAddress(),Keys.TAB,
                Keys.TAB,faker.address().country(),Keys.TAB,faker.address().state(),Keys.TAB,faker.address().city(),Keys.TAB,faker.address().zipCode());
        WebElement mobileNumberInput = driver.findElement(By.id("mobile_number"));
        mobileNumberInput.sendKeys(faker.phoneNumber().phoneNumber());

        //Click 'Create Account button'
        WebElement createAccButton = driver.findElement(By.xpath("//*[.='Create Account']"));
        createAccButton.click();

        // Verify that 'ACCOUNT CREATED!' is visible
        WebElement accCreated = driver.findElement(By.xpath("//*[.='Account Created!']"));
        Assert.assertTrue(accCreated.isDisplayed());
        Assert.assertEquals("Automation Exercise - Account Created",driver.getTitle());

        // Click 'Continue' button
        WebElement continueButton = driver.findElement(By.xpath("//*[.='Continue']"));
        continueButton.click();

        //Verify that 'Logged in as username' is visible
        WebElement loggedInAsUser = driver.findElement(By.xpath("//*[text()=' Logged in as ']"));
        Assert.assertTrue(loggedInAsUser.isDisplayed());

        //Click 'Delete Account' button
        WebElement deleteAccButton = driver.findElement(By.xpath("//*[.=' Delete Account']"));
        deleteAccButton.click();

        // Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
        WebElement accDeletedText = driver.findElement(By.xpath("//*[.='Account Deleted!']"));
        Assert.assertTrue(accDeletedText.isDisplayed());

    }

    @Test
    public void LoginWithCorrectEmailAndPassword() {
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
        emailArea.sendKeys("1yildizmert@gmail.com",Keys.TAB,"test123456",Keys.TAB,Keys.ENTER);

        //Verify that 'Logged in as username' is visible
        WebElement loggedInAs = driver.findElement(By.xpath("//*[text()=' Logged in as ']"));
        wait.until(ExpectedConditions.visibilityOf(loggedInAs));
        Assert.assertTrue(loggedInAs.isDisplayed());

        //Click 'Delete Account' button
        WebElement deleteAccButton = driver.findElement(By.xpath("//*[.=' Delete Account']"));
        deleteAccButton.click();

        // Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
        WebElement accDeletedText = driver.findElement(By.xpath("//*[.='Account Deleted!']"));
        Assert.assertTrue(accDeletedText.isDisplayed());

    }
}
