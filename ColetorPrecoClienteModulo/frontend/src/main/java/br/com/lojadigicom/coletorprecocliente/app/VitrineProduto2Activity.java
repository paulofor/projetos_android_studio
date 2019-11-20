package br.com.lojadigicom.coletorprecocliente.app;
// Laboratorio de melhorias para o componente.


import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import br.com.lojadigicom.coletorprecocliente.R;
import br.com.lojadigicom.coletorprecocliente.data.contract.ProdutoClienteContract;
import br.com.lojadigicom.coletorprecocliente.framework.dao.MontadorDaoI;
import br.com.lojadigicom.coletorprecocliente.framework.data.DCCursorLoader;
import br.com.lojadigicom.coletorprecocliente.framework.data.MontadorDaoComposite;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.framework.tela.ResourceObj;
import br.com.lojadigicom.coletorprecocliente.modelo.ProdutoCliente;

import br.com.lojadigicom.coletorprecocliente.servico.FabricaServicoApp;
import br.com.lojadigicom.coletorprecocliente.servico.InteresseProdutoServicoApp;
import br.com.lojadigicom.coletorprecocliente.servico.ParamServico;
import br.com.lojadigicom.coletorprecocliente.servico.filtro.InteresseProdutoFiltro;
import br.com.lojadigicom.coletorprecocliente.servico.sync.InteresseProdutoAsyncTask;



public class VitrineProduto2Activity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public final static String CHAVE_TOOLBAR = "chaveToolbar";
    public final static String CHAVE_BACK = "chaveBack";

    private final static String TITULO_PADRAO = "Gosto ou Não gosto";

    private TextView txtNotaCard1 = null;
    private TextView txtNotaCard2 = null;




    private boolean cartao1Livre = true;
    private boolean cartao2Livre = true;

    private static final int DETAIL_LOADER = 2;

    protected ResourceObj resourceObj = getLayoutDetalheResource();

    private CardView card_view1 = null;
    private ImageView imgProduto1 = null;
    private TextView txtNomeProdutoVitrine1 = null;
    private TextView txtPrecoProdutoVitrine1 = null;
    private TextView txtDescricaoNota1 = null;
    private FloatingActionButton btnOk1 = null;
    private RatingBar rtbInteresse1 = null;

    private CardView card_view2 = null;
    private ImageView imgProduto2 = null;
    private TextView txtNomeProdutoVitrine2 = null;
    private TextView txtPrecoProdutoVitrine2 = null;
    private TextView txtDescricaoNota2 = null;
    private FloatingActionButton btnOk2 = null;
    private RatingBar rtbInteresse2 = null;

    private TextView txtDescNota2_1 = null;
    private TextView txtDescNota2_2 = null;
    private TextView txtDescNota2_3 = null;
    private TextView txtDescNota2_4 = null;
    private TextView txtDescNota2_5 = null;
    private TextView txtDescNota1_1 = null;
    private TextView txtDescNota1_2 = null;
    private TextView txtDescNota1_3 = null;
    private TextView txtDescNota1_4 = null;
    private TextView txtDescNota1_5 = null;

    private ProdutoCliente produtoAtual1 = null;
    private ProdutoCliente produtoAtual2 = null;

    private FrameLayout principal = null;

    private boolean primeiro = true;

    protected ResourceObj getLayoutDetalheResource() {
        return new ResourceObj(R.layout.activity_vitrine_produto2,"R.layout.activity_vitrine_produto2");
    }
    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(resourceObj.getCodigo());
        this.getSupportLoaderManager().initLoader(DETAIL_LOADER, null, this);

        Bundle extras = getIntent().getExtras();
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar_vitrine2);
        String titulo = (extras!=null?extras.getString(CHAVE_TOOLBAR):null);
        if (titulo!=null) {
            toolbar.setTitle(titulo);
        }  else {
            toolbar.setTitle(TITULO_PADRAO);
        }
        setSupportActionBar(toolbar);
        boolean existeBack = (extras!=null?extras.getBoolean(CHAVE_BACK,true):true);
        if (existeBack) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        complementaOnCreate();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int x = item.getItemId();
        switch (item.getItemId()) {
            case android.R.id.home:
                DCLog.d(DCLog.CICLO_VIEW,this,"entrou no case");
                finish();
                //Intent intent = new Intent("br.com.lojadigicom.olhonoprecogames.MainActivity");
                //startActivity(intent);
                // This is called when the Home (Up) button is pressed in the action bar.
                // Create a simple intent that starts the hierarchical parent activity and
                // use NavUtils in the Support Package to ensure proper handling of Up.
                //Intent upIntent = new Intent(this, MainActivity.class);
                //if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is not part of the application's task, so create a new task
                    // with a synthesized back stack.
                    //TaskStackBuilder.from(this)
                            // If there are ancestor activities, they should be added here.
                    //        .addNextIntent(upIntent)
                    //        .startActivities();
                    //finish();
                //} else {
                    // This activity is part of the application's task, so simply
                    // navigate up to the hierarchical parent activity.
                    //NavUtils.navigateUpTo(this, upIntent);
                //}
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = ProdutoClienteContract.buildAllSemInteresseProdutoPossui();
        return new DCCursorLoader(this,uri,null,null,null,null);
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        //data.moveToFirst();
        //String nome = data.getString(getConstColunaNomeTitulo());
        //ActionBar actionBar = this.getSupportActionBar();
        //actionBar.setTitle(nome);

        MontadorDaoI montadorDao = ProdutoClienteContract.getMontadorComposto();
        montadorDao = completaMontador((MontadorDaoComposite)montadorDao);
        if (data.moveToFirst()) {
            ProdutoCliente produtoAtual = (ProdutoCliente) montadorDao.getItem(data);
            onLoadObject(produtoAtual);
        }
    }

    private void onLoadObject(ProdutoCliente item) {
        //DCLog.d(DCLog.ANIMACAO,this,"OnLoad");
        if (cartao1Livre) {
            onLoadObject1(item);
            if (!primeiro) this.trocaCartao2PorCartao1();
            else {
                card_view1.bringToFront();
                primeiro = false;
            }
            cartao2Livre = true;
            cartao1Livre = false;
            return;
        }
        if (cartao2Livre) {
            onLoadObject2(item);
            card_view2.setVisibility(View.VISIBLE);
            this.trocaCartao1PorCartao2();
            cartao2Livre = false;
            cartao1Livre = true;
        }
    }


    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    protected MontadorDaoI completaMontador(MontadorDaoComposite montadorDao) {
        return montadorDao;
    }
