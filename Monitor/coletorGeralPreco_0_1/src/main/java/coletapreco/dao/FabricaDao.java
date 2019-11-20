package coletapreco.dao;

import android.content.Context;

public class FabricaDao {

	static FabricaDao fabrica = new FabricaDao();
	
	public static FabricaDao getInstancia() {
		return fabrica;
	}


	private UsuarioDBHelper usuarioDBHelper = null;
	
	public UsuarioDBHelper getUsuarioDBHelper() {
		if (usuarioDBHelper==null) {
			usuarioDBHelper = new UsuarioDBHelper();
		}
		return usuarioDBHelper;
	}
	

	private DispositivoUsuarioDBHelper dispositivoUsuarioDBHelper = null;
	
	public DispositivoUsuarioDBHelper getDispositivoUsuarioDBHelper() {
		if (dispositivoUsuarioDBHelper==null) {
			dispositivoUsuarioDBHelper = new DispositivoUsuarioDBHelper();
		}
		return dispositivoUsuarioDBHelper;
	}
	

	private ProdutoDBHelper produtoDBHelper = null;
	
	public ProdutoDBHelper getProdutoDBHelper() {
		if (produtoDBHelper==null) {
			produtoDBHelper = new ProdutoDBHelper();
		}
		return produtoDBHelper;
	}
	

	private PrecoProdutoDBHelper precoProdutoDBHelper = null;
	
	public PrecoProdutoDBHelper getPrecoProdutoDBHelper() {
		if (precoProdutoDBHelper==null) {
			precoProdutoDBHelper = new PrecoProdutoDBHelper();
		}
		return precoProdutoDBHelper;
	}
	

	private ModeloProdutoDBHelper modeloProdutoDBHelper = null;
	
	public ModeloProdutoDBHelper getModeloProdutoDBHelper() {
		if (modeloProdutoDBHelper==null) {
			modeloProdutoDBHelper = new ModeloProdutoDBHelper();
		}
		return modeloProdutoDBHelper;
	}
	

	private ModeloProdutoProdutoDBHelper modeloProdutoProdutoDBHelper = null;
	
	public ModeloProdutoProdutoDBHelper getModeloProdutoProdutoDBHelper() {
		if (modeloProdutoProdutoDBHelper==null) {
			modeloProdutoProdutoDBHelper = new ModeloProdutoProdutoDBHelper();
		}
		return modeloProdutoProdutoDBHelper;
	}
	

	private LojaVirtualDBHelper lojaVirtualDBHelper = null;
	
	public LojaVirtualDBHelper getLojaVirtualDBHelper() {
		if (lojaVirtualDBHelper==null) {
			lojaVirtualDBHelper = new LojaVirtualDBHelper();
		}
		return lojaVirtualDBHelper;
	}
	

	private MarcaDBHelper marcaDBHelper = null;
	
	public MarcaDBHelper getMarcaDBHelper() {
		if (marcaDBHelper==null) {
			marcaDBHelper = new MarcaDBHelper();
		}
		return marcaDBHelper;
	}
	

	private CategoriaLojaDBHelper categoriaLojaDBHelper = null;
	
	public CategoriaLojaDBHelper getCategoriaLojaDBHelper() {
		if (categoriaLojaDBHelper==null) {
			categoriaLojaDBHelper = new CategoriaLojaDBHelper();
		}
		return categoriaLojaDBHelper;
	}
	

	private CategoriaLojaProdutoDBHelper categoriaLojaProdutoDBHelper = null;
	
	public CategoriaLojaProdutoDBHelper getCategoriaLojaProdutoDBHelper() {
		if (categoriaLojaProdutoDBHelper==null) {
			categoriaLojaProdutoDBHelper = new CategoriaLojaProdutoDBHelper();
		}
		return categoriaLojaProdutoDBHelper;
	}
	

	private NaturezaProdutoDBHelper naturezaProdutoDBHelper = null;
	
	public NaturezaProdutoDBHelper getNaturezaProdutoDBHelper() {
		if (naturezaProdutoDBHelper==null) {
			naturezaProdutoDBHelper = new NaturezaProdutoDBHelper();
		}
		return naturezaProdutoDBHelper;
	}
	

	private OportunidadeDiaDBHelper oportunidadeDiaDBHelper = null;
	
	public OportunidadeDiaDBHelper getOportunidadeDiaDBHelper() {
		if (oportunidadeDiaDBHelper==null) {
			oportunidadeDiaDBHelper = new OportunidadeDiaDBHelper();
		}
		return oportunidadeDiaDBHelper;
	}
	

	private LojaNaturezaDBHelper lojaNaturezaDBHelper = null;
	
	public LojaNaturezaDBHelper getLojaNaturezaDBHelper() {
		if (lojaNaturezaDBHelper==null) {
			lojaNaturezaDBHelper = new LojaNaturezaDBHelper();
		}
		return lojaNaturezaDBHelper;
	}
	

	private PalavraDBHelper palavraDBHelper = null;
	
	public PalavraDBHelper getPalavraDBHelper() {
		if (palavraDBHelper==null) {
			palavraDBHelper = new PalavraDBHelper();
		}
		return palavraDBHelper;
	}
	

	private PalavraProdutoDBHelper palavraProdutoDBHelper = null;
	
	public PalavraProdutoDBHelper getPalavraProdutoDBHelper() {
		if (palavraProdutoDBHelper==null) {
			palavraProdutoDBHelper = new PalavraProdutoDBHelper();
		}
		return palavraProdutoDBHelper;
	}
	

	private UsuarioPesquisaDBHelper usuarioPesquisaDBHelper = null;
	
	public UsuarioPesquisaDBHelper getUsuarioPesquisaDBHelper() {
		if (usuarioPesquisaDBHelper==null) {
			usuarioPesquisaDBHelper = new UsuarioPesquisaDBHelper();
		}
		return usuarioPesquisaDBHelper;
	}
	
}

