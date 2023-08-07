package com.madatouriste.vue;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.madatouriste.R;

public class ExoPlayerVideoActivity extends AppCompatActivity {

    private PlayerView playerView;
    private SimpleExoPlayer player;
    private String videoPath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo_player_video);

        playerView = findViewById(R.id.exoPlayerView);
        player = new SimpleExoPlayer.Builder(this, new DefaultRenderersFactory(this))
                .setTrackSelector(new DefaultTrackSelector(this))
                .build();
        playerView.setPlayer(player);
        Bundle b = new Bundle();
        String path = getIntent().getExtras().getString("path");

        MediaSource mediaSource = buildMediaSource(Uri.parse(path));
        player.setMediaSource(mediaSource);
        player.prepare();
    }

    private MediaSource buildMediaSource(Uri uri) {
        DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer");
        DefaultDataSourceFactory defaultDataSourceFactory = new DefaultDataSourceFactory(this, null, dataSourceFactory);
        return new ProgressiveMediaSource.Factory(defaultDataSourceFactory).createMediaSource(uri);
    }

    @Override
    protected void onDestroy() {
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