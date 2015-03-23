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
	 * @param solution
	 * @return optimal solution for local search
	 */
	private static ArrayList<String[]> doLocalSearch(ArrayList<String[]> solution) {

 //TO-DO

		return null;
	}

	/**
	 * @return a starting solution 
	 */
	private static ArrayList<String[]> getStartingSolution() {


		ArrayList<String[]> solution=new ArrayList<String[]>();
		
		String[] recipe;

		for(int k=0; k<setOfRecipies.size(); k++){
			recipeLoop:
				if(currentRecipes[k]==false){ //if recipe not already in solution

					//check if this recipe is disjoint with solution
					recipe=setOfRecipies.get(k);
					for(int l=0; l<solution.size();l++){ //iterate through the subset(s) of solution
						for(int m=0; m<solution.get(l).length; m++){ //iterate through the content of each subset of solution
							for(int n=0; n<recipe.length; n++){ //iterate through the content of recipe being considered

								if(recipe[n]==solution.get(l)[m]) { //intersects with solution
									break recipeLoop; //go to next recipe 
								}
							}
						}
					}

					//add recipe to solution
					solution.add(recipe);
					currentRecipes[k]=true;
					totalWeight= totalWeight + weights.get(recipe);

				}//else go to next recipe 
		}

		return solution;
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
