package com.arniko.fragmentsinandroid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment {
    private Fragment2.Fragment2Listener listener;
    private Button btn;
    private TextView textView;

    public interface Fragment2Listener {
        void onFragment2ButtonClick();

    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        textView = view.findViewById(R.id.textView1);
        btn = view.findViewById(R.id.btn2);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                listener.onFragment2ButtonClick();
            }
        });

        return view;


    }
    public void updateTextView(String text){
        textView.setText(text);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Fragment2.Fragment2Listener) {
            listener = (Fragment2.Fragment2Listener) context;
        } else {
            throw new RuntimeException(context.toString() + "Must implement fragment2 listener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
