package sg.edu.np.mad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class FoodCourtViewPage extends AppCompatActivity implements SelectListenerFC{
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
        FoodCourt_Adapter fcadapter = new FoodCourt_Adapter(foodCourtArrayList, this);
        LinearLayoutManager fclayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(fclayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(fcadapter);

    }

    @Override
    public void onItemClicked(FoodCourt foodCourt) {
        for(FoodCourt f : foodCourtArrayList){
            if (f.getFoodCourtName() == "Makan Place"){
                Intent toGeneralPage = new Intent(FoodCourtViewPage.this, GeneralViewPage.class);
                startActivity(toGeneralPage);
            }
            else if (f.getFoodCourtName() == "Munch"){
                Intent toGeneralPage = new Intent(FoodCourtViewPage.this, GeneralViewPage.class); //planning to have multiple general view page for each of the foodcourt
                startActivity(toGeneralPage);
            }
            else if(f.getFoodCourtName() =="Food Club"){
                Intent toGeneralPage = new Intent(FoodCourtViewPage.this, GeneralViewPage.class);
                startActivity(toGeneralPage);
            }
        }

    }
}