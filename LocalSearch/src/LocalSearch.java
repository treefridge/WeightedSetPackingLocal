import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

/**
 * 
 */
public class LocalSearch {

	private ArrayList<Recipe> recipes = new ArrayList<Recipe>();
	private boolean[] currentSolution; //used to store which recipes are in current solution
	private int totalWeight = 0;
	private static final int NUM_SUBSET_REMOVED = 2; //number of subsets that get swapped out during doLocalSearch()
	/**
	 * The number of subsets to add back in is defined as the number x of subsets to add back in s.t.
	 * x <= NUM_SUBSET_REMOVED + SUBSET_ADD_WINDOW
	 * x >= NUM_SUBSET_REMOVED - SUBSET_ADD_WINDOW
	 */
	private static final int SUBSET_ADD_WINDOW = 1;
	
	private static final int NUM_SWAP_TRIES = 30; // Number of subsets to evaluate for adding back in


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LocalSearch in = new LocalSearch();
		ArrayList<Recipe> startingSolution = in.getStartingSolution();


		System.out.println("Total weight = "+in.totalWeight);
	}

	public LocalSearch(){
		DataSet data= new DataSet();

		recipes = data.getData(); //creates all the data sets

		currentSolution = new boolean[recipes.size()]; 
		Arrays.fill(currentSolution, false); //initialize all values to false
	}

	/**
	 * @return a starting solution 
	 */
	private ArrayList<Recipe> getStartingSolution() {
		ArrayList<Recipe> solution = new ArrayList<Recipe>();
		Recipe recipe;

		for(int k=0; k < recipes.size(); k++){
		
				if(currentSolution[k]==false){ //if recipe not already in solution

					recipe = recipes.get(k);

					if(isDisjoint(recipe, solution)){

						//add recipe to solution
						solution.add(recipe);
						currentSolution[k]=true;
						totalWeight += recipe.getWeight();
					}
				}// go to next recipe 
		}

		return solution;
	}

	/**
	 * Checks whether a recipe is disjoint with the current solution
	 * @param recipe
	 * @param solution
	 * @return true/false
	 */
	private boolean isDisjoint(Recipe recipe,
			ArrayList<Recipe> solution) {

		//check if this recipe is disjoint with solution
		for(int l=0; l < solution.size();l++){ //iterate through the subset(s) of solution
			for(int m=0; m < solution.get(l).getIngredients().length; m++){ //iterate through the content of each subset of solution
				for(int n=0; n < recipe.getIngredients().length; n++){ //iterate through the content of recipe being considered
					if(recipe.getIngredients()[n] == solution.get(l).getIngredients()[m]) { //intersects with solution
						return false;
					}
				}
			}
		}
		return true;
	}


	/**
	 * Swaps out t subsets with some collection of greater total weight that does not intersect with remainder of solution<br>
	 * The potential "swapped-out" subsets are chosen randomly 
	 * @param solution
	 * @return optimal solution for local search
	 */
	private ArrayList<Recipe> doLocalSearch(ArrayList<Recipe> solution) {

		ArrayList<Recipe> swappingOut=new ArrayList<Recipe>();
		ArrayList<Recipe> swappingIn=new ArrayList<Recipe>();

		//choose subsets that will be potentially swapped out
		for(int i=0; i < NUM_SUBSET_REMOVED; i++){
			//randomly choose a subset in the solution to swap out
			swappingOut.add(solution.get((int) Math.round(Math.random() * solution.size())));
		}

		//store the "swapped-out"'s total weight 
		int previousTotalWeight = 0;
		for(int j=0; j < NUM_SUBSET_REMOVED; j++){
			previousTotalWeight+= swappingOut.get(j).getWeight();
		}

		//find a set of subsets that are disjoint with remainder of solution and have higher total weight


		return null;
	}






	//	/**
	//	 * Takes current solution and adds on neighbor that is pairwise disjoint with largest weight 
	//	 * @param  
	//	 * @return current solution + best neighbor 
	//	 */
	//	private static ArrayList<String[]> addNeighbor(ArrayList<String[]> solution) {
	//
	//
	//
	//		//find best feasible neighbor
	//
	//		int bestCurrentWeight=0; //store best feasible weight so far
	//		String[] bestCurrentNeighbor; //store corresponding best neighbor so far
	//		int z; //index position in setOfRecipies of best neighbor so far
	//		String[] recipe;
	//
	//		for(int k=0; k<setOfRecipies.size(); k++){
	//			recipeLoop:
	//				if(currentRecipes[k]==false){ //if recipe not already in solution
	//
	//					//check if this recipe is disjoint with solution
	//					recipe=setOfRecipies.get(k);
	//
	//					for(int l=0; l<solution.size();l++){ //iterate through the subset(s) of solution
	//						for(int m=0; m<solution.get(l).length; m++){ //iterate through the content of each subset of solution
	//							for(int n=0; n<recipe.length; n++){ //iterate through the content of recipe being considered
	//
	//								if(recipe[n]==solution.get(l)[m]) { //intersects with solution
	//									break recipeLoop;
	//								}
	//							}
	//						}
	//					}
	//
	//					//compare to current best weight
	//					if(!weights.containsKey(recipe)) { //FOR TESTING ONLY
	//						System.out.println("ERROR: OBJECT POINTER ERROR!!!");
	//						return solution;
	//					}
	//
	//					else if(weights.get(recipe)>bestCurrentWeight){ 
	//						z=k;
	//						bestCurrentWeight=weights.get(recipe);
	//						bestCurrentNeighbor=setOfRecipies.get(k);
	//
	//					}
	//
	//				}//else go to next recipe 
	//		}
	//
	//
	//
	//		return solution;
	//	}


}
