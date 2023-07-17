package sg.edu.np.mad;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.text.DecimalFormat;

public class CataloguePage extends AppCompatActivity implements Serializable {
    TextView foodName;
    TextView foodName2;
    ImageView foodImage;
    ImageView foodImage2;
    ImageView foodImage3;
    TextView foodPrice;
    TextView foodCalories;
    TextView storeLocation;

    //Food object;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogue_page);

        foodName = findViewById(R.id.CATfoodName);
        foodName2 = findViewById(R.id.CATfoodName3);
        foodPrice = findViewById(R.id.CATfoodPrice);
        foodCalories = findViewById(R.id.CATfoodCalories);
        foodImage = findViewById(R.id.image1);
        foodImage2 = findViewById(R.id.image2);
        foodImage3 = findViewById(R.id.image3);
        storeLocation = findViewById(R.id.storeLocation);
        Button FoodReview = findViewById(R.id.OpenAllFoodReviews);

        ImageView BackButton = findViewById(R.id.backButton);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });
        FoodReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent toAFR = new Intent(CataloguePage.this, AllReviewsPage.class);
                toAFR.putExtra("foodname",foodName.getText());
                startActivity(toAFR);

            }
        });


        //getting information from the intent and setting them into the textView and imageView
        foodName.setText(getIntent().getExtras().getString("FoodName"));
        foodName2.setText(getIntent().getExtras().getString("FoodName"));
        foodPrice.setText("$ " + String.valueOf(getIntent().getExtras().getDouble("FoodPrice",0.00)));
        foodCalories.setText(String.valueOf(getIntent().getExtras().getInt("FoodCalories")));
        storeLocation.setText(getIntent().getExtras().getString("storeLocation"));

        int foodImg = getIntent().getIntExtra("FoodImg", 0);
        foodImage.setImageResource(foodImg);

        int foodImg2 = getIntent().getIntExtra("FoodImg2", 0);
        foodImage2.setImageResource(foodImg2);
        foodImage3.setImageResource(foodImg2);


        //object = (Food)getIntent().getExtras().getSerializable("object");

        DatabaseReference reviewsRef = FirebaseDatabase.getInstance().getReference("Reviews");
        ValueEventListener reviewsListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int numofreviews = 0;
                int totalstars = 0;
                float avgstars = 0;
                for (DataSnapshot reviewSnapshot : dataSnapshot.getChildren()) {
                    FoodReview foodReview = reviewSnapshot.getValue(FoodReview.class);
                    if (foodReview != null && foodReview.FoodName.equals(foodName.getText().toString())) {
                        numofreviews++;
                        totalstars += foodReview.Rating;
                    }
                }
                if(numofreviews != 0){
                    avgstars = totalstars/numofreviews;
                    TextView text = findViewById(R.id.textView24);
                    text.setText("" + new DecimalFormat("#.0").format(avgstars/20)+ " Stars");
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
    protected void onStart(){
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        DatabaseReference reviewsRef = FirebaseDatabase.getInstance().getReference("Reviews");
        ValueEventListener reviewsListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int numofreviews = 0;
                int totalstars = 0;
                float avgstars = 0;
                for (DataSnapshot reviewSnapshot : dataSnapshot.getChildren()) {
                    FoodReview foodReview = reviewSnapshot.getValue(FoodReview.class);
                    if (foodReview != null && foodReview.FoodName.equals(foodName.getText().toString())) {
                        numofreviews++;
                        totalstars += foodReview.Rating;
                    }
                }
                if(numofreviews != 0){
                    avgstars = totalstars/numofreviews;
                    TextView text = findViewById(R.id.textView24);
                    text.setText("" + new DecimalFormat("#.0").format(avgstars/20)+ " Stars");
                    if(avgstars == 0){
                        text.setText("0.0 Stars");
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle any errors
            }
        };
        reviewsRef.addListenerForSingleValueEvent(reviewsListener);
    }
}