import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;


public class LocalSearch {

	private ArrayList<Recipe> recipes = new ArrayList<Recipe>();
	private ArrayList<Recipe> currentSolution = new ArrayList<Recipe>();
	private ArrayList<Recipe> otherCandidates = new ArrayList<Recipe>();
	
	/**
	 * 0 = not intersecting<br>
	 * 1= intersecting<br>
	 * 2 = not checked yet 
	 */
	private int[][] neighborMatrix;

	private static final int MAX_NUM_SUBSET_REMOVED = 2; //number of subsets that get swapped out during doLocalSearch()//TODO 1 is problem
	/**
	 * The rate at which the number of recipes for removal from the solution is decreased relative to the total # of solution recipes.
	 * 2 means that the number of recipes for removal is set to currentSolution size / 2 if MAX_NUM_SUBSET_REMOVED is larger than currentSolution size
	 */
	private static final int DECREASE_RATIO = 2;

	//TODO make use of this one if iterating over all possible combinations ends up being a bottleneck:
	private static final int NUM_SWAP_TRIES = 30; // Number of subsets to evaluate for adding back in


	public static void main(String[] args) {
		
		LocalSearch in = new LocalSearch();
		in.initializeStartingSolution();
		ArrayList<Recipe> sol = in.doLocalSearch();

		System.out.println("Final solution: "+sol.toString());
		System.out.println("Total weight = "+in.getTotalWeight(in.currentSolution));
	}

	public LocalSearch(){
		DataSet data= new DataSet();
		recipes = data.getData(); //creates all the data sets
		neighborMatrix = data.getNeighborMatrix(); //initialized all to 2's (not yet checked)
	}

	/**
	 * @return a non-greedy starting solution of disjoint recipes
	 */
	private void initializeStartingSolution() {
		for(int k=0; k < recipes.size(); k++){
			Recipe recipe = recipes.get(k);

			if(isDisjoint(recipe, currentSolution)){
				//add recipe to solution
				currentSolution.add(recipe);
			} else {
				//add recipe to recipes - solution
				otherCandidates.add(recipe); //TODO ALTER FOR GREEDY APPROACH CONVERSION
			}
		}
	}

