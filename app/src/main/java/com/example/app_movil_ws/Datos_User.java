package com.example.app_movil_ws;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.LinkedHashMap;
import java.util.Map;

public class Datos_User extends AppCompatActivity {
    String id;
    EditText inputNombre;
    EditText inputApellidos;
    EditText inputCedul;
    EditText inputTipo ;
    EditText inputUserN;
    EditText inputCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_user);
        inputNombre = findViewById(R.id.inputNombre);
        inputApellidos = findViewById(R.id.inputApellidos4);
        inputCedul = findViewById(R.id.inputCedul4);
        inputTipo = findViewById(R.id.inputTipo4);
        inputUserN = findViewById(R.id.inputUserN4);
        inputCorreo = findViewById(R.id.inputCorreo4);
        SharedPreferences prefs = getSharedPreferences("shared_login_data",   Context.MODE_PRIVATE);
        id = prefs.getString("id", "");
        loaddata();
    }

    private void loaddata()
    {
        Map<String, String> map = new LinkedHashMap<>();
        try
        {
            map.put("sentencia", "" +
                    "SELECT  nombres, apellidos, cedula, tipo, usuario, correo\n" +
                    "\tFROM public.usuario where id_usuario = "+id);
            SOAPWork dd = new SOAPWork("http://"+IP_SERVIDOR+":"+PUERTO+"/Server_App_Movil/ws_Procesar?WSDL", map, this, (Asynchtask) this);
            dd.setMethod_name("Obtener");
            dd.execute();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error, "+e.toString(),Toast.LENGTH_LONG).show();
        }
    }
    public void update(View view)
    {
        Map<String, String> map = new LinkedHashMap<>();
        try
        {
            map.put("sentencia", "UPDATE public.usuario\n" +
                    "\tSET nombre='"+inputNombre.getText()+"'," +
                    " apellido='"+inputApellidos.getText()+"'," +
                    " direccion='"+inputDirec.getText()+"'," +
                    " celular='"+inputcelu.getText()+"', " +
                    "usuario='"+inputUserN.getText()+"', " +
                    "correo='"+inputCorreo.getText()+"'\n" +
                    "\tWHERE id_usuario="+id);
            SOAPWork dd = new SOAPWork("http://"+IP_SERVIDOR+":"+PUERTO+"/Server_App_Movil/ws_Procesar?WSDL", map, this, (Asynchtask) this);
            dd.setMethod_name("procesar");
            dd.execute();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error, "+e.toString(),Toast.LENGTH_LONG).show();
        }
    }
}