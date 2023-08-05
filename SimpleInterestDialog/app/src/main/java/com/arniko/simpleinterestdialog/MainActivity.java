package com.arniko.simpleinterestdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //custom dialog
        btn = findViewById(R.id.custom_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog();
                // logic to show custom dialog
            }
        });
    }
    private  void showCustomDialog(){
        View dialogView = getLayoutInflater().inflate(R.layout.custom_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        builder.setCancelable(true);
        builder.setTitle("Calculate the simple Interest");

        EditText principle = dialogView.findViewById(R.id.principle);
        EditText time = dialogView.findViewById(R.id.time);
        EditText rate = dialogView.findViewById(R.id.rate);

        Button calculate = dialogView.findViewById(R.id.calculateButton);
        TextView result = dialogView.findViewById(R.id.result);


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int p = Integer.parseInt(principle.getText().toString());
                int t = Integer.parseInt(time.getText().toString());
                int r = Integer.parseInt(rate.getText().toString());

                int interest = p*t*r/100;
                result.setText("Interest is " + interest);
            }
        });
        // Show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}