package components;

public class FoodItem {
//each food
    private String name, serving;
    private float fats, protein, carbs;
    private int calories;

    public FoodItem(String name, float carbs, float fats, float protein, int calories, String serving) {
        this.name = name;
        this.carbs = carbs;
        this.fats = fats;
        this.protein = protein;
        this.calories = calories;
        this.serving = serving;
    }

    public String getName() {
        return name;
    }

    public float getCarbs() {
        return carbs;
    }

    public float getFats() {
        return fats;
    }

    public float getProtein() {
        return protein;
    }

    public int getCalories() {
        return calories;
    }

    public String getServing(){ return serving;}
}
