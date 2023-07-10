package sg.edu.np.mad;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

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
        Button FoodReview = findViewById(R.id.OpenFoodReview);

        ImageView BackButton = findViewById(R.id.imageView8);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });
        FoodReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent toFR = new Intent(CataloguePage.this, FoodReviewPage.class);
                startActivity(toFR);

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
    }

    @Override
    protected void onStart(){
        super.onStart();
        /*locationimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tomap = new Intent(CataloguePage.this, map.class);
                tomap.putExtra("object", object.getLocationImage());
                startActivity(tomap);
            }
        });*/
    }
}