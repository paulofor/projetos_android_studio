package br.com.lojadigicom.coletorprecocliente.app;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.transition.Transition;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import br.com.lojadigicom.coletorprecocliente.R;
import br.com.lojadigicom.coletorprecocliente.data.contract.InteresseProdutoContract;
import br.com.lojadigicom.coletorprecocliente.data.contract.PrecoDiarioClienteContract;
import br.com.lojadigicom.coletorprecocliente.framework.dao.MontadorDaoI;
import br.com.lojadigicom.coletorprecocliente.framework.data.MontadorDaoComposite;
import br.com.lojadigicom.coletorprecocliente.framework.tela.DCAplicacao;
import br.com.lojadigicom.coletorprecocliente.framework.tela.ResourceObj;
import br.com.lojadigicom.coletorprecocliente.framework.util.UtilDatas;
import br.com.lojadigicom.coletorprecocliente.modelo.CompartilhamentoProduto;
import br.com.lojadigicom.coletorprecocliente.modelo.CompartilhamentoProdutoVo;
import br.com.lojadigicom.coletorprecocliente.modelo.InteresseProduto;
import br.com.lojadigicom.coletorprecocliente.modelo.ProdutoCliente;

import br.com.lojadigicom.coletorprecocliente.servico.FabricaServicoApp;
import br.com.lojadigicom.coletorprecocliente.servico.InteresseProdutoServicoApp;
import br.com.lojadigicom.coletorprecocliente.servico.base.CompartilhamentoProdutoServico;
import br.com.lojadigicom.coletorprecocliente.servico.base.FabricaServico;
import br.com.lojadigicom.coletorprecocliente.servico.base.InteresseProdutoServico;
import br.com.lojadigicom.coletorprecocliente.tela.base.activity.Constantes;
import br.com.lojadigicom.coletorprecocliente.tela.base.activity.detalhe.InteresseProdutoExibeItemListaBaseActivity;


public class ListaMonitoramentoDetalheActivity extends InteresseProdutoExibeItemListaBaseActivity implements View.OnClickListener{

    private FloatingActionButton btnFacebook = null;
    private FloatingActionButton btnLoja = null;
    private FloatingActionButton btnGrafico = null;

    private ShareDialog shareDialog;
    private CallbackManager callbackManager;


    private ImageView imgProdutoEspera;
    private TextView txtNomeProdutoEscolha;
    private TextView txtPrecoProdutoEscolha;
    private RatingBar rtbRecomendacao;
    private TextView txtNomeLoja;
    private TextView txtDescriacaoRecomendacao;
    private TextView txtDescriacaoRecomendacao2;
    private TextView txtNovo;

    private Switch btnEspera;
    private Switch btnMonitora;
    // Passar para arquitetura ? (30-11-2016)
    private InteresseProduto item;
    private InteresseProdutoServicoApp srvInteresse = FabricaServicoApp.getInstancia().getInteresseProdutoServico();

    private CompartilhamentoProdutoServico compartilhamentoProdutoServico = FabricaServico.getInstancia().getCompartilhamentoProdutoServico();

    private final int BTN_ESPERA = 1;
    private final int BTN_MONITORA = 2;






