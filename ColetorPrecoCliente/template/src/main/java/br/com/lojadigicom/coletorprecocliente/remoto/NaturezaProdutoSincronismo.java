package  br.com.lojadigicom.coletorprecocliente.remoto;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;


import br.com.lojadigicom.coletorprecocliente.data.contract.NaturezaProdutoContract;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;

public class NaturezaProdutoSincronismo {
	
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
	 	NaturezaProdutoRemote servicoRemoto = new  NaturezaProdutoRemote(); // fazer estatico ?
        try {
             ContentResolver resolver = contexto.getContentResolver();
             Cursor cursor = resolver.query(NaturezaProdutoContract.buildAllSinc(), NaturezaProdutoContract.COLS_SINC, null, null, null);
             int tam = cursor.getCount();
             if (cursor.getCount() > 0 ) {
                  servicoRemoto.listaSincronizadaAlteracao(cursor, contexto);
                  DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "NaturezaProduto: " + tam +  " >> ");
                  resolver.delete(NaturezaProdutoContract.buildDeleteAllSinc(),null,null);
             }
             if ((atualizaLocal) && (forcaAtualizacao || (tam>0))) {
                 int tamLista = servicoRemoto.listaSincronizadaDao(contexto,delete);
                 DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "NaturezaProduto: " + tamLista +  " << ");
             }
        } catch (Exception e) {
         	 DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,e);
             e.printStackTrace();
        }
        
    }
    
    public void sincroniza(Context contexto, boolean forcaAtualizacao, boolean delete, boolean atualizaLocal, String codigoAplicacao) {
	 	NaturezaProdutoRemote servicoRemoto = new  NaturezaProdutoRemote(); // fazer estatico ?
        try {
             ContentResolver resolver = contexto.getContentResolver();
             Cursor cursor = resolver.query(NaturezaProdutoContract.buildAllSinc(), NaturezaProdutoContract.COLS_SINC, null, null, null);
             int tam = cursor.getCount();
             if (cursor.getCount() > 0 ) {
                  servicoRemoto.listaSincronizadaAlteracao(cursor, contexto);
                  DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "NaturezaProduto: " + tam +  " >> ");
                  resolver.delete(NaturezaProdutoContract.buildDeleteAllSinc(),null,null);
             }
             if ((atualizaLocal) && (forcaAtualizacao || (tam>0))) {
                 int tamLista = servicoRemoto.listaSincronizadaDao(contexto,delete,codigoAplicacao);
                 DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "NaturezaProduto: " + tamLista +  " << ");
             }
        } catch (Exception e) {
         	 DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,e);
             e.printStackTrace();
        }
        
    }
}