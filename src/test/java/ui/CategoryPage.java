package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class CategoryPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public CategoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By addButton = By.xpath("//button[contains(text(),'Adicionar')]");
    private By nameInput = By.xpath("//input[@placeholder='Nombre de categoría']");
    private By isSubcategoryCheckbox = By.xpath("//input[@type='checkbox']");
    private By parentDropdown = By.xpath("//mat-form-field//div[contains(@class,'mat-select-arrow-wrapper')]");
    private By acceptButton = By.xpath("//button[contains(text(),'Aceptar')]");

    public void goToCategoryPage() {
        driver.get("https://club-administration.qa.qubika.com/#/category-type");
    }

    public void createCategory(String name, boolean isRoot) throws InterruptedException {
        driver.findElement(addButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput));
        driver.findElement(nameInput).sendKeys(name);
        if (!isRoot) {
            driver.findElement(isSubcategoryCheckbox).click();
        }
        driver.findElement(acceptButton).click();
    }

    public void createSubCategory(String name, String parentCategory) throws InterruptedException {
        driver.findElement(addButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput));
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(isSubcategoryCheckbox).click();
        driver.findElement(parentDropdown).click();
        WebElement input = driver.switchTo().activeElement(); 
        input.sendKeys(parentCategory);
        input.sendKeys(Keys.ENTER);
        driver.findElement(acceptButton).click();
    }

    public boolean isCategoryPresent(String name) {
        return driver.getPageSource().contains(name);
    }
}
