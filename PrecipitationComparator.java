/**
 * @author Shiny Melhiraj, ID:114535120, shiny.melhiraj@stonybrook.edu 
 * 4/15/22
 * CSE 214-01 (R02)
 * Homework 6, PrecipitationComparator Class
 * 
 * This class compares the precipitation of the storms
 */
import java.util.Comparator;
public class PrecipitationComparator implements Comparator<Storm>{
	/**
	 * Compares two storms based on precipitation
	 * 
	 * @return	
	 * 		returns 1 if the first storm has a higher precipitation
	 * 		0 if both storms have the same precipitation
	 * 		-1 if the second storm has the higher precipitation
	 */
	public int compare(Storm o1, Storm o2) {
		Storm left = o1;
		Storm right = o2;
		if(left.getPrecipitation() < right.getPrecipitation())
			return -1;
		else if(left.getPrecipitation() > right.getPrecipitation())
			return 1;
		else
			return 0;
	}
}
