package com.lise.testcases.PutMethod;

import com.github.javafaker.Faker;
import com.lise.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PutResponse extends BaseClass {

    @Test
    public void putUpdateTest() {
        Faker faker = new Faker();
        String name = faker.name().name();

        String body = "{\n" +
                "    \"name\": \"" + name + "\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";

        Response response = putUpdate(body);

        assertThat(response.getStatusCode(), is(HttpStatus.SC_OK));

        JSONObject jsonObject = new JSONObject(response.asString());

        assertThat(jsonObject.getString("name"), is(name));
        assertThat(jsonObject.getString("job"), notNullValue());
        assertThat(jsonObject.getString("updatedAt"), notNullValue());

    }

    //Put Update Method
    public Response putUpdate(String body) {
        Response response = given()
                .header("Authorization", accessToken)
                .contentType(ContentType.JSON)
                .body(body)
                .request(Method.PUT, "/users/2");
        return response;
    }
}
