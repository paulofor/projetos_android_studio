package br.com.lojadigicom.coletorprecocliente.servico;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.sql.Timestamp;
import java.util.Date;

import br.com.lojadigicom.coletorprecocliente.data.contract.InteresseProdutoContract;
import br.com.lojadigicom.coletorprecocliente.data.contract.UsuarioContract;
import br.com.lojadigicom.coletorprecocliente.data.contract.UsuarioPesquisaContract;
import br.com.lojadigicom.coletorprecocliente.framework.dao.MontadorDaoI;
import br.com.lojadigicom.coletorprecocliente.modelo.InteresseProduto;
import br.com.lojadigicom.coletorprecocliente.modelo.InteresseProdutoVo;
import br.com.lojadigicom.coletorprecocliente.modelo.Usuario;
import br.com.lojadigicom.coletorprecocliente.modelo.UsuarioPesquisa;
import br.com.lojadigicom.coletorprecocliente.modelo.UsuarioPesquisaVo;
import br.com.lojadigicom.coletorprecocliente.modelo.montador.UsuarioMontador;

/**
 * Created by Paulo on 23/04/2016.
 */
public class InteresseProdutoServicoImpl  extends InteresseProdutoServico {
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
        }
    }

    // Passar para o base
    protected Usuario getUsuario(Context context) {
        Usuario usuario = null;
        Uri uriUsuario = UsuarioContract.buildAll();
        Cursor cursor = context.getContentResolver().query(uriUsuario, null, null, null, null);
        if (cursor.moveToFirst()) {
            MontadorDaoI montador = new UsuarioMontador();
            usuario = (Usuario) montador.getItem(cursor);
        }
        return usuario;
    }
}
