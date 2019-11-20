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
import repcom.modelo.vo.LinhaProdutoVo;
import repcom.modelo.vo.FabricaVo;
import repcom.dao.*;
import repcom.dao.montador.*;
import repcom.dao.datasource.DataSourceAplicacao;

public abstract class LinhaProdutoDBHelperBase extends DBHelperBase
	implements DaoSincronismo // coloquei aqui para evitar impacto de arquitetura
{

    private static final String DB_NAME = "w_alert";
    public static final String DB_TABLE = "linha_produto";
    public static final String DB_TABLE_SINC = "linha_produto_sinc";
    public static final int DB_VERSION = 3;

    protected static final String CLASSNAME = LinhaProdutoDBHelperBase.class.getSimpleName();
    protected static final String[] COLS = new String[] { 
        "id_linha_produto"
        ,"nome"
        ,"url"
        ,"codigo_line_id"
        ,"data_inclusao"
    };

	protected static final String[] COLS_SINC = new String[] { 
        "id_linha_produto"
        ,"nome"
        ,"url"
        ,"codigo_line_id"
        ,"data_inclusao"	, "operacao_sinc"
    };

    protected SQLiteDatabase db;
    protected final DBOpenHelper dbOpenHelper;

	@Override
	protected MontadorDaoI criaMontadorPadrao() {
		return new LinhaProdutoMontador();
	}
	
	protected String getSqlIndices() {
		return "";
	}
	protected String getSqlCreate(){
		return  "CREATE TABLE "
        + LinhaProdutoDBHelperBase.DB_TABLE + " ("
        + "  id_linha_produto INTEGER PRIMARY KEY "
        + " , nome TEXT "
        + " , url TEXT "
        + " , codigo_line_id INTEGER "
        + " , data_inclusao INTEGER "
		+ getSqlIndices()
        + ");";
	}

	

	public static final String DB_CREATE_SINCRONIZADA = "CREATE TABLE IF NOT EXISTS "
        + LinhaProdutoDBHelperBase.DB_TABLE_SINC + " ("
        + "  id_linha_produto INTEGER "
        + " , nome TEXT "
        + " , url TEXT "
        + " , codigo_line_id INTEGER "
        + " , data_inclusao INTEGER "
        + ", operacao_sinc TEXT);";


    public static final String DB_CREATE = "CREATE TABLE IF NOT EXISTS "
        + LinhaProdutoDBHelperBase.DB_TABLE + " ("
        + "  id_linha_produto INTEGER PRIMARY KEY "
        + " , nome TEXT "
        + " , url TEXT "
        + " , codigo_line_id INTEGER "
        + " , data_inclusao INTEGER "
        + ");";
    
    private static final String DB_DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private static final String DB_DROP = "DROP TABLE IF EXISTS " + DB_TABLE;
    private static final String DB_DROP_SINCRONIZADA = "DROP TABLE IF EXISTS " + DB_TABLE_SINC;
    
    private static class DBOpenHelper extends SQLiteOpenHelper {

       

        public DBOpenHelper(final Context context) {
            super(context, LinhaProdutoDBHelperBase.DB_NAME, null, LinhaProdutoDBHelperBase.DB_VERSION);
        }

        @Override
        public void onCreate(final SQLiteDatabase db) {
            try {
                db.execSQL(DB_CREATE);
            } catch (SQLException e) {
                Log.e(Constants.LOGTAG, LinhaProdutoDBHelperBase.CLASSNAME, e);
            }
        }

        @Override
        public void onOpen(final SQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + LinhaProdutoDBHelperBase.DB_TABLE);
            onCreate(db);
        }
    }

    //
    // end inner classes
    //
    
    // Versao Nova
 	public LinhaProdutoDBHelperBase() {
    	this.dbOpenHelper = null;
    	setDataSource(DataSourceAplicacao.getInstancia());
    }
    
   
	
	protected ContentValues montaValores(final DCIObjetoDominio valor) {
		LinhaProduto item = (LinhaProduto) valor;
		ContentValues valores = new ContentValues();
       	putValor(valores,"id_linha_produto",item.getIdLinhaProduto());
       	
       	putValor(valores,"nome",item.getNome());
       	
       	putValor(valores,"url",item.getUrl());
       	
       	putValor(valores,"codigo_line_id",item.getCodigoLineId());
       	
       	putValorData(valores,"data_inclusao",item.getDataInclusao());
        		
        return valores;
	}


    // **** Chamadas Diretas Objeto Banco de Dados
    private void registraErroChamadaDireta(Exception e) {
    	Log.e(Constants.LOGTAG, LinhaProdutoDBHelperBase.CLASSNAME, e);
    }
   
    public final void insert(final LinhaProduto item) {
	    try {
	        getDb().insert(LinhaProdutoDBHelperBase.DB_TABLE, null, montaValores(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    public final void update(final LinhaProduto item) {
	    try {
	        getDb().update(LinhaProdutoDBHelperBase.DB_TABLE, montaValores(item), "id_linha_produto=" + item.getIdLinhaProduto(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     // Nao pode ser final o parametro para retornar o objeto com seu Id
    public final void insertSinc(LinhaProduto item) {
        try {
        	item.setIdLinhaProduto((int)getMaxId() + 1);
        	DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc: " + item.debug());
	        long id = getDb().insert(LinhaProdutoDBHelperBase.DB_TABLE, null, montaValores(item));
    	    ((ObjetoSinc)item).setOperacaoSinc("I");
        	getDb().insert(LinhaProdutoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     public final void updateSinc(final LinhaProduto item) {
        try {
	        DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc: " + item.debug());
	        getDb().update(LinhaProdutoDBHelperBase.DB_TABLE, montaValores(item), "id_linha_produto=" + item.getIdLinhaProduto(), null);
	        ((ObjetoSinc)item).setOperacaoSinc("A");
	        LinhaProduto atual = this.getSincById(item.getId());
	        if (atual==null) {
	        	getDb().insert(LinhaProdutoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	        } else {
	        	if ("I".equals(((ObjetoSinc)atual).getOperacaoSinc()))
	        		((ObjetoSinc)item).setOperacaoSinc("I");
	        	getDb().update(LinhaProdutoDBHelperBase.DB_TABLE_SINC, montaValoresSinc(item), "id_linha_produto=" + item.getIdLinhaProduto(), null);
	        }
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    protected final void delete(final long id) {
        try {
			getDb().delete(LinhaProdutoDBHelperBase.DB_TABLE, "id_linha_produto=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    /*
    private void deleteSinc(final long id) {
        try {
			getDb().delete(LinhaProdutoDBHelperBase.DB_TABLE_SINC, "id_linha_produto=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
	*/
    public void limpaSinc(final LinhaProduto item) {
    	try {
			getDb().delete(LinhaProdutoDBHelperBase.DB_TABLE_SINC, "id_linha_produto=" + item.getId(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    
    public void deleteSinc(final LinhaProduto item) {
    	try {
	        DCLog.dStack(DCLog.DATABASE_ADM, this, "deleteSinc: " + item.debug());
	        delete(item.getId());
	        ((ObjetoSinc)item).setOperacaoSinc("D");
        	getDb().insert(LinhaProdutoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
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
    public LinhaProduto getById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (LinhaProduto) getItemQuery(true, LinhaProdutoDBHelperBase.DB_TABLE, LinhaProdutoDBHelperBase.COLS, "id_linha_produto = " + id + "", null, null, null, null,null);
    }
    
    // Esta com orderBy que pode ser bom para exibicoes em tela
    // mas nao e bom para sincronizacao, pensar em ter um metodo para tela e outro para sinc.
    public List<LinhaProduto> getAll() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, null);
    }
    public List<LinhaProduto> getAllTela() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, orderByColuna());
    }
    
    private LinhaProduto getByRowId(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (LinhaProduto) getItemQuery(true, LinhaProdutoDBHelperBase.DB_TABLE, LinhaProdutoDBHelperBase.COLS, "ROWID = " + id + "", null, null, null, null,null);
    }
    private LinhaProduto getSincById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (LinhaProduto) getItemQuerySinc(true, LinhaProdutoDBHelperBase.DB_TABLE_SINC, LinhaProdutoDBHelperBase.COLS_SINC, "id_linha_produto = " + id + "", null, null, null, null,null);
    }
    
    
    public long getMaxId() {
		String sql = "select max(id_linha_produto) from " + DB_TABLE;
		return this.getNumeroSql(sql);
	}
	protected String orderByColuna() {
    	return null;
    }
	
  
  	
  
  
  	
	/*
	public LinhaProduto obtemPorProdutoPossui(long id) {
		string sql;
		sql = "select " + camposOrdenados() +
			" from " + tabelaSelect() +
			innerJoinProduto_Possui() + 
			" where id_produto = " + id;
		return (LinhaProduto) getObjeto(sql);
	}
	*/
	public static String innerJoinProduto_Possui() {
		return " inner join " + ProdutoDBHelperBase.DB_TABLE + " on " + ProdutoDBHelperBase.DB_TABLE + ".id_linha_produto_ee = " + DB_TABLE + ".id_linha_produto ";  
	}
	public static String innerJoinSincProduto_Possui() {
		return " inner join " + ProdutoDBHelperBase.DB_TABLE_SINC + " on " + ProdutoDBHelperBase.DB_TABLE_SINC + ".id_linha_produto_ee = " + DB_TABLE_SINC + ".id_linha_produto ";  
	}
	public static String outerJoinProduto_Possui() {
		return " left outer join " + ProdutoDBHelperBase.DB_TABLE + " on " + ProdutoDBHelperBase.DB_TABLE + ".id_linha_produto_ee = " + DB_TABLE + ".id_linha_produto ";  
	}
	public static String outerJoinSincProduto_Possui() {
		return " left outer join " + ProdutoDBHelperBase.DB_TABLE_SINC + " on " + ProdutoDBHelperBase.DB_TABLE_SINC + ".id_linha_produto_ee = " + DB_TABLE_SINC + ".id_linha_produto ";  
	}
 	
	
	
	// Relacionamento onde esse objeto ? chave estrangeira de outro. ????
	
	// ********************************************************************	
	
	
	public static String camposOrdenados() 
	{
		return " linha_produto.id_linha_produto " 
		+ " ,linha_produto.nome " 
		+ " ,linha_produto.url " 
		+ " ,linha_produto.codigo_line_id " 
		+ " ,linha_produto.data_inclusao " 
		;
	}
	public static String camposOrdenadosSinc() 
	{
		return " linha_produto_sinc.id_linha_produto " 
		+ " ,linha_produto_sinc.nome " 
		+ " ,linha_produto_sinc.url " 
		+ " ,linha_produto_sinc.codigo_line_id " 
		+ " ,linha_produto_sinc.data_inclusao " 
		
		+ " ,linha_produto_sinc.operacao_sinc "
		;
	}
	public static String camposOrdenadosAlias(String nomeTabela) 
	{
		return  nomeTabela + ".id_linha_produto " 
		+ " , " + nomeTabela + ".nome " 
		+ " , " + nomeTabela + ".url " 
		+ " , " + nomeTabela + ".codigo_line_id " 
		+ " , " +  "DATE_FORMAT(" + nomeTabela + ".data_inclusao,'%d-%m-%Y') " 
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
        montador.adicionaMontador(new LinhaProdutoMontador(), null);
         return montador;
    }
	
    
   	// Poderia passar para classe abstrata.
    public final List<LinhaProduto> getAllSinc() throws DaoException {
    	this.setMontador(null);
    	List<LinhaProduto> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<LinhaProduto>();
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
    public final List<LinhaProduto> getAllSincSoAlteracao() throws DaoException {
    	List<LinhaProduto> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    		saida = null; // Melhorar aqui !!!!
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<LinhaProduto>();
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
	
	public final List<LinhaProduto> getAllSincImpl() throws DaoException {
   		String sql = "select " + camposOrdenadosSinc()   
   			+ " from " + this.DB_TABLE_SINC;
   		LinhaProdutoMontador montador = new LinhaProdutoMontador(true); // sinc ligado
   		this.setMontador(montador);
   		List<LinhaProduto> saida = this.getListaSql(sql);
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
		this.insert((LinhaProduto) obj);
	}
	@Override
	public final void dropCreate() {
		this.dropTable();
		this.criaTabela();
	}
}
