package sg.edu.np.mad;

import static android.icu.text.DisplayContext.LENGTH_SHORT;

//import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    private FirebaseDatabase database;

    String tag;

    public GeneralView_Adapter(Context context, List<Food> input, SelectListenerFood ListenerFood, IconClickListener listener) {
        this.context = context;
        this.data = input;
        this.listenerFood = ListenerFood;
        this.listenerWL = listener;
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
        /*if (DataHolder.viewHoldering == null) {
            //inflating the generalView holder with the relevant details
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.general_view_viewholder, parent, false);
            return new GeneralView_Viewholder(item);
        } else {
            return DataHolder.viewHoldering;
        }*/

    }

    @Override
    public void onBindViewHolder(GeneralView_Viewholder holder, int position) {
        Food f = data.get(position);
        Log.i("data", String.valueOf(f));
        Log.i("holder number", String.valueOf(holder));
        Log.i("status check", String.valueOf(f.getAddedWishlist()));
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

        holder.wishlisticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onClick wishlist button, adds the food object to the wishlist page
                Log.i("wishlist f", String.valueOf(f));

//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                DatabaseReference accountsRef = database.getReference("Accounts").child("-hello");
//                accountsRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        Account acc = snapshot.getValue(Account.class);
//                        if (!acc.wishlist.contains(f.getFoodIndex())){
//                            acc.wishlist.add(f.getFoodIndex());
//                            Toast.makeText(v.getContext(), "Food added to the wishlist!", Toast.LENGTH_SHORT).show();
//                            f.setAddedWishlist(true);
//
//                        }
//                        else{
//                            acc.wishlist.remove(f.getFoodIndex());
//                            Toast.makeText(v.getContext(), "Food removed from the wishlist!", Toast.LENGTH_SHORT).show();
//                            f.setAddedWishlist(false);
//                        }
//                        changeIconColor(v, f, holder);
//                        //get the wishlist. check if inside. --> update the colour --> update wishlist --> Toast --> update Database.
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });

                if (!DataHolder.wishlist_List.contains(f)) {
                    DataHolder.wishlist_List.add(f);
                    Toast.makeText(v.getContext(), "Food added to the wishlist!", Toast.LENGTH_SHORT).show();
                    f.setAddedWishlist(true);

                    //need to make it change the colour
                    //changeIconColor(v, f, holder); //changing the colour from black to red since it is being added to the wishlist
                } else {
                    DataHolder.wishlist_List.remove(f);
                    Toast.makeText(v.getContext(), "Food removed from the wishlist!", Toast.LENGTH_SHORT).show();
                    f.setAddedWishlist(false);
                    //changeIconColor(v, f, holder); //changing the colour from red to black since it is being removed from the wishlist
                }
                changeIconColor(v, f, holder);
            }
        });
    }

    public int getItemCount() {
        Log.i("DATA SIZE", String.valueOf(data.size()));
        return data.size();
    }

    public void changeIconColor(View view, Food f, GeneralView_Viewholder holder) {
        // Change the color of the icon
        if (f.getAddedWishlist() == true){
            int newColor = Color.parseColor("#FF0000"); // Set the desired color here
            holder.wishlisticon.setColorFilter(newColor);

        }
        else if(f.getAddedWishlist() == false){
            int newColor = Color.parseColor("#000000");
            holder.wishlisticon.setColorFilter(newColor);
        }
        //DataHolder.viewHoldering = holder;
    }

}


