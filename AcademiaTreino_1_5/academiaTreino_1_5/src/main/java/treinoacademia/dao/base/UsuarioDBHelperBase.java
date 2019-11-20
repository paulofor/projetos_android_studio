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
import treinoacademia.modelo.vo.UsuarioVo;
import treinoacademia.modelo.vo.FabricaVo;
import treinoacademia.dao.*;
import treinoacademia.dao.montador.*;
import treinoacademia.dao.datasource.DataSourceAplicacao;
import treinoacademia.app.TrataErro;

public abstract class UsuarioDBHelperBase extends DBHelperBase
	implements DaoSincronismo // coloquei aqui para evitar impacto de arquitetura
{

    private static final String DB_NAME = "w_alert";
    public static final String DB_TABLE = "usuario";
    public static final String DB_TABLE_SINC = "usuario_sinc";
    public static final int DB_VERSION = 3;

    protected static final String CLASSNAME = UsuarioDBHelperBase.class.getSimpleName();
    protected static final String[] COLS = new String[] { 
        "id_usuario"
        ,"nome_usuario"
        ,"numero_celular"
        ,"melhor_path"
    };

	@Override
	public void erroException(Exception e, DBHelperBase dao) {
		TrataErro.getInstancia().setErro(e, dao);
	}

	protected static final String[] COLS_SINC = new String[] { 
        "id_usuario"
        ,"nome_usuario"
        ,"numero_celular"
        ,"melhor_path"	, "operacao_sinc"
    };

    protected SQLiteDatabase db;
    protected final DBOpenHelper dbOpenHelper;

	@Override
	protected MontadorDaoI criaMontadorPadrao() {
		return new UsuarioMontador();
	}
	
	protected String getSqlIndices() {
		return "";
	}
	protected String getSqlCreate(){
		return  "CREATE TABLE "
        + UsuarioDBHelperBase.DB_TABLE + " ("
        + "  id_usuario INTEGER PRIMARY KEY "
        + " , nome_usuario TEXT "
        + " , numero_celular TEXT "
        + " , melhor_path TEXT "
		+ getSqlIndices()
        + ");";
	}

	
	public Usuario getCorrente() {
		String sql = " select " + camposOrdenados() + " from " + DB_TABLE +
				" limit 1";
		return (Usuario) this.geObjetoSql(sql);
	}
	

	public static final String DB_CREATE_SINCRONIZADA = "CREATE TABLE IF NOT EXISTS "
        + UsuarioDBHelperBase.DB_TABLE_SINC + " ("
        + "  id_usuario INTEGER "
        + " , nome_usuario TEXT "
        + " , numero_celular TEXT "
        + " , melhor_path TEXT "
        + ", operacao_sinc TEXT);";


    public static final String DB_CREATE = "CREATE TABLE IF NOT EXISTS "
        + UsuarioDBHelperBase.DB_TABLE + " ("
        + "  id_usuario INTEGER PRIMARY KEY "
        + " , nome_usuario TEXT "
        + " , numero_celular TEXT "
        + " , melhor_path TEXT "
        + ");";
    
    private static final String DB_DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private static final String DB_DROP = "DROP TABLE IF EXISTS " + DB_TABLE;
    private static final String DB_DROP_SINCRONIZADA = "DROP TABLE IF EXISTS " + DB_TABLE_SINC;
    
    private static class DBOpenHelper extends SQLiteOpenHelper {

       

        public DBOpenHelper(final Context context) {
            super(context, UsuarioDBHelperBase.DB_NAME, null, UsuarioDBHelperBase.DB_VERSION);
        }

        @Override
        public void onCreate(final SQLiteDatabase db) {
            try {
                db.execSQL(DB_CREATE);
            } catch (SQLException e) {
                Log.e(Constants.LOGTAG, UsuarioDBHelperBase.CLASSNAME, e);
            }
        }

        @Override
        public void onOpen(final SQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + UsuarioDBHelperBase.DB_TABLE);
            onCreate(db);
        }
    }

    //
    // end inner classes
    //
    
    // Versao Nova
 	public UsuarioDBHelperBase() {
    	this.dbOpenHelper = null;
    	setDataSource(DataSourceAplicacao.getInstancia());
    }
    
   
	
	protected ContentValues montaValores(final DCIObjetoDominio valor) {
		Usuario item = (Usuario) valor;
		ContentValues valores = new ContentValues();
       	putValor(valores,"id_usuario",item.getIdUsuario());
       	
       	putValor(valores,"nome_usuario",item.getNomeUsuario());
       	
       	putValor(valores,"numero_celular",item.getNumeroCelular());
       	
       	putValor(valores,"melhor_path",item.getMelhorPath());
       	
        return valores;
	}


    // **** Chamadas Diretas Objeto Banco de Dados
    private void registraErroChamadaDireta(Exception e) {
    	Log.e(Constants.LOGTAG, UsuarioDBHelperBase.CLASSNAME, e);
    }
   
    public final void insert(final Usuario item) {
	    try {
	        getDb().insert(UsuarioDBHelperBase.DB_TABLE, null, montaValores(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    public final void update(final Usuario item) {
	    try {
	        getDb().update(UsuarioDBHelperBase.DB_TABLE, montaValores(item), "id_usuario=" + item.getIdUsuario(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     // Nao pode ser final o parametro para retornar o objeto com seu Id
    public final void insertSinc(Usuario item) {
        try {
        	item.setIdUsuario((int)getMaxId() + 1);
        	DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc: " + item.debug());
	        long id = getDb().insert(UsuarioDBHelperBase.DB_TABLE, null, montaValores(item));
    	    ((ObjetoSinc)item).setOperacaoSinc("I");
        	getDb().insert(UsuarioDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     public final void updateSinc(final Usuario item) {
        try {
	        DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc: " + item.debug());
	        getDb().update(UsuarioDBHelperBase.DB_TABLE, montaValores(item), "id_usuario=" + item.getIdUsuario(), null);
	        ((ObjetoSinc)item).setOperacaoSinc("A");
	        Usuario atual = this.getSincById(item.getId());
	        if (atual==null) {
	        	getDb().insert(UsuarioDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	        } else {
	        	if ("I".equals(((ObjetoSinc)atual).getOperacaoSinc()))
	        		((ObjetoSinc)item).setOperacaoSinc("I");
	        	getDb().update(UsuarioDBHelperBase.DB_TABLE_SINC, montaValoresSinc(item), "id_usuario=" + item.getIdUsuario(), null);
	        }
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    protected final void delete(final long id) {
        try {
			getDb().delete(UsuarioDBHelperBase.DB_TABLE, "id_usuario=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    /*
    private void deleteSinc(final long id) {
        try {
			getDb().delete(UsuarioDBHelperBase.DB_TABLE_SINC, "id_usuario=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
	*/
    public void limpaSinc(final Usuario item) {
    	try {
			getDb().delete(UsuarioDBHelperBase.DB_TABLE_SINC, "id_usuario=" + item.getId(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    
    public void deleteSinc(final Usuario item) {
    	try {
	        DCLog.dStack(DCLog.DATABASE_ADM, this, "deleteSinc: " + item.debug());
	        delete(item.getId());
	        ((ObjetoSinc)item).setOperacaoSinc("D");
        	getDb().insert(UsuarioDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
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
    public Usuario getById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (Usuario) getItemQuery(true, UsuarioDBHelperBase.DB_TABLE, UsuarioDBHelperBase.COLS, "id_usuario = " + id + "", null, null, null, null,null);
    }
    
    // Esta com orderBy que pode ser bom para exibicoes em tela
    // mas nao e bom para sincronizacao, pensar em ter um metodo para tela e outro para sinc.
    public List<Usuario> getAll() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, null);
    }
    public List<Usuario> getAllTela() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, orderByColuna());
    }
    
    private Usuario getByRowId(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (Usuario) getItemQuery(true, UsuarioDBHelperBase.DB_TABLE, UsuarioDBHelperBase.COLS, "ROWID = " + id + "", null, null, null, null,null);
    }
    private Usuario getSincById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (Usuario) getItemQuerySinc(true, UsuarioDBHelperBase.DB_TABLE_SINC, UsuarioDBHelperBase.COLS_SINC, "id_usuario = " + id + "", null, null, null, null,null);
    }
    
    
    public long getMaxId() {
		String sql = "select max(id_usuario) from " + DB_TABLE;
		return this.getNumeroSql(sql);
	}
	protected String orderByColuna() {
    	return null;
    }
	
  
  	
  
  
  	
	
	
	// Relacionamento onde esse objeto ? chave estrangeira de outro. ????
	
	// ********************************************************************	
	
	
	public static String camposOrdenados() 
	{
		return " usuario.id_usuario " 
		+ " ,usuario.nome_usuario " 
		+ " ,usuario.numero_celular " 
		+ " ,usuario.melhor_path " 
		;
	}
	public static String camposOrdenadosSinc() 
	{
		return " usuario_sinc.id_usuario " 
		+ " ,usuario_sinc.nome_usuario " 
		+ " ,usuario_sinc.numero_celular " 
		+ " ,usuario_sinc.melhor_path " 
		
		+ " ,usuario_sinc.operacao_sinc "
		;
	}
	public static String camposOrdenadosAlias(String nomeTabela) 
	{
		return  nomeTabela + ".id_usuario " 
		+ " , " + nomeTabela + ".nome_usuario " 
		+ " , " + nomeTabela + ".numero_celular " 
		+ " , " + nomeTabela + ".melhor_path " 
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
        montador.adicionaMontador(new UsuarioMontador(), null);
         return montador;
    }
	
    
   	// Poderia passar para classe abstrata.
    public final List<Usuario> getAllSinc() throws DaoException {
    	this.setMontador(null);
    	List<Usuario> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<Usuario>();
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
    public final List<Usuario> getAllSincSoAlteracao() throws DaoException {
    	List<Usuario> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    		saida = null; // Melhorar aqui !!!!
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<Usuario>();
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
	
	public final List<Usuario> getAllSincImpl() throws DaoException {
   		String sql = "select " + camposOrdenadosSinc()   
   			+ " from " + this.DB_TABLE_SINC;
   		UsuarioMontador montador = new UsuarioMontador(true); // sinc ligado
   		this.setMontador(montador);
   		List<Usuario> saida = this.getListaSql(sql);
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
		this.insert((Usuario) obj);
	}
	@Override
	public final void dropCreate() {
		this.dropTable();
		this.criaTabela();
	}
}
