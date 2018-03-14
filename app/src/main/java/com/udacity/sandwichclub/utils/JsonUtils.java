package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        List<String> alsoKnownList = new ArrayList<>();
        List<String> ingredientsList = new ArrayList<>();

        try {

            JSONObject parentJsonObject = new JSONObject(json);
            JSONObject childJsonObject = parentJsonObject.getJSONObject("name");

            String mainName = childJsonObject.getString("mainName");
            JSONArray alsoKnownAsJsonArray = childJsonObject.getJSONArray("alsoKnownAs");

            String description = parentJsonObject.getString("description");
            String placeOfOrigin = parentJsonObject.getString("placeOfOrigin");
            String image = parentJsonObject.getString("image");
            JSONArray ingredientsJsonArray = parentJsonObject.getJSONArray("ingredients");

            for (int i = 0; i < alsoKnownAsJsonArray.length(); i++) {
                alsoKnownList.add(alsoKnownAsJsonArray.getString(i));
            }

            for (int i = 0; i < ingredientsJsonArray.length(); i++) {
                ingredientsList.add(ingredientsJsonArray.getString(i));
            }

            return new Sandwich(mainName,alsoKnownList,placeOfOrigin,description,image,ingredientsList);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
