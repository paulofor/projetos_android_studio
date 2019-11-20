package br.com.lojadigicom.coletorprecocliente.app;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;

import android.view.View;
import android.widget.TextView;

import br.com.lojadigicom.coletorprecocliente.R;
import br.com.lojadigicom.coletorprecocliente.adapter.OportunidadeDiaListViewHolder;
import br.com.lojadigicom.coletorprecocliente.data.contract.NaturezaProdutoContract;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.framework.tela.ResourceObj;
import br.com.lojadigicom.coletorprecocliente.modelo.NaturezaProduto;
import br.com.lojadigicom.coletorprecocliente.tela.base.activity.Constantes;
import br.com.lojadigicom.coletorprecocliente.tela.base.activity.NaturezaProdutoDetalheBaseActivity;
import br.com.lojadigicom.coletorprecocliente.template.lista.OportunidadeDiaListFragmentBase;
import br.com.lojadigicom.coletorprecocliente.template.lista.OportunidadeDiaListViewHolderBase;

public class NaturezaProdutoDetalheActivity extends NaturezaProdutoDetalheBaseActivity implements OportunidadeDiaListFragmentBase.Callback{


    private TextView txtNomeNaturezaDetalhe = null;

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


    @Override
    protected int getConstColunaNomeTitulo() {
        return NaturezaProdutoContract.COL_NOME_NATUREZA_PRODUTO;
    }

    @Override
    protected void complementaOnCreate(long idNaturezaProduto) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //txtNomeNaturezaDetalhe = (TextView)this.findViewById(R.id.txtNomeNaturezaDetalhe);
        OportunidadeDiaListFragment novoQuadro = (OportunidadeDiaListFragment) getSupportFragmentManager().findFragmentById(R.id.oportunidade_dia_list_fragment);
        novoQuadro.setIdNaturezaProduto(idNaturezaProduto);
    }

    @Override
    protected ResourceObj getLayoutDetalheResource() {
        return new ResourceObj(R.layout.activity_natureza_produto_detalhe,"R.layout.activity_natureza_produto_detalhe");
    }

    @Override
    protected int getIdToolbar() {
        return R.id.toolbar_natureza_detalhe;
    }

    @Override
    public void onItemSelected(int idItemLista, OportunidadeDiaListViewHolderBase vh) {
        Intent intent = new Intent(getApplicationContext(), OportunidadeDiaDetalheActivity.class);
        DCLog.d("OnClick", this, "Item selecionado ID:" + idItemLista);
        intent.putExtra(Constantes.OPORTUNIDADE_DIA_ID,idItemLista);
        if (idItemLista==0) {
            throw new RuntimeException("Id igual a zero");
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            OportunidadeDiaListViewHolder holder = (OportunidadeDiaListViewHolder) vh;
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                    Pair.create((View)holder.mNomeProduto, "trNomeProduto"),
                    Pair.create((View)holder.mPrecoAtual, "trPrecoAtual"));
            this.startActivity(intent,options.toBundle());
        } else {
            this.startActivity(intent);
        }

    }
}
