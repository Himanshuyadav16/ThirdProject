package com.lise.testcases;

import com.lise.BaseClass;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DeleteResponse extends BaseClass {
    @Test
    public void deleteResponseTest() {
        Response response = deleteUser();

        assertThat(response.getStatusCode(), is(HttpStatus.SC_NO_CONTENT));

    }
    // Delete Method
    public Response deleteUser() {
        Response response = given()
                .request(Method.DELETE, "/users/2");
        return response;
    }
}
