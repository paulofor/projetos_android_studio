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
import coletapreco.modelo.vo.LojaVirtualVo;
import coletapreco.modelo.vo.FabricaVo;
import coletapreco.dao.*;
import coletapreco.dao.montador.*;
import coletapreco.dao.datasource.DataSourceAplicacao;

public abstract class LojaVirtualDBHelperBase extends DBHelperBase
	implements DaoSincronismo // coloquei aqui para evitar impacto de arquitetura
{

    private static final String DB_NAME = "w_alert";
    public static final String DB_TABLE = "loja_virtual";
    public static final String DB_TABLE_SINC = "loja_virtual_sinc";
    public static final int DB_VERSION = 3;

    protected static final String CLASSNAME = LojaVirtualDBHelperBase.class.getSimpleName();
    protected static final String[] COLS = new String[] { 
        "id_loja_virtual"
        ,"nome_loja_virtual"
        ,"url_inicial_brinquedo"
    };

	protected static final String[] COLS_SINC = new String[] { 
        "id_loja_virtual"
        ,"nome_loja_virtual"
        ,"url_inicial_brinquedo"	, "operacao_sinc"
    };

    protected SQLiteDatabase db;
    protected final DBOpenHelper dbOpenHelper;

	@Override
	protected MontadorDaoI criaMontadorPadrao() {
		return new LojaVirtualMontador();
	}
	
	protected String getSqlIndices() {
		return "";
	}
	protected String getSqlCreate(){
		return  "CREATE TABLE "
        + LojaVirtualDBHelperBase.DB_TABLE + " ("
        + "  id_loja_virtual INTEGER PRIMARY KEY "
        + " , nome_loja_virtual TEXT "
        + " , url_inicial_brinquedo TEXT "
		+ getSqlIndices()
        + ");";
	}

	

	public static final String DB_CREATE_SINCRONIZADA = "CREATE TABLE IF NOT EXISTS "
        + LojaVirtualDBHelperBase.DB_TABLE_SINC + " ("
        + "  id_loja_virtual INTEGER "
        + " , nome_loja_virtual TEXT "
        + " , url_inicial_brinquedo TEXT "
        + ", operacao_sinc TEXT);";


    public static final String DB_CREATE = "CREATE TABLE IF NOT EXISTS "
        + LojaVirtualDBHelperBase.DB_TABLE + " ("
        + "  id_loja_virtual INTEGER PRIMARY KEY "
        + " , nome_loja_virtual TEXT "
        + " , url_inicial_brinquedo TEXT "
        + ");";
    
    private static final String DB_DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private static final String DB_DROP = "DROP TABLE IF EXISTS " + DB_TABLE;
    private static final String DB_DROP_SINCRONIZADA = "DROP TABLE IF EXISTS " + DB_TABLE_SINC;
    
    private static class DBOpenHelper extends SQLiteOpenHelper {

       

        public DBOpenHelper(final Context context) {
            super(context, LojaVirtualDBHelperBase.DB_NAME, null, LojaVirtualDBHelperBase.DB_VERSION);
        }

        @Override
        public void onCreate(final SQLiteDatabase db) {
            try {
                db.execSQL(DB_CREATE);
            } catch (SQLException e) {
                Log.e(Constants.LOGTAG, LojaVirtualDBHelperBase.CLASSNAME, e);
            }
        }

        @Override
        public void onOpen(final SQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + LojaVirtualDBHelperBase.DB_TABLE);
            onCreate(db);
        }
    }

    //
    // end inner classes
    //
    
    // Versao Nova
 	public LojaVirtualDBHelperBase() {
    	this.dbOpenHelper = null;
    	setDataSource(DataSourceAplicacao.getInstancia());
    }
    
   
	
	protected ContentValues montaValores(final DCIObjetoDominio valor) {
		LojaVirtual item = (LojaVirtual) valor;
		ContentValues valores = new ContentValues();
       	putValor(valores,"id_loja_virtual",item.getIdLojaVirtual());
       	
       	putValor(valores,"nome_loja_virtual",item.getNomeLojaVirtual());
       	
       	putValor(valores,"url_inicial_brinquedo",item.getUrlInicialBrinquedo());
       	
        return valores;
	}


    // **** Chamadas Diretas Objeto Banco de Dados
    private void registraErroChamadaDireta(Exception e) {
    	Log.e(Constants.LOGTAG, LojaVirtualDBHelperBase.CLASSNAME, e);
    }
   
    public final void insert(final LojaVirtual item) {
	    try {
	        getDb().insert(LojaVirtualDBHelperBase.DB_TABLE, null, montaValores(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    public final void update(final LojaVirtual item) {
	    try {
	        getDb().update(LojaVirtualDBHelperBase.DB_TABLE, montaValores(item), "id_loja_virtual=" + item.getIdLojaVirtual(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     // Nao pode ser final o parametro para retornar o objeto com seu Id
    public final void insertSinc(LojaVirtual item) {
        try {
        	item.setIdLojaVirtual((int)getMaxId() + 1);
        	DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc: " + item.debug());
	        long id = getDb().insert(LojaVirtualDBHelperBase.DB_TABLE, null, montaValores(item));
    	    ((ObjetoSinc)item).setOperacaoSinc("I");
        	getDb().insert(LojaVirtualDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     public final void updateSinc(final LojaVirtual item) {
        try {
	        DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc: " + item.debug());
	        getDb().update(LojaVirtualDBHelperBase.DB_TABLE, montaValores(item), "id_loja_virtual=" + item.getIdLojaVirtual(), null);
	        ((ObjetoSinc)item).setOperacaoSinc("A");
	        LojaVirtual atual = this.getSincById(item.getId());
	        if (atual==null) {
	        	getDb().insert(LojaVirtualDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	        } else {
	        	if ("I".equals(((ObjetoSinc)atual).getOperacaoSinc()))
	        		((ObjetoSinc)item).setOperacaoSinc("I");
	        	getDb().update(LojaVirtualDBHelperBase.DB_TABLE_SINC, montaValoresSinc(item), "id_loja_virtual=" + item.getIdLojaVirtual(), null);
	        }
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    protected final void delete(final long id) {
        try {
			getDb().delete(LojaVirtualDBHelperBase.DB_TABLE, "id_loja_virtual=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    /*
    private void deleteSinc(final long id) {
        try {
			getDb().delete(LojaVirtualDBHelperBase.DB_TABLE_SINC, "id_loja_virtual=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
	*/
    public void limpaSinc(final LojaVirtual item) {
    	try {
			getDb().delete(LojaVirtualDBHelperBase.DB_TABLE_SINC, "id_loja_virtual=" + item.getId(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    
    public void deleteSinc(final LojaVirtual item) {
    	try {
	        DCLog.dStack(DCLog.DATABASE_ADM, this, "deleteSinc: " + item.debug());
	        delete(item.getId());
	        ((ObjetoSinc)item).setOperacaoSinc("D");
        	getDb().insert(LojaVirtualDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
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
    public LojaVirtual getById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (LojaVirtual) getItemQuery(true, LojaVirtualDBHelperBase.DB_TABLE, LojaVirtualDBHelperBase.COLS, "id_loja_virtual = " + id + "", null, null, null, null,null);
    }
    
    // Esta com orderBy que pode ser bom para exibicoes em tela
    // mas nao e bom para sincronizacao, pensar em ter um metodo para tela e outro para sinc.
    public List<LojaVirtual> getAll() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, null);
    }
    public List<LojaVirtual> getAllTela() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, orderByColuna());
    }
    
    private LojaVirtual getByRowId(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (LojaVirtual) getItemQuery(true, LojaVirtualDBHelperBase.DB_TABLE, LojaVirtualDBHelperBase.COLS, "ROWID = " + id + "", null, null, null, null,null);
    }
    private LojaVirtual getSincById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (LojaVirtual) getItemQuerySinc(true, LojaVirtualDBHelperBase.DB_TABLE_SINC, LojaVirtualDBHelperBase.COLS_SINC, "id_loja_virtual = " + id + "", null, null, null, null,null);
    }
    
    
    public long getMaxId() {
		String sql = "select max(id_loja_virtual) from " + DB_TABLE;
		return this.getNumeroSql(sql);
	}
	protected String orderByColuna() {
    	return null;
    }
	
  
  	
  
  
  	
	/*
	public LojaVirtual obtemPorProdutoPossui(long id) {
		string sql;
		sql = "select " + camposOrdenados() +
			" from " + tabelaSelect() +
			innerJoinProduto_Possui() + 
			" where id_produto = " + id;
		return (LojaVirtual) getObjeto(sql);
	}
	*/
	public static String innerJoinProduto_Possui() {
		return " inner join " + ProdutoDBHelperBase.DB_TABLE + " on " + ProdutoDBHelperBase.DB_TABLE + ".id_loja_virtual_le = " + DB_TABLE + ".id_loja_virtual ";  
	}
	public static String innerJoinSincProduto_Possui() {
		return " inner join " + ProdutoDBHelperBase.DB_TABLE_SINC + " on " + ProdutoDBHelperBase.DB_TABLE_SINC + ".id_loja_virtual_le = " + DB_TABLE_SINC + ".id_loja_virtual ";  
	}
	public static String outerJoinProduto_Possui() {
		return " left outer join " + ProdutoDBHelperBase.DB_TABLE + " on " + ProdutoDBHelperBase.DB_TABLE + ".id_loja_virtual_le = " + DB_TABLE + ".id_loja_virtual ";  
	}
	public static String outerJoinSincProduto_Possui() {
		return " left outer join " + ProdutoDBHelperBase.DB_TABLE_SINC + " on " + ProdutoDBHelperBase.DB_TABLE_SINC + ".id_loja_virtual_le = " + DB_TABLE_SINC + ".id_loja_virtual ";  
	}
 	
	/*
	public LojaVirtual obtemPorCategoriaLojaPossui(long id) {
		string sql;
		sql = "select " + camposOrdenados() +
			" from " + tabelaSelect() +
			innerJoinCategoriaLoja_Possui() + 
			" where id_categoria_loja = " + id;
		return (LojaVirtual) getObjeto(sql);
	}
	*/
	public static String innerJoinCategoriaLoja_Possui() {
		return " inner join " + CategoriaLojaDBHelperBase.DB_TABLE + " on " + CategoriaLojaDBHelperBase.DB_TABLE + ".id_loja_virtual_pa = " + DB_TABLE + ".id_loja_virtual ";  
	}
	public static String innerJoinSincCategoriaLoja_Possui() {
		return " inner join " + CategoriaLojaDBHelperBase.DB_TABLE_SINC + " on " + CategoriaLojaDBHelperBase.DB_TABLE_SINC + ".id_loja_virtual_pa = " + DB_TABLE_SINC + ".id_loja_virtual ";  
	}
	public static String outerJoinCategoriaLoja_Possui() {
		return " left outer join " + CategoriaLojaDBHelperBase.DB_TABLE + " on " + CategoriaLojaDBHelperBase.DB_TABLE + ".id_loja_virtual_pa = " + DB_TABLE + ".id_loja_virtual ";  
	}
	public static String outerJoinSincCategoriaLoja_Possui() {
		return " left outer join " + CategoriaLojaDBHelperBase.DB_TABLE_SINC + " on " + CategoriaLojaDBHelperBase.DB_TABLE_SINC + ".id_loja_virtual_pa = " + DB_TABLE_SINC + ".id_loja_virtual ";  
	}
 	
	/*
	public LojaVirtual obtemPorLojaNaturezaOferece(long id) {
		string sql;
		sql = "select " + camposOrdenados() +
			" from " + tabelaSelect() +
			innerJoinLojaNatureza_Oferece() + 
			" where id_loja_natureza = " + id;
		return (LojaVirtual) getObjeto(sql);
	}
	*/
	public static String innerJoinLojaNatureza_Oferece() {
		return " inner join " + LojaNaturezaDBHelperBase.DB_TABLE + " on " + LojaNaturezaDBHelperBase.DB_TABLE + ".id_loja_virtual_ra = " + DB_TABLE + ".id_loja_virtual ";  
	}
	public static String innerJoinSincLojaNatureza_Oferece() {
		return " inner join " + LojaNaturezaDBHelperBase.DB_TABLE_SINC + " on " + LojaNaturezaDBHelperBase.DB_TABLE_SINC + ".id_loja_virtual_ra = " + DB_TABLE_SINC + ".id_loja_virtual ";  
	}
	public static String outerJoinLojaNatureza_Oferece() {
		return " left outer join " + LojaNaturezaDBHelperBase.DB_TABLE + " on " + LojaNaturezaDBHelperBase.DB_TABLE + ".id_loja_virtual_ra = " + DB_TABLE + ".id_loja_virtual ";  
	}
	public static String outerJoinSincLojaNatureza_Oferece() {
		return " left outer join " + LojaNaturezaDBHelperBase.DB_TABLE_SINC + " on " + LojaNaturezaDBHelperBase.DB_TABLE_SINC + ".id_loja_virtual_ra = " + DB_TABLE_SINC + ".id_loja_virtual ";  
	}
 	
	
	
	// Relacionamento onde esse objeto ? chave estrangeira de outro. ????
	
	// ********************************************************************	
	
	
	public static String camposOrdenados() 
	{
		return " loja_virtual.id_loja_virtual " 
		+ " ,loja_virtual.nome_loja_virtual " 
		+ " ,loja_virtual.url_inicial_brinquedo " 
		;
	}
	public static String camposOrdenadosSinc() 
	{
		return " loja_virtual_sinc.id_loja_virtual " 
		+ " ,loja_virtual_sinc.nome_loja_virtual " 
		+ " ,loja_virtual_sinc.url_inicial_brinquedo " 
		
		+ " ,loja_virtual_sinc.operacao_sinc "
		;
	}
	public static String camposOrdenadosAlias(String nomeTabela) 
	{
		return  nomeTabela + ".id_loja_virtual " 
		+ " , " + nomeTabela + ".nome_loja_virtual " 
		+ " , " + nomeTabela + ".url_inicial_brinquedo " 
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
        montador.adicionaMontador(new LojaVirtualMontador(), null);
         return montador;
    }
	
    
   	// Poderia passar para classe abstrata.
    public final List<LojaVirtual> getAllSinc() throws DaoException {
    	this.setMontador(null);
    	List<LojaVirtual> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<LojaVirtual>();
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
    public final List<LojaVirtual> getAllSincSoAlteracao() throws DaoException {
    	List<LojaVirtual> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    		saida = null; // Melhorar aqui !!!!
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<LojaVirtual>();
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
	
	public final List<LojaVirtual> getAllSincImpl() throws DaoException {
   		String sql = "select " + camposOrdenadosSinc()   
   			+ " from " + this.DB_TABLE_SINC;
   		LojaVirtualMontador montador = new LojaVirtualMontador(true); // sinc ligado
   		this.setMontador(montador);
   		List<LojaVirtual> saida = this.getListaSql(sql);
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
		this.insert((LojaVirtual) obj);
	}
	@Override
	public final void dropCreate() {
		this.dropTable();
		this.criaTabela();
	}
}
