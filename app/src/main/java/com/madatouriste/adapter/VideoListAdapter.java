package com.madatouriste.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.madatouriste.R;
import com.madatouriste.modele.VideoModel;

import org.w3c.dom.Text;

import java.util.List;

public class VideoListAdapter extends BaseAdapter {
    private List<String> videoList;
    private String libele ="";
    private Context context;

    public VideoListAdapter(Context context,    String libele, List<String> videoList) {
        this.context = context;
        this.libele = libele;
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
            ((TextView)convertView.findViewById(R.id.videoTitle)).setText(libele+" "+(position+1));
            holder = new ViewHolder();
            holder.videoThumbnail = convertView.findViewById(R.id.videoThumbnail);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String video = videoList.get(position);
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.baseline_image_24)
                .error(R.drawable.baseline_broken_image_24);
        Glide.with(context)
                .load(video)
                .apply(requestOptions)
                .into(holder.videoThumbnail);

        return convertView;
    }

    private static class ViewHolder {
        ImageView videoThumbnail;
    }
}