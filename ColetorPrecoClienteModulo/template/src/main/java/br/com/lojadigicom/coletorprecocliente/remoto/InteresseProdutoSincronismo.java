package  br.com.lojadigicom.coletorprecocliente.remoto;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import br.com.lojadigicom.coletorprecocliente.data.contract.AplicacaoContract;
import br.com.lojadigicom.coletorprecocliente.data.contract.InteresseProdutoContract;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;

public class InteresseProdutoSincronismo {
	
    public void sincroniza(Context contexto, boolean forcaAtualizacao, boolean delete, boolean atualizaLocal) {
    	String codigoAplicacao = AplicacaoContract.getCodigoAplicacaoSinc();
 	 	InteresseProdutoRemote servicoRemoto = new  InteresseProdutoRemote(); // fazer estatico ?
        try {
             ContentResolver resolver = contexto.getContentResolver();
             Cursor cursor = resolver.query(InteresseProdutoContract.buildAllSinc(), InteresseProdutoContract.COLS_SINC, null, null, null);
             int tam = cursor.getCount();
             if (cursor.getCount() > 0 ) {
                  servicoRemoto.listaSincronizadaAlteracao(cursor, contexto);
                  DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "InteresseProduto: " + tam +  " >> ");
                  resolver.delete(InteresseProdutoContract.buildDeleteAllSinc(),null,null);
             }
             if ((atualizaLocal) && (forcaAtualizacao || (tam>0))) {
                 int tamLista = 0;
                 tamLista = servicoRemoto.listaSincronizadaDao(contexto,delete,codigoAplicacao);
                 DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "InteresseProduto: " + tamLista +  " << ");
             }
        } catch (Exception e) {
         	 DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,e);
             e.printStackTrace();
        }
    }
    
    // Chama outro metodo de DAO - Fazendo apenas updates. Ex: InteresseProduto
    public void sincronizaAtualizaPorId(Context contexto, boolean forcaAtualizacao, boolean delete, boolean atualizaLocal) {
    	String codigoAplicacao = AplicacaoContract.getCodigoAplicacaoSinc();
 	 	InteresseProdutoRemote servicoRemoto = new  InteresseProdutoRemote(); // fazer estatico ?
        try {
             ContentResolver resolver = contexto.getContentResolver();
             Cursor cursor = resolver.query(InteresseProdutoContract.buildAllSinc(), InteresseProdutoContract.COLS_SINC, null, null, null);
             int tam = cursor.getCount();
             if (cursor.getCount() > 0 ) {
                  servicoRemoto.listaSincronizadaAlteracao(cursor, contexto);
                  DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "InteresseProduto: " + tam +  " >> ");
                  resolver.delete(InteresseProdutoContract.buildDeleteAllSinc(),null,null);
             }
             if ((atualizaLocal) && (forcaAtualizacao || (tam>0))) {
                 int tamLista = 0;
                 tamLista = servicoRemoto.listaSincronizadaDaoAtualizaPorId(contexto,delete,codigoAplicacao);
                 DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "InteresseProduto: " + tamLista +  " << ");
             }
        } catch (Exception e) {
         	 DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,e);
             e.printStackTrace();
        }
        
    }
}