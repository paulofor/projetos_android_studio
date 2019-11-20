package br.com.lojadigicom.coletorprecocliente.servico;

import android.content.Context;
import android.net.Uri;
import android.view.ContextThemeWrapper;

import java.sql.Timestamp;

import br.com.lojadigicom.coletorprecocliente.data.contract.InteresseProdutoContract;
import br.com.lojadigicom.coletorprecocliente.modelo.InteresseProduto;
import br.com.lojadigicom.coletorprecocliente.modelo.ProdutoCliente;
import br.com.lojadigicom.coletorprecocliente.servico.filtro.IFiltro;
import br.com.lojadigicom.coletorprecocliente.servico.filtro.InteresseProdutoFiltro;
import br.com.lojadigicom.coletorprecocliente.servico.filtro.UsuarioPesquisaFiltro;

/**
 * Created by Paulo on 23/04/2016.
 */
public abstract class InteresseProdutoServicoApp implements IServico{

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

    // Operação
    public abstract InteresseProduto criaParaProduto(ProdutoCliente produto, Context context);

    // Essa parte debaixo depois de amadureciento pode ser passada para template
    // hoje: 05-11-2016 ( contar 2 meses -> 05-01-2017 )
    public void insereAtualiza(InteresseProduto interesse, Context context) {
        if (interesse.getIdObj()==0) {
            insere(interesse,context);
        } else {
            atualiza(interesse,context);
        }
    }
    public void insere(InteresseProduto interesse, Context ctx) {
        Uri uriInsert = InteresseProdutoContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, interesse.getContentValues());
    }
    public void atualiza(InteresseProduto interesse, Context ctx) {
        Uri uriUpdate = InteresseProdutoContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,interesse.getContentValues(),null,null);
    }


}
