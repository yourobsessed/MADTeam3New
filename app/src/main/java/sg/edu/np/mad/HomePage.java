package sg.edu.np.mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class HomePage extends AppCompatActivity{//} implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        String Username = getIntent().getStringExtra("Username");


        TextView UsernameText = findViewById(R.id.UsernameText);
        UsernameText.setText("Hello " + Username + ",");

        Button MapButton = findViewById(R.id.MapButton);
        MapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openMap = new Intent(HomePage.this, MapPage.class);
                startActivity(openMap);
            }
        });
        Button CrowdButton = findViewById(R.id.button);
        CrowdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openCrowd = new Intent(HomePage.this, MainPage.class);
                startActivity(openCrowd);
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    /*@Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_HomeButton:
                Intent toHomePage = new Intent(HomePage.this, HomePage.class);
                startActivity(toHomePage);
                break;
            case R.id.nav_foodbank:
                Intent toGeneralviewpage = new Intent(HomePage.this, GeneralViewPage.class);
                startActivity(toGeneralviewpage);
                break;
            case R.id.nav_NotificationButton:
                Intent toNotification = new Intent(HomePage.this, NotificationViewPage.class);
                startActivity(toNotification);
                break;
            case R.id.nav_WishlistButton:
                Intent toWishlist = new Intent(HomePage.this, WishlistPage.class);
                startActivity(toWishlist);
                break;
            case R.id.nav_reviewButton:
                Intent toReview = new Intent(HomePage.this, ReviewPage.class);
                startActivity(toReview);
                break;
            case R.id.nav_profileButton:
                Intent toProfile = new Intent(HomePage.this, Profile.class);
                startActivity(toProfile);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/

    @Override
    public void onBackPressed(){
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }
}