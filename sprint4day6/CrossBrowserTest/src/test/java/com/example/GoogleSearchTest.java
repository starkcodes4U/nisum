package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleSearchTest extends BaseTest {

    @Test
    public void testGoogleSearch() {
        driver.get("https://www.google.com");
        driver.findElement(By.name("q")).sendKeys("Cross Browser Testing with Selenium" + Keys.ENTER);
        boolean isPresent = driver.getTitle().toLowerCase().contains("cross browser testing");
        Assert.assertTrue(isPresent, "Search result title does not contain expected text.");
    }
}
