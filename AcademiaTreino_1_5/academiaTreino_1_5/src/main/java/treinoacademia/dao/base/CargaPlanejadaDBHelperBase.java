package treinoacademia.dao.base;


import java.io.PrintWriter;
import java.io.StringWriter;
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
import treinoacademia.modelo.*;
import treinoacademia.modelo.vo.CargaPlanejadaVo;
import treinoacademia.modelo.vo.FabricaVo;
import treinoacademia.dao.*;
import treinoacademia.dao.montador.*;
import treinoacademia.dao.datasource.DataSourceAplicacao;
import treinoacademia.app.TrataErro;

public abstract class CargaPlanejadaDBHelperBase extends DBHelperBase
	implements DaoSincronismo // coloquei aqui para evitar impacto de arquitetura
{

    private static final String DB_NAME = "w_alert";
    public static final String DB_TABLE = "carga_planejada";
    public static final String DB_TABLE_SINC = "carga_planejada_sinc";
    public static final int DB_VERSION = 3;

    protected static final String CLASSNAME = CargaPlanejadaDBHelperBase.class.getSimpleName();
    protected static final String[] COLS = new String[] { 
        "id_carga_planejada"
        ,"valor_carga"
        ,"data_inicio"
        ,"data_final"
        ,"ativa"
        ,"quantidade_repeticao"
        ,"ordem_repeticao"
		, "id_item_serie_ra"
	
		, "id_usuario_s"
	
    };

	@Override
	public void erroException(Exception e, DBHelperBase dao) {
		TrataErro.getInstancia().setErro(e, dao);
	}

	protected static final String[] COLS_SINC = new String[] { 
        "id_carga_planejada"
        ,"valor_carga"
        ,"data_inicio"
        ,"data_final"
        ,"ativa"
        ,"quantidade_repeticao"
        ,"ordem_repeticao"
		, "id_item_serie_ra"
	
		, "id_usuario_s"
		, "operacao_sinc"
    };

    protected SQLiteDatabase db;
    protected final DBOpenHelper dbOpenHelper;

	@Override
	protected MontadorDaoI criaMontadorPadrao() {
		return new CargaPlanejadaMontador();
	}
	
	protected String getSqlIndices() {
		return "";
	}
	protected String getSqlCreate(){
		return  "CREATE TABLE "
        + CargaPlanejadaDBHelperBase.DB_TABLE + " ("
        + "  id_carga_planejada INTEGER PRIMARY KEY "
        + " , valor_carga REAL "
        + " , data_inicio INTEGER "
        + " , data_final INTEGER "
        + " , ativa NUMERIC "
        + " , quantidade_repeticao INTEGER "
        + " , ordem_repeticao INTEGER "
		+ " , id_item_serie_ra INTEGER "
		
		+ " , id_usuario_s INTEGER "
		
		+ getSqlIndices()
        + ");";
	}

	

	public static final String DB_CREATE_SINCRONIZADA = "CREATE TABLE IF NOT EXISTS "
        + CargaPlanejadaDBHelperBase.DB_TABLE_SINC + " ("
        + "  id_carga_planejada INTEGER "
        + " , valor_carga REAL "
        + " , data_inicio INTEGER "
        + " , data_final INTEGER "
        + " , ativa NUMERIC "
        + " , quantidade_repeticao INTEGER "
        + " , ordem_repeticao INTEGER "
		+ " , id_item_serie_ra INTEGER "
		
		+ " , id_usuario_s INTEGER "
		
        + ", operacao_sinc TEXT);";


    public static final String DB_CREATE = "CREATE TABLE IF NOT EXISTS "
        + CargaPlanejadaDBHelperBase.DB_TABLE + " ("
        + "  id_carga_planejada INTEGER PRIMARY KEY "
        + " , valor_carga REAL "
        + " , data_inicio INTEGER "
        + " , data_final INTEGER "
        + " , ativa NUMERIC "
        + " , quantidade_repeticao INTEGER "
        + " , ordem_repeticao INTEGER "
		+ " , id_item_serie_ra INTEGER "
		
		+ " , id_usuario_s INTEGER "
		
        + ");";
    
    private static final String DB_DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private static final String DB_DROP = "DROP TABLE IF EXISTS " + DB_TABLE;
    private static final String DB_DROP_SINCRONIZADA = "DROP TABLE IF EXISTS " + DB_TABLE_SINC;
    
    private static class DBOpenHelper extends SQLiteOpenHelper {

       

        public DBOpenHelper(final Context context) {
            super(context, CargaPlanejadaDBHelperBase.DB_NAME, null, CargaPlanejadaDBHelperBase.DB_VERSION);
        }

        @Override
        public void onCreate(final SQLiteDatabase db) {
            try {
                db.execSQL(DB_CREATE);
            } catch (SQLException e) {
                Log.e(Constants.LOGTAG, CargaPlanejadaDBHelperBase.CLASSNAME, e);
            }
        }

        @Override
        public void onOpen(final SQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + CargaPlanejadaDBHelperBase.DB_TABLE);
            onCreate(db);
        }
    }

    //
    // end inner classes
    //
    
    // Versao Nova
 	public CargaPlanejadaDBHelperBase() {
    	this.dbOpenHelper = null;
    	setDataSource(DataSourceAplicacao.getInstancia());
    }
    
   
	
	protected ContentValues montaValores(final DCIObjetoDominio valor) {
		CargaPlanejada item = (CargaPlanejada) valor;
		ContentValues valores = new ContentValues();
       	putValor(valores,"id_carga_planejada",item.getIdCargaPlanejada());
       	
       	putValor(valores,"valor_carga",item.getValorCarga());
       	
       	putValorData(valores,"data_inicio",item.getDataInicio());
        		
       	putValorData(valores,"data_final",item.getDataFinal());
        		
       	putValor(valores,"ativa",item.getAtiva());
       	
       	putValor(valores,"quantidade_repeticao",item.getQuantidadeRepeticao());
       	
       	putValor(valores,"ordem_repeticao",item.getOrdemRepeticao());
       	
       	putValor(valores,"id_item_serie_ra",item.getIdItemSerieRa());
       	
       	putValor(valores,"id_usuario_s",item.getIdUsuarioS());
       	
        return valores;
	}


    // **** Chamadas Diretas Objeto Banco de Dados
    private void registraErroChamadaDireta(Exception e) {
    	Log.e(Constants.LOGTAG, CargaPlanejadaDBHelperBase.CLASSNAME, e);
    }
   
    public final void insert(final CargaPlanejada item) {
	    try {
	        getDb().insert(CargaPlanejadaDBHelperBase.DB_TABLE, null, montaValores(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    public final void update(final CargaPlanejada item) {
	    try {
	        getDb().update(CargaPlanejadaDBHelperBase.DB_TABLE, montaValores(item), "id_carga_planejada=" + item.getIdCargaPlanejada(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     // Nao pode ser final o parametro para retornar o objeto com seu Id
    public final void insertSinc(CargaPlanejada item) {
        try {
        	item.setIdCargaPlanejada((int)getMaxId() + 1);
        	DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc: " + item.debug());
	        long id = getDb().insert(CargaPlanejadaDBHelperBase.DB_TABLE, null, montaValores(item));
    	    ((ObjetoSinc)item).setOperacaoSinc("I");
        	getDb().insert(CargaPlanejadaDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     public final void updateSinc(final CargaPlanejada item) {
        try {
	        DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc: " + item.debug());
	        getDb().update(CargaPlanejadaDBHelperBase.DB_TABLE, montaValores(item), "id_carga_planejada=" + item.getIdCargaPlanejada(), null);
	        ((ObjetoSinc)item).setOperacaoSinc("A");
	        CargaPlanejada atual = this.getSincById(item.getId());
	        if (atual==null) {
	        	getDb().insert(CargaPlanejadaDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	        } else {
	        	if ("I".equals(((ObjetoSinc)atual).getOperacaoSinc()))
	        		((ObjetoSinc)item).setOperacaoSinc("I");
	        	getDb().update(CargaPlanejadaDBHelperBase.DB_TABLE_SINC, montaValoresSinc(item), "id_carga_planejada=" + item.getIdCargaPlanejada(), null);
	        }
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    protected final void delete(final long id) {
        try {
			getDb().delete(CargaPlanejadaDBHelperBase.DB_TABLE, "id_carga_planejada=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    /*
    private void deleteSinc(final long id) {
        try {
			getDb().delete(CargaPlanejadaDBHelperBase.DB_TABLE_SINC, "id_carga_planejada=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
	*/
    public void limpaSinc(final CargaPlanejada item) {
    	try {
			getDb().delete(CargaPlanejadaDBHelperBase.DB_TABLE_SINC, "id_carga_planejada=" + item.getId(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    
    public void deleteSinc(final CargaPlanejada item) {
    	try {
	        DCLog.dStack(DCLog.DATABASE_ADM, this, "deleteSinc: " + item.debug());
	        delete(item.getId());
	        ((ObjetoSinc)item).setOperacaoSinc("D");
        	getDb().insert(CargaPlanejadaDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
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
    public CargaPlanejada getById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (CargaPlanejada) getItemQuery(true, CargaPlanejadaDBHelperBase.DB_TABLE, CargaPlanejadaDBHelperBase.COLS, "id_carga_planejada = " + id + "", null, null, null, null,null);
    }
    
    // Esta com orderBy que pode ser bom para exibicoes em tela
    // mas nao e bom para sincronizacao, pensar em ter um metodo para tela e outro para sinc.
    public List<CargaPlanejada> getAll() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, null);
    }
    public List<CargaPlanejada> getAllTela() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, orderByColuna());
    }
    
    private CargaPlanejada getByRowId(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (CargaPlanejada) getItemQuery(true, CargaPlanejadaDBHelperBase.DB_TABLE, CargaPlanejadaDBHelperBase.COLS, "ROWID = " + id + "", null, null, null, null,null);
    }
    private CargaPlanejada getSincById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (CargaPlanejada) getItemQuerySinc(true, CargaPlanejadaDBHelperBase.DB_TABLE_SINC, CargaPlanejadaDBHelperBase.COLS_SINC, "id_carga_planejada = " + id + "", null, null, null, null,null);
    }
    
    
    public long getMaxId() {
		String sql = "select max(id_carga_planejada) from " + DB_TABLE;
		return this.getNumeroSql(sql);
	}
	protected String orderByColuna() {
    	return null;
    }
	
	
	public List<CargaPlanejada> getPorReferenteAItemSerie(Context contexto, long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_item_serie_ra = " + id, null, null, null, orderByColuna());
	}
	public List<CargaPlanejada> getPorReferenteAItemSerie(long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_item_serie_ra = " + id, null, null, null, orderByColuna());
	}
	
	
  
  	
  
  
  	
	
	
	// Relacionamento onde esse objeto ? chave estrangeira de outro. ????
	
	private boolean _obtemItemSerie_ReferenteA = false;
	public void setObtemItemSerie_ReferenteA() {
		_obtemItemSerie_ReferenteA = true;
	}
	protected String outterJoinItemSerie_ReferenteA() {
		return " left outer join " + ItemSerieDBHelperBase.DB_TABLE + " on " + ItemSerieDBHelperBase.DB_TABLE + ".id_item_serie = " + DB_TABLE + ".id_item_serie_ra ";  
	}
	public boolean atualizaReferenteAItemSerie(long idCargaPlanejada, long idItemSerieRa) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_item_serie_ra  = " + idItemSerieRa +
        " where id_carga_planejada = " +  idCargaPlanejada;
       	//this.executaSql(sql);
       	return true;
	}
	public CargaPlanejada obtemPorIdsReferenteAItemSerie(long idCargaPlanejada, long idItemSerieRa) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_item_serie_ra = " + idItemSerieRa + " and " +
			  " id_carga_planejada = " + idCargaPlanejada;
		return (CargaPlanejada) this.geObjetoSql(sql);
	}
	
	
	public static String innerJoinItemSerie_ReferenteA() {
		return " inner join " + ItemSerieDBHelperBase.DB_TABLE + " on " + ItemSerieDBHelperBase.DB_TABLE + ".id_item_serie = " + DB_TABLE + ".id_item_serie_ra ";  
	}
	public static String outerJoinItemSerie_ReferenteA() {
		return " left outer join " + ItemSerieDBHelperBase.DB_TABLE + " on " + ItemSerieDBHelperBase.DB_TABLE + ".id_item_serie = " + DB_TABLE + ".id_item_serie_ra ";  
	}
	public static String innerJoinSincItemSerie_ReferenteA() {
		return " inner join " + ItemSerieDBHelperBase.DB_TABLE_SINC + " on " + ItemSerieDBHelperBase.DB_TABLE_SINC + ".id_item_serie = " + DB_TABLE_SINC + ".id_item_serie_ra ";  
	}
	public static String outerJoinSincItemSerie_ReferenteA() {
		return " left outer join " + ItemSerieDBHelperBase.DB_TABLE_SINC + " on " + ItemSerieDBHelperBase.DB_TABLE_SINC + ".id_item_serie = " + DB_TABLE_SINC + ".id_item_serie_ra ";  
	}
	
 	
	// ********************************************************************	
	
	
	public static String camposOrdenados() 
	{
		return " carga_planejada.id_carga_planejada " 
		+ " ,carga_planejada.valor_carga " 
		+ " ,carga_planejada.data_inicio " 
		+ " ,carga_planejada.data_final " 
		+ " ,carga_planejada.ativa " 
		+ " ,carga_planejada.quantidade_repeticao " 
		+ " ,carga_planejada.ordem_repeticao " 
		+ " ,carga_planejada.id_item_serie_ra " 
		+ " ,carga_planejada.id_usuario_s " 
		;
	}
	public static String camposOrdenadosSinc() 
	{
		return " carga_planejada_sinc.id_carga_planejada " 
		+ " ,carga_planejada_sinc.valor_carga " 
		+ " ,carga_planejada_sinc.data_inicio " 
		+ " ,carga_planejada_sinc.data_final " 
		+ " ,carga_planejada_sinc.ativa " 
		+ " ,carga_planejada_sinc.quantidade_repeticao " 
		+ " ,carga_planejada_sinc.ordem_repeticao " 
		+ " ,carga_planejada_sinc.id_item_serie_ra " 
		+ " ,carga_planejada_sinc.id_usuario_s " 
		
		+ " ,carga_planejada_sinc.operacao_sinc "
		;
	}
	public static String camposOrdenadosAlias(String nomeTabela) 
	{
		return  nomeTabela + ".id_carga_planejada " 
		+ " , " + nomeTabela + ".valor_carga " 
		+ " , " +  "DATE_FORMAT(" + nomeTabela + ".data_inicio,'%d-%m-%Y') " 
		+ " , " +  "DATE_FORMAT(" + nomeTabela + ".data_final,'%d-%m-%Y') " 
		+ " , " + nomeTabela + ".ativa " 
		+ " , " + nomeTabela + ".quantidade_repeticao " 
		+ " , " + nomeTabela + ".ordem_repeticao " 
		+ " , " + nomeTabela + ".id_item_serie_ra " 
		+ " , " + nomeTabela + ".id_usuario_s " 
		;
	}
	
	protected String camposOrdenadosJoin()
    {
        String saida = camposOrdenados();
		saida += (this._obtemItemSerie_ReferenteA?" , " +ItemSerieDBHelperBase.camposOrdenados():"");
        return saida;
    }
    
        
    public void limpaObtem()
    {
		_obtemItemSerie_ReferenteA = false;
    }
    
	protected String outterJoinAgrupado()
    {
        String saida = " ";
		saida += (this._obtemItemSerie_ReferenteA? outterJoinItemSerie_ReferenteA() + " ":"");
        return saida;
    }
    protected MontadorDaoI getMontadorAgrupado()
    {
        MontadorDaoComposite montador = new MontadorDaoComposite();
        montador.adicionaMontador(new CargaPlanejadaMontador(), null);
		if (this._obtemItemSerie_ReferenteA)
            montador.adicionaMontador(new ItemSerieMontador(), "ItemSerie_ReferenteA");
         return montador;
    }
	
    
   	// Poderia passar para classe abstrata.
    public final List<CargaPlanejada> getAllSinc() throws DaoException {
    	this.setMontador(null);
    	List<CargaPlanejada> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<CargaPlanejada>();
    		}
    		DCLog.e(DCLog.DATABASE_ERRO, this, e);
    		TrataErro.getInstancia().setErro(e, this);
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
    public final List<CargaPlanejada> getAllSincSoAlteracao() throws DaoException {
    	List<CargaPlanejada> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    		saida = null; // Melhorar aqui !!!!
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<CargaPlanejada>();
    		}
    		DCLog.e(DCLog.DATABASE_ERRO, this, e);
    		TrataErro.getInstancia().setErro(e, this);
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
	
	public final List<CargaPlanejada> getAllSincImpl() throws DaoException {
   		String sql = "select " + camposOrdenadosSinc()   
   			+ " from " + this.DB_TABLE_SINC;
   		CargaPlanejadaMontador montador = new CargaPlanejadaMontador(true); // sinc ligado
   		this.setMontador(montador);
   		List<CargaPlanejada> saida = this.getListaSql(sql);
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
		this.insert((CargaPlanejada) obj);
	}
	@Override
	public final void dropCreate() {
		this.dropTable();
		this.criaTabela();
	}
}
