package com.giga.firstapplication;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

public class AlertDialog extends DialogFragment {

    // método para devolver una instancia con parámetros inyectados por medio de Bundle
    static AlertDialog nuevaInstancia(String titulo, String nombre, String email){

        // una instancia de la clase que soportará al Diálogo
        AlertDialog aDialog = new AlertDialog();

        // Bundle (diccionario llave / valor) que inyectará los parámetros a la clase
        Bundle bundle = new Bundle();
        bundle.putString("titulo", titulo);
        bundle.putString("nombre", nombre);
        bundle.putString("email", email);

        // con esto inyectamos los parámetros
        aDialog.setArguments(bundle);

        return aDialog;
    }

    // Intercepción del llamado a mostrar el fragmento
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        String titulo = getArguments().getString("titulo");
        String nombre = getArguments().getString("nombre");
        String email = getArguments().getString("email");

        Dialog dialog =
                new androidx.appcompat.app.AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.icon_android)
                .setTitle(titulo)
                .setMessage("Tus datos son: \nNombre: " + nombre + "\nEmail: " + email)
                .setPositiveButton(
                        "OK",
                        (_dialog, which) -> {
                                ((SecondActivity) getActivity()).registrar();
                            })
                .setNegativeButton(
                        "Cancelar",
                        (__dialog, which) -> {
                        ((SecondActivity) getActivity()).cancelar();
                })
                .create();


        return dialog;

    }

}
