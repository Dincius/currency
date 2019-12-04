package currency;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sun.jdi.connect.spi.Connection;

public class CSVreader {
	private static final String splitCSV = ",";
	
	 
	public List<String> createItemList(String csvUrl) throws MalformedURLException {
		
			BufferedReader br = null;
		
		URL urlCSV = new URL(csvUrl);
	 
		List<String> currList = new ArrayList<String>();
		
		try {
			br = new BufferedReader(new InputStreamReader(urlCSV.openStream()));
			
			String line = "";
			br.readLine();
			
			while ((line = br.readLine()) != null) {
				String[] allItems = line.split(splitCSV);
				if(allItems.length > 0) {
					String currencyToNormal = new String(allItems[0].concat(".".concat(allItems[1])));
					currList.add(currencyToNormal);
				}
			}
		}			
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			
			try {
				br.close();
			}
			catch(IOException ie) {
				System.out.println("Error while closing");
				ie.printStackTrace();
			}
		}
		return currList;
	}

	public List<Currency> makeCurrency(List<String> currencyList) {
		
		List<Currency> normalList = new ArrayList<Currency>();
		for(String i : currencyList) {
			String str = i.replace("\"", "");
			String[] arrOfStr = 
					str.split(";");
			
		    Currency currency = new Currency(arrOfStr[0],
		    		arrOfStr[1],
		    		Double.parseDouble(arrOfStr[2]),
		    		arrOfStr[3]);
		    normalList.add(currency);
			}
		return normalList;
		
	}
}