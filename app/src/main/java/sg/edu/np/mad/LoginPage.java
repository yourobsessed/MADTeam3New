package sg.edu.np.mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String password = sharedPreferences.getString("password", "");
        Boolean rememberMe = sharedPreferences.getBoolean("RememberMe", false);

        if(rememberMe){
            Intent OpenMain = new Intent(LoginPage.this, HomePage.class);
            OpenMain.putExtra("Username", username);
            OpenMain.putExtra("Password", password);
            startActivity(OpenMain);
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference DatabaseRef = database.getReference();
        EditText UsernameText = findViewById(R.id.UsernameEdit);
        EditText PasswordText = findViewById(R.id.PasswordEdit);
        TextView existsText = findViewById(R.id.existsText);
        TextView incorrectText = findViewById(R.id.incorrectText);
        TextView emptyText = findViewById(R.id.emptyText);

        existsText.setVisibility(View.INVISIBLE);
        incorrectText.setVisibility(View.INVISIBLE);
        emptyText.setVisibility(View.INVISIBLE);

        Button LoginButton = findViewById(R.id.SignInButton);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(UsernameText.getText().toString().equals("") || PasswordText.getText().toString().equals("")){
                    emptyText.setVisibility(View.VISIBLE);
                    existsText.setVisibility(View.INVISIBLE);
                    incorrectText.setVisibility(View.INVISIBLE);
                }
                else {
                    emptyText.setVisibility(View.INVISIBLE);
                    DatabaseRef.child("Accounts").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            boolean exists = false;
                            String dbPassword = null;
                            for (DataSnapshot reviewSnapshot : snapshot.getChildren()) {
                                Account account = reviewSnapshot.getValue(Account.class);
                                System.out.println(account);
                                if (account.username.equals(UsernameText.getText().toString())) {
                                    exists = true;
                                    DataHolder.username= account.username;
                                    dbPassword = account.password;
                                }
                            }
                            if (exists) {
                                existsText.setVisibility(View.INVISIBLE);
                                if (dbPassword.equals(PasswordText.getText().toString())) {
                                    incorrectText.setVisibility(View.INVISIBLE);
                                    //Toast.makeText(getApplicationContext(), "Account Valid", Toast.LENGTH_SHORT).show();

                                    CheckBox RememberMe = findViewById(R.id.checkBox);

                                    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putBoolean("RememberMe", RememberMe.isChecked());
                                    editor.apply();

                                    Intent OpenMain = new Intent(LoginPage.this, HomePage.class);
                                    OpenMain.putExtra("Username", UsernameText.getText().toString());
                                    OpenMain.putExtra("Password", PasswordText.getText().toString());
                                    startActivity(OpenMain);
                                } else {
                                    incorrectText.setVisibility(View.VISIBLE);
                                    //Toast.makeText(getApplicationContext(), "Password Wrong", Toast.LENGTH_SHORT).show();
                                }
                            } else if (!exists) {
                                existsText.setVisibility(View.VISIBLE);
                                //Toast.makeText(getApplicationContext(), "Username doesn't Exist", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
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

    @Override
    protected void onResume(){
        super.onResume();

    }

    @Override
    protected void onPause(){
        super.onPause();

    }

    @Override
    protected void onStop(){
        super.onStop();

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

    }

}