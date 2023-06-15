package sg.edu.np.mad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodCourt_Adapter extends RecyclerView.Adapter<FoodCourt_ViewHolder> {
    ArrayList<FoodCourt> data;
    private SelectListenerFC listenerFC;

    public FoodCourt_Adapter(ArrayList<FoodCourt> input, SelectListenerFC listenerFC){
        this.data = input;
        this.listenerFC = listenerFC;
    }

    public FoodCourt_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_court_viewholder, parent, false);
        return new FoodCourt_ViewHolder(item);
    }

    public void onBindViewHolder(FoodCourt_ViewHolder holder, int position){
        FoodCourt fc = data.get(position);
        holder.foodCourtName.setText(fc.getFoodCourtName());
        holder.foodCourtDescription.setText(fc.getFoodCourtDesc());
        holder.foodCourtImage.setImageResource(fc.getFoodCourtImage());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerFC.onItemClicked(fc);
            }
        });
    }

    public int getItemCount(){
        return data.size();
    }
}
