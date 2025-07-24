package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;

public class UserApiTest {

    private static final String BASE_URL = "https://api.club-administration.qa.qubika.com";

    @Test
    public void createUserAndSaveData() throws IOException {
        // Dinamic data
        String email = "test_" + System.currentTimeMillis() + "@test.com";
        String password = "Test1234!";
        String role = "ROLE_ADMIN";
        // JSON body
        JSONObject body = new JSONObject();
        body.put("email", email);
        body.put("password", password);
        body.put("roles", new String[]{role});
        // Request
        Response response = RestAssured
                .given()
                    .baseUri(BASE_URL)
                    .basePath("/api/auth/register")
                    .contentType(ContentType.JSON)
                    .body(body.toString())
                .when()
                    .post()
                .then()
                    .log().all()
                    .extract().response();
        // Response validation
        Assert.assertEquals(response.statusCode(), 201, "Expected HTTP 201 Created");
        String userId = response.jsonPath().getString("id");
        String userName = response.jsonPath().getString("userName");
        // Save user data to JSON file
        JSONObject userData = new JSONObject();
        userData.put("email", email);
        userData.put("password", password);
        userData.put("userName", userName);
        userData.put("id", userId);
        try (FileWriter file = new FileWriter("user-data.json")) {
            file.write(userData.toString(4));
            System.out.println("user-data.json saved.");
        }
    }
}
