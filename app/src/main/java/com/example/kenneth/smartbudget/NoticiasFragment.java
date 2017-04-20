package com.example.kenneth.smartbudget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


public class NoticiasFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_noticias, container, false);
        WebView webView = (WebView) v.findViewById(R.id.webview);
        webView.loadUrl("https://www.forbes.com.mx/8-frases-de-carlos-slim-para-mejorar-tus-finanzas-personales/");

        return  v; }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
