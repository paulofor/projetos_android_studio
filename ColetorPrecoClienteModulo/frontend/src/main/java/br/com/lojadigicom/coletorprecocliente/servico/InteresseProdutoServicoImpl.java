package br.com.lojadigicom.coletorprecocliente.servico;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.sql.Timestamp;
import java.util.Date;

import br.com.lojadigicom.coletorprecocliente.data.contract.InteresseProdutoContract;
import br.com.lojadigicom.coletorprecocliente.data.contract.UsuarioContract;
import br.com.lojadigicom.coletorprecocliente.framework.dao.MontadorDaoI;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.modelo.InteresseProduto;
import br.com.lojadigicom.coletorprecocliente.modelo.InteresseProdutoVo;
import br.com.lojadigicom.coletorprecocliente.modelo.ProdutoCliente;
import br.com.lojadigicom.coletorprecocliente.modelo.Usuario;
import br.com.lojadigicom.coletorprecocliente.modelo.montador.UsuarioMontador;


// Em alguma momento passar isso para template entre 23-01-2017 ate 23-02-2017
class InteresseProdutoServicoImpl  extends InteresseProdutoServicoApp {
    @Override
    public void ExcluiItem(Context context) {

    }

    @Override
    public void IncluiItem(Context context) { // verifica se ja existe.
        // Não existe registro, verifica usuario
        Usuario usuario = getUsuario(context);
        if (usuario != null) {
            InteresseProduto interesseProduto = new InteresseProdutoVo();
            interesseProduto.setIdUsuarioS(usuario.getIdUsuario());
            interesseProduto.setIdProdutoClienteRa(this.getFiltro().getProdutoCliente().getIdProdutoCliente());
            interesseProduto.setNota(this.getFiltro().getNota());
            interesseProduto.setValor(this.getFiltro().getProdutoCliente().getPrecoAtual());
            Date date= new Date();
            interesseProduto.setData(new Timestamp(date.getTime()));
            Uri uriInsert = InteresseProdutoContract.buildInsereSinc();
            context.getContentResolver().insert(uriInsert, interesseProduto.getContentValues());
        } else {
            DCLog.e(DCLog.SERVICO_DATABASE,this,"Usuario = null !!!");
        }
    }

    @Override
    public InteresseProduto criaParaProduto(ProdutoCliente produto, Context context) {
        // apos update no banco executa novamente.
        InteresseProduto interesseProduto = null;
        Usuario usuario = getUsuario(context);
        if (usuario != null) {
            interesseProduto = new InteresseProdutoVo();
            interesseProduto.setIdUsuarioS(usuario.getIdUsuario());
            interesseProduto.setProdutoCliente_ReferenteA(produto);
            interesseProduto.setIdProdutoClienteRa(produto.getIdObj());
            interesseProduto.setNota(0);
            interesseProduto.setNovo(true);
            interesseProduto.setMudanca(false);
            interesseProduto.setValor(produto.getPrecoAtual());
            Date date= new Date();
            interesseProduto.setData(new Timestamp(date.getTime()));
            interesseProduto.setMonitora(false);
            interesseProduto.setEspera(false);
        } else {
            DCLog.e(DCLog.SERVICO_DATABASE,this,"Usuario = null !!!");
        }
        return interesseProduto;
    }

    // Passar para o base
    private Usuario getUsuario(Context context) {
        Usuario usuario = null;
        Uri uriUsuario = UsuarioContract.buildAll();
        Cursor cursor = context.getContentResolver().query(uriUsuario, null, null, null, null);
        if (cursor!=null && cursor.moveToFirst()) {
            MontadorDaoI montador = new UsuarioMontador();
            usuario = (Usuario) montador.getItem(cursor);
        }
        return usuario;
    }
}
