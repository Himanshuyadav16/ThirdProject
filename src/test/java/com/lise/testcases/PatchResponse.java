package com.lise.testcases;

import com.github.javafaker.Faker;
import com.lise.BaseClass;
import com.lise.modals.patch.PatchUserBody;
import com.lise.modals.patch.PatchUserResponse;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PatchResponse extends BaseClass {

    @Test
    public void patchUpdateTest() {
        Faker faker = new Faker();
        String name = faker.name().name();
        String job = "zion resident";

        PatchUserBody patchUserBody = new PatchUserBody();
        patchUserBody.setName(name);
        patchUserBody.setJob(job);
        PatchUserResponse response = patchUpdate(patchUserBody);

        assertThat(response.getName(), is(name));
        assertThat(response.getJob(), is(job));
        assertThat(response.getUpdatedAt(), notNullValue());
    }

    // Patch Update Method
    public PatchUserResponse patchUpdate(PatchUserBody body) {
        PatchUserResponse response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .request(Method.PATCH, "/users/2")
                .as(PatchUserResponse.class);
        return response;
    }
}