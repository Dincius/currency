package currency;

public class Currency {
	
	private String name;
	private String code;
	private String date;
	private double course;
	
	public Currency(String name, String code, double course, String date) {
		this.name = name;
		this.code = code;
		this.course = course;
		this.date = date;
	}
	
	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	public double getCourse() {
		return course;
	}
	
	public String getDate() {
		return date;
	}
	
	public String toString() {
		 return "Currency " + name + ", code " + code + ", course " + course + ", date " + date + ".";
		}

}
