package com.madatouriste.vue;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.madatouriste.R;

public class WebViewFragment extends Fragment {
    private WebView webView;
    String lien;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_web_view, container, false);

        webView = (WebView) v.findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(lien);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        return v ;
    }

    public boolean onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return false;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }
}