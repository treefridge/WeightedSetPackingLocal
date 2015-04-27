
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Generates and returns the data for local search, including the set of all recipes and the neighbor matrix for them
 * Creates and returns the data sets.<br>
 * Will be replaced with a parser to use with a large .txt file storing data sets.
 *
 */
public class DataSet {

	private ArrayList<Recipe> recipes;

	/**
	 * 0 = not intersecting<br>
	 * 1= intersecting<br>
	 * 2 = not checked yet 
	 */
	private int neighborMatrix[][]; 

	/**
	 * @return the adjacency matrix for all recipes. Initialized all to 2, not checked yet
	 */
	public int[][] getNeighborMatrix() {
		
		neighborMatrix = new int[recipes.size()][recipes.size()];
		
		
		// Fill each row with 2
		for (int[] row: neighborMatrix)
		    Arrays.fill(row, 2);


		return neighborMatrix;
	}

	/**
	 * creates the all data sets: weights, setOfIngredients, and setOfRecipies
	 */
	public ArrayList<Recipe> getData(){
		if(recipes != null){
			return recipes;
		}
		recipes = new ArrayList<Recipe>();
		
		//reset 
		Integer[] temp={1};
		Recipe temp1= new Recipe(temp, 0);
		temp1.reset();
		
		

/*		Integer[] recipe1= {1,2,3};			//conflicts with 3,7
		Integer[] recipe2= {4,5,6};			//conflicts with 4,5
		Integer[] recipe3= {7,8,9,1};		//conflicts with 1,7
		Integer[] recipe4= {10,11,12,6};	//conflicts with 2,5
		Integer[] recipe5= {13,14,15,6};	//conflicts with 2,4
		Integer[] recipe6= {16,17,18};		//conflicts with NOBODY
		Integer[] recipe7= {19,20,21,1};	//conflicts with 1,3

		recipes.add(new Recipe(recipe1, 1));	//necessarily included in the initial non-greedy solution		
		recipes.add(new Recipe(recipe2, 1));		
		recipes.add(new Recipe(recipe3, 1));		
		recipes.add(new Recipe(recipe4, 1));		
		recipes.add(new Recipe(recipe5, 1));		
		recipes.add(new Recipe(recipe6, 1));		
		recipes.add(new Recipe(recipe7, 125));	//necessarily not included in the initial non-greedy solution, but should be in sol iff works out... :/

		*/
		
		int ingredientCount = 0;
		int recipeCount = 0;
		//read from input file
		try {
			BufferedReader br = new BufferedReader(new FileReader("recipeset.txt"));

	        String line = br.readLine();
	        //first line
	        if(line!=null){
	        	String[] parts = line.split(" ");
	        	ingredientCount = Integer.parseInt(parts[2]);
	        	recipeCount = Integer.parseInt(parts[3]);
	        	line = br.readLine();
	        }

	        //remaining lines
	        while (line != null) {
	        	String[] parts = line.split(" ");
	        	
	        	int weight = Integer.parseInt(parts[1]);
	        	int size = parts.length - 2;
	        	
	        	Integer [] recipe = new Integer[size];
	        	for(int i = 0; i<size; i++){
	        		recipe[i] = new Integer(parts[i+2]);
	        	}
	        	recipes.add(new Recipe(recipe, weight));
	            line = br.readLine();
	        }

	        br.close();
	    } catch(Exception e) {
	    	System.err.println(e.getMessage());
	    }
		
		//testing purposes
		//System.out.println("IngredientCount= " + ingredientCount + " RecipeCount= " + recipeCount + " ActualRecipeCount= " + recipes.size());
		

		return recipes;
	}

}
