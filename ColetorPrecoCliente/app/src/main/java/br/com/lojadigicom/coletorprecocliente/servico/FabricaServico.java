package br.com.lojadigicom.coletorprecocliente.servico;

/**
 * Created by Paulo on 24/02/16.
 */
public class FabricaServico {

    static FabricaServico fabrica = new FabricaServico();

    public static FabricaServico getInstancia() {
        return fabrica;
    }

    private UsuarioPesquisaServico usuarioPesquisaServico = null;
    private InteresseProdutoServico interesseProdutoServico = null;

    public UsuarioPesquisaServico getUsuarioPesquisaServico() {
       if (usuarioPesquisaServico==null) {
           usuarioPesquisaServico = new UsuarioPesquisaServicoImpl();
       }
       return usuarioPesquisaServico;
    }

    public InteresseProdutoServico getInteresseProdutoServico() {
        if (interesseProdutoServico==null) {
            interesseProdutoServico = new InteresseProdutoServicoImpl();
        }
        return interesseProdutoServico;
    }
}
