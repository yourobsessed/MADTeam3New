package sg.edu.np.mad;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GeneralViewPage extends AppCompatActivity implements SelectListenerFood{

    private SearchView searchView;
    private RecyclerView recyclerView;
    private GeneralView_Adapter gAdapter;

    private Chip chipHalal,chipVegeterian,chipHealthy,chipAffordable,chipNoodles,chipRice,chipDessert,chipDrinks;

    ArrayList<String> selectedChipData = new ArrayList<>();
    ArrayList<Food> storeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_view_page);
        recyclerView=findViewById(R.id.recyclerView);
        searchView=findViewById(R.id.searchView);
        searchView.clearFocus();

        //chips
        /*chipHalal=findViewById(R.id.chipHealthy);
        chipVegeterian=findViewById(R.id.chipVegeterian);
        chipHealthy=findViewById(R.id.chipHealthy);
        chipAffordable=findViewById(R.id.chipAffordable);
        chipNoodles=findViewById(R.id.chipNoodles);
        chipRice=findViewById(R.id.chipRice);
        chipDessert=findViewById(R.id.chipDessert);
        chipDrinks=findViewById(R.id.chipDrinks);*/

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Food> filteredList = new ArrayList<>();
                for (Food food : storeList) {
                    if (food.getStoreName().toLowerCase().contains(newText.toLowerCase())) {
                        filteredList.add(food);

                    }
                }
                if (filteredList.isEmpty()) {
                    Toast.makeText(GeneralViewPage.this,"No data found",Toast.LENGTH_SHORT).show();
                }
                else {
                    gAdapter.setFilteredList(filteredList);
                }
                return true;
            }
        });
        Food munchSaladBowl = new Food("Regular", "Munch", 4, 400, "Filling amount for one person", R.drawable.store, true, false, true);

        Food food1 = new Food("Roasted Chicken Rice", "Food Club", 3, 500, "Very favourful", R.drawable.chickenrice, false, true, true);

        storeList.add(munchSaladBowl);
        storeList.add(food1);

        gAdapter = new GeneralView_Adapter(storeList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(gAdapter);

    }


    @Override
    public void onItemClicked(Food food) {
        Intent toCataloguePage = new Intent(GeneralViewPage.this, CataloguePage.class);
        startActivity(toCataloguePage);
    }
    private void filterList(String text) {
        List<Food> filteredList = new ArrayList<>();
        for (Food food : storeList) {
            if (food.getStoreName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(food);

            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(GeneralViewPage.this,"No data found",Toast.LENGTH_SHORT).show();
        }
        else {
            gAdapter.setFilteredList(filteredList);
        }

    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    /*searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
        @Override
        public boolean onQueryTextSubmit(String query){
            searchList=new ArrayList<>();
            if (query.length()>0){
                for(int i=0;i <arrayList.size();i++){
                    if (arrayList.get(1). getFruitName(). toUpperCase(). contains(query. toUpperCase() ) ){
                        ModelClass modelClass=new ModelClass();modelClass.setFruitName(arrayList.get(i).getFruitName());modelClass. setFruitNum(arrayList. get(i).getFruitNum());modelClass.setImg(arrayList.get(i).getImg());searchList. add(modelClass);
                        {
                            {
                                RecyclerView.LayoutManager layoutManager=new LinearLayoutManager( context: MainActivity.this);recyclerView.setLayoutManager(layoutManager) ;
                                FruitAdapter fruitAdapter=new FruitAdapter( context: MainActivity.this,searchList);recyclerView. setAdapter(fruitAdapter);
                            }
                            return false;*/


    @Override
    protected void onResume(){
        super.onResume();

        //for( int i = 0; i < storeList.size(); i++){

        //}
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        String selectedDataString = data.getStringExtra("data");
        if(requestCode==101)
        {

            String[] foodStrings = selectedDataString.split(",");
            List<Food> filteredList = new ArrayList<>();
            /*for (String foodString : foodStrings) {
                Food food = Food.fromString(foodString); // Use a method in the Food class to create an instance from the string
                selectedDataList.add(food);
            }*/
            gAdapter.setFilteredList(filteredList);
        }

    }

}