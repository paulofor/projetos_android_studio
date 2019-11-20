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
import repcom.modelo.vo.ProdutoPedidoFornecedorVo;
import repcom.modelo.vo.FabricaVo;
import repcom.dao.*;
import repcom.dao.montador.*;
import repcom.dao.datasource.DataSourceAplicacao;

public abstract class ProdutoPedidoFornecedorDBHelperBase extends DBHelperBase
	implements DaoSincronismo // coloquei aqui para evitar impacto de arquitetura
{

    private static final String DB_NAME = "w_alert";
    public static final String DB_TABLE = "produto_pedido_fornecedor";
    public static final String DB_TABLE_SINC = "produto_pedido_fornecedor_sinc";
    public static final int DB_VERSION = 3;

    protected static final String CLASSNAME = ProdutoPedidoFornecedorDBHelperBase.class.getSimpleName();
    protected static final String[] COLS = new String[] { 
        "id_produto_pedido_fornecedor"
		, "id_pedido_fornecedor_a"
	
		, "id_produto_a"
	
    };

	protected static final String[] COLS_SINC = new String[] { 
        "id_produto_pedido_fornecedor"
		, "id_pedido_fornecedor_a"
	
		, "id_produto_a"
		, "operacao_sinc"
    };

    protected SQLiteDatabase db;
    protected final DBOpenHelper dbOpenHelper;

	@Override
	protected MontadorDaoI criaMontadorPadrao() {
		return new ProdutoPedidoFornecedorMontador();
	}
	
	protected String getSqlIndices() {
		return "";
	}
	protected String getSqlCreate(){
		return  "CREATE TABLE "
        + ProdutoPedidoFornecedorDBHelperBase.DB_TABLE + " ("
        + "  id_produto_pedido_fornecedor INTEGER PRIMARY KEY "
		+ " , id_pedido_fornecedor_a INTEGER "
		
		+ " , id_produto_a INTEGER "
		
		+ getSqlIndices()
        + ");";
	}

	

	public static final String DB_CREATE_SINCRONIZADA = "CREATE TABLE IF NOT EXISTS "
        + ProdutoPedidoFornecedorDBHelperBase.DB_TABLE_SINC + " ("
        + "  id_produto_pedido_fornecedor INTEGER "
		+ " , id_pedido_fornecedor_a INTEGER "
		
		+ " , id_produto_a INTEGER "
		
        + ", operacao_sinc TEXT);";


    public static final String DB_CREATE = "CREATE TABLE IF NOT EXISTS "
        + ProdutoPedidoFornecedorDBHelperBase.DB_TABLE + " ("
        + "  id_produto_pedido_fornecedor INTEGER PRIMARY KEY "
		+ " , id_pedido_fornecedor_a INTEGER "
		
		+ " , id_produto_a INTEGER "
		
        + ");";
    
    private static final String DB_DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private static final String DB_DROP = "DROP TABLE IF EXISTS " + DB_TABLE;
    private static final String DB_DROP_SINCRONIZADA = "DROP TABLE IF EXISTS " + DB_TABLE_SINC;
    
    private static class DBOpenHelper extends SQLiteOpenHelper {

       

        public DBOpenHelper(final Context context) {
            super(context, ProdutoPedidoFornecedorDBHelperBase.DB_NAME, null, ProdutoPedidoFornecedorDBHelperBase.DB_VERSION);
        }

        @Override
        public void onCreate(final SQLiteDatabase db) {
            try {
                db.execSQL(DB_CREATE);
            } catch (SQLException e) {
                Log.e(Constants.LOGTAG, ProdutoPedidoFornecedorDBHelperBase.CLASSNAME, e);
            }
        }

        @Override
        public void onOpen(final SQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + ProdutoPedidoFornecedorDBHelperBase.DB_TABLE);
            onCreate(db);
        }
    }

    //
    // end inner classes
    //
    
    // Versao Nova
 	public ProdutoPedidoFornecedorDBHelperBase() {
    	this.dbOpenHelper = null;
    	setDataSource(DataSourceAplicacao.getInstancia());
    }
    
   
	
	protected ContentValues montaValores(final DCIObjetoDominio valor) {
		ProdutoPedidoFornecedor item = (ProdutoPedidoFornecedor) valor;
		ContentValues valores = new ContentValues();
       	putValor(valores,"id_produto_pedido_fornecedor",item.getIdProdutoPedidoFornecedor());
       	
       	putValor(valores,"id_pedido_fornecedor_a",item.getIdPedidoFornecedorA());
       	
       	putValor(valores,"id_produto_a",item.getIdProdutoA());
       	
        return valores;
	}


    // **** Chamadas Diretas Objeto Banco de Dados
    private void registraErroChamadaDireta(Exception e) {
    	Log.e(Constants.LOGTAG, ProdutoPedidoFornecedorDBHelperBase.CLASSNAME, e);
    }
   
    public final void insert(final ProdutoPedidoFornecedor item) {
	    try {
	        getDb().insert(ProdutoPedidoFornecedorDBHelperBase.DB_TABLE, null, montaValores(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    public final void update(final ProdutoPedidoFornecedor item) {
	    try {
	        getDb().update(ProdutoPedidoFornecedorDBHelperBase.DB_TABLE, montaValores(item), "id_produto_pedido_fornecedor=" + item.getIdProdutoPedidoFornecedor(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     // Nao pode ser final o parametro para retornar o objeto com seu Id
    public final void insertSinc(ProdutoPedidoFornecedor item) {
        try {
        	item.setIdProdutoPedidoFornecedor((int)getMaxId() + 1);
        	DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc: " + item.debug());
	        long id = getDb().insert(ProdutoPedidoFornecedorDBHelperBase.DB_TABLE, null, montaValores(item));
    	    ((ObjetoSinc)item).setOperacaoSinc("I");
        	getDb().insert(ProdutoPedidoFornecedorDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     public final void updateSinc(final ProdutoPedidoFornecedor item) {
        try {
	        DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc: " + item.debug());
	        getDb().update(ProdutoPedidoFornecedorDBHelperBase.DB_TABLE, montaValores(item), "id_produto_pedido_fornecedor=" + item.getIdProdutoPedidoFornecedor(), null);
	        ((ObjetoSinc)item).setOperacaoSinc("A");
	        ProdutoPedidoFornecedor atual = this.getSincById(item.getId());
	        if (atual==null) {
	        	getDb().insert(ProdutoPedidoFornecedorDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	        } else {
	        	if ("I".equals(((ObjetoSinc)atual).getOperacaoSinc()))
	        		((ObjetoSinc)item).setOperacaoSinc("I");
	        	getDb().update(ProdutoPedidoFornecedorDBHelperBase.DB_TABLE_SINC, montaValoresSinc(item), "id_produto_pedido_fornecedor=" + item.getIdProdutoPedidoFornecedor(), null);
	        }
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    protected final void delete(final long id) {
        try {
			getDb().delete(ProdutoPedidoFornecedorDBHelperBase.DB_TABLE, "id_produto_pedido_fornecedor=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    /*
    private void deleteSinc(final long id) {
        try {
			getDb().delete(ProdutoPedidoFornecedorDBHelperBase.DB_TABLE_SINC, "id_produto_pedido_fornecedor=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
	*/
    public void limpaSinc(final ProdutoPedidoFornecedor item) {
    	try {
			getDb().delete(ProdutoPedidoFornecedorDBHelperBase.DB_TABLE_SINC, "id_produto_pedido_fornecedor=" + item.getId(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    
    public void deleteSinc(final ProdutoPedidoFornecedor item) {
    	try {
	        DCLog.dStack(DCLog.DATABASE_ADM, this, "deleteSinc: " + item.debug());
	        delete(item.getId());
	        ((ObjetoSinc)item).setOperacaoSinc("D");
        	getDb().insert(ProdutoPedidoFornecedorDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
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
    public ProdutoPedidoFornecedor getById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (ProdutoPedidoFornecedor) getItemQuery(true, ProdutoPedidoFornecedorDBHelperBase.DB_TABLE, ProdutoPedidoFornecedorDBHelperBase.COLS, "id_produto_pedido_fornecedor = " + id + "", null, null, null, null,null);
    }
    
    // Esta com orderBy que pode ser bom para exibicoes em tela
    // mas nao e bom para sincronizacao, pensar em ter um metodo para tela e outro para sinc.
    public List<ProdutoPedidoFornecedor> getAll() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, null);
    }
    public List<ProdutoPedidoFornecedor> getAllTela() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, orderByColuna());
    }
    
    private ProdutoPedidoFornecedor getByRowId(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (ProdutoPedidoFornecedor) getItemQuery(true, ProdutoPedidoFornecedorDBHelperBase.DB_TABLE, ProdutoPedidoFornecedorDBHelperBase.COLS, "ROWID = " + id + "", null, null, null, null,null);
    }
    private ProdutoPedidoFornecedor getSincById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (ProdutoPedidoFornecedor) getItemQuerySinc(true, ProdutoPedidoFornecedorDBHelperBase.DB_TABLE_SINC, ProdutoPedidoFornecedorDBHelperBase.COLS_SINC, "id_produto_pedido_fornecedor = " + id + "", null, null, null, null,null);
    }
    
    
    public long getMaxId() {
		String sql = "select max(id_produto_pedido_fornecedor) from " + DB_TABLE;
		return this.getNumeroSql(sql);
	}
	protected String orderByColuna() {
    	return null;
    }
	
	
	// Classe e relacionamento
	public List<ProdutoPedidoFornecedor> getPorAssociadaPedidoFornecedor(Context contexto, long id) throws DaoException{
		String sql = "select " + camposOrdenados() + 
				" , " + ProdutoDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinProduto_Associada() +
				" where id_pedido_fornecedor_a = " + id;
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new ProdutoPedidoFornecedorMontador(), null);
		montador.adicionaMontador(new ProdutoMontador(), "Produto_Associada");
		setMontador(montador);
		return this.getListaSqlListaInterna(sql);
			
	}
	public List<ProdutoPedidoFornecedor> getPorAssociadaPedidoFornecedor(long id) throws DaoException{
		String sql = "select " + camposOrdenados() + 
				" , " + ProdutoDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinProduto_Associada() +
				" where id_pedido_fornecedor_a = " + id;
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new ProdutoPedidoFornecedorMontador(), null);
		montador.adicionaMontador(new ProdutoMontador(), "Produto_Associada");
		setMontador(montador);
		return this.getListaSqlListaInterna(sql);
			
	}
	public List<Produto> getProdutoPorAssociadaPedidoFornecedor(Context contexto, long id) throws DaoException{
		String sql = "select " + ProdutoDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinProduto_Associada() +
				" where id_pedido_fornecedor_a = " + id;
		setMontador(new ProdutoMontador());
		List<Produto> saida = this.getListaSql(sql);
		setMontador(null);
		return saida;
	}
	
	
	
	// Classe e relacionamento
	public List<ProdutoPedidoFornecedor> getPorAssociadaProduto(Context contexto, long id) throws DaoException{
		String sql = "select " + camposOrdenados() + 
				" , " + PedidoFornecedorDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinPedidoFornecedor_Associada() +
				" where id_produto_a = " + id;
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new ProdutoPedidoFornecedorMontador(), null);
		montador.adicionaMontador(new PedidoFornecedorMontador(), "PedidoFornecedor_Associada");
		setMontador(montador);
		return this.getListaSqlListaInterna(sql);
			
	}
	public List<ProdutoPedidoFornecedor> getPorAssociadaProduto(long id) throws DaoException{
		String sql = "select " + camposOrdenados() + 
				" , " + PedidoFornecedorDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinPedidoFornecedor_Associada() +
				" where id_produto_a = " + id;
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new ProdutoPedidoFornecedorMontador(), null);
		montador.adicionaMontador(new PedidoFornecedorMontador(), "PedidoFornecedor_Associada");
		setMontador(montador);
		return this.getListaSqlListaInterna(sql);
			
	}
	public List<PedidoFornecedor> getPedidoFornecedorPorAssociadaProduto(Context contexto, long id) throws DaoException{
		String sql = "select " + PedidoFornecedorDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinPedidoFornecedor_Associada() +
				" where id_produto_a = " + id;
		setMontador(new PedidoFornecedorMontador());
		List<PedidoFornecedor> saida = this.getListaSql(sql);
		setMontador(null);
		return saida;
	}
	
	
  
  	
	public ProdutoPedidoFornecedor getPorPedidoFornecedorProduto(long idXXXX, long idYYYYY) {
		return null;
	}
	
  
  
  	
	
	
	// Relacionamento onde esse objeto ? chave estrangeira de outro. ????
	
	private boolean _obtemPedidoFornecedor_Associada = false;
	public void setObtemPedidoFornecedor_Associada() {
		_obtemPedidoFornecedor_Associada = true;
	}
	protected String outterJoinPedidoFornecedor_Associada() {
		return " left outer join " + PedidoFornecedorDBHelperBase.DB_TABLE + " on " + PedidoFornecedorDBHelperBase.DB_TABLE + ".id_pedido_fornecedor = " + DB_TABLE + ".id_pedido_fornecedor_a ";  
	}
	public boolean atualizaAssociadaPedidoFornecedor(long idProdutoPedidoFornecedor, long idPedidoFornecedorA) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_pedido_fornecedor_a  = " + idPedidoFornecedorA +
        " where id_produto_pedido_fornecedor = " +  idProdutoPedidoFornecedor;
       	//this.executaSql(sql);
       	return true;
	}
	public ProdutoPedidoFornecedor obtemPorIdsAssociadaPedidoFornecedor(long idProdutoPedidoFornecedor, long idPedidoFornecedorA) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_pedido_fornecedor_a = " + idPedidoFornecedorA + " and " +
			  " id_produto_pedido_fornecedor = " + idProdutoPedidoFornecedor;
		return (ProdutoPedidoFornecedor) this.geObjetoSql(sql);
	}
	
	
	public static String innerJoinPedidoFornecedor_Associada() {
		return " inner join " + PedidoFornecedorDBHelperBase.DB_TABLE + " on " + PedidoFornecedorDBHelperBase.DB_TABLE + ".id_pedido_fornecedor = " + DB_TABLE + ".id_pedido_fornecedor_a ";  
	}
	public static String outerJoinPedidoFornecedor_Associada() {
		return " left outer join " + PedidoFornecedorDBHelperBase.DB_TABLE + " on " + PedidoFornecedorDBHelperBase.DB_TABLE + ".id_pedido_fornecedor = " + DB_TABLE + ".id_pedido_fornecedor_a ";  
	}
	public static String innerJoinSincPedidoFornecedor_Associada() {
		return " inner join " + PedidoFornecedorDBHelperBase.DB_TABLE_SINC + " on " + PedidoFornecedorDBHelperBase.DB_TABLE_SINC + ".id_pedido_fornecedor = " + DB_TABLE_SINC + ".id_pedido_fornecedor_a ";  
	}
	public static String outerJoinSincPedidoFornecedor_Associada() {
		return " left outer join " + PedidoFornecedorDBHelperBase.DB_TABLE_SINC + " on " + PedidoFornecedorDBHelperBase.DB_TABLE_SINC + ".id_pedido_fornecedor = " + DB_TABLE_SINC + ".id_pedido_fornecedor_a ";  
	}
	
 	
	private boolean _obtemProduto_Associada = false;
	public void setObtemProduto_Associada() {
		_obtemProduto_Associada = true;
	}
	protected String outterJoinProduto_Associada() {
		return " left outer join " + ProdutoDBHelperBase.DB_TABLE + " on " + ProdutoDBHelperBase.DB_TABLE + ".id_produto = " + DB_TABLE + ".id_produto_a ";  
	}
	public boolean atualizaAssociadaProduto(long idProdutoPedidoFornecedor, long idProdutoA) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_produto_a  = " + idProdutoA +
        " where id_produto_pedido_fornecedor = " +  idProdutoPedidoFornecedor;
       	//this.executaSql(sql);
       	return true;
	}
	public ProdutoPedidoFornecedor obtemPorIdsAssociadaProduto(long idProdutoPedidoFornecedor, long idProdutoA) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_produto_a = " + idProdutoA + " and " +
			  " id_produto_pedido_fornecedor = " + idProdutoPedidoFornecedor;
		return (ProdutoPedidoFornecedor) this.geObjetoSql(sql);
	}
	
	
	public static String innerJoinProduto_Associada() {
		return " inner join " + ProdutoDBHelperBase.DB_TABLE + " on " + ProdutoDBHelperBase.DB_TABLE + ".id_produto = " + DB_TABLE + ".id_produto_a ";  
	}
	public static String outerJoinProduto_Associada() {
		return " left outer join " + ProdutoDBHelperBase.DB_TABLE + " on " + ProdutoDBHelperBase.DB_TABLE + ".id_produto = " + DB_TABLE + ".id_produto_a ";  
	}
	public static String innerJoinSincProduto_Associada() {
		return " inner join " + ProdutoDBHelperBase.DB_TABLE_SINC + " on " + ProdutoDBHelperBase.DB_TABLE_SINC + ".id_produto = " + DB_TABLE_SINC + ".id_produto_a ";  
	}
	public static String outerJoinSincProduto_Associada() {
		return " left outer join " + ProdutoDBHelperBase.DB_TABLE_SINC + " on " + ProdutoDBHelperBase.DB_TABLE_SINC + ".id_produto = " + DB_TABLE_SINC + ".id_produto_a ";  
	}
	
 	
	// ********************************************************************	
	
	
	public static String camposOrdenados() 
	{
		return " produto_pedido_fornecedor.id_produto_pedido_fornecedor " 
		+ " ,produto_pedido_fornecedor.id_pedido_fornecedor_a " 
		+ " ,produto_pedido_fornecedor.id_produto_a " 
		;
	}
	public static String camposOrdenadosSinc() 
	{
		return " produto_pedido_fornecedor_sinc.id_produto_pedido_fornecedor " 
		+ " ,produto_pedido_fornecedor_sinc.id_pedido_fornecedor_a " 
		+ " ,produto_pedido_fornecedor_sinc.id_produto_a " 
		
		+ " ,produto_pedido_fornecedor_sinc.operacao_sinc "
		;
	}
	public static String camposOrdenadosAlias(String nomeTabela) 
	{
		return  nomeTabela + ".id_produto_pedido_fornecedor " 
		+ " , " + nomeTabela + ".id_pedido_fornecedor_a " 
		+ " , " + nomeTabela + ".id_produto_a " 
		;
	}
	
	protected String camposOrdenadosJoin()
    {
        String saida = camposOrdenados();
		saida += (this._obtemPedidoFornecedor_Associada?" , " +PedidoFornecedorDBHelperBase.camposOrdenados():"");
		saida += (this._obtemProduto_Associada?" , " +ProdutoDBHelperBase.camposOrdenados():"");
        return saida;
    }
    
        
    public void limpaObtem()
    {
		_obtemPedidoFornecedor_Associada = false;
		_obtemProduto_Associada = false;
    }
    
	protected String outterJoinAgrupado()
    {
        String saida = " ";
		saida += (this._obtemPedidoFornecedor_Associada? outterJoinPedidoFornecedor_Associada() + " ":"");
		saida += (this._obtemProduto_Associada? outterJoinProduto_Associada() + " ":"");
        return saida;
    }
    protected MontadorDaoI getMontadorAgrupado()
    {
        MontadorDaoComposite montador = new MontadorDaoComposite();
        montador.adicionaMontador(new ProdutoPedidoFornecedorMontador(), null);
		if (this._obtemPedidoFornecedor_Associada)
            montador.adicionaMontador(new PedidoFornecedorMontador(), "PedidoFornecedor_Associada");
		if (this._obtemProduto_Associada)
            montador.adicionaMontador(new ProdutoMontador(), "Produto_Associada");
         return montador;
    }
	
    
   	// Poderia passar para classe abstrata.
    public final List<ProdutoPedidoFornecedor> getAllSinc() throws DaoException {
    	this.setMontador(null);
    	List<ProdutoPedidoFornecedor> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<ProdutoPedidoFornecedor>();
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
    public final List<ProdutoPedidoFornecedor> getAllSincSoAlteracao() throws DaoException {
    	List<ProdutoPedidoFornecedor> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    		saida = null; // Melhorar aqui !!!!
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<ProdutoPedidoFornecedor>();
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
	
	public final List<ProdutoPedidoFornecedor> getAllSincImpl() throws DaoException {
   		String sql = "select " + camposOrdenadosSinc()   
   			+ " from " + this.DB_TABLE_SINC;
   		ProdutoPedidoFornecedorMontador montador = new ProdutoPedidoFornecedorMontador(true); // sinc ligado
   		this.setMontador(montador);
   		List<ProdutoPedidoFornecedor> saida = this.getListaSql(sql);
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
		this.insert((ProdutoPedidoFornecedor) obj);
	}
	@Override
	public final void dropCreate() {
		this.dropTable();
		this.criaTabela();
	}
}
