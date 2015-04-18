
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
	public void makeData(){

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


		String[] recipe1= {a,b,d};
		String[] recipe2= {a,g,h,b,e};
		String[] recipe3= {b,c,d,e};
		String[] recipe4= {a,c,e,g};
		String[] recipe5= {h,g,f,e,a};
		String[] recipe6= {a,c,e,g};
		String[] recipe7= {h};

		setOfRecipies.add(recipe1);
		setOfRecipies.add(recipe2);
		setOfRecipies.add(recipe3);
		setOfRecipies.add(recipe4);
		setOfRecipies.add(recipe5);
		setOfRecipies.add(recipe6);
		setOfRecipies.add(recipe7);

		weights.put(recipe1, 4);
		weights.put(recipe2, 8);
		weights.put(recipe3, 2);
		weights.put(recipe4, 5);
		weights.put(recipe5, 7);
		weights.put(recipe6, 3);
		weights.put(recipe7, 5);
	}

	/**
	 * @return Hashtable<String[], Integer> weights
	 */
	public Hashtable<String[], Integer> getWeights() {
		return weights;
	}

	/**
	 * @return ArrayList<String> setOfIngredients
	 */
	public ArrayList<String> getSetOfIngredients() {
		return setOfIngredients;
	}

	/**
	 * @return ArrayList<String[]> setOfRecipies
	 */
	public ArrayList<String[]> getSetOfRecipes() {
		return setOfRecipies;
	}
}
