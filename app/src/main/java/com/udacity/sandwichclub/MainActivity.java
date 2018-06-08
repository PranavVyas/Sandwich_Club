package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.udacity.sandwichclub.RecyclerUtils.Sendwich_Adapter;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    RecyclerView rvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvList = (RecyclerView) findViewById(R.id.rv_sendwich_list);

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_names);
        String[] image = new String[sandwiches.length];

        for(int position = 0; position < sandwiches.length; position++) {
            String[] sandwichesDetails = getResources().getStringArray(R.array.sandwich_details);
            String json = sandwichesDetails[position];
            Sandwich sandwich = null;
            try {
                sandwich = JsonUtils.parseSandwichJson(json);
                String temp = sandwich.getImage();
                image[position] = temp;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Sendwich_Adapter adapterRv = new Sendwich_Adapter(this,sandwiches,image);
        rvList.setAdapter(adapterRv);
        rvList.setLayoutManager(new LinearLayoutManager(this));
    }
}
