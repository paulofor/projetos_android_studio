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
import coletapreco.modelo.vo.UsuarioPesquisaVo;
import coletapreco.modelo.vo.FabricaVo;
import coletapreco.dao.*;
import coletapreco.dao.montador.*;
import coletapreco.dao.datasource.DataSourceAplicacao;

public abstract class UsuarioPesquisaDBHelperBase extends DBHelperBase
	implements DaoSincronismo // coloquei aqui para evitar impacto de arquitetura
{

    private static final String DB_NAME = "w_alert";
    public static final String DB_TABLE = "usuario_pesquisa";
    public static final String DB_TABLE_SINC = "usuario_pesquisa_sinc";
    public static final int DB_VERSION = 3;

    protected static final String CLASSNAME = UsuarioPesquisaDBHelperBase.class.getSimpleName();
    protected static final String[] COLS = new String[] { 
        "id_usuario_pesquisa"
		, "id_usuario_s"
	
		, "id_natureza_produto_p"
	
    };

	protected static final String[] COLS_SINC = new String[] { 
        "id_usuario_pesquisa"
		, "id_usuario_s"
	
		, "id_natureza_produto_p"
		, "operacao_sinc"
    };

    protected SQLiteDatabase db;
    protected final DBOpenHelper dbOpenHelper;

	@Override
	protected MontadorDaoI criaMontadorPadrao() {
		return new UsuarioPesquisaMontador();
	}
	
	protected String getSqlIndices() {
		return "";
	}
	protected String getSqlCreate(){
		return  "CREATE TABLE "
        + UsuarioPesquisaDBHelperBase.DB_TABLE + " ("
        + "  id_usuario_pesquisa INTEGER PRIMARY KEY "
		+ " , id_usuario_s INTEGER "
		
		+ " , id_natureza_produto_p INTEGER "
		
		+ getSqlIndices()
        + ");";
	}

	

	public static final String DB_CREATE_SINCRONIZADA = "CREATE TABLE IF NOT EXISTS "
        + UsuarioPesquisaDBHelperBase.DB_TABLE_SINC + " ("
        + "  id_usuario_pesquisa INTEGER "
		+ " , id_usuario_s INTEGER "
		
		+ " , id_natureza_produto_p INTEGER "
		
        + ", operacao_sinc TEXT);";


    public static final String DB_CREATE = "CREATE TABLE IF NOT EXISTS "
        + UsuarioPesquisaDBHelperBase.DB_TABLE + " ("
        + "  id_usuario_pesquisa INTEGER PRIMARY KEY "
		+ " , id_usuario_s INTEGER "
		
		+ " , id_natureza_produto_p INTEGER "
		
        + ");";
    
    private static final String DB_DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private static final String DB_DROP = "DROP TABLE IF EXISTS " + DB_TABLE;
    private static final String DB_DROP_SINCRONIZADA = "DROP TABLE IF EXISTS " + DB_TABLE_SINC;
    
    private static class DBOpenHelper extends SQLiteOpenHelper {

       

        public DBOpenHelper(final Context context) {
            super(context, UsuarioPesquisaDBHelperBase.DB_NAME, null, UsuarioPesquisaDBHelperBase.DB_VERSION);
        }

        @Override
        public void onCreate(final SQLiteDatabase db) {
            try {
                db.execSQL(DB_CREATE);
            } catch (SQLException e) {
                Log.e(Constants.LOGTAG, UsuarioPesquisaDBHelperBase.CLASSNAME, e);
            }
        }

        @Override
        public void onOpen(final SQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + UsuarioPesquisaDBHelperBase.DB_TABLE);
            onCreate(db);
        }
    }

    //
    // end inner classes
    //
    
    // Versao Nova
 	public UsuarioPesquisaDBHelperBase() {
    	this.dbOpenHelper = null;
    	setDataSource(DataSourceAplicacao.getInstancia());
    }
    
   
	
	protected ContentValues montaValores(final DCIObjetoDominio valor) {
		UsuarioPesquisa item = (UsuarioPesquisa) valor;
		ContentValues valores = new ContentValues();
       	putValor(valores,"id_usuario_pesquisa",item.getIdUsuarioPesquisa());
       	
       	putValor(valores,"id_usuario_s",item.getIdUsuarioS());
       	
       	putValor(valores,"id_natureza_produto_p",item.getIdNaturezaProdutoP());
       	
        return valores;
	}


    // **** Chamadas Diretas Objeto Banco de Dados
    private void registraErroChamadaDireta(Exception e) {
    	Log.e(Constants.LOGTAG, UsuarioPesquisaDBHelperBase.CLASSNAME, e);
    }
   
    public final void insert(final UsuarioPesquisa item) {
	    try {
	        getDb().insert(UsuarioPesquisaDBHelperBase.DB_TABLE, null, montaValores(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    public final void update(final UsuarioPesquisa item) {
	    try {
	        getDb().update(UsuarioPesquisaDBHelperBase.DB_TABLE, montaValores(item), "id_usuario_pesquisa=" + item.getIdUsuarioPesquisa(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     // Nao pode ser final o parametro para retornar o objeto com seu Id
    public final void insertSinc(UsuarioPesquisa item) {
        try {
        	item.setIdUsuarioPesquisa((int)getMaxId() + 1);
        	DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc: " + item.debug());
	        long id = getDb().insert(UsuarioPesquisaDBHelperBase.DB_TABLE, null, montaValores(item));
    	    ((ObjetoSinc)item).setOperacaoSinc("I");
        	getDb().insert(UsuarioPesquisaDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     public final void updateSinc(final UsuarioPesquisa item) {
        try {
	        DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc: " + item.debug());
	        getDb().update(UsuarioPesquisaDBHelperBase.DB_TABLE, montaValores(item), "id_usuario_pesquisa=" + item.getIdUsuarioPesquisa(), null);
	        ((ObjetoSinc)item).setOperacaoSinc("A");
	        UsuarioPesquisa atual = this.getSincById(item.getId());
	        if (atual==null) {
	        	getDb().insert(UsuarioPesquisaDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	        } else {
	        	if ("I".equals(((ObjetoSinc)atual).getOperacaoSinc()))
	        		((ObjetoSinc)item).setOperacaoSinc("I");
	        	getDb().update(UsuarioPesquisaDBHelperBase.DB_TABLE_SINC, montaValoresSinc(item), "id_usuario_pesquisa=" + item.getIdUsuarioPesquisa(), null);
	        }
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    protected final void delete(final long id) {
        try {
			getDb().delete(UsuarioPesquisaDBHelperBase.DB_TABLE, "id_usuario_pesquisa=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    /*
    private void deleteSinc(final long id) {
        try {
			getDb().delete(UsuarioPesquisaDBHelperBase.DB_TABLE_SINC, "id_usuario_pesquisa=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
	*/
    public void limpaSinc(final UsuarioPesquisa item) {
    	try {
			getDb().delete(UsuarioPesquisaDBHelperBase.DB_TABLE_SINC, "id_usuario_pesquisa=" + item.getId(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    
    public void deleteSinc(final UsuarioPesquisa item) {
    	try {
	        DCLog.dStack(DCLog.DATABASE_ADM, this, "deleteSinc: " + item.debug());
	        delete(item.getId());
	        ((ObjetoSinc)item).setOperacaoSinc("D");
        	getDb().insert(UsuarioPesquisaDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
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
    public UsuarioPesquisa getById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (UsuarioPesquisa) getItemQuery(true, UsuarioPesquisaDBHelperBase.DB_TABLE, UsuarioPesquisaDBHelperBase.COLS, "id_usuario_pesquisa = " + id + "", null, null, null, null,null);
    }
    
    // Esta com orderBy que pode ser bom para exibicoes em tela
    // mas nao e bom para sincronizacao, pensar em ter um metodo para tela e outro para sinc.
    public List<UsuarioPesquisa> getAll() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, null);
    }
    public List<UsuarioPesquisa> getAllTela() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, orderByColuna());
    }
    
    private UsuarioPesquisa getByRowId(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (UsuarioPesquisa) getItemQuery(true, UsuarioPesquisaDBHelperBase.DB_TABLE, UsuarioPesquisaDBHelperBase.COLS, "ROWID = " + id + "", null, null, null, null,null);
    }
    private UsuarioPesquisa getSincById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (UsuarioPesquisa) getItemQuerySinc(true, UsuarioPesquisaDBHelperBase.DB_TABLE_SINC, UsuarioPesquisaDBHelperBase.COLS_SINC, "id_usuario_pesquisa = " + id + "", null, null, null, null,null);
    }
    
    
    public long getMaxId() {
		String sql = "select max(id_usuario_pesquisa) from " + DB_TABLE;
		return this.getNumeroSql(sql);
	}
	protected String orderByColuna() {
    	return null;
    }
	
	
	public List<UsuarioPesquisa> getPorPesquisaNaturezaProduto(Context contexto, long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_natureza_produto_p = " + id, null, null, null, null);
	}
	public List<UsuarioPesquisa> getPorPesquisaNaturezaProduto(long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_natureza_produto_p = " + id, null, null, null, null);
	}
	
	
  
  	
  
  
  	
	
	
	// Relacionamento onde esse objeto ? chave estrangeira de outro. ????
	
	private boolean _obtemNaturezaProduto_Pesquisa = false;
	public void setObtemNaturezaProduto_Pesquisa() {
		_obtemNaturezaProduto_Pesquisa = true;
	}
	protected String outterJoinNaturezaProduto_Pesquisa() {
		return " left outer join " + NaturezaProdutoDBHelperBase.DB_TABLE + " on " + NaturezaProdutoDBHelperBase.DB_TABLE + ".id_natureza_produto = " + DB_TABLE + ".id_natureza_produto_p ";  
	}
	public boolean atualizaPesquisaNaturezaProduto(long idUsuarioPesquisa, long idNaturezaProdutoP) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_natureza_produto_p  = " + idNaturezaProdutoP +
        " where id_usuario_pesquisa = " +  idUsuarioPesquisa;
       	//this.executaSql(sql);
       	return true;
	}
	public UsuarioPesquisa obtemPorIdsPesquisaNaturezaProduto(long idUsuarioPesquisa, long idNaturezaProdutoP) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_natureza_produto_p = " + idNaturezaProdutoP + " and " +
			  " id_usuario_pesquisa = " + idUsuarioPesquisa;
		return (UsuarioPesquisa) this.geObjetoSql(sql);
	}
	
	
	public static String innerJoinNaturezaProduto_Pesquisa() {
		return " inner join " + NaturezaProdutoDBHelperBase.DB_TABLE + " on " + NaturezaProdutoDBHelperBase.DB_TABLE + ".id_natureza_produto = " + DB_TABLE + ".id_natureza_produto_p ";  
	}
	public static String outerJoinNaturezaProduto_Pesquisa() {
		return " left outer join " + NaturezaProdutoDBHelperBase.DB_TABLE + " on " + NaturezaProdutoDBHelperBase.DB_TABLE + ".id_natureza_produto = " + DB_TABLE + ".id_natureza_produto_p ";  
	}
	public static String innerJoinSincNaturezaProduto_Pesquisa() {
		return " inner join " + NaturezaProdutoDBHelperBase.DB_TABLE_SINC + " on " + NaturezaProdutoDBHelperBase.DB_TABLE_SINC + ".id_natureza_produto = " + DB_TABLE_SINC + ".id_natureza_produto_p ";  
	}
	public static String outerJoinSincNaturezaProduto_Pesquisa() {
		return " left outer join " + NaturezaProdutoDBHelperBase.DB_TABLE_SINC + " on " + NaturezaProdutoDBHelperBase.DB_TABLE_SINC + ".id_natureza_produto = " + DB_TABLE_SINC + ".id_natureza_produto_p ";  
	}
	
 	
	// ********************************************************************	
	
	
	public static String camposOrdenados() 
	{
		return " usuario_pesquisa.id_usuario_pesquisa " 
		+ " ,usuario_pesquisa.id_usuario_s " 
		+ " ,usuario_pesquisa.id_natureza_produto_p " 
		;
	}
	public static String camposOrdenadosSinc() 
	{
		return " usuario_pesquisa_sinc.id_usuario_pesquisa " 
		+ " ,usuario_pesquisa_sinc.id_usuario_s " 
		+ " ,usuario_pesquisa_sinc.id_natureza_produto_p " 
		
		+ " ,usuario_pesquisa_sinc.operacao_sinc "
		;
	}
	public static String camposOrdenadosAlias(String nomeTabela) 
	{
		return  nomeTabela + ".id_usuario_pesquisa " 
		+ " , " + nomeTabela + ".id_usuario_s " 
		+ " , " + nomeTabela + ".id_natureza_produto_p " 
		;
	}
	
	protected String camposOrdenadosJoin()
    {
        String saida = camposOrdenados();
		saida += (this._obtemNaturezaProduto_Pesquisa?" , " +NaturezaProdutoDBHelperBase.camposOrdenados():"");
        return saida;
    }
    
        
    public void limpaObtem()
    {
		_obtemNaturezaProduto_Pesquisa = false;
    }
    
	protected String outterJoinAgrupado()
    {
        String saida = " ";
		saida += (this._obtemNaturezaProduto_Pesquisa? outterJoinNaturezaProduto_Pesquisa() + " ":"");
        return saida;
    }
    protected MontadorDaoI getMontadorAgrupado()
    {
        MontadorDaoComposite montador = new MontadorDaoComposite();
        montador.adicionaMontador(new UsuarioPesquisaMontador(), null);
		if (this._obtemNaturezaProduto_Pesquisa)
            montador.adicionaMontador(new NaturezaProdutoMontador(), "NaturezaProduto_Pesquisa");
         return montador;
    }
	
    
   	// Poderia passar para classe abstrata.
    public final List<UsuarioPesquisa> getAllSinc() throws DaoException {
    	this.setMontador(null);
    	List<UsuarioPesquisa> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<UsuarioPesquisa>();
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
    public final List<UsuarioPesquisa> getAllSincSoAlteracao() throws DaoException {
    	List<UsuarioPesquisa> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    		saida = null; // Melhorar aqui !!!!
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<UsuarioPesquisa>();
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
	
	public final List<UsuarioPesquisa> getAllSincImpl() throws DaoException {
   		String sql = "select " + camposOrdenadosSinc()   
   			+ " from " + this.DB_TABLE_SINC;
   		UsuarioPesquisaMontador montador = new UsuarioPesquisaMontador(true); // sinc ligado
   		this.setMontador(montador);
   		List<UsuarioPesquisa> saida = this.getListaSql(sql);
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
		this.insert((UsuarioPesquisa) obj);
	}
	@Override
	public final void dropCreate() {
		this.dropTable();
		this.criaTabela();
	}
}
