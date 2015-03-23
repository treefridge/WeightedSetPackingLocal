import java.util.ArrayList;
import java.util.Hashtable;

/**
 * 
 */

/**
 *
 */
public class LocalSearch {
	
	private static Hashtable<String[], Integer> weights = new Hashtable<String[], Integer>();
	private static ArrayList<String> setOfIngredients= new ArrayList<String>();
	private static ArrayList<String[]> setOfRecipies= new ArrayList<String[]>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DataSets data= new DataSets();
		
		data.makeData(); //creates all the data sets
		
		weights=data.getWeights();
		setOfIngredients=data.getSetOfIngredients();
		setOfRecipies=data.getSetOfRecipies();
		

	}

}
