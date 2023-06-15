package sg.edu.np.mad;

public class Store extends FoodCourt{

    private String storeName;
    private String location;
    private String description;
    private int image;

    public Store() {
    }

    public Store(String StoreName, String Location, String Description, int Image) {
        this.storeName = StoreName;
        this.location = getFoodCourtName();
        this.description = Description;
        this.image = Image;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
