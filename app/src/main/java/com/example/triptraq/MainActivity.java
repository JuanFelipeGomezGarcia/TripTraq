package com.example.triptraq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText pt_usuario;
    private EditText pt_contraseña;
    private Button Btn_Iniciar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pt_usuario = findViewById(R.id.pt_usuario);
        pt_contraseña = findViewById(R.id.pt_contraseña);
        Btn_Iniciar =findViewById(R.id.Btn_IniciarSesion);


    }
    public void onClickIniciar(View view){
        Intent intent = new Intent(this,Home.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}