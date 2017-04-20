package com.example.kenneth.smartbudget;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class NotificacionesFragment extends Fragment {
    private TextView verificacion;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_notificaciones, container, false);


       verificacion = (TextView) v.findViewById(R.id.verificacion);
        Button MiBoton = (Button) v.findViewById(R.id.btnsiono);
        MiBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                DialogoSiNo(v.findViewById(R.id.btnsiono));
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Notificaciones");
    }

    public void DialogoSiNo(View view){

        AlertDialog.Builder builder1 = new
                AlertDialog.Builder(view.getContext());
        builder1.setMessage("Deseas recibir notificaciones");
        builder1.setCancelable(true);
        builder1.setPositiveButton("Si",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id)
                    {verificacion.setText("Si"); } });
        builder1.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id)
                    {verificacion.setText("No"); } });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    };



}
