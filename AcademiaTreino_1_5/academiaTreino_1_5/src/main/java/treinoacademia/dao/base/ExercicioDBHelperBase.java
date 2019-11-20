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
import treinoacademia.modelo.vo.ExercicioVo;
import treinoacademia.modelo.vo.FabricaVo;
import treinoacademia.dao.*;
import treinoacademia.dao.montador.*;
import treinoacademia.dao.datasource.DataSourceAplicacao;
import treinoacademia.app.TrataErro;

public abstract class ExercicioDBHelperBase extends DBHelperBase
	implements DaoSincronismo // coloquei aqui para evitar impacto de arquitetura
{

    private static final String DB_NAME = "w_alert";
    public static final String DB_TABLE = "exercicio";
    public static final String DB_TABLE_SINC = "exercicio_sinc";
    public static final int DB_VERSION = 3;

    protected static final String CLASSNAME = ExercicioDBHelperBase.class.getSimpleName();
    protected static final String[] COLS = new String[] { 
        "id_exercicio"
        ,"descricao_exercicio"
        ,"imagem"
        ,"titulo"
        ,"subtitulo"
		, "id_grupo_muscular_p"
	
		, "id_usuario_s"
	
    };

	@Override
	public void erroException(Exception e, DBHelperBase dao) {
		TrataErro.getInstancia().setErro(e, dao);
	}

	protected static final String[] COLS_SINC = new String[] { 
        "id_exercicio"
        ,"descricao_exercicio"
        ,"imagem"
        ,"titulo"
        ,"subtitulo"
		, "id_grupo_muscular_p"
	
		, "id_usuario_s"
		, "operacao_sinc"
    };

    protected SQLiteDatabase db;
    protected final DBOpenHelper dbOpenHelper;

	@Override
	protected MontadorDaoI criaMontadorPadrao() {
		return new ExercicioMontador();
	}
	
	protected String getSqlIndices() {
		return "";
	}
	protected String getSqlCreate(){
		return  "CREATE TABLE "
        + ExercicioDBHelperBase.DB_TABLE + " ("
        + "  id_exercicio INTEGER PRIMARY KEY "
        + " , descricao_exercicio TEXT "
        + " , imagem TEXT "
        + " , titulo TEXT "
        + " , subtitulo TEXT "
		+ " , id_grupo_muscular_p INTEGER "
		
		+ " , id_usuario_s INTEGER "
		
		+ getSqlIndices()
        + ");";
	}

	

	public static final String DB_CREATE_SINCRONIZADA = "CREATE TABLE IF NOT EXISTS "
        + ExercicioDBHelperBase.DB_TABLE_SINC + " ("
        + "  id_exercicio INTEGER "
        + " , descricao_exercicio TEXT "
        + " , imagem TEXT "
        + " , titulo TEXT "
        + " , subtitulo TEXT "
		+ " , id_grupo_muscular_p INTEGER "
		
		+ " , id_usuario_s INTEGER "
		
        + ", operacao_sinc TEXT);";


    public static final String DB_CREATE = "CREATE TABLE IF NOT EXISTS "
        + ExercicioDBHelperBase.DB_TABLE + " ("
        + "  id_exercicio INTEGER PRIMARY KEY "
        + " , descricao_exercicio TEXT "
        + " , imagem TEXT "
        + " , titulo TEXT "
        + " , subtitulo TEXT "
		+ " , id_grupo_muscular_p INTEGER "
		
		+ " , id_usuario_s INTEGER "
		
        + ");";
    
    private static final String DB_DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private static final String DB_DROP = "DROP TABLE IF EXISTS " + DB_TABLE;
    private static final String DB_DROP_SINCRONIZADA = "DROP TABLE IF EXISTS " + DB_TABLE_SINC;
    
    private static class DBOpenHelper extends SQLiteOpenHelper {

       

        public DBOpenHelper(final Context context) {
            super(context, ExercicioDBHelperBase.DB_NAME, null, ExercicioDBHelperBase.DB_VERSION);
        }

        @Override
        public void onCreate(final SQLiteDatabase db) {
            try {
                db.execSQL(DB_CREATE);
            } catch (SQLException e) {
                Log.e(Constants.LOGTAG, ExercicioDBHelperBase.CLASSNAME, e);
            }
        }

        @Override
        public void onOpen(final SQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + ExercicioDBHelperBase.DB_TABLE);
            onCreate(db);
        }
    }

    //
    // end inner classes
    //
    
    // Versao Nova
 	public ExercicioDBHelperBase() {
    	this.dbOpenHelper = null;
    	setDataSource(DataSourceAplicacao.getInstancia());
    }
    
   
	
	protected ContentValues montaValores(final DCIObjetoDominio valor) {
		Exercicio item = (Exercicio) valor;
		ContentValues valores = new ContentValues();
       	putValor(valores,"id_exercicio",item.getIdExercicio());
       	
       	putValor(valores,"descricao_exercicio",item.getDescricaoExercicio());
       	
       	putValor(valores,"imagem",item.getImagem());
       	
       	putValor(valores,"titulo",item.getTitulo());
       	
       	putValor(valores,"subtitulo",item.getSubtitulo());
       	
       	putValor(valores,"id_grupo_muscular_p",item.getIdGrupoMuscularP());
       	
       	putValor(valores,"id_usuario_s",item.getIdUsuarioS());
       	
        return valores;
	}


    // **** Chamadas Diretas Objeto Banco de Dados
    private void registraErroChamadaDireta(Exception e) {
    	Log.e(Constants.LOGTAG, ExercicioDBHelperBase.CLASSNAME, e);
    }
   
    public final void insert(final Exercicio item) {
	    try {
	        getDb().insert(ExercicioDBHelperBase.DB_TABLE, null, montaValores(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    public final void update(final Exercicio item) {
	    try {
	        getDb().update(ExercicioDBHelperBase.DB_TABLE, montaValores(item), "id_exercicio=" + item.getIdExercicio(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     // Nao pode ser final o parametro para retornar o objeto com seu Id
    public final void insertSinc(Exercicio item) {
        try {
        	item.setIdExercicio((int)getMaxId() + 1);
        	DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc: " + item.debug());
	        long id = getDb().insert(ExercicioDBHelperBase.DB_TABLE, null, montaValores(item));
    	    ((ObjetoSinc)item).setOperacaoSinc("I");
        	getDb().insert(ExercicioDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     public final void updateSinc(final Exercicio item) {
        try {
	        DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc: " + item.debug());
	        getDb().update(ExercicioDBHelperBase.DB_TABLE, montaValores(item), "id_exercicio=" + item.getIdExercicio(), null);
	        ((ObjetoSinc)item).setOperacaoSinc("A");
	        Exercicio atual = this.getSincById(item.getId());
	        if (atual==null) {
	        	getDb().insert(ExercicioDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	        } else {
	        	if ("I".equals(((ObjetoSinc)atual).getOperacaoSinc()))
	        		((ObjetoSinc)item).setOperacaoSinc("I");
	        	getDb().update(ExercicioDBHelperBase.DB_TABLE_SINC, montaValoresSinc(item), "id_exercicio=" + item.getIdExercicio(), null);
	        }
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    protected final void delete(final long id) {
        try {
			getDb().delete(ExercicioDBHelperBase.DB_TABLE, "id_exercicio=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    /*
    private void deleteSinc(final long id) {
        try {
			getDb().delete(ExercicioDBHelperBase.DB_TABLE_SINC, "id_exercicio=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
	*/
    public void limpaSinc(final Exercicio item) {
    	try {
			getDb().delete(ExercicioDBHelperBase.DB_TABLE_SINC, "id_exercicio=" + item.getId(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    
    public void deleteSinc(final Exercicio item) {
    	try {
	        DCLog.dStack(DCLog.DATABASE_ADM, this, "deleteSinc: " + item.debug());
	        delete(item.getId());
	        ((ObjetoSinc)item).setOperacaoSinc("D");
        	getDb().insert(ExercicioDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
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
    public Exercicio getById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (Exercicio) getItemQuery(true, ExercicioDBHelperBase.DB_TABLE, ExercicioDBHelperBase.COLS, "id_exercicio = " + id + "", null, null, null, null,null);
    }
    
    // Esta com orderBy que pode ser bom para exibicoes em tela
    // mas nao e bom para sincronizacao, pensar em ter um metodo para tela e outro para sinc.
    public List<Exercicio> getAll() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, null);
    }
    public List<Exercicio> getAllTela() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, orderByColuna());
    }
    
    private Exercicio getByRowId(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (Exercicio) getItemQuery(true, ExercicioDBHelperBase.DB_TABLE, ExercicioDBHelperBase.COLS, "ROWID = " + id + "", null, null, null, null,null);
    }
    private Exercicio getSincById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (Exercicio) getItemQuerySinc(true, ExercicioDBHelperBase.DB_TABLE_SINC, ExercicioDBHelperBase.COLS_SINC, "id_exercicio = " + id + "", null, null, null, null,null);
    }
    
    
    public long getMaxId() {
		String sql = "select max(id_exercicio) from " + DB_TABLE;
		return this.getNumeroSql(sql);
	}
	protected String orderByColuna() {
    	return null;
    }
	
	
	public List<Exercicio> getPorParaGrupoMuscular(Context contexto, long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_grupo_muscular_p = " + id, null, null, null, orderByColuna());
	}
	public List<Exercicio> getPorParaGrupoMuscular(long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_grupo_muscular_p = " + id, null, null, null, orderByColuna());
	}
	
	
  
  	
  
  
  	
	/*
	public Exercicio obtemPorItemSerieGera(long id) {
		string sql;
		sql = "select " + camposOrdenados() +
			" from " + tabelaSelect() +
			innerJoinItemSerie_Gera() + 
			" where id_item_serie = " + id;
		return (Exercicio) getObjeto(sql);
	}
	*/
	public static String innerJoinItemSerie_Gera() {
		return " inner join " + ItemSerieDBHelperBase.DB_TABLE + " on " + ItemSerieDBHelperBase.DB_TABLE + ".id_exercicio_ed = " + DB_TABLE + ".id_exercicio ";  
	}
	public static String innerJoinSincItemSerie_Gera() {
		return " inner join " + ItemSerieDBHelperBase.DB_TABLE_SINC + " on " + ItemSerieDBHelperBase.DB_TABLE_SINC + ".id_exercicio_ed = " + DB_TABLE_SINC + ".id_exercicio ";  
	}
	public static String outerJoinItemSerie_Gera() {
		return " left outer join " + ItemSerieDBHelperBase.DB_TABLE + " on " + ItemSerieDBHelperBase.DB_TABLE + ".id_exercicio_ed = " + DB_TABLE + ".id_exercicio ";  
	}
	public static String outerJoinSincItemSerie_Gera() {
		return " left outer join " + ItemSerieDBHelperBase.DB_TABLE_SINC + " on " + ItemSerieDBHelperBase.DB_TABLE_SINC + ".id_exercicio_ed = " + DB_TABLE_SINC + ".id_exercicio ";  
	}
 	
	/*
	public Exercicio obtemPorExecucaoItemSerieExecutado(long id) {
		string sql;
		sql = "select " + camposOrdenados() +
			" from " + tabelaSelect() +
			innerJoinExecucaoItemSerie_Executado() + 
			" where id_execucao_item_serie = " + id;
		return (Exercicio) getObjeto(sql);
	}
	*/
	public static String innerJoinExecucaoItemSerie_Executado() {
		return " inner join " + ExecucaoItemSerieDBHelperBase.DB_TABLE + " on " + ExecucaoItemSerieDBHelperBase.DB_TABLE + ".id_exercicio_ra = " + DB_TABLE + ".id_exercicio ";  
	}
	public static String innerJoinSincExecucaoItemSerie_Executado() {
		return " inner join " + ExecucaoItemSerieDBHelperBase.DB_TABLE_SINC + " on " + ExecucaoItemSerieDBHelperBase.DB_TABLE_SINC + ".id_exercicio_ra = " + DB_TABLE_SINC + ".id_exercicio ";  
	}
	public static String outerJoinExecucaoItemSerie_Executado() {
		return " left outer join " + ExecucaoItemSerieDBHelperBase.DB_TABLE + " on " + ExecucaoItemSerieDBHelperBase.DB_TABLE + ".id_exercicio_ra = " + DB_TABLE + ".id_exercicio ";  
	}
	public static String outerJoinSincExecucaoItemSerie_Executado() {
		return " left outer join " + ExecucaoItemSerieDBHelperBase.DB_TABLE_SINC + " on " + ExecucaoItemSerieDBHelperBase.DB_TABLE_SINC + ".id_exercicio_ra = " + DB_TABLE_SINC + ".id_exercicio ";  
	}
 	
	
	
	// Relacionamento onde esse objeto ? chave estrangeira de outro. ????
	
	private boolean _obtemGrupoMuscular_Para = false;
	public void setObtemGrupoMuscular_Para() {
		_obtemGrupoMuscular_Para = true;
	}
	protected String outterJoinGrupoMuscular_Para() {
		return " left outer join " + GrupoMuscularDBHelperBase.DB_TABLE + " on " + GrupoMuscularDBHelperBase.DB_TABLE + ".id_grupo_muscular = " + DB_TABLE + ".id_grupo_muscular_p ";  
	}
	public boolean atualizaParaGrupoMuscular(long idExercicio, long idGrupoMuscularP) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_grupo_muscular_p  = " + idGrupoMuscularP +
        " where id_exercicio = " +  idExercicio;
       	//this.executaSql(sql);
       	return true;
	}
	public Exercicio obtemPorIdsParaGrupoMuscular(long idExercicio, long idGrupoMuscularP) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_grupo_muscular_p = " + idGrupoMuscularP + " and " +
			  " id_exercicio = " + idExercicio;
		return (Exercicio) this.geObjetoSql(sql);
	}
	
	
	public static String innerJoinGrupoMuscular_Para() {
		return " inner join " + GrupoMuscularDBHelperBase.DB_TABLE + " on " + GrupoMuscularDBHelperBase.DB_TABLE + ".id_grupo_muscular = " + DB_TABLE + ".id_grupo_muscular_p ";  
	}
	public static String outerJoinGrupoMuscular_Para() {
		return " left outer join " + GrupoMuscularDBHelperBase.DB_TABLE + " on " + GrupoMuscularDBHelperBase.DB_TABLE + ".id_grupo_muscular = " + DB_TABLE + ".id_grupo_muscular_p ";  
	}
	public static String innerJoinSincGrupoMuscular_Para() {
		return " inner join " + GrupoMuscularDBHelperBase.DB_TABLE_SINC + " on " + GrupoMuscularDBHelperBase.DB_TABLE_SINC + ".id_grupo_muscular = " + DB_TABLE_SINC + ".id_grupo_muscular_p ";  
	}
	public static String outerJoinSincGrupoMuscular_Para() {
		return " left outer join " + GrupoMuscularDBHelperBase.DB_TABLE_SINC + " on " + GrupoMuscularDBHelperBase.DB_TABLE_SINC + ".id_grupo_muscular = " + DB_TABLE_SINC + ".id_grupo_muscular_p ";  
	}
	
 	
	// ********************************************************************	
	
	
	public static String camposOrdenados() 
	{
		return " exercicio.id_exercicio " 
		+ " ,exercicio.descricao_exercicio " 
		+ " ,exercicio.imagem " 
		+ " ,exercicio.titulo " 
		+ " ,exercicio.subtitulo " 
		+ " ,exercicio.id_grupo_muscular_p " 
		+ " ,exercicio.id_usuario_s " 
		;
	}
	public static String camposOrdenadosSinc() 
	{
		return " exercicio_sinc.id_exercicio " 
		+ " ,exercicio_sinc.descricao_exercicio " 
		+ " ,exercicio_sinc.imagem " 
		+ " ,exercicio_sinc.titulo " 
		+ " ,exercicio_sinc.subtitulo " 
		+ " ,exercicio_sinc.id_grupo_muscular_p " 
		+ " ,exercicio_sinc.id_usuario_s " 
		
		+ " ,exercicio_sinc.operacao_sinc "
		;
	}
	public static String camposOrdenadosAlias(String nomeTabela) 
	{
		return  nomeTabela + ".id_exercicio " 
		+ " , " + nomeTabela + ".descricao_exercicio " 
		+ " , " + nomeTabela + ".imagem " 
		+ " , " + nomeTabela + ".titulo " 
		+ " , " + nomeTabela + ".subtitulo " 
		+ " , " + nomeTabela + ".id_grupo_muscular_p " 
		+ " , " + nomeTabela + ".id_usuario_s " 
		;
	}
	
	protected String camposOrdenadosJoin()
    {
        String saida = camposOrdenados();
		saida += (this._obtemGrupoMuscular_Para?" , " +GrupoMuscularDBHelperBase.camposOrdenados():"");
        return saida;
    }
    
        
    public void limpaObtem()
    {
		_obtemGrupoMuscular_Para = false;
    }
    
	protected String outterJoinAgrupado()
    {
        String saida = " ";
		saida += (this._obtemGrupoMuscular_Para? outterJoinGrupoMuscular_Para() + " ":"");
        return saida;
    }
    protected MontadorDaoI getMontadorAgrupado()
    {
        MontadorDaoComposite montador = new MontadorDaoComposite();
        montador.adicionaMontador(new ExercicioMontador(), null);
		if (this._obtemGrupoMuscular_Para)
            montador.adicionaMontador(new GrupoMuscularMontador(), "GrupoMuscular_Para");
         return montador;
    }
	
    
   	// Poderia passar para classe abstrata.
    public final List<Exercicio> getAllSinc() throws DaoException {
    	this.setMontador(null);
    	List<Exercicio> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<Exercicio>();
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
    public final List<Exercicio> getAllSincSoAlteracao() throws DaoException {
    	List<Exercicio> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    		saida = null; // Melhorar aqui !!!!
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<Exercicio>();
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
	
	public final List<Exercicio> getAllSincImpl() throws DaoException {
   		String sql = "select " + camposOrdenadosSinc()   
   			+ " from " + this.DB_TABLE_SINC;
   		ExercicioMontador montador = new ExercicioMontador(true); // sinc ligado
   		this.setMontador(montador);
   		List<Exercicio> saida = this.getListaSql(sql);
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
		this.insert((Exercicio) obj);
	}
	@Override
	public final void dropCreate() {
		this.dropTable();
		this.criaTabela();
	}
}
