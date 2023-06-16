package sg.edu.np.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class CataloguePage extends AppCompatActivity {
    TextView foodName;
    ImageView foodImage;
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
        foodImage= findViewById(R.id.imageView3);
        System.out.println(foodName);

        /*Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            foodName.setText(bundle.getString("FoodName"));
            foodCalories.setText(bundle.getInt("FoodCalories"));
            foodPrice.setText(String.valueOf(bundle.getDouble("FoodPrice", 0.0)));
            foodImage.setImageResource(bundle.getInt("FoodImg"));
        }*/
        foodName.setText(getIntent().getExtras().getString("FoodName"));
        System.out.println(foodName);
        foodPrice.setText("$" + String.valueOf(getIntent().getExtras().getDouble("FoodPrice")));
        foodCalories.setText(String.valueOf(getIntent().getExtras().getInt("FoodCalories")));
        int foodImg = getIntent().getIntExtra("FoodImg", 0);
        foodImage.setImageResource(foodImg);
    }
}