package sg.edu.np.mad;

import android.util.Log;

public class Food extends Store{
    private String foodName;
    private String location;
    private double price;
    private int calories;
    private int foodImage1;

    private int foodImage2;
    private int foodImage3;

    private int locationImage;
    private String description;
    private Boolean noodle;
    private Boolean soup;
    private Boolean rice;
    private Boolean halal;
    private Boolean vegetarian;
    private Boolean dessert;


    public Food() {
    }

    public Food(String FoodName, String Location, int LocationImage, double Price, int Calories, String Description, int FoodImage, Boolean Noodle, Boolean Soup, Boolean Rice, Boolean Halal, Boolean Vegetarian, Boolean Dessert) {
        this.foodName = FoodName;
        this.location = Location;
        this.locationImage = LocationImage;
        this.price = Price;
        this.calories = Calories;
        this.description = Description;
        this.foodImage2 = FoodImage;
        this.noodle = Noodle;
        this.soup = Soup;
        this.rice = Rice;
        this.halal = Halal;
        this.vegetarian = Vegetarian;
        this.dessert = Dessert;
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


    public int getLocationImage() {
        return locationImage;
    }

    public void setLocationImage(int locationImage) {
        this.locationImage = locationImage;
    }

    @Override
    public Boolean getHalal() {
        return halal;
    }

    @Override
    public void setHalal(Boolean halal) {
        this.halal = halal;
    }

    @Override
    public Boolean getVegetarian() {
        return vegetarian;
    }

    @Override
    public void setVegetarian(Boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public Boolean getDessert() {
        return dessert;
    }

    public void setDessert(Boolean dessert) {
        this.dessert = dessert;
    }

    public Boolean isHealthy(){
        return calories < 500;
    }
    public Boolean isAffordable(){
        return price < 5;
    }
    public Boolean isDrinks(){
        return !noodle && !rice && !dessert;
    }
    public String toString() {
        String convertedMsg = this.foodName +"," + this.location +","+ this.description;
        return convertedMsg;
        //return String.format("%s","%s","%s",this.foodName,this.location, this.description);
    }
}
