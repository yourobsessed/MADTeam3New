package sg.edu.np.mad;

public class Notification {
    String title;
    String content;
    Food foodItem;

    public Notification() {
    }

    public Notification(String Title, String Content, Food FoodItem) {
        title = Title;
        content = Content;
        foodItem = FoodItem;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Food getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(Food foodItem) {
        this.foodItem = foodItem;
    }
}
