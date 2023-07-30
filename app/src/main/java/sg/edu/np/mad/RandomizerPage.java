package sg.edu.np.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomizerPage extends AppCompatActivity {
    HashMap<String, String> foodstalls = new HashMap<>();

    private ImageView backbutton;
    private TextView changetext;
    private Button generatebutton;


    String store;
    String court;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randomizer);
        foodstalls = CreateObject(foodstalls);

        changetext = findViewById(R.id.randomchange);
        backbutton = findViewById(R.id.backButton);
        generatebutton = findViewById(R.id.generate);

        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        generatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();
                vibe.vibrate(200);
                List<String> keysAsArray = new ArrayList<String>(foodstalls.keySet());

                Runnable updateUITask = new Runnable() {
                    int counter = 0;

                    @Override
                    public void run() {
                        if (counter < 5) {
                            Random r = new Random();
                            store = keysAsArray.get(r.nextInt(keysAsArray.size()));
                            court = foodstalls.get(store);
                            changetext.setText(store + " at " + court);
                            Log.i("hi", store);

                            counter++; // Increment the counter

                            // Schedule the next update after a delay (e.g., 1000 milliseconds or 1 second)
                            handler.postDelayed(this, 150); // Change 1000 to the desired delay in milliseconds
                        }
                    }
                };

                // Start the updates
                handler.post(updateUITask);
            }
        });


        foodstalls = CreateObject(foodstalls);



        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });


    }



    public HashMap<String, String> CreateObject(HashMap<String,String> foodstalls){
        //creating all the food stores
        //key, value

        //Food Club
        foodstalls.put("Pasta","Food Club");
        foodstalls.put("Salad Bar","Food Club");
        foodstalls.put("King Kong Fried Rice","Food Club");
        foodstalls.put("Canopy Coffee Club","Food Club");
        foodstalls.put("Chicken Rice","Food Club");
        foodstalls.put("Indonesian","Food Club");
        foodstalls.put("Creamy Duck","Food Club");

        //Makan Place
        foodstalls.put("Korean Fusion","Makan Place");
        foodstalls.put("Western Cuisine","Makan Place");
        foodstalls.put("Pick & Bite","Makan Place");
        foodstalls.put("Hotto Neko","Makan Place");
        foodstalls.put("Soul Salad","Makan Place");
        foodstalls.put("Asian Delights","Makan Place");
        foodstalls.put("Japanese Cuisine","Makan Place");
        foodstalls.put("Ban Main & Fish Soup","Makan Place");
        foodstalls.put("Mala Xiang Guo","Makan Place");
        foodstalls.put("Yong Tau Foo","Makan Place");
        foodstalls.put("GoPizza","Makan Place");


        //Munch
        foodstalls.put("Mala Hotpot","Munch");
        foodstalls.put("Salad Bowl","Munch");
        foodstalls.put("Western","Munch");
        foodstalls.put("Claypot","Munch");
        foodstalls.put("Nasi Padang","Munch");
        foodstalls.put("Pizza Arc","Munch");
        foodstalls.put("Korean","Munch");
        foodstalls.put("Tim Sum","Munch");


        return foodstalls;


    }
}

