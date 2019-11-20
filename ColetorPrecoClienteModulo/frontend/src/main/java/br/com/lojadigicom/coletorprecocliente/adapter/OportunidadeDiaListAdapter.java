package br.com.lojadigicom.coletorprecocliente.adapter;

import android.content.Context;
import android.view.View;

import br.com.lojadigicom.coletorprecocliente.R;
import br.com.lojadigicom.coletorprecocliente.framework.tela.ResourceObj;
import br.com.lojadigicom.coletorprecocliente.framework.util.UtilDatas;
import br.com.lojadigicom.coletorprecocliente.modelo.NaturezaProduto;
import br.com.lojadigicom.coletorprecocliente.modelo.OportunidadeDia;
import br.com.lojadigicom.coletorprecocliente.template.lista.NaturezaProdutoListAdapterBase;
import br.com.lojadigicom.coletorprecocliente.template.lista.OportunidadeDiaListAdapterBase;
import br.com.lojadigicom.coletorprecocliente.template.lista.OportunidadeDiaListViewHolderBase;

/**
 * Created by Paulo on 15/01/16.
 */
public class OportunidadeDiaListAdapter extends OportunidadeDiaListAdapterBase {

    public OportunidadeDiaListAdapter(Context context, OportunidadeDiaListAdapterOnClickHandler dh, View emptyView, int choiceMode) {
        super(context, dh, emptyView, choiceMode);
    }

    @Override
    protected ResourceObj getLayoutItemResource() {
        return new ResourceObj(R.layout.item_oportunidade_dia,"R.layout.item_oportunidade_dia");
    }


    @Override
    protected OportunidadeDiaListViewHolderBase getViewHolder(View v) {
        return new OportunidadeDiaListViewHolder(v);
    }

    @Override
    public void onBindViewHolderDC(OportunidadeDia oportunidadeDia, OportunidadeDiaListViewHolderBase holder, int position) {
        OportunidadeDiaListViewHolder hold = (OportunidadeDiaListViewHolder) holder;
        //mCursor.moveToPosition(position);
        hold.mNomeProduto.setText(oportunidadeDia.getNomeProduto());
        hold.mNomeLoja.setText(oportunidadeDia.getNomeLojaVirtual());
        String data = UtilDatas.getDataDeslocada(1, oportunidadeDia.getDataUltimaPrecoAnterior());
        hold.mDataMudanca.setText("Desde " + data);
        hold.mPrecoMinimo.setText(oportunidadeDia.getPrecoMinimoTela());
        hold.mPrecoMedio.setText(oportunidadeDia.getPrecoMedioTela());
        hold.mPrecoAtual.setText(oportunidadeDia.getPrecoVendaAtualTela());
        hold.mPrecoAnterior.setText(oportunidadeDia.getPrecoVendaAnteriorTela());
    }
}
