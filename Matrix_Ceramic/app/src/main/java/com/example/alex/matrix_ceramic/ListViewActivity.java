package com.example.alex.matrix_ceramic;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ListViewActivity extends FragmentActivity implements LoaderCallbacks<Cursor> {

    ListView lv;
    Matrix_DB db;
    SimpleCursorAdapter sca;
    String name, type, part, inventory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        name = getIntent().getStringExtra("name");
        type = getIntent().getStringExtra("type");
        part = getIntent().getStringExtra("part");
        inventory = getIntent().getStringExtra("inventory");



        //открываем подключение к базе данных
        db = new Matrix_DB(this);
        db.open();

        //офрмируем столбци сопостовления
        String[] from = new String[]{Matrix_DB.NAME, Matrix_DB.TYPE, Matrix_DB.PART, Matrix_DB.INVENTORY, Matrix_DB.WEIGHT, Matrix_DB.RACK};
        int[] to = new int[]{R.id.tv_name_item, R.id.tv_type_item, R.id.tv_part_item, R.id.tv_inventory_item, R.id.tv_weight_item, R.id.tv_rack_item};

        //создаем адаптер и настраеваем список
        sca = new SimpleCursorAdapter(this, R.layout.item, null, from, to, 0);
        lv = (ListView) findViewById(R.id.shou_view);
        lv.setAdapter(sca);

        // добавляем контекстное меню к списку
        registerForContextMenu(lv);

        // создаем лоадер для чтения данных
        getSupportLoaderManager().initLoader(0,null,this);




    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new MyCursorLoader(this, db, name, type, part, inventory);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    static class MyCursorLoader extends CursorLoader{
        Matrix_DB db;
        String name, type, part, inventory;
        public MyCursorLoader(Context context, Matrix_DB db, String name, String type, String part, String inventory) {
            super(context);
            this.db = db;
            this.name = name;
            this.type = type;
            this.part = part;
            this.inventory = inventory;
        }

        @Override
        public Cursor loadInBackground() {
            return db.allDate();
        }
    }

}
