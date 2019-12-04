package currency;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class CurrencySyst {

	private String userInput;
	private String userInputDate1;
	private String userInputDate2;
	private String userInputCode;
	public static List<CurrencyList> currencyList;

	public void Start() throws ParseException, MalformedURLException {


		while(true){
			System.out.println("***** WELCOME TO CURRENCY INFORMATION SYSTEM *****");
			System.out.println("* Press 1 to get information about currency's course on specific day");
			System.out.println("* Press 2 to get currencies of period of time");
			System.out.println("* Press 3 to exit");
			System.out.println("Enter your choice");

			Scanner sn = new Scanner(System.in);

			userInput = sn.next();

			switch(userInput){
			case "1": 				
				System.out.println("* Please enter the date in format 'yyyy-MM-dd'");
				userInputDate1 = sn.next();
				System.out.println("* Please enter code of currency");
				userInputCode = sn.next();
				courseOfSpecDate(userInputDate1, userInputCode);
				System.out.println("Choose again:\n");
				break;
			case "2":
				System.out.println("* Please enter the date in format 'yyyy-MM-dd'");
				userInputDate1 = sn.next();
				System.out.println("* Please enter the second date in format 'yyyy-MM-dd'");
				userInputDate2 = sn.next();
				System.out.println("* Please enter code of currency");
				System.out.println("Enter 0 to finish.");
				userInputCode = sn.next();
				diffOfCourse(userInputDate1, userInputDate2, userInputCode);
				System.out.println("Choose again:\n");
				break;
			case "3":
				System.out.println("Exiting...");
				System.exit(0);
				break;
			default:
				System.out.println("Incorrect entry.");
				System.out.println("Choose again:\n");
		}
	}
		
	}
	
	private String isWorkingDay(String userInputDate, Currency currency) {
		DateValidator dv = new DateValidator();
		
		if(dv.isDateCorrect(userInputDate, currency) == false) {
			return "Warning! Your selected date is not working day. System uses last working day!";
		}
		else
		{
			return "Your selected date is valid.";
		}
	}
		
	private double courseOfSpecDate(String userInputDate1, String userInputCode) throws ParseException, MalformedURLException {
		// TODO Auto-generated method stub
		String csvUrl =  "https://www.lb.lt/lt/currency/daylyexport/?csv=1&class=Eu&type=day&date_day=";
		csvUrl = csvUrl.concat(userInputDate1);
		
		CSVreader oneDate = new CSVreader();
		currencyList = oneDate.createItemList(csvUrl);
		
		
		
		for(CurrencyList i : currencyList) {
			CurrencyList one = new CurrencyList(i.getPart1(), i.getPart2());
			one.makeCurrency(currencyList);
//			System.out.println("Currency " + i.getPart1() + ", code " + i.getPart2());
//			if (i.getCode().equals(userInputCode.toUpperCase())) {
//				System.out.println("Currency " + i.getPart1() + ", code " + i.getPart2());
////				isWorkingDay(userInputDate1, i);
//			return i.getCourse();
//			}
//			else {
//				System.out.println("No such currency found!");
//			}
			
		}
		return 0; 	
	}
	
	private double diffOfCourse(String userInputDate1, String userInputDate2, String userInputCode) throws ParseException, MalformedURLException {
		String csvUrl1 = "https://www.lb.lt/lt/currency/exportlist/?csv=1&currency=";
		String csvUrl2 = "&ff=1&class=Eu&type=day&date_from_day=";
		String csvUrl3 = "&date_to_day=";
		
		String csvUrl = csvUrl1.concat(userInputCode.concat(csvUrl2.concat(userInputDate1.concat(csvUrl3.concat(userInputDate2)))));
		
		CSVreader period = new CSVreader(); 
		period.createItemList(csvUrl);
		
		double course1 = courseOfSpecDate(userInputDate1, userInputCode);
		double course2 = courseOfSpecDate(userInputDate2, userInputCode);
	    double difference = course2 - course1;
		double diffPercents = (difference * 100)/course1;
	System.out.println("Currency course has changed by " + diffPercents + "% on this period.");
	return diffPercents;	
}
}