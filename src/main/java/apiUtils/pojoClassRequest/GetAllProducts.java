package apiUtils.pojoClassRequest;

import java.util.List;

public class GetAllProducts {
	
	private String productName;
	private int minPrice;
	private int maxPrice;
	private List<String> productCategory;
	private List<String> productSubCategory;
	private List<String>productFor;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}
	public int getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}
	public List<String> getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(List<String> productCategory) {
		this.productCategory = productCategory;
	}
	public List<String> getProductSubCategory() {
		return productSubCategory;
	}
	public void setProductSubCategory(List<String> productSubCategory) {
		this.productSubCategory = productSubCategory;
	}
	public List<String> getProductFor() {
		return productFor;
	}
	public void setProductFor(List<String> productFor) {
		this.productFor = productFor;
	}
	

}
