package com.giga.firstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private static String nombre;
    private static String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void invocarConfirmacion(View v){

        EditText txtname = findViewById(R.id.txtname);
        nombre = txtname.getText().toString();

        EditText txtemail = findViewById(R.id.txtemail);
        email = txtemail.getText().toString();

        // Desplegar fragmento de diálogo
        AlertDialog dialog = AlertDialog.nuevaInstancia("CONFIRMA TUS DATOS", nombre, email);
        dialog.show(getSupportFragmentManager(), "dialogo");
    }


    public void registrar(){
        Toast.makeText(getBaseContext(), "Diste click en OK", Toast.LENGTH_SHORT).show();

        Intent i = new Intent();

        i.putExtra("name", nombre);
        i.putExtra("email", email);

        setResult(RESULT_OK, i);

        finish();

    }

    public void cancelar(){
        Toast.makeText(getBaseContext(), "Diste click en cancelar", Toast.LENGTH_SHORT).show();
    }

}
