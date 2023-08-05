package com.arniko.messagepassthroughintent;// DisplayActivity.java
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayActivity extends Activity {
    private TextView tvName, tvAge, tvGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        tvName = findViewById(R.id.tvName);
        tvAge = findViewById(R.id.tvAge);
        tvGender = findViewById(R.id.tvGender);

        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            int age = intent.getIntExtra("age", 0);
            boolean isMale = intent.getBooleanExtra("isMale", false);

            tvName.setText("Name: " + name);
            tvAge.setText("Age: " + age);
            tvGender.setText("Gender: " + (isMale ? "Male" : "Female"));
        }
    }
}
