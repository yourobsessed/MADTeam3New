package sg.edu.np.mad;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawer;
    NavigationView navigationView;
    String title;
    ImageView wlImage;
    ImageView wlImage2;
    ImageView wlImage3;
    ImageView wlImage4;
    ImageView wlImage5;
    TextView wlName;
    TextView wlName2;
    TextView wlName3;
    TextView wlName4;
    TextView wlName5;
    int count;

    public Food selectedFood;
    ArrayList<Food> foodList = new ArrayList<>();
    ArrayList<Food> receivedList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        foodList = CreateObject(receivedList);


        String Username = getIntent().getStringExtra("Username");

        TextView UsernameText = findViewById(R.id.UsernameText);
        UsernameText.setText("Hi, " + Username + " \uD83D\uDC4B\uD83C\uDFFB");

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

        TextView randomButton = findViewById(R.id.randombutton);
        randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openRandomizer = new Intent(HomePage.this, RandomizerPage.class);
                startActivity(openRandomizer);
            }
        });

        List<CrowdReview> CrowdReviewsList = new ArrayList<>();
        getCrowd(CrowdReviewsList, CrowdButton, MapButton);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        Toolbar toolbar = drawer.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.baseline_notes_24);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        getSupportActionBar().setTitle("");

        drawer.addDrawerListener(toggle);
        Log.i(title, "drawer added");
        toggle.syncState();


        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }
