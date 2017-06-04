package com.example.kenneth.smartbudget;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

// Se debe crear en el paquete com.example...
// Forma de uso:
// VariablesGlobales vg = VariablesGlobales.getInstance(); vg.setMitexto("Hola");    int i = vg.getMivalor();
public class VariablesGlobales {
    private int idAhorro=1;
    private int idActual=0;
    private String nombre="";

    private FirebaseAuth firebaseAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference smartbudget_db;



    private static VariablesGlobales instance = null;

    protected VariablesGlobales() {}
    public static VariablesGlobales getInstance() {
        if(instance == null) {instance = new VariablesGlobales(); }
        return instance;
    }

    public int getMivalor() {
        return idAhorro;
    }

    public void setMivalor(int mivalor) {
        this.idAhorro = mivalor;
    }

    public void addMivalor() {
        idAhorro++;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String minum) {
        this.nombre = minum;
    }

    public int getIdActual() {
        return idActual;
    }

    public void setIdActual(int idActual) {
        this.idActual = idActual;
    }

    public int obtenerUltimo() {
        final int aux=0;
        try{


        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        // se supone que ya usted creo el objeto carro en su firebase
        smartbudget_db = database.getReference("Users");
        smartbudget_db = smartbudget_db.child("user"+user.getUid());
        smartbudget_db = smartbudget_db.child("lista_cuentas");

        smartbudget_db.child("ahorros").orderByChild("id").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                Ahorro dinosaur = dataSnapshot.getValue(Ahorro.class);
                System.out.println(dataSnapshot.getKey() + " was " + dinosaur.id + " meters tall.");
                //aux=dinosaur.getId();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        }catch (Exception e) { System.out.println("Instrucciones a ejecutar cuando se produce un error");  }
        return aux;
    }

}// fin de la clase de variables globales
