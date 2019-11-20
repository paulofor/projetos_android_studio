package  br.com.lojadigicom.coletorprecocliente.remoto;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;


import br.com.lojadigicom.coletorprecocliente.data.contract.DispositivoUsuarioContract;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;

public class DispositivoUsuarioSincronismo {
	
	private static String codigoAplicacao = null;
	
	// Versao tradicional sempre com atualizacao local.
    public void sincroniza(Context contexto, boolean forcaAtualizacao, String codigoAplicacao) {
        sincroniza(contexto,forcaAtualizacao,true,true,codigoAplicacao);
    }
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
    
    public void sincroniza(Context contexto, boolean forcaAtualizacao, boolean delete, boolean atualizaLocal, String codigoAplicacao) {
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
                 int tamLista = servicoRemoto.listaSincronizadaDao(contexto,delete,codigoAplicacao);
                 DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, "DispositivoUsuario: " + tamLista +  " << ");
             }
        } catch (Exception e) {
         	 DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,e);
             e.printStackTrace();
        }
        
    }
}