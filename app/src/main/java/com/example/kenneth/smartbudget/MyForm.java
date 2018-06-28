package com.example.kenneth.smartbudget;

import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.R.string.no;
import static android.os.Build.VERSION_CODES.M;
import static com.example.kenneth.smartbudget.IngresosFragment.MyAcounts;

/**
 * Created by ariel on 30/5/2017.
 */

public class MyForm {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference smartbudget_db;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    private int ID;
    private boolean Activate = true;
    private Context MyContext;
    private LinearLayout MyLayout;

    private TextView TextNameAcount;
    private TextView TextRode;
    private TextView TextCoin;
    private TextView TextType;

    private EditText EditNameAcount;
    private EditText EditRode;

    private Spinner SpinCoin;
    private Spinner SpinType;

    private Button BtnSave;
    private Button BtnClear;

    public MyForm(Context myContext, int ID) {
        MyContext = myContext;
        this.ID = ID;
        InitComponents();
    }

    private void InitComponents(){
        MyLayout = new LinearLayout(MyContext);
        MyLayout.setOrientation(LinearLayout.VERTICAL);
        MyLayout.setBackgroundResource(R.drawable.borderchild);

        TextNameAcount = new TextView(this.MyContext);
        TextRode = new TextView(this.MyContext);
        TextCoin = new TextView(this.MyContext);
        TextType = new TextView(this.MyContext);

        TextNameAcount.setText("Nombre de la cuenta:");
        TextNameAcount.setTextColor(Color.BLACK);
        TextRode.setText("Monto:");
        TextRode.setTextColor(Color.BLACK);
        TextCoin.setText("Tipo de moneda:");
        TextCoin.setTextColor(Color.BLACK);
        TextType.setText("Tipo de ingreso:");
        TextType.setTextColor(Color.BLACK);

        EditNameAcount = new EditText(MyContext);
        EditNameAcount.setText(IngresosFragment.MyAcounts.get(ID));
        EditRode = new EditText(MyContext);
        EditRode.setInputType(InputType.TYPE_CLASS_NUMBER);

        SpinCoin = new Spinner(MyContext);
        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(MyContext,R.array.Coin,android.R.layout.simple_gallery_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinCoin.setAdapter(adapter1);
        SpinCoin.setBackgroundColor(Color.WHITE);

        SpinType = new Spinner(MyContext);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(MyContext,R.array.Savings,android.R.layout.simple_gallery_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinType.setAdapter(adapter);
        SpinType.setBackgroundColor(Color.WHITE);

        BtnSave = new Button(MyContext);
        BtnSave.setText("Guardar cuenta");
        BtnSave.setWidth(MyLayout.getWidth());
        BtnSave.setTextColor(Color.BLACK);
        BtnSave.setBackgroundColor(Color.WHITE);
        BtnSave.setBackgroundResource(R.drawable.borde);

        BtnClear = new Button(MyContext);
        BtnClear.setText("Borrar cuenta");
        BtnClear.setWidth(MyLayout.getWidth());
        BtnClear.setTextColor(Color.BLACK);
        BtnClear.setBackgroundColor(Color.WHITE);
        BtnClear.setBackgroundResource(R.drawable.borde);

        MyLayout.addView(TextNameAcount);
        MyLayout.addView(EditNameAcount);
        MyLayout.addView(TextRode);
        MyLayout.addView(EditRode);
        MyLayout.addView(TextCoin);
        MyLayout.addView(SpinCoin);
        MyLayout.addView(TextType);
        MyLayout.addView(SpinType);
        MyLayout.addView(BtnSave);
        MyLayout.addView(BtnClear);
    }

    public LinearLayout GetForm(){return this.MyLayout;}

    public void LoadSave(){
        BtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Activate){
                    IngresosFragment.MyAcounts.set(ID,EditNameAcount.getText().toString());
                    saveIngreso(EditNameAcount.getText().toString(), EditRode.getText().toString(), SpinCoin.getSelectedItem().toString(),SpinType.getSelectedItem().toString());
                    Toast.makeText(MyContext, "Se ha recuperado esta cuenta", Toast.LENGTH_SHORT).show();
                    Activate = true;

                }
                else{
                    IngresosFragment.MyAcounts.set(ID,EditNameAcount.getText().toString());
                    saveIngreso(EditNameAcount.getText().toString(), EditRode.getText().toString(), SpinCoin.getSelectedItem().toString(),SpinType.getSelectedItem().toString());
                    Toast.makeText(MyContext, "Se ha guardado el ingreso", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void ClearAccount(){
        BtnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Activate){
                    Toast.makeText(MyContext, "Esta cuenta ya ha sido borrada", Toast.LENGTH_SHORT).show();
                }
                else{
                    BorrarIngreso(IngresosFragment.MyAcounts.get(ID));
                    Activate = false;
                    Toast.makeText(MyContext, "Se ha borrado el ingreso", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveIngreso(String nombre, String monto, String tipo_moneda, String tipo_ingreso) {

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        //Ingreso ingreso_actual = new Ingreso(nombre,monto,tipo_ingreso,tipo_moneda,id,fecha);

        smartbudget_db = database.getReference("Users");
        smartbudget_db = smartbudget_db.child("user"+user.getUid());
        smartbudget_db = smartbudget_db.child("Ingresos");
        smartbudget_db = smartbudget_db.child(nombre);

        smartbudget_db.child("Nombre Cuenta").setValue(nombre);
        smartbudget_db.child("Monto").setValue(monto);
        smartbudget_db.child("Tipo moneda").setValue(tipo_moneda);
        smartbudget_db.child("Tipo ingreso").setValue(tipo_ingreso);

        smartbudget_db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }

    private void BorrarIngreso(String nombreobj) {

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        smartbudget_db = database.getReference("Users");
        smartbudget_db = smartbudget_db.child("user"+user.getUid());

        smartbudget_db.child("Ingresos").child(nombreobj).removeValue();

        smartbudget_db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });

    }

    public boolean GetActivate(){return Activate;}
}
