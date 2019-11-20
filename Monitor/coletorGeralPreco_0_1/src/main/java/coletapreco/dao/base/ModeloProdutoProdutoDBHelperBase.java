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
import coletapreco.modelo.vo.ModeloProdutoProdutoVo;
import coletapreco.modelo.vo.FabricaVo;
import coletapreco.dao.*;
import coletapreco.dao.montador.*;
import coletapreco.dao.datasource.DataSourceAplicacao;

public abstract class ModeloProdutoProdutoDBHelperBase extends DBHelperBase
	implements DaoSincronismo // coloquei aqui para evitar impacto de arquitetura
{

    private static final String DB_NAME = "w_alert";
    public static final String DB_TABLE = "modelo_produto_produto";
    public static final String DB_TABLE_SINC = "modelo_produto_produto_sinc";
    public static final int DB_VERSION = 3;

    protected static final String CLASSNAME = ModeloProdutoProdutoDBHelperBase.class.getSimpleName();
    protected static final String[] COLS = new String[] { 
        "id_modelo_produto_produto"
		, "id_modelo_produto_ra"
	
		, "id_produto_ra"
	
    };

	protected static final String[] COLS_SINC = new String[] { 
        "id_modelo_produto_produto"
		, "id_modelo_produto_ra"
	
		, "id_produto_ra"
		, "operacao_sinc"
    };

    protected SQLiteDatabase db;
    protected final DBOpenHelper dbOpenHelper;

	@Override
	protected MontadorDaoI criaMontadorPadrao() {
		return new ModeloProdutoProdutoMontador();
	}
	
	protected String getSqlIndices() {
		return "";
	}
	protected String getSqlCreate(){
		return  "CREATE TABLE "
        + ModeloProdutoProdutoDBHelperBase.DB_TABLE + " ("
        + "  id_modelo_produto_produto INTEGER PRIMARY KEY "
		+ " , id_modelo_produto_ra INTEGER "
		
		+ " , id_produto_ra INTEGER "
		
		+ getSqlIndices()
        + ");";
	}

	

	public static final String DB_CREATE_SINCRONIZADA = "CREATE TABLE IF NOT EXISTS "
        + ModeloProdutoProdutoDBHelperBase.DB_TABLE_SINC + " ("
        + "  id_modelo_produto_produto INTEGER "
		+ " , id_modelo_produto_ra INTEGER "
		
		+ " , id_produto_ra INTEGER "
		
        + ", operacao_sinc TEXT);";


    public static final String DB_CREATE = "CREATE TABLE IF NOT EXISTS "
        + ModeloProdutoProdutoDBHelperBase.DB_TABLE + " ("
        + "  id_modelo_produto_produto INTEGER PRIMARY KEY "
		+ " , id_modelo_produto_ra INTEGER "
		
		+ " , id_produto_ra INTEGER "
		
        + ");";
    
    private static final String DB_DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private static final String DB_DROP = "DROP TABLE IF EXISTS " + DB_TABLE;
    private static final String DB_DROP_SINCRONIZADA = "DROP TABLE IF EXISTS " + DB_TABLE_SINC;
    
    private static class DBOpenHelper extends SQLiteOpenHelper {

       

        public DBOpenHelper(final Context context) {
            super(context, ModeloProdutoProdutoDBHelperBase.DB_NAME, null, ModeloProdutoProdutoDBHelperBase.DB_VERSION);
        }

        @Override
        public void onCreate(final SQLiteDatabase db) {
            try {
                db.execSQL(DB_CREATE);
            } catch (SQLException e) {
                Log.e(Constants.LOGTAG, ModeloProdutoProdutoDBHelperBase.CLASSNAME, e);
            }
        }

        @Override
        public void onOpen(final SQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + ModeloProdutoProdutoDBHelperBase.DB_TABLE);
            onCreate(db);
        }
    }

    //
    // end inner classes
    //
    
    // Versao Nova
 	public ModeloProdutoProdutoDBHelperBase() {
    	this.dbOpenHelper = null;
    	setDataSource(DataSourceAplicacao.getInstancia());
    }
    
   
	
	protected ContentValues montaValores(final DCIObjetoDominio valor) {
		ModeloProdutoProduto item = (ModeloProdutoProduto) valor;
		ContentValues valores = new ContentValues();
       	putValor(valores,"id_modelo_produto_produto",item.getIdModeloProdutoProduto());
       	
       	putValor(valores,"id_modelo_produto_ra",item.getIdModeloProdutoRa());
       	
       	putValor(valores,"id_produto_ra",item.getIdProdutoRa());
       	
        return valores;
	}


    // **** Chamadas Diretas Objeto Banco de Dados
    private void registraErroChamadaDireta(Exception e) {
    	Log.e(Constants.LOGTAG, ModeloProdutoProdutoDBHelperBase.CLASSNAME, e);
    }
   
    public final void insert(final ModeloProdutoProduto item) {
	    try {
	        getDb().insert(ModeloProdutoProdutoDBHelperBase.DB_TABLE, null, montaValores(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    public final void update(final ModeloProdutoProduto item) {
	    try {
	        getDb().update(ModeloProdutoProdutoDBHelperBase.DB_TABLE, montaValores(item), "id_modelo_produto_produto=" + item.getIdModeloProdutoProduto(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     // Nao pode ser final o parametro para retornar o objeto com seu Id
    public final void insertSinc(ModeloProdutoProduto item) {
        try {
        	item.setIdModeloProdutoProduto((int)getMaxId() + 1);
        	DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc: " + item.debug());
	        long id = getDb().insert(ModeloProdutoProdutoDBHelperBase.DB_TABLE, null, montaValores(item));
    	    ((ObjetoSinc)item).setOperacaoSinc("I");
        	getDb().insert(ModeloProdutoProdutoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     public final void updateSinc(final ModeloProdutoProduto item) {
        try {
	        DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc: " + item.debug());
	        getDb().update(ModeloProdutoProdutoDBHelperBase.DB_TABLE, montaValores(item), "id_modelo_produto_produto=" + item.getIdModeloProdutoProduto(), null);
	        ((ObjetoSinc)item).setOperacaoSinc("A");
	        ModeloProdutoProduto atual = this.getSincById(item.getId());
	        if (atual==null) {
	        	getDb().insert(ModeloProdutoProdutoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	        } else {
	        	if ("I".equals(((ObjetoSinc)atual).getOperacaoSinc()))
	        		((ObjetoSinc)item).setOperacaoSinc("I");
	        	getDb().update(ModeloProdutoProdutoDBHelperBase.DB_TABLE_SINC, montaValoresSinc(item), "id_modelo_produto_produto=" + item.getIdModeloProdutoProduto(), null);
	        }
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    protected final void delete(final long id) {
        try {
			getDb().delete(ModeloProdutoProdutoDBHelperBase.DB_TABLE, "id_modelo_produto_produto=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    /*
    private void deleteSinc(final long id) {
        try {
			getDb().delete(ModeloProdutoProdutoDBHelperBase.DB_TABLE_SINC, "id_modelo_produto_produto=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
	*/
    public void limpaSinc(final ModeloProdutoProduto item) {
    	try {
			getDb().delete(ModeloProdutoProdutoDBHelperBase.DB_TABLE_SINC, "id_modelo_produto_produto=" + item.getId(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    
    public void deleteSinc(final ModeloProdutoProduto item) {
    	try {
	        DCLog.dStack(DCLog.DATABASE_ADM, this, "deleteSinc: " + item.debug());
	        delete(item.getId());
	        ((ObjetoSinc)item).setOperacaoSinc("D");
        	getDb().insert(ModeloProdutoProdutoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
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
    public ModeloProdutoProduto getById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (ModeloProdutoProduto) getItemQuery(true, ModeloProdutoProdutoDBHelperBase.DB_TABLE, ModeloProdutoProdutoDBHelperBase.COLS, "id_modelo_produto_produto = " + id + "", null, null, null, null,null);
    }
    
    // Esta com orderBy que pode ser bom para exibicoes em tela
    // mas nao e bom para sincronizacao, pensar em ter um metodo para tela e outro para sinc.
    public List<ModeloProdutoProduto> getAll() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, null);
    }
    public List<ModeloProdutoProduto> getAllTela() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, orderByColuna());
    }
    
    private ModeloProdutoProduto getByRowId(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (ModeloProdutoProduto) getItemQuery(true, ModeloProdutoProdutoDBHelperBase.DB_TABLE, ModeloProdutoProdutoDBHelperBase.COLS, "ROWID = " + id + "", null, null, null, null,null);
    }
    private ModeloProdutoProduto getSincById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (ModeloProdutoProduto) getItemQuerySinc(true, ModeloProdutoProdutoDBHelperBase.DB_TABLE_SINC, ModeloProdutoProdutoDBHelperBase.COLS_SINC, "id_modelo_produto_produto = " + id + "", null, null, null, null,null);
    }
    
    
    public long getMaxId() {
		String sql = "select max(id_modelo_produto_produto) from " + DB_TABLE;
		return this.getNumeroSql(sql);
	}
	protected String orderByColuna() {
    	return null;
    }
	
	
	// Classe e relacionamento
	public List<ModeloProdutoProduto> getPorReferenteAModeloProduto(Context contexto, long id) throws DaoException{
		String sql = "select " + camposOrdenados() + 
				" , " + ProdutoDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinProduto_ReferenteA() +
				" where id_modelo_produto_ra = " + id;
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new ModeloProdutoProdutoMontador(), null);
		montador.adicionaMontador(new ProdutoMontador(), "Produto_ReferenteA");
		setMontador(montador);
		return this.getListaSqlListaInterna(sql);
			
	}
	public List<ModeloProdutoProduto> getPorReferenteAModeloProduto(long id) throws DaoException{
		String sql = "select " + camposOrdenados() + 
				" , " + ProdutoDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinProduto_ReferenteA() +
				" where id_modelo_produto_ra = " + id;
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new ModeloProdutoProdutoMontador(), null);
		montador.adicionaMontador(new ProdutoMontador(), "Produto_ReferenteA");
		setMontador(montador);
		return this.getListaSqlListaInterna(sql);
			
	}
	public List<Produto> getProdutoPorReferenteAModeloProduto(Context contexto, long id) throws DaoException{
		String sql = "select " + ProdutoDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinProduto_ReferenteA() +
				" where id_modelo_produto_ra = " + id;
		setMontador(new ProdutoMontador());
		List<Produto> saida = this.getListaSql(sql);
		setMontador(null);
		return saida;
	}
	
	
	
	// Classe e relacionamento
	public List<ModeloProdutoProduto> getPorReferenteAProduto(Context contexto, long id) throws DaoException{
		String sql = "select " + camposOrdenados() + 
				" , " + ModeloProdutoDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinModeloProduto_ReferenteA() +
				" where id_produto_ra = " + id;
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new ModeloProdutoProdutoMontador(), null);
		montador.adicionaMontador(new ModeloProdutoMontador(), "ModeloProduto_ReferenteA");
		setMontador(montador);
		return this.getListaSqlListaInterna(sql);
			
	}
	public List<ModeloProdutoProduto> getPorReferenteAProduto(long id) throws DaoException{
		String sql = "select " + camposOrdenados() + 
				" , " + ModeloProdutoDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinModeloProduto_ReferenteA() +
				" where id_produto_ra = " + id;
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new ModeloProdutoProdutoMontador(), null);
		montador.adicionaMontador(new ModeloProdutoMontador(), "ModeloProduto_ReferenteA");
		setMontador(montador);
		return this.getListaSqlListaInterna(sql);
			
	}
	public List<ModeloProduto> getModeloProdutoPorReferenteAProduto(Context contexto, long id) throws DaoException{
		String sql = "select " + ModeloProdutoDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinModeloProduto_ReferenteA() +
				" where id_produto_ra = " + id;
		setMontador(new ModeloProdutoMontador());
		List<ModeloProduto> saida = this.getListaSql(sql);
		setMontador(null);
		return saida;
	}
	
	
  
  	
	public ModeloProdutoProduto getPorModeloProdutoProduto(long idXXXX, long idYYYYY) {
		return null;
	}
	
  
  
  	
	
	
	// Relacionamento onde esse objeto ? chave estrangeira de outro. ????
	
	private boolean _obtemModeloProduto_ReferenteA = false;
	public void setObtemModeloProduto_ReferenteA() {
		_obtemModeloProduto_ReferenteA = true;
	}
	protected String outterJoinModeloProduto_ReferenteA() {
		return " left outer join " + ModeloProdutoDBHelperBase.DB_TABLE + " on " + ModeloProdutoDBHelperBase.DB_TABLE + ".id_modelo_produto = " + DB_TABLE + ".id_modelo_produto_ra ";  
	}
	public boolean atualizaReferenteAModeloProduto(long idModeloProdutoProduto, long idModeloProdutoRa) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_modelo_produto_ra  = " + idModeloProdutoRa +
        " where id_modelo_produto_produto = " +  idModeloProdutoProduto;
       	//this.executaSql(sql);
       	return true;
	}
	public ModeloProdutoProduto obtemPorIdsReferenteAModeloProduto(long idModeloProdutoProduto, long idModeloProdutoRa) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_modelo_produto_ra = " + idModeloProdutoRa + " and " +
			  " id_modelo_produto_produto = " + idModeloProdutoProduto;
		return (ModeloProdutoProduto) this.geObjetoSql(sql);
	}
	
	
	public static String innerJoinModeloProduto_ReferenteA() {
		return " inner join " + ModeloProdutoDBHelperBase.DB_TABLE + " on " + ModeloProdutoDBHelperBase.DB_TABLE + ".id_modelo_produto = " + DB_TABLE + ".id_modelo_produto_ra ";  
	}
	public static String outerJoinModeloProduto_ReferenteA() {
		return " left outer join " + ModeloProdutoDBHelperBase.DB_TABLE + " on " + ModeloProdutoDBHelperBase.DB_TABLE + ".id_modelo_produto = " + DB_TABLE + ".id_modelo_produto_ra ";  
	}
	public static String innerJoinSincModeloProduto_ReferenteA() {
		return " inner join " + ModeloProdutoDBHelperBase.DB_TABLE_SINC + " on " + ModeloProdutoDBHelperBase.DB_TABLE_SINC + ".id_modelo_produto = " + DB_TABLE_SINC + ".id_modelo_produto_ra ";  
	}
	public static String outerJoinSincModeloProduto_ReferenteA() {
		return " left outer join " + ModeloProdutoDBHelperBase.DB_TABLE_SINC + " on " + ModeloProdutoDBHelperBase.DB_TABLE_SINC + ".id_modelo_produto = " + DB_TABLE_SINC + ".id_modelo_produto_ra ";  
	}
	
 	
	private boolean _obtemProduto_ReferenteA = false;
	public void setObtemProduto_ReferenteA() {
		_obtemProduto_ReferenteA = true;
	}
	protected String outterJoinProduto_ReferenteA() {
		return " left outer join " + ProdutoDBHelperBase.DB_TABLE + " on " + ProdutoDBHelperBase.DB_TABLE + ".id_produto = " + DB_TABLE + ".id_produto_ra ";  
	}
	public boolean atualizaReferenteAProduto(long idModeloProdutoProduto, long idProdutoRa) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_produto_ra  = " + idProdutoRa +
        " where id_modelo_produto_produto = " +  idModeloProdutoProduto;
       	//this.executaSql(sql);
       	return true;
	}
	public ModeloProdutoProduto obtemPorIdsReferenteAProduto(long idModeloProdutoProduto, long idProdutoRa) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_produto_ra = " + idProdutoRa + " and " +
			  " id_modelo_produto_produto = " + idModeloProdutoProduto;
		return (ModeloProdutoProduto) this.geObjetoSql(sql);
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
		return " modelo_produto_produto.id_modelo_produto_produto " 
		+ " ,modelo_produto_produto.id_modelo_produto_ra " 
		+ " ,modelo_produto_produto.id_produto_ra " 
		;
	}
	public static String camposOrdenadosSinc() 
	{
		return " modelo_produto_produto_sinc.id_modelo_produto_produto " 
		+ " ,modelo_produto_produto_sinc.id_modelo_produto_ra " 
		+ " ,modelo_produto_produto_sinc.id_produto_ra " 
		
		+ " ,modelo_produto_produto_sinc.operacao_sinc "
		;
	}
	public static String camposOrdenadosAlias(String nomeTabela) 
	{
		return  nomeTabela + ".id_modelo_produto_produto " 
		+ " , " + nomeTabela + ".id_modelo_produto_ra " 
		+ " , " + nomeTabela + ".id_produto_ra " 
		;
	}
	
	protected String camposOrdenadosJoin()
    {
        String saida = camposOrdenados();
		saida += (this._obtemModeloProduto_ReferenteA?" , " +ModeloProdutoDBHelperBase.camposOrdenados():"");
		saida += (this._obtemProduto_ReferenteA?" , " +ProdutoDBHelperBase.camposOrdenados():"");
        return saida;
    }
    
        
    public void limpaObtem()
    {
		_obtemModeloProduto_ReferenteA = false;
		_obtemProduto_ReferenteA = false;
    }
    
	protected String outterJoinAgrupado()
    {
        String saida = " ";
		saida += (this._obtemModeloProduto_ReferenteA? outterJoinModeloProduto_ReferenteA() + " ":"");
		saida += (this._obtemProduto_ReferenteA? outterJoinProduto_ReferenteA() + " ":"");
        return saida;
    }
    protected MontadorDaoI getMontadorAgrupado()
    {
        MontadorDaoComposite montador = new MontadorDaoComposite();
        montador.adicionaMontador(new ModeloProdutoProdutoMontador(), null);
		if (this._obtemModeloProduto_ReferenteA)
            montador.adicionaMontador(new ModeloProdutoMontador(), "ModeloProduto_ReferenteA");
		if (this._obtemProduto_ReferenteA)
            montador.adicionaMontador(new ProdutoMontador(), "Produto_ReferenteA");
         return montador;
    }
	
    
   	// Poderia passar para classe abstrata.
    public final List<ModeloProdutoProduto> getAllSinc() throws DaoException {
    	this.setMontador(null);
    	List<ModeloProdutoProduto> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<ModeloProdutoProduto>();
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
    public final List<ModeloProdutoProduto> getAllSincSoAlteracao() throws DaoException {
    	List<ModeloProdutoProduto> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    		saida = null; // Melhorar aqui !!!!
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<ModeloProdutoProduto>();
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
	
	public final List<ModeloProdutoProduto> getAllSincImpl() throws DaoException {
   		String sql = "select " + camposOrdenadosSinc()   
   			+ " from " + this.DB_TABLE_SINC;
   		ModeloProdutoProdutoMontador montador = new ModeloProdutoProdutoMontador(true); // sinc ligado
   		this.setMontador(montador);
   		List<ModeloProdutoProduto> saida = this.getListaSql(sql);
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
		this.insert((ModeloProdutoProduto) obj);
	}
	@Override
	public final void dropCreate() {
		this.dropTable();
		this.criaTabela();
	}
}
