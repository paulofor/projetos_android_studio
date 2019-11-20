package  br.com.lojadigicom.repcom.remoto;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;


import br.com.lojadigicom.repcom.data.contract.ClienteInteresseCategoriaContract;
import br.com.lojadigicom.repcom.framework.log.DCLog;

public class ClienteInteresseCategoriaSincronismo {
	
	// Versao tradicional sempre com atualizacao local.
    public void sincroniza(Context contexto, boolean forcaAtualizacao, boolean delete) {
        sincroniza(contexto,forcaAtualizacao,delete,true);
    }

	// Versao nova podendo nao ter atualizacao local.
	public void sincroniza(Context contexto, boolean forcaAtualizacao, boolean delete, boolean atualizaLocal) {
	 	ClienteInteresseCategoriaRemote servicoRemoto = new  ClienteInteresseCategoriaRemote(); // fazer estatico ?
        try {
             ContentResolver resolver = contexto.getContentResolver();
             Cursor cursor = resolver.query(ClienteInteresseCategoriaContract.buildAllSinc(), ClienteInteresseCategoriaContract.COLS_SINC, null, null, null);
             int tam = cursor.getCount();
             if (cursor.getCount() > 0 ) {
                  servicoRemoto.listaSincronizadaAlteracao(cursor, contexto);
                  DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "ClienteInteresseCategoria: " + tam +  " >> ");
                  resolver.delete(ClienteInteresseCategoriaContract.buildDeleteAllSinc(),null,null);
             }
             if ((atualizaLocal) && (forcaAtualizacao || (tam>0))) {
                 int tamLista = servicoRemoto.listaSincronizadaDao(contexto,delete);
                 DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "ClienteInteresseCategoria: " + tamLista +  " << ");
             }
        } catch (Exception e) {
         	 DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,e);
             e.printStackTrace();
        }
        
    }
}