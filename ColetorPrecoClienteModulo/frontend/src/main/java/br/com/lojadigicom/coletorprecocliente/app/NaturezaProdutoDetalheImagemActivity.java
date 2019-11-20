package br.com.lojadigicom.coletorprecocliente.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import br.com.lojadigicom.coletorprecocliente.R;
import br.com.lojadigicom.coletorprecocliente.adapter.OportunidadeDiaListImagemViewHolder;
import br.com.lojadigicom.coletorprecocliente.adapter.OportunidadeDiaListViewHolder;
import br.com.lojadigicom.coletorprecocliente.data.contract.NaturezaProdutoContract;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.framework.tela.ResourceObj;
import br.com.lojadigicom.coletorprecocliente.modelo.NaturezaProduto;
import br.com.lojadigicom.coletorprecocliente.tela.base.activity.Constantes;
import br.com.lojadigicom.coletorprecocliente.tela.base.activity.detalhe.NaturezaProdutoDetalheBaseActivity;
import br.com.lojadigicom.coletorprecocliente.template.lista.OportunidadeDiaListFragmentBase;
import br.com.lojadigicom.coletorprecocliente.template.lista.OportunidadeDiaListViewHolderBase;

public class NaturezaProdutoDetalheImagemActivity extends NaturezaProdutoDetalheBaseActivity {



    private TextView txtNomeNaturezaDetalhe = null;


    // Pensei em colocar isso na arquiteetura mas uma
    // activity de detalhe pode querer retornar para outra com animação.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onLoadObject(NaturezaProduto item) {

    }

    @Override
    protected void preparaTransicao() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            /*// Definindo a transição
            Explode trans1 = new Explode();
            trans1.setDuration(3000); // 3 segs
            Fade trans2 = new Fade();
            trans2.setDuration(3000);


            // Activity inicial da transição.
            getWindow().setExitTransition(trans1);
            getWindow().setReenterTransition(trans2);*/
            TransitionInflater inflater = TransitionInflater.from(this);
            Transition transition = inflater.inflateTransition(R.transition.transitions);
            getWindow().setSharedElementExitTransition(transition);
        }
    }

//    @Override
//    protected Uri getUri(long id) {
//        return NaturezaProdutoContract.buildNaturezaProdutoUri(id);
//    }


    @Override
    protected int getConstColunaNomeTitulo() {
        return NaturezaProdutoContract.COL_NOME_NATUREZA_PRODUTO;
    }

    @Override
    protected void complementaOnCreate(long idNaturezaProduto) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //txtNomeNaturezaDetalhe = (TextView)this.findViewById(R.id.txtNomeNaturezaDetalhe);
        OportunidadeDiaListFragmentBase novoQuadro = (OportunidadeDiaListFragmentBase) getSupportFragmentManager().findFragmentById(R.id.oportunidade_dia_list_fragment);
        novoQuadro.setIdNaturezaProduto(idNaturezaProduto);
    }



    @Override
    protected ResourceObj getLayoutDetalheResource() {
        return new ResourceObj(R.layout.activity_natureza_produto_detalhe_imagem,"R.layout.activity_natureza_produto_detalhe_imagem");
    }

    @Override
    protected int getIdToolbar() {
        return R.id.toolbar_natureza_detalhe;
    }


    @Override
    public void onTransitionEnd(Transition transition) {
    }
    @Override
    protected void esconderElementosParaPosAnimacao() {
    }
}
