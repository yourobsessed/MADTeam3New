package sg.edu.np.mad;

public class Food extends Store{
    private String foodName;
    private String location;
    private double price;
    private int calories;
    private int foodImage1;

    private int foodImage2;
    private int foodImage3;
    private int foodImage4;
    private int foodImage5;
    private int locationImage;
    private String description;
    private Boolean noodle;
    private Boolean soup;
    private Boolean rice;


    public Food() {
    }

    public Food(String FoodName, String Location, int LocationImage, double Price, int Calories, String Description, int FoodImage, Boolean Noodle, Boolean Soup, Boolean Rice) {
        this.foodName = FoodName;
        this.location = Location;
        this.locationImage = LocationImage;
        this.price = Price;
        this.calories = Calories;
        this.description = Description;
        this.foodImage1 = FoodImage;
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

    public int getFoodImage1() {
        return foodImage1;
    }

    public void setFoodImage1(int foodImage) {
        this.foodImage1 = foodImage;
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

    public int getFoodImage2() {
        return foodImage2;
    }

    public void setFoodImage2(int foodImage2) {
        this.foodImage2 = foodImage2;
    }

    public int getFoodImage3() {
        return foodImage3;
    }

    public void setFoodImage3(int foodImage3) {
        this.foodImage3 = foodImage3;
    }

    public int getFoodImage4() {
        return foodImage4;
    }

    public void setFoodImage4(int foodImage4) {
        this.foodImage4 = foodImage4;
    }

    public int getFoodImage5() {
        return foodImage5;
    }

    public void setFoodImage5(int foodImage5) {
        this.foodImage5 = foodImage5;
    }

    public int getLocationImage() {
        return locationImage;
    }

    public void setLocationImage(int locationImage) {
        this.locationImage = locationImage;
    }

    public String toString() {
        return String.format("%s,%s","%s",this.foodName,this.location, this.description);
    }
}
