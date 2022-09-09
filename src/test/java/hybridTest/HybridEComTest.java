package hybridTest;

import TestComponents.Retry;
import apiUtils.apiAutomationScript.ApiScript;
import apiUtils.apiResources.ApiUtilities;
import apiUtils.pojoClassResponse.DataGetAllProducts;
import apiUtils.pojoClassResponse.GetAllProducts;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.*;
import resources.BaseClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HybridEComTest extends BaseClass {

    public WebDriver driver;
    private static final Logger log = LogManager.getLogger(HybridEComTest.class.getName());
    MainPage mp; LoginPage lp; ApiScript apisc; CartPage cap; String email; String password;
    PaymentPage payp; OrderPlaced orderplaced;

    @BeforeMethod(alwaysRun = true)
    public void initializeDriverAndBrowser() throws IOException {
        driver = initializeDriver();
        driver.get(prop.getProperty("url"));
        lp = new LoginPage(driver);
        mp = new MainPage(driver);
        apisc = new ApiScript();
        cap = new CartPage(driver);
        payp = new PaymentPage(driver);
        orderplaced = new OrderPlaced(driver);

    }

    @Test(retryAnalyzer= Retry.class, dataProvider = "getLoginData")
    public void verify_that_user_is_able_to_login_to_application_through_api_and_ui(HashMap<String, String> input) throws IOException {
        lp.loginToApplication(input.get("email"), input.get("password"));
        log.info("Logged in with " + input.get("email") + " and " + input.get("password"));
        if (mp.getOrdersButtonText().equalsIgnoreCase("ORDERS")) {
            Assert.assertTrue(true);
            log.info("Login Successful through UI");
        }
        else {
            Assert.assertTrue(false);
            log.error("Login failed through UI");
        }
        if (apisc.loginAPI(input.get("email"), input.get("password")).statusCode() == 200) {
            Assert.assertTrue(true);
            log.info("Login successful through API");
        }
        else {
            Assert.assertTrue(false);
            log.error("Login failed through API");
        }
    }

    @Test(retryAnalyzer= Retry.class, dataProvider = "getLoginData")
    public void verify_that_get_all_products_names_are_equals_and_name_matches_with_api_response
            (HashMap<String, String> input) throws IOException {
        email = input.get("email");
        password = input.get("password");
        // Get Products Name from UI
        lp.loginToApplication(input.get("email"), input.get("password"));
        List<String> uiList = mp.storeAllProductsNameInAList();
        log.info("Product List from UI is " + uiList);
        // Get Products Name from API
        Response resp = apisc.getAllProductsAPI(email, password);
        GetAllProducts responseGetAllProducts = resp.as(GetAllProducts.class);
        List<DataGetAllProducts> dataSetList = responseGetAllProducts.getData();
        List<String> apiList = new ArrayList<String>();
        for (int i = 0; i < dataSetList.size(); i++) {
            String productNames = dataSetList.get(i).getProductName().toUpperCase();
            apiList.add(productNames);
        }
        log.info("Product List from API is " + apiList);
        // Compare both list
        if (uiList.equals(apiList)) {
            Assert.assertTrue(true);
            log.info("Product List from UI and API are same");
        } else {
            Assert.assertTrue(false);
            log.info("Product List from UI and API are not same");
        }
    }

        @Test(retryAnalyzer= Retry.class, dataProvider = "getOrderPlaceData")
        public void verify_that_user_is_able_to_place_order_with_new_created_product(HashMap<String, String> input) throws Exception {
            String productId = null;
            email = input.get("email");
            password = input.get("password");
            try {
                // Create New Product from API
                Response createProductApiResponse = apisc.createProductAPI(email, password);
                ApiUtilities apu = new ApiUtilities();
                productId = apu.jsonParsingUsingJsonPathClass(createProductApiResponse, "productId");
                // Test from UI
                lp.loginToApplication(email, password);
                mp.addTestProductToCart("TEST PRODUCT");
                mp.clickOnCartButton();
                cap.clickOnBuyNowButton();
                payp.placeOrder(input.get("cvv"), input.get("cardHolderName"), input.get("countryName"));
                if(orderplaced.getSuccessfulOrderText().equals("THANKYOU FOR THE ORDER.")){
                    Assert.assertTrue(true);
                    log.info("Order successfully placed");
                }
                else{
                    Assert.assertTrue(false);
                    log.error("Order is not placed");
                }
            }
            finally {
                Response deleteProductResponse = apisc.deleteProductAPI(email, password, productId);
                if(deleteProductResponse.statusCode() == 200){
                    log.info(productId + " deleted");
                }
                else{
                    log.error(productId + " not deleted");
                }
            }
        }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    @DataProvider
    public Object[][] getLoginData() throws IOException {
        List<HashMap<String, String>> data = getJsonDataToMap(
                System.getProperty("user.dir") + "//src//main/java//TestData//LoginTestData.json");
        return new Object[][]{{data.get(0)}};
    }

    @DataProvider
    public Object[][] getOrderPlaceData() throws IOException {
        List<HashMap<String, String>> data = getJsonDataToMap(
                System.getProperty("user.dir") + "//src//main/java//TestData//OrderPlaceData.json");
        return new Object[][]{{data.get(0)}};
    }
}
