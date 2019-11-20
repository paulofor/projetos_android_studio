package br.com.lojadigicom.coletorprecocliente.servico;

/**
 * Created by Paulo on 23/12/2016.
 */


// Em alguma momento passar isso para template entre 23-01-2017 ate 23-02-2017
public class FabricaServicoApp {

    private static FabricaServicoApp fabrica = new FabricaServicoApp();

    public static FabricaServicoApp getInstancia() {
        return fabrica;
    }

    private UsuarioPesquisaServicoApp usuarioPesquisaServico = null;
    private InteresseProdutoServicoApp interesseProdutoServico = null;

    public UsuarioPesquisaServicoApp getUsuarioPesquisaServico() {
        if (usuarioPesquisaServico==null) {
            usuarioPesquisaServico = new UsuarioPesquisaServicoImpl();
        }
        return usuarioPesquisaServico;
    }

    public InteresseProdutoServicoApp getInteresseProdutoServico() {
        if (interesseProdutoServico==null) {
            interesseProdutoServico = new InteresseProdutoServicoImpl();
        }
        return interesseProdutoServico;
    }
}
