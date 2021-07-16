package com.giga.firstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Notification_Giga extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giga_notification);

        // esperar parámetros
        if(getIntent().getExtras() != null){
            Log.d("giga_talks", "testing");
            Toast.makeText(
                    getBaseContext(),
                    "Parámetros: " + getIntent().getStringExtra("saludar"),
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void lanzar_main(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}