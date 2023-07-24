package sg.edu.np.mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WishlistPage extends AppCompatActivity implements SelectListenerFood{

    SwipeRefreshLayout swipeRefreshLayout;



    ArrayList<Food> receivedList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist_page);

        swipeRefreshLayout = findViewById(R.id.swiperefresh);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference accountsRef = database.getReference("Accounts");

                accountsRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot Snapshot : snapshot.getChildren()) {
                            Account acc = Snapshot.getValue(Account.class);
                            String usernameToSearch = acc.username;
                            DatabaseReference userWishList = accountsRef.child(usernameToSearch).child("wishlist");
                            Log.i("Account", String.valueOf(acc.wishlist));
                            Log.i("Account Details", String.valueOf(acc));
                            userWishList.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for (DataSnapshot WLSnapshot : snapshot.getChildren()) {
                                        if (WLSnapshot != null) {
                                            int foodIndex = WLSnapshot.getValue(Integer.class);
                                            Log.i("Checking foodIndex", String.valueOf(foodIndex));
                                            for (Food f : DataHolder.food_List) {
                                                if (f.getFoodIndex() == foodIndex) {
                                                    receivedList.add(f);
                                                    f.setAddedWishlist(true);
                                                    //Toast.makeText(WishlistPage.this, "Food added to the wishlist!", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                //Toast.makeText(WishlistPage.this,"Food removed from the wishlist!", Toast.LENGTH_SHORT).show();
                RecyclerView WLrecyclerView = findViewById(R.id.wishlist_RV);
                WishList_Adapter mAdapter = new WishList_Adapter(WishlistPage.this, receivedList);
                LinearLayoutManager mLayoutManger = new LinearLayoutManager(WishlistPage.this);
                WLrecyclerView.setLayoutManager(mLayoutManger);
                WLrecyclerView.setItemAnimator(new DefaultItemAnimator());
                WLrecyclerView.setAdapter(mAdapter);
                Log.i("HELLLLLLLLLOOOOOO!!!", "HELLLLLLL");
                swipeRefreshLayout.setRefreshing(false);
            }

        });

        ImageView BackButton = findViewById(R.id.backButton);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });





    }
    @Override
    protected void onStart(){
        super.onStart();

    }

    @Override
    protected void onResume(){
        super.onResume();
        swipeRefreshLayout = findViewById(R.id.swiperefresh);


        RecyclerView WLrecyclerView = findViewById(R.id.wishlist_RV);
        WishList_Adapter mAdapter = new WishList_Adapter(this, receivedList);
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