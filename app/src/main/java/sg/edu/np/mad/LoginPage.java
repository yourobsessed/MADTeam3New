package sg.edu.np.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);


        Button LoginButton = findViewById(R.id.LoginButton);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to execute when the button is clicked
                Intent OpenMain = new Intent(LoginPage.this, MainPage.class);
                startActivity(OpenMain);
            }
        });

        TextView RegisterButton = findViewById(R.id.RegisterButton);
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to execute when the button is clicked
                Intent OpenRegister = new Intent(LoginPage.this, RegisterPage.class);
                startActivity(OpenRegister);
            }
        });

    }

    @Override
    protected void onStart() {

        super.onStart();
    }

}