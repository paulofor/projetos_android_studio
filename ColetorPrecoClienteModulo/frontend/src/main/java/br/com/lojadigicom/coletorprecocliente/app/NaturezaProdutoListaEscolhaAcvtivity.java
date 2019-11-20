package br.com.lojadigicom.coletorprecocliente.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import br.com.lojadigicom.coletorprecocliente.R;

public class NaturezaProdutoListaEscolhaAcvtivity extends AppCompatActivity {

    public static String CHAVE_TOOLBAR = "titulo";

    //private TextView txtAvisoAlteracao = null;
    private TextView txtTituloToolbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_natureza_produto_lista_escolha_acvtivity);
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar_escolha_natureza);
        //txtAvisoAlteracao = (TextView) this.findViewById(R.id.txtAvisoAlteracao);
        txtTituloToolbar = (TextView) this.findViewById(R.id.toolbar_oportunidade_dia_texto);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        String titulo = bundle.getString(CHAVE_TOOLBAR);
        if (titulo!=null) {
            txtTituloToolbar.setText(titulo);
        }
    }
/*
    @Override
    public void onItemSelected(int idItemLista, NaturezaProdutoListViewHolderBase vh) {
        UsuarioPesquisaFiltro filtro = new UsuarioPesquisaFiltro();
        //txtAvisoAlteracao.setVisibility(View.VISIBLE);
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
    }*/
}
