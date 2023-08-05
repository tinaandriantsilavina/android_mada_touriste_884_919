package com.madatouriste.vue;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.madatouriste.R;
import com.madatouriste.utils.ProgressBuilder;

public class VideoFragment extends Fragment {
    VideoView simpleVideoView;
    MediaController mediaControls;
    String videoPath;
    View view ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =inflater.inflate(R.layout.fragment_video, container, false);
        view=v;
        startVideo(videoPath);
        // Inflate the layout for this fragment
        return v;
    }

    private void startVideo(String videoPath){
        simpleVideoView = (VideoView) view.findViewById(R.id.simpleVideoView);
        ProgressBuilder dialog  = new ProgressBuilder(getActivity());
        dialog.showProgressDialog();
        if (isServerOnline(videoPath)) {
            if (mediaControls == null) {
                // create an object of media controller class
                mediaControls = new MediaController(view.getContext());
                mediaControls.setAnchorView(simpleVideoView);
            }
            // set the media controller for video view
            simpleVideoView.setMediaController(mediaControls);
            // set the uri for the video view

            simpleVideoView.setVideoURI(Uri.parse(videoPath));
            simpleVideoView.setOnErrorListener((mp,what,extra)->{
                Toast.makeText(getActivity(),"Erreur lors de la chargement de la video ", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(getActivity(), "Thank You...!!!", Toast.LENGTH_LONG).show(); // display a toast when an video is completed
                }
            });
        } else {
            Toast.makeText(getActivity(), "Le serveur est hors ligne, veuillez démarrer XAMPP", Toast.LENGTH_SHORT).show();
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

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }
}