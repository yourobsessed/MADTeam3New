package sg.edu.np.mad;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class WishList_ViewHolder extends RecyclerView.ViewHolder{
    TextView Name;
    TextView Desc;
    ImageView Image;
    ImageView wishlistButton;
    CardView cardView;

    public WishList_ViewHolder(View itemView){
        super(itemView);
        Name = itemView.findViewById(R.id.WLNameVH);
        Desc = itemView.findViewById(R.id.WLDescriptionVH);
        wishlistButton = itemView.findViewById(R.id.WLButton);
        Image = itemView.findViewById(R.id.WLImageVH);
        cardView = itemView.findViewById(R.id.WLcardview);

    }


}
