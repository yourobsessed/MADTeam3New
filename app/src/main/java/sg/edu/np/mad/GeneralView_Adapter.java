package sg.edu.np.mad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GeneralView_Adapter extends RecyclerView.Adapter<GeneralView_Viewholder> {
    List<Store> data;

    public GeneralView_Adapter(ArrayList<Store> input){
        this.data = input;
    }

    public void setFilteredList(List<Store> filteredList) {
        // below line is to add our filtered
        // list in our course array list.
        data = filteredList;
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
        Store s = data.get(position);
        holder.storeName.setText(s.getStoreName());
        holder.storeDescription.setText(s.getDescription());
        holder.storeImage.setImageResource(s.getImage());
    }

    public int getItemCount(){
        //System.out.println(data.size());
        return data.size();
    }
}
