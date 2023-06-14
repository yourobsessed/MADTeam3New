package sg.edu.np.mad;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class GeneralView_Viewholder extends RecyclerView.ViewHolder {
    TextView storeName;
    TextView storeDescription;
    ImageView storeImage;

    public GeneralView_Viewholder (View itemView){
        super(itemView);
        storeName = itemView.findViewById(R.id.txt);
        storeDescription = itemView.findViewById(R.id.txt2);
        storeImage = itemView.findViewById(R.id.img);
    }
}
