
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
public class DataSet {

	ArrayList<Recipe> recipes;


	/**
	 * creates the all data sets: weights, setOfIngredients, and setOfRecipies
	 */
	public ArrayList<Recipe> getData(){
		if(recipes != null){
			return recipes;
		}
		recipes = new ArrayList<Recipe>();

		Integer[] recipe1= {1,2,4};
		Integer[] recipe2= {1,7,8,2,5};
		Integer[] recipe3= {2,3,4,5};
		Integer[] recipe4= {1,3,5,7};
		Integer[] recipe5= {8,7,6,5,1};
		Integer[] recipe6= {1,3,5,7};
		Integer[] recipe7= {8};
		
		recipes.add(new Recipe(recipe1, 4));		
		recipes.add(new Recipe(recipe2, 8));		
		recipes.add(new Recipe(recipe3, 2));		
		recipes.add(new Recipe(recipe4, 5));		
		recipes.add(new Recipe(recipe5, 7));		
		recipes.add(new Recipe(recipe6, 3));		
		recipes.add(new Recipe(recipe7, 5));
		
		return recipes;
	}
}
