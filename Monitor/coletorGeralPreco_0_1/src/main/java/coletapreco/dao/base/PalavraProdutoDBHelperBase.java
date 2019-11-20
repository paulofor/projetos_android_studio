package coletapreco.dao.base;

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
import coletapreco.modelo.*;
import coletapreco.modelo.vo.PalavraProdutoVo;
import coletapreco.modelo.vo.FabricaVo;
import coletapreco.dao.*;
import coletapreco.dao.montador.*;
import coletapreco.dao.datasource.DataSourceAplicacao;

public abstract class PalavraProdutoDBHelperBase extends DBHelperBase
	implements DaoSincronismo // coloquei aqui para evitar impacto de arquitetura
{

    private static final String DB_NAME = "w_alert";
    public static final String DB_TABLE = "palavra_produto";
    public static final String DB_TABLE_SINC = "palavra_produto_sinc";
    public static final int DB_VERSION = 3;

    protected static final String CLASSNAME = PalavraProdutoDBHelperBase.class.getSimpleName();
    protected static final String[] COLS = new String[] { 
        "id_palavra_produto"
		, "id_palavra_ra"
	
		, "id_produto_ra"
	
    };

	protected static final String[] COLS_SINC = new String[] { 
        "id_palavra_produto"
		, "id_palavra_ra"
	
		, "id_produto_ra"
		, "operacao_sinc"
    };

    protected SQLiteDatabase db;
    protected final DBOpenHelper dbOpenHelper;

	@Override
	protected MontadorDaoI criaMontadorPadrao() {
		return new PalavraProdutoMontador();
	}
	
	protected String getSqlIndices() {
		return "";
	}
	protected String getSqlCreate(){
		return  "CREATE TABLE "
        + PalavraProdutoDBHelperBase.DB_TABLE + " ("
        + "  id_palavra_produto INTEGER PRIMARY KEY "
		+ " , id_palavra_ra INTEGER "
		
		+ " , id_produto_ra INTEGER "
		
		+ getSqlIndices()
        + ");";
	}

	

	public static final String DB_CREATE_SINCRONIZADA = "CREATE TABLE IF NOT EXISTS "
        + PalavraProdutoDBHelperBase.DB_TABLE_SINC + " ("
        + "  id_palavra_produto INTEGER "
		+ " , id_palavra_ra INTEGER "
		
		+ " , id_produto_ra INTEGER "
		
        + ", operacao_sinc TEXT);";


    public static final String DB_CREATE = "CREATE TABLE IF NOT EXISTS "
        + PalavraProdutoDBHelperBase.DB_TABLE + " ("
        + "  id_palavra_produto INTEGER PRIMARY KEY "
		+ " , id_palavra_ra INTEGER "
		
		+ " , id_produto_ra INTEGER "
		
        + ");";
    
    private static final String DB_DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private static final String DB_DROP = "DROP TABLE IF EXISTS " + DB_TABLE;
    private static final String DB_DROP_SINCRONIZADA = "DROP TABLE IF EXISTS " + DB_TABLE_SINC;
    
    private static class DBOpenHelper extends SQLiteOpenHelper {

       

        public DBOpenHelper(final Context context) {
            super(context, PalavraProdutoDBHelperBase.DB_NAME, null, PalavraProdutoDBHelperBase.DB_VERSION);
        }

        @Override
        public void onCreate(final SQLiteDatabase db) {
            try {
                db.execSQL(DB_CREATE);
            } catch (SQLException e) {
                Log.e(Constants.LOGTAG, PalavraProdutoDBHelperBase.CLASSNAME, e);
            }
        }

        @Override
        public void onOpen(final SQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + PalavraProdutoDBHelperBase.DB_TABLE);
            onCreate(db);
        }
    }

    //
    // end inner classes
    //
    
    // Versao Nova
 	public PalavraProdutoDBHelperBase() {
    	this.dbOpenHelper = null;
    	setDataSource(DataSourceAplicacao.getInstancia());
    }
    
   
	
	protected ContentValues montaValores(final DCIObjetoDominio valor) {
		PalavraProduto item = (PalavraProduto) valor;
		ContentValues valores = new ContentValues();
       	putValor(valores,"id_palavra_produto",item.getIdPalavraProduto());
       	
       	putValor(valores,"id_palavra_ra",item.getIdPalavraRa());
       	
       	putValor(valores,"id_produto_ra",item.getIdProdutoRa());
       	
        return valores;
	}


    // **** Chamadas Diretas Objeto Banco de Dados
    private void registraErroChamadaDireta(Exception e) {
    	Log.e(Constants.LOGTAG, PalavraProdutoDBHelperBase.CLASSNAME, e);
    }
   
    public final void insert(final PalavraProduto item) {
	    try {
	        getDb().insert(PalavraProdutoDBHelperBase.DB_TABLE, null, montaValores(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    public final void update(final PalavraProduto item) {
	    try {
	        getDb().update(PalavraProdutoDBHelperBase.DB_TABLE, montaValores(item), "id_palavra_produto=" + item.getIdPalavraProduto(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     // Nao pode ser final o parametro para retornar o objeto com seu Id
    public final void insertSinc(PalavraProduto item) {
        try {
        	item.setIdPalavraProduto((int)getMaxId() + 1);
        	DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc: " + item.debug());
	        long id = getDb().insert(PalavraProdutoDBHelperBase.DB_TABLE, null, montaValores(item));
    	    ((ObjetoSinc)item).setOperacaoSinc("I");
        	getDb().insert(PalavraProdutoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     public final void updateSinc(final PalavraProduto item) {
        try {
	        DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc: " + item.debug());
	        getDb().update(PalavraProdutoDBHelperBase.DB_TABLE, montaValores(item), "id_palavra_produto=" + item.getIdPalavraProduto(), null);
	        ((ObjetoSinc)item).setOperacaoSinc("A");
	        PalavraProduto atual = this.getSincById(item.getId());
	        if (atual==null) {
	        	getDb().insert(PalavraProdutoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	        } else {
	        	if ("I".equals(((ObjetoSinc)atual).getOperacaoSinc()))
	        		((ObjetoSinc)item).setOperacaoSinc("I");
	        	getDb().update(PalavraProdutoDBHelperBase.DB_TABLE_SINC, montaValoresSinc(item), "id_palavra_produto=" + item.getIdPalavraProduto(), null);
	        }
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    protected final void delete(final long id) {
        try {
			getDb().delete(PalavraProdutoDBHelperBase.DB_TABLE, "id_palavra_produto=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    /*
    private void deleteSinc(final long id) {
        try {
			getDb().delete(PalavraProdutoDBHelperBase.DB_TABLE_SINC, "id_palavra_produto=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
	*/
    public void limpaSinc(final PalavraProduto item) {
    	try {
			getDb().delete(PalavraProdutoDBHelperBase.DB_TABLE_SINC, "id_palavra_produto=" + item.getId(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    
    public void deleteSinc(final PalavraProduto item) {
    	try {
	        DCLog.dStack(DCLog.DATABASE_ADM, this, "deleteSinc: " + item.debug());
	        delete(item.getId());
	        ((ObjetoSinc)item).setOperacaoSinc("D");
        	getDb().insert(PalavraProdutoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
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
    public PalavraProduto getById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (PalavraProduto) getItemQuery(true, PalavraProdutoDBHelperBase.DB_TABLE, PalavraProdutoDBHelperBase.COLS, "id_palavra_produto = " + id + "", null, null, null, null,null);
    }
    
    // Esta com orderBy que pode ser bom para exibicoes em tela
    // mas nao e bom para sincronizacao, pensar em ter um metodo para tela e outro para sinc.
    public List<PalavraProduto> getAll() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, null);
    }
    public List<PalavraProduto> getAllTela() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, orderByColuna());
    }
    
    private PalavraProduto getByRowId(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (PalavraProduto) getItemQuery(true, PalavraProdutoDBHelperBase.DB_TABLE, PalavraProdutoDBHelperBase.COLS, "ROWID = " + id + "", null, null, null, null,null);
    }
    private PalavraProduto getSincById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (PalavraProduto) getItemQuerySinc(true, PalavraProdutoDBHelperBase.DB_TABLE_SINC, PalavraProdutoDBHelperBase.COLS_SINC, "id_palavra_produto = " + id + "", null, null, null, null,null);
    }
    
    
    public long getMaxId() {
		String sql = "select max(id_palavra_produto) from " + DB_TABLE;
		return this.getNumeroSql(sql);
	}
	protected String orderByColuna() {
    	return null;
    }
	
	
	// Classe e relacionamento
	public List<PalavraProduto> getPorRelaciondoAPalavra(Context contexto, long id) throws DaoException{
		String sql = "select " + camposOrdenados() + 
				" , " + ProdutoDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinProduto_RelaciondoA() +
				" where id_palavra_ra = " + id;
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new PalavraProdutoMontador(), null);
		montador.adicionaMontador(new ProdutoMontador(), "Produto_RelaciondoA");
		setMontador(montador);
		return this.getListaSqlListaInterna(sql);
			
	}
	public List<PalavraProduto> getPorRelaciondoAPalavra(long id) throws DaoException{
		String sql = "select " + camposOrdenados() + 
				" , " + ProdutoDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinProduto_RelaciondoA() +
				" where id_palavra_ra = " + id;
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new PalavraProdutoMontador(), null);
		montador.adicionaMontador(new ProdutoMontador(), "Produto_RelaciondoA");
		setMontador(montador);
		return this.getListaSqlListaInterna(sql);
			
	}
	public List<Produto> getProdutoPorRelaciondoAPalavra(Context contexto, long id) throws DaoException{
		String sql = "select " + ProdutoDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinProduto_RelaciondoA() +
				" where id_palavra_ra = " + id;
		setMontador(new ProdutoMontador());
		List<Produto> saida = this.getListaSql(sql);
		setMontador(null);
		return saida;
	}
	
	
	
	// Classe e relacionamento
	public List<PalavraProduto> getPorRelaciondoAProduto(Context contexto, long id) throws DaoException{
		String sql = "select " + camposOrdenados() + 
				" , " + PalavraDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinPalavra_RelaciondoA() +
				" where id_produto_ra = " + id;
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new PalavraProdutoMontador(), null);
		montador.adicionaMontador(new PalavraMontador(), "Palavra_RelaciondoA");
		setMontador(montador);
		return this.getListaSqlListaInterna(sql);
			
	}
	public List<PalavraProduto> getPorRelaciondoAProduto(long id) throws DaoException{
		String sql = "select " + camposOrdenados() + 
				" , " + PalavraDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinPalavra_RelaciondoA() +
				" where id_produto_ra = " + id;
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new PalavraProdutoMontador(), null);
		montador.adicionaMontador(new PalavraMontador(), "Palavra_RelaciondoA");
		setMontador(montador);
		return this.getListaSqlListaInterna(sql);
			
	}
	public List<Palavra> getPalavraPorRelaciondoAProduto(Context contexto, long id) throws DaoException{
		String sql = "select " + PalavraDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinPalavra_RelaciondoA() +
				" where id_produto_ra = " + id;
		setMontador(new PalavraMontador());
		List<Palavra> saida = this.getListaSql(sql);
		setMontador(null);
		return saida;
	}
	
	
  
  	
	public PalavraProduto getPorPalavraProduto(long idXXXX, long idYYYYY) {
		return null;
	}
	
  
  
  	
	
	
	// Relacionamento onde esse objeto ? chave estrangeira de outro. ????
	
	private boolean _obtemPalavra_RelaciondoA = false;
	public void setObtemPalavra_RelaciondoA() {
		_obtemPalavra_RelaciondoA = true;
	}
	protected String outterJoinPalavra_RelaciondoA() {
		return " left outer join " + PalavraDBHelperBase.DB_TABLE + " on " + PalavraDBHelperBase.DB_TABLE + ".id_palavra = " + DB_TABLE + ".id_palavra_ra ";  
	}
	public boolean atualizaRelaciondoAPalavra(long idPalavraProduto, long idPalavraRa) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_palavra_ra  = " + idPalavraRa +
        " where id_palavra_produto = " +  idPalavraProduto;
       	//this.executaSql(sql);
       	return true;
	}
	public PalavraProduto obtemPorIdsRelaciondoAPalavra(long idPalavraProduto, long idPalavraRa) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_palavra_ra = " + idPalavraRa + " and " +
			  " id_palavra_produto = " + idPalavraProduto;
		return (PalavraProduto) this.geObjetoSql(sql);
	}
	
	
	public static String innerJoinPalavra_RelaciondoA() {
		return " inner join " + PalavraDBHelperBase.DB_TABLE + " on " + PalavraDBHelperBase.DB_TABLE + ".id_palavra = " + DB_TABLE + ".id_palavra_ra ";  
	}
	public static String outerJoinPalavra_RelaciondoA() {
		return " left outer join " + PalavraDBHelperBase.DB_TABLE + " on " + PalavraDBHelperBase.DB_TABLE + ".id_palavra = " + DB_TABLE + ".id_palavra_ra ";  
	}
	public static String innerJoinSincPalavra_RelaciondoA() {
		return " inner join " + PalavraDBHelperBase.DB_TABLE_SINC + " on " + PalavraDBHelperBase.DB_TABLE_SINC + ".id_palavra = " + DB_TABLE_SINC + ".id_palavra_ra ";  
	}
	public static String outerJoinSincPalavra_RelaciondoA() {
		return " left outer join " + PalavraDBHelperBase.DB_TABLE_SINC + " on " + PalavraDBHelperBase.DB_TABLE_SINC + ".id_palavra = " + DB_TABLE_SINC + ".id_palavra_ra ";  
	}
	
 	
	private boolean _obtemProduto_RelaciondoA = false;
	public void setObtemProduto_RelaciondoA() {
		_obtemProduto_RelaciondoA = true;
	}
	protected String outterJoinProduto_RelaciondoA() {
		return " left outer join " + ProdutoDBHelperBase.DB_TABLE + " on " + ProdutoDBHelperBase.DB_TABLE + ".id_produto = " + DB_TABLE + ".id_produto_ra ";  
	}
	public boolean atualizaRelaciondoAProduto(long idPalavraProduto, long idProdutoRa) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_produto_ra  = " + idProdutoRa +
        " where id_palavra_produto = " +  idPalavraProduto;
       	//this.executaSql(sql);
       	return true;
	}
	public PalavraProduto obtemPorIdsRelaciondoAProduto(long idPalavraProduto, long idProdutoRa) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_produto_ra = " + idProdutoRa + " and " +
			  " id_palavra_produto = " + idPalavraProduto;
		return (PalavraProduto) this.geObjetoSql(sql);
	}
	
	
	public static String innerJoinProduto_RelaciondoA() {
		return " inner join " + ProdutoDBHelperBase.DB_TABLE + " on " + ProdutoDBHelperBase.DB_TABLE + ".id_produto = " + DB_TABLE + ".id_produto_ra ";  
	}
	public static String outerJoinProduto_RelaciondoA() {
		return " left outer join " + ProdutoDBHelperBase.DB_TABLE + " on " + ProdutoDBHelperBase.DB_TABLE + ".id_produto = " + DB_TABLE + ".id_produto_ra ";  
	}
	public static String innerJoinSincProduto_RelaciondoA() {
		return " inner join " + ProdutoDBHelperBase.DB_TABLE_SINC + " on " + ProdutoDBHelperBase.DB_TABLE_SINC + ".id_produto = " + DB_TABLE_SINC + ".id_produto_ra ";  
	}
	public static String outerJoinSincProduto_RelaciondoA() {
		return " left outer join " + ProdutoDBHelperBase.DB_TABLE_SINC + " on " + ProdutoDBHelperBase.DB_TABLE_SINC + ".id_produto = " + DB_TABLE_SINC + ".id_produto_ra ";  
	}
	
 	
	// ********************************************************************	
	
	
	public static String camposOrdenados() 
	{
		return " palavra_produto.id_palavra_produto " 
		+ " ,palavra_produto.id_palavra_ra " 
		+ " ,palavra_produto.id_produto_ra " 
		;
	}
	public static String camposOrdenadosSinc() 
	{
		return " palavra_produto_sinc.id_palavra_produto " 
		+ " ,palavra_produto_sinc.id_palavra_ra " 
		+ " ,palavra_produto_sinc.id_produto_ra " 
		
		+ " ,palavra_produto_sinc.operacao_sinc "
		;
	}
	public static String camposOrdenadosAlias(String nomeTabela) 
	{
		return  nomeTabela + ".id_palavra_produto " 
		+ " , " + nomeTabela + ".id_palavra_ra " 
		+ " , " + nomeTabela + ".id_produto_ra " 
		;
	}
	
	protected String camposOrdenadosJoin()
    {
        String saida = camposOrdenados();
		saida += (this._obtemPalavra_RelaciondoA?" , " +PalavraDBHelperBase.camposOrdenados():"");
		saida += (this._obtemProduto_RelaciondoA?" , " +ProdutoDBHelperBase.camposOrdenados():"");
        return saida;
    }
    
        
    public void limpaObtem()
    {
		_obtemPalavra_RelaciondoA = false;
		_obtemProduto_RelaciondoA = false;
    }
    
	protected String outterJoinAgrupado()
    {
        String saida = " ";
		saida += (this._obtemPalavra_RelaciondoA? outterJoinPalavra_RelaciondoA() + " ":"");
		saida += (this._obtemProduto_RelaciondoA? outterJoinProduto_RelaciondoA() + " ":"");
        return saida;
    }
    protected MontadorDaoI getMontadorAgrupado()
    {
        MontadorDaoComposite montador = new MontadorDaoComposite();
        montador.adicionaMontador(new PalavraProdutoMontador(), null);
		if (this._obtemPalavra_RelaciondoA)
            montador.adicionaMontador(new PalavraMontador(), "Palavra_RelaciondoA");
		if (this._obtemProduto_RelaciondoA)
            montador.adicionaMontador(new ProdutoMontador(), "Produto_RelaciondoA");
         return montador;
    }
	
    
   	// Poderia passar para classe abstrata.
    public final List<PalavraProduto> getAllSinc() throws DaoException {
    	this.setMontador(null);
    	List<PalavraProduto> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<PalavraProduto>();
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
    public final List<PalavraProduto> getAllSincSoAlteracao() throws DaoException {
    	List<PalavraProduto> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    		saida = null; // Melhorar aqui !!!!
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<PalavraProduto>();
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
	
	public final List<PalavraProduto> getAllSincImpl() throws DaoException {
   		String sql = "select " + camposOrdenadosSinc()   
   			+ " from " + this.DB_TABLE_SINC;
   		PalavraProdutoMontador montador = new PalavraProdutoMontador(true); // sinc ligado
   		this.setMontador(montador);
   		List<PalavraProduto> saida = this.getListaSql(sql);
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
		this.insert((PalavraProduto) obj);
	}
	@Override
	public final void dropCreate() {
		this.dropTable();
		this.criaTabela();
	}
}
