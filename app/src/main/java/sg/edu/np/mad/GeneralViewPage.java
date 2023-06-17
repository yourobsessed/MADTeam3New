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
                filterList(newText);
                return true;
            }
        });

        foodList = CreateObject(foodList);
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

    public ArrayList<Food> CreateObject(ArrayList<Food> foodList){
        //creating all the food items
        //Munch
        Food munchSaladBowlL = new Food("Large bowl Salad", "Munch",R.drawable.munchmap, 9.5, 645, "Suitable for 2 to 4 pax. Available for dine-in Only!", R.drawable.chickenrice, true, false, true);
        Food munchSaladBowlR = new Food("Regular bowl Salad", "Munch",R.drawable.munchmap ,4, 400, "Filling amount for one person", R.drawable.store, true, false, true);
        Food munchWestern1 = new Food("Ribeye Steak", "Munch", R.drawable.munchmap,8.3, 567, "Delicious Ribeye steak with 2 sides", R.drawable.chickenrice, true, false, true);
        Food munchWestern2 = new Food("Mixed Grilled", "Munch",R.drawable.munchmap, 9.8, 650, "Multiple Varieties of grills with 2 sides", R.drawable.chickenrice, false, false, false);
        Food munchWestern3 = new Food("Grilled Salmon", "Munch",R.drawable.munchmap, 8.5, 250, "Grilled Salmon and it comes with 2 sides!", R.drawable.chickenrice,false, false, false);
        Food munchWestern4 = new Food("Chicken Chop", "Munch",R.drawable.munchmap, 5, 480, "Delicious chicken chop with 2 sides", R.drawable.chickenrice, false, false, true);
        Food munchWestern5 = new Food("Chicken Teriyaki", "Munch", R.drawable.munchmap,5, 490, "Delicious chicken chop with Teriyaki sauce and 2 sides", R.drawable.chickenrice, false, false, false);
        Food munchWestern6 = new Food("Chicken Cutlet", "Munch", R.drawable.munchmap,5, 490, "Fried chicken cutlet with 2 sides!", R.drawable.chickenrice,false, false, false);
        Food munchWestern7 = new Food("Mexican Chicken Chop", "Munch",R.drawable.munchmap,6, 550, "Mexican Style Chicken Chop with 2 sides", R.drawable.chickenrice, false, false, false);
        Food munchWestern8 = new Food("Fish & Chips", "Munch",R.drawable.munchmap, 4.8, 480, "Delicious Fried Fish pieces with 2 sides", R.drawable.chickenrice, false, false, false);
        Food munchWestern9 = new Food("Grilled Dory Fish", "Munch",R.drawable.munchmap, 4.8, 250, "Grilled Dory Fish and it comes with 2 sides!", R.drawable.chickenrice,false, false, false);
        Food munchWestern10 = new Food("Sausage Combo", "Munch",R.drawable.munchmap, 4.5, 490, "Delicious chicken chop with 2 sides", R.drawable.chickenrice, false, false, false);
        Food munchWestern11 = new Food("Spaghetti With Grilled Dory Fish", "Munch",R.drawable.munchmap, 5.5, 500, "Tomato Spaghetti with grilled dory fish", R.drawable.chickenrice, true, false, false);
        Food munchWestern12 = new Food("Spaghetti With Breaded Fish", "Munch", R.drawable.munchmap,5.5, 550, "Tomato Spaghetti with fried fish", R.drawable.chickenrice,true, false, false);
        Food munchWestern13 = new Food("Spaghetti with sausage", "Munch",R.drawable.munchmap,4.5, 550, "Tomato Spaghetti with sausages", R.drawable.chickenrice, true, false, false);
        Food munchWestern14 = new Food("Spaghetti with chicken chop", "Munch", R.drawable.munchmap,5.5, 480, "Tomato Spaghetti with chicken chop", R.drawable.chickenrice, true, false, false);
        Food munchWestern15 = new Food("Spaghetti with chicken cutlet", "Munch",R.drawable.munchmap, 5.5, 480, "Tomato Spaghetti with fried chicken cutlet", R.drawable.chickenrice,true, false, false);
        Food munchWestern16 = new Food("Aglio Olio chicken chop", "Munch", R.drawable.munchmap,5.5, 490, "Delicious Aglio Olio with chicken chop", R.drawable.chickenrice, true, false, false);
        Food munchWestern17 = new Food("Aglio Olio chicken cutlet", "Munch", R.drawable.munchmap,5.5, 550, "Delicious Aglio Olio with fried chicken cutlet ", R.drawable.chickenrice, true, false, false);
        Food munchWestern18 = new Food("Aglio Olio grilled dory fish", "Munch",R.drawable.munchmap, 5.5, 500, "Delicious Aglio Olio with grilled dory fish", R.drawable.chickenrice,true, false, false);
        Food munchWestern19 = new Food("Aglio Olio Breaded Fish", "Munch",R.drawable.munchmap,5.5, 550, "Delicious Aglio Olio with  breaded fish", R.drawable.chickenrice, true, false, false);
        Food munchWestern20 = new Food("Spring chicken (Half)", "Munch",R.drawable.munchmap, 5.1, 550, "Half of whole fried chicken", R.drawable.chickenrice, false, false, false);
        Food munchWestern21 = new Food("Chicken Wings (Min 2 Pcs)", "Munch", R.drawable.munchmap,1.3, 350, "Savoury fried chicken wings", R.drawable.chickenrice,false, false, false);
        //Food munchWestern22 = new Food("Add-Ons: Coleslaw", "Munch", R.drawable.munchmap,1, 120, "Favourful bowl of vegetables", R.drawable.chickenrice, false, false, false);
        //Food munchWestern23 = new Food("Add-Ons: French Fries", "Munch", R.drawable.munchmap,1.5, 300, "Big bowl of salty fries that you can share with friends", R.drawable.chickenrice, false, false, false);
        //Food munchWestern24 = new Food("Add-Ons: Cheesy French Fries", "Munch",R.drawable.munchmap, 2.8, 400, "Salty fries with cheesy cheese sauce", R.drawable.chickenrice,false, false, false);
        //Food munchWestern25 = new Food("Add-Ons: Mushroom Soup", "Munch",R.drawable.munchmap, 2, 250, "Favourful bowl of mushroom soup", R.drawable.chickenrice, false, true, false);
        //Food munchWestern26 = new Food("Add-Ons: Sunshine Egg", "Munch", R.drawable.munchmap,0.8, 65, "A sunshine egg with flowy egg yolk", R.drawable.chickenrice, false, false, false);
        //Food munchWestern27 = new Food("Add-Ons: Nacho Cheese", "Munch",R.drawable.munchmap, 1, 50, "Cheesy nacho cheese", R.drawable.chickenrice,false, false, false);

        Food munchClaypot1 = new Food("Magma Beef/Chicken", "Munch", R.drawable.munchmap,6, 550, "A savoury stew with a lot of ingredients", R.drawable.mala, false, true, true);
        Food munchClaypot2 = new Food("Magma Seafood", "Munch",R.drawable.munchmap,6.5, 550, "A savoury stew with a lot of seafoods", R.drawable.mala, true, true, true);
        Food munchClaypot3 = new Food("Paitan Scallop Beef/Chicken", "Munch", R.drawable.munchmap,6, 550, "A savoury stew with a lot of ingredients", R.drawable.mala, true, true, true);
        Food munchClaypot4 = new Food("Paitan Scallop Seafood", "Munch", R.drawable.munchmap,6.5, 550, "A savoury stew with a lot of seafoods", R.drawable.mala, true, true, true);
        Food munchClaypot5 = new Food("Tomyum Beef/Chicken", "Munch", R.drawable.munchmap,5.5, 550, "A savoury stew with a lot of ingredients", R.drawable.mala, true, true, true);
        Food munchClaypot6 = new Food("Tomyum Scallop Seafood", "Munch", R.drawable.munchmap,6, 550, "A savoury stew with a lot of seafoods", R.drawable.mala, true, true, true);
        Food munchClaypot7 = new Food("Nonya Curry Rendang Chicken Cutlet", "Munch",R.drawable.munchmap, 5.5, 550, "A Nonya style curry with chicken cutlet", R.drawable.mala, true, true, true);
        Food munchClaypot8 = new Food("Nonya Curry Rendang Beef", "Munch",R.drawable.munchmap, 5.5, 550, "A Nonya style curry with beef", R.drawable.mala, true, true, true);
        Food munchClaypot9 = new Food("Yaki Cheesey Tamago", "Munch", R.drawable.munchmap,5.5, 550, "Cheesy Egg roll", R.drawable.mala, false, false, false);
        Food munchClaypot10 = new Food("Fried Takoyaki (6 Pcs)", "Munch", R.drawable.munchmap,5.5, 550, "Takoyaki balls with savoury ingredients", R.drawable.mala, false, false, false);
        //Food munchClaypot11 = new Food("Add-Ons: Japanese Rice", "Munch",R.drawable.munchmap, 0.8, 100, "Fresh, steaming hot bowl of rice", R.drawable.mala, false, false, true);
        //Food munchClaypot12 = new Food("Add-Ons: Egg", "Munch", R.drawable.munchmap,0.8, 65, "A sunny side up egg", R.drawable.mala, false, false, false);
        //Food munchClaypot13 = new Food("Add-Ons: Chicken Cutlet", "Munch", R.drawable.munchmap,2.5, 550, "Fried chicken cutlet", R.drawable.mala, false, false, false);

        Food munchNasiPadang1 = new Food("Ayam Penyet", "Munch",R.drawable.munchmap, 4.5, 550, "Indonesian fried chicken with chili sauce", R.drawable.store, false, false, true);
        Food munchNasiPadang2 = new Food("Ayam Penyet Sabolado", "Munch",R.drawable.munchmap,4.5, 600, "Indonesian fried chicken with special chili sauce", R.drawable.store, false, false, true);
        Food munchNasiPadang3 = new Food("Grilled chicken with sweet and spicy sauce", "Munch", R.drawable.munchmap,4.5, 550, "Sweet and savoury taste of grilled chicken", R.drawable.store, false, false, true);

        Food munchPizza1 = new Food("Classic Pizza: Meat Madness (Beef)", "Munch", R.drawable.munchmap, 3.5, 350, "Delicious slice of pizza that is enough for one!", R.drawable.store, false, false, false);
        Food munchPizza2= new Food("Classic Pizza: Aloha Hawaiian", "Munch", R.drawable.munchmap, 3.5, 357, "Delicious slice of pizza that is enough for one!", R.drawable.store, false, false, false);
        Food munchPizza3 = new Food("Classic Pizza: Spicy Garlic Chicken", "Munch", R.drawable.munchmap, 3.5, 350, "Delicious slice of pizza that is enough for one!", R.drawable.store, false, false, false);
        Food munchPizza4= new Food("Classic Pizza: Fisherman Catch", "Munch", R.drawable.munchmap, 3.5, 350, "Delicious slice of pizza that is enough for one!", R.drawable.store, false, false, false);
        Food munchPizza5 = new Food("Classic Pizza: Yummy Cheese", "Munch", R.drawable.munchmap, 3.5, 350, "Delicious slice of pizza that is enough for one!", R.drawable.store, false, false, false);
        Food munchPizza6 = new Food("Premium Pizza: Beef Pepperoni Double Wonders", "Munch", R.drawable.munchmap, 4.5, 350, "Delicious slice of pizza that is enough for one!", R.drawable.store, false, false, false);
        Food munchPizza7 = new Food("Premium Pizza: Cheesy Cheese", "Munch", R.drawable.munchmap, 4.5, 460, "Delicious slice of pizza that is enough for one!", R.drawable.store, false, false, false);
        Food munchPizza8 = new Food("Premium Pizza: Margarita", "Munch", R.drawable.munchmap, 4.5, 430, "Delicious slice of pizza that is enough for one!", R.drawable.store, false, false, false);
        Food munchPizza9 = new Food("Premium Pizza: Sambal Chicken/Prawn", "Munch", R.drawable.munchmap, 4.5, 400, "Delicious slice of pizza that is enough for one!", R.drawable.store, false, false, false);

        Food munchKorean1 = new Food("Bibimbap", "Munch", R.drawable.munchmap, 4.40, 460, "A flavorful Korean dish consisting of a bowl of mixed rice topped with various vegetables, meat or tofu, and a fried egg, served with spicy gochujang sauce, offering a delicious blend of flavors and textures.", R.drawable.store, false, true, true);
        Food munchKorean2 = new Food("Hot Stone Bibimbap", "Munch", R.drawable.munchmap, 5.30, 460, "A flavorful Korean dish consisting of a bowl of mixed rice topped with various vegetables, meat or tofu, and a fried egg, served with spicy gochujang sauce, offering a delicious blend of flavors and textures in a hot stone bowl", R.drawable.store, false, true, true);
        Food munchKorean3 = new Food("BBQ Crisy chicken Omelette", "Munch", R.drawable.munchmap, 4.80, 530, "BBQ Crispy Omelette is a delicious combination of a fluffy omelette filled with smoky barbecue-flavored meat or vegetables, featuring a satisfying crisp exterior.", R.drawable.store, false, true, true);
        Food munchKorean4 = new Food("BBQ Chicken set", "Munch", R.drawable.munchmap, 5.0, 510, "Generous amount of spicy barbecue-flavoured chicken, served with crunchy beansprout and onions.", R.drawable.store, false, true, true);
        Food munchKorean5 = new Food("BBQ Saba Fish set", "Munch", R.drawable.munchmap, 5.30, 460, "A whole grilled Saba Fish ", R.drawable.store, false, true, true);
        Food munchKorean6 = new Food("BBQ Beef set", "Munch", R.drawable.munchmap, 5.30, 550, "Generous amount of non-spicy barbecue-flavoured beef, served with crunchy beansprout and onions", R.drawable.store, false, true, true);
        Food munchKorean7 = new Food("BBQ Chicken & Fish set", "Munch", R.drawable.munchmap, 6.30, 600, "Generous amount of spicy barbecue-flavoured chicken, served with crunchy beansprout and onions with a half piece of saba fish", R.drawable.store, false, true, true);
        Food munchKorean8 = new Food("BBQ Beef & Fish set", "Munch", R.drawable.munchmap, 6.30, 650, "Generous amount of non-spicy barbecue-flavoured beef, served with crunchy beansprout and onions with half a piece of saba fish", R.drawable.store, false, true, true);
        Food munchKorean9 = new Food("Korean Ramen", "Munch", R.drawable.munchmap, 2.80, 500, "Hot Instant Noodles ", R.drawable.store, true, true, false);
        Food munchKorean10 = new Food("Chicken Ramen", "Munch", R.drawable.munchmap, 4.50, 560, "Hot Instant Noodles with marinated chicken", R.drawable.store, true, true, false);
        Food munchKorean11 = new Food("Beef Ramen", "Munch", R.drawable.munchmap, 5.30, 570, "Hot Instant Noodles with marinated beef", R.drawable.store, true, true, false);
        Food munchKorean12 = new Food("Sundubu", "Munch", R.drawable.munchmap, 4.00, 460, "Spicy tofu stew", R.drawable.store, false, true, true);
        Food munchKorean13 = new Food("Kimchi Jjigae", "Munch", R.drawable.munchmap, 4.00, 500, "Kimchi jjigae is a flavorful Korean stew made with fermented kimchi, tofu or pork, and a variety of vegetables, resulting in a spicy and tangy dish that warms both the palate and the soul.", R.drawable.store, false, true, true);
        Food munchKorean14 = new Food("Bulgogi Jungol", "Munch", R.drawable.munchmap, 5.00, 530, "Korean beef stew - non spicy", R.drawable.store, false, true, true);
        Food munchKorean15 = new Food("Kimchi Fried Rice", "Munch", R.drawable.munchmap, 3.8, 470, "Kimchi fried rice is a savory Korean dish that combines fried rice with spicy and tangy kimchi, creating a flavorful and satisfying meal.", R.drawable.store, false, true, true);
        Food munchKorean16 = new Food("Chicken Fried Rice", "Munch", R.drawable.munchmap, 4.20, 460, "Chicken fried rice infused with korean flavours", R.drawable.store, false, true, true);
        Food munchKorean17 = new Food("beef Fried Rice", "Munch", R.drawable.munchmap, 4.40, 550, "Beef fried rice infused with korean flavours", R.drawable.store, false, true, true);
        Food munchKorean18 = new Food("Fried Chicken with Omurice", "Munch", R.drawable.munchmap, 4.40, 550, "Fluffy omelette filled with fried rice, and comes with flavourful and crispy chicken ", R.drawable.store, false, true, true);
        Food munchKorean19 = new Food("Fried Fish with Omurice", "Munch", R.drawable.munchmap, 4.40, 556, "Fluffy omelette filled with fried rice, and comes with flavourful and crispy chicken ", R.drawable.store, false, true, true);
        Food munchKorean20 = new Food("Fried Prawn with Omurice", "Munch", R.drawable.munchmap, 4.40, 610, "Fluffy omelette filled with fried rice, and comes with flavourful and crispy prawn ", R.drawable.store, false, true, true);
        Food munchKorean21 = new Food("Fried scallop with Omurice", "Munch", R.drawable.munchmap, 4.40, 600, "Fluffy omelette filled with fried rice, and comes with flavourful and crispy scallop ", R.drawable.store, false, true, true);
        Food munchKorean22 = new Food("Omurice curry rice", "Munch", R.drawable.munchmap, 3.00, 450, "Fluffy omelette filled with fried rice, and comes with delicious curry sauce ", R.drawable.store, false, true, true);
        Food munchKorean23 = new Food("Kimchi Udon", "Munch", R.drawable.munchmap, 4.50, 460, "Kimchi soup based with udon noodles", R.drawable.store, true, true, false);
        Food munchKorean24 = new Food("Chicken Cutlet Mayo and Omelette curry rice", "Munch", R.drawable.munchmap, 4.50, 460, "Fluffy omelette filled with fried rice, and comes with delicious curry sauce and a piece of chicken cutlet", R.drawable.store, false, true, true);

        //FOODCLUB
        Food chickenrice1 = new Food("Roasted Chicken Rice Set", "Food Club", R.drawable.foodclubmap,4.2, 669, "A healthy set of chicken rice that comes with vegetable!", R.drawable.chickenrice, false, true, true);
        Food chickenrice2 = new Food("Roasted Chicken Rice", "Food Club", R.drawable.foodclubmap,3, 500, "Very flavourful plate of roasted chicken rice! Do choose the type of meat you want!", R.drawable.chickenrice, false, true, true);
        Food chickenrice3 = new Food("Steamed Chicken Rice", "Food Club", R.drawable.foodclubmap,3, 500, "Very flavourful plate of steamed chicken rice! Do choose the type of meat you want!", R.drawable.chickenrice, false, true, true);
        Food chickenrice4 = new Food("Lemon Chicken Rice", "Food Club", R.drawable.foodclubmap,3, 596, "Crispy chicken cutlet drizzled with sweet and sour sauce!", R.drawable.chickenrice, false, true, true);
        Food chickenrice5 = new Food("Chicken Cutlet Noodle", "Food Club", R.drawable.foodclubmap,3, 500, "A plate of seasoned noodles topped with crispy chicken cutlet!", R.drawable.chickenrice, true, true, false);
        Food chickenrice6 = new Food("Roasted Chicken Noodle", "Food Club", R.drawable.foodclubmap,3, 475, "A plate of seasoned noodles, top with roasted chicken!", R.drawable.chickenrice, true, true, false);


        Food pasta1 = new Food("Aglio Olio", "Food Club", R.drawable.foodclubmap,2.5, 300, "A plate of flavourful pasta. Do add on more ingredients to make it healthier meal!", R.drawable.chickenrice, true, false, false);
        Food pasta2 = new Food("Tomato pasta", "Food Club", R.drawable.foodclubmap,2.5, 325, "A plate of tomato pasta that is slightly tangy. Do add on more ingredients to make it healthier meal!", R.drawable.chickenrice, true, false, false);
        Food pasta3 = new Food("Cream pasta", "Food Club", R.drawable.foodclubmap,2.5, 375, "A plate of creamy pasta, with every strand of noodle clung with the creamy sauce. Do add on more ingredients to make it healthier meal! ", R.drawable.chickenrice, true, false, false);
        Food pasta4 = new Food("Tom yum pasta", "Food Club", R.drawable.foodclubmap,2.5, 350, "A plate of pasta that has a spicy and sour taste. Do add on more ingredients to make it healthier meal!", R.drawable.chickenrice, true, false, false);
        Food pasta5 = new Food("Vegetable: corn", "Food Club", R.drawable.foodclubmap,0.5, 30, "Wide varieties of Vegetable for you to choose", R.drawable.chickenrice, false, false, false);
        Food pasta6 = new Food("Meat: Chicken Breast", "Food Club", R.drawable.foodclubmap,1, 50, "Wide varieties of meat options for you to choose", R.drawable.chickenrice, false, false, false);
        Food pasta7 = new Food("Premium: Sliced Smoked Duck", "Food Club", R.drawable.foodclubmap,1.5, 60, "Wide varieties of other meat options for you to choose", R.drawable.chickenrice, false, false, false);

        Food saladbar1 = new Food("Low Calorie Salad Bowl", "Food CLub", R.drawable.foodclubmap, 3.5, 350, "Greens + 3 Normal Sides",R.drawable.chickenrice, false,false,false);
        Food saladbar2 = new Food("Signature Salad Bowl", "Food CLub", R.drawable.foodclubmap, 4.2, 400, "Greens + 2 Normal Sides + 1 Premium Side",R.drawable.chickenrice, false,false,false);
        Food saladbar3 = new Food("Fill-me-up Salad Bowl", "Food CLub", R.drawable.foodclubmap, 4.5, 476, "Greens + 1 Normal Side + 2 Premium Sides",R.drawable.chickenrice, false,false,false);
        Food saladbar4 = new Food("Party Salad Bowl", "Food CLub", R.drawable.foodclubmap, 8, 700, "Greens + 5 Normal Sides + 3 Premium Sides",R.drawable.chickenrice, false,false,false);


        Food kkFriedRice1 = new Food("Egg Fried Rice", "Food Club", R.drawable.foodclubmap,2.5, 250, "Signature fried rice flavour!", R.drawable.chickenrice, false,false,true);
        Food kkFriedRice2 = new Food("Sambal Fried Rice", "Food Club", R.drawable.foodclubmap,3, 313, "Signature sambal fried rice that is sweet and spicy!", R.drawable.chickenrice, false,false,true);
        Food kkFriedRice3 = new Food("Tom Yum Fried Rice", "Food Club", R.drawable.foodclubmap,3, 320, "Signature tom yum fried rice that is spicy and sour!", R.drawable.chickenrice, false,false,true);
        Food kkFriedRice4 = new Food("Meat: Chicken Cutlet", "Food Club", R.drawable.foodclubmap,1.5, 123, "Additional ingredients to make the fried rice extra flavourful", R.drawable.chickenrice, false,false,false);
        Food kkFriedRice5 = new Food("Add-Ons: Cabbage", "Food Club", R.drawable.foodclubmap,0.8, 50, "Additional ingredients to make the meal healthier!", R.drawable.chickenrice, false,false,false);


        Food Indonesian1 = new Food("Ayam Penyet Set", "Food CLub", R.drawable.foodclubmap, 4.50, 650, "Signature! With a gigantic piece of drumstick, drenched in curry!", R.drawable.chickenrice, false, false, true);
        Food Indonesian2 = new Food("Udang Penyet Set", "Food CLub", R.drawable.foodclubmap, 4.50, 502, "Signature! With prawn pieces, drenched in curry!", R.drawable.chickenrice, false, false, true);
        Food Indonesian3 = new Food("Ikan Dory Set", "Food CLub", R.drawable.foodclubmap, 4.40, 657, "Huge portion of fish, drenched in curry!", R.drawable.chickenrice, false, false, true);
        Food Indonesian4 = new Food("Otah Penyet Set", "Food CLub", R.drawable.foodclubmap, 4.50, 650, "Favourite fried otah, drenched in curry!", R.drawable.chickenrice, false, false, true);
        Food Indonesian5 = new Food("Mee Siam Set", "Food CLub", R.drawable.foodclubmap, 4.50, 571, "Bee hoon with a unique sweet and sour gravy", R.drawable.chickenrice, true, true, false);


        Food creamyDuck1 = new Food("Waffles", "Food Club", R.drawable.foodclubmap, 1.8, 200, "Multiple variations of the waffle filling to choose from!", R.drawable.chickenrice, false, false, false);
        Food creamyDuck2 = new Food("Corn butter salt", "Food Club", R.drawable.foodclubmap, 1.7, 150, "Steamed corn mixed with butter and salt, very delicious", R.drawable.chickenrice, false, false, false);
        Food creamyDuck3 = new Food("Corn butter choc", "Food Club", R.drawable.foodclubmap, 1.9, 173, "Steamed corn mixed with butter and chocolate, sweet and savoury", R.drawable.chickenrice, false, false, false);
        Food creamyDuck4 = new Food("Takoyaki", "Food Club", R.drawable.foodclubmap, 2.8, 350, "Multiple variations of the Takoyaki fillings to choose from! Either choose prawn, ham, octopus or crabstick!", R.drawable.chickenrice, false, false, false);
        Food creamyDuck5 = new Food("Ice Cream", "Food Club", R.drawable.foodclubmap, 1.8, 230, "Comes in different sizes, choose either 6oz or 12oz single scoop", R.drawable.chickenrice, false, false, false);


        Food coffeeClub1 = new Food("Breakfast Set A", "Food Club", R.drawable.foodclubmap, 2, 450, "Comes with 2 toasted bread + 2 half boiled eggs and 1 hot beverage", R.drawable.chickenrice, false, false, false);
        Food coffeeClub2 = new Food("Breakfast Set B", "Food Club", R.drawable.foodclubmap, 2.20, 435, "Comes with 1 Apollo bread + 2 half boiled eggs and 1 hot beverage", R.drawable.chickenrice, false, false, false);
        Food coffeeClub3 = new Food("Breakfast Set C", "Food Club", R.drawable.foodclubmap, 2.40, 440, "Comes with 1 thick bread + 2 half boiled eggs and 1 hot beverage", R.drawable.chickenrice, false, false, false);
        Food coffeeClub4 = new Food("Breakfast Set D", "Food Club", R.drawable.foodclubmap, 2.40, 463, "Comes with 4 cream crackers + 2 half boiled eggs and 1 hot beverage", R.drawable.chickenrice, false, false, false);

        //MAKAN PLACE
        Food MPKoreanFusion1 = new Food("Hotplate Saba Fish", "Makan Place", R.drawable.makanplacemap, 5.30, 530, "Grilled saba fish that comes in a set with korean rice and a bowl of soup!", R.drawable.store, false, true, true);
        Food MPKoreanFusion2 = new Food("Hotplate Chicken Fish", "Makan Place", R.drawable.makanplacemap, 5.00, 546, "Korean spicy marinated chicken that comes in a set with korean rice and a bowl of soup!", R.drawable.store, false, true, true);
        Food MPKoreanFusion3 = new Food("Beef Ramen", "Makan Place", R.drawable.makanplacemap, 5.30, 530, "Korean ramen with marinated and tender beef!", R.drawable.store, true, true, false);
        Food MPKoreanFusion4 = new Food("Bibimbap (Chicken)", "Makan Place", R.drawable.makanplacemap, 4.40, 450, "Korean rice with various vegetables and a spoonful of gochujang sauce!", R.drawable.store, false, true, true);
        Food MPKoreanFusion5 = new Food("Hotplate Chicken + Saba Fish", "Makan Place", R.drawable.makanplacemap, 6.30, 530, "Grilled saba fish and spicy marinated chicken that comes in a set with korean rice and a bowl of soup!", R.drawable.store, false, true, true);


        Food MPWestern1 = new Food("Chicken Chop", "Makan Place", R.drawable.makanplacemap, 5, 430, "Grilled chicken with BBQ sauce and it comes with 2 sides!", R.drawable.store, false, false, false);
        Food MPWestern2 = new Food("Fish Rice Set", "Makan Place", R.drawable.makanplacemap, 5.80, 460, "Big piece of fried fish and it comes with rice and 1 side!", R.drawable.store, false, false, true);
        Food MPWestern3 = new Food("Fish with Aglio Olio", "Makan Place", R.drawable.makanplacemap, 5.50, 560, "Big piece of fried fish and it comes with aglio olio on the side!", R.drawable.store, true, false, false);
        Food MPWestern4 = new Food("Fried chicken with tomato spaghetti", "Makan Place", R.drawable.makanplacemap, 5.5, 580, "Fried chicken with BBQ sauce and it comes with tomato spaghetti on the side!", R.drawable.store, true, false, false);
        Food MPWestern5 = new Food("Chicken Chop rice set", "Makan Place", R.drawable.makanplacemap, 5, 430, "Grilled chicken with BBQ sauce and it comes with rice and 1 side!", R.drawable.store, false, false, true);

        Food MPHotto1 = new Food("Beef Roll Hotto Rice", "Makan Place", R.drawable.makanplacemap, 5.9, 500, "Beef slices and rice on a hot plate, sizzlingly delicious", R.drawable.store, false, false, true);
        Food MPHotto2 = new Food("Salmon Hotto Rice", "Makan Place", R.drawable.makanplacemap, 6.9, 500, "Salmon and rice on a hot plate, sizzlingly delicious", R.drawable.store, false, false, true);
        Food MPHotto3 = new Food("Chicken Hotto Rice", "Makan Place", R.drawable.makanplacemap, 4.9, 500, "Chicken cubes and rice on a hot plate, sizzlingly delicious", R.drawable.store, false, false, true);
        Food MPHotto4 = new Food("Black pepper chicken burger", "Makan Place", R.drawable.makanplacemap, 3.9, 370, "Chicken patty with black pepper sauce in a burger", R.drawable.store, false, false, false);
        Food MPHotto5 = new Food("Creamy Shroom Pasta", "Makan Place", R.drawable.makanplacemap, 4.9, 510, "Creamy mushroom pasta", R.drawable.store, true, false, false);

        Food MPJap1 = new Food("Hotplate Chicken Fuyong", "Makan Place", R.drawable.makanplacemap, 4, 500, "Fried chicken with egg, drizzled with the special sweet sauce", R.drawable.store, false, true, true);
        Food MPJap2 = new Food("Chicken and pork bento", "Makan Place", R.drawable.makanplacemap, 4.50, 560, "Fried chicken and pork in a bento with rice", R.drawable.store, false, true, true);
        Food MPJap3 = new Food("Salmon Don", "Makan Place", R.drawable.makanplacemap, 4.50, 500, "Geilled salmon with store's special sauce on top of a bowl of rice", R.drawable.store, false, true, true);
        Food MPJap4 = new Food("Ebi fry Katsu Curry Rice", "Makan Place", R.drawable.makanplacemap, 4.50, 560, "Fried ebi with japanese curry rice", R.drawable.store, false, true, true);
        Food MPJap5 = new Food("Pork Shabu shabu curry udon", "Makan Place", R.drawable.makanplacemap, 4, 500, "Thin slices of pork shabu shabu with japanese udon curry", R.drawable.store, true, true, false);

        Food MPGoPizza1 = new Food("American Cheese Pizza", "Makan Place", R.drawable.makanplacemap, 6.0, 560, "Pizza topped with multiple variations of cheese", R.drawable.store, false, false, false);
        Food MPGoPizza2 = new Food("K-Bulgogi Pizza", "Makan Place", R.drawable.makanplacemap, 10.0, 600, "Pizza topped with of cheese and korean marinated beef", R.drawable.store, false, false, false);
        Food MPGoPizza3 = new Food("Original Wings", "Makan Place", R.drawable.makanplacemap, 6.80, 560, "Three pieces of original flavoured wings for 6.80! Crispy on the outside, tender & juicy on the insider!", R.drawable.store, false, false, false);
        Food MPGoPizza4 = new Food("Cheese Stick", "Makan Place", R.drawable.makanplacemap, 4.80, 516, "Cheesy cheese stick that is savoury and suitable for all ages!", R.drawable.store, false, false, false);
        Food MPGoPizza5 = new Food("Shaker Fries", "Makan Place", R.drawable.makanplacemap, 2.80, 430, "Choose either sea salt of seaweed flavour for your shaker fries", R.drawable.store, false, false, false);

        //left ban mian, yong tau foo, mala, drinks + add them into the list










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
        //foodList.add(munchWestern22);
        //foodList.add(munchWestern23);
        //foodList.add(munchWestern24);
        //foodList.add(munchWestern25);
        //foodList.add(munchWestern26);
        //foodList.add(munchWestern27);

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
        //foodList.add(munchClaypot11);
        //foodList.add(munchClaypot12);
        //foodList.add(munchClaypot13);

        foodList.add(munchNasiPadang1);
        foodList.add(munchNasiPadang2);
        foodList.add(munchNasiPadang3);

        foodList.add(munchPizza1);
        foodList.add(munchPizza2);
        foodList.add(munchPizza3);
        foodList.add(munchPizza4);
        foodList.add(munchPizza5);
        foodList.add(munchPizza6);
        foodList.add(munchPizza7);
        foodList.add(munchPizza8);
        foodList.add(munchPizza9);

        foodList.add(munchKorean1);
        foodList.add(munchKorean2);
        foodList.add(munchKorean3);
        foodList.add(munchKorean4);
        foodList.add(munchKorean5);
        foodList.add(munchKorean6);
        foodList.add(munchKorean7);
        foodList.add(munchKorean8);
        foodList.add(munchKorean9);
        foodList.add(munchKorean10);
        foodList.add(munchKorean11);
        foodList.add(munchKorean12);
        foodList.add(munchKorean13);
        foodList.add(munchKorean14);
        foodList.add(munchKorean15);
        foodList.add(munchKorean16);
        foodList.add(munchKorean17);
        foodList.add(munchKorean18);
        foodList.add(munchKorean19);
        foodList.add(munchKorean20);
        foodList.add(munchKorean21);
        foodList.add(munchKorean22);
        foodList.add(munchKorean23);
        foodList.add(munchKorean24);


        foodList.add(chickenrice1);
        foodList.add(chickenrice2);
        foodList.add(chickenrice3);
        foodList.add(chickenrice4);
        foodList.add(chickenrice5);
        foodList.add(chickenrice6);

        foodList.add(pasta1);
        foodList.add(pasta2);
        foodList.add(pasta3);
        foodList.add(pasta4);
        foodList.add(pasta5);
        foodList.add(pasta6);
        foodList.add(pasta7);

        foodList.add(saladbar1);
        foodList.add(saladbar2);
        foodList.add(saladbar3);
        foodList.add(saladbar4);

        foodList.add(kkFriedRice1);
        foodList.add(kkFriedRice2);
        foodList.add(kkFriedRice3);
        foodList.add(kkFriedRice4);
        foodList.add(kkFriedRice5);

        foodList.add(Indonesian1);
        foodList.add(Indonesian2);
        foodList.add(Indonesian3);
        foodList.add(Indonesian4);
        foodList.add(Indonesian5);

        foodList.add(creamyDuck1);
        foodList.add(creamyDuck2);
        foodList.add(creamyDuck3);
        foodList.add(creamyDuck4);
        foodList.add(creamyDuck5);

        foodList.add(coffeeClub1);
        foodList.add(coffeeClub2);
        foodList.add(coffeeClub3);
        foodList.add(coffeeClub4);
        return foodList;
    }

}