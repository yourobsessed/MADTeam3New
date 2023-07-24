package sg.edu.np.mad;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.internal.bind.ArrayTypeAdapter;

import java.util.ArrayList;

public class Notification_Adapter extends RecyclerView.Adapter<Notification_ViewHolder> implements SelectListenerFood {
    ArrayList<Notification> data;
    Context context;
    SelectListenerFood selectListenerFood;

    public Notification_Adapter(ArrayList<Notification> input){
        this.data = input;
        //this.selectListenerFood = SelectListenerFood;
    }

    public Notification_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_viewholder, parent, false);
        return new Notification_ViewHolder(item);
    }

    public void onBindViewHolder(Notification_ViewHolder holder, int position){
        Notification n = data.get(position);
        holder.notification_Title.setText(n.title);
        holder.notification_context.setText(n.content);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toNotification = new Intent(v.getContext(), CataloguePage.class);
                toNotification.putExtra("FoodName",  n.foodItem.getFoodName());
                toNotification.putExtra("FoodPrice",  n.foodItem.getPrice());
                toNotification.putExtra("FoodCalories",  n.foodItem.getCalories());
                toNotification.putExtra("FoodImg", n.foodItem.getFoodImage1());
                toNotification.putExtra("FoodImg2",  n.foodItem.getFoodImage2());
                toNotification.putExtra("LocationImg",  n.foodItem.getLocationImage());
                toNotification.putExtra("storeLocation",  n.foodItem.getLocation());
                context.startActivity(toNotification);
            }
        });
    }

    public int getItemCount(){
        return data.size();
    }

    @Override
    public void onItemClicked(Food food) {
        // Implement the logic when a food item is clicked.
        // You can access the clicked food item using the 'food' parameter.
        // For example, you might want to show a notification or perform some action.
    }

}
