package com.lise.testcases.PostMethod;

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


public class PostResponse extends BaseClass {

    @Test
    public void postCreateTest() {
        Faker faker = new Faker();
        String name = faker.name().name();
        String body = "{\n" +
                "    \"name\": \"" + name + "\",\n" +
                "    \"job\": \"leader\"\n" +
                "}\n";
        Response response = postCreate(body);
        assertThat(response.getStatusCode(), is(HttpStatus.SC_CREATED));


        JSONObject jsonObject = new JSONObject(response.asString());

        assertThat(jsonObject.getString("name"), is(name));
        assertThat(jsonObject.getString("id"), notNullValue());
        assertThat(jsonObject.getString("job"), is("leader"));
        assertThat(jsonObject.getString("createdAt"), notNullValue());
    }

    @Test
    public void postRegisterSuccessfulTest() {
        String body = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}";
        Response response = postRegisterSuccessful(body);

        assertThat(response.getStatusCode(), is(HttpStatus.SC_OK));
        JSONObject jsonObject = new JSONObject(response.asString());


        assertThat(jsonObject.getInt("id"), notNullValue());
        assertThat(jsonObject.getString("token"), notNullValue());
    }

    @Test
    public void postRegisterUnSuccessfulTest() {
        String body = "{\n" +
                "    \"email\": \"sydney@fife\"\n" +
                "}";
        Response response = postRegisterUnsuccessful(body);

        assertThat(response.getStatusCode(), is(HttpStatus.SC_BAD_REQUEST));

    }

    @Test
    public void postLoginSuccessfulTest() {
        String body = "\n" +
                "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"cityslicka\"\n" +
                "}";

        Response response = postLoginSuccesful(body);
        assertThat(response.getStatusCode(), is(HttpStatus.SC_OK));
        JSONObject jsonObject = new JSONObject(response.asString());

        assertThat(jsonObject.getString("token"), notNullValue());
    }

    @Test
    public void postLoginUnSuccessfulTest() {
        String body = "{\n" +
                "    \"email\": \"peter@klaven\"\n" +
                "}";
        Response response = postLoginUnSuccesful(body);

        assertThat(response.getStatusCode(), is(HttpStatus.SC_BAD_REQUEST));
    }

    // Post Create Method
    public Response postCreate(String body) {
        Response response = given()
                .header("Authorization", accessToken)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.POST, "/users");
        return response;
    }

    // post Register successful
    public Response postRegisterSuccessful(String body) {
        Response response = given()
                .header("Authorization", accessToken)
                .contentType(ContentType.JSON)
                .body(body)
                .request(Method.POST, "/register");
        return response;
    }

    //Post Register Unsuccessful
    public Response postRegisterUnsuccessful(String body) {
        Response response = given()
                .header("Authorization", accessToken)
                .contentType(ContentType.JSON)
                .body(body)
                .request(Method.POST, "/register");
        return response;
    }

    //Post  Login Successful
    public Response postLoginSuccesful(String body) {
        Response response = given()
                .header("Authorization", accessToken)
                .contentType(ContentType.JSON)
                .body(body)
                .request(Method.POST, "/login");
        return response;
    }

    //Post  Login UnSuccessful
    public Response postLoginUnSuccesful(String body) {
        Response response = given()
                .header("Authorization", accessToken)
                .contentType(ContentType.JSON)
                .body(body)
                .request(Method.POST, "/login");
        return response;
    }

}
