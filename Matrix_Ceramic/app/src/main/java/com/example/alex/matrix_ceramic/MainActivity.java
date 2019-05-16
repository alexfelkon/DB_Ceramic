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
    Matrix_DB db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new Matrix_DB(this);
        db.open();

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

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().equals(""))Toast.makeText(getApplicationContext(), R.string.not_name, Toast.LENGTH_SHORT).show();
                else if (type.getText().toString().equals(""))Toast.makeText(getApplicationContext(), R.string.not_type, Toast.LENGTH_SHORT).show();
                else if (part.getText().toString().equals(""))Toast.makeText(getApplicationContext(), R.string.not_part, Toast.LENGTH_SHORT).show();

                String tempName = name.getText().toString().substring(0,1).toUpperCase() + name.getText().toString().substring(1).toLowerCase();
                String tempType = type.getText().toString();
                String tempPart = part.getText().toString();

                if (db.existMatrix(tempName, tempType, tempPart))
                    Toast.makeText(getApplicationContext(), R.string.exist_matrix, Toast.LENGTH_SHORT).show();
                else {
                    String tempInventory = inventory.getText().toString();
                    String tempWeight = weight.getText().toString();
                    String tempRack = rack.getText().toString();
                    db.add(tempName,tempType,tempPart,tempInventory,tempWeight,tempRack);
                }
            }
        });




    }
}
