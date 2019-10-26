package com.example.practica2_1;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;


public class MainActivity extends AppCompatActivity implements Asynchtask {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Map<String, String> datos = new HashMap<String, String>();
        WebService ws = new WebService("https://api.androidhive.info/contacts/", datos, MainActivity.this, MainActivity.this);
        ws.execute("");


    }

    @Override
    public void processFinish(String result) throws JSONException {
        Log.i("processFinish",result);
        //String datos="";
        ArrayList<String>datos=new ArrayList<String>();
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

            String dts=name+"\n"+mobile;
            datos.add(dts);
        }
                ArrayAdapter adaptadornoticias=new ArrayAdapter(this, android.R.layout.simple_list_item_1,datos);

        ListView lstOpciones = (ListView)findViewById(R.id.lstLista);

        lstOpciones.setAdapter(adaptadornoticias);
    }

}
