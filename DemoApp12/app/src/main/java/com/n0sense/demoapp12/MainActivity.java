package com.n0sense.demoapp12;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    private Requests requests;
    private ArrayList<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestQueue queue = Volley.newRequestQueue(this);
        requests = new Requests(queue);
        productList = new ArrayList<>();
        getData();
    }

    private void getData() {
        String url = "https://api-hmugo-web.itheima.net/api/public/v1/goods/search";
        requests.doGet(url,
                successResponse -> {
                    Log.e("tag", "get");
                    LinkedHashMap<String, Object> message
                            = (LinkedHashMap<String, Object>) successResponse.get("message");
                    ArrayList<LinkedHashMap<String, Object>> goodsList
                            = (ArrayList<LinkedHashMap<String, Object>>) message.get("goods");
                    for (LinkedHashMap<String, Object> goods : goodsList) {
                        String goodsName = (String) goods.get("goods_name");
                        String goodsSmallLogo = (String) goods.get("goods_small_logo");
                        productList.add(new Product(goodsName, goodsSmallLogo));
                    }
                    MyRecyclerAdapter adapter = new MyRecyclerAdapter(productList, MainActivity.this);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                    RecyclerView recyclerView = findViewById(R.id.recycler);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(adapter);
                },
                failResponse -> {
                    ;
                });
    }

    @Override
    public void onClick(int position) {
        Log.e("onClick", "Position=" + position);
    }
}