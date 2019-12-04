package currency;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sun.jdi.connect.spi.Connection;

public class CSVreader {
	private static final String splitCSV = ",";
	
	 
	public List<CurrencyList> createItemList(String csvUrl) throws MalformedURLException {
		
			BufferedReader br = null;
		
		URL urlCSV = new URL(csvUrl);
	 
		List<CurrencyList> currList = new ArrayList<CurrencyList>();
		try {
//			InputStream stream = urlXml.openStream();
//			InputStreamReader one = new InputStreamReader(stream);
			br = new BufferedReader(new InputStreamReader(urlCSV.openStream()));
			
			String line = ",";
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] allItems = line.split(splitCSV);
//				System.out.println(allItems);
				if(allItems.length > 0) {
					CurrencyList currencyToNormal = new CurrencyList(allItems[0], 
							allItems[1]);
					currList.add(currencyToNormal);
				}
			}
//			 for(CurrencyList i : currList) {
//				 System.out.println(i.getPart1() + "."+ i.getPart2());
//			 }
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

}