package com.example.kenneth.smartbudget;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class GastosFragment extends Fragment {

    public static String DirecUser = "";
    public static int indexSpend;
    private String Spend = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gastos, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Gastos");

        ((Button) view.findViewById(R.id.button_Location)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intento = new Intent(getActivity(), MapsActivity.class);
                getActivity().startActivity(intento);
                GiveMeLocation(getActivity().findViewById(R.id.editText));
            }
        });
    }

    static EditText texto;

    public void GiveMeLocation(final View view) {
        // Uso:
        texto = new EditText(view.getContext());
        AlertDialog.Builder builder1 = new AlertDialog.Builder(view.getContext());
        builder1.setMessage("Digite su gasto:");
        texto.setText("");
        texto.selectAll();
        builder1.setView(texto);

        builder1.setCancelable(true);
        builder1.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Spend = DirecUser + ": " + texto.getText().toString() + " colones";
                        ((EditText) view.findViewById(R.id.editText)).setText(Spend);
                    }
                });

        builder1.setNegativeButton("Cancelar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getActivity(), "Se canceló la operación", Toast.LENGTH_SHORT).show();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    ;
}
