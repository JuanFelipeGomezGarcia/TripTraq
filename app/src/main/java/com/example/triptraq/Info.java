package com.example.triptraq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }

    public void onClickCancelar(View view){

        Intent intent = new Intent(Info.this, Menu.class);
        startActivity(intent);

    }
    public void onClickGuardar(View view){

        Intent intent = new Intent(Info.this, Menu.class);
        startActivity(intent);

    }
}