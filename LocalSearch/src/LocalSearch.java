import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

/**
 * 
 */
public class LocalSearch {

	private Hashtable<Integer[], Integer> weights = new Hashtable<Integer[], Integer>();
	private ArrayList<Integer> setOfIngredients= new ArrayList<Integer>();
	private ArrayList<Integer[]> setOfRecipes= new ArrayList<Integer[]>();
	private boolean[] currentRecipes; //used to store which recipes are in current solution
	private int totalWeight=0;
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
		ArrayList<Integer[]> startingSolution = in.getStartingSolution();


		System.out.println("Total weight = "+in.totalWeight);
	}

	public LocalSearch(){
		DataSets data= new DataSets();

		data.makeData(); //creates all the data sets

		weights=data.getWeights();
		setOfIngredients=data.getSetOfIngredients();
		setOfRecipes=data.getSetOfRecipes();

		currentRecipes= new boolean[setOfRecipes.size()]; 
		Arrays.fill(currentRecipes, false); //initialize all values to false
	}

	/**
	 * @return a starting solution 
	 */
	private ArrayList<Integer[]> getStartingSolution() {
		ArrayList<Integer[]> solution=new ArrayList<Integer[]>();
		Integer[] recipe;

		for(int k=0; k<setOfRecipes.size(); k++){
		
				if(currentRecipes[k]==false){ //if recipe not already in solution

					recipe=setOfRecipes.get(k);

					if(isDisjoint(recipe, solution)){

						//add recipe to solution
						solution.add(recipe);
						currentRecipes[k]=true;
						totalWeight+=weights.get(recipe);
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
	private boolean isDisjoint(Integer[] recipe,
			ArrayList<Integer[]> solution) {

		//check if this recipe is disjoint with solution
		for(int l=0; l<solution.size();l++){ //iterate through the subset(s) of solution
			for(int m=0; m<solution.get(l).length; m++){ //iterate through the content of each subset of solution
				for(int n=0; n<recipe.length; n++){ //iterate through the content of recipe being considered
					if(recipe[n]==solution.get(l)[m]) { //intersects with solution
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
	private ArrayList<Integer[]> doLocalSearch(ArrayList<Integer[]> solution) {

		ArrayList<Integer[]> swappingOut=new ArrayList<Integer[]>();
		ArrayList<Integer[]> swappingIn=new ArrayList<Integer[]>();
		

		//choose subsets that will be potentially swapped out
		for(int i=0; i<NUM_SUBSET_REMOVED;i++){
			//randomly choose a subset in the solution to swap out
			swappingOut.add(solution.get((int) Math.round(Math.random()*solution.size())));
		}

		//store the "swapped-out"'s total weight 
		int previousTotalWeight=0;
		for(int j=0; j<NUM_SUBSET_REMOVED;j++){
			previousTotalWeight+= weights.get(swappingOut.get(j));
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
