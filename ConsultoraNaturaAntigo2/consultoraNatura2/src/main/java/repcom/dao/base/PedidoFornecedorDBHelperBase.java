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
import repcom.modelo.vo.PedidoFornecedorVo;
import repcom.modelo.vo.FabricaVo;
import repcom.dao.*;
import repcom.dao.montador.*;
import repcom.dao.datasource.DataSourceAplicacao;

public abstract class PedidoFornecedorDBHelperBase extends DBHelperBase
	implements DaoSincronismo // coloquei aqui para evitar impacto de arquitetura
{

    private static final String DB_NAME = "w_alert";
    public static final String DB_TABLE = "pedido_fornecedor";
    public static final String DB_TABLE_SINC = "pedido_fornecedor_sinc";
    public static final int DB_VERSION = 3;

    protected static final String CLASSNAME = PedidoFornecedorDBHelperBase.class.getSimpleName();
    protected static final String[] COLS = new String[] { 
        "id_pedido_fornecedor"
    };

	protected static final String[] COLS_SINC = new String[] { 
        "id_pedido_fornecedor"	, "operacao_sinc"
    };

    protected SQLiteDatabase db;
    protected final DBOpenHelper dbOpenHelper;

	@Override
	protected MontadorDaoI criaMontadorPadrao() {
		return new PedidoFornecedorMontador();
	}
	
	protected String getSqlIndices() {
		return "";
	}
	protected String getSqlCreate(){
		return  "CREATE TABLE "
        + PedidoFornecedorDBHelperBase.DB_TABLE + " ("
        + "  id_pedido_fornecedor INTEGER PRIMARY KEY "
		+ getSqlIndices()
        + ");";
	}

	

	public static final String DB_CREATE_SINCRONIZADA = "CREATE TABLE IF NOT EXISTS "
        + PedidoFornecedorDBHelperBase.DB_TABLE_SINC + " ("
        + "  id_pedido_fornecedor INTEGER "
        + ", operacao_sinc TEXT);";


    public static final String DB_CREATE = "CREATE TABLE IF NOT EXISTS "
        + PedidoFornecedorDBHelperBase.DB_TABLE + " ("
        + "  id_pedido_fornecedor INTEGER PRIMARY KEY "
        + ");";
    
    private static final String DB_DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private static final String DB_DROP = "DROP TABLE IF EXISTS " + DB_TABLE;
    private static final String DB_DROP_SINCRONIZADA = "DROP TABLE IF EXISTS " + DB_TABLE_SINC;
    
    private static class DBOpenHelper extends SQLiteOpenHelper {

       

        public DBOpenHelper(final Context context) {
            super(context, PedidoFornecedorDBHelperBase.DB_NAME, null, PedidoFornecedorDBHelperBase.DB_VERSION);
        }

        @Override
        public void onCreate(final SQLiteDatabase db) {
            try {
                db.execSQL(DB_CREATE);
            } catch (SQLException e) {
                Log.e(Constants.LOGTAG, PedidoFornecedorDBHelperBase.CLASSNAME, e);
            }
        }

        @Override
        public void onOpen(final SQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + PedidoFornecedorDBHelperBase.DB_TABLE);
            onCreate(db);
        }
    }

    //
    // end inner classes
    //
    
    // Versao Nova
 	public PedidoFornecedorDBHelperBase() {
    	this.dbOpenHelper = null;
    	setDataSource(DataSourceAplicacao.getInstancia());
    }
    
   
	
	protected ContentValues montaValores(final DCIObjetoDominio valor) {
		PedidoFornecedor item = (PedidoFornecedor) valor;
		ContentValues valores = new ContentValues();
       	putValor(valores,"id_pedido_fornecedor",item.getIdPedidoFornecedor());
       	
        return valores;
	}


    // **** Chamadas Diretas Objeto Banco de Dados
    private void registraErroChamadaDireta(Exception e) {
    	Log.e(Constants.LOGTAG, PedidoFornecedorDBHelperBase.CLASSNAME, e);
    }
   
    public final void insert(final PedidoFornecedor item) {
	    try {
	        getDb().insert(PedidoFornecedorDBHelperBase.DB_TABLE, null, montaValores(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    public final void update(final PedidoFornecedor item) {
	    try {
	        getDb().update(PedidoFornecedorDBHelperBase.DB_TABLE, montaValores(item), "id_pedido_fornecedor=" + item.getIdPedidoFornecedor(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     // Nao pode ser final o parametro para retornar o objeto com seu Id
    public final void insertSinc(PedidoFornecedor item) {
        try {
        	item.setIdPedidoFornecedor((int)getMaxId() + 1);
        	DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc: " + item.debug());
	        long id = getDb().insert(PedidoFornecedorDBHelperBase.DB_TABLE, null, montaValores(item));
    	    ((ObjetoSinc)item).setOperacaoSinc("I");
        	getDb().insert(PedidoFornecedorDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     public final void updateSinc(final PedidoFornecedor item) {
        try {
	        DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc: " + item.debug());
	        getDb().update(PedidoFornecedorDBHelperBase.DB_TABLE, montaValores(item), "id_pedido_fornecedor=" + item.getIdPedidoFornecedor(), null);
	        ((ObjetoSinc)item).setOperacaoSinc("A");
	        PedidoFornecedor atual = this.getSincById(item.getId());
	        if (atual==null) {
	        	getDb().insert(PedidoFornecedorDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	        } else {
	        	if ("I".equals(((ObjetoSinc)atual).getOperacaoSinc()))
	        		((ObjetoSinc)item).setOperacaoSinc("I");
	        	getDb().update(PedidoFornecedorDBHelperBase.DB_TABLE_SINC, montaValoresSinc(item), "id_pedido_fornecedor=" + item.getIdPedidoFornecedor(), null);
	        }
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    protected final void delete(final long id) {
        try {
			getDb().delete(PedidoFornecedorDBHelperBase.DB_TABLE, "id_pedido_fornecedor=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    /*
    private void deleteSinc(final long id) {
        try {
			getDb().delete(PedidoFornecedorDBHelperBase.DB_TABLE_SINC, "id_pedido_fornecedor=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
	*/
    public void limpaSinc(final PedidoFornecedor item) {
    	try {
			getDb().delete(PedidoFornecedorDBHelperBase.DB_TABLE_SINC, "id_pedido_fornecedor=" + item.getId(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    
    public void deleteSinc(final PedidoFornecedor item) {
    	try {
	        DCLog.dStack(DCLog.DATABASE_ADM, this, "deleteSinc: " + item.debug());
	        delete(item.getId());
	        ((ObjetoSinc)item).setOperacaoSinc("D");
        	getDb().insert(PedidoFornecedorDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
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
    public PedidoFornecedor getById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (PedidoFornecedor) getItemQuery(true, PedidoFornecedorDBHelperBase.DB_TABLE, PedidoFornecedorDBHelperBase.COLS, "id_pedido_fornecedor = " + id + "", null, null, null, null,null);
    }
    
    // Esta com orderBy que pode ser bom para exibicoes em tela
    // mas nao e bom para sincronizacao, pensar em ter um metodo para tela e outro para sinc.
    public List<PedidoFornecedor> getAll() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, null);
    }
    public List<PedidoFornecedor> getAllTela() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, orderByColuna());
    }
    
    private PedidoFornecedor getByRowId(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (PedidoFornecedor) getItemQuery(true, PedidoFornecedorDBHelperBase.DB_TABLE, PedidoFornecedorDBHelperBase.COLS, "ROWID = " + id + "", null, null, null, null,null);
    }
    private PedidoFornecedor getSincById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (PedidoFornecedor) getItemQuerySinc(true, PedidoFornecedorDBHelperBase.DB_TABLE_SINC, PedidoFornecedorDBHelperBase.COLS_SINC, "id_pedido_fornecedor = " + id + "", null, null, null, null,null);
    }
    
    
    public long getMaxId() {
		String sql = "select max(id_pedido_fornecedor) from " + DB_TABLE;
		return this.getNumeroSql(sql);
	}
	protected String orderByColuna() {
    	return null;
    }
	
  
  	
  
  
  	
	/*
	public PedidoFornecedor obtemPorProdutoPedidoFornecedorAssociada(long id) {
		string sql;
		sql = "select " + camposOrdenados() +
			" from " + tabelaSelect() +
			innerJoinProdutoPedidoFornecedor_Associada() + 
			" where id_produto_pedido_fornecedor = " + id;
		return (PedidoFornecedor) getObjeto(sql);
	}
	*/
	public static String innerJoinProdutoPedidoFornecedor_Associada() {
		return " inner join " + ProdutoPedidoFornecedorDBHelperBase.DB_TABLE + " on " + ProdutoPedidoFornecedorDBHelperBase.DB_TABLE + ".id_pedido_fornecedor_a = " + DB_TABLE + ".id_pedido_fornecedor ";  
	}
	public static String innerJoinSincProdutoPedidoFornecedor_Associada() {
		return " inner join " + ProdutoPedidoFornecedorDBHelperBase.DB_TABLE_SINC + " on " + ProdutoPedidoFornecedorDBHelperBase.DB_TABLE_SINC + ".id_pedido_fornecedor_a = " + DB_TABLE_SINC + ".id_pedido_fornecedor ";  
	}
	public static String outerJoinProdutoPedidoFornecedor_Associada() {
		return " left outer join " + ProdutoPedidoFornecedorDBHelperBase.DB_TABLE + " on " + ProdutoPedidoFornecedorDBHelperBase.DB_TABLE + ".id_pedido_fornecedor_a = " + DB_TABLE + ".id_pedido_fornecedor ";  
	}
	public static String outerJoinSincProdutoPedidoFornecedor_Associada() {
		return " left outer join " + ProdutoPedidoFornecedorDBHelperBase.DB_TABLE_SINC + " on " + ProdutoPedidoFornecedorDBHelperBase.DB_TABLE_SINC + ".id_pedido_fornecedor_a = " + DB_TABLE_SINC + ".id_pedido_fornecedor ";  
	}
 	
	
	
	// Relacionamento onde esse objeto ? chave estrangeira de outro. ????
	
	// ********************************************************************	
	
	
	public static String camposOrdenados() 
	{
		return " pedido_fornecedor.id_pedido_fornecedor " 
		;
	}
	public static String camposOrdenadosSinc() 
	{
		return " pedido_fornecedor_sinc.id_pedido_fornecedor " 
		
		+ " ,pedido_fornecedor_sinc.operacao_sinc "
		;
	}
	public static String camposOrdenadosAlias(String nomeTabela) 
	{
		return  nomeTabela + ".id_pedido_fornecedor " 
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
        montador.adicionaMontador(new PedidoFornecedorMontador(), null);
         return montador;
    }
	
    
   	// Poderia passar para classe abstrata.
    public final List<PedidoFornecedor> getAllSinc() throws DaoException {
    	this.setMontador(null);
    	List<PedidoFornecedor> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<PedidoFornecedor>();
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
    public final List<PedidoFornecedor> getAllSincSoAlteracao() throws DaoException {
    	List<PedidoFornecedor> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    		saida = null; // Melhorar aqui !!!!
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<PedidoFornecedor>();
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
	
	public final List<PedidoFornecedor> getAllSincImpl() throws DaoException {
   		String sql = "select " + camposOrdenadosSinc()   
   			+ " from " + this.DB_TABLE_SINC;
   		PedidoFornecedorMontador montador = new PedidoFornecedorMontador(true); // sinc ligado
   		this.setMontador(montador);
   		List<PedidoFornecedor> saida = this.getListaSql(sql);
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
		this.insert((PedidoFornecedor) obj);
	}
	@Override
	public final void dropCreate() {
		this.dropTable();
		this.criaTabela();
	}
}
