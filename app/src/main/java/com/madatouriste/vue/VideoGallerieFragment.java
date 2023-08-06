package com.madatouriste.vue;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.madatouriste.R;
import com.madatouriste.adapter.VideoListAdapter;
import com.madatouriste.modele.VideoModel;
import com.madatouriste.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class VideoGallerieFragment extends Fragment {
    private ListView listView;
    private VideoListAdapter adapter;
    private List<String> videoList;

    private String libele ="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_video_gallerie, container, false);
        listView = v.findViewById(R.id.listView);
        adapter = new VideoListAdapter(getActivity(), libele, videoList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ExoPlayerFragment video = new ExoPlayerFragment();
                video.setVideoPath(videoList.get(position));
                Utils.fragmentNavig(getActivity(),video);
            }

        });
        return v;
    }

    public List<String> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<String> videoList) {
        this.videoList = videoList;
    }

    public String getLibele() {
        return libele;
    }

    public void setLibele(String libele) {
        this.libele = libele;
    }
}