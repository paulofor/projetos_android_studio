package br.com.lojadigicom.coletorprecocliente.app;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


import java.util.List;

import br.com.lojadigicom.coletorprecocliente.R;
import br.com.lojadigicom.coletorprecocliente.app.adapter.NaturezaProdutoAdapterView;
import br.com.lojadigicom.coletorprecocliente.data.contract.ProdutoClienteContract;
import br.com.lojadigicom.coletorprecocliente.framework.dao.MontadorDaoI;
import br.com.lojadigicom.coletorprecocliente.framework.data.DCCursorLoader;
import br.com.lojadigicom.coletorprecocliente.framework.data.MontadorDaoComposite;
import br.com.lojadigicom.coletorprecocliente.framework.faturamento.ConstantesBilling;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.framework.tela.DCAplicacao;
import br.com.lojadigicom.coletorprecocliente.framework.tela.ResourceObj;
import br.com.lojadigicom.coletorprecocliente.framework.util.UtilDatas;
import br.com.lojadigicom.coletorprecocliente.modelo.InteresseProduto;
import br.com.lojadigicom.coletorprecocliente.modelo.NaturezaProduto;
import br.com.lojadigicom.coletorprecocliente.modelo.ProdutoCliente;
import br.com.lojadigicom.coletorprecocliente.modelo.Usuario;
import br.com.lojadigicom.coletorprecocliente.servico.FabricaServicoApp;
import br.com.lojadigicom.coletorprecocliente.servico.InteresseProdutoServicoApp;
import br.com.lojadigicom.coletorprecocliente.servico.NaturezaProdutoServicoImpl;
import br.com.lojadigicom.coletorprecocliente.servico.UsuarioPesquisaServicoImpl;
import br.com.lojadigicom.coletorprecocliente.tela.base.activity.criaacopla.InteresseProdutoCriaAcoplaBaseActivity;


/*

Trata-se de um componente do tipo cria e acopla !!!
----------------------------------------------------

Os elementos principais desse activity :

1- Dados de Estrutura.
    - Toolbar ( tratamento e conteudo   -> titulo)
    - Definição do layout

2- Ligação dos campos de layout com objetos de tela
    - Card1 e Card2

3- Uri que vai montar o acesso ao banco de dados. -> No objeto recebido na sincrornização
    -> Aqui um fator muito importante e baseado em outro objeto : Produto

4- No retorno do objeto do banco, fazer a transformação/ criação do objeto principal



C1 - Mecanismo de Alteração da Tela em Exibição. ( antes da confirmação ) -> copiar no notepad para trabalhar melhor.
-------------------------------------------
1- Evento no botão
2- Chamada do serviço
3- Alteração no banco de dados.
4- Recebe novo OnLoad
5- Identifica que é o mesmo produto -> Não troca cartão
6- Carrega objetos de acordo com o objeto que foi trazido do banco de dados.

Chamda no Banco de Dados.
1- Verificando se é antiga ou nova.
2- Se for antiga e preciso ter o Id do Produto



 */


public class VitrineProdutoEscolhaActivity extends InteresseProdutoCriaAcoplaBaseActivity<ProdutoCliente> implements LoaderManager.LoaderCallbacks<Cursor>, AdapterView.OnItemSelectedListener {

    private String TAG_DEBUG = "VitrineAnimacao1";


    public final static String CHAVE_TOOLBAR = "chaveToolbar";
    public final static String CHAVE_BACK = "chaveBack";

    private final static String TITULO_PADRAO = "Gosto ou Não gosto";


    private TextView txtNotaCard1 = null;
    private TextView txtNotaCard2 = null;


    private List<NaturezaProduto> listaNatureza = null;


    private boolean cartao1Livre = true;
    private boolean cartao2Livre = true;

    private static final int LOADER_INTERESSE = 7;
    private static final int LOADER_USUARIO = 8;

    protected ResourceObj resourceObj = getLayoutDetalheResource();

