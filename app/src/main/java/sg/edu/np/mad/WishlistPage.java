package sg.edu.np.mad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class WishlistPage extends AppCompatActivity implements SelectListenerFood{
    ArrayList<Food> receivedList = DataHolder.wishlist_List;
    SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist_page);

        swipeRefreshLayout = findViewById(R.id.swiperefresh);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Toast.makeText(WishlistPage.this,"Food removed from the wishlist!", Toast.LENGTH_SHORT).show();
                RecyclerView WLrecyclerView = findViewById(R.id.wishlist_RV);
                WishList_Adapter mAdapter = new WishList_Adapter(WishlistPage.this, receivedList);
                LinearLayoutManager mLayoutManger = new LinearLayoutManager(WishlistPage.this);
                WLrecyclerView.setLayoutManager(mLayoutManger);
                WLrecyclerView.setItemAnimator(new DefaultItemAnimator());
                WLrecyclerView.setAdapter(mAdapter);
                Log.i("HELLLLLLLLLOOOOOO!!!", "HELLLLLLL");
                swipeRefreshLayout.setRefreshing(false);
            }

        });

        ImageView BackButton = findViewById(R.id.backButton);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });



        /*RecyclerView WLrecyclerView = findViewById(R.id.wishlist_RV);
        WishList_Adapter mAdapter = new WishList_Adapter(this, receivedList, this);
        LinearLayoutManager mLayoutManger = new LinearLayoutManager(this);
        WLrecyclerView.setLayoutManager(mLayoutManger);
        WLrecyclerView.setItemAnimator(new DefaultItemAnimator());
        WLrecyclerView.setAdapter(mAdapter);*/


    }

    @Override
    public void onItemClicked(Food food) {
        Intent toCataloguePage = new Intent(WishlistPage.this, CataloguePage.class);
        startActivity(toCataloguePage);
    }
}