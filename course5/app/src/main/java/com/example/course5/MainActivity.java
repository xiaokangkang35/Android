package com.example.course5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayList<ImageView> picList = new ArrayList<>();
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x01){
                for (int i = 0; i < arrayList.size(); i++){
                    ImageView imageView = new ImageView(MainActivity.this);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    Glide.with(MainActivity.this).load(arrayList.get(i)).into(imageView);
                    picList.add(imageView);

                }
                myadapter = new Myadapter(picList);
                viewPager.setAdapter(myadapter);
            }
        }
    };

    private ViewPager viewPager;
    private Myadapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewpager);
        downdata();
    }

    //从后台获取数据
    private void downdata() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://api-hmugo-web.itheima.net/api/public/v1/home/swiperdata");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.connect();
                    if (connection.getResponseCode()==HttpURLConnection.HTTP_OK){
                        InputStream inputStream = connection.getInputStream();
                        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                        int len = 0;
                        char [] buffer = new char[128];
                        StringBuffer stringBuffer = new StringBuffer();
                        while((len = inputStreamReader.read(buffer)) != -1){
                            stringBuffer.append(buffer);
                        }
                        //解析json字符串
                        Log.e("tag",stringBuffer.toString());

                        JSONObject jsonObject = new JSONObject(stringBuffer.toString());
                        JSONArray jsonArray = jsonObject.getJSONArray("message");
                        for(int i = 0; i < jsonArray.length(); i++){
                            JSONObject js = (JSONObject) jsonArray.get(i);
                            String image_src = js.getString("image_src");
//                            Log.e("tag",image_src);
                            arrayList.add(image_src);

                        }
                        handler.sendEmptyMessage(0x01);

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }).start();
    }
}