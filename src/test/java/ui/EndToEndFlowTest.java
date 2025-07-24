package ui;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Instant;

public class EndToEndFlowTest extends BaseTest {

    @Test
    public void fullUserCategoryFlow() throws InterruptedException {
        // Step 1: Create user by API
        String email = "test_" + Instant.now().toEpochMilli() + "@test.com";
        String password = "Test1234!";
        JSONObject body = new JSONObject();
        body.put("email", email);
        body.put("password", password);
        body.put("roles", new String[]{"ROLE_ADMIN"});

        Response response = RestAssured
                .given()
                    .baseUri("https://api.club-administration.qa.qubika.com")
                    .basePath("/api/auth/register")
                    .contentType(ContentType.JSON)
                    .body(body.toString())
                .when()
                    .post()
                .then()
                    .extract().response();

        Assert.assertEquals(response.statusCode(), 201, "User should be created successfully");

        // Step 2: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        loginPage.login(email, password);

        Thread.sleep(2000);
        Assert.assertTrue(driver.getPageSource().contains("Dashboard") || driver.getCurrentUrl().contains("dashboard"),
                "Should be logged in");

        // Step 3: Category creation
        CategoryPage categoryPage = new CategoryPage(driver);
        categoryPage.goToCategoryPage();

        String parentCategory = "Parent_" + Instant.now().toEpochMilli();
        String subCategory = "Child_" + Instant.now().toEpochMilli();

        categoryPage.createCategory(parentCategory, true);
        Assert.assertTrue(categoryPage.isCategoryPresent(parentCategory), "Category should be created"); //IT FAILS BECAUSE OF PAGINATION BUG

        categoryPage.createSubCategory(subCategory, parentCategory);
        Assert.assertTrue(categoryPage.isCategoryPresent(subCategory), "Subcategory should be created"); //IT FAILS BECAUSE OF PAGINATION BUG
    }
}
