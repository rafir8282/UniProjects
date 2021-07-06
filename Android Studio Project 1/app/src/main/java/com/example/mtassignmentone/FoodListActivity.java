package com.example.mtassignmentone;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class FoodListActivity extends AppCompatActivity {
    ArrayList<Food> foods = new ArrayList<Food>();
    FoodDbHelper mydb = new FoodDbHelper(this,"FoodDb",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        foods = mydb.getAllFoods();
        if (foods.isEmpty()) {
            mydb.insertFood(new Food("Apple", R.drawable.apple, new Date(2019,3,1)));
            mydb.insertFood(new Food("Cheeseburger", R.drawable.cheeseburger, new Date(2019,3,8)));
            mydb.insertFood(new Food("Chicken", R.drawable.chicken, new Date(2019,3,16)));
            foods = mydb.getAllFoods();
        }

        FoodAdapter adapter = new FoodAdapter(this, R.layout.my_list_item, foods);

        ListView listView = (ListView) findViewById(R.id.listViewDatabase);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Food cbrevent = foods.get(position);
                Intent intent = new Intent(view.getContext(), FoodViewActivity.class);
                intent.putExtra("title", cbrevent.getTitle());
                intent.putExtra("imageResource", cbrevent.getImageResource());
                intent.putExtra("date", cbrevent.getDateString());
                startActivity(intent);
            }
        });
    }

    public void openRecogniserActivity(View v) {
        Intent intent = new Intent(getApplicationContext(), FoodRecogniserActivity.class);
        startActivity(intent);
    }
}
