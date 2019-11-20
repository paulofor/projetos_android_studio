package  br.com.lojadigicom.coletorprecocliente.remoto;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import br.com.lojadigicom.coletorprecocliente.data.contract.AplicacaoContract;
import br.com.lojadigicom.coletorprecocliente.data.contract.OportunidadeInteresseClienteContract;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;

public class OportunidadeInteresseClienteSincronismo {
	
    public void sincroniza(Context contexto, boolean forcaAtualizacao, boolean delete, boolean atualizaLocal) {
    	String codigoAplicacao = AplicacaoContract.getCodigoAplicacaoSinc();
 	 	OportunidadeInteresseClienteRemote servicoRemoto = new  OportunidadeInteresseClienteRemote(); // fazer estatico ?
        try {
             ContentResolver resolver = contexto.getContentResolver();
             Cursor cursor = resolver.query(OportunidadeInteresseClienteContract.buildAllSinc(), OportunidadeInteresseClienteContract.COLS_SINC, null, null, null);
             int tam = cursor.getCount();
             if (cursor.getCount() > 0 ) {
                  servicoRemoto.listaSincronizadaAlteracao(cursor, contexto);
                  DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "OportunidadeInteresseCliente: " + tam +  " >> ");
                  resolver.delete(OportunidadeInteresseClienteContract.buildDeleteAllSinc(),null,null);
             }
             if ((atualizaLocal) && (forcaAtualizacao || (tam>0))) {
                 int tamLista = 0;
                 tamLista = servicoRemoto.listaSincronizadaDao(contexto,delete,codigoAplicacao);
                 DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "OportunidadeInteresseCliente: " + tamLista +  " << ");
             }
        } catch (Exception e) {
         	 DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,e);
             e.printStackTrace();
        }
    }
    
    // Chama outro metodo de DAO - Fazendo apenas updates. Ex: InteresseProduto
    public void sincronizaAtualizaPorId(Context contexto, boolean forcaAtualizacao, boolean delete, boolean atualizaLocal) {
    	String codigoAplicacao = AplicacaoContract.getCodigoAplicacaoSinc();
 	 	OportunidadeInteresseClienteRemote servicoRemoto = new  OportunidadeInteresseClienteRemote(); // fazer estatico ?
        try {
             ContentResolver resolver = contexto.getContentResolver();
             Cursor cursor = resolver.query(OportunidadeInteresseClienteContract.buildAllSinc(), OportunidadeInteresseClienteContract.COLS_SINC, null, null, null);
             int tam = cursor.getCount();
             if (cursor.getCount() > 0 ) {
                  servicoRemoto.listaSincronizadaAlteracao(cursor, contexto);
                  DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "OportunidadeInteresseCliente: " + tam +  " >> ");
                  resolver.delete(OportunidadeInteresseClienteContract.buildDeleteAllSinc(),null,null);
             }
             if ((atualizaLocal) && (forcaAtualizacao || (tam>0))) {
                 int tamLista = 0;
                 tamLista = servicoRemoto.listaSincronizadaDaoAtualizaPorId(contexto,delete,codigoAplicacao);
                 DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "OportunidadeInteresseCliente: " + tamLista +  " << ");
             }
        } catch (Exception e) {
         	 DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,e);
             e.printStackTrace();
        }
        
    }
}