package com.lise;

import com.lise.utils.ApplicationProperties;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

public class BaseClass {
    @BeforeSuite
    public void beforeSuite() {
        RestAssured.baseURI = ApplicationProperties.INSTANCE.getUrl();
        System.out.println("Before Suite");
    }
}
