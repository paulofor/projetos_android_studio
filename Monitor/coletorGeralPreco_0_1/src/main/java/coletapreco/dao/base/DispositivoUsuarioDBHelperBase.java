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
import coletapreco.modelo.vo.DispositivoUsuarioVo;
import coletapreco.modelo.vo.FabricaVo;
import coletapreco.dao.*;
import coletapreco.dao.montador.*;
import coletapreco.dao.datasource.DataSourceAplicacao;

public abstract class DispositivoUsuarioDBHelperBase extends DBHelperBase
	implements DaoSincronismo // coloquei aqui para evitar impacto de arquitetura
{

    private static final String DB_NAME = "w_alert";
    public static final String DB_TABLE = "dispositivo_usuario";
    public static final String DB_TABLE_SINC = "dispositivo_usuario_sinc";
    public static final int DB_VERSION = 3;

    protected static final String CLASSNAME = DispositivoUsuarioDBHelperBase.class.getSimpleName();
    protected static final String[] COLS = new String[] { 
        "id_dispositivo_usuario"
        ,"numero_celular"
        ,"codigo_dispositivo"
        ,"tipo_acesso"
        ,"melhor_path"
		, "id_usuario_ra"
	
    };

	protected static final String[] COLS_SINC = new String[] { 
        "id_dispositivo_usuario"
        ,"numero_celular"
        ,"codigo_dispositivo"
        ,"tipo_acesso"
        ,"melhor_path"
		, "id_usuario_ra"
		, "operacao_sinc"
    };

    protected SQLiteDatabase db;
    protected final DBOpenHelper dbOpenHelper;

	@Override
	protected MontadorDaoI criaMontadorPadrao() {
		return new DispositivoUsuarioMontador();
	}
	
	protected String getSqlIndices() {
		return "";
	}
	protected String getSqlCreate(){
		return  "CREATE TABLE "
        + DispositivoUsuarioDBHelperBase.DB_TABLE + " ("
        + "  id_dispositivo_usuario INTEGER PRIMARY KEY "
        + " , numero_celular TEXT "
        + " , codigo_dispositivo TEXT "
        + " , tipo_acesso TEXT "
        + " , melhor_path TEXT "
		+ " , id_usuario_ra INTEGER "
		
		+ getSqlIndices()
        + ");";
	}

	
	public DispositivoUsuario getCorrente() {
		String sql = " select " + camposOrdenados() + " from " + DB_TABLE +
				" limit 1";
		return (DispositivoUsuario) this.geObjetoSql(sql);
	}
	

	public static final String DB_CREATE_SINCRONIZADA = "CREATE TABLE IF NOT EXISTS "
        + DispositivoUsuarioDBHelperBase.DB_TABLE_SINC + " ("
        + "  id_dispositivo_usuario INTEGER "
        + " , numero_celular TEXT "
        + " , codigo_dispositivo TEXT "
        + " , tipo_acesso TEXT "
        + " , melhor_path TEXT "
		+ " , id_usuario_ra INTEGER "
		
        + ", operacao_sinc TEXT);";


    public static final String DB_CREATE = "CREATE TABLE IF NOT EXISTS "
        + DispositivoUsuarioDBHelperBase.DB_TABLE + " ("
        + "  id_dispositivo_usuario INTEGER PRIMARY KEY "
        + " , numero_celular TEXT "
        + " , codigo_dispositivo TEXT "
        + " , tipo_acesso TEXT "
        + " , melhor_path TEXT "
		+ " , id_usuario_ra INTEGER "
		
        + ");";
    
    private static final String DB_DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private static final String DB_DROP = "DROP TABLE IF EXISTS " + DB_TABLE;
    private static final String DB_DROP_SINCRONIZADA = "DROP TABLE IF EXISTS " + DB_TABLE_SINC;
    
    private static class DBOpenHelper extends SQLiteOpenHelper {

       

        public DBOpenHelper(final Context context) {
            super(context, DispositivoUsuarioDBHelperBase.DB_NAME, null, DispositivoUsuarioDBHelperBase.DB_VERSION);
        }

        @Override
        public void onCreate(final SQLiteDatabase db) {
            try {
                db.execSQL(DB_CREATE);
            } catch (SQLException e) {
                Log.e(Constants.LOGTAG, DispositivoUsuarioDBHelperBase.CLASSNAME, e);
            }
        }

        @Override
        public void onOpen(final SQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DispositivoUsuarioDBHelperBase.DB_TABLE);
            onCreate(db);
        }
    }

    //
    // end inner classes
    //
    
    // Versao Nova
 	public DispositivoUsuarioDBHelperBase() {
    	this.dbOpenHelper = null;
    	setDataSource(DataSourceAplicacao.getInstancia());
    }
    
   
	
	protected ContentValues montaValores(final DCIObjetoDominio valor) {
		DispositivoUsuario item = (DispositivoUsuario) valor;
		ContentValues valores = new ContentValues();
       	putValor(valores,"id_dispositivo_usuario",item.getIdDispositivoUsuario());
       	
       	putValor(valores,"numero_celular",item.getNumeroCelular());
       	
       	putValor(valores,"codigo_dispositivo",item.getCodigoDispositivo());
       	
       	putValor(valores,"tipo_acesso",item.getTipoAcesso());
       	
       	putValor(valores,"melhor_path",item.getMelhorPath());
       	
       	putValor(valores,"id_usuario_ra",item.getIdUsuarioRa());
       	
        return valores;
	}


    // **** Chamadas Diretas Objeto Banco de Dados
    private void registraErroChamadaDireta(Exception e) {
    	Log.e(Constants.LOGTAG, DispositivoUsuarioDBHelperBase.CLASSNAME, e);
    }
   
    public final void insert(final DispositivoUsuario item) {
	    try {
	        getDb().insert(DispositivoUsuarioDBHelperBase.DB_TABLE, null, montaValores(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    public final void update(final DispositivoUsuario item) {
	    try {
	        getDb().update(DispositivoUsuarioDBHelperBase.DB_TABLE, montaValores(item), "id_dispositivo_usuario=" + item.getIdDispositivoUsuario(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     // Nao pode ser final o parametro para retornar o objeto com seu Id
    public final void insertSinc(DispositivoUsuario item) {
        try {
        	item.setIdDispositivoUsuario((int)getMaxId() + 1);
        	DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc: " + item.debug());
	        long id = getDb().insert(DispositivoUsuarioDBHelperBase.DB_TABLE, null, montaValores(item));
    	    ((ObjetoSinc)item).setOperacaoSinc("I");
        	getDb().insert(DispositivoUsuarioDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     public final void updateSinc(final DispositivoUsuario item) {
        try {
	        DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc: " + item.debug());
	        getDb().update(DispositivoUsuarioDBHelperBase.DB_TABLE, montaValores(item), "id_dispositivo_usuario=" + item.getIdDispositivoUsuario(), null);
	        ((ObjetoSinc)item).setOperacaoSinc("A");
	        DispositivoUsuario atual = this.getSincById(item.getId());
	        if (atual==null) {
	        	getDb().insert(DispositivoUsuarioDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	        } else {
	        	if ("I".equals(((ObjetoSinc)atual).getOperacaoSinc()))
	        		((ObjetoSinc)item).setOperacaoSinc("I");
	        	getDb().update(DispositivoUsuarioDBHelperBase.DB_TABLE_SINC, montaValoresSinc(item), "id_dispositivo_usuario=" + item.getIdDispositivoUsuario(), null);
	        }
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    protected final void delete(final long id) {
        try {
			getDb().delete(DispositivoUsuarioDBHelperBase.DB_TABLE, "id_dispositivo_usuario=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    /*
    private void deleteSinc(final long id) {
        try {
			getDb().delete(DispositivoUsuarioDBHelperBase.DB_TABLE_SINC, "id_dispositivo_usuario=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
	*/
    public void limpaSinc(final DispositivoUsuario item) {
    	try {
			getDb().delete(DispositivoUsuarioDBHelperBase.DB_TABLE_SINC, "id_dispositivo_usuario=" + item.getId(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    
    public void deleteSinc(final DispositivoUsuario item) {
    	try {
	        DCLog.dStack(DCLog.DATABASE_ADM, this, "deleteSinc: " + item.debug());
	        delete(item.getId());
	        ((ObjetoSinc)item).setOperacaoSinc("D");
        	getDb().insert(DispositivoUsuarioDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
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
    public DispositivoUsuario getById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (DispositivoUsuario) getItemQuery(true, DispositivoUsuarioDBHelperBase.DB_TABLE, DispositivoUsuarioDBHelperBase.COLS, "id_dispositivo_usuario = " + id + "", null, null, null, null,null);
    }
    
    // Esta com orderBy que pode ser bom para exibicoes em tela
    // mas nao e bom para sincronizacao, pensar em ter um metodo para tela e outro para sinc.
    public List<DispositivoUsuario> getAll() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, null);
    }
    public List<DispositivoUsuario> getAllTela() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, orderByColuna());
    }
    
    private DispositivoUsuario getByRowId(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (DispositivoUsuario) getItemQuery(true, DispositivoUsuarioDBHelperBase.DB_TABLE, DispositivoUsuarioDBHelperBase.COLS, "ROWID = " + id + "", null, null, null, null,null);
    }
    private DispositivoUsuario getSincById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (DispositivoUsuario) getItemQuerySinc(true, DispositivoUsuarioDBHelperBase.DB_TABLE_SINC, DispositivoUsuarioDBHelperBase.COLS_SINC, "id_dispositivo_usuario = " + id + "", null, null, null, null,null);
    }
    
    
    public long getMaxId() {
		String sql = "select max(id_dispositivo_usuario) from " + DB_TABLE;
		return this.getNumeroSql(sql);
	}
	protected String orderByColuna() {
    	return null;
    }
	
	
	public List<DispositivoUsuario> getPorReferenteAUsuario(Context contexto, long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_usuario_ra = " + id, null, null, null, null);
	}
	public List<DispositivoUsuario> getPorReferenteAUsuario(long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_usuario_ra = " + id, null, null, null, null);
	}
	
	
  
  	
  
  
  	
	
	
	// Relacionamento onde esse objeto ? chave estrangeira de outro. ????
	
	private boolean _obtemUsuario_ReferenteA = false;
	public void setObtemUsuario_ReferenteA() {
		_obtemUsuario_ReferenteA = true;
	}
	protected String outterJoinUsuario_ReferenteA() {
		return " left outer join " + UsuarioDBHelperBase.DB_TABLE + " on " + UsuarioDBHelperBase.DB_TABLE + ".id_usuario = " + DB_TABLE + ".id_usuario_ra ";  
	}
	public boolean atualizaReferenteAUsuario(long idDispositivoUsuario, long idUsuarioRa) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_usuario_ra  = " + idUsuarioRa +
        " where id_dispositivo_usuario = " +  idDispositivoUsuario;
       	//this.executaSql(sql);
       	return true;
	}
	public DispositivoUsuario obtemPorIdsReferenteAUsuario(long idDispositivoUsuario, long idUsuarioRa) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_usuario_ra = " + idUsuarioRa + " and " +
			  " id_dispositivo_usuario = " + idDispositivoUsuario;
		return (DispositivoUsuario) this.geObjetoSql(sql);
	}
	
	
	public static String innerJoinUsuario_ReferenteA() {
		return " inner join " + UsuarioDBHelperBase.DB_TABLE + " on " + UsuarioDBHelperBase.DB_TABLE + ".id_usuario = " + DB_TABLE + ".id_usuario_ra ";  
	}
	public static String outerJoinUsuario_ReferenteA() {
		return " left outer join " + UsuarioDBHelperBase.DB_TABLE + " on " + UsuarioDBHelperBase.DB_TABLE + ".id_usuario = " + DB_TABLE + ".id_usuario_ra ";  
	}
	public static String innerJoinSincUsuario_ReferenteA() {
		return " inner join " + UsuarioDBHelperBase.DB_TABLE_SINC + " on " + UsuarioDBHelperBase.DB_TABLE_SINC + ".id_usuario = " + DB_TABLE_SINC + ".id_usuario_ra ";  
	}
	public static String outerJoinSincUsuario_ReferenteA() {
		return " left outer join " + UsuarioDBHelperBase.DB_TABLE_SINC + " on " + UsuarioDBHelperBase.DB_TABLE_SINC + ".id_usuario = " + DB_TABLE_SINC + ".id_usuario_ra ";  
	}
	
 	
	// ********************************************************************	
	
	
	public static String camposOrdenados() 
	{
		return " dispositivo_usuario.id_dispositivo_usuario " 
		+ " ,dispositivo_usuario.numero_celular " 
		+ " ,dispositivo_usuario.codigo_dispositivo " 
		+ " ,dispositivo_usuario.tipo_acesso " 
		+ " ,dispositivo_usuario.melhor_path " 
		+ " ,dispositivo_usuario.id_usuario_ra " 
		;
	}
	public static String camposOrdenadosSinc() 
	{
		return " dispositivo_usuario_sinc.id_dispositivo_usuario " 
		+ " ,dispositivo_usuario_sinc.numero_celular " 
		+ " ,dispositivo_usuario_sinc.codigo_dispositivo " 
		+ " ,dispositivo_usuario_sinc.tipo_acesso " 
		+ " ,dispositivo_usuario_sinc.melhor_path " 
		+ " ,dispositivo_usuario_sinc.id_usuario_ra " 
		
		+ " ,dispositivo_usuario_sinc.operacao_sinc "
		;
	}
	public static String camposOrdenadosAlias(String nomeTabela) 
	{
		return  nomeTabela + ".id_dispositivo_usuario " 
		+ " , " + nomeTabela + ".numero_celular " 
		+ " , " + nomeTabela + ".codigo_dispositivo " 
		+ " , " + nomeTabela + ".tipo_acesso " 
		+ " , " + nomeTabela + ".melhor_path " 
		+ " , " + nomeTabela + ".id_usuario_ra " 
		;
	}
	
	protected String camposOrdenadosJoin()
    {
        String saida = camposOrdenados();
		saida += (this._obtemUsuario_ReferenteA?" , " +UsuarioDBHelperBase.camposOrdenados():"");
        return saida;
    }
    
        
    public void limpaObtem()
    {
		_obtemUsuario_ReferenteA = false;
    }
    
	protected String outterJoinAgrupado()
    {
        String saida = " ";
		saida += (this._obtemUsuario_ReferenteA? outterJoinUsuario_ReferenteA() + " ":"");
        return saida;
    }
    protected MontadorDaoI getMontadorAgrupado()
    {
        MontadorDaoComposite montador = new MontadorDaoComposite();
        montador.adicionaMontador(new DispositivoUsuarioMontador(), null);
		if (this._obtemUsuario_ReferenteA)
            montador.adicionaMontador(new UsuarioMontador(), "Usuario_ReferenteA");
         return montador;
    }
	
    
   	// Poderia passar para classe abstrata.
    public final List<DispositivoUsuario> getAllSinc() throws DaoException {
    	this.setMontador(null);
    	List<DispositivoUsuario> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<DispositivoUsuario>();
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
    public final List<DispositivoUsuario> getAllSincSoAlteracao() throws DaoException {
    	List<DispositivoUsuario> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    		saida = null; // Melhorar aqui !!!!
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<DispositivoUsuario>();
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
	
	public final List<DispositivoUsuario> getAllSincImpl() throws DaoException {
   		String sql = "select " + camposOrdenadosSinc()   
   			+ " from " + this.DB_TABLE_SINC;
   		DispositivoUsuarioMontador montador = new DispositivoUsuarioMontador(true); // sinc ligado
   		this.setMontador(montador);
   		List<DispositivoUsuario> saida = this.getListaSql(sql);
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
		this.insert((DispositivoUsuario) obj);
	}
	@Override
	public final void dropCreate() {
		this.dropTable();
		this.criaTabela();
	}
}
