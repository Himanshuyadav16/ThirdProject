package com.lise.testcases;

import com.github.javafaker.Faker;
import com.lise.BaseClass;
import com.lise.modals.Put.PutUserBody;
import com.lise.modals.Put.PutUserResponse;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PutResponse extends BaseClass {

    @Test
    public void putUpdateTest() {
        Faker faker = new Faker();

        String name = faker.name().name();
        String job = "zion resident";

        PutUserBody putUserBody = new PutUserBody();
        putUserBody.setName(name);
        putUserBody.setJob(job);

        PutUserResponse response = putUpdate(putUserBody);

        assertThat(response.getName(), is(name));
        assertThat(response.getJob(), is(job));
        assertThat(response.getUpdatedAt(), notNullValue());
    }

    //Put Update Method
    public PutUserResponse putUpdate(PutUserBody body) {
        PutUserResponse response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .request(Method.PUT, "/users/2")
                .as(PutUserResponse.class);
        return response;
    }
}
