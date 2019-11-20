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
import repcom.modelo.vo.CategoriaProdutoProdutoVo;
import repcom.modelo.vo.FabricaVo;
import repcom.dao.*;
import repcom.dao.montador.*;
import repcom.dao.datasource.DataSourceAplicacao;

public abstract class CategoriaProdutoProdutoDBHelperBase extends DBHelperBase
	implements DaoSincronismo // coloquei aqui para evitar impacto de arquitetura
{

    private static final String DB_NAME = "w_alert";
    public static final String DB_TABLE = "categoria_produto_produto";
    public static final String DB_TABLE_SINC = "categoria_produto_produto_sinc";
    public static final int DB_VERSION = 3;

    protected static final String CLASSNAME = CategoriaProdutoProdutoDBHelperBase.class.getSimpleName();
    protected static final String[] COLS = new String[] { 
        "id_categoria_produto_produto"
        ,"data_inclusao"
		, "id_categoria_produto_ra"
	
		, "id_produto_ra"
	
    };

	protected static final String[] COLS_SINC = new String[] { 
        "id_categoria_produto_produto"
        ,"data_inclusao"
		, "id_categoria_produto_ra"
	
		, "id_produto_ra"
		, "operacao_sinc"
    };

    protected SQLiteDatabase db;
    protected final DBOpenHelper dbOpenHelper;

	@Override
	protected MontadorDaoI criaMontadorPadrao() {
		return new CategoriaProdutoProdutoMontador();
	}
	
	protected String getSqlIndices() {
		return "";
	}
	protected String getSqlCreate(){
		return  "CREATE TABLE "
        + CategoriaProdutoProdutoDBHelperBase.DB_TABLE + " ("
        + "  id_categoria_produto_produto INTEGER PRIMARY KEY "
        + " , data_inclusao INTEGER "
		+ " , id_categoria_produto_ra INTEGER "
		
		+ " , id_produto_ra INTEGER "
		
		+ getSqlIndices()
        + ");";
	}

	

	public static final String DB_CREATE_SINCRONIZADA = "CREATE TABLE IF NOT EXISTS "
        + CategoriaProdutoProdutoDBHelperBase.DB_TABLE_SINC + " ("
        + "  id_categoria_produto_produto INTEGER "
        + " , data_inclusao INTEGER "
		+ " , id_categoria_produto_ra INTEGER "
		
		+ " , id_produto_ra INTEGER "
		
        + ", operacao_sinc TEXT);";


    public static final String DB_CREATE = "CREATE TABLE IF NOT EXISTS "
        + CategoriaProdutoProdutoDBHelperBase.DB_TABLE + " ("
        + "  id_categoria_produto_produto INTEGER PRIMARY KEY "
        + " , data_inclusao INTEGER "
		+ " , id_categoria_produto_ra INTEGER "
		
		+ " , id_produto_ra INTEGER "
		
        + ");";
    
    private static final String DB_DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private static final String DB_DROP = "DROP TABLE IF EXISTS " + DB_TABLE;
    private static final String DB_DROP_SINCRONIZADA = "DROP TABLE IF EXISTS " + DB_TABLE_SINC;
    
    private static class DBOpenHelper extends SQLiteOpenHelper {

       

        public DBOpenHelper(final Context context) {
            super(context, CategoriaProdutoProdutoDBHelperBase.DB_NAME, null, CategoriaProdutoProdutoDBHelperBase.DB_VERSION);
        }

        @Override
        public void onCreate(final SQLiteDatabase db) {
            try {
                db.execSQL(DB_CREATE);
            } catch (SQLException e) {
                Log.e(Constants.LOGTAG, CategoriaProdutoProdutoDBHelperBase.CLASSNAME, e);
            }
        }

        @Override
        public void onOpen(final SQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + CategoriaProdutoProdutoDBHelperBase.DB_TABLE);
            onCreate(db);
        }
    }

    //
    // end inner classes
    //
    
    // Versao Nova
 	public CategoriaProdutoProdutoDBHelperBase() {
    	this.dbOpenHelper = null;
    	setDataSource(DataSourceAplicacao.getInstancia());
    }
    
   
	
	protected ContentValues montaValores(final DCIObjetoDominio valor) {
		CategoriaProdutoProduto item = (CategoriaProdutoProduto) valor;
		ContentValues valores = new ContentValues();
       	putValor(valores,"id_categoria_produto_produto",item.getIdCategoriaProdutoProduto());
       	
       	putValorData(valores,"data_inclusao",item.getDataInclusao());
        		
       	putValor(valores,"id_categoria_produto_ra",item.getIdCategoriaProdutoRa());
       	
       	putValor(valores,"id_produto_ra",item.getIdProdutoRa());
       	
        return valores;
	}


    // **** Chamadas Diretas Objeto Banco de Dados
    private void registraErroChamadaDireta(Exception e) {
    	Log.e(Constants.LOGTAG, CategoriaProdutoProdutoDBHelperBase.CLASSNAME, e);
    }
   
    public final void insert(final CategoriaProdutoProduto item) {
	    try {
	        getDb().insert(CategoriaProdutoProdutoDBHelperBase.DB_TABLE, null, montaValores(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    public final void update(final CategoriaProdutoProduto item) {
	    try {
	        getDb().update(CategoriaProdutoProdutoDBHelperBase.DB_TABLE, montaValores(item), "id_categoria_produto_produto=" + item.getIdCategoriaProdutoProduto(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     // Nao pode ser final o parametro para retornar o objeto com seu Id
    public final void insertSinc(CategoriaProdutoProduto item) {
        try {
        	item.setIdCategoriaProdutoProduto((int)getMaxId() + 1);
        	DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc: " + item.debug());
	        long id = getDb().insert(CategoriaProdutoProdutoDBHelperBase.DB_TABLE, null, montaValores(item));
    	    ((ObjetoSinc)item).setOperacaoSinc("I");
        	getDb().insert(CategoriaProdutoProdutoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     public final void updateSinc(final CategoriaProdutoProduto item) {
        try {
	        DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc: " + item.debug());
	        getDb().update(CategoriaProdutoProdutoDBHelperBase.DB_TABLE, montaValores(item), "id_categoria_produto_produto=" + item.getIdCategoriaProdutoProduto(), null);
	        ((ObjetoSinc)item).setOperacaoSinc("A");
	        CategoriaProdutoProduto atual = this.getSincById(item.getId());
	        if (atual==null) {
	        	getDb().insert(CategoriaProdutoProdutoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	        } else {
	        	if ("I".equals(((ObjetoSinc)atual).getOperacaoSinc()))
	        		((ObjetoSinc)item).setOperacaoSinc("I");
	        	getDb().update(CategoriaProdutoProdutoDBHelperBase.DB_TABLE_SINC, montaValoresSinc(item), "id_categoria_produto_produto=" + item.getIdCategoriaProdutoProduto(), null);
	        }
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    protected final void delete(final long id) {
        try {
			getDb().delete(CategoriaProdutoProdutoDBHelperBase.DB_TABLE, "id_categoria_produto_produto=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    /*
    private void deleteSinc(final long id) {
        try {
			getDb().delete(CategoriaProdutoProdutoDBHelperBase.DB_TABLE_SINC, "id_categoria_produto_produto=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
	*/
    public void limpaSinc(final CategoriaProdutoProduto item) {
    	try {
			getDb().delete(CategoriaProdutoProdutoDBHelperBase.DB_TABLE_SINC, "id_categoria_produto_produto=" + item.getId(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    
    public void deleteSinc(final CategoriaProdutoProduto item) {
    	try {
	        DCLog.dStack(DCLog.DATABASE_ADM, this, "deleteSinc: " + item.debug());
	        delete(item.getId());
	        ((ObjetoSinc)item).setOperacaoSinc("D");
        	getDb().insert(CategoriaProdutoProdutoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
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
    public CategoriaProdutoProduto getById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (CategoriaProdutoProduto) getItemQuery(true, CategoriaProdutoProdutoDBHelperBase.DB_TABLE, CategoriaProdutoProdutoDBHelperBase.COLS, "id_categoria_produto_produto = " + id + "", null, null, null, null,null);
    }
    
    // Esta com orderBy que pode ser bom para exibicoes em tela
    // mas nao e bom para sincronizacao, pensar em ter um metodo para tela e outro para sinc.
    public List<CategoriaProdutoProduto> getAll() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, null);
    }
    public List<CategoriaProdutoProduto> getAllTela() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, orderByColuna());
    }
    
    private CategoriaProdutoProduto getByRowId(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (CategoriaProdutoProduto) getItemQuery(true, CategoriaProdutoProdutoDBHelperBase.DB_TABLE, CategoriaProdutoProdutoDBHelperBase.COLS, "ROWID = " + id + "", null, null, null, null,null);
    }
    private CategoriaProdutoProduto getSincById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (CategoriaProdutoProduto) getItemQuerySinc(true, CategoriaProdutoProdutoDBHelperBase.DB_TABLE_SINC, CategoriaProdutoProdutoDBHelperBase.COLS_SINC, "id_categoria_produto_produto = " + id + "", null, null, null, null,null);
    }
    
    
    public long getMaxId() {
		String sql = "select max(id_categoria_produto_produto) from " + DB_TABLE;
		return this.getNumeroSql(sql);
	}
	protected String orderByColuna() {
    	return null;
    }
	
	
	// Classe e relacionamento
	public List<CategoriaProdutoProduto> getPorReferenteACategoriaProduto(Context contexto, long id) throws DaoException{
		String sql = "select " + camposOrdenados() + 
				" , " + ProdutoDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinProduto_ReferenteA() +
				" where id_categoria_produto_ra = " + id;
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new CategoriaProdutoProdutoMontador(), null);
		montador.adicionaMontador(new ProdutoMontador(), "Produto_ReferenteA");
		setMontador(montador);
		return this.getListaSqlListaInterna(sql);
			
	}
	public List<CategoriaProdutoProduto> getPorReferenteACategoriaProduto(long id) throws DaoException{
		String sql = "select " + camposOrdenados() + 
				" , " + ProdutoDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinProduto_ReferenteA() +
				" where id_categoria_produto_ra = " + id;
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new CategoriaProdutoProdutoMontador(), null);
		montador.adicionaMontador(new ProdutoMontador(), "Produto_ReferenteA");
		setMontador(montador);
		return this.getListaSqlListaInterna(sql);
			
	}
	public List<Produto> getProdutoPorReferenteACategoriaProduto(Context contexto, long id) throws DaoException{
		String sql = "select " + ProdutoDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinProduto_ReferenteA() +
				" where id_categoria_produto_ra = " + id;
		setMontador(new ProdutoMontador());
		List<Produto> saida = this.getListaSql(sql);
		setMontador(null);
		return saida;
	}
	
	
	
	// Classe e relacionamento
	public List<CategoriaProdutoProduto> getPorReferenteAProduto(Context contexto, long id) throws DaoException{
		String sql = "select " + camposOrdenados() + 
				" , " + CategoriaProdutoDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinCategoriaProduto_ReferenteA() +
				" where id_produto_ra = " + id;
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new CategoriaProdutoProdutoMontador(), null);
		montador.adicionaMontador(new CategoriaProdutoMontador(), "CategoriaProduto_ReferenteA");
		setMontador(montador);
		return this.getListaSqlListaInterna(sql);
			
	}
	public List<CategoriaProdutoProduto> getPorReferenteAProduto(long id) throws DaoException{
		String sql = "select " + camposOrdenados() + 
				" , " + CategoriaProdutoDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinCategoriaProduto_ReferenteA() +
				" where id_produto_ra = " + id;
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new CategoriaProdutoProdutoMontador(), null);
		montador.adicionaMontador(new CategoriaProdutoMontador(), "CategoriaProduto_ReferenteA");
		setMontador(montador);
		return this.getListaSqlListaInterna(sql);
			
	}
	public List<CategoriaProduto> getCategoriaProdutoPorReferenteAProduto(Context contexto, long id) throws DaoException{
		String sql = "select " + CategoriaProdutoDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinCategoriaProduto_ReferenteA() +
				" where id_produto_ra = " + id;
		setMontador(new CategoriaProdutoMontador());
		List<CategoriaProduto> saida = this.getListaSql(sql);
		setMontador(null);
		return saida;
	}
	
	
  
  	
	public CategoriaProdutoProduto getPorCategoriaProdutoProduto(long idXXXX, long idYYYYY) {
		return null;
	}
	
  
  
  	
	
	
	// Relacionamento onde esse objeto ? chave estrangeira de outro. ????
	
	private boolean _obtemCategoriaProduto_ReferenteA = false;
	public void setObtemCategoriaProduto_ReferenteA() {
		_obtemCategoriaProduto_ReferenteA = true;
	}
	protected String outterJoinCategoriaProduto_ReferenteA() {
		return " left outer join " + CategoriaProdutoDBHelperBase.DB_TABLE + " on " + CategoriaProdutoDBHelperBase.DB_TABLE + ".id_categoria_produto = " + DB_TABLE + ".id_categoria_produto_ra ";  
	}
	public boolean atualizaReferenteACategoriaProduto(long idCategoriaProdutoProduto, long idCategoriaProdutoRa) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_categoria_produto_ra  = " + idCategoriaProdutoRa +
        " where id_categoria_produto_produto = " +  idCategoriaProdutoProduto;
       	//this.executaSql(sql);
       	return true;
	}
	public CategoriaProdutoProduto obtemPorIdsReferenteACategoriaProduto(long idCategoriaProdutoProduto, long idCategoriaProdutoRa) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_categoria_produto_ra = " + idCategoriaProdutoRa + " and " +
			  " id_categoria_produto_produto = " + idCategoriaProdutoProduto;
		return (CategoriaProdutoProduto) this.geObjetoSql(sql);
	}
	
	
	public static String innerJoinCategoriaProduto_ReferenteA() {
		return " inner join " + CategoriaProdutoDBHelperBase.DB_TABLE + " on " + CategoriaProdutoDBHelperBase.DB_TABLE + ".id_categoria_produto = " + DB_TABLE + ".id_categoria_produto_ra ";  
	}
	public static String outerJoinCategoriaProduto_ReferenteA() {
		return " left outer join " + CategoriaProdutoDBHelperBase.DB_TABLE + " on " + CategoriaProdutoDBHelperBase.DB_TABLE + ".id_categoria_produto = " + DB_TABLE + ".id_categoria_produto_ra ";  
	}
	public static String innerJoinSincCategoriaProduto_ReferenteA() {
		return " inner join " + CategoriaProdutoDBHelperBase.DB_TABLE_SINC + " on " + CategoriaProdutoDBHelperBase.DB_TABLE_SINC + ".id_categoria_produto = " + DB_TABLE_SINC + ".id_categoria_produto_ra ";  
	}
	public static String outerJoinSincCategoriaProduto_ReferenteA() {
		return " left outer join " + CategoriaProdutoDBHelperBase.DB_TABLE_SINC + " on " + CategoriaProdutoDBHelperBase.DB_TABLE_SINC + ".id_categoria_produto = " + DB_TABLE_SINC + ".id_categoria_produto_ra ";  
	}
	
 	
	private boolean _obtemProduto_ReferenteA = false;
	public void setObtemProduto_ReferenteA() {
		_obtemProduto_ReferenteA = true;
	}
	protected String outterJoinProduto_ReferenteA() {
		return " left outer join " + ProdutoDBHelperBase.DB_TABLE + " on " + ProdutoDBHelperBase.DB_TABLE + ".id_produto = " + DB_TABLE + ".id_produto_ra ";  
	}
	public boolean atualizaReferenteAProduto(long idCategoriaProdutoProduto, long idProdutoRa) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_produto_ra  = " + idProdutoRa +
        " where id_categoria_produto_produto = " +  idCategoriaProdutoProduto;
       	//this.executaSql(sql);
       	return true;
	}
	public CategoriaProdutoProduto obtemPorIdsReferenteAProduto(long idCategoriaProdutoProduto, long idProdutoRa) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_produto_ra = " + idProdutoRa + " and " +
			  " id_categoria_produto_produto = " + idCategoriaProdutoProduto;
		return (CategoriaProdutoProduto) this.geObjetoSql(sql);
	}
	
	
	public static String innerJoinProduto_ReferenteA() {
		return " inner join " + ProdutoDBHelperBase.DB_TABLE + " on " + ProdutoDBHelperBase.DB_TABLE + ".id_produto = " + DB_TABLE + ".id_produto_ra ";  
	}
	public static String outerJoinProduto_ReferenteA() {
		return " left outer join " + ProdutoDBHelperBase.DB_TABLE + " on " + ProdutoDBHelperBase.DB_TABLE + ".id_produto = " + DB_TABLE + ".id_produto_ra ";  
	}
	public static String innerJoinSincProduto_ReferenteA() {
		return " inner join " + ProdutoDBHelperBase.DB_TABLE_SINC + " on " + ProdutoDBHelperBase.DB_TABLE_SINC + ".id_produto = " + DB_TABLE_SINC + ".id_produto_ra ";  
	}
	public static String outerJoinSincProduto_ReferenteA() {
		return " left outer join " + ProdutoDBHelperBase.DB_TABLE_SINC + " on " + ProdutoDBHelperBase.DB_TABLE_SINC + ".id_produto = " + DB_TABLE_SINC + ".id_produto_ra ";  
	}
	
 	
	// ********************************************************************	
	
	
	public static String camposOrdenados() 
	{
		return " categoria_produto_produto.id_categoria_produto_produto " 
		+ " ,categoria_produto_produto.data_inclusao " 
		+ " ,categoria_produto_produto.id_categoria_produto_ra " 
		+ " ,categoria_produto_produto.id_produto_ra " 
		;
	}
	public static String camposOrdenadosSinc() 
	{
		return " categoria_produto_produto_sinc.id_categoria_produto_produto " 
		+ " ,categoria_produto_produto_sinc.data_inclusao " 
		+ " ,categoria_produto_produto_sinc.id_categoria_produto_ra " 
		+ " ,categoria_produto_produto_sinc.id_produto_ra " 
		
		+ " ,categoria_produto_produto_sinc.operacao_sinc "
		;
	}
	public static String camposOrdenadosAlias(String nomeTabela) 
	{
		return  nomeTabela + ".id_categoria_produto_produto " 
		+ " , " +  "DATE_FORMAT(" + nomeTabela + ".data_inclusao,'%d-%m-%Y') " 
		+ " , " + nomeTabela + ".id_categoria_produto_ra " 
		+ " , " + nomeTabela + ".id_produto_ra " 
		;
	}
	
	protected String camposOrdenadosJoin()
    {
        String saida = camposOrdenados();
		saida += (this._obtemCategoriaProduto_ReferenteA?" , " +CategoriaProdutoDBHelperBase.camposOrdenados():"");
		saida += (this._obtemProduto_ReferenteA?" , " +ProdutoDBHelperBase.camposOrdenados():"");
        return saida;
    }
    
        
    public void limpaObtem()
    {
		_obtemCategoriaProduto_ReferenteA = false;
		_obtemProduto_ReferenteA = false;
    }
    
	protected String outterJoinAgrupado()
    {
        String saida = " ";
		saida += (this._obtemCategoriaProduto_ReferenteA? outterJoinCategoriaProduto_ReferenteA() + " ":"");
		saida += (this._obtemProduto_ReferenteA? outterJoinProduto_ReferenteA() + " ":"");
        return saida;
    }
    protected MontadorDaoI getMontadorAgrupado()
    {
        MontadorDaoComposite montador = new MontadorDaoComposite();
        montador.adicionaMontador(new CategoriaProdutoProdutoMontador(), null);
		if (this._obtemCategoriaProduto_ReferenteA)
            montador.adicionaMontador(new CategoriaProdutoMontador(), "CategoriaProduto_ReferenteA");
		if (this._obtemProduto_ReferenteA)
            montador.adicionaMontador(new ProdutoMontador(), "Produto_ReferenteA");
         return montador;
    }
	
    
   	// Poderia passar para classe abstrata.
    public final List<CategoriaProdutoProduto> getAllSinc() throws DaoException {
    	this.setMontador(null);
    	List<CategoriaProdutoProduto> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<CategoriaProdutoProduto>();
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
    public final List<CategoriaProdutoProduto> getAllSincSoAlteracao() throws DaoException {
    	List<CategoriaProdutoProduto> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    		saida = null; // Melhorar aqui !!!!
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<CategoriaProdutoProduto>();
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
	
	public final List<CategoriaProdutoProduto> getAllSincImpl() throws DaoException {
   		String sql = "select " + camposOrdenadosSinc()   
   			+ " from " + this.DB_TABLE_SINC;
   		CategoriaProdutoProdutoMontador montador = new CategoriaProdutoProdutoMontador(true); // sinc ligado
   		this.setMontador(montador);
   		List<CategoriaProdutoProduto> saida = this.getListaSql(sql);
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
		this.insert((CategoriaProdutoProduto) obj);
	}
	@Override
	public final void dropCreate() {
		this.dropTable();
		this.criaTabela();
	}
}
