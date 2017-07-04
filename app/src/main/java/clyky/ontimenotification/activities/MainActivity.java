package clyky.ontimenotification.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import clyky.ontimenotification.R;
import clyky.ontimenotification.receivers.NotificationPublisher;

/**
 * Created by Clyky on 04/07/2017.
 */

public class MainActivity extends AppCompatActivity
{
    private EditText txtSeconds;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtSeconds = (EditText) findViewById(R.id.txtSeconds);
    }

    public void setupNotification(View v)
    {
        int seconds = Integer.parseInt(txtSeconds.getText().toString());
        int id = 1; // due pending intent con lo stesso id si sovrascrivono
        Intent notificationIntent = new Intent(this, NotificationPublisher.class); // broadcast receiver attivato dall'alarm manager
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, new Date().getTime() + (seconds * 1000), alarmIntent);

        // RTC_WAKEUP significa che sveglia il telefono
        // new Date().getTime() + (seconds * 1000) è l'istante in cui si deve attivare l'alarm manager (in millisecondi)
        // alarmIntent è ciò che deve fare l'alarm quando si attiva (in questo caso richiamare il broadcast receiver)

        Toast.makeText(this, "Prossima notifica fra: " + seconds + " secondi", Toast.LENGTH_LONG).show();
    }
}
