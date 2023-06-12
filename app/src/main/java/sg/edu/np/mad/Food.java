package sg.edu.np.mad;

public class Food {
    private String foodName;
    private String location;
    private double price;
    private int calories;

    public Food() {
    }

    public Food(String foodName, String location, double price, int calories) {
        this.foodName = foodName;
        this.location = location;
        this.price = price;
        this.calories = calories;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
