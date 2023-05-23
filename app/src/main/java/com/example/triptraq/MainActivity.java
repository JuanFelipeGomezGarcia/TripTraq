package com.example.triptraq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText pt_usuario;
    private EditText pt_contraseña;
    private CardView Btn_Iniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pt_usuario = findViewById(R.id.pt_usuario);
        pt_contraseña = findViewById(R.id.pt_contraseña);
        Btn_Iniciar =findViewById(R.id.bt_login);


    }
    public void login(View v) {
        String usuario = pt_usuario.getText().toString().trim();
        String contrasena = pt_contraseña.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://triptrack.eastus.cloudapp.azure.com/getlogin.php" + "?usuario=" + usuario + "&contrasena=" + contrasena,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("Login exitoso")) {
                            // Login exitoso, pasar a otra actividad
                            Toast.makeText(MainActivity.this, "Login exitoso", Toast.LENGTH_SHORT).show();
                            // Aquí puedes iniciar la otra actividad
                        } else {
                            // Usuario o contraseña incorrectos
                            Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String errorMessage = "Error de conexión";

                        if (error instanceof NetworkError) {
                            errorMessage = "Error de red";
                        } else if (error instanceof ServerError) {
                            errorMessage = "Error del servidor";
                        } else if (error instanceof AuthFailureError) {
                            errorMessage = "Error de autenticación";
                        } else if (error instanceof ParseError) {
                            errorMessage = "Error de análisis";
                        } else if (error instanceof NoConnectionError) {
                            errorMessage = "Sin conexión";
                        } else if (error instanceof TimeoutError) {
                            errorMessage = "Tiempo de espera agotado";
                        }

                        // Mostrar el mensaje de error en el Toast
                        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });

        // Agregar la solicitud a la cola de solicitudes
        Volley.newRequestQueue(this).add(stringRequest);
    }
}