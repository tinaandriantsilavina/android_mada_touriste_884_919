package com.madatouriste.vue;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.madatouriste.R;
import com.madatouriste.adapter.VideoListAdapter;
import com.madatouriste.modele.VideoModel;

import java.util.ArrayList;
import java.util.List;

public class VideoGallerieFragment extends Fragment {
    private ListView listView;
    private VideoListAdapter adapter;
    private List<VideoModel> videoList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_video_gallerie, container, false);
        listView = v.findViewById(R.id.listView);
        videoList = new ArrayList<>();

        // Ajoutez les URL de vos vidéos à la liste videoList
        videoList.add(new VideoModel("http://192.168.56.1:3900/api/public/videos/antananarivo_001.mp4"));
        videoList.add(new VideoModel("http://192.168.56.1:3900/api/public/videos/antananarivo_001.mp4"));
        // ...

        adapter = new VideoListAdapter(getActivity(), videoList);
        listView.setAdapter(adapter);
        return v;
    }

    public List<VideoModel> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<VideoModel> videoList) {
        this.videoList = videoList;
    }
}