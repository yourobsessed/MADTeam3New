package sg.edu.np.mad;

import static android.icu.text.DisplayContext.LENGTH_SHORT;

//import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Icon;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mapbox.mapboxsdk.Mapbox;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.DoubleAdder;

public class GeneralView_Adapter extends RecyclerView.Adapter<GeneralView_Viewholder> { //implements Serializable
    private Context context;
    private List<Food> data;
    private SelectListenerFood listenerFood;
    private IconClickListener listenerWL;

    private int imageViewColor;
    //private FirebaseDatabase database;

    String tag;
    //private MyDBHandler myDBHandler;

    public GeneralView_Adapter(Context context, List<Food> input, SelectListenerFood ListenerFood, IconClickListener listener){//, MyDBHandler mydbHandler) {
        this.context = context;
        this.data = input;
        this.listenerFood = ListenerFood;
        this.listenerWL = listener;
        //this.myDBHandler = mydbHandler;
    }
    /*public void setIconClickListener(IconClickListener listener) {
        this.listenerWL = listener;
    }*/


    public void setFilteredList(List<Food> filteredList) {
        // below line is to add our filtered
        // list in our course array list.
        this.data = filteredList;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    @Override
    public GeneralView_Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.general_view_viewholder, parent, false);
        return new GeneralView_Viewholder(item);

    }

    @Override
    public void onBindViewHolder(GeneralView_Viewholder holder, int position) {
        //MyDBHandler myDBHandler = new MyDBHandler(context);
        Food f = data.get(position);
        holder.foodName.setText(f.getFoodName());
        holder.foodDescription.setText(f.getDescription());
        holder.foodImage.setImageResource(f.getFoodImage2());

        //creating onclick intent of the cardView to the catalogue page
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //changing to another catalogue page
                Intent toCatalogue = new Intent(v.getContext(), CataloguePage.class);

                //passing important information into the intent with putExtra
                toCatalogue.putExtra("FoodName", data.get(holder.getAdapterPosition()).getFoodName());
                toCatalogue.putExtra("FoodPrice", data.get(holder.getAdapterPosition()).getPrice());
                toCatalogue.putExtra("FoodCalories", data.get(holder.getAdapterPosition()).getCalories());
                toCatalogue.putExtra("FoodImg", f.getFoodImage1());
                toCatalogue.putExtra("FoodImg2", f.getFoodImage2());
                toCatalogue.putExtra("LocationImg", f.getLocationImage());
                toCatalogue.putExtra("storeLocation", f.getLocation());
                context.startActivity(toCatalogue);

            }
        });

        //reading the database with the updated information
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference accountsRef = database.getReference("Accounts").child(DataHolder.username);

        //changing the colour based on the status
        //remove the random colouring of the heart icon problem
        if(DataHolder.wishlist_List.contains(f.getFoodIndex())) {
            holder.wishlisticon.setColorFilter(Color.RED);
        } else {
            int newColor = Color.parseColor("#D3D3D3");
            holder.wishlisticon.setColorFilter(newColor);
        }

        holder.wishlisticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //reading the database
                accountsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Account acc = snapshot.getValue(Account.class);

                        Log.i("Account: 1", String.valueOf(acc.wishlist));
                        Log.i("Account Details", String.valueOf(acc));

                        if (!acc.wishlist.contains(f.getFoodIndex())) { //checking if the food is added in the wishlist
                            acc.wishlist.add(f.getFoodIndex()); //adds the food to the wishlist
                            f.setAddedWishlist(true); //changing the food status to help with the changing of colours
                            Toast.makeText(v.getContext(), "Food added to the wishlist!", Toast.LENGTH_SHORT).show(); //showing the toast

                            Log.i("wishlist: f", String.valueOf(acc.wishlist));
                            DatabaseReference userWishList = accountsRef.child("wishlist"); //reading the database for the specific wishlist of the username account

                            //passinig the db data into the project data for use
                            DataHolder.wishlist_List = acc.wishlist;
                            Log.i("dataholder.wishlist", String.valueOf(DataHolder.wishlist_List));
                            userWishList.setValue(acc.wishlist); //updating the database's wishlist for specific users
                            //changeIconColor(f, holder);
                        }

                        else {
                            Log.i("acc before", String.valueOf(acc.wishlist));
                            int itemToRemove = acc.wishlist.indexOf(f.getFoodIndex()); //finding the item to remove
                            acc.wishlist.remove(itemToRemove); //removing the item from the wishlist
                            Log.i("acc after", String.valueOf(acc.wishlist));
                            f.setAddedWishlist(false); //chaning the status
                            Toast.makeText(v.getContext(), "Food removed from the wishlist!", Toast.LENGTH_SHORT).show(); //showing the toast

                            DatabaseReference userWishList = accountsRef.child("wishlist"); //having access to the wishlist of the specific user's wishlist
                            DataHolder.wishlist_List = acc.wishlist; //setting the global wishlist to the database wishlist
                            Log.i("dataholder.wishlist", String.valueOf(DataHolder.wishlist_List));
                            userWishList.setValue(acc.wishlist); //updating the database's wishlist for specific users
                            //changeIconColor(f, holder);
                        }
                        changeIconColor(f,holder);  //chaning the icon onclick based on the status of each food item
                        //myDBHandler.updateFood(f);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }
    public int getItemCount () {
        //Log.i("DATA SIZE", String.valueOf(data.size()));
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        //System.out.println(data.get(position));
        return position;
    }

    public void changeIconColor (Food f, GeneralView_Viewholder holder){
        // Change the color of the icon


        if (f.getAddedWishlist() == true) { //checking the status of each app
            int newColor = Color.parseColor("#FF0000"); // changing the colour to red
            holder.wishlisticon.setColorFilter(newColor);


        } else if (f.getAddedWishlist() == false) { //checking the status of each app
            int newColor = Color.parseColor("#D3D3D3"); //chaning colour to black
            holder.wishlisticon.setColorFilter(newColor); //setting
        }
    }

}