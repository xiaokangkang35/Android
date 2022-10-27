package com.example.ex2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.nio.file.attribute.FileTime;
import java.util.ArrayList;

public class Myda extends BaseAdapter {
    private ArrayList<Person> arrayList;
    private LayoutInflater inflater;

    public Myda(ArrayList<Person> arrayList, Context context) {
        this.arrayList = arrayList;
        this.inflater = LayoutInflater.from(context);
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
        ViewHolder holder;
        if(view == null){
            view =inflater.inflate(R.layout.item,null);
            holder = new ViewHolder();
            holder.imageView = view.findViewById(R.id.pic);
            holder.textView1 = view.findViewById(R.id.tv1);
            holder.textView2 = view.findViewById(R.id.tv2);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        holder.imageView.setImageResource(arrayList.get(i).getPic());
        holder.textView1.setText(arrayList.get(i).getTitle());
        holder.textView2.setText(arrayList.get(i).getCont());
        return view;
    }

    class ViewHolder{
        ImageView imageView;
        TextView textView1;
        TextView textView2;
    }
}
