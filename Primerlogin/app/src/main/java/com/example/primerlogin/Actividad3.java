package com.example.primerlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;


public class Actividad3 extends AppCompatActivity implements Asynchtask {
    private TextView txtContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad3);
        txtContact = (TextView)findViewById(R.id.lblcontactos);
        txtContact.setMovementMethod(new ScrollingMovementMethod());
        txtContact.setVisibility(View.INVISIBLE);
        Bundle bundle = this.getIntent().getExtras();
        txtContact.setText("Usuario: " + bundle.getString("Usuario")+"  Clave: " + bundle.getString("Clave"));

        //LLamado al WebService
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws = new WebService("https://api.androidhive.info/contacts/", datos, Actividad3.this, Actividad3.this);
         ws.execute("");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        Log.i("processFinish",result);
        //Leer el JSON
        JSONObject jsonObj = new JSONObject(result);
        ArrayList<HashMap<String, String>> contactList = new ArrayList<>();
        JSONArray contacts = jsonObj.getJSONArray("contacts");
        for (int i=0; i<contacts.length();i++){
            JSONObject obj= contacts.getJSONObject(i);
            String id =obj.getString("id");
            String name=obj.getString("name");
            String email=obj.getString("email");
            String address=obj.getString("address");
            String gender=obj.getString("gender");

            JSONObject phone= obj.getJSONObject("phone");
            String mobile= phone.getString("mobile");
            String home = phone.getString("home");
            String office=phone.getString("office");

            HashMap<String, String> contactos = new HashMap<String, String>();
            contactos.put("id",id);
            contactos.put("name",name);
            contactos.put("email",email);
            contactos.put("address",address);
            contactos.put("mobile",mobile);
            contactList.add(contactos);
        }
        //Presentacion de datos parceados
        String datos="";
        for(int j=0;j<contactList.size();j++){
            HashMap<String, String> item=contactList.get(j);
            for(Map.Entry<String, String> entry:item.entrySet()){
                datos = datos+""+entry.getKey()+": "+ entry.getValue()+ "\n";

            }
            datos=datos+"\n";
        }
        txtContact.setText(datos);
        txtContact.setVisibility(View.VISIBLE);
    }

}
