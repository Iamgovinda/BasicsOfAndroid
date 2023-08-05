package com.arniko.sqlitedemo;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.arniko.studentcrud.SQLiteDatabaseHandler;
import com.arniko.studentcrud.Student;

import java.util.ArrayList;

public class CustomStudentList extends BaseAdapter {
    private Activity context;
    private PopupWindow pwindo;
    SQLiteDatabaseHandler db;
    BaseAdapter ba;

    ArrayList<Student> countries;

    public CustomStudentList(Activity context, ArrayList<Student> students, SQLiteDatabaseHandler db) {
        this.context = context;
        this.countries=students;
        this.db = db;
    }


    public static class ViewHolder{
        TextView textViewId;
        TextView textViewStudent;
        TextView textViewPopulation;

        Button editButton;
        Button deleteButton;
    }

    @Override
    public int getCount() {
//        Log.d(countries);

        return countries.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row = view;
        LayoutInflater inflater = context.getLayoutInflater();
        ViewHolder vh;

        if(view == null){
            vh = new ViewHolder();

            row = inflater.inflate(R.layout.new_item, null, true);

            vh.textViewId = (TextView)row.findViewById(R.id.textViewId);
            vh.textViewStudent = (TextView)row.findViewById(R.id.textViewStudent);
            vh.textViewPopulation = (TextView) row.findViewById(R.id.textViewPopulation);
            vh.editButton = (Button) row.findViewById(R.id.edit);
            vh.deleteButton = (Button) row.findViewById(R.id.delete);


            row.setTag(vh);



        }
        else{
            vh = (ViewHolder) view.getTag();
        }
        Log.d("Values Countries", String.valueOf(countries.get(i)));
        vh.textViewStudent.setText(countries.get(i).countryName);
        vh.textViewId.setText("" + countries.get(i).id);
        vh.textViewPopulation.setText("" + countries.get(i).population);
        final int positionPopup = i;
        vh.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editPopup(positionPopup);
            }
        });
        vh.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteStudent(countries.get(positionPopup));
                countries = (ArrayList<Student>) db.getAllCountries();
                notifyDataSetChanged();
            }
        });

        return row;
    }


    public void editPopup(final int positionPopup){
        LayoutInflater inflater = context.getLayoutInflater();
        View layout = inflater.inflate(R.layout.edit_popup, (ViewGroup) context.findViewById(R.id.popup_element));
        pwindo = new PopupWindow(layout, 600, 670, true);
        pwindo.showAtLocation(layout, Gravity.CENTER, 0,0);
        final EditText countryEdit = (EditText) layout.findViewById(R.id.editTextStudent);
        final EditText populationEdit = (EditText) layout.findViewById(R.id.editTextPopulation);
        countryEdit.setText(countries.get(positionPopup).getStudentName());
        populationEdit.setText("" + countries.get(positionPopup).getPopulation());
        Button save = (Button) layout.findViewById(R.id.save_popup);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String countryStr = countryEdit.getText().toString();
                String population = populationEdit.getText().toString();
                Student country = countries.get(positionPopup);
                country.setStudentName(countryStr);
                country.setPopulation(Long.parseLong(population));
                db.updateStudent(country);
                countries = (ArrayList<Student>) db.getAllCountries();
                notifyDataSetChanged();
                pwindo.dismiss();
            }
        });
    }
}
