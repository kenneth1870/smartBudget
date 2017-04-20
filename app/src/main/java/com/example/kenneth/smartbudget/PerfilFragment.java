package com.example.kenneth.smartbudget;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class PerfilFragment extends Fragment {

    private TextView texto_nombre;
    private TextView texto_email;
    private ImageView icono_perfil;
    private ImageView icono_fecha;

    public PerfilFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String nombre =getArguments().getString("nombre");
        String email =getArguments().getString("email");
        String foto =getArguments().getString("photo");

        View v = inflater.inflate(R.layout.fragment_perfil, container, false);
        texto_nombre = (TextView) v.findViewById(R.id.texto_nombre);
        texto_email = (TextView) v.findViewById(R.id.texto_email);
        icono_perfil = (ImageView) v.findViewById(R.id.icono_perfil);
        icono_fecha = (ImageView) v.findViewById(R.id.icono_indicador_derecho);

        texto_email.setText(email.toString());
        if(nombre != null){
            texto_nombre.setText(nombre.toString());
        }
        if(foto != null){
            Glide.with(this).load(foto.toString()).into(icono_perfil);
        }else{
            icono_perfil.setImageResource(R.drawable.ic_account);

        }


        icono_fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mensaje("Estamos trabajando en ello ");
                DemeTexto(v.findViewById(R.id.icono_indicador_derecho));

            }
        });
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Mi cuenta");
    }

    static EditText texto;
    public void DemeTexto(View view){
// Uso: DemeTexto(findViewById(R.id.btnNombreBoton))
        texto = new EditText(view.getContext());
        AlertDialog.Builder builder1 = new
                AlertDialog.Builder(view.getContext());
        builder1.setMessage("Digite su nueva contraseña");
        texto.setText("Dato");
        texto.selectAll();
        builder1.setView(texto);
        builder1.setCancelable(true);
        builder1.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        String newPassword = texto.getText().toString();
                        user.updatePassword(newPassword)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Mensaje("Tu contraseña fue actualizada");
                                        }else{
                                            Mensaje("Hemos topada con un error, intentalo más tarde :) ");
                                        }
                                    }
                                });
                    }
                });
}

    public  void Mensaje(String msg ){
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    };

}
