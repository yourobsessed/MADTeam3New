package sg.edu.np.mad;

import static sg.edu.np.mad.R.id.drawer_layout;
import static sg.edu.np.mad.R.id.nav_HomeButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity{//} implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        //initNavigationDrawer();

        String Username = getIntent().getStringExtra("Username");


        TextView UsernameText = findViewById(R.id.UsernameText);
        UsernameText.setText("Hello " + Username + ",");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference DatabaseRef = database.getReference();

        TextView MapButton = findViewById(R.id.MapButton);
        MapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openMap = new Intent(HomePage.this, MapPage.class);
                startActivity(openMap);
            }
        });
        TextView CrowdButton = findViewById(R.id.crowdbutton);
        CrowdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openCrowd = new Intent(HomePage.this, MainPage.class);
                startActivity(openCrowd);
            }
        });


        List<CrowdReview> CrowdReviewsList = new ArrayList<>();
        getCrowd(CrowdReviewsList, CrowdButton, MapButton);


        Toolbar toolbar = drawer.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

    }
    private void handleNavigationDrawerItemClick(int itemId) {
        switch (itemId) {
            case R.id.nav_HomeButton:
                Intent toHomePage = new Intent(HomePage.this, HomePage.class);
                startActivity(toHomePage);
                break;
            case R.id.nav_foodbank:
                // Handle item 2 selection
                Intent toGeneralviewpage = new Intent(HomePage.this, GeneralViewPage.class);
                startActivity(toGeneralviewpage);
                break;
            // Add more cases for other items
        }
    }
    /*@Override
    public void setContentView(View view){
        drawer = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_home_page, null);

        Toolbar toolbar = drawer.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }*/


    /*public boolean onNavigationItemSelected(@NonNull MenuItem item){

        drawer.closeDrawer(GravityCompat.START);
        switch(item.getItemId()){
            case R.id.nav_HomeButton:
                Intent toHomePage = new Intent(HomePage.this, HomePage.class);
                startActivity(toHomePage);
                overridePendingTransition(0,0);
                break;
            case R.id.nav_foodbank:
                Intent toGeneralviewpage = new Intent(HomePage.this, GeneralViewPage.class);
                startActivity(toGeneralviewpage);
                overridePendingTransition(0,0);
                break;
            case R.id.nav_NotificationButton:
                Intent toNotification = new Intent(HomePage.this, NotificationViewPage.class);
                startActivity(toNotification);
                overridePendingTransition(0,0);
                break;
            case R.id.nav_WishlistButton:
                Intent toWishlist = new Intent(HomePage.this, WishlistPage.class);
                startActivity(toWishlist);
                overridePendingTransition(0,0);
                break;
            case R.id.nav_reviewButton:
                Intent toReview = new Intent(HomePage.this, ReviewPage.class);
                startActivity(toReview);
                overridePendingTransition(0,0);
                break;
            case R.id.nav_profileButton:
                Intent toProfile = new Intent(HomePage.this, Profile.class);
                startActivity(toProfile);
                overridePendingTransition(0,0);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    protected void allocatedActivityTitle(String titleString){
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(titleString);
        }
    }
    @Override
    public void onBackPressed(){
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }*/



    /*public void initNavigationDrawer(){
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                switch (id){
                    case nav_HomeButton:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment().commit)
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

                    default:
                        break;
                }
                return true;
            }
        });


    }*/



        /*switch(item.getItemId()){
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
    }
    */

    @Override
    public void onResume() {
        super.onResume();
        TextView MapButton = findViewById(R.id.MapButton);
        TextView CrowdButton = findViewById(R.id.button);
        List<CrowdReview> CrowdReviewsList = new ArrayList<>();
        getCrowd(CrowdReviewsList, CrowdButton, MapButton);

    }


    public void getCrowd(List CrowdReviewsList, TextView txt, TextView txt2) {
        CrowdReviewsList.clear();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference DatabaseRef = database.getReference();
        DatabaseRef.child("Crowdedness").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot reviewSnapshot : snapshot.getChildren()) {
                    CrowdReview crowdReview = reviewSnapshot.getValue(CrowdReview.class);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
                    LocalDateTime ReviewDate = LocalDateTime.parse(crowdReview.time, formatter);

                    if (Duration.between(LocalDateTime.now(), ReviewDate).abs().toHours() < 1) {
                        CrowdReviewsList.add(crowdReview);

                    } else {
                        reviewSnapshot.getRef().removeValue();
                    }
                }

                //Toast.makeText(getApplicationContext(), ""+ CrowdReviewsList.size() , Toast.LENGTH_SHORT).show();
                findleastcrowded(CrowdReviewsList, txt, txt2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void findleastcrowded(List<CrowdReview> list, TextView txt, TextView txt2){

        TextView FCText = findViewById(R.id.FCText);
        TextView MPText = findViewById(R.id.MPText);
        TextView MText = findViewById(R.id.MText);

        int FCCrowd = 0;
        int MPCrowd = 0;
        int MCrowd = 0;

        List<Integer> FCCrowdlist = new ArrayList<>();
        List<Integer> MPCrowdlist = new ArrayList<>();
        List<Integer> MCrowdlist = new ArrayList<>();

        //Toast.makeText(getApplicationContext(), "" + list.size(), Toast.LENGTH_SHORT).show();

        for (CrowdReview crowdReview : list) {

            if(crowdReview.foodcourt.equals("Food Club")){
                FCCrowdlist.add(crowdReview.crowd);
            }
            else if(crowdReview.foodcourt.equals("Makan Place")){
                MPCrowdlist.add(crowdReview.crowd);
            }
            else if(crowdReview.foodcourt.equals("Munch")){
                MCrowdlist.add(crowdReview.crowd);
            }

        }

        FCCrowd = calculateAverage(FCCrowdlist);
        MPCrowd = calculateAverage(MPCrowdlist);
        MCrowd = calculateAverage(MCrowdlist);

        if(FCCrowd == 150 ||  MPCrowd == 150 || MCrowd == 150){
            txt.setText("Click to view crowdedness of each food court");
            txt2.setText("Food Courts Map");
        }
        else{
            if(FCCrowd < MPCrowd){
                if(FCCrowd < MCrowd){
                    txt.setText("Food Club is currently the least crowded.\nClick to view crowdedness of each food court");
                    txt2.setText("Map to get to\nFood Club");
                }

            }
            else if(MPCrowd < MCrowd){
                if(MPCrowd < FCCrowd){
                    txt.setText("Makan Place is currently the least crowded.\nClick to view crowdedness of each food court");
                    txt2.setText("Map to get to\nMakan Place");
                }
            }
            else{
                txt.setText("Munch is currently the least crowded.\nClick to view crowdedness of each food court");
                txt2.setText("Map to get to\nMunch");
            }

        }


    }



    public static int calculateAverage(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return 150;
        }

        int sum = 0;
        for (int num : list) {
            sum += num;
        }

        return (int) sum / list.size();
    }
}


