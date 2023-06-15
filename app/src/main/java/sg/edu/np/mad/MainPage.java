package sg.edu.np.mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.Nullable;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


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

        ArrayList<CrowdReview> CrowdReviewsList = new ArrayList<>();
        DatabaseRef.child("Crowdedness").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot reviewSnapshot : snapshot.getChildren()) {
                    CrowdReview crowdReview = reviewSnapshot.getValue(CrowdReview.class);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
                    //Toast.makeText(getApplicationContext(), "" + crowdReview.time, Toast.LENGTH_SHORT).show();
                    LocalDateTime ReviewDate = LocalDateTime.parse(crowdReview.time, formatter);
                    if (Duration.between(LocalDateTime.now(), ReviewDate).abs().toHours() <= 1) {
                        CrowdReviewsList.add(crowdReview);
                    } else {
                        reviewSnapshot.getRef().removeValue();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

        ProgressBar FCBar = findViewById(R.id.FCBar);
        ProgressBar MPBar = findViewById(R.id.MPBar);
        ProgressBar MBar = findViewById(R.id.MBar);

        updateBar("");







        ImageView WishlistButton = findViewById(R.id.WishlistButton);
        WishlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent OpenWishList = new Intent(MainPage.this, WishlistPage.class);
                startActivity(OpenWishList);
            }
        });

        ImageView CatalogueButton = findViewById(R.id.CatalogueButton);
        CatalogueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent OpenCatalogue = new Intent(MainPage.this, StoreViewPage.class);
                startActivity(OpenCatalogue);
            }
        });

        ImageView NotificationButton = findViewById(R.id.NotificationButton);
        NotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    private void updateBar(String foodCourt){

    }

}