package com.example.course8;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LeftFragment extends ListFragment {
    private RequestQueue queue;
    public static ArrayList<CateItem> cateItems = new ArrayList<>();
    public static ArrayList<String> catelist = new ArrayList<>();
    public interface callback{
        void send(int pos);
    }
    private callback callback;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        callback = (LeftFragment.callback) getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        queue = Volley.newRequestQueue(getActivity());
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
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                                String cate_name = jsonObject1.getString("cat_name");
                                JSONArray jsonArray1 = jsonObject1.getJSONArray("children");
                                ArrayList<Product> products = new ArrayList<>();
                                for(int j=0;j<jsonArray1.length();j++){
                                    JSONObject jsonObject2 = jsonArray1.getJSONObject(j);
                                    String name = jsonObject2.getString("cat_name");
                                    String cat_icon = jsonObject2.getString("cat_icon");
                                    Product product = new Product(name,cat_icon);
                                    products.add(product);
                                }
                                CateItem cateItem = new CateItem(cate_name,products);
                                cateItems.add(cateItem);
                                catelist.add(cate_name);
                            }
                            ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1, catelist);
                            setListAdapter(arrayAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
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

    @SuppressLint("ResourceAsColor")
    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        callback.send(position);

    }
}
