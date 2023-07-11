package sg.edu.np.mad;

import android.net.Uri;

import java.io.Serializable;

public class FoodReview implements Serializable {
    public String FoodName;
    public int Rating;
    public String Description;
    public String User;
    public String Image;

    public FoodReview(String foodName, int rating, String description, String user, String image) {
        FoodName = foodName;
        Rating = rating;
        Description = description;
        User = user;
        Image =  image;
    }


    public FoodReview() {
    }
}
