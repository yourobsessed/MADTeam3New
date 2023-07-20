package sg.edu.np.mad;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataHolder {
    public static ArrayList<Food> wishlist_List = new ArrayList<Food>() {
        @Override
        public boolean contains(Object c) {
        if (c == null) {
            return false;
        }
        Food d = (Food) c;
        for (int i = 0; i < size(); i++) {
            if (d.getFoodIndex() == this.get(i).getFoodIndex()) {
                return true;
            }
        }
        return false;
        }
    };


    public static ArrayList<Food> food_List = new ArrayList<>();
    public static ArrayList<Notification> notification_List = new ArrayList<>();

   // public static GeneralView_Viewholder viewHoldering = null;


}