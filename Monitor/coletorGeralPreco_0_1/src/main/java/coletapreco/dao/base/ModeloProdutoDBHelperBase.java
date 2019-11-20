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
import coletapreco.modelo.vo.ModeloProdutoVo;
import coletapreco.modelo.vo.FabricaVo;
import coletapreco.dao.*;
import coletapreco.dao.montador.*;
import coletapreco.dao.datasource.DataSourceAplicacao;

public abstract class ModeloProdutoDBHelperBase extends DBHelperBase
	implements DaoSincronismo // coloquei aqui para evitar impacto de arquitetura
{

    private static final String DB_NAME = "w_alert";
    public static final String DB_TABLE = "modelo_produto";
    public static final String DB_TABLE_SINC = "modelo_produto_sinc";
    public static final int DB_VERSION = 3;

    protected static final String CLASSNAME = ModeloProdutoDBHelperBase.class.getSimpleName();
    protected static final String[] COLS = new String[] { 
        "id_modelo_produto"
        ,"nome_modelo_produto"
    };

	protected static final String[] COLS_SINC = new String[] { 
        "id_modelo_produto"
        ,"nome_modelo_produto"	, "operacao_sinc"
    };

    protected SQLiteDatabase db;
    protected final DBOpenHelper dbOpenHelper;

	@Override
	protected MontadorDaoI criaMontadorPadrao() {
		return new ModeloProdutoMontador();
	}
	
	protected String getSqlIndices() {
		return "";
	}
	protected String getSqlCreate(){
		return  "CREATE TABLE "
        + ModeloProdutoDBHelperBase.DB_TABLE + " ("
        + "  id_modelo_produto INTEGER PRIMARY KEY "
        + " , nome_modelo_produto TEXT "
		+ getSqlIndices()
        + ");";
	}

	

	public static final String DB_CREATE_SINCRONIZADA = "CREATE TABLE IF NOT EXISTS "
        + ModeloProdutoDBHelperBase.DB_TABLE_SINC + " ("
        + "  id_modelo_produto INTEGER "
        + " , nome_modelo_produto TEXT "
        + ", operacao_sinc TEXT);";


    public static final String DB_CREATE = "CREATE TABLE IF NOT EXISTS "
        + ModeloProdutoDBHelperBase.DB_TABLE + " ("
        + "  id_modelo_produto INTEGER PRIMARY KEY "
        + " , nome_modelo_produto TEXT "
        + ");";
    
    private static final String DB_DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private static final String DB_DROP = "DROP TABLE IF EXISTS " + DB_TABLE;
    private static final String DB_DROP_SINCRONIZADA = "DROP TABLE IF EXISTS " + DB_TABLE_SINC;
    
    private static class DBOpenHelper extends SQLiteOpenHelper {

       

        public DBOpenHelper(final Context context) {
            super(context, ModeloProdutoDBHelperBase.DB_NAME, null, ModeloProdutoDBHelperBase.DB_VERSION);
        }

        @Override
        public void onCreate(final SQLiteDatabase db) {
            try {
                db.execSQL(DB_CREATE);
            } catch (SQLException e) {
                Log.e(Constants.LOGTAG, ModeloProdutoDBHelperBase.CLASSNAME, e);
            }
        }

        @Override
        public void onOpen(final SQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + ModeloProdutoDBHelperBase.DB_TABLE);
            onCreate(db);
        }
    }

    //
    // end inner classes
    //
    
    // Versao Nova
 	public ModeloProdutoDBHelperBase() {
    	this.dbOpenHelper = null;
    	setDataSource(DataSourceAplicacao.getInstancia());
    }
    
   
	
	protected ContentValues montaValores(final DCIObjetoDominio valor) {
		ModeloProduto item = (ModeloProduto) valor;
		ContentValues valores = new ContentValues();
       	putValor(valores,"id_modelo_produto",item.getIdModeloProduto());
       	
       	putValor(valores,"nome_modelo_produto",item.getNomeModeloProduto());
       	
        return valores;
	}


    // **** Chamadas Diretas Objeto Banco de Dados
    private void registraErroChamadaDireta(Exception e) {
    	Log.e(Constants.LOGTAG, ModeloProdutoDBHelperBase.CLASSNAME, e);
    }
   
    public final void insert(final ModeloProduto item) {
	    try {
	        getDb().insert(ModeloProdutoDBHelperBase.DB_TABLE, null, montaValores(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    public final void update(final ModeloProduto item) {
	    try {
	        getDb().update(ModeloProdutoDBHelperBase.DB_TABLE, montaValores(item), "id_modelo_produto=" + item.getIdModeloProduto(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     // Nao pode ser final o parametro para retornar o objeto com seu Id
    public final void insertSinc(ModeloProduto item) {
        try {
        	item.setIdModeloProduto((int)getMaxId() + 1);
        	DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc: " + item.debug());
	        long id = getDb().insert(ModeloProdutoDBHelperBase.DB_TABLE, null, montaValores(item));
    	    ((ObjetoSinc)item).setOperacaoSinc("I");
        	getDb().insert(ModeloProdutoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     public final void updateSinc(final ModeloProduto item) {
        try {
	        DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc: " + item.debug());
	        getDb().update(ModeloProdutoDBHelperBase.DB_TABLE, montaValores(item), "id_modelo_produto=" + item.getIdModeloProduto(), null);
	        ((ObjetoSinc)item).setOperacaoSinc("A");
	        ModeloProduto atual = this.getSincById(item.getId());
	        if (atual==null) {
	        	getDb().insert(ModeloProdutoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	        } else {
	        	if ("I".equals(((ObjetoSinc)atual).getOperacaoSinc()))
	        		((ObjetoSinc)item).setOperacaoSinc("I");
	        	getDb().update(ModeloProdutoDBHelperBase.DB_TABLE_SINC, montaValoresSinc(item), "id_modelo_produto=" + item.getIdModeloProduto(), null);
	        }
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    protected final void delete(final long id) {
        try {
			getDb().delete(ModeloProdutoDBHelperBase.DB_TABLE, "id_modelo_produto=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    /*
    private void deleteSinc(final long id) {
        try {
			getDb().delete(ModeloProdutoDBHelperBase.DB_TABLE_SINC, "id_modelo_produto=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
	*/
    public void limpaSinc(final ModeloProduto item) {
    	try {
			getDb().delete(ModeloProdutoDBHelperBase.DB_TABLE_SINC, "id_modelo_produto=" + item.getId(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    
    public void deleteSinc(final ModeloProduto item) {
    	try {
	        DCLog.dStack(DCLog.DATABASE_ADM, this, "deleteSinc: " + item.debug());
	        delete(item.getId());
	        ((ObjetoSinc)item).setOperacaoSinc("D");
        	getDb().insert(ModeloProdutoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
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
    public ModeloProduto getById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (ModeloProduto) getItemQuery(true, ModeloProdutoDBHelperBase.DB_TABLE, ModeloProdutoDBHelperBase.COLS, "id_modelo_produto = " + id + "", null, null, null, null,null);
    }
    
    // Esta com orderBy que pode ser bom para exibicoes em tela
    // mas nao e bom para sincronizacao, pensar em ter um metodo para tela e outro para sinc.
    public List<ModeloProduto> getAll() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, null);
    }
    public List<ModeloProduto> getAllTela() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, orderByColuna());
    }
    
    private ModeloProduto getByRowId(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (ModeloProduto) getItemQuery(true, ModeloProdutoDBHelperBase.DB_TABLE, ModeloProdutoDBHelperBase.COLS, "ROWID = " + id + "", null, null, null, null,null);
    }
    private ModeloProduto getSincById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (ModeloProduto) getItemQuerySinc(true, ModeloProdutoDBHelperBase.DB_TABLE_SINC, ModeloProdutoDBHelperBase.COLS_SINC, "id_modelo_produto = " + id + "", null, null, null, null,null);
    }
    
    
    public long getMaxId() {
		String sql = "select max(id_modelo_produto) from " + DB_TABLE;
		return this.getNumeroSql(sql);
	}
	protected String orderByColuna() {
    	return null;
    }
	
  
  	
  
  
  	
	/*
	public ModeloProduto obtemPorModeloProdutoProdutoReferenteA(long id) {
		string sql;
		sql = "select " + camposOrdenados() +
			" from " + tabelaSelect() +
			innerJoinModeloProdutoProduto_ReferenteA() + 
			" where id_modelo_produto_produto = " + id;
		return (ModeloProduto) getObjeto(sql);
	}
	*/
	public static String innerJoinModeloProdutoProduto_ReferenteA() {
		return " inner join " + ModeloProdutoProdutoDBHelperBase.DB_TABLE + " on " + ModeloProdutoProdutoDBHelperBase.DB_TABLE + ".id_modelo_produto_ra = " + DB_TABLE + ".id_modelo_produto ";  
	}
	public static String innerJoinSincModeloProdutoProduto_ReferenteA() {
		return " inner join " + ModeloProdutoProdutoDBHelperBase.DB_TABLE_SINC + " on " + ModeloProdutoProdutoDBHelperBase.DB_TABLE_SINC + ".id_modelo_produto_ra = " + DB_TABLE_SINC + ".id_modelo_produto ";  
	}
	public static String outerJoinModeloProdutoProduto_ReferenteA() {
		return " left outer join " + ModeloProdutoProdutoDBHelperBase.DB_TABLE + " on " + ModeloProdutoProdutoDBHelperBase.DB_TABLE + ".id_modelo_produto_ra = " + DB_TABLE + ".id_modelo_produto ";  
	}
	public static String outerJoinSincModeloProdutoProduto_ReferenteA() {
		return " left outer join " + ModeloProdutoProdutoDBHelperBase.DB_TABLE_SINC + " on " + ModeloProdutoProdutoDBHelperBase.DB_TABLE_SINC + ".id_modelo_produto_ra = " + DB_TABLE_SINC + ".id_modelo_produto ";  
	}
 	
	
	
	// Relacionamento onde esse objeto ? chave estrangeira de outro. ????
	
	// ********************************************************************	
	
	
	public static String camposOrdenados() 
	{
		return " modelo_produto.id_modelo_produto " 
		+ " ,modelo_produto.nome_modelo_produto " 
		;
	}
	public static String camposOrdenadosSinc() 
	{
		return " modelo_produto_sinc.id_modelo_produto " 
		+ " ,modelo_produto_sinc.nome_modelo_produto " 
		
		+ " ,modelo_produto_sinc.operacao_sinc "
		;
	}
	public static String camposOrdenadosAlias(String nomeTabela) 
	{
		return  nomeTabela + ".id_modelo_produto " 
		+ " , " + nomeTabela + ".nome_modelo_produto " 
		;
	}
	
	protected String camposOrdenadosJoin()
    {
        String saida = camposOrdenados();
        return saida;
    }
    
        
    public void limpaObtem()
    {
    }
    
	protected String outterJoinAgrupado()
    {
        String saida = " ";
        return saida;
    }
    protected MontadorDaoI getMontadorAgrupado()
    {
        MontadorDaoComposite montador = new MontadorDaoComposite();
        montador.adicionaMontador(new ModeloProdutoMontador(), null);
         return montador;
    }
	
    
   	// Poderia passar para classe abstrata.
    public final List<ModeloProduto> getAllSinc() throws DaoException {
    	this.setMontador(null);
    	List<ModeloProduto> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<ModeloProduto>();
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
    public final List<ModeloProduto> getAllSincSoAlteracao() throws DaoException {
    	List<ModeloProduto> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    		saida = null; // Melhorar aqui !!!!
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<ModeloProduto>();
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
	
	public final List<ModeloProduto> getAllSincImpl() throws DaoException {
   		String sql = "select " + camposOrdenadosSinc()   
   			+ " from " + this.DB_TABLE_SINC;
   		ModeloProdutoMontador montador = new ModeloProdutoMontador(true); // sinc ligado
   		this.setMontador(montador);
   		List<ModeloProduto> saida = this.getListaSql(sql);
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
		this.insert((ModeloProduto) obj);
	}
	@Override
	public final void dropCreate() {
		this.dropTable();
		this.criaTabela();
	}
}
