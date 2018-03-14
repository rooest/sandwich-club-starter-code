package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    ImageView ingredientsIv;
    TextView integrated;
    TextView description;
    TextView placeoforgin;
    TextView alsoKnownAs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION,DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        initViews();

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());

        setViews(sandwich);
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this,R.string.detail_error_message,Toast.LENGTH_SHORT).show();
    }

    private void initViews() {
        ingredientsIv = findViewById(R.id.image_iv);
        integrated = findViewById(R.id.ingredients_tv);
        description = findViewById(R.id.description_tv);
        placeoforgin = findViewById(R.id.origin_tv);
        alsoKnownAs = findViewById(R.id.also_known_tv);
    }

    private void setViews(Sandwich sandwich) {
        integrated.setText(sandwich.getIngredients().toString());
        alsoKnownAs.setText(sandwich.getAlsoKnownAs().toString());
        description.setText(sandwich.getDescription());
        placeoforgin.setText(sandwich.getPlaceOfOrigin());
    }

    private void populateUI() {


    }
}
