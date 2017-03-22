package com.general.mediaplayer.geapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.general.mediaplayer.geapp.R;
import com.general.mediaplayer.geapp.model.MediaModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 19/03/2017.
 */

public class MediaAdapter extends RecyclerView.Adapter{

    private Context mContext;
    private List<MediaModel> imageModels = new ArrayList<>();

    public MediaAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.row_media, null);
        MyViewHolder holder = new MyViewHolder(itemView);
        holder.imageView = (ImageView) itemView.findViewById(R.id.media_imageview);
        holder.container = (RelativeLayout)itemView.findViewById(R.id.media_container);
        holder.progressBar = (ProgressBar)itemView.findViewById(R.id.media_progressBar);
        holder.playImageView = (ImageView)itemView.findViewById(R.id.media_playicon);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position){

        final MyViewHolder vh = (MyViewHolder) viewHolder;
        final MediaModel item = imageModels.get(position);

        Picasso.with(mContext)
                .load(item.photoPath)
                .resize(1000, 1000)
                .onlyScaleDown()
                .centerInside()
                .into(vh.imageView, new Callback() {
                    @Override
                    public void onSuccess() {

                        if (!item.bIsPhoto)
                            vh.playImageView.setVisibility(View.VISIBLE);
                        vh.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        Log.d("error", item.photoPath);
                        if (!item.bIsPhoto)
                            vh.playImageView.setVisibility(View.VISIBLE);
                        vh.progressBar.setVisibility(View.GONE);
                    }
                });
    }

    @Override
    public int getItemCount() {
        return imageModels.size();
    }

    public void addMedia(MediaModel imageModel) {

        imageModels.add(imageModel);
    }

    public int getMedia(String name)
    {
        for (int i = 0 ; i < imageModels.size() ; i ++)
        {
            MediaModel model = imageModels.get(i);
            if (model.photoPath.contains(name)) return i;
        }

        return -1;
    }

    public MediaModel getMedia(int i)
    {
        return imageModels.get(i);
    }

    public String getUrl(int position)
    {
        return imageModels.get(position).photoPath;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public ProgressBar progressBar;
        public RelativeLayout container;
        public ImageView playImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

}