package sg.edu.np.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class NotificationPage extends AppCompatActivity implements SelectListenerFood{

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_page);
            textView = findViewById(R.id.Text);
            String data = getIntent().getStringExtra("data");
            textView.setText(data);

    }


    @Override
    public void onItemClicked(Food food) {
        Intent toCataloguePage = new Intent(NotificationPage.this, CataloguePage.class);
        startActivity(toCataloguePage);
    }
}