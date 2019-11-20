package br.com.lojadigicom.coletorprecocliente.sync;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;

import java.io.File;
import java.util.List;

import br.com.lojadigicom.coletorprecocliente.app.MainActivity;
import br.com.lojadigicom.coletorprecocliente.data.contract.InteresseProdutoContract;
import br.com.lojadigicom.coletorprecocliente.data.contract.ProdutoClienteContract;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.framework.util.FileUtil;
import br.com.lojadigicom.coletorprecocliente.modelo.ProdutoCliente;
import br.com.lojadigicom.coletorprecocliente.modelo.UsuarioPesquisa;
import br.com.lojadigicom.coletorprecocliente.remoto.InteresseProdutoSincronismo;
import br.com.lojadigicom.coletorprecocliente.remoto.NaturezaProdutoSincronismo;
import br.com.lojadigicom.coletorprecocliente.remoto.OportunidadeDiaSincronismo;
import br.com.lojadigicom.coletorprecocliente.remoto.ProdutoClienteSincronismo;
import br.com.lojadigicom.coletorprecocliente.remoto.UsuarioPesquisaSincronismo;


/**
 * Created by Paulo on 19/11/15.
 */
public class Sincronizador {

    private static String codigoAplicacao = null;

    public static void executa(Context ctx) {

        //apagaTabela(ctx);
        UsuarioPesquisaSincronismo usuarioPesquisaSincronismo = new UsuarioPesquisaSincronismo();
        usuarioPesquisaSincronismo.sincroniza(ctx,true,true,codigoAplicacao);

        NaturezaProdutoSincronismo naturezaProdutoSincronismo = new NaturezaProdutoSincronismo();
        naturezaProdutoSincronismo.sincroniza(ctx,true, true,codigoAplicacao);

        OportunidadeDiaSincronismo oportunidadeDiaSincronismo = new OportunidadeDiaSincronismo();
        oportunidadeDiaSincronismo.sincroniza(ctx,true, true,codigoAplicacao);

        ProdutoClienteSincronismo produtoSincronismo = new ProdutoClienteSincronismo();
        produtoSincronismo.sincroniza(ctx,true,false,codigoAplicacao);



        InteresseProdutoSincronismo interesseProdutoSincronismo = new InteresseProdutoSincronismo();
        interesseProdutoSincronismo.sincroniza(ctx,false,false,false,codigoAplicacao);

        obtemImagens(ctx);

        //apagaTabela(ctx);
        FileUtil.copiaArquivo();
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

    private static void obtemImagens(Context contexto) {
        Uri uri = ProdutoClienteContract.buildObtemProximoNaoEscolhido();
        Cursor cursor = contexto.getContentResolver().query(uri,null,null,null,null);
        List<ProdutoCliente> lista = ProdutoClienteContract.converteLista(cursor);
        for (ProdutoCliente produto : lista) {
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


    public static void setCodigoAplicacao(String valor) {
        codigoAplicacao = valor;
    }
    public static String getCodigoAplicacao() { return codigoAplicacao;}
}
