package com.arniko.activitylifecycleinandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView textView1 = findViewById(R.id.textView2);
        TextView textView2 = findViewById(R.id.textView4);
        Button button = findViewById(R.id.btnse1);


        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String gender = intent.getStringExtra("gender");

        textView1.setText(name);
        textView2.setText(gender);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SecondActivity.this, MainActivity.class);
                i.putExtra("result", "This is the message sent from second activity");
                setResult(RESULT_OK, i);
                finish();
            }
        });





    }
}