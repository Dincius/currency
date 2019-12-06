package currency;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CurrencySyst {

	private String userInput;
	private String userInputDate1;
	private String userInputDate2;
	private String userInputCode;
	public static List<String> currencyList;
	public static List<String> inputCodes;

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
				inputCodes = codesToArray();
				courseOfSpecDate(userInputDate1, inputCodes);
				System.out.println("Choose again:\n");
				break;
			case "2":
				System.out.println("* Please enter the date in format 'yyyy-MM-dd'");
				userInputDate1 = sn.next();
				System.out.println("* Please enter the second date in format 'yyyy-MM-dd'");
				userInputDate2 = sn.next();
				inputCodes = codesToArray();
				diffOfCourse(userInputDate1, userInputDate2, inputCodes);
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
		
	private void courseOfSpecDate(String userInputDate1, List<String> inputCodes) throws ParseException, MalformedURLException {
		String csvUrl =  "https://www.lb.lt/lt/currency/daylyexport/?csv=1&class=Eu&type=day&date_day=";
		
		DateValidator dv = new DateValidator();
		
		if (dv.isDateCorrect(userInputDate1) == true) {
			csvUrl = csvUrl.concat(userInputDate1);
		
		double course = 0;
		
		CSVreader oneDate = new CSVreader();
		currencyList = oneDate.createItemList(csvUrl);
		List<Currency> finalList = oneDate.makeCurrency(currencyList);
		
		for(int a = 0; a < inputCodes.size(); a++) {
			userInputCode = inputCodes.get(a);
			
			
		if(dv.isDateValid(userInputDate1) == true) {
			for(int i = 0; i < finalList.size(); i++) {
				if (finalList.get(i).getCode().equals(userInputCode.toUpperCase())) {
					System.out.println("Currency " + finalList.get(i).getName() + " with code " + finalList.get(i).getCode());
					
					course = finalList.get(i).getCourse();
					System.out.println("Course on " + userInputDate1 + " was " + course);
				}
			}
		}
		else {
			System.out.println("Warning! Date is not valid to 'yyyy-MM-dd' pattern");
			}
		}
		
		if(!finalList.get(0).getDate().equals(userInputDate1)) {
			System.out.println("WARNING! Date is not working day!");
			System.out.println("System uses the last published course.");
			}
		}
		else {
			System.out.println("The date is not working date! Please choose again.");
		}
	}

	
	private void diffOfCourse(String userInputDate1, String userInputDate2, List<String> inputCodes) throws ParseException, MalformedURLException {
		String csvUrl1 = "https://www.lb.lt/lt/currency/exportlist/?csv=1&currency=";
		String csvUrl2 = "&ff=1&class=Eu&type=day&date_from_day=";
		String csvUrl3 = "&date_to_day=";
		
		double course1 = 0;
		double course2 = 0;
		
		for(int a = 0; a < inputCodes.size(); a++) {
			userInputCode = inputCodes.get(a);

		String csvUrl = csvUrl1.concat(userInputCode.concat(csvUrl2.concat(userInputDate1.concat(csvUrl3.concat(userInputDate2)))));
		
		CSVreader period = new CSVreader(); 
		currencyList = period.createItemList(csvUrl);

		List<Currency> finalList = period.makeCurrency(currencyList);
		
		System.out.println("Currency " + finalList.get(0).getName() + " with code " + finalList.get(0).getCode());
		DateValidator dv = new DateValidator();
		
		if(dv.isDateValid(userInputDate1) == true && dv.isDateValid(userInputDate2) == true) {
			for(int i = 0; i < finalList.size(); i++) {
			    course1 = finalList.get(0).getCourse();
			    course2 = finalList.get(finalList.size()-1).getCourse();
		}
		
			  System.out.println("Course on " + finalList.get(0).getDate() + " was " + course1);
			  System.out.println("Course on " + finalList.get(finalList.size()-1).getDate() + " was " + course2);
		}
		else if(!finalList.get(0).getDate().equals(userInputDate2) || !finalList.get(finalList.size()-1).getDate().equals(userInputDate2)) {
			System.out.println("WARNING!One or both dates is not working day!");
			System.out.println("System uses the last published course.");
		}
		else {
			System.out.println("Warning! Date is not valid to 'yyyy-MM-dd' pattern");
		}
		
		
		double difference = course2 - course1;
		double diffPercents = (difference * 100)/course1;
       
		if (diffPercents == 0) {
    	   System.out.println("Currency have not changed on this period.");	
    	   System.out.println(" ");
		}
		else {
			System.out.println("Currency course has changed by " + difference + " (" + diffPercents + "%) on this period.");	
			System.out.println(" ");
		}
		}
	}
	
	private List<String> codesToArray() {

		List<String> inputCodes = new ArrayList<String>();       
		boolean notEnd = true;
		
		System.out.println("* Please enter currencies codes. When you finnish, enter '0'.");
	    Scanner sn = new Scanner(System.in);
	    
	    while(notEnd) {
	    	userInputCode = sn.nextLine();
	        if(userInputCode.equals("0")) {
	        	notEnd = false;
	        	System.out.println(inputCodes);
	        	}
	        else {
	        	inputCodes.add(userInputCode);
	        	}    
	    }
	    
		return inputCodes;
	}
	}