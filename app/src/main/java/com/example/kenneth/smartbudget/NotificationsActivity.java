package com.example.kenneth.smartbudget;

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
        ToggleButton toggle = (ToggleButton) findViewById(R.id.si_no);
        toggle.setOnCheckedChangeListener(new
                                                  CompoundButton.OnCheckedChangeListener() {
                                                      public void onCheckedChanged(CompoundButton buttonView, boolean
                                                              isChecked) {
                                                          if (isChecked) {
                                                              Mensaje("Te enviaremos notificaciones.");
                                                          } else {
                                                              Mensaje("Lo sentimos!");
                                                          }
                                                      }
                                                  });

    }

    public void Mensaje(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }


}
