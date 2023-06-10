package sg.edu.np.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Intent receivingIntent = getIntent();
        String message = receivingIntent.getStringExtra("Username");
        String message2 = receivingIntent.getStringExtra("Password");
        Log.i("Username", message);
        Log.i("Password:",message2);
    }

}