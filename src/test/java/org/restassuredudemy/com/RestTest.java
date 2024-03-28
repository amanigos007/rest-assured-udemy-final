package org.restassuredudemy.com;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.restassuredudemy.com.data.Employee;
import org.restassuredudemy.com.utils.ResponseBodyUtils;
import org.testng.annotations.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class RestTest {

    @Test
    public void firstTest() {

        Map<String, String> headers = new HashMap<>();
        headers.put("x-api-key", "");
        headers.put("content-type", "application/json");

        Response response = RestAssured
                .given()
                .baseUri("https://api.postman.com")
                .basePath("/workspaces/ade0fe70-3e0e-4a0f-83b4-1fa090231be2")
                .headers(headers)
                .when()
                .get();

        response.then().log().body();
        assertThat(response.statusCode()).isEqualTo(200);
        System.out.println("Id: " + ResponseBodyUtils.fetchJsonValue(response, "$.workspace.id"));

        String listOfEnvironments =
                ResponseBodyUtils.fetchJsonValue(response, "$.workspace.environments..name")
                        .toString().replaceAll("^\\[|]$", "").replaceAll("\"", "");

        List<String> environments = new ArrayList<>(Arrays.asList(listOfEnvironments.split(",")));
        environments.forEach(System.out::println);
    }

    @Test
    public void postmanMockServerRequest() {

        Employee employee = Employee
                .builder()
                .name("Tester")
                .idNumber(1131018)
                .build();

        RestAssured.given()
                .baseUri("https://c1293e7d-c1f6-4f3e-b191-1bf76ecbabb4.mock.pstmn.io")
                .header("content-type", "application/json")
                .body(employee)
                .post()
                .then()
                .statusCode(201)
                .log()
                .body();
    }
}
