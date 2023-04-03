package com.arniko.eventhandlinginandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    CheckBox checkBox;
    EditText editTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        Button btn1 = findViewById(R.id.btn1);
        Switch switch1 = findViewById(R.id.switch1);
        checkBox = findViewById(R.id.checkBox1);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);


        btn1.setOnClickListener(this);
        switch1.setOnClickListener(this);
        checkBox.setOnClickListener(this);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                Toast.makeText(MainActivity.this, "Selected Value: " + radioButton.getText(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                String name = editTextName.getText().toString().trim();
                if (name.isEmpty()) {
                    editTextName.setError("Name is required");
                    editTextName.requestFocus();
                    return;
                }
                Toast.makeText(this, "Your Entered Name: " + name, Toast.LENGTH_SHORT).show();
                break;
            case R.id.switch1:
                Toast.makeText(this, "Switched", Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkBox1:
                boolean isChecked = checkBox.isChecked();
                Toast.makeText(this, Boolean.toString(isChecked), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}