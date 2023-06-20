package sg.edu.np.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

public class GeneralView_Filter extends AppCompatActivity {

    private Chip chipMakan,chipClub,chipMunch;

    private ChipGroup chipGroupLocation;


    private Button buttonApply;

    private ArrayList<String> selectedChipData;
    /*@Override
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
    }*/

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_view_filter);

        chipGroupLocation = findViewById(R.id.chipGroupLocation);
        buttonApply = findViewById(R.id.buttonApply);
        selectedChipData = new ArrayList<>();

        chipGroupLocation.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                Chip chip = group.findViewById(checkedId);
                if (chip != null) {
                    if (chip.isChecked()) {
                        selectedChipData.add(chip.getText().toString());
                    } else {
                        selectedChipData.remove(chip.getText().toString());
                    }
                }
            }
        });

        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Selected Chips", selectedChipData.toString());
                // Further processing or sending the selected data
            }
        });
    }*/

    //private ChipGroup chipGroupLocation;
    //private Button buttonApply;
    private List<Chip> selectedChips;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_view_filter);

        chipGroupLocation = findViewById(R.id.chipGroupLocation);
        buttonApply = findViewById(R.id.buttonApply);
        selectedChips = new ArrayList<>();

        for (int i = 0; i < chipGroupLocation.getChildCount(); i++) {
            Chip chip = (Chip) chipGroupLocation.getChildAt(i);
            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectedChips.contains(chip)) {
                        selectedChips.remove(chip);
                    } else {
                        selectedChips.add(chip);
                    }
                    chip.setChecked(!chip.isChecked());
                }
            });
        }

        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> selectedChipData = new ArrayList<>();
                for (Chip chip : selectedChips) {
                    selectedChipData.add(chip.getText().toString());
                }
                // Perform further processing or send the selected data
            }
        });
    }

}