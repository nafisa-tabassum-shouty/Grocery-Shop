package com.example.groceryshop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groceryshop.Model.CartModel;
import com.example.groceryshop.R;

import java.util.ArrayList;

public class cartAdapter extends RecyclerView.Adapter<cartAdapter.viewHolder>{
    ArrayList<CartModel> list;
    Context context;
    public cartAdapter(ArrayList<CartModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public cartAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sample_cart,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cartAdapter.viewHolder holder, int position) {
        CartModel model=list.get(position);
        holder.imageView.setImageResource(model.getPic());
        holder.textView.setText(model.getText());
        holder.textView2.setText(model.getProductprice());
        holder.textView3.setText(model.getQuantity());


        switch (position){
            case 0:
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context,"Image 1 is clicked",Toast.LENGTH_SHORT).show();
                    }
                });
                holder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context,"Image 1 is clicked",Toast.LENGTH_SHORT).show();
                    }
                });
                break;

            case 1:
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context,"Image 2 is clicked",Toast.LENGTH_SHORT).show();
                    }
                });
                holder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context,"Image 2 is clicked",Toast.LENGTH_SHORT).show();
                    }
                });
                break;



        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView,textView2,textView3;
        Button plus,minus;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById((R.id.product_image));
            textView = itemView.findViewById(R.id.product_name);
            textView2=itemView.findViewById(R.id.product_price);
            textView3=itemView.findViewById(R.id.quantity);
            /*plus=itemView.findViewById(R.id.ADD);
            minus=itemView.findViewById(R.id.DELETE);*/


        }
    }
}
