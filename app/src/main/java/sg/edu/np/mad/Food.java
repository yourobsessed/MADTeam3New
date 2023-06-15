package sg.edu.np.mad;

public class Food extends Store{
    private String foodName;
    private String location;
    private double price;
    private int calories;
    private int foodImage;
    private String description;


    public Food() {
    }

    public Food(String foodName, String location, double price, int calories, String description, int foodImage) {
        this.foodName = foodName;
        this.location = getFoodCourtName();
        this.price = price;
        this.calories = calories;
        this.description = description;
        this.foodImage = foodImage;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(int foodImage) {
        this.foodImage = foodImage;
    }

    public String toString() {
        return String.format("%s,%s","%s",this.foodName,this.location, this.description);
    }
}
