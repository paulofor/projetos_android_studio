package br.com.lojadigicom.coletorprecocliente.app;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;

import android.support.design.widget.FloatingActionButton;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
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
import java.net.MalformedURLException;
import java.net.URL;


import br.com.lojadigicom.coletorprecocliente.R;
import br.com.lojadigicom.coletorprecocliente.data.contract.OportunidadeDiaContract;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.framework.tela.ResourceObj;
import br.com.lojadigicom.coletorprecocliente.modelo.OportunidadeDia;
import br.com.lojadigicom.coletorprecocliente.tela.base.activity.Constantes;
import br.com.lojadigicom.coletorprecocliente.tela.base.activity.detalhe.OportunidadeDiaDetalheBaseActivity;
//import uk.co.senab.photoview.PhotoViewAttacher;

public class OportunidadeDiaDetalheActivity extends OportunidadeDiaDetalheBaseActivity implements View.OnClickListener{

    private ImageView mImagemProduto = null;
    private TextView mTitulo = null;
    private TextView mPrecoProduto = null;
    private ViewGroup lytImagem = null;
    private ViewGroup lytBotoes = null;

    private FloatingActionButton btnFacebook = null;
    private FloatingActionButton btnLoja = null;

    private OportunidadeDia item = null;
    //private PhotoViewAttacher mAttacher;

    private ShareDialog shareDialog;
    private CallbackManager callbackManager;


    protected void preparaFacebook() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {

            @Override
            public void onSuccess(Sharer.Result result) {
                Log.d("FCBK", "success");
            }

            @Override
            public void onCancel() {

                Log.e("FCBK", "cancel");
            }

            @Override
            public void onError(FacebookException e) {
                //e.printStackTrace();
                Log.e("FCBK", "error");
            }

        });
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
            getWindow().setEnterTransition(trans1);
            getWindow().setReturnTransition(trans2);*/
            TransitionInflater inflater = TransitionInflater.from(this);
            Transition transition = inflater.inflateTransition(R.transition.transitions);
            getWindow().setSharedElementEnterTransition(transition);
            //getWindow().setSharedElementEnterTransition(new ChangeBounds());

            Transition transition1 = getWindow().getSharedElementEnterTransition();
            transition1.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {

                }

                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onTransitionEnd(Transition transition) {
                    //TransitionManager.beginDelayedTransition(lytImagem, new Fade());
                    //mImagemProduto.setVisibility(View.VISIBLE);
                    TransitionManager.beginDelayedTransition(lytBotoes, new Slide());
                    lytBotoes.setVisibility(View.VISIBLE);

                }

                @Override
                public void onTransitionCancel(Transition transition) {

                }

                @Override
                public void onTransitionPause(Transition transition) {

                }

                @Override
                public void onTransitionResume(Transition transition) {

                }
            });
        } else {
            //mImagemProduto.setVisibility(View.VISIBLE);
            lytBotoes.setVisibility(View.VISIBLE);
        }
        preparaFacebook();
    }

//    @Override
//    protected Uri getUri(long id) {
//        return OportunidadeDiaContract.buildOportunidadeDiaUri(id);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        data.putExtra(Constantes.NATUREZA_PRODUTO_ID, item.getIdNaturezaProdutoPa());
        super.onActivityResult(requestCode, resultCode, data);
        // Facebook
        /*
        callbackManager.onActivityResult(requestCode, resultCode, data);
        Log.d("FCBK", "result " + ShareDialog.canShow(ShareLinkContent.class));
        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentTitle("Hello Facebook")
                    .setContentDescription(
                            "The 'Hello Facebook' sample  showcases simple Facebook integration")
                    .setContentUrl(Uri.parse("http://ift.tt/T39fwy"))
                    .build();

            shareDialog.show(linkContent);
        }*/
    }

    @Override
    protected void onLoadObject(OportunidadeDia item) {
        this.item = item;
        mTitulo.setText(item.getNomeProduto());
        Glide.with(this)
                .load(item.getUrlImagem())
                .asBitmap()
                .error(R.drawable.img_nao_disponivel)
                .into(mImagemProduto)
                .getRequest();
        mPrecoProduto.setText("R$ " + item.getPrecoVendaAtualTela());

        btnFacebook.setOnClickListener(this);
        btnLoja.setOnClickListener(this);
        /*URL url = null;
        try {
            url = new URL(item.getUrlImagem());
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            mImagemProduto.setImageBitmap(bmp);
        } catch (MalformedURLException e) {
            DCLog.e("Activity",this,e);
        } catch (IOException e) {
            DCLog.e("Activity",this, e);
        }
*/
    }

    @Override
    protected int getConstColunaNomeTitulo() {
        return OportunidadeDiaContract.COL_NOME_PRODUTO;
    }

    @Override
    protected void complementaOnCreate(long idOportunidadeDia) {
        mTitulo = (TextView) findViewById(R.id.toolbar_oportunidade_dia_texto);
        mImagemProduto = (ImageView) findViewById(R.id.imgProduto);
        mPrecoProduto = (TextView) findViewById(R.id.txtPrecoProduto);
        lytImagem = (ViewGroup) findViewById(R.id.lytImagem);
        //mAttacher = new PhotoViewAttacher(mImagemProduto);
        this.btnFacebook = (FloatingActionButton) findViewById(R.id.btnFabFacebook);
        this.btnLoja = (FloatingActionButton) findViewById(R.id.btnFabLoja);

        this.lytBotoes = (ViewGroup) findViewById(R.id.lytBotoes);
    }



    @Override
    protected ResourceObj getLayoutDetalheResource() {
        return new ResourceObj(R.layout.activity_oportunidade_dia_detalhe,"R.layout.activity_oportunidade_dia_detalhe");
    }

    @Override
    protected int getIdToolbar() {
        return R.id.toolbar_oportunidade_dia;
    }

    @Override
    public void onClick(View v) {
        final int btn1 = btnFacebook.getId();
        if (v.getId() == btnFacebook.getId()) {
            enviaFacebookLink();
        }
        if (v.getId() ==  R.id.btnFabLoja) {
            String url = item.getUrlProduto();
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }
    }

    public void enviaFacebookLink() {
        String url = "https://play.google.com/store/apps/details?id=br.com.lojadigicom.coletorprecocliente";
        if (shareDialog.canShow(ShareLinkContent.class)) {
            String mensagem = "De Olho no Preço encontrou !    De: R$ " + item.getPrecoVendaAnteriorTela() +
                    " Por: R$ " + item.getPrecoVendaAtualTela();
            ShareLinkContent content = new ShareLinkContent.Builder()
                    .setContentTitle(mensagem)
                    .setImageUrl(Uri.parse(item.getUrlImagem()))
                    .setContentDescription(item.getNomeProduto())
                    .setContentUrl(Uri.parse(url))
                    .build();
            ShareDialog.show(this, content);

        }
    }


    public void enviaFacebookFoto() {

        if (shareDialog.canShow(SharePhotoContent.class)) {

            mImagemProduto.buildDrawingCache();
            Bitmap bmap = mImagemProduto.getDrawingCache();

            String mensagem = "Preço Médio: " + item.getPrecoMedioTela() + "\n" +
                    " Preço Atual: " + item.getPrecoVendaAtualTela();
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
            Bitmap myBitmap=BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onTransitionEnd(Transition transition) {

    }
    @Override
    protected void esconderElementosParaPosAnimacao() {

    }
}
