package sg.edu.np.mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegisterPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference DatabaseRef = database.getReference();
        EditText UsernameText = findViewById(R.id.UsernameEdit);
        EditText PasswordText = findViewById(R.id.PasswordEdit);


        TextView BackButton = findViewById(R.id.BackButton);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        TextView SignUpButton = findViewById(R.id.SignUpButton);
        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseRef.child("Accounts").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        boolean exists = false;
                        for (DataSnapshot reviewSnapshot : snapshot.getChildren()) {
                            Account account = reviewSnapshot.getValue(Account.class);
                            if(account.Username.equals(UsernameText.getText().toString())){
                                exists = true;
                            }
                        }
                        if(!exists) {
                            DatabaseRef.child("Accounts").push().setValue(new Account(UsernameText.getText().toString(), PasswordText.getText().toString()));
                            Toast.makeText(getApplicationContext(), "Account Created", Toast.LENGTH_SHORT).show();
                        }
                        else if(exists){
                            Toast.makeText(getApplicationContext(), "Username Exists", Toast.LENGTH_SHORT).show();
                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



            }


        });
    }
}