//
        //gets top 3 foods and displays them based on reviews
        DatabaseRef.child("Reviews").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<FoodReview> reviewsList = new ArrayList<>();

                HashMap<String, Pair<Integer, Integer> > foodRatings = new HashMap<>();

                //for every review, add to a hashmap, which contains food name, total ratings, and number of ratings
                for (DataSnapshot reviewSnapshot : snapshot.getChildren()) {
                    FoodReview foodReview = reviewSnapshot.getValue(FoodReview.class);
                    reviewsList.add(foodReview);

                    if (foodRatings.containsKey(foodReview.FoodName)) {
                        Pair<Integer, Integer> ratingsPair = foodRatings.get(foodReview.FoodName);
                        int totalRatings = ratingsPair.first + foodReview.Rating;
                        int count = ratingsPair.second + 1;
                        foodRatings.put(foodReview.FoodName, new Pair<>(totalRatings, count));
                    } else {
                        foodRatings.put(foodReview.FoodName, new Pair<>(foodReview.Rating, 1));
                    }
                }
                //gets the average rating of each food then puts into a hashmap
                HashMap<String, Double> averageRatings = new HashMap<>();
                for (Map.Entry<String, Pair<Integer, Integer>> entry : foodRatings.entrySet()) {
                    String foodName = entry.getKey();
                    Pair<Integer, Integer> ratingsPair = entry.getValue();
                    double averageRating = (double) ratingsPair.first / ratingsPair.second;
                    averageRatings.put(foodName, averageRating);
                }

                //sorts the hashmap so that we can get the top 3
                List<Map.Entry<String, Double>> sortedRatings = new ArrayList<>(averageRatings.entrySet());
                Collections.sort(sortedRatings, (entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

                List<Map.Entry<String, Double>> top3Dishes = sortedRatings.subList(0, Math.min(3, sortedRatings.size()));

                for (int i = 0; i < top3Dishes.size(); i++) {
                    Map.Entry<String, Double> entry = top3Dishes.get(i);
                    String foodName = entry.getKey();
                    double averageRating = entry.getValue();
                    Log.d("Top3Dishes", (i + 1) + ". Food: " + foodName + ", Average Rating: " + averageRating/20);
                }
                TextView top1 = findViewById(R.id.top1food);
                TextView top2 = findViewById(R.id.top2food);
                TextView top3 = findViewById(R.id.top3food);
                ImageView top1pic = findViewById(R.id.top1pic);
                ImageView top2pic = findViewById(R.id.top2pic);
                ImageView top3pic = findViewById(R.id.top3pic);


                top1.setText(top3Dishes.get(0).getKey() + "\n" + top3Dishes.get(0).getValue()/20 + " Stars");
                for (Food dish : foodList){
                    if(dish.foodName.equals(top3Dishes.get(0).getKey())){
                        top1pic.setImageResource(dish.foodImage2);
                        break;
                    }
                }
                top2.setText(top3Dishes.get(1).getKey() + "\n" + top3Dishes.get(1).getValue()/20 + " Stars");
                for (Food dish : foodList){
                    if(dish.foodName.equals(top3Dishes.get(1).getKey())){
                        top2pic.setImageResource(dish.foodImage2);
                        break;
                    }
                }
                top3.setText(top3Dishes.get(2).getKey() + "\n" + top3Dishes.get(2).getValue()/20 + " Stars");
                for (Food dish : foodList){
                    if(dish.foodName.equals(top3Dishes.get(2).getKey())){
                        top3pic.setImageResource(dish.foodImage2);
                        break;
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


    }
    @Override
    public void onResume() {
        super.onResume();
        TextView wishlistButton = findViewById(R.id.wl);
        wlImage = findViewById(R.id.image);
        wlImage2 = findViewById(R.id.image2);
        wlImage3 = findViewById(R.id.image3);
        wlName = findViewById(R.id.name);
        wlName2 = findViewById(R.id.name2);
        wlName3 = findViewById(R.id.name3);
        TextView wlName4 = findViewById(R.id.name4);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference accountsRef = database.getReference("Accounts").child(DataHolder.username);
        accountsRef.addListenerForSingleValueEvent(new ValueEventListener() { //reading the data's wishlist
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               Account acc = snapshot.getValue(Account.class);
               DataHolder.wishlist_List = acc.wishlist;
               //Toast.makeText(getApplicationContext(), acc.wishlist.toString() , Toast.LENGTH_SHORT).show();
               /*
               if (DataHolder.wishlist_List.size() == 0){
                   //horizontalScrollView.setVisibility(View.GONE);
                   //wlImage.setVisibility(View.GONE);
               }
               else{
                   for (Food food : foodList){
                       if (count <= 3) {
                           Random random = new Random();
                           int position = random.nextInt(DataHolder.wishlist_List.size());
                           if (DataHolder.wishlist_List.get(position) == (food.getFoodIndex())){
                               count = 1;
                               wlImage.setImageResource(food.getFoodImage2());
                               wlName.setText(food.getFoodName());
                           }
                           if (DataHolder.wishlist_List.get(position) == (food.getFoodIndex())){
                               count = 2;
                               wlImage2.setImageResource(food.getFoodImage2());
                               wlName2.setText(food.getFoodName());
                           }
                           if (DataHolder.wishlist_List.get(position) == (food.getFoodIndex())){
                               count = 3;
                               wlImage3.setImageResource(food.getFoodImage2());
                               wlName3.setText(food.getFoodName());
                           }
                       }
                   }
               }*/
               if(DataHolder.wishlist_List.size() >= 2) {
                   for (Food food : foodList) {
                       if (food.foodIndex == DataHolder.wishlist_List.get(1)) {
                           wlImage.setImageResource(food.foodImage2);
                           wlName.setText(food.getFoodName());
                       }
                   }
               }
               if(DataHolder.wishlist_List.size() >= 3) {
                   for (Food food : foodList) {
                       if (food.foodIndex == DataHolder.wishlist_List.get(2)) {
                           wlImage2.setImageResource(food.foodImage2);
                           wlName2.setText(food.getFoodName());
                       }
                   }
               }
               if(DataHolder.wishlist_List.size() >= 4) {
                   for (Food food : foodList) {
                       if (food.foodIndex == DataHolder.wishlist_List.get(3)) {
                           wlImage3.setImageResource(food.foodImage2);
                           wlName3.setText(food.getFoodName());
                       }
                   }
               }

               if(DataHolder.wishlist_List.size() == 1) {
                   wlImage.setImageResource(0);
                   wlName.setText("");
                   wlImage2.setImageResource(0);
                   wlName2.setText("");
                   wlName4.setText("Try adding some food dishes into your Wishlist!");
                   wlImage3.setImageResource(0);
                   wlName3.setText("");
               }
               if(DataHolder.wishlist_List.size() == 2) {
                   wlImage2.setImageResource(0);
                   wlName2.setText("");
                   wlName4.setText("");
                   wlImage3.setImageResource(0);
                   wlName3.setText("");
               }
               if(DataHolder.wishlist_List.size() == 3) {
                   wlName4.setText("");
                   wlImage3.setImageResource(0);
                   wlName3.setText("");
               }
               if(DataHolder.wishlist_List.size() == 4) {
                   wlName4.setText("");
               }

           }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        TextView MapButton = findViewById(R.id.MapButton);
        TextView CrowdButton = findViewById(R.id.crowdbutton);
        List<CrowdReview> CrowdReviewsList = new ArrayList<>();
        getCrowd(CrowdReviewsList, CrowdButton, MapButton);

        TextView UsernameText = findViewById(R.id.UsernameText);
        UsernameText.setText("Hi, " + DataHolder.username + " \uD83D\uDC4B\uD83C\uDFFB");
        HorizontalScrollView horizontalScrollView = findViewById(R.id.horizontalScrollView2);




    }

    @Override
    public void onPause(){
        super.onPause();

    }

    public void onDestroy(){
        super.onDestroy();

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
            Intent toProfilePage = new Intent(HomePage.this, ProfilePage.class);
            startActivity(toProfilePage);

        } else if (menuItem.getItemId() == R.id.nav_aboutusbutton) {
            Intent toAboutUs = new Intent(HomePage.this, AboutUs.class);
            startActivity(toAboutUs);

        } else if (menuItem.getItemId() == R.id.nav_infobutton) {
            Intent toFeedbackPage = new Intent(HomePage.this, Information.class);
            startActivity(toFeedbackPage);

        } else if (menuItem.getItemId() == R.id.nav_randomizer) {
            Intent toRandomizerPage = new Intent(HomePage.this, RandomizerPage.class);
            startActivity(toRandomizerPage);

        } else if (menuItem.getItemId() == R.id.nav_logoutbutton) {

            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("RememberMe", false);
            editor.apply();

            Intent toLogin = new Intent(HomePage.this, LoginPage.class);
            startActivity(toLogin);
        }
        drawer.closeDrawer(GravityCompat.START);

        return true;
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

        ImageView imageView = findViewById(R.id.imageView2);

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
            txt.setText(Html.fromHtml( "<b>Food Courts</b><br/><br/>Click to view crowdedness\nof each food court"));
            imageView.setImageResource(R.drawable.fcc3);
            imageView.setPadding(0,0,0,0);
        }
        else{
            if(FCCrowd < MPCrowd){
                if(FCCrowd < MCrowd){
                    txt.setText(Html.fromHtml( "<b>Food Club</b><br/><br/>Food Club is currently the least crowded."));
                    imageView.setImageResource(R.drawable.fc);
                    imageView.setPadding(0,0,60,0);
                    txt2.setText("Directions to Food Club");
                }

            }
            else if(MPCrowd < MCrowd){
                if(MPCrowd < FCCrowd){
                    txt.setText(Html.fromHtml( "<b>Makan Place</b><br/><br/>Makan Place is currently the least crowded."));
                    imageView.setImageResource(R.drawable.makan_place);
                    imageView.setPadding(0,0,60,0);
                    txt2.setText("Directions to Makan Place");
                }
            }
            else{
                //txt.setText("Food Courts\n\nMunch is currently the least crowded.");
                txt.setText(Html.fromHtml( "<b>Munch</b><br/><br/>Munch is currently the least crowded."));
                imageView.setImageResource(R.drawable.munch);
                imageView.setPadding(0,0,0,0);
                txt2.setText("Directions to Munch");
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


    public ArrayList<Food> CreateObject(ArrayList<Food> foodList){
        //creating all the food items
        //Munch
        Food munchSaladBowlL = new Food(1, "Large bowl Salad", "Munch",R.drawable.munchmap, 9.5, 645, "Suitable for 2 to 4 pax. Available for dine-in Only!", R.drawable.saladbowl, true, false, true, false, true, false);
        Food munchSaladBowlR = new Food(2, "Regular bowl Salad", "Munch",R.drawable.munchmap ,4, 400, "Filling amount for one person", R.drawable.saladbowl, true, false, true, false, true, false);
        munchSaladBowlR.setFoodImage1(R.drawable.munchsalad);
        munchSaladBowlL.setFoodImage1(R.drawable.munchsalad);


        Food munchWestern1 = new Food(3, "Ribeye Steak", "Munch", R.drawable.munchmap,8.3, 567, "Delicious Ribeye steak with 2 sides", R.drawable.ribeye, true, false, true, true, false, false);
        Food munchWestern2 = new Food(4, "Mixed Grilled", "Munch",R.drawable.munchmap, 9.8, 650, "Multiple Varieties of grills with 2 sides", R.drawable.mixgrill, false, false, false,true, false, false);
        Food munchWestern3 = new Food(5, "Grilled Salmon", "Munch",R.drawable.munchmap, 8.5, 250, "Grilled Salmon and it comes with 2 sides!", R.drawable.grilledfish,false, false, false,true, false, false);
        Food munchWestern4 = new Food(6, "Chicken Chop", "Munch",R.drawable.munchmap, 5, 480, "Delicious chicken chop with 2 sides", R.drawable.chickenchop, false, false, true,true, false, false);
        Food munchWestern5 = new Food(7, "Chicken Teriyaki", "Munch", R.drawable.munchmap,5, 490, "Delicious chicken chop with Teriyaki sauce and 2 sides", R.drawable.chickenchop, false, false, false,true, false, false);
        Food munchWestern6 = new Food(8, "Chicken Cutlet", "Munch", R.drawable.munchmap,5, 490, "Fried chicken cutlet with 2 sides!", R.drawable.chickencutlet,false, false, false,true, false, false);
        Food munchWestern7 = new Food(9, "Mexican Chicken Chop", "Munch",R.drawable.munchmap,6, 550, "Mexican Style Chicken Chop with 2 sides", R.drawable.chickenchop, false, false, false,true, false, false);
        Food munchWestern8 = new Food(10, "Fish & Chips", "Munch",R.drawable.munchmap, 4.8, 480, "Delicious Fried Fish pieces with 2 sides", R.drawable.fishchips, false, false, false,true, false, false);
        Food munchWestern9 = new Food(11, "Grilled Dory Fish", "Munch",R.drawable.munchmap, 4.8, 250, "Grilled Dory Fish and it comes with 2 sides!", R.drawable.grilledfish,false, false, false,true, false, false);
        Food munchWestern10 = new Food(12, "Sausage Combo", "Munch",R.drawable.munchmap, 4.5, 490, "Delicious chicken chop with 2 sides", R.drawable.sausage, false, false, false,true, false, false);
        Food munchWestern11 = new Food(13, "Spaghetti With Grilled Dory Fish", "Munch",R.drawable.munchmap, 5.5, 500, "Tomato Spaghetti with grilled dory fish", R.drawable.tomatopasta, true, false, false,true, false, false);
        Food munchWestern12 = new Food(14, "Spaghetti With Breaded Fish", "Munch", R.drawable.munchmap,5.5, 550, "Tomato Spaghetti with fried fish", R.drawable.chickenpasta,true, false, false,true, false, false);
        Food munchWestern13 = new Food(15, "Spaghetti with sausage", "Munch",R.drawable.munchmap,4.5, 550, "Tomato Spaghetti with sausages", R.drawable.chickenpasta, true, false, false,true, false, false);
        Food munchWestern14 = new Food(16, "Spaghetti with chicken chop", "Munch", R.drawable.munchmap,5.5, 480, "Tomato Spaghetti with chicken chop", R.drawable.chickenpasta, true, false, false,true, false, false);
        Food munchWestern15 = new Food(17, "Spaghetti with chicken cutlet", "Munch",R.drawable.munchmap, 5.5, 480, "Tomato Spaghetti with fried chicken cutlet", R.drawable.chickenpasta,true, false, false,true, false, false);
        Food munchWestern16 = new Food(18, "Aglio Olio chicken chop", "Munch", R.drawable.munchmap,5.5, 490, "Delicious Aglio Olio with chicken chop", R.drawable.chickenagliooliio, true, false, false,true, false, false);
        Food munchWestern17 = new Food(19, "Aglio Olio chicken cutlet", "Munch", R.drawable.munchmap,5.5, 550, "Delicious Aglio Olio with fried chicken cutlet ", R.drawable.chickenagliooliio, true, false, false,true, false, false);
        Food munchWestern18 = new Food(20, "Aglio Olio grilled dory fish", "Munch",R.drawable.munchmap, 5.5, 500, "Delicious Aglio Olio with grilled dory fish", R.drawable.chickenagliooliio,true, false, false,true, false, false);
        Food munchWestern19 = new Food(21, "Aglio Olio Breaded Fish", "Munch",R.drawable.munchmap,5.5, 550, "Delicious Aglio Olio with  breaded fish", R.drawable.chickenagliooliio, true, false, false,true, false, false);
        Food munchWestern20 = new Food(22, "Spring chicken (Half)", "Munch",R.drawable.munchmap, 5.1, 550, "Half of whole fried chicken", R.drawable.springchicken, false, false, false,true, false, false);
        Food munchWestern21 = new Food(23, "Chicken Wings (Min 2 Pcs)", "Munch", R.drawable.munchmap,1.3, 350, "Savoury fried chicken wings", R.drawable.chickenwings,false, false, false,true, false, false);
        //Food munchWestern22 = new Food("Add-Ons: Coleslaw", "Munch", R.drawable.munchmap,1, 120, "Favourful bowl of vegetables", R.drawable.chickenrice, false, false, false);
        //Food munchWestern23 = new Food("Add-Ons: French Fries", "Munch", R.drawable.munchmap,1.5, 300, "Big bowl of salty fries that you can share with friends", R.drawable.chickenrice, false, false, false);
        //Food munchWestern24 = new Food("Add-Ons: Cheesy French Fries", "Munch",R.drawable.munchmap, 2.8, 400, "Salty fries with cheesy cheese sauce", R.drawable.chickenrice,false, false, false);
        //Food munchWestern25 = new Food("Add-Ons: Mushroom Soup", "Munch",R.drawable.munchmap, 2, 250, "Favourful bowl of mushroom soup", R.drawable.chickenrice, false, true, false);
        //Food munchWestern26 = new Food("Add-Ons: Sunshine Egg", "Munch", R.drawable.munchmap,0.8, 65, "A sunshine egg with flowy egg yolk", R.drawable.chickenrice, false, false, false);
        //Food munchWestern27 = new Food("Add-Ons: Nacho Cheese", "Munch",R.drawable.munchmap, 1, 50, "Cheesy nacho cheese", R.drawable.chickenrice,false, false, false);

        munchWestern1.setFoodImage1(R.drawable.munchwestern);
        munchWestern2.setFoodImage1(R.drawable.munchwestern);
        munchWestern3.setFoodImage1(R.drawable.munchwestern);
        munchWestern4.setFoodImage1(R.drawable.munchwestern);
        munchWestern4.setFoodImage1(R.drawable.munchwestern);
        munchWestern5.setFoodImage1(R.drawable.munchwestern);
        munchWestern6.setFoodImage1(R.drawable.munchwestern);
        munchWestern7.setFoodImage1(R.drawable.munchwestern);
        munchWestern8.setFoodImage1(R.drawable.munchwestern);
        munchWestern9.setFoodImage1(R.drawable.munchwestern);
        munchWestern10.setFoodImage1(R.drawable.munchwestern);
        munchWestern11.setFoodImage1(R.drawable.munchwestern);
        munchWestern12.setFoodImage1(R.drawable.munchwestern);
        munchWestern13.setFoodImage1(R.drawable.munchwestern);
        munchWestern14.setFoodImage1(R.drawable.munchwestern);
        munchWestern15.setFoodImage1(R.drawable.munchwestern);
        munchWestern16.setFoodImage1(R.drawable.munchwestern);
        munchWestern17.setFoodImage1(R.drawable.munchwestern);
        munchWestern18.setFoodImage1(R.drawable.munchwestern);
        munchWestern19.setFoodImage1(R.drawable.munchwestern);
        munchWestern20.setFoodImage1(R.drawable.munchwestern);
        munchWestern21.setFoodImage1(R.drawable.munchwestern);



        Food munchClaypot1 = new Food(24, "Magma Beef/Chicken", "Munch", R.drawable.munchmap, 6, 550, "A savory stew with a lot of ingredients", R.drawable.munchclaypotfood, false, true, true, true, false, false);
        Food munchClaypot2 = new Food(25, "Magma Seafood", "Munch", R.drawable.munchmap, 6.5, 550, "A savory stew with a lot of seafood", R.drawable.munchclaypotfood, true, true, true, true, false, false);
        Food munchClaypot3 = new Food(26, "Paitan Scallop Beef/Chicken", "Munch", R.drawable.munchmap, 6, 550, "A savory stew with a lot of ingredients", R.drawable.munchclaypotfood, true, true, true, true, false, false);
        Food munchClaypot4 = new Food(27, "Paitan Scallop Seafood", "Munch", R.drawable.munchmap, 6.5, 550, "A savory stew with a lot of seafood", R.drawable.munchclaypotfood, true, true, true, true, false, false);
        Food munchClaypot5 = new Food(28, "Tomyum Beef/Chicken", "Munch", R.drawable.munchmap, 5.5, 550, "A savory stew with a lot of ingredients", R.drawable.munchclaypotfood, true, true, true, true, false, false);
        Food munchClaypot6 = new Food(29, "Tomyum Scallop Seafood", "Munch", R.drawable.munchmap, 6, 550, "A savory stew with a lot of seafood", R.drawable.munchclaypotfood, true, true, true, true, false, false);
        Food munchClaypot7 = new Food(30, "Nonya Curry Rendang Chicken Cutlet", "Munch", R.drawable.munchmap, 5.5, 550, "A Nonya style curry with chicken cutlet", R.drawable.nonyacurry, true, true, true, true, false, false);
        Food munchClaypot8 = new Food(31, "Nonya Curry Rendang Beef", "Munch", R.drawable.munchmap, 5.5, 550, "A Nonya style curry with beef", R.drawable.nonyacurry, true, true, true, true, false, false);
        Food munchClaypot9 = new Food(32, "Yaki Cheesey Tamago", "Munch", R.drawable.munchmap, 5.5, 550, "Cheesy Egg roll", R.drawable.cheeseytamago, false, false, false, true, false, false);
        Food munchClaypot10 = new Food(33, "Fried Takoyaki (6 Pcs)", "Munch", R.drawable.munchmap, 5.5, 550, "Takoyaki balls with savory ingredients", R.drawable.takoyaki, false, false, false, true, false, false);
        //Food munchClaypot11 = new Food("Add-Ons: Japanese Rice", "Munch",R.drawable.munchmap, 0.8, 100, "Fresh, steaming hot bowl of rice", R.drawable.mala, false, false, true);
        //Food munchClaypot12 = new Food("Add-Ons: Egg", "Munch", R.drawable.munchmap,0.8, 65, "A sunny side up egg", R.drawable.mala, false, false, false);
        //Food munchClaypot13 = new Food("Add-Ons: Chicken Cutlet", "Munch", R.drawable.munchmap,2.5, 550, "Fried chicken cutlet", R.drawable.mala, false, false, false);

        munchClaypot1.setFoodImage1(R.drawable.munchclaypot);
        munchClaypot2.setFoodImage1(R.drawable.munchclaypot);
        munchClaypot3.setFoodImage1(R.drawable.munchclaypot);
        munchClaypot4.setFoodImage1(R.drawable.munchclaypot);
        munchClaypot5.setFoodImage1(R.drawable.munchclaypot);
        munchClaypot6.setFoodImage1(R.drawable.munchclaypot);
        munchClaypot7.setFoodImage1(R.drawable.munchclaypot);
        munchClaypot8.setFoodImage1(R.drawable.munchclaypot);
        munchClaypot9.setFoodImage1(R.drawable.munchclaypot);
        munchClaypot10.setFoodImage1(R.drawable.munchclaypot);

        Food munchNasiPadang1 = new Food(34, "Ayam Penyet", "Munch", R.drawable.munchmap, 4.5, 550, "Indonesian fried chicken with chili sauce", R.drawable.ayampenyet, false, false, true, true, false, false);
        Food munchNasiPadang2 = new Food(35, "Ayam Penyet Sabolado", "Munch", R.drawable.munchmap, 4.5, 600, "Indonesian fried chicken with special chili sauce", R.drawable.ayampenyet, false, false, true, true, false, false);
        Food munchNasiPadang3 = new Food(36, "Grilled chicken with sweet and spicy sauce", "Munch", R.drawable.munchmap, 4.5, 550, "Sweet and savory taste of grilled chicken", R.drawable.nasipadang, false, false, true, true, false, false);

        munchNasiPadang1.setFoodImage1(R.drawable.munchnasipadang);
        munchNasiPadang2.setFoodImage1(R.drawable.munchnasipadang);
        munchNasiPadang3.setFoodImage1(R.drawable.munchnasipadang);



        Food munchPizza1 = new Food(37, "Classic Pizza: Meat Madness (Beef)", "Munch", R.drawable.munchmap, 3.5, 350, "Delicious slice of pizza that is enough for one!", R.drawable.meatmadnes, false, false, false, true, false, false);
        Food munchPizza2 = new Food(38, "Classic Pizza: Aloha Hawaiian", "Munch", R.drawable.munchmap, 3.5, 357, "Delicious slice of pizza that is enough for one!", R.drawable.aloha, false, false, false, true, false, false);
        Food munchPizza3 = new Food(39, "Classic Pizza: Spicy Garlic Chicken", "Munch", R.drawable.munchmap, 3.5, 350, "Delicious slice of pizza that is enough for one!", R.drawable.spicygarlicchicken, false, false, false, true, false, false);
        Food munchPizza4 = new Food(40, "Classic Pizza: Fisherman Catch", "Munch", R.drawable.munchmap, 3.5, 350, "Delicious slice of pizza that is enough for one!", R.drawable.fishermancatch, false, false, false, true, false, false);
        Food munchPizza5 = new Food(41, "Classic Pizza: Yummy Cheese", "Munch", R.drawable.munchmap, 3.5, 350, "Delicious slice of pizza that is enough for one!", R.drawable.yummycheese, false, false, false, true, false, false);
        Food munchPizza6 = new Food(42, "Premium Pizza: Beef Pepperoni Double Wonders", "Munch", R.drawable.munchmap, 4.5, 350, "Delicious slice of pizza that is enough for one!", R.drawable.beefpepperoni, false, false, false, true, false, false);
        Food munchPizza7 = new Food(43, "Premium Pizza: Cheesy Cheese", "Munch", R.drawable.munchmap, 4.5, 460, "Delicious slice of pizza that is enough for one!", R.drawable.cheesycheesepizza, false, false, false, true, false, false);
        //Food munchPizza8 = new Food(44, "Premium Pizza: Margarita", "Munch", R.drawable.munchmap, 4.5, 430, "Delicious slice of pizza that is enough for one!", R.drawable.store, false, false, false);
        Food munchPizza9 = new Food(45, "Premium Pizza: Sambal Chicken/Prawn", "Munch", R.drawable.munchmap, 4.5, 400, "Delicious slice of pizza that is enough for one!", R.drawable.sambalchickenpizza, false, false, false, true, false, false);

        munchPizza1.setFoodImage1(R.drawable.munchpizza);
        munchPizza2.setFoodImage1(R.drawable.munchpizza);
        munchPizza3.setFoodImage1(R.drawable.munchpizza);
        munchPizza4.setFoodImage1(R.drawable.munchpizza);
        munchPizza5.setFoodImage1(R.drawable.munchpizza);
        munchPizza6.setFoodImage1(R.drawable.munchpizza);
        munchPizza7.setFoodImage1(R.drawable.munchpizza);
        munchPizza9.setFoodImage1(R.drawable.munchpizza);


        Food munchKorean1 = new Food(46, "Bibimbap", "Munch", R.drawable.munchmap, 4.40, 460, "A flavorful Korean dish consisting of a bowl of rice with various ingredients, served with spicy gochujang sauce.", R.drawable.bibimbap, false, true, true, true, false, false);
        Food munchKorean2 = new Food(47,"Hot Stone Bibimbap", "Munch", R.drawable.munchmap, 5.30, 460, "A flavorful Korean dish consisting of a bowl of rice with various ingredients,with spicy gochujang sauce.", R.drawable.bibimbap, false, true, true, true, false, false);
        //Food munchKorean3 = new Food(48,"BBQ Crisy chicken Omelette", "Munch", R.drawable.munchmap, 4.80, 530, "BBQ Crispy Omelette is a delicious combination of a fluffy omelette filled with smoky barbecue-flavored meat or vegetables.", R.drawable.store, false, true, true, true, false, false);
        Food munchKorean4 = new Food(49,"BBQ Chicken set", "Munch", R.drawable.munchmap, 5.0, 510, "Generous amount of spicy barbecue-flavoured chicken, served with crunchy beansprout and onions.", R.drawable.koreanbbq, false, true, true, true, false, false);
        Food munchKorean5 = new Food(50,"BBQ Saba Fish set", "Munch", R.drawable.munchmap, 5.30, 460, "A whole grilled Saba Fish ", R.drawable.sabafish, false, true, true, true, false, false);
        Food munchKorean6 = new Food(51,"BBQ Beef set", "Munch", R.drawable.munchmap, 5.30, 550, "Generous amount of non-spicy barbecue-flavoured beef, served with crunchy beansprout and onions", R.drawable.koreanbbq, false, true, true, true, false, false);
        Food munchKorean7 = new Food(52,"BBQ Chicken & Fish set", "Munch", R.drawable.munchmap, 6.30, 600, "Generous amount of spicy barbecue-flavoured chicken, served with crunchy beansprout and onions with a half piece of saba fish", R.drawable.chickenfishset, false, true, true, true, false, false);
        Food munchKorean8 = new Food(53,"BBQ Beef & Fish set", "Munch", R.drawable.munchmap, 6.30, 650, "Generous amount of non-spicy barbecue-flavoured beef, served with crunchy beansprout and onions with half a piece of saba fish", R.drawable.chickenfishset, false, true, true, true, false, false);
        Food munchKorean9 = new Food(54,"Korean Ramen", "Munch", R.drawable.munchmap, 2.80, 500, "Hot Instant Noodles ", R.drawable.koreanramen, true, true, false, true, false, false);
        Food munchKorean10 = new Food(55,"Chicken Ramen", "Munch", R.drawable.munchmap, 4.50, 560, "Hot Instant Noodles with marinated chicken", R.drawable.koreanramen, true, true, false, true, false, false);
        Food munchKorean11 = new Food(56,"Beef Ramen", "Munch", R.drawable.munchmap, 5.30, 570, "Hot Instant Noodles with marinated beef", R.drawable.koreanramen, true, true, false, true, false, false);
        Food munchKorean12 = new Food(57,"Sundubu", "Munch", R.drawable.munchmap, 4.00, 460, "Spicy tofu stew", R.drawable.koreanstew, false, true, true, true, false, false);
        Food munchKorean13 = new Food(58,"Kimchi Jjigae", "Munch", R.drawable.munchmap, 4.00, 500, "Kimchi Jjigae is a flavorful Korean stew made with fermented kimchi, tofu or pork, creating a spicy and tangy dish that warms the palate.", R.drawable.koreanstew, false, true, true, true, false, false);
        Food munchKorean14 = new Food(59,"Bulgogi Jungol", "Munch", R.drawable.munchmap, 5.00, 530, "Korean beef stew - non spicy", R.drawable.koreanstew, false, true, true, true, false, false);
        Food munchKorean15 = new Food(60,"Kimchi Fried Rice", "Munch", R.drawable.munchmap, 3.8, 470, "Kimchi fried rice is a savory Korean dish that combines fried rice with spicy and tangy kimchi, creating a flavorful and satisfying meal.", R.drawable.kimchifriedrice, false, true, true, true, false, false);
        Food munchKorean16 = new Food(61,"Chicken Fried Rice", "Munch", R.drawable.munchmap, 4.20, 460, "Chicken fried rice infused with korean flavours", R.drawable.kimchifriedrice, false, true, true, true, false, false);
        Food munchKorean17 = new Food(62,"Beef Fried Rice", "Munch", R.drawable.munchmap, 4.40, 550, "Beef fried rice infused with korean flavours", R.drawable.kimchifriedrice, false, true, true, true, false, false);
        Food munchKorean18 = new Food(63,"Fried Chicken with Omurice", "Munch", R.drawable.munchmap, 4.40, 550, "Fluffy omelette filled with fried rice, and comes with flavourful and crispy chicken ", R.drawable.omurice, false, true, true, true, false, false);
        Food munchKorean19 = new Food(64,"Fried Fish with Omurice", "Munch", R.drawable.munchmap, 4.40, 556, "Fluffy omelette filled with fried rice, and comes with flavourful and crispy chicken ", R.drawable.omurice, false, true, true, true, false, false);
        Food munchKorean20 = new Food(65,"Fried Prawn with Omurice", "Munch", R.drawable.munchmap, 4.40, 610, "Fluffy omelette filled with fried rice, and comes with flavourful and crispy prawn ", R.drawable.omurice, false, true, true, true, false, false);
        Food munchKorean21 = new Food(66,"Fried scallop with Omurice", "Munch", R.drawable.munchmap, 4.40, 600, "Fluffy omelette filled with fried rice, and comes with flavourful and crispy scallop ", R.drawable.omurice, false, true, true, true, false, false);
        Food munchKorean22 = new Food(67,"Omurice curry rice", "Munch", R.drawable.munchmap, 3.00, 450, "Fluffy omelette filled with fried rice, and comes with delicious curry sauce ", R.drawable.omurice, false, true, true, true, false, false);
        Food munchKorean23 = new Food(68,"Kimchi Udon", "Munch", R.drawable.munchmap, 4.50, 460, "Kimchi soup based with udon noodles", R.drawable.kimchiudon, true, true, false, true, false, false);
        Food munchKorean24 = new Food(69,"Chicken Cutlet Mayo and Omelette curry rice", "Munch", R.drawable.munchmap, 4.50, 460, "Fluffy omelette filled with fried rice, and comes with delicious curry sauce and a piece of chicken cutlet", R.drawable.omurice, false, true, true, true, false, false);


        munchKorean1.setFoodImage1(R.drawable.munchkorean);
        munchKorean2.setFoodImage1(R.drawable.munchkorean);
        //munchKorean3.setFoodImage1(R.drawable.munchkorean);
        munchKorean4.setFoodImage1(R.drawable.munchkorean);
        munchKorean5.setFoodImage1(R.drawable.munchkorean);
        munchKorean6.setFoodImage1(R.drawable.munchkorean);
        munchKorean7.setFoodImage1(R.drawable.munchkorean);
        munchKorean8.setFoodImage1(R.drawable.munchkorean);
        munchKorean9.setFoodImage1(R.drawable.munchkorean);
        munchKorean10.setFoodImage1(R.drawable.munchkorean);
        munchKorean11.setFoodImage1(R.drawable.munchkorean);
        munchKorean12.setFoodImage1(R.drawable.munchkorean);
        munchKorean13.setFoodImage1(R.drawable.munchkorean);
        munchKorean14.setFoodImage1(R.drawable.munchkorean);
        munchKorean15.setFoodImage1(R.drawable.munchkorean);
        munchKorean16.setFoodImage1(R.drawable.munchkorean);
        munchKorean17.setFoodImage1(R.drawable.munchkorean);
        munchKorean18.setFoodImage1(R.drawable.munchkorean);
        munchKorean19.setFoodImage1(R.drawable.munchkorean);
        munchKorean20.setFoodImage1(R.drawable.munchkorean);
        munchKorean21.setFoodImage1(R.drawable.munchkorean);
        munchKorean22.setFoodImage1(R.drawable.munchkorean);
        munchKorean23.setFoodImage1(R.drawable.munchkorean);
        munchKorean24.setFoodImage1(R.drawable.munchkorean);



        //FOODCLUB
        Food chickenrice1 = new Food(70, "Roasted Chicken Rice Set", "Food Club", R.drawable.foodclubmap,4.2, 669, "A healthy set of chicken rice that comes with vegetable!", R.drawable.roastedchickenrice, false, true, true, true, false, false);
        Food chickenrice2 = new Food(71,"Roasted Chicken Rice", "Food Club", R.drawable.foodclubmap,3, 500, "Very flavourful plate of roasted chicken rice! Do choose the type of meat you want!", R.drawable.roastedchickenrice, false, true, true, true, false, false);
        Food chickenrice3 = new Food(72,"Steamed Chicken Rice", "Food Club", R.drawable.foodclubmap,3, 500, "Very flavourful plate of steamed chicken rice! Do choose the type of meat you want!", R.drawable.steamchickenrice, false, true, true, true, false, false);
        Food chickenrice4 = new Food(73,"Lemon Chicken Rice", "Food Club", R.drawable.foodclubmap,3, 596, "Crispy chicken cutlet drizzled with sweet and sour sauce!", R.drawable.lemonchickenrice, false, true, true, true, false, false);
        Food chickenrice5 = new Food(74,"Chicken Cutlet Noodle", "Food Club", R.drawable.foodclubmap,3, 500, "A plate of seasoned noodles topped with crispy chicken cutlet!", R.drawable.roastedchickennoodle, true, true, false, true, false, false);
        Food chickenrice6 = new Food(75,"Roasted Chicken Noodle", "Food Club", R.drawable.foodclubmap,3, 475, "A plate of seasoned noodles, top with roasted chicken!", R.drawable.roastedchickennoodle, true, true, false, true, false, false);
        chickenrice1.setFoodImage1(R.drawable.fcchickenrice);
        chickenrice2.setFoodImage1(R.drawable.fcchickenrice);
        chickenrice3.setFoodImage1(R.drawable.fcchickenrice);
        chickenrice4.setFoodImage1(R.drawable.fcchickenrice);
        chickenrice5.setFoodImage1(R.drawable.fcchickenrice);
        chickenrice6.setFoodImage1(R.drawable.fcchickenrice);


        Food pasta1 = new Food(76,"Aglio Olio", "Food Club", R.drawable.foodclubmap,2.5, 300, "A plate of flavourful pasta. Do add on more ingredients to make it healthier meal!", R.drawable.aglioolio, true, false, false, true, false, false);
        Food pasta2 = new Food(77,"Tomato pasta", "Food Club", R.drawable.foodclubmap,2.5, 325, "A plate of tomato pasta that is slightly tangy. Do add on more ingredients to make it healthier meal!", R.drawable.tomatopasta, true, false, false, true, false, false);
        Food pasta3 = new Food(78,"Cream pasta", "Food Club", R.drawable.foodclubmap,2.5, 375, "A plate of creamy pasta, with every strand of noodle clung with the creamy sauce.", R.drawable.creampasta, true, false, false, true, false, false);
        Food pasta4 = new Food(79,"Tom yum pasta", "Food Club", R.drawable.foodclubmap,2.5, 350, "A plate of pasta that has a spicy and sour taste. Do add on more ingredients to make it healthier meal!", R.drawable.tomyumpasta, true, false, false, true, false, false);
        Food pasta5 = new Food(80,"Vegetable: corn", "Food Club", R.drawable.foodclubmap,0.5, 30, "Wide varieties of Vegetable for you to choose", R.drawable.corn, false, false, false, true, false, false);
        Food pasta6 = new Food(81,"Meat: Chicken Breast", "Food Club", R.drawable.foodclubmap,1, 50, "Wide varieties of meat options for you to choose", R.drawable.chickenbreast, false, false, false, true, false, false);
        Food pasta7 = new Food(82,"Premium: Sliced Smoked Duck", "Food Club", R.drawable.foodclubmap,1.5, 60, "Wide varieties of other meat options for you to choose", R.drawable.smokedduck, false, false, false, true, false, false);

        pasta1.setFoodImage1(R.drawable.fcpasta);
        pasta2.setFoodImage1(R.drawable.fcpasta);
        pasta3.setFoodImage1(R.drawable.fcpasta);
        pasta4.setFoodImage1(R.drawable.fcpasta);
        pasta5.setFoodImage1(R.drawable.fcpasta);
        pasta6.setFoodImage1(R.drawable.fcpasta);
        pasta7.setFoodImage1(R.drawable.fcpasta);



        Food saladbar1 = new Food(83,"Low Calorie Salad Bowl", "Food Club", R.drawable.foodclubmap, 3.5, 350, "Greens + 3 Normal Sides",R.drawable.saladbowl, false,false,false, false, true, false);
        Food saladbar2 = new Food(84,"Signature Salad Bowl", "Food Club", R.drawable.foodclubmap, 4.2, 400, "Greens + 2 Normal Sides + 1 Premium Side",R.drawable.saladbowl, false,false,false, false, true, false);
        Food saladbar3 = new Food(85,"Fill-me-up Salad Bowl", "Food Club", R.drawable.foodclubmap, 4.5, 476, "Greens + 1 Normal Side + 2 Premium Sides",R.drawable.saladbowl, false,false,false, false, true, false);
        Food saladbar4 = new Food(86,"Party Salad Bowl", "Food Club", R.drawable.foodclubmap, 8, 700, "Greens + 5 Normal Sides + 3 Premium Sides",R.drawable.saladbowl, false,false,false, false, true, false);

        saladbar1.setFoodImage1(R.drawable.munchsalad);
        saladbar2.setFoodImage1(R.drawable.munchsalad);
        saladbar3.setFoodImage1(R.drawable.munchsalad);
        saladbar4.setFoodImage1(R.drawable.munchsalad);

        Food kkFriedRice1 = new Food(87,"Egg Fried Rice", "Food Club", R.drawable.foodclubmap,2.5, 250, "Signature fried rice flavour!", R.drawable.friedrice, false,false,true, true, false, false);
        Food kkFriedRice2 = new Food(88,"Sambal Fried Rice", "Food Club", R.drawable.foodclubmap,3, 313, "Signature sambal fried rice that is sweet and spicy!", R.drawable.friedrice, false,false,true, true, false, false);
        Food kkFriedRice3 = new Food(89,"Tom Yum Fried Rice", "Food Club", R.drawable.foodclubmap,3, 320, "Signature tom yum fried rice that is spicy and sour!", R.drawable.friedrice, false,false,true, true, false, false);
        Food kkFriedRice4 = new Food(90,"Meat: Chicken Cutlet", "Food Club", R.drawable.foodclubmap,1.5, 123, "Additional ingredients to make the fried rice extra flavourful", R.drawable.chickencutlet, false,false,false, true, false, false);
        Food kkFriedRice5 = new Food(91,"Add-Ons: Cabbage", "Food Club", R.drawable.foodclubmap,0.8, 50, "Additional ingredients to make the meal healthier!", R.drawable.cabbage, false,false,false, true, false, false);

        kkFriedRice1.setFoodImage1(R.drawable.fckingkong);
        kkFriedRice2.setFoodImage1(R.drawable.fckingkong);
        kkFriedRice3.setFoodImage1(R.drawable.fckingkong);
        kkFriedRice4.setFoodImage1(R.drawable.fckingkong);
        kkFriedRice5.setFoodImage1(R.drawable.fckingkong);

        Food Indonesian1 = new Food(92,"Ayam Penyet Set", "Food Club", R.drawable.foodclubmap, 4.50, 650, "Signature! With a gigantic piece of drumstick, drenched in curry!", R.drawable.ayampenyet, false, false, true, true, false, false);
        Food Indonesian2 = new Food(93,"Udang Penyet Set", "Food Club", R.drawable.foodclubmap, 4.50, 502, "Signature! With prawn pieces, drenched in curry!", R.drawable.ayampenyet, false, false, true, true, false, false);
        Food Indonesian3 = new Food(94,"Ikan Dory Set", "Food Club", R.drawable.foodclubmap, 4.40, 657, "Huge portion of fish, drenched in curry!", R.drawable.ayampenyet, false, false, true, true, false, false);
        Food Indonesian4 = new Food(95,"Otah Penyet Set", "Food Club", R.drawable.foodclubmap, 4.50, 650, "Favourite fried otah, drenched in curry!", R.drawable.ayampenyet, false, false, true, true, false, false);
        Food Indonesian5 = new Food(96,"Mee Siam Set", "Food Club", R.drawable.foodclubmap, 4.50, 571, "Bee hoon with a unique sweet and sour gravy", R.drawable.meesiam, true, true, false, true, false, false);

        Indonesian1.setFoodImage1(R.drawable.fcindonesian);
        Indonesian2.setFoodImage1(R.drawable.fcindonesian);
        Indonesian3.setFoodImage1(R.drawable.fcindonesian);
        Indonesian4.setFoodImage1(R.drawable.fcindonesian);
        Indonesian5.setFoodImage1(R.drawable.fcindonesian);


        Food creamyDuck1 = new Food(97,"Waffles", "Food Club", R.drawable.foodclubmap, 1.8, 200, "Multiple variations of the waffle filling to choose from!", R.drawable.waffles, false, false, false, true, false, true);
        Food creamyDuck2 = new Food(98,"Corn butter salt", "Food Club", R.drawable.foodclubmap, 1.9, 173, "Steamed corn mixed with butter and salt, buttery and savoury", R.drawable.corn, false, false, false, true, false, true);
        Food creamyDuck3 = new Food(99,"Corn butter choc", "Food Club", R.drawable.foodclubmap, 1.9, 173, "Steamed corn mixed with butter and chocolate, sweet and savoury", R.drawable.corn, false, false, false, true, false, true);
        Food creamyDuck4 = new Food(100,"Takoyaki", "Food Club", R.drawable.foodclubmap, 2.8, 350, "Multiple variations of the Takoyaki fillings to choose from! Either choose prawn, ham, octopus or crabstick!", R.drawable.takoyaki, false, false, false, true, false, true);
        Food creamyDuck5 = new Food(101,"Ice Cream", "Food Club", R.drawable.foodclubmap, 1.8, 230, "Comes in different sizes, choose either 6oz or 12oz single scoop", R.drawable.icecream, false, false, false, true, false, true);

        creamyDuck1.setFoodImage1(R.drawable.fcwaffle);
        creamyDuck2.setFoodImage1(R.drawable.fcwaffle);
        creamyDuck3.setFoodImage1(R.drawable.fcwaffle);
        creamyDuck4.setFoodImage1(R.drawable.fcwaffle);
        creamyDuck5.setFoodImage1(R.drawable.fcwaffle);


        Food coffeeClub1 = new Food(102,"Breakfast Set A", "Food Club", R.drawable.foodclubmap, 2, 450, "Comes with 2 toasted bread + 2 half boiled eggs and 1 hot beverage", R.drawable.breakfastset, false, false, false, true, false, false);
        Food coffeeClub2 = new Food(103,"Breakfast Set B", "Food Club", R.drawable.foodclubmap, 2.20, 435, "Comes with 1 Apollo bread + 2 half boiled eggs and 1 hot beverage", R.drawable.breakfastset, false, false, false , true, false, false);
        Food coffeeClub3 = new Food(104,"Breakfast Set C", "Food Club", R.drawable.foodclubmap, 2.40, 440, "Comes with 1 thick bread + 2 half boiled eggs and 1 hot beverage", R.drawable.breakfastset, false, false, false, true, false, false);
        Food coffeeClub4 = new Food(105,"Breakfast Set D", "Food Club", R.drawable.foodclubmap, 2.40, 463, "Comes with 4 cream crackers + 2 half boiled eggs and 1 hot beverage", R.drawable.breakfastset, false, false, false, true, false, false);

        coffeeClub1.setFoodImage1(R.drawable.fcdrinks);
        coffeeClub2.setFoodImage1(R.drawable.fcdrinks);
        coffeeClub3.setFoodImage1(R.drawable.fcdrinks);
        coffeeClub4.setFoodImage1(R.drawable.fcdrinks);

        //MAKAN PLACE
        Food MPKoreanFusion1 = new Food(106,"Hotplate Saba Fish", "Makan Place", R.drawable.makanplacemap, 5.30, 530, "Grilled saba fish that comes in a set with korean rice and a bowl of soup!", R.drawable.sabafish, false, true, true, true, false, false);
        Food MPKoreanFusion2 = new Food(107,"Hotplate Chicken Fish", "Makan Place", R.drawable.makanplacemap, 5.00, 546, "Korean spicy marinated chicken that comes in a set with korean rice and a bowl of soup!", R.drawable.koreanbbq, false, true, true, true, false, false);
        Food MPKoreanFusion3 = new Food(108,"Beef Ramen", "Makan Place", R.drawable.makanplacemap, 5.30, 530, "Korean ramen with marinated and tender beef!", R.drawable.koreanramen, true, true, false, true, false, false);
        Food MPKoreanFusion4 = new Food(109,"Bibimbap (Chicken)", "Makan Place", R.drawable.makanplacemap, 4.40, 450, "Korean rice with various vegetables and a spoonful of gochujang sauce!", R.drawable.bibimbap, false, true, true, true, false, false);
        Food MPKoreanFusion5 = new Food(110,"Hotplate Chicken + Saba Fish", "Makan Place", R.drawable.makanplacemap, 6.30, 530, "Grilled saba fish and spicy marinated chicken that comes in a set with korean rice and a bowl of soup!", R.drawable.chickenfishset, false, true, true, true, false, false);

        MPKoreanFusion1.setFoodImage1(R.drawable.makankorean);
        MPKoreanFusion2.setFoodImage1(R.drawable.makankorean);
        MPKoreanFusion3.setFoodImage1(R.drawable.makankorean);
        MPKoreanFusion4.setFoodImage1(R.drawable.makankorean);
        MPKoreanFusion5.setFoodImage1(R.drawable.makankorean);


        Food MPWestern1 = new Food(111,"Chicken Chop", "Makan Place", R.drawable.makanplacemap, 5, 430, "Grilled chicken with BBQ sauce and it comes with 2 sides!", R.drawable.store, false, false, false, true, false, false);
        Food MPWestern2 = new Food(112,"Fish Rice Set", "Makan Place", R.drawable.makanplacemap, 5.80, 460, "Big piece of fried fish and it comes with rice and 1 side!", R.drawable.store, false, false, true, true, false, false);
        Food MPWestern3 = new Food(113,"Fish with Aglio Olio", "Makan Place", R.drawable.makanplacemap, 5.50, 560, "Big piece of fried fish and it comes with aglio olio on the side!", R.drawable.store, true, false, false, true, false, false);
        Food MPWestern4 = new Food(114,"Fried chicken with tomato spaghetti", "Makan Place", R.drawable.makanplacemap, 5.5, 580, "Fried chicken with BBQ sauce and it comes with tomato spaghetti on the side!", R.drawable.store, true, false, false, true, false, false);
        Food MPWestern5 = new Food(115,"Chicken Chop rice set", "Makan Place", R.drawable.makanplacemap, 5, 430, "Grilled chicken with BBQ sauce and it comes with rice and 1 side!", R.drawable.store, false, false, true, true, false, false);

        MPWestern1.setFoodImage1(R.drawable.makanwestern);
        MPWestern2.setFoodImage1(R.drawable.makanwestern);
        MPWestern3.setFoodImage1(R.drawable.makanwestern);
        MPWestern4.setFoodImage1(R.drawable.makanwestern);
        MPWestern5.setFoodImage1(R.drawable.makanwestern);

        Food MPHotto1 = new Food(116,"Beef Roll Hotto Rice", "Makan Place", R.drawable.makanplacemap, 5.9, 500, "Beef slices and rice on a hot plate, sizzlingly delicious", R.drawable.hotto, false, false, true, true, false, false);
        Food MPHotto2 = new Food(117,"Salmon Hotto Rice", "Makan Place", R.drawable.makanplacemap, 6.9, 500, "Salmon and rice on a hot plate, sizzlingly delicious", R.drawable.hotto, false, false, true, true, false, false);
        Food MPHotto3 = new Food(118,"Chicken Hotto Rice", "Makan Place", R.drawable.makanplacemap, 4.9, 500, "Chicken cubes and rice on a hot plate, sizzlingly delicious", R.drawable.hotto, false, false, true, true, false, false);
        Food MPHotto4 = new Food(119,"Black pepper chicken burger", "Makan Place", R.drawable.makanplacemap, 3.9, 370, "Chicken patty with black pepper sauce in a burger", R.drawable.burger, false, false, false, true, false, false);
        Food MPHotto5 = new Food(120,"Creamy Shroom Pasta", "Makan Place", R.drawable.makanplacemap, 4.9, 510, "Creamy mushroom pasta", R.drawable.creampasta, true, false, false, true, false, false);

        MPHotto1.setFoodImage1(R.drawable.makanhottoneko);
        MPHotto2.setFoodImage1(R.drawable.makanhottoneko);
        MPHotto3.setFoodImage1(R.drawable.makanhottoneko);
        MPHotto4.setFoodImage1(R.drawable.makanhottoneko);
        MPHotto5.setFoodImage1(R.drawable.makanhottoneko);

        Food MPJap1 = new Food(121,"Hotplate Chicken Fuyong", "Makan Place", R.drawable.makanplacemap, 4, 500, "Fried chicken with egg, drizzled with the special sweet sauce", R.drawable.chickenfuyong, false, true, true, false, false, false);
        Food MPJap2 = new Food(122,"Chicken and pork bento", "Makan Place", R.drawable.makanplacemap, 4.50, 560, "Fried chicken and pork in a bento with rice", R.drawable.chickenporkbento, false, true, true, false, false, false);
        Food MPJap3 = new Food(123,"Salmon Don", "Makan Place", R.drawable.makanplacemap, 4.50, 500, "Geilled salmon with store's special sauce on top of a bowl of rice", R.drawable.salmondon, false, true, true, false, false, false);
        Food MPJap4 = new Food(124,"Ebi fry Katsu Curry Rice", "Makan Place", R.drawable.makanplacemap, 4.50, 560, "Fried ebi with japanese curry rice", R.drawable.ebikatsucurry, false, true, true, false, false, false);
        Food MPJap5 = new Food(125,"Pork Shabu shabu curry udon", "Makan Place", R.drawable.makanplacemap, 4, 500, "Thin slices of pork shabu shabu with japanese udon curry", R.drawable.curryudon, true, true, false, false, false, false);

        MPJap1.setFoodImage1(R.drawable.makanjap);
        MPJap2.setFoodImage1(R.drawable.makanjap);
        MPJap3.setFoodImage1(R.drawable.makanjap);
        MPJap4.setFoodImage1(R.drawable.makanjap);
        MPJap5.setFoodImage1(R.drawable.makanjap);


        Food MPGoPizza1 = new Food(126,"American Cheese Pizza", "Makan Place", R.drawable.makanplacemap, 6.0, 560, "Pizza topped with multiple variations of cheese", R.drawable.americancheese, false, false, false, false, false, false);
        Food MPGoPizza2 = new Food(127,"K-Bulgogi Pizza", "Makan Place", R.drawable.makanplacemap, 10.0, 600, "Pizza topped with of cheese and korean marinated beef", R.drawable.kbulgogi, false, false, false, false, false, false);
        Food MPGoPizza3 = new Food(128,"Original Wings", "Makan Place", R.drawable.makanplacemap, 6.80, 560, "Three pieces of original flavoured wings for 6.80! Crispy on the outside, tender & juicy on the insider!", R.drawable.originalwings, false, false, false, false, false, false);
        Food MPGoPizza4 = new Food(129,"Cheese Stick", "Makan Place", R.drawable.makanplacemap, 4.80, 516, "Cheesy cheese stick that is savoury and suitable for all ages!", R.drawable.cheesesticks, false, false, false, false, false, false);
        Food MPGoPizza5 = new Food(130,"Shaker Fries", "Makan Place", R.drawable.makanplacemap, 2.80, 430, "Choose either sea salt of seaweed flavour for your shaker fries", R.drawable.shakerfries, false, false, false, false, false, false);

        MPGoPizza1.setFoodImage1(R.drawable.makanpizza);
        MPGoPizza2.setFoodImage1(R.drawable.makanpizza);
        MPGoPizza3.setFoodImage1(R.drawable.makanpizza);
        MPGoPizza4.setFoodImage1(R.drawable.makanpizza);
        MPGoPizza5.setFoodImage1(R.drawable.makanpizza);


        //left ban mian, yong tau foo, mala, drinks + add them into the list
        Food MPBanMian1 = new Food(131,"Ban Mian", "Makan Place", R.drawable.makanplacemap, 2.80, 350, "Healthy bowl of noodles with egg and minced meat!", R.drawable.banmian, true, true, false, false, false, false);
        Food MPBanMian2 = new Food(132,"Hot & Spicy Ban Mian / You Mian", "Makan Place", R.drawable.makanplacemap, 3.80, 450, "A bowl of spicy noodles with egg and minced meat! Do specify the type of noodles you want!", R.drawable.banmian, true, true, false, false, false, false);
        Food MPBanMian3 = new Food(133,"Fried Fish soup", "Makan Place", R.drawable.makanplacemap, 3.50, 400, "A bowl of warm and savoury fish soup, with the fried fishes dipped in the bowl of soup.", R.drawable.fishsoup, false, true, false, false, false, false);
        Food MPBanMian4 = new Food(134,"Signatue La Mian", "Makan Place", R.drawable.makanplacemap, 3.50, 400, "Healthy bowl of noodles with egg and minced meat!", R.drawable.banmian, true, true, false, false, false, false);
        Food MPBanMian5 = new Food(135,"Tom Yum Ramen", "Makan Place", R.drawable.makanplacemap, 3.60, 476, "Hot bowl of noodles that is spicy and sour! ", R.drawable.tomyumlamian, true, true,false, false, false, false);

        MPBanMian1.setFoodImage1(R.drawable.makanbanmian);
        MPBanMian2.setFoodImage1(R.drawable.makanbanmian);
        MPBanMian3.setFoodImage1(R.drawable.makanbanmian);
        MPBanMian4.setFoodImage1(R.drawable.makanbanmian);
        MPBanMian5.setFoodImage1(R.drawable.makanbanmian);

        Food MPmala = new Food(136,"Mala Xiang Guo", "Makan Place", R.drawable.makanplacemap, 7, 600, "Pick and choose your favourite ingredients in your bowl of mala(dry/soup)! Price ranges!", R.drawable.mala, true, false, true, false, false, false);
        MPmala.setFoodImage1(R.drawable.makanmala);

        Food MPWokQueen1 = new Food(137,"Tomyum Fried Rice", "Makan Place", R.drawable.makanplacemap, 4.50, 363, "Tomyum Flavoured fried rice, make sure to choose chicken or prawn!", R.drawable.friedrice, false, false, true, true, false, false);
        Food MPWokQueen2 = new Food(138,"Sambal Fried Rice", "Makan Place", R.drawable.makanplacemap, 4.50, 363, "Sambal Flavoured fried rice, make sure to choose chicken or prawn!", R.drawable.friedrice, false, false, true, true, false, false);
        Food MPWokQueen3 = new Food(139,"Egg Fried Rice", "Makan Place", R.drawable.makanplacemap, 4.50, 363, "Flavourful Fried rice, make sure to choose chicken or prawn!", R.drawable.friedrice, false, false, true, true, false, false);
        Food MPWokQueen4 = new Food(140,"Seafood Tomyum soup with rice and egg", "Makan Place", R.drawable.makanplacemap, 5.00, 560, "A bowl of tomyum soup with a lot of ingredients!", R.drawable.tomyumsoup, false, true, true, true, false, false);
        Food MPWokQueen5 = new Food(141,"Green curry chicken with rice and egg", "Makan Place", R.drawable.makanplacemap, 5.00, 363, "A bowl of green curry with rice and a piece of egg on the side!", R.drawable.greencurry, false, true, true, true, false, false);

        MPWokQueen1.setFoodImage1(R.drawable.makanasian);
        MPWokQueen2.setFoodImage1(R.drawable.makanasian);
        MPWokQueen3.setFoodImage1(R.drawable.makanasian);
        MPWokQueen4.setFoodImage1(R.drawable.makanasian);
        MPWokQueen5.setFoodImage1(R.drawable.makanasian);

        Food YongTauFoo1 = new Food (142,"Yong Tau Foo (Laksa)", "Makan Place", R.drawable.makanplacemap, 4.50, 654, "A customisable bowl of noodles with various ingredients and noodles with laksa soup base!", R.drawable.laskaytf, true, true, false, false, false, false);
        Food YongTauFoo2 = new Food (143,"Yong Tau Foo (Tomyum)", "Makan Place", R.drawable.makanplacemap, 4.50, 654, "A customisable bowl of noodles with various ingredients and noodles, with tomyum soup base!", R.drawable.tomyumytf, true, true, false, false, false, false);
        Food YongTauFoo3 = new Food (144,"Yong Tau Foo (Dry/Soup)", "Makan Place", R.drawable.makanplacemap, 3, 654, "A customisable bowl of noodles with various ingredients and noodles with normal/no soup base!", R.drawable.ytf1, true, true, false, false, false, false);

        YongTauFoo1.setFoodImage1(R.drawable.makanytf);
        YongTauFoo2.setFoodImage1(R.drawable.makanytf);
        YongTauFoo3.setFoodImage1(R.drawable.makanytf);

        Food MPSalad1 = new Food(145,"Garden Salad", "Makan Place", R.drawable.makanplacemap, 4.50, 345, "A bowl salad with vegetable. Do make a choice if you want to add on salad dressing!", R.drawable.saladbowl, false, false, false, false, true, false);
        Food MPSalad2 = new Food(146,"Chicken Salad", "Makan Place", R.drawable.makanplacemap, 5.50, 413, "A bowl salad with vegetable and chicken breast. Do make a choice if you want to add on salad dressing!", R.drawable.saladbowl, false, false, false, false, true, false);
        Food MPSalad3 = new Food(147,"Wraps", "Makan Place", R.drawable.makanplacemap, 5.50, 456, "Healthy ingredients all wrap together!", R.drawable.wrap, false, false, false, false, true, false);

        MPSalad1.setFoodImage1(R.drawable.makansalad);
        MPSalad2.setFoodImage1(R.drawable.makansalad);
        MPSalad3.setFoodImage1(R.drawable.makansalad);

        Food Mbingsu1 = new Food(148,"Matcha Bingsu", "Makan Place", R.drawable.makanplacemap, 5.90, 346, "A refreshing bowl of matcha flavoured shaved ice", R.drawable.matchabingsu, false, false, false, true, false, true);
        Food Mbingsu2 = new Food(149,"Mango Bingsu", "Makan Place", R.drawable.makanplacemap, 5.90, 346, "A refreshing bowl of mango flavoured shaved ice", R.drawable.mangobingsu, false, false, false, true, false, true);
        Food Obingsu3 = new Food(150,"Orea Bingsu", "Makan Place", R.drawable.makanplacemap, 5.90, 346, "A refreshing bowl of orea flavoured shaved ice", R.drawable.store, false, false, false, true, false, true);

        Mbingsu1.setFoodImage1(R.drawable.makanhottoneko);
        Mbingsu2.setFoodImage1(R.drawable.makanhottoneko);
        Obingsu3.setFoodImage1(R.drawable.makanhottoneko);



        foodList.add(munchSaladBowlR);
        foodList.add(munchSaladBowlL);
        foodList.add(munchWestern1);
        foodList.add(munchWestern2);
        foodList.add(munchWestern3);
        foodList.add(munchWestern4);
        foodList.add(munchWestern5);
        foodList.add(munchWestern6);
        foodList.add(munchWestern7);
        foodList.add(munchWestern8);
        foodList.add(munchWestern9);
        foodList.add(munchWestern10);
        foodList.add(munchWestern11);
        foodList.add(munchWestern12);
        foodList.add(munchWestern13);
        foodList.add(munchWestern14);
        foodList.add(munchWestern15);
        foodList.add(munchWestern16);
        foodList.add(munchWestern17);
        foodList.add(munchWestern18);
        foodList.add(munchWestern19);
        foodList.add(munchWestern20);
        foodList.add(munchWestern21);
        //foodList.add(munchWestern22);
        //foodList.add(munchWestern23);
        //foodList.add(munchWestern24);
        //foodList.add(munchWestern25);
        //foodList.add(munchWestern26);
        //foodList.add(munchWestern27);

        foodList.add(munchClaypot1);
        foodList.add(munchClaypot2);
        foodList.add(munchClaypot3);
        foodList.add(munchClaypot4);
        foodList.add(munchClaypot5);
        foodList.add(munchClaypot6);
        foodList.add(munchClaypot7);
        foodList.add(munchClaypot8);
        foodList.add(munchClaypot9);
        foodList.add(munchClaypot10);
        //foodList.add(munchClaypot11);
        //foodList.add(munchClaypot12);
        //foodList.add(munchClaypot13);

        foodList.add(munchNasiPadang1);
        foodList.add(munchNasiPadang2);
        foodList.add(munchNasiPadang3);

        foodList.add(munchPizza1);
        foodList.add(munchPizza2);
        foodList.add(munchPizza3);
        foodList.add(munchPizza4);
        foodList.add(munchPizza5);
        foodList.add(munchPizza6);
        foodList.add(munchPizza7);
        //foodList.add(munchPizza8);
        foodList.add(munchPizza9);

        foodList.add(munchKorean1);
        foodList.add(munchKorean2);
        //foodList.add(munchKorean3);
        foodList.add(munchKorean4);
        foodList.add(munchKorean5);
        foodList.add(munchKorean6);
        foodList.add(munchKorean7);
        foodList.add(munchKorean8);
        foodList.add(munchKorean9);
        foodList.add(munchKorean10);
        foodList.add(munchKorean11);
        foodList.add(munchKorean12);
        foodList.add(munchKorean13);
        foodList.add(munchKorean14);
        foodList.add(munchKorean15);
        foodList.add(munchKorean16);
        foodList.add(munchKorean17);
        foodList.add(munchKorean18);
        foodList.add(munchKorean19);
        foodList.add(munchKorean20);
        foodList.add(munchKorean21);
        foodList.add(munchKorean22);
        foodList.add(munchKorean23);
        foodList.add(munchKorean24);


        foodList.add(chickenrice1);
        foodList.add(chickenrice2);
        foodList.add(chickenrice3);
        foodList.add(chickenrice4);
        foodList.add(chickenrice5);
        foodList.add(chickenrice6);

        foodList.add(pasta1);
        foodList.add(pasta2);
        foodList.add(pasta3);
        foodList.add(pasta4);
        foodList.add(pasta5);
        foodList.add(pasta6);
        foodList.add(pasta7);

        foodList.add(saladbar1);
        foodList.add(saladbar2);
        foodList.add(saladbar3);
        foodList.add(saladbar4);

        foodList.add(kkFriedRice1);
        foodList.add(kkFriedRice2);
        foodList.add(kkFriedRice3);
        foodList.add(kkFriedRice4);
        foodList.add(kkFriedRice5);

        foodList.add(Indonesian1);
        foodList.add(Indonesian2);
        foodList.add(Indonesian3);
        foodList.add(Indonesian4);
        foodList.add(Indonesian5);

        foodList.add(creamyDuck1);
        foodList.add(creamyDuck2);
        foodList.add(creamyDuck3);
        foodList.add(creamyDuck4);
        foodList.add(creamyDuck5);

        foodList.add(coffeeClub1);
        foodList.add(coffeeClub2);
        foodList.add(coffeeClub3);
        foodList.add(coffeeClub4);

        foodList.add(MPBanMian1);
        foodList.add(MPBanMian2);
        foodList.add(MPBanMian3);
        foodList.add(MPBanMian4);
        foodList.add(MPBanMian5);

        foodList.add(MPmala);

        foodList.add(MPWokQueen1);
        foodList.add(MPWokQueen2);
        foodList.add(MPWokQueen3);
        foodList.add(MPWokQueen4);
        foodList.add(MPWokQueen5);

        foodList.add(YongTauFoo1);
        foodList.add(YongTauFoo2);
        foodList.add(YongTauFoo3);

        foodList.add(MPSalad1);
        foodList.add(MPSalad2);
        foodList.add(MPSalad3);

        foodList.add(Mbingsu1);
        foodList.add(Mbingsu2);
        foodList.add(Obingsu3);

        foodList.add(MPKoreanFusion1);
        foodList.add(MPKoreanFusion2);
        foodList.add(MPKoreanFusion3);
        foodList.add(MPKoreanFusion4);
        foodList.add(MPKoreanFusion5);

        foodList.add(MPWestern1);
        foodList.add(MPWestern2);
        foodList.add(MPWestern3);
        foodList.add(MPWestern4);
        foodList.add(MPWestern5);

        foodList.add(MPHotto1);
        foodList.add(MPHotto2);
        foodList.add(MPHotto3);
        foodList.add(MPHotto4);
        foodList.add(MPHotto5);

        foodList.add(MPJap1);
        foodList.add(MPJap2);
        foodList.add(MPJap3);
        foodList.add(MPJap4);
        foodList.add(MPJap5);

        foodList.add(MPGoPizza1);
        foodList.add(MPGoPizza2);
        foodList.add(MPGoPizza3);
        foodList.add(MPGoPizza4);
        foodList.add(MPGoPizza5);

        return foodList;


    }

}


