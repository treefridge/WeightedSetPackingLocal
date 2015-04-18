import java.util.ArrayList;
import java.util.Arrays;
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
	private static boolean[] currentRecipes; //used to store which recipes are in current solution
	private static int totalWeight=0;
	private final static int t=2; //number of subsets that get swapped out during doLocalSearch()

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DataSets data= new DataSets();

		data.makeData(); //creates all the data sets

		weights=data.getWeights();
		setOfIngredients=data.getSetOfIngredients();
		setOfRecipies=data.getSetOfRecipies();

		currentRecipes= new boolean[setOfRecipies.size()]; 
		Arrays.fill(currentRecipes, false); //initialize all values to false

		ArrayList<String[]> startingSolution=getStartingSolution();

		System.out.println("Total weight = "+totalWeight);

	}


	/**
	 * @return a starting solution 
	 */
	private static ArrayList<String[]> getStartingSolution() {

		ArrayList<String[]> solution=new ArrayList<String[]>();

		String[] recipe;

		for(int k=0; k<setOfRecipies.size(); k++){
		
				if(currentRecipes[k]==false){ //if recipe not already in solution

					recipe=setOfRecipies.get(k);

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
	private static boolean isDisjoint(String[] recipe,
			ArrayList<String[]> solution) {

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
	private static ArrayList<String[]> doLocalSearch(ArrayList<String[]> solution) {

		ArrayList<String[]> swappingOut=new ArrayList<String[]>();
		ArrayList<String[]> swappingIn=new ArrayList<String[]>();

		//choose subsets that will be potentially swapped out
		for(int i=0; i<t;i++){
			//randomly choose a subset in the solution to swap out
			swappingOut.add(solution.get((int) Math.round(Math.random()*solution.size())));
		}

		//store the "swapped-out"'s total weight 
		int previousTotalWeight=0;
		for(int j=0; j<t;j++){
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
