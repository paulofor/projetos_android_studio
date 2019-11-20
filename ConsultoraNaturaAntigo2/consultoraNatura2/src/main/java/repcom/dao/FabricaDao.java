package repcom.dao;

import android.content.Context;

public class FabricaDao {

	static FabricaDao fabrica = new FabricaDao();
	
	public static FabricaDao getInstancia() {
		return fabrica;
	}


	private ClienteDBHelper clienteDBHelper = null;
	
	public ClienteDBHelper getClienteDBHelper() {
		if (clienteDBHelper==null) {
			clienteDBHelper = new ClienteDBHelper();
		}
		return clienteDBHelper;
	}
	

	private ProdutoDBHelper produtoDBHelper = null;
	
	public ProdutoDBHelper getProdutoDBHelper() {
		if (produtoDBHelper==null) {
			produtoDBHelper = new ProdutoDBHelper();
		}
		return produtoDBHelper;
	}
	

	private CategoriaProdutoDBHelper categoriaProdutoDBHelper = null;
	
	public CategoriaProdutoDBHelper getCategoriaProdutoDBHelper() {
		if (categoriaProdutoDBHelper==null) {
			categoriaProdutoDBHelper = new CategoriaProdutoDBHelper();
		}
		return categoriaProdutoDBHelper;
	}
	

	private PedidoFornecedorDBHelper pedidoFornecedorDBHelper = null;
	
	public PedidoFornecedorDBHelper getPedidoFornecedorDBHelper() {
		if (pedidoFornecedorDBHelper==null) {
			pedidoFornecedorDBHelper = new PedidoFornecedorDBHelper();
		}
		return pedidoFornecedorDBHelper;
	}
	

	private VendaDBHelper vendaDBHelper = null;
	
	public VendaDBHelper getVendaDBHelper() {
		if (vendaDBHelper==null) {
			vendaDBHelper = new VendaDBHelper();
		}
		return vendaDBHelper;
	}
	

	private ContatoClienteDBHelper contatoClienteDBHelper = null;
	
	public ContatoClienteDBHelper getContatoClienteDBHelper() {
		if (contatoClienteDBHelper==null) {
			contatoClienteDBHelper = new ContatoClienteDBHelper();
		}
		return contatoClienteDBHelper;
	}
	

	private LinhaProdutoDBHelper linhaProdutoDBHelper = null;
	
	public LinhaProdutoDBHelper getLinhaProdutoDBHelper() {
		if (linhaProdutoDBHelper==null) {
			linhaProdutoDBHelper = new LinhaProdutoDBHelper();
		}
		return linhaProdutoDBHelper;
	}
	

	private ProdutoPedidoFornecedorDBHelper produtoPedidoFornecedorDBHelper = null;
	
	public ProdutoPedidoFornecedorDBHelper getProdutoPedidoFornecedorDBHelper() {
		if (produtoPedidoFornecedorDBHelper==null) {
			produtoPedidoFornecedorDBHelper = new ProdutoPedidoFornecedorDBHelper();
		}
		return produtoPedidoFornecedorDBHelper;
	}
	

	private ProdutoVendaDBHelper produtoVendaDBHelper = null;
	
	public ProdutoVendaDBHelper getProdutoVendaDBHelper() {
		if (produtoVendaDBHelper==null) {
			produtoVendaDBHelper = new ProdutoVendaDBHelper();
		}
		return produtoVendaDBHelper;
	}
	

	private PagamentoFornecedorDBHelper pagamentoFornecedorDBHelper = null;
	
	public PagamentoFornecedorDBHelper getPagamentoFornecedorDBHelper() {
		if (pagamentoFornecedorDBHelper==null) {
			pagamentoFornecedorDBHelper = new PagamentoFornecedorDBHelper();
		}
		return pagamentoFornecedorDBHelper;
	}
	

	private ParcelaVendaDBHelper parcelaVendaDBHelper = null;
	
	public ParcelaVendaDBHelper getParcelaVendaDBHelper() {
		if (parcelaVendaDBHelper==null) {
			parcelaVendaDBHelper = new ParcelaVendaDBHelper();
		}
		return parcelaVendaDBHelper;
	}
	

	private ClienteInteresseCategoriaDBHelper clienteInteresseCategoriaDBHelper = null;
	
	public ClienteInteresseCategoriaDBHelper getClienteInteresseCategoriaDBHelper() {
		if (clienteInteresseCategoriaDBHelper==null) {
			clienteInteresseCategoriaDBHelper = new ClienteInteresseCategoriaDBHelper();
		}
		return clienteInteresseCategoriaDBHelper;
	}
	

	private EstoqueDBHelper estoqueDBHelper = null;
	
	public EstoqueDBHelper getEstoqueDBHelper() {
		if (estoqueDBHelper==null) {
			estoqueDBHelper = new EstoqueDBHelper();
		}
		return estoqueDBHelper;
	}
	

	private UsuarioDBHelper usuarioDBHelper = null;
	
	public UsuarioDBHelper getUsuarioDBHelper() {
		if (usuarioDBHelper==null) {
			usuarioDBHelper = new UsuarioDBHelper();
		}
		return usuarioDBHelper;
	}
	

	private PrecoProdutoDBHelper precoProdutoDBHelper = null;
	
	public PrecoProdutoDBHelper getPrecoProdutoDBHelper() {
		if (precoProdutoDBHelper==null) {
			precoProdutoDBHelper = new PrecoProdutoDBHelper();
		}
		return precoProdutoDBHelper;
	}
	

	private CategoriaProdutoProdutoDBHelper categoriaProdutoProdutoDBHelper = null;
	
	public CategoriaProdutoProdutoDBHelper getCategoriaProdutoProdutoDBHelper() {
		if (categoriaProdutoProdutoDBHelper==null) {
			categoriaProdutoProdutoDBHelper = new CategoriaProdutoProdutoDBHelper();
		}
		return categoriaProdutoProdutoDBHelper;
	}
	

	private MesAnoDBHelper mesAnoDBHelper = null;
	
	public MesAnoDBHelper getMesAnoDBHelper() {
		if (mesAnoDBHelper==null) {
			mesAnoDBHelper = new MesAnoDBHelper();
		}
		return mesAnoDBHelper;
	}
	

	private DispositivoUsuarioDBHelper dispositivoUsuarioDBHelper = null;
	
	public DispositivoUsuarioDBHelper getDispositivoUsuarioDBHelper() {
		if (dispositivoUsuarioDBHelper==null) {
			dispositivoUsuarioDBHelper = new DispositivoUsuarioDBHelper();
		}
		return dispositivoUsuarioDBHelper;
	}
	
}

