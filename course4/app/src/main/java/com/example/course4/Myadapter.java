package com.example.course4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Myadapter extends BaseAdapter {
    private ArrayList<Person> arrayList;
    private LayoutInflater layoutInflater;

    public Myadapter(ArrayList<Person> arrayList, Context context) {
        this.arrayList = arrayList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
            viewHolder  = new ViewHolder();
            view = layoutInflater.inflate(R.layout.item,null);
            viewHolder.imageView = view.findViewById(R.id.img);
            viewHolder.textView = view.findViewById(R.id.tv);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.imageView.setImageResource(arrayList.get(i).getPic());
        viewHolder.textView.setText(arrayList.get(i).getName());
        return view;
    }

    class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
