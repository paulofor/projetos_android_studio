package br.com.lojadigicom.coletorprecocliente.app;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import br.com.lojadigicom.coletorprecocliente.R;
import br.com.lojadigicom.coletorprecocliente.data.contract.UsuarioContract;
import br.com.lojadigicom.coletorprecocliente.data.contract.UsuarioPesquisaContract;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.modelo.NaturezaProduto;
import br.com.lojadigicom.coletorprecocliente.modelo.UsuarioPesquisa;
import br.com.lojadigicom.coletorprecocliente.modelo.UsuarioPesquisaVo;
import br.com.lojadigicom.coletorprecocliente.servico.FabricaServico;
import br.com.lojadigicom.coletorprecocliente.servico.ParamServico;
import br.com.lojadigicom.coletorprecocliente.servico.UsuarioPesquisaServico;
import br.com.lojadigicom.coletorprecocliente.servico.filtro.UsuarioPesquisaFiltro;
import br.com.lojadigicom.coletorprecocliente.servico.sync.UsuarioPesquisaAsyncTask;
import br.com.lojadigicom.coletorprecocliente.template.lista.NaturezaProdutoListFragmentBase;
import br.com.lojadigicom.coletorprecocliente.template.lista.NaturezaProdutoListViewHolderBase;
import br.com.lojadigicom.coletorprecocliente.adapter.NaturezaProdutoListEdicaoViewHolder;

public class NaturezaProdutoListaEscolhaAcvtivity extends AppCompatActivity implements  NaturezaProdutoListFragmentBase.Callback {


    private TextView txtAvisoAlteracao = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_natureza_produto_lista_escolha_acvtivity);
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar_escolha_natureza);
        txtAvisoAlteracao = (TextView) this.findViewById(R.id.txtAvisoAlteracao);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onItemSelected(int idItemLista, NaturezaProdutoListViewHolderBase vh) {
        UsuarioPesquisaFiltro filtro = new UsuarioPesquisaFiltro();
        txtAvisoAlteracao.setVisibility(View.VISIBLE);
        ParamServico param = new ParamServico(this, FabricaServico.getInstancia().getUsuarioPesquisaServico());
        NaturezaProdutoListEdicaoViewHolder hold = (NaturezaProdutoListEdicaoViewHolder) vh;
        filtro.setNatureza(vh.getItem());
        UsuarioPesquisaAsyncTask task = new UsuarioPesquisaAsyncTask();
        if (hold.isChecked()) {
            param.setIdOperacao(UsuarioPesquisaServico.OP_INCLUI_ITEM);
        } else {
            param.setIdOperacao(UsuarioPesquisaServico.OP_EXCLUI_ITEM);
        }
        param.setFiltro(filtro);
        task.execute(param);
        DCLog.d("Diversos",this , "ID:" + idItemLista);
    }
}
