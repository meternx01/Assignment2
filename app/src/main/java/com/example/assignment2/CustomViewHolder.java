package com.example.assignment2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    ImageView iv_coverImage;
    TextView tv_price, tv_songName, tv_artistName;


    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);

        iv_coverImage = itemView.findViewById(R.id.iv_coverImage);
        tv_price = itemView.findViewById(R.id.tv_price);
        tv_songName = itemView.findViewById(R.id.tv_songName);
        tv_artistName = itemView.findViewById(R.id.tv_artistName);
    }

    public void onBindViewHolder(final ResultsPojo item, final CustomListener listener){

        tv_artistName.setText(item.artistName);
        tv_price.setText(String.valueOf(item.trackPrice));
        tv_songName.setText(item.trackName);


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(item);
            }
        });
    }
}
