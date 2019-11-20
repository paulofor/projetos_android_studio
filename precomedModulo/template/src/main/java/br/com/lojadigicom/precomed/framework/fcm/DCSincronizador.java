package br.com.lojadigicom.precomed.framework.fcm;


import android.content.Context;
import br.com.lojadigicom.precomed.remoto.DispositivoUsuarioSincronismo;
import br.com.lojadigicom.precomed.remoto.UsuarioSincronismo;
import br.com.lojadigicom.precomed.data.contract.AplicacaoContract;

public abstract class DCSincronizador {

    public final void executaServerToMobile(Context ctx) {
    	executaDiario(ctx,AplicacaoContract.getCodigoAplicacaoSinc());
    	sincronizaDispositivo(ctx);
    	sincronizaUsuario(ctx); // Precisa quando ocorre alteracao de banco de dados
    }
    
    
    
    public abstract void executaDiario(Context ctx, String codigoAplicacao);
    
    private void sincronizaDispositivo(Context ctx) {
    	DispositivoUsuarioSincronismo dispositivoUsuarioSincronismo = new DispositivoUsuarioSincronismo();
        dispositivoUsuarioSincronismo.sincroniza(ctx,true);
    }
    private void sincronizaUsuario(Context ctx) {
        UsuarioSincronismo usuarioSincronismo = new UsuarioSincronismo();
        usuarioSincronismo.sincroniza(ctx,true, false, AplicacaoContract.getCodigoAplicacaoSinc());
    }
    //public void sincronizaUsuarioDispositivo(Context ctx) {
        // Primeiro atualizar dispositivo para criar automaticamente o usuario.
    //    DispositivoUsuarioSincronismo dispositivoUsuario = new DispositivoUsuarioSincronismo();
    //    dispositivoUsuario.sincroniza(ctx,true, Sincronizador.getCodigoAplicacao());
    //    UsuarioSincronismo usuarioSincronismo = new UsuarioSincronismo();
    //    usuarioSincronismo.sincroniza(ctx,true, false, Sincronizador.getCodigoAplicacao());
    //}
    
    public void sincronizaDispositivoFcmId(Context ctx) {
        DispositivoUsuarioSincronismo dispositivoUsuarioSincronismo = new DispositivoUsuarioSincronismo();
        dispositivoUsuarioSincronismo.sincroniza(ctx, true);
    }
	public void sincronizaUsuarioDispositivoFcmId(Context ctx) {
        // Primeiro atualizar dispositivo para criar automaticamente o usuario.
        DispositivoUsuarioSincronismo dispositivoUsuario = new DispositivoUsuarioSincronismo();
        dispositivoUsuario.sincroniza(ctx,true, AplicacaoContract.getCodigoAplicacaoSinc());
        UsuarioSincronismo usuarioSincronismo = new UsuarioSincronismo();
        usuarioSincronismo.sincroniza(ctx,true, false, AplicacaoContract.getCodigoAplicacaoSinc());
    }
}
