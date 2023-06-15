package sg.edu.np.mad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;


import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GeneralViewPage extends AppCompatActivity {

    private SearchView searchView;
    private RecyclerView recyclerView;
    ArrayList<ArrayList> storeList;
    ArrayList<Store> FoodClubstoreList = new ArrayList<>();
    ArrayList<Store> MakanstoreList = new ArrayList<>();
    ArrayList<Store> MunchstoreList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_view_page);
        recyclerView=findViewById(R.id.recyclerView);
        searchView=findViewById(R.id.searchView);
        searchView.clearFocus();
        /*searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        })*/

        Store store1 = new Store("Chicken Rice", "Food Club", "Sells Chicken Rice", R.drawable.chickenrice);
        Store store2 = new Store("Korean Cuisine", "Munch", "Sells korean food", R.drawable.chickenrice);
        Store store3 = new Store("Japanese Cuisine", "Makan Place", "Sells Japanese food", R.drawable.chickenrice);

        FoodClubstoreList.add(store1);
        MakanstoreList.add(store2);
        MunchstoreList.add(store3);
        /*storeList.add(FoodClubstoreList);
        storeList.add(MakanstoreList);
        storeList.add(MunchstoreList);*/

        for (Store s: MakanstoreList) { //accessing the objects in the list
            if (s.getLocation() == "Makan Place"){
                RecyclerView GeneralView_recyclerView = findViewById(R.id.recyclerView);
                GeneralView_Adapter gAdapter = new GeneralView_Adapter(MakanstoreList);
                LinearLayoutManager gLayoutManager = new LinearLayoutManager(this);
                GeneralView_recyclerView.setLayoutManager(gLayoutManager);
                GeneralView_recyclerView.setItemAnimator(new DefaultItemAnimator());
                GeneralView_recyclerView.setAdapter(gAdapter);
            }
        }



        /*private void filterList(String text) {
            List<Store> filteredList = new ArrayList<>();
            for (Store store : storeList) {
                if (store.getStoreName().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(store);

                }
            }
            if (filteredList.isEmpty()) {
                Toast.makeText(this,"No data found",Toast.LENGTH_SHORT).show();
            }
            else {
                gAdapter.setFilteredList(filteredList);
            }

        }*/

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

}