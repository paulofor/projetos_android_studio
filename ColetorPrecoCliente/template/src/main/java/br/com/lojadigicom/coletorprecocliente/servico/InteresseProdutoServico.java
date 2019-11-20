package br.com.lojadigicom.coletorprecocliente.servico;

import android.content.Context;

import br.com.lojadigicom.coletorprecocliente.servico.filtro.IFiltro;
import br.com.lojadigicom.coletorprecocliente.servico.filtro.InteresseProdutoFiltro;
import br.com.lojadigicom.coletorprecocliente.servico.filtro.UsuarioPesquisaFiltro;

/**
 * Created by Paulo on 23/04/2016.
 */
public abstract class InteresseProdutoServico implements IServico{

    public static final int OP_INCLUI_ITEM = 1;
    public static final int OP_EXCLUI_ITEM = 2;

    private InteresseProdutoFiltro filtro = null;

    public void setFiltro(IFiltro val) {
        filtro = (InteresseProdutoFiltro) val;
    }

    public InteresseProdutoFiltro getFiltro() {
        return filtro;
    }

    public abstract void ExcluiItem(Context context);

    public abstract void IncluiItem(Context context);
}
