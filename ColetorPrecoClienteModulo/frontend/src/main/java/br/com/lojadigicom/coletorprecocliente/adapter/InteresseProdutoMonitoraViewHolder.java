package br.com.lojadigicom.coletorprecocliente.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import br.com.lojadigicom.coletorprecocliente.R;
import br.com.lojadigicom.coletorprecocliente.template.lista.InteresseProdutoListViewHolderBase;

/**
 * Created by Paulo on 15/11/2016.
 */

public class InteresseProdutoMonitoraViewHolder extends InteresseProdutoListViewHolderBase{

    public TextView txtNomeProduto;
    public TextView txtPreco;
    public TextView txtLoja;
    public ImageView imgProduto;
    public RatingBar rtbRecomendacao;
    public TextView txtNovo;
    public ImageView imgPrecoBaixo;
    public ImageView imgPrecoAlto;
    public ImageView imgMudanca;

    public InteresseProdutoMonitoraViewHolder(View itemView) {
        super(itemView);
        txtLoja = getTextView(itemView,R.id.txtLoja,"R.id.txtLoja");
        txtNomeProduto = getTextView(itemView,R.id.txtNomeProduto,"R.id.txtNomeProduto");
        txtPreco = getTextView(itemView,R.id.txtPreco,"R.id.txtPreco");
        imgProduto = getImageView(itemView,R.id.imgProduto,"R.id.imgProduto");
        rtbRecomendacao = getRatingBar(itemView,R.id.rtbRecomendacao,"R.id.rtbRecomendacao");
        txtNovo = getTextView(itemView,R.id.txtNovo,"R.id.txtNovo");
        imgPrecoBaixo = getImageView(itemView,R.id.imgPrecoBaixo,"R.id.imgPrecoBaixo");
        imgPrecoAlto = getImageView(itemView,R.id.imgPrecoAlto,"R.id.imgPrecoAlto");
        imgMudanca = getImageView(itemView,R.id.imgMudanca,"R.id.imgMudanca");

    }





}
