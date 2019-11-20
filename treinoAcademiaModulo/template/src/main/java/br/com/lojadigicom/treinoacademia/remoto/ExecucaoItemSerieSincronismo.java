package  br.com.lojadigicom.treinoacademia.remoto;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import br.com.lojadigicom.treinoacademia.data.contract.AplicacaoContract;
import br.com.lojadigicom.treinoacademia.data.contract.ExecucaoItemSerieContract;
import br.com.lojadigicom.treinoacademia.framework.log.DCLog;

public class ExecucaoItemSerieSincronismo {
	
    public void sincroniza(Context contexto, boolean forcaAtualizacao, boolean delete, boolean atualizaLocal) {
    	String codigoAplicacao = AplicacaoContract.getCodigoAplicacaoSinc();
 	 	ExecucaoItemSerieRemote servicoRemoto = new  ExecucaoItemSerieRemote(); // fazer estatico ?
        try {
             ContentResolver resolver = contexto.getContentResolver();
             Cursor cursor = resolver.query(ExecucaoItemSerieContract.buildAllSinc(), ExecucaoItemSerieContract.COLS_SINC, null, null, null);
             int tam = cursor.getCount();
             if (cursor.getCount() > 0 ) {
                  servicoRemoto.listaSincronizadaAlteracao(cursor, contexto);
                  DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "ExecucaoItemSerie: " + tam +  " >> ");
                  resolver.delete(ExecucaoItemSerieContract.buildDeleteAllSinc(),null,null);
             }
             if ((atualizaLocal) && (forcaAtualizacao || (tam>0))) {
                 int tamLista = 0;
                 tamLista = servicoRemoto.listaSincronizadaDao(contexto,delete,codigoAplicacao);
                 DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "ExecucaoItemSerie: " + tamLista +  " << ");
             }
        } catch (Exception e) {
         	 DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,e);
             e.printStackTrace();
        }
    }
    
    // Chama outro metodo de DAO - Fazendo apenas updates. Ex: InteresseProduto
    public void sincronizaAtualizaPorId(Context contexto, boolean forcaAtualizacao, boolean delete, boolean atualizaLocal) {
    	String codigoAplicacao = AplicacaoContract.getCodigoAplicacaoSinc();
 	 	ExecucaoItemSerieRemote servicoRemoto = new  ExecucaoItemSerieRemote(); // fazer estatico ?
        try {
             ContentResolver resolver = contexto.getContentResolver();
             Cursor cursor = resolver.query(ExecucaoItemSerieContract.buildAllSinc(), ExecucaoItemSerieContract.COLS_SINC, null, null, null);
             int tam = cursor.getCount();
             if (cursor.getCount() > 0 ) {
                  servicoRemoto.listaSincronizadaAlteracao(cursor, contexto);
                  DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "ExecucaoItemSerie: " + tam +  " >> ");
                  resolver.delete(ExecucaoItemSerieContract.buildDeleteAllSinc(),null,null);
             }
             if ((atualizaLocal) && (forcaAtualizacao || (tam>0))) {
                 int tamLista = 0;
                 tamLista = servicoRemoto.listaSincronizadaDaoAtualizaPorId(contexto,delete,codigoAplicacao);
                 DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "ExecucaoItemSerie: " + tamLista +  " << ");
             }
        } catch (Exception e) {
         	 DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,e);
             e.printStackTrace();
        }
        
    }
}