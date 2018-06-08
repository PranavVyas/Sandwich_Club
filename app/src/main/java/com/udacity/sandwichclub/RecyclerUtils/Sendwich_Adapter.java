package com.udacity.sandwichclub.RecyclerUtils;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.R;

public class Sendwich_Adapter extends RecyclerView.Adapter<Sendwich_Adapter.Holder>{

    Context ct;
    String[] names,images;
    public Sendwich_Adapter(Context ctx,String name[], String image[]){
        names = name;
        images = image;
        ct = ctx;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ct);
        View view = inflater.inflate(R.layout.senwich_row,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.tvname.setText(names[position]);
        Picasso.with(ct)
                .load(images[position])
                .into(holder.ivPhoto);
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView tvname;
        ImageView ivPhoto;
        public Holder(View itemView) {
            super(itemView);
            tvname = (TextView) itemView.findViewById(R.id.tv_item_name_main);
            ivPhoto = (ImageView) itemView.findViewById(R.id.iv_photo_main);
        }
    }

}


