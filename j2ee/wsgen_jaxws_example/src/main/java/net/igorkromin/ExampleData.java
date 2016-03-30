package net.igorkromin;

public class ExampleData {

	private String dataId;

	public ExampleData(String dataId) {
		this.dataId = dataId;
	}

	public String toString() {
		return "Some data with ID: " + dataId;
	}
}
