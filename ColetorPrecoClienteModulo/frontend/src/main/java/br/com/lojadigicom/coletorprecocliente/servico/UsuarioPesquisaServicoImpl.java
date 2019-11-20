package br.com.lojadigicom.coletorprecocliente.servico;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import br.com.lojadigicom.coletorprecocliente.data.contract.InteresseProdutoContract;
import br.com.lojadigicom.coletorprecocliente.data.contract.UsuarioContract;
import br.com.lojadigicom.coletorprecocliente.data.contract.UsuarioPesquisaContract;
import br.com.lojadigicom.coletorprecocliente.faturamento.TipoBilling;
import br.com.lojadigicom.coletorprecocliente.framework.faturamento.ConstantesBilling;
import br.com.lojadigicom.coletorprecocliente.framework.dao.MontadorDaoI;
import br.com.lojadigicom.coletorprecocliente.modelo.Usuario;
import br.com.lojadigicom.coletorprecocliente.modelo.UsuarioPesquisa;
import br.com.lojadigicom.coletorprecocliente.modelo.UsuarioPesquisaVo;
import br.com.lojadigicom.coletorprecocliente.modelo.montador.UsuarioMontador;
import br.com.lojadigicom.coletorprecocliente.util.Inventory;



/**
 * Created by Paulo on 24/02/16.
 */

// Em alguma momento passar isso para template entre 23-01-2017 ate 23-02-2017
public class UsuarioPesquisaServicoImpl extends UsuarioPesquisaServicoApp implements TipoBilling {
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

    private int qtdeMonitora;
    private int qtdeEspera;
    private Usuario usuario;

    public void verificaLimites(Context context) {
        Uri uri = InteresseProdutoContract.buildQuantidadeMonitorado();
        Cursor data = context.getContentResolver().query(uri,null,null,null,null);
        if (data.moveToFirst()) {
            qtdeMonitora = data.getInt(data.getColumnIndex("qtde_monitora"));
            qtdeEspera = data.getInt(data.getColumnIndex("qtde_espera"));
        }
        usuario = getUsuario(context);
    }
    public int getQtdeMonitora() {
        return qtdeMonitora;
    }
    public int getQtdeEspera() {
        return qtdeEspera;
    }

    public int getLimiteEsperaAtual(ConstantesBilling billing) {
        if (usuario.getPlano03()) {
            return billing.getLimitePlano03(TIPO_ESPERA);
        }
        if (usuario.getPlano02()) {
            return billing.getLimitePlano02(TIPO_ESPERA);
        }
        if (usuario.getPlano01()) {
            return billing.getLimitePlano01(TIPO_ESPERA);
        }
        return billing.getLimiteGratis(TIPO_ESPERA);
    }
    public int getLimiteMonitoraAtual(ConstantesBilling billing) {
        if (usuario.getPlano03()) {
            return billing.getLimitePlano03(TIPO_MONITORA);
        }
        if (usuario.getPlano02()) {
            return billing.getLimitePlano02(TIPO_MONITORA);
        }
        if (usuario.getPlano01()) {
            return billing.getLimitePlano01(TIPO_MONITORA);
        }
        return billing.getLimiteGratis(TIPO_MONITORA);
    }

    public boolean permiteMonitorar(ConstantesBilling billing) {
        if (usuario.getPlano03()) {
            return (qtdeMonitora<billing.getLimitePlano03(TIPO_MONITORA));
        }
        if (usuario.getPlano02()) {
            return (qtdeMonitora<billing.getLimitePlano02(TIPO_MONITORA));
        }
        if (usuario.getPlano01()) {
            return (qtdeMonitora<billing.getLimitePlano01(TIPO_MONITORA));
        }
        return (qtdeMonitora<billing.getLimiteGratis(TIPO_MONITORA));
    }
    public boolean permiteEsperar(ConstantesBilling billing) {
        if (usuario.getPlano03()) {
            return (qtdeEspera<billing.getLimitePlano03(TIPO_ESPERA));
        }
        if (usuario.getPlano02()) {
            return (qtdeEspera<billing.getLimitePlano02(TIPO_ESPERA));
        }
        if (usuario.getPlano01()) {
            return (qtdeEspera<billing.getLimitePlano01(TIPO_ESPERA));
        }
        return (qtdeEspera< billing.getLimiteGratis(TIPO_ESPERA));
    }

    public void atualizaUsuario(Context ctx, Inventory inventory,ConstantesBilling billing) {
        Usuario usuario = getUsuario(ctx);
        usuario.setPlano01(inventory.hasPurchase(billing.getSkuPlano01()));
        usuario.setPlano02(inventory.hasPurchase(billing.getSkuPlano02()));
        usuario.setPlano03(inventory.hasPurchase(billing.getSkuPlano03()));
        Uri uri = UsuarioContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uri,usuario.getContentValues(),null,null);
    }



}
