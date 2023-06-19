package com.lise.testcases.GetMethod;

import com.lise.BaseClass;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetResponse extends BaseClass {
    @Test
    public void GetListTest() {
        Response response = getListUser();

        JSONObject jsonObject = new JSONObject(response.asString());

        assertThat(jsonObject.getInt("page"), greaterThan(0));
        assertThat(jsonObject.getInt("per_page"), greaterThan(0));
        assertThat(jsonObject.getInt("total_pages"), greaterThan(0));
        assertThat(jsonObject.getInt("total"), greaterThan(0));

        JSONArray data = jsonObject.getJSONArray("data");
        assertThat(jsonObject.getJSONArray("data"), notNullValue());

        JSONObject json = data.getJSONObject(0);

        assertThat(json.getInt("id"), greaterThan(0));
        assertThat(json.getString("last_name"), notNullValue());
        assertThat(json.getString("email"), notNullValue());
        assertThat(json.getString("first_name"), notNullValue());
        assertThat(json.getString("avatar"), notNullValue());

        JSONObject jsonData = jsonObject.getJSONObject("support");
        assertThat(jsonData.getString("url"), notNullValue());
        assertThat(jsonData.getString("text"), notNullValue());
    }

    @Test
    public void getSingleTest() {
        Response response = getSingleUser();

        JSONObject json = new JSONObject(response.asString());

        JSONObject jsonObject = json.getJSONObject("data");

        assertThat(jsonObject.getInt("id"), greaterThan(0));
        assertThat(jsonObject.getString("last_name"), notNullValue());
        assertThat(jsonObject.getString("email"), notNullValue());
        assertThat(jsonObject.getString("first_name"), notNullValue());
        assertThat(jsonObject.getString("avatar"), notNullValue());

        JSONObject jsonData = json.getJSONObject("support");
        assertThat(jsonData.getString("url"), notNullValue());
        assertThat(jsonData.getString("text"), notNullValue());
    }

    @Test
    public void getSingleUserNotFoundTest() {
        Response response = getSingleUserNotFound();
        assertThat(response.getStatusCode(), is(HttpStatus.SC_NOT_FOUND));
    }

    @Test
    public void getListResourceTest() {
        Response response = getListResource();

        JSONObject jsonObject = new JSONObject(response.asString());

        assertThat(jsonObject.getInt("page"), greaterThan(0));
        assertThat(jsonObject.getInt("per_page"), greaterThan(0));
        assertThat(jsonObject.getInt("total_pages"), greaterThan(0));
        assertThat(jsonObject.getInt("total"), greaterThan(0));

        JSONArray data = jsonObject.getJSONArray("data");
        assertThat(jsonObject.getJSONArray("data"), notNullValue());

        JSONObject json = data.getJSONObject(0);

        assertThat(json.getInt("id"), greaterThan(0));
        assertThat(json.getString("name"), notNullValue());
        assertThat(json.getInt("year"), notNullValue());
        assertThat(json.getString("color"), notNullValue());
        assertThat(json.getString("pantone_value"), notNullValue());

        JSONObject jsonData = jsonObject.getJSONObject("support");
        assertThat(jsonData.getString("url"), notNullValue());
        assertThat(jsonData.getString("text"), notNullValue());
    }

    @Test
    public void getSingleResourceTest() {
        Response response = getSingleResource();

        JSONObject json = new JSONObject(response.asString());

        JSONObject jsonObject = json.getJSONObject("data");

        assertThat(jsonObject.getInt("id"), greaterThan(0));
        assertThat(jsonObject.getString("name"), notNullValue());
        assertThat(jsonObject.getInt("year"), notNullValue());
        assertThat(jsonObject.getString("color"), notNullValue());
        assertThat(jsonObject.getString("pantone_value"), notNullValue());

        JSONObject jsonData = json.getJSONObject("support");
        assertThat(jsonData.getString("url"), notNullValue());
        assertThat(jsonData.getString("text"), notNullValue());
    }

    @Test
    public void getSingleResourceNotFoundTest() {
        Response response = getSingleResourceNotFound();

        assertThat(response.getStatusCode(), is(HttpStatus.SC_NOT_FOUND));
    }

    @Test
    public void getDelayedResponseTest(){
        Response response=getDelayedResponse();

        JSONObject jsonObject= new JSONObject(response.asString());

        assertThat(jsonObject.getInt("page"), greaterThan(0));
        assertThat(jsonObject.getInt("per_page"), greaterThan(0));
        assertThat(jsonObject.getInt("total_pages"), greaterThan(0));
        assertThat(jsonObject.getInt("total"), greaterThan(0));

        JSONArray data = jsonObject.getJSONArray("data");
        assertThat(jsonObject.getJSONArray("data"), notNullValue());

        JSONObject json = data.getJSONObject(0);

        assertThat(json.getInt("id"), greaterThan(0));
        assertThat(json.getString("last_name"), notNullValue());
        assertThat(json.getString("email"), notNullValue());
        assertThat(json.getString("first_name"), notNullValue());
        assertThat(json.getString("avatar"), notNullValue());

        JSONObject jsonData=jsonObject.getJSONObject("support");

        assertThat(jsonData.getString("url"),notNullValue());

        assertThat(jsonData.getString("text"),notNullValue());
    }

    //Get List Method
    public Response getListUser() {
        Response response = given()
                .request(Method.GET, "/users?page=2");
        return response;
    }

    //Get Single Method
    public Response getSingleUser() {
        Response response = given()
                .request(Method.GET, "/users/2");
        return response;
    }

    //Get Single User Not Found Method
    public Response getSingleUserNotFound() {
        Response response = given()
                .request(Method.GET, "/users/23");
        return response;
    }

    //Get List <Resource> Method
    public Response getListResource() {
        Response response = given()
                .request(Method.GET, "/unknown");
        return response;
    }

    //Get Single <Resource> Method
    public Response getSingleResource() {
        Response response = given()
                .request(Method.GET, "unknown/2");
        return response;
    }

    //Get Single <Resource> Not Found Method
    public Response getSingleResourceNotFound() {
        Response response = given()
                .request(Method.GET, "/unknown/23");
        return response;
    }
    //Get Delayed response
    public  Response getDelayedResponse(){
    Response response=given()
            .request(Method.GET,"/users?delay=3");
    return response;
}
}