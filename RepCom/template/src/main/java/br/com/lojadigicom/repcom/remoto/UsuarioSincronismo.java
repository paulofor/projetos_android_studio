package  br.com.lojadigicom.repcom.remoto;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;


import br.com.lojadigicom.repcom.data.contract.UsuarioContract;
import br.com.lojadigicom.repcom.framework.log.DCLog;

public class UsuarioSincronismo {
	
	// Versao tradicional sempre com atualizacao local.
    public void sincroniza(Context contexto, boolean forcaAtualizacao, boolean delete) {
        sincroniza(contexto,forcaAtualizacao,delete,true);
    }

	// Versao nova podendo nao ter atualizacao local.
	public void sincroniza(Context contexto, boolean forcaAtualizacao, boolean delete, boolean atualizaLocal) {
	 	UsuarioRemote servicoRemoto = new  UsuarioRemote(); // fazer estatico ?
        try {
             ContentResolver resolver = contexto.getContentResolver();
             Cursor cursor = resolver.query(UsuarioContract.buildAllSinc(), UsuarioContract.COLS_SINC, null, null, null);
             int tam = cursor.getCount();
             if (cursor.getCount() > 0 ) {
                  servicoRemoto.listaSincronizadaAlteracao(cursor, contexto);
                  DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "Usuario: " + tam +  " >> ");
                  resolver.delete(UsuarioContract.buildDeleteAllSinc(),null,null);
             }
             if ((atualizaLocal) && (forcaAtualizacao || (tam>0))) {
                 int tamLista = servicoRemoto.listaSincronizadaDao(contexto,delete);
                 DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "Usuario: " + tamLista +  " << ");
             }
        } catch (Exception e) {
         	 DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,e);
             e.printStackTrace();
        }
        
    }
}