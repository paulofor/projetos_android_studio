package  br.com.lojadigicom.capitalexterno.remoto;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;


import br.com.lojadigicom.capitalexterno.data.contract.CustoMensalContract;
import br.com.lojadigicom.capitalexterno.framework.log.DCLog;

public class CustoMensalSincronismo {
	
	private static String codigoAplicacao = null;
	
	// Versao tradicional sempre com atualizacao local.
	/*
    public void sincroniza(Context contexto, boolean forcaAtualizacao, boolean delete, String codigoAplicacao) {
        sincroniza(contexto,forcaAtualizacao,delete,true,codigoAplicacao);
    }
    public void sincroniza(Context contexto, boolean forcaAtualizacao, boolean delete) {
        sincroniza(contexto,forcaAtualizacao,delete,true);
    }
    */

	// Versao nova podendo nao ter atualizacao local.
	/*
	public void sincroniza(Context contexto, boolean forcaAtualizacao, boolean delete, boolean atualizaLocal) {
	 	CustoMensalRemote servicoRemoto = new  CustoMensalRemote(); // fazer estatico ?
        try {
             ContentResolver resolver = contexto.getContentResolver();
             Cursor cursor = resolver.query(CustoMensalContract.buildAllSinc(), CustoMensalContract.COLS_SINC, null, null, null);
             int tam = cursor.getCount();
             DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "CustoMensal: " + tam +  " >> ");
             if (cursor.getCount() > 0 ) {
                  servicoRemoto.listaSincronizadaAlteracao(cursor, contexto);
                  resolver.delete(CustoMensalContract.buildDeleteAllSinc(),null,null);
             }
             if ((atualizaLocal) && (forcaAtualizacao || (tam>0))) {
                 int tamLista = servicoRemoto.listaSincronizadaDao(contexto,delete);
                 DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "CustoMensal: " + tamLista +  " << ");
             }
        } catch (Exception e) {
         	 DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,e);
             e.printStackTrace();
        }
        
    }
    */
    
    public void sincroniza(Context contexto, boolean forcaAtualizacao, boolean delete, boolean atualizaLocal) {
    	sincroniza(contexto, forcaAtualizacao, delete, atualizaLocal,null);
    }
    
    
    
    public void sincronizaAtualizaPorId(Context contexto, boolean forcaAtualizacao, boolean delete, boolean atualizaLocal, String codigoAplicacao) {
        //DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "forcaAtualizacao=" + forcaAtualizacao + ", delete=" + delete + ", atualizaMobile=" + atualizaLocal);
        CustoMensalRemote servicoRemoto = new CustoMensalRemote(); // fazer estatico ?
        try {
            ContentResolver resolver = contexto.getContentResolver();
            Cursor cursor = resolver.query(CustoMensalContract.buildAllSinc(), CustoMensalContract.COLS_SINC, null, null, null);
            int tam = cursor.getCount();
            if (cursor.getCount() > 0) {
                servicoRemoto.listaSincronizadaAlteracao(cursor, contexto);
                DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "CustoMensal: " + tam + " >> ");
                resolver.delete(CustoMensalContract.buildDeleteAllSinc(), null, null);
            }
            if ((atualizaLocal) && (forcaAtualizacao || (tam > 0))) {
                int tamLista = 0;
                if (codigoAplicacao == null) {
                    tamLista = servicoRemoto.listaSincronizadaDaoAtualizaPorId(contexto, delete, null);
                } else {
                    tamLista = servicoRemoto.listaSincronizadaDaoAtualizaPorId(contexto, delete, codigoAplicacao);
                }
                DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "CustoMensal: " + tamLista + " << ");
            }
        } catch (Exception e) {
            DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
            e.printStackTrace();
        }
    }
    
    
    public void sincroniza(Context contexto, boolean forcaAtualizacao, boolean delete, boolean atualizaLocal, String codigoAplicacao) {
     	//DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"forcaAtualizacao=" + forcaAtualizacao + ", delete=" + delete + ", atualizaMobile=" + atualizaLocal);
	 	CustoMensalRemote servicoRemoto = new  CustoMensalRemote(); // fazer estatico ?
        try {
             ContentResolver resolver = contexto.getContentResolver();
             Cursor cursor = resolver.query(CustoMensalContract.buildAllSinc(), CustoMensalContract.COLS_SINC, null, null, null);
             int tam = cursor.getCount();
             if (cursor.getCount() > 0 ) {
                  servicoRemoto.listaSincronizadaAlteracao(cursor, contexto);
                  DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "CustoMensal: " + tam +  " >> ");
                  resolver.delete(CustoMensalContract.buildDeleteAllSinc(),null,null);
             }
             if ((atualizaLocal) && (forcaAtualizacao || (tam>0))) {
                 int tamLista = 0;
                 if (codigoAplicacao==null) {
	                 tamLista = servicoRemoto.listaSincronizadaDao(contexto,delete);
                 } else {
    	             tamLista = servicoRemoto.listaSincronizadaDao(contexto,delete,codigoAplicacao);
    	         }    
                 DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "CustoMensal: " + tamLista +  " << ");
             }
        } catch (Exception e) {
         	 DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,e);
             e.printStackTrace();
        }
        
    }
}