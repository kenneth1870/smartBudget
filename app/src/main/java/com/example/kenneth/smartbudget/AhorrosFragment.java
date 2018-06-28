package com.example.kenneth.smartbudget;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.GestureDetector;
import android.view.MotionEvent;


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

import static com.example.kenneth.smartbudget.R.drawable.ahorro;


public class AhorrosFragment extends Fragment implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{

    private FirebaseAuth firebaseAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference smartbudget_db;

    private ProgressBar firstBar = null;
    private ProgressBar secondBar = null;

    private GestureDetector gestos ;
    private View vTouch=null;

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
        firstBar = (ProgressBar) getActivity().findViewById(R.id.firstBar);
        secondBar = (ProgressBar)getActivity().findViewById(R.id.secondBar);
        //Programamos el evento onclick
        MiButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                SimpleDateFormat today = new SimpleDateFormat("dd/M/yyyy");
                String date = today.format(new Date());

              //  saveCuenta("ahorros","gastos","ingresos","0","Efectivo");
                //LeeObjetoEnFirebase("ahorro01");
                //BorrarAhorro("ahorro06");

                Intent intento = new Intent(getActivity().getApplicationContext(), IngresarAhorro.class);
                startActivity(intento);
            }
        });

        gestos = new GestureDetector(getActivity(), this);
        ListView Mi_imageview = (ListView) view.findViewById(R.id.lista_ahorros);
        Mi_imageview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // La siguiente asingación  recoge cuál view  usamos.
                vTouch = v;
                return gestos.onTouchEvent(event);
            }
        });
    }

    private void saveCuenta(String ahorro, String gasto, String ingreso, String monto_actual, String nombre, String tipo_moneda) {
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        Cuenta cuenta= new Cuenta(gasto,ahorro,ingreso,monto_actual,nombre);

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
                MensajeOK(carro.getNombre()+" tiene un periodo: "+carro.getTipoMoneda());
                MensajeOK( String.valueOf(numChildren));
                //To do guardar data en view
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
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
                    ArrayAdapter<Ahorro> adapter = new MyListAdapter();
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

    public void BorrarAhorro(String nombreobj) {
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        smartbudget_db = database.getReference("Users");
        smartbudget_db = smartbudget_db.child("user"+user.getUid());
        smartbudget_db = smartbudget_db.child("lista_cuentas");

        smartbudget_db.child("ahorros").child(nombreobj).removeValue();

        smartbudget_db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
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
        ArrayAdapter<Ahorro> adapter = new MyListAdapter();
        ListView list = (ListView) getActivity().findViewById(R.id.lista_ahorros);
        list.setAdapter(adapter);
    }
    private void RegistrarClicks() {
        ListView list = (ListView) getActivity().findViewById(R.id.lista_ahorros);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked,
                                    int position, long id) {
                Ahorro ObjEscogido = misObjetos.get(position);
                /*String message = "Elegiste item No.  " + (1+position)
                        + " que es un objeto cuyo primer atributo es  " + ObjEscogido.getNombre();
                MensajeOK(message);*/
                VariablesGlobales vg = VariablesGlobales.getInstance();vg.setIdActual(ObjEscogido.getId());
                ListView milistview = (ListView) getActivity().findViewById(R.id.lista_ahorros);
                registerForContextMenu(milistview);
            }
        });
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {

        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    public boolean onTouchEvent(MotionEvent event) {
        gestos.onTouchEvent(event);
        vTouch = null;
        return getActivity().onTouchEvent(event);
    }


    private class MyListAdapter extends ArrayAdapter<Ahorro> {
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
            Ahorro ObjetoActual = misObjetos.get(position);
            // Fill the view
            ImageView imageView = (ImageView)itemView.findViewById(R.id.ivdibujo);
            imageView.setImageResource(ahorro);
            TextView elatributo01 = (TextView) itemView.findViewById(R.id.paraelatributo01);
            elatributo01.setText(ObjetoActual.getNombre());
            TextView elatributo02 = (TextView) itemView.findViewById(R.id.paraelatributo02);
            elatributo02.setText("" + ObjetoActual.getFechaFinal());
            TextView elatributo03 = (TextView) itemView.findViewById(R.id.paraelatributo03);
            elatributo03.setText("" + ObjetoActual.getMontoInicial());
            TextView elatributo04 = (TextView) itemView.findViewById(R.id.paraelatributo04);
            elatributo04.setText(ObjetoActual.getObjetivo());
            TextView elatributo05 = (TextView) itemView.findViewById(R.id.paraelatributo05);
            elatributo05.setText("" + ObjetoActual.getTipoMoneda());
            return itemView;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v.getId()==R.id.lista_ahorros) {
            menu.setHeaderTitle("Opciones");
            menu.add(0, 1, 0, "Editar");
            menu.add(0, 2, 0, "Eliminar");
            menu.add(0, 3, 0, "Detalles");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        int opcionseleccionada = item.getItemId();
        switch (item.getItemId()) {
            case 1: Intent intento = new Intent(getActivity().getApplicationContext(), EditarAhorro.class);
                startActivity(intento);
                break;
            case 2:

                    VariablesGlobales vg = VariablesGlobales.getInstance(); int i = vg.getIdActual();
                    BorrarAhorro("ahorro0"+i);
                    MensajeOK("Elemento eliminado correctamente");

                break;
            case 3: MensajeOK("Op. 3"); break;
            case 4: MensajeOK("Op. 4"); break;
            case 5: MensajeOK("Op. 5"); break;
            case 6: MensajeOK("Op. 6"); break;
            //case ?: Mensaje("Op. ?"); break;
            default:  MensajeOK("No clasificado"); break;
        }
        return true;
    }


}
