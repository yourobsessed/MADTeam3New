package sg.edu.np.mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.Nullable;

import java.lang.ref.Reference;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


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

        Button RefreshButton = findViewById(R.id.RefreshButton);

        SendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int crowd = 0;
                if(CrowdSpinner.getSelectedItem().toString().equals("Full")) {
                    crowd = 100;
                }
                else if(CrowdSpinner.getSelectedItem().toString().equals("Almost Full")) {
                    crowd = 80;
                }
                else if(CrowdSpinner.getSelectedItem().toString().equals("Not Crowded")) {
                    crowd = 50;
                }
                else if(CrowdSpinner.getSelectedItem().toString().equals("Almost Empty")) {
                    crowd = 20;
                }
                else if(CrowdSpinner.getSelectedItem().toString().equals("Empty")) {
                    crowd = 0;
                }
                DatabaseRef.child("Crowdedness").push().setValue(new CrowdReview(FCSpinner.getSelectedItem().toString(), crowd, (LocalDateTime.now()).toString()));
            }
        });

        List<CrowdReview> CrowdReviewsList = new ArrayList<>();
        getCrowd(CrowdReviewsList);
        RefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCrowd(CrowdReviewsList);
            }
        });



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
                Intent OpenCatalogue = new Intent(MainPage.this, FoodCourtViewPage.class);
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
    public void getCrowd(List CrowdReviewsList) {
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
                updateBar(CrowdReviewsList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
    }

    public void updateBar(List<CrowdReview> list){
        ProgressBar FCBar = findViewById(R.id.FCBar);
        ProgressBar MPBar = findViewById(R.id.MPBar);
        ProgressBar MBar = findViewById(R.id.MBar);

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
        FCBar.setProgress(FCCrowd);
        MPBar.setProgress(MPCrowd);
        MBar.setProgress(MCrowd);

        changeBarColor(FCBar, FCText, calculateAverage(FCCrowdlist));
        changeBarColor(MPBar, MPText, calculateAverage(MPCrowdlist));
        changeBarColor(MBar, MText, calculateAverage(MCrowdlist));
    }

    public void changeBarColor(ProgressBar bar, TextView text, int value){
        if(value >= 101) {
            bar.getProgressDrawable().setColorFilter(Color.rgb(100, 100, 200), PorterDuff.Mode.SRC_IN);
            text.setText("Not Enough Data");
        }
        else if(value >= 80) {
            bar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
            text.setText("Full");
        }
        else if(value >= 50) {
            bar.getProgressDrawable().setColorFilter(Color.rgb(255, 165, 0), PorterDuff.Mode.SRC_IN);
            text.setText("Almost Full");
        }
        else if(value >= 20) {
            bar.getProgressDrawable().setColorFilter(Color.rgb(200, 200, 0), PorterDuff.Mode.SRC_IN);
            text.setText("Not Crowded");
        }
        else if(value >= 0) {
            bar.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
            text.setText("Empty");
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