    @Override
    protected void complementaOnCreate(long idInteresseProduto) {
        imgProdutoEspera = getImageView(R.id.imgProduto,"imgProduto");
        txtNomeProdutoEscolha = getTextView(R.id.txtNomeProduto,"txtNomeProduto");
        txtPrecoProdutoEscolha = getTextView(R.id.txtPrecoProduto,"txtPrecoProduto");
        txtNomeLoja = getTextView(R.id.txtNomeLoja,"txtNomeLoja");
        rtbRecomendacao = getRatingBar(R.id.rtbRecomendacao,"rtbRecomendacao");

        txtDescriacaoRecomendacao = getTextView(R.id.txtDescriacaoRecomendacao,"txtDescriacaoRecomendacao");
        txtDescriacaoRecomendacao2 = getTextView(R.id.txtDescriacaoRecomendacao2,"txtDescriacaoRecomendacao2");
        //this.criaBotaoBack();
        btnEspera = (Switch) this.findViewById(R.id.btnEspera);
        btnMonitora = (Switch) this.findViewById(R.id.btnMonitora);
        //this.criaBotaoBack();
        btnEspera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaComando(BTN_ESPERA);
            }
        });
        btnMonitora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaComando(BTN_MONITORA);
            }
        });

        this.btnFacebook = (FloatingActionButton) findViewById(R.id.btnFacebook);
        this.btnLoja = (FloatingActionButton) findViewById(R.id.btnLoja);
        this.btnGrafico = (FloatingActionButton) findViewById(R.id.btnGrafico);

        txtNovo = getTextView(R.id.txtNovo,"txtNovo");

        preparaFacebook();


    }

    private void chamaComando(int tipo) {

        if (tipo == BTN_MONITORA && btnMonitora.isChecked()) {
            btnEspera.setChecked(false);
        }
        if (tipo == BTN_ESPERA && btnEspera.isChecked()) {
            btnMonitora.setChecked(false);
        }

        item.setEspera(btnEspera.isChecked());
        item.setMonitora(btnMonitora.isChecked());
        item.setData(UtilDatas.getDataHora());
        srvInteresse.insereAtualiza(item, this);
    }




    @Override
    protected ResourceObj getLayoutDetalheResource() {
        return new ResourceObj(R.layout.activity_lista_monitoramento_detalhe_v3,"R.layout.activity_lista_monitoramento_detalhe_v3");
    }

    //@Override
    //protected Uri getUri(long id) {
    //    return InteresseProdutoContract.buildInteresseProdutoUri(id);
    //}


    @Override
    protected Uri completaUri(Uri uri) {
        uri = uri.buildUpon().appendPath(InteresseProdutoContract.COM_COMPLEMENTO).build();
        uri = InteresseProdutoContract.adicionaProdutoClienteReferenteA(uri);
        return uri;
    }

    @Override
    protected MontadorDaoI completaMontador(MontadorDaoComposite montadorDao) {
        return InteresseProdutoContract.adicionaMontadorProdutoClienteReferenteA(montadorDao);
    }

    @Override
    protected void onLoadObject(InteresseProduto item) {
        this.item = item;
        ProdutoCliente produto = item.getProdutoCliente_ReferenteA();
        txtNomeProdutoEscolha.setText(produto.getNome());
        //this.imagemFromUrl(this.imgProdutoEspera,item.getProdutoCliente_ReferenteA().getImagem());
        this.carregaImagemCache(item.getProdutoCliente_ReferenteA().getImagem(),this.imgProdutoEspera);
        String textoPreco =  "R$ " + item.getValorTela();
        txtPrecoProdutoEscolha.setText(textoPreco);

        txtNomeLoja.setText(produto.getLoja());
        if (item.getNovo()) {
            rtbRecomendacao.setVisibility(View.INVISIBLE);
            txtNovo.setVisibility(View.VISIBLE);
            //btnFacebook.setVisibility(View.GONE);
            //btnGrafico.setVisibility(View.GONE);
        } else {
            rtbRecomendacao.setVisibility(View.VISIBLE);
            txtNovo.setVisibility(View.INVISIBLE);
            rtbRecomendacao.setRating(item.getNota());
            //btnFacebook.setVisibility(View.VISIBLE);
            //btnGrafico.setVisibility(View.VISIBLE);
       }

       //Fazer depois
       //Uri uri = PrecoDiarioClienteContract.buildQuantidadePorProduto();

       txtDescriacaoRecomendacao.setText(getMensagemRating(item));
       txtDescriacaoRecomendacao2.setText(getMensagemRating2(item));


        btnEspera.setChecked(item.getEspera());
        btnMonitora.setChecked(item.getMonitora());

        btnFacebook.setOnClickListener(this);
        btnLoja.setOnClickListener(this);
        btnGrafico.setOnClickListener(this);
    }

    private String getMensagemRating(InteresseProduto interesse) {
        if (interesse.getNovo()) return "";
        return "Médio: R$ " + interesse.getPrecoMedioTela();

    }
    private String getMensagemRating2(InteresseProduto interesse) {
        if (interesse.getNovo()) return "";
        return "Mínimo: R$ " + interesse.getPrecoMinimoTela();
    }

    @Override
    protected int getConstColunaNomeTitulo() {
        return 0;
    }



    @Override
    protected int getIdToolbar() {
        return R.id.toolbar_detalhe_produto;
    }


    public void enviaFacebookLink() {
        ProdutoCliente produto = item.getProdutoCliente_ReferenteA();
        DCAplicacao app = (DCAplicacao) this.getApplication();


        //String url = "https://play.google.com/store/apps/details?id=br.com.lojadigicom.olhonoprecosapato";
        String url = app.getLinkFacebook();
        if (shareDialog.canShow(ShareLinkContent.class)) {
            //String mensagem = "De Olho no Preço encontrou !    De: R$ " + item.getPrecoVendaAnteriorTela() +
            //        " Por: R$ " + item.getPrecoVendaAtualTela();
            String descricao = item.getProdutoCliente_ReferenteA().getNome() + " - Preço Atual: R$ " + item.getValorTela() +
                    " - Preço Médio: R$ " + item.getPrecoMedioTela() +
                    " - Loja: " + item.getCorrenteProdutoCliente_ReferenteA().getLoja() + " ";

            String titulo = "Encontrado por: Olho no Preço - Eletrônicos";


            ShareLinkContent content = new ShareLinkContent.Builder()
                    .setContentTitle(descricao)
                    .setContentDescription(titulo)
                    .setContentUrl(Uri.parse(url))
                    .setImageUrl(Uri.parse(item.getProdutoCliente_ReferenteA().getImagem()))
                    .build();
            ShareDialog.show(this, content);
            //registraCompartilhamento();
        }
    }

    private void registraCompartilhamento() {
        CompartilhamentoProduto compartilhamentoProduto = new CompartilhamentoProdutoVo();
        compartilhamentoProduto.setDataHora(UtilDatas.getDataHora());
        compartilhamentoProduto.setIdProdutoRa(item.getProdutoCliente_ReferenteA().getIdObj());
        compartilhamentoProduto.setIdUsuarioS(item.getIdUsuarioS());
        compartilhamentoProdutoServico.insere(compartilhamentoProduto,this);
    }


    protected void preparaFacebook() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                Log.d("Facebook", "success");
            }
            @Override
            public void onCancel() {
                Log.e("Facebook", "cancel");
            }
            @Override
            public void onError(FacebookException e) {
                Log.e("Facebook", "error");
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btnFacebook)  {
            enviaFacebookLink();
        }

        if (v.getId()==R.id.btnLoja)  {
            String url = item.getProdutoCliente_ReferenteA().getUrl();
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }

        if (v.getId()==R.id.btnGrafico)  {
            Intent mIntent = new Intent(this, GraficoPrecoActivity.class);
            mIntent.putExtra(Constantes.PRODUTO_CLIENTE_ID, item.getProdutoCliente_ReferenteA().getIdObj());
            startActivity(mIntent);
        }
       /* switch (v.getId()) {
            case R.id.btnFacebook :
                enviaFacebookLink();
                break;
            case R.id.btnLoja :
                String url = null;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;
        }*/
    }

    public void enviaFacebookFoto() {

        if (shareDialog.canShow(SharePhotoContent.class)) {

            imgProdutoEspera.buildDrawingCache();
            Bitmap bmap = imgProdutoEspera.getDrawingCache();

            String mensagem = "Preço Médio: XXXX" +" \n" +
                    " Preço Atual: xxxxxx";
            SharePhoto photo = new SharePhoto.Builder()
                    .setBitmap(bmap)
                    .build();
            SharePhotoContent content = new SharePhotoContent.Builder()
                    .addPhoto(photo)
                    .build();

            shareDialog.show(content);
        }
    }


    public  Bitmap getBitmapFromURL(String srcurl){
        try {
            URL url = new URL(srcurl);
            HttpURLConnection connection=(HttpURLConnection)url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input=connection.getInputStream();
            Bitmap myBitmap= BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void onTransitionEnd(Transition transition) {
        if (!item.getNovo()) {
            this.btnFacebook.setVisibility(View.VISIBLE);
            this.btnGrafico.setVisibility(View.VISIBLE);
        } else {
            this.btnFacebook.setVisibility(View.GONE);
            this.btnGrafico.setVisibility(View.GONE);
        }
        this.btnLoja.setVisibility(View.VISIBLE);
    }
    @Override
    protected void esconderElementosParaPosAnimacao() {
        this.btnFacebook.setVisibility(View.INVISIBLE);
        this.btnGrafico.setVisibility(View.INVISIBLE);
        this.btnLoja.setVisibility(View.INVISIBLE);
    }
}
