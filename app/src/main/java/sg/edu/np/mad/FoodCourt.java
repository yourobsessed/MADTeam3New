package sg.edu.np.mad;

import java.util.ArrayList;

public class FoodCourt {
    private String foodCourtName;
    private String foodCourtDesc;
    private int foodCourtImage;
    public ArrayList<Store> storeList;

    public FoodCourt() {
    }

    public FoodCourt(String foodCourtName, String foodCourtDesc, int foodCourtImage) {
        this.foodCourtName = foodCourtName;
        this.foodCourtDesc = foodCourtDesc;
        this.foodCourtImage = foodCourtImage;
    }

    public String getFoodCourtName() {
        return foodCourtName;
    }

    public void setFoodCourtName(String foodCourtName) {
        this.foodCourtName = foodCourtName;
    }

    public String getFoodCourtDesc() {
        return foodCourtDesc;
    }

    public void setFoodCourtDesc(String foodCourtDesc) {
        this.foodCourtDesc = foodCourtDesc;
    }

    public int getFoodCourtImage() {
        return foodCourtImage;
    }

    public void setFoodCourtImage(int foodCourtImage) {
        this.foodCourtImage = foodCourtImage;
    }

    public ArrayList<Store> getStoreList() {
        return storeList;
    }

    public void setStoreList(ArrayList<Store> storeList) {
        this.storeList = storeList;
    }

    public String toString() {
        return String.format("%s,%s",this.foodCourtName,this.foodCourtDesc);
    }
}
