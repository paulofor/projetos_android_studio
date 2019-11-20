package br.com.lojadigicom.coletorprecocliente.servico;

import android.content.Context;

import br.com.lojadigicom.coletorprecocliente.servico.filtro.IFiltro;
import br.com.lojadigicom.coletorprecocliente.servico.filtro.UsuarioPesquisaFiltro;

/**
 * Created by Paulo on 24/02/16.
 */
public abstract class UsuarioPesquisaServico implements IServico{

    public static final int OP_INCLUI_ITEM = 1;
    public static final int OP_EXCLUI_ITEM = 2;

    private UsuarioPesquisaFiltro filtro = null;

    public void setFiltro(IFiltro val) {
        filtro = (UsuarioPesquisaFiltro) val;
    }

    public UsuarioPesquisaFiltro getFiltro() {
        return filtro;
    }

    public abstract void ExcluiItem(Context context);

    public abstract void IncluiItem(Context context);
}
