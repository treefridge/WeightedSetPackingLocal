
public class Recipe {
	private static int serialNum = 0;
	private Integer[] ingredients;
	private int weight;
	private int recipeNumber;
	

	public Recipe(Integer[] ingredients, int weight){
		this.ingredients = ingredients;
		this.weight = weight;
		this.recipeNumber = serialNum++;
	}
	
	public Integer[] getIngredients() {
		return ingredients;
	}
	public void setIngredients(Integer[] ingredients) {
		this.ingredients = ingredients;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String toString(){
		return " R# = " + recipeNumber;
	}
	public int getRecipeNumber() {
		return recipeNumber;
	}

	public void setRecipeNumber(int number) {
		this.recipeNumber = number;
	}
	
	public void reset(){
		serialNum = 0;
	}
}
