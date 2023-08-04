package com.madatouriste.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.madatouriste.R;
import com.madatouriste.modele.VideoModel;

import java.util.List;

public class VideoListAdapter extends BaseAdapter {
    private List<VideoModel> videoList;
    private Context context;

    public VideoListAdapter(Context context, List<VideoModel> videoList) {
        this.context = context;
        this.videoList = videoList;
    }

    @Override
    public int getCount() {
        return videoList.size();
    }

    @Override
    public Object getItem(int position) {
        return videoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_video, parent, false);
            holder = new ViewHolder();
            holder.videoThumbnail = convertView.findViewById(R.id.videoThumbnail);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        VideoModel video = videoList.get(position);
        Glide.with(context)
                .load(video.getVideoUrl())  // Charger l'URL de la vidéo dans la galerie
                .into(holder.videoThumbnail);

        return convertView;
    }

    private static class ViewHolder {
        ImageView videoThumbnail;
    }
}