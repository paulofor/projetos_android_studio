package br.com.lojadigicom.coletorprecocliente.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import br.com.lojadigicom.coletorprecocliente.R;
import br.com.lojadigicom.coletorprecocliente.framework.tela.ResourceObj;
import br.com.lojadigicom.coletorprecocliente.framework.util.UtilDatas;
import br.com.lojadigicom.coletorprecocliente.modelo.OportunidadeDia;
import br.com.lojadigicom.coletorprecocliente.template.lista.OportunidadeDiaListAdapterBase;
import br.com.lojadigicom.coletorprecocliente.template.lista.OportunidadeDiaListViewHolderBase;

/**
 * Created by Paulo on 15/01/16.
 */
public class OportunidadeDiaListImagemAdapter extends OportunidadeDiaListAdapterBase {

    private Context context;

    public OportunidadeDiaListImagemAdapter(Context context, OportunidadeDiaListAdapterOnClickHandler dh, View emptyView, int choiceMode) {
        super(context, dh, emptyView, choiceMode);
        this.context = context;
    }

    @Override
    protected ResourceObj getLayoutItemResource() {
        return new ResourceObj(R.layout.item_oportunidade_dia_imagem,"R.layout.item_oportunidade_dia_imagem");
    }

    @Override
    protected OportunidadeDiaListViewHolderBase getViewHolder(View v) {
        return new OportunidadeDiaListImagemViewHolder(v);
    }

    @Override
    public void onBindViewHolderDC(OportunidadeDia oportunidadeDia, OportunidadeDiaListViewHolderBase holder, int position) {
        OportunidadeDiaListImagemViewHolder hold = (OportunidadeDiaListImagemViewHolder) holder;
        //mCursor.moveToPosition(position);
        hold.mNomeProduto.setText(oportunidadeDia.getNomeProduto());
        hold.mNomeLoja.setText(oportunidadeDia.getNomeLojaVirtual());
        String data = UtilDatas.getDataDeslocada(1, oportunidadeDia.getDataUltimaPrecoAnterior());
        hold.mDataMudanca.setText("Desde " + data);
        hold.mPrecoAtual.setText(oportunidadeDia.getPrecoVendaAtualTela());
        //setImagemFromUrl(hold.mImgemProduto, oportunidadeDia.getUrlImagem());

        carregaImagemCache(oportunidadeDia.getUrlImagem(),hold.mImgemProduto);
    }


}
