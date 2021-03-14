package com.example.vuelos.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.vuelos.R;
import com.example.vuelos.utils.Ciudad;
import com.example.vuelos.utils.Vuelos;

import java.util.ArrayList;

public class RecyclerVuelos extends RecyclerView.Adapter<RecyclerVuelos.miHolder> {

    ArrayList<Vuelos> listaVuelos, lista;
    Context context;
    OnSelectedViajeDetalle listener;
    RequestOptions option;


    public RecyclerVuelos(ArrayList<Vuelos> listaVuelos, Context context) {
        this.listaVuelos = listaVuelos;
        this.context = context;
        listener = (OnSelectedViajeDetalle) context;
        option = new RequestOptions().centerCrop().placeholder(R.drawable.correcta).error(R.drawable.error);


    }

    @NonNull
    @Override
    public RecyclerVuelos.miHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.vista_recycler_vuelo,parent,false);
        miHolder miHolder = new miHolder(view);
        return miHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerVuelos.miHolder holder, int position) {

        //holder.imagen.setImageResource(ciudad.getImagen());
       // holder.texto.setText(vuelos.getNombre());


        final Vuelos vuelos = listaVuelos.get(position);

        if(vuelos.getRegreso().length()==0) {
            holder.salida.setText(vuelos.getSalida());
            holder.regreso.setText(vuelos.getRegreso());
            Glide.with(context).load(vuelos.getImagen()).apply(option).into(holder.imagenIda);
            holder.boton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    listener.OnSelectedDetalle(vuelos);

                }

            });
        }else{
            holder.salida.setText(vuelos.getSalida());
            holder.regreso.setText(vuelos.getRegreso());
            Glide.with(context).load(vuelos.getImagen()).apply(option).into(holder.imagen);
            Glide.with(context).load(vuelos.getImagen2()).apply(option).into(holder.imagen2);
            holder.boton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    listener.OnSelectedDetalle(vuelos);

                }

            });
        }


    }


    public void agregarNueva(Vuelos vuelos){
        listaVuelos.add(vuelos);
        notifyDataSetChanged();
    }

    public interface OnSelectedViajeDetalle{
        public void OnSelectedDetalle(Vuelos vuelos);
    }

    @Override
    public int getItemCount() {

        return listaVuelos.size();
        //lista = new ArrayList(listaVuelos);
       // for (Vuelos item : lista) {
            /*if ("".equals("")) {
                return listaVuelos.size();

            }else{
                return 0;

            }*/
        //}
        /*
        if(lista.get().equals(2022)){
            return 0;
        }else {
            return listaVuelos.size();
        }*/
    }

    public class miHolder extends RecyclerView.ViewHolder {
        ImageView imagen,imagen2,imagenIda;
        TextView texto,regreso,salida;
        Button boton;
        public miHolder(@NonNull View itemView) {
            super(itemView);
            imagenIda= itemView.findViewById(R.id.imagen_idaa);
            imagen = itemView.findViewById(R.id.imagen_salida);
            imagen2 = itemView.findViewById(R.id.imagen_regreso);
            boton=itemView.findViewById(R.id.btn_detalle);
            salida = itemView.findViewById(R.id.salida_texto_recycler);
            regreso = itemView.findViewById(R.id.regreso_texto_recycler);

           // texto = itemView.findViewById(R.id.texto_rc);
        }

        public ImageView getImagenIda() {
            return imagenIda;
        }

        public void setImagenIda(ImageView imagenIda) {
            this.imagenIda = imagenIda;
        }

        public ImageView getImagen() {
            return imagen;
        }

        public void setImagen(ImageView imagen) {
            this.imagen = imagen;
        }

        public ImageView getImagen2() {
            return imagen2;
        }

        public void setImagen2(ImageView imagen2) {
            this.imagen2 = imagen2;
        }

        public TextView getTexto() {
            return texto;
        }

        public void setTexto(TextView texto) {
            this.texto = texto;
        }

        public TextView getRegreso() {
            return regreso;
        }

        public void setRegreso(TextView regreso) {
            this.regreso = regreso;
        }

        public TextView getSalida() {
            return salida;
        }

        public void setSalida(TextView salida) {
            this.salida = salida;
        }

        public Button getBoton() {
            return boton;
        }

        public void setBoton(Button boton) {
            this.boton = boton;
        }
    }

}
