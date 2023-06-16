package sg.edu.np.mad;

import java.util.ArrayList;

public class Store {

    private String storeName;
    private String location;
    private String description;

    private Boolean halal;
    private Boolean vegetarian;


    public Store() {
    }

    public Store(String StoreName, String Location, String Description, Boolean Halal, Boolean Vegetarian) {
        this.storeName = StoreName;
        this.location = Location;
        this.description = Description;
        this.halal = Halal;
        this.vegetarian = Vegetarian;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String foodName) {
        this.storeName = storeName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getHalal() {
        return halal;
    }

    public void setHalal(Boolean halal) {
        this.halal = halal;
    }

    public Boolean getVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(Boolean vegetarian) {
        this.vegetarian = vegetarian;
    }
}
