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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kenneth.smartbudget.Modelo.Gasto;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;


public class GastosFragment extends Fragment {
    private FirebaseAuth firebaseAuth;
    private DatabaseReference smartbudget_db;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    public static String DirecUser = "";
    public static int indexSpend;
    private String Spend = "";
    private ListView listView;
    private ImageView img_gastos;
    private TextView info_gastos;

    public GastosFragment() {

    }
    public  static ArrayList<Gasto> listaGastos = new ArrayList<Gasto>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gastos, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        img_gastos = (ImageView) getActivity().findViewById(R.id.info_gastos);
        info_gastos = (TextView) getActivity().findViewById(R.id.gastos_lbl);
        getActivity().setTitle("Gastos");

    }



    private void populateListView() {
        ArrayAdapter<Gasto> adapter = new MyListAdapter();
        listView.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<Gasto> {

        public MyListAdapter() {
            super(getActivity(), R.layout.content_list_gastos, listaGastos);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup
                parent) {

            View itemView = convertView;
            if (itemView == null) {
                itemView = getActivity().getLayoutInflater().inflate(R.layout.content_list_gastos, parent, false);
            }

            Gasto currentGasto = listaGastos.get(position);

            TextView nombregasto = (TextView) itemView.findViewById(R.id.nombre_gasto_lbl);
            TextView tipo_gasto = (TextView) itemView.findViewById(R.id.tipo_gasto_lbl);
            TextView monto_gasto = (TextView) itemView.findViewById(R.id.total_gasto_lbl);
            TextView lugar_gasto = (TextView) itemView.findViewById(R.id.lugar_gasto_lbl);
            nombregasto.setText(currentGasto.getNombre_gasto());
            tipo_gasto.setText(currentGasto.getTipo_gasto());
            monto_gasto.setText("-₡" + currentGasto.getValor_gasto());
            lugar_gasto.setText(currentGasto.getUbicacion());

            return itemView;
        }
    }


    private void registerClickCallback() {
        ListView list = (ListView) getActivity().findViewById(R.id.list_gastos);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked,
                                    int position, long id) {
                Gasto clickedCar = listaGastos.get(position);
                eliminarGasto(viewClicked, clickedCar.getNombre_gasto().toString());

            }
        });
    }

    public void eliminarGasto(View view, final String gasto_id){
// Uso: DialogoSiNo(findViewById(R.id.btnNombreBoton))
        AlertDialog.Builder builder1 = new
                AlertDialog.Builder(view.getContext());
        builder1.setMessage("Estas seguro de hacer esto.");
        builder1.setCancelable(true);
        builder1.setPositiveButton("Si",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        BorrarGasto(gasto_id);
                        Toast.makeText(getActivity(), "Se elimino tu gasto",Toast.LENGTH_SHORT).show();
                    }
        });
        builder1.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        Toast.makeText(getActivity(), "Bien hecho",Toast.LENGTH_SHORT).show();
                    } });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void BorrarGasto(String gasto) {

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        smartbudget_db = database.getReference("Users");
        smartbudget_db = smartbudget_db.child("user"+user.getUid());

        smartbudget_db.child("Gastos").child(gasto).removeValue();

        smartbudget_db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }

    public List<Ahorro> misObjetos = new ArrayList<>();

    private void LlenarListaObjetos() {
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        smartbudget_db = database.getReference("Users");
        smartbudget_db = smartbudget_db.child("user"+user.getUid());
        smartbudget_db = smartbudget_db.child("lista_cuentas");
        smartbudget_db.child("ahorros").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                misObjetos.clear();
                List<Ahorro> fcmAhorro = new ArrayList<>();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    try{
                        Ahorro  ahorro = postSnapshot.getValue(Ahorro.class);
                        fcmAhorro.add(ahorro);
                    }
                    catch (Exception e) { System.out.println("Instrucciones a ejecutar cuando se produce un error");  }

                }
                // Check your arraylist size and pass to list view like

                if(fcmAhorro.size()>0){//Listo to view (to do what you need).
                    for(int i=0;i<fcmAhorro.size();i++){
                        if(!misObjetos.contains(fcmAhorro.get(i)))
                            misObjetos.add(fcmAhorro.get(i));
                    }
                  //  ArrayAdapter<Ahorro> adapter = new MyListAdapter();

                    //
                   // ListView list = (ListView) getActivity().findViewById(R.id.lista_ahorros);
                    //list.setAdapter(adapter);
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






}
