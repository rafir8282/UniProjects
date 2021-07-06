package com.example.mtassignmentone;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodAdapter extends ArrayAdapter<Food> {
    ArrayList<Food> foods;

    public FoodAdapter(FoodListActivity context, int resource, ArrayList<Food> objects) {
        super(context, resource, objects);
        foods = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.my_list_item, parent, false);
        }

        Food food = foods.get(position);

        ImageView icon = (ImageView)convertView.findViewById(R.id.imageViewIcon);
        icon.setImageResource(R.mipmap.food_icon);

        TextView title = (TextView)convertView.findViewById(R.id.textViewTitle);
        title.setText(food.getTitle());

        if (position % 2 == 0) {
            convertView.setBackgroundColor(Color.parseColor("#e6e6e6"));
        }

        return convertView;
    }
}
