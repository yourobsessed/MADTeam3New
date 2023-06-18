package sg.edu.np.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Button sidePanelButton = findViewById(R.id.SidePanel);
        sidePanelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toSidePanel = new Intent(HomePage.this, PanelPage.class);
                startActivity(toSidePanel);
            }
        });

    }
}