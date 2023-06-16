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

        Food munchSaladBowl = new Food("Regular", "Munch", 4, 400, "Filling amount for one person", R.drawable.store, true, false, true);

        Food food1 = new Food("Roasted Chicken Rice", "Food Club", 3, 500, "Very favourful", R.drawable.chickenrice, false, true, true);

        foodArrayList.add(food1);
        foodArrayList.add(munchSaladBowl);

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