package clyky.ontimenotification.receivers;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import clyky.ontimenotification.R;
import clyky.ontimenotification.activities.MainActivity;

/**
 * Un broadcast receiver è come un event listener: resta in ascolto di un evento e quando viene lanciato si attiva il metodo onReceive.
 * In questo caso l'evento è lanciato dall'alarm manager settato nella main activity, ma un broadcast receiver può catturare anche un evento di sistema (es. accensione/spegnimento, sms ricevuto, ecc.)
 *
 * I BROADCAST RECEIVER DEVONO ESSERE MESSI NEL MANIFEST!!!
 * Created by Clyky on 04/07/2017.
 */

public class NotificationPublisher extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        String title = "Titolo";
        String message = "Message";
        int id = 5; // due notifiche con lo stesso id si sostituiscono

        Notification.Builder builder = new Notification.Builder(context);
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        builder.setContentTitle(title);
        builder.setContentText(message);
        builder.setSmallIcon(R.mipmap.ic_launcher); // setta l'icona della notifica
        builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS); // la notifica avrà il suono, la vibrazione e accenderà il led

        // per far aprire la tua app cliccando sulla notifica:

        Intent openApp = new Intent(context, MainActivity.class);
        openApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP); // necessarie per creare un nuovo processo dell'applicazione anzichè aprirla nello stato corrente

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, openApp, 0);
        builder.setContentIntent(pendingIntent);

        manager.notify(id, builder.build()); // invia la notifica al sistema
    }
}
