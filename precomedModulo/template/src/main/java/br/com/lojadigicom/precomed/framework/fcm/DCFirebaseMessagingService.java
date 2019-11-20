package br.com.lojadigicom.precomed.framework.fcm;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import br.com.lojadigicom.precomed.framework.log.DCLog;
import br.com.lojadigicom.precomed.template.R;



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
        sendNotification();

    }

    private void sendNotification() {
        DCLog.d(DCLog.SINCRONIZACAO_GCM,this,"Criando notificacao");
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(notificacao.getIdIcone())
                        .setContentTitle(notificacao.getTitulo())
                        .setContentText(notificacao.getTexto());
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
