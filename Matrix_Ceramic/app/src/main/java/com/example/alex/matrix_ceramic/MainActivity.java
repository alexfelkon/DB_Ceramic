package com.example.alex.matrix_ceramic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button add, find;
    EditText name, inventory, weight, rack;
    AutoCompleteTextView type, part;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = (Button) findViewById(R.id.btn_add);
        find = (Button)findViewById(R.id.btn_find);
        name = (EditText) findViewById(R.id.et_name);
        inventory = (EditText) findViewById(R.id.et_inventory);
        weight = (EditText) findViewById(R.id.et_weight);
        rack = (EditText) findViewById(R.id.et_rack);
        type = (AutoCompleteTextView) findViewById(R.id.ac_type);
        part = (AutoCompleteTextView) findViewById(R.id.ac_part);
        part.setEnabled(false);

        String[] arrType = getResources().getStringArray(R.array.arr_type);
        String[] arrToilet = getResources().getStringArray(R.array.arr_toilet);
        String[] arrWashbowl = getResources().getStringArray(R.array.arr_washbowl);

        List<String> listWashbowl = Arrays.asList(arrWashbowl);
        List<String> listToilet = Arrays.asList(arrToilet);
        List<String> listType = Arrays.asList(arrType);

        final ArrayAdapter<String> adapterType = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, listType);
        final ArrayAdapter<String> adapterToilet = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, listToilet);
        final ArrayAdapter<String> adapterWashbowl = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, listWashbowl);
        type.setAdapter(adapterType);

        type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String temp = type.getText().toString();
                if (temp.equals("унитаз")|| temp.equals("писуар")){
                    part.setAdapter(adapterToilet);
                    part.setEnabled(true);
                }
                else {
                    part.setAdapter(adapterWashbowl);
                    part.setEnabled(true);
                }
            }
        });




    }
}
