package Exercises;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.TestBase;

import java.util.List;

import static org.junit.Assert.*;

public class C19_ViewCartBrandProducts extends TestBase {

    @Test
    public void test19() {

        // Navigate to url 'http://automationexercise.com'
        String url = "http://automationexercise.com";
        driver.get(url);

        // Click on 'Products' button
        WebElement productsButton = driver.findElement(By.xpath("//i[@class='material-icons card_travel']"));
        productsButton.click();

        // Verify that Brands are visible on left side bar
        WebElement brandsProducts = driver.findElement(By.xpath("//div[@class='brands_products']"));
        assertTrue(brandsProducts.isDisplayed());

        // Click on any brand name
        WebElement brandPolo = driver.findElement(By.xpath("//*[text()='Polo']"));
        brandPolo.click();

        // Verify that user is navigated to brand page and brand products are displayed
        String brandPoloProductsText = driver.findElement(By.xpath("//h2[@class='title text-center']")).getText();
        String expectedBrandPoloProductText = "BRAND - POLO PRODUCTS";
        assertEquals(expectedBrandPoloProductText, brandPoloProductsText);

        List<WebElement> poloProductsResult = driver.findElements(By.xpath("//i[@class='fa fa-plus-square']"));

        for (int i = 0; i < poloProductsResult.size(); i++) {
            assertTrue(poloProductsResult.get(i).isDisplayed());
        }

        // On left side bar, click on any other brand link
        WebElement brandHM = driver.findElement(By.xpath("//*[text()='H&M']"));
        brandHM.click();

        // Verify that user is navigated to that brand page and can see products
        String brandHMProductsText = driver.findElement(By.xpath("//h2[@class='title text-center']")).getText();
        String expectedBrandHMProductText = "BRAND - H&M PRODUCTS";
        assertEquals(expectedBrandHMProductText, brandHMProductsText);

        List<WebElement> hmProductsResult = driver.findElements(By.xpath("//i[@class='fa fa-plus-square']"));

        for (int i = 0; i < hmProductsResult.size(); i++) {
            assertTrue(hmProductsResult.get(i).isDisplayed());
        }
    }
}
