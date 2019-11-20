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
import coletapreco.modelo.vo.LojaNaturezaVo;
import coletapreco.modelo.vo.FabricaVo;
import coletapreco.dao.*;
import coletapreco.dao.montador.*;
import coletapreco.dao.datasource.DataSourceAplicacao;

public abstract class LojaNaturezaDBHelperBase extends DBHelperBase
	implements DaoSincronismo // coloquei aqui para evitar impacto de arquitetura
{

    private static final String DB_NAME = "w_alert";
    public static final String DB_TABLE = "loja_natureza";
    public static final String DB_TABLE_SINC = "loja_natureza_sinc";
    public static final int DB_VERSION = 3;

    protected static final String CLASSNAME = LojaNaturezaDBHelperBase.class.getSimpleName();
    protected static final String[] COLS = new String[] { 
        "id_loja_natureza"
        ,"url_inicial"
        ,"parse_categoria"
		, "id_loja_virtual_ra"
	
		, "id_natureza_produto_ra"
	
    };

	protected static final String[] COLS_SINC = new String[] { 
        "id_loja_natureza"
        ,"url_inicial"
        ,"parse_categoria"
		, "id_loja_virtual_ra"
	
		, "id_natureza_produto_ra"
		, "operacao_sinc"
    };

    protected SQLiteDatabase db;
    protected final DBOpenHelper dbOpenHelper;

	@Override
	protected MontadorDaoI criaMontadorPadrao() {
		return new LojaNaturezaMontador();
	}
	
	protected String getSqlIndices() {
		return "";
	}
	protected String getSqlCreate(){
		return  "CREATE TABLE "
        + LojaNaturezaDBHelperBase.DB_TABLE + " ("
        + "  id_loja_natureza INTEGER PRIMARY KEY "
        + " , url_inicial TEXT "
        + " , parse_categoria NUMERIC "
		+ " , id_loja_virtual_ra INTEGER "
		
		+ " , id_natureza_produto_ra INTEGER "
		
		+ getSqlIndices()
        + ");";
	}

	

	public static final String DB_CREATE_SINCRONIZADA = "CREATE TABLE IF NOT EXISTS "
        + LojaNaturezaDBHelperBase.DB_TABLE_SINC + " ("
        + "  id_loja_natureza INTEGER "
        + " , url_inicial TEXT "
        + " , parse_categoria NUMERIC "
		+ " , id_loja_virtual_ra INTEGER "
		
		+ " , id_natureza_produto_ra INTEGER "
		
        + ", operacao_sinc TEXT);";


    public static final String DB_CREATE = "CREATE TABLE IF NOT EXISTS "
        + LojaNaturezaDBHelperBase.DB_TABLE + " ("
        + "  id_loja_natureza INTEGER PRIMARY KEY "
        + " , url_inicial TEXT "
        + " , parse_categoria NUMERIC "
		+ " , id_loja_virtual_ra INTEGER "
		
		+ " , id_natureza_produto_ra INTEGER "
		
        + ");";
    
    private static final String DB_DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private static final String DB_DROP = "DROP TABLE IF EXISTS " + DB_TABLE;
    private static final String DB_DROP_SINCRONIZADA = "DROP TABLE IF EXISTS " + DB_TABLE_SINC;
    
    private static class DBOpenHelper extends SQLiteOpenHelper {

       

        public DBOpenHelper(final Context context) {
            super(context, LojaNaturezaDBHelperBase.DB_NAME, null, LojaNaturezaDBHelperBase.DB_VERSION);
        }

        @Override
        public void onCreate(final SQLiteDatabase db) {
            try {
                db.execSQL(DB_CREATE);
            } catch (SQLException e) {
                Log.e(Constants.LOGTAG, LojaNaturezaDBHelperBase.CLASSNAME, e);
            }
        }

        @Override
        public void onOpen(final SQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + LojaNaturezaDBHelperBase.DB_TABLE);
            onCreate(db);
        }
    }

    //
    // end inner classes
    //
    
    // Versao Nova
 	public LojaNaturezaDBHelperBase() {
    	this.dbOpenHelper = null;
    	setDataSource(DataSourceAplicacao.getInstancia());
    }
    
   
	
	protected ContentValues montaValores(final DCIObjetoDominio valor) {
		LojaNatureza item = (LojaNatureza) valor;
		ContentValues valores = new ContentValues();
       	putValor(valores,"id_loja_natureza",item.getIdLojaNatureza());
       	
       	putValor(valores,"url_inicial",item.getUrlInicial());
       	
       	putValor(valores,"parse_categoria",item.getParseCategoria());
       	
       	putValor(valores,"id_loja_virtual_ra",item.getIdLojaVirtualRa());
       	
       	putValor(valores,"id_natureza_produto_ra",item.getIdNaturezaProdutoRa());
       	
        return valores;
	}


    // **** Chamadas Diretas Objeto Banco de Dados
    private void registraErroChamadaDireta(Exception e) {
    	Log.e(Constants.LOGTAG, LojaNaturezaDBHelperBase.CLASSNAME, e);
    }
   
    public final void insert(final LojaNatureza item) {
	    try {
	        getDb().insert(LojaNaturezaDBHelperBase.DB_TABLE, null, montaValores(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    public final void update(final LojaNatureza item) {
	    try {
	        getDb().update(LojaNaturezaDBHelperBase.DB_TABLE, montaValores(item), "id_loja_natureza=" + item.getIdLojaNatureza(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     // Nao pode ser final o parametro para retornar o objeto com seu Id
    public final void insertSinc(LojaNatureza item) {
        try {
        	item.setIdLojaNatureza((int)getMaxId() + 1);
        	DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc: " + item.debug());
	        long id = getDb().insert(LojaNaturezaDBHelperBase.DB_TABLE, null, montaValores(item));
    	    ((ObjetoSinc)item).setOperacaoSinc("I");
        	getDb().insert(LojaNaturezaDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     public final void updateSinc(final LojaNatureza item) {
        try {
	        DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc: " + item.debug());
	        getDb().update(LojaNaturezaDBHelperBase.DB_TABLE, montaValores(item), "id_loja_natureza=" + item.getIdLojaNatureza(), null);
	        ((ObjetoSinc)item).setOperacaoSinc("A");
	        LojaNatureza atual = this.getSincById(item.getId());
	        if (atual==null) {
	        	getDb().insert(LojaNaturezaDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	        } else {
	        	if ("I".equals(((ObjetoSinc)atual).getOperacaoSinc()))
	        		((ObjetoSinc)item).setOperacaoSinc("I");
	        	getDb().update(LojaNaturezaDBHelperBase.DB_TABLE_SINC, montaValoresSinc(item), "id_loja_natureza=" + item.getIdLojaNatureza(), null);
	        }
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    protected final void delete(final long id) {
        try {
			getDb().delete(LojaNaturezaDBHelperBase.DB_TABLE, "id_loja_natureza=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    /*
    private void deleteSinc(final long id) {
        try {
			getDb().delete(LojaNaturezaDBHelperBase.DB_TABLE_SINC, "id_loja_natureza=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
	*/
    public void limpaSinc(final LojaNatureza item) {
    	try {
			getDb().delete(LojaNaturezaDBHelperBase.DB_TABLE_SINC, "id_loja_natureza=" + item.getId(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    
    public void deleteSinc(final LojaNatureza item) {
    	try {
	        DCLog.dStack(DCLog.DATABASE_ADM, this, "deleteSinc: " + item.debug());
	        delete(item.getId());
	        ((ObjetoSinc)item).setOperacaoSinc("D");
        	getDb().insert(LojaNaturezaDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
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
    public LojaNatureza getById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (LojaNatureza) getItemQuery(true, LojaNaturezaDBHelperBase.DB_TABLE, LojaNaturezaDBHelperBase.COLS, "id_loja_natureza = " + id + "", null, null, null, null,null);
    }
    
    // Esta com orderBy que pode ser bom para exibicoes em tela
    // mas nao e bom para sincronizacao, pensar em ter um metodo para tela e outro para sinc.
    public List<LojaNatureza> getAll() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, null);
    }
    public List<LojaNatureza> getAllTela() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, orderByColuna());
    }
    
    private LojaNatureza getByRowId(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (LojaNatureza) getItemQuery(true, LojaNaturezaDBHelperBase.DB_TABLE, LojaNaturezaDBHelperBase.COLS, "ROWID = " + id + "", null, null, null, null,null);
    }
    private LojaNatureza getSincById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (LojaNatureza) getItemQuerySinc(true, LojaNaturezaDBHelperBase.DB_TABLE_SINC, LojaNaturezaDBHelperBase.COLS_SINC, "id_loja_natureza = " + id + "", null, null, null, null,null);
    }
    
    
    public long getMaxId() {
		String sql = "select max(id_loja_natureza) from " + DB_TABLE;
		return this.getNumeroSql(sql);
	}
	protected String orderByColuna() {
    	return null;
    }
	
	
	// Classe e relacionamento
	public List<LojaNatureza> getPorReferenteALojaVirtual(Context contexto, long id) throws DaoException{
		String sql = "select " + camposOrdenados() + 
				" , " + NaturezaProdutoDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinNaturezaProduto_ReferenteA() +
				" where id_loja_virtual_ra = " + id;
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new LojaNaturezaMontador(), null);
		montador.adicionaMontador(new NaturezaProdutoMontador(), "NaturezaProduto_ReferenteA");
		setMontador(montador);
		return this.getListaSqlListaInterna(sql);
			
	}
	public List<LojaNatureza> getPorReferenteALojaVirtual(long id) throws DaoException{
		String sql = "select " + camposOrdenados() + 
				" , " + NaturezaProdutoDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinNaturezaProduto_ReferenteA() +
				" where id_loja_virtual_ra = " + id;
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new LojaNaturezaMontador(), null);
		montador.adicionaMontador(new NaturezaProdutoMontador(), "NaturezaProduto_ReferenteA");
		setMontador(montador);
		return this.getListaSqlListaInterna(sql);
			
	}
	public List<NaturezaProduto> getNaturezaProdutoPorReferenteALojaVirtual(Context contexto, long id) throws DaoException{
		String sql = "select " + NaturezaProdutoDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinNaturezaProduto_ReferenteA() +
				" where id_loja_virtual_ra = " + id;
		setMontador(new NaturezaProdutoMontador());
		List<NaturezaProduto> saida = this.getListaSql(sql);
		setMontador(null);
		return saida;
	}
	
	
	
	// Classe e relacionamento
	public List<LojaNatureza> getPorReferenteANaturezaProduto(Context contexto, long id) throws DaoException{
		String sql = "select " + camposOrdenados() + 
				" , " + LojaVirtualDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinLojaVirtual_ReferenteA() +
				" where id_natureza_produto_ra = " + id;
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new LojaNaturezaMontador(), null);
		montador.adicionaMontador(new LojaVirtualMontador(), "LojaVirtual_ReferenteA");
		setMontador(montador);
		return this.getListaSqlListaInterna(sql);
			
	}
	public List<LojaNatureza> getPorReferenteANaturezaProduto(long id) throws DaoException{
		String sql = "select " + camposOrdenados() + 
				" , " + LojaVirtualDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinLojaVirtual_ReferenteA() +
				" where id_natureza_produto_ra = " + id;
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new LojaNaturezaMontador(), null);
		montador.adicionaMontador(new LojaVirtualMontador(), "LojaVirtual_ReferenteA");
		setMontador(montador);
		return this.getListaSqlListaInterna(sql);
			
	}
	public List<LojaVirtual> getLojaVirtualPorReferenteANaturezaProduto(Context contexto, long id) throws DaoException{
		String sql = "select " + LojaVirtualDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + " " +
				this.innerJoinLojaVirtual_ReferenteA() +
				" where id_natureza_produto_ra = " + id;
		setMontador(new LojaVirtualMontador());
		List<LojaVirtual> saida = this.getListaSql(sql);
		setMontador(null);
		return saida;
	}
	
	
  
  	
	public LojaNatureza getPorLojaVirtualNaturezaProduto(long idXXXX, long idYYYYY) {
		return null;
	}
	
  
  
  	
	
	
	// Relacionamento onde esse objeto ? chave estrangeira de outro. ????
	
	private boolean _obtemLojaVirtual_ReferenteA = false;
	public void setObtemLojaVirtual_ReferenteA() {
		_obtemLojaVirtual_ReferenteA = true;
	}
	protected String outterJoinLojaVirtual_ReferenteA() {
		return " left outer join " + LojaVirtualDBHelperBase.DB_TABLE + " on " + LojaVirtualDBHelperBase.DB_TABLE + ".id_loja_virtual = " + DB_TABLE + ".id_loja_virtual_ra ";  
	}
	public boolean atualizaReferenteALojaVirtual(long idLojaNatureza, long idLojaVirtualRa) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_loja_virtual_ra  = " + idLojaVirtualRa +
        " where id_loja_natureza = " +  idLojaNatureza;
       	//this.executaSql(sql);
       	return true;
	}
	public LojaNatureza obtemPorIdsReferenteALojaVirtual(long idLojaNatureza, long idLojaVirtualRa) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_loja_virtual_ra = " + idLojaVirtualRa + " and " +
			  " id_loja_natureza = " + idLojaNatureza;
		return (LojaNatureza) this.geObjetoSql(sql);
	}
	
	
	public static String innerJoinLojaVirtual_ReferenteA() {
		return " inner join " + LojaVirtualDBHelperBase.DB_TABLE + " on " + LojaVirtualDBHelperBase.DB_TABLE + ".id_loja_virtual = " + DB_TABLE + ".id_loja_virtual_ra ";  
	}
	public static String outerJoinLojaVirtual_ReferenteA() {
		return " left outer join " + LojaVirtualDBHelperBase.DB_TABLE + " on " + LojaVirtualDBHelperBase.DB_TABLE + ".id_loja_virtual = " + DB_TABLE + ".id_loja_virtual_ra ";  
	}
	public static String innerJoinSincLojaVirtual_ReferenteA() {
		return " inner join " + LojaVirtualDBHelperBase.DB_TABLE_SINC + " on " + LojaVirtualDBHelperBase.DB_TABLE_SINC + ".id_loja_virtual = " + DB_TABLE_SINC + ".id_loja_virtual_ra ";  
	}
	public static String outerJoinSincLojaVirtual_ReferenteA() {
		return " left outer join " + LojaVirtualDBHelperBase.DB_TABLE_SINC + " on " + LojaVirtualDBHelperBase.DB_TABLE_SINC + ".id_loja_virtual = " + DB_TABLE_SINC + ".id_loja_virtual_ra ";  
	}
	
 	
	private boolean _obtemNaturezaProduto_ReferenteA = false;
	public void setObtemNaturezaProduto_ReferenteA() {
		_obtemNaturezaProduto_ReferenteA = true;
	}
	protected String outterJoinNaturezaProduto_ReferenteA() {
		return " left outer join " + NaturezaProdutoDBHelperBase.DB_TABLE + " on " + NaturezaProdutoDBHelperBase.DB_TABLE + ".id_natureza_produto = " + DB_TABLE + ".id_natureza_produto_ra ";  
	}
	public boolean atualizaReferenteANaturezaProduto(long idLojaNatureza, long idNaturezaProdutoRa) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_natureza_produto_ra  = " + idNaturezaProdutoRa +
        " where id_loja_natureza = " +  idLojaNatureza;
       	//this.executaSql(sql);
       	return true;
	}
	public LojaNatureza obtemPorIdsReferenteANaturezaProduto(long idLojaNatureza, long idNaturezaProdutoRa) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_natureza_produto_ra = " + idNaturezaProdutoRa + " and " +
			  " id_loja_natureza = " + idLojaNatureza;
		return (LojaNatureza) this.geObjetoSql(sql);
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
	
 	
	// ********************************************************************	
	
	
	public static String camposOrdenados() 
	{
		return " loja_natureza.id_loja_natureza " 
		+ " ,loja_natureza.url_inicial " 
		+ " ,loja_natureza.parse_categoria " 
		+ " ,loja_natureza.id_loja_virtual_ra " 
		+ " ,loja_natureza.id_natureza_produto_ra " 
		;
	}
	public static String camposOrdenadosSinc() 
	{
		return " loja_natureza_sinc.id_loja_natureza " 
		+ " ,loja_natureza_sinc.url_inicial " 
		+ " ,loja_natureza_sinc.parse_categoria " 
		+ " ,loja_natureza_sinc.id_loja_virtual_ra " 
		+ " ,loja_natureza_sinc.id_natureza_produto_ra " 
		
		+ " ,loja_natureza_sinc.operacao_sinc "
		;
	}
	public static String camposOrdenadosAlias(String nomeTabela) 
	{
		return  nomeTabela + ".id_loja_natureza " 
		+ " , " + nomeTabela + ".url_inicial " 
		+ " , " + nomeTabela + ".parse_categoria " 
		+ " , " + nomeTabela + ".id_loja_virtual_ra " 
		+ " , " + nomeTabela + ".id_natureza_produto_ra " 
		;
	}
	
	protected String camposOrdenadosJoin()
    {
        String saida = camposOrdenados();
		saida += (this._obtemLojaVirtual_ReferenteA?" , " +LojaVirtualDBHelperBase.camposOrdenados():"");
		saida += (this._obtemNaturezaProduto_ReferenteA?" , " +NaturezaProdutoDBHelperBase.camposOrdenados():"");
        return saida;
    }
    
        
    public void limpaObtem()
    {
		_obtemLojaVirtual_ReferenteA = false;
		_obtemNaturezaProduto_ReferenteA = false;
    }
    
	protected String outterJoinAgrupado()
    {
        String saida = " ";
		saida += (this._obtemLojaVirtual_ReferenteA? outterJoinLojaVirtual_ReferenteA() + " ":"");
		saida += (this._obtemNaturezaProduto_ReferenteA? outterJoinNaturezaProduto_ReferenteA() + " ":"");
        return saida;
    }
    protected MontadorDaoI getMontadorAgrupado()
    {
        MontadorDaoComposite montador = new MontadorDaoComposite();
        montador.adicionaMontador(new LojaNaturezaMontador(), null);
		if (this._obtemLojaVirtual_ReferenteA)
            montador.adicionaMontador(new LojaVirtualMontador(), "LojaVirtual_ReferenteA");
		if (this._obtemNaturezaProduto_ReferenteA)
            montador.adicionaMontador(new NaturezaProdutoMontador(), "NaturezaProduto_ReferenteA");
         return montador;
    }
	
    
   	// Poderia passar para classe abstrata.
    public final List<LojaNatureza> getAllSinc() throws DaoException {
    	this.setMontador(null);
    	List<LojaNatureza> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<LojaNatureza>();
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
    public final List<LojaNatureza> getAllSincSoAlteracao() throws DaoException {
    	List<LojaNatureza> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    		saida = null; // Melhorar aqui !!!!
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<LojaNatureza>();
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
	
	public final List<LojaNatureza> getAllSincImpl() throws DaoException {
   		String sql = "select " + camposOrdenadosSinc()   
   			+ " from " + this.DB_TABLE_SINC;
   		LojaNaturezaMontador montador = new LojaNaturezaMontador(true); // sinc ligado
   		this.setMontador(montador);
   		List<LojaNatureza> saida = this.getListaSql(sql);
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
		this.insert((LojaNatureza) obj);
	}
	@Override
	public final void dropCreate() {
		this.dropTable();
		this.criaTabela();
	}
}
