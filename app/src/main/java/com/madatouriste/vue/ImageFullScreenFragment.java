package com.madatouriste.vue;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.madatouriste.R;
public class ImageFullScreenFragment extends Fragment {
    String imgPath ="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_image_full_screen, container, false);
        ImageView image= ((ImageView)v.findViewById(R.id.img_full)); //.setImageResource(R.drawable.baseline_logout_24);
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.baseline_image_24)
                .error(R.drawable.baseline_broken_image_24);

        Glide.with(this)
                .load(imgPath)
//                .apply(requestOptions)
                .into(image);

        if (getActivity() != null) {
//            getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE);
        }

        // Mettre le fragment en mode plein écran
        if (getActivity() != null) {
//            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        return v;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
    @Override
    public void onResume() {
        super.onResume();
        hideSystemUI();
    }

    private void hideSystemUI() {
        View decorView = getActivity().getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
        );
    }
}