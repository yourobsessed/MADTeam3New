package sg.edu.np.mad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FoodReviewAdapter extends RecyclerView.Adapter<FoodReviewViewHolder> {
    private List<FoodReview> itemList;
    private Context context;

    public FoodReviewAdapter(List<FoodReview> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public FoodReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_review_item, parent, false);
        return new FoodReviewViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FoodReviewViewHolder holder, int position) {
        FoodReview item = itemList.get(position);
        holder.bind(item, context);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}