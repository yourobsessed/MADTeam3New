package sg.edu.np.mad;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class CataloguePage extends AppCompatActivity {
    TextView foodName;
    ImageView foodImage;
    ImageView foodImage2;
    ImageView foodImage3;
    ImageView locationimg;
    TextView foodPrice;
    TextView foodCalories;
    ImageView locationImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogue_page);

        foodName = findViewById(R.id.CATfoodName);
        foodPrice = findViewById(R.id.CATfoodPrice);
        foodCalories = findViewById(R.id.CATfoodCalories);
        foodImage= findViewById(R.id.image1);
        foodImage2= findViewById(R.id.image2);
        foodImage3= findViewById(R.id.image3);
        locationimg = findViewById(R.id.imageView3);

        foodName.setText(getIntent().getExtras().getString("FoodName"));
        foodPrice.setText("$ " + String.valueOf(getIntent().getExtras().getDouble("FoodPrice",0.00)));
        foodCalories.setText(String.valueOf(getIntent().getExtras().getInt("FoodCalories")));
        int foodImg = getIntent().getIntExtra("FoodImg", 0);
        foodImage.setImageResource(foodImg);
        foodImage2.setImageResource(foodImg);
        foodImage3.setImageResource(foodImg);
        int locationImg = getIntent().getIntExtra("LocationImg", 0);
        locationimg.setImageResource(locationImg);

    }
}