package br.com.lojadigicom.coletorprecocliente.servico.base;

import br.com.lojadigicom.coletorprecocliente.servico.impl.*;

public class FabricaServico {


	private static FabricaServico fabrica = new FabricaServico();

    public static FabricaServico getInstancia() {
        return fabrica;
    }

	private NaturezaProdutoServico naturezaProdutoServico = null;
    public NaturezaProdutoServico getNaturezaProdutoServico() {
       if (naturezaProdutoServico==null) {
           naturezaProdutoServico = new NaturezaProdutoServicoImpl();
       }
       return naturezaProdutoServico;
    }
	private OportunidadeDiaServico oportunidadeDiaServico = null;
    public OportunidadeDiaServico getOportunidadeDiaServico() {
       if (oportunidadeDiaServico==null) {
           oportunidadeDiaServico = new OportunidadeDiaServicoImpl();
       }
       return oportunidadeDiaServico;
    }
	private PrecoDiarioClienteServico precoDiarioClienteServico = null;
    public PrecoDiarioClienteServico getPrecoDiarioClienteServico() {
       if (precoDiarioClienteServico==null) {
           precoDiarioClienteServico = new PrecoDiarioClienteServicoImpl();
       }
       return precoDiarioClienteServico;
    }
	private UsuarioServico usuarioServico = null;
    public UsuarioServico getUsuarioServico() {
       if (usuarioServico==null) {
           usuarioServico = new UsuarioServicoImpl();
       }
       return usuarioServico;
    }
	private DispositivoUsuarioServico dispositivoUsuarioServico = null;
    public DispositivoUsuarioServico getDispositivoUsuarioServico() {
       if (dispositivoUsuarioServico==null) {
           dispositivoUsuarioServico = new DispositivoUsuarioServicoImpl();
       }
       return dispositivoUsuarioServico;
    }
	private UsuarioPesquisaServico usuarioPesquisaServico = null;
    public UsuarioPesquisaServico getUsuarioPesquisaServico() {
       if (usuarioPesquisaServico==null) {
           usuarioPesquisaServico = new UsuarioPesquisaServicoImpl();
       }
       return usuarioPesquisaServico;
    }
	private ProdutoClienteServico produtoClienteServico = null;
    public ProdutoClienteServico getProdutoClienteServico() {
       if (produtoClienteServico==null) {
           produtoClienteServico = new ProdutoClienteServicoImpl();
       }
       return produtoClienteServico;
    }
	private InteresseProdutoServico interesseProdutoServico = null;
    public InteresseProdutoServico getInteresseProdutoServico() {
       if (interesseProdutoServico==null) {
           interesseProdutoServico = new InteresseProdutoServicoImpl();
       }
       return interesseProdutoServico;
    }
	private PalavraChavePesquisaServico palavraChavePesquisaServico = null;
    public PalavraChavePesquisaServico getPalavraChavePesquisaServico() {
       if (palavraChavePesquisaServico==null) {
           palavraChavePesquisaServico = new PalavraChavePesquisaServicoImpl();
       }
       return palavraChavePesquisaServico;
    }
	private AppProdutoServico appProdutoServico = null;
    public AppProdutoServico getAppProdutoServico() {
       if (appProdutoServico==null) {
           appProdutoServico = new AppProdutoServicoImpl();
       }
       return appProdutoServico;
    }
	private OportunidadeInteresseClienteServico oportunidadeInteresseClienteServico = null;
    public OportunidadeInteresseClienteServico getOportunidadeInteresseClienteServico() {
       if (oportunidadeInteresseClienteServico==null) {
           oportunidadeInteresseClienteServico = new OportunidadeInteresseClienteServicoImpl();
       }
       return oportunidadeInteresseClienteServico;
    }
	private CompartilhamentoProdutoServico compartilhamentoProdutoServico = null;
    public CompartilhamentoProdutoServico getCompartilhamentoProdutoServico() {
       if (compartilhamentoProdutoServico==null) {
           compartilhamentoProdutoServico = new CompartilhamentoProdutoServicoImpl();
       }
       return compartilhamentoProdutoServico;
    }
	private LocalizacaoServico localizacaoServico = null;
    public LocalizacaoServico getLocalizacaoServico() {
       if (localizacaoServico==null) {
           localizacaoServico = new LocalizacaoServicoImpl();
       }
       return localizacaoServico;
    }
	private AcaoClienteServico acaoClienteServico = null;
    public AcaoClienteServico getAcaoClienteServico() {
       if (acaoClienteServico==null) {
           acaoClienteServico = new AcaoClienteServicoImpl();
       }
       return acaoClienteServico;
    }
	private MensagemServico mensagemServico = null;
    public MensagemServico getMensagemServico() {
       if (mensagemServico==null) {
           mensagemServico = new MensagemServicoImpl();
       }
       return mensagemServico;
    }
}