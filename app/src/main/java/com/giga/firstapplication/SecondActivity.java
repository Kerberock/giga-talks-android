package com.giga.firstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void registrar(View v){
        Intent i = new Intent();

        EditText txtname = findViewById(R.id.txtname);
        String name = txtname.getText().toString();

        EditText txtemail = findViewById(R.id.txtemail);
        String email = txtemail.getText().toString();

        i.putExtra("name", name);
        i.putExtra("email", email);

        setResult(RESULT_OK, i);

        finish();

    }

}
