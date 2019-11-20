package  br.com.lojadigicom.precomed.remoto;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;


import br.com.lojadigicom.precomed.data.contract.MarcaContract;
import br.com.lojadigicom.precomed.framework.log.DCLog;

public class MarcaSincronismo {
	
	private static String codigoAplicacao = null;
	
	// Versao tradicional sempre com atualizacao local.
    public void sincroniza(Context contexto, boolean forcaAtualizacao, boolean delete, String codigoAplicacao) {
        sincroniza(contexto,forcaAtualizacao,delete,true,codigoAplicacao);
    }
    public void sincroniza(Context contexto, boolean forcaAtualizacao, boolean delete) {
        sincroniza(contexto,forcaAtualizacao,delete,true);
    }

	// Versao nova podendo nao ter atualizacao local.
	public void sincroniza(Context contexto, boolean forcaAtualizacao, boolean delete, boolean atualizaLocal) {
	 	MarcaRemote servicoRemoto = new  MarcaRemote(); // fazer estatico ?
        try {
             ContentResolver resolver = contexto.getContentResolver();
             Cursor cursor = resolver.query(MarcaContract.buildAllSinc(), MarcaContract.COLS_SINC, null, null, null);
             int tam = cursor.getCount();
             if (cursor.getCount() > 0 ) {
                  servicoRemoto.listaSincronizadaAlteracao(cursor, contexto);
                  DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "Marca: " + tam +  " >> ");
                  resolver.delete(MarcaContract.buildDeleteAllSinc(),null,null);
             }
             if ((atualizaLocal) && (forcaAtualizacao || (tam>0))) {
                 int tamLista = servicoRemoto.listaSincronizadaDao(contexto,delete);
                 DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "Marca: " + tamLista +  " << ");
             }
        } catch (Exception e) {
         	 DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,e);
             e.printStackTrace();
        }
        
    }
    
    public void sincroniza(Context contexto, boolean forcaAtualizacao, boolean delete, boolean atualizaLocal, String codigoAplicacao) {
	 	MarcaRemote servicoRemoto = new  MarcaRemote(); // fazer estatico ?
        try {
             ContentResolver resolver = contexto.getContentResolver();
             Cursor cursor = resolver.query(MarcaContract.buildAllSinc(), MarcaContract.COLS_SINC, null, null, null);
             int tam = cursor.getCount();
             if (cursor.getCount() > 0 ) {
                  servicoRemoto.listaSincronizadaAlteracao(cursor, contexto);
                  DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "Marca: " + tam +  " >> ");
                  resolver.delete(MarcaContract.buildDeleteAllSinc(),null,null);
             }
             if ((atualizaLocal) && (forcaAtualizacao || (tam>0))) {
                 int tamLista = servicoRemoto.listaSincronizadaDao(contexto,delete,codigoAplicacao);
                 DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "Marca: " + tamLista +  " << ");
             }
        } catch (Exception e) {
         	 DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,e);
             e.printStackTrace();
        }
        
    }
}