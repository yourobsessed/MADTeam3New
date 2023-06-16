package sg.edu.np.mad;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class GeneralView_Viewholder extends RecyclerView.ViewHolder {
    TextView foodName;
    TextView foodDescription;
    ImageView foodImage;
    CardView cardView;

    public GeneralView_Viewholder (View itemView){
        super(itemView);
        foodName = itemView.findViewById(R.id.FoodNameVH);
        foodDescription = itemView.findViewById(R.id.FoodDescriptionVH);
        foodImage = itemView.findViewById(R.id.FoodImageVH);
        cardView = itemView.findViewById(R.id.Foodcardview);
    }
}
