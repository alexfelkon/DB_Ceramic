package com.example.alex.matrix_ceramic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button add, find;
    EditText name, inventory, weight, rack;
    AutoCompleteTextView type, part;
    Matrix_DB db;

    public String setName (){
        return name.getText().toString().substring(0,1).toUpperCase() + name.getText().toString().substring(1).toLowerCase();
    }

    public String setType (){
        return type.getText().toString();
    }

    public String setPart(){
        return part.getText().toString();
    }

    public String setInventory(){
        return inventory.getText().toString();
    }

    public String setWeight(){
        return weight.getText().toString();
    }

    public String setRack(){
        return rack.getText().toString();
    }

    public void setToast (int toast){
        Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_SHORT).show();
    }



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

        //обработка нажития на кнопку "Добавить"
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().equals(""))setToast(R.string.not_name);
                else if (type.getText().toString().equals(""))setToast(R.string.not_type);
                else if (part.getText().toString().equals(""))setToast(R.string.not_part);

                if (db.existMatrix(setName(), setType(), setPart()))
                    setToast(R.string.exist_matrix);
                else {
                    db.add(setName(),setType(),setPart(),setInventory(),setWeight(),setRack());
                }
            }
        });

        //обработка нажития на кнопку "Найти"
        find.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (name.getText().toString().equals("")) setToast(R.string.not_name);

        else {
            Intent intent = new Intent(this, ListViewActivity.class);
            intent.putExtra("name", setName());
            intent.putExtra("type", setType());
            intent.putExtra("part", setPart());
            intent.putExtra("inventory", setInventory());
            startActivity(intent);

        }
    }
}
