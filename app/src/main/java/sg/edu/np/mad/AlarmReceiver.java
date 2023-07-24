package sg.edu.np.mad;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent notificationToCat = new Intent(context, CataloguePage.class);
        notificationToCat.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

//        Food selectedFood = randomPicker(); //calling for method
//        notificationToCat.putExtra("FoodName", selectedFood.getFoodName());
//        notificationToCat.putExtra("FoodPrice", selectedFood.getPrice());
//        notificationToCat.putExtra("FoodCalories", selectedFood.getCalories());
//        notificationToCat.putExtra("FoodImg", selectedFood.getFoodImage1());
//        notificationToCat.putExtra("FoodImg2", selectedFood.getFoodImage2());
//        notificationToCat.putExtra("LocationImg", selectedFood.getLocationImage());
//        notificationToCat.putExtra("storeLocation", selectedFood.getLocation());
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationToCat, PendingIntent.FLAG_MUTABLE);


        String channelID = "CHANNEL_ID_NOTIFICATION";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,channelID);

        builder.setSmallIcon(R.drawable.notification);
        builder.setContentTitle("Time for Lunch!");
        builder.setContentText("Try out this dish during your lunch break!");
        builder.setAutoCancel(true);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setContentIntent(pendingIntent);


        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = NotificationManager.getNotificationChannel(channelID);
            if (notificationChannel == null){
                int importance = NotificationManager.IMPORTANCE_HIGH;
                notificationChannel = new NotificationChannel(channelID, "Some Description", importance);

                notificationChannel.setLightColor(Color.GREEN);
                notificationChannel.enableVibration(true);
                notificationManagerCompat.createNotificationChannel(notificationChannel);
            }
        }

        notificationManagerCompat.notify(123, builder.build());
    }
}
