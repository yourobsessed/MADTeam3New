package sg.edu.np.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import java.io.Serializable;

public class map extends AppCompatActivity{ //implements Serializable
    ImageView mapImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        int locationImg = getIntent().getIntExtra("object", 0);
        mapImage.setImageResource(locationImg);

    }
}