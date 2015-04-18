
public class Recipe {
	private Integer[] ingredients;
	private int weight;
	
	public Recipe(Integer[] ingredients, int weight){
		this.ingredients = ingredients;
		this.weight = weight;
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
}
