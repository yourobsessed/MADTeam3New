package sg.edu.np.mad;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class AlarmReceiver extends BroadcastReceiver {
    private static final int NOTIFICATION_ID = 1;
    public ArrayList<Food> foodList = new ArrayList<>();
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent notificationToCat = new Intent(context, CataloguePage.class);
        notificationToCat.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        Food selectedFood = new Food();

        notificationToCat.putExtra("FoodName", selectedFood.getFoodName());
        notificationToCat.putExtra("FoodPrice", selectedFood.getPrice());
        notificationToCat.putExtra("FoodCalories", selectedFood.getCalories());
        notificationToCat.putExtra("FoodImg", selectedFood.getFoodImage1());
        notificationToCat.putExtra("FoodImg2", selectedFood.getFoodImage2());
        notificationToCat.putExtra("LocationImg", selectedFood.getLocationImage());
        notificationToCat.putExtra("storeLocation", selectedFood.getLocation());
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationToCat, PendingIntent.FLAG_IMMUTABLE);


        String channelID = "CHANNEL_ID_NOTIFICATION";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelID)
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("Time for Lunch!")
                .setContentText("Try out this dish during your lunch break!")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            if (!notificationManagerCompat.areNotificationsEnabled()) {
                // Notifications are not enabled, so you may want to handle this case accordingly
                // For example, you could show a toast message or a dialog to prompt the user to enable notifications.
                Toast.makeText(context, "Notification permission not allowed", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
    }


}
