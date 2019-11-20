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
import repcom.modelo.vo.ProdutoVendaVo;
import repcom.modelo.vo.FabricaVo;
import repcom.dao.*;
import repcom.dao.montador.*;
import repcom.dao.datasource.DataSourceAplicacao;

public abstract class ProdutoVendaDBHelperBase extends DBHelperBase
	implements DaoSincronismo // coloquei aqui para evitar impacto de arquitetura
{

    private static final String DB_NAME = "w_alert";
    public static final String DB_TABLE = "produto_venda";
    public static final String DB_TABLE_SINC = "produto_venda_sinc";
    public static final int DB_VERSION = 3;

    protected static final String CLASSNAME = ProdutoVendaDBHelperBase.class.getSimpleName();
    protected static final String[] COLS = new String[] { 
        "id_produto_venda"
        ,"quantidade"
        ,"valor_total"
        ,"valor_item"
		, "id_produto_a"
	
		, "id_venda_a"
	
    };

	protected static final String[] COLS_SINC = new String[] { 
        "id_produto_venda"
        ,"quantidade"
        ,"valor_total"
        ,"valor_item"
		, "id_produto_a"
	
		, "id_venda_a"
		, "operacao_sinc"
    };

    protected SQLiteDatabase db;
    protected final DBOpenHelper dbOpenHelper;

	@Override
	protected MontadorDaoI criaMontadorPadrao() {
		return new ProdutoVendaMontador();
	}
	
	protected String getSqlIndices() {
		return "";
	}
	protected String getSqlCreate(){
		return  "CREATE TABLE "
        + ProdutoVendaDBHelperBase.DB_TABLE + " ("
        + "  id_produto_venda INTEGER PRIMARY KEY "
        + " , quantidade INTEGER "
        + " , valor_total REAL "
        + " , valor_item REAL "
		+ " , id_produto_a INTEGER "
		
		+ " , id_venda_a INTEGER "
		
		+ getSqlIndices()
        + ");";
	}

	

	public static final String DB_CREATE_SINCRONIZADA = "CREATE TABLE IF NOT EXISTS "
        + ProdutoVendaDBHelperBase.DB_TABLE_SINC + " ("
        + "  id_produto_venda INTEGER "
        + " , quantidade INTEGER "
        + " , valor_total REAL "
        + " , valor_item REAL "
		+ " , id_produto_a INTEGER "
		
		+ " , id_venda_a INTEGER "
		
        + ", operacao_sinc TEXT);";


    public static final String DB_CREATE = "CREATE TABLE IF NOT EXISTS "
        + ProdutoVendaDBHelperBase.DB_TABLE + " ("
        + "  id_produto_venda INTEGER PRIMARY KEY "
        + " , quantidade INTEGER "
        + " , valor_total REAL "
        + " , valor_item REAL "
		+ " , id_produto_a INTEGER "
		
		+ " , id_venda_a INTEGER "
		
        + ");";
    
    private static final String DB_DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private static final String DB_DROP = "DROP TABLE IF EXISTS " + DB_TABLE;
    private static final String DB_DROP_SINCRONIZADA = "DROP TABLE IF EXISTS " + DB_TABLE_SINC;
    
    private static class DBOpenHelper extends SQLiteOpenHelper {

       

        public DBOpenHelper(final Context context) {
            super(context, ProdutoVendaDBHelperBase.DB_NAME, null, ProdutoVendaDBHelperBase.DB_VERSION);
        }

        @Override
        public void onCreate(final SQLiteDatabase db) {
            try {
                db.execSQL(DB_CREATE);
            } catch (SQLException e) {
                Log.e(Constants.LOGTAG, ProdutoVendaDBHelperBase.CLASSNAME, e);
            }
        }

        @Override
        public void onOpen(final SQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + ProdutoVendaDBHelperBase.DB_TABLE);
            onCreate(db);
        }
    }

    //
    // end inner classes
    //
    
    // Versao Nova
 	public ProdutoVendaDBHelperBase() {
    	this.dbOpenHelper = null;
    	setDataSource(DataSourceAplicacao.getInstancia());
    }
    
   
	
	protected ContentValues montaValores(final DCIObjetoDominio valor) {
		ProdutoVenda item = (ProdutoVenda) valor;
		ContentValues valores = new ContentValues();
       	putValor(valores,"id_produto_venda",item.getIdProdutoVenda());
       	
       	putValor(valores,"quantidade",item.getQuantidade());
       	
       	putValor(valores,"valor_total",item.getValorTotal());
       	
       	putValor(valores,"valor_item",item.getValorItem());
       	
       	putValor(valores,"id_produto_a",item.getIdProdutoA());
       	
       	putValor(valores,"id_venda_a",item.getIdVendaA());
       	
        return valores;
	}


    // **** Chamadas Diretas Objeto Banco de Dados
    private void registraErroChamadaDireta(Exception e) {
    	Log.e(Constants.LOGTAG, ProdutoVendaDBHelperBase.CLASSNAME, e);
    }
   
    public final void insert(final ProdutoVenda item) {
	    try {
	        getDb().insert(ProdutoVendaDBHelperBase.DB_TABLE, null, montaValores(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    public final void update(final ProdutoVenda item) {
	    try {
	        getDb().update(ProdutoVendaDBHelperBase.DB_TABLE, montaValores(item), "id_produto_venda=" + item.getIdProdutoVenda(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     // Nao pode ser final o parametro para retornar o objeto com seu Id
    public final void insertSinc(ProdutoVenda item) {
        try {
        	item.setIdProdutoVenda((int)getMaxId() + 1);
        	DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc: " + item.debug());
	        long id = getDb().insert(ProdutoVendaDBHelperBase.DB_TABLE, null, montaValores(item));
    	    ((ObjetoSinc)item).setOperacaoSinc("I");
        	getDb().insert(ProdutoVendaDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     public final void updateSinc(final ProdutoVenda item) {
        try {
	        DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc: " + item.debug());
	        getDb().update(ProdutoVendaDBHelperBase.DB_TABLE, montaValores(item), "id_produto_venda=" + item.getIdProdutoVenda(), null);
	        ((ObjetoSinc)item).setOperacaoSinc("A");
	        ProdutoVenda atual = this.getSincById(item.getId());
	        if (atual==null) {
	        	getDb().insert(ProdutoVendaDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	        } else {
	        	if ("I".equals(((ObjetoSinc)atual).getOperacaoSinc()))
	        		((ObjetoSinc)item).setOperacaoSinc("I");
	        	getDb().update(ProdutoVendaDBHelperBase.DB_TABLE_SINC, montaValoresSinc(item), "id_produto_venda=" + item.getIdProdutoVenda(), null);
	        }
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    protected final void delete(final long id) {
        try {
			getDb().delete(ProdutoVendaDBHelperBase.DB_TABLE, "id_produto_venda=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    /*
    private void deleteSinc(final long id) {
        try {
			getDb().delete(ProdutoVendaDBHelperBase.DB_TABLE_SINC, "id_produto_venda=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
	*/
    public void limpaSinc(final ProdutoVenda item) {
    	try {
			getDb().delete(ProdutoVendaDBHelperBase.DB_TABLE_SINC, "id_produto_venda=" + item.getId(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    
    public void deleteSinc(final ProdutoVenda item) {
    	try {
	        DCLog.dStack(DCLog.DATABASE_ADM, this, "deleteSinc: " + item.debug());
	        delete(item.getId());
	        ((ObjetoSinc)item).setOperacaoSinc("D");
        	getDb().insert(ProdutoVendaDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
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
    public ProdutoVenda getById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (ProdutoVenda) getItemQuery(true, ProdutoVendaDBHelperBase.DB_TABLE, ProdutoVendaDBHelperBase.COLS, "id_produto_venda = " + id + "", null, null, null, null,null);
    }
    
    // Esta com orderBy que pode ser bom para exibicoes em tela
    // mas nao e bom para sincronizacao, pensar em ter um metodo para tela e outro para sinc.
    public List<ProdutoVenda> getAll() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, null);
    }
    public List<ProdutoVenda> getAllTela() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, orderByColuna());
    }
    
    private ProdutoVenda getByRowId(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (ProdutoVenda) getItemQuery(true, ProdutoVendaDBHelperBase.DB_TABLE, ProdutoVendaDBHelperBase.COLS, "ROWID = " + id + "", null, null, null, null,null);
    }
    private ProdutoVenda getSincById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (ProdutoVenda) getItemQuerySinc(true, ProdutoVendaDBHelperBase.DB_TABLE_SINC, ProdutoVendaDBHelperBase.COLS_SINC, "id_produto_venda = " + id + "", null, null, null, null,null);
    }
    
    
    public long getMaxId() {
		String sql = "select max(id_produto_venda) from " + DB_TABLE;
		return this.getNumeroSql(sql);
	}
	protected String orderByColuna() {
    	return null;
    }
	
	
	// Classe e relacionamento
	public List<ProdutoVenda> getPorAssociadaProduto(Context contexto, long id) throws DaoException{
		String sql = "select " + camposOrdenados() + 
				" , " + VendaDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinVenda_Associada() +
				" where id_produto_a = " + id;
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new ProdutoVendaMontador(), null);
		montador.adicionaMontador(new VendaMontador(), "Venda_Associada");
		setMontador(montador);
		return this.getListaSqlListaInterna(sql);
			
	}
	public List<ProdutoVenda> getPorAssociadaProduto(long id) throws DaoException{
		String sql = "select " + camposOrdenados() + 
				" , " + VendaDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinVenda_Associada() +
				" where id_produto_a = " + id;
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new ProdutoVendaMontador(), null);
		montador.adicionaMontador(new VendaMontador(), "Venda_Associada");
		setMontador(montador);
		return this.getListaSqlListaInterna(sql);
			
	}
	public List<Venda> getVendaPorAssociadaProduto(Context contexto, long id) throws DaoException{
		String sql = "select " + VendaDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinVenda_Associada() +
				" where id_produto_a = " + id;
		setMontador(new VendaMontador());
		List<Venda> saida = this.getListaSql(sql);
		setMontador(null);
		return saida;
	}
	
	
	
	// Classe e relacionamento
	public List<ProdutoVenda> getPorAssociadaVenda(Context contexto, long id) throws DaoException{
		String sql = "select " + camposOrdenados() + 
				" , " + ProdutoDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinProduto_Associada() +
				" where id_venda_a = " + id;
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new ProdutoVendaMontador(), null);
		montador.adicionaMontador(new ProdutoMontador(), "Produto_Associada");
		setMontador(montador);
		return this.getListaSqlListaInterna(sql);
			
	}
	public List<ProdutoVenda> getPorAssociadaVenda(long id) throws DaoException{
		String sql = "select " + camposOrdenados() + 
				" , " + ProdutoDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinProduto_Associada() +
				" where id_venda_a = " + id;
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new ProdutoVendaMontador(), null);
		montador.adicionaMontador(new ProdutoMontador(), "Produto_Associada");
		setMontador(montador);
		return this.getListaSqlListaInterna(sql);
			
	}
	public List<Produto> getProdutoPorAssociadaVenda(Context contexto, long id) throws DaoException{
		String sql = "select " + ProdutoDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinProduto_Associada() +
				" where id_venda_a = " + id;
		setMontador(new ProdutoMontador());
		List<Produto> saida = this.getListaSql(sql);
		setMontador(null);
		return saida;
	}
	
	
  
  	
	public ProdutoVenda getPorProdutoVenda(long idXXXX, long idYYYYY) {
		return null;
	}
	
  
  
  	
	
	
	// Relacionamento onde esse objeto ? chave estrangeira de outro. ????
	
	private boolean _obtemProduto_Associada = false;
	public void setObtemProduto_Associada() {
		_obtemProduto_Associada = true;
	}
	protected String outterJoinProduto_Associada() {
		return " left outer join " + ProdutoDBHelperBase.DB_TABLE + " on " + ProdutoDBHelperBase.DB_TABLE + ".id_produto = " + DB_TABLE + ".id_produto_a ";  
	}
	public boolean atualizaAssociadaProduto(long idProdutoVenda, long idProdutoA) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_produto_a  = " + idProdutoA +
        " where id_produto_venda = " +  idProdutoVenda;
       	//this.executaSql(sql);
       	return true;
	}
	public ProdutoVenda obtemPorIdsAssociadaProduto(long idProdutoVenda, long idProdutoA) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_produto_a = " + idProdutoA + " and " +
			  " id_produto_venda = " + idProdutoVenda;
		return (ProdutoVenda) this.geObjetoSql(sql);
	}
	
	
	public static String innerJoinProduto_Associada() {
		return " inner join " + ProdutoDBHelperBase.DB_TABLE + " on " + ProdutoDBHelperBase.DB_TABLE + ".id_produto = " + DB_TABLE + ".id_produto_a ";  
	}
	public static String outerJoinProduto_Associada() {
		return " left outer join " + ProdutoDBHelperBase.DB_TABLE + " on " + ProdutoDBHelperBase.DB_TABLE + ".id_produto = " + DB_TABLE + ".id_produto_a ";  
	}
	public static String innerJoinSincProduto_Associada() {
		return " inner join " + ProdutoDBHelperBase.DB_TABLE_SINC + " on " + ProdutoDBHelperBase.DB_TABLE_SINC + ".id_produto = " + DB_TABLE_SINC + ".id_produto_a ";  
	}
	public static String outerJoinSincProduto_Associada() {
		return " left outer join " + ProdutoDBHelperBase.DB_TABLE_SINC + " on " + ProdutoDBHelperBase.DB_TABLE_SINC + ".id_produto = " + DB_TABLE_SINC + ".id_produto_a ";  
	}
	
 	
	private boolean _obtemVenda_Associada = false;
	public void setObtemVenda_Associada() {
		_obtemVenda_Associada = true;
	}
	protected String outterJoinVenda_Associada() {
		return " left outer join " + VendaDBHelperBase.DB_TABLE + " on " + VendaDBHelperBase.DB_TABLE + ".id_venda = " + DB_TABLE + ".id_venda_a ";  
	}
	public boolean atualizaAssociadaVenda(long idProdutoVenda, long idVendaA) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_venda_a  = " + idVendaA +
        " where id_produto_venda = " +  idProdutoVenda;
       	//this.executaSql(sql);
       	return true;
	}
	public ProdutoVenda obtemPorIdsAssociadaVenda(long idProdutoVenda, long idVendaA) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_venda_a = " + idVendaA + " and " +
			  " id_produto_venda = " + idProdutoVenda;
		return (ProdutoVenda) this.geObjetoSql(sql);
	}
	
	
	public static String innerJoinVenda_Associada() {
		return " inner join " + VendaDBHelperBase.DB_TABLE + " on " + VendaDBHelperBase.DB_TABLE + ".id_venda = " + DB_TABLE + ".id_venda_a ";  
	}
	public static String outerJoinVenda_Associada() {
		return " left outer join " + VendaDBHelperBase.DB_TABLE + " on " + VendaDBHelperBase.DB_TABLE + ".id_venda = " + DB_TABLE + ".id_venda_a ";  
	}
	public static String innerJoinSincVenda_Associada() {
		return " inner join " + VendaDBHelperBase.DB_TABLE_SINC + " on " + VendaDBHelperBase.DB_TABLE_SINC + ".id_venda = " + DB_TABLE_SINC + ".id_venda_a ";  
	}
	public static String outerJoinSincVenda_Associada() {
		return " left outer join " + VendaDBHelperBase.DB_TABLE_SINC + " on " + VendaDBHelperBase.DB_TABLE_SINC + ".id_venda = " + DB_TABLE_SINC + ".id_venda_a ";  
	}
	
 	
	// ********************************************************************	
	
	
	public static String camposOrdenados() 
	{
		return " produto_venda.id_produto_venda " 
		+ " ,produto_venda.quantidade " 
		+ " ,produto_venda.valor_total " 
		+ " ,produto_venda.valor_item " 
		+ " ,produto_venda.id_produto_a " 
		+ " ,produto_venda.id_venda_a " 
		;
	}
	public static String camposOrdenadosSinc() 
	{
		return " produto_venda_sinc.id_produto_venda " 
		+ " ,produto_venda_sinc.quantidade " 
		+ " ,produto_venda_sinc.valor_total " 
		+ " ,produto_venda_sinc.valor_item " 
		+ " ,produto_venda_sinc.id_produto_a " 
		+ " ,produto_venda_sinc.id_venda_a " 
		
		+ " ,produto_venda_sinc.operacao_sinc "
		;
	}
	public static String camposOrdenadosAlias(String nomeTabela) 
	{
		return  nomeTabela + ".id_produto_venda " 
		+ " , " + nomeTabela + ".quantidade " 
		+ " , " + nomeTabela + ".valor_total " 
		+ " , " + nomeTabela + ".valor_item " 
		+ " , " + nomeTabela + ".id_produto_a " 
		+ " , " + nomeTabela + ".id_venda_a " 
		;
	}
	
	protected String camposOrdenadosJoin()
    {
        String saida = camposOrdenados();
		saida += (this._obtemProduto_Associada?" , " +ProdutoDBHelperBase.camposOrdenados():"");
		saida += (this._obtemVenda_Associada?" , " +VendaDBHelperBase.camposOrdenados():"");
        return saida;
    }
    
        
    public void limpaObtem()
    {
		_obtemProduto_Associada = false;
		_obtemVenda_Associada = false;
    }
    
	protected String outterJoinAgrupado()
    {
        String saida = " ";
		saida += (this._obtemProduto_Associada? outterJoinProduto_Associada() + " ":"");
		saida += (this._obtemVenda_Associada? outterJoinVenda_Associada() + " ":"");
        return saida;
    }
    protected MontadorDaoI getMontadorAgrupado()
    {
        MontadorDaoComposite montador = new MontadorDaoComposite();
        montador.adicionaMontador(new ProdutoVendaMontador(), null);
		if (this._obtemProduto_Associada)
            montador.adicionaMontador(new ProdutoMontador(), "Produto_Associada");
		if (this._obtemVenda_Associada)
            montador.adicionaMontador(new VendaMontador(), "Venda_Associada");
         return montador;
    }
	
    
   	// Poderia passar para classe abstrata.
    public final List<ProdutoVenda> getAllSinc() throws DaoException {
    	this.setMontador(null);
    	List<ProdutoVenda> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<ProdutoVenda>();
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
    public final List<ProdutoVenda> getAllSincSoAlteracao() throws DaoException {
    	List<ProdutoVenda> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    		saida = null; // Melhorar aqui !!!!
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<ProdutoVenda>();
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
	
	public final List<ProdutoVenda> getAllSincImpl() throws DaoException {
   		String sql = "select " + camposOrdenadosSinc()   
   			+ " from " + this.DB_TABLE_SINC;
   		ProdutoVendaMontador montador = new ProdutoVendaMontador(true); // sinc ligado
   		this.setMontador(montador);
   		List<ProdutoVenda> saida = this.getListaSql(sql);
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
		this.insert((ProdutoVenda) obj);
	}
	@Override
	public final void dropCreate() {
		this.dropTable();
		this.criaTabela();
	}
}
