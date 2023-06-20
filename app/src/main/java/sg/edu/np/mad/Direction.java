package sg.edu.np.mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class Direction extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawer;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        Toolbar toolbar = drawer.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        //Log.i(title, "drawer added");
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
        //System.out.println("Helllllllllllllllllooooo");
        if (menuItem.getItemId() == R.id.nav_HomeButton) {

            //Log.i(title, "HomeButton pressed");

            Intent newIntent = new Intent(Direction.this, HomePage.class);
            startActivity(newIntent);

        } else if (menuItem.getItemId() == R.id.nav_foodbank) {
            //Log.i(title, "FoodBank pressed");
            Intent newIntent = new Intent(Direction.this, GeneralViewPage.class);
            startActivity(newIntent);
        } else if (menuItem.getItemId() == R.id.nav_NotificationButton) {
            Intent toNotificationpage = new Intent(Direction.this, NotificationViewPage.class);
            startActivity(toNotificationpage);

        } else if (menuItem.getItemId() == R.id.nav_WishlistButton) {
            Intent toWishlistpage = new Intent(Direction.this, WishlistPage.class);
            startActivity(toWishlistpage);

        } else if (menuItem.getItemId() == R.id.nav_reviewButton) {
            Intent toReviewPage = new Intent(Direction.this, ReviewPage.class);
            startActivity(toReviewPage);

        } else if (menuItem.getItemId() == R.id.nav_directionButton) {
            Intent todirectionPage = new Intent(Direction.this, Direction.class);
            startActivity(todirectionPage);

        } else if (menuItem.getItemId() == R.id.nav_profileButton) {
            Intent toProfilePage = new Intent(Direction.this, Profile.class);
            startActivity(toProfilePage);

        } else if (menuItem.getItemId() == R.id.nav_aboutusbutton) {
            Intent toAboutUs = new Intent(Direction.this, Infomation.class);
            startActivity(toAboutUs);

        } else if (menuItem.getItemId() == R.id.nav_feedbackbutton) {
            Intent toFeedbackPage = new Intent(Direction.this, Feedback.class);
            startActivity(toFeedbackPage);
        }
        drawer.closeDrawer(GravityCompat.START);

        return true;
    };
}