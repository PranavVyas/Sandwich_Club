package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    TextView tvAlsoKnownAs,tvIngrediants,tvOrigin,tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        Log.d(TAG, "onCreate: Received not null intent with value ");

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        Log.d(TAG, "onCreate: Position is "+position);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = null;
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (sandwich == null) {
            // Sandwich data unavailable
            Log.d(TAG, "onCreate: Sendwich is null");
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        tvAlsoKnownAs = (TextView) findViewById(R.id.also_known_tv);
        tvDescription = (TextView) findViewById(R.id.description_tv);
        tvOrigin = (TextView) findViewById(R.id.origin_tv);
        tvIngrediants = (TextView) findViewById(R.id.ingredients_tv);

        String origin = sandwich.getPlaceOfOrigin();
        List<String> alsoKnownAs = sandwich.getAlsoKnownAs();
        List<String> ingrediants = sandwich.getIngredients();
        String description = sandwich.getDescription();

        tvDescription.setText(description);
        tvOrigin.setText(origin);
        boolean flag = true;
        for(String x : ingrediants){
            if(flag == true) {tvIngrediants.append(x);flag = false;}
            else{
            tvIngrediants.append("\n"+x);}
        }
        flag = true;
        for(String x : alsoKnownAs){
            if(flag == true) {tvAlsoKnownAs.append(x);flag = false;}
            else{
            tvAlsoKnownAs.append("\n"+x);}
        }
    }
}
