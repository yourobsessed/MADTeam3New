package sg.edu.np.mad;

import android.os.strictmode.SqliteObjectLeakedViolation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GeneralView_Adapter extends RecyclerView.Adapter<GeneralView_Viewholder> {
    ArrayList<Store> data;

    public GeneralView_Adapter(ArrayList<Store> input){
        data = input;
    }

    @Override
    public GeneralView_Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_file, parent, false);
        return new GeneralView_Viewholder(item);
    }

    @Override
    public void onBindViewHolder(GeneralView_Viewholder holder, int position){
        Store s = data.get(position);
        holder.storeName.setText(s.getStoreName());
        holder.storeDescription.setText(s.getDescription());
        holder.storeImage.setImageResource(s.getImage());
    }

    public int getItemCount(){
        return data.size();
    }
}
