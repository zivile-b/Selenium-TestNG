package utils;

import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;  
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  

import org.openqa.selenium.WebDriver;

public class Utils {
	int leftLimit = 97; // letter 'a'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 10;
    Random random = new Random();
//   XLSXreader xlsx = new XLSXreader("test-data\\login.xlsx");
    
	public Utils() {
		
	}

	public String generatedString() {
		return random.ints(leftLimit, rightLimit + 1)
	              .limit(targetStringLength)
	              .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	              .toString();  
	}
		 public String getUserName() {
		        return configData("userNameXml"); 
		    }


		    public String getPassword() {
		        return configData("paswordXml");  
		    }

		    public String getUrl() {
		        return configData("urlXml");
		    }
		    
		    public String configData(String commonTag) {
		        try {
		            File File = new File("config\\config.xml");
		            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		            Document doc = dBuilder.parse(File);
		            doc.getDocumentElement().normalize();
		            NodeList nList = doc.getElementsByTagName("config");
		            Node nNode = nList.item(0);
		            System.out.println("\nCurrent Element :" + nNode.getNodeName());
		            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		                Element eElement = (Element) nNode;
		            
		                return eElement.getElementsByTagName(commonTag).item(0).getTextContent();
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        return "";
		
	}
		    
}	

