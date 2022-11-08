package com.example.course8;

import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.flexbox.FlexboxLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CateActivity extends AppCompatActivity implements LeftFragment.callback{
    private RequestQueue queue;
    private LinearLayout linearLayout;
    public static  ArrayList<CateBean> cateBeans = new ArrayList<>();
    private int width;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cate);
        linearLayout = findViewById(R.id.liner_rightfragment);
        queue = Volley.newRequestQueue(this);
        Display display = getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        getcategories();
    }

    private void getcategories() {
// Instantiate the RequestQueue.
        String url = "https://api-hmugo-web.itheima.net/api/public/v1/categories";
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            JSONArray jsonArray = jsonObject.getJSONArray("message");
                            Log.e("tag",jsonArray.toString());
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                                String cate_name = jsonObject1.getString("cat_name");
                                JSONArray jsonArray1 = jsonObject1.getJSONArray("children");
                                ArrayList<CateItem> catelist = new ArrayList<>();
                                for(int j=0;j<jsonArray1.length();j++){
                                    JSONObject jsonObject2 = jsonArray1.getJSONObject(j);
                                    String name = jsonObject2.getString("cat_name");
                                    if(jsonObject2.has("children")){
                                        JSONArray jsonArray2 = jsonObject2.getJSONArray("children");
                                        ArrayList<Product> products = new ArrayList<>();
                                        for (int k=0;k<jsonArray2.length();k++){
                                            JSONObject jsonObject3 = jsonArray2.getJSONObject(k);
                                            String cat_name = jsonObject3.getString("cat_name");
                                            String cat_icon= jsonObject3.getString("cat_icon");
                                            Product product = new Product(cat_name,cat_icon);
                                            products.add(product);
                                        }
                                        CateItem cateItem = new CateItem(name,products);
                                        catelist.add(cateItem);
                                    }
                                }
                                CateBean cateBean = new CateBean(catelist,cate_name);
                                cateBeans.add(cateBean);
                            }
                            refreshView(0);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }


    private void refreshView(int pos){
        for(int i=0;i<cateBeans.get(pos).getCateItems().size();i++){
            View cateview = LayoutInflater.from(CateActivity.this).inflate(R.layout.cate_item,null);
            FlexboxLayout flexboxLayout = cateview.findViewById(R.id.flex);
            TextView tv_title = cateview.findViewById(R.id.tv);
            tv_title.setText(cateBeans.get(pos).getCateItems().get(i).getCat_name());
            for (int j=0;j<cateBeans.get(pos).getCateItems().get(i).getProduct().size();j++){
                View view = LayoutInflater.from(CateActivity.this).inflate(R.layout.item,null);
                ImageView imageView = view.findViewById(R.id.img);
                TextView tv = view.findViewById(R.id.tv);
                int w = (3*width/4)/4;
                LinearLayout.LayoutParams parmsview = new LinearLayout.LayoutParams(w, ViewGroup.LayoutParams.WRAP_CONTENT);
                view.setLayoutParams(parmsview);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                Glide.with(CateActivity.this).load(cateBeans.get(pos).getCateItems().get(i).getProduct().get(j).getImage_src()).into(imageView);
                LinearLayout.LayoutParams paramsimg = new LinearLayout.LayoutParams(w,w);
                imageView.setLayoutParams(paramsimg);
                tv.setText(cateBeans.get(pos).getCateItems().get(i).getProduct().get(j).getName());
                LinearLayout.LayoutParams parmstv = new LinearLayout.LayoutParams(w,ViewGroup.LayoutParams.WRAP_CONTENT);
                tv.setLayoutParams(parmstv);
                flexboxLayout.addView(view);
            }
            linearLayout.addView(cateview);

        }
    }

    @Override
    public void send(int pos) {
        linearLayout.removeAllViews();
        refreshView(pos);
    }
}
