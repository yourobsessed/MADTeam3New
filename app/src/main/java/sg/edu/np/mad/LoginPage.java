package sg.edu.np.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);


        Button LoginButton = findViewById(R.id.SignInButton);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent OpenMain = new Intent(LoginPage.this, MainPage.class);
                startActivity(OpenMain);
            }
        });

        TextView RegisterButton = findViewById(R.id.RegisterButton);
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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