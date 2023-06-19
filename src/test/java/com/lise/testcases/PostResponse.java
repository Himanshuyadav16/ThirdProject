package com.lise.testcases;

import com.github.javafaker.Faker;
import com.lise.BaseClass;

import com.lise.modals.post.*;
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
        String job = "leader";

        CreateUserBody createUserBody = new CreateUserBody();
        createUserBody.setName(name);
        createUserBody.setJob(job);

        CreateUserResponse createPostResponse = postCreate(createUserBody);

        assertThat(createPostResponse.getName(), is(name));
        assertThat(createPostResponse.getId(), notNullValue());
        assertThat(createPostResponse.getJob(), is(job));
        assertThat(createPostResponse.getCreatedAt(), notNullValue());
    }

    @Test
    public void postRegisterSuccessfulTest() {
        String email="eve.holt@reqres.in";
        String password="pistol";

        RegisterUserBody registerUserBody = new RegisterUserBody();

        registerUserBody.setEmail(email);
        registerUserBody.setPassword(password);

        RegisterUserResponse response = postRegisterSuccessful(registerUserBody);

        assertThat(response.getId(), notNullValue());
        assertThat(response.getToken(), notNullValue());
    }

    @Test
    public void postRegisterUnSuccessfulTest() {
        String email="sydney@fife";
        String body = "{\n" +
                "    \"email\": \""+email+"\"\n" +
                "}";
        Response response = postRegisterUnsuccessful(body);

        assertThat(response.getStatusCode(), is(HttpStatus.SC_BAD_REQUEST));

        JSONObject jsonObject=new JSONObject(response.asString());

        assertThat(jsonObject.getString("error"),is("Missing password"));


    }

    @Test
    public void postLoginSuccessfulTest() {
        String email="eve.holt@reqres.in";
        String password="cityslicka";

        LoginUserBody loginPostBody = new LoginUserBody();
        loginPostBody.setEmail(email);
        loginPostBody.setPassword(password);

        LoginUserResponse response = postLoginSuccesful(loginPostBody);
        assertThat(response.getToken(), notNullValue());
    }

    @Test
    public void postLoginUnSuccessfulTest() {
        String email="peter@klaven";
        String body = "{\n" +
                "    \"email\": \""+email+"\"\n" +
                "}";
        Response response = postLoginUnSuccesful(body);

        assertThat(response.getStatusCode(), is(HttpStatus.SC_BAD_REQUEST));

        JSONObject jsonObject=new JSONObject(response.asString());

        assertThat(jsonObject.getString("error"),is("Missing password"));

    }

    // Post Create Method
    public CreateUserResponse postCreate(CreateUserBody body) {
        CreateUserResponse response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.POST, "/users")
                .then()
                .extract()
                .as(CreateUserResponse.class);
        return response;
    }

    // post Register successful
    public RegisterUserResponse postRegisterSuccessful(RegisterUserBody body) {
        RegisterUserResponse response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .request(Method.POST, "/register")
                .as(RegisterUserResponse.class);
        return response;
    }

    //Post Register Unsuccessful
    public Response postRegisterUnsuccessful(String body) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .request(Method.POST, "/register");

        return response;
    }

    //Post  Login Successful
    public LoginUserResponse postLoginSuccesful(LoginUserBody body) {
        LoginUserResponse response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .request(Method.POST, "/login")
                .as(LoginUserResponse.class);
        return response;
    }

    //Post  Login UnSuccessful
    public Response postLoginUnSuccesful(String body) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .request(Method.POST, "/login");
        return response;
    }

}
