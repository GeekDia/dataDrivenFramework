package it.testware.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Utilities {

	final private static int WAIT = 10;

	static InputStream inputStream;
	static JSONObject object;

	public static String getDateTime() {
		Date date = new Date();

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

		return dateFormat.format(date);
	}

	public static int getWait() {
		return WAIT;
	}

	/*
	 * 
	 * public Logger log() { return
	 * LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName()
	 * ); }
	 * 
	 */

	/*
	 * Get test Data form Json file
	 */

	public static JSONObject getTestDataFromJson(String file) throws IOException {
		try {
			String fileName = file;

			inputStream = Utilities.class.getClassLoader().getResourceAsStream(fileName);

			JSONTokener tokener = new JSONTokener(inputStream);
			object = new JSONObject(tokener);

		} catch (Exception e) {

		} finally {
			if (inputStream != null)
				inputStream.close();
		}

		return object;
	}

	/*
	 * Get static text (string) for assertion from xml file
	 */

	public HashMap<String, String> parseStringXML(InputStream file) throws Exception {
		HashMap<String, String> stringMap = new HashMap<String, String>();
		// Get Document Builder
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		// Build Document
		Document document = builder.parse(file);

		// Normalize the XML Structure; It's just too important !!
		document.getDocumentElement().normalize();

		// Here comes the root node
		Element root = document.getDocumentElement();

		// Get all elements
		NodeList nList = document.getElementsByTagName("string");

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node node = nList.item(temp);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;
				// Store each element key value in map
				stringMap.put(eElement.getAttribute("name"), eElement.getTextContent());
			}
		}
		return stringMap;
	}
}
