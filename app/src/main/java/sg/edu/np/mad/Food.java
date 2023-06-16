package sg.edu.np.mad;

public class Food extends Store{
    private String foodName;
    private String location;
    private double price;
    private int calories;
    private int foodImage;
    private String description;
    private Boolean noodle;
    private Boolean soup;
    private Boolean rice;


    public Food() {
    }

    public Food(String FoodName, String Location, double Price, int Calories, String Description, int FoodImage, Boolean Noodle, Boolean Soup, Boolean Rice) {
        this.foodName = FoodName;
        this.location = Location;
        this.price = Price;
        this.calories = Calories;
        this.description = Description;
        this.foodImage = FoodImage;
        this.noodle = Noodle;
        this.soup = Soup;
        this.rice = Rice;
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

    public Boolean getNoodle() {
        return noodle;
    }

    public void setNoodle(Boolean noodle) {
        this.noodle = noodle;
    }

    public Boolean getSoup() {
        return soup;
    }

    public void setSoup(Boolean soup) {
        this.soup = soup;
    }

    public Boolean getRice() {
        return rice;
    }

    public void setRice(Boolean rice) {
        this.rice = rice;
    }

    public String toString() {
        return String.format("%s,%s","%s",this.foodName,this.location, this.description);
    }
}
