package apiUtils.apiResources;

public enum EndPoints {

    loginAPI("/api/ecom/auth/login"),
    getAllProductsAPI("/api/ecom/product/get-all-products"),
    createProductAPI("/api/ecom/product/add-product"),
    getIndividualProductDetail("/api/ecom/product/get-product-detail/{key}"),
    deleteProductAPI("/api/ecom/product/delete-product/{productId}");

    private String resource;


    EndPoints(String resource){
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }
}
