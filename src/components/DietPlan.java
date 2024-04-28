package components;

public class DietPlan {
    private DietPlan(){}//to prevent object creation.
    public static int[] getWeightGain(){
        return new int[]{3000, 345, 138, 103};
    }
    public static int[] getWeightLoss(){
        return new int[]{1200,152,60,45};
    }
    public static int[] getMaintainWeight(){
        return new int[]{1700,210,84,63};
    }
}
