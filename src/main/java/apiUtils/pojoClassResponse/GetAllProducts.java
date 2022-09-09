package apiUtils.pojoClassResponse;

import java.util.List;

public class GetAllProducts {

	private List<DataGetAllProducts> data;
	private int count;
	private String message;

	public List<DataGetAllProducts> getData() {
		return data;
	}

	public void setData(List<DataGetAllProducts> data) {
		this.data = data;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
