package sg.edu.np.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PanelPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_page);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume(){
        super.onResume();

        TextView limitOptText = findViewById(R.id.limitOptText);
        limitOptText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toCataloguePage = new Intent(PanelPage.this, GeneralViewPage.class);
                startActivity(toCataloguePage);
            }
        });

        TextView CatalogueText = findViewById(R.id.fcText);
        CatalogueText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toCataloguePage = new Intent(PanelPage.this, GeneralViewPage.class);
                //will go to the storeView general page first then from the store page then go to the specific food item page
                startActivity(toCataloguePage);
            }
        });

        TextView FeedbackText = findViewById(R.id.fbText);
        FeedbackText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toFeedBackPage = new Intent(PanelPage.this, MainPage.class);
                //to be implemented in stage 2
                startActivity(toFeedBackPage);
            }
        });

        TextView InfoText = findViewById(R.id.InfoText);
        InfoText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toInfoPage = new Intent(PanelPage.this, MainPage.class);
                //to be implemented in stage 2
                startActivity(toInfoPage);
            }
        });

        //returning back to the main page
        ImageView sidePanelButton = findViewById(R.id.SidePanelButton);
        sidePanelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToMainPage = new Intent(PanelPage.this, MainPage.class);
                startActivity(backToMainPage);
            }
        });
    }

}