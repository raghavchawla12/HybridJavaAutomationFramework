package apiUtils.apiResources;

import apiUtils.pojoClassRequest.GetAllProducts;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuilder {

    public GetAllProducts getAllProductPayload() {
        GetAllProducts getall = new GetAllProducts();
        List<String> list = new ArrayList<String>();
        getall.setProductName("");
        getall.setMinPrice(0);
        getall.setMaxPrice(1000000000);
        getall.setProductCategory(list);
        getall.setProductFor(list);
        getall.setProductSubCategory(list);
        return getall;
    }
}
