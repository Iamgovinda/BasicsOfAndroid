package com.arniko.messagepassthroughintent;// InputActivity.java
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends Activity {
    private EditText etName, etAge;
    private Switch switchGender;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        switchGender = findViewById(R.id.switchGender);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                int age = Integer.parseInt(etAge.getText().toString());
                boolean isMale = switchGender.isChecked();

                Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("age", age);
                intent.putExtra("isMale", isMale);
                startActivity(intent);
            }
        });
    }
}
