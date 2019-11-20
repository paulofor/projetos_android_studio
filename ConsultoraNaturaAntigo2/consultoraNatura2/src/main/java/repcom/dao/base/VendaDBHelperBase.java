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
import repcom.modelo.vo.VendaVo;
import repcom.modelo.vo.FabricaVo;
import repcom.dao.*;
import repcom.dao.montador.*;
import repcom.dao.datasource.DataSourceAplicacao;

public abstract class VendaDBHelperBase extends DBHelperBase
	implements DaoSincronismo // coloquei aqui para evitar impacto de arquitetura
{

    private static final String DB_NAME = "w_alert";
    public static final String DB_TABLE = "venda";
    public static final String DB_TABLE_SINC = "venda_sinc";
    public static final int DB_VERSION = 3;

    protected static final String CLASSNAME = VendaDBHelperBase.class.getSimpleName();
    protected static final String[] COLS = new String[] { 
        "id_venda"
        ,"data"
        ,"valor_total"
		, "id_cliente_fp"
	
		, "id_usuario_s"
	
    };

	protected static final String[] COLS_SINC = new String[] { 
        "id_venda"
        ,"data"
        ,"valor_total"
		, "id_cliente_fp"
	
		, "id_usuario_s"
		, "operacao_sinc"
    };

    protected SQLiteDatabase db;
    protected final DBOpenHelper dbOpenHelper;

	@Override
	protected MontadorDaoI criaMontadorPadrao() {
		return new VendaMontador();
	}
	
	protected String getSqlIndices() {
		return "";
	}
	protected String getSqlCreate(){
		return  "CREATE TABLE "
        + VendaDBHelperBase.DB_TABLE + " ("
        + "  id_venda INTEGER PRIMARY KEY "
        + " , data INTEGER "
        + " , valor_total REAL "
		+ " , id_cliente_fp INTEGER "
		
		+ " , id_usuario_s INTEGER "
		
		+ getSqlIndices()
        + ");";
	}

	

	public static final String DB_CREATE_SINCRONIZADA = "CREATE TABLE IF NOT EXISTS "
        + VendaDBHelperBase.DB_TABLE_SINC + " ("
        + "  id_venda INTEGER "
        + " , data INTEGER "
        + " , valor_total REAL "
		+ " , id_cliente_fp INTEGER "
		
		+ " , id_usuario_s INTEGER "
		
        + ", operacao_sinc TEXT);";


    public static final String DB_CREATE = "CREATE TABLE IF NOT EXISTS "
        + VendaDBHelperBase.DB_TABLE + " ("
        + "  id_venda INTEGER PRIMARY KEY "
        + " , data INTEGER "
        + " , valor_total REAL "
		+ " , id_cliente_fp INTEGER "
		
		+ " , id_usuario_s INTEGER "
		
        + ");";
    
    private static final String DB_DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private static final String DB_DROP = "DROP TABLE IF EXISTS " + DB_TABLE;
    private static final String DB_DROP_SINCRONIZADA = "DROP TABLE IF EXISTS " + DB_TABLE_SINC;
    
    private static class DBOpenHelper extends SQLiteOpenHelper {

       

        public DBOpenHelper(final Context context) {
            super(context, VendaDBHelperBase.DB_NAME, null, VendaDBHelperBase.DB_VERSION);
        }

        @Override
        public void onCreate(final SQLiteDatabase db) {
            try {
                db.execSQL(DB_CREATE);
            } catch (SQLException e) {
                Log.e(Constants.LOGTAG, VendaDBHelperBase.CLASSNAME, e);
            }
        }

        @Override
        public void onOpen(final SQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + VendaDBHelperBase.DB_TABLE);
            onCreate(db);
        }
    }

    //
    // end inner classes
    //
    
    // Versao Nova
 	public VendaDBHelperBase() {
    	this.dbOpenHelper = null;
    	setDataSource(DataSourceAplicacao.getInstancia());
    }
    
   
	
	protected ContentValues montaValores(final DCIObjetoDominio valor) {
		Venda item = (Venda) valor;
		ContentValues valores = new ContentValues();
       	putValor(valores,"id_venda",item.getIdVenda());
       	
       	putValorData(valores,"data",item.getData());
        		
       	putValor(valores,"valor_total",item.getValorTotal());
       	
       	putValor(valores,"id_cliente_fp",item.getIdClienteFp());
       	
       	putValor(valores,"id_usuario_s",item.getIdUsuarioS());
       	
        return valores;
	}


    // **** Chamadas Diretas Objeto Banco de Dados
    private void registraErroChamadaDireta(Exception e) {
    	Log.e(Constants.LOGTAG, VendaDBHelperBase.CLASSNAME, e);
    }
   
    public final void insert(final Venda item) {
	    try {
	        getDb().insert(VendaDBHelperBase.DB_TABLE, null, montaValores(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    public final void update(final Venda item) {
	    try {
	        getDb().update(VendaDBHelperBase.DB_TABLE, montaValores(item), "id_venda=" + item.getIdVenda(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     // Nao pode ser final o parametro para retornar o objeto com seu Id
    public final void insertSinc(Venda item) {
        try {
        	item.setIdVenda((int)getMaxId() + 1);
        	DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc: " + item.debug());
	        long id = getDb().insert(VendaDBHelperBase.DB_TABLE, null, montaValores(item));
    	    ((ObjetoSinc)item).setOperacaoSinc("I");
        	getDb().insert(VendaDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     public final void updateSinc(final Venda item) {
        try {
	        DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc: " + item.debug());
	        getDb().update(VendaDBHelperBase.DB_TABLE, montaValores(item), "id_venda=" + item.getIdVenda(), null);
	        ((ObjetoSinc)item).setOperacaoSinc("A");
	        Venda atual = this.getSincById(item.getId());
	        if (atual==null) {
	        	getDb().insert(VendaDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	        } else {
	        	if ("I".equals(((ObjetoSinc)atual).getOperacaoSinc()))
	        		((ObjetoSinc)item).setOperacaoSinc("I");
	        	getDb().update(VendaDBHelperBase.DB_TABLE_SINC, montaValoresSinc(item), "id_venda=" + item.getIdVenda(), null);
	        }
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    protected final void delete(final long id) {
        try {
			getDb().delete(VendaDBHelperBase.DB_TABLE, "id_venda=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    /*
    private void deleteSinc(final long id) {
        try {
			getDb().delete(VendaDBHelperBase.DB_TABLE_SINC, "id_venda=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
	*/
    public void limpaSinc(final Venda item) {
    	try {
			getDb().delete(VendaDBHelperBase.DB_TABLE_SINC, "id_venda=" + item.getId(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    
    public void deleteSinc(final Venda item) {
    	try {
	        DCLog.dStack(DCLog.DATABASE_ADM, this, "deleteSinc: " + item.debug());
	        delete(item.getId());
	        ((ObjetoSinc)item).setOperacaoSinc("D");
        	getDb().insert(VendaDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
   
    
	public void criaTabelaSincronizacao() {
	    try {
			getDb().execSQL(DB_CREATE_SINCRONIZADA);
		
			// Dependente
			//getDb().execSQL(ProdutoVendaDBHelperBase.DB_CREATE_SINCRONIZADA);
			
		
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
	}
    public void criaTabela() {
        try {
	        getDb().execSQL(DB_CREATE);
	    
			// Dependente
			//getDb().execSQL(ProdutoVendaDBHelperBase.DB_CREATE);
			
		
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
    public Venda getById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (Venda) getItemQuery(true, VendaDBHelperBase.DB_TABLE, VendaDBHelperBase.COLS, "id_venda = " + id + "", null, null, null, null,null);
    }
    
    // Esta com orderBy que pode ser bom para exibicoes em tela
    // mas nao e bom para sincronizacao, pensar em ter um metodo para tela e outro para sinc.
    public List<Venda> getAll() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, null);
    }
    public List<Venda> getAllTela() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, orderByColuna());
    }
    
    private Venda getByRowId(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (Venda) getItemQuery(true, VendaDBHelperBase.DB_TABLE, VendaDBHelperBase.COLS, "ROWID = " + id + "", null, null, null, null,null);
    }
    private Venda getSincById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (Venda) getItemQuerySinc(true, VendaDBHelperBase.DB_TABLE_SINC, VendaDBHelperBase.COLS_SINC, "id_venda = " + id + "", null, null, null, null,null);
    }
    
    
    public long getMaxId() {
		String sql = "select max(id_venda) from " + DB_TABLE;
		return this.getNumeroSql(sql);
	}
	protected String orderByColuna() {
    	return null;
    }
	
	
	public List<Venda> getPorFeitaParaCliente(Context contexto, long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_cliente_fp = " + id, null, null, null, null);
	}
	public List<Venda> getPorFeitaParaCliente(long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_cliente_fp = " + id, null, null, null, null);
	}
	
	
  
  	
  
  
  	
	/*
	public Venda obtemPorProdutoVendaAssociada(long id) {
		string sql;
		sql = "select " + camposOrdenados() +
			" from " + tabelaSelect() +
			innerJoinProdutoVenda_Associada() + 
			" where id_produto_venda = " + id;
		return (Venda) getObjeto(sql);
	}
	*/
	public static String innerJoinProdutoVenda_Associada() {
		return " inner join " + ProdutoVendaDBHelperBase.DB_TABLE + " on " + ProdutoVendaDBHelperBase.DB_TABLE + ".id_venda_a = " + DB_TABLE + ".id_venda ";  
	}
	public static String innerJoinSincProdutoVenda_Associada() {
		return " inner join " + ProdutoVendaDBHelperBase.DB_TABLE_SINC + " on " + ProdutoVendaDBHelperBase.DB_TABLE_SINC + ".id_venda_a = " + DB_TABLE_SINC + ".id_venda ";  
	}
	public static String outerJoinProdutoVenda_Associada() {
		return " left outer join " + ProdutoVendaDBHelperBase.DB_TABLE + " on " + ProdutoVendaDBHelperBase.DB_TABLE + ".id_venda_a = " + DB_TABLE + ".id_venda ";  
	}
	public static String outerJoinSincProdutoVenda_Associada() {
		return " left outer join " + ProdutoVendaDBHelperBase.DB_TABLE_SINC + " on " + ProdutoVendaDBHelperBase.DB_TABLE_SINC + ".id_venda_a = " + DB_TABLE_SINC + ".id_venda ";  
	}
 	
	
	
	// Relacionamento onde esse objeto ? chave estrangeira de outro. ????
	
	private boolean _obtemCliente_FeitaPara = false;
	public void setObtemCliente_FeitaPara() {
		_obtemCliente_FeitaPara = true;
	}
	protected String outterJoinCliente_FeitaPara() {
		return " left outer join " + ClienteDBHelperBase.DB_TABLE + " on " + ClienteDBHelperBase.DB_TABLE + ".id_cliente = " + DB_TABLE + ".id_cliente_fp ";  
	}
	public boolean atualizaFeitaParaCliente(long idVenda, long idClienteFp) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_cliente_fp  = " + idClienteFp +
        " where id_venda = " +  idVenda;
       	//this.executaSql(sql);
       	return true;
	}
	public Venda obtemPorIdsFeitaParaCliente(long idVenda, long idClienteFp) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_cliente_fp = " + idClienteFp + " and " +
			  " id_venda = " + idVenda;
		return (Venda) this.geObjetoSql(sql);
	}
	
	
	public static String innerJoinCliente_FeitaPara() {
		return " inner join " + ClienteDBHelperBase.DB_TABLE + " on " + ClienteDBHelperBase.DB_TABLE + ".id_cliente = " + DB_TABLE + ".id_cliente_fp ";  
	}
	public static String outerJoinCliente_FeitaPara() {
		return " left outer join " + ClienteDBHelperBase.DB_TABLE + " on " + ClienteDBHelperBase.DB_TABLE + ".id_cliente = " + DB_TABLE + ".id_cliente_fp ";  
	}
	public static String innerJoinSincCliente_FeitaPara() {
		return " inner join " + ClienteDBHelperBase.DB_TABLE_SINC + " on " + ClienteDBHelperBase.DB_TABLE_SINC + ".id_cliente = " + DB_TABLE_SINC + ".id_cliente_fp ";  
	}
	public static String outerJoinSincCliente_FeitaPara() {
		return " left outer join " + ClienteDBHelperBase.DB_TABLE_SINC + " on " + ClienteDBHelperBase.DB_TABLE_SINC + ".id_cliente = " + DB_TABLE_SINC + ".id_cliente_fp ";  
	}
	
 	
	// ********************************************************************	
	
	
	public static String camposOrdenados() 
	{
		return " venda.id_venda " 
		+ " ,venda.data " 
		+ " ,venda.valor_total " 
		+ " ,venda.id_cliente_fp " 
		+ " ,venda.id_usuario_s " 
		;
	}
	public static String camposOrdenadosSinc() 
	{
		return " venda_sinc.id_venda " 
		+ " ,venda_sinc.data " 
		+ " ,venda_sinc.valor_total " 
		+ " ,venda_sinc.id_cliente_fp " 
		+ " ,venda_sinc.id_usuario_s " 
		
		+ " ,venda_sinc.operacao_sinc "
		;
	}
	public static String camposOrdenadosAlias(String nomeTabela) 
	{
		return  nomeTabela + ".id_venda " 
		+ " , " +  "DATE_FORMAT(" + nomeTabela + ".data,'%d-%m-%Y') " 
		+ " , " + nomeTabela + ".valor_total " 
		+ " , " + nomeTabela + ".id_cliente_fp " 
		+ " , " + nomeTabela + ".id_usuario_s " 
		;
	}
	
	protected String camposOrdenadosJoin()
    {
        String saida = camposOrdenados();
		saida += (this._obtemCliente_FeitaPara?" , " +ClienteDBHelperBase.camposOrdenados():"");
        return saida;
    }
    
        
    public void limpaObtem()
    {
		_obtemCliente_FeitaPara = false;
    }
    
	protected String outterJoinAgrupado()
    {
        String saida = " ";
		saida += (this._obtemCliente_FeitaPara? outterJoinCliente_FeitaPara() + " ":"");
        return saida;
    }
    protected MontadorDaoI getMontadorAgrupado()
    {
        MontadorDaoComposite montador = new MontadorDaoComposite();
        montador.adicionaMontador(new VendaMontador(), null);
		if (this._obtemCliente_FeitaPara)
            montador.adicionaMontador(new ClienteMontador(), "Cliente_FeitaPara");
         return montador;
    }
	
    
   	// Poderia passar para classe abstrata.
    public final List<Venda> getAllSinc() throws DaoException {
    	this.setMontador(null);
    	List<Venda> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<Venda>();
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
    public final List<Venda> getAllSincSoAlteracao() throws DaoException {
    	List<Venda> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    		saida = null; // Melhorar aqui !!!!
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<Venda>();
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
	
	public final List<Venda> getAllSincImpl() throws DaoException {
   		String sql = "select " + camposOrdenadosSinc()   
   			+ " from " + this.DB_TABLE_SINC;
   		VendaMontador montador = new VendaMontador(true); // sinc ligado
   		this.setMontador(montador);
   		List<Venda> saida = this.getListaSql(sql);
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
		this.insert((Venda) obj);
	}
	@Override
	public final void dropCreate() {
		this.dropTable();
		this.criaTabela();
	}
}
