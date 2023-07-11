package sg.edu.np.mad;

import static android.icu.text.DisplayContext.LENGTH_SHORT;

import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GeneralView_Adapter extends RecyclerView.Adapter<GeneralView_Viewholder> { //implements Serializable
    private Context context;
    private List<Food> data;
    private SelectListenerFood listenerFood;
    private IconClickListener listenerWL;
    private List<Food> wishlist_List;

    public GeneralView_Adapter(Context context, List<Food> input, SelectListenerFood ListenerFood, IconClickListener listener){
        this.context = context;
        this.data = input;
        this.listenerFood = ListenerFood;
        this.listenerWL = listener;
    }
    public void setIconClickListener(IconClickListener listener) {
        this.listenerWL = listener;
    }


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

        //inflating the generalView holder with the relevant details
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.general_view_viewholder, parent, false);
        return new GeneralView_Viewholder(item);
    }

    @Override
    public void onBindViewHolder(GeneralView_Viewholder holder, int position){
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
                //toCatalogue.putExtra("object", (Serializable)f);
                context.startActivity(toCatalogue);

            }
        });

        holder.wishlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onClick wishlist button, adds the food object to the wishlist page
                wishlist_List.add(data.get(position));

                Toast.makeText(getApplicationContext(),"Food added to the wishlist!", Toast.LENGTH_SHORT).show();

                System.out.println(wishlist_List);
            }
        });
    }

    public int getItemCount(){
        //System.out.println(data.size());
        return data.size();
    }

    //for it to change colour
    public void changeIconColor(View view) {
        // Change the color of the icon

        //Food f = wishlist_List.get(f);
        int newColor = Color.parseColor("#FF0000"); // Set the desired color here
        //holder.wishlistButton.setColorFilter(newColor);

    }


}
