package sg.edu.np.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RandomizerPage extends AppCompatActivity {

    //private Chip chipMakan,chipClub,chipMunch;

    //private ChipGroup chipGroupLocation;
    Map<String, String> foodstalls = new HashMap<>();



    //private Button buttonApply;

    //private ArrayList<String> selectedChipData = new ArrayList<>();

    //ArrayList<Food> filteredFoodList= new ArrayList<>();
    //ArrayList<Food> foodList= DataHolder.food_List;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randomizer);
        DataHolder.food_List = CreateObject(foodstalls);
        /*chipMakan=findViewById(R.id.chipMakan);
        chipClub=findViewById(R.id.chipClub);
        chipMunch=findViewById(R.id.chipMunch);
        chipGroupLocation=findViewById(R.id.chipGroupLocation);

        selectedChipData=new ArrayList<>();
        ArrayList<Food> filteredList;

        filteredList = (ArrayList<Food>) getIntent().getSerializableExtra("filteredList");
        for (int i = 0; i < chipGroupLocation.getChildCount(); i++) {
            View child = chipGroupLocation.getChildAt(i);
            if (child instanceof Chip) {
                Chip chip = (Chip) child;
                chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        registerFilterChanged();
                    }
                });
            }
        }
        chipClub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chipClub.isChecked()){
                    for (Food food: foodList){
                        if (food.getLocation() == "Food Club"){
                            filteredFoodList.add(food);
                        }
                        else{
                            break;
                        }
                    }
                }
            }
        });

        chipMakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chipMakan.isChecked()){
                    for (Food food: foodList){
                        if (food.getLocation() == "Makan Place"){
                            filteredFoodList.add(food);
                        }
                        else{
                            break;
                        }
                    }
                }
            }
        });
        chipMakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chipMakan.isChecked()){
                    makan = true;
                }
                else if (!chipMakan.isChecked()) {
                    makan = false;
                }
                limitoption();
            }
        });
        chipClub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chipClub.isChecked()){
                    club = true;
                }
                else if (!chipClub.isChecked()) {
                    club = false;
                }
                limitoption();
            }
        });
        chipMunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chipMunch.isChecked()){
                    munch = true;
                }
                else if (!chipMunch.isChecked()) {
                    munch = false;
                }
                limitoption();
            }
        });

        /*chipMunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chipMunch.isChecked()){
                    for (Food food: foodList){
                        if (food.getLocation() == "Munch"){
                            filteredFoodList.add(food);
                        }
                        else{
                            break;
                        }
                    }
                }
            }
        });*/

        /*Button ButtonApply = findViewById(R.id.buttonApply);
        ButtonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toGeneralView = new Intent(RandomizerPage.this, GeneralViewPage.class);
                toGeneralView.putExtra("filteredList", filteredList);
                startActivity(toGeneralView);

            }
        });
        /*CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
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

        /*chipClub.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                Chip chip = group.findViewById(checkedId);
                if (chip != null) {
                    final String chipText = chip.getText().toString();

                     selectedChipData<Food> filteredData = prepareData().stream().filter(new Predicate<Food>() {
                        @Override
                        public boolean test(Food item) {
                            return item.getNoodle().equals(chipText);
                        }
                    }).collect(Collectors.toList());

                    adapter.setData(filteredData);
                } else {
                    adapter.setData(prepareData());
                }
            }
        });*/



        /*buttonApply = findViewById(R.id.buttonApply);
        buttonApply.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent(GeneralView_Filter.this, GeneralViewPage.class);
                resultIntent.putExtra("data",selectedChipData.toString());
                setResult(101,resultIntent);
                finish();
                startActivity(resultIntent);
            }
        });*/
    }
    /*
    private void registerFilterChanged() {
        ArrayList<Integer> checkedIds = new ArrayList<>();
        List<CharSequence> titles = new ArrayList<>();

        for (int i = 0; i < chipGroupLocation.getChildCount(); i++) {
            View childView = chipGroupLocation.getChildAt(i);
            if (childView instanceof Chip) {
                Chip chip = (Chip) childView;
                if (chip.isChecked()) {
                    checkedIds.add(chip.getId());
                    titles.add(chip.getText());
                }
            }
        }

        String text;
        if (!titles.isEmpty()) {
            text = TextUtils.join(", ", titles);
        } else {
            text = "No Choice";
        }

        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();

    }


    }

    //private ChipGroup chipGroupLocation;
    //private Button buttonApply;
    /*private List<Chip> selectedChips;
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
    }*/
    /*
    private void limitoption() {
        List<Food> filteredList = new ArrayList<>();
        for (Food food : foodList) {
            filteredList.add(food);
        }
        //Toast.makeText(this, "" + filteredList.get(0).getHalal(), Toast.LENGTH_SHORT).show();

        List<Food> itemsToRemove = new ArrayList<>();

        for (Food food : filteredList) {
            if (makan && food.getLocation()!="Makan Place") {
                itemsToRemove.add(food);
            }
            if (munch && food.getLocation()!="Munch") {
                itemsToRemove.add(food);
            }
            if (club && food.getLocation()!="Food Club") {
                itemsToRemove.add(food);
            }

        }

        filteredList.removeAll(itemsToRemove);

    }*/


    public Map<String, String> CreateObject(HashMap<> foodstalls){
        //creating all the food stores
        //key, value

        //Food Club
        foodstalls.put("Pasta","Food Club");
        foodstalls.put("Salad Bar","Food Club");
        foodstalls.put("King Kong Fried Rice","Food Club");
        foodstalls.put("Canopy Coffee Club","Food Club");
        foodstalls.put("Chicken Rice","Food Club");
        foodstalls.put("Indonesian","Food Club");
        foodstalls.put("Creamy Duck","Food Club");

        //Makan Place
        foodstalls.put("Korean Fusion""Makan Place",);
        foodstalls.put("Western Cuisine","Makan Place",);
        foodstalls.put("Pick & Bite","Makan Place",);
        foodstalls.put("Hotto Neko","Makan Place",);

        //Munch
        foodstalls.put("Mala Hotpot","Munch");
        foodstalls.put("Salad Bowl","Munch");
        foodstalls.put("Western","Munch");
        foodstalls.put("Claypot","Munch");
        foodstalls.put("Nasi Padang","Munch");
        foodstalls.put("Pizza Arc","Munch");
        foodstalls.put("Korean","Munch");
        foodstalls.put("Tim Sum","Munch");


        return foodstalls;


    }
}