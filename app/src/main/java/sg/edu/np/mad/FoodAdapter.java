package sg.edu.np.mad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<storeView_ViewHolder> {

    ArrayList<Food> food;

    public FoodAdapter(ArrayList<Food> input){
        food = input;
    }

    public storeView_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_store_view_page, parent, false);
        storeView_ViewHolder holder = new storeView_ViewHolder(view);
        return holder;
    }

    public void onBindViewHolder(storeView_ViewHolder holder, int position){
        Food foodItem = food.get(position);
        holder.foodName.setText(foodItem.getFoodName());
        holder.foodDescription.setText(foodItem.getDescription());
        holder.foodImage.setImageResource(foodItem.getFoodImage());

    }

    public int getItemCount(){
        return food.size();
    }
}
