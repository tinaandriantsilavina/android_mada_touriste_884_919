package com.madatouriste.vue;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.madatouriste.R;

public class ExoPlayerFragment extends Fragment {
    private PlayerView playerView;
    private SimpleExoPlayer player;

    private String videoPath;

    public ExoPlayerFragment() {
        // Constructeur vide requis
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_exo_player, container, false);
        playerView = rootView.findViewById(R.id.exoPlayerView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        player = new SimpleExoPlayer.Builder(requireContext(), new DefaultRenderersFactory(requireContext()))
                .setTrackSelector(new DefaultTrackSelector(requireContext()))
                .build();
        playerView.setPlayer(player);
//        String videoUrl = "http://192.168.56.1:3900/api/public/videos/antananarivo_001.mp4";
        MediaSource mediaSource = buildMediaSource(Uri.parse(videoPath));
        player.setMediaSource(mediaSource);
        player.prepare();
    }


    private MediaSource buildMediaSource(Uri uri) {
        DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer");
        DefaultDataSourceFactory defaultDataSourceFactory = new DefaultDataSourceFactory(requireContext(), null, dataSourceFactory);
        return new ProgressiveMediaSource.Factory(defaultDataSourceFactory).createMediaSource(uri);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.release();


    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }
}