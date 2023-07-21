package sg.edu.np.mad;

import java.util.ArrayList;

public class Account {

    public String Username;
    public String Password;

    public ArrayList<Integer> wishlist;

    public Account(String Username, String Password,ArrayList<Integer> wishlist) {
        this.Username = Username;
        this.Password = Password;
        this.wishlist = wishlist;

    }

    public Account() {
    }

    public String toString() {
        return String.format("%s %s",this.Username,this.Password);
    }
}
