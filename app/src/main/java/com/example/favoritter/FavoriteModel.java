package com.example.favoritter;

import androidx.annotation.NonNull;

public class FavoriteModel {
    private int foodID;
    private String favoriteName;



    public FavoriteModel(int foodID, String favoriteName) {
        this.foodID = foodID;
        this.favoriteName = favoriteName;
    }

    @NonNull
    @Override
    public String toString() {
        return favoriteName ;
    }
    public int getFoodID() {
        return foodID;
    }
    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }
    public String getFavoriteName() {
        return favoriteName;
    }
    public void setFavoriteName(String favoriteName) {  this.favoriteName = favoriteName;  }
}
