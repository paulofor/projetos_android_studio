package  br.com.lojadigicom.repcom.remoto;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import br.com.lojadigicom.repcom.data.contract.AplicacaoContract;
import br.com.lojadigicom.repcom.data.contract.PrecoProdutoContract;
import br.com.lojadigicom.repcom.framework.log.DCLog;

public class PrecoProdutoSincronismo {
	
    public void sincroniza(Context contexto, boolean forcaAtualizacao, boolean delete, boolean atualizaLocal) {
    	String codigoAplicacao = AplicacaoContract.getCodigoAplicacaoSinc();
 	 	PrecoProdutoRemote servicoRemoto = new  PrecoProdutoRemote(); // fazer estatico ?
        try {
             ContentResolver resolver = contexto.getContentResolver();
             Cursor cursor = resolver.query(PrecoProdutoContract.buildAllSinc(), PrecoProdutoContract.COLS_SINC, null, null, null);
             int tam = cursor.getCount();
             if (cursor.getCount() > 0 ) {
                  servicoRemoto.listaSincronizadaAlteracao(cursor, contexto);
                  DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "PrecoProduto: " + tam +  " >> ");
                  resolver.delete(PrecoProdutoContract.buildDeleteAllSinc(),null,null);
             }
             if ((atualizaLocal) && (forcaAtualizacao || (tam>0))) {
                 int tamLista = 0;
                 tamLista = servicoRemoto.listaSincronizadaDao(contexto,delete,codigoAplicacao);
                 DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "PrecoProduto: " + tamLista +  " << ");
             }
        } catch (Exception e) {
         	 DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,e);
             e.printStackTrace();
        }
    }
    
    // Chama outro metodo de DAO - Fazendo apenas updates. Ex: InteresseProduto
    public void sincronizaAtualizaPorId(Context contexto, boolean forcaAtualizacao, boolean delete, boolean atualizaLocal) {
    	String codigoAplicacao = AplicacaoContract.getCodigoAplicacaoSinc();
 	 	PrecoProdutoRemote servicoRemoto = new  PrecoProdutoRemote(); // fazer estatico ?
        try {
             ContentResolver resolver = contexto.getContentResolver();
             Cursor cursor = resolver.query(PrecoProdutoContract.buildAllSinc(), PrecoProdutoContract.COLS_SINC, null, null, null);
             int tam = cursor.getCount();
             if (cursor.getCount() > 0 ) {
                  servicoRemoto.listaSincronizadaAlteracao(cursor, contexto);
                  DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "PrecoProduto: " + tam +  " >> ");
                  resolver.delete(PrecoProdutoContract.buildDeleteAllSinc(),null,null);
             }
             if ((atualizaLocal) && (forcaAtualizacao || (tam>0))) {
                 int tamLista = 0;
                 tamLista = servicoRemoto.listaSincronizadaDaoAtualizaPorId(contexto,delete,codigoAplicacao);
                 DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "PrecoProduto: " + tamLista +  " << ");
             }
        } catch (Exception e) {
         	 DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,e);
             e.printStackTrace();
        }
        
    }
}