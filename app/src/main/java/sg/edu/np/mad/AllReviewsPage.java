package sg.edu.np.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;

public class AllReviewsPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_reviews_page);

        TextView text = findViewById(R.id.txt1);
        text.setText(getIntent().getExtras().getString("foodname"));

        ImageView backButton = findViewById(R.id.imageView11);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TextView writereviewbutton = findViewById(R.id.writereviewbutton);
        writereviewbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent toAFR = new Intent(AllReviewsPage.this, FoodReviewPage.class);
                toAFR.putExtra("foodname",getIntent().getExtras().getString("foodname"));
                startActivity(toAFR);

            }
        });



        DatabaseReference reviewsRef = FirebaseDatabase.getInstance().getReference("Reviews");
        ValueEventListener reviewsListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int numofreviews = 0;
                int totalstars = 0;
                float avgstars = 0;
                for (DataSnapshot reviewSnapshot : dataSnapshot.getChildren()) {
                    FoodReview foodReview = reviewSnapshot.getValue(FoodReview.class);
                    if (foodReview != null && foodReview.FoodName.equals(text.getText().toString())) {
                        numofreviews++;
                        totalstars += foodReview.Rating;
                    }
                }
                if(numofreviews != 0){
                    avgstars = totalstars/numofreviews;
                    TextView text = findViewById(R.id.txt);
                    text.setText("" + new DecimalFormat("#.0").format(avgstars/20));
                    RatingBar stars = findViewById(R.id.ratingBar);
                    stars.setRating(avgstars/20);
                    TextView numofrev = findViewById(R.id.txt2);
                    numofrev.setText("Based on " + numofreviews + " reviews");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle any errors
            }
        };

        reviewsRef.addListenerForSingleValueEvent(reviewsListener);
    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}