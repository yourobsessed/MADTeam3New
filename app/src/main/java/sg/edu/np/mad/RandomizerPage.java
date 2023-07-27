package sg.edu.np.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.text.TextUtils;
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

                vibe.vibrate(200);
                List<String> keysAsArray = new ArrayList<String>(foodstalls.keySet());



                Random r = new Random();
                store = keysAsArray.get(r.nextInt(keysAsArray.size()));
                court = foodstalls.get(keysAsArray.get(r.nextInt(keysAsArray.size())));

                changetext.setText(store + " at " + court);



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

