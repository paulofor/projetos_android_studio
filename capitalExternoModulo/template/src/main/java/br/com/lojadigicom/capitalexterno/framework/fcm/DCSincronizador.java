package br.com.lojadigicom.capitalexterno.framework.fcm;


/*
Sincronizacao Inicial

1- DCFirebaseInstanceIDService.onTokenRefresh <- Recebendo do FCM
2- sincronizador.sincronizaUsuarioDispositivoFcmId -> Servidor para criar usuario/dispositivo
3- Mobile atualiza dispositivo com o token
4- sincroniza??o de dispositivo para enviar o token.
5- Execu??o sincroniza??o geral diario.

*/

import android.content.Context;
import br.com.lojadigicom.capitalexterno.framework.tela.DCAplicacao;
import br.com.lojadigicom.capitalexterno.remoto.DispositivoUsuarioSincronismo;
import br.com.lojadigicom.capitalexterno.remoto.UsuarioSincronismo;
import br.com.lojadigicom.capitalexterno.data.contract.AplicacaoContract;

public abstract class DCSincronizador {


	protected DCAplicacao aplicacao; // para ser usado como contexto quando necessario.

    public DCSincronizador(DCAplicacao app) {
        aplicacao = app;
    }

    public final void executaServerToMobile(Context ctx) {
    	executaDiario(ctx,AplicacaoContract.getCodigoAplicacaoSinc());
    	sincronizaDispositivo(ctx);
    	sincronizaUsuario(ctx); // Precisa quando ocorre alteracao de banco de dados
    }
    
    
    
    public final void executaDiario(Context ctx, String codigoAplicacao) {
        sincronizacaoDiaria(ctx,codigoAplicacao);
        carregaImagens(ctx,codigoAplicacao); // Diferencial. Permite uso offline.
    }
    protected abstract void sincronizacaoDiaria(Context ctx, String codigoAplicacao);
    public abstract void sincronizacaoUpgradeBd();
    protected abstract void carregaImagens(Context ctx, String codigoAplicacao);

    
    private void sincronizaDispositivo(Context ctx) {
    	DispositivoUsuarioSincronismo dispositivoUsuarioSincronismo = new DispositivoUsuarioSincronismo();
        dispositivoUsuarioSincronismo.sincroniza(ctx,true);
    }
    private void sincronizaUsuario(Context ctx) {
        UsuarioSincronismo usuarioSincronismo = new UsuarioSincronismo();
        usuarioSincronismo.sincroniza(ctx,true, false, true, AplicacaoContract.getCodigoAplicacaoSinc());
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
        usuarioSincronismo.sincroniza(ctx,true, false, true, AplicacaoContract.getCodigoAplicacaoSinc());
    }
}
