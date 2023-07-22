package sg.edu.np.mad;

import java.util.ArrayList;
import java.util.HashMap;

public class Account {

    public String username;
    public String password;

    //public ArrayList<HashMap<Integer, Integer>> wishlist;
    public ArrayList<Integer> wishlist;
    public Account(String Username, String Password,ArrayList<Integer> Wishlist) {
        this.username = Username;
        this.password = Password;
        this.wishlist = Wishlist;

    }

    public void setUsername(String Username) {
        this.username = Username;
    }

    public void setPassword(String Password) {
        this.password = Password;
    }

    public void setWishlist(ArrayList<Integer> wishlist) {
        this.wishlist = wishlist;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Integer> getWishlist() {
        return wishlist;
    }

    public Account() {
    }

    public String toString() {
        return String.format("%s %s",this.username,this.password);
    }
}
