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
import coletapreco.modelo.vo.NaturezaProdutoVo;
import coletapreco.modelo.vo.FabricaVo;
import coletapreco.dao.*;
import coletapreco.dao.montador.*;
import coletapreco.dao.datasource.DataSourceAplicacao;

public abstract class NaturezaProdutoDBHelperBase extends DBHelperBase
	implements DaoSincronismo // coloquei aqui para evitar impacto de arquitetura
{

    private static final String DB_NAME = "w_alert";
    public static final String DB_TABLE = "natureza_produto";
    public static final String DB_TABLE_SINC = "natureza_produto_sinc";
    public static final int DB_VERSION = 3;

    protected static final String CLASSNAME = NaturezaProdutoDBHelperBase.class.getSimpleName();
    protected static final String[] COLS = new String[] { 
        "id_natureza_produto"
        ,"nome_natureza_produto"
        ,"codigo_natureza_produto"
    };

	protected static final String[] COLS_SINC = new String[] { 
        "id_natureza_produto"
        ,"nome_natureza_produto"
        ,"codigo_natureza_produto"	, "operacao_sinc"
    };

    protected SQLiteDatabase db;
    protected final DBOpenHelper dbOpenHelper;

	@Override
	protected MontadorDaoI criaMontadorPadrao() {
		return new NaturezaProdutoMontador();
	}
	
	protected String getSqlIndices() {
		return "";
	}
	protected String getSqlCreate(){
		return  "CREATE TABLE "
        + NaturezaProdutoDBHelperBase.DB_TABLE + " ("
        + "  id_natureza_produto INTEGER PRIMARY KEY "
        + " , nome_natureza_produto TEXT "
        + " , codigo_natureza_produto TEXT "
		+ getSqlIndices()
        + ");";
	}

	

	public static final String DB_CREATE_SINCRONIZADA = "CREATE TABLE IF NOT EXISTS "
        + NaturezaProdutoDBHelperBase.DB_TABLE_SINC + " ("
        + "  id_natureza_produto INTEGER "
        + " , nome_natureza_produto TEXT "
        + " , codigo_natureza_produto TEXT "
        + ", operacao_sinc TEXT);";


    public static final String DB_CREATE = "CREATE TABLE IF NOT EXISTS "
        + NaturezaProdutoDBHelperBase.DB_TABLE + " ("
        + "  id_natureza_produto INTEGER PRIMARY KEY "
        + " , nome_natureza_produto TEXT "
        + " , codigo_natureza_produto TEXT "
        + ");";
    
    private static final String DB_DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private static final String DB_DROP = "DROP TABLE IF EXISTS " + DB_TABLE;
    private static final String DB_DROP_SINCRONIZADA = "DROP TABLE IF EXISTS " + DB_TABLE_SINC;
    
    private static class DBOpenHelper extends SQLiteOpenHelper {

       

        public DBOpenHelper(final Context context) {
            super(context, NaturezaProdutoDBHelperBase.DB_NAME, null, NaturezaProdutoDBHelperBase.DB_VERSION);
        }

        @Override
        public void onCreate(final SQLiteDatabase db) {
            try {
                db.execSQL(DB_CREATE);
            } catch (SQLException e) {
                Log.e(Constants.LOGTAG, NaturezaProdutoDBHelperBase.CLASSNAME, e);
            }
        }

        @Override
        public void onOpen(final SQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + NaturezaProdutoDBHelperBase.DB_TABLE);
            onCreate(db);
        }
    }

    //
    // end inner classes
    //
    
    // Versao Nova
 	public NaturezaProdutoDBHelperBase() {
    	this.dbOpenHelper = null;
    	setDataSource(DataSourceAplicacao.getInstancia());
    }
    
   
	
	protected ContentValues montaValores(final DCIObjetoDominio valor) {
		NaturezaProduto item = (NaturezaProduto) valor;
		ContentValues valores = new ContentValues();
       	putValor(valores,"id_natureza_produto",item.getIdNaturezaProduto());
       	
       	putValor(valores,"nome_natureza_produto",item.getNomeNaturezaProduto());
       	
       	putValor(valores,"codigo_natureza_produto",item.getCodigoNaturezaProduto());
       	
        return valores;
	}


    // **** Chamadas Diretas Objeto Banco de Dados
    private void registraErroChamadaDireta(Exception e) {
    	Log.e(Constants.LOGTAG, NaturezaProdutoDBHelperBase.CLASSNAME, e);
    }
   
    public final void insert(final NaturezaProduto item) {
	    try {
	        getDb().insert(NaturezaProdutoDBHelperBase.DB_TABLE, null, montaValores(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    public final void update(final NaturezaProduto item) {
	    try {
	        getDb().update(NaturezaProdutoDBHelperBase.DB_TABLE, montaValores(item), "id_natureza_produto=" + item.getIdNaturezaProduto(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     // Nao pode ser final o parametro para retornar o objeto com seu Id
    public final void insertSinc(NaturezaProduto item) {
        try {
        	item.setIdNaturezaProduto((int)getMaxId() + 1);
        	DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc: " + item.debug());
	        long id = getDb().insert(NaturezaProdutoDBHelperBase.DB_TABLE, null, montaValores(item));
    	    ((ObjetoSinc)item).setOperacaoSinc("I");
        	getDb().insert(NaturezaProdutoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     public final void updateSinc(final NaturezaProduto item) {
        try {
	        DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc: " + item.debug());
	        getDb().update(NaturezaProdutoDBHelperBase.DB_TABLE, montaValores(item), "id_natureza_produto=" + item.getIdNaturezaProduto(), null);
	        ((ObjetoSinc)item).setOperacaoSinc("A");
	        NaturezaProduto atual = this.getSincById(item.getId());
	        if (atual==null) {
	        	getDb().insert(NaturezaProdutoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	        } else {
	        	if ("I".equals(((ObjetoSinc)atual).getOperacaoSinc()))
	        		((ObjetoSinc)item).setOperacaoSinc("I");
	        	getDb().update(NaturezaProdutoDBHelperBase.DB_TABLE_SINC, montaValoresSinc(item), "id_natureza_produto=" + item.getIdNaturezaProduto(), null);
	        }
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    protected final void delete(final long id) {
        try {
			getDb().delete(NaturezaProdutoDBHelperBase.DB_TABLE, "id_natureza_produto=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    /*
    private void deleteSinc(final long id) {
        try {
			getDb().delete(NaturezaProdutoDBHelperBase.DB_TABLE_SINC, "id_natureza_produto=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
	*/
    public void limpaSinc(final NaturezaProduto item) {
    	try {
			getDb().delete(NaturezaProdutoDBHelperBase.DB_TABLE_SINC, "id_natureza_produto=" + item.getId(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    
    public void deleteSinc(final NaturezaProduto item) {
    	try {
	        DCLog.dStack(DCLog.DATABASE_ADM, this, "deleteSinc: " + item.debug());
	        delete(item.getId());
	        ((ObjetoSinc)item).setOperacaoSinc("D");
        	getDb().insert(NaturezaProdutoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
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
    public NaturezaProduto getById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (NaturezaProduto) getItemQuery(true, NaturezaProdutoDBHelperBase.DB_TABLE, NaturezaProdutoDBHelperBase.COLS, "id_natureza_produto = " + id + "", null, null, null, null,null);
    }
    
    // Esta com orderBy que pode ser bom para exibicoes em tela
    // mas nao e bom para sincronizacao, pensar em ter um metodo para tela e outro para sinc.
    public List<NaturezaProduto> getAll() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, null);
    }
    public List<NaturezaProduto> getAllTela() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, orderByColuna());
    }
    
    private NaturezaProduto getByRowId(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (NaturezaProduto) getItemQuery(true, NaturezaProdutoDBHelperBase.DB_TABLE, NaturezaProdutoDBHelperBase.COLS, "ROWID = " + id + "", null, null, null, null,null);
    }
    private NaturezaProduto getSincById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (NaturezaProduto) getItemQuerySinc(true, NaturezaProdutoDBHelperBase.DB_TABLE_SINC, NaturezaProdutoDBHelperBase.COLS_SINC, "id_natureza_produto = " + id + "", null, null, null, null,null);
    }
    
    
    public long getMaxId() {
		String sql = "select max(id_natureza_produto) from " + DB_TABLE;
		return this.getNumeroSql(sql);
	}
	protected String orderByColuna() {
    	return null;
    }
	
  
  	
  
  
  	
	/*
	public NaturezaProduto obtemPorCategoriaLojaPossui(long id) {
		string sql;
		sql = "select " + camposOrdenados() +
			" from " + tabelaSelect() +
			innerJoinCategoriaLoja_Possui() + 
			" where id_categoria_loja = " + id;
		return (NaturezaProduto) getObjeto(sql);
	}
	*/
	public static String innerJoinCategoriaLoja_Possui() {
		return " inner join " + CategoriaLojaDBHelperBase.DB_TABLE + " on " + CategoriaLojaDBHelperBase.DB_TABLE + ".id_natureza_produto_ra = " + DB_TABLE + ".id_natureza_produto ";  
	}
	public static String innerJoinSincCategoriaLoja_Possui() {
		return " inner join " + CategoriaLojaDBHelperBase.DB_TABLE_SINC + " on " + CategoriaLojaDBHelperBase.DB_TABLE_SINC + ".id_natureza_produto_ra = " + DB_TABLE_SINC + ".id_natureza_produto ";  
	}
	public static String outerJoinCategoriaLoja_Possui() {
		return " left outer join " + CategoriaLojaDBHelperBase.DB_TABLE + " on " + CategoriaLojaDBHelperBase.DB_TABLE + ".id_natureza_produto_ra = " + DB_TABLE + ".id_natureza_produto ";  
	}
	public static String outerJoinSincCategoriaLoja_Possui() {
		return " left outer join " + CategoriaLojaDBHelperBase.DB_TABLE_SINC + " on " + CategoriaLojaDBHelperBase.DB_TABLE_SINC + ".id_natureza_produto_ra = " + DB_TABLE_SINC + ".id_natureza_produto ";  
	}
 	
	/*
	public NaturezaProduto obtemPorLojaNaturezaEncontrada(long id) {
		string sql;
		sql = "select " + camposOrdenados() +
			" from " + tabelaSelect() +
			innerJoinLojaNatureza_Encontrada() + 
			" where id_loja_natureza = " + id;
		return (NaturezaProduto) getObjeto(sql);
	}
	*/
	public static String innerJoinLojaNatureza_Encontrada() {
		return " inner join " + LojaNaturezaDBHelperBase.DB_TABLE + " on " + LojaNaturezaDBHelperBase.DB_TABLE + ".id_natureza_produto_ra = " + DB_TABLE + ".id_natureza_produto ";  
	}
	public static String innerJoinSincLojaNatureza_Encontrada() {
		return " inner join " + LojaNaturezaDBHelperBase.DB_TABLE_SINC + " on " + LojaNaturezaDBHelperBase.DB_TABLE_SINC + ".id_natureza_produto_ra = " + DB_TABLE_SINC + ".id_natureza_produto ";  
	}
	public static String outerJoinLojaNatureza_Encontrada() {
		return " left outer join " + LojaNaturezaDBHelperBase.DB_TABLE + " on " + LojaNaturezaDBHelperBase.DB_TABLE + ".id_natureza_produto_ra = " + DB_TABLE + ".id_natureza_produto ";  
	}
	public static String outerJoinSincLojaNatureza_Encontrada() {
		return " left outer join " + LojaNaturezaDBHelperBase.DB_TABLE_SINC + " on " + LojaNaturezaDBHelperBase.DB_TABLE_SINC + ".id_natureza_produto_ra = " + DB_TABLE_SINC + ".id_natureza_produto ";  
	}
 	
	/*
	public NaturezaProduto obtemPorOportunidadeDiaPossui(long id) {
		string sql;
		sql = "select " + camposOrdenados() +
			" from " + tabelaSelect() +
			innerJoinOportunidadeDia_Possui() + 
			" where id_oportunidade_dia = " + id;
		return (NaturezaProduto) getObjeto(sql);
	}
	*/
	public static String innerJoinOportunidadeDia_Possui() {
		return " inner join " + OportunidadeDiaDBHelperBase.DB_TABLE + " on " + OportunidadeDiaDBHelperBase.DB_TABLE + ".id_natureza_produto_pa = " + DB_TABLE + ".id_natureza_produto ";  
	}
	public static String innerJoinSincOportunidadeDia_Possui() {
		return " inner join " + OportunidadeDiaDBHelperBase.DB_TABLE_SINC + " on " + OportunidadeDiaDBHelperBase.DB_TABLE_SINC + ".id_natureza_produto_pa = " + DB_TABLE_SINC + ".id_natureza_produto ";  
	}
	public static String outerJoinOportunidadeDia_Possui() {
		return " left outer join " + OportunidadeDiaDBHelperBase.DB_TABLE + " on " + OportunidadeDiaDBHelperBase.DB_TABLE + ".id_natureza_produto_pa = " + DB_TABLE + ".id_natureza_produto ";  
	}
	public static String outerJoinSincOportunidadeDia_Possui() {
		return " left outer join " + OportunidadeDiaDBHelperBase.DB_TABLE_SINC + " on " + OportunidadeDiaDBHelperBase.DB_TABLE_SINC + ".id_natureza_produto_pa = " + DB_TABLE_SINC + ".id_natureza_produto ";  
	}
 	
	/*
	public NaturezaProduto obtemPorUsuarioPesquisaPesquisadoPor(long id) {
		string sql;
		sql = "select " + camposOrdenados() +
			" from " + tabelaSelect() +
			innerJoinUsuarioPesquisa_PesquisadoPor() + 
			" where id_usuario_pesquisa = " + id;
		return (NaturezaProduto) getObjeto(sql);
	}
	*/
	public static String innerJoinUsuarioPesquisa_PesquisadoPor() {
		return " inner join " + UsuarioPesquisaDBHelperBase.DB_TABLE + " on " + UsuarioPesquisaDBHelperBase.DB_TABLE + ".id_natureza_produto_p = " + DB_TABLE + ".id_natureza_produto ";  
	}
	public static String innerJoinSincUsuarioPesquisa_PesquisadoPor() {
		return " inner join " + UsuarioPesquisaDBHelperBase.DB_TABLE_SINC + " on " + UsuarioPesquisaDBHelperBase.DB_TABLE_SINC + ".id_natureza_produto_p = " + DB_TABLE_SINC + ".id_natureza_produto ";  
	}
	public static String outerJoinUsuarioPesquisa_PesquisadoPor() {
		return " left outer join " + UsuarioPesquisaDBHelperBase.DB_TABLE + " on " + UsuarioPesquisaDBHelperBase.DB_TABLE + ".id_natureza_produto_p = " + DB_TABLE + ".id_natureza_produto ";  
	}
	public static String outerJoinSincUsuarioPesquisa_PesquisadoPor() {
		return " left outer join " + UsuarioPesquisaDBHelperBase.DB_TABLE_SINC + " on " + UsuarioPesquisaDBHelperBase.DB_TABLE_SINC + ".id_natureza_produto_p = " + DB_TABLE_SINC + ".id_natureza_produto ";  
	}
 	
	
	
	// Relacionamento onde esse objeto ? chave estrangeira de outro. ????
	
	// ********************************************************************	
	
	
	public static String camposOrdenados() 
	{
		return " natureza_produto.id_natureza_produto " 
		+ " ,natureza_produto.nome_natureza_produto " 
		+ " ,natureza_produto.codigo_natureza_produto " 
		;
	}
	public static String camposOrdenadosSinc() 
	{
		return " natureza_produto_sinc.id_natureza_produto " 
		+ " ,natureza_produto_sinc.nome_natureza_produto " 
		+ " ,natureza_produto_sinc.codigo_natureza_produto " 
		
		+ " ,natureza_produto_sinc.operacao_sinc "
		;
	}
	public static String camposOrdenadosAlias(String nomeTabela) 
	{
		return  nomeTabela + ".id_natureza_produto " 
		+ " , " + nomeTabela + ".nome_natureza_produto " 
		+ " , " + nomeTabela + ".codigo_natureza_produto " 
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
        montador.adicionaMontador(new NaturezaProdutoMontador(), null);
         return montador;
    }
	
    
   	// Poderia passar para classe abstrata.
    public final List<NaturezaProduto> getAllSinc() throws DaoException {
    	this.setMontador(null);
    	List<NaturezaProduto> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<NaturezaProduto>();
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
    public final List<NaturezaProduto> getAllSincSoAlteracao() throws DaoException {
    	List<NaturezaProduto> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    		saida = null; // Melhorar aqui !!!!
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<NaturezaProduto>();
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
	
	public final List<NaturezaProduto> getAllSincImpl() throws DaoException {
   		String sql = "select " + camposOrdenadosSinc()   
   			+ " from " + this.DB_TABLE_SINC;
   		NaturezaProdutoMontador montador = new NaturezaProdutoMontador(true); // sinc ligado
   		this.setMontador(montador);
   		List<NaturezaProduto> saida = this.getListaSql(sql);
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
		this.insert((NaturezaProduto) obj);
	}
	@Override
	public final void dropCreate() {
		this.dropTable();
		this.criaTabela();
	}
}
