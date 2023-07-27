package sg.edu.np.mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);


        createNotificationChannel();
        //scheduleDailyNotification(this);

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
                                    Intent OpenMain = new Intent(LoginPage.this, HomePage.class);
                                    OpenMain.putExtra("Username", UsernameText.getText().toString());
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
        //scheduleDailyNotification(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        scheduleDailyNotification(this);
    }

    @Override
    protected void onPause(){
        super.onPause();
        //scheduleDailyNotification(this);
    }

    @Override
    protected void onStop(){
        super.onStop();
        //scheduleDailyNotification(this);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        //scheduleDailyNotification(this);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Lunch Notification";
            String Description = "Notification that reminds users to have lunch";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("CHANNEL_ID_NOTIFICATION", name, importance);
            channel.setDescription(Description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void scheduleDailyNotification(Context context){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);
        if (calendar.getTimeInMillis() <= System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.setAction("TO CATALOGUE PAGE");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 101, intent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);


        if (Build.VERSION.SDK_INT >= 23){
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
        else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }
        else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }
        else{
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            if (ContextCompat.checkSelfPermission(LoginPage.this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(LoginPage.this, new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 101);
            }
            if (ContextCompat.checkSelfPermission(LoginPage.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(LoginPage.this, new String[]{Manifest.permission.CAMERA}, 101);
            }
            if (ContextCompat.checkSelfPermission(LoginPage.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(LoginPage.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        }

        Log.i("CREATING NOTIFICATIONS", "CREATED NOTIFICATIONS");


    }

}