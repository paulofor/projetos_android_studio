package br.com.lojadigicom.capitalexterno.framework.fcm;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import br.com.lojadigicom.capitalexterno.framework.log.DCLog;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import br.com.lojadigicom.capitalexterno.data.contract.DispositivoUsuarioContract;
import br.com.lojadigicom.capitalexterno.framework.log.DCLog;
import br.com.lojadigicom.capitalexterno.framework.telefonia.Telefone;
import br.com.lojadigicom.capitalexterno.modelo.DispositivoUsuario;
import br.com.lojadigicom.capitalexterno.modelo.montador.DispositivoUsuarioMontador;
import br.com.lojadigicom.capitalexterno.remoto.DispositivoUsuarioSincronismo;
import br.com.lojadigicom.capitalexterno.remoto.UsuarioSincronismo;
import br.com.lojadigicom.capitalexterno.data.contract.AplicacaoContract;


public class DCFirebaseInstanceIDService extends FirebaseInstanceIdService {

	private static DCSincronizador sincronizador = null;

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        DCLog.d(DCLog.SINCRONIZACAO_GCM,this,"token:" + refreshedToken);
        sincronizador.sincronizaUsuarioDispositivoFcmId(this);
        processaAtualizacao(refreshedToken);
        sincronizador.sincronizaDispositivoFcmId(this);
        sincronizador.executaDiario(this,AplicacaoContract.getCodigoAplicacaoSinc());
   	}

	public static void setSincronizador(DCSincronizador valor) {
        sincronizador = valor;
    }
	
	private void processaAtualizacao(String token) {
        Uri uriDispositivo = DispositivoUsuarioContract.buildAll();
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(uriDispositivo, null, null, null, null);
        DispositivoUsuario dispositivoUsuario = null;
        if (cursor.moveToFirst()) {
            DispositivoUsuarioMontador montador = new DispositivoUsuarioMontador();
            dispositivoUsuario = (DispositivoUsuario) montador.getItem(cursor);
            dispositivoUsuario.setTokenGcm(token);
            dispositivoUsuario.setCodigoDispositivo(Telefone.getModelo(this));
            uriDispositivo = DispositivoUsuarioContract.buildDispositivoUsuarioUri(dispositivoUsuario.getIdObj());
            resolver.update(uriDispositivo, dispositivoUsuario.getContentValues(), null, null);
        }
	}
	

   
}
