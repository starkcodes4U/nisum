package com.nisum.stepdefs;

import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class GoogleSearchSteps {

    WebDriver driver;

    @Given("user is on Google homepage")
    public void user_is_on_google_homepage() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.google.com");
    }

    @When("user searches for {string}")
    public void user_searches_for(String query) {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(query);
        searchBox.sendKeys(Keys.ENTER);
    }

    @Then("search results should be displayed")
    public void search_results_should_be_displayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // ✅ Corrected locator here
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.g")));
        System.out.println("Search results are visible.");
        driver.quit();
    }
}
