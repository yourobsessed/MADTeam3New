package sg.edu.np.mad;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

public class WishList_Adapter extends RecyclerView.Adapter<WishList_ViewHolder> {
    private List<Food> data;
    private Context context;
    private SelectListenerFood listenerFood;


    public WishList_Adapter(Context context, List<Food> input) {//, SelectListenerFood ListenerFood){
        this.context = context;
        this.data = input;
        //this.listenerFood = ListenerFood;

    }

    public WishList_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_viewholder, parent, false);
        return new WishList_ViewHolder(item);
    }

    public void onBindViewHolder(WishList_ViewHolder holder, int position) {
        Food f = data.get(position);
        holder.Name.setText(f.getFoodName());
        holder.Desc.setText(f.getDescription());
        holder.Image.setImageResource(f.getFoodImage2());
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
                if (f.getAddedWishlist() == false) {
                    DataHolder.wishlist_List.add(f);
                    Toast.makeText(v.getContext(), "Food added to the wishlist!", Toast.LENGTH_SHORT).show();
                    f.setAddedWishlist(true);

                    //need to make it change the colour
                    changeIconColor(v, f, holder); //changing the colour from black to red since it is being added to the wishlist
                } else {
                    DataHolder.wishlist_List.remove(f);
                    Toast.makeText(v.getContext(), "Food removed from the wishlist!", Toast.LENGTH_SHORT).show();
                    f.setAddedWishlist(false);
                    changeIconColor(v, f, holder); //changing the colour from red to black since it is being removed from the wishlist
                }


//                if (DataHolder.wishlist_List.contains(f)) {
//                    //System.out.println("HELLLLLLLLLLLLOOOOOOOOO");
//                    DataHolder.wishlist_List.remove(f);
//                    Toast.makeText(v.getContext(), "Food removed from the wishlist!", Toast.LENGTH_SHORT).show();
//                    f.setAddedWishlist(false);
//                }
//
//                changeIconColor(v, f, holder);
            }
        });

    }

    private View getContext() {
        return null;
    }

    public int getItemCount() {
        return data.size();
    }

    public void changeIconColor(View view, Food f, WishList_ViewHolder holder) {
        // Change the color of the icon
        if (f.getAddedWishlist() == true) {
            int newColor = Color.parseColor("#FF0000"); // Set the desired color here
            holder.wishlistButton.setColorFilter(newColor);
        } else if (f.getAddedWishlist() == false) {
            int newColor = Color.parseColor("#000000");
            holder.wishlistButton.setColorFilter(newColor);
        }
        //DataHolder.viewHoldering = holder;

    }

}
