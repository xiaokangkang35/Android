package com.example.course4;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SecActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private ArrayList<ImageView> arrayList = new ArrayList<>();
    private PicAdapter picAdapter;
    private Timer timer = new Timer();
    private int pos = 0;
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            handler.sendEmptyMessage(0x01);
        }
    };
    private Handler handler =new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            pos++;
            if (pos>=arrayList.size()){
                pos = 0;
            }
            viewPager.setCurrentItem(pos);
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);
        init();
        Intent intent = getIntent();
        pos = intent.getIntExtra("pos",0);
        picAdapter = new PicAdapter(arrayList);
        viewPager = findViewById(R.id.vp);
        viewPager.setAdapter(picAdapter);
        viewPager.setCurrentItem(pos);
        timer.schedule(timerTask,1000,1000);
    }
    private void init(){
        for(int i = 0;i<MainActivity.arrayList.size();i++){
            ImageView imageView = new ImageView(SecActivity.this);
            imageView.setImageResource(MainActivity.arrayList.get(i).getPic());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            arrayList.add(imageView);
        }
    }
}
