package br.com.lojadigicom.coletorprecocliente.servico;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import br.com.lojadigicom.coletorprecocliente.data.contract.UsuarioContract;
import br.com.lojadigicom.coletorprecocliente.data.contract.UsuarioPesquisaContract;
import br.com.lojadigicom.coletorprecocliente.framework.dao.MontadorDaoI;
import br.com.lojadigicom.coletorprecocliente.modelo.Usuario;
import br.com.lojadigicom.coletorprecocliente.modelo.UsuarioPesquisa;
import br.com.lojadigicom.coletorprecocliente.modelo.UsuarioPesquisaVo;
import br.com.lojadigicom.coletorprecocliente.modelo.montador.UsuarioMontador;

/**
 * Created by Paulo on 24/02/16.
 */
public class UsuarioPesquisaServicoImpl extends UsuarioPesquisaServico{
    @Override
    public void ExcluiItem(Context context) {
        // verifica se ja existe.
        Uri uri = UsuarioPesquisaContract.buildAll();
        Cursor cursor = context.getContentResolver().query(uri,null,
                UsuarioPesquisaContract.COLUNA_ID_NATUREZA_PRODUTO_P + " = ? ",
                new String[]{"" + getFiltro().getNatureza().getIdNaturezaProduto()},null);
        if (cursor.moveToNext()) {
            // Verifica se veio o registro - O registro precisa existir se não existir é um erro
            UsuarioPesquisa usuarioPesquisa = this.getFiltro().getNatureza().getCorrenteUsuarioPesquisa_PesquisadoPor();
            if (this.getFiltro().getNatureza()==null) {
                throw new RuntimeException("Objeto Natureza Produto está nulo");
            }
            if (usuarioPesquisa==null) {
                throw new RuntimeException("Item UsuarioPesquisa não encontrado ");
            }
            //if (usuarioPesquisa!=null) {
            Uri uriDelete = UsuarioPesquisaContract.buildDeleteSinc((int)usuarioPesquisa.getIdUsuarioPesquisa());
            context.getContentResolver().delete(uriDelete,null,null);
            //}
        }
    }

    @Override
    public void IncluiItem(Context context) {
        // verifica se ja existe.
        Uri uri = UsuarioPesquisaContract.buildAll();
        Cursor cursor = context.getContentResolver().query(uri,null,
                UsuarioPesquisaContract.COLUNA_ID_NATUREZA_PRODUTO_P + " = ? ",
                new String[]{"" + getFiltro().getNatureza().getIdNaturezaProduto()},null);
        if (!cursor.moveToNext()) {
            // Não existe registro, verifica usuario
            Usuario usuario = getUsuario(context);
            if (usuario != null) {
                UsuarioPesquisa usuarioPesquisa = new UsuarioPesquisaVo();
                usuarioPesquisa.setIdUsuarioS(usuario.getIdUsuario());
                usuarioPesquisa.setIdNaturezaProdutoP(this.getFiltro().getNatureza().getIdNaturezaProduto());
                Uri uriInsert = UsuarioPesquisaContract.buildInsereSinc();
                context.getContentResolver().insert(uriInsert, usuarioPesquisa.getContentValues());
            }
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
