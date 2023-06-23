package sg.edu.np.mad;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class CustomAdapterFood extends ArrayAdapter<Food> {
    private List<Food> dataSet;

    public CustomAdapterFood(Context context, List<Food> data) {
        super(context, 0, data);
        dataSet = data;
    }

    public void setData(List<Food> newData) {
        dataSet.clear();
        dataSet.addAll(newData);
        notifyDataSetChanged();
    }

    // Other adapter methods (getView, getItem, etc.) go here
}
