package com.example.favoritter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String FAVORITE_TABLE       = "FAVORITE_TABLE";
    public static final String COLUMN_FAVORITE_NAME = "FAVORITE_NAME";
    public static final String COLUMN_FOOD_ID ="FOOD_ID";
    FavoriteModel favoriteModel;

    //Constructor, overides the variables inside constructor, except context.
    public DataBaseHelper(@Nullable Context context) {super(context, "favorite.db", null, 1);}


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement =
                "CREATE TABLE "+ FAVORITE_TABLE +
                        " ( " + COLUMN_FOOD_ID +" INTEGER PRIMARY KEY, "
                        +COLUMN_FAVORITE_NAME+" TEXT )";

        db.execSQL(createTableStatement);
    }

    //Will be called whenever the database version changes.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {  }

    public List<FavoriteModel> getUserFavorite(){
        List<FavoriteModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM "+FAVORITE_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            do{
                int foodID = cursor.getInt(0);
                String favoriteName = cursor.getString(1);
                FavoriteModel newFavorite = new FavoriteModel(foodID,favoriteName);
                returnList.add(newFavorite);
            } while (cursor.moveToNext());
        }
        else {} cursor.close();
                    db.close();
        return returnList;
    }
    public void addFavorite(int foodID, String foodname) {
       //Creating db conn, and write access.
        SQLiteDatabase db = this.getWritableDatabase();
        //creating content value variable for passing the data
        ContentValues values = new ContentValues();
        //Passing value to the columns
        values.put(COLUMN_FAVORITE_NAME, foodname);
        values.put(COLUMN_FOOD_ID, foodID);
        //Inserting to table
        db.insert(FAVORITE_TABLE, null, values);
        //Closing the db after operation
        db.close();
    }
    //Returns a list of all the ids for the dinners the user has favorite.
    public LinkedList<Integer> getId(){
        LinkedList<Integer> list = new LinkedList();
        String queryString = "SELECT " + COLUMN_FOOD_ID + " from " + FAVORITE_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if (cursor.moveToNext()){
            do{
                int foodId = cursor.getInt(0);
                list.add(foodId);
            }while (cursor.moveToNext());
        } else {} cursor.close();
        db.close();
        return list;
    }
    public boolean deleteOne(FavoriteModel favoriteModel){
        SQLiteDatabase db = getWritableDatabase();
        String queryString = "DELETE FROM " + FAVORITE_TABLE + " WHERE " + COLUMN_FOOD_ID + " = " + favoriteModel.getFoodID();

        Cursor cursor = db.rawQuery(queryString, null);
        return cursor.moveToFirst();
    }
}
