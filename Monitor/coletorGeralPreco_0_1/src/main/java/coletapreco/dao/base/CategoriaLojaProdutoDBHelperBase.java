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
import coletapreco.modelo.vo.CategoriaLojaProdutoVo;
import coletapreco.modelo.vo.FabricaVo;
import coletapreco.dao.*;
import coletapreco.dao.montador.*;
import coletapreco.dao.datasource.DataSourceAplicacao;

public abstract class CategoriaLojaProdutoDBHelperBase extends DBHelperBase
	implements DaoSincronismo // coloquei aqui para evitar impacto de arquitetura
{

    private static final String DB_NAME = "w_alert";
    public static final String DB_TABLE = "categoria_loja_produto";
    public static final String DB_TABLE_SINC = "categoria_loja_produto_sinc";
    public static final int DB_VERSION = 3;

    protected static final String CLASSNAME = CategoriaLojaProdutoDBHelperBase.class.getSimpleName();
    protected static final String[] COLS = new String[] { 
        "id_categoria_loja_produto"
        ,"data_ultima_visita"
		, "id_categoria_loja_ra"
	
		, "id_produto_ra"
	
    };

	protected static final String[] COLS_SINC = new String[] { 
        "id_categoria_loja_produto"
        ,"data_ultima_visita"
		, "id_categoria_loja_ra"
	
		, "id_produto_ra"
		, "operacao_sinc"
    };

    protected SQLiteDatabase db;
    protected final DBOpenHelper dbOpenHelper;

	@Override
	protected MontadorDaoI criaMontadorPadrao() {
		return new CategoriaLojaProdutoMontador();
	}
	
	protected String getSqlIndices() {
		return "";
	}
	protected String getSqlCreate(){
		return  "CREATE TABLE "
        + CategoriaLojaProdutoDBHelperBase.DB_TABLE + " ("
        + "  id_categoria_loja_produto INTEGER PRIMARY KEY "
        + " , data_ultima_visita INTEGER "
		+ " , id_categoria_loja_ra INTEGER "
		
		+ " , id_produto_ra INTEGER "
		
		+ getSqlIndices()
        + ");";
	}

	

	public static final String DB_CREATE_SINCRONIZADA = "CREATE TABLE IF NOT EXISTS "
        + CategoriaLojaProdutoDBHelperBase.DB_TABLE_SINC + " ("
        + "  id_categoria_loja_produto INTEGER "
        + " , data_ultima_visita INTEGER "
		+ " , id_categoria_loja_ra INTEGER "
		
		+ " , id_produto_ra INTEGER "
		
        + ", operacao_sinc TEXT);";


    public static final String DB_CREATE = "CREATE TABLE IF NOT EXISTS "
        + CategoriaLojaProdutoDBHelperBase.DB_TABLE + " ("
        + "  id_categoria_loja_produto INTEGER PRIMARY KEY "
        + " , data_ultima_visita INTEGER "
		+ " , id_categoria_loja_ra INTEGER "
		
		+ " , id_produto_ra INTEGER "
		
        + ");";
    
    private static final String DB_DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private static final String DB_DROP = "DROP TABLE IF EXISTS " + DB_TABLE;
    private static final String DB_DROP_SINCRONIZADA = "DROP TABLE IF EXISTS " + DB_TABLE_SINC;
    
    private static class DBOpenHelper extends SQLiteOpenHelper {

       

        public DBOpenHelper(final Context context) {
            super(context, CategoriaLojaProdutoDBHelperBase.DB_NAME, null, CategoriaLojaProdutoDBHelperBase.DB_VERSION);
        }

        @Override
        public void onCreate(final SQLiteDatabase db) {
            try {
                db.execSQL(DB_CREATE);
            } catch (SQLException e) {
                Log.e(Constants.LOGTAG, CategoriaLojaProdutoDBHelperBase.CLASSNAME, e);
            }
        }

        @Override
        public void onOpen(final SQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + CategoriaLojaProdutoDBHelperBase.DB_TABLE);
            onCreate(db);
        }
    }

    //
    // end inner classes
    //
    
    // Versao Nova
 	public CategoriaLojaProdutoDBHelperBase() {
    	this.dbOpenHelper = null;
    	setDataSource(DataSourceAplicacao.getInstancia());
    }
    
   
	
	protected ContentValues montaValores(final DCIObjetoDominio valor) {
		CategoriaLojaProduto item = (CategoriaLojaProduto) valor;
		ContentValues valores = new ContentValues();
       	putValor(valores,"id_categoria_loja_produto",item.getIdCategoriaLojaProduto());
       	
       	putValorData(valores,"data_ultima_visita",item.getDataUltimaVisita());
        		
       	putValor(valores,"id_categoria_loja_ra",item.getIdCategoriaLojaRa());
       	
       	putValor(valores,"id_produto_ra",item.getIdProdutoRa());
       	
        return valores;
	}


    // **** Chamadas Diretas Objeto Banco de Dados
    private void registraErroChamadaDireta(Exception e) {
    	Log.e(Constants.LOGTAG, CategoriaLojaProdutoDBHelperBase.CLASSNAME, e);
    }
   
    public final void insert(final CategoriaLojaProduto item) {
	    try {
	        getDb().insert(CategoriaLojaProdutoDBHelperBase.DB_TABLE, null, montaValores(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    public final void update(final CategoriaLojaProduto item) {
	    try {
	        getDb().update(CategoriaLojaProdutoDBHelperBase.DB_TABLE, montaValores(item), "id_categoria_loja_produto=" + item.getIdCategoriaLojaProduto(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     // Nao pode ser final o parametro para retornar o objeto com seu Id
    public final void insertSinc(CategoriaLojaProduto item) {
        try {
        	item.setIdCategoriaLojaProduto((int)getMaxId() + 1);
        	DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc: " + item.debug());
	        long id = getDb().insert(CategoriaLojaProdutoDBHelperBase.DB_TABLE, null, montaValores(item));
    	    ((ObjetoSinc)item).setOperacaoSinc("I");
        	getDb().insert(CategoriaLojaProdutoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     public final void updateSinc(final CategoriaLojaProduto item) {
        try {
	        DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc: " + item.debug());
	        getDb().update(CategoriaLojaProdutoDBHelperBase.DB_TABLE, montaValores(item), "id_categoria_loja_produto=" + item.getIdCategoriaLojaProduto(), null);
	        ((ObjetoSinc)item).setOperacaoSinc("A");
	        CategoriaLojaProduto atual = this.getSincById(item.getId());
	        if (atual==null) {
	        	getDb().insert(CategoriaLojaProdutoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	        } else {
	        	if ("I".equals(((ObjetoSinc)atual).getOperacaoSinc()))
	        		((ObjetoSinc)item).setOperacaoSinc("I");
	        	getDb().update(CategoriaLojaProdutoDBHelperBase.DB_TABLE_SINC, montaValoresSinc(item), "id_categoria_loja_produto=" + item.getIdCategoriaLojaProduto(), null);
	        }
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    protected final void delete(final long id) {
        try {
			getDb().delete(CategoriaLojaProdutoDBHelperBase.DB_TABLE, "id_categoria_loja_produto=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    /*
    private void deleteSinc(final long id) {
        try {
			getDb().delete(CategoriaLojaProdutoDBHelperBase.DB_TABLE_SINC, "id_categoria_loja_produto=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
	*/
    public void limpaSinc(final CategoriaLojaProduto item) {
    	try {
			getDb().delete(CategoriaLojaProdutoDBHelperBase.DB_TABLE_SINC, "id_categoria_loja_produto=" + item.getId(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    
    public void deleteSinc(final CategoriaLojaProduto item) {
    	try {
	        DCLog.dStack(DCLog.DATABASE_ADM, this, "deleteSinc: " + item.debug());
	        delete(item.getId());
	        ((ObjetoSinc)item).setOperacaoSinc("D");
        	getDb().insert(CategoriaLojaProdutoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
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
    public CategoriaLojaProduto getById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (CategoriaLojaProduto) getItemQuery(true, CategoriaLojaProdutoDBHelperBase.DB_TABLE, CategoriaLojaProdutoDBHelperBase.COLS, "id_categoria_loja_produto = " + id + "", null, null, null, null,null);
    }
    
    // Esta com orderBy que pode ser bom para exibicoes em tela
    // mas nao e bom para sincronizacao, pensar em ter um metodo para tela e outro para sinc.
    public List<CategoriaLojaProduto> getAll() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, null);
    }
    public List<CategoriaLojaProduto> getAllTela() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, orderByColuna());
    }
    
    private CategoriaLojaProduto getByRowId(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (CategoriaLojaProduto) getItemQuery(true, CategoriaLojaProdutoDBHelperBase.DB_TABLE, CategoriaLojaProdutoDBHelperBase.COLS, "ROWID = " + id + "", null, null, null, null,null);
    }
    private CategoriaLojaProduto getSincById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (CategoriaLojaProduto) getItemQuerySinc(true, CategoriaLojaProdutoDBHelperBase.DB_TABLE_SINC, CategoriaLojaProdutoDBHelperBase.COLS_SINC, "id_categoria_loja_produto = " + id + "", null, null, null, null,null);
    }
    
    
    public long getMaxId() {
		String sql = "select max(id_categoria_loja_produto) from " + DB_TABLE;
		return this.getNumeroSql(sql);
	}
	protected String orderByColuna() {
    	return null;
    }
	
	
	// Classe e relacionamento
	public List<CategoriaLojaProduto> getPorReferenteACategoriaLoja(Context contexto, long id) throws DaoException{
		String sql = "select " + camposOrdenados() + 
				" , " + ProdutoDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinProduto_ReferenteA() +
				" where id_categoria_loja_ra = " + id;
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new CategoriaLojaProdutoMontador(), null);
		montador.adicionaMontador(new ProdutoMontador(), "Produto_ReferenteA");
		setMontador(montador);
		return this.getListaSqlListaInterna(sql);
			
	}
	public List<CategoriaLojaProduto> getPorReferenteACategoriaLoja(long id) throws DaoException{
		String sql = "select " + camposOrdenados() + 
				" , " + ProdutoDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinProduto_ReferenteA() +
				" where id_categoria_loja_ra = " + id;
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new CategoriaLojaProdutoMontador(), null);
		montador.adicionaMontador(new ProdutoMontador(), "Produto_ReferenteA");
		setMontador(montador);
		return this.getListaSqlListaInterna(sql);
			
	}
	public List<Produto> getProdutoPorReferenteACategoriaLoja(Context contexto, long id) throws DaoException{
		String sql = "select " + ProdutoDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinProduto_ReferenteA() +
				" where id_categoria_loja_ra = " + id;
		setMontador(new ProdutoMontador());
		List<Produto> saida = this.getListaSql(sql);
		setMontador(null);
		return saida;
	}
	
	
	
	// Classe e relacionamento
	public List<CategoriaLojaProduto> getPorReferenteAProduto(Context contexto, long id) throws DaoException{
		String sql = "select " + camposOrdenados() + 
				" , " + CategoriaLojaDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinCategoriaLoja_ReferenteA() +
				" where id_produto_ra = " + id;
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new CategoriaLojaProdutoMontador(), null);
		montador.adicionaMontador(new CategoriaLojaMontador(), "CategoriaLoja_ReferenteA");
		setMontador(montador);
		return this.getListaSqlListaInterna(sql);
			
	}
	public List<CategoriaLojaProduto> getPorReferenteAProduto(long id) throws DaoException{
		String sql = "select " + camposOrdenados() + 
				" , " + CategoriaLojaDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinCategoriaLoja_ReferenteA() +
				" where id_produto_ra = " + id;
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new CategoriaLojaProdutoMontador(), null);
		montador.adicionaMontador(new CategoriaLojaMontador(), "CategoriaLoja_ReferenteA");
		setMontador(montador);
		return this.getListaSqlListaInterna(sql);
			
	}
	public List<CategoriaLoja> getCategoriaLojaPorReferenteAProduto(Context contexto, long id) throws DaoException{
		String sql = "select " + CategoriaLojaDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinCategoriaLoja_ReferenteA() +
				" where id_produto_ra = " + id;
		setMontador(new CategoriaLojaMontador());
		List<CategoriaLoja> saida = this.getListaSql(sql);
		setMontador(null);
		return saida;
	}
	
	
  
  	
	public CategoriaLojaProduto getPorCategoriaLojaProduto(long idXXXX, long idYYYYY) {
		return null;
	}
	
  
  
  	
	
	
	// Relacionamento onde esse objeto ? chave estrangeira de outro. ????
	
	private boolean _obtemCategoriaLoja_ReferenteA = false;
	public void setObtemCategoriaLoja_ReferenteA() {
		_obtemCategoriaLoja_ReferenteA = true;
	}
	protected String outterJoinCategoriaLoja_ReferenteA() {
		return " left outer join " + CategoriaLojaDBHelperBase.DB_TABLE + " on " + CategoriaLojaDBHelperBase.DB_TABLE + ".id_categoria_loja = " + DB_TABLE + ".id_categoria_loja_ra ";  
	}
	public boolean atualizaReferenteACategoriaLoja(long idCategoriaLojaProduto, long idCategoriaLojaRa) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_categoria_loja_ra  = " + idCategoriaLojaRa +
        " where id_categoria_loja_produto = " +  idCategoriaLojaProduto;
       	//this.executaSql(sql);
       	return true;
	}
	public CategoriaLojaProduto obtemPorIdsReferenteACategoriaLoja(long idCategoriaLojaProduto, long idCategoriaLojaRa) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_categoria_loja_ra = " + idCategoriaLojaRa + " and " +
			  " id_categoria_loja_produto = " + idCategoriaLojaProduto;
		return (CategoriaLojaProduto) this.geObjetoSql(sql);
	}
	
	
	public static String innerJoinCategoriaLoja_ReferenteA() {
		return " inner join " + CategoriaLojaDBHelperBase.DB_TABLE + " on " + CategoriaLojaDBHelperBase.DB_TABLE + ".id_categoria_loja = " + DB_TABLE + ".id_categoria_loja_ra ";  
	}
	public static String outerJoinCategoriaLoja_ReferenteA() {
		return " left outer join " + CategoriaLojaDBHelperBase.DB_TABLE + " on " + CategoriaLojaDBHelperBase.DB_TABLE + ".id_categoria_loja = " + DB_TABLE + ".id_categoria_loja_ra ";  
	}
	public static String innerJoinSincCategoriaLoja_ReferenteA() {
		return " inner join " + CategoriaLojaDBHelperBase.DB_TABLE_SINC + " on " + CategoriaLojaDBHelperBase.DB_TABLE_SINC + ".id_categoria_loja = " + DB_TABLE_SINC + ".id_categoria_loja_ra ";  
	}
	public static String outerJoinSincCategoriaLoja_ReferenteA() {
		return " left outer join " + CategoriaLojaDBHelperBase.DB_TABLE_SINC + " on " + CategoriaLojaDBHelperBase.DB_TABLE_SINC + ".id_categoria_loja = " + DB_TABLE_SINC + ".id_categoria_loja_ra ";  
	}
	
 	
	private boolean _obtemProduto_ReferenteA = false;
	public void setObtemProduto_ReferenteA() {
		_obtemProduto_ReferenteA = true;
	}
	protected String outterJoinProduto_ReferenteA() {
		return " left outer join " + ProdutoDBHelperBase.DB_TABLE + " on " + ProdutoDBHelperBase.DB_TABLE + ".id_produto = " + DB_TABLE + ".id_produto_ra ";  
	}
	public boolean atualizaReferenteAProduto(long idCategoriaLojaProduto, long idProdutoRa) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_produto_ra  = " + idProdutoRa +
        " where id_categoria_loja_produto = " +  idCategoriaLojaProduto;
       	//this.executaSql(sql);
       	return true;
	}
	public CategoriaLojaProduto obtemPorIdsReferenteAProduto(long idCategoriaLojaProduto, long idProdutoRa) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_produto_ra = " + idProdutoRa + " and " +
			  " id_categoria_loja_produto = " + idCategoriaLojaProduto;
		return (CategoriaLojaProduto) this.geObjetoSql(sql);
	}
	
	
	public static String innerJoinProduto_ReferenteA() {
		return " inner join " + ProdutoDBHelperBase.DB_TABLE + " on " + ProdutoDBHelperBase.DB_TABLE + ".id_produto = " + DB_TABLE + ".id_produto_ra ";  
	}
	public static String outerJoinProduto_ReferenteA() {
		return " left outer join " + ProdutoDBHelperBase.DB_TABLE + " on " + ProdutoDBHelperBase.DB_TABLE + ".id_produto = " + DB_TABLE + ".id_produto_ra ";  
	}
	public static String innerJoinSincProduto_ReferenteA() {
		return " inner join " + ProdutoDBHelperBase.DB_TABLE_SINC + " on " + ProdutoDBHelperBase.DB_TABLE_SINC + ".id_produto = " + DB_TABLE_SINC + ".id_produto_ra ";  
	}
	public static String outerJoinSincProduto_ReferenteA() {
		return " left outer join " + ProdutoDBHelperBase.DB_TABLE_SINC + " on " + ProdutoDBHelperBase.DB_TABLE_SINC + ".id_produto = " + DB_TABLE_SINC + ".id_produto_ra ";  
	}
	
 	
	// ********************************************************************	
	
	
	public static String camposOrdenados() 
	{
		return " categoria_loja_produto.id_categoria_loja_produto " 
		+ " ,categoria_loja_produto.data_ultima_visita " 
		+ " ,categoria_loja_produto.id_categoria_loja_ra " 
		+ " ,categoria_loja_produto.id_produto_ra " 
		;
	}
	public static String camposOrdenadosSinc() 
	{
		return " categoria_loja_produto_sinc.id_categoria_loja_produto " 
		+ " ,categoria_loja_produto_sinc.data_ultima_visita " 
		+ " ,categoria_loja_produto_sinc.id_categoria_loja_ra " 
		+ " ,categoria_loja_produto_sinc.id_produto_ra " 
		
		+ " ,categoria_loja_produto_sinc.operacao_sinc "
		;
	}
	public static String camposOrdenadosAlias(String nomeTabela) 
	{
		return  nomeTabela + ".id_categoria_loja_produto " 
		+ " , " +  "DATE_FORMAT(" + nomeTabela + ".data_ultima_visita,'%d-%m-%Y') " 
		+ " , " + nomeTabela + ".id_categoria_loja_ra " 
		+ " , " + nomeTabela + ".id_produto_ra " 
		;
	}
	
	protected String camposOrdenadosJoin()
    {
        String saida = camposOrdenados();
		saida += (this._obtemCategoriaLoja_ReferenteA?" , " +CategoriaLojaDBHelperBase.camposOrdenados():"");
		saida += (this._obtemProduto_ReferenteA?" , " +ProdutoDBHelperBase.camposOrdenados():"");
        return saida;
    }
    
        
    public void limpaObtem()
    {
		_obtemCategoriaLoja_ReferenteA = false;
		_obtemProduto_ReferenteA = false;
    }
    
	protected String outterJoinAgrupado()
    {
        String saida = " ";
		saida += (this._obtemCategoriaLoja_ReferenteA? outterJoinCategoriaLoja_ReferenteA() + " ":"");
		saida += (this._obtemProduto_ReferenteA? outterJoinProduto_ReferenteA() + " ":"");
        return saida;
    }
    protected MontadorDaoI getMontadorAgrupado()
    {
        MontadorDaoComposite montador = new MontadorDaoComposite();
        montador.adicionaMontador(new CategoriaLojaProdutoMontador(), null);
		if (this._obtemCategoriaLoja_ReferenteA)
            montador.adicionaMontador(new CategoriaLojaMontador(), "CategoriaLoja_ReferenteA");
		if (this._obtemProduto_ReferenteA)
            montador.adicionaMontador(new ProdutoMontador(), "Produto_ReferenteA");
         return montador;
    }
	
    
   	// Poderia passar para classe abstrata.
    public final List<CategoriaLojaProduto> getAllSinc() throws DaoException {
    	this.setMontador(null);
    	List<CategoriaLojaProduto> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<CategoriaLojaProduto>();
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
    public final List<CategoriaLojaProduto> getAllSincSoAlteracao() throws DaoException {
    	List<CategoriaLojaProduto> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    		saida = null; // Melhorar aqui !!!!
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<CategoriaLojaProduto>();
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
	
	public final List<CategoriaLojaProduto> getAllSincImpl() throws DaoException {
   		String sql = "select " + camposOrdenadosSinc()   
   			+ " from " + this.DB_TABLE_SINC;
   		CategoriaLojaProdutoMontador montador = new CategoriaLojaProdutoMontador(true); // sinc ligado
   		this.setMontador(montador);
   		List<CategoriaLojaProduto> saida = this.getListaSql(sql);
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
		this.insert((CategoriaLojaProduto) obj);
	}
	@Override
	public final void dropCreate() {
		this.dropTable();
		this.criaTabela();
	}
}