    private CardView card_view1 = null;
    private ImageView imgProduto1 = null;
    private TextView txtNomeProdutoVitrine1 = null;
    private TextView txtPrecoProdutoVitrine1 = null;
    private TextView txtDescricaoNota1 = null;
    private FloatingActionButton btnOk1 = null;
    //private RatingBar rtbInteresse1 = null;

    private CardView card_view2 = null;
    private ImageView imgProduto2 = null;
    private TextView txtNomeProdutoVitrine2 = null;
    private TextView txtPrecoProdutoVitrine2 = null;
    private TextView txtDescricaoNota2 = null;
    private FloatingActionButton btnOk2 = null;
    //private RatingBar rtbInteresse2 = null;

    private TextView txtDescListaEspera1 = null;
    private TextView txtDescListaEspera2 = null;
    private TextView txtDescMonitora1 = null;
    private TextView txtDescMonitora2 = null;

    private FrameLayout principal = null;


    private InteresseProduto produtoAtual1 = null;
    private InteresseProduto produtoAtual2 = null;

    private Switch btnMonitora1 = null;
    private Switch btnEspera1 = null;
    private Switch btnMonitora2 = null;
    private Switch btnEspera2 = null;

    private Spinner spnNatureza = null;

    private boolean primeiro = true;

    private InteresseProdutoServicoApp srvInteresse = FabricaServicoApp.getInstancia().getInteresseProdutoServico();
    private UsuarioPesquisaServicoImpl usuarioServico = (UsuarioPesquisaServicoImpl) FabricaServicoApp.getInstancia().getUsuarioPesquisaServico();

    private long idProdutoTela = 0;

    private long idNatureza = 0;

    protected String titulo;
    protected boolean existeBack;

    private Button btnAssinaturaEspera1 = null;
    private Button btnAssinaturaEspera2 = null;
    private Button btnAssinaturaMonitora1 = null;
    private Button btnAssinaturaMonitora2 = null;

    private Usuario usuario = null;

    private ConstantesBilling constantesBilling = null;

    // Passar isso para arquitetura e melhorar logs.
    protected ResourceObj getLayoutDetalheResource() {
        return new ResourceObj(R.layout.activity_vitrine_produto_escolha_v4,"R.layout.activity_vitrine_produto_escolha_v4");
    }
    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DCAplicacao app = (DCAplicacao) getApplication();
        constantesBilling = app.getBilling();
        setContentView(resourceObj.getCodigo());


