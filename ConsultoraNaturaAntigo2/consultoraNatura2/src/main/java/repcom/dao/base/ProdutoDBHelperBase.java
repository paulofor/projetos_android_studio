package repcom.dao.base;

import java.util.ArrayList;
import java.util.List;
import br.com.digicom.Constants;
import br.com.digicom.dao.DBHelperBase;
import br.com.digicom.dao.MontadorDaoComposite;
import br.com.digicom.dao.MontadorDaoI;
import br.com.digicom.modelo.ObjetoSinc;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.dao.DaoException;
import br.com.digicom.log.DCLog;
import br.com.digicom.dao.DaoSincronismo;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.util.Log;
import repcom.modelo.*;
import repcom.modelo.vo.ProdutoVo;
import repcom.modelo.vo.FabricaVo;
import repcom.dao.*;
import repcom.dao.montador.*;
import repcom.dao.datasource.DataSourceAplicacao;

public abstract class ProdutoDBHelperBase extends DBHelperBase
	implements DaoSincronismo // coloquei aqui para evitar impacto de arquitetura
{

    private static final String DB_NAME = "w_alert";
    public static final String DB_TABLE = "produto";
    public static final String DB_TABLE_SINC = "produto_sinc";
    public static final int DB_VERSION = 3;

    protected static final String CLASSNAME = ProdutoDBHelperBase.class.getSimpleName();
    protected static final String[] COLS = new String[] { 
        "id_produto"
        ,"nome"
        ,"url"
        ,"imagem"
        ,"tamanho_imagem"
        ,"data_inclusao"
        ,"data_exclusao"
		, "id_linha_produto_ee"
	
    };

	protected static final String[] COLS_SINC = new String[] { 
        "id_produto"
        ,"nome"
        ,"url"
        ,"imagem"
        ,"tamanho_imagem"
        ,"data_inclusao"
        ,"data_exclusao"
		, "id_linha_produto_ee"
		, "operacao_sinc"
    };

    protected SQLiteDatabase db;
    protected final DBOpenHelper dbOpenHelper;

	@Override
	protected MontadorDaoI criaMontadorPadrao() {
		return new ProdutoMontador();
	}
	
	protected String getSqlIndices() {
		return "";
	}
	protected String getSqlCreate(){
		return  "CREATE TABLE "
        + ProdutoDBHelperBase.DB_TABLE + " ("
        + "  id_produto INTEGER PRIMARY KEY "
        + " , nome TEXT "
        + " , url TEXT "
        + " , imagem TEXT "
        + " , tamanho_imagem INTEGER "
        + " , data_inclusao INTEGER "
        + " , data_exclusao INTEGER "
		+ " , id_linha_produto_ee INTEGER "
		
		+ getSqlIndices()
        + ");";
	}

	

	public static final String DB_CREATE_SINCRONIZADA = "CREATE TABLE IF NOT EXISTS "
        + ProdutoDBHelperBase.DB_TABLE_SINC + " ("
        + "  id_produto INTEGER "
        + " , nome TEXT "
        + " , url TEXT "
        + " , imagem TEXT "
        + " , tamanho_imagem INTEGER "
        + " , data_inclusao INTEGER "
        + " , data_exclusao INTEGER "
		+ " , id_linha_produto_ee INTEGER "
		
        + ", operacao_sinc TEXT);";


    public static final String DB_CREATE = "CREATE TABLE IF NOT EXISTS "
        + ProdutoDBHelperBase.DB_TABLE + " ("
        + "  id_produto INTEGER PRIMARY KEY "
        + " , nome TEXT "
        + " , url TEXT "
        + " , imagem TEXT "
        + " , tamanho_imagem INTEGER "
        + " , data_inclusao INTEGER "
        + " , data_exclusao INTEGER "
		+ " , id_linha_produto_ee INTEGER "
		
        + ");";
    
    private static final String DB_DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private static final String DB_DROP = "DROP TABLE IF EXISTS " + DB_TABLE;
    private static final String DB_DROP_SINCRONIZADA = "DROP TABLE IF EXISTS " + DB_TABLE_SINC;
    
    private static class DBOpenHelper extends SQLiteOpenHelper {

       

        public DBOpenHelper(final Context context) {
            super(context, ProdutoDBHelperBase.DB_NAME, null, ProdutoDBHelperBase.DB_VERSION);
        }

        @Override
        public void onCreate(final SQLiteDatabase db) {
            try {
                db.execSQL(DB_CREATE);
            } catch (SQLException e) {
                Log.e(Constants.LOGTAG, ProdutoDBHelperBase.CLASSNAME, e);
            }
        }

        @Override
        public void onOpen(final SQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + ProdutoDBHelperBase.DB_TABLE);
            onCreate(db);
        }
    }

    //
    // end inner classes
    //
    
    // Versao Nova
 	public ProdutoDBHelperBase() {
    	this.dbOpenHelper = null;
    	setDataSource(DataSourceAplicacao.getInstancia());
    }
    
   
	
	protected ContentValues montaValores(final DCIObjetoDominio valor) {
		Produto item = (Produto) valor;
		ContentValues valores = new ContentValues();
       	putValor(valores,"id_produto",item.getIdProduto());
       	
       	putValor(valores,"nome",item.getNome());
       	
       	putValor(valores,"url",item.getUrl());
       	
       	putValor(valores,"imagem",item.getImagem());
       	
       	putValor(valores,"tamanho_imagem",item.getTamanhoImagem());
       	
       	putValorData(valores,"data_inclusao",item.getDataInclusao());
        		
       	putValorData(valores,"data_exclusao",item.getDataExclusao());
        		
       	putValor(valores,"id_linha_produto_ee",item.getIdLinhaProdutoEe());
       	
        return valores;
	}


    // **** Chamadas Diretas Objeto Banco de Dados
    private void registraErroChamadaDireta(Exception e) {
    	Log.e(Constants.LOGTAG, ProdutoDBHelperBase.CLASSNAME, e);
    }
   
    public final void insert(final Produto item) {
	    try {
	        getDb().insert(ProdutoDBHelperBase.DB_TABLE, null, montaValores(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    public final void update(final Produto item) {
	    try {
	        getDb().update(ProdutoDBHelperBase.DB_TABLE, montaValores(item), "id_produto=" + item.getIdProduto(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     // Nao pode ser final o parametro para retornar o objeto com seu Id
    public final void insertSinc(Produto item) {
        try {
        	item.setIdProduto((int)getMaxId() + 1);
        	DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc: " + item.debug());
	        long id = getDb().insert(ProdutoDBHelperBase.DB_TABLE, null, montaValores(item));
    	    ((ObjetoSinc)item).setOperacaoSinc("I");
        	getDb().insert(ProdutoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     public final void updateSinc(final Produto item) {
        try {
	        DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc: " + item.debug());
	        getDb().update(ProdutoDBHelperBase.DB_TABLE, montaValores(item), "id_produto=" + item.getIdProduto(), null);
	        ((ObjetoSinc)item).setOperacaoSinc("A");
	        Produto atual = this.getSincById(item.getId());
	        if (atual==null) {
	        	getDb().insert(ProdutoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	        } else {
	        	if ("I".equals(((ObjetoSinc)atual).getOperacaoSinc()))
	        		((ObjetoSinc)item).setOperacaoSinc("I");
	        	getDb().update(ProdutoDBHelperBase.DB_TABLE_SINC, montaValoresSinc(item), "id_produto=" + item.getIdProduto(), null);
	        }
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    protected final void delete(final long id) {
        try {
			getDb().delete(ProdutoDBHelperBase.DB_TABLE, "id_produto=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    /*
    private void deleteSinc(final long id) {
        try {
			getDb().delete(ProdutoDBHelperBase.DB_TABLE_SINC, "id_produto=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
	*/
    public void limpaSinc(final Produto item) {
    	try {
			getDb().delete(ProdutoDBHelperBase.DB_TABLE_SINC, "id_produto=" + item.getId(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    
    public void deleteSinc(final Produto item) {
    	try {
	        DCLog.dStack(DCLog.DATABASE_ADM, this, "deleteSinc: " + item.debug());
	        delete(item.getId());
	        ((ObjetoSinc)item).setOperacaoSinc("D");
        	getDb().insert(ProdutoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
   
    
	public void criaTabelaSincronizacao() {
	    try {
			getDb().execSQL(DB_CREATE_SINCRONIZADA);
		
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
	}
    public void criaTabela() {
        try {
	        getDb().execSQL(DB_CREATE);
	    
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    public void deleteAll() {
        try {
	        getDb().execSQL(DB_DELETE_ALL);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    public void dropTable() {
        try {
	        getDb().execSQL(DB_DROP);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
  
    public void dropTableSincronizacao() {
        try {
        	getDb().execSQL(DB_DROP_SINCRONIZADA);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
     }

	// *** Chamadas Indireta ( via Digicom - Base )	
    public Produto getById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (Produto) getItemQuery(true, ProdutoDBHelperBase.DB_TABLE, ProdutoDBHelperBase.COLS, "id_produto = " + id + "", null, null, null, null,null);
    }
    
    // Esta com orderBy que pode ser bom para exibicoes em tela
    // mas nao e bom para sincronizacao, pensar em ter um metodo para tela e outro para sinc.
    public List<Produto> getAll() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, null);
    }
    public List<Produto> getAllTela() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, orderByColuna());
    }
    
    private Produto getByRowId(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (Produto) getItemQuery(true, ProdutoDBHelperBase.DB_TABLE, ProdutoDBHelperBase.COLS, "ROWID = " + id + "", null, null, null, null,null);
    }
    private Produto getSincById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (Produto) getItemQuerySinc(true, ProdutoDBHelperBase.DB_TABLE_SINC, ProdutoDBHelperBase.COLS_SINC, "id_produto = " + id + "", null, null, null, null,null);
    }
    
    
    public long getMaxId() {
		String sql = "select max(id_produto) from " + DB_TABLE;
		return this.getNumeroSql(sql);
	}
	protected String orderByColuna() {
    	return null;
    }
	
	
	public List<Produto> getPorEstaEmLinhaProduto(Context contexto, long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_linha_produto_ee = " + id, null, null, null, null);
	}
	public List<Produto> getPorEstaEmLinhaProduto(long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_linha_produto_ee = " + id, null, null, null, null);
	}
	
	
  
  	
  
  
  	
	/*
	public Produto obtemPorProdutoPedidoFornecedorAssociada(long id) {
		string sql;
		sql = "select " + camposOrdenados() +
			" from " + tabelaSelect() +
			innerJoinProdutoPedidoFornecedor_Associada() + 
			" where id_produto_pedido_fornecedor = " + id;
		return (Produto) getObjeto(sql);
	}
	*/
	public static String innerJoinProdutoPedidoFornecedor_Associada() {
		return " inner join " + ProdutoPedidoFornecedorDBHelperBase.DB_TABLE + " on " + ProdutoPedidoFornecedorDBHelperBase.DB_TABLE + ".id_produto_a = " + DB_TABLE + ".id_produto ";  
	}
	public static String innerJoinSincProdutoPedidoFornecedor_Associada() {
		return " inner join " + ProdutoPedidoFornecedorDBHelperBase.DB_TABLE_SINC + " on " + ProdutoPedidoFornecedorDBHelperBase.DB_TABLE_SINC + ".id_produto_a = " + DB_TABLE_SINC + ".id_produto ";  
	}
	public static String outerJoinProdutoPedidoFornecedor_Associada() {
		return " left outer join " + ProdutoPedidoFornecedorDBHelperBase.DB_TABLE + " on " + ProdutoPedidoFornecedorDBHelperBase.DB_TABLE + ".id_produto_a = " + DB_TABLE + ".id_produto ";  
	}
	public static String outerJoinSincProdutoPedidoFornecedor_Associada() {
		return " left outer join " + ProdutoPedidoFornecedorDBHelperBase.DB_TABLE_SINC + " on " + ProdutoPedidoFornecedorDBHelperBase.DB_TABLE_SINC + ".id_produto_a = " + DB_TABLE_SINC + ".id_produto ";  
	}
 	
	/*
	public Produto obtemPorProdutoVendaAssociada(long id) {
		string sql;
		sql = "select " + camposOrdenados() +
			" from " + tabelaSelect() +
			innerJoinProdutoVenda_Associada() + 
			" where id_produto_venda = " + id;
		return (Produto) getObjeto(sql);
	}
	*/
	public static String innerJoinProdutoVenda_Associada() {
		return " inner join " + ProdutoVendaDBHelperBase.DB_TABLE + " on " + ProdutoVendaDBHelperBase.DB_TABLE + ".id_produto_a = " + DB_TABLE + ".id_produto ";  
	}
	public static String innerJoinSincProdutoVenda_Associada() {
		return " inner join " + ProdutoVendaDBHelperBase.DB_TABLE_SINC + " on " + ProdutoVendaDBHelperBase.DB_TABLE_SINC + ".id_produto_a = " + DB_TABLE_SINC + ".id_produto ";  
	}
	public static String outerJoinProdutoVenda_Associada() {
		return " left outer join " + ProdutoVendaDBHelperBase.DB_TABLE + " on " + ProdutoVendaDBHelperBase.DB_TABLE + ".id_produto_a = " + DB_TABLE + ".id_produto ";  
	}
	public static String outerJoinSincProdutoVenda_Associada() {
		return " left outer join " + ProdutoVendaDBHelperBase.DB_TABLE_SINC + " on " + ProdutoVendaDBHelperBase.DB_TABLE_SINC + ".id_produto_a = " + DB_TABLE_SINC + ".id_produto ";  
	}
 	
	/*
	public Produto obtemPorPrecoProdutoPossui(long id) {
		string sql;
		sql = "select " + camposOrdenados() +
			" from " + tabelaSelect() +
			innerJoinPrecoProduto_Possui() + 
			" where id_preco_produto = " + id;
		return (Produto) getObjeto(sql);
	}
	*/
	public static String innerJoinPrecoProduto_Possui() {
		return " inner join " + PrecoProdutoDBHelperBase.DB_TABLE + " on " + PrecoProdutoDBHelperBase.DB_TABLE + ".id_produto_pa = " + DB_TABLE + ".id_produto ";  
	}
	public static String innerJoinSincPrecoProduto_Possui() {
		return " inner join " + PrecoProdutoDBHelperBase.DB_TABLE_SINC + " on " + PrecoProdutoDBHelperBase.DB_TABLE_SINC + ".id_produto_pa = " + DB_TABLE_SINC + ".id_produto ";  
	}
	public static String outerJoinPrecoProduto_Possui() {
		return " left outer join " + PrecoProdutoDBHelperBase.DB_TABLE + " on " + PrecoProdutoDBHelperBase.DB_TABLE + ".id_produto_pa = " + DB_TABLE + ".id_produto ";  
	}
	public static String outerJoinSincPrecoProduto_Possui() {
		return " left outer join " + PrecoProdutoDBHelperBase.DB_TABLE_SINC + " on " + PrecoProdutoDBHelperBase.DB_TABLE_SINC + ".id_produto_pa = " + DB_TABLE_SINC + ".id_produto ";  
	}
 	
	/*
	public Produto obtemPorCategoriaProdutoProdutoPossui(long id) {
		string sql;
		sql = "select " + camposOrdenados() +
			" from " + tabelaSelect() +
			innerJoinCategoriaProdutoProduto_Possui() + 
			" where id_categoria_produto_produto = " + id;
		return (Produto) getObjeto(sql);
	}
	*/
	public static String innerJoinCategoriaProdutoProduto_Possui() {
		return " inner join " + CategoriaProdutoProdutoDBHelperBase.DB_TABLE + " on " + CategoriaProdutoProdutoDBHelperBase.DB_TABLE + ".id_produto_ra = " + DB_TABLE + ".id_produto ";  
	}
	public static String innerJoinSincCategoriaProdutoProduto_Possui() {
		return " inner join " + CategoriaProdutoProdutoDBHelperBase.DB_TABLE_SINC + " on " + CategoriaProdutoProdutoDBHelperBase.DB_TABLE_SINC + ".id_produto_ra = " + DB_TABLE_SINC + ".id_produto ";  
	}
	public static String outerJoinCategoriaProdutoProduto_Possui() {
		return " left outer join " + CategoriaProdutoProdutoDBHelperBase.DB_TABLE + " on " + CategoriaProdutoProdutoDBHelperBase.DB_TABLE + ".id_produto_ra = " + DB_TABLE + ".id_produto ";  
	}
	public static String outerJoinSincCategoriaProdutoProduto_Possui() {
		return " left outer join " + CategoriaProdutoProdutoDBHelperBase.DB_TABLE_SINC + " on " + CategoriaProdutoProdutoDBHelperBase.DB_TABLE_SINC + ".id_produto_ra = " + DB_TABLE_SINC + ".id_produto ";  
	}
 	
	
	
	// Relacionamento onde esse objeto ? chave estrangeira de outro. ????
	
	private boolean _obtemLinhaProduto_EstaEm = false;
	public void setObtemLinhaProduto_EstaEm() {
		_obtemLinhaProduto_EstaEm = true;
	}
	protected String outterJoinLinhaProduto_EstaEm() {
		return " left outer join " + LinhaProdutoDBHelperBase.DB_TABLE + " on " + LinhaProdutoDBHelperBase.DB_TABLE + ".id_linha_produto = " + DB_TABLE + ".id_linha_produto_ee ";  
	}
	public boolean atualizaEstaEmLinhaProduto(long idProduto, long idLinhaProdutoEe) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_linha_produto_ee  = " + idLinhaProdutoEe +
        " where id_produto = " +  idProduto;
       	//this.executaSql(sql);
       	return true;
	}
	public Produto obtemPorIdsEstaEmLinhaProduto(long idProduto, long idLinhaProdutoEe) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_linha_produto_ee = " + idLinhaProdutoEe + " and " +
			  " id_produto = " + idProduto;
		return (Produto) this.geObjetoSql(sql);
	}
	
	
	public static String innerJoinLinhaProduto_EstaEm() {
		return " inner join " + LinhaProdutoDBHelperBase.DB_TABLE + " on " + LinhaProdutoDBHelperBase.DB_TABLE + ".id_linha_produto = " + DB_TABLE + ".id_linha_produto_ee ";  
	}
	public static String outerJoinLinhaProduto_EstaEm() {
		return " left outer join " + LinhaProdutoDBHelperBase.DB_TABLE + " on " + LinhaProdutoDBHelperBase.DB_TABLE + ".id_linha_produto = " + DB_TABLE + ".id_linha_produto_ee ";  
	}
	public static String innerJoinSincLinhaProduto_EstaEm() {
		return " inner join " + LinhaProdutoDBHelperBase.DB_TABLE_SINC + " on " + LinhaProdutoDBHelperBase.DB_TABLE_SINC + ".id_linha_produto = " + DB_TABLE_SINC + ".id_linha_produto_ee ";  
	}
	public static String outerJoinSincLinhaProduto_EstaEm() {
		return " left outer join " + LinhaProdutoDBHelperBase.DB_TABLE_SINC + " on " + LinhaProdutoDBHelperBase.DB_TABLE_SINC + ".id_linha_produto = " + DB_TABLE_SINC + ".id_linha_produto_ee ";  
	}
	
 	
	// ********************************************************************	
	
	
	public static String camposOrdenados() 
	{
		return " produto.id_produto " 
		+ " ,produto.nome " 
		+ " ,produto.url " 
		+ " ,produto.imagem " 
		+ " ,produto.tamanho_imagem " 
		+ " ,produto.data_inclusao " 
		+ " ,produto.data_exclusao " 
		+ " ,produto.id_linha_produto_ee " 
		;
	}
	public static String camposOrdenadosSinc() 
	{
		return " produto_sinc.id_produto " 
		+ " ,produto_sinc.nome " 
		+ " ,produto_sinc.url " 
		+ " ,produto_sinc.imagem " 
		+ " ,produto_sinc.tamanho_imagem " 
		+ " ,produto_sinc.data_inclusao " 
		+ " ,produto_sinc.data_exclusao " 
		+ " ,produto_sinc.id_linha_produto_ee " 
		
		+ " ,produto_sinc.operacao_sinc "
		;
	}
	public static String camposOrdenadosAlias(String nomeTabela) 
	{
		return  nomeTabela + ".id_produto " 
		+ " , " + nomeTabela + ".nome " 
		+ " , " + nomeTabela + ".url " 
		+ " , " + nomeTabela + ".imagem " 
		+ " , " + nomeTabela + ".tamanho_imagem " 
		+ " , " +  "DATE_FORMAT(" + nomeTabela + ".data_inclusao,'%d-%m-%Y') " 
		+ " , " +  "DATE_FORMAT(" + nomeTabela + ".data_exclusao,'%d-%m-%Y') " 
		+ " , " + nomeTabela + ".id_linha_produto_ee " 
		;
	}
	
	protected String camposOrdenadosJoin()
    {
        String saida = camposOrdenados();
		saida += (this._obtemLinhaProduto_EstaEm?" , " +LinhaProdutoDBHelperBase.camposOrdenados():"");
        return saida;
    }
    
        
    public void limpaObtem()
    {
		_obtemLinhaProduto_EstaEm = false;
    }
    
	protected String outterJoinAgrupado()
    {
        String saida = " ";
		saida += (this._obtemLinhaProduto_EstaEm? outterJoinLinhaProduto_EstaEm() + " ":"");
        return saida;
    }
    protected MontadorDaoI getMontadorAgrupado()
    {
        MontadorDaoComposite montador = new MontadorDaoComposite();
        montador.adicionaMontador(new ProdutoMontador(), null);
		if (this._obtemLinhaProduto_EstaEm)
            montador.adicionaMontador(new LinhaProdutoMontador(), "LinhaProduto_EstaEm");
         return montador;
    }
	
    
   	// Poderia passar para classe abstrata.
    public final List<Produto> getAllSinc() throws DaoException {
    	this.setMontador(null);
    	List<Produto> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<Produto>();
    		}
    		DCLog.e(DCLog.DATABASE_ERRO, this, e);
    		this.criaTabelaSincronizacao(); // Faltando dependentes.
    		DCLog.d(DCLog.SINCRONIZACAO_DAO, this, "Criando tabela ( falta dependentes ) " + e.getMessage());
    	}
    	if (saida==null) {
    		saida = getListaQuerySinc(DB_TABLE_SINC, COLS_SINC, null, null, null, null, null);
    		DCLog.d(DCLog.SINCRONIZACAO_DAO, this, "getListaQuerySinc() -> " + saida.size() + " elementos.");
    	} else {
    		DCLog.d(DCLog.SINCRONIZACAO_DAO, this, "getAllSincImpl() -> " + saida.size() + " elementos.");
    	}
    	return saida;
	}
	
	// Poderia passar para classe abstrata.
    public final List<Produto> getAllSincSoAlteracao() throws DaoException {
    	List<Produto> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    		saida = null; // Melhorar aqui !!!!
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<Produto>();
    		}
    		DCLog.e(DCLog.DATABASE_ERRO, this, e);
    		this.criaTabelaSincronizacao(); // Faltando dependentes.
    		DCLog.d(DCLog.SINCRONIZACAO_DAO, this, "Criando tabela ( falta dependentes ) " + e.getMessage());
    	}
    	if (saida==null) {
    		saida = getListaQuerySinc(DB_TABLE_SINC, COLS_SINC, "operacao_sinc='A'", null, null, null, null);
    		DCLog.d(DCLog.SINCRONIZACAO_DAO, this, "getListaQuerySinc() -> " + saida.size() + " elementos.");
    	} else {
    		DCLog.d(DCLog.SINCRONIZACAO_DAO, this, "getAllSincImpl() -> " + saida.size() + " elementos.");
    	}
    	return saida;
	}
	
	public final List<Produto> getAllSincImpl() throws DaoException {
   		String sql = "select " + camposOrdenadosSinc()   
   			+ " from " + this.DB_TABLE_SINC;
   		ProdutoMontador montador = new ProdutoMontador(true); // sinc ligado
   		this.setMontador(montador);
   		List<Produto> saida = this.getListaSql(sql);
   		return saida;
   	}
   	
	public long getQtdeSinc() {
		String sql = "select count(*) from " + DB_TABLE_SINC;
		return this.getNumeroSql(sql);
	}
	public boolean tabelaSincVazia() {
		return (this.getQtdeSinc() == 0);
	}
	
	// DaoSincronizacao
	@Override
	public final void insere(DCIObjetoDominio obj) {
		this.insert((Produto) obj);
	}
	@Override
	public final void dropCreate() {
		this.dropTable();
		this.criaTabela();
	}
}
