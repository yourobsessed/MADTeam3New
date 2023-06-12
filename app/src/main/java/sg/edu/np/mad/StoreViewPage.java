package sg.edu.np.mad;

import androidx.appcompat.app.AppCompatActivity;
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
        Food tryFood = new Food();
        tryFood.setFoodImage(R.drawable.chickenrice);
        tryFood.setFoodName("Ayam Penyet");
        tryFood.setDescription("Fried Chicken with curry rice");
        tryFood.setLocation("Food Club");
        tryFood.setCalories(500);
        tryFood.setPrice(4.50);
        foodArrayList.add(tryFood);

        RecyclerView recyclerView= findViewById(R.id.recyclerView);
        FoodAdapter adapter = new FoodAdapter(foodArrayList); //need to create foodList
        LinearLayoutManager foodLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(foodLayout);
        recyclerView.setAdapter(adapter);


    }
}