package com.arniko.activitylifecycleinandroid;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTextName;
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView resultText;

    private ActivityResultLauncher<Intent> mGenContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        Button btn1 = findViewById(R.id.btn1);
        radioGroup = findViewById(R.id.radioGroup);
        resultText = findViewById(R.id.result_section);


        btn1.setOnClickListener(this);

        mGenContent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    String resultString = data.getStringExtra("result");
                    resultText.setText(resultString);

                }
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                radioButton = findViewById(checkedId);
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
                if (radioGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                i.putExtra("name", name);
                i.putExtra("gender", radioButton.getText());
//                startActivity(i);
                mGenContent.launch(i);

                break;
        }
    }
}