package currency;


import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLreader {
	
	private String xmlUrl = "https://www.lb.lt/lt/currency/daylyexport/?xml=1&class=Eu&type=day&date_day=";
	
	public List<Currency> createCurrencyList(String userInput) {
		List<Currency> currency = new ArrayList<Currency>();
		try {
	//		File xmlFile = new File("D://Users/Documents/IES praktika/currency-2019-12-02.xml");
		URL xmlFile = new URL(xmlUrl.concat(userInput));
	    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbFactory.newDocumentBuilder();
		InputStream stream = xmlFile.openStream();
		Document doc = db.parse(stream);
		doc.getDocumentElement().normalize();
		System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
		NodeList nodeList = doc.getElementsByTagName("item");
		
		for(int itr = 0; itr < nodeList.getLength(); itr++) {
			Node node = nodeList.item(itr);
//			System.out.println("\nNode Name:" + node.getNodeName());
			
			if (node.getNodeType() == Node.ELEMENT_NODE)  {  
					Element eElement = (Element) node;
					Currency moneys = new Currency(eElement.getElementsByTagName("pavadinimas").item(itr).getTextContent(),
							eElement.getElementsByTagName("valiutos_kodas").item(itr).getTextContent(),
							doubleCourse(eElement.getElementsByTagName("santykis").item(itr).getTextContent()),
							eElement.getElementsByTagName("data").item(itr).getTextContent());
					currency.add(moneys);
			System.out.println(currency);
			return currency;
			}  
			}
		    }
			catch (Exception e)   
			{  
			e.printStackTrace();  
			}
			return currency;  
			}
	
	public double doubleCourse(String course) {		
	    double courseNo = 0;
		if( course.indexOf(",") != -1 )
		 {
		     course.replaceAll(",","\\.");
		     courseNo = Double.parseDouble(course);
		}
        return courseNo;
	}

	 public String addDate(String userInput) throws ParseException {

		 DateValidator dv = new DateValidator();
		 
			if (dv.isDateValid(userInput) == true) {
				xmlUrl = xmlUrl.concat(userInput);
				 return xmlUrl;
			}
			else
			{
				System.out.println("Incorrect date format!");
	     		return "Date format 'yyyy-MM-dd'";
	 		}
	 }
}

