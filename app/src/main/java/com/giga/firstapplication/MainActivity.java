package com.giga.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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

    // método para mandar a llamar a la próxima Activity (llamada explícita) [Intent]
    public void lanzar(View v){
        Intent i = new Intent(this, SecondActivity.class);
        startActivityForResult(i, requestCode);
    }

    // llamada implícita [Intent]
    // Toda llamada implícita lleva un action y una data
    // action para decir qué es lo que va a hacer y el data es para entender qué necesita para hacerlo
    public void abrirWeb(View v){
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://grupo-giga.com/"));
        startActivity(i);
    }

    public void localizar(View v){
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:19.200852181164866,-96.2357457174807"));
        startActivity(i);
    }

    public void marcar(View v){
        Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+522281231234"));
        startActivity(i);
    }

    // lamadas implícitas

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