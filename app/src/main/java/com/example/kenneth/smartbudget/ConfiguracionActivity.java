package com.example.kenneth.smartbudget;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ConfiguracionActivity extends AppCompatActivity  {
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private String texto_nombre;
    private String texto_email;
    private Uri icono_perfil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    setUserData(user);
                }else{
                    Toast.makeText(getApplicationContext(), "No hemos podido cargar tu informacion " + user.getDisplayName() , Toast.LENGTH_SHORT).show();
                }
            }
        };

        LlenarListView();
        DandoClickALosItems();



    }


    private void setUserData(FirebaseUser user) {
        texto_nombre = user.getDisplayName();
        texto_email = user.getEmail();
        icono_perfil = user.getPhotoUrl();

    }

    private void LlenarListView() {
        String[] presidentes = {
                "Notificaciones",
                "Cuenta"
        };

        ArrayAdapter<String> adaptador =new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, presidentes);
        ListView milistview = (ListView) findViewById(R.id.listview);
        milistview.setAdapter(adaptador);
    }

    //  Paso 4.  Crear un OnItemClickListener para ejecutar una acción cuando el usuario escoge un item.


    public void DandoClickALosItems() {

        ListView list = (ListView) findViewById(R.id.listview);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> paret, View viewClicked,
                                    int position, long id)
            { TextView textView = (TextView) viewClicked;
                final int Notificaciones = 0; //should be equal to the index in your array.
                final int Perfil = 1;
                FragmentTransaction FT = getSupportFragmentManager().beginTransaction();
                final Fragment perfil= new PerfilFragment();
                final Fragment notificaciones = new NotificacionesFragment();

                switch (position) {
                    case Notificaciones:
                        FT.replace(R.id.contenedor_config, notificaciones);
                        break;
                    case  Perfil:
                        FT.replace(R.id.contenedor_config, perfil);
                        Bundle data = new Bundle();
                        data.putString("nombre", texto_nombre);
                        data.putString("email", texto_email);
                        data.putString("photo", icono_perfil.toString());
                        perfil.setArguments(data);

                        break;

                    default:
                        break;
                }
                FT.commit();
            }
        });
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



}
