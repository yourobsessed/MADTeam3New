package sg.edu.np.mad;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GeneralView_Adapter extends RecyclerView.Adapter<GeneralView_Viewholder> { //implements Serializable
    private Context context;
    private List<Food> data;
    private SelectListenerFood listenerFood;
    private IconClickListener listenerWL;
    public GeneralView_Adapter(Context context, List<Food> input, SelectListenerFood ListenerFood){
        this.context = context;
        this.data = input;
        this.listenerFood = ListenerFood;
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

            }
        });
    }

    public int getItemCount(){
        //System.out.println(data.size());
        return data.size();
    }

    public void changeIconColor(View view) {
        // Change the color of the icon
        int newColor = Color.RED; // Set the desired color here
        //holder.wishlistButton.setColorFilter(newColor);

    }


}
