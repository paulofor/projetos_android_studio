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
import treinoacademia.modelo.vo.ItemSerieVo;
import treinoacademia.modelo.vo.FabricaVo;
import treinoacademia.dao.*;
import treinoacademia.dao.montador.*;
import treinoacademia.dao.datasource.DataSourceAplicacao;
import treinoacademia.app.TrataErro;

public abstract class ItemSerieDBHelperBase extends DBHelperBase
	implements DaoSincronismo // coloquei aqui para evitar impacto de arquitetura
{

    private static final String DB_NAME = "w_alert";
    public static final String DB_TABLE = "item_serie";
    public static final String DB_TABLE_SINC = "item_serie_sinc";
    public static final int DB_VERSION = 3;

    protected static final String CLASSNAME = ItemSerieDBHelperBase.class.getSimpleName();
    protected static final String[] COLS = new String[] { 
        "id_item_serie"
        ,"repeticoes"
        ,"serie"
        ,"parametros"
        ,"ordem_execucao"
		, "id_exercicio_ed"
	
		, "id_serie_treino_pa"
	
		, "id_usuario_s"
	
    };

	@Override
	public void erroException(Exception e, DBHelperBase dao) {
		TrataErro.getInstancia().setErro(e, dao);
	}

	protected static final String[] COLS_SINC = new String[] { 
        "id_item_serie"
        ,"repeticoes"
        ,"serie"
        ,"parametros"
        ,"ordem_execucao"
		, "id_exercicio_ed"
	
		, "id_serie_treino_pa"
	
		, "id_usuario_s"
		, "operacao_sinc"
    };

    protected SQLiteDatabase db;
    protected final DBOpenHelper dbOpenHelper;

	@Override
	protected MontadorDaoI criaMontadorPadrao() {
		return new ItemSerieMontador();
	}
	
	protected String getSqlIndices() {
		return "";
	}
	protected String getSqlCreate(){
		return  "CREATE TABLE "
        + ItemSerieDBHelperBase.DB_TABLE + " ("
        + "  id_item_serie INTEGER PRIMARY KEY "
        + " , repeticoes INTEGER "
        + " , serie INTEGER "
        + " , parametros TEXT "
        + " , ordem_execucao INTEGER "
		+ " , id_exercicio_ed INTEGER "
		
		+ " , id_serie_treino_pa INTEGER "
		
		+ " , id_usuario_s INTEGER "
		
		+ getSqlIndices()
        + ");";
	}

	

	public static final String DB_CREATE_SINCRONIZADA = "CREATE TABLE IF NOT EXISTS "
        + ItemSerieDBHelperBase.DB_TABLE_SINC + " ("
        + "  id_item_serie INTEGER "
        + " , repeticoes INTEGER "
        + " , serie INTEGER "
        + " , parametros TEXT "
        + " , ordem_execucao INTEGER "
		+ " , id_exercicio_ed INTEGER "
		
		+ " , id_serie_treino_pa INTEGER "
		
		+ " , id_usuario_s INTEGER "
		
        + ", operacao_sinc TEXT);";


    public static final String DB_CREATE = "CREATE TABLE IF NOT EXISTS "
        + ItemSerieDBHelperBase.DB_TABLE + " ("
        + "  id_item_serie INTEGER PRIMARY KEY "
        + " , repeticoes INTEGER "
        + " , serie INTEGER "
        + " , parametros TEXT "
        + " , ordem_execucao INTEGER "
		+ " , id_exercicio_ed INTEGER "
		
		+ " , id_serie_treino_pa INTEGER "
		
		+ " , id_usuario_s INTEGER "
		
        + ");";
    
    private static final String DB_DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private static final String DB_DROP = "DROP TABLE IF EXISTS " + DB_TABLE;
    private static final String DB_DROP_SINCRONIZADA = "DROP TABLE IF EXISTS " + DB_TABLE_SINC;
    
    private static class DBOpenHelper extends SQLiteOpenHelper {

       

        public DBOpenHelper(final Context context) {
            super(context, ItemSerieDBHelperBase.DB_NAME, null, ItemSerieDBHelperBase.DB_VERSION);
        }

        @Override
        public void onCreate(final SQLiteDatabase db) {
            try {
                db.execSQL(DB_CREATE);
            } catch (SQLException e) {
                Log.e(Constants.LOGTAG, ItemSerieDBHelperBase.CLASSNAME, e);
            }
        }

        @Override
        public void onOpen(final SQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + ItemSerieDBHelperBase.DB_TABLE);
            onCreate(db);
        }
    }

    //
    // end inner classes
    //
    
    // Versao Nova
 	public ItemSerieDBHelperBase() {
    	this.dbOpenHelper = null;
    	setDataSource(DataSourceAplicacao.getInstancia());
    }
    
   
	
	protected ContentValues montaValores(final DCIObjetoDominio valor) {
		ItemSerie item = (ItemSerie) valor;
		ContentValues valores = new ContentValues();
       	putValor(valores,"id_item_serie",item.getIdItemSerie());
       	
       	putValor(valores,"repeticoes",item.getRepeticoes());
       	
       	putValor(valores,"serie",item.getSerie());
       	
       	putValor(valores,"parametros",item.getParametros());
       	
       	putValor(valores,"ordem_execucao",item.getOrdemExecucao());
       	
       	putValor(valores,"id_exercicio_ed",item.getIdExercicioEd());
       	
       	putValor(valores,"id_serie_treino_pa",item.getIdSerieTreinoPa());
       	
       	putValor(valores,"id_usuario_s",item.getIdUsuarioS());
       	
        return valores;
	}


    // **** Chamadas Diretas Objeto Banco de Dados
    private void registraErroChamadaDireta(Exception e) {
    	Log.e(Constants.LOGTAG, ItemSerieDBHelperBase.CLASSNAME, e);
    }
   
    public final void insert(final ItemSerie item) {
	    try {
	        getDb().insert(ItemSerieDBHelperBase.DB_TABLE, null, montaValores(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    public final void update(final ItemSerie item) {
	    try {
	        getDb().update(ItemSerieDBHelperBase.DB_TABLE, montaValores(item), "id_item_serie=" + item.getIdItemSerie(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     // Nao pode ser final o parametro para retornar o objeto com seu Id
    public final void insertSinc(ItemSerie item) {
        try {
        	item.setIdItemSerie((int)getMaxId() + 1);
        	DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc: " + item.debug());
	        long id = getDb().insert(ItemSerieDBHelperBase.DB_TABLE, null, montaValores(item));
    	    ((ObjetoSinc)item).setOperacaoSinc("I");
        	getDb().insert(ItemSerieDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     public final void updateSinc(final ItemSerie item) {
        try {
	        DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc: " + item.debug());
	        getDb().update(ItemSerieDBHelperBase.DB_TABLE, montaValores(item), "id_item_serie=" + item.getIdItemSerie(), null);
	        ((ObjetoSinc)item).setOperacaoSinc("A");
	        ItemSerie atual = this.getSincById(item.getId());
	        if (atual==null) {
	        	getDb().insert(ItemSerieDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	        } else {
	        	if ("I".equals(((ObjetoSinc)atual).getOperacaoSinc()))
	        		((ObjetoSinc)item).setOperacaoSinc("I");
	        	getDb().update(ItemSerieDBHelperBase.DB_TABLE_SINC, montaValoresSinc(item), "id_item_serie=" + item.getIdItemSerie(), null);
	        }
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    protected final void delete(final long id) {
        try {
			getDb().delete(ItemSerieDBHelperBase.DB_TABLE, "id_item_serie=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    /*
    private void deleteSinc(final long id) {
        try {
			getDb().delete(ItemSerieDBHelperBase.DB_TABLE_SINC, "id_item_serie=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
	*/
    public void limpaSinc(final ItemSerie item) {
    	try {
			getDb().delete(ItemSerieDBHelperBase.DB_TABLE_SINC, "id_item_serie=" + item.getId(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    
    public void deleteSinc(final ItemSerie item) {
    	try {
	        DCLog.dStack(DCLog.DATABASE_ADM, this, "deleteSinc: " + item.debug());
	        delete(item.getId());
	        ((ObjetoSinc)item).setOperacaoSinc("D");
        	getDb().insert(ItemSerieDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
   
    
	public void criaTabelaSincronizacao() {
	    try {
			getDb().execSQL(DB_CREATE_SINCRONIZADA);
		
			// Dependente
			//getDb().execSQL(CargaPlanejadaDBHelperBase.DB_CREATE_SINCRONIZADA);
			
		
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
	}
    public void criaTabela() {
        try {
	        getDb().execSQL(DB_CREATE);
	    
			// Dependente
			//getDb().execSQL(CargaPlanejadaDBHelperBase.DB_CREATE);
			
		
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
    public ItemSerie getById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (ItemSerie) getItemQuery(true, ItemSerieDBHelperBase.DB_TABLE, ItemSerieDBHelperBase.COLS, "id_item_serie = " + id + "", null, null, null, null,null);
    }
    
    // Esta com orderBy que pode ser bom para exibicoes em tela
    // mas nao e bom para sincronizacao, pensar em ter um metodo para tela e outro para sinc.
    public List<ItemSerie> getAll() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, null);
    }
    public List<ItemSerie> getAllTela() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, orderByColuna());
    }
    
    private ItemSerie getByRowId(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (ItemSerie) getItemQuery(true, ItemSerieDBHelperBase.DB_TABLE, ItemSerieDBHelperBase.COLS, "ROWID = " + id + "", null, null, null, null,null);
    }
    private ItemSerie getSincById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (ItemSerie) getItemQuerySinc(true, ItemSerieDBHelperBase.DB_TABLE_SINC, ItemSerieDBHelperBase.COLS_SINC, "id_item_serie = " + id + "", null, null, null, null,null);
    }
    
    
    public long getMaxId() {
		String sql = "select max(id_item_serie) from " + DB_TABLE;
		return this.getNumeroSql(sql);
	}
	protected String orderByColuna() {
    	return null;
    }
	
	
	public List<ItemSerie> getPorExecucaoDeExercicio(Context contexto, long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_exercicio_ed = " + id, null, null, null, orderByColuna());
	}
	public List<ItemSerie> getPorExecucaoDeExercicio(long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_exercicio_ed = " + id, null, null, null, orderByColuna());
	}
	
	
	
	public List<ItemSerie> getPorPertencenteASerieTreino(Context contexto, long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_serie_treino_pa = " + id, null, null, null, orderByColuna());
	}
	public List<ItemSerie> getPorPertencenteASerieTreino(long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_serie_treino_pa = " + id, null, null, null, orderByColuna());
	}
	
	
  
  	
  
  
  	
	/*
	public ItemSerie obtemPorCargaPlanejadaPossui(long id) {
		string sql;
		sql = "select " + camposOrdenados() +
			" from " + tabelaSelect() +
			innerJoinCargaPlanejada_Possui() + 
			" where id_carga_planejada = " + id;
		return (ItemSerie) getObjeto(sql);
	}
	*/
	public static String innerJoinCargaPlanejada_Possui() {
		return " inner join " + CargaPlanejadaDBHelperBase.DB_TABLE + " on " + CargaPlanejadaDBHelperBase.DB_TABLE + ".id_item_serie_ra = " + DB_TABLE + ".id_item_serie ";  
	}
	public static String innerJoinSincCargaPlanejada_Possui() {
		return " inner join " + CargaPlanejadaDBHelperBase.DB_TABLE_SINC + " on " + CargaPlanejadaDBHelperBase.DB_TABLE_SINC + ".id_item_serie_ra = " + DB_TABLE_SINC + ".id_item_serie ";  
	}
	public static String outerJoinCargaPlanejada_Possui() {
		return " left outer join " + CargaPlanejadaDBHelperBase.DB_TABLE + " on " + CargaPlanejadaDBHelperBase.DB_TABLE + ".id_item_serie_ra = " + DB_TABLE + ".id_item_serie ";  
	}
	public static String outerJoinSincCargaPlanejada_Possui() {
		return " left outer join " + CargaPlanejadaDBHelperBase.DB_TABLE_SINC + " on " + CargaPlanejadaDBHelperBase.DB_TABLE_SINC + ".id_item_serie_ra = " + DB_TABLE_SINC + ".id_item_serie ";  
	}
 	
	/*
	public ItemSerie obtemPorExecucaoItemSerieGera(long id) {
		string sql;
		sql = "select " + camposOrdenados() +
			" from " + tabelaSelect() +
			innerJoinExecucaoItemSerie_Gera() + 
			" where id_execucao_item_serie = " + id;
		return (ItemSerie) getObjeto(sql);
	}
	*/
	public static String innerJoinExecucaoItemSerie_Gera() {
		return " inner join " + ExecucaoItemSerieDBHelperBase.DB_TABLE + " on " + ExecucaoItemSerieDBHelperBase.DB_TABLE + ".id_item_serie_ra = " + DB_TABLE + ".id_item_serie ";  
	}
	public static String innerJoinSincExecucaoItemSerie_Gera() {
		return " inner join " + ExecucaoItemSerieDBHelperBase.DB_TABLE_SINC + " on " + ExecucaoItemSerieDBHelperBase.DB_TABLE_SINC + ".id_item_serie_ra = " + DB_TABLE_SINC + ".id_item_serie ";  
	}
	public static String outerJoinExecucaoItemSerie_Gera() {
		return " left outer join " + ExecucaoItemSerieDBHelperBase.DB_TABLE + " on " + ExecucaoItemSerieDBHelperBase.DB_TABLE + ".id_item_serie_ra = " + DB_TABLE + ".id_item_serie ";  
	}
	public static String outerJoinSincExecucaoItemSerie_Gera() {
		return " left outer join " + ExecucaoItemSerieDBHelperBase.DB_TABLE_SINC + " on " + ExecucaoItemSerieDBHelperBase.DB_TABLE_SINC + ".id_item_serie_ra = " + DB_TABLE_SINC + ".id_item_serie ";  
	}
 	
	
	
	// Relacionamento onde esse objeto ? chave estrangeira de outro. ????
	
	private boolean _obtemExercicio_ExecucaoDe = false;
	public void setObtemExercicio_ExecucaoDe() {
		_obtemExercicio_ExecucaoDe = true;
	}
	protected String outterJoinExercicio_ExecucaoDe() {
		return " left outer join " + ExercicioDBHelperBase.DB_TABLE + " on " + ExercicioDBHelperBase.DB_TABLE + ".id_exercicio = " + DB_TABLE + ".id_exercicio_ed ";  
	}
	public boolean atualizaExecucaoDeExercicio(long idItemSerie, long idExercicioEd) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_exercicio_ed  = " + idExercicioEd +
        " where id_item_serie = " +  idItemSerie;
       	//this.executaSql(sql);
       	return true;
	}
	public ItemSerie obtemPorIdsExecucaoDeExercicio(long idItemSerie, long idExercicioEd) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_exercicio_ed = " + idExercicioEd + " and " +
			  " id_item_serie = " + idItemSerie;
		return (ItemSerie) this.geObjetoSql(sql);
	}
	
	
	public static String innerJoinExercicio_ExecucaoDe() {
		return " inner join " + ExercicioDBHelperBase.DB_TABLE + " on " + ExercicioDBHelperBase.DB_TABLE + ".id_exercicio = " + DB_TABLE + ".id_exercicio_ed ";  
	}
	public static String outerJoinExercicio_ExecucaoDe() {
		return " left outer join " + ExercicioDBHelperBase.DB_TABLE + " on " + ExercicioDBHelperBase.DB_TABLE + ".id_exercicio = " + DB_TABLE + ".id_exercicio_ed ";  
	}
	public static String innerJoinSincExercicio_ExecucaoDe() {
		return " inner join " + ExercicioDBHelperBase.DB_TABLE_SINC + " on " + ExercicioDBHelperBase.DB_TABLE_SINC + ".id_exercicio = " + DB_TABLE_SINC + ".id_exercicio_ed ";  
	}
	public static String outerJoinSincExercicio_ExecucaoDe() {
		return " left outer join " + ExercicioDBHelperBase.DB_TABLE_SINC + " on " + ExercicioDBHelperBase.DB_TABLE_SINC + ".id_exercicio = " + DB_TABLE_SINC + ".id_exercicio_ed ";  
	}
	
 	
	private boolean _obtemSerieTreino_PertencenteA = false;
	public void setObtemSerieTreino_PertencenteA() {
		_obtemSerieTreino_PertencenteA = true;
	}
	protected String outterJoinSerieTreino_PertencenteA() {
		return " left outer join " + SerieTreinoDBHelperBase.DB_TABLE + " on " + SerieTreinoDBHelperBase.DB_TABLE + ".id_serie_treino = " + DB_TABLE + ".id_serie_treino_pa ";  
	}
	public boolean atualizaPertencenteASerieTreino(long idItemSerie, long idSerieTreinoPa) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_serie_treino_pa  = " + idSerieTreinoPa +
        " where id_item_serie = " +  idItemSerie;
       	//this.executaSql(sql);
       	return true;
	}
	public ItemSerie obtemPorIdsPertencenteASerieTreino(long idItemSerie, long idSerieTreinoPa) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_serie_treino_pa = " + idSerieTreinoPa + " and " +
			  " id_item_serie = " + idItemSerie;
		return (ItemSerie) this.geObjetoSql(sql);
	}
	
	
	public static String innerJoinSerieTreino_PertencenteA() {
		return " inner join " + SerieTreinoDBHelperBase.DB_TABLE + " on " + SerieTreinoDBHelperBase.DB_TABLE + ".id_serie_treino = " + DB_TABLE + ".id_serie_treino_pa ";  
	}
	public static String outerJoinSerieTreino_PertencenteA() {
		return " left outer join " + SerieTreinoDBHelperBase.DB_TABLE + " on " + SerieTreinoDBHelperBase.DB_TABLE + ".id_serie_treino = " + DB_TABLE + ".id_serie_treino_pa ";  
	}
	public static String innerJoinSincSerieTreino_PertencenteA() {
		return " inner join " + SerieTreinoDBHelperBase.DB_TABLE_SINC + " on " + SerieTreinoDBHelperBase.DB_TABLE_SINC + ".id_serie_treino = " + DB_TABLE_SINC + ".id_serie_treino_pa ";  
	}
	public static String outerJoinSincSerieTreino_PertencenteA() {
		return " left outer join " + SerieTreinoDBHelperBase.DB_TABLE_SINC + " on " + SerieTreinoDBHelperBase.DB_TABLE_SINC + ".id_serie_treino = " + DB_TABLE_SINC + ".id_serie_treino_pa ";  
	}
	
 	
	// ********************************************************************	
	
	
	public static String camposOrdenados() 
	{
		return " item_serie.id_item_serie " 
		+ " ,item_serie.repeticoes " 
		+ " ,item_serie.serie " 
		+ " ,item_serie.parametros " 
		+ " ,item_serie.ordem_execucao " 
		+ " ,item_serie.id_exercicio_ed " 
		+ " ,item_serie.id_serie_treino_pa " 
		+ " ,item_serie.id_usuario_s " 
		;
	}
	public static String camposOrdenadosSinc() 
	{
		return " item_serie_sinc.id_item_serie " 
		+ " ,item_serie_sinc.repeticoes " 
		+ " ,item_serie_sinc.serie " 
		+ " ,item_serie_sinc.parametros " 
		+ " ,item_serie_sinc.ordem_execucao " 
		+ " ,item_serie_sinc.id_exercicio_ed " 
		+ " ,item_serie_sinc.id_serie_treino_pa " 
		+ " ,item_serie_sinc.id_usuario_s " 
		
		+ " ,item_serie_sinc.operacao_sinc "
		;
	}
	public static String camposOrdenadosAlias(String nomeTabela) 
	{
		return  nomeTabela + ".id_item_serie " 
		+ " , " + nomeTabela + ".repeticoes " 
		+ " , " + nomeTabela + ".serie " 
		+ " , " + nomeTabela + ".parametros " 
		+ " , " + nomeTabela + ".ordem_execucao " 
		+ " , " + nomeTabela + ".id_exercicio_ed " 
		+ " , " + nomeTabela + ".id_serie_treino_pa " 
		+ " , " + nomeTabela + ".id_usuario_s " 
		;
	}
	
	protected String camposOrdenadosJoin()
    {
        String saida = camposOrdenados();
		saida += (this._obtemExercicio_ExecucaoDe?" , " +ExercicioDBHelperBase.camposOrdenados():"");
		saida += (this._obtemSerieTreino_PertencenteA?" , " +SerieTreinoDBHelperBase.camposOrdenados():"");
        return saida;
    }
    
        
    public void limpaObtem()
    {
		_obtemExercicio_ExecucaoDe = false;
		_obtemSerieTreino_PertencenteA = false;
    }
    
	protected String outterJoinAgrupado()
    {
        String saida = " ";
		saida += (this._obtemExercicio_ExecucaoDe? outterJoinExercicio_ExecucaoDe() + " ":"");
		saida += (this._obtemSerieTreino_PertencenteA? outterJoinSerieTreino_PertencenteA() + " ":"");
        return saida;
    }
    protected MontadorDaoI getMontadorAgrupado()
    {
        MontadorDaoComposite montador = new MontadorDaoComposite();
        montador.adicionaMontador(new ItemSerieMontador(), null);
		if (this._obtemExercicio_ExecucaoDe)
            montador.adicionaMontador(new ExercicioMontador(), "Exercicio_ExecucaoDe");
		if (this._obtemSerieTreino_PertencenteA)
            montador.adicionaMontador(new SerieTreinoMontador(), "SerieTreino_PertencenteA");
         return montador;
    }
	
    
   	// Poderia passar para classe abstrata.
    public final List<ItemSerie> getAllSinc() throws DaoException {
    	this.setMontador(null);
    	List<ItemSerie> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<ItemSerie>();
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
    public final List<ItemSerie> getAllSincSoAlteracao() throws DaoException {
    	List<ItemSerie> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    		saida = null; // Melhorar aqui !!!!
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<ItemSerie>();
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
	
	public final List<ItemSerie> getAllSincImpl() throws DaoException {
   		String sql = "select " + camposOrdenadosSinc()   
   			+ " from " + this.DB_TABLE_SINC;
   		ItemSerieMontador montador = new ItemSerieMontador(true); // sinc ligado
   		this.setMontador(montador);
   		List<ItemSerie> saida = this.getListaSql(sql);
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
		this.insert((ItemSerie) obj);
	}
	@Override
	public final void dropCreate() {
		this.dropTable();
		this.criaTabela();
	}
}
