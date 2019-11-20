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
import repcom.modelo.vo.ClienteInteresseCategoriaVo;
import repcom.modelo.vo.FabricaVo;
import repcom.dao.*;
import repcom.dao.montador.*;
import repcom.dao.datasource.DataSourceAplicacao;

public abstract class ClienteInteresseCategoriaDBHelperBase extends DBHelperBase
	implements DaoSincronismo // coloquei aqui para evitar impacto de arquitetura
{

    private static final String DB_NAME = "w_alert";
    public static final String DB_TABLE = "cliente_interesse_categoria";
    public static final String DB_TABLE_SINC = "cliente_interesse_categoria_sinc";
    public static final int DB_VERSION = 3;

    protected static final String CLASSNAME = ClienteInteresseCategoriaDBHelperBase.class.getSimpleName();
    protected static final String[] COLS = new String[] { 
        "id_cliente_interesse_categoria"
		, "id_cliente_a"
	
		, "id_categoria_produto_a"
	
    };

	protected static final String[] COLS_SINC = new String[] { 
        "id_cliente_interesse_categoria"
		, "id_cliente_a"
	
		, "id_categoria_produto_a"
		, "operacao_sinc"
    };

    protected SQLiteDatabase db;
    protected final DBOpenHelper dbOpenHelper;

	@Override
	protected MontadorDaoI criaMontadorPadrao() {
		return new ClienteInteresseCategoriaMontador();
	}
	
	protected String getSqlIndices() {
		return "";
	}
	protected String getSqlCreate(){
		return  "CREATE TABLE "
        + ClienteInteresseCategoriaDBHelperBase.DB_TABLE + " ("
        + "  id_cliente_interesse_categoria INTEGER PRIMARY KEY "
		+ " , id_cliente_a INTEGER "
		
		+ " , id_categoria_produto_a INTEGER "
		
		+ getSqlIndices()
        + ");";
	}

	

	public static final String DB_CREATE_SINCRONIZADA = "CREATE TABLE IF NOT EXISTS "
        + ClienteInteresseCategoriaDBHelperBase.DB_TABLE_SINC + " ("
        + "  id_cliente_interesse_categoria INTEGER "
		+ " , id_cliente_a INTEGER "
		
		+ " , id_categoria_produto_a INTEGER "
		
        + ", operacao_sinc TEXT);";


    public static final String DB_CREATE = "CREATE TABLE IF NOT EXISTS "
        + ClienteInteresseCategoriaDBHelperBase.DB_TABLE + " ("
        + "  id_cliente_interesse_categoria INTEGER PRIMARY KEY "
		+ " , id_cliente_a INTEGER "
		
		+ " , id_categoria_produto_a INTEGER "
		
        + ");";
    
    private static final String DB_DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private static final String DB_DROP = "DROP TABLE IF EXISTS " + DB_TABLE;
    private static final String DB_DROP_SINCRONIZADA = "DROP TABLE IF EXISTS " + DB_TABLE_SINC;
    
    private static class DBOpenHelper extends SQLiteOpenHelper {

       

        public DBOpenHelper(final Context context) {
            super(context, ClienteInteresseCategoriaDBHelperBase.DB_NAME, null, ClienteInteresseCategoriaDBHelperBase.DB_VERSION);
        }

        @Override
        public void onCreate(final SQLiteDatabase db) {
            try {
                db.execSQL(DB_CREATE);
            } catch (SQLException e) {
                Log.e(Constants.LOGTAG, ClienteInteresseCategoriaDBHelperBase.CLASSNAME, e);
            }
        }

        @Override
        public void onOpen(final SQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + ClienteInteresseCategoriaDBHelperBase.DB_TABLE);
            onCreate(db);
        }
    }

    //
    // end inner classes
    //
    
    // Versao Nova
 	public ClienteInteresseCategoriaDBHelperBase() {
    	this.dbOpenHelper = null;
    	setDataSource(DataSourceAplicacao.getInstancia());
    }
    
   
	
	protected ContentValues montaValores(final DCIObjetoDominio valor) {
		ClienteInteresseCategoria item = (ClienteInteresseCategoria) valor;
		ContentValues valores = new ContentValues();
       	putValor(valores,"id_cliente_interesse_categoria",item.getIdClienteInteresseCategoria());
       	
       	putValor(valores,"id_cliente_a",item.getIdClienteA());
       	
       	putValor(valores,"id_categoria_produto_a",item.getIdCategoriaProdutoA());
       	
        return valores;
	}


    // **** Chamadas Diretas Objeto Banco de Dados
    private void registraErroChamadaDireta(Exception e) {
    	Log.e(Constants.LOGTAG, ClienteInteresseCategoriaDBHelperBase.CLASSNAME, e);
    }
   
    public final void insert(final ClienteInteresseCategoria item) {
	    try {
	        getDb().insert(ClienteInteresseCategoriaDBHelperBase.DB_TABLE, null, montaValores(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    public final void update(final ClienteInteresseCategoria item) {
	    try {
	        getDb().update(ClienteInteresseCategoriaDBHelperBase.DB_TABLE, montaValores(item), "id_cliente_interesse_categoria=" + item.getIdClienteInteresseCategoria(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     // Nao pode ser final o parametro para retornar o objeto com seu Id
    public final void insertSinc(ClienteInteresseCategoria item) {
        try {
        	item.setIdClienteInteresseCategoria((int)getMaxId() + 1);
        	DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc: " + item.debug());
	        long id = getDb().insert(ClienteInteresseCategoriaDBHelperBase.DB_TABLE, null, montaValores(item));
    	    ((ObjetoSinc)item).setOperacaoSinc("I");
        	getDb().insert(ClienteInteresseCategoriaDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     public final void updateSinc(final ClienteInteresseCategoria item) {
        try {
	        DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc: " + item.debug());
	        getDb().update(ClienteInteresseCategoriaDBHelperBase.DB_TABLE, montaValores(item), "id_cliente_interesse_categoria=" + item.getIdClienteInteresseCategoria(), null);
	        ((ObjetoSinc)item).setOperacaoSinc("A");
	        ClienteInteresseCategoria atual = this.getSincById(item.getId());
	        if (atual==null) {
	        	getDb().insert(ClienteInteresseCategoriaDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	        } else {
	        	if ("I".equals(((ObjetoSinc)atual).getOperacaoSinc()))
	        		((ObjetoSinc)item).setOperacaoSinc("I");
	        	getDb().update(ClienteInteresseCategoriaDBHelperBase.DB_TABLE_SINC, montaValoresSinc(item), "id_cliente_interesse_categoria=" + item.getIdClienteInteresseCategoria(), null);
	        }
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    protected final void delete(final long id) {
        try {
			getDb().delete(ClienteInteresseCategoriaDBHelperBase.DB_TABLE, "id_cliente_interesse_categoria=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    /*
    private void deleteSinc(final long id) {
        try {
			getDb().delete(ClienteInteresseCategoriaDBHelperBase.DB_TABLE_SINC, "id_cliente_interesse_categoria=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
	*/
    public void limpaSinc(final ClienteInteresseCategoria item) {
    	try {
			getDb().delete(ClienteInteresseCategoriaDBHelperBase.DB_TABLE_SINC, "id_cliente_interesse_categoria=" + item.getId(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    
    public void deleteSinc(final ClienteInteresseCategoria item) {
    	try {
	        DCLog.dStack(DCLog.DATABASE_ADM, this, "deleteSinc: " + item.debug());
	        delete(item.getId());
	        ((ObjetoSinc)item).setOperacaoSinc("D");
        	getDb().insert(ClienteInteresseCategoriaDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
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
    public ClienteInteresseCategoria getById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (ClienteInteresseCategoria) getItemQuery(true, ClienteInteresseCategoriaDBHelperBase.DB_TABLE, ClienteInteresseCategoriaDBHelperBase.COLS, "id_cliente_interesse_categoria = " + id + "", null, null, null, null,null);
    }
    
    // Esta com orderBy que pode ser bom para exibicoes em tela
    // mas nao e bom para sincronizacao, pensar em ter um metodo para tela e outro para sinc.
    public List<ClienteInteresseCategoria> getAll() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, null);
    }
    public List<ClienteInteresseCategoria> getAllTela() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, orderByColuna());
    }
    
    private ClienteInteresseCategoria getByRowId(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (ClienteInteresseCategoria) getItemQuery(true, ClienteInteresseCategoriaDBHelperBase.DB_TABLE, ClienteInteresseCategoriaDBHelperBase.COLS, "ROWID = " + id + "", null, null, null, null,null);
    }
    private ClienteInteresseCategoria getSincById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (ClienteInteresseCategoria) getItemQuerySinc(true, ClienteInteresseCategoriaDBHelperBase.DB_TABLE_SINC, ClienteInteresseCategoriaDBHelperBase.COLS_SINC, "id_cliente_interesse_categoria = " + id + "", null, null, null, null,null);
    }
    
    
    public long getMaxId() {
		String sql = "select max(id_cliente_interesse_categoria) from " + DB_TABLE;
		return this.getNumeroSql(sql);
	}
	protected String orderByColuna() {
    	return null;
    }
	
	
	// Classe e relacionamento
	public List<ClienteInteresseCategoria> getPorAssociadaCliente(Context contexto, long id) throws DaoException{
		String sql = "select " + camposOrdenados() + 
				" , " + CategoriaProdutoDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinCategoriaProduto_Associada() +
				" where id_cliente_a = " + id;
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new ClienteInteresseCategoriaMontador(), null);
		montador.adicionaMontador(new CategoriaProdutoMontador(), "CategoriaProduto_Associada");
		setMontador(montador);
		return this.getListaSqlListaInterna(sql);
			
	}
	public List<ClienteInteresseCategoria> getPorAssociadaCliente(long id) throws DaoException{
		String sql = "select " + camposOrdenados() + 
				" , " + CategoriaProdutoDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinCategoriaProduto_Associada() +
				" where id_cliente_a = " + id;
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new ClienteInteresseCategoriaMontador(), null);
		montador.adicionaMontador(new CategoriaProdutoMontador(), "CategoriaProduto_Associada");
		setMontador(montador);
		return this.getListaSqlListaInterna(sql);
			
	}
	public List<CategoriaProduto> getCategoriaProdutoPorAssociadaCliente(Context contexto, long id) throws DaoException{
		String sql = "select " + CategoriaProdutoDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinCategoriaProduto_Associada() +
				" where id_cliente_a = " + id;
		setMontador(new CategoriaProdutoMontador());
		List<CategoriaProduto> saida = this.getListaSql(sql);
		setMontador(null);
		return saida;
	}
	
	
	
	// Classe e relacionamento
	public List<ClienteInteresseCategoria> getPorAssociadaCategoriaProduto(Context contexto, long id) throws DaoException{
		String sql = "select " + camposOrdenados() + 
				" , " + ClienteDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinCliente_Associada() +
				" where id_categoria_produto_a = " + id;
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new ClienteInteresseCategoriaMontador(), null);
		montador.adicionaMontador(new ClienteMontador(), "Cliente_Associada");
		setMontador(montador);
		return this.getListaSqlListaInterna(sql);
			
	}
	public List<ClienteInteresseCategoria> getPorAssociadaCategoriaProduto(long id) throws DaoException{
		String sql = "select " + camposOrdenados() + 
				" , " + ClienteDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinCliente_Associada() +
				" where id_categoria_produto_a = " + id;
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new ClienteInteresseCategoriaMontador(), null);
		montador.adicionaMontador(new ClienteMontador(), "Cliente_Associada");
		setMontador(montador);
		return this.getListaSqlListaInterna(sql);
			
	}
	public List<Cliente> getClientePorAssociadaCategoriaProduto(Context contexto, long id) throws DaoException{
		String sql = "select " + ClienteDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinCliente_Associada() +
				" where id_categoria_produto_a = " + id;
		setMontador(new ClienteMontador());
		List<Cliente> saida = this.getListaSql(sql);
		setMontador(null);
		return saida;
	}
	
	
  
  	
	public ClienteInteresseCategoria getPorClienteCategoriaProduto(long idXXXX, long idYYYYY) {
		return null;
	}
	
  
  
  	
	
	
	// Relacionamento onde esse objeto ? chave estrangeira de outro. ????
	
	private boolean _obtemCliente_Associada = false;
	public void setObtemCliente_Associada() {
		_obtemCliente_Associada = true;
	}
	protected String outterJoinCliente_Associada() {
		return " left outer join " + ClienteDBHelperBase.DB_TABLE + " on " + ClienteDBHelperBase.DB_TABLE + ".id_cliente = " + DB_TABLE + ".id_cliente_a ";  
	}
	public boolean atualizaAssociadaCliente(long idClienteInteresseCategoria, long idClienteA) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_cliente_a  = " + idClienteA +
        " where id_cliente_interesse_categoria = " +  idClienteInteresseCategoria;
       	//this.executaSql(sql);
       	return true;
	}
	public ClienteInteresseCategoria obtemPorIdsAssociadaCliente(long idClienteInteresseCategoria, long idClienteA) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_cliente_a = " + idClienteA + " and " +
			  " id_cliente_interesse_categoria = " + idClienteInteresseCategoria;
		return (ClienteInteresseCategoria) this.geObjetoSql(sql);
	}
	
	
	public static String innerJoinCliente_Associada() {
		return " inner join " + ClienteDBHelperBase.DB_TABLE + " on " + ClienteDBHelperBase.DB_TABLE + ".id_cliente = " + DB_TABLE + ".id_cliente_a ";  
	}
	public static String outerJoinCliente_Associada() {
		return " left outer join " + ClienteDBHelperBase.DB_TABLE + " on " + ClienteDBHelperBase.DB_TABLE + ".id_cliente = " + DB_TABLE + ".id_cliente_a ";  
	}
	public static String innerJoinSincCliente_Associada() {
		return " inner join " + ClienteDBHelperBase.DB_TABLE_SINC + " on " + ClienteDBHelperBase.DB_TABLE_SINC + ".id_cliente = " + DB_TABLE_SINC + ".id_cliente_a ";  
	}
	public static String outerJoinSincCliente_Associada() {
		return " left outer join " + ClienteDBHelperBase.DB_TABLE_SINC + " on " + ClienteDBHelperBase.DB_TABLE_SINC + ".id_cliente = " + DB_TABLE_SINC + ".id_cliente_a ";  
	}
	
 	
	private boolean _obtemCategoriaProduto_Associada = false;
	public void setObtemCategoriaProduto_Associada() {
		_obtemCategoriaProduto_Associada = true;
	}
	protected String outterJoinCategoriaProduto_Associada() {
		return " left outer join " + CategoriaProdutoDBHelperBase.DB_TABLE + " on " + CategoriaProdutoDBHelperBase.DB_TABLE + ".id_categoria_produto = " + DB_TABLE + ".id_categoria_produto_a ";  
	}
	public boolean atualizaAssociadaCategoriaProduto(long idClienteInteresseCategoria, long idCategoriaProdutoA) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_categoria_produto_a  = " + idCategoriaProdutoA +
        " where id_cliente_interesse_categoria = " +  idClienteInteresseCategoria;
       	//this.executaSql(sql);
       	return true;
	}
	public ClienteInteresseCategoria obtemPorIdsAssociadaCategoriaProduto(long idClienteInteresseCategoria, long idCategoriaProdutoA) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_categoria_produto_a = " + idCategoriaProdutoA + " and " +
			  " id_cliente_interesse_categoria = " + idClienteInteresseCategoria;
		return (ClienteInteresseCategoria) this.geObjetoSql(sql);
	}
	
	
	public static String innerJoinCategoriaProduto_Associada() {
		return " inner join " + CategoriaProdutoDBHelperBase.DB_TABLE + " on " + CategoriaProdutoDBHelperBase.DB_TABLE + ".id_categoria_produto = " + DB_TABLE + ".id_categoria_produto_a ";  
	}
	public static String outerJoinCategoriaProduto_Associada() {
		return " left outer join " + CategoriaProdutoDBHelperBase.DB_TABLE + " on " + CategoriaProdutoDBHelperBase.DB_TABLE + ".id_categoria_produto = " + DB_TABLE + ".id_categoria_produto_a ";  
	}
	public static String innerJoinSincCategoriaProduto_Associada() {
		return " inner join " + CategoriaProdutoDBHelperBase.DB_TABLE_SINC + " on " + CategoriaProdutoDBHelperBase.DB_TABLE_SINC + ".id_categoria_produto = " + DB_TABLE_SINC + ".id_categoria_produto_a ";  
	}
	public static String outerJoinSincCategoriaProduto_Associada() {
		return " left outer join " + CategoriaProdutoDBHelperBase.DB_TABLE_SINC + " on " + CategoriaProdutoDBHelperBase.DB_TABLE_SINC + ".id_categoria_produto = " + DB_TABLE_SINC + ".id_categoria_produto_a ";  
	}
	
 	
	// ********************************************************************	
	
	
	public static String camposOrdenados() 
	{
		return " cliente_interesse_categoria.id_cliente_interesse_categoria " 
		+ " ,cliente_interesse_categoria.id_cliente_a " 
		+ " ,cliente_interesse_categoria.id_categoria_produto_a " 
		;
	}
	public static String camposOrdenadosSinc() 
	{
		return " cliente_interesse_categoria_sinc.id_cliente_interesse_categoria " 
		+ " ,cliente_interesse_categoria_sinc.id_cliente_a " 
		+ " ,cliente_interesse_categoria_sinc.id_categoria_produto_a " 
		
		+ " ,cliente_interesse_categoria_sinc.operacao_sinc "
		;
	}
	public static String camposOrdenadosAlias(String nomeTabela) 
	{
		return  nomeTabela + ".id_cliente_interesse_categoria " 
		+ " , " + nomeTabela + ".id_cliente_a " 
		+ " , " + nomeTabela + ".id_categoria_produto_a " 
		;
	}
	
	protected String camposOrdenadosJoin()
    {
        String saida = camposOrdenados();
		saida += (this._obtemCliente_Associada?" , " +ClienteDBHelperBase.camposOrdenados():"");
		saida += (this._obtemCategoriaProduto_Associada?" , " +CategoriaProdutoDBHelperBase.camposOrdenados():"");
        return saida;
    }
    
        
    public void limpaObtem()
    {
		_obtemCliente_Associada = false;
		_obtemCategoriaProduto_Associada = false;
    }
    
	protected String outterJoinAgrupado()
    {
        String saida = " ";
		saida += (this._obtemCliente_Associada? outterJoinCliente_Associada() + " ":"");
		saida += (this._obtemCategoriaProduto_Associada? outterJoinCategoriaProduto_Associada() + " ":"");
        return saida;
    }
    protected MontadorDaoI getMontadorAgrupado()
    {
        MontadorDaoComposite montador = new MontadorDaoComposite();
        montador.adicionaMontador(new ClienteInteresseCategoriaMontador(), null);
		if (this._obtemCliente_Associada)
            montador.adicionaMontador(new ClienteMontador(), "Cliente_Associada");
		if (this._obtemCategoriaProduto_Associada)
            montador.adicionaMontador(new CategoriaProdutoMontador(), "CategoriaProduto_Associada");
         return montador;
    }
	
    
   	// Poderia passar para classe abstrata.
    public final List<ClienteInteresseCategoria> getAllSinc() throws DaoException {
    	this.setMontador(null);
    	List<ClienteInteresseCategoria> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<ClienteInteresseCategoria>();
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
    public final List<ClienteInteresseCategoria> getAllSincSoAlteracao() throws DaoException {
    	List<ClienteInteresseCategoria> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    		saida = null; // Melhorar aqui !!!!
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<ClienteInteresseCategoria>();
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
	
	public final List<ClienteInteresseCategoria> getAllSincImpl() throws DaoException {
   		String sql = "select " + camposOrdenadosSinc()   
   			+ " from " + this.DB_TABLE_SINC;
   		ClienteInteresseCategoriaMontador montador = new ClienteInteresseCategoriaMontador(true); // sinc ligado
   		this.setMontador(montador);
   		List<ClienteInteresseCategoria> saida = this.getListaSql(sql);
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
		this.insert((ClienteInteresseCategoria) obj);
	}
	@Override
	public final void dropCreate() {
		this.dropTable();
		this.criaTabela();
	}
}
