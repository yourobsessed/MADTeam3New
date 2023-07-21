package sg.edu.np.mad;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class StoreView_ViewHolder extends RecyclerView.ViewHolder {

    ImageView foodImage;
    TextView foodName;
    TextView foodDescription;

    public StoreView_ViewHolder(View itemView){
        super(itemView);
        foodName = itemView.findViewById(R.id.WLNameVH);
        foodImage = itemView.findViewById(R.id.WLImageVH);
        foodDescription = itemView.findViewById(R.id.WLDescriptionVH);
    }
}
