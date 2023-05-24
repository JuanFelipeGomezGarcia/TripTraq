package com.example.triptraq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Map;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);





    }

    public void onClickInicio(View view){

        Intent intent = new Intent(Menu.this, Info.class);
        startActivity(intent);

    }

    public void onClickMapa(View view){

        Intent intent = new Intent(Menu.this, GPS.class);
        startActivity(intent);

    }
    public void onClickBuscar(View view){

        Intent intent = new Intent(Menu.this, Buscar.class);
        startActivity(intent);

    }
    public void onClickGas(View view){

        Intent intent = new Intent(Menu.this, Combustible.class);
        startActivity(intent);

    }
    public void onClickReparacion(View view){

        Intent intent = new Intent(Menu.this, Reparacion.class);
        startActivity(intent);

    }
}