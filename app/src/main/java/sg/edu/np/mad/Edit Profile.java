
package sg.edu.np.mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity{
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextView editusername, editpassword;

    ImageView BackButton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        editusername = findViewById(R.id.changeusername);
        editpassword = findViewById(R.id.changepassword);
        BackButton = findViewById(R.id.back);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        editpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPasswordChangeDialog();
            }
        });

        editusername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUsernameChangeDialog();
            }
        });

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        editpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPasswordChangeDialog();
            }
        });

        editusername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUsernameChangeDialog();
            }
        });

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        editpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPasswordChangeDialog();
            }
        });

        editusername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUsernameChangeDialog();
            }
        });

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    // Show a dialog box where users will authenticate and change password
    private void showPasswordChangeDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_update_password, null);
        final EditText oldpass = view.findViewById(R.id.oldpasslog);
        final EditText newpass = view.findViewById(R.id.newpasslog);
        Button editpass = view.findViewById(R.id.updatepass);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.show();
        editpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldp = oldpass.getText().toString().trim();
                String newp = newpass.getText().toString().trim();
                if (TextUtils.isEmpty(oldp)) {
                    Toast.makeText(Profile.this, "Current Password cant be empty", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(newp)) {
                    Toast.makeText(Profile.this, "New Password cant be empty", Toast.LENGTH_LONG).show();
                    return;
                }
                dialog.dismiss();
                updatePassword(oldp, newp);
            }
        });
    }

    // Authenticate and update new password in database
    private void updatePassword(String oldp, final String newp) {
        databaseReference.child("Accounts").child(DataHolder.username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Authenticate Old Password
                // Comment : Not best practice. Should consider using firebase authentication.
                Account acc = snapshot.getValue(Account.class);
                Boolean query = String.valueOf(acc.password).equals(oldp);
                if (query) {
                    // Set new password in database
                    acc.setPassword(newp);
                    databaseReference.child("Accounts").child(DataHolder.username).setValue(acc);
                    Toast.makeText(Profile.this, "Changed Password", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(Profile.this, "Old Password Wrong", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Profile.this, "Failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    // Show a dialog box where users will change username
    private void showUsernameChangeDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_update_username, null);
        final EditText newusername = view.findViewById(R.id.newusernamelog);
        Button editusername = view.findViewById(R.id.updateusername);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.show();
        editusername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newuname = newusername.getText().toString().trim();
                if (TextUtils.isEmpty(newuname)) {
                    Toast.makeText(Profile.this, "New Username cant be empty", Toast.LENGTH_LONG).show();
                    return;
                }
                dialog.dismiss();
                updateUsername(newuname);
            }
        });
    }

    // Update new username in database
    private void updateUsername(final String newuname) {
        databaseReference.child("Accounts").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Boolean query = false;
                // Check if username exists
                for (DataSnapshot reviewSnapshot : snapshot.getChildren()) {
                    Account account = reviewSnapshot.getValue(Account.class);
                    if (account.username.equals(newuname)) {
                        query = true;
                    }
                }
                if (!query) {
                    // Delete old user account and set a new account with new username
                    // Comment: Not best practice. Currently your database nodes is using username
                    // as its naming convention. But nodes in firebase cannot be renamed so only way
                    // is to copy old node values with new username to a new node.
                    // Recommendation: Restructure your database.
                    Account acc = snapshot.child(DataHolder.username).getValue(Account.class);
                    acc.setUsername(newuname);
                    databaseReference.child("Accounts").child(newuname).setValue(acc);
                    databaseReference.child("Accounts").child(DataHolder.username).removeValue();
                    DataHolder.username = newuname;
                    Toast.makeText(Profile.this, "Changed Username", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(Profile.this, "Username already exists", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Profile.this, "Failed", Toast.LENGTH_LONG).show();
            }
        });
    }
}
