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
import coletapreco.modelo.vo.CategoriaLojaVo;
import coletapreco.modelo.vo.FabricaVo;
import coletapreco.dao.*;
import coletapreco.dao.montador.*;
import coletapreco.dao.datasource.DataSourceAplicacao;

public abstract class CategoriaLojaDBHelperBase extends DBHelperBase
	implements DaoSincronismo // coloquei aqui para evitar impacto de arquitetura
{

    private static final String DB_NAME = "w_alert";
    public static final String DB_TABLE = "categoria_loja";
    public static final String DB_TABLE_SINC = "categoria_loja_sinc";
    public static final int DB_VERSION = 3;

    protected static final String CLASSNAME = CategoriaLojaDBHelperBase.class.getSimpleName();
    protected static final String[] COLS = new String[] { 
        "id_categoria_loja"
        ,"nome"
        ,"url"
        ,"data_inclusao"
		, "id_categoria_loja_f"
	
		, "id_natureza_produto_ra"
	
		, "id_loja_virtual_pa"
	
    };

	protected static final String[] COLS_SINC = new String[] { 
        "id_categoria_loja"
        ,"nome"
        ,"url"
        ,"data_inclusao"
		, "id_categoria_loja_f"
	
		, "id_natureza_produto_ra"
	
		, "id_loja_virtual_pa"
		, "operacao_sinc"
    };

    protected SQLiteDatabase db;
    protected final DBOpenHelper dbOpenHelper;

	@Override
	protected MontadorDaoI criaMontadorPadrao() {
		return new CategoriaLojaMontador();
	}
	
	protected String getSqlIndices() {
		return "";
	}
	protected String getSqlCreate(){
		return  "CREATE TABLE "
        + CategoriaLojaDBHelperBase.DB_TABLE + " ("
        + "  id_categoria_loja INTEGER PRIMARY KEY "
        + " , nome TEXT "
        + " , url TEXT "
        + " , data_inclusao INTEGER "
		+ " , id_categoria_loja_f INTEGER "
		
		+ " , id_natureza_produto_ra INTEGER "
		
		+ " , id_loja_virtual_pa INTEGER "
		
		+ getSqlIndices()
        + ");";
	}

	

	public static final String DB_CREATE_SINCRONIZADA = "CREATE TABLE IF NOT EXISTS "
        + CategoriaLojaDBHelperBase.DB_TABLE_SINC + " ("
        + "  id_categoria_loja INTEGER "
        + " , nome TEXT "
        + " , url TEXT "
        + " , data_inclusao INTEGER "
		+ " , id_categoria_loja_f INTEGER "
		
		+ " , id_natureza_produto_ra INTEGER "
		
		+ " , id_loja_virtual_pa INTEGER "
		
        + ", operacao_sinc TEXT);";


    public static final String DB_CREATE = "CREATE TABLE IF NOT EXISTS "
        + CategoriaLojaDBHelperBase.DB_TABLE + " ("
        + "  id_categoria_loja INTEGER PRIMARY KEY "
        + " , nome TEXT "
        + " , url TEXT "
        + " , data_inclusao INTEGER "
		+ " , id_categoria_loja_f INTEGER "
		
		+ " , id_natureza_produto_ra INTEGER "
		
		+ " , id_loja_virtual_pa INTEGER "
		
        + ");";
    
    private static final String DB_DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private static final String DB_DROP = "DROP TABLE IF EXISTS " + DB_TABLE;
    private static final String DB_DROP_SINCRONIZADA = "DROP TABLE IF EXISTS " + DB_TABLE_SINC;
    
    private static class DBOpenHelper extends SQLiteOpenHelper {

       

        public DBOpenHelper(final Context context) {
            super(context, CategoriaLojaDBHelperBase.DB_NAME, null, CategoriaLojaDBHelperBase.DB_VERSION);
        }

        @Override
        public void onCreate(final SQLiteDatabase db) {
            try {
                db.execSQL(DB_CREATE);
            } catch (SQLException e) {
                Log.e(Constants.LOGTAG, CategoriaLojaDBHelperBase.CLASSNAME, e);
            }
        }

        @Override
        public void onOpen(final SQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + CategoriaLojaDBHelperBase.DB_TABLE);
            onCreate(db);
        }
    }

    //
    // end inner classes
    //
    
    // Versao Nova
 	public CategoriaLojaDBHelperBase() {
    	this.dbOpenHelper = null;
    	setDataSource(DataSourceAplicacao.getInstancia());
    }
    
   
	
	protected ContentValues montaValores(final DCIObjetoDominio valor) {
		CategoriaLoja item = (CategoriaLoja) valor;
		ContentValues valores = new ContentValues();
       	putValor(valores,"id_categoria_loja",item.getIdCategoriaLoja());
       	
       	putValor(valores,"nome",item.getNome());
       	
       	putValor(valores,"url",item.getUrl());
       	
       	putValorData(valores,"data_inclusao",item.getDataInclusao());
        		
       	putValor(valores,"id_categoria_loja_f",item.getIdCategoriaLojaF());
       	
       	putValor(valores,"id_natureza_produto_ra",item.getIdNaturezaProdutoRa());
       	
       	putValor(valores,"id_loja_virtual_pa",item.getIdLojaVirtualPa());
       	
        return valores;
	}


    // **** Chamadas Diretas Objeto Banco de Dados
    private void registraErroChamadaDireta(Exception e) {
    	Log.e(Constants.LOGTAG, CategoriaLojaDBHelperBase.CLASSNAME, e);
    }
   
    public final void insert(final CategoriaLoja item) {
	    try {
	        getDb().insert(CategoriaLojaDBHelperBase.DB_TABLE, null, montaValores(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    public final void update(final CategoriaLoja item) {
	    try {
	        getDb().update(CategoriaLojaDBHelperBase.DB_TABLE, montaValores(item), "id_categoria_loja=" + item.getIdCategoriaLoja(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     // Nao pode ser final o parametro para retornar o objeto com seu Id
    public final void insertSinc(CategoriaLoja item) {
        try {
        	item.setIdCategoriaLoja((int)getMaxId() + 1);
        	DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc: " + item.debug());
	        long id = getDb().insert(CategoriaLojaDBHelperBase.DB_TABLE, null, montaValores(item));
    	    ((ObjetoSinc)item).setOperacaoSinc("I");
        	getDb().insert(CategoriaLojaDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     public final void updateSinc(final CategoriaLoja item) {
        try {
	        DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc: " + item.debug());
	        getDb().update(CategoriaLojaDBHelperBase.DB_TABLE, montaValores(item), "id_categoria_loja=" + item.getIdCategoriaLoja(), null);
	        ((ObjetoSinc)item).setOperacaoSinc("A");
	        CategoriaLoja atual = this.getSincById(item.getId());
	        if (atual==null) {
	        	getDb().insert(CategoriaLojaDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	        } else {
	        	if ("I".equals(((ObjetoSinc)atual).getOperacaoSinc()))
	        		((ObjetoSinc)item).setOperacaoSinc("I");
	        	getDb().update(CategoriaLojaDBHelperBase.DB_TABLE_SINC, montaValoresSinc(item), "id_categoria_loja=" + item.getIdCategoriaLoja(), null);
	        }
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    protected final void delete(final long id) {
        try {
			getDb().delete(CategoriaLojaDBHelperBase.DB_TABLE, "id_categoria_loja=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    /*
    private void deleteSinc(final long id) {
        try {
			getDb().delete(CategoriaLojaDBHelperBase.DB_TABLE_SINC, "id_categoria_loja=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
	*/
    public void limpaSinc(final CategoriaLoja item) {
    	try {
			getDb().delete(CategoriaLojaDBHelperBase.DB_TABLE_SINC, "id_categoria_loja=" + item.getId(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    
    public void deleteSinc(final CategoriaLoja item) {
    	try {
	        DCLog.dStack(DCLog.DATABASE_ADM, this, "deleteSinc: " + item.debug());
	        delete(item.getId());
	        ((ObjetoSinc)item).setOperacaoSinc("D");
        	getDb().insert(CategoriaLojaDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
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
    public CategoriaLoja getById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (CategoriaLoja) getItemQuery(true, CategoriaLojaDBHelperBase.DB_TABLE, CategoriaLojaDBHelperBase.COLS, "id_categoria_loja = " + id + "", null, null, null, null,null);
    }
    
    // Esta com orderBy que pode ser bom para exibicoes em tela
    // mas nao e bom para sincronizacao, pensar em ter um metodo para tela e outro para sinc.
    public List<CategoriaLoja> getAll() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, null);
    }
    public List<CategoriaLoja> getAllTela() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, orderByColuna());
    }
    
    private CategoriaLoja getByRowId(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (CategoriaLoja) getItemQuery(true, CategoriaLojaDBHelperBase.DB_TABLE, CategoriaLojaDBHelperBase.COLS, "ROWID = " + id + "", null, null, null, null,null);
    }
    private CategoriaLoja getSincById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (CategoriaLoja) getItemQuerySinc(true, CategoriaLojaDBHelperBase.DB_TABLE_SINC, CategoriaLojaDBHelperBase.COLS_SINC, "id_categoria_loja = " + id + "", null, null, null, null,null);
    }
    
    
    public long getMaxId() {
		String sql = "select max(id_categoria_loja) from " + DB_TABLE;
		return this.getNumeroSql(sql);
	}
	protected String orderByColuna() {
    	return null;
    }
	
	
	public List<CategoriaLoja> getPorFilhoCategoriaLoja(Context contexto, long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_categoria_loja_f = " + id, null, null, null, null);
	}
	public List<CategoriaLoja> getPorFilhoCategoriaLoja(long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_categoria_loja_f = " + id, null, null, null, null);
	}
	
	
	
	public List<CategoriaLoja> getPorReferenteANaturezaProduto(Context contexto, long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_natureza_produto_ra = " + id, null, null, null, null);
	}
	public List<CategoriaLoja> getPorReferenteANaturezaProduto(long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_natureza_produto_ra = " + id, null, null, null, null);
	}
	
	
	
	public List<CategoriaLoja> getPorPertenceALojaVirtual(Context contexto, long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_loja_virtual_pa = " + id, null, null, null, null);
	}
	public List<CategoriaLoja> getPorPertenceALojaVirtual(long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_loja_virtual_pa = " + id, null, null, null, null);
	}
	
	
  
  	
  
  
  	
	/*
	public CategoriaLoja obtemPorCategoriaLojaProdutoPossui(long id) {
		string sql;
		sql = "select " + camposOrdenados() +
			" from " + tabelaSelect() +
			innerJoinCategoriaLojaProduto_Possui() + 
			" where id_categoria_loja_produto = " + id;
		return (CategoriaLoja) getObjeto(sql);
	}
	*/
	public static String innerJoinCategoriaLojaProduto_Possui() {
		return " inner join " + CategoriaLojaProdutoDBHelperBase.DB_TABLE + " on " + CategoriaLojaProdutoDBHelperBase.DB_TABLE + ".id_categoria_loja_ra = " + DB_TABLE + ".id_categoria_loja ";  
	}
	public static String innerJoinSincCategoriaLojaProduto_Possui() {
		return " inner join " + CategoriaLojaProdutoDBHelperBase.DB_TABLE_SINC + " on " + CategoriaLojaProdutoDBHelperBase.DB_TABLE_SINC + ".id_categoria_loja_ra = " + DB_TABLE_SINC + ".id_categoria_loja ";  
	}
	public static String outerJoinCategoriaLojaProduto_Possui() {
		return " left outer join " + CategoriaLojaProdutoDBHelperBase.DB_TABLE + " on " + CategoriaLojaProdutoDBHelperBase.DB_TABLE + ".id_categoria_loja_ra = " + DB_TABLE + ".id_categoria_loja ";  
	}
	public static String outerJoinSincCategoriaLojaProduto_Possui() {
		return " left outer join " + CategoriaLojaProdutoDBHelperBase.DB_TABLE_SINC + " on " + CategoriaLojaProdutoDBHelperBase.DB_TABLE_SINC + ".id_categoria_loja_ra = " + DB_TABLE_SINC + ".id_categoria_loja ";  
	}
 	
	/*
	public CategoriaLoja obtemPorCategoriaLojaFilho(long id) {
		string sql;
		sql = "select " + camposOrdenados() +
			" from " + tabelaSelect() +
			innerJoinCategoriaLoja_Filho() + 
			" where id_categoria_loja = " + id;
		return (CategoriaLoja) getObjeto(sql);
	}
	*/
	public static String innerJoinCategoriaLoja_Filho() {
		return " inner join " + CategoriaLojaDBHelperBase.DB_TABLE + " on " + CategoriaLojaDBHelperBase.DB_TABLE + ".id_categoria_loja_p = " + DB_TABLE + ".id_categoria_loja ";  
	}
	public static String innerJoinSincCategoriaLoja_Filho() {
		return " inner join " + CategoriaLojaDBHelperBase.DB_TABLE_SINC + " on " + CategoriaLojaDBHelperBase.DB_TABLE_SINC + ".id_categoria_loja_p = " + DB_TABLE_SINC + ".id_categoria_loja ";  
	}
	public static String outerJoinCategoriaLoja_Filho() {
		return " left outer join " + CategoriaLojaDBHelperBase.DB_TABLE + " on " + CategoriaLojaDBHelperBase.DB_TABLE + ".id_categoria_loja_p = " + DB_TABLE + ".id_categoria_loja ";  
	}
	public static String outerJoinSincCategoriaLoja_Filho() {
		return " left outer join " + CategoriaLojaDBHelperBase.DB_TABLE_SINC + " on " + CategoriaLojaDBHelperBase.DB_TABLE_SINC + ".id_categoria_loja_p = " + DB_TABLE_SINC + ".id_categoria_loja ";  
	}
 	
	
	
	// Relacionamento onde esse objeto ? chave estrangeira de outro. ????
	
	private boolean _obtemCategoriaLoja_Filho = false;
	public void setObtemCategoriaLoja_Filho() {
		_obtemCategoriaLoja_Filho = true;
	}
	protected String outterJoinCategoriaLoja_Filho() {
		return " left outer join " + CategoriaLojaDBHelperBase.DB_TABLE + " on " + CategoriaLojaDBHelperBase.DB_TABLE + ".id_categoria_loja = " + DB_TABLE + ".id_categoria_loja_f ";  
	}
	public boolean atualizaFilhoCategoriaLoja(long idCategoriaLoja, long idCategoriaLojaF) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_categoria_loja_f  = " + idCategoriaLojaF +
        " where id_categoria_loja = " +  idCategoriaLoja;
       	//this.executaSql(sql);
       	return true;
	}
	public CategoriaLoja obtemPorIdsFilhoCategoriaLoja(long idCategoriaLoja, long idCategoriaLojaF) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_categoria_loja_f = " + idCategoriaLojaF + " and " +
			  " id_categoria_loja = " + idCategoriaLoja;
		return (CategoriaLoja) this.geObjetoSql(sql);
	}
	
	
 	
	private boolean _obtemNaturezaProduto_ReferenteA = false;
	public void setObtemNaturezaProduto_ReferenteA() {
		_obtemNaturezaProduto_ReferenteA = true;
	}
	protected String outterJoinNaturezaProduto_ReferenteA() {
		return " left outer join " + NaturezaProdutoDBHelperBase.DB_TABLE + " on " + NaturezaProdutoDBHelperBase.DB_TABLE + ".id_natureza_produto = " + DB_TABLE + ".id_natureza_produto_ra ";  
	}
	public boolean atualizaReferenteANaturezaProduto(long idCategoriaLoja, long idNaturezaProdutoRa) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_natureza_produto_ra  = " + idNaturezaProdutoRa +
        " where id_categoria_loja = " +  idCategoriaLoja;
       	//this.executaSql(sql);
       	return true;
	}
	public CategoriaLoja obtemPorIdsReferenteANaturezaProduto(long idCategoriaLoja, long idNaturezaProdutoRa) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_natureza_produto_ra = " + idNaturezaProdutoRa + " and " +
			  " id_categoria_loja = " + idCategoriaLoja;
		return (CategoriaLoja) this.geObjetoSql(sql);
	}
	
	
	public static String innerJoinNaturezaProduto_ReferenteA() {
		return " inner join " + NaturezaProdutoDBHelperBase.DB_TABLE + " on " + NaturezaProdutoDBHelperBase.DB_TABLE + ".id_natureza_produto = " + DB_TABLE + ".id_natureza_produto_ra ";  
	}
	public static String outerJoinNaturezaProduto_ReferenteA() {
		return " left outer join " + NaturezaProdutoDBHelperBase.DB_TABLE + " on " + NaturezaProdutoDBHelperBase.DB_TABLE + ".id_natureza_produto = " + DB_TABLE + ".id_natureza_produto_ra ";  
	}
	public static String innerJoinSincNaturezaProduto_ReferenteA() {
		return " inner join " + NaturezaProdutoDBHelperBase.DB_TABLE_SINC + " on " + NaturezaProdutoDBHelperBase.DB_TABLE_SINC + ".id_natureza_produto = " + DB_TABLE_SINC + ".id_natureza_produto_ra ";  
	}
	public static String outerJoinSincNaturezaProduto_ReferenteA() {
		return " left outer join " + NaturezaProdutoDBHelperBase.DB_TABLE_SINC + " on " + NaturezaProdutoDBHelperBase.DB_TABLE_SINC + ".id_natureza_produto = " + DB_TABLE_SINC + ".id_natureza_produto_ra ";  
	}
	
 	
	private boolean _obtemLojaVirtual_PertenceA = false;
	public void setObtemLojaVirtual_PertenceA() {
		_obtemLojaVirtual_PertenceA = true;
	}
	protected String outterJoinLojaVirtual_PertenceA() {
		return " left outer join " + LojaVirtualDBHelperBase.DB_TABLE + " on " + LojaVirtualDBHelperBase.DB_TABLE + ".id_loja_virtual = " + DB_TABLE + ".id_loja_virtual_pa ";  
	}
	public boolean atualizaPertenceALojaVirtual(long idCategoriaLoja, long idLojaVirtualPa) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_loja_virtual_pa  = " + idLojaVirtualPa +
        " where id_categoria_loja = " +  idCategoriaLoja;
       	//this.executaSql(sql);
       	return true;
	}
	public CategoriaLoja obtemPorIdsPertenceALojaVirtual(long idCategoriaLoja, long idLojaVirtualPa) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_loja_virtual_pa = " + idLojaVirtualPa + " and " +
			  " id_categoria_loja = " + idCategoriaLoja;
		return (CategoriaLoja) this.geObjetoSql(sql);
	}
	
	
	public static String innerJoinLojaVirtual_PertenceA() {
		return " inner join " + LojaVirtualDBHelperBase.DB_TABLE + " on " + LojaVirtualDBHelperBase.DB_TABLE + ".id_loja_virtual = " + DB_TABLE + ".id_loja_virtual_pa ";  
	}
	public static String outerJoinLojaVirtual_PertenceA() {
		return " left outer join " + LojaVirtualDBHelperBase.DB_TABLE + " on " + LojaVirtualDBHelperBase.DB_TABLE + ".id_loja_virtual = " + DB_TABLE + ".id_loja_virtual_pa ";  
	}
	public static String innerJoinSincLojaVirtual_PertenceA() {
		return " inner join " + LojaVirtualDBHelperBase.DB_TABLE_SINC + " on " + LojaVirtualDBHelperBase.DB_TABLE_SINC + ".id_loja_virtual = " + DB_TABLE_SINC + ".id_loja_virtual_pa ";  
	}
	public static String outerJoinSincLojaVirtual_PertenceA() {
		return " left outer join " + LojaVirtualDBHelperBase.DB_TABLE_SINC + " on " + LojaVirtualDBHelperBase.DB_TABLE_SINC + ".id_loja_virtual = " + DB_TABLE_SINC + ".id_loja_virtual_pa ";  
	}
	
 	
	// ********************************************************************	
	
	
	public static String camposOrdenados() 
	{
		return " categoria_loja.id_categoria_loja " 
		+ " ,categoria_loja.nome " 
		+ " ,categoria_loja.url " 
		+ " ,categoria_loja.data_inclusao " 
		+ " ,categoria_loja.id_categoria_loja_f " 
		+ " ,categoria_loja.id_natureza_produto_ra " 
		+ " ,categoria_loja.id_loja_virtual_pa " 
		;
	}
	public static String camposOrdenadosSinc() 
	{
		return " categoria_loja_sinc.id_categoria_loja " 
		+ " ,categoria_loja_sinc.nome " 
		+ " ,categoria_loja_sinc.url " 
		+ " ,categoria_loja_sinc.data_inclusao " 
		+ " ,categoria_loja_sinc.id_categoria_loja_f " 
		+ " ,categoria_loja_sinc.id_natureza_produto_ra " 
		+ " ,categoria_loja_sinc.id_loja_virtual_pa " 
		
		+ " ,categoria_loja_sinc.operacao_sinc "
		;
	}
	public static String camposOrdenadosAlias(String nomeTabela) 
	{
		return  nomeTabela + ".id_categoria_loja " 
		+ " , " + nomeTabela + ".nome " 
		+ " , " + nomeTabela + ".url " 
		+ " , " +  "DATE_FORMAT(" + nomeTabela + ".data_inclusao,'%d-%m-%Y') " 
		+ " , " + nomeTabela + ".id_categoria_loja_f " 
		+ " , " + nomeTabela + ".id_natureza_produto_ra " 
		+ " , " + nomeTabela + ".id_loja_virtual_pa " 
		;
	}
	
	protected String camposOrdenadosJoin()
    {
        String saida = camposOrdenados();
		saida += (this._obtemCategoriaLoja_Filho?" , " +CategoriaLojaDBHelperBase.camposOrdenados():"");
		saida += (this._obtemNaturezaProduto_ReferenteA?" , " +NaturezaProdutoDBHelperBase.camposOrdenados():"");
		saida += (this._obtemLojaVirtual_PertenceA?" , " +LojaVirtualDBHelperBase.camposOrdenados():"");
        return saida;
    }
    
        
    public void limpaObtem()
    {
		_obtemCategoriaLoja_Filho = false;
		_obtemNaturezaProduto_ReferenteA = false;
		_obtemLojaVirtual_PertenceA = false;
    }
    
	protected String outterJoinAgrupado()
    {
        String saida = " ";
		saida += (this._obtemCategoriaLoja_Filho? outterJoinCategoriaLoja_Filho() + " ":"");
		saida += (this._obtemNaturezaProduto_ReferenteA? outterJoinNaturezaProduto_ReferenteA() + " ":"");
		saida += (this._obtemLojaVirtual_PertenceA? outterJoinLojaVirtual_PertenceA() + " ":"");
        return saida;
    }
    protected MontadorDaoI getMontadorAgrupado()
    {
        MontadorDaoComposite montador = new MontadorDaoComposite();
        montador.adicionaMontador(new CategoriaLojaMontador(), null);
		if (this._obtemCategoriaLoja_Filho)
            montador.adicionaMontador(new CategoriaLojaMontador(), "CategoriaLoja_Filho");
		if (this._obtemNaturezaProduto_ReferenteA)
            montador.adicionaMontador(new NaturezaProdutoMontador(), "NaturezaProduto_ReferenteA");
		if (this._obtemLojaVirtual_PertenceA)
            montador.adicionaMontador(new LojaVirtualMontador(), "LojaVirtual_PertenceA");
         return montador;
    }
	
    
   	// Poderia passar para classe abstrata.
    public final List<CategoriaLoja> getAllSinc() throws DaoException {
    	this.setMontador(null);
    	List<CategoriaLoja> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<CategoriaLoja>();
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
    public final List<CategoriaLoja> getAllSincSoAlteracao() throws DaoException {
    	List<CategoriaLoja> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    		saida = null; // Melhorar aqui !!!!
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<CategoriaLoja>();
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
	
	public final List<CategoriaLoja> getAllSincImpl() throws DaoException {
   		String sql = "select " + camposOrdenadosSinc()   
   			+ " from " + this.DB_TABLE_SINC;
   		CategoriaLojaMontador montador = new CategoriaLojaMontador(true); // sinc ligado
   		this.setMontador(montador);
   		List<CategoriaLoja> saida = this.getListaSql(sql);
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
		this.insert((CategoriaLoja) obj);
	}
	@Override
	public final void dropCreate() {
		this.dropTable();
		this.criaTabela();
	}
}
