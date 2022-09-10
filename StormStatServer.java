/**
 * @author Shiny Melhiraj, ID:114535120, shiny.melhiraj@stonybrook.edu 
 * 4/15/22
 * CSE 214-01 (R02)
 * Homework 6, StormStatServer Class
 * 
 * This class represents a database of storms that the user can manipulate
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
public class StormStatServer {
	private static HashMap<String, Storm> database;
	static Scanner sc = new Scanner(System.in);
	
	/**
	 * The main method with which the user manipulates the storm database
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		System.out.println("Welcome to the StormStatServer, we may not be able to make it rain, but we sure can tell you "
				+ "when it happened!\n");
		
		try {
			FileInputStream file = new FileInputStream("hurricane.ser");
			ObjectInputStream inStream = new ObjectInputStream(file);
			database = (HashMap<String, Storm>)inStream.readObject();
			inStream.close();
			System.out.println("hurricane.ser was found and loaded.\n");
		} catch (IOException | ClassNotFoundException e) {
			//e.printStackTrace();
			database = new HashMap<>();
			System.out.println("No previous data found.\n");
		}
		
		String option = "";
		
		while(!option.equalsIgnoreCase("Q") && !option.equalsIgnoreCase("X")) {
			printMenu();
			System.out.print("\nPlease choose an option: ");
			option = sc.nextLine();
			
			if(option.equalsIgnoreCase("A"))
				add();
			else if(option.equalsIgnoreCase("L"))
				look();
			else if(option.equalsIgnoreCase("D"))
				delete();
			else if(option.equalsIgnoreCase("E"))
				edit();
			else if(option.equalsIgnoreCase("R"))
				rainPrint();
			else if(option.equalsIgnoreCase("W"))
				windPrint();
		}
		
		if(option.equalsIgnoreCase("X")) {
			try {
				FileOutputStream file = new FileOutputStream("hurricane.ser");
				ObjectOutputStream outStream = new ObjectOutputStream(file);
				outStream.writeObject(database);
				outStream.close();
				System.out.println("File saved to hurricane.ser; feel free to use the weather channel in the meantime.");
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				FileOutputStream file = new FileOutputStream("hurricane.ser");
				ObjectOutputStream outStream = new ObjectOutputStream(file);
				database = new HashMap<>();
				outStream.writeObject(database);
				outStream.close();
				System.out.println("Goodbye, it's hard to hold an (electric) candle in the cold November (and April!) rain!.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Prints the storm database with the storms ordered by windspeed
	 */
	public static void windPrint() {
		System.out.printf("%s%20s%15s%15s\n", "Name", "Windspeed", "Rainfall", "Date");
		System.out.println("-----------------------------------------------------------");
		Collection<Storm> values = database.values();
		ArrayList<Storm> stormValues = new ArrayList<Storm>(values);
		Collections.sort(stormValues, new WindSpeedComparator());
		for(int i = 0; i < stormValues.size(); i++)
			stormValues.get(i).print();
	}
	
	/**
	 * Prints the storm database with the storms ordered by rainfall
	 */
	public static void rainPrint() {
		System.out.printf("%s%20s%15s%15s\n", "Name", "Windspeed", "Rainfall", "Date");
		System.out.println("-----------------------------------------------------------");
		Collection<Storm> values = database.values();
		ArrayList<Storm> stormValues = new ArrayList<Storm>(values);
		Collections.sort(stormValues, new PrecipitationComparator());
		for(int i = 0; i < stormValues.size(); i++) 
			stormValues.get(i).print();

	}
	
	/**
	 * Allows the user to edit a storm from the database
	 */
	public static void edit() {
		System.out.print("Please enter name: ");
		String name = sc.nextLine();
		name = name.toUpperCase();
		if(!database.containsKey(name)) {
			System.out.println("Storm " + name + " does not exist.\n");
			return;
		}
		System.out.println("In Edit Mode, press enter without any input to leave data unchanged.");
		System.out.print("Please enter date: ");
		String date = sc.nextLine();
		if(!validDate(date)) {
			System.out.println("Invalid date format\n");
			return;
		}
		System.out.print("Please enter precipitation (cm): ");
		String precipitation = sc.nextLine();
		if(Double.parseDouble(precipitation) < 0) {
			System.out.println("Invalid precipitation\n");
			return;
		}
		System.out.print("Please enter windspeed (km/h): ");
		String windspeed = sc.nextLine();
		if(Double.parseDouble(windspeed) < 0) {
			System.out.println("Invalid windspeed\n");
			return;
		}
		if(date != "")
			database.get(name).setDate(date);
		if(precipitation != "")
			database.get(name).setPrecipitation(Double.parseDouble(precipitation));
		if(windspeed != "")
			database.get(name).setWindspeed(Double.parseDouble(windspeed));
		System.out.println(name + " added.\n");
	}
	
	/**
	 * Deletes a storm from the database
	 */
	public static void delete() {
		System.out.print("Please enter name: ");
		String name = sc.nextLine();
		name = name.toUpperCase();
		if(database.containsKey(name)) {
			database.remove(name);
			System.out.println("Storm " + name + " has been deleted.\n");
		}
		else
			System.out.println("Storm " + name + " does not exist.\n");
	}
	
	/**
	 * Allows the user to view the details of one specific storm from the database
	 */
	public static void look() {
		System.out.print("Please enter name: ");
		String name = sc.nextLine();
		name = name.toUpperCase();
		if(database.containsKey(name))
			System.out.println("\n" + database.get(name) + "\n");
		else
			System.out.println("Key not found.\n");
	}
	
	/**
	 * Determines whether the date is in YYYY-MM-DD format
	 * 
	 * @param date
	 * 		the date of the storm
	 * @return
	 * 		returns true if the date is in proper format; false if not
	 */
	public static boolean validDate(String date) {
		char c = ' ';
		for(int i = 0; i < date.length(); i++) {
			c = date.charAt(i);
			if(i < 4 && (c < '0' || c > '9'))
				return false;
			if((c < '0' || c > '9') && c != '-')
				return false;
		}
		return true;
	}
	
	/**
	 * Adds a storm to the database
	 */
	public static void add() {
		System.out.print("Please enter name: ");
		String name = sc.nextLine();
		name = name.toUpperCase();
		System.out.print("Please enter date: ");
		String date = sc.nextLine();
		if(!validDate(date)) {
			System.out.println("Invalid date format\n");
			return;
		}
		System.out.print("Please enter precipitation (cm): ");
		double precipitation = sc.nextDouble();
		if(precipitation < 0) {
			System.out.println("Invalid precipitation\n");
			return;
		}
		System.out.print("Please enter windspeed (km/h): ");
		double windspeed = sc.nextDouble();
		if(windspeed < 0) {
			System.out.println("Invalid windspeed\n");
			return;
		}
		Storm s = new Storm(name, date, precipitation, windspeed);
		database.put(name, s);
		System.out.println(s.getName() + " added.\n");
	}
	
	/**
	 * Prints the menu
	 */
	public static void printMenu() {
		System.out.println("Menu:"
				+ "\t\nA) Add A Storm"
				+ "\t\nL) Look Up A Storm"
				+ "\t\nD) Delete A Storm"
				+ "\t\nE) Edit Storm Data"
				+ "\t\nR) Print Storms Sorted By Rainfall"
				+ "\t\nW) Print Storms by Windspeed"
				+ "\t\nX) Save and quit"
				+ "\t\nQ) Quit and delete saved data");
	}
}
