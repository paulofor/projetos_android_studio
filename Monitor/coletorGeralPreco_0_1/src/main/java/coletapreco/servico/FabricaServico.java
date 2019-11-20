package coletapreco.servico;

import coletapreco.servico.sqlite.impl.*;
import coletapreco.servico.wsjson.impl.*;
//import coletapreco.servico.*;

public class FabricaServico {

	static FabricaServico fabrica = new FabricaServico();
	public static final int TIPO_SQLITE = 1;
	public static final int TIPO_WSJSON = 2;
	
	public static FabricaServico getInstancia() {
		return fabrica;
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
	

	private ModeloProdutoServico modeloProdutoServicoSqlite = null;
	private ModeloProdutoServico modeloProdutoServicoJson = null;
	
	public ModeloProdutoServico getModeloProdutoServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (modeloProdutoServicoSqlite==null) {
				modeloProdutoServicoSqlite = new ModeloProdutoServicoSqliteImpl();
			}
			return modeloProdutoServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (modeloProdutoServicoJson==null) {
				modeloProdutoServicoJson = new ModeloProdutoServicoWsJsonImpl();
			}
			return modeloProdutoServicoJson;
		}
		return null;
	}
	

	private ModeloProdutoProdutoServico modeloProdutoProdutoServicoSqlite = null;
	private ModeloProdutoProdutoServico modeloProdutoProdutoServicoJson = null;
	
	public ModeloProdutoProdutoServico getModeloProdutoProdutoServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (modeloProdutoProdutoServicoSqlite==null) {
				modeloProdutoProdutoServicoSqlite = new ModeloProdutoProdutoServicoSqliteImpl();
			}
			return modeloProdutoProdutoServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (modeloProdutoProdutoServicoJson==null) {
				modeloProdutoProdutoServicoJson = new ModeloProdutoProdutoServicoWsJsonImpl();
			}
			return modeloProdutoProdutoServicoJson;
		}
		return null;
	}
	

	private LojaVirtualServico lojaVirtualServicoSqlite = null;
	private LojaVirtualServico lojaVirtualServicoJson = null;
	
	public LojaVirtualServico getLojaVirtualServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (lojaVirtualServicoSqlite==null) {
				lojaVirtualServicoSqlite = new LojaVirtualServicoSqliteImpl();
			}
			return lojaVirtualServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (lojaVirtualServicoJson==null) {
				lojaVirtualServicoJson = new LojaVirtualServicoWsJsonImpl();
			}
			return lojaVirtualServicoJson;
		}
		return null;
	}
	

	private MarcaServico marcaServicoSqlite = null;
	private MarcaServico marcaServicoJson = null;
	
	public MarcaServico getMarcaServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (marcaServicoSqlite==null) {
				marcaServicoSqlite = new MarcaServicoSqliteImpl();
			}
			return marcaServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (marcaServicoJson==null) {
				marcaServicoJson = new MarcaServicoWsJsonImpl();
			}
			return marcaServicoJson;
		}
		return null;
	}
	

	private CategoriaLojaServico categoriaLojaServicoSqlite = null;
	private CategoriaLojaServico categoriaLojaServicoJson = null;
	
	public CategoriaLojaServico getCategoriaLojaServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (categoriaLojaServicoSqlite==null) {
				categoriaLojaServicoSqlite = new CategoriaLojaServicoSqliteImpl();
			}
			return categoriaLojaServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (categoriaLojaServicoJson==null) {
				categoriaLojaServicoJson = new CategoriaLojaServicoWsJsonImpl();
			}
			return categoriaLojaServicoJson;
		}
		return null;
	}
	

	private CategoriaLojaProdutoServico categoriaLojaProdutoServicoSqlite = null;
	private CategoriaLojaProdutoServico categoriaLojaProdutoServicoJson = null;
	
	public CategoriaLojaProdutoServico getCategoriaLojaProdutoServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (categoriaLojaProdutoServicoSqlite==null) {
				categoriaLojaProdutoServicoSqlite = new CategoriaLojaProdutoServicoSqliteImpl();
			}
			return categoriaLojaProdutoServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (categoriaLojaProdutoServicoJson==null) {
				categoriaLojaProdutoServicoJson = new CategoriaLojaProdutoServicoWsJsonImpl();
			}
			return categoriaLojaProdutoServicoJson;
		}
		return null;
	}
	

	private NaturezaProdutoServico naturezaProdutoServicoSqlite = null;
	private NaturezaProdutoServico naturezaProdutoServicoJson = null;
	
	public NaturezaProdutoServico getNaturezaProdutoServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (naturezaProdutoServicoSqlite==null) {
				naturezaProdutoServicoSqlite = new NaturezaProdutoServicoSqliteImpl();
			}
			return naturezaProdutoServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (naturezaProdutoServicoJson==null) {
				naturezaProdutoServicoJson = new NaturezaProdutoServicoWsJsonImpl();
			}
			return naturezaProdutoServicoJson;
		}
		return null;
	}
	

	private OportunidadeDiaServico oportunidadeDiaServicoSqlite = null;
	private OportunidadeDiaServico oportunidadeDiaServicoJson = null;
	
	public OportunidadeDiaServico getOportunidadeDiaServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (oportunidadeDiaServicoSqlite==null) {
				oportunidadeDiaServicoSqlite = new OportunidadeDiaServicoSqliteImpl();
			}
			return oportunidadeDiaServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (oportunidadeDiaServicoJson==null) {
				oportunidadeDiaServicoJson = new OportunidadeDiaServicoWsJsonImpl();
			}
			return oportunidadeDiaServicoJson;
		}
		return null;
	}
	

	private LojaNaturezaServico lojaNaturezaServicoSqlite = null;
	private LojaNaturezaServico lojaNaturezaServicoJson = null;
	
	public LojaNaturezaServico getLojaNaturezaServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (lojaNaturezaServicoSqlite==null) {
				lojaNaturezaServicoSqlite = new LojaNaturezaServicoSqliteImpl();
			}
			return lojaNaturezaServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (lojaNaturezaServicoJson==null) {
				lojaNaturezaServicoJson = new LojaNaturezaServicoWsJsonImpl();
			}
			return lojaNaturezaServicoJson;
		}
		return null;
	}
	

	private PalavraServico palavraServicoSqlite = null;
	private PalavraServico palavraServicoJson = null;
	
	public PalavraServico getPalavraServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (palavraServicoSqlite==null) {
				palavraServicoSqlite = new PalavraServicoSqliteImpl();
			}
			return palavraServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (palavraServicoJson==null) {
				palavraServicoJson = new PalavraServicoWsJsonImpl();
			}
			return palavraServicoJson;
		}
		return null;
	}
	

	private PalavraProdutoServico palavraProdutoServicoSqlite = null;
	private PalavraProdutoServico palavraProdutoServicoJson = null;
	
	public PalavraProdutoServico getPalavraProdutoServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (palavraProdutoServicoSqlite==null) {
				palavraProdutoServicoSqlite = new PalavraProdutoServicoSqliteImpl();
			}
			return palavraProdutoServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (palavraProdutoServicoJson==null) {
				palavraProdutoServicoJson = new PalavraProdutoServicoWsJsonImpl();
			}
			return palavraProdutoServicoJson;
		}
		return null;
	}
	

	private UsuarioPesquisaServico usuarioPesquisaServicoSqlite = null;
	private UsuarioPesquisaServico usuarioPesquisaServicoJson = null;
	
	public UsuarioPesquisaServico getUsuarioPesquisaServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (usuarioPesquisaServicoSqlite==null) {
				usuarioPesquisaServicoSqlite = new UsuarioPesquisaServicoSqliteImpl();
			}
			return usuarioPesquisaServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (usuarioPesquisaServicoJson==null) {
				usuarioPesquisaServicoJson = new UsuarioPesquisaServicoWsJsonImpl();
			}
			return usuarioPesquisaServicoJson;
		}
		return null;
	}
	
}

