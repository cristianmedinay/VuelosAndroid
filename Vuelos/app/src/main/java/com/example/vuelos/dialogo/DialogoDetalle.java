package com.example.vuelos.dialogo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.vuelos.R;
import com.example.vuelos.utils.Vuelos;

public class DialogoDetalle extends DialogFragment {


    TextView salida,entrada,hSalida,hEntrada,fSalida,fEntrada;
    Vuelos vueloRecuperado;

    public static DialogoDetalle newInstance(Vuelos vuelos) {
        Bundle args = new Bundle();
        args.putSerializable("key",vuelos);
        DialogoDetalle fragment = new DialogoDetalle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (this.getArguments()!=null) {
            vueloRecuperado = (Vuelos) this.getArguments().getSerializable("key");

        }else{
        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_detalle_dialogo,null);
        salida = view.findViewById(R.id.texto_ida_detalle);
        entrada = view.findViewById(R.id.texto_regreso_detalle);
        hSalida = view.findViewById(R.id.texto_hora_salida_detalle);
        hEntrada = view.findViewById(R.id.texto_hora_regreso_detalle);
        fSalida = view.findViewById(R.id.texto_fecha_salida_detalle);
        fEntrada = view.findViewById(R.id.texto_fecha_regreso_detalle);
        salida.setText(vueloRecuperado.getSalida());
        entrada.setText(vueloRecuperado.getRegreso());
        hSalida.setText(vueloRecuperado.getHsalida());
        hEntrada.setText(vueloRecuperado.getHregreso());
        fSalida.setText(vueloRecuperado.getFsalida());
        fEntrada.setText(vueloRecuperado.getFregreso());
        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
}
