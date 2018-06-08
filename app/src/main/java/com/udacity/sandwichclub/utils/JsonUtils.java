package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final String TAG = "JsonUtils";

    public static Sandwich parseSandwichJson(String json) throws JSONException {

        JSONObject sandwich = new JSONObject(json);
        JSONObject name =sandwich.getJSONObject("name");

        String mainName,placeofOrigin,descrption,image;
        List<String> ingrediants = new ArrayList<String>();
        List<String> alsoKnownAs = new ArrayList<String>();

        mainName = name.getString("mainName");
        placeofOrigin = sandwich.getString("placeOfOrigin");
        descrption = sandwich.getString("description");
        image = sandwich.getString("image");
        Log.d(TAG, " Name : "+mainName+"\nPlace origin"+placeofOrigin+"\nDescription"+descrption+"\nimage "+image);

        JSONArray array = sandwich.getJSONArray("ingredients");
        int size = array.length();
        Log.d(TAG, "parseSandwichJson: Size is "+size);
        for (int index = 0; index < size; index++){
            ingrediants.add(array.getString(index));
            Log.d(TAG, "\nparseSandwichJson: Just Added "+array.getString(index)+" to the Ingrediants");
            index++;
        }
        array = name.getJSONArray("alsoKnownAs");
        size = array.length();
        Log.d(TAG, "parseSandwichJson: Size of Also Known as is "+size);
        for (int index = 0; index < size; index++){
            alsoKnownAs.add(array.getString(index));
            Log.d(TAG, "\nparseSandwichJson: Just Added "+array.getString(index)+" to the Also Known as");
            index++;
        }
        Sandwich sand = new Sandwich(mainName,alsoKnownAs,placeofOrigin,descrption,image,ingrediants);
        return sand;
    }
}
