package com.madatouriste.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.madatouriste.R;
import com.madatouriste.utils.ProgressBuilder;

public class VideoActivity extends AppCompatActivity {
    VideoView simpleVideoView;
    MediaController mediaControls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        Bundle b = getIntent().getExtras();
        String videoPath = "http://192.168.56.1/1.mp4";
        startVideo(videoPath);
    }


    private void startVideo(String videoPath){
        simpleVideoView = (VideoView) findViewById(R.id.simpleVideoView);
        ProgressBuilder dialog  = new ProgressBuilder(this);
        dialog.showProgressDialog();
        if (isServerOnline(videoPath)) {
            if (mediaControls == null) {
                // create an object of media controller class
                mediaControls = new MediaController(VideoActivity.this);
                mediaControls.setAnchorView(simpleVideoView);
            }
            // set the media controller for video view
            simpleVideoView.setMediaController(mediaControls);
            // set the uri for the video view

            simpleVideoView.setVideoURI(Uri.parse(videoPath));
            simpleVideoView.setOnErrorListener((mp,what,extra)->{
                Toast.makeText(VideoActivity.this,"Erreur lors de la chargement de la video ", Toast.LENGTH_SHORT).show();
                dialog.dismissProgressDialog();
                return true;
            });
            // start a video
            simpleVideoView.start();
            dialog.dismissProgressDialog();
            // implement on completion listener on video view
            simpleVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Toast.makeText(getApplicationContext(), "Thank You...!!!", Toast.LENGTH_LONG).show(); // display a toast when an video is completed
                }
            });
        } else {
            Toast.makeText(this, "Le serveur est hors ligne, veuillez démarrer XAMPP", Toast.LENGTH_SHORT).show();
            dialog.dismissProgressDialog();
        }

    }


    private boolean isServerOnline(String videoUrl) {
//        try {
//            URL url = new URL(videoUrl);
//            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
////            httpURLConnection.setRequestMethod("HEAD");
//            int responseCode = httpURLConnection.getResponseCode();
//            return (responseCode == HttpURLConnection.HTTP_OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
        return true;
    }
}