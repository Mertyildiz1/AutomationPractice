package Exercises;

import net.bytebuddy.description.modifier.Visibility;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.TestBase;

import java.time.Duration;
import java.util.List;

public class C12_AddProductsInCart extends TestBase {
    @Test
    public void test12() throws InterruptedException {
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
        // Navigate to url 'http://automationexercise.com'
        String url = "http://automationexercise.com";
        driver.get(url);

        // Verify that home page is visible successfully
        WebElement featuresItemsText = driver.findElement(By.xpath("//h2[@class='title text-center']"));
        wait.until(ExpectedConditions.visibilityOf(featuresItemsText));
        Assert.assertTrue(featuresItemsText.isDisplayed());

        String expectedTitle = "Automation Exercise";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);

        // Click on 'Products' button
        WebElement productsButton = driver.findElement(By.cssSelector(".material-icons.card_travel"));
        productsButton.click();

        // Hover over first product and click 'Add to cart'
        WebElement brandBıba = driver.findElement(By.xpath("(//span[@class='pull-right'])[8]"));
        actions.scrollToElement(brandBıba).perform();
        WebElement firstProduct = driver.findElement(By.xpath("(//div[@class='productinfo text-center'])[1]"));
        actions.moveToElement(firstProduct).perform();
        WebElement addToCart = driver.findElement(By.xpath("(//a[@class='btn btn-default add-to-cart'])[1]"));
        addToCart.click();

        // Click 'Continue Shopping' button
        WebElement continueShopping = driver.findElement(By.xpath("//button[@class='btn btn-success close-modal btn-block']"));
        wait.until(ExpectedConditions.visibilityOf(continueShopping));
        continueShopping.click();

        // Hover over second product and click 'Add to cart'
        WebElement secondProduct = driver.findElement(By.xpath("(//div[@class='productinfo text-center'])[1]"));
        actions.moveToElement(secondProduct).perform();
        WebElement addToCart2 = driver.findElement(By.xpath("(//a[@class='btn btn-default add-to-cart'])[3]"));
        wait.until(ExpectedConditions.visibilityOf(addToCart2));
        addToCart2.click();

        // Click 'View Cart' button
        WebElement viewCartButton = driver.findElement(By.xpath("//*[text()='View Cart']"));
        wait.until(ExpectedConditions.visibilityOf(viewCartButton));
        viewCartButton.click();

        // Verify both products are added to Cart
        List<WebElement> productsInCart = driver.findElements(By.xpath("//h4//a"));
        int sayac = 0 ;
        for (int i = 0; i < productsInCart.size() ; i++) {
            productsInCart.get(i).click();
            Thread.sleep(1500);
            driver.navigate().back();
            productsInCart = driver.findElements(By.xpath("//h4//a"));
            sayac++;
        }
        Assert.assertEquals(2,sayac);

        // Verify their prices, quantity and total price
        WebElement blueTopPrice = driver.findElement(By.xpath("(//td[@class='cart_price'])[1]"));
        String expectedBlueTopPrice = "Rs. 500";
        Assert.assertEquals(expectedBlueTopPrice,blueTopPrice.getText());

        WebElement menTshirtPrice = driver.findElement(By.xpath("(//td[@class='cart_price'])[2]"));
        String expectedMenTshirtPrice = "Rs. 400";
        Assert.assertEquals(expectedMenTshirtPrice,menTshirtPrice.getText());

        WebElement blueTopQuantity = driver.findElement(By.xpath("(//button[@class='disabled'])[1]"));
        String expectedBlueTopQuantity = "1";
        Assert.assertEquals(expectedBlueTopQuantity,blueTopQuantity.getText());

        WebElement menTshirtQuantity = driver.findElement(By.xpath("(//button[@class='disabled'])[2]"));
        String expectedMenTshirtQuantity = "1";
        Assert.assertEquals(expectedMenTshirtQuantity,menTshirtQuantity.getText());

        WebElement totalPrice1 = driver.findElement(By.xpath("(//p[@class='cart_total_price'])[1]"));
        String actualTotalPrice1 = totalPrice1.getText();
        String expectedTotalPrice1 = "Rs. 500";
        Assert.assertEquals(expectedTotalPrice1,actualTotalPrice1);
        WebElement totalPrice2 = driver.findElement(By.xpath("(//p[@class='cart_total_price'])[2]"));
        String actualTotalPrice2= totalPrice2.getText();
        String expectedTotalPrice2 = "Rs. 400";
        Assert.assertEquals(expectedTotalPrice2,actualTotalPrice2);
    }
}
