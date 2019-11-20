package br.com.lojadigicom.coletorprecocliente.framework.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.template.R;



public class DCFirebaseMessagingService extends FirebaseMessagingService {

	// Para evoluir :
	// https://developer.android.com/training/notify-user/build-notification.html

	private static DCSincronizador sincronizador = null;
	private static DCNotificacaoParam notificacao = null;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        DCLog.d(DCLog.SINCRONIZACAO_GCM,this,"Recebeu mensagem servidor");

        if (remoteMessage.getData().size() > 0) {
            DCLog.d(DCLog.SINCRONIZACAO_GCM,this,"Mensagem dados: " + remoteMessage.getData());
        }

        if (remoteMessage.getNotification() != null) {
            DCLog.d(DCLog.SINCRONIZACAO_GCM,this,"Mensagem notificacao: " + remoteMessage.getNotification().getBody());
        }
        sincronizador.executaServerToMobile(this);
        if (notificacao.existeAlteracao())
	        sendNotification();

    }

    private void sendNotification() {
        Intent intent = new Intent(this, notificacao.getMainClass());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        DCLog.d(DCLog.SINCRONIZACAO_GCM,this,"Criando notificacao");
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(notificacao.getIdIcone())
                        .setContentTitle(notificacao.getTitulo())
                        .setContentText(notificacao.getTexto())
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        DCLog.d(DCLog.SINCRONIZACAO_GCM,this,"Notificacao manager");
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0 /* ID of notification */, mBuilder.build());
    }

 	public static void setSincronizador(DCSincronizador valor) {
        sincronizador = valor;
    }
    public static void setNotificador(DCNotificacaoParam valor) {
        notificacao = valor;
    }
}
