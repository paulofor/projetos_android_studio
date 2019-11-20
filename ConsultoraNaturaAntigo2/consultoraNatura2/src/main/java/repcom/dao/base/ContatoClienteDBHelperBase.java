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
import repcom.modelo.vo.ContatoClienteVo;
import repcom.modelo.vo.FabricaVo;
import repcom.dao.*;
import repcom.dao.montador.*;
import repcom.dao.datasource.DataSourceAplicacao;

public abstract class ContatoClienteDBHelperBase extends DBHelperBase
	implements DaoSincronismo // coloquei aqui para evitar impacto de arquitetura
{

    private static final String DB_NAME = "w_alert";
    public static final String DB_TABLE = "contato_cliente";
    public static final String DB_TABLE_SINC = "contato_cliente_sinc";
    public static final int DB_VERSION = 3;

    protected static final String CLASSNAME = ContatoClienteDBHelperBase.class.getSimpleName();
    protected static final String[] COLS = new String[] { 
        "id_contato_cliente"
        ,"data_contato"
		, "id_cliente_ra"
	
    };

	protected static final String[] COLS_SINC = new String[] { 
        "id_contato_cliente"
        ,"data_contato"
		, "id_cliente_ra"
		, "operacao_sinc"
    };

    protected SQLiteDatabase db;
    protected final DBOpenHelper dbOpenHelper;

	@Override
	protected MontadorDaoI criaMontadorPadrao() {
		return new ContatoClienteMontador();
	}
	
	protected String getSqlIndices() {
		return "";
	}
	protected String getSqlCreate(){
		return  "CREATE TABLE "
        + ContatoClienteDBHelperBase.DB_TABLE + " ("
        + "  id_contato_cliente INTEGER PRIMARY KEY "
        + " , data_contato INTEGER "
		+ " , id_cliente_ra INTEGER "
		
		+ getSqlIndices()
        + ");";
	}

	

	public static final String DB_CREATE_SINCRONIZADA = "CREATE TABLE IF NOT EXISTS "
        + ContatoClienteDBHelperBase.DB_TABLE_SINC + " ("
        + "  id_contato_cliente INTEGER "
        + " , data_contato INTEGER "
		+ " , id_cliente_ra INTEGER "
		
        + ", operacao_sinc TEXT);";


    public static final String DB_CREATE = "CREATE TABLE IF NOT EXISTS "
        + ContatoClienteDBHelperBase.DB_TABLE + " ("
        + "  id_contato_cliente INTEGER PRIMARY KEY "
        + " , data_contato INTEGER "
		+ " , id_cliente_ra INTEGER "
		
        + ");";
    
    private static final String DB_DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private static final String DB_DROP = "DROP TABLE IF EXISTS " + DB_TABLE;
    private static final String DB_DROP_SINCRONIZADA = "DROP TABLE IF EXISTS " + DB_TABLE_SINC;
    
    private static class DBOpenHelper extends SQLiteOpenHelper {

       

        public DBOpenHelper(final Context context) {
            super(context, ContatoClienteDBHelperBase.DB_NAME, null, ContatoClienteDBHelperBase.DB_VERSION);
        }

        @Override
        public void onCreate(final SQLiteDatabase db) {
            try {
                db.execSQL(DB_CREATE);
            } catch (SQLException e) {
                Log.e(Constants.LOGTAG, ContatoClienteDBHelperBase.CLASSNAME, e);
            }
        }

        @Override
        public void onOpen(final SQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + ContatoClienteDBHelperBase.DB_TABLE);
            onCreate(db);
        }
    }

    //
    // end inner classes
    //
    
    // Versao Nova
 	public ContatoClienteDBHelperBase() {
    	this.dbOpenHelper = null;
    	setDataSource(DataSourceAplicacao.getInstancia());
    }
    
   
	
	protected ContentValues montaValores(final DCIObjetoDominio valor) {
		ContatoCliente item = (ContatoCliente) valor;
		ContentValues valores = new ContentValues();
       	putValor(valores,"id_contato_cliente",item.getIdContatoCliente());
       	
       	putValorData(valores,"data_contato",item.getDataContato());
        		
       	putValor(valores,"id_cliente_ra",item.getIdClienteRa());
       	
        return valores;
	}


    // **** Chamadas Diretas Objeto Banco de Dados
    private void registraErroChamadaDireta(Exception e) {
    	Log.e(Constants.LOGTAG, ContatoClienteDBHelperBase.CLASSNAME, e);
    }
   
    public final void insert(final ContatoCliente item) {
	    try {
	        getDb().insert(ContatoClienteDBHelperBase.DB_TABLE, null, montaValores(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    public final void update(final ContatoCliente item) {
	    try {
	        getDb().update(ContatoClienteDBHelperBase.DB_TABLE, montaValores(item), "id_contato_cliente=" + item.getIdContatoCliente(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     // Nao pode ser final o parametro para retornar o objeto com seu Id
    public final void insertSinc(ContatoCliente item) {
        try {
        	item.setIdContatoCliente((int)getMaxId() + 1);
        	DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc: " + item.debug());
	        long id = getDb().insert(ContatoClienteDBHelperBase.DB_TABLE, null, montaValores(item));
    	    ((ObjetoSinc)item).setOperacaoSinc("I");
        	getDb().insert(ContatoClienteDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     public final void updateSinc(final ContatoCliente item) {
        try {
	        DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc: " + item.debug());
	        getDb().update(ContatoClienteDBHelperBase.DB_TABLE, montaValores(item), "id_contato_cliente=" + item.getIdContatoCliente(), null);
	        ((ObjetoSinc)item).setOperacaoSinc("A");
	        ContatoCliente atual = this.getSincById(item.getId());
	        if (atual==null) {
	        	getDb().insert(ContatoClienteDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	        } else {
	        	if ("I".equals(((ObjetoSinc)atual).getOperacaoSinc()))
	        		((ObjetoSinc)item).setOperacaoSinc("I");
	        	getDb().update(ContatoClienteDBHelperBase.DB_TABLE_SINC, montaValoresSinc(item), "id_contato_cliente=" + item.getIdContatoCliente(), null);
	        }
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    protected final void delete(final long id) {
        try {
			getDb().delete(ContatoClienteDBHelperBase.DB_TABLE, "id_contato_cliente=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    /*
    private void deleteSinc(final long id) {
        try {
			getDb().delete(ContatoClienteDBHelperBase.DB_TABLE_SINC, "id_contato_cliente=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
	*/
    public void limpaSinc(final ContatoCliente item) {
    	try {
			getDb().delete(ContatoClienteDBHelperBase.DB_TABLE_SINC, "id_contato_cliente=" + item.getId(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    
    public void deleteSinc(final ContatoCliente item) {
    	try {
	        DCLog.dStack(DCLog.DATABASE_ADM, this, "deleteSinc: " + item.debug());
	        delete(item.getId());
	        ((ObjetoSinc)item).setOperacaoSinc("D");
        	getDb().insert(ContatoClienteDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
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
    public ContatoCliente getById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (ContatoCliente) getItemQuery(true, ContatoClienteDBHelperBase.DB_TABLE, ContatoClienteDBHelperBase.COLS, "id_contato_cliente = " + id + "", null, null, null, null,null);
    }
    
    // Esta com orderBy que pode ser bom para exibicoes em tela
    // mas nao e bom para sincronizacao, pensar em ter um metodo para tela e outro para sinc.
    public List<ContatoCliente> getAll() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, null);
    }
    public List<ContatoCliente> getAllTela() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, orderByColuna());
    }
    
    private ContatoCliente getByRowId(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (ContatoCliente) getItemQuery(true, ContatoClienteDBHelperBase.DB_TABLE, ContatoClienteDBHelperBase.COLS, "ROWID = " + id + "", null, null, null, null,null);
    }
    private ContatoCliente getSincById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (ContatoCliente) getItemQuerySinc(true, ContatoClienteDBHelperBase.DB_TABLE_SINC, ContatoClienteDBHelperBase.COLS_SINC, "id_contato_cliente = " + id + "", null, null, null, null,null);
    }
    
    
    public long getMaxId() {
		String sql = "select max(id_contato_cliente) from " + DB_TABLE;
		return this.getNumeroSql(sql);
	}
	protected String orderByColuna() {
    	return null;
    }
	
	
	public List<ContatoCliente> getPorReferenteACliente(Context contexto, long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_cliente_ra = " + id, null, null, null, null);
	}
	public List<ContatoCliente> getPorReferenteACliente(long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_cliente_ra = " + id, null, null, null, null);
	}
	
	
  
  	
  
  
  	
	
	
	// Relacionamento onde esse objeto ? chave estrangeira de outro. ????
	
	private boolean _obtemCliente_ReferenteA = false;
	public void setObtemCliente_ReferenteA() {
		_obtemCliente_ReferenteA = true;
	}
	protected String outterJoinCliente_ReferenteA() {
		return " left outer join " + ClienteDBHelperBase.DB_TABLE + " on " + ClienteDBHelperBase.DB_TABLE + ".id_cliente = " + DB_TABLE + ".id_cliente_ra ";  
	}
	public boolean atualizaReferenteACliente(long idContatoCliente, long idClienteRa) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_cliente_ra  = " + idClienteRa +
        " where id_contato_cliente = " +  idContatoCliente;
       	//this.executaSql(sql);
       	return true;
	}
	public ContatoCliente obtemPorIdsReferenteACliente(long idContatoCliente, long idClienteRa) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_cliente_ra = " + idClienteRa + " and " +
			  " id_contato_cliente = " + idContatoCliente;
		return (ContatoCliente) this.geObjetoSql(sql);
	}
	
	
	public static String innerJoinCliente_ReferenteA() {
		return " inner join " + ClienteDBHelperBase.DB_TABLE + " on " + ClienteDBHelperBase.DB_TABLE + ".id_cliente = " + DB_TABLE + ".id_cliente_ra ";  
	}
	public static String outerJoinCliente_ReferenteA() {
		return " left outer join " + ClienteDBHelperBase.DB_TABLE + " on " + ClienteDBHelperBase.DB_TABLE + ".id_cliente = " + DB_TABLE + ".id_cliente_ra ";  
	}
	public static String innerJoinSincCliente_ReferenteA() {
		return " inner join " + ClienteDBHelperBase.DB_TABLE_SINC + " on " + ClienteDBHelperBase.DB_TABLE_SINC + ".id_cliente = " + DB_TABLE_SINC + ".id_cliente_ra ";  
	}
	public static String outerJoinSincCliente_ReferenteA() {
		return " left outer join " + ClienteDBHelperBase.DB_TABLE_SINC + " on " + ClienteDBHelperBase.DB_TABLE_SINC + ".id_cliente = " + DB_TABLE_SINC + ".id_cliente_ra ";  
	}
	
 	
	// ********************************************************************	
	
	
	public static String camposOrdenados() 
	{
		return " contato_cliente.id_contato_cliente " 
		+ " ,contato_cliente.data_contato " 
		+ " ,contato_cliente.id_cliente_ra " 
		;
	}
	public static String camposOrdenadosSinc() 
	{
		return " contato_cliente_sinc.id_contato_cliente " 
		+ " ,contato_cliente_sinc.data_contato " 
		+ " ,contato_cliente_sinc.id_cliente_ra " 
		
		+ " ,contato_cliente_sinc.operacao_sinc "
		;
	}
	public static String camposOrdenadosAlias(String nomeTabela) 
	{
		return  nomeTabela + ".id_contato_cliente " 
		+ " , " +  "DATE_FORMAT(" + nomeTabela + ".data_contato,'%d-%m-%Y') " 
		+ " , " + nomeTabela + ".id_cliente_ra " 
		;
	}
	
	protected String camposOrdenadosJoin()
    {
        String saida = camposOrdenados();
		saida += (this._obtemCliente_ReferenteA?" , " +ClienteDBHelperBase.camposOrdenados():"");
        return saida;
    }
    
        
    public void limpaObtem()
    {
		_obtemCliente_ReferenteA = false;
    }
    
	protected String outterJoinAgrupado()
    {
        String saida = " ";
		saida += (this._obtemCliente_ReferenteA? outterJoinCliente_ReferenteA() + " ":"");
        return saida;
    }
    protected MontadorDaoI getMontadorAgrupado()
    {
        MontadorDaoComposite montador = new MontadorDaoComposite();
        montador.adicionaMontador(new ContatoClienteMontador(), null);
		if (this._obtemCliente_ReferenteA)
            montador.adicionaMontador(new ClienteMontador(), "Cliente_ReferenteA");
         return montador;
    }
	
    
   	// Poderia passar para classe abstrata.
    public final List<ContatoCliente> getAllSinc() throws DaoException {
    	this.setMontador(null);
    	List<ContatoCliente> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<ContatoCliente>();
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
    public final List<ContatoCliente> getAllSincSoAlteracao() throws DaoException {
    	List<ContatoCliente> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    		saida = null; // Melhorar aqui !!!!
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<ContatoCliente>();
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
	
	public final List<ContatoCliente> getAllSincImpl() throws DaoException {
   		String sql = "select " + camposOrdenadosSinc()   
   			+ " from " + this.DB_TABLE_SINC;
   		ContatoClienteMontador montador = new ContatoClienteMontador(true); // sinc ligado
   		this.setMontador(montador);
   		List<ContatoCliente> saida = this.getListaSql(sql);
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
		this.insert((ContatoCliente) obj);
	}
	@Override
	public final void dropCreate() {
		this.dropTable();
		this.criaTabela();
	}
}
