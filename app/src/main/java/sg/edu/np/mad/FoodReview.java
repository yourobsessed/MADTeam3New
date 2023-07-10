package sg.edu.np.mad;

import android.net.Uri;

import java.io.Serializable;

public class FoodReview implements Serializable {
    public String FoodName;
    public int Rating;
    public String Description;
    public String User;

    public FoodReview(String foodName, int rating, String description, String user) {
        FoodName = foodName;
        Rating = rating;
        Description = description;
        User = user;
    }


    public FoodReview() {
    }
}
