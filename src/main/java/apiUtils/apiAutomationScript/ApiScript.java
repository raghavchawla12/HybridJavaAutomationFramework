package apiUtils.apiAutomationScript;

import apiUtils.apiResources.ApiUtilities;
import apiUtils.apiResources.TestDataBuilder;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.IOException;

public class ApiScript extends ApiUtilities {

    RequestSpecification req;
    Response response;
    TestDataBuilder testDataBuilder = new TestDataBuilder();
    static String token;

    public String createLoginToken(String email, String password) throws IOException {
        String token = jsonParsingUsingJsonPathClass(loginAPI(email, password), "token");
        return token;
    }

    public Response loginAPI(String email, String password) throws IOException {
        RequestSpecification req2 = RestAssured.given().spec(loginRequestSpecification(email, password));
        Response loginResponse = callAPI(req2, "loginAPI", "POST");
        return loginResponse;
    }

    public Response getAllProductsAPI(String email, String password) throws IOException {
        token = createLoginToken(email, password);
        req = RestAssured.given().spec(commonRequestSpecification(token))
                .contentType("application/json").body(testDataBuilder.getAllProductPayload());
        response = callAPI(req, "getAllProductsAPI", "POST");
        return response;
    }

    public Response createProductAPI(String email, String password) throws IOException {
        token = createLoginToken(email, password);
        String userId = jsonParsingUsingJsonPathClass(loginAPI(email, password), "userId");
        req = RestAssured.given().spec(commonRequestSpecification(token))
                .param("productName", "Test Product")
                .param("productAddedBy", userId)
                .param("productCategory", "fashion")
                .param("productSubCategory", "shirts")
                .param("productPrice", 11500)
                .param("productDescription", "Addias Originals")
                .param("productFor", "women")
                .multiPart("productImage", new File(System.getProperty("user.dir")
                        + "\\src\\main\\java\\TestData\\images.png"));
        response = callAPI(req, "createProductAPI" ,"POST");
        return response;
    }

    public Response deleteProductAPI(String email, String password, String productId) throws IOException {
        token = createLoginToken(email, password);
        req = RestAssured.given().spec(commonRequestSpecification(token))
                .pathParam("productId", productId);
        response = callAPI(req, "deleteProductAPI", "DELETE");
        return response;
    }
}
