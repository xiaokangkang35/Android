package com.example.ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Myda myda;
    private ListView listView;
    private ArrayList<Person> arrayList =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        listView = findViewById(R.id.ls);
        myda =new Myda(arrayList,this);
        listView.setAdapter(myda);
    }

    public void init(){
        for(int i = 1;i<51;i++){
            Person person = new Person("第"+i+"个标题","内容",R.mipmap.a);
            arrayList.add(person);
        }
    }
}