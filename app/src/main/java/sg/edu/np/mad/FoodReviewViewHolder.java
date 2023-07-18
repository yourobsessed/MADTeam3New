package sg.edu.np.mad;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class FoodReviewViewHolder  extends RecyclerView.ViewHolder{
    private TextView username;
    private TextView description;
    private RatingBar ratingBar;
    private ImageView image;

    public FoodReviewViewHolder(View itemView) {
        super(itemView);
        username = itemView.findViewById(R.id.username);
        description = itemView.findViewById(R.id.description);
        ratingBar = itemView.findViewById(R.id.ratingBar3);
        image = itemView.findViewById(R.id.imageView13);
    }

    public void bind(FoodReview foodreview, Context context) {
        username.setText(foodreview.User);
        description.setText(foodreview.Description);
        ratingBar.setRating(foodreview.Rating/20);
        if(!foodreview.Image.equals("")){
            Glide.with(context)
                    .load(foodreview.Image)
                    .into(image);
            image.getLayoutParams().height = 410;
        }
    }
}