	/**
	 * Checks whether a recipe is disjoint with the current solution, an ArrayList of recipes
	 * @param recipe
	 * @param solution
	 * @return true/false
	 */
	private boolean isDisjoint(final Recipe recipe,
			final ArrayList<Recipe> solution) {
		if(solution.isEmpty())
			return true;

		//check if this recipe is disjoint with solution
		for(int l=0; l < solution.size();l++){ //iterate through the subset(s) of solution
			if(! areDisjointRecipes(recipe, solution.get(l))){
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks whether a recipe is disjoint with another recipe
	 * @param recipe1
	 * @param recipe2
	 * @return
	 */
	private boolean areDisjointRecipes(final Recipe recipe1, final Recipe recipe2){

		int intersection = neighborMatrix[recipe1.getRecipeNumber()][recipe2.getRecipeNumber()];

		switch(intersection){

		case 0: return true;

		case 1: return false;

		case 2: checkForDisjoint(recipe1, recipe2);
		}

		return areDisjointRecipes(recipe1, recipe2); //used iff case 2

	}




	/**
	 * Used when value for adjacency matrix not yet computed<br>
	 * Manually checks if 2 recipes are disjoint and then updates adjacency matrix
	 * @param recipe1
	 * @param recipe2
	 */
	private void checkForDisjoint(Recipe recipe01, Recipe recipe02) {

		Integer[] recipe1= recipe01.getIngredients();
		Integer[] recipe2 = recipe02.getIngredients();

		for(int i=0; i<recipe1.length; i++){
			for (int j=0; j<recipe2.length; j++){
				if(recipe1[i].equals(recipe2[j])){
					//update adjacency matrix
					neighborMatrix[recipe01.getRecipeNumber()][recipe02.getRecipeNumber()] = 1;
					neighborMatrix[recipe02.getRecipeNumber()][recipe01.getRecipeNumber()] = 1;
					return;
				}
			}
		}

		//update adjacency matrix
		neighborMatrix[recipe01.getRecipeNumber()][recipe02.getRecipeNumber()] = 0;
		neighborMatrix[recipe02.getRecipeNumber()][recipe01.getRecipeNumber()] = 0;
		return;


	}

	/**
	 * Repeatedly swaps out subsets of size <= MAX_NUM_SUBSET_REMOVED with some collection of greater total weight that does not intersect with remainder of solution<br>
	 * The potential "swapped-out" subsets are all of the combinations of subsets of appropriate size of the solution
	 * @return the solution that local search generated
	 */
	private ArrayList<Recipe> doLocalSearch() {
		if(currentSolution == null || currentSolution.size() == 0){
			throw new IllegalArgumentException("Provided solution was invalid.");
		}

		if(otherCandidates == null || otherCandidates.size() == 0){
			return currentSolution;
		}

		boolean keepGoing = true;

		while(keepGoing){
			//			System.out.println("Candidates = "+otherCandidates + " & CurrentSol = "+currentSolution.toString());
			final int numSubsetsToRemove = getNumSubsetsToRemove(currentSolution.size());

			// Generate all combinations of recipes belonging to the current solution of size numSubsetsToRemove
			final Generator<Recipe> combinations = getCombinations(currentSolution, numSubsetsToRemove);

			//Iterate over all possible combinations
			for(ICombinatoricsVector<Recipe> combination : combinations){
				//Used to store the state of the potential solution set & the rest of the (unpicked) recipes after the swap out occurred:
				ArrayList<Recipe> solutionAfterSwapOut = (ArrayList<Recipe>) currentSolution.clone();
				ArrayList<Recipe> otherCandidatesAfterSwapOut = (ArrayList<Recipe>) otherCandidates.clone();

				ArrayList<Recipe> combinationRecipes = convertCombinationToList(combination);
				ArrayList<Recipe> potentialSwapInRecipes = new ArrayList<Recipe>();

				//Get the index of the recipe to potentially remove from the solution & for which to find neighbors
				final int indexRecipeForExpansion = (int) Math.random() * combinationRecipes.size();

				final Recipe recipeForExpansion = combinationRecipes.remove(indexRecipeForExpansion);
				otherCandidatesAfterSwapOut.add(recipeForExpansion);
				solutionAfterSwapOut.remove(recipeForExpansion);

				//Add all other members of the combination, the ones that don't include recipeForExpansion:
				potentialSwapInRecipes.addAll(combinationRecipes);
				otherCandidatesAfterSwapOut.addAll(combinationRecipes);
				solutionAfterSwapOut.removeAll(combinationRecipes);

				//Find all neighbors for the recipeForExpansion & add them to potentialSwapIns
				final ArrayList<Recipe> neighbors = findAllNeighbors(recipeForExpansion);
				potentialSwapInRecipes.addAll(neighbors);

				//TEST PRINTOUTS:
				//				System.out.println("expansion recipe: "+recipeForExpansion);
				//				System.out.println("neighbors: "+neighbors);
				//				System.out.println("before conflict resolution: "+potentialSwapInRecipes);

				// Now remove all non-disjoint recipes between any and all member recipes of potentialSwapInRecipes
				potentialSwapInRecipes = resolveAllConflicts(potentialSwapInRecipes,solutionAfterSwapOut);

				//TEST PRINTOUTS:
				//				System.out.println("after conflict resolution: "+potentialSwapInRecipes);
				//				System.out.println("wAfter= "+getTotalWeight(potentialSwapInRecipes) + " wCurr= "+(getTotalWeight(combinationRecipes) + recipeForExpansion.getWeight()));

				// Keep this new solution iff the swap in set is larger in weight than the swap out set
				if(getTotalWeight(potentialSwapInRecipes) > getTotalWeight(combinationRecipes) + recipeForExpansion.getWeight()){
					//Now we must update the current solution to reflect those taken out & added in
					updateCurrentSolution(solutionAfterSwapOut, otherCandidatesAfterSwapOut, potentialSwapInRecipes);//TODO changes clarify
					break;
				} else if(reachedLastCombination(combinations, combination)){
					//We no longer have any combinations to try from the current solution, it's quitting time!
					keepGoing = false;
				}
			}
		}

		return currentSolution;
	}

	/**
	 * 
	 * Updates the values for currentSolution and otherCandidates from the new, better solution found
	 * @param solAfterSwapOuts the currentSolution minus the swapped out values
	 * @param otherCandidatesAfterSwapOuts the otherCandidates plus the swapped out values
	 * @param swapIns the values to be swapped in
	 */
	private void updateCurrentSolution(ArrayList<Recipe> solAfterSwapOuts, ArrayList<Recipe> otherCandidatesAfterSwapOuts, ArrayList<Recipe> swapIns) {//kk
		for(Recipe recipe : swapIns){
			solAfterSwapOuts.add(recipe);
			otherCandidatesAfterSwapOuts.remove(recipe);
		}
		currentSolution = solAfterSwapOuts;
		otherCandidates = otherCandidatesAfterSwapOuts;
	}

	/**
	 * Finds the combined weight of all recipes in the list
	 * @param recipes the list of recipes
	 * @return the total weight
	 */
	private int getTotalWeight(ArrayList<Recipe> recipes) {
		int weight = 0;

		for(Recipe recipe : recipes){
			weight += recipe.getWeight();
		}

		return weight;
	}

	/**
	 * Checks whether all combinations were visited or whether we still have more we can try
	 * NOTE TODO this may be labor intensive, if we have a bottleneck this may be it.
	 * @param combinations
	 * @param combination
	 * @return
	 */
	private boolean reachedLastCombination(Generator<Recipe> combinations, ICombinatoricsVector<Recipe> combination) {
		List<ICombinatoricsVector<Recipe>> allCombinations = combinations.generateAllObjects();
		ICombinatoricsVector<Recipe> lastCombination = allCombinations.get(allCombinations.size() - 1);

		return lastCombination.equals(combination)? true : false;
	}

	/**
	 * Ensures that potentialSwapIns contains no 1) recipes that clash within it nor 2) recipes that clash with recipes in the solution after the swap out was done
	 * @param potentialSwapIns recipes we are considering for the swap-in
	 * @param solutionAfterSwapOut the current solution minus the recipes we removed in the swap-out
	 * @return potentialSwapIns with all conflicts eliminated (keeping the one of greatest weight, when applicable)
	 */
	private ArrayList<Recipe> resolveAllConflicts(ArrayList<Recipe> potentialSwapIns, ArrayList<Recipe> solutionAfterSwapOut) {
		//		for(int i = potentialSwapIns.size() - 1; i > -1; --i){
		//			for(int j = potentialSwapIns.size() - 1; j > -1; --j){
		//				Recipe r1 = potentialSwapIns.get(i);
		//				Recipe r2 = potentialSwapIns.get(j);
		//				// Do not check a recipe against itself, nor a recipe that is already flagged as to-be-removed
		//				if(r1 == r2){
		//					continue;
		//				} else if(! areDisjointRecipes(r1, r2)){
		//					potentialSwapIns.remove(r1.getWeight() >= r2.getWeight()? r2 : r1);
		//				}
		//			}
		//		}

		// Keep track of all recipes to be removed, avoid making direct changes to potentialSwapIns since we are not using safe Iterator objects
		ArrayList<Recipe> toBeRemoved = new ArrayList<Recipe>();

		// For each pair of recipes, check if they are in conflict, aka one should be flagged for removal
		for(Recipe recipe1 : potentialSwapIns){
			for(Recipe recipe2 : potentialSwapIns){
				// Do not check a recipe against itself, nor a recipe that is already flagged as to-be-removed
				if(recipe1 == recipe2 || toBeRemoved.contains(recipe2)){
					continue;
				} else if(toBeRemoved.contains(recipe1)){
					break;
				} else if(! areDisjointRecipes(recipe1, recipe2)){
					toBeRemoved.add(recipe1.getWeight() >= recipe2.getWeight()? recipe2 : recipe1);
				}
			}
		}

		//Remove all flagged recipes
		for(Recipe toRemove : toBeRemoved){
			potentialSwapIns.remove(toRemove);
		}

		toBeRemoved.clear();

		// For each pair of recipes, between the potentialSwapIns and the currentSolution, check if they are in conflict
		for(Recipe recipe1 : potentialSwapIns){
			for(Recipe recipe2 : solutionAfterSwapOut){
				// Do not check a recipe against itself, nor a recipe that is already flagged as to-be-removed
				if(recipe1 == recipe2){
					throw new UnsupportedOperationException("recipe1 and recipe2 were the same..");//TODO remove
				} else if(toBeRemoved.contains(recipe2)){
					continue;
				} else if(toBeRemoved.contains(recipe1)){
					break;
				} else if(! areDisjointRecipes(recipe1, recipe2)){
					toBeRemoved.add(recipe1);
				}
			}
		}

		//Remove all flagged recipes
		for(Recipe toRemove : toBeRemoved){
			potentialSwapIns.remove(toRemove);
		}

		return potentialSwapIns;
	}

	/**
	 * Uses the neighbor matrix to find all neighbor (non-disjoint) recipes  to the provided recipe TODO using actual generated neighbor matrix
	 * Assumes the recipe number corresponds directly to the row/col number of the matrix for that recipe.
	 * @param recipeForExpansion
	 * @return list of recipes which are neighbors of the recipeForExpansion
	 */
	private ArrayList<Recipe> findAllNeighbors(Recipe recipeForExpansion) {
		ArrayList<Integer> neighborRecipeNumbers = new ArrayList<Integer>();
		ArrayList<Recipe> neighborRecipes = new ArrayList<Recipe>();

		int row = recipeForExpansion.getRecipeNumber();

		for(int col = 0; col < neighborMatrix.length; col++){
			if(neighborMatrix[row][col] == 1){
				neighborRecipeNumbers.add(col);
			}
		}

		for(Integer recipeNumber : neighborRecipeNumbers){
			for(Recipe recipe : otherCandidates){
				if(recipeNumber.equals(recipe.getRecipeNumber())){
					neighborRecipes.add(recipe);
				}
			}
		}

		return neighborRecipes;
	}

	/**
	 * Converts the ICombinatoricsVector of recipes to a list
	 * @param combination the vector
	 * @return the list version
	 */
	private ArrayList<Recipe> convertCombinationToList(final ICombinatoricsVector<Recipe> combination) {
		ArrayList<Recipe> list = new ArrayList<Recipe>();
		for(Recipe recipe : combination){
			list.add(recipe);
		}
		return list;
	}

	/**
	 * Gets all combinations in the provided list, of the provided size
	 * @param combinationSourceSet the set of recipes for combining
	 * @param subsetsToRemove the number of elements each combination should have
	 * @return the generator for the combinations
	 */
	private Generator<Recipe> getCombinations(ArrayList<Recipe> combinationSourceSet, int subsetsToRemove) {
		// Create the initial vector
		ICombinatoricsVector<Recipe> initialVector = Factory.createVector(
				combinationSourceSet );

		// Create a simple combination generator to generate 3-combinations of the initial vector
		Generator<Recipe> gen = Factory.createSimpleCombinationGenerator(initialVector, subsetsToRemove);


		// TEST Print all possible combinations
		//printAllPossibleCombinations(gen);

		return gen;
	}

	/**
	 * Finds the correct number of subsets to use for swapping out. This value should be >1 and either MAX_NUM_SUBSET_REMOVED or size/ratio repeatedly until it is < size
	 * @param size max size of the set based on the set of sets we are picking from
	 * @return the size of the combinations that should be used
	 */
	private int getNumSubsetsToRemove(int size) {
		int num = MAX_NUM_SUBSET_REMOVED;
		//If the number of subsets to be removed is greater than the size of the starting solution, we must decrease it
		while(num > size){
			num = size / DECREASE_RATIO;
		}

		if(num < 1){
			num = 1;
		}

		return num;
	}

	/**
	 * Prints all possible subset combinations
	 * @param gen the generator for combinations
	 */
	public void printAllPossibleCombinations(Generator<Recipe> gen){
		System.out.println("--TEST All possible combinations:");
		for (ICombinatoricsVector<Recipe> combination : gen) {
			System.out.println("\t" + combination);
		}
	}

}
