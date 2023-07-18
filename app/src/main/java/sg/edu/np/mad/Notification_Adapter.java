package sg.edu.np.mad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.internal.bind.ArrayTypeAdapter;

import java.util.ArrayList;

public class Notification_Adapter extends RecyclerView.Adapter<Notification_ViewHolder> {
    ArrayList<Notification> data;

    public Notification_Adapter(ArrayList<Notification> input){
        data = input;
    }

    public Notification_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_viewholder, parent, false);
        return new Notification_ViewHolder(item);
    }

    public void onBindViewHolder(Notification_ViewHolder holder, int position){
        Notification n = data.get(position);
        holder.notification_Title.setText(n.Title);
        holder.notification_context.setText(n.Content);
    }

    public int getItemCount(){
        return data.size();
    }

}
