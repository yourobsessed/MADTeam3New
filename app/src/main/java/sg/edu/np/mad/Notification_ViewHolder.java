package sg.edu.np.mad;

import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class Notification_ViewHolder extends RecyclerView.ViewHolder {
    TextView notification_Title;
    TextView notification_context;
    CardView cardView;

    public Notification_ViewHolder(View itemView){
        super(itemView);
        notification_Title = itemView.findViewById(R.id.Notification_Title);
        notification_context = itemView.findViewById(R.id.Notification_content);
        cardView = itemView.findViewById(R.id.Foodcardview);
    }
}
