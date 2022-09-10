/**
 * @author Shiny Melhiraj, ID:114535120, shiny.melhiraj@stonybrook.edu 
 * 4/15/22
 * CSE 214-01 (R02)
 * Homework 6, WindSpeedComparator Class
 * 
 * This class compares the windspeeds of the storms
 */
import java.util.Comparator;
public class WindSpeedComparator implements Comparator<Storm>{
	/**
	 * Compares two storms based on windspeed
	 * 
	 * @return	
	 * 		returns 1 if the first storm has a higher windspeed
	 * 		0 if both storms have the same windspeed
	 * 		-1 if the second storm has the higher windspeed
	 */
	public int compare(Storm o1, Storm o2) {
		Storm left = o1;
		Storm right = o2;
		if(left.getWindspeed() < right.getWindspeed())
			return -1;
		else if(left.getWindspeed() > right.getWindspeed())
			return 1;
		else
			return 0;
	}

}
