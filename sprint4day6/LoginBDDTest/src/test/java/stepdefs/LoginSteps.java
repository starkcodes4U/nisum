package stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {
    private WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Given("the login page is open")
    public void the_login_page_is_open() {
        String filePath = new File("src/main/resources/login.html").getAbsolutePath();
        driver.get("file:///" + filePath);
    }

    @When("user enters username {string} and password {string}")
    public void user_enters_username_and_password(String username, String password) {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("user clicks the login button")
    public void user_clicks_the_login_button() {
        driver.findElement(By.xpath("//button[text()='Login']")).click();
    }

    @Then("user should see a success message")
    public void user_should_see_a_success_message() {
        String message = driver.findElement(By.id("message")).getText();
        assertTrue(message.contains("successful"));
    }

    @Then("user should see a failure message")
    public void user_should_see_a_failure_message() {
        String message = driver.findElement(By.id("message")).getText();
        assertTrue(message.contains("failed"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
