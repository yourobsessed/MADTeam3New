package sg.edu.np.mad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class GeneralViewPage extends AppCompatActivity {

    SearchView searchView;
    RecyclerView recyclerView;
    ArrayList<Store> storeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_view_page);
        recyclerView=findViewById(R.id.recyclerView);
        searchView=findViewById(R.id.searchView);
    }

    @Override
    protected void onStart(){
        super.onStart();
    }
}