package com.example.course4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int[] pic = new int[]{R.mipmap.a,R.mipmap.b,R.mipmap.c};
    private String[] name = new String[]{"zhangsan","lisi","wangwu"};
    public static ArrayList<Person> arrayList = new ArrayList<>();
    private Random random = new Random();
    private GridView gridView;
    private Myadapter myadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        gridView = findViewById(R.id.gv);
        myadapter = new Myadapter(arrayList,MainActivity.this);
        gridView.setAdapter(myadapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,SecActivity.class);
                intent.putExtra("pos",i);
                startActivity(intent);
            }
        });
    }

    private void init(){
        for (int i= 0;i<500;i++){
            int index = random.nextInt(3);
            Person person = new Person(pic[index],name[index]);
            arrayList.add(person);
        }
    }
}