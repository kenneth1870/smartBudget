package com.example.kenneth.smartbudget;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private TextView texto_nombre;
    private TextView texto_email;
    private ImageView icono_perfil;
    private ImageView icono_fecha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        texto_nombre = (TextView) findViewById(R.id.texto_nombre);
        texto_email = (TextView) findViewById(R.id.texto_email);
        icono_perfil = (ImageView) findViewById(R.id.icono_perfil);
        icono_fecha = (ImageView) findViewById(R.id.icono_indicador_derecho);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    setUserData(user);
                } else {
                    Toast.makeText(getApplicationContext(), "No hemos podido cargar tu informacion " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
                }
            }
        };

        icono_fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GiveMePassword(findViewById(R.id.icono_indicador_derecho));

            }
        });
    }

    private void setUserData(FirebaseUser user) {
        texto_nombre.setText(user.getDisplayName());
        texto_email.setText(user.getEmail());
        if (user.getPhotoUrl() != null) {
            Glide.with(this).load(user.getPhotoUrl()).into(icono_perfil);
        } else {
            icono_perfil.setImageResource(R.drawable.ic_account);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);

    }


    @Override
    protected void onStop() {
        super.onStop();

        if (firebaseAuthListener != null) {
            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
        }
    }

    static EditText texto;

    public void DemeTexto(final View view) {
// Uso: DemeTexto(findViewById(R.id.btnNombreBoton))
        texto = new EditText(view.getContext());
        AlertDialog.Builder builder1 = new
                AlertDialog.Builder(view.getContext());
        builder1.setMessage("Digite su nueva contraseña");
        texto.setText("Dato");
        texto.selectAll();
        builder1.setView(texto);
        builder1.setCancelable(true);
        builder1.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        String newPassword = texto.getText().toString();
                        user.updatePassword(newPassword)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Mensaje("Tu contraseña fue actualizada");
                                        } else {
                                            Mensaje("Hemos topad0 con un error, intentalo más tarde :) ");
                                        }
                                    }
                                });
                    }
                });
    }

    public void GiveMePassword(final View view) {
        // Uso:
        texto = new EditText(view.getContext());
        AlertDialog.Builder builder1 = new AlertDialog.Builder(view.getContext());
        builder1.setMessage("Digite su nueva contraseña:");
        texto.setText("");
        texto.selectAll();
        builder1.setView(texto);

        builder1.setCancelable(true);
        builder1.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        String newPassword = texto.getText().toString();
                        user.updatePassword(newPassword)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Mensaje("Tu contraseña fue actualizada");
                                        } else {
                                            Mensaje("Hemos topada con un error, intentalo más tarde :) ");
                                        }
                                    }
                                });
                    }
                });

        builder1.setNegativeButton("Cancelar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Mensaje("Cancelado");
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    ;


    public void Mensaje(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }


}
