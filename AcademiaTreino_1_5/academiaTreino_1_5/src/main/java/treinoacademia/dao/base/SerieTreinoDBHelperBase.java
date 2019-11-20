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
import treinoacademia.modelo.vo.SerieTreinoVo;
import treinoacademia.modelo.vo.FabricaVo;
import treinoacademia.dao.*;
import treinoacademia.dao.montador.*;
import treinoacademia.dao.datasource.DataSourceAplicacao;
import treinoacademia.app.TrataErro;

public abstract class SerieTreinoDBHelperBase extends DBHelperBase
	implements DaoSincronismo // coloquei aqui para evitar impacto de arquitetura
{

    private static final String DB_NAME = "w_alert";
    public static final String DB_TABLE = "serie_treino";
    public static final String DB_TABLE_SINC = "serie_treino_sinc";
    public static final int DB_VERSION = 3;

    protected static final String CLASSNAME = SerieTreinoDBHelperBase.class.getSimpleName();
    protected static final String[] COLS = new String[] { 
        "id_serie_treino"
        ,"qtde_execucao"
        ,"ativa"
        ,"data_criacao"
        ,"data_ultima_execucao"
		, "id_usuario_s"
	
    };

	@Override
	public void erroException(Exception e, DBHelperBase dao) {
		TrataErro.getInstancia().setErro(e, dao);
	}

	protected static final String[] COLS_SINC = new String[] { 
        "id_serie_treino"
        ,"qtde_execucao"
        ,"ativa"
        ,"data_criacao"
        ,"data_ultima_execucao"
		, "id_usuario_s"
		, "operacao_sinc"
    };

    protected SQLiteDatabase db;
    protected final DBOpenHelper dbOpenHelper;

	@Override
	protected MontadorDaoI criaMontadorPadrao() {
		return new SerieTreinoMontador();
	}
	
	protected String getSqlIndices() {
		return "";
	}
	protected String getSqlCreate(){
		return  "CREATE TABLE "
        + SerieTreinoDBHelperBase.DB_TABLE + " ("
        + "  id_serie_treino INTEGER PRIMARY KEY "
        + " , qtde_execucao INTEGER "
        + " , ativa NUMERIC "
        + " , data_criacao INTEGER "
        + " , data_ultima_execucao INTEGER "
		+ " , id_usuario_s INTEGER "
		
		+ getSqlIndices()
        + ");";
	}

	

	public static final String DB_CREATE_SINCRONIZADA = "CREATE TABLE IF NOT EXISTS "
        + SerieTreinoDBHelperBase.DB_TABLE_SINC + " ("
        + "  id_serie_treino INTEGER "
        + " , qtde_execucao INTEGER "
        + " , ativa NUMERIC "
        + " , data_criacao INTEGER "
        + " , data_ultima_execucao INTEGER "
		+ " , id_usuario_s INTEGER "
		
        + ", operacao_sinc TEXT);";


    public static final String DB_CREATE = "CREATE TABLE IF NOT EXISTS "
        + SerieTreinoDBHelperBase.DB_TABLE + " ("
        + "  id_serie_treino INTEGER PRIMARY KEY "
        + " , qtde_execucao INTEGER "
        + " , ativa NUMERIC "
        + " , data_criacao INTEGER "
        + " , data_ultima_execucao INTEGER "
		+ " , id_usuario_s INTEGER "
		
        + ");";
    
    private static final String DB_DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private static final String DB_DROP = "DROP TABLE IF EXISTS " + DB_TABLE;
    private static final String DB_DROP_SINCRONIZADA = "DROP TABLE IF EXISTS " + DB_TABLE_SINC;
    
    private static class DBOpenHelper extends SQLiteOpenHelper {

       

        public DBOpenHelper(final Context context) {
            super(context, SerieTreinoDBHelperBase.DB_NAME, null, SerieTreinoDBHelperBase.DB_VERSION);
        }

        @Override
        public void onCreate(final SQLiteDatabase db) {
            try {
                db.execSQL(DB_CREATE);
            } catch (SQLException e) {
                Log.e(Constants.LOGTAG, SerieTreinoDBHelperBase.CLASSNAME, e);
            }
        }

        @Override
        public void onOpen(final SQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + SerieTreinoDBHelperBase.DB_TABLE);
            onCreate(db);
        }
    }

    //
    // end inner classes
    //
    
    // Versao Nova
 	public SerieTreinoDBHelperBase() {
    	this.dbOpenHelper = null;
    	setDataSource(DataSourceAplicacao.getInstancia());
    }
    
   
	
	protected ContentValues montaValores(final DCIObjetoDominio valor) {
		SerieTreino item = (SerieTreino) valor;
		ContentValues valores = new ContentValues();
       	putValor(valores,"id_serie_treino",item.getIdSerieTreino());
       	
       	putValor(valores,"qtde_execucao",item.getQtdeExecucao());
       	
       	putValor(valores,"ativa",item.getAtiva());
       	
       	putValorData(valores,"data_criacao",item.getDataCriacao());
        		
       	putValorData(valores,"data_ultima_execucao",item.getDataUltimaExecucao());
        		
       	putValor(valores,"id_usuario_s",item.getIdUsuarioS());
       	
        return valores;
	}


    // **** Chamadas Diretas Objeto Banco de Dados
    private void registraErroChamadaDireta(Exception e) {
    	Log.e(Constants.LOGTAG, SerieTreinoDBHelperBase.CLASSNAME, e);
    }
   
    public final void insert(final SerieTreino item) {
	    try {
	        getDb().insert(SerieTreinoDBHelperBase.DB_TABLE, null, montaValores(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    public final void update(final SerieTreino item) {
	    try {
	        getDb().update(SerieTreinoDBHelperBase.DB_TABLE, montaValores(item), "id_serie_treino=" + item.getIdSerieTreino(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     // Nao pode ser final o parametro para retornar o objeto com seu Id
    public final void insertSinc(SerieTreino item) {
        try {
        	item.setIdSerieTreino((int)getMaxId() + 1);
        	DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc: " + item.debug());
	        long id = getDb().insert(SerieTreinoDBHelperBase.DB_TABLE, null, montaValores(item));
    	    ((ObjetoSinc)item).setOperacaoSinc("I");
        	getDb().insert(SerieTreinoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     public final void updateSinc(final SerieTreino item) {
        try {
	        DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc: " + item.debug());
	        getDb().update(SerieTreinoDBHelperBase.DB_TABLE, montaValores(item), "id_serie_treino=" + item.getIdSerieTreino(), null);
	        ((ObjetoSinc)item).setOperacaoSinc("A");
	        SerieTreino atual = this.getSincById(item.getId());
	        if (atual==null) {
	        	getDb().insert(SerieTreinoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	        } else {
	        	if ("I".equals(((ObjetoSinc)atual).getOperacaoSinc()))
	        		((ObjetoSinc)item).setOperacaoSinc("I");
	        	getDb().update(SerieTreinoDBHelperBase.DB_TABLE_SINC, montaValoresSinc(item), "id_serie_treino=" + item.getIdSerieTreino(), null);
	        }
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    protected final void delete(final long id) {
        try {
			getDb().delete(SerieTreinoDBHelperBase.DB_TABLE, "id_serie_treino=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    /*
    private void deleteSinc(final long id) {
        try {
			getDb().delete(SerieTreinoDBHelperBase.DB_TABLE_SINC, "id_serie_treino=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
	*/
    public void limpaSinc(final SerieTreino item) {
    	try {
			getDb().delete(SerieTreinoDBHelperBase.DB_TABLE_SINC, "id_serie_treino=" + item.getId(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    
    public void deleteSinc(final SerieTreino item) {
    	try {
	        DCLog.dStack(DCLog.DATABASE_ADM, this, "deleteSinc: " + item.debug());
	        delete(item.getId());
	        ((ObjetoSinc)item).setOperacaoSinc("D");
        	getDb().insert(SerieTreinoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
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
    public SerieTreino getById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (SerieTreino) getItemQuery(true, SerieTreinoDBHelperBase.DB_TABLE, SerieTreinoDBHelperBase.COLS, "id_serie_treino = " + id + "", null, null, null, null,null);
    }
    
    // Esta com orderBy que pode ser bom para exibicoes em tela
    // mas nao e bom para sincronizacao, pensar em ter um metodo para tela e outro para sinc.
    public List<SerieTreino> getAll() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, null);
    }
    public List<SerieTreino> getAllTela() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, orderByColuna());
    }
    
    private SerieTreino getByRowId(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (SerieTreino) getItemQuery(true, SerieTreinoDBHelperBase.DB_TABLE, SerieTreinoDBHelperBase.COLS, "ROWID = " + id + "", null, null, null, null,null);
    }
    private SerieTreino getSincById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (SerieTreino) getItemQuerySinc(true, SerieTreinoDBHelperBase.DB_TABLE_SINC, SerieTreinoDBHelperBase.COLS_SINC, "id_serie_treino = " + id + "", null, null, null, null,null);
    }
    
    
    public long getMaxId() {
		String sql = "select max(id_serie_treino) from " + DB_TABLE;
		return this.getNumeroSql(sql);
	}
	protected String orderByColuna() {
    	return null;
    }
	
  
  	
  
  
  	
	/*
	public SerieTreino obtemPorItemSeriePossui(long id) {
		string sql;
		sql = "select " + camposOrdenados() +
			" from " + tabelaSelect() +
			innerJoinItemSerie_Possui() + 
			" where id_item_serie = " + id;
		return (SerieTreino) getObjeto(sql);
	}
	*/
	public static String innerJoinItemSerie_Possui() {
		return " inner join " + ItemSerieDBHelperBase.DB_TABLE + " on " + ItemSerieDBHelperBase.DB_TABLE + ".id_serie_treino_pa = " + DB_TABLE + ".id_serie_treino ";  
	}
	public static String innerJoinSincItemSerie_Possui() {
		return " inner join " + ItemSerieDBHelperBase.DB_TABLE_SINC + " on " + ItemSerieDBHelperBase.DB_TABLE_SINC + ".id_serie_treino_pa = " + DB_TABLE_SINC + ".id_serie_treino ";  
	}
	public static String outerJoinItemSerie_Possui() {
		return " left outer join " + ItemSerieDBHelperBase.DB_TABLE + " on " + ItemSerieDBHelperBase.DB_TABLE + ".id_serie_treino_pa = " + DB_TABLE + ".id_serie_treino ";  
	}
	public static String outerJoinSincItemSerie_Possui() {
		return " left outer join " + ItemSerieDBHelperBase.DB_TABLE_SINC + " on " + ItemSerieDBHelperBase.DB_TABLE_SINC + ".id_serie_treino_pa = " + DB_TABLE_SINC + ".id_serie_treino ";  
	}
 	
	/*
	public SerieTreino obtemPorDiaTreinoSerieDia(long id) {
		string sql;
		sql = "select " + camposOrdenados() +
			" from " + tabelaSelect() +
			innerJoinDiaTreino_SerieDia() + 
			" where id_dia_treino = " + id;
		return (SerieTreino) getObjeto(sql);
	}
	*/
	public static String innerJoinDiaTreino_SerieDia() {
		return " inner join " + DiaTreinoDBHelperBase.DB_TABLE + " on " + DiaTreinoDBHelperBase.DB_TABLE + ".id_serie_treino_sd = " + DB_TABLE + ".id_serie_treino ";  
	}
	public static String innerJoinSincDiaTreino_SerieDia() {
		return " inner join " + DiaTreinoDBHelperBase.DB_TABLE_SINC + " on " + DiaTreinoDBHelperBase.DB_TABLE_SINC + ".id_serie_treino_sd = " + DB_TABLE_SINC + ".id_serie_treino ";  
	}
	public static String outerJoinDiaTreino_SerieDia() {
		return " left outer join " + DiaTreinoDBHelperBase.DB_TABLE + " on " + DiaTreinoDBHelperBase.DB_TABLE + ".id_serie_treino_sd = " + DB_TABLE + ".id_serie_treino ";  
	}
	public static String outerJoinSincDiaTreino_SerieDia() {
		return " left outer join " + DiaTreinoDBHelperBase.DB_TABLE_SINC + " on " + DiaTreinoDBHelperBase.DB_TABLE_SINC + ".id_serie_treino_sd = " + DB_TABLE_SINC + ".id_serie_treino ";  
	}
 	
	
	
	// Relacionamento onde esse objeto ? chave estrangeira de outro. ????
	
	// ********************************************************************	
	
	
	public static String camposOrdenados() 
	{
		return " serie_treino.id_serie_treino " 
		+ " ,serie_treino.qtde_execucao " 
		+ " ,serie_treino.ativa " 
		+ " ,serie_treino.data_criacao " 
		+ " ,serie_treino.data_ultima_execucao " 
		+ " ,serie_treino.id_usuario_s " 
		;
	}
	public static String camposOrdenadosSinc() 
	{
		return " serie_treino_sinc.id_serie_treino " 
		+ " ,serie_treino_sinc.qtde_execucao " 
		+ " ,serie_treino_sinc.ativa " 
		+ " ,serie_treino_sinc.data_criacao " 
		+ " ,serie_treino_sinc.data_ultima_execucao " 
		+ " ,serie_treino_sinc.id_usuario_s " 
		
		+ " ,serie_treino_sinc.operacao_sinc "
		;
	}
	public static String camposOrdenadosAlias(String nomeTabela) 
	{
		return  nomeTabela + ".id_serie_treino " 
		+ " , " + nomeTabela + ".qtde_execucao " 
		+ " , " + nomeTabela + ".ativa " 
		+ " , " +  "DATE_FORMAT(" + nomeTabela + ".data_criacao,'%d-%m-%Y') " 
		+ " , " +  "DATE_FORMAT(" + nomeTabela + ".data_ultima_execucao,'%d-%m-%Y') " 
		+ " , " + nomeTabela + ".id_usuario_s " 
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
        montador.adicionaMontador(new SerieTreinoMontador(), null);
         return montador;
    }
	
    
   	// Poderia passar para classe abstrata.
    public final List<SerieTreino> getAllSinc() throws DaoException {
    	this.setMontador(null);
    	List<SerieTreino> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<SerieTreino>();
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
    public final List<SerieTreino> getAllSincSoAlteracao() throws DaoException {
    	List<SerieTreino> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    		saida = null; // Melhorar aqui !!!!
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<SerieTreino>();
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
	
	public final List<SerieTreino> getAllSincImpl() throws DaoException {
   		String sql = "select " + camposOrdenadosSinc()   
   			+ " from " + this.DB_TABLE_SINC;
   		SerieTreinoMontador montador = new SerieTreinoMontador(true); // sinc ligado
   		this.setMontador(montador);
   		List<SerieTreino> saida = this.getListaSql(sql);
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
		this.insert((SerieTreino) obj);
	}
	@Override
	public final void dropCreate() {
		this.dropTable();
		this.criaTabela();
	}
}
