package com.example.tom.foodbook;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CsvHelper {
    AssetManager mngr;
    Context myContext;

    public CsvHelper(Context myContext) {
        mngr = myContext.getAssets();
        this.myContext = myContext;
    }

    /**
     * Returns Arraylist of models of canteens saved in canteen.csv
     *
     * @return ArrayList<Canteen>
     */
    public ArrayList<Canteen> getCanteens() {

        ArrayList<Canteen> canteens = new ArrayList<>();
        try {

            CsvReader products = new CsvReader(new InputStreamReader(myContext.getAssets().open("canteen.csv")));

            products.readHeaders();

            while (products.readRecord()) {
                int id = Integer.parseInt(products.get("id"));
                String name = products.get("name");


                double latitude = Double.parseDouble(products.get("latitude"));
                double longitude = Double.parseDouble(products.get("longitude"));
                String building = products.get("building");
                String description = products.get("description");
                int imageId = Integer.parseInt(products.get("id"));

                Canteen canteen = new Canteen(id, name, latitude, longitude, building, description, imageId);
                canteens.add(canteen);

                System.out.println(id + ":" + name);
            }

            products.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return canteens;
    }

    /**
     * Returns Arraylist of models of foods saved in food.csv
     *
     * @return ArrayList<Food>
     */
    public ArrayList<Food> getFoods() {
        ArrayList<Food> foods = new ArrayList<>();
        try {

            CsvReader products = new CsvReader(new InputStreamReader(myContext.getAssets().open("food.csv")));

            products.readHeaders();

            while (products.readRecord()) {
                int id = Integer.parseInt(products.get("id"));
                String name = products.get("name");
                double price = Double.parseDouble(products.get("price"));
                int calories = Integer.parseInt(products.get("calories"));
                int proteins = Integer.parseInt(products.get("proteins"));
                int fats = Integer.parseInt(products.get("fats"));
                int sugar = Integer.parseInt(products.get("sugar"));
                boolean vegetarian = products.get("vegetarian") == "1" ? true : false;
                boolean vegan = products.get("vegan") == "1" ? true : false;
                boolean glutenFree = products.get("glutenFree") == "1" ? true : false;
                int canteenId = Integer.parseInt(products.get("canteenId"));
                int imageId = Integer.parseInt(products.get("imageId"));

                Food food = new Food(id, name, price, calories, proteins, fats, sugar, vegetarian, vegan, glutenFree, canteenId, imageId);

                foods.add(food);

                System.out.println(id + ":" + name + price);
            }

            products.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return foods;
    }
}