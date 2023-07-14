package sg.edu.np.mad;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WishList_Adapter extends RecyclerView.Adapter<WishList_ViewHolder> {
    private List<Food> data;
    private Context context;
    private SelectListenerFood listenerFood;


    public WishList_Adapter(Context context, List<Food> input, SelectListenerFood ListenerFood){
        this.context = context;
        this.data = input;
        this.listenerFood = ListenerFood;

    }

    public WishList_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_viewholder, parent, false);
        return new WishList_ViewHolder(item);
    }

    public void onBindViewHolder(WishList_ViewHolder holder, int position){
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
                //System.out.println("HELLLLLLLLLLLLOOOOOOOOO");
                if (DataHolder.wishlist_List.contains(f)) {
                    //System.out.println("HELLLLLLLLLLLLOOOOOOOOO");
                    DataHolder.wishlist_List.remove(f);
                    Toast.makeText(v.getContext(),"Food removed from the wishlist!", Toast.LENGTH_SHORT).show();


                }
            }
        });

    }

    public int getItemCount() {
        return data.size();
    }

}
