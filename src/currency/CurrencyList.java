package currency;

import java.util.ArrayList;
import java.util.List;

public class CurrencyList {
	private String part1;
	private String part2;
	
	private String name;
	private String code;
	private double course;
	private String date;
	
	public void makeCurrency(List<CurrencyList> currencyList) {
//		String csvUrl1 = "https://www.lb.lt/lt/currency/exportlist/?csv=1&currency=";
//		String csvUrl2 = "&ff=1&class=Eu&type=day&date_from_day=";
//		String csvUrl3 = "&date_to_day=";
//		
//		String csvUrl = csvUrl1.concat(inputCode.concat(csvUrl2.concat(inputDate1.concat(csvUrl3.concat(inputDate2)))));
//		
//		CSVreader period = new CSVreader(); 
//		period.createItemList(csvUrl);
		
		List<Currency> normalList = new ArrayList<Currency>();
		for(CurrencyList i : currencyList) {
			String str = i.getPart1().concat(".".concat(i.getPart2()));
			String[] arrOfStr = str.split(";");
			
			for (String a : arrOfStr) {
				a.replace(" \" ", "");
//				System.out.println(a.replace(" \" ", ""));

		    Currency currency = new Currency(arrOfStr[0],
		    		arrOfStr[1],
		    		Double.parseDouble(arrOfStr[2]),
		    		arrOfStr[3]);
		    normalList.add(currency);
		    System.out.println(currency);
			}
		
		}
		
		
	}
	public CurrencyList(String part1, String part2) {
		this.part1 = part1;
		this.part2 = part2;
	}
	public String getPart1() {
		return part1;
	}
	public void setPart1(String part1) {
		this.part1 = part1;
	}
	public String getPart2() {
		return part2;
	}
	public void setPart2(String part2) {
		this.part2 = part2;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public double getCourse() {
		return course;
	}
	public void setCourse(double course) {
		this.course = course;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
