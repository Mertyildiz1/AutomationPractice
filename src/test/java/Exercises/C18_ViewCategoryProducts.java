package Exercises;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.TestBase;

import static org.junit.Assert.*;

public class C18_ViewCategoryProducts extends TestBase {
    @Test
    public void test18() {

        // Navigate to url 'http://automationexercise.com'
        String url = "http://automationexercise.com";
        driver.get(url);

        // Verify that categories are visible on left side bar
        WebElement leftSideBar = driver.findElement(By.xpath("//div[@class='left-sidebar']"));
        assertTrue(leftSideBar.isDisplayed());

        // Click on 'Women' category
        WebElement categoriesWomenSelection = driver.findElement(By.xpath("(//i[@class='fa fa-plus'])[1]"));
        categoriesWomenSelection.click();

        // Click on any category link under 'Women' category, for example: Dress
        WebElement dressSelectionAtWomenTab = driver.findElement(By.xpath("//*[text()='Dress ']"));
        dressSelectionAtWomenTab.click();

        // Verify that category page is displayed and confirm text 'WOMEN -  DRESS PRODUCTS'
        WebElement womenDressProducts = driver.findElement(By.xpath("//h2[@class='title text-center']"));
        assertTrue(womenDressProducts.isDisplayed());
        String expectedWomenDressPruductsText = "WOMEN - DRESS PRODUCTS";
        String actualWomenDressProductsText = womenDressProducts.getText();
        assertEquals(expectedWomenDressPruductsText,actualWomenDressProductsText);

        // On left side bar, click on any sub-category link of 'Men' category
        WebElement categoriesMenSelection = driver.findElement(By.xpath("(//i[@class='fa fa-plus'])[2]"));
        categoriesMenSelection.click();

        WebElement tshirtSelectionAtMenTab = driver.findElement(By.xpath("//*[text()='Tshirts ']"));
        tshirtSelectionAtMenTab.click();

        // Verify that user is navigated to that category page
        WebElement menTshirtProducts = driver.findElement(By.xpath("//h2[@class='title text-center']"));
        assertTrue(menTshirtProducts.isDisplayed());
        String expectedMenTshirtPruductsText = "MEN - TSHIRTS PRODUCTS";
        String actualMenTshirtPruductsText = menTshirtProducts.getText();
        assertEquals(expectedMenTshirtPruductsText,actualMenTshirtPruductsText);
    }
}
