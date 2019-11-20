package br.com.lojadigicom.repcom.servico.base;

import br.com.lojadigicom.repcom.servico.impl.*;

public class FabricaServico {


	private static FabricaServico fabrica = new FabricaServico();

    public static FabricaServico getInstancia() {
        return fabrica;
    }

	private ClienteServico clienteServico = null;
    public ClienteServico getClienteServico() {
       if (clienteServico==null) {
           clienteServico = new ClienteServicoImpl();
       }
       return clienteServico;
    }
	private ProdutoServico produtoServico = null;
    public ProdutoServico getProdutoServico() {
       if (produtoServico==null) {
           produtoServico = new ProdutoServicoImpl();
       }
       return produtoServico;
    }
	private CategoriaProdutoServico categoriaProdutoServico = null;
    public CategoriaProdutoServico getCategoriaProdutoServico() {
       if (categoriaProdutoServico==null) {
           categoriaProdutoServico = new CategoriaProdutoServicoImpl();
       }
       return categoriaProdutoServico;
    }
	private PedidoFornecedorServico pedidoFornecedorServico = null;
    public PedidoFornecedorServico getPedidoFornecedorServico() {
       if (pedidoFornecedorServico==null) {
           pedidoFornecedorServico = new PedidoFornecedorServicoImpl();
       }
       return pedidoFornecedorServico;
    }
	private VendaServico vendaServico = null;
    public VendaServico getVendaServico() {
       if (vendaServico==null) {
           vendaServico = new VendaServicoImpl();
       }
       return vendaServico;
    }
	private ContatoClienteServico contatoClienteServico = null;
    public ContatoClienteServico getContatoClienteServico() {
       if (contatoClienteServico==null) {
           contatoClienteServico = new ContatoClienteServicoImpl();
       }
       return contatoClienteServico;
    }
	private LinhaProdutoServico linhaProdutoServico = null;
    public LinhaProdutoServico getLinhaProdutoServico() {
       if (linhaProdutoServico==null) {
           linhaProdutoServico = new LinhaProdutoServicoImpl();
       }
       return linhaProdutoServico;
    }
	private ProdutoPedidoFornecedorServico produtoPedidoFornecedorServico = null;
    public ProdutoPedidoFornecedorServico getProdutoPedidoFornecedorServico() {
       if (produtoPedidoFornecedorServico==null) {
           produtoPedidoFornecedorServico = new ProdutoPedidoFornecedorServicoImpl();
       }
       return produtoPedidoFornecedorServico;
    }
	private ProdutoVendaServico produtoVendaServico = null;
    public ProdutoVendaServico getProdutoVendaServico() {
       if (produtoVendaServico==null) {
           produtoVendaServico = new ProdutoVendaServicoImpl();
       }
       return produtoVendaServico;
    }
	private PagamentoFornecedorServico pagamentoFornecedorServico = null;
    public PagamentoFornecedorServico getPagamentoFornecedorServico() {
       if (pagamentoFornecedorServico==null) {
           pagamentoFornecedorServico = new PagamentoFornecedorServicoImpl();
       }
       return pagamentoFornecedorServico;
    }
	private ParcelaVendaServico parcelaVendaServico = null;
    public ParcelaVendaServico getParcelaVendaServico() {
       if (parcelaVendaServico==null) {
           parcelaVendaServico = new ParcelaVendaServicoImpl();
       }
       return parcelaVendaServico;
    }
	private ClienteInteresseCategoriaServico clienteInteresseCategoriaServico = null;
    public ClienteInteresseCategoriaServico getClienteInteresseCategoriaServico() {
       if (clienteInteresseCategoriaServico==null) {
           clienteInteresseCategoriaServico = new ClienteInteresseCategoriaServicoImpl();
       }
       return clienteInteresseCategoriaServico;
    }
	private EstoqueServico estoqueServico = null;
    public EstoqueServico getEstoqueServico() {
       if (estoqueServico==null) {
           estoqueServico = new EstoqueServicoImpl();
       }
       return estoqueServico;
    }
	private UsuarioServico usuarioServico = null;
    public UsuarioServico getUsuarioServico() {
       if (usuarioServico==null) {
           usuarioServico = new UsuarioServicoImpl();
       }
       return usuarioServico;
    }
	private PrecoProdutoServico precoProdutoServico = null;
    public PrecoProdutoServico getPrecoProdutoServico() {
       if (precoProdutoServico==null) {
           precoProdutoServico = new PrecoProdutoServicoImpl();
       }
       return precoProdutoServico;
    }
	private CategoriaProdutoProdutoServico categoriaProdutoProdutoServico = null;
    public CategoriaProdutoProdutoServico getCategoriaProdutoProdutoServico() {
       if (categoriaProdutoProdutoServico==null) {
           categoriaProdutoProdutoServico = new CategoriaProdutoProdutoServicoImpl();
       }
       return categoriaProdutoProdutoServico;
    }
	private MesAnoServico mesAnoServico = null;
    public MesAnoServico getMesAnoServico() {
       if (mesAnoServico==null) {
           mesAnoServico = new MesAnoServicoImpl();
       }
       return mesAnoServico;
    }
	private DispositivoUsuarioServico dispositivoUsuarioServico = null;
    public DispositivoUsuarioServico getDispositivoUsuarioServico() {
       if (dispositivoUsuarioServico==null) {
           dispositivoUsuarioServico = new DispositivoUsuarioServicoImpl();
       }
       return dispositivoUsuarioServico;
    }
	private ErroExceptionServico erroExceptionServico = null;
    public ErroExceptionServico getErroExceptionServico() {
       if (erroExceptionServico==null) {
           erroExceptionServico = new ErroExceptionServicoImpl();
       }
       return erroExceptionServico;
    }
}