        Bundle extras = getIntent().getExtras();
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar_vitrine_produto);
        titulo = (extras!=null?extras.getString(CHAVE_TOOLBAR):null);

        spnNatureza = (Spinner) this.findViewById(R.id.spnNatureza);
        spnNatureza.setOnItemSelectedListener(this);
        carregaSpinnerNatureza();


        btnAssinaturaMonitora1 = (Button) this.findViewById(R.id.btnAssinaturaMonitora1);
        btnAssinaturaMonitora1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chamaAssinatura();
            }
        });
        btnAssinaturaMonitora2 = (Button) this.findViewById(R.id.btnAssinaturaMonitora2);
        btnAssinaturaMonitora2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chamaAssinatura();
            }
        });


        setSupportActionBar(toolbar);
        existeBack = (extras==null || extras.getBoolean(CHAVE_BACK,true));


        cartao1Livre = false;
        complementaOnCreate();
        if (existeBack) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if (titulo!=null) {
            toolbar.setTitle(titulo);
        }  else {
            toolbar.setTitle(TITULO_PADRAO);
        }

        verificaBilling();
    }

    @Override
    protected void onResume() {
        super.onResume();
        verificaBilling();
    }

    public void chamaAssinatura() {
        Intent mIntent = new Intent(this, AdmAssinaturaClienteActivity.class);
        startActivity(mIntent);
    }



    private void carregaSpinnerNatureza() {
        NaturezaProdutoServicoImpl servico = new NaturezaProdutoServicoImpl();
        listaNatureza = servico.listaTotal(this);
        NaturezaProdutoAdapterView adpater = new NaturezaProdutoAdapterView(this);
        adpater.setLista(listaNatureza);
        spnNatureza.setAdapter(adpater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (id==LOADER_INTERESSE) {
            NaturezaProduto natureza = (NaturezaProduto) spnNatureza.getTag();
            ProdutoClienteContract.getFiltro().setIdNatureza(natureza.getIdNaturezaProduto());
            Uri uri = ProdutoClienteContract.buildObtemProximoNaoEscolhido();
            return new DCCursorLoader(this, uri, null, null, null, null, id);
        }
        return null;
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        DCCursorLoader dcCursorLoader = (DCCursorLoader) loader;
        if (dcCursorLoader.getId()==LOADER_INTERESSE) {
            MontadorDaoI montadorDao = ProdutoClienteContract.getMontadorComposto();
            montadorDao = ProdutoClienteContract.adicionaMontadorInteresseProdutoPossuiLista(montadorDao);

            if (data.moveToFirst()) {
                ProdutoCliente produtoAtual = (ProdutoCliente) montadorDao.getItem(data);
                InteresseProduto interesseProduto = null;
                // Testa se é null nao funcionou.
                if (produtoAtual.getCorrenteInteresseProduto_Possui().getIdObj() == 0) {
                    DCLog.d(TAG_DEBUG, this, "interesse novo");
                    interesseProduto = srvInteresse.criaParaProduto(produtoAtual, this);
                } else {
                    DCLog.d(TAG_DEBUG, this, "interesse antigo");
                    interesseProduto = produtoAtual.getCorrenteInteresseProduto_Possui();

                    interesseProduto.setProdutoCliente_ReferenteA(produtoAtual);
                }
                DCLog.d(TAG_DEBUG, this, "IdProduto:" + interesseProduto.getProdutoCliente_ReferenteA().getIdObj());
                onLoadObject(interesseProduto);
            }
        }
    }
    private void setObjetosTela(InteresseProduto item) {

    }

    // Esse e o metodo mais importante !!!!
    // Fazer em cima de ProdutoCliente ou InterresseProduto com  o produto dentro ?
    // Opção dois: Muito mais completo
    private void onLoadObject(InteresseProduto interesse) {
        DCLog.d(TAG_DEBUG,this,"onLoadObject");
        if (idProdutoTela==0) idProdutoTela = interesse.getProdutoCliente_ReferenteA().getIdObj();
        if (idProdutoTela==interesse.getProdutoCliente_ReferenteA().getIdObj()) {
            DCLog.d(TAG_DEBUG,this,"mesmoCartao");
            mesmoCartao(interesse);
        } else {
            DCLog.d(TAG_DEBUG,this,"trocaCartao");
            trocaCartao(interesse);
        }
        idProdutoTela = interesse.getProdutoCliente_ReferenteA().getIdObj();
        DCLog.d(TAG_DEBUG,this,"Novo IdProdutoTela: " + idProdutoTela);
    }

    private void mesmoCartao(InteresseProduto interesse) {
        if (!cartao1Livre) {
            onLoadObject1(interesse);
            this.card_view1.bringToFront();
            this.card_view1.setVisibility(View.VISIBLE);
            return;
        }
        if (!cartao2Livre) {
            onLoadObject2(interesse);
            this.card_view2.bringToFront();
            this.card_view2.setVisibility(View.VISIBLE);
            return;
        }
    }
    private void trocaCartao(InteresseProduto interesse) {
        if (cartao1Livre) {
            onLoadObject1(interesse);
            this.trocaCartao2PorCartao1();
            card_view1.bringToFront();
            cartao2Livre = true;
            cartao1Livre = false;
            return;
        }
        if (cartao2Livre) {
            onLoadObject2(interesse);
            this.trocaCartao1PorCartao2();
            card_view2.bringToFront();
            cartao1Livre = true;
            cartao2Livre = false;
            return;
        }
    }


    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    protected MontadorDaoI completaMontador(MontadorDaoComposite montadorDao) {
        return montadorDao;
    }
/**/

    protected void onLoadObject2(InteresseProduto interesse) {
        if (interesse==null) return;
        ProdutoCliente item = interesse.getCorrenteProdutoCliente_ReferenteA();
        Glide.with(this)
                .load(item.getImagem())
                .error(R.drawable.img_nao_disponivel)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgProduto2);
        txtNomeProdutoVitrine2.setText(item.getNome());

        txtPrecoProdutoVitrine2.setText("R$ " + item.getPrecoAtualTela());
        txtDescricaoNota2.setText(item.getLoja());
        btnMonitora2.setChecked(interesse.getMonitora());
        btnEspera2.setChecked(interesse.getEspera());
        //rtbInteresse2.setRating(0);
        //btnOk2.setVisibility(View.INVISIBLE);
        produtoAtual2 = interesse;

    }

    protected void onLoadObject1(InteresseProduto interesse) {
        if (interesse==null) return;
        ProdutoCliente item = interesse.getCorrenteProdutoCliente_ReferenteA();
        Glide.with(this)
                .load(item.getImagem())
                .error(R.drawable.img_nao_disponivel)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgProduto1);
        txtNomeProdutoVitrine1.setText(item.getNome());

        txtPrecoProdutoVitrine1.setText("R$ " + item.getPrecoAtualTela());
        txtDescricaoNota1.setText(item.getLoja());
        DCLog.d(TAG_DEBUG,this,"Monitora=" + interesse.getMonitora() + " Espera=" + interesse.getEspera());
        btnMonitora1.setChecked(interesse.getMonitora());
        btnEspera1.setChecked(interesse.getEspera());
        //rtbInteresse1.setRating(0);
        //btnOk1.setVisibility(View.INVISIBLE);
        this.produtoAtual1 = interesse;
    }

    protected int getConstColunaNomeTitulo() {
        return ProdutoClienteContract.COL_NOME;
    }
    protected void complementaOnCreate() {

        principal = (FrameLayout) this.findViewById(R.id.principal);

        // Card 1
        card_view1 = (CardView) this.findViewById(R.id.card_view1) ;
        imgProduto1 = (ImageView) card_view1.findViewById(R.id.imgProduto1);
        //rtbInteresse1 = (RatingBar) this.findViewById(R.id.rtbInteresse1);
        txtNomeProdutoVitrine1 = (TextView) card_view1.findViewById(R.id.txtNomeProdutoVitrine1);
        txtPrecoProdutoVitrine1 = (TextView) card_view1.findViewById(R.id.txtPrecoProdutoVitrine1);
        txtDescricaoNota1 = (TextView) card_view1.findViewById(R.id.txtDescricaoNota1);
        btnOk1 = (FloatingActionButton) card_view1.findViewById(R.id.btnOk1);
        btnOk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finaliza1();
                //enviarNota1();
            }
        });
        //rtbInteresse1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
        //    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        //        escolheuRating1();
        //    }
        //});
        txtDescListaEspera1 = (TextView) card_view1.findViewById(R.id.txtDescListaEspera1);
        txtDescMonitora1 = (TextView) card_view1.findViewById(R.id.txtDescMonitora1);
        btnMonitora1 = (Switch) card_view1.findViewById(R.id.btnMonitora1);
        btnMonitora1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                chamaMonitora1();
            }
        });
        btnEspera1 = (Switch) card_view1.findViewById(R.id.btnEspera1);
        btnEspera1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                chamaEspera1();
            }
        });

        // Card 2
        card_view2 = (CardView) this.findViewById(R.id.card_view2) ;
        imgProduto2 = (ImageView) card_view2.findViewById(R.id.imgProduto2);
        //rtbInteresse2 = (RatingBar) this.findViewById(R.id.rtbInteresse2);
        txtNomeProdutoVitrine2 = (TextView) card_view2.findViewById(R.id.txtNomeProdutoVitrine2);
        txtPrecoProdutoVitrine2 = (TextView) card_view2.findViewById(R.id.txtPrecoProdutoVitrine2);
        txtDescricaoNota2 = (TextView) card_view2.findViewById(R.id.txtDescricaoNota2);
        btnOk2 = (FloatingActionButton) card_view2.findViewById(R.id.btnOk2);
        btnOk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finaliza2();
                //enviarNota2();
            }
        });

        //rtbInteresse2.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
        //    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        //        escolheuRating2();
        //    }
        //});
        txtDescListaEspera2 = (TextView) card_view2.findViewById(R.id.txtDescListaEspera2);
        txtDescMonitora2 = (TextView) card_view2.findViewById(R.id.txtDescMonitora2);
        btnMonitora2 = (Switch) card_view2.findViewById(R.id.btnMonitora2);
        btnMonitora2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                chamaMonitora2();
                // Monitora 2
                // 1- Retira de monitoramento.
                // 2- Altera no banco de dados o interesse produto2 (monitoramento=false, espera=true)
            }
        });
        btnEspera2 = (Switch) card_view2.findViewById(R.id.btnEspera2);
        btnEspera2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                chamaEspera2();
              }
        });
    }


    public void chamaMonitora1() {
        // Monitora 1
        // 1- Retira de espera1.
        // 2- Altera no banco de dados o interesse produto1 (monitoramento=false, espera=true)
        //btnEspera1.setChecked(false);
        DCLog.d(TAG_DEBUG,this,"onClickMonitora1");
        if (btnMonitora1.isChecked()) {
            produtoAtual1.setEspera(false);
            produtoAtual1.setMonitora(true);
        } else {
            produtoAtual1.setMonitora(false);
        }
        DCLog.d(TAG_DEBUG,this,"chamaServico");
        produtoAtual1.setData(UtilDatas.getDataHora());
        srvInteresse.insereAtualiza(produtoAtual1, this);
    }

    public void chamaEspera1() {
        // Espera 1
        // 1- Retira de monitoramento1.
        // 2- Altera no banco de dados o interesse produto1 (monitoramento=alse, espera=true)
        //btnMonitora1.setChecked(false);
        DCLog.d(TAG_DEBUG,this,"onClickEspera1");
        if (btnEspera1.isChecked()) {
            produtoAtual1.setEspera(true);
            produtoAtual1.setMonitora(false);
        } else {
            produtoAtual1.setEspera(false);
        }
        DCLog.d(TAG_DEBUG,this,"chamaServico");
        produtoAtual1.setData(UtilDatas.getDataHora());
        srvInteresse.insereAtualiza(produtoAtual1, this);
    }
    public void chamaMonitora2() {
        // Monitora 2
        // 1- Retira de espera2.
        // 2- Altera no banco de dados o interesse produto2 (monitoramento=false, espera=true)
        //btnEspera2.setChecked(false);
        DCLog.d(TAG_DEBUG,this,"onClickMonitora2");
        if (btnMonitora2.isChecked()) {
            produtoAtual2.setEspera(false);
            produtoAtual2.setMonitora(true);
        } else {
            produtoAtual2.setMonitora(false);
        }
        DCLog.d(TAG_DEBUG,this,"chamaServico");
        produtoAtual2.setData(UtilDatas.getDataHora());
        srvInteresse.insereAtualiza(produtoAtual2, this);
    }
    public void chamaEspera2() {
        // Espera 2
        // 1- Retira de monitoramento2.
        // 2- Altera no banco de dados o interesse produto2 (monitoramento=false, espera=true)
        //btnMonitora2.setChecked(false);
        DCLog.d(TAG_DEBUG,this,"onClickEspera2");
        if (btnEspera2.isChecked()) {
            produtoAtual2.setEspera(true);
            produtoAtual2.setMonitora(false);
        } else {
            produtoAtual2.setEspera(false);
        }
        DCLog.d(TAG_DEBUG,this,"chamaServico");
        produtoAtual2.setData(UtilDatas.getDataHora());
        srvInteresse.insereAtualiza(produtoAtual2, this);
    }



    private TextView animado1 = null;
    private TextView animado2 = null;

    public void limpaDesc2() {
        this.txtDescListaEspera2.setVisibility(View.INVISIBLE);
        this.txtDescMonitora2.setVisibility(View.INVISIBLE);

    }
    public void limpaDesc1() {
        this.txtDescListaEspera1.setVisibility(View.INVISIBLE);
        this.txtDescMonitora1.setVisibility(View.INVISIBLE);
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

//    public void enviarNota1() {
//        float nota = rtbInteresse1.getRating();
//        long notaInt = (long) Math.ceil(nota);
//        InteresseProdutoFiltro filtro = new InteresseProdutoFiltro();
//        filtro.setProdutoCliente(this.produtoAtual1);
//        filtro.setNota(notaInt);
//        ParamServico param = new ParamServico(this, FabricaServico.getInstancia().getInteresseProdutoServico());
//        param.setFiltro(filtro);
//        param.setIdOperacao(InteresseProdutoServico.OP_INCLUI_ITEM);
//        InteresseProdutoAsyncTask task = new InteresseProdutoAsyncTask();
//        task.execute(param);
//        limpaDesc1();
//    }

//    public void enviarNota2() {
//
//        float nota = rtbInteresse2.getRating();
//        long notaInt = (long) Math.ceil(nota);
//        InteresseProdutoFiltro filtro = new InteresseProdutoFiltro();
//        filtro.setProdutoCliente(this.produtoAtual2);
//        filtro.setNota(notaInt);
//        ParamServico param = new ParamServico(this, FabricaServico.getInstancia().getInteresseProdutoServico());
//        param.setFiltro(filtro);
//        param.setIdOperacao(InteresseProdutoServico.OP_INCLUI_ITEM);
//        InteresseProdutoAsyncTask task = new InteresseProdutoAsyncTask();
//        task.execute(param);
//        limpaDesc2();
//    }


    public void finaliza1() {
        produtoAtual1.setVisualizacaoConcluida(true);
        DCLog.d(TAG_DEBUG,this,"Fianaliza1 = " + produtoAtual1.getContentValues().toString());
        srvInteresse.insereAtualiza(produtoAtual1, this);
        limpaDesc1();
        verificaBilling();
    }

    public void finaliza2() {
        produtoAtual2.setVisualizacaoConcluida(true);
        DCLog.d(TAG_DEBUG,this,"Fianaliza2 = " + produtoAtual2.getContentValues().toString());
        srvInteresse.insereAtualiza(produtoAtual2, this);
        limpaDesc2();
        verificaBilling();
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


        //this.cartao2Livre = true;
    }

    protected int getIdToolbar() {
        return 0;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        spnNatureza.setTag(listaNatureza.get(i));
        this.getSupportLoaderManager().restartLoader(LOADER_INTERESSE, null, this);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    private void verificaBilling() {
        usuarioServico.verificaLimites(this);
        if (usuarioServico.permiteMonitorar(constantesBilling)) {
            btnMonitora1.setVisibility(View.VISIBLE);
            btnMonitora2.setVisibility(View.VISIBLE);
            btnAssinaturaMonitora1.setVisibility(View.GONE);
            btnAssinaturaMonitora2.setVisibility(View.GONE);
        } else {
            btnMonitora1.setVisibility(View.GONE);
            btnMonitora2.setVisibility(View.GONE);
            btnAssinaturaMonitora1.setVisibility(View.VISIBLE);
            btnAssinaturaMonitora2.setVisibility(View.VISIBLE);
        }

        if (usuarioServico.permiteEsperar(constantesBilling)) {
            btnEspera1.setVisibility(View.VISIBLE);
            btnEspera2.setVisibility(View.VISIBLE);
        } else {
            btnEspera1.setVisibility(View.GONE);
            btnEspera2.setVisibility(View.GONE);
        }
    }
}
