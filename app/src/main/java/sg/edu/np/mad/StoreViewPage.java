package sg.edu.np.mad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class StoreViewPage extends AppCompatActivity {
    ArrayList<Food> foodArrayList = new ArrayList<Food>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_view_page);

        Food munchMala = new Food();

        Food food1 = new Food();
        food1.setFoodImage(R.drawable.chickenrice);
        food1.setFoodName("Roasted Chicken Rice");
        food1.setPrice(3);
        food1.setCalories(500);
        food1.setDescription("Very favourful");
        food1.setLocation("Food Club");

        foodArrayList.add(food1);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        FoodAdapter adapter = new FoodAdapter(foodArrayList); //need to create foodList
        LinearLayoutManager foodLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(foodLayout);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart(){
        super.onStart();
    }
}