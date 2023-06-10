package sg.edu.np.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button LoginButton = findViewById(R.id.LoginButton);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to execute when the button is clicked
                Intent OpenMain = new Intent(MainActivity.this, MainPage.class);
                startActivity(OpenMain);
            }
        });

        TextView RegisterButton = findViewById(R.id.RegisterButton);
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to execute when the button is clicked
                Intent OpenRegister = new Intent(MainActivity.this, RegisterPage.class);
                startActivity(OpenRegister);
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(title, "Starting Acct Creation");

        EditText etUsername = findViewById(R.id.editUsername);
        EditText etPassword = findViewById(R.id.editPassword);
        Button createButton = findViewById(R.id.LoginButton);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                Bundle extras = new Bundle();
                extras.putString("Username", username);
                extras.putString("Password", password);
                Intent activityName = new Intent(MainActivity.this, HomePage.class);
                activityName.putExtras(extras);
                startActivity(activityName);
            }

        });
    }}
