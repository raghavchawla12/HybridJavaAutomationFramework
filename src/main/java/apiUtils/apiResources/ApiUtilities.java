package apiUtils.apiResources;

import apiUtils.apiPayload.APIPayload;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class ApiUtilities {

    String apiResponse; JsonPath js;

    static RequestSpecification req;
    Response res;

    public RequestSpecification loginRequestSpecification(String email, String password) throws IOException {
        RequestSpecification req2 = new RequestSpecBuilder()
                .setBaseUri(getGlobalValue("baseUrl")).setContentType("application/json")
                .setBody(APIPayload.loginAPIPayload(email, password))
                .build();
        return req2;
    }

    public RequestSpecification commonRequestSpecification(String token) throws IOException {
        if(req==null) {
            PrintStream log = new PrintStream(new FileOutputStream("API_Logs.txt"));
            req = new RequestSpecBuilder()
                    .setBaseUri(getGlobalValue("baseUrl")).addHeader("authorization", token)
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .build();
            return req;
        }
        return req;
    }

    public static String getGlobalValue(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") +
                        "\\src\\main\\java\\apiUtils\\apiResources\\globalURL.properties");
        prop.load(fis);
        return prop.getProperty(key);
    }

    public String jsonParsingUsingJsonPathClass(Response response, String key) {
        apiResponse = response.asString();
        js = new JsonPath(apiResponse);
        String value = js.get(key).toString();
        return value;
    }

    public Response callAPI(RequestSpecification req, String resource, String httpMethod){
        EndPoints resourceAPI = EndPoints.valueOf(resource);
        if (httpMethod.equalsIgnoreCase("POST"))
            res = req.when().post(resourceAPI.getResource());
        else if (httpMethod.equalsIgnoreCase("GET"))
            res = req.when().get(resourceAPI.getResource());
        else if (httpMethod.equalsIgnoreCase("DELETE"))
            res = req.when().delete(resourceAPI.getResource());
        else if (httpMethod.equalsIgnoreCase("PUT"))
            res = req.when().put(resourceAPI.getResource()).then().extract().response();
        return res;
    }
}

