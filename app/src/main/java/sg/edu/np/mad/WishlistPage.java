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

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class WishlistPage extends AppCompatActivity implements SelectListenerFood{

    ArrayList<Food> foodlist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist_page);

        RecyclerView WLrecyclerView = findViewById(R.id.wishlist_RV);
        WishList_Adapter mAdapter = new WishList_Adapter(foodlist);
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