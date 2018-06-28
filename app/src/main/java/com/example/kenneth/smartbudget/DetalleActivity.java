package com.example.kenneth.smartbudget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class DetalleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        Bundle bundle = getIntent().getExtras();
        String url = getIntent().getStringExtra("url");
        WebView webView = (WebView) findViewById(R.id.articulo_detalle);
        webView.loadUrl(url);
        setTitle("Noticias");

    }
}
