package com.giga.firstapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final int requestCode = 1;
    static final String channelID = "giga";

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
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:19.168715896865976, -96.22714154508199?q=auto giga"));
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

    public void lanzar_notificacion(View v){
        Intent i = new Intent(this, Notification_Giga.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.putExtra("saludar", "Hola notificación");

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, 0);

        Log.d("giga_talks", "La versión de Android actual es: " + Build.VERSION.SDK_INT);

        // comparación de versión para definir si tenemos que crear canal de notificación o no
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            create_notification_channel("Giga channel", "Channel of Giga", NotificationManager.IMPORTANCE_DEFAULT);
        }

        NotificationManagerCompat manager = NotificationManagerCompat.from(this);

        Notification notification = generar_notificacion(
                "Confirmación de la activación",
                "Entra para activar tu cuenta",
                pendingIntent);

        // Detona la notificación
        manager.notify(0, notification);

    }

    // este método nos servirá para crear la notificación
    private Notification generar_notificacion(String title, String content, PendingIntent pendingIntent){
        return new NotificationCompat.Builder(this, channelID)
                .setSmallIcon(R.drawable.icon_android)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();
    }

    private void create_notification_channel(String name, String description, int importance){
        // Preparar nuestro canal de notificación
        NotificationChannel channel = new NotificationChannel(channelID, name, importance);
        channel.setDescription(description);

        // Registrar canal de notificación
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }

}