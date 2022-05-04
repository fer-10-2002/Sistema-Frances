package com.example.sistemafrances;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class Tabla extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla);
        TextView P2 = findViewById(R.id.P2);
        TextView N2 = findViewById(R.id.N2);
        TextView Tem2 = findViewById(R.id.Tem2);
        TableLayout table = findViewById(R.id.table2);
        P2.setText(this.getIntent().getStringExtra("P"));
        N2.setText(this.getIntent().getStringExtra("N"));

        double Tem;
        if (this.getIntent().getBooleanExtra("sw", false)) {
            Tem2.setText(this.getIntent().getStringExtra("Tem") + "%");
            Tem = Double.valueOf(Tem2.getText().toString().replace("%", "")) / 100;
        } else {
            Tem2.setText(this.getIntent().getStringExtra("Tem"));
            Tem = Double.valueOf(Tem2.getText().toString());
        }
        double P = Double.valueOf(P2.getText().toString());
        double N = Double.valueOf(N2.getText().toString());
        TableRow row = new TableRow(this);
        DecimalFormat df = new DecimalFormat("###.###");
        if (this.getIntent().getStringExtra("metodo").equals("Sistema Frances")) {
            double S = P;
            double I = 0;

            Double A = (P * Tem) / (1 - Math.pow(1 + Tem, -N));
            double Amo = 0;
            for (int u = 0; u <= N; u++) {
                for (int i = 0; i < 5; i++) {
                    TextView textView = new TextView(this);
                    if (u == 0) {
                        if (i == 0) {
                            textView.setText("0");
                        }
                        if (i == 4) {
                            textView.setText(df.format(P));
                        }
                    } else {
                        if (i == 0) {
                            textView.setText(df.format(u));
                        } else if (i == 1) {
                            textView.setText(df.format(A));
                        } else if (i == 2) {
                            I = Tem * S;
                            textView.setText(df.format(I));
                        } else if (i == 3) {
                            Amo = A - I;
                            textView.setText(df.format(Amo));
                        } else if (i == 4) {
                            S = S - Amo;
                            textView.setText(df.format(S));
                        }
                    }
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) findViewById(R.id.amortizacion).getLayoutParams();
                    textView.setLayoutParams(params);
                    textView.setGravity(Gravity.CENTER);
                    textView.setTextColor(Color.WHITE);
                    textView.setBackgroundResource(R.drawable.tabla);
                    row.addView(textView);
                }
                table.addView(row, -1);
                row = new TableRow(this);
            }

        }
        else if (this.getIntent().getStringExtra("metodo").equals("Sistema Ingles")) {
            TextView renta = findViewById(R.id.renta);
            renta.setText("Interes");
            TextView interes = findViewById(R.id.interes);
            interes.setText("Mensualidad");
            double I = Tem*P;

            double Amo = 0;
            for (int u = 0; u <= N; u++) {
                for (int i = 0; i < 5; i++) {
                    TextView textView = new TextView(this);
                    if (u == 0) {
                        if (i == 0) {
                            textView.setText("0");
                        }
                        if (i == 4) {
                            textView.setText(df.format(P));
                        }
                    } else {
                        if (i == 0) {
                            textView.setText(df.format(u));
                        } else if (i == 1) {
                            textView.setText(df.format(I));
                        } else if (i == 2) {
                            if(u==N) {
                                textView.setText(df.format(I+P));
                            }else{
                                textView.setText(df.format(I));
                            }
                        } else if (i == 3) {
                            if(u==N) {
                                textView.setText(df.format(P));
                            }else{
                                textView.setText(df.format(0));
                            }
                        } else if (i == 4) {
                            if(u!=N) {
                                textView.setText(df.format(P));
                            }else{
                                textView.setText(df.format(0));
                            }
                        }
                    }
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) findViewById(R.id.amortizacion).getLayoutParams();
                    textView.setLayoutParams(params);
                    textView.setGravity(Gravity.CENTER);
                    textView.setTextColor(Color.WHITE);
                    textView.setBackgroundResource(R.drawable.tabla);
                    row.addView(textView);
                }
                table.addView(row, -1);
                row = new TableRow(this);
            }
        }
        else if (this.getIntent().getStringExtra("metodo").equals("Sistema Aleman")){
            TextView renta = findViewById(R.id.renta);
            renta.setText("Mensualidad Renta");
            TextView interes = findViewById(R.id.interes);
            interes.setText("Interes");
            double S = P;
            double I = 0;
            double amo = P/N;
            double mr=0;
            double I2 = 0;

            for (int u = 0; u <= N; u++) {
                for (int i = 0; i < 5; i++) {
                    TextView textView = new TextView(this);
                    if (u == 0) {
                        if (i == 0) {
                            textView.setText("0");
                        }
                        if (i == 4) {
                            textView.setText(df.format(P));
                        }
                    } else {
                        if (i == 0) {
                            textView.setText(df.format(u));
                        } else if (i == 1) {
                            I2 = Tem*S;
                            textView.setText(df.format(I2+amo));
                        } else if (i == 2) {
                            I = Tem*S;
                            textView.setText(df.format(I));
                        } else if (i == 3) {
                            textView.setText(df.format(amo));
                        } else if (i == 4) {
                            S = S - amo;
                            textView.setText(df.format(S));
                        }
                    }
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) findViewById(R.id.amortizacion).getLayoutParams();
                    textView.setLayoutParams(params);
                    textView.setGravity(Gravity.CENTER);
                    textView.setTextColor(Color.WHITE);
                    textView.setBackgroundResource(R.drawable.tabla);
                    row.addView(textView);
                }
                table.addView(row, -1);
                row = new TableRow(this);
            }

        }
        else if(this.getIntent().getStringExtra("metodo").equals("Flat")){
            TextView renta = findViewById(R.id.renta);
            renta.setText("Interes");
            TextView interes = findViewById(R.id.interes);
            interes.setText("Mensualidad");
            double I = Tem*P;
            double amo = P/N;
            double S=P;
            for (int u = 0; u <= N; u++) {
                for (int i = 0; i < 5; i++) {
                    TextView textView = new TextView(this);
                    if (u == 0) {
                        if (i == 0) {
                            textView.setText("0");
                        }
                        if (i == 4) {
                            textView.setText(df.format(P));
                        }
                    } else {
                        if (i == 0) {
                            textView.setText(df.format(u));
                        } else if (i == 1) {
                            textView.setText(df.format(I));
                        } else if (i == 2) {
                            textView.setText(df.format(I+amo));
                        } else if (i == 3) {
                            textView.setText(df.format(amo));
                        } else if (i == 4) {
                            S = S-amo;
                            textView.setText(df.format(S));
                        }
                    }
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) findViewById(R.id.amortizacion).getLayoutParams();
                    textView.setLayoutParams(params);
                    textView.setGravity(Gravity.CENTER);
                    textView.setTextColor(Color.WHITE);
                    textView.setBackgroundResource(R.drawable.tabla);
                    row.addView(textView);
                }
                table.addView(row, -1);
                row = new TableRow(this);
            }
        }
    }
}