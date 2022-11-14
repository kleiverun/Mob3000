package com.example.favoritter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Favorite extends AppCompatActivity {
    ListView lv_favoriteList;
    DataBaseHelper dbHelper;
    ArrayAdapter favoriteArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        lv_favoriteList = findViewById(R.id.lv_allFavorites);
        dbHelper = new DataBaseHelper(Favorite.this);
        System.out.println("wdadw");
        //dbHelper.getId() This returns the ids of all favorites
        Toast.makeText(this, "Click a favorite to delete it! \uD83D\uDE00 ", Toast.LENGTH_LONG).show();
        showFavoritesOnListView(dbHelper);

        lv_favoriteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FavoriteModel clickedCustomer =(FavoriteModel) parent.getItemAtPosition(position);
                dbHelper.deleteOne(clickedCustomer);
                showFavoritesOnListView(dbHelper);
                Toast.makeText(Favorite.this, "Deleted " + clickedCustomer.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void showFavoritesOnListView(DataBaseHelper dataBaseHelper2){
        favoriteArrayAdapter = new ArrayAdapter<FavoriteModel>(Favorite.this, android.R.layout.simple_list_item_1, dbHelper.getUserFavorite());
        lv_favoriteList.setAdapter(favoriteArrayAdapter);
    }
}