package com.example.kenneth.smartbudget;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class NotificationsActivity extends AppCompatActivity {
    private TextView verificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        final ToggleButton toggle = (ToggleButton) findViewById(R.id.si_no);
        toggle.setOnCheckedChangeListener(new
                                                  CompoundButton.OnCheckedChangeListener() {
                                                      public void onCheckedChanged(CompoundButton buttonView, boolean
                                                              isChecked) {
                                                          if (isChecked) {
                                                              Mensaje("Te enviaremos notificaciones.");
                                                              toggle.setBackgroundColor(Color.rgb(0, 172, 193));
                                                          } else {
                                                              Mensaje("Lo sentimos!");
                                                              toggle.setBackgroundColor(Color.GRAY);
                                                          }
                                                      }
                                                  });

    }

    public void Mensaje(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }


}
