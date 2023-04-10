package com.arniko.fragmentsinandroid;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {
    private EditText editText;
    private Button btn;

    private Fragment1Listener listener;

    public interface Fragment1Listener {
        void onFragment1ButtonClick(String text);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);
        editText = view.findViewById(R.id.edit1);
        btn = view.findViewById(R.id.btn1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString().trim();
                if (!text.isEmpty()) {
                    listener.onFragment1ButtonClick(text);
                }
            }
        });


        return view;
    }

    public void updateText() {
        editText.setText("");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Fragment1Listener) {
            listener = (Fragment1Listener) context;
        } else {
            throw new RuntimeException(context.toString() + "Must implement fragment1 listener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

}
