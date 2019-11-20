package br.com.lojadigicom.precomed.framework.fcm;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import br.com.lojadigicom.precomed.framework.log.DCLog;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import br.com.lojadigicom.precomed.data.contract.DispositivoUsuarioContract;
import br.com.lojadigicom.precomed.framework.log.DCLog;
import br.com.lojadigicom.precomed.framework.telefonia.Telefone;
import br.com.lojadigicom.precomed.modelo.DispositivoUsuario;
import br.com.lojadigicom.precomed.modelo.montador.DispositivoUsuarioMontador;
import br.com.lojadigicom.precomed.remoto.DispositivoUsuarioSincronismo;
import br.com.lojadigicom.precomed.remoto.UsuarioSincronismo;
import br.com.lojadigicom.precomed.data.contract.AplicacaoContract;


public class DCFirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        DCLog.d(DCLog.SINCRONIZACAO_GCM,this,"token:" + refreshedToken);
        sincronizaUsuarioDispositivo();
        processaAtualizacao(refreshedToken);
        sincronizaDispositivo();
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
	

    private void sincronizaDispositivo() {
        DispositivoUsuarioSincronismo dispositivoUsuarioSincronismo = new DispositivoUsuarioSincronismo();
        dispositivoUsuarioSincronismo.sincroniza(this, true);
    }
	private void sincronizaUsuarioDispositivo() {
        // Primeiro atualizar dispositivo para criar automaticamente o usuario.
        DispositivoUsuarioSincronismo dispositivoUsuario = new DispositivoUsuarioSincronismo();
        dispositivoUsuario.sincroniza(this,true, AplicacaoContract.getCodigoAplicacaoSinc());
        UsuarioSincronismo usuarioSincronismo = new UsuarioSincronismo();
        usuarioSincronismo.sincroniza(this,true, false, AplicacaoContract.getCodigoAplicacaoSinc());
    }
}
