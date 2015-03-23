import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * 
 */

/**
 * Just for testing<br>
 * Creates and returns the data sets.<br>
 * Will be replaced with a parser to use with a large .txt file storing data sets.
 *
 */
public class DataSets {

	public static Hashtable<String[], Integer> weights = new Hashtable<String[], Integer>();
	public static ArrayList<String> setOfIngredients= new ArrayList<String>();
	public static ArrayList<String[]> setOfRecipies= new ArrayList<String[]>();
	
	private final int MAX_WEIGHT=100;
	private final int MIN_WEIGHT=1;
	

	static String a="Flour";
	static String b="Milk";
	static String c="Chilli Peppers";
	static String d="Chocolate Chips";
	static String e="Vegetable Stock";
	static String f="Butter";
	static String g="Corn Starch";
	static String h="Apples";
	
	
	
//	public static void  main(String args[]){
//		makeData();
//		
//		System.out.println(setOfIngredients.toString());
//	
//	}
	
	
	/**
	 * creates the all data sets: weights, setOfIngredients, and setOfRecipies
	 */
	public static void makeData(){
		
		setOfIngredients.add(a);
		setOfIngredients.add(b);
		setOfIngredients.add(c);
		setOfIngredients.add(d);
		setOfIngredients.add(e);
		setOfIngredients.add(f);
		setOfIngredients.add(g);
		setOfIngredients.add(h);
		
		String[] recipie1= {a,b,d};
		String[] recipie2= {a,g,h,b,e};
		String[] recipie3= {b,c,d,e};
		String[] recipie4= {a,c,e,g};
		String[] recipie5= {h,g,f,e,a};
		String[] recipie6= {a,c,e,g};
		String[] recipie7= {h};
		
		setOfRecipies.add(recipie1);
		setOfRecipies.add(recipie2);
		setOfRecipies.add(recipie3);
		setOfRecipies.add(recipie4);
		setOfRecipies.add(recipie5);
		setOfRecipies.add(recipie6);
		setOfRecipies.add(recipie7);
		
		weights.put(recipie1, 4);
		weights.put(recipie2, 8);
		weights.put(recipie3, 2);
		weights.put(recipie4, 5);
		weights.put(recipie5, 7);
		weights.put(recipie6, 3);
		weights.put(recipie7, 5);
			
	}
	
	/**
	 * @return Hashtable<String[], Integer> weights
	 */
	public static Hashtable<String[], Integer> getWeights() {
		return weights;
	}



	/**
	 * @return ArrayList<String> setOfIngredients
	 */
	public static ArrayList<String> getSetOfIngredients() {
		return setOfIngredients;
	}




	/**
	 * @return ArrayList<String[]> setOfRecipies
	 */
	public static ArrayList<String[]> getSetOfRecipies() {
		return setOfRecipies;
	}


	


}
