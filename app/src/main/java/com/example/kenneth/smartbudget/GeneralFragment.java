package com.example.kenneth.smartbudget;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static com.example.kenneth.smartbudget.GastosFragment.DirecUser;
import static com.example.kenneth.smartbudget.IngresosFragment.MyAdapter;
import static com.example.kenneth.smartbudget.IngresosFragment.texto;
import static com.example.kenneth.smartbudget.R.drawable.ahorro;
import static com.example.kenneth.smartbudget.R.drawable.googleg_disabled_color_18;


public class GeneralFragment extends Fragment {

    private PieChart pieChart;
    private ArrayList<Entry> entries;
    ArrayList<String> labels;
    public static String DirecUser = "";
    public static int indexSpend;
    private String Spend = "";

    private FirebaseAuth firebaseAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference smartbudget_db;
    User user;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_general, container, false);
        OnclickDelImageView(view.findViewById(R.id.añadircuenta));
        //mostrarGraficaRedonda(view);

        validarUsuario(view);
        mostrarSaldo();
        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView MiImageView = (ImageView) view.findViewById(R.id.buttonlapiz);
        MiImageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditarSaldoIcono(v);
            }
        });

    }

    static EditText texto;

    public void EditarSaldoIcono(final View view) {
        // Uso:
        texto = new EditText(view.getContext());
        AlertDialog.Builder builder1 = new AlertDialog.Builder(view.getContext());
        builder1.setMessage("Edite su saldo:");
        texto.setText("");
        texto.selectAll();
        builder1.setView(texto);

        builder1.setCancelable(true);
        builder1.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Spend = texto.getText().toString();
                        EditarSaldo(Spend);
                        guardarSaldo();

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
    private void EditarSaldo( String monto_actual) {

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        smartbudget_db = database.getReference("Users");
        smartbudget_db = smartbudget_db.child("user"+user.getUid());
        smartbudget_db = smartbudget_db.child("lista_cuentas");

        smartbudget_db.child("monto_actual").setValue(monto_actual);//Guarda el objeto ahorro en la lista de ahorros.

        smartbudget_db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }

    public void mostrarSaldo() {
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        // se supone que ya usted creo el objeto carro en su firebase
        smartbudget_db = database.getReference("Users");
        smartbudget_db = smartbudget_db.child("user"+user.getUid());
        smartbudget_db = smartbudget_db.child("lista_cuentas");
        smartbudget_db =  smartbudget_db.child("monto_actual");
        smartbudget_db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long numChildren = dataSnapshot.getChildrenCount();
                String monto_actual = dataSnapshot.getValue(String.class);

                if(monto_actual!=null){
                    TextView Mi_textview = (TextView) getActivity().findViewById(R.id.saldo);
                    TextView Mi_textview2 = (TextView) getActivity().findViewById(R.id.efectivo);
                    Mi_textview.setText("₡"+monto_actual);
                    Mi_textview2.setText("₡"+monto_actual);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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

    public void mostrarGraficaRedonda(final View view ){
        entries = new ArrayList<>();
       firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        smartbudget_db = database.getReference("Users");
        smartbudget_db = smartbudget_db.child("user"+user.getUid());
        smartbudget_db = smartbudget_db.child("lista_cuentas");
        smartbudget_db =  smartbudget_db.child("monto_actual");
        smartbudget_db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long numChildren = dataSnapshot.getChildrenCount();
                String monto_actual = dataSnapshot.getValue(String.class);

                if(monto_actual!=null){
                    pieChart = (PieChart) view.findViewById(R.id.chart);


                        entries.add(new Entry(4f,Integer.parseInt(monto_actual)));
                        //MensajeOK(monto_actual+"");
                    //entries = new ArrayList<>();
                    //entries.add(new Entry(12f, 3));
                    //entries.add(new Entry(18f, 4));
                    // entries.add(new Entry(9f, 5));
                  /*entries.add(new Entry(40f, 100));
                    entries.add(new Entry(8f, 1));
                    entries.add(new Entry(6f, 2));*/

                    PieDataSet dataset = new PieDataSet(entries, "# of Calls");
                    labels = new ArrayList<String>();

                    labels.add("January");
                    labels.add("February");
                    labels.add("March");
                    labels.add("April");
                    labels.add("May");
                    labels.add("June");

                    PieData data = new PieData(labels, dataset);
                    dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                    pieChart.setDescription("Description");
                    pieChart.setData(data);

                    pieChart.animateY(3000); //hace aparecer

                    pieChart.saveToGallery("/sd/mychart.jpg", 85);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //android:visibility="invisible"
/*TableLayout aux = (TableLayout) findViewById(R.id.tablebotones);
//aux.setVisibility(aux.VISIBLE);
if (aux.getVisibility()==aux.VISIBLE){
aux.setVisibility(aux.INVISIBLE);} else {aux.setVisibility(aux.VISIBLE);}*/

    }
    public void validarUsuario(final View view){

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user!=null){
            //MensajeOK("mostrar"+user.getEmail());
            mostrarGraficaRedonda(view);
            //guardarSaldo();
        }else{
            MensajeOK("no hay"+user.getEmail());
        }
    }

    public void OnclickDelImageView(View view) {
        // Ejemplo  OnclickDelImageView(R.id.MiImageView);

        ImageView miImageView = (ImageView)  view;
        //  final String msg = miImageView.getText().toString();       // 2.  Programar el evento onclick
        miImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // if(msg.equals("Texto")){Mensaje("Texto en el botón ");};
                switch (v.getId()) {

                    case R.id.añadircuenta:
                        IngresosFragment newFragment = new IngresosFragment();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.exv, newFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();

                        break;
                    default:break; }// fin de casos
            }// fin del onclick
        });
    }// fin de OnclickDelImageView

    public void guardarSaldo(){

       // String nombreIngreso =
        //MyAdapter.CreateFather(nombreIngreso);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        // se supone que ya usted creo el objeto carro en su firebase
        smartbudget_db = database.getReference("Users");
        smartbudget_db = smartbudget_db.child("user"+user.getUid());
        smartbudget_db = smartbudget_db.child("Ingresos");
        smartbudget_db =  smartbudget_db.child("Comida").child("Monto");
        smartbudget_db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long numChildren = dataSnapshot.getChildrenCount();
                String monto = dataSnapshot.getValue(String.class);

                /*List<String> listaIngreso = new ArrayList<>();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Ingreso  ingreso = postSnapshot.getValue(Ingreso.class);
                    monto = ingreso.getValor();
                    listaIngreso.add(monto);
                }*/


                if(monto!=null){
                   // TextView Mi_textview = (TextView) getActivity().findViewById(R.id.saldo);
                   // TextView Mi_textview2 = (TextView) getActivity().findViewById(R.id.efectivo);
                    //Mi_textview.setText("₡"+monto_actual);
                    //Mi_textview2.setText("₡"+monto_actual);
                    MensajeOK(monto);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}