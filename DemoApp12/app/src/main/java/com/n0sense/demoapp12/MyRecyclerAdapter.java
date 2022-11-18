package com.n0sense.demoapp12;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {
    private ArrayList<Product> products;
    private Context context;

    public MyRecyclerAdapter(ArrayList<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.textView.setText(products.get(position).getGoodsName());
        Log.e("image_res", products.get(position).getGoodsSmallLogo());
        Glide.with(context)
                .load(products.get(position).getGoodsSmallLogo().replace("http://", "https://"))
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.imageView);
        holder.itemView.setOnClickListener(view -> {
            OnItemClickListener itemClickListener = (OnItemClickListener)context;
            itemClickListener.onClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_img);
            textView = itemView.findViewById(R.id.item_text);
        }
    }
}
