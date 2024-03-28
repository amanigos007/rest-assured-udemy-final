package org.restassuredudemy.com.utils;

import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;

public final class ResponseBodyUtils {

    private ResponseBodyUtils() {}

    public static Object fetchJsonValue(Response response, String jsonPath) {
        return JsonPath.read(response.body().asString(), jsonPath);
    }
}
