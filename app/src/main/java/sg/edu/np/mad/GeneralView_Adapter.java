package sg.edu.np.mad;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GeneralView_Adapter extends RecyclerView.Adapter<GeneralView_Viewholder> {
    private Context context;
    private List<Food> data;
    private SelectListenerFood listenerFood;
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
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.general_view_viewholder, parent, false);
        return new GeneralView_Viewholder(item);
    }

    @Override
    public void onBindViewHolder(GeneralView_Viewholder holder, int position){
        Food f = data.get(position);
        holder.foodName.setText(f.getFoodName());
        holder.foodDescription.setText(f.getDescription());
        holder.foodImage.setImageResource(f.getFoodImage());
        System.out.println(f);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toCatalogue = new Intent(v.getContext(), CataloguePage.class);
                toCatalogue.putExtra("FoodName", data.get(holder.getAdapterPosition()).getFoodName());
                toCatalogue.putExtra("FoodPrice", data.get(holder.getAdapterPosition()).getPrice());
                toCatalogue.putExtra("FoodCalories", data.get(holder.getAdapterPosition()).getCalories());
                toCatalogue.putExtra("FoodImg", f.getFoodImage());
                context.startActivity(toCatalogue);

            }
        });
    }

    public int getItemCount(){
        //System.out.println(data.size());
        return data.size();
    }


}
