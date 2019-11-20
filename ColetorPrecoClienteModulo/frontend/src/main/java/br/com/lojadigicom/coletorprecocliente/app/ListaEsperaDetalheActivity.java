package br.com.lojadigicom.coletorprecocliente.app;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.transition.Transition;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;


import br.com.lojadigicom.coletorprecocliente.R;
import br.com.lojadigicom.coletorprecocliente.data.contract.InteresseProdutoContract;
import br.com.lojadigicom.coletorprecocliente.data.contract.ProdutoClienteContract;
import br.com.lojadigicom.coletorprecocliente.framework.dao.MontadorDaoI;
import br.com.lojadigicom.coletorprecocliente.framework.data.MontadorDaoComposite;
import br.com.lojadigicom.coletorprecocliente.framework.faturamento.ConstantesBilling;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.framework.tela.DCAplicacao;
import br.com.lojadigicom.coletorprecocliente.framework.tela.ResourceObj;
import br.com.lojadigicom.coletorprecocliente.framework.util.UtilDatas;
import br.com.lojadigicom.coletorprecocliente.modelo.InteresseProduto;
import br.com.lojadigicom.coletorprecocliente.modelo.ProdutoCliente;

import br.com.lojadigicom.coletorprecocliente.servico.FabricaServicoApp;
import br.com.lojadigicom.coletorprecocliente.servico.InteresseProdutoServicoApp;
import br.com.lojadigicom.coletorprecocliente.servico.UsuarioPesquisaServicoImpl;
import br.com.lojadigicom.coletorprecocliente.servico.base.FabricaServico;
import br.com.lojadigicom.coletorprecocliente.tela.base.activity.detalhe.InteresseProdutoDetalheBaseActivity;
import br.com.lojadigicom.coletorprecocliente.tela.base.activity.detalhe.InteresseProdutoExibeItemListaBaseActivity;
import br.com.lojadigicom.coletorprecocliente.tela.base.activity.detalhe.OportunidadeDiaDetalheBaseActivity;

public class ListaEsperaDetalheActivity extends InteresseProdutoExibeItemListaBaseActivity {

    private ImageView imgProdutoEspera;
    private TextView txtNomeProdutoEscolha;
    private TextView txtPrecoProdutoEscolha;
    private Switch btnEspera;
    private Switch btnMonitora;
    private Button btnAssinaturaMonitora;

    // Passar para arquitetura ? (30-11-2016)
    private InteresseProduto item;
    private InteresseProdutoServicoApp srvInteresse = FabricaServicoApp.getInstancia().getInteresseProdutoServico();

    private ConstantesBilling constantesBilling = null;

    private final int BTN_ESPERA = 1;
    private final int BTN_MONITORA = 2;

    private UsuarioPesquisaServicoImpl usuarioServico = (UsuarioPesquisaServicoImpl) FabricaServicoApp.getInstancia().getUsuarioPesquisaServico();


    @Override
    protected void complementaOnCreate(long idInteresseProduto) {
        imgProdutoEspera = (ImageView) this.findViewById(R.id.imgProduto);
        txtNomeProdutoEscolha = (TextView) this.findViewById(R.id.txtNomeProduto);
        txtPrecoProdutoEscolha = (TextView) this.findViewById(R.id.txtPrecoProduto);
        btnEspera = (Switch) this.findViewById(R.id.btnEspera);
        btnMonitora = (Switch) this.findViewById(R.id.btnMonitora);
        btnAssinaturaMonitora = (Button) this.findViewById(R.id.btnAssinaturaMonitora);

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

        DCAplicacao app = (DCAplicacao) getApplication();
        constantesBilling = app.getBilling();
        usuarioServico.verificaLimites(this);
        if (usuarioServico.permiteMonitorar(constantesBilling)) {
            btnAssinaturaMonitora.setVisibility(View.GONE);
            btnMonitora.setVisibility(View.VISIBLE);
        } else {
            btnAssinaturaMonitora.setVisibility(View.VISIBLE);
            btnMonitora.setVisibility(View.GONE);
        }

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
        return new ResourceObj(R.layout.activity_lista_espera_detalhe_v4,"R.layout.activity_lista_espera_detalhe_v4");
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
        String textoPreco = "Valor em " + item.getDataDDMMAAAA() + " : R$ " + produto.getPrecoAtualTela();
        txtPrecoProdutoEscolha.setText(textoPreco);

        btnEspera.setChecked(item.getEspera());
        btnMonitora.setChecked(item.getMonitora());
    }

    @Override
    protected int getConstColunaNomeTitulo() {
        return 0;
    }



    @Override
    protected int getIdToolbar() {
        return R.id.toolbar_detalhe_produto;
    }

    @Override
    public void onTransitionEnd(Transition transition) {
    }
    @Override
    protected void esconderElementosParaPosAnimacao() {
    }
}
