package repcom.servico;

import repcom.servico.sqlite.impl.*;
import repcom.servico.wsjson.impl.*;
//import repcom.servico.*;

public class FabricaServico {

	static FabricaServico fabrica = new FabricaServico();
	public static final int TIPO_SQLITE = 1;
	public static final int TIPO_WSJSON = 2;
	
	public static FabricaServico getInstancia() {
		return fabrica;
	}


	private ClienteServico clienteServicoSqlite = null;
	private ClienteServico clienteServicoJson = null;
	
	public ClienteServico getClienteServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (clienteServicoSqlite==null) {
				clienteServicoSqlite = new ClienteServicoSqliteImpl();
			}
			return clienteServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (clienteServicoJson==null) {
				clienteServicoJson = new ClienteServicoWsJsonImpl();
			}
			return clienteServicoJson;
		}
		return null;
	}
	

	private ProdutoServico produtoServicoSqlite = null;
	private ProdutoServico produtoServicoJson = null;
	
	public ProdutoServico getProdutoServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (produtoServicoSqlite==null) {
				produtoServicoSqlite = new ProdutoServicoSqliteImpl();
			}
			return produtoServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (produtoServicoJson==null) {
				produtoServicoJson = new ProdutoServicoWsJsonImpl();
			}
			return produtoServicoJson;
		}
		return null;
	}
	

	private CategoriaProdutoServico categoriaProdutoServicoSqlite = null;
	private CategoriaProdutoServico categoriaProdutoServicoJson = null;
	
	public CategoriaProdutoServico getCategoriaProdutoServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (categoriaProdutoServicoSqlite==null) {
				categoriaProdutoServicoSqlite = new CategoriaProdutoServicoSqliteImpl();
			}
			return categoriaProdutoServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (categoriaProdutoServicoJson==null) {
				categoriaProdutoServicoJson = new CategoriaProdutoServicoWsJsonImpl();
			}
			return categoriaProdutoServicoJson;
		}
		return null;
	}
	

	private PedidoFornecedorServico pedidoFornecedorServicoSqlite = null;
	private PedidoFornecedorServico pedidoFornecedorServicoJson = null;
	
	public PedidoFornecedorServico getPedidoFornecedorServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (pedidoFornecedorServicoSqlite==null) {
				pedidoFornecedorServicoSqlite = new PedidoFornecedorServicoSqliteImpl();
			}
			return pedidoFornecedorServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (pedidoFornecedorServicoJson==null) {
				pedidoFornecedorServicoJson = new PedidoFornecedorServicoWsJsonImpl();
			}
			return pedidoFornecedorServicoJson;
		}
		return null;
	}
	

	private VendaServico vendaServicoSqlite = null;
	private VendaServico vendaServicoJson = null;
	
	public VendaServico getVendaServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (vendaServicoSqlite==null) {
				vendaServicoSqlite = new VendaServicoSqliteImpl();
			}
			return vendaServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (vendaServicoJson==null) {
				vendaServicoJson = new VendaServicoWsJsonImpl();
			}
			return vendaServicoJson;
		}
		return null;
	}
	

	private ContatoClienteServico contatoClienteServicoSqlite = null;
	private ContatoClienteServico contatoClienteServicoJson = null;
	
	public ContatoClienteServico getContatoClienteServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (contatoClienteServicoSqlite==null) {
				contatoClienteServicoSqlite = new ContatoClienteServicoSqliteImpl();
			}
			return contatoClienteServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (contatoClienteServicoJson==null) {
				contatoClienteServicoJson = new ContatoClienteServicoWsJsonImpl();
			}
			return contatoClienteServicoJson;
		}
		return null;
	}
	

	private LinhaProdutoServico linhaProdutoServicoSqlite = null;
	private LinhaProdutoServico linhaProdutoServicoJson = null;
	
	public LinhaProdutoServico getLinhaProdutoServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (linhaProdutoServicoSqlite==null) {
				linhaProdutoServicoSqlite = new LinhaProdutoServicoSqliteImpl();
			}
			return linhaProdutoServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (linhaProdutoServicoJson==null) {
				linhaProdutoServicoJson = new LinhaProdutoServicoWsJsonImpl();
			}
			return linhaProdutoServicoJson;
		}
		return null;
	}
	

	private ProdutoPedidoFornecedorServico produtoPedidoFornecedorServicoSqlite = null;
	private ProdutoPedidoFornecedorServico produtoPedidoFornecedorServicoJson = null;
	
	public ProdutoPedidoFornecedorServico getProdutoPedidoFornecedorServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (produtoPedidoFornecedorServicoSqlite==null) {
				produtoPedidoFornecedorServicoSqlite = new ProdutoPedidoFornecedorServicoSqliteImpl();
			}
			return produtoPedidoFornecedorServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (produtoPedidoFornecedorServicoJson==null) {
				produtoPedidoFornecedorServicoJson = new ProdutoPedidoFornecedorServicoWsJsonImpl();
			}
			return produtoPedidoFornecedorServicoJson;
		}
		return null;
	}
	

	private ProdutoVendaServico produtoVendaServicoSqlite = null;
	private ProdutoVendaServico produtoVendaServicoJson = null;
	
	public ProdutoVendaServico getProdutoVendaServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (produtoVendaServicoSqlite==null) {
				produtoVendaServicoSqlite = new ProdutoVendaServicoSqliteImpl();
			}
			return produtoVendaServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (produtoVendaServicoJson==null) {
				produtoVendaServicoJson = new ProdutoVendaServicoWsJsonImpl();
			}
			return produtoVendaServicoJson;
		}
		return null;
	}
	

	private PagamentoFornecedorServico pagamentoFornecedorServicoSqlite = null;
	private PagamentoFornecedorServico pagamentoFornecedorServicoJson = null;
	
	public PagamentoFornecedorServico getPagamentoFornecedorServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (pagamentoFornecedorServicoSqlite==null) {
				pagamentoFornecedorServicoSqlite = new PagamentoFornecedorServicoSqliteImpl();
			}
			return pagamentoFornecedorServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (pagamentoFornecedorServicoJson==null) {
				pagamentoFornecedorServicoJson = new PagamentoFornecedorServicoWsJsonImpl();
			}
			return pagamentoFornecedorServicoJson;
		}
		return null;
	}
	

	private ParcelaVendaServico parcelaVendaServicoSqlite = null;
	private ParcelaVendaServico parcelaVendaServicoJson = null;
	
	public ParcelaVendaServico getParcelaVendaServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (parcelaVendaServicoSqlite==null) {
				parcelaVendaServicoSqlite = new ParcelaVendaServicoSqliteImpl();
			}
			return parcelaVendaServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (parcelaVendaServicoJson==null) {
				parcelaVendaServicoJson = new ParcelaVendaServicoWsJsonImpl();
			}
			return parcelaVendaServicoJson;
		}
		return null;
	}
	

	private ClienteInteresseCategoriaServico clienteInteresseCategoriaServicoSqlite = null;
	private ClienteInteresseCategoriaServico clienteInteresseCategoriaServicoJson = null;
	
	public ClienteInteresseCategoriaServico getClienteInteresseCategoriaServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (clienteInteresseCategoriaServicoSqlite==null) {
				clienteInteresseCategoriaServicoSqlite = new ClienteInteresseCategoriaServicoSqliteImpl();
			}
			return clienteInteresseCategoriaServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (clienteInteresseCategoriaServicoJson==null) {
				clienteInteresseCategoriaServicoJson = new ClienteInteresseCategoriaServicoWsJsonImpl();
			}
			return clienteInteresseCategoriaServicoJson;
		}
		return null;
	}
	

	private EstoqueServico estoqueServicoSqlite = null;
	private EstoqueServico estoqueServicoJson = null;
	
	public EstoqueServico getEstoqueServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (estoqueServicoSqlite==null) {
				estoqueServicoSqlite = new EstoqueServicoSqliteImpl();
			}
			return estoqueServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (estoqueServicoJson==null) {
				estoqueServicoJson = new EstoqueServicoWsJsonImpl();
			}
			return estoqueServicoJson;
		}
		return null;
	}
	

	private UsuarioServico usuarioServicoSqlite = null;
	private UsuarioServico usuarioServicoJson = null;
	
	public UsuarioServico getUsuarioServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (usuarioServicoSqlite==null) {
				usuarioServicoSqlite = new UsuarioServicoSqliteImpl();
			}
			return usuarioServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (usuarioServicoJson==null) {
				usuarioServicoJson = new UsuarioServicoWsJsonImpl();
			}
			return usuarioServicoJson;
		}
		return null;
	}
	

	private PrecoProdutoServico precoProdutoServicoSqlite = null;
	private PrecoProdutoServico precoProdutoServicoJson = null;
	
	public PrecoProdutoServico getPrecoProdutoServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (precoProdutoServicoSqlite==null) {
				precoProdutoServicoSqlite = new PrecoProdutoServicoSqliteImpl();
			}
			return precoProdutoServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (precoProdutoServicoJson==null) {
				precoProdutoServicoJson = new PrecoProdutoServicoWsJsonImpl();
			}
			return precoProdutoServicoJson;
		}
		return null;
	}
	

	private CategoriaProdutoProdutoServico categoriaProdutoProdutoServicoSqlite = null;
	private CategoriaProdutoProdutoServico categoriaProdutoProdutoServicoJson = null;
	
	public CategoriaProdutoProdutoServico getCategoriaProdutoProdutoServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (categoriaProdutoProdutoServicoSqlite==null) {
				categoriaProdutoProdutoServicoSqlite = new CategoriaProdutoProdutoServicoSqliteImpl();
			}
			return categoriaProdutoProdutoServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (categoriaProdutoProdutoServicoJson==null) {
				categoriaProdutoProdutoServicoJson = new CategoriaProdutoProdutoServicoWsJsonImpl();
			}
			return categoriaProdutoProdutoServicoJson;
		}
		return null;
	}
	

	private MesAnoServico mesAnoServicoSqlite = null;
	private MesAnoServico mesAnoServicoJson = null;
	
	public MesAnoServico getMesAnoServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (mesAnoServicoSqlite==null) {
				mesAnoServicoSqlite = new MesAnoServicoSqliteImpl();
			}
			return mesAnoServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (mesAnoServicoJson==null) {
				mesAnoServicoJson = new MesAnoServicoWsJsonImpl();
			}
			return mesAnoServicoJson;
		}
		return null;
	}
	

	private DispositivoUsuarioServico dispositivoUsuarioServicoSqlite = null;
	private DispositivoUsuarioServico dispositivoUsuarioServicoJson = null;
	
	public DispositivoUsuarioServico getDispositivoUsuarioServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (dispositivoUsuarioServicoSqlite==null) {
				dispositivoUsuarioServicoSqlite = new DispositivoUsuarioServicoSqliteImpl();
			}
			return dispositivoUsuarioServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (dispositivoUsuarioServicoJson==null) {
				dispositivoUsuarioServicoJson = new DispositivoUsuarioServicoWsJsonImpl();
			}
			return dispositivoUsuarioServicoJson;
		}
		return null;
	}
	
}

