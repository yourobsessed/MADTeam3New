package sg.edu.np.mad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class storeView_ViewHolder extends RecyclerView.ViewHolder {

    ImageView foodImage;
    TextView foodName;
    TextView foodDescription;

    public storeView_ViewHolder(View itemView){
        super(itemView);
        foodName = itemView.findViewById(R.id.FoodNameVH);
        foodImage = itemView.findViewById(R.id.FoodImageVH);
        foodDescription = itemView.findViewById(R.id.FoodDescriptionVH);
    }

}