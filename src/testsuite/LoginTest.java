package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {
    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void openBrowser() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() {
        WebElement emailField = driver.findElement(By.id("username"));
        emailField.sendKeys("tomsmith");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("SuperSecretPassword!");
        WebElement loginButton = driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']"));
        loginButton.click();
        String expectedMessage = "Secure Area";
        WebElement actualTextElement = driver.findElement(By.xpath("//h2[text()=' Secure Area']"));
        String actualMessage = actualTextElement.getText();
        Assert.assertEquals("Not directed to Secure Area page", expectedMessage, actualMessage);
    }


    @Test
    public void verifyTheUsernameErrorMessage() {
        WebElement emailField = driver.findElement(By.id("username"));
        emailField.sendKeys("tomsmith1");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("SuperSecretPassword!");
        WebElement loginButton = driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']"));
        loginButton.click();
        String expectedMessage = "Your username is invalid!\n" + "×";
        WebElement actualTextElement = driver.findElement(By.xpath("//div[@class='flash error']"));
        String actualMessage = actualTextElement.getText();
        Assert.assertEquals("Your username is invalid message not displayed", expectedMessage, actualMessage);
    }

    @Test
    public void verifyThePasswordErrorMessage() {
        WebElement emailField = driver.findElement(By.id("username"));
        emailField.sendKeys("tomsmith");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("SuperSecretPassword");
        WebElement loginButton = driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']"));
        loginButton.click();
        String expectedMessage = "Your password is invalid!\n×";
        WebElement actualTextElement = driver.findElement(By.xpath("//div[@id='flash']"));
        String actualMessage = actualTextElement.getText();
        Assert.assertEquals("Your password is invalid message, not displayed", expectedMessage, actualMessage);
    }


    @After
    public void tearDown() {
        close();
    }
}
