package currency;

import java.text.DateFormat;
	import java.text.ParseException;
	import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

	public class DateValidator {
 		
 		private String dateStr;
		
public boolean isDateValid(String dateStr) {
		   // DateFormat sdf = new SimpleDateFormat(this.dateFormat);
		    String pattern = "yyyy-MM-dd";
		    DateFormat format = new SimpleDateFormat(pattern);
	 
	        format.setLenient(false);

	        try {
	            format.parse(dateStr);
	        } catch (ParseException e) {
	            return false;
	        }
	        return true;
		    }

			public String getDateStr() {
				return dateStr;
			}
			
			
public boolean isDateCorrect(String dateStr) throws ParseException {
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
	cal.setTime(sdf.parse(dateStr));
	 
		if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
			return false;
		}
		
		if (cal.get(Calendar.MONTH) == Calendar.JANUARY
			&& cal.get(Calendar.DAY_OF_MONTH) == 1) {
			return false;
		}
		
		// check if Christmas
		if (cal.get(Calendar.MONTH) == Calendar.DECEMBER
			&& cal.get(Calendar.DAY_OF_MONTH) == 25) {
			return false;
		}
		
		// check if 4th of July
		if (cal.get(Calendar.MONTH) == Calendar.MAY
			&& cal.get(Calendar.DAY_OF_MONTH) == 1) {
			return false;
		}
				
		return true;
	}
	}


