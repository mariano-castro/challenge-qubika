package ui;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By emailInput = By.xpath("//input[@formcontrolname='email']");
    private By passwordInput = By.xpath("//input[@formcontrolname='password']");
    private By loginButton = By.xpath("//button[contains(text(),'Autenticar')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void goToLoginPage() {
        driver.get("https://club-administration.qa.qubika.com/#/auth/login");
    }

    public boolean isLoginPageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
            boolean emailVisible = driver.findElement(emailInput).isDisplayed();
            boolean passwordVisible = driver.findElement(passwordInput).isDisplayed();
            boolean loginButtonVisible = driver.findElement(loginButton).isDisplayed();
            return emailVisible && passwordVisible && loginButtonVisible;
        } catch (Exception e) {
            System.out.println("Cannot validate login page: " + e.getMessage());
            return false;
        }
    }

    public void login(String email, String password) {
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
    }
}
