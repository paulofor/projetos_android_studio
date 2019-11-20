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
import treinoacademia.modelo.vo.DiaTreinoVo;
import treinoacademia.modelo.vo.FabricaVo;
import treinoacademia.dao.*;
import treinoacademia.dao.montador.*;
import treinoacademia.dao.datasource.DataSourceAplicacao;
import treinoacademia.app.TrataErro;

public abstract class DiaTreinoDBHelperBase extends DBHelperBase
	implements DaoSincronismo // coloquei aqui para evitar impacto de arquitetura
{

    private static final String DB_NAME = "w_alert";
    public static final String DB_TABLE = "dia_treino";
    public static final String DB_TABLE_SINC = "dia_treino_sinc";
    public static final int DB_VERSION = 3;

    protected static final String CLASSNAME = DiaTreinoDBHelperBase.class.getSimpleName();
    protected static final String[] COLS = new String[] { 
        "id_dia_treino"
        ,"data"
        ,"concluido"
		, "id_usuario_s"
	
		, "id_serie_treino_sd"
	
    };

	@Override
	public void erroException(Exception e, DBHelperBase dao) {
		TrataErro.getInstancia().setErro(e, dao);
	}

	protected static final String[] COLS_SINC = new String[] { 
        "id_dia_treino"
        ,"data"
        ,"concluido"
		, "id_usuario_s"
	
		, "id_serie_treino_sd"
		, "operacao_sinc"
    };

    protected SQLiteDatabase db;
    protected final DBOpenHelper dbOpenHelper;

	@Override
	protected MontadorDaoI criaMontadorPadrao() {
		return new DiaTreinoMontador();
	}
	
	protected String getSqlIndices() {
		return "";
	}
	protected String getSqlCreate(){
		return  "CREATE TABLE "
        + DiaTreinoDBHelperBase.DB_TABLE + " ("
        + "  id_dia_treino INTEGER PRIMARY KEY "
        + " , data INTEGER "
        + " , concluido NUMERIC "
		+ " , id_usuario_s INTEGER "
		
		+ " , id_serie_treino_sd INTEGER "
		
		+ getSqlIndices()
        + ");";
	}

	

	public static final String DB_CREATE_SINCRONIZADA = "CREATE TABLE IF NOT EXISTS "
        + DiaTreinoDBHelperBase.DB_TABLE_SINC + " ("
        + "  id_dia_treino INTEGER "
        + " , data INTEGER "
        + " , concluido NUMERIC "
		+ " , id_usuario_s INTEGER "
		
		+ " , id_serie_treino_sd INTEGER "
		
        + ", operacao_sinc TEXT);";


    public static final String DB_CREATE = "CREATE TABLE IF NOT EXISTS "
        + DiaTreinoDBHelperBase.DB_TABLE + " ("
        + "  id_dia_treino INTEGER PRIMARY KEY "
        + " , data INTEGER "
        + " , concluido NUMERIC "
		+ " , id_usuario_s INTEGER "
		
		+ " , id_serie_treino_sd INTEGER "
		
        + ");";
    
    private static final String DB_DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private static final String DB_DROP = "DROP TABLE IF EXISTS " + DB_TABLE;
    private static final String DB_DROP_SINCRONIZADA = "DROP TABLE IF EXISTS " + DB_TABLE_SINC;
    
    private static class DBOpenHelper extends SQLiteOpenHelper {

       

        public DBOpenHelper(final Context context) {
            super(context, DiaTreinoDBHelperBase.DB_NAME, null, DiaTreinoDBHelperBase.DB_VERSION);
        }

        @Override
        public void onCreate(final SQLiteDatabase db) {
            try {
                db.execSQL(DB_CREATE);
            } catch (SQLException e) {
                Log.e(Constants.LOGTAG, DiaTreinoDBHelperBase.CLASSNAME, e);
            }
        }

        @Override
        public void onOpen(final SQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DiaTreinoDBHelperBase.DB_TABLE);
            onCreate(db);
        }
    }

    //
    // end inner classes
    //
    
    // Versao Nova
 	public DiaTreinoDBHelperBase() {
    	this.dbOpenHelper = null;
    	setDataSource(DataSourceAplicacao.getInstancia());
    }
    
   
	
	protected ContentValues montaValores(final DCIObjetoDominio valor) {
		DiaTreino item = (DiaTreino) valor;
		ContentValues valores = new ContentValues();
       	putValor(valores,"id_dia_treino",item.getIdDiaTreino());
       	
       	putValorData(valores,"data",item.getData());
        		
       	putValor(valores,"concluido",item.getConcluido());
       	
       	putValor(valores,"id_usuario_s",item.getIdUsuarioS());
       	
       	putValor(valores,"id_serie_treino_sd",item.getIdSerieTreinoSd());
       	
        return valores;
	}


    // **** Chamadas Diretas Objeto Banco de Dados
    private void registraErroChamadaDireta(Exception e) {
    	Log.e(Constants.LOGTAG, DiaTreinoDBHelperBase.CLASSNAME, e);
    }
   
    public final void insert(final DiaTreino item) {
	    try {
	        getDb().insert(DiaTreinoDBHelperBase.DB_TABLE, null, montaValores(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    public final void update(final DiaTreino item) {
	    try {
	        getDb().update(DiaTreinoDBHelperBase.DB_TABLE, montaValores(item), "id_dia_treino=" + item.getIdDiaTreino(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     // Nao pode ser final o parametro para retornar o objeto com seu Id
    public final void insertSinc(DiaTreino item) {
        try {
        	item.setIdDiaTreino((int)getMaxId() + 1);
        	DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc: " + item.debug());
	        long id = getDb().insert(DiaTreinoDBHelperBase.DB_TABLE, null, montaValores(item));
    	    ((ObjetoSinc)item).setOperacaoSinc("I");
        	getDb().insert(DiaTreinoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     public final void updateSinc(final DiaTreino item) {
        try {
	        DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc: " + item.debug());
	        getDb().update(DiaTreinoDBHelperBase.DB_TABLE, montaValores(item), "id_dia_treino=" + item.getIdDiaTreino(), null);
	        ((ObjetoSinc)item).setOperacaoSinc("A");
	        DiaTreino atual = this.getSincById(item.getId());
	        if (atual==null) {
	        	getDb().insert(DiaTreinoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	        } else {
	        	if ("I".equals(((ObjetoSinc)atual).getOperacaoSinc()))
	        		((ObjetoSinc)item).setOperacaoSinc("I");
	        	getDb().update(DiaTreinoDBHelperBase.DB_TABLE_SINC, montaValoresSinc(item), "id_dia_treino=" + item.getIdDiaTreino(), null);
	        }
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    protected final void delete(final long id) {
        try {
			getDb().delete(DiaTreinoDBHelperBase.DB_TABLE, "id_dia_treino=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    /*
    private void deleteSinc(final long id) {
        try {
			getDb().delete(DiaTreinoDBHelperBase.DB_TABLE_SINC, "id_dia_treino=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
	*/
    public void limpaSinc(final DiaTreino item) {
    	try {
			getDb().delete(DiaTreinoDBHelperBase.DB_TABLE_SINC, "id_dia_treino=" + item.getId(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    
    public void deleteSinc(final DiaTreino item) {
    	try {
	        DCLog.dStack(DCLog.DATABASE_ADM, this, "deleteSinc: " + item.debug());
	        delete(item.getId());
	        ((ObjetoSinc)item).setOperacaoSinc("D");
        	getDb().insert(DiaTreinoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
   
    
	public void criaTabelaSincronizacao() {
	    try {
			getDb().execSQL(DB_CREATE_SINCRONIZADA);
		
			// Dependente
			//getDb().execSQL(ExecucaoItemSerieDBHelperBase.DB_CREATE_SINCRONIZADA);
			
		
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
	}
    public void criaTabela() {
        try {
	        getDb().execSQL(DB_CREATE);
	    
			// Dependente
			//getDb().execSQL(ExecucaoItemSerieDBHelperBase.DB_CREATE);
			
		
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
    public DiaTreino getById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (DiaTreino) getItemQuery(true, DiaTreinoDBHelperBase.DB_TABLE, DiaTreinoDBHelperBase.COLS, "id_dia_treino = " + id + "", null, null, null, null,null);
    }
    
    // Esta com orderBy que pode ser bom para exibicoes em tela
    // mas nao e bom para sincronizacao, pensar em ter um metodo para tela e outro para sinc.
    public List<DiaTreino> getAll() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, null);
    }
    public List<DiaTreino> getAllTela() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, orderByColuna());
    }
    
    private DiaTreino getByRowId(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (DiaTreino) getItemQuery(true, DiaTreinoDBHelperBase.DB_TABLE, DiaTreinoDBHelperBase.COLS, "ROWID = " + id + "", null, null, null, null,null);
    }
    private DiaTreino getSincById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (DiaTreino) getItemQuerySinc(true, DiaTreinoDBHelperBase.DB_TABLE_SINC, DiaTreinoDBHelperBase.COLS_SINC, "id_dia_treino = " + id + "", null, null, null, null,null);
    }
    
    
    public long getMaxId() {
		String sql = "select max(id_dia_treino) from " + DB_TABLE;
		return this.getNumeroSql(sql);
	}
	protected String orderByColuna() {
    	return null;
    }
	
	
	public List<DiaTreino> getPorSerieDiaSerieTreino(Context contexto, long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_serie_treino_sd = " + id, null, null, null, orderByColuna());
	}
	public List<DiaTreino> getPorSerieDiaSerieTreino(long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_serie_treino_sd = " + id, null, null, null, orderByColuna());
	}
	
	
  
  	
  
  
  	
	/*
	public DiaTreino obtemPorExecucaoItemSerieFoiRealizado(long id) {
		string sql;
		sql = "select " + camposOrdenados() +
			" from " + tabelaSelect() +
			innerJoinExecucaoItemSerie_FoiRealizado() + 
			" where id_execucao_item_serie = " + id;
		return (DiaTreino) getObjeto(sql);
	}
	*/
	public static String innerJoinExecucaoItemSerie_FoiRealizado() {
		return " inner join " + ExecucaoItemSerieDBHelperBase.DB_TABLE + " on " + ExecucaoItemSerieDBHelperBase.DB_TABLE + ".id_dia_treino_e = " + DB_TABLE + ".id_dia_treino ";  
	}
	public static String innerJoinSincExecucaoItemSerie_FoiRealizado() {
		return " inner join " + ExecucaoItemSerieDBHelperBase.DB_TABLE_SINC + " on " + ExecucaoItemSerieDBHelperBase.DB_TABLE_SINC + ".id_dia_treino_e = " + DB_TABLE_SINC + ".id_dia_treino ";  
	}
	public static String outerJoinExecucaoItemSerie_FoiRealizado() {
		return " left outer join " + ExecucaoItemSerieDBHelperBase.DB_TABLE + " on " + ExecucaoItemSerieDBHelperBase.DB_TABLE + ".id_dia_treino_e = " + DB_TABLE + ".id_dia_treino ";  
	}
	public static String outerJoinSincExecucaoItemSerie_FoiRealizado() {
		return " left outer join " + ExecucaoItemSerieDBHelperBase.DB_TABLE_SINC + " on " + ExecucaoItemSerieDBHelperBase.DB_TABLE_SINC + ".id_dia_treino_e = " + DB_TABLE_SINC + ".id_dia_treino ";  
	}
 	
	
	
	// Relacionamento onde esse objeto ? chave estrangeira de outro. ????
	
	private boolean _obtemSerieTreino_SerieDia = false;
	public void setObtemSerieTreino_SerieDia() {
		_obtemSerieTreino_SerieDia = true;
	}
	protected String outterJoinSerieTreino_SerieDia() {
		return " left outer join " + SerieTreinoDBHelperBase.DB_TABLE + " on " + SerieTreinoDBHelperBase.DB_TABLE + ".id_serie_treino = " + DB_TABLE + ".id_serie_treino_sd ";  
	}
	public boolean atualizaSerieDiaSerieTreino(long idDiaTreino, long idSerieTreinoSd) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_serie_treino_sd  = " + idSerieTreinoSd +
        " where id_dia_treino = " +  idDiaTreino;
       	//this.executaSql(sql);
       	return true;
	}
	public DiaTreino obtemPorIdsSerieDiaSerieTreino(long idDiaTreino, long idSerieTreinoSd) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_serie_treino_sd = " + idSerieTreinoSd + " and " +
			  " id_dia_treino = " + idDiaTreino;
		return (DiaTreino) this.geObjetoSql(sql);
	}
	
	
	public static String innerJoinSerieTreino_SerieDia() {
		return " inner join " + SerieTreinoDBHelperBase.DB_TABLE + " on " + SerieTreinoDBHelperBase.DB_TABLE + ".id_serie_treino = " + DB_TABLE + ".id_serie_treino_sd ";  
	}
	public static String outerJoinSerieTreino_SerieDia() {
		return " left outer join " + SerieTreinoDBHelperBase.DB_TABLE + " on " + SerieTreinoDBHelperBase.DB_TABLE + ".id_serie_treino = " + DB_TABLE + ".id_serie_treino_sd ";  
	}
	public static String innerJoinSincSerieTreino_SerieDia() {
		return " inner join " + SerieTreinoDBHelperBase.DB_TABLE_SINC + " on " + SerieTreinoDBHelperBase.DB_TABLE_SINC + ".id_serie_treino = " + DB_TABLE_SINC + ".id_serie_treino_sd ";  
	}
	public static String outerJoinSincSerieTreino_SerieDia() {
		return " left outer join " + SerieTreinoDBHelperBase.DB_TABLE_SINC + " on " + SerieTreinoDBHelperBase.DB_TABLE_SINC + ".id_serie_treino = " + DB_TABLE_SINC + ".id_serie_treino_sd ";  
	}
	
 	
	// ********************************************************************	
	
	
	public static String camposOrdenados() 
	{
		return " dia_treino.id_dia_treino " 
		+ " ,dia_treino.data " 
		+ " ,dia_treino.concluido " 
		+ " ,dia_treino.id_usuario_s " 
		+ " ,dia_treino.id_serie_treino_sd " 
		;
	}
	public static String camposOrdenadosSinc() 
	{
		return " dia_treino_sinc.id_dia_treino " 
		+ " ,dia_treino_sinc.data " 
		+ " ,dia_treino_sinc.concluido " 
		+ " ,dia_treino_sinc.id_usuario_s " 
		+ " ,dia_treino_sinc.id_serie_treino_sd " 
		
		+ " ,dia_treino_sinc.operacao_sinc "
		;
	}
	public static String camposOrdenadosAlias(String nomeTabela) 
	{
		return  nomeTabela + ".id_dia_treino " 
		+ " , " +  "DATE_FORMAT(" + nomeTabela + ".data,'%d-%m-%Y') " 
		+ " , " + nomeTabela + ".concluido " 
		+ " , " + nomeTabela + ".id_usuario_s " 
		+ " , " + nomeTabela + ".id_serie_treino_sd " 
		;
	}
	
	protected String camposOrdenadosJoin()
    {
        String saida = camposOrdenados();
		saida += (this._obtemSerieTreino_SerieDia?" , " +SerieTreinoDBHelperBase.camposOrdenados():"");
        return saida;
    }
    
        
    public void limpaObtem()
    {
		_obtemSerieTreino_SerieDia = false;
    }
    
	protected String outterJoinAgrupado()
    {
        String saida = " ";
		saida += (this._obtemSerieTreino_SerieDia? outterJoinSerieTreino_SerieDia() + " ":"");
        return saida;
    }
    protected MontadorDaoI getMontadorAgrupado()
    {
        MontadorDaoComposite montador = new MontadorDaoComposite();
        montador.adicionaMontador(new DiaTreinoMontador(), null);
		if (this._obtemSerieTreino_SerieDia)
            montador.adicionaMontador(new SerieTreinoMontador(), "SerieTreino_SerieDia");
         return montador;
    }
	
    
   	// Poderia passar para classe abstrata.
    public final List<DiaTreino> getAllSinc() throws DaoException {
    	this.setMontador(null);
    	List<DiaTreino> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<DiaTreino>();
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
    public final List<DiaTreino> getAllSincSoAlteracao() throws DaoException {
    	List<DiaTreino> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    		saida = null; // Melhorar aqui !!!!
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<DiaTreino>();
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
	
	public final List<DiaTreino> getAllSincImpl() throws DaoException {
   		String sql = "select " + camposOrdenadosSinc()   
   			+ " from " + this.DB_TABLE_SINC;
   		DiaTreinoMontador montador = new DiaTreinoMontador(true); // sinc ligado
   		this.setMontador(montador);
   		List<DiaTreino> saida = this.getListaSql(sql);
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
		this.insert((DiaTreino) obj);
	}
	@Override
	public final void dropCreate() {
		this.dropTable();
		this.criaTabela();
	}
}
