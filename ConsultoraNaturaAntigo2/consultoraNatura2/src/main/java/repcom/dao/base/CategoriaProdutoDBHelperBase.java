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
import repcom.modelo.vo.CategoriaProdutoVo;
import repcom.modelo.vo.FabricaVo;
import repcom.dao.*;
import repcom.dao.montador.*;
import repcom.dao.datasource.DataSourceAplicacao;

public abstract class CategoriaProdutoDBHelperBase extends DBHelperBase
	implements DaoSincronismo // coloquei aqui para evitar impacto de arquitetura
{

    private static final String DB_NAME = "w_alert";
    public static final String DB_TABLE = "categoria_produto";
    public static final String DB_TABLE_SINC = "categoria_produto_sinc";
    public static final int DB_VERSION = 3;

    protected static final String CLASSNAME = CategoriaProdutoDBHelperBase.class.getSimpleName();
    protected static final String[] COLS = new String[] { 
        "id_categoria_produto"
        ,"nome"
        ,"url"
        ,"codigo_line_id"
        ,"data_inclusao"
		, "id_categoria_produto_p"
	
    };

	protected static final String[] COLS_SINC = new String[] { 
        "id_categoria_produto"
        ,"nome"
        ,"url"
        ,"codigo_line_id"
        ,"data_inclusao"
		, "id_categoria_produto_p"
		, "operacao_sinc"
    };

    protected SQLiteDatabase db;
    protected final DBOpenHelper dbOpenHelper;

	@Override
	protected MontadorDaoI criaMontadorPadrao() {
		return new CategoriaProdutoMontador();
	}
	
	protected String getSqlIndices() {
		return "";
	}
	protected String getSqlCreate(){
		return  "CREATE TABLE "
        + CategoriaProdutoDBHelperBase.DB_TABLE + " ("
        + "  id_categoria_produto INTEGER PRIMARY KEY "
        + " , nome TEXT "
        + " , url TEXT "
        + " , codigo_line_id INTEGER "
        + " , data_inclusao INTEGER "
		+ " , id_categoria_produto_p INTEGER "
		
		+ getSqlIndices()
        + ");";
	}

	

	public static final String DB_CREATE_SINCRONIZADA = "CREATE TABLE IF NOT EXISTS "
        + CategoriaProdutoDBHelperBase.DB_TABLE_SINC + " ("
        + "  id_categoria_produto INTEGER "
        + " , nome TEXT "
        + " , url TEXT "
        + " , codigo_line_id INTEGER "
        + " , data_inclusao INTEGER "
		+ " , id_categoria_produto_p INTEGER "
		
        + ", operacao_sinc TEXT);";


    public static final String DB_CREATE = "CREATE TABLE IF NOT EXISTS "
        + CategoriaProdutoDBHelperBase.DB_TABLE + " ("
        + "  id_categoria_produto INTEGER PRIMARY KEY "
        + " , nome TEXT "
        + " , url TEXT "
        + " , codigo_line_id INTEGER "
        + " , data_inclusao INTEGER "
		+ " , id_categoria_produto_p INTEGER "
		
        + ");";
    
    private static final String DB_DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private static final String DB_DROP = "DROP TABLE IF EXISTS " + DB_TABLE;
    private static final String DB_DROP_SINCRONIZADA = "DROP TABLE IF EXISTS " + DB_TABLE_SINC;
    
    private static class DBOpenHelper extends SQLiteOpenHelper {

       

        public DBOpenHelper(final Context context) {
            super(context, CategoriaProdutoDBHelperBase.DB_NAME, null, CategoriaProdutoDBHelperBase.DB_VERSION);
        }

        @Override
        public void onCreate(final SQLiteDatabase db) {
            try {
                db.execSQL(DB_CREATE);
            } catch (SQLException e) {
                Log.e(Constants.LOGTAG, CategoriaProdutoDBHelperBase.CLASSNAME, e);
            }
        }

        @Override
        public void onOpen(final SQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + CategoriaProdutoDBHelperBase.DB_TABLE);
            onCreate(db);
        }
    }

    //
    // end inner classes
    //
    
    // Versao Nova
 	public CategoriaProdutoDBHelperBase() {
    	this.dbOpenHelper = null;
    	setDataSource(DataSourceAplicacao.getInstancia());
    }
    
   
	
	protected ContentValues montaValores(final DCIObjetoDominio valor) {
		CategoriaProduto item = (CategoriaProduto) valor;
		ContentValues valores = new ContentValues();
       	putValor(valores,"id_categoria_produto",item.getIdCategoriaProduto());
       	
       	putValor(valores,"nome",item.getNome());
       	
       	putValor(valores,"url",item.getUrl());
       	
       	putValor(valores,"codigo_line_id",item.getCodigoLineId());
       	
       	putValorData(valores,"data_inclusao",item.getDataInclusao());
        		
       	putValor(valores,"id_categoria_produto_p",item.getIdCategoriaProdutoP());
       	
        return valores;
	}


    // **** Chamadas Diretas Objeto Banco de Dados
    private void registraErroChamadaDireta(Exception e) {
    	Log.e(Constants.LOGTAG, CategoriaProdutoDBHelperBase.CLASSNAME, e);
    }
   
    public final void insert(final CategoriaProduto item) {
	    try {
	        getDb().insert(CategoriaProdutoDBHelperBase.DB_TABLE, null, montaValores(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    public final void update(final CategoriaProduto item) {
	    try {
	        getDb().update(CategoriaProdutoDBHelperBase.DB_TABLE, montaValores(item), "id_categoria_produto=" + item.getIdCategoriaProduto(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     // Nao pode ser final o parametro para retornar o objeto com seu Id
    public final void insertSinc(CategoriaProduto item) {
        try {
        	item.setIdCategoriaProduto((int)getMaxId() + 1);
        	DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc: " + item.debug());
	        long id = getDb().insert(CategoriaProdutoDBHelperBase.DB_TABLE, null, montaValores(item));
    	    ((ObjetoSinc)item).setOperacaoSinc("I");
        	getDb().insert(CategoriaProdutoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     public final void updateSinc(final CategoriaProduto item) {
        try {
	        DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc: " + item.debug());
	        getDb().update(CategoriaProdutoDBHelperBase.DB_TABLE, montaValores(item), "id_categoria_produto=" + item.getIdCategoriaProduto(), null);
	        ((ObjetoSinc)item).setOperacaoSinc("A");
	        CategoriaProduto atual = this.getSincById(item.getId());
	        if (atual==null) {
	        	getDb().insert(CategoriaProdutoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	        } else {
	        	if ("I".equals(((ObjetoSinc)atual).getOperacaoSinc()))
	        		((ObjetoSinc)item).setOperacaoSinc("I");
	        	getDb().update(CategoriaProdutoDBHelperBase.DB_TABLE_SINC, montaValoresSinc(item), "id_categoria_produto=" + item.getIdCategoriaProduto(), null);
	        }
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    protected final void delete(final long id) {
        try {
			getDb().delete(CategoriaProdutoDBHelperBase.DB_TABLE, "id_categoria_produto=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    /*
    private void deleteSinc(final long id) {
        try {
			getDb().delete(CategoriaProdutoDBHelperBase.DB_TABLE_SINC, "id_categoria_produto=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
	*/
    public void limpaSinc(final CategoriaProduto item) {
    	try {
			getDb().delete(CategoriaProdutoDBHelperBase.DB_TABLE_SINC, "id_categoria_produto=" + item.getId(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    
    public void deleteSinc(final CategoriaProduto item) {
    	try {
	        DCLog.dStack(DCLog.DATABASE_ADM, this, "deleteSinc: " + item.debug());
	        delete(item.getId());
	        ((ObjetoSinc)item).setOperacaoSinc("D");
        	getDb().insert(CategoriaProdutoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
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
    public CategoriaProduto getById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (CategoriaProduto) getItemQuery(true, CategoriaProdutoDBHelperBase.DB_TABLE, CategoriaProdutoDBHelperBase.COLS, "id_categoria_produto = " + id + "", null, null, null, null,null);
    }
    
    // Esta com orderBy que pode ser bom para exibicoes em tela
    // mas nao e bom para sincronizacao, pensar em ter um metodo para tela e outro para sinc.
    public List<CategoriaProduto> getAll() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, null);
    }
    public List<CategoriaProduto> getAllTela() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, orderByColuna());
    }
    
    private CategoriaProduto getByRowId(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (CategoriaProduto) getItemQuery(true, CategoriaProdutoDBHelperBase.DB_TABLE, CategoriaProdutoDBHelperBase.COLS, "ROWID = " + id + "", null, null, null, null,null);
    }
    private CategoriaProduto getSincById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (CategoriaProduto) getItemQuerySinc(true, CategoriaProdutoDBHelperBase.DB_TABLE_SINC, CategoriaProdutoDBHelperBase.COLS_SINC, "id_categoria_produto = " + id + "", null, null, null, null,null);
    }
    
    
    public long getMaxId() {
		String sql = "select max(id_categoria_produto) from " + DB_TABLE;
		return this.getNumeroSql(sql);
	}
	protected String orderByColuna() {
    	return null;
    }
	
	
	public List<CategoriaProduto> getPorPaiCategoriaProduto(Context contexto, long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_categoria_produto_p = " + id, null, null, null, null);
	}
	public List<CategoriaProduto> getPorPaiCategoriaProduto(long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_categoria_produto_p = " + id, null, null, null, null);
	}
	
	
  
  	
  
  
  	
	/*
	public CategoriaProduto obtemPorClienteInteresseCategoriaAssociada(long id) {
		string sql;
		sql = "select " + camposOrdenados() +
			" from " + tabelaSelect() +
			innerJoinClienteInteresseCategoria_Associada() + 
			" where id_cliente_interesse_categoria = " + id;
		return (CategoriaProduto) getObjeto(sql);
	}
	*/
	public static String innerJoinClienteInteresseCategoria_Associada() {
		return " inner join " + ClienteInteresseCategoriaDBHelperBase.DB_TABLE + " on " + ClienteInteresseCategoriaDBHelperBase.DB_TABLE + ".id_categoria_produto_a = " + DB_TABLE + ".id_categoria_produto ";  
	}
	public static String innerJoinSincClienteInteresseCategoria_Associada() {
		return " inner join " + ClienteInteresseCategoriaDBHelperBase.DB_TABLE_SINC + " on " + ClienteInteresseCategoriaDBHelperBase.DB_TABLE_SINC + ".id_categoria_produto_a = " + DB_TABLE_SINC + ".id_categoria_produto ";  
	}
	public static String outerJoinClienteInteresseCategoria_Associada() {
		return " left outer join " + ClienteInteresseCategoriaDBHelperBase.DB_TABLE + " on " + ClienteInteresseCategoriaDBHelperBase.DB_TABLE + ".id_categoria_produto_a = " + DB_TABLE + ".id_categoria_produto ";  
	}
	public static String outerJoinSincClienteInteresseCategoria_Associada() {
		return " left outer join " + ClienteInteresseCategoriaDBHelperBase.DB_TABLE_SINC + " on " + ClienteInteresseCategoriaDBHelperBase.DB_TABLE_SINC + ".id_categoria_produto_a = " + DB_TABLE_SINC + ".id_categoria_produto ";  
	}
 	
	/*
	public CategoriaProduto obtemPorCategoriaProdutoProdutoPossui(long id) {
		string sql;
		sql = "select " + camposOrdenados() +
			" from " + tabelaSelect() +
			innerJoinCategoriaProdutoProduto_Possui() + 
			" where id_categoria_produto_produto = " + id;
		return (CategoriaProduto) getObjeto(sql);
	}
	*/
	public static String innerJoinCategoriaProdutoProduto_Possui() {
		return " inner join " + CategoriaProdutoProdutoDBHelperBase.DB_TABLE + " on " + CategoriaProdutoProdutoDBHelperBase.DB_TABLE + ".id_categoria_produto_ra = " + DB_TABLE + ".id_categoria_produto ";  
	}
	public static String innerJoinSincCategoriaProdutoProduto_Possui() {
		return " inner join " + CategoriaProdutoProdutoDBHelperBase.DB_TABLE_SINC + " on " + CategoriaProdutoProdutoDBHelperBase.DB_TABLE_SINC + ".id_categoria_produto_ra = " + DB_TABLE_SINC + ".id_categoria_produto ";  
	}
	public static String outerJoinCategoriaProdutoProduto_Possui() {
		return " left outer join " + CategoriaProdutoProdutoDBHelperBase.DB_TABLE + " on " + CategoriaProdutoProdutoDBHelperBase.DB_TABLE + ".id_categoria_produto_ra = " + DB_TABLE + ".id_categoria_produto ";  
	}
	public static String outerJoinSincCategoriaProdutoProduto_Possui() {
		return " left outer join " + CategoriaProdutoProdutoDBHelperBase.DB_TABLE_SINC + " on " + CategoriaProdutoProdutoDBHelperBase.DB_TABLE_SINC + ".id_categoria_produto_ra = " + DB_TABLE_SINC + ".id_categoria_produto ";  
	}
 	
	/*
	public CategoriaProduto obtemPorCategoriaProdutoPai(long id) {
		string sql;
		sql = "select " + camposOrdenados() +
			" from " + tabelaSelect() +
			innerJoinCategoriaProduto_Pai() + 
			" where id_categoria_produto = " + id;
		return (CategoriaProduto) getObjeto(sql);
	}
	*/
	public static String innerJoinCategoriaProduto_Pai() {
		return " inner join " + CategoriaProdutoDBHelperBase.DB_TABLE + " on " + CategoriaProdutoDBHelperBase.DB_TABLE + ".id_categoria_produto_p = " + DB_TABLE + ".id_categoria_produto ";  
	}
	public static String innerJoinSincCategoriaProduto_Pai() {
		return " inner join " + CategoriaProdutoDBHelperBase.DB_TABLE_SINC + " on " + CategoriaProdutoDBHelperBase.DB_TABLE_SINC + ".id_categoria_produto_p = " + DB_TABLE_SINC + ".id_categoria_produto ";  
	}
	public static String outerJoinCategoriaProduto_Pai() {
		return " left outer join " + CategoriaProdutoDBHelperBase.DB_TABLE + " on " + CategoriaProdutoDBHelperBase.DB_TABLE + ".id_categoria_produto_p = " + DB_TABLE + ".id_categoria_produto ";  
	}
	public static String outerJoinSincCategoriaProduto_Pai() {
		return " left outer join " + CategoriaProdutoDBHelperBase.DB_TABLE_SINC + " on " + CategoriaProdutoDBHelperBase.DB_TABLE_SINC + ".id_categoria_produto_p = " + DB_TABLE_SINC + ".id_categoria_produto ";  
	}
 	
	
	
	// Relacionamento onde esse objeto ? chave estrangeira de outro. ????
	
	private boolean _obtemCategoriaProduto_Pai = false;
	public void setObtemCategoriaProduto_Pai() {
		_obtemCategoriaProduto_Pai = true;
	}
	protected String outterJoinCategoriaProduto_Pai() {
		return " left outer join " + CategoriaProdutoDBHelperBase.DB_TABLE + " on " + CategoriaProdutoDBHelperBase.DB_TABLE + ".id_categoria_produto = " + DB_TABLE + ".id_categoria_produto_p ";  
	}
	public boolean atualizaPaiCategoriaProduto(long idCategoriaProduto, long idCategoriaProdutoP) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_categoria_produto_p  = " + idCategoriaProdutoP +
        " where id_categoria_produto = " +  idCategoriaProduto;
       	//this.executaSql(sql);
       	return true;
	}
	public CategoriaProduto obtemPorIdsPaiCategoriaProduto(long idCategoriaProduto, long idCategoriaProdutoP) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_categoria_produto_p = " + idCategoriaProdutoP + " and " +
			  " id_categoria_produto = " + idCategoriaProduto;
		return (CategoriaProduto) this.geObjetoSql(sql);
	}
	
	
 	
	// ********************************************************************	
	
	
	public static String camposOrdenados() 
	{
		return " categoria_produto.id_categoria_produto " 
		+ " ,categoria_produto.nome " 
		+ " ,categoria_produto.url " 
		+ " ,categoria_produto.codigo_line_id " 
		+ " ,categoria_produto.data_inclusao " 
		+ " ,categoria_produto.id_categoria_produto_p " 
		;
	}
	public static String camposOrdenadosSinc() 
	{
		return " categoria_produto_sinc.id_categoria_produto " 
		+ " ,categoria_produto_sinc.nome " 
		+ " ,categoria_produto_sinc.url " 
		+ " ,categoria_produto_sinc.codigo_line_id " 
		+ " ,categoria_produto_sinc.data_inclusao " 
		+ " ,categoria_produto_sinc.id_categoria_produto_p " 
		
		+ " ,categoria_produto_sinc.operacao_sinc "
		;
	}
	public static String camposOrdenadosAlias(String nomeTabela) 
	{
		return  nomeTabela + ".id_categoria_produto " 
		+ " , " + nomeTabela + ".nome " 
		+ " , " + nomeTabela + ".url " 
		+ " , " + nomeTabela + ".codigo_line_id " 
		+ " , " +  "DATE_FORMAT(" + nomeTabela + ".data_inclusao,'%d-%m-%Y') " 
		+ " , " + nomeTabela + ".id_categoria_produto_p " 
		;
	}
	
	protected String camposOrdenadosJoin()
    {
        String saida = camposOrdenados();
		saida += (this._obtemCategoriaProduto_Pai?" , " +CategoriaProdutoDBHelperBase.camposOrdenados():"");
        return saida;
    }
    
        
    public void limpaObtem()
    {
		_obtemCategoriaProduto_Pai = false;
    }
    
	protected String outterJoinAgrupado()
    {
        String saida = " ";
		saida += (this._obtemCategoriaProduto_Pai? outterJoinCategoriaProduto_Pai() + " ":"");
        return saida;
    }
    protected MontadorDaoI getMontadorAgrupado()
    {
        MontadorDaoComposite montador = new MontadorDaoComposite();
        montador.adicionaMontador(new CategoriaProdutoMontador(), null);
		if (this._obtemCategoriaProduto_Pai)
            montador.adicionaMontador(new CategoriaProdutoMontador(), "CategoriaProduto_Pai");
         return montador;
    }
	
    
   	// Poderia passar para classe abstrata.
    public final List<CategoriaProduto> getAllSinc() throws DaoException {
    	this.setMontador(null);
    	List<CategoriaProduto> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<CategoriaProduto>();
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
    public final List<CategoriaProduto> getAllSincSoAlteracao() throws DaoException {
    	List<CategoriaProduto> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    		saida = null; // Melhorar aqui !!!!
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<CategoriaProduto>();
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
	
	public final List<CategoriaProduto> getAllSincImpl() throws DaoException {
   		String sql = "select " + camposOrdenadosSinc()   
   			+ " from " + this.DB_TABLE_SINC;
   		CategoriaProdutoMontador montador = new CategoriaProdutoMontador(true); // sinc ligado
   		this.setMontador(montador);
   		List<CategoriaProduto> saida = this.getListaSql(sql);
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
		this.insert((CategoriaProduto) obj);
	}
	@Override
	public final void dropCreate() {
		this.dropTable();
		this.criaTabela();
	}
}
