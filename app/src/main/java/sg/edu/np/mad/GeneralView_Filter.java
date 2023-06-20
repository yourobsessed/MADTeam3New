package sg.edu.np.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

public class GeneralView_Filter extends AppCompatActivity {

    private Chip chipMakan,chipClub,chipMunch;

    private ChipGroup chipGroupLocation;


    private Button buttonApply;

    private ArrayList<String> selectedChipData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_view_filter);

        chipMakan=findViewById(R.id.chipMakan);
        chipClub=findViewById(R.id.chipClub);
        chipMunch=findViewById(R.id.chipMunch);
        chipGroupLocation=findViewById(R.id.chipGroupLocation);

        selectedChipData=new ArrayList<>();

        CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    buttonView.setChecked(true);
                    selectedChipData.add(buttonView.getText().toString());
                }
                else
                {
                    buttonView.setChecked(false);
                    selectedChipData.remove(buttonView.getText().toString());
                }
            }
        };
        chipClub.setOnCheckedChangeListener(checkedChangeListener);
        chipMakan.setOnCheckedChangeListener(checkedChangeListener);
        chipClub.setOnCheckedChangeListener(checkedChangeListener);


        buttonApply = findViewById(R.id.buttonApply);
        buttonApply.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("data",selectedChipData.toString());
                setResult(101,resultIntent);
                finish();

            }
        });
    }
}