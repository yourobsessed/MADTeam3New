package sg.edu.np.mad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StoreAdapter extends RecyclerView.Adapter<StoreView_ViewHolder> {

    private ArrayList<Store> store;

    public StoreAdapter(ArrayList<Store> input){
        this.store = input;
    }

    public StoreView_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_view_viewholder, parent, false);
        StoreView_ViewHolder holder = new StoreView_ViewHolder(view);
        return holder;
    }

    public void onBindViewHolder(StoreView_ViewHolder holder, int position){
        Store s = store.get(position);
        holder.foodName.setText(s.getStoreName());
        holder.foodDescription.setText(s.getDescription());

    }

    public int getItemCount(){

        return store.size();
    }
}
