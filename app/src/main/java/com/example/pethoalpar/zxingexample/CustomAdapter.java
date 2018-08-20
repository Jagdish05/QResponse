package com.example.pethoalpar.zxingexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by katar on 2/5/2016.
 */
class CustomAdapter extends ArrayAdapter<String> {

    CustomAdapter(Context context, List<String> foods) {

        super(context,R.layout.name_list, foods);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());
        View customView=layoutInflater.inflate(R.layout.name_list, parent, false);

        String singleFood=getItem(position);
        String split[] = singleFood.split("_");

        StringBuilder sb = new StringBuilder();
        sb.append("Date: "+split[0]+"\n").append("Entry: "+split[1]+"\n").append("Exit: "+split[2]+"\n").append("Duration: "+split[3]);
        singleFood = sb.toString();
        TextView textView=(TextView)customView.findViewById(R.id.textView_Custom);
       // ImageView imageView=(ImageView)customView.findViewById(R.id.imageView_custom);
        textView.setText(singleFood);
       // imageView.setImageResource(R.mipmap.ic_launcher);
        return customView;
    }
}
