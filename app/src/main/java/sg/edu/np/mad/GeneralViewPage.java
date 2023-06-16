package sg.edu.np.mad;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
    ArrayList<Food> foodList = new ArrayList<>();

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

        FloatingActionButton fab = findViewById(R.id.filterbutton);
        fab.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               Intent toFilter = new Intent(GeneralViewPage.this, GeneralView_Filter.class);
               startActivityForResult(toFilter,101);
           }

       });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Food> filteredList = new ArrayList<>();
                for (Food food : foodList) {
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

        //creating all the food items
        //Munch
        Food munchSaladBowlL = new Food("Large bowl Salad", "Munch", 9.5, 645, "Suitable for 2 to 4 pax. Available for dine-in Only!", R.drawable.chickenrice, true, false, true);
        Food munchSaladBowlR = new Food("Regular bowl Salad", "Munch", 4, 400, "Filling amount for one person", R.drawable.store, true, false, true);
        Food munchWestern1 = new Food("Ribeye Steak", "Munch", 8.3, 567, "Delicious Ribeye steak with 2 sides", R.drawable.chickenrice, true, false, true);
        Food munchWestern2 = new Food("Mixed Grilled", "Munch", 9.8, 650, "Multiple Varieties of grills with 2 sides", R.drawable.chickenrice, false, false, false);
        Food munchWestern3 = new Food("Grilled Salmon", "Munch", 8.5, 250, "Grilled Salmon and it comes with 2 sides!", R.drawable.chickenrice,false, false, false);
        Food munchWestern4 = new Food("Chicken Chop", "Munch", 5, 480, "Delicious chicken chop with 2 sides", R.drawable.chickenrice, false, false, true);
        Food munchWestern5 = new Food("Chicken Teriyaki", "Munch", 5, 490, "Delicious chicken chop with Teriyaki sauce and 2 sides", R.drawable.chickenrice, false, false, false);
        Food munchWestern6 = new Food("Chicken Cutlet", "Munch", 5, 490, "Fried chicken cutlet with 2 sides!", R.drawable.chickenrice,false, false, false);
        Food munchWestern7 = new Food("Mexican Chicken Chop", "Munch",6, 550, "Mexican Style Chicken Chop with 2 sides", R.drawable.chickenrice, false, false, false);
        Food munchWestern8 = new Food("Fish & Chips", "Munch", 4.8, 480, "Delicious Fried Fish pieces with 2 sides", R.drawable.chickenrice, false, false, false);
        Food munchWestern9 = new Food("Grilled Dory Fish", "Munch", 4.8, 250, "Grilled Dory Fish and it comes with 2 sides!", R.drawable.chickenrice,false, false, false);
        Food munchWestern10 = new Food("Sausage Combo", "Munch", 4.5, 490, "Delicious chicken chop with 2 sides", R.drawable.chickenrice, false, false, false);
        Food munchWestern11 = new Food("Spaghetti With Grilled Dory Fish", "Munch", 5.5, 500, "Tomato Spaghetti with grilled dory fish", R.drawable.chickenrice, true, false, false);
        Food munchWestern12 = new Food("Spaghetti With Breaded Fish", "Munch", 5.5, 550, "Tomato Spaghetti with fried fish", R.drawable.chickenrice,true, false, false);
        Food munchWestern13 = new Food("Spaghetti with sausage", "Munch",4.5, 550, "Tomato Spaghetti with sausages", R.drawable.chickenrice, true, false, false);
        Food munchWestern14 = new Food("Spaghetti with chicken chop", "Munch", 5.5, 480, "Tomato Spaghetti with chicken chop", R.drawable.chickenrice, true, false, false);
        Food munchWestern15 = new Food("Spaghetti with chicken cutlet", "Munch", 5.5, 480, "Tomato Spaghetti with fried chicken cutlet", R.drawable.chickenrice,true, false, false);
        Food munchWestern16 = new Food("Aglio Olio chicken chop", "Munch", 5.5, 490, "Delicious Aglio Olio with chicken chop", R.drawable.chickenrice, true, false, false);
        Food munchWestern17 = new Food("Aglio Olio chicken cutlet", "Munch", 5.5, 550, "Delicious Aglio Olio with fried chicken cutlet ", R.drawable.chickenrice, true, false, false);
        Food munchWestern18 = new Food("Aglio Olio grilled dory fish", "Munch", 5.5, 500, "Delicious Aglio Olio with grilled dory fish", R.drawable.chickenrice,true, false, false);
        Food munchWestern19 = new Food("Aglio Olio Breaded Fish", "Munch",5.5, 550, "Delicious Aglio Olio with  breaded fish", R.drawable.chickenrice, true, false, false);
        Food munchWestern20 = new Food("Spring chicken (Half)", "Munch", 5.1, 550, "Half of whole fried chicken", R.drawable.chickenrice, false, false, false);
        Food munchWestern21 = new Food("Chicken Wings (Min 2 Pcs)", "Munch", 1.3, 350, "Savoury fried chicken wings", R.drawable.chickenrice,false, false, false);
        Food munchWestern22 = new Food("Add-Ons: Coleslaw", "Munch", 1, 120, "Favourful bowl of vegetables", R.drawable.chickenrice, false, false, false);
        Food munchWestern23 = new Food("Add-Ons: French Fries", "Munch", 1.5, 300, "Big bowl of salty fries that you can share with friends", R.drawable.chickenrice, false, false, false);
        Food munchWestern24 = new Food("Add-Ons: Cheesy French Fries", "Munch", 2.8, 400, "Salty fries with cheesy cheese sauce", R.drawable.chickenrice,false, false, false);
        Food munchWestern25 = new Food("Add-Ons: Mushroom Soup", "Munch", 2, 250, "Favourful bowl of mushroom soup", R.drawable.chickenrice, false, true, false);
        Food munchWestern26 = new Food("Add-Ons: Sunshine Egg", "Munch", 0.8, 65, "A sunshine egg with flowy egg yolk", R.drawable.chickenrice, false, false, false);
        Food munchWestern27 = new Food("Add-Ons: Nacho Cheese", "Munch", 1, 50, "Cheesy nacho cheese", R.drawable.chickenrice,false, false, false);

        Food munchClaypot1 = new Food("Magma Beef/Chicken", "Munch", 6, 550, "A savoury stew with a lot of ingredients", R.drawable.mala, false, true, true);
        Food munchClaypot2 = new Food("Magma Seafood", "Munch", 6.5, 550, "A savoury stew with a lot of seafoods", R.drawable.mala, true, true, true);
        Food munchClaypot3 = new Food("Paitan Scallop Beef/Chicken", "Munch", 6, 550, "A savoury stew with a lot of ingredients", R.drawable.mala, true, true, true);
        Food munchClaypot4 = new Food("Paitan Scallop Seafood", "Munch", 6.5, 550, "A savoury stew with a lot of seafoods", R.drawable.mala, true, true, true);
        Food munchClaypot5 = new Food("Tomyum Beef/Chicken", "Munch", 5.5, 550, "A savoury stew with a lot of ingredients", R.drawable.mala, true, true, true);
        Food munchClaypot6 = new Food("Tomyum Scallop Seafood", "Munch", 6, 550, "A savoury stew with a lot of seafoods", R.drawable.mala, true, true, true);
        Food munchClaypot7 = new Food("Nonya Curry Rendang Chicken Cutlet", "Munch", 5.5, 550, "A Nonya style curry with chicken cutlet", R.drawable.mala, true, true, true);
        Food munchClaypot8 = new Food("Nonya Curry Rendang Beef", "Munch", 5.5, 550, "A Nonya style curry with beef", R.drawable.mala, true, true, true);
        Food munchClaypot9 = new Food("Yaki Cheesey Tamago", "Munch", 5.5, 550, "Cheesy Egg roll", R.drawable.mala, false, false, false);
        Food munchClaypot10 = new Food("Fried Takoyaki (6 Pcs)", "Munch", 5.5, 550, "Takoyaki balls with savoury ingredients", R.drawable.mala, false, false, false);
        Food munchClaypot11 = new Food("Add-Ons: Japanese Rice", "Munch", 0.8, 100, "Fresh, steaming hot bowl of rice", R.drawable.mala, false, false, true);
        Food munchClaypot12 = new Food("Add-Ons: Egg", "Munch", 0.8, 65, "A sunny side up egg", R.drawable.mala, false, false, false);
        Food munchClaypot13 = new Food("Add-Ons: Chicken Cutlet", "Munch", 2.5, 550, "Fried chicken cutlet", R.drawable.mala, false, false, false);

        Food munchNasiPadang1 = new Food("Ayam Penyet", "Munch", 4.5, 550, "Indonesian fried chicken with chili sauce", R.drawable.store, false, false, true);
        Food munchNasiPadang2 = new Food("Ayam Penyet Sabolado", "Munch", 4.5, 600, "Indonesian fried chicken with special chili sauce", R.drawable.store, false, false, true);
        Food munchNasiPadang3 = new Food("Grilled chicken with sweet and spicy sauce", "Munch", 4.5, 550, "Sweet and savoury taste of grilled chicken", R.drawable.store, false, false, true);

        Food food1 = new Food("Roasted Chicken Rice", "Food Club", 3, 500, "Very favourful", R.drawable.chickenrice, false, true, true);

        foodList.add(munchSaladBowlR);
        foodList.add(munchSaladBowlL);
        foodList.add(munchWestern1);
        foodList.add(munchWestern2);
        foodList.add(munchWestern3);
        foodList.add(munchWestern4);
        foodList.add(munchWestern5);
        foodList.add(munchWestern6);
        foodList.add(munchWestern7);
        foodList.add(munchWestern8);
        foodList.add(munchWestern9);
        foodList.add(munchWestern10);
        foodList.add(munchWestern11);
        foodList.add(munchWestern12);
        foodList.add(munchWestern13);
        foodList.add(munchWestern14);
        foodList.add(munchWestern15);
        foodList.add(munchWestern16);
        foodList.add(munchWestern17);
        foodList.add(munchWestern18);
        foodList.add(munchWestern19);
        foodList.add(munchWestern20);
        foodList.add(munchWestern21);
        foodList.add(munchWestern22);
        foodList.add(munchWestern23);
        foodList.add(munchWestern24);
        foodList.add(munchWestern25);
        foodList.add(munchWestern26);
        foodList.add(munchWestern27);

        foodList.add(munchClaypot1);
        foodList.add(munchClaypot2);
        foodList.add(munchClaypot3);
        foodList.add(munchClaypot4);
        foodList.add(munchClaypot5);
        foodList.add(munchClaypot6);
        foodList.add(munchClaypot7);
        foodList.add(munchClaypot8);
        foodList.add(munchClaypot9);
        foodList.add(munchClaypot10);
        foodList.add(munchClaypot11);
        foodList.add(munchClaypot12);
        foodList.add(munchClaypot13);

        foodList.add(munchNasiPadang1);
        foodList.add(munchNasiPadang2);
        foodList.add(munchNasiPadang3);


        gAdapter = new GeneralView_Adapter(GeneralViewPage.this, foodList, this);
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
        for (Food food : foodList) {
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

    private void switchToActivity(int position) {
        Intent intent = new Intent(GeneralViewPage.this, CataloguePage.class);
        intent.putExtra("position", position); // Pass any data if required
        startActivity(intent);
    }



    @Override
    protected void onActivityResult(int requestCode,int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        String selectedDataString = data.getStringExtra("data");
        if(requestCode==101)
        {
            String[] foodStrings = selectedDataString.split(",");

            List<Food> filteredList = new ArrayList<>();

            for (Food food : foodList)
            {
                for (String chip : foodStrings){
                    if (chip.equals("Makan Place")){
                        if (food.getLocation() == "Makan Place")
                        {
                            filteredList.add(food);
                        }
                    }
                    else if (chip.equals("Food Club")){
                        if (food.getLocation() == "Food Club")
                        {
                            filteredList.add(food);
                        }
                    }
                    else if (chip.equals("Munch")){
                        if (food.getLocation() == "Munch")
                        {
                            filteredList.add(food);
                        }
                    }
                }

            }

            gAdapter.setFilteredList(filteredList);
        }

    }

}