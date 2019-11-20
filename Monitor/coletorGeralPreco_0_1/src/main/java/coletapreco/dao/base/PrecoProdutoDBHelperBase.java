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
import coletapreco.modelo.vo.PrecoProdutoVo;
import coletapreco.modelo.vo.FabricaVo;
import coletapreco.dao.*;
import coletapreco.dao.montador.*;
import coletapreco.dao.datasource.DataSourceAplicacao;

public abstract class PrecoProdutoDBHelperBase extends DBHelperBase
	implements DaoSincronismo // coloquei aqui para evitar impacto de arquitetura
{

    private static final String DB_NAME = "w_alert";
    public static final String DB_TABLE = "preco_produto";
    public static final String DB_TABLE_SINC = "preco_produto_sinc";
    public static final int DB_VERSION = 3;

    protected static final String CLASSNAME = PrecoProdutoDBHelperBase.class.getSimpleName();
    protected static final String[] COLS = new String[] { 
        "id_preco_produto"
        ,"preco_boleto"
        ,"data_visita_inicial"
        ,"quantidade_parcela"
        ,"preco_parcela"
        ,"preco_venda"
        ,"preco_regular"
        ,"data_ultima_visita"
        ,"percentual_ajuste"
		, "id_produto_pa"
	
    };

	protected static final String[] COLS_SINC = new String[] { 
        "id_preco_produto"
        ,"preco_boleto"
        ,"data_visita_inicial"
        ,"quantidade_parcela"
        ,"preco_parcela"
        ,"preco_venda"
        ,"preco_regular"
        ,"data_ultima_visita"
        ,"percentual_ajuste"
		, "id_produto_pa"
		, "operacao_sinc"
    };

    protected SQLiteDatabase db;
    protected final DBOpenHelper dbOpenHelper;

	@Override
	protected MontadorDaoI criaMontadorPadrao() {
		return new PrecoProdutoMontador();
	}
	
	protected String getSqlIndices() {
		return "";
	}
	protected String getSqlCreate(){
		return  "CREATE TABLE "
        + PrecoProdutoDBHelperBase.DB_TABLE + " ("
        + "  id_preco_produto INTEGER PRIMARY KEY "
        + " , preco_boleto REAL "
        + " , data_visita_inicial INTEGER "
        + " , quantidade_parcela INTEGER "
        + " , preco_parcela REAL "
        + " , preco_venda REAL "
        + " , preco_regular REAL "
        + " , data_ultima_visita INTEGER "
        + " , percentual_ajuste REAL "
		+ " , id_produto_pa INTEGER "
		
		+ getSqlIndices()
        + ");";
	}

	

	public static final String DB_CREATE_SINCRONIZADA = "CREATE TABLE IF NOT EXISTS "
        + PrecoProdutoDBHelperBase.DB_TABLE_SINC + " ("
        + "  id_preco_produto INTEGER "
        + " , preco_boleto REAL "
        + " , data_visita_inicial INTEGER "
        + " , quantidade_parcela INTEGER "
        + " , preco_parcela REAL "
        + " , preco_venda REAL "
        + " , preco_regular REAL "
        + " , data_ultima_visita INTEGER "
        + " , percentual_ajuste REAL "
		+ " , id_produto_pa INTEGER "
		
        + ", operacao_sinc TEXT);";


    public static final String DB_CREATE = "CREATE TABLE IF NOT EXISTS "
        + PrecoProdutoDBHelperBase.DB_TABLE + " ("
        + "  id_preco_produto INTEGER PRIMARY KEY "
        + " , preco_boleto REAL "
        + " , data_visita_inicial INTEGER "
        + " , quantidade_parcela INTEGER "
        + " , preco_parcela REAL "
        + " , preco_venda REAL "
        + " , preco_regular REAL "
        + " , data_ultima_visita INTEGER "
        + " , percentual_ajuste REAL "
		+ " , id_produto_pa INTEGER "
		
        + ");";
    
    private static final String DB_DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private static final String DB_DROP = "DROP TABLE IF EXISTS " + DB_TABLE;
    private static final String DB_DROP_SINCRONIZADA = "DROP TABLE IF EXISTS " + DB_TABLE_SINC;
    
    private static class DBOpenHelper extends SQLiteOpenHelper {

       

        public DBOpenHelper(final Context context) {
            super(context, PrecoProdutoDBHelperBase.DB_NAME, null, PrecoProdutoDBHelperBase.DB_VERSION);
        }

        @Override
        public void onCreate(final SQLiteDatabase db) {
            try {
                db.execSQL(DB_CREATE);
            } catch (SQLException e) {
                Log.e(Constants.LOGTAG, PrecoProdutoDBHelperBase.CLASSNAME, e);
            }
        }

        @Override
        public void onOpen(final SQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + PrecoProdutoDBHelperBase.DB_TABLE);
            onCreate(db);
        }
    }

    //
    // end inner classes
    //
    
    // Versao Nova
 	public PrecoProdutoDBHelperBase() {
    	this.dbOpenHelper = null;
    	setDataSource(DataSourceAplicacao.getInstancia());
    }
    
   
	
	protected ContentValues montaValores(final DCIObjetoDominio valor) {
		PrecoProduto item = (PrecoProduto) valor;
		ContentValues valores = new ContentValues();
       	putValor(valores,"id_preco_produto",item.getIdPrecoProduto());
       	
       	putValor(valores,"preco_boleto",item.getPrecoBoleto());
       	
       	putValorData(valores,"data_visita_inicial",item.getDataVisitaInicial());
        		
       	putValor(valores,"quantidade_parcela",item.getQuantidadeParcela());
       	
       	putValor(valores,"preco_parcela",item.getPrecoParcela());
       	
       	putValor(valores,"preco_venda",item.getPrecoVenda());
       	
       	putValor(valores,"preco_regular",item.getPrecoRegular());
       	
       	putValorData(valores,"data_ultima_visita",item.getDataUltimaVisita());
        		
       	putValor(valores,"percentual_ajuste",item.getPercentualAjuste());
       	
       	putValor(valores,"id_produto_pa",item.getIdProdutoPa());
       	
        return valores;
	}


    // **** Chamadas Diretas Objeto Banco de Dados
    private void registraErroChamadaDireta(Exception e) {
    	Log.e(Constants.LOGTAG, PrecoProdutoDBHelperBase.CLASSNAME, e);
    }
   
    public final void insert(final PrecoProduto item) {
	    try {
	        getDb().insert(PrecoProdutoDBHelperBase.DB_TABLE, null, montaValores(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    public final void update(final PrecoProduto item) {
	    try {
	        getDb().update(PrecoProdutoDBHelperBase.DB_TABLE, montaValores(item), "id_preco_produto=" + item.getIdPrecoProduto(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     // Nao pode ser final o parametro para retornar o objeto com seu Id
    public final void insertSinc(PrecoProduto item) {
        try {
        	item.setIdPrecoProduto((int)getMaxId() + 1);
        	DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc: " + item.debug());
	        long id = getDb().insert(PrecoProdutoDBHelperBase.DB_TABLE, null, montaValores(item));
    	    ((ObjetoSinc)item).setOperacaoSinc("I");
        	getDb().insert(PrecoProdutoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     public final void updateSinc(final PrecoProduto item) {
        try {
	        DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc: " + item.debug());
	        getDb().update(PrecoProdutoDBHelperBase.DB_TABLE, montaValores(item), "id_preco_produto=" + item.getIdPrecoProduto(), null);
	        ((ObjetoSinc)item).setOperacaoSinc("A");
	        PrecoProduto atual = this.getSincById(item.getId());
	        if (atual==null) {
	        	getDb().insert(PrecoProdutoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	        } else {
	        	if ("I".equals(((ObjetoSinc)atual).getOperacaoSinc()))
	        		((ObjetoSinc)item).setOperacaoSinc("I");
	        	getDb().update(PrecoProdutoDBHelperBase.DB_TABLE_SINC, montaValoresSinc(item), "id_preco_produto=" + item.getIdPrecoProduto(), null);
	        }
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    protected final void delete(final long id) {
        try {
			getDb().delete(PrecoProdutoDBHelperBase.DB_TABLE, "id_preco_produto=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    /*
    private void deleteSinc(final long id) {
        try {
			getDb().delete(PrecoProdutoDBHelperBase.DB_TABLE_SINC, "id_preco_produto=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
	*/
    public void limpaSinc(final PrecoProduto item) {
    	try {
			getDb().delete(PrecoProdutoDBHelperBase.DB_TABLE_SINC, "id_preco_produto=" + item.getId(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    
    public void deleteSinc(final PrecoProduto item) {
    	try {
	        DCLog.dStack(DCLog.DATABASE_ADM, this, "deleteSinc: " + item.debug());
	        delete(item.getId());
	        ((ObjetoSinc)item).setOperacaoSinc("D");
        	getDb().insert(PrecoProdutoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
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
    public PrecoProduto getById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (PrecoProduto) getItemQuery(true, PrecoProdutoDBHelperBase.DB_TABLE, PrecoProdutoDBHelperBase.COLS, "id_preco_produto = " + id + "", null, null, null, null,null);
    }
    
    // Esta com orderBy que pode ser bom para exibicoes em tela
    // mas nao e bom para sincronizacao, pensar em ter um metodo para tela e outro para sinc.
    public List<PrecoProduto> getAll() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, null);
    }
    public List<PrecoProduto> getAllTela() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, orderByColuna());
    }
    
    private PrecoProduto getByRowId(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (PrecoProduto) getItemQuery(true, PrecoProdutoDBHelperBase.DB_TABLE, PrecoProdutoDBHelperBase.COLS, "ROWID = " + id + "", null, null, null, null,null);
    }
    private PrecoProduto getSincById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (PrecoProduto) getItemQuerySinc(true, PrecoProdutoDBHelperBase.DB_TABLE_SINC, PrecoProdutoDBHelperBase.COLS_SINC, "id_preco_produto = " + id + "", null, null, null, null,null);
    }
    
    
    public long getMaxId() {
		String sql = "select max(id_preco_produto) from " + DB_TABLE;
		return this.getNumeroSql(sql);
	}
	protected String orderByColuna() {
    	return null;
    }
	
	
	public List<PrecoProduto> getPorPertenceAProduto(Context contexto, long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_produto_pa = " + id, null, null, null, null);
	}
	public List<PrecoProduto> getPorPertenceAProduto(long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_produto_pa = " + id, null, null, null, null);
	}
	
	
  
  	
  
  
  	
	
	
	// Relacionamento onde esse objeto ? chave estrangeira de outro. ????
	
	private boolean _obtemProduto_PertenceA = false;
	public void setObtemProduto_PertenceA() {
		_obtemProduto_PertenceA = true;
	}
	protected String outterJoinProduto_PertenceA() {
		return " left outer join " + ProdutoDBHelperBase.DB_TABLE + " on " + ProdutoDBHelperBase.DB_TABLE + ".id_produto = " + DB_TABLE + ".id_produto_pa ";  
	}
	public boolean atualizaPertenceAProduto(long idPrecoProduto, long idProdutoPa) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_produto_pa  = " + idProdutoPa +
        " where id_preco_produto = " +  idPrecoProduto;
       	//this.executaSql(sql);
       	return true;
	}
	public PrecoProduto obtemPorIdsPertenceAProduto(long idPrecoProduto, long idProdutoPa) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_produto_pa = " + idProdutoPa + " and " +
			  " id_preco_produto = " + idPrecoProduto;
		return (PrecoProduto) this.geObjetoSql(sql);
	}
	
	
	public static String innerJoinProduto_PertenceA() {
		return " inner join " + ProdutoDBHelperBase.DB_TABLE + " on " + ProdutoDBHelperBase.DB_TABLE + ".id_produto = " + DB_TABLE + ".id_produto_pa ";  
	}
	public static String outerJoinProduto_PertenceA() {
		return " left outer join " + ProdutoDBHelperBase.DB_TABLE + " on " + ProdutoDBHelperBase.DB_TABLE + ".id_produto = " + DB_TABLE + ".id_produto_pa ";  
	}
	public static String innerJoinSincProduto_PertenceA() {
		return " inner join " + ProdutoDBHelperBase.DB_TABLE_SINC + " on " + ProdutoDBHelperBase.DB_TABLE_SINC + ".id_produto = " + DB_TABLE_SINC + ".id_produto_pa ";  
	}
	public static String outerJoinSincProduto_PertenceA() {
		return " left outer join " + ProdutoDBHelperBase.DB_TABLE_SINC + " on " + ProdutoDBHelperBase.DB_TABLE_SINC + ".id_produto = " + DB_TABLE_SINC + ".id_produto_pa ";  
	}
	
 	
	// ********************************************************************	
	
	
	public static String camposOrdenados() 
	{
		return " preco_produto.id_preco_produto " 
		+ " ,preco_produto.preco_boleto " 
		+ " ,preco_produto.data_visita_inicial " 
		+ " ,preco_produto.quantidade_parcela " 
		+ " ,preco_produto.preco_parcela " 
		+ " ,preco_produto.preco_venda " 
		+ " ,preco_produto.preco_regular " 
		+ " ,preco_produto.data_ultima_visita " 
		+ " ,preco_produto.percentual_ajuste " 
		+ " ,preco_produto.id_produto_pa " 
		;
	}
	public static String camposOrdenadosSinc() 
	{
		return " preco_produto_sinc.id_preco_produto " 
		+ " ,preco_produto_sinc.preco_boleto " 
		+ " ,preco_produto_sinc.data_visita_inicial " 
		+ " ,preco_produto_sinc.quantidade_parcela " 
		+ " ,preco_produto_sinc.preco_parcela " 
		+ " ,preco_produto_sinc.preco_venda " 
		+ " ,preco_produto_sinc.preco_regular " 
		+ " ,preco_produto_sinc.data_ultima_visita " 
		+ " ,preco_produto_sinc.percentual_ajuste " 
		+ " ,preco_produto_sinc.id_produto_pa " 
		
		+ " ,preco_produto_sinc.operacao_sinc "
		;
	}
	public static String camposOrdenadosAlias(String nomeTabela) 
	{
		return  nomeTabela + ".id_preco_produto " 
		+ " , " + nomeTabela + ".preco_boleto " 
		+ " , " +  "DATE_FORMAT(" + nomeTabela + ".data_visita_inicial,'%d-%m-%Y') " 
		+ " , " + nomeTabela + ".quantidade_parcela " 
		+ " , " + nomeTabela + ".preco_parcela " 
		+ " , " + nomeTabela + ".preco_venda " 
		+ " , " + nomeTabela + ".preco_regular " 
		+ " , " +  "DATE_FORMAT(" + nomeTabela + ".data_ultima_visita,'%d-%m-%Y') " 
		+ " , " + nomeTabela + ".percentual_ajuste " 
		+ " , " + nomeTabela + ".id_produto_pa " 
		;
	}
	
	protected String camposOrdenadosJoin()
    {
        String saida = camposOrdenados();
		saida += (this._obtemProduto_PertenceA?" , " +ProdutoDBHelperBase.camposOrdenados():"");
        return saida;
    }
    
        
    public void limpaObtem()
    {
		_obtemProduto_PertenceA = false;
    }
    
	protected String outterJoinAgrupado()
    {
        String saida = " ";
		saida += (this._obtemProduto_PertenceA? outterJoinProduto_PertenceA() + " ":"");
        return saida;
    }
    protected MontadorDaoI getMontadorAgrupado()
    {
        MontadorDaoComposite montador = new MontadorDaoComposite();
        montador.adicionaMontador(new PrecoProdutoMontador(), null);
		if (this._obtemProduto_PertenceA)
            montador.adicionaMontador(new ProdutoMontador(), "Produto_PertenceA");
         return montador;
    }
	
    
   	// Poderia passar para classe abstrata.
    public final List<PrecoProduto> getAllSinc() throws DaoException {
    	this.setMontador(null);
    	List<PrecoProduto> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<PrecoProduto>();
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
    public final List<PrecoProduto> getAllSincSoAlteracao() throws DaoException {
    	List<PrecoProduto> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    		saida = null; // Melhorar aqui !!!!
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<PrecoProduto>();
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
	
	public final List<PrecoProduto> getAllSincImpl() throws DaoException {
   		String sql = "select " + camposOrdenadosSinc()   
   			+ " from " + this.DB_TABLE_SINC;
   		PrecoProdutoMontador montador = new PrecoProdutoMontador(true); // sinc ligado
   		this.setMontador(montador);
   		List<PrecoProduto> saida = this.getListaSql(sql);
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
		this.insert((PrecoProduto) obj);
	}
	@Override
	public final void dropCreate() {
		this.dropTable();
		this.criaTabela();
	}
}
