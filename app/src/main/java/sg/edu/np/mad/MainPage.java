package sg.edu.np.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDateTime;


public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);



        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference DatabaseRef = database.getReference();

        DatabaseRef.child("Crowdedness").push().setValue(new CrowdReview("FoodClub", 5, LocalDateTime.now()));



        ImageView WishlistButton = findViewById(R.id.WishlistButton);
        WishlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to execute when the button is clicked
                Intent OpenWishList = new Intent(MainPage.this, WishlistPage.class);
                startActivity(OpenWishList);
            }
        });

        ImageView CatalogueButton = findViewById(R.id.CatalogueButton);
        CatalogueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to execute when the button is clicked
                Intent OpenCatalogue = new Intent(MainPage.this, StoreViewPage.class);
                startActivity(OpenCatalogue);
            }
        });

        ImageView NotificationButton = findViewById(R.id.NotificationButton);
        NotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to execute when the button is clicked
                Intent OpenNotification = new Intent(MainPage.this, MainPage.class);
                startActivity(OpenNotification);
                //to be implemented in stage 2
            }
        });

        ImageView sidePanelButton = findViewById(R.id.SidePanelButtonMain);
        sidePanelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toSidePanel = new Intent(MainPage.this, PanelPage.class);
                startActivity(toSidePanel);
            }
        });
    }
    @Override
    public void onStart(){
        super.onStart();
    }

}