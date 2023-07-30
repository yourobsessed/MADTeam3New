package sg.edu.np.mad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AllReviewsPage extends AppCompatActivity {

    TextView text;
    ArrayList<FoodReview> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_reviews_page);

        text = findViewById(R.id.txt1);
        text.setText(getIntent().getExtras().getString("foodname"));

        ImageView backButton = findViewById(R.id.imageView11);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TextView writereviewbutton = findViewById(R.id.writereviewbutton);
        writereviewbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent toAFR = new Intent(AllReviewsPage.this, FoodReviewPage.class);
                toAFR.putExtra("foodname",getIntent().getExtras().getString("foodname"));
                startActivity(toAFR);

            }
        });




    }

    @Override
    protected void onResume() {
        super.onResume();
        itemList.clear();
        DatabaseReference reviewsRef = FirebaseDatabase.getInstance().getReference("Reviews");
        ValueEventListener reviewsListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemList.clear();
                int numofreviews = 0;
                int totalstars = 0;
                float avgstars = 0;
                for (DataSnapshot reviewSnapshot : dataSnapshot.getChildren()) {
                    FoodReview foodReview = reviewSnapshot.getValue(FoodReview.class);
                    //check if the current review is the same food dish
                    if (foodReview != null && foodReview.FoodName.equals(text.getText().toString())) {
                        numofreviews++;
                        totalstars += foodReview.Rating;
                        itemList.add(foodReview);
                    }
                }
                //set the text to display rating and number of reviews
                if(numofreviews != 0){
                    avgstars = totalstars/numofreviews;
                    TextView text = findViewById(R.id.txt);
                    text.setText("" + new DecimalFormat("#.0").format(avgstars/20));
                    RatingBar stars = findViewById(R.id.ratingBar);
                    stars.setRating(avgstars/20);
                    TextView numofrev = findViewById(R.id.txt2);
                    numofrev.setText("Based on " + numofreviews + " reviews");
                    if(avgstars == 0){
                        text.setText("0.0");
                    }

                }
                //pass list to adapter to display on recycler view
                Collections.reverse(itemList);
                RecyclerView recyclerView;
                FoodReviewAdapter itemAdapter;

                recyclerView = findViewById(R.id.recyclerview);
                recyclerView.setLayoutManager(new LinearLayoutManager(AllReviewsPage.this));

                itemAdapter = new FoodReviewAdapter(itemList, AllReviewsPage.this);
                recyclerView.setAdapter(itemAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };

        reviewsRef.addListenerForSingleValueEvent(reviewsListener);

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedOption = spinner.getSelectedItem().toString();
                if (selectedOption.equals("Sort by Date")) {                                    //sort by date
                    reviewsRef.addListenerForSingleValueEvent(reviewsListener);

                } else if (selectedOption.equals("Sort by Highest")) {
                    Collections.sort(itemList, new Comparator<FoodReview>() {
                        @Override
                        public int compare(FoodReview review1, FoodReview review2) {                // sort by highest
                            return Integer.compare(review2.Rating, review1.Rating);
                        }
                    });
                    RecyclerView recyclerView;
                    FoodReviewAdapter itemAdapter;

                    recyclerView = findViewById(R.id.recyclerview);
                    recyclerView.setLayoutManager(new LinearLayoutManager(AllReviewsPage.this));

                    itemAdapter = new FoodReviewAdapter(itemList, AllReviewsPage.this);
                    recyclerView.setAdapter(itemAdapter);

                } else if (selectedOption.equals("Sort by Lowest")) {
                    Collections.sort(itemList, new Comparator<FoodReview>() {
                        @Override                                                                      //sort by lowest
                        public int compare(FoodReview review1, FoodReview review2) {
                            return Integer.compare(review1.Rating, review2.Rating);
                        }
                    });
                    RecyclerView recyclerView;
                    FoodReviewAdapter itemAdapter;

                    recyclerView = findViewById(R.id.recyclerview);
                    recyclerView.setLayoutManager(new LinearLayoutManager(AllReviewsPage.this));

                    itemAdapter = new FoodReviewAdapter(itemList, AllReviewsPage.this);
                    recyclerView.setAdapter(itemAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }
}