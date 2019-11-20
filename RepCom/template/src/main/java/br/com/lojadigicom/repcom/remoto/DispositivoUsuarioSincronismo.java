package  br.com.lojadigicom.repcom.remoto;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;


import br.com.lojadigicom.repcom.data.contract.DispositivoUsuarioContract;
import br.com.lojadigicom.repcom.framework.log.DCLog;

public class DispositivoUsuarioSincronismo {
	
	// Versao tradicional sempre com atualizacao local.
    public void sincroniza(Context contexto, boolean forcaAtualizacao) {
        sincroniza(contexto,forcaAtualizacao,true,true);
    }

	// Versao nova podendo nao ter atualizacao local.
	public void sincroniza(Context contexto, boolean forcaAtualizacao, boolean delete, boolean atualizaLocal) {
	 	DispositivoUsuarioRemote servicoRemoto = new  DispositivoUsuarioRemote(); // fazer estatico ?
        try {
             ContentResolver resolver = contexto.getContentResolver();
             Cursor cursor = resolver.query(DispositivoUsuarioContract.buildAllSinc(), DispositivoUsuarioContract.COLS_SINC, null, null, null);
             int tam = cursor.getCount();
             if (cursor.getCount() > 0 ) {
                  servicoRemoto.listaSincronizadaAlteracao(cursor, contexto);
                  DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "DispositivoUsuario: " + tam +  " >> ");
                  resolver.delete(DispositivoUsuarioContract.buildDeleteAllSinc(),null,null);
             }
             if ((atualizaLocal) && (forcaAtualizacao || (tam>0))) {
                 int tamLista = servicoRemoto.listaSincronizadaDao(contexto,delete);
                 DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "DispositivoUsuario: " + tamLista +  " << ");
             }
        } catch (Exception e) {
         	 DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,e);
             e.printStackTrace();
        }
        
    }
}