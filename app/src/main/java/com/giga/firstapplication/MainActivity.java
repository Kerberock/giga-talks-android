package com.giga.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final int requestCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // método para mandar a llamar a la próxima Activity
    public void lanzar(View v){
        Intent i = new Intent(this, SecondActivity.class);
        startActivityForResult(i, requestCode);
    }

    // esto nos va a cachar la respuesta de la segunda actividad
    @Override
    public void onActivityResult(int request_code, int result_code, Intent data){
        super.onActivityResult(request_code, result_code, data);

        Log.d("giga_talks", "Request_code => " + request_code + " - result code => " + result_code);

        if(request_code == requestCode){
            if(result_code == RESULT_OK){

                String name = data.getStringExtra("name");
                String email = data.getStringExtra("email");

                Toast.makeText(
                        getBaseContext(),
                        "Ya regresó la activity. Nombre: " + name + ", email: " + email,
                        Toast.LENGTH_SHORT).show();
            }
        }

    }

}