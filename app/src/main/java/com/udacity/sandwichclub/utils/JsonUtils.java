package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

  private static final String KEY_NAME = "name";
  private static final String KEY_MAIN_NAME = "mainName";
  private static final String KEY_ALSO_KNOW_AS = "alsoKnownAs";
  private static final String KEY_PLACE_OF_ORIGIN = "placeOfOrigin";
  private static final String KEY_DESCRIPTION = "description";
  private static final String KEY_IMAGE = "image";
  private static final String KEY_INGREDIENTS = "ingredients";


    public static Sandwich parseSandwichJson(String json) {

        List<String> alsoKnownList = new ArrayList<>();
        List<String> ingredientsList = new ArrayList<>();

        try {

            JSONObject parentJsonObject = new JSONObject(json);
            JSONObject childJsonObject = parentJsonObject.getJSONObject(KEY_NAME);

            String mainName = childJsonObject.getString(KEY_MAIN_NAME);
            JSONArray alsoKnownAsJsonArray = childJsonObject.getJSONArray(KEY_ALSO_KNOW_AS);

            String description = parentJsonObject.getString(KEY_DESCRIPTION);
            String placeOfOrigin = parentJsonObject.getString(KEY_PLACE_OF_ORIGIN);
            String image = parentJsonObject.getString(KEY_IMAGE);
            JSONArray ingredientsJsonArray = parentJsonObject.getJSONArray(KEY_INGREDIENTS);

            for (int i = 0; i < alsoKnownAsJsonArray.length(); i++) {
                alsoKnownList.add(alsoKnownAsJsonArray.optString(i,""));
            }

            for (int i = 0; i < ingredientsJsonArray.length(); i++) {
                ingredientsList.add(ingredientsJsonArray.optString(i,""));
            }

            return new Sandwich(mainName,alsoKnownList,placeOfOrigin,description,image,ingredientsList);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
