package com.example.primerlogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btEnviar(View view){
        TextView txtNombre;
       txtNombre = (TextView)findViewById(R.id.lblUsuario);
        String Usuario= txtNombre.getText().toString();

         TextView txtClave;
        txtClave = (TextView)findViewById(R.id.lblClave);
         String Clave= txtClave.getText().toString();
        //Toast.makeText(this.getApplicationContext(), Usuario+""+Clave,Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this,Actividad3.class);
        Bundle b=new Bundle();
        b.putString("Usuario",Usuario); b.putString("Clave",Clave);
        intent.putExtras(b);
        startActivity(intent);
    }
}
