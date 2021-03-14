package com.example.vuelos.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.vuelos.R;
import com.example.vuelos.utils.Ciudad;

import java.util.ArrayList;

public class AdaptadorSpinner extends BaseAdapter {

    ArrayList<Ciudad> listaCiudad;
    Context context;
    RequestOptions option;
    public AdaptadorSpinner(ArrayList<Ciudad> listaCiudad, Context context) {
        this.listaCiudad = listaCiudad;
        this.context = context;
        option = new RequestOptions().centerCrop().placeholder(R.drawable.correcta).error(R.drawable.error);

    }

    @Override
    public int getCount() {
        return listaCiudad.size();
    }

    @Override
    public Object getItem(int position) {
        return listaCiudad.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.vista_spinner_item,parent,false);
        Ciudad ciudad = listaCiudad.get(position);
        TextView nombre;
        ImageView imagen;

        nombre = convertView.findViewById(R.id.texto_spinner);
        imagen = convertView.findViewById(R.id.imagen_spinner);

        nombre.setText(ciudad.getNombre());
        //imagen.setImageResource(Integer.parseInt(ciudad.getImagen()));
        Glide.with(context).load(ciudad.getImagen()).apply(option.circleCropTransform()).into(imagen);
        return convertView;
    }

}
