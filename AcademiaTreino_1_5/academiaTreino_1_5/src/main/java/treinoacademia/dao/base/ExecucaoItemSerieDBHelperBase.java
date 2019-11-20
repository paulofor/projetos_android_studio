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
import treinoacademia.modelo.vo.ExecucaoItemSerieVo;
import treinoacademia.modelo.vo.FabricaVo;
import treinoacademia.dao.*;
import treinoacademia.dao.montador.*;
import treinoacademia.dao.datasource.DataSourceAplicacao;
import treinoacademia.app.TrataErro;

public abstract class ExecucaoItemSerieDBHelperBase extends DBHelperBase
	implements DaoSincronismo // coloquei aqui para evitar impacto de arquitetura
{

    private static final String DB_NAME = "w_alert";
    public static final String DB_TABLE = "execucao_item_serie";
    public static final String DB_TABLE_SINC = "execucao_item_serie_sinc";
    public static final int DB_VERSION = 3;

    protected static final String CLASSNAME = ExecucaoItemSerieDBHelperBase.class.getSimpleName();
    protected static final String[] COLS = new String[] { 
        "id_execucao_item_serie"
        ,"data_hora_inicio"
        ,"carga_utilizada"
        ,"sucesso_repeticoes"
        ,"numero_serie"
        ,"data_hora_finalizacao"
        ,"quantidade_repeticao"
		, "id_item_serie_ra"
	
		, "id_dia_treino_e"
	
		, "id_exercicio_ra"
	
		, "id_usuario_s"
	
    };

	@Override
	public void erroException(Exception e, DBHelperBase dao) {
		TrataErro.getInstancia().setErro(e, dao);
	}

	protected static final String[] COLS_SINC = new String[] { 
        "id_execucao_item_serie"
        ,"data_hora_inicio"
        ,"carga_utilizada"
        ,"sucesso_repeticoes"
        ,"numero_serie"
        ,"data_hora_finalizacao"
        ,"quantidade_repeticao"
		, "id_item_serie_ra"
	
		, "id_dia_treino_e"
	
		, "id_exercicio_ra"
	
		, "id_usuario_s"
		, "operacao_sinc"
    };

    protected SQLiteDatabase db;
    protected final DBOpenHelper dbOpenHelper;

	@Override
	protected MontadorDaoI criaMontadorPadrao() {
		return new ExecucaoItemSerieMontador();
	}
	
	protected String getSqlIndices() {
		return "";
	}
	protected String getSqlCreate(){
		return  "CREATE TABLE "
        + ExecucaoItemSerieDBHelperBase.DB_TABLE + " ("
        + "  id_execucao_item_serie INTEGER PRIMARY KEY "
        + " , data_hora_inicio INTEGER "
        + " , carga_utilizada REAL "
        + " , sucesso_repeticoes NUMERIC "
        + " , numero_serie INTEGER "
        + " , data_hora_finalizacao INTEGER "
        + " , quantidade_repeticao INTEGER "
		+ " , id_item_serie_ra INTEGER "
		
		+ " , id_dia_treino_e INTEGER "
		
		+ " , id_exercicio_ra INTEGER "
		
		+ " , id_usuario_s INTEGER "
		
		+ getSqlIndices()
        + ");";
	}

	

	public static final String DB_CREATE_SINCRONIZADA = "CREATE TABLE IF NOT EXISTS "
        + ExecucaoItemSerieDBHelperBase.DB_TABLE_SINC + " ("
        + "  id_execucao_item_serie INTEGER "
        + " , data_hora_inicio INTEGER "
        + " , carga_utilizada REAL "
        + " , sucesso_repeticoes NUMERIC "
        + " , numero_serie INTEGER "
        + " , data_hora_finalizacao INTEGER "
        + " , quantidade_repeticao INTEGER "
		+ " , id_item_serie_ra INTEGER "
		
		+ " , id_dia_treino_e INTEGER "
		
		+ " , id_exercicio_ra INTEGER "
		
		+ " , id_usuario_s INTEGER "
		
        + ", operacao_sinc TEXT);";


    public static final String DB_CREATE = "CREATE TABLE IF NOT EXISTS "
        + ExecucaoItemSerieDBHelperBase.DB_TABLE + " ("
        + "  id_execucao_item_serie INTEGER PRIMARY KEY "
        + " , data_hora_inicio INTEGER "
        + " , carga_utilizada REAL "
        + " , sucesso_repeticoes NUMERIC "
        + " , numero_serie INTEGER "
        + " , data_hora_finalizacao INTEGER "
        + " , quantidade_repeticao INTEGER "
		+ " , id_item_serie_ra INTEGER "
		
		+ " , id_dia_treino_e INTEGER "
		
		+ " , id_exercicio_ra INTEGER "
		
		+ " , id_usuario_s INTEGER "
		
        + ");";
    
    private static final String DB_DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private static final String DB_DROP = "DROP TABLE IF EXISTS " + DB_TABLE;
    private static final String DB_DROP_SINCRONIZADA = "DROP TABLE IF EXISTS " + DB_TABLE_SINC;
    
    private static class DBOpenHelper extends SQLiteOpenHelper {

       

        public DBOpenHelper(final Context context) {
            super(context, ExecucaoItemSerieDBHelperBase.DB_NAME, null, ExecucaoItemSerieDBHelperBase.DB_VERSION);
        }

        @Override
        public void onCreate(final SQLiteDatabase db) {
            try {
                db.execSQL(DB_CREATE);
            } catch (SQLException e) {
                Log.e(Constants.LOGTAG, ExecucaoItemSerieDBHelperBase.CLASSNAME, e);
            }
        }

        @Override
        public void onOpen(final SQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + ExecucaoItemSerieDBHelperBase.DB_TABLE);
            onCreate(db);
        }
    }

    //
    // end inner classes
    //
    
    // Versao Nova
 	public ExecucaoItemSerieDBHelperBase() {
    	this.dbOpenHelper = null;
    	setDataSource(DataSourceAplicacao.getInstancia());
    }
    
   
	
	protected ContentValues montaValores(final DCIObjetoDominio valor) {
		ExecucaoItemSerie item = (ExecucaoItemSerie) valor;
		ContentValues valores = new ContentValues();
       	putValor(valores,"id_execucao_item_serie",item.getIdExecucaoItemSerie());
       	
	 	putValorDataHora(valores,"data_hora_inicio",item.getDataHoraInicio());
        		
       	putValor(valores,"carga_utilizada",item.getCargaUtilizada());
       	
       	putValor(valores,"sucesso_repeticoes",item.getSucessoRepeticoes());
       	
       	putValor(valores,"numero_serie",item.getNumeroSerie());
       	
	 	putValorDataHora(valores,"data_hora_finalizacao",item.getDataHoraFinalizacao());
        		
       	putValor(valores,"quantidade_repeticao",item.getQuantidadeRepeticao());
       	
       	putValor(valores,"id_item_serie_ra",item.getIdItemSerieRa());
       	
       	putValor(valores,"id_dia_treino_e",item.getIdDiaTreinoE());
       	
       	putValor(valores,"id_exercicio_ra",item.getIdExercicioRa());
       	
       	putValor(valores,"id_usuario_s",item.getIdUsuarioS());
       	
        return valores;
	}


    // **** Chamadas Diretas Objeto Banco de Dados
    private void registraErroChamadaDireta(Exception e) {
    	Log.e(Constants.LOGTAG, ExecucaoItemSerieDBHelperBase.CLASSNAME, e);
    }
   
    public final void insert(final ExecucaoItemSerie item) {
	    try {
	        getDb().insert(ExecucaoItemSerieDBHelperBase.DB_TABLE, null, montaValores(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    public final void update(final ExecucaoItemSerie item) {
	    try {
	        getDb().update(ExecucaoItemSerieDBHelperBase.DB_TABLE, montaValores(item), "id_execucao_item_serie=" + item.getIdExecucaoItemSerie(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     // Nao pode ser final o parametro para retornar o objeto com seu Id
    public final void insertSinc(ExecucaoItemSerie item) {
        try {
        	item.setIdExecucaoItemSerie((int)getMaxId() + 1);
        	DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc: " + item.debug());
	        long id = getDb().insert(ExecucaoItemSerieDBHelperBase.DB_TABLE, null, montaValores(item));
    	    ((ObjetoSinc)item).setOperacaoSinc("I");
        	getDb().insert(ExecucaoItemSerieDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     public final void updateSinc(final ExecucaoItemSerie item) {
        try {
	        DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc: " + item.debug());
	        getDb().update(ExecucaoItemSerieDBHelperBase.DB_TABLE, montaValores(item), "id_execucao_item_serie=" + item.getIdExecucaoItemSerie(), null);
	        ((ObjetoSinc)item).setOperacaoSinc("A");
	        ExecucaoItemSerie atual = this.getSincById(item.getId());
	        if (atual==null) {
	        	getDb().insert(ExecucaoItemSerieDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	        } else {
	        	if ("I".equals(((ObjetoSinc)atual).getOperacaoSinc()))
	        		((ObjetoSinc)item).setOperacaoSinc("I");
	        	getDb().update(ExecucaoItemSerieDBHelperBase.DB_TABLE_SINC, montaValoresSinc(item), "id_execucao_item_serie=" + item.getIdExecucaoItemSerie(), null);
	        }
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    protected final void delete(final long id) {
        try {
			getDb().delete(ExecucaoItemSerieDBHelperBase.DB_TABLE, "id_execucao_item_serie=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    /*
    private void deleteSinc(final long id) {
        try {
			getDb().delete(ExecucaoItemSerieDBHelperBase.DB_TABLE_SINC, "id_execucao_item_serie=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
	*/
    public void limpaSinc(final ExecucaoItemSerie item) {
    	try {
			getDb().delete(ExecucaoItemSerieDBHelperBase.DB_TABLE_SINC, "id_execucao_item_serie=" + item.getId(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    
    public void deleteSinc(final ExecucaoItemSerie item) {
    	try {
	        DCLog.dStack(DCLog.DATABASE_ADM, this, "deleteSinc: " + item.debug());
	        delete(item.getId());
	        ((ObjetoSinc)item).setOperacaoSinc("D");
        	getDb().insert(ExecucaoItemSerieDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
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
    public ExecucaoItemSerie getById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (ExecucaoItemSerie) getItemQuery(true, ExecucaoItemSerieDBHelperBase.DB_TABLE, ExecucaoItemSerieDBHelperBase.COLS, "id_execucao_item_serie = " + id + "", null, null, null, null,null);
    }
    
    // Esta com orderBy que pode ser bom para exibicoes em tela
    // mas nao e bom para sincronizacao, pensar em ter um metodo para tela e outro para sinc.
    public List<ExecucaoItemSerie> getAll() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, null);
    }
    public List<ExecucaoItemSerie> getAllTela() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, orderByColuna());
    }
    
    private ExecucaoItemSerie getByRowId(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (ExecucaoItemSerie) getItemQuery(true, ExecucaoItemSerieDBHelperBase.DB_TABLE, ExecucaoItemSerieDBHelperBase.COLS, "ROWID = " + id + "", null, null, null, null,null);
    }
    private ExecucaoItemSerie getSincById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (ExecucaoItemSerie) getItemQuerySinc(true, ExecucaoItemSerieDBHelperBase.DB_TABLE_SINC, ExecucaoItemSerieDBHelperBase.COLS_SINC, "id_execucao_item_serie = " + id + "", null, null, null, null,null);
    }
    
    
    public long getMaxId() {
		String sql = "select max(id_execucao_item_serie) from " + DB_TABLE;
		return this.getNumeroSql(sql);
	}
	protected String orderByColuna() {
    	return null;
    }
	
	
	public List<ExecucaoItemSerie> getPorReferenteAItemSerie(Context contexto, long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_item_serie_ra = " + id, null, null, null, orderByColuna());
	}
	public List<ExecucaoItemSerie> getPorReferenteAItemSerie(long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_item_serie_ra = " + id, null, null, null, orderByColuna());
	}
	
	
	
	public List<ExecucaoItemSerie> getPorEmDiaTreino(Context contexto, long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_dia_treino_e = " + id, null, null, null, orderByColuna());
	}
	public List<ExecucaoItemSerie> getPorEmDiaTreino(long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_dia_treino_e = " + id, null, null, null, orderByColuna());
	}
	
	
	
	public List<ExecucaoItemSerie> getPorReferenteAExercicio(Context contexto, long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_exercicio_ra = " + id, null, null, null, orderByColuna());
	}
	public List<ExecucaoItemSerie> getPorReferenteAExercicio(long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_exercicio_ra = " + id, null, null, null, orderByColuna());
	}
	
	
  
  	
  
  
  	
	
	
	// Relacionamento onde esse objeto ? chave estrangeira de outro. ????
	
	private boolean _obtemItemSerie_ReferenteA = false;
	public void setObtemItemSerie_ReferenteA() {
		_obtemItemSerie_ReferenteA = true;
	}
	protected String outterJoinItemSerie_ReferenteA() {
		return " left outer join " + ItemSerieDBHelperBase.DB_TABLE + " on " + ItemSerieDBHelperBase.DB_TABLE + ".id_item_serie = " + DB_TABLE + ".id_item_serie_ra ";  
	}
	public boolean atualizaReferenteAItemSerie(long idExecucaoItemSerie, long idItemSerieRa) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_item_serie_ra  = " + idItemSerieRa +
        " where id_execucao_item_serie = " +  idExecucaoItemSerie;
       	//this.executaSql(sql);
       	return true;
	}
	public ExecucaoItemSerie obtemPorIdsReferenteAItemSerie(long idExecucaoItemSerie, long idItemSerieRa) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_item_serie_ra = " + idItemSerieRa + " and " +
			  " id_execucao_item_serie = " + idExecucaoItemSerie;
		return (ExecucaoItemSerie) this.geObjetoSql(sql);
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
	
 	
	private boolean _obtemDiaTreino_Em = false;
	public void setObtemDiaTreino_Em() {
		_obtemDiaTreino_Em = true;
	}
	protected String outterJoinDiaTreino_Em() {
		return " left outer join " + DiaTreinoDBHelperBase.DB_TABLE + " on " + DiaTreinoDBHelperBase.DB_TABLE + ".id_dia_treino = " + DB_TABLE + ".id_dia_treino_e ";  
	}
	public boolean atualizaEmDiaTreino(long idExecucaoItemSerie, long idDiaTreinoE) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_dia_treino_e  = " + idDiaTreinoE +
        " where id_execucao_item_serie = " +  idExecucaoItemSerie;
       	//this.executaSql(sql);
       	return true;
	}
	public ExecucaoItemSerie obtemPorIdsEmDiaTreino(long idExecucaoItemSerie, long idDiaTreinoE) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_dia_treino_e = " + idDiaTreinoE + " and " +
			  " id_execucao_item_serie = " + idExecucaoItemSerie;
		return (ExecucaoItemSerie) this.geObjetoSql(sql);
	}
	
	
	public static String innerJoinDiaTreino_Em() {
		return " inner join " + DiaTreinoDBHelperBase.DB_TABLE + " on " + DiaTreinoDBHelperBase.DB_TABLE + ".id_dia_treino = " + DB_TABLE + ".id_dia_treino_e ";  
	}
	public static String outerJoinDiaTreino_Em() {
		return " left outer join " + DiaTreinoDBHelperBase.DB_TABLE + " on " + DiaTreinoDBHelperBase.DB_TABLE + ".id_dia_treino = " + DB_TABLE + ".id_dia_treino_e ";  
	}
	public static String innerJoinSincDiaTreino_Em() {
		return " inner join " + DiaTreinoDBHelperBase.DB_TABLE_SINC + " on " + DiaTreinoDBHelperBase.DB_TABLE_SINC + ".id_dia_treino = " + DB_TABLE_SINC + ".id_dia_treino_e ";  
	}
	public static String outerJoinSincDiaTreino_Em() {
		return " left outer join " + DiaTreinoDBHelperBase.DB_TABLE_SINC + " on " + DiaTreinoDBHelperBase.DB_TABLE_SINC + ".id_dia_treino = " + DB_TABLE_SINC + ".id_dia_treino_e ";  
	}
	
 	
	private boolean _obtemExercicio_ReferenteA = false;
	public void setObtemExercicio_ReferenteA() {
		_obtemExercicio_ReferenteA = true;
	}
	protected String outterJoinExercicio_ReferenteA() {
		return " left outer join " + ExercicioDBHelperBase.DB_TABLE + " on " + ExercicioDBHelperBase.DB_TABLE + ".id_exercicio = " + DB_TABLE + ".id_exercicio_ra ";  
	}
	public boolean atualizaReferenteAExercicio(long idExecucaoItemSerie, long idExercicioRa) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_exercicio_ra  = " + idExercicioRa +
        " where id_execucao_item_serie = " +  idExecucaoItemSerie;
       	//this.executaSql(sql);
       	return true;
	}
	public ExecucaoItemSerie obtemPorIdsReferenteAExercicio(long idExecucaoItemSerie, long idExercicioRa) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_exercicio_ra = " + idExercicioRa + " and " +
			  " id_execucao_item_serie = " + idExecucaoItemSerie;
		return (ExecucaoItemSerie) this.geObjetoSql(sql);
	}
	
	
	public static String innerJoinExercicio_ReferenteA() {
		return " inner join " + ExercicioDBHelperBase.DB_TABLE + " on " + ExercicioDBHelperBase.DB_TABLE + ".id_exercicio = " + DB_TABLE + ".id_exercicio_ra ";  
	}
	public static String outerJoinExercicio_ReferenteA() {
		return " left outer join " + ExercicioDBHelperBase.DB_TABLE + " on " + ExercicioDBHelperBase.DB_TABLE + ".id_exercicio = " + DB_TABLE + ".id_exercicio_ra ";  
	}
	public static String innerJoinSincExercicio_ReferenteA() {
		return " inner join " + ExercicioDBHelperBase.DB_TABLE_SINC + " on " + ExercicioDBHelperBase.DB_TABLE_SINC + ".id_exercicio = " + DB_TABLE_SINC + ".id_exercicio_ra ";  
	}
	public static String outerJoinSincExercicio_ReferenteA() {
		return " left outer join " + ExercicioDBHelperBase.DB_TABLE_SINC + " on " + ExercicioDBHelperBase.DB_TABLE_SINC + ".id_exercicio = " + DB_TABLE_SINC + ".id_exercicio_ra ";  
	}
	
 	
	// ********************************************************************	
	
	
	public static String camposOrdenados() 
	{
		return " execucao_item_serie.id_execucao_item_serie " 
		+ " ,execucao_item_serie.data_hora_inicio " 
		+ " ,execucao_item_serie.carga_utilizada " 
		+ " ,execucao_item_serie.sucesso_repeticoes " 
		+ " ,execucao_item_serie.numero_serie " 
		+ " ,execucao_item_serie.data_hora_finalizacao " 
		+ " ,execucao_item_serie.quantidade_repeticao " 
		+ " ,execucao_item_serie.id_item_serie_ra " 
		+ " ,execucao_item_serie.id_dia_treino_e " 
		+ " ,execucao_item_serie.id_exercicio_ra " 
		+ " ,execucao_item_serie.id_usuario_s " 
		;
	}
	public static String camposOrdenadosSinc() 
	{
		return " execucao_item_serie_sinc.id_execucao_item_serie " 
		+ " ,execucao_item_serie_sinc.data_hora_inicio " 
		+ " ,execucao_item_serie_sinc.carga_utilizada " 
		+ " ,execucao_item_serie_sinc.sucesso_repeticoes " 
		+ " ,execucao_item_serie_sinc.numero_serie " 
		+ " ,execucao_item_serie_sinc.data_hora_finalizacao " 
		+ " ,execucao_item_serie_sinc.quantidade_repeticao " 
		+ " ,execucao_item_serie_sinc.id_item_serie_ra " 
		+ " ,execucao_item_serie_sinc.id_dia_treino_e " 
		+ " ,execucao_item_serie_sinc.id_exercicio_ra " 
		+ " ,execucao_item_serie_sinc.id_usuario_s " 
		
		+ " ,execucao_item_serie_sinc.operacao_sinc "
		;
	}
	public static String camposOrdenadosAlias(String nomeTabela) 
	{
		return  nomeTabela + ".id_execucao_item_serie " 
		+ " , " +  "DATE_FORMAT(" + nomeTabela + ".data_hora_inicio,'%d-%m-%Y %H:%i:%s') " 
		+ " , " + nomeTabela + ".carga_utilizada " 
		+ " , " + nomeTabela + ".sucesso_repeticoes " 
		+ " , " + nomeTabela + ".numero_serie " 
		+ " , " +  "DATE_FORMAT(" + nomeTabela + ".data_hora_finalizacao,'%d-%m-%Y %H:%i:%s') " 
		+ " , " + nomeTabela + ".quantidade_repeticao " 
		+ " , " + nomeTabela + ".id_item_serie_ra " 
		+ " , " + nomeTabela + ".id_dia_treino_e " 
		+ " , " + nomeTabela + ".id_exercicio_ra " 
		+ " , " + nomeTabela + ".id_usuario_s " 
		;
	}
	
	protected String camposOrdenadosJoin()
    {
        String saida = camposOrdenados();
		saida += (this._obtemItemSerie_ReferenteA?" , " +ItemSerieDBHelperBase.camposOrdenados():"");
		saida += (this._obtemDiaTreino_Em?" , " +DiaTreinoDBHelperBase.camposOrdenados():"");
		saida += (this._obtemExercicio_ReferenteA?" , " +ExercicioDBHelperBase.camposOrdenados():"");
        return saida;
    }
    
        
    public void limpaObtem()
    {
		_obtemItemSerie_ReferenteA = false;
		_obtemDiaTreino_Em = false;
		_obtemExercicio_ReferenteA = false;
    }
    
	protected String outterJoinAgrupado()
    {
        String saida = " ";
		saida += (this._obtemItemSerie_ReferenteA? outterJoinItemSerie_ReferenteA() + " ":"");
		saida += (this._obtemDiaTreino_Em? outterJoinDiaTreino_Em() + " ":"");
		saida += (this._obtemExercicio_ReferenteA? outterJoinExercicio_ReferenteA() + " ":"");
        return saida;
    }
    protected MontadorDaoI getMontadorAgrupado()
    {
        MontadorDaoComposite montador = new MontadorDaoComposite();
        montador.adicionaMontador(new ExecucaoItemSerieMontador(), null);
		if (this._obtemItemSerie_ReferenteA)
            montador.adicionaMontador(new ItemSerieMontador(), "ItemSerie_ReferenteA");
		if (this._obtemDiaTreino_Em)
            montador.adicionaMontador(new DiaTreinoMontador(), "DiaTreino_Em");
		if (this._obtemExercicio_ReferenteA)
            montador.adicionaMontador(new ExercicioMontador(), "Exercicio_ReferenteA");
         return montador;
    }
	
    
   	// Poderia passar para classe abstrata.
    public final List<ExecucaoItemSerie> getAllSinc() throws DaoException {
    	this.setMontador(null);
    	List<ExecucaoItemSerie> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<ExecucaoItemSerie>();
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
    public final List<ExecucaoItemSerie> getAllSincSoAlteracao() throws DaoException {
    	List<ExecucaoItemSerie> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    		saida = null; // Melhorar aqui !!!!
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<ExecucaoItemSerie>();
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
	
	public final List<ExecucaoItemSerie> getAllSincImpl() throws DaoException {
   		String sql = "select " + camposOrdenadosSinc()   
   			+ " from " + this.DB_TABLE_SINC;
   		ExecucaoItemSerieMontador montador = new ExecucaoItemSerieMontador(true); // sinc ligado
   		this.setMontador(montador);
   		List<ExecucaoItemSerie> saida = this.getListaSql(sql);
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
		this.insert((ExecucaoItemSerie) obj);
	}
	@Override
	public final void dropCreate() {
		this.dropTable();
		this.criaTabela();
	}
}
