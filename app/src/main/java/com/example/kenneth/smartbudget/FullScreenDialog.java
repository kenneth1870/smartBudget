package com.example.kenneth.smartbudget;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by kenneth on 3/6/17.
 */

public class FullScreenDialog extends DialogFragment {
    private FirebaseAuth firebaseAuth;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference smartbudget_db;
    private EditText nombre_gasto;
    private Spinner tipo_gasto;
    private TextView ubicacion;
    private EditText monto_gasto;

    public FullScreenDialog() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        // Obtener instancia de la action bar
        ActionBar actionBar = ((AppCompatActivity) getActivity())
                .getSupportActionBar();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fullscreen_dialog, container, false);
        nombre_gasto = (EditText) view.findViewById(R.id.nombre_gasto);
        monto_gasto = (EditText) view.findViewById(R.id.monto_gasto);
        tipo_gasto = (Spinner) view.findViewById(R.id.tipo_gasto);
        ubicacion = (TextView) view.findViewById(R.id.ubicacion_gasto);


        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fullscreen_dialog, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_close:
                procesarDescartar();
                break;
            case R.id.action_save:
                saveGasto(nombre_gasto.getText().toString(), monto_gasto.getText().toString(), "San jose", tipo_gasto.getSelectedItem().toString());
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void procesarDescartar() {

        Fragment fragment = null;
        fragment = new GastosFragment();
        this.getFragmentManager().beginTransaction()
                .replace(R.id.contenedor_principal, fragment, null)
                .addToBackStack(null)
                .commit();
    }

    private void saveGasto(String nombre_gasto, String monto_gasto, String ubicacion, String tipo_gasto) {

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        smartbudget_db = database.getReference("Users");
        smartbudget_db = smartbudget_db.child("user" + user.getUid());
        smartbudget_db = smartbudget_db.child("Gastos");
        smartbudget_db = smartbudget_db.child(nombre_gasto);

        smartbudget_db.child("nombre_gasto").setValue(nombre_gasto);
        smartbudget_db.child("valor_gasto").setValue(monto_gasto);
        smartbudget_db.child("ubicacion").setValue(ubicacion);
        smartbudget_db.child("tipo_gasto").setValue(tipo_gasto);

        smartbudget_db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                procesarDescartar();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


}