package com.example.primerlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class Actividad2 extends AppCompatActivity implements Asynchtask {
   private TextView txtSaludo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);

        txtSaludo= (TextView)findViewById(R.id.lblMensaje);
        Bundle bundle =this.getIntent().getExtras();
        //txtSaludo.setText("User"+bundle.getString("Usuario")+"Clave"+bundle.getString("Clave"));
        Map<String,String> datos = new HashMap<>();
        WebService ws= new WebService("http://revistas.uteq.edu.ec/ws/login.php?usr=cristian&pass=cristian", datos, Actividad2.this, Actividad2.this);
        ws.execute("");
    }

    @Override
    public void processFinish(String result) {

        Log.i("processFinish", result);
        txtSaludo.setText(result);
    }
}
