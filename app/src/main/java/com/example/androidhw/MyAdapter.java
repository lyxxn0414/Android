package com.example.androidhw;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Video> dataSet;
    private MainActivity activity;

    public MyAdapter(MainActivity mainActivity) {
        activity=mainActivity;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView avatar;
        public TextView nickname;
        public ImageButton play;
        public TextView description;

        public MyViewHolder(View v){
            super(v);
            avatar=v.findViewById(R.id.video_item_avatar);
            nickname=v.findViewById(R.id.video_item_nickname);
            play=v.findViewById(R.id.video_item_play_button);
            description=v.findViewById(R.id.description);
        }
    }

    public void setData(List<Video> dataSet){
        this.dataSet=dataSet;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.video_list_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int position){
        loadImage(viewHolder,dataSet.get(position).avatar);
        viewHolder.nickname.setText(dataSet.get(position).nickname);
        viewHolder.description.setText(dataSet.get(position).description);
        viewHolder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("feedurl", dataSet.get(position).feedurl);
                intent.putExtra("_id",dataSet.get(position)._id);
                intent.putExtra("avatar",dataSet.get(position).avatar);
                intent.putExtra("description",dataSet.get(position).description);
                intent.putExtra("nickname",dataSet.get(position).nickname);
                intent.putExtra("likecount",dataSet.get(position).likecount);
                intent.setClass(activity,ItemDetailActivity.class);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount(){return dataSet==null?0:dataSet.size();}

    private void loadImage(MyViewHolder viewHolder,String url){
        RequestOptions options=new RequestOptions();
        options=options.centerCrop();
        Glide.with(activity).load(url).apply(options).placeholder(R.drawable.avatar).into(viewHolder.avatar);
    }
}
