package sg.edu.np.mad;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class FoodCourt_ViewHolder extends RecyclerView.ViewHolder {
    TextView foodCourtName;
    TextView foodCourtDescription;
    ImageView foodCourtImage;

    public FoodCourt_ViewHolder(View itemView){
        super(itemView);
        foodCourtImage = itemView.findViewById(R.id.FoodCourtImageVH);
        foodCourtName = itemView.findViewById(R.id.name);
        foodCourtDescription = itemView.findViewById(R.id.FoodCourtDescriptionVH);
    }
}
