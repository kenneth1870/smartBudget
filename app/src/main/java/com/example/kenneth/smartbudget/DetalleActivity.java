package com.example.kenneth.smartbudget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class DetalleActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        WebView webView = (WebView) findViewById(R.id.webview);
        webView.loadUrl("https://www.forbes.com.mx/trafico-de-pasajeros-en-latinoamerica-crecera-4-5-hacia-2035-airbus/");

    }

}
