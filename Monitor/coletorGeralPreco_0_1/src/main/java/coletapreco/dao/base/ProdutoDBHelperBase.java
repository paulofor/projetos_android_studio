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
import coletapreco.modelo.vo.ProdutoVo;
import coletapreco.modelo.vo.FabricaVo;
import coletapreco.dao.*;
import coletapreco.dao.montador.*;
import coletapreco.dao.datasource.DataSourceAplicacao;

public abstract class ProdutoDBHelperBase extends DBHelperBase
	implements DaoSincronismo // coloquei aqui para evitar impacto de arquitetura
{

    private static final String DB_NAME = "w_alert";
    public static final String DB_TABLE = "produto";
    public static final String DB_TABLE_SINC = "produto_sinc";
    public static final int DB_VERSION = 3;

    protected static final String CLASSNAME = ProdutoDBHelperBase.class.getSimpleName();
    protected static final String[] COLS = new String[] { 
        "id_produto"
        ,"url_origem"
        ,"imagem_local"
        ,"data_inclusao"
        ,"url"
        ,"nome"
        ,"posicao_produto"
        ,"imagem"
		, "id_loja_virtual_le"
	
		, "id_marca_p"
	
    };

	protected static final String[] COLS_SINC = new String[] { 
        "id_produto"
        ,"url_origem"
        ,"imagem_local"
        ,"data_inclusao"
        ,"url"
        ,"nome"
        ,"posicao_produto"
        ,"imagem"
		, "id_loja_virtual_le"
	
		, "id_marca_p"
		, "operacao_sinc"
    };

    protected SQLiteDatabase db;
    protected final DBOpenHelper dbOpenHelper;

	@Override
	protected MontadorDaoI criaMontadorPadrao() {
		return new ProdutoMontador();
	}
	
	protected String getSqlIndices() {
		return "";
	}
	protected String getSqlCreate(){
		return  "CREATE TABLE "
        + ProdutoDBHelperBase.DB_TABLE + " ("
        + "  id_produto INTEGER PRIMARY KEY "
        + " , url_origem TEXT "
        + " , imagem_local TEXT "
        + " , data_inclusao INTEGER "
        + " , url TEXT "
        + " , nome TEXT "
        + " , posicao_produto INTEGER "
        + " , imagem TEXT "
		+ " , id_loja_virtual_le INTEGER "
		
		+ " , id_marca_p INTEGER "
		
		+ getSqlIndices()
        + ");";
	}

	

	public static final String DB_CREATE_SINCRONIZADA = "CREATE TABLE IF NOT EXISTS "
        + ProdutoDBHelperBase.DB_TABLE_SINC + " ("
        + "  id_produto INTEGER "
        + " , url_origem TEXT "
        + " , imagem_local TEXT "
        + " , data_inclusao INTEGER "
        + " , url TEXT "
        + " , nome TEXT "
        + " , posicao_produto INTEGER "
        + " , imagem TEXT "
		+ " , id_loja_virtual_le INTEGER "
		
		+ " , id_marca_p INTEGER "
		
        + ", operacao_sinc TEXT);";


    public static final String DB_CREATE = "CREATE TABLE IF NOT EXISTS "
        + ProdutoDBHelperBase.DB_TABLE + " ("
        + "  id_produto INTEGER PRIMARY KEY "
        + " , url_origem TEXT "
        + " , imagem_local TEXT "
        + " , data_inclusao INTEGER "
        + " , url TEXT "
        + " , nome TEXT "
        + " , posicao_produto INTEGER "
        + " , imagem TEXT "
		+ " , id_loja_virtual_le INTEGER "
		
		+ " , id_marca_p INTEGER "
		
        + ");";
    
    private static final String DB_DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private static final String DB_DROP = "DROP TABLE IF EXISTS " + DB_TABLE;
    private static final String DB_DROP_SINCRONIZADA = "DROP TABLE IF EXISTS " + DB_TABLE_SINC;
    
    private static class DBOpenHelper extends SQLiteOpenHelper {

       

        public DBOpenHelper(final Context context) {
            super(context, ProdutoDBHelperBase.DB_NAME, null, ProdutoDBHelperBase.DB_VERSION);
        }

        @Override
        public void onCreate(final SQLiteDatabase db) {
            try {
                db.execSQL(DB_CREATE);
            } catch (SQLException e) {
                Log.e(Constants.LOGTAG, ProdutoDBHelperBase.CLASSNAME, e);
            }
        }

        @Override
        public void onOpen(final SQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + ProdutoDBHelperBase.DB_TABLE);
            onCreate(db);
        }
    }

    //
    // end inner classes
    //
    
    // Versao Nova
 	public ProdutoDBHelperBase() {
    	this.dbOpenHelper = null;
    	setDataSource(DataSourceAplicacao.getInstancia());
    }
    
   
	
	protected ContentValues montaValores(final DCIObjetoDominio valor) {
		Produto item = (Produto) valor;
		ContentValues valores = new ContentValues();
       	putValor(valores,"id_produto",item.getIdProduto());
       	
       	putValor(valores,"url_origem",item.getUrlOrigem());
       	
       	putValor(valores,"imagem_local",item.getImagemLocal());
       	
	 	putValorDataHora(valores,"data_inclusao",item.getDataInclusao());
        		
       	putValor(valores,"url",item.getUrl());
       	
       	putValor(valores,"nome",item.getNome());
       	
       	putValor(valores,"posicao_produto",item.getPosicaoProduto());
       	
       	putValor(valores,"imagem",item.getImagem());
       	
       	putValor(valores,"id_loja_virtual_le",item.getIdLojaVirtualLe());
       	
       	putValor(valores,"id_marca_p",item.getIdMarcaP());
       	
        return valores;
	}


    // **** Chamadas Diretas Objeto Banco de Dados
    private void registraErroChamadaDireta(Exception e) {
    	Log.e(Constants.LOGTAG, ProdutoDBHelperBase.CLASSNAME, e);
    }
   
    public final void insert(final Produto item) {
	    try {
	        getDb().insert(ProdutoDBHelperBase.DB_TABLE, null, montaValores(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    public final void update(final Produto item) {
	    try {
	        getDb().update(ProdutoDBHelperBase.DB_TABLE, montaValores(item), "id_produto=" + item.getIdProduto(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     // Nao pode ser final o parametro para retornar o objeto com seu Id
    public final void insertSinc(Produto item) {
        try {
        	item.setIdProduto((int)getMaxId() + 1);
        	DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc: " + item.debug());
	        long id = getDb().insert(ProdutoDBHelperBase.DB_TABLE, null, montaValores(item));
    	    ((ObjetoSinc)item).setOperacaoSinc("I");
        	getDb().insert(ProdutoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     public final void updateSinc(final Produto item) {
        try {
	        DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc: " + item.debug());
	        getDb().update(ProdutoDBHelperBase.DB_TABLE, montaValores(item), "id_produto=" + item.getIdProduto(), null);
	        ((ObjetoSinc)item).setOperacaoSinc("A");
	        Produto atual = this.getSincById(item.getId());
	        if (atual==null) {
	        	getDb().insert(ProdutoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	        } else {
	        	if ("I".equals(((ObjetoSinc)atual).getOperacaoSinc()))
	        		((ObjetoSinc)item).setOperacaoSinc("I");
	        	getDb().update(ProdutoDBHelperBase.DB_TABLE_SINC, montaValoresSinc(item), "id_produto=" + item.getIdProduto(), null);
	        }
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    protected final void delete(final long id) {
        try {
			getDb().delete(ProdutoDBHelperBase.DB_TABLE, "id_produto=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    /*
    private void deleteSinc(final long id) {
        try {
			getDb().delete(ProdutoDBHelperBase.DB_TABLE_SINC, "id_produto=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
	*/
    public void limpaSinc(final Produto item) {
    	try {
			getDb().delete(ProdutoDBHelperBase.DB_TABLE_SINC, "id_produto=" + item.getId(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    
    public void deleteSinc(final Produto item) {
    	try {
	        DCLog.dStack(DCLog.DATABASE_ADM, this, "deleteSinc: " + item.debug());
	        delete(item.getId());
	        ((ObjetoSinc)item).setOperacaoSinc("D");
        	getDb().insert(ProdutoDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
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
    public Produto getById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (Produto) getItemQuery(true, ProdutoDBHelperBase.DB_TABLE, ProdutoDBHelperBase.COLS, "id_produto = " + id + "", null, null, null, null,null);
    }
    
    // Esta com orderBy que pode ser bom para exibicoes em tela
    // mas nao e bom para sincronizacao, pensar em ter um metodo para tela e outro para sinc.
    public List<Produto> getAll() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, null);
    }
    public List<Produto> getAllTela() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, orderByColuna());
    }
    
    private Produto getByRowId(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (Produto) getItemQuery(true, ProdutoDBHelperBase.DB_TABLE, ProdutoDBHelperBase.COLS, "ROWID = " + id + "", null, null, null, null,null);
    }
    private Produto getSincById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (Produto) getItemQuerySinc(true, ProdutoDBHelperBase.DB_TABLE_SINC, ProdutoDBHelperBase.COLS_SINC, "id_produto = " + id + "", null, null, null, null,null);
    }
    
    
    public long getMaxId() {
		String sql = "select max(id_produto) from " + DB_TABLE;
		return this.getNumeroSql(sql);
	}
	protected String orderByColuna() {
    	return null;
    }
	
	
	public List<Produto> getPorLidoEmLojaVirtual(Context contexto, long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_loja_virtual_le = " + id, null, null, null, null);
	}
	public List<Produto> getPorLidoEmLojaVirtual(long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_loja_virtual_le = " + id, null, null, null, null);
	}
	
	
	
	public List<Produto> getPorPossuiMarca(Context contexto, long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_marca_p = " + id, null, null, null, null);
	}
	public List<Produto> getPorPossuiMarca(long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_marca_p = " + id, null, null, null, null);
	}
	
	
  
  	
  
  
  	
	/*
	public Produto obtemPorModeloProdutoProdutoReferenteA(long id) {
		string sql;
		sql = "select " + camposOrdenados() +
			" from " + tabelaSelect() +
			innerJoinModeloProdutoProduto_ReferenteA() + 
			" where id_modelo_produto_produto = " + id;
		return (Produto) getObjeto(sql);
	}
	*/
	public static String innerJoinModeloProdutoProduto_ReferenteA() {
		return " inner join " + ModeloProdutoProdutoDBHelperBase.DB_TABLE + " on " + ModeloProdutoProdutoDBHelperBase.DB_TABLE + ".id_produto_ra = " + DB_TABLE + ".id_produto ";  
	}
	public static String innerJoinSincModeloProdutoProduto_ReferenteA() {
		return " inner join " + ModeloProdutoProdutoDBHelperBase.DB_TABLE_SINC + " on " + ModeloProdutoProdutoDBHelperBase.DB_TABLE_SINC + ".id_produto_ra = " + DB_TABLE_SINC + ".id_produto ";  
	}
	public static String outerJoinModeloProdutoProduto_ReferenteA() {
		return " left outer join " + ModeloProdutoProdutoDBHelperBase.DB_TABLE + " on " + ModeloProdutoProdutoDBHelperBase.DB_TABLE + ".id_produto_ra = " + DB_TABLE + ".id_produto ";  
	}
	public static String outerJoinSincModeloProdutoProduto_ReferenteA() {
		return " left outer join " + ModeloProdutoProdutoDBHelperBase.DB_TABLE_SINC + " on " + ModeloProdutoProdutoDBHelperBase.DB_TABLE_SINC + ".id_produto_ra = " + DB_TABLE_SINC + ".id_produto ";  
	}
 	
	/*
	public Produto obtemPorPrecoProdutoPossui(long id) {
		string sql;
		sql = "select " + camposOrdenados() +
			" from " + tabelaSelect() +
			innerJoinPrecoProduto_Possui() + 
			" where id_preco_produto = " + id;
		return (Produto) getObjeto(sql);
	}
	*/
	public static String innerJoinPrecoProduto_Possui() {
		return " inner join " + PrecoProdutoDBHelperBase.DB_TABLE + " on " + PrecoProdutoDBHelperBase.DB_TABLE + ".id_produto_pa = " + DB_TABLE + ".id_produto ";  
	}
	public static String innerJoinSincPrecoProduto_Possui() {
		return " inner join " + PrecoProdutoDBHelperBase.DB_TABLE_SINC + " on " + PrecoProdutoDBHelperBase.DB_TABLE_SINC + ".id_produto_pa = " + DB_TABLE_SINC + ".id_produto ";  
	}
	public static String outerJoinPrecoProduto_Possui() {
		return " left outer join " + PrecoProdutoDBHelperBase.DB_TABLE + " on " + PrecoProdutoDBHelperBase.DB_TABLE + ".id_produto_pa = " + DB_TABLE + ".id_produto ";  
	}
	public static String outerJoinSincPrecoProduto_Possui() {
		return " left outer join " + PrecoProdutoDBHelperBase.DB_TABLE_SINC + " on " + PrecoProdutoDBHelperBase.DB_TABLE_SINC + ".id_produto_pa = " + DB_TABLE_SINC + ".id_produto ";  
	}
 	
	/*
	public Produto obtemPorCategoriaLojaProdutoPossui(long id) {
		string sql;
		sql = "select " + camposOrdenados() +
			" from " + tabelaSelect() +
			innerJoinCategoriaLojaProduto_Possui() + 
			" where id_categoria_loja_produto = " + id;
		return (Produto) getObjeto(sql);
	}
	*/
	public static String innerJoinCategoriaLojaProduto_Possui() {
		return " inner join " + CategoriaLojaProdutoDBHelperBase.DB_TABLE + " on " + CategoriaLojaProdutoDBHelperBase.DB_TABLE + ".id_produto_ra = " + DB_TABLE + ".id_produto ";  
	}
	public static String innerJoinSincCategoriaLojaProduto_Possui() {
		return " inner join " + CategoriaLojaProdutoDBHelperBase.DB_TABLE_SINC + " on " + CategoriaLojaProdutoDBHelperBase.DB_TABLE_SINC + ".id_produto_ra = " + DB_TABLE_SINC + ".id_produto ";  
	}
	public static String outerJoinCategoriaLojaProduto_Possui() {
		return " left outer join " + CategoriaLojaProdutoDBHelperBase.DB_TABLE + " on " + CategoriaLojaProdutoDBHelperBase.DB_TABLE + ".id_produto_ra = " + DB_TABLE + ".id_produto ";  
	}
	public static String outerJoinSincCategoriaLojaProduto_Possui() {
		return " left outer join " + CategoriaLojaProdutoDBHelperBase.DB_TABLE_SINC + " on " + CategoriaLojaProdutoDBHelperBase.DB_TABLE_SINC + ".id_produto_ra = " + DB_TABLE_SINC + ".id_produto ";  
	}
 	
	/*
	public Produto obtemPorOportunidadeDiaPodePossuir(long id) {
		string sql;
		sql = "select " + camposOrdenados() +
			" from " + tabelaSelect() +
			innerJoinOportunidadeDia_PodePossuir() + 
			" where id_oportunidade_dia = " + id;
		return (Produto) getObjeto(sql);
	}
	*/
	public static String innerJoinOportunidadeDia_PodePossuir() {
		return " inner join " + OportunidadeDiaDBHelperBase.DB_TABLE + " on " + OportunidadeDiaDBHelperBase.DB_TABLE + ".id_produto_ra = " + DB_TABLE + ".id_produto ";  
	}
	public static String innerJoinSincOportunidadeDia_PodePossuir() {
		return " inner join " + OportunidadeDiaDBHelperBase.DB_TABLE_SINC + " on " + OportunidadeDiaDBHelperBase.DB_TABLE_SINC + ".id_produto_ra = " + DB_TABLE_SINC + ".id_produto ";  
	}
	public static String outerJoinOportunidadeDia_PodePossuir() {
		return " left outer join " + OportunidadeDiaDBHelperBase.DB_TABLE + " on " + OportunidadeDiaDBHelperBase.DB_TABLE + ".id_produto_ra = " + DB_TABLE + ".id_produto ";  
	}
	public static String outerJoinSincOportunidadeDia_PodePossuir() {
		return " left outer join " + OportunidadeDiaDBHelperBase.DB_TABLE_SINC + " on " + OportunidadeDiaDBHelperBase.DB_TABLE_SINC + ".id_produto_ra = " + DB_TABLE_SINC + ".id_produto ";  
	}
 	
	/*
	public Produto obtemPorPalavraProdutoPossui(long id) {
		string sql;
		sql = "select " + camposOrdenados() +
			" from " + tabelaSelect() +
			innerJoinPalavraProduto_Possui() + 
			" where id_palavra_produto = " + id;
		return (Produto) getObjeto(sql);
	}
	*/
	public static String innerJoinPalavraProduto_Possui() {
		return " inner join " + PalavraProdutoDBHelperBase.DB_TABLE + " on " + PalavraProdutoDBHelperBase.DB_TABLE + ".id_produto_ra = " + DB_TABLE + ".id_produto ";  
	}
	public static String innerJoinSincPalavraProduto_Possui() {
		return " inner join " + PalavraProdutoDBHelperBase.DB_TABLE_SINC + " on " + PalavraProdutoDBHelperBase.DB_TABLE_SINC + ".id_produto_ra = " + DB_TABLE_SINC + ".id_produto ";  
	}
	public static String outerJoinPalavraProduto_Possui() {
		return " left outer join " + PalavraProdutoDBHelperBase.DB_TABLE + " on " + PalavraProdutoDBHelperBase.DB_TABLE + ".id_produto_ra = " + DB_TABLE + ".id_produto ";  
	}
	public static String outerJoinSincPalavraProduto_Possui() {
		return " left outer join " + PalavraProdutoDBHelperBase.DB_TABLE_SINC + " on " + PalavraProdutoDBHelperBase.DB_TABLE_SINC + ".id_produto_ra = " + DB_TABLE_SINC + ".id_produto ";  
	}
 	
	
	
	// Relacionamento onde esse objeto ? chave estrangeira de outro. ????
	
	private boolean _obtemLojaVirtual_LidoEm = false;
	public void setObtemLojaVirtual_LidoEm() {
		_obtemLojaVirtual_LidoEm = true;
	}
	protected String outterJoinLojaVirtual_LidoEm() {
		return " left outer join " + LojaVirtualDBHelperBase.DB_TABLE + " on " + LojaVirtualDBHelperBase.DB_TABLE + ".id_loja_virtual = " + DB_TABLE + ".id_loja_virtual_le ";  
	}
	public boolean atualizaLidoEmLojaVirtual(long idProduto, long idLojaVirtualLe) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_loja_virtual_le  = " + idLojaVirtualLe +
        " where id_produto = " +  idProduto;
       	//this.executaSql(sql);
       	return true;
	}
	public Produto obtemPorIdsLidoEmLojaVirtual(long idProduto, long idLojaVirtualLe) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_loja_virtual_le = " + idLojaVirtualLe + " and " +
			  " id_produto = " + idProduto;
		return (Produto) this.geObjetoSql(sql);
	}
	
	
	public static String innerJoinLojaVirtual_LidoEm() {
		return " inner join " + LojaVirtualDBHelperBase.DB_TABLE + " on " + LojaVirtualDBHelperBase.DB_TABLE + ".id_loja_virtual = " + DB_TABLE + ".id_loja_virtual_le ";  
	}
	public static String outerJoinLojaVirtual_LidoEm() {
		return " left outer join " + LojaVirtualDBHelperBase.DB_TABLE + " on " + LojaVirtualDBHelperBase.DB_TABLE + ".id_loja_virtual = " + DB_TABLE + ".id_loja_virtual_le ";  
	}
	public static String innerJoinSincLojaVirtual_LidoEm() {
		return " inner join " + LojaVirtualDBHelperBase.DB_TABLE_SINC + " on " + LojaVirtualDBHelperBase.DB_TABLE_SINC + ".id_loja_virtual = " + DB_TABLE_SINC + ".id_loja_virtual_le ";  
	}
	public static String outerJoinSincLojaVirtual_LidoEm() {
		return " left outer join " + LojaVirtualDBHelperBase.DB_TABLE_SINC + " on " + LojaVirtualDBHelperBase.DB_TABLE_SINC + ".id_loja_virtual = " + DB_TABLE_SINC + ".id_loja_virtual_le ";  
	}
	
 	
	private boolean _obtemMarca_Possui = false;
	public void setObtemMarca_Possui() {
		_obtemMarca_Possui = true;
	}
	protected String outterJoinMarca_Possui() {
		return " left outer join " + MarcaDBHelperBase.DB_TABLE + " on " + MarcaDBHelperBase.DB_TABLE + ".id_marca = " + DB_TABLE + ".id_marca_p ";  
	}
	public boolean atualizaPossuiMarca(long idProduto, long idMarcaP) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_marca_p  = " + idMarcaP +
        " where id_produto = " +  idProduto;
       	//this.executaSql(sql);
       	return true;
	}
	public Produto obtemPorIdsPossuiMarca(long idProduto, long idMarcaP) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_marca_p = " + idMarcaP + " and " +
			  " id_produto = " + idProduto;
		return (Produto) this.geObjetoSql(sql);
	}
	
	
	public static String innerJoinMarca_Possui() {
		return " inner join " + MarcaDBHelperBase.DB_TABLE + " on " + MarcaDBHelperBase.DB_TABLE + ".id_marca = " + DB_TABLE + ".id_marca_p ";  
	}
	public static String outerJoinMarca_Possui() {
		return " left outer join " + MarcaDBHelperBase.DB_TABLE + " on " + MarcaDBHelperBase.DB_TABLE + ".id_marca = " + DB_TABLE + ".id_marca_p ";  
	}
	public static String innerJoinSincMarca_Possui() {
		return " inner join " + MarcaDBHelperBase.DB_TABLE_SINC + " on " + MarcaDBHelperBase.DB_TABLE_SINC + ".id_marca = " + DB_TABLE_SINC + ".id_marca_p ";  
	}
	public static String outerJoinSincMarca_Possui() {
		return " left outer join " + MarcaDBHelperBase.DB_TABLE_SINC + " on " + MarcaDBHelperBase.DB_TABLE_SINC + ".id_marca = " + DB_TABLE_SINC + ".id_marca_p ";  
	}
	
 	
	// ********************************************************************	
	
	
	public static String camposOrdenados() 
	{
		return " produto.id_produto " 
		+ " ,produto.url_origem " 
		+ " ,produto.imagem_local " 
		+ " ,produto.data_inclusao " 
		+ " ,produto.url " 
		+ " ,produto.nome " 
		+ " ,produto.posicao_produto " 
		+ " ,produto.imagem " 
		+ " ,produto.id_loja_virtual_le " 
		+ " ,produto.id_marca_p " 
		;
	}
	public static String camposOrdenadosSinc() 
	{
		return " produto_sinc.id_produto " 
		+ " ,produto_sinc.url_origem " 
		+ " ,produto_sinc.imagem_local " 
		+ " ,produto_sinc.data_inclusao " 
		+ " ,produto_sinc.url " 
		+ " ,produto_sinc.nome " 
		+ " ,produto_sinc.posicao_produto " 
		+ " ,produto_sinc.imagem " 
		+ " ,produto_sinc.id_loja_virtual_le " 
		+ " ,produto_sinc.id_marca_p " 
		
		+ " ,produto_sinc.operacao_sinc "
		;
	}
	public static String camposOrdenadosAlias(String nomeTabela) 
	{
		return  nomeTabela + ".id_produto " 
		+ " , " + nomeTabela + ".url_origem " 
		+ " , " + nomeTabela + ".imagem_local " 
		+ " , " +  "DATE_FORMAT(" + nomeTabela + ".data_inclusao,'%d-%m-%Y %H:%i:%s') " 
		+ " , " + nomeTabela + ".url " 
		+ " , " + nomeTabela + ".nome " 
		+ " , " + nomeTabela + ".posicao_produto " 
		+ " , " + nomeTabela + ".imagem " 
		+ " , " + nomeTabela + ".id_loja_virtual_le " 
		+ " , " + nomeTabela + ".id_marca_p " 
		;
	}
	
	protected String camposOrdenadosJoin()
    {
        String saida = camposOrdenados();
		saida += (this._obtemLojaVirtual_LidoEm?" , " +LojaVirtualDBHelperBase.camposOrdenados():"");
		saida += (this._obtemMarca_Possui?" , " +MarcaDBHelperBase.camposOrdenados():"");
        return saida;
    }
    
        
    public void limpaObtem()
    {
		_obtemLojaVirtual_LidoEm = false;
		_obtemMarca_Possui = false;
    }
    
	protected String outterJoinAgrupado()
    {
        String saida = " ";
		saida += (this._obtemLojaVirtual_LidoEm? outterJoinLojaVirtual_LidoEm() + " ":"");
		saida += (this._obtemMarca_Possui? outterJoinMarca_Possui() + " ":"");
        return saida;
    }
    protected MontadorDaoI getMontadorAgrupado()
    {
        MontadorDaoComposite montador = new MontadorDaoComposite();
        montador.adicionaMontador(new ProdutoMontador(), null);
		if (this._obtemLojaVirtual_LidoEm)
            montador.adicionaMontador(new LojaVirtualMontador(), "LojaVirtual_LidoEm");
		if (this._obtemMarca_Possui)
            montador.adicionaMontador(new MarcaMontador(), "Marca_Possui");
         return montador;
    }
	
    
   	// Poderia passar para classe abstrata.
    public final List<Produto> getAllSinc() throws DaoException {
    	this.setMontador(null);
    	List<Produto> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<Produto>();
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
    public final List<Produto> getAllSincSoAlteracao() throws DaoException {
    	List<Produto> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    		saida = null; // Melhorar aqui !!!!
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<Produto>();
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
	
	public final List<Produto> getAllSincImpl() throws DaoException {
   		String sql = "select " + camposOrdenadosSinc()   
   			+ " from " + this.DB_TABLE_SINC;
   		ProdutoMontador montador = new ProdutoMontador(true); // sinc ligado
   		this.setMontador(montador);
   		List<Produto> saida = this.getListaSql(sql);
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
		this.insert((Produto) obj);
	}
	@Override
	public final void dropCreate() {
		this.dropTable();
		this.criaTabela();
	}
}
