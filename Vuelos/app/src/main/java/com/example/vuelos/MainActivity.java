package com.example.vuelos;

import androidx.annotation.FontRes;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.vuelos.adaptador.AdaptadorSpinner;
import com.example.vuelos.adaptador.RecyclerVuelos;
import com.example.vuelos.dialogo.DialogoBuscar;
import com.example.vuelos.dialogo.DialogoDetalle;
import com.example.vuelos.dialogo.DialogoSHora;
import com.example.vuelos.dialogo.DialogoSalidaF;
import com.example.vuelos.utils.Ciudad;
import com.example.vuelos.utils.Vuelos;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements
        RecyclerVuelos.OnSelectedViajeDetalle,
        TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener,
        View.OnClickListener,
        DialogoBuscar.OndialogoItemListener{
    Toolbar toolbar;
    Spinner spinner,spinnerRegreso;
    RecyclerView recyclerView;
    RecyclerVuelos adaptadorRecycler;
    ArrayList<Ciudad> listaCiudades;
    ArrayList<Vuelos> listaRecycler;
    ArrayList<Vuelos> listaNueva,listaFiltrada;
    AdaptadorSpinner adaptadorSpinner;
    TextView texto_spinner;
    CheckBox checkIda;
    Button btnAgregar;
    String horasalida;
    TextView fechaSalida,horaSalida,fechaRegreso,horaRegreso;
    DialogoBuscar dialogoBuscar;
    Vuelos vuelos;
    int ciudad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instancias();
        acciones();
        listaRecyclers();
        spinner();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_buscador,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.buscador:
                //dialogoBuscar = DialogoBuscar.newInstance(vuelo);
                //dialogoBuscar = (DialogoBuscar) getSupportFragmentManager().findFragmentByTag("buscar");
                    dialogoBuscar = new DialogoBuscar();
                    dialogoBuscar.show(getSupportFragmentManager(), "key3");
                break;
        }
        return true;
    }

    private void listaRecyclers() {
        recyclerView.setAdapter(adaptadorRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
        adaptadorRecycler.notifyDataSetChanged();

    }

    private void spinner() {
        spinner.setAdapter(adaptadorSpinner);
        spinnerRegreso.setAdapter(adaptadorSpinner);
        listaCiudades.add(new Ciudad("New York","https://sergimateo.com/wp-content/2012/12/Nueva-York-3.jpg"));
        listaCiudades.add(new Ciudad("San Francisco", "https://static9lonelyplanetes.cdnstatics.com/sites/default/files/styles/max_1300x1300/public/fotos/eeuu_sanfrancisco_paintedladies_alamosquare_shutterstockrf_568363738_canadastock_shutterstock.jpg?itok=fxStvcj9"));
        listaCiudades.add(new Ciudad("Los Angeles", "https://static9lonelyplanetes.cdnstatics.com/sites/default/files/styles/max_1300x1300/public/fotos/EEUU_California_LosAngeles_shutterstock_186048416_View%20Apart_Shutterstock_0.jpg?itok=DMvbfchS"));
        listaCiudades.add(new Ciudad("Roma", "https://www.101viajes.com/sites/default/files/styles/guia-full/public/noche-roma-vaticano.jpg"));
        listaCiudades.add(new Ciudad("Florencia", "https://www.ouyeah.travel/blog/wp-content/uploads/2019/05/Florencia.jpg"));
        listaCiudades.add(new Ciudad("Barcelona", "https://www.metropoliabierta.com/uploads/s1/62/75/35/bcn_5_570x340.jpeg"));
        listaCiudades.add(new Ciudad("Madrid", "https://media.timeout.com/images/103793769/630/472/image.jpg"));
        adaptadorSpinner.notifyDataSetChanged();
    }

    private void acciones() {
        checkIda.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                //if (buttonView.isChecked()){
                    //fechaRegreso.setEnabled(!isChecked);
                    //horaRegreso.setEnabled(!isChecked);
                    //fechaRegreso.setHint("");
                    //horaRegreso.setHint("");
                //}
                if(isChecked){
                    fechaRegreso.setEnabled(false);
                    horaRegreso.setEnabled(false);
                    fechaRegreso.setHint("");
                    horaRegreso.setHint("");
                }else{
                    fechaRegreso.setEnabled(true);
                    horaRegreso.setEnabled(true);
                    fechaRegreso.setHint("regreso");
                    horaRegreso.setHint("Hora salida");
                }
            }
        }

        );
        fechaSalida.setOnClickListener(this);
        horaSalida.setOnClickListener(this);
        fechaRegreso.setOnClickListener(this);
        horaRegreso.setOnClickListener(this);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinnerRegreso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkIda.isChecked()) {
                    if (!horaSalida.getText().toString().isEmpty() && !fechaSalida.getText().toString().isEmpty()) {
                        Ciudad objeto = listaCiudades.get(spinner.getSelectedItemPosition());
                        String nombre = objeto.getNombre();
                        String img = objeto.getImagen();
                        horasalida = String.valueOf(horaSalida.getText());
                        String fechasalida = String.valueOf(fechaSalida.getText());
                        Vuelos vuelos = new Vuelos(nombre, img, fechasalida, horasalida, "", "", "", "");
                        //dialogoBuscar.addAgregar(vuelos);
                        listaNueva.add(vuelos);
                        adaptadorRecycler.notifyDataSetChanged();

                    }else{
                        Toast.makeText(getApplicationContext(), "esta vacio un campo", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    if (!horaSalida.getText().toString().isEmpty() && !fechaSalida.getText().toString().isEmpty()
                            && !horaRegreso.getText().toString().isEmpty() && !fechaRegreso.getText().toString().isEmpty()) {
                        Ciudad objeto = listaCiudades.get(spinner.getSelectedItemPosition());
                        Ciudad objeto2 = listaCiudades.get(spinnerRegreso.getSelectedItemPosition());
                        String nombre = objeto.getNombre();
                        String img = objeto.getImagen();
                        horasalida = String.valueOf(horaSalida.getText());
                        String fechasalida = String.valueOf(fechaSalida.getText());
                        String nombre2 = objeto2.getNombre();
                        String img2 = objeto2.getImagen();
                        String horaregreso = String.valueOf(horaRegreso.getText());
                        String fecharegreso = String.valueOf(fechaRegreso.getText());
                        Vuelos vuelos = new Vuelos(nombre, img, fechasalida, horasalida, nombre2, img2, fecharegreso, horaregreso);
                        listaNueva.add(vuelos);
                        adaptadorRecycler.notifyDataSetChanged();

                    } else {
                        Toast.makeText(getApplicationContext(), "esta vacio un campo", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void instancias() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        texto_spinner =findViewById(R.id.texto_spinner);
        horaRegreso = findViewById(R.id.editex_dia_regreso);
        horaSalida = findViewById(R.id.editex_dia);
        fechaSalida = findViewById(R.id.editex_fecha);
        fechaRegreso = findViewById(R.id.editex_fecha_regreso);
        spinnerRegreso = findViewById(R.id.spinner_regreso);
        checkIda = findViewById(R.id.check_ida);
        btnAgregar = findViewById(R.id.btn_agregar);
        recyclerView = findViewById(R.id.recycler);

        spinner = findViewById(R.id.spinner);
        listaRecycler = new ArrayList();
        listaCiudades = new ArrayList();
        listaNueva = new ArrayList();
        adaptadorRecycler = new RecyclerVuelos(listaNueva, MainActivity.this);
        adaptadorSpinner = new AdaptadorSpinner(listaCiudades,getApplicationContext());

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Toast.makeText(getApplicationContext(),String.format("%d:%d",hourOfDay,minute),Toast.LENGTH_SHORT).show();
        String hora = String.valueOf(hourOfDay);
        String minuto = String.valueOf(minute);
        DialogoSHora dialogoSHora = (DialogoSHora) getSupportFragmentManager().findFragmentByTag("hora");
        if(dialogoSHora!=null){
            horaSalida.setText(hora+"-"+minuto);
        }else{
            horaRegreso.setText(hora+"-"+minuto);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        //Toast.makeText(getApplicationContext(),String.format("%d/%d/%d/",year,month,dayOfMonth),Toast.LENGTH_SHORT).show();
        String año = String.valueOf(year);
        String mes =String.valueOf(month);
        String[] nombres = {"Ene","Feb","Mar","Abr","May","Jun","Jul","Ago","Sep","Oct","Nov","Dic"};
        String dia = String.valueOf(dayOfMonth);
        DialogoSalidaF dialogoSalidaF = (DialogoSalidaF) getSupportFragmentManager().findFragmentByTag("fecha");

        Date objDate = new Date();
        String strDateFormat = "MM";
        String strYearFormat = "y";

        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        java.text.SimpleDateFormat Año = new java.text.SimpleDateFormat(strYearFormat);

        if(dialogoSalidaF!=null) {
            if(Año.format(objDate).compareTo(String.valueOf(Integer.parseInt(año)))==0) {
                if (objSDF.format(objDate).compareTo(Integer.parseInt(mes)>=9 ? String.valueOf(Integer.parseInt(mes)+1) : 0 + String.valueOf(Integer.parseInt(mes) + 1))<=0) {
                    if (fechaRegreso.getText().toString().isEmpty()) {
                        fechaSalida.setText(dia + " " + nombres[Integer.parseInt(mes)] + " " + año);
                    } else {
                        String m = fechaRegreso.getText().toString().substring(2, fechaRegreso.getText().toString().length() - 5);
                        String m2 = fechaRegreso.getText().toString().substring(3, fechaRegreso.getText().toString().length() - 5);

                        int d1 = Integer.parseInt(fechaRegreso.getText().toString().substring(0, fechaRegreso.getText().toString().length() - 9));
                        //fechaSalida.setText(dia + " " + nombres[Integer.parseInt(mes)] + " " + año);
                       for (int i = 0; i < nombres.length; i++) {
                            String n = nombres[i];
                            if (d1 <= 9) {
                                if ((m).equals(n)) {
                                    if(Año.format(objDate).compareTo(String.valueOf(Integer.parseInt(año)))==0){
                                        fechaSalida.setText(dia + " " + nombres[Integer.parseInt(mes)] + " " + año);
//--------------
                                    }else {
                                        System.out.println("parte 1 " + m.length());
                                        if (i > month) {
                                            fechaSalida.setText(dia + " " + nombres[Integer.parseInt(mes)] + " " + año);
                                        } else if (i == month && d1 != dayOfMonth) {
                                            fechaSalida.setText(dia + " " + nombres[Integer.parseInt(mes)] + " " + año);
                                        } else {
                                            Toast.makeText(getApplicationContext(), "dia anterior * ", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } else {
                                    System.out.println("error no funciona 1");
                                }
                            } else {
                                if ((m2).equals(n)) {
                                    if(Año.format(objDate).compareTo(String.valueOf(Integer.parseInt(año)))==0){
                                        fechaSalida.setText(dia + " " + nombres[Integer.parseInt(mes)] + " " + año);
                                        fechaRegreso.setText("");
//----------
                                    }else {
                                        if (i > month) {
                                            fechaSalida.setText(dia + " " + nombres[Integer.parseInt(mes)] + " " + año);
                                        } else if (i == month && d1 != dayOfMonth) {
                                            fechaSalida.setText(dia + " " + nombres[Integer.parseInt(mes)] + " " + año);
                                        } else {
                                            Toast.makeText(getApplicationContext(), "dia anterio", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } else {

                                }
                            }
                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "No se puede Añadir fecha", Toast.LENGTH_SHORT).show();
                }
            }else if(Año.format(objDate).compareTo(String.valueOf(Integer.parseInt(año)))<0){
                if (fechaRegreso.getText().toString().isEmpty()) {
                    fechaSalida.setText(dia + " " + nombres[Integer.parseInt(mes)] + " " + año);
                } else {
                    String m = fechaRegreso.getText().toString().substring(2, fechaRegreso.getText().toString().length() - 5);
                    String m2 = fechaRegreso.getText().toString().substring(3, fechaRegreso.getText().toString().length() - 5);
                    int d1 = Integer.parseInt(fechaRegreso.getText().toString().substring(0, fechaRegreso.getText().toString().length() - 9));
                    String y1 = fechaRegreso.getText().toString().substring(6, fechaRegreso.getText().toString().length() - 0);
                    String y2 = fechaRegreso.getText().toString().substring(7, fechaRegreso.getText().toString().length() - 0);
                    //if (d1 != dayOfMonth) {
                        //fechaSalida.setText(dia + " " + nombres[Integer.parseInt(mes)] + " " + año);
                    //}
                    for (int i = 0; i < nombres.length; i++) {
                        String n = nombres[i];
                        if (d1 <= 9) {
                            if ((m).equals(n)) {
                                if(y1.compareTo(String.valueOf(Integer.parseInt(año)))==0){
                                    System.out.println("parte 1 " + m.length());
                                    if (i > month) {
                                        fechaSalida.setText(dia + " " + nombres[Integer.parseInt(mes)] + " " + año);
//cambie el dia a >
                                    } else if (i == month && d1 > dayOfMonth) {
                                        fechaSalida.setText(dia + " " + nombres[Integer.parseInt(mes)] + " " + año);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "dia anterior", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } else {
                                System.out.println("error");
                            }
                        } else {
//errorrrrrrrrrr
                            if ((m2).equals(n)) {
                                if(y2.compareTo(String.valueOf(Integer.parseInt(año)))==0){
                                    if (i > month) {
                                        fechaSalida.setText(dia + " " + nombres[Integer.parseInt(mes)] + " " + año);
                                    } else if (i == month && d1 > dayOfMonth) {
                                        fechaSalida.setText(dia + " " + nombres[Integer.parseInt(mes)] + " " + año);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "fecha salida mayor a la de regreso", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } else {
                                System.out.println("error no funciona mes 2");
                            }
                        }
                    }
                }
            }else{
            }
        }else {
            if (fechaSalida.getText().toString().isEmpty()) {
                fechaRegreso.setText(dia + " " + nombres[Integer.parseInt(mes)] + " " + año);
            } else {
                if (Año.format(objDate).compareTo(String.valueOf(Integer.parseInt(año))) == 0) {
                    if (objSDF.format(objDate).compareTo(Integer.parseInt(mes) >= 9 ? String.valueOf(Integer.parseInt(mes) + 1) : 0 + String.valueOf(Integer.parseInt(mes) + 1)) <= 0) {
                        String m = fechaSalida.getText().toString().substring(2, fechaSalida.getText().toString().length() - 5);
                        String m2 = fechaSalida.getText().toString().substring(3, fechaSalida.getText().toString().length() - 5);
                        String y1 = fechaSalida.getText().toString().substring(6, fechaSalida.getText().toString().length() - 0);
                        String y2 = fechaSalida.getText().toString().substring(7, fechaSalida.getText().toString().length() - 0);
                        int d = Integer.parseInt(fechaSalida.getText().toString().substring(0, fechaSalida.getText().toString().length() - 9));
                        System.out.println("mes" + month);
                        for (int i = 0; i < nombres.length; i++) {
                            String n = nombres[i];
                            if (d <= 9) {
                                if ((m).equals(n)) {
                                    System.out.println("parte 1 " + m.length());
                                    if (y1.compareTo(String.valueOf(Integer.parseInt(año))) == 0) {
                                        if (i < month) {
                                            fechaRegreso.setText(dia + " " + nombres[Integer.parseInt(mes)] + " " + año);
                                        } else if (i == month && d < dayOfMonth) {
                                            fechaRegreso.setText(dia + " " + nombres[Integer.parseInt(mes)] + " " + año);
                                        } else {
                                            Toast.makeText(getApplicationContext(), "-- dia anterior", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } else {
                                    System.out.println("error no funciona 1");
                                }
                            } else {
                                if ((m2).equals(n)) {
//puedo poner alerta en denegar en ves de el settext
                                    if (y2.compareTo(String.valueOf(Integer.parseInt(año))) == 0) {
                                        if (i < month) {
                                            fechaRegreso.setText(dia + " " + nombres[Integer.parseInt(mes)] + " " + año);
                                        } else if (i == month && d < dayOfMonth) {
                                            fechaRegreso.setText(dia + " " + nombres[Integer.parseInt(mes)] + " " + año);

                                        } else {
                                            Toast.makeText(getApplicationContext(), "no se puede añadir", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } else {
                                    System.out.println("error no funciona mes 2");
                                }
                            }
                        }
                    }
                } else if (Año.format(objDate).compareTo(String.valueOf(Integer.parseInt(año))) < 0) {
                    String m = fechaSalida.getText().toString().substring(2, fechaSalida.getText().toString().length() - 5);
                    String m2 = fechaSalida.getText().toString().substring(3, fechaSalida.getText().toString().length() - 5);
                    int d = Integer.parseInt(fechaSalida.getText().toString().substring(0, fechaSalida.getText().toString().length() - 9));
                    String y1 = fechaSalida.getText().toString().substring(6, fechaSalida.getText().toString().length() - 0);
                    String y2 = fechaSalida.getText().toString().substring(7, fechaSalida.getText().toString().length() - 0);
                    System.out.println("mes" + month);
                    for (int i = 0; i < nombres.length; i++) {
                        String n = nombres[i];
                        if (d <= 9) {
                            if ((m).equals(n)) {
//poner si el primero esta en 2021 olvidar los meses aqui
                                if (y1.compareTo(String.valueOf(Integer.parseInt(año))) == 0) {
                                    if (i < month) {
                                        fechaRegreso.setText(dia + " " + nombres[Integer.parseInt(mes)] + " " + año);
                                    } else if (i == month && d < dayOfMonth) {
                                        fechaRegreso.setText(dia + " " + nombres[Integer.parseInt(mes)] + " " + año);
                                    } else {
//ARREGLAR
                                        fechaRegreso.setText("");
                                        Toast.makeText(getApplicationContext(), "dia anterior", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    fechaRegreso.setText(dia + " " + nombres[Integer.parseInt(mes)] + " " + año);
                                }
                            } else {
                            }
                        } else {
                            if ((m2).equals(n)) {
                                if (y2.compareTo(String.valueOf(Integer.parseInt(año))) == 0) {
                                    if (i < month) {
                                        fechaRegreso.setText(dia + " " + nombres[Integer.parseInt(mes)] + " " + año);
                                    } else if (i == month && d < dayOfMonth) {
                                        fechaRegreso.setText(dia + " " + nombres[Integer.parseInt(mes)] + " " + año);
                            /*else{
                                Toast.makeText(getApplicationContext(),"Fecha anterior al dia"+String.valueOf(d)+" y "+String.valueOf(dayOfMonth),Toast.LENGTH_SHORT).show();
                            }*/
                                    } else {
                                        Toast.makeText(getApplicationContext(), "- dia anterior " + d + " a " + dayOfMonth, Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    fechaRegreso.setText(dia + " " + nombres[Integer.parseInt(mes)] + " " + año);
                                }
                            } else {
                                System.out.println("error no funciona mes 2");
                            }
                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "No se puede Añadir fecha a la Actual", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.editex_fecha:
                DialogoSalidaF fechaSalida = new DialogoSalidaF();
                fechaSalida.show(getSupportFragmentManager(),"fecha");
            break;
            case R.id.editex_dia:
                DialogoSHora dialogoSHora = new DialogoSHora();
                dialogoSHora.show(getSupportFragmentManager(),"hora");
            break;
            case R.id.editex_fecha_regreso:
                DialogoSalidaF fechaRegreso = new DialogoSalidaF();
                fechaRegreso.show(getSupportFragmentManager(),"fechaRegreso");
                break;
            case R.id.editex_dia_regreso:
                DialogoSHora horaRegreso = new DialogoSHora();
                horaRegreso.show(getSupportFragmentManager(),"horaRegreso");
                break;

            default:
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void OnSelectedDetalle(Vuelos vuelos) {

        DialogoDetalle dialogoDetalle = DialogoDetalle.newInstance(vuelos);
        dialogoDetalle.show(getSupportFragmentManager(),"key");

    }


    @Override
    public void OndialogoSelected(String nombre) {
        //String item = String.valueOf(vuelos);
        listaFiltrada = new ArrayList(listaNueva);

        //listaNueva.clear();
        for (Vuelos item : listaFiltrada) {
            if(item.getSalida().equals(nombre)) {
                //Toast.makeText(getApplicationContext(), String.valueOf(item), Toast.LENGTH_SHORT).show();
                listaNueva.add(item);
                //adaptadorRecycler.agregarNueva(item);
            }
            adaptadorRecycler.notifyDataSetChanged();
        }



    }

    @Override
    public void OndialogoIdaRegresoSelected(String ida, String regreso) {
        listaFiltrada = new ArrayList(listaNueva);
        listaNueva.clear();
        for (Vuelos item : listaFiltrada) {
            if(item.getSalida().equals(ida) && item.getRegreso().equals(regreso) ) {
                //Toast.makeText(getApplicationContext(), String.valueOf(item), Toast.LENGTH_SHORT).show();
                listaNueva.add(item);
            }
            adaptadorRecycler.notifyDataSetChanged();
        }

    }


}
