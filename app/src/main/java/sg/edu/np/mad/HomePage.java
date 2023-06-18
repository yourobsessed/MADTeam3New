package sg.edu.np.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        String Username = getIntent().getStringExtra("Username");

        Button sidePanelButton = findViewById(R.id.SidePanel);
        sidePanelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toSidePanel = new Intent(HomePage.this, PanelPage.class);
                startActivity(toSidePanel);
            }
        });

        TextView UsernameText = findViewById(R.id.UsernameText);
        UsernameText.setText("Hello " + Username + ",");

        Button MapButton = findViewById(R.id.MapButton);
        MapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openMap = new Intent(HomePage.this, MapPage.class);
                startActivity(openMap);
            }
        });

    }
}