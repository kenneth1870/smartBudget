package com.example.kenneth.smartbudget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.kenneth.smartbudget.Modelo.VariablesGlobales;

import java.util.ArrayList;
import java.util.List;


public class IngresosFragment extends Fragment {

    public static MiAdaptador MyAdapter;
    static EditText texto;
    public static List<String> MyAcounts = new ArrayList<String>();
    public static int cont = -1;
    public static List<MyForm> MyForms = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view =inflater.inflate(R.layout.fragment_ingresos, container, false);
        ExpandableListView exv =  (ExpandableListView) view.findViewById(R.id.exv);
        MyAdapter = new MiAdaptador(this.getContext());
        exv.setAdapter(MyAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Ingresos");
    }

    public static void DemeTexto(View view){
        // Uso:
        texto =  new EditText(view.getContext());
        AlertDialog.Builder builder1 = new AlertDialog.Builder(view.getContext());
        builder1.setMessage("Digite el nombre del ingreso:");
        texto.setText("");
        texto.selectAll();
        builder1.setView(texto);

        builder1.setCancelable(true);
        builder1.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MyAdapter.CreateFather(texto.getText().toString());
                        VariablesGlobales vg = VariablesGlobales.getInstance();
                        vg.setMitexto(texto.getText().toString());
                    }
                });

        builder1.setNegativeButton("Cancelar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Toast.makeText(, "No se registró ingreso",Toast.LENGTH_SHORT).show();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

}
