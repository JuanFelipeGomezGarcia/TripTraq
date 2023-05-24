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

public class Combustible extends AppCompatActivity {
    EditText et_galones, et_precio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combustible);
        et_galones=findViewById(R.id.et_galones);
        et_precio=findViewById(R.id.et_precio);
    }
    public void onClickCancelar(View view){

        Intent intent = new Intent(Combustible.this, Menu.class);
        startActivity(intent);

    }
    public void onClick(View v) {
        insertData();
        Intent intent =new Intent(Combustible.this, Menu.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    private void insertData() {

        final String galones = et_galones.getText().toString().trim();
        final String precio = et_precio.getText().toString().trim();


        class InsertData extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL("http://triptrack.eastus.cloudapp.azure.com/combustible.php");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);

                    // Construir la cadena de parámetros a enviar
                    String postData ="galones=" + URLEncoder.encode(galones, "UTF-8") +
                            "&precio=" + URLEncoder.encode(precio, "UTF-8");

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