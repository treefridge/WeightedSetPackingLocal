
import java.util.ArrayList;
import java.util.Hashtable;


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

	private static Hashtable<String[], Integer> weights = new Hashtable<String[], Integer>();
	private static ArrayList<String> setOfIngredients = new ArrayList<String>();
	private static ArrayList<String[]> setOfRecipies= new ArrayList<String[]>();


	/**
	 * creates the all data sets: weights, setOfIngredients, and setOfRecipies
	 */
	public static void makeData(){

		setOfIngredients.add("Flour"); 				//a
		setOfIngredients.add("Milk");				//b
		setOfIngredients.add("Chilli Peppers");		//c
		setOfIngredients.add("Chocolate Chips");	//d
		setOfIngredients.add("Vegetable Stock");	//e
		setOfIngredients.add("Butter");				//f
		setOfIngredients.add("Corn Starch");		//g
		setOfIngredients.add("Apples");				//h

		//used to make recipe code cleaner
		String a=setOfIngredients.get(0);
		String b=setOfIngredients.get(1);
		String c=setOfIngredients.get(2);
		String d=setOfIngredients.get(3);
		String e=setOfIngredients.get(4);
		String f=setOfIngredients.get(5);
		String g=setOfIngredients.get(6);
		String h=setOfIngredients.get(7);


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
