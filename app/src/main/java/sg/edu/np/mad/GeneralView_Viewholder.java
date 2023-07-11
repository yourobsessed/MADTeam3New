package sg.edu.np.mad;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class GeneralView_Viewholder extends RecyclerView.ViewHolder{
    TextView foodName;
    TextView foodDescription;
    ImageView foodImage;
    TextView foodCourtLocation;
    ImageView wishlistButton;
    CardView cardView;

    public GeneralView_Viewholder (View itemView) {
        super(itemView);
        foodName = itemView.findViewById(R.id.WLNameVH);
        foodDescription = itemView.findViewById(R.id.WLDescriptionVH);
        foodImage = itemView.findViewById(R.id.WLImageVH);
        cardView = itemView.findViewById(R.id.Foodcardview);
        foodCourtLocation = itemView.findViewById(R.id.storeLocation);
        wishlistButton = itemView.findViewById(R.id.WLButton);


    }


}
