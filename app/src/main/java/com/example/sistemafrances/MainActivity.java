package com.example.sistemafrances;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static String d;
    static String metodo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void Calcular(View view) {
        Button btn=(Button)view;
        metodo=btn.getText().toString();
        EditText P=findViewById(R.id.P);
        comprobar(1000,50000,P.getText().toString(),"P");

    }
    void repetir(){

        if(d=="P") {
            EditText N = findViewById(R.id.N);
            comprobar(6, 24, N.getText().toString(), "N");
        }
        else if (d == "N") {
            Switch sw = findViewById(R.id.switch1);
            EditText Tem = findViewById(R.id.Tem);
            Double temint = Double.valueOf(Tem.getText().toString().replace("%", ""));
            if (sw.isChecked()) {
                temint = temint / 100;
            }
            comprobar(0.01,0.04,temint.toString(),"Tem");
        }
        else if(d=="Tem"){
            crear();
        }


    }
    void crear(){
        EditText P=findViewById(R.id.P);
        EditText N = findViewById(R.id.N);
        Switch sw = findViewById(R.id.switch1);
        EditText Tem = findViewById(R.id.Tem);
        Intent intent = new Intent(MainActivity.this,Tabla.class);
        intent.putExtra("P",P.getText().toString());
        intent.putExtra("N",N.getText().toString());
        intent.putExtra("Tem",Tem.getText().toString());
        intent.putExtra("metodo",metodo);
        if(sw.isChecked()){
            intent.putExtra("sw",true);
        }else{
            intent.putExtra("sw",false);
        }
        startActivity(intent);
    }
    void comprobar(double inicio,double fin,String dato,String nombre){
        double pint = Double.parseDouble(dato);

        if(pint>=inicio && pint<=fin){
            d=nombre;
            repetir();
        }else{

            AlertDialog.Builder myalert=new AlertDialog.Builder(this);
            myalert.setMessage("El parametro "+nombre+" esta fuera de rango de: " +String.valueOf(inicio) +" y "+String.valueOf(fin) +" ¿desea continuar?");
            myalert.setTitle("Alerta de rango");
            myalert.setPositiveButton("Continuar de todas formas", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    d=nombre;
                    dialogInterface.cancel();
                    repetir();

                }
            });
            myalert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });

            AlertDialog dialog=myalert.create();
            dialog.show();

        }
    }
    public void porcentaje(View view) {
        Switch sw= (Switch) view;
        LinearLayout ll=findViewById(R.id.linearLayoutporcentaje);
        if(sw.isChecked()){
            TextView tv=new TextView(this);
            tv.setText("%");
            tv.setTextColor(Color.WHITE);
            ll.addView(tv);
        }
        else{
           ll.removeViewAt(2);
        }
    }
}