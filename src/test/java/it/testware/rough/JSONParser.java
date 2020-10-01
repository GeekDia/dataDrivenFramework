package it.testware.rough;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONObject;

import it.testware.utilities.Utilities;

public class JSONParser {

	static InputStream inputStream;
	static JSONObject object;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String fileName = "data/testData.json";
		object = Utilities.getTestDataFromJson(fileName);

		System.out.println(object.getJSONObject("invalidUsername").get("username"));

		System.out.println(object.getJSONObject("validUsername").get("password"));

	}

}
