package ui;

import base.BaseTest;
import utils.UserDataReader;

import java.io.IOException;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.Assert;

public class UITests extends BaseTest {

    @Test
    public void loginAndVerify() throws IOException {
        JSONObject user = UserDataReader.getUserData();
        String email = user.getString("email");
        String password = user.getString("password");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be visible");
        loginPage.login(email, password);
        By dashboardIndicator = By.xpath("//*[contains(text(),'Dashboard') or contains(text(),'Home')]");
        waitForElement(dashboardIndicator);
        Assert.assertTrue(driver.findElements(dashboardIndicator).size() > 0, "Should be logged in successfully");
    }

    @Test
    public void createCategoryAndSubCategory() throws IOException, InterruptedException {
        JSONObject user = UserDataReader.getUserData();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        loginPage.login(user.getString("email"), user.getString("password"));
        CategoryPage categoryPage = new CategoryPage(driver);
        categoryPage.goToCategoryPage();
        // Create category
        String categoryName = "Padre_" + System.currentTimeMillis();
        categoryPage.createCategory(categoryName, true);
        Assert.assertTrue(categoryPage.isCategoryPresent(categoryName), "Category should be created"); //IT FAILS BECAUSE OF PAGINATION BUG
        // Create subcategory
        String subCategoryName = "Hijo_" + System.currentTimeMillis();
        categoryPage.createSubCategory(subCategoryName, categoryName);
        Assert.assertTrue(categoryPage.isCategoryPresent(subCategoryName), "Subcategory should be created"); //IT FAILS BECAUSE OF PAGINATION BUG
    }
}
