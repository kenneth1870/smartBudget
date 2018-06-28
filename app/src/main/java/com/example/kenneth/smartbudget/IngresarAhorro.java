package com.example.kenneth.smartbudget;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

import static android.R.attr.id;
import static android.R.id.empty;
import static android.os.Build.VERSION_CODES.M;
import static com.example.kenneth.smartbudget.R.id.inicial;

public class IngresarAhorro extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference smartbudget_db;

    EditText date;
    DatePickerDialog datePickerDialog;
    Button MiButton;
    TextView nombre;
    TextView objetivo;
    TextView fecha;
    TextView inicial;
    Spinner moneda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_ahorro);
        // A continuación mi código para OnCreate
        Mensaje("Agregar Ahorro");

        // initiate the date picker and a button
        date = (EditText) findViewById(R.id.date);
        // perform click event on edit text
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender

                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(IngresarAhorro.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                date.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }

                return v;
            }

            @Override
            public int getCount() {
                return super.getCount()-1; // you dont display last item. It is used as hint.
            }

        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.add("Colón");
        adapter.add("Dólar");
        adapter.add("Yen");
        adapter.add("Pesos");
        adapter.add("Tipo de moneda"); //This is the text that will be displayed as hint.


        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getCount()); //set the hint the default selection so it appears on launch.
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        // alambramos el Button
        MiButton = (Button) findViewById(R.id.listo);
        nombre = (TextView) findViewById(R.id.nombre);
        objetivo = (TextView) findViewById(R.id.objetivo);
        fecha = (TextView) findViewById(R.id.date);
        inicial  = (TextView) findViewById(R.id.inicial);
        moneda = (Spinner)findViewById(R.id.spinner);



        //Programamos el evento onclick

        MiButton.setOnClickListener(new View.OnClickListener(){

            @Override

            public void onClick(View arg0) {

                boolean flag=false;
                String text= null;
                if(moneda != null && moneda.getSelectedItem() !=null) {
                    text = (String)moneda.getSelectedItem();
                    if (!text.equals("Tipo de moneda")) {
                        flag=true;
                    }
                } else  {

                }

                if (nombre.getText().toString().length()>0 && objetivo.getText().toString().length()>0&&fecha.getText().toString().length()>0 && flag){

                    saveAhorro(nombre.getText().toString(), objetivo.getText().toString() ,fecha.getText().toString(), inicial.getText().toString(),text);
                    MensajeToast("Ahorro agregado correctamente");

                    onBackPressed();medioSeg();

                }else {
                    MensajeToast("Por favor llene todos los campos");//null or empty
                }



            }

        });

    } // Fin del Oncreate

    private void saveAhorro( String nombre, String objetivo, String fechaFinal, String montoInicial, String tipoMoneda) {

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        VariablesGlobales vg = VariablesGlobales.getInstance(); int i = vg.getMivalor();
        Ahorro ahorro = new Ahorro(nombre,objetivo,fechaFinal,montoInicial,tipoMoneda,i);

        smartbudget_db = database.getReference("Users");
        smartbudget_db = smartbudget_db.child("user"+user.getUid());
        smartbudget_db = smartbudget_db.child("lista_cuentas");
        smartbudget_db = smartbudget_db.child("ahorros");
        smartbudget_db.child("ahorro0"+i).setValue(ahorro);//Guarda el objeto ahorro en la lista de ahorros.
        vg.addMivalor();
        smartbudget_db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }

    public void MensajeOK(String msg){
        View v1 = getWindow().getDecorView().getRootView();
        AlertDialog.Builder builder1 = new AlertDialog.Builder( v1.getContext());
        builder1.setMessage(msg);
        builder1.setCancelable(true);
        builder1.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {} });
        AlertDialog alert11 = builder1.create();
        alert11.show();
        ;};


    public void Mensaje(String msg){getSupportActionBar().setTitle(msg);};

    public void MensajeToast(String msg){Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();}

    private void medioSeg(){  try{Thread.sleep(500); }catch (InterruptedException e){}
    }

} // [22:49:54] Fin de la Clase Actividad 01