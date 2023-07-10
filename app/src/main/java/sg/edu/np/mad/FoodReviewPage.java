package sg.edu.np.mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FoodReviewPage extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 1337;
    private static final int REQUEST_CAMERA_PERMISSION = 100;

    private int stars = 100;
    private String desc = "";
    private Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_review_page);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        TextView foodtext = findViewById(R.id.foodname);
        foodtext.setText(getIntent().getExtras().getString("foodname"));

        ImageView backButton = findViewById(R.id.imageView9);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ImageView openCameraButton = findViewById(R.id.openCameraButton);
        openCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(FoodReviewPage.this, "android.permission.CAMERA") != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(FoodReviewPage.this, new String[]{"android.permission.CAMERA"}, REQUEST_CAMERA_PERMISSION);
                } else {
                    startCameraActivity();
                }
            }
        });



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference DatabaseRef = database.getReference();
        EditText DescriptionEdit = findViewById(R.id.DescriptionEdit);
        TextView sendbutton = findViewById(R.id.sendbutton);
        sendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseRef.child("Reviews").push().setValue(new FoodReview(getIntent().getExtras().getString("foodname"), stars, DescriptionEdit.getText().toString(), username));
                finish();
            }
        });


        ImageView star1 = findViewById(R.id.star1);
        ImageView star2 = findViewById(R.id.star2);
        ImageView star3 = findViewById(R.id.star3);
        ImageView star4 = findViewById(R.id.star4);
        ImageView star5 = findViewById(R.id.star5);
        star1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stars = 20;
                star2.setImageDrawable(getResources().getDrawable(R.drawable.baseline_star_24_grey));
                star3.setImageDrawable(getResources().getDrawable(R.drawable.baseline_star_24_grey));
                star4.setImageDrawable(getResources().getDrawable(R.drawable.baseline_star_24_grey));
                star5.setImageDrawable(getResources().getDrawable(R.drawable.baseline_star_24_grey));
            }
        });
        star2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stars = 40;
                star2.setImageDrawable(getResources().getDrawable(R.drawable.baseline_star_24));
                star3.setImageDrawable(getResources().getDrawable(R.drawable.baseline_star_24_grey));
                star4.setImageDrawable(getResources().getDrawable(R.drawable.baseline_star_24_grey));
                star5.setImageDrawable(getResources().getDrawable(R.drawable.baseline_star_24_grey));
            }
        });
        star3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stars = 60;
                star2.setImageDrawable(getResources().getDrawable(R.drawable.baseline_star_24));
                star3.setImageDrawable(getResources().getDrawable(R.drawable.baseline_star_24));
                star4.setImageDrawable(getResources().getDrawable(R.drawable.baseline_star_24_grey));
                star5.setImageDrawable(getResources().getDrawable(R.drawable.baseline_star_24_grey));
            }
        });
        star4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stars = 80;
                star2.setImageDrawable(getResources().getDrawable(R.drawable.baseline_star_24));
                star3.setImageDrawable(getResources().getDrawable(R.drawable.baseline_star_24));
                star4.setImageDrawable(getResources().getDrawable(R.drawable.baseline_star_24));
                star5.setImageDrawable(getResources().getDrawable(R.drawable.baseline_star_24_grey));
            }
        });
        star5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stars = 100;
                star2.setImageDrawable(getResources().getDrawable(R.drawable.baseline_star_24));
                star3.setImageDrawable(getResources().getDrawable(R.drawable.baseline_star_24));
                star4.setImageDrawable(getResources().getDrawable(R.drawable.baseline_star_24));
                star5.setImageDrawable(getResources().getDrawable(R.drawable.baseline_star_24));
            }
        });

    }

    private void startCameraActivity() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCameraActivity();
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            image = (Bitmap) data.getExtras().get("data");
            ImageView imageView = findViewById(R.id.imageView17);
            imageView.setImageBitmap(image);
            imageView.setElevation(5);

        }
    }
}
