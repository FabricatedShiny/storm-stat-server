/**
 * @author Shiny Melhiraj, ID:114535120, shiny.melhiraj@stonybrook.edu  
 * 4/15/22
 * CSE 214-01 (R02)
 * Homework 6, Storm Class
 * 
 * This class represents a storm
 */
import java.io.Serializable;
public class Storm implements Serializable{
	private String name;
	private double precipitation;
	private double windspeed;
	private String date;
	
	/**
	 * Constructor method to create new storm instance
	 * 
	 * @param name
	 * 		name of the storm
	 * @param date
	 * 		the date the storm began in YYYY-MM-DD format
	 * @param precipitation
	 * 		the amount of rainfall during the storm in cm
	 * @param windspeed
	 * 		the windspeed during storm in km/h
	 */
	public Storm(String name, String date, double precipitation, double windspeed) {
		this.name = name;
		this.date = date;
		this.precipitation = precipitation;
		this.windspeed = windspeed;
	}
	
	/**
	 * Accessor method for the name of the storm
	 * 
	 * @return
	 * 		returns the name of the storm
	 */
	public String getName() {
			return name;
	}
	
	/**
	 * Accessor method for the total precipitation amount of the storm
	 * 
	 * @return
	 * 		returns the total precipitation during the storm
	 */
	public double getPrecipitation() {
		return precipitation;
	}
	
	/**
	 * Accessor method for the windspeed of the storm
	 * 
	 * @return
	 * 		returns the windspeed during the storm
	 */
	public double getWindspeed() {
		return windspeed;
	}
	
	/**
	 * Accessor method for the date of the storm
	 * 
	 * @return
	 * 		returns the date the storm began
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * Mutator method to change the name of the storm
	 * 
	 * @param name
	 * 		the new name of the storm
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Mutator method to change the amount of precipitation during the storm
	 * 
	 * @param precipitation
	 * 		the newtotal precipitation during the storm
	 */
	public void setPrecipitation(double precipitation) {
		this.precipitation = precipitation;
	}
	
	/**
	 * Mutator method to change the windspeed of the storm
	 * 
	 * @param windspeed
	 * 		the new windspeed during the storm
	 */
	public void setWindspeed(double windspeed) {
		this.windspeed = windspeed;
	}
	
	/**
	 * Mutator method to change the date of the storm
	 * 
	 * @param date
	 * 		the new date of the storm
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * Represents the storm as a String
	 * 
	 * @return
	 * 		returns the String representation of a storm
	 */
	public String toString() {
		return "Storm " + getName() 
		+ ": Date " + getDate() 
		+ ", " + getWindspeed() + " km/h winds, " 
		+ getPrecipitation() + " cm of rain.";
	}
	
	/**
	 * Prints the storm for a table format
	 */
	public void print() {
		System.out.printf("%S%20f%15f%15s\n", name, windspeed, precipitation, date);
	}
}
