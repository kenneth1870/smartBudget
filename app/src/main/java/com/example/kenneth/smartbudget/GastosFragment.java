package com.example.kenneth.smartbudget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kenneth.smartbudget.Modelo.Gasto;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


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
    ArrayList<Gasto> listaGastos = new ArrayList<Gasto>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gastos, container, false);
        //loadGastos();
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        smartbudget_db = database.getReference("Users");
        smartbudget_db = smartbudget_db.child("user" + user.getUid());
        smartbudget_db = smartbudget_db.child("Gastos");

        smartbudget_db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Gasto gasto = postSnapshot.getValue(Gasto.class);
                    listaGastos.add(gasto);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        populateListView();
        listView = (ListView) v.findViewById(R.id.list_gastos);

        return v;
    }




    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        img_gastos = (ImageView) getActivity().findViewById(R.id.info_gastos);
        info_gastos = (TextView) getActivity().findViewById(R.id.gastos_lbl);

        getActivity().setTitle("Gastos");

    }




    public void loadGastos() {
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        smartbudget_db = database.getReference("Users");
        smartbudget_db = smartbudget_db.child("user" + user.getUid());
        smartbudget_db = smartbudget_db.child("Gastos");

        smartbudget_db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Gasto gasto = postSnapshot.getValue(Gasto.class);
                    listaGastos.add(gasto);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


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
            monto_gasto.setText(currentGasto.getValor_gasto());
            lugar_gasto.setText(currentGasto.getUbicacion());


            return itemView;
        }
    }


}
