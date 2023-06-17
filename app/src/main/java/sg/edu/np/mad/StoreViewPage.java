package sg.edu.np.mad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class StoreViewPage extends AppCompatActivity {
    ArrayList<Store> storeList = new ArrayList<Store>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_view_page);

        Store munch1 = new Store("Mala", "Munch", "Get your favourite mala combination here with the huge varieties of food!", true, false);

        storeList.add(munch1);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        StoreAdapter adapter = new StoreAdapter(storeList); //need to create foodList
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