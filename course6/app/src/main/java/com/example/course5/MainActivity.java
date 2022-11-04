package com.example.course5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
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
    private RequestQueue queue;
    private LinearLayout linearLayout,linearLayout2;
    private int width;
    private int height;
    private ArrayList<FloorItem> floorItems = new ArrayList<>();
    private int[] idx = {R.id.img1,R.id.img2,R.id.img3,R.id.img4,R.id.img5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewpager);
        //downdata();
        queue = Volley.newRequestQueue(this);
        linearLayout = findViewById(R.id.liner);
        linearLayout2 = findViewById(R.id.linear2);
        Display display = getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();

        Log.e("tag",width+"##"+ height);
        getswiper();
        getcate();
        getfloor();
    }

    private void getfloor() {
        // Instantiate the RequestQueue.
        String url = "https://api-hmugo-web.itheima.net/api/public/v1/home/floordata";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
//                        Log.e("response",response.toString());
                        //JSON data analysis
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            JSONArray jsonArray = jsonObject.getJSONArray("message");
                            for(int i = 0; i < jsonArray.length(); i++){
                                JSONObject js = (JSONObject) jsonArray.get(i);
                                String floor_title = js.getString("floor_title");
                                JSONObject jsonObject1 = new JSONObject(floor_title);
                                String floor_title_img = jsonObject1.getString("image_src");

                                JSONArray jsonArray1 = js.getJSONArray("product_list");
                                ArrayList<Product> products = new ArrayList<>();
                                for (int j = 0; j<jsonArray1.length();j++){
                                    JSONObject jsonObject2 = (JSONObject) jsonArray1.get(j);
                                    String name = jsonObject2.getString("name");
                                    String img_src = jsonObject2.getString("image_src");
                                    Product product = new Product(name,img_src);
                                    products.add(product);

                                }
                                FloorItem floorItem = new FloorItem(floor_title_img,products);
                                floorItems.add(floorItem);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        for (int i = 0; i<floorItems.size(); i++){
                            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.flooritem,null);
                            ImageView img = view.findViewById(R.id.floor_title);
                            Glide.with(MainActivity.this).load(floorItems.get(i).getImg_src()).into(img);
                            for (int j = 0; j<floorItems.get(i).getProducts().size(); j++){
                                ImageView img_src = view.findViewById(idx[j]);
                                Glide.with(MainActivity.this).load(floorItems.get(i).getProducts().get(j).getImage_src()).into(img_src);
                            }
                            linearLayout2.addView(view);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    private void getcate() {
        // Instantiate the RequestQueue.
        String url = "https://api-hmugo-web.itheima.net/api/public/v1/home/catitems";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
//                        Log.e("response",response.toString());
                        //JSON data analysis
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            JSONArray jsonArray = jsonObject.getJSONArray("message");
                            for(int i = 0; i < jsonArray.length(); i++){
                                JSONObject js = (JSONObject) jsonArray.get(i);
                                String image_src = js.getString("image_src");
//                                Log.e("tag",image_src);
                                ImageView imageView = new ImageView(MainActivity.this);
//                                Log.e("image_src",image_src);
                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width/jsonArray.length(),width/jsonArray.length());
                                Glide.with(MainActivity.this).load(image_src).into(imageView);
                                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                                imageView.setPadding(5,5,5,5);
                                imageView.setLayoutParams(params);
                                linearLayout.addView(imageView);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        myadapter = new Myadapter(picList);
                        viewPager.setAdapter(myadapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

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

    private void getswiper(){

// Instantiate the RequestQueue.
        String url = "https://api-hmugo-web.itheima.net/api/public/v1/home/swiperdata";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
//                        Log.e("response",response.toString());
                        //JSON data analysis
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            JSONArray jsonArray = jsonObject.getJSONArray("message");
                            for(int i = 0; i < jsonArray.length(); i++){
                                JSONObject js = (JSONObject) jsonArray.get(i);
                                String image_src = js.getString("image_src");
//                            Log.e("tag",image_src);
                                ImageView imageView = new ImageView(MainActivity.this);
                                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                                Glide.with(MainActivity.this).load(image_src).into(imageView);
                                picList.add(imageView);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        myadapter = new Myadapter(picList);
                        viewPager.setAdapter(myadapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}