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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>
{
    RequestOptions options ;
    private Context mContext ;
    private List<Anime> mData ;
    String user;

    public RecyclerViewAdapter(Context mContext, List<Anime> mData, String user) {
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
        view = inflater.inflate(R.layout.item_card,parent,false);
        final MyViewHolder viewHolder = new MyViewHolder(view);

        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, AnimeActivity.class);
                // sending data process
                i.putExtra("id",mData.get(viewHolder.getAdapterPosition()).getId());
                i.putExtra("anime_name",mData.get(viewHolder.getAdapterPosition()).getName());
                i.putExtra("anime_size",mData.get(viewHolder.getAdapterPosition()).getSize());
                i.putExtra("anime_studio",mData.get(viewHolder.getAdapterPosition()).getPrice());
                i.putExtra("anime_category",mData.get(viewHolder.getAdapterPosition()).getCategory());
                i.putExtra("anime_rating",mData.get(viewHolder.getAdapterPosition()).getRating());
                i.putExtra("anime_img",mData.get(viewHolder.getAdapterPosition()).getPath());
                i.putExtra("user",user);

                mContext.startActivity(i);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.tvname.setText(mData.get(position).getName());
        //holder.tv_rate.setText(mData.get(position).getRating());
        holder.tvstudio.setText(mData.get(position).getPrice());
        holder.tvcat.setText(mData.get(position).getSize());
        holder.tvrating.setText(mData.get(position).getRating());

        // load image from the internet using Glide
        Glide.with(mContext).load(mData.get(position).getPath()).apply(options).into(holder.AnimeThumbnail);
        
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvname,tv_rate,tvstudio,tvcat,tvrating;
        ImageView AnimeThumbnail;
        LinearLayout view_container;

        public MyViewHolder(View itemView) {
            super(itemView);
            view_container = (LinearLayout) itemView.findViewById(R.id.container);
            tvname = itemView.findViewById(R.id.rowname);
            tvstudio = itemView.findViewById(R.id.studio);
            tv_rate = itemView.findViewById(R.id.rating);
            tvcat = itemView.findViewById(R.id.categorie);
            tvrating = itemView.findViewById(R.id.rating);
            AnimeThumbnail = itemView.findViewById(R.id.thumbnail);
        }
    }
}
