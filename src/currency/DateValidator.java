package currency;

import java.text.DateFormat;
	import java.text.ParseException;
	import java.text.SimpleDateFormat;

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
			
			public boolean isDateCorrect(String dateStr, Currency knowDate) {
				String dateFromXml = knowDate.getDate();
				if (dateFromXml.equals(dateStr)) {
					return true;
				}
				else {
					return false;
				}
			}

	}

