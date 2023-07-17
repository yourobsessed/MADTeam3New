package sg.edu.np.mad;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    NavigationView navigationView;
    String title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        //initNavigationDrawer();

        String Username = getIntent().getStringExtra("Username");


        TextView UsernameText = findViewById(R.id.UsernameText);
        UsernameText.setText("Hello " + Username + ",");

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", Username);
        editor.apply();

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

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        Toolbar toolbar = drawer.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        Log.i(title, "drawer added");
        toggle.syncState();


        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
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
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.nav_foodbank){
            Log.i(title, "FoodBank pressed");
            Intent newIntent = new Intent(HomePage.this, GeneralViewPage.class);
            startActivity(newIntent);
        }
        else if (menuItem.getItemId() == R.id.nav_NotificationButton) {
            Intent toNotificationpage = new Intent(HomePage.this, NotificationViewPage.class);
            startActivity(toNotificationpage);

        } else if (menuItem.getItemId() == R.id.nav_WishlistButton) {
            Intent toWishlistpage = new Intent(HomePage.this, WishlistPage.class);
            startActivity(toWishlistpage);

        } else if (menuItem.getItemId() == R.id.nav_directionButton) {
            Intent todirectionPage = new Intent(HomePage.this, MapPage.class);
            startActivity(todirectionPage);
        } else if (menuItem.getItemId() == R.id.nav_crowdbutton) {
            Intent todirectionPage = new Intent(HomePage.this, MainPage.class);
            startActivity(todirectionPage);

        } else if (menuItem.getItemId() == R.id.nav_profileButton) {
            Intent toProfilePage = new Intent(HomePage.this, Profile.class);
            startActivity(toProfilePage);

        } else if (menuItem.getItemId() == R.id.nav_aboutusbutton) {
            Intent toAboutUs = new Intent(HomePage.this, Infomation.class);
            startActivity(toAboutUs);

        } else if (menuItem.getItemId() == R.id.nav_feedbackbutton) {
            Intent toFeedbackPage = new Intent(HomePage.this, Feedback.class);
            startActivity(toFeedbackPage);
        }
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }


    @Override
    public void onResume() {
        super.onResume();
        TextView MapButton = findViewById(R.id.MapButton);
        TextView CrowdButton = findViewById(R.id.crowdbutton);
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


