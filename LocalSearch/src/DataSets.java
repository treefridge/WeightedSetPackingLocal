
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

	private static Hashtable<Integer[], Integer> weights = new Hashtable<Integer[], Integer>();
	private static ArrayList<Integer> setOfIngredients = new ArrayList<Integer>();
	private static ArrayList<Integer[]> setOfRecipies= new ArrayList<Integer[]>();


	/**
	 * creates the all data sets: weights, setOfIngredients, and setOfRecipies
	 */
	public void makeData(){

		setOfIngredients.add(1); 				//a
		setOfIngredients.add(2);				//b
		setOfIngredients.add(3);				//c
		setOfIngredients.add(4);				//d
		setOfIngredients.add(5);				//e
		setOfIngredients.add(6);				//f
		setOfIngredients.add(7);				//g
		setOfIngredients.add(8);				//h

		//used to make recipe code cleaner
		Integer a=setOfIngredients.get(0);
		Integer b=setOfIngredients.get(1);
		Integer c=setOfIngredients.get(2);
		Integer d=setOfIngredients.get(3);
		int e=setOfIngredients.get(4);
		int f=setOfIngredients.get(5);
		int g=setOfIngredients.get(6);
		int h=setOfIngredients.get(7);


		Integer[] recipe1= {a,b,d};
		Integer[] recipe2= {a,g,h,b,e};
		Integer[] recipe3= {b,c,d,e};
		Integer[] recipe4= {a,c,e,g};
		Integer[] recipe5= {h,g,f,e,a};
		Integer[] recipe6= {a,c,e,g};
		Integer[] recipe7= {h};

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
	public Hashtable<Integer[], Integer> getWeights() {
		return weights;
	}

	/**
	 * @return ArrayList<String> setOfIngredients
	 */
	public ArrayList<Integer> getSetOfIngredients() {
		return setOfIngredients;
	}

	/**
	 * @return ArrayList<String[]> setOfRecipies
	 */
	public ArrayList<Integer[]> getSetOfRecipes() {
		return setOfRecipies;
	}
}
