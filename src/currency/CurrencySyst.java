package currency;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import currency.DateValidator;
import currency.Currency;

public class CurrencySyst {

	private String userInput;
	private String userInputDate1;
	private String userInputDate2;
	private String userInputCode;
	public static List<Currency> currencyList;

	public void Start() throws ParseException {

		Scanner sn = new Scanner(System.in);

		while(true){
			System.out.println("***** WELCOME TO CURRENCY INFORMATION SYSTEM *****");
			System.out.println("* Press 1 to get information about currency's course on specific day");
			System.out.println("* Press 2 to get currencies of period of time");
			System.out.println("* Press 3 to exit");
			System.out.println("Enter your choice");

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
			System.out.println("Invalid choice. Read the options carefully...");
			sn.close();
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
		
	private double courseOfSpecDate(String userInputDate1, String userInputCode) throws ParseException {
		// TODO Auto-generated method stub
		XMLreader oneDate = new XMLreader();
		currencyList = oneDate.createCurrencyList(userInputDate1);
		
		for(Currency i : currencyList) {
			if (i.getCode().equals(userInputCode.toUpperCase())) {
				System.out.println("Currency " + i.getName() + ", code " + i.getCode() + ", course on " + userInputDate1 + " was " + i.getCourse());
				isWorkingDay(userInputDate1, i);
			return i.getCourse();
			}
			else {
				System.out.println("No such currency found!");
			}
		}
		return 0; 	
	}
	
	private double diffOfCourse(String userInputDate1, String userInputDate2, String userInputCode) throws ParseException {
		
		double course1 = courseOfSpecDate(userInputDate1, userInputCode);
		double course2 = courseOfSpecDate(userInputDate2, userInputCode);
	    double difference = course2 - course1;
		double diffPercents = (difference * 100)/course1;
	System.out.println("Currency course has changed by " + diffPercents + "% on this period.");
	return diffPercents;	
}
}