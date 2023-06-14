package sg.edu.np.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDateTime;


public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference DatabaseRef = database.getReference();

        Spinner FCSpinner = findViewById(R.id.FCSpinner);
        Spinner CrowdSpinner = findViewById(R.id.CrowdSpinner);
        Button SendButton = findViewById(R.id.SendCrowdButton);

        SendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int crowd = 0;
                if(CrowdSpinner.getSelectedItem().toString().equals("Full")) {
                    crowd = 10;
                }
                else if(CrowdSpinner.getSelectedItem().toString().equals("Almost Full")) {
                    crowd = 8;
                }
                else if(CrowdSpinner.getSelectedItem().toString().equals("Not Crowded")) {
                    crowd = 5;
                }
                else if(CrowdSpinner.getSelectedItem().toString().equals("Almost Empty")) {
                    crowd = 2;
                }
                else if(CrowdSpinner.getSelectedItem().toString().equals("Empty")) {
                    crowd = 0;
                }
                DatabaseRef.child("Crowdedness").push().setValue(new CrowdReview(FCSpinner.getSelectedItem().toString(), crowd, (LocalDateTime.now()).toString()));
            }
        });




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