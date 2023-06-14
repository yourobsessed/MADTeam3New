package sg.edu.np.mad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class FoodCourtViewPage extends AppCompatActivity {
    ArrayList<FoodCourt> foodCourtArrayList = new ArrayList<FoodCourt>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_court_view_page);

        FoodCourt MakanPlace = new FoodCourt("Makan Place", "Furthest Away", R.drawable.mala);
        FoodCourt FoodClub = new FoodCourt("Food Club", "Near ICT Block", R.drawable.chickenrice);
        FoodCourt Munch = new FoodCourt("Munch", "Near Business Block", R.drawable.store);

        foodCourtArrayList.add(MakanPlace);
        foodCourtArrayList.add(FoodClub);
        foodCourtArrayList.add(Munch);

        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
        FoodCourt_Adapter fcadapter = new FoodCourt_Adapter(foodCourtArrayList);
        LinearLayoutManager fclayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(fclayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(fcadapter);

    }
}