/**/

    protected void onLoadObject2(ProdutoCliente item) {
        if (item==null) return;
 /*       Glide.with(this)
                .load(item.getImagem())
                .asBitmap()
                .error(R.drawable.img_nao_disponivel)
                .into(imgProduto)
                .getRequest();*/

        Glide.with(this)
                .load(item.getImagem())
                .error(R.drawable.img_nao_disponivel)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgProduto2);
        txtNomeProdutoVitrine2.setText(item.getNome());
        DCLog.d(DCLog.ANIMACAO,this,"Card2: " + item.getNome());
        txtPrecoProdutoVitrine2.setText("R$ " + item.getPrecoAtualTela());
        txtDescricaoNota2.setText(item.getLoja());
        rtbInteresse2.setRating(0);
        btnOk2.setVisibility(View.INVISIBLE);
        this.produtoAtual2 = item;
    }

    protected void onLoadObject1(ProdutoCliente item) {
        if (item==null) return;
 /*       Glide.with(this)
                .load(item.getImagem())
                .asBitmap()
                .error(R.drawable.img_nao_disponivel)
                .into(imgProduto)
                .getRequest();*/

        Glide.with(this)
                .load(item.getImagem())
                .error(R.drawable.img_nao_disponivel)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgProduto1);
        txtNomeProdutoVitrine1.setText(item.getNome());
        DCLog.d(DCLog.ANIMACAO,this,"Card1: " + item.getNome());
        txtPrecoProdutoVitrine1.setText("R$ " + item.getPrecoAtualTela());
        txtDescricaoNota1.setText(item.getLoja());
        rtbInteresse1.setRating(0);
        btnOk1.setVisibility(View.INVISIBLE);
        this.produtoAtual1 = item;
    }

    protected int getConstColunaNomeTitulo() {
        return ProdutoClienteContract.COL_NOME;
    }
    protected void complementaOnCreate() {

        principal = (FrameLayout) this.findViewById(R.id.principal);

        // Card 1
        card_view1 = (CardView) this.findViewById(R.id.card_view21) ;
        imgProduto1 = (ImageView) card_view1.findViewById(R.id.imgProduto1);
        rtbInteresse1 = (RatingBar) card_view1.findViewById(R.id.rtbInteresse1);
        txtNomeProdutoVitrine1 = (TextView) card_view1.findViewById(R.id.txtNomeProdutoVitrine1);
        txtPrecoProdutoVitrine1 = (TextView) card_view1.findViewById(R.id.txtPrecoProdutoVitrine1);
        txtDescricaoNota1 = (TextView) card_view1.findViewById(R.id.txtDescricaoNota1);
        btnOk1 = (FloatingActionButton) card_view1.findViewById(R.id.btnOk1);
        btnOk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarNota1();
            }
        });
        rtbInteresse1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                escolheuRating1();
            }
        });
        txtDescNota1_1 = (TextView) card_view1.findViewById(R.id.txtDescNota1_1);
        txtDescNota1_2 = (TextView) card_view1.findViewById(R.id.txtDescNota1_2);
        txtDescNota1_3 = (TextView) card_view1.findViewById(R.id.txtDescNota1_3);
        txtDescNota1_4 = (TextView) card_view1.findViewById(R.id.txtDescNota1_4);
        txtDescNota1_5 = (TextView) card_view1.findViewById(R.id.txtDescNota1_5);

        // Card 2
        card_view2 = (CardView) this.findViewById(R.id.card_view22) ;
        imgProduto2 = (ImageView) card_view2.findViewById(R.id.imgProduto2);
        rtbInteresse2 = (RatingBar) card_view2.findViewById(R.id.rtbInteresse2);
        txtNomeProdutoVitrine2 = (TextView) card_view2.findViewById(R.id.txtNomeProdutoVitrine2);
        txtPrecoProdutoVitrine2 = (TextView) card_view2.findViewById(R.id.txtPrecoProdutoVitrine2);
        txtDescricaoNota2 = (TextView) card_view2.findViewById(R.id.txtDescricaoNota2);
        btnOk2 = (FloatingActionButton) card_view2.findViewById(R.id.btnOk2);
        btnOk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarNota2();
            }
        });
        rtbInteresse2.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                escolheuRating2();
            }
        });
        txtDescNota2_1 = (TextView) card_view2.findViewById(R.id.txtDescNota2_1);
        txtDescNota2_2 = (TextView) card_view2.findViewById(R.id.txtDescNota2_2);
        txtDescNota2_3 = (TextView) card_view2.findViewById(R.id.txtDescNota2_3);
        txtDescNota2_4 = (TextView) card_view2.findViewById(R.id.txtDescNota2_4);
        txtDescNota2_5 = (TextView) card_view2.findViewById(R.id.txtDescNota2_5);

    }

    public void escolheuRating1() {
        float nota = rtbInteresse1.getRating();
        int notaInt = (int) Math.ceil(nota);
        switch (notaInt) {
            case 1: {
                this.colocaNota1(this.txtDescNota1_1);
                //txtDescricaoNota1.setText("Não gostei");
                break;
            }
            case 2: {
                this.colocaNota1(this.txtDescNota1_2);
                //txtDescricaoNota1.setText("Razoavel");
                break;
            }
            case 3: {
                this.colocaNota1(this.txtDescNota1_3);
                //txtDescricaoNota1.setText("Gostei");
                break;
            }
            case 4: {
                this.colocaNota1(this.txtDescNota1_4);
                //txtDescricaoNota1.setText("Gostei muito");
                break;
            }
            case 5: {
                this.colocaNota1(this.txtDescNota1_5);
                //txtDescricaoNota1.setText("Adorei");
                break;
            }
        }
        btnOk1.setVisibility(View.VISIBLE);
    }

    public void escolheuRating2() {
        float nota = rtbInteresse2.getRating();
        int notaInt = (int) Math.ceil(nota);
        switch (notaInt) {
            case 1: {
                this.colocaNota2(this.txtDescNota2_1);
                //txtDescricaoNota2.setText("Não gostei");
                break;
            }
            case 2: {
                this.colocaNota2(this.txtDescNota2_2);
                //txtDescricaoNota2.setText("Razoavel");
                break;
            }
            case 3: {
                this.colocaNota2(this.txtDescNota2_3);
                //txtDescricaoNota2.setText("Gostei");
                break;
            }
            case 4: {
                this.colocaNota2(this.txtDescNota2_4);
                //txtDescricaoNota2.setText("Gostei muito");
                break;
            }
            case 5: {
                this.colocaNota2(this.txtDescNota2_5);
                //txtDescricaoNota2.setText("Adorei");
                break;
            }
        }
        btnOk2.setVisibility(View.VISIBLE);
    }


    private TextView animado1 = null;
    private TextView animado2 = null;

    public void limpaDesc2() {
        this.txtDescNota2_1.setVisibility(View.INVISIBLE);
        this.txtDescNota2_2.setVisibility(View.INVISIBLE);
        this.txtDescNota2_3.setVisibility(View.INVISIBLE);
        this.txtDescNota2_4.setVisibility(View.INVISIBLE);
        this.txtDescNota2_5.setVisibility(View.INVISIBLE);
    }
    public void limpaDesc1() {
        this.txtDescNota1_1.setVisibility(View.INVISIBLE);
        this.txtDescNota1_2.setVisibility(View.INVISIBLE);
        this.txtDescNota1_3.setVisibility(View.INVISIBLE);
        this.txtDescNota1_4.setVisibility(View.INVISIBLE);
        this.txtDescNota1_5.setVisibility(View.INVISIBLE);
    }

    public void colocaNota2(TextView txt) {
        animado2 = txt;
        Animation animacao = AnimationUtils.loadAnimation(this, R.anim.translate_entrando);
        animacao.setDuration(600);
        animacao.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                animado2.bringToFront();
                animado2.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                if (txtNotaCard2!=null && txtNotaCard2!=animado2) {
                    txtNotaCard2.setVisibility(View.INVISIBLE);
                }
                txtNotaCard2 = animado2;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        txt.startAnimation(animacao);
    }

    public void colocaNota1(TextView txt) {
        DCLog.d(DCLog.ANIMACAO,this,"colocaNota1");
        animado1 = txt;
        Animation animacao = AnimationUtils.loadAnimation(this, R.anim.translate_entrando);
        animacao.setDuration(600);
        animacao.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                animado1.bringToFront();
                animado1.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                if (txtNotaCard1!=null && txtNotaCard1!=animado1) {
                    txtNotaCard1.setVisibility(View.INVISIBLE);
                }
                txtNotaCard1 = animado1;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        txt.startAnimation(animacao);
    }

    public void enviarNota1() {

        float nota = rtbInteresse1.getRating();
        long notaInt = (long) Math.ceil(nota);
        InteresseProdutoFiltro filtro = new InteresseProdutoFiltro();
        filtro.setProdutoCliente(this.produtoAtual1);
        filtro.setNota(notaInt);
        ParamServico param = new ParamServico(this, FabricaServicoApp.getInstancia().getInteresseProdutoServico());
        param.setFiltro(filtro);
        param.setIdOperacao(InteresseProdutoServicoApp.OP_INCLUI_ITEM);
        InteresseProdutoAsyncTask task = new InteresseProdutoAsyncTask();
        task.execute(param);
        limpaDesc1();
    }

    public void enviarNota2() {

        float nota = rtbInteresse2.getRating();
        long notaInt = (long) Math.ceil(nota);
        InteresseProdutoFiltro filtro = new InteresseProdutoFiltro();
        filtro.setProdutoCliente(this.produtoAtual2);
        filtro.setNota(notaInt);
        ParamServico param = new ParamServico(this, FabricaServicoApp.getInstancia().getInteresseProdutoServico());
        param.setFiltro(filtro);
        param.setIdOperacao(InteresseProdutoServicoApp.OP_INCLUI_ITEM);
        InteresseProdutoAsyncTask task = new InteresseProdutoAsyncTask();
        task.execute(param);
        limpaDesc2();
    }



    private void trocaCartao1PorCartao2(){
        //this.card_view2.setVisibility(View.VISIBLE);
        //this.card_view1.setVisibility(View.VISIBLE);
        Animation animacao1 = AnimationUtils.loadAnimation(this, R.anim.translate_saindo);
        card_view1.setAnimation(animacao1);
        Animation animacao2 = AnimationUtils.loadAnimation(this, R.anim.translate_entrando);
        card_view2.setAnimation(animacao2);
        AnimationSet rootSet = new AnimationSet(true);
        rootSet.setInterpolator(new AccelerateInterpolator());
        rootSet.addAnimation(animacao1);
        rootSet.addAnimation(animacao2);
        card_view2.bringToFront();
        rootSet.startNow();
        DCLog.d(DCLog.ANIMACAO,this,"Trocando 1 por 2");
        //this.cartao1Livre = true;
    }


    private void trocaCartao2PorCartao1(){
        //this.card_view2.setVisibility(View.VISIBLE);
        //this.card_view1.setVisibility(View.VISIBLE);
        Animation animacao1 = AnimationUtils.loadAnimation(this, R.anim.translate_saindo);
        card_view2.setAnimation(animacao1);
        Animation animacao2 = AnimationUtils.loadAnimation(this, R.anim.translate_entrando);
        card_view1.setAnimation(animacao2);
        AnimationSet rootSet = new AnimationSet(true);
        rootSet.setInterpolator(new AccelerateInterpolator());
        rootSet.addAnimation(animacao1);
        rootSet.addAnimation(animacao2);
        card_view1.bringToFront();
        rootSet.startNow();
        DCLog.d(DCLog.ANIMACAO,this,"Trocando 2 por 1");

        //this.cartao2Livre = true;
    }

    protected int getIdToolbar() {
        return 0;
    }
}
