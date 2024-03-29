package sg.edu.np.mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.*;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RegisterPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference DatabaseRef = database.getReference();
        EditText UsernameText = findViewById(R.id.UsernameEdit);
        EditText PasswordText = findViewById(R.id.PasswordEdit);
        TextView ExistsText = findViewById(R.id.ExistsText);
        TextView EmptyText = findViewById(R.id.EmptyText);

        ExistsText.setVisibility(View.INVISIBLE);
        EmptyText.setVisibility(View.INVISIBLE);

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
                if(UsernameText.getText().toString().equals("") || PasswordText.getText().toString().equals("")){
                    EmptyText.setVisibility(View.VISIBLE);
                    ExistsText.setVisibility(View.INVISIBLE);
                }
                else {
                    EmptyText.setVisibility(View.INVISIBLE);
                    String username = UsernameText.getText().toString();
                    DatabaseReference userRef = DatabaseRef.child("Accounts");
                    userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            boolean exists = false;
                            for (DataSnapshot reviewSnapshot : snapshot.getChildren()) {
                                Account account = reviewSnapshot.getValue(Account.class);
                                System.out.println(account.username);
                                if (account.username.equals(UsernameText.getText().toString())) {
                                    exists = true;
                                }
                            }
                            if (!exists) {
                                ExistsText.setVisibility(View.INVISIBLE);
                                ArrayList<Integer> wishlist = new ArrayList<>();
                                wishlist.add(0);

                                Account newAccount = new Account();
                                newAccount.setUsername(username);
                                newAccount.setPassword(PasswordText.getText().toString());
                                newAccount.setWishlist(wishlist);

                                userRef.child(username).setValue(newAccount);

                                //DatabaseRef.child("Accounts").push().setValue(new Account(UsernameText.getText().toString(), PasswordText.getText().toString(), wishlist));
                                Toast.makeText(getApplicationContext(), "Account Created", Toast.LENGTH_SHORT).show();

                                DataHolder.username= username;

                                Intent OpenMain = new Intent(RegisterPage.this, HomePage.class);
                                OpenMain.putExtra("Username", UsernameText.getText().toString());
                                startActivity(OpenMain);
                            } else if (exists) {
                                ExistsText.setVisibility(View.VISIBLE);
                                Toast.makeText(getApplicationContext(), "Username Exists", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }


            }


        });
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}