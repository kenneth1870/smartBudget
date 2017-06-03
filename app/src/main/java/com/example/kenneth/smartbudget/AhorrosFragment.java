package com.example.kenneth.smartbudget;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class AhorrosFragment extends Fragment {

    private FirebaseAuth firebaseAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference smartbudget_db;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ahorros, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Ahorros");
        // alambramos el Button

        LlenarListaObjetos();
        //LlenarListView();
        RegistrarClicks();

        Button MiButton;
        MiButton = (Button) view.findViewById(R.id.button2);
        //Programamos el evento onclick
        MiButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                SimpleDateFormat today = new SimpleDateFormat("dd/M/yyyy");
                String date = today.format(new Date());
                //saveCuenta("ahorros","gastos","ingresos","0","Efectivo","Dolares");
                saveAhorro(date, date ,"1" ,"99999", "Añonuevo","Semanal");
                LeeObjetoEnFirebase("ahorro01");

            }
        });


    }



    private void saveCuenta(String ahorro, String gasto, String ingreso, String monto_actual, String nombre, String tipo_moneda) {
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        Cuenta cuenta= new Cuenta(gasto,ahorro,ingreso,monto_actual,nombre,tipo_moneda);

        smartbudget_db = database.getReference("Users");
        smartbudget_db = smartbudget_db.child("user"+user.getUid());
        smartbudget_db.child("lista_cuentas").setValue(cuenta); // Guarda el objeto cuenta en la lista de cuentas.

            smartbudget_db.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) { }
                @Override
                public void onCancelled(DatabaseError databaseError) {}
            });

        }

    private void saveAhorro( String fechaFinal, String fechaInicial, String id, String monto, String nombre, String periodo) {

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        Ahorro ahorro = new Ahorro(fechaInicial,fechaFinal,id,monto,nombre,periodo);

        smartbudget_db = database.getReference("Users");
        smartbudget_db = smartbudget_db.child("user"+user.getUid());
        smartbudget_db = smartbudget_db.child("lista_cuentas");

        smartbudget_db.child("ahorros").child("ahorro04").setValue(ahorro);//Guarda el objeto ahorro en la lista de ahorros.

        smartbudget_db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }


    public void LeeObjetoEnFirebase(String nombreobj) {
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        // se supone que ya usted creo el objeto carro en su firebase
        smartbudget_db = database.getReference("Users");
        smartbudget_db = smartbudget_db.child("user"+user.getUid());
        smartbudget_db = smartbudget_db.child("lista_cuentas");
        smartbudget_db.child("ahorros").child(nombreobj).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long numChildren = dataSnapshot.getChildrenCount();
                Ahorro carro = dataSnapshot.getValue(Ahorro.class);
                MensajeOK(carro.getNombre()+" tiene un periodo: "+carro.getPeriodo());
                MensajeOK( String.valueOf(numChildren));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public List<ObjetosxDesplegar> misObjetos = new ArrayList<>();

    private void LlenarListaObjetos() {

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        // se supone que ya usted creo el objeto carro en su firebase
        smartbudget_db = database.getReference("Users");
        smartbudget_db = smartbudget_db.child("user"+user.getUid());
        smartbudget_db = smartbudget_db.child("lista_cuentas");
        smartbudget_db =  smartbudget_db.child("ahorros");

        smartbudget_db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                List<Ahorro> fcmAhorro = new ArrayList<>();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Ahorro  ahorro = postSnapshot.getValue(Ahorro.class);
                    fcmAhorro.add(ahorro);
                }

                // Check your arraylist size and pass to list view like
                if(fcmAhorro.size()>0){
                    for(int i=0;i<fcmAhorro.size();i++){
                        misObjetos.add(new ObjetosxDesplegar(fcmAhorro.get(i).getNombre(),fcmAhorro.get(i).getPeriodo(), fcmAhorro.get(i).getMonto(), R.drawable.ahorro));
                    }
                    ArrayAdapter<ObjetosxDesplegar> adapter = new MyListAdapter();
                    ListView list = (ListView) getActivity().findViewById(R.id.lista_ahorros);
                    list.setAdapter(adapter);
                }else{
                    // Display dialog for there is no user available.
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // for handling database error
            }
        });

    }

    public void MensajeOK(String msg){
        View v1 = getActivity().getWindow().getDecorView().getRootView();
        AlertDialog.Builder builder1 = new AlertDialog.Builder( v1.getContext());
        builder1.setMessage(msg);
        builder1.setCancelable(true);
        builder1.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {} });
        AlertDialog alert11 = builder1.create();
        alert11.show();
        }




    private void LlenarListView() {
        ArrayAdapter<ObjetosxDesplegar> adapter = new MyListAdapter();
        ListView list = (ListView) getActivity().findViewById(R.id.lista_ahorros);
        list.setAdapter(adapter);
    }
    private void RegistrarClicks() {
        ListView list = (ListView) getActivity().findViewById(R.id.lista_ahorros);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked,
                                    int position, long id) {
                ObjetosxDesplegar ObjEscogido = misObjetos.get(position);
                String message = "Elegiste item No.  " + (1+position)
                        + " que es un objeto cuyo primer atributo es  " + ObjEscogido.getAtributo01();
                MensajeOK(message);
            }
        });
    }


    private class MyListAdapter extends ArrayAdapter<ObjetosxDesplegar> {
        public MyListAdapter() {
            super(getActivity(), R.layout.desplegandoahorros, misObjetos);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Make sure we have a view to work with (may have been given null)
            View itemView = convertView;
            if (itemView == null) {
                itemView = getActivity().getLayoutInflater().inflate(R.layout.desplegandoahorros, parent, false);
            }
            ObjetosxDesplegar ObjetoActual = misObjetos.get(position);
            // Fill the view
            ImageView imageView = (ImageView)itemView.findViewById(R.id.ivdibujo);
            imageView.setImageResource(ObjetoActual.getNumDibujo());
            TextView elatributo01 = (TextView) itemView.findViewById(R.id.paraelatributo01);
            elatributo01.setText(ObjetoActual.getAtributo01());
            TextView elatributo02 = (TextView) itemView.findViewById(R.id.paraelatributo02);
            elatributo02.setText("" + ObjetoActual.getAtributo02());
            TextView elatributo03 = (TextView) itemView.findViewById(R.id.paraelatributo03);
            elatributo03.setText("" + ObjetoActual.getAtributo03());
            return itemView;
        }
    }

}
