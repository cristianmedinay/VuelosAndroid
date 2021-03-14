package com.example.vuelos.dialogo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.vuelos.R;
import com.example.vuelos.utils.Vuelos;

import java.util.ArrayList;

public class DialogoBuscar extends DialogFragment {
    OndialogoItemListener listener;
    Button btn,btn2;
    EditText editText,ida,regreso;
    ArrayList<Vuelos> listaVuelos;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (this.getArguments()!=null){
        }
        listaVuelos = new ArrayList();
        try {
            listener = (OndialogoItemListener) context;

        }catch (ClassCastException e){
            Log.v("Vacio","Vacio");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());

        View view = LayoutInflater.from(getContext()).inflate(R.layout.vista_dialogo_buscador,null);
        editText = view.findViewById(R.id.ciudad_dialogo);
        ida = view.findViewById(R.id.ciudad_ida_dialogo);
        regreso = view.findViewById(R.id.ciudad_regreso_dialogo);
        btn = view.findViewById(R.id.btn_buscar_ciudad);
        btn2 = view.findViewById(R.id.btn_buscar_dos);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OndialogoIdaRegresoSelected(ida.getText().toString(),regreso.getText().toString());

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (editText.getText().toString().isEmpty()) {
                        Toast.makeText(getContext(),"Texto Vacio",Toast.LENGTH_SHORT).show();
                    }else{
                        listener.OndialogoSelected(editText.getText().toString());
                        dismiss();
                    }

            }
        });

        alertDialog.setView(view);
        return alertDialog.create();

    }



    public interface OndialogoItemListener{
        void OndialogoSelected(String texto);
        void OndialogoIdaRegresoSelected(String txt1,String txt2);
    }

}
