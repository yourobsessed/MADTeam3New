package sg.edu.np.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class NotificationPage extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_page);
            textView = findViewById(R.id.Text);
            String data = getIntent().getStringExtra("data");
            textView.setText(data);

    }
}