package sg.edu.np.mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class WishlistPage extends AppCompatActivity implements SelectListenerFood{
    ArrayList<Food> receivedList = DataHolder.wishlist_List;
    SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist_page);

        /*swipeRefreshLayout.findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Toast.makeText(WishlistPage.this,"Food removed from the wishlist!", Toast.LENGTH_SHORT).show();
                RecyclerView WLrecyclerView = findViewById(R.id.wishlist_RV);
                WishList_Adapter mAdapter = new WishList_Adapter(WishlistPage.this, receivedList, this);
                LinearLayoutManager mLayoutManger = new LinearLayoutManager(WishlistPage.this);
                WLrecyclerView.setLayoutManager(mLayoutManger);
                WLrecyclerView.setItemAnimator(new DefaultItemAnimator());
                WLrecyclerView.setAdapter(mAdapter);
                System.out.println("HELLLLLLLLLOOOOOO!!!");
                swipeRefreshLayout.setRefreshing(false);
            }

        });*/



        RecyclerView WLrecyclerView = findViewById(R.id.wishlist_RV);
        WishList_Adapter mAdapter = new WishList_Adapter(this, receivedList, this);
        LinearLayoutManager mLayoutManger = new LinearLayoutManager(this);
        WLrecyclerView.setLayoutManager(mLayoutManger);
        WLrecyclerView.setItemAnimator(new DefaultItemAnimator());
        WLrecyclerView.setAdapter(mAdapter);


    }

    @Override
    public void onItemClicked(Food food) {
        Intent toCataloguePage = new Intent(WishlistPage.this, CataloguePage.class);
        startActivity(toCataloguePage);
    }
}