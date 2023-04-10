package com.arniko.fragmentsinandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Fragment1.Fragment1Listener, Fragment2.Fragment2Listener {
    private Fragment1 fragment1;
    private Fragment2 fragment2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container1, fragment1).replace(R.id.fragment_container2, fragment2).
                commit();

    }

    @Override
    public void onFragment1ButtonClick(String text) {
        fragment2.updateTextView(text);
    }

    @Override
    public void onFragment2ButtonClick() {
        fragment1.updateText();
    }
}