package br.com.lojadigicom.coletorprecocliente.sync;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

import java.io.File;
import java.util.List;


import br.com.lojadigicom.coletorprecocliente.data.contract.AplicacaoContract;
import br.com.lojadigicom.coletorprecocliente.data.contract.InteresseProdutoContract;
import br.com.lojadigicom.coletorprecocliente.data.contract.ProdutoClienteContract;
import br.com.lojadigicom.coletorprecocliente.faturamento.BillingWrapper;
import br.com.lojadigicom.coletorprecocliente.framework.fcm.DCSincronizador;

import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.framework.tela.DCAplicacao;
import br.com.lojadigicom.coletorprecocliente.framework.util.FileUtil;
import br.com.lojadigicom.coletorprecocliente.modelo.ProdutoCliente;
import br.com.lojadigicom.coletorprecocliente.modelo.UsuarioPesquisa;
import br.com.lojadigicom.coletorprecocliente.remoto.DispositivoUsuarioSincronismo;
import br.com.lojadigicom.coletorprecocliente.remoto.InteresseProdutoSincronismo;
import br.com.lojadigicom.coletorprecocliente.remoto.NaturezaProdutoSincronismo;
import br.com.lojadigicom.coletorprecocliente.remoto.OportunidadeDiaSincronismo;
import br.com.lojadigicom.coletorprecocliente.remoto.PrecoDiarioClienteSincronismo;
import br.com.lojadigicom.coletorprecocliente.remoto.ProdutoClienteSincronismo;
import br.com.lojadigicom.coletorprecocliente.remoto.UsuarioPesquisaSincronismo;
import br.com.lojadigicom.coletorprecocliente.remoto.UsuarioSincronismo;


/**
 * Created by Paulo on 19/11/15.
 */
public class Sincronizador extends DCSincronizador {


    public Sincronizador(DCAplicacao app) {
        super(app);
    }

    @Override
    protected void sincronizacaoDiaria(Context ctx, String codigoAplicacao) {

        //apagaTabela(ctx);
        UsuarioPesquisaSincronismo usuarioPesquisaSincronismo = new UsuarioPesquisaSincronismo();
        usuarioPesquisaSincronismo.sincroniza(ctx,true,true,true);



        //OportunidadeDiaSincronismo oportunidadeDiaSincronismo = new OportunidadeDiaSincronismo();
        //oportunidadeDiaSincronismo.sincroniza(ctx,true, true,codigoAplicacao);

        ProdutoClienteSincronismo produtoSincronismo = new ProdutoClienteSincronismo();
        produtoSincronismo.sincroniza(ctx,true, false, true);


        InteresseProdutoSincronismo interesseProdutoSincronismo = new InteresseProdutoSincronismo();
        interesseProdutoSincronismo.sincronizaAtualizaPorId(ctx,true, true, true);


        PrecoDiarioClienteSincronismo precoDiarioClienteSincronismo = new PrecoDiarioClienteSincronismo();
        // delete atual, atualizacao on -> igual ao oportunidadedia
        precoDiarioClienteSincronismo.sincroniza(ctx,true, true,true);


        NaturezaProdutoSincronismo naturezaProdutoSincronismo = new NaturezaProdutoSincronismo();
        naturezaProdutoSincronismo.sincroniza(ctx,true, true,true);

        try {
            atualizaBilling(ctx);
        } catch (Exception e) {
            DCLog.d(DCLog.BILLING,this,"Pulou o billing");
        }
        //obtemImagens(ctx);

        //apagaTabela(ctx);
        //FileUtil.copiaArquivo();
//
//        LojaVirtualSincronismo lojaVirtualSincronismo = new LojaVirtualSincronismo();
//        lojaVirtualSincronismo.sincroniza(ctx,true);
//
//        ContagemProdutoSincronismo contagemProdutoSincronismo = new ContagemProdutoSincronismo();
//        contagemProdutoSincronismo.sincroniza(ctx,true);
//
//        DispositivoUsuarioSincronismo dispositivoUsuarioSincronismo = new DispositivoUsuarioSincronismo();
//        dispositivoUsuarioSincronismo.sincroniza(ctx,true);
//        UsuarioSincronismo usuarioSincronismo = new UsuarioSincronismo();
//        usuarioSincronismo.sincroniza(ctx,true);
    }

    @Override
    public void sincronizacaoUpgradeBd() {

        Context ctx = this.aplicacao;

        DispositivoUsuarioSincronismo dispositivoUsuarioSincronismo = new DispositivoUsuarioSincronismo();
        dispositivoUsuarioSincronismo.sincroniza(ctx,true, false,true);

        UsuarioSincronismo usuarioSincronismo = new UsuarioSincronismo();
        usuarioSincronismo.sincroniza(ctx,true, false,true);

        NaturezaProdutoSincronismo naturezaProdutoSincronismo = new NaturezaProdutoSincronismo();
        naturezaProdutoSincronismo.sincroniza(ctx,true, false,true);

        ProdutoClienteSincronismo produtoClienteSincronismo = new ProdutoClienteSincronismo();
        produtoClienteSincronismo.sincroniza(ctx,true, false,true);

        InteresseProdutoSincronismo interesseProdutoSincronismo = new InteresseProdutoSincronismo();
        interesseProdutoSincronismo.sincroniza(ctx,true, false,true);

        PrecoDiarioClienteSincronismo produtoDiarioCliente = new PrecoDiarioClienteSincronismo();
        produtoDiarioCliente.sincroniza(ctx,true, false,true);

        UsuarioPesquisaSincronismo usuarioPesquisaSincronismo = new UsuarioPesquisaSincronismo();
        usuarioPesquisaSincronismo.sincroniza(ctx,true, false,true);

        OportunidadeDiaSincronismo oportunidadeDiaSincronismo = new OportunidadeDiaSincronismo();
        oportunidadeDiaSincronismo.sincroniza(ctx,true, false,true);
    }


    private void atualizaBilling(Context ctx) {
        BillingWrapper billingWrapper = new BillingWrapper(aplicacao);
        billingWrapper.inicializaVerificacao(ctx);
    }



    @Override
    protected void carregaImagens(Context ctx, String codigoAplicacao) {
        obtemImagens(ctx);
    }

    private static void obtemImagens(Context contexto) {
        int QUANTIDADE = 10;
        Uri uri = ProdutoClienteContract.buildObtemProximoNaoEscolhido();
        Cursor cursor = contexto.getContentResolver().query(uri,null,null,null,null);
        List<ProdutoCliente> lista = ProdutoClienteContract.converteLista(cursor);

        for (int i = 0; (i < lista.size()) && (i < QUANTIDADE); i++) {
            ProdutoCliente produto = lista.get(i);
            FutureTarget<File> future = Glide.with(contexto)
                    .load(produto.getImagem())
                    .downloadOnly(280, 280);
            DCLog.d("Cache",Sincronizador.class,"(ID:" + produto.getIdProdutoCliente() + ") " +produto.getImagem());
        }
    }

    private static void apagaTabela(Context context) {
        Uri uri = InteresseProdutoContract.buildDeleteAllRecreate();
        context.getContentResolver().delete(uri,null,null);
        uri = InteresseProdutoContract.buildAllSinc();
        context.getContentResolver().delete(uri,null,null);

        uri = ProdutoClienteContract.buildDeleteAllRecreate();
        context.getContentResolver().delete(uri,null,null);
    }


    /*
    private void obtemEmailGoogle() {
        GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
        GoogleSignInAccount acct = result.getSignInAccount();
        String personName = acct.getDisplayName();
        String personGivenName = acct.getGivenName();
        String personFamilyName = acct.getFamilyName();
        String personEmail = acct.getEmail();
        String personId = acct.getId();
        Uri personPhoto = acct.getPhotoUrl();
    }
    */



}
