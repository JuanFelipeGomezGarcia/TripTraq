package com.example.triptraq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Info extends AppCompatActivity {
    EditText Et_origen, Et_destino, Et_flete, Et_galones, Et_precio, Et_anticipo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Et_origen=findViewById(R.id.et_origen);
        Et_destino=findViewById(R.id.et_destino);
        Et_flete=findViewById(R.id.et_flete);
        Et_galones=findViewById(R.id.et_galones);
        Et_precio=findViewById(R.id.et_precio);
        Et_anticipo=findViewById(R.id.et_anticipo);
    }

    public void onClickCancelar(View view){

        Intent intent = new Intent(Info.this, Menu.class);
        startActivity(intent);

    }
    public void onClick(View v) {
        insertData();
        Intent intent =new Intent(Info.this, Menu.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    private void insertData() {
        final String origen = Et_origen.getText().toString().trim();
        final String destino = Et_destino.getText().toString().trim();
        final String flete = Et_flete.getText().toString().trim();
        final String galones = Et_galones.getText().toString().trim();
        final String precio = Et_precio.getText().toString().trim();
        final String anticipo = Et_anticipo.getText().toString().trim();

        class InsertData extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL("http://triptrack.eastus.cloudapp.azure.com/info.php");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);

                    // Construir la cadena de parámetros a enviar
                    String postData = "origen=" + URLEncoder.encode(origen, "UTF-8") +
                            "&destino=" + URLEncoder.encode(destino, "UTF-8") +
                            "&flete=" + URLEncoder.encode(flete, "UTF-8") +
                            "&galones=" + URLEncoder.encode(galones, "UTF-8") +
                            "&precio=" + URLEncoder.encode(precio, "UTF-8") +
                            "&anticipo=" + URLEncoder.encode(anticipo, "UTF-8");

                    // Escribir los parámetros en el cuerpo de la solicitud
                    OutputStream outputStream = connection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    writer.write(postData);
                    writer.flush();
                    writer.close();
                    outputStream.close();

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String result = bufferedReader.readLine();

                    bufferedReader.close();
                    connection.disconnect();

                    return result;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(getApplicationContext(), "Datos insertados correctamente", Toast.LENGTH_SHORT).show();
            }
        }

        InsertData insertData = new InsertData();
        insertData.execute();
    }
}