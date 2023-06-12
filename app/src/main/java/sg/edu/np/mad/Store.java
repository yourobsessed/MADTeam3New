package sg.edu.np.mad;

public class Store {
    private String storeName;
    private String location;
    private String description;

    public Store() {
    }

    public Store(String StoreName, String Location, String Description) {
        StoreName = storeName;
        Location = location;
        Description = description;
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
}
