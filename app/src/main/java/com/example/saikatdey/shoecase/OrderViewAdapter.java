package com.example.saikatdey.shoecase;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderViewAdapter extends RecyclerView.Adapter<OrderViewAdapter.MyViewHolder>
{
    RequestOptions options ;
    private Context mContext ;
    private List<MyData> mData ;
    String user;

    public OrderViewAdapter(Context mContext, List<MyData> mData, String user) {
        this.mContext = mContext;
        this.mData = mData;
        this.user = user;

        //Requset option for glide
        options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.loading_shape)
                .error(R.drawable.loading_shape);
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.order_card,parent,false);
        final MyViewHolder viewHolder = new MyViewHolder(view);

        //final String URL_REGIST = "https://shoecase2018.000webhostapp.com/shoecase/deleteItem.php?id="+mData.get(viewHolder.getAdapterPosition()).getId();


        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext, "Delete pressed", Toast.LENGTH_SHORT).show();
                /*StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_REGIST,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                // Matching server responce message to our text.
                                if (response.equalsIgnoreCase("success")) {
                                    Toast.makeText(mContext, "Successfully Deleted!", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(mContext, "Error while deleting the item!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Toast.makeText(mContext, error.toString(), Toast.LENGTH_SHORT).show();
                                //loading.setVisibility(View.GONE);
                                //signin.setVisibility(View.VISIBLE);
                            }
                        }) {
                    //name, description, price, category, rating, image_url;
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(mContext);
                requestQueue.add(stringRequest);*/
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.tvname.setText(mData.get(position).getItemName());
        //holder.tv_rate.setText(mData.get(position).getRating());
        holder.tvdate.setText(mData.get(position).getDate());
        holder.tvprice.setText(mData.get(position).getPrice());

        // load image from the internet using Glide
        Glide.with(mContext).load(mData.get(position).getPath()).apply(options).into(holder.AnimeThumbnail);
        
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvname,tvdate,tvprice;
        ImageView AnimeThumbnail, delete;
        LinearLayout view_container;

        public MyViewHolder(View itemView) {
            super(itemView);
            view_container = (LinearLayout) itemView.findViewById(R.id.container);
            tvname = itemView.findViewById(R.id.rowname);
            tvdate = itemView.findViewById(R.id.date);
            tvprice = itemView.findViewById(R.id.price);
            delete = itemView.findViewById(R.id.delete);
            //tvgrand = itemView.findViewById(R.id.grand);
            AnimeThumbnail = itemView.findViewById(R.id.thumbnail);
        }
    }
}
