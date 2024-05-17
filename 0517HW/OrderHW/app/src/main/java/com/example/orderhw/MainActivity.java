package com.example.orderhw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerMealType;
    private LinearLayout buttonContainer;
    private TextView selected_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        spinnerMealType = findViewById(R.id.spinner_meal_type);
        buttonContainer = findViewById(R.id.button_container);
        selected_item = findViewById(R.id.selected_item);

        // Setup Spinner Adapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.meal_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMealType.setAdapter(adapter);

        // Spinner item selection handling
        spinnerMealType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateButtons(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void updateButtons(int mealType) {
        buttonContainer.removeAllViews();  // Clear previous buttons

        int arrayId;
        switch (mealType) {
            case 0:
                arrayId = R.array.main_dish_options;  // Index matches meal_types array
                break;
            case 1:
                arrayId = R.array.side_dish_options;
                break;
            case 2:
                arrayId = R.array.drink_options;
                break;
            default:
                return; // In case of an unexpected value
        }

        String[] items = getResources().getStringArray(arrayId);
        for (String item : items) {
            Button button = new Button(this);
            button.setText(item);
            button.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            button.setOnClickListener(v -> selected_item.setText("Selected: " + item));
            buttonContainer.addView(button);
        }
    }
}
