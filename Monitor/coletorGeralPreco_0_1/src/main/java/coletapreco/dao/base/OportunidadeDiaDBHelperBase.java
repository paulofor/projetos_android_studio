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
import coletapreco.modelo.vo.OportunidadeDiaVo;
import coletapreco.modelo.vo.FabricaVo;
import coletapreco.dao.*;
import coletapreco.dao.montador.*;
import coletapreco.dao.datasource.DataSourceAplicacao;

public abstract class OportunidadeDiaDBHelperBase extends DBHelperBase
	implements DaoSincronismo // coloquei aqui para evitar impacto de arquitetura
{

    private static final String DB_NAME = "w_alert";
    public static final String DB_TABLE = "oportunidade_dia";
    public static final String DB_TABLE_SINC = "oportunidade_dia_sinc";
    public static final int DB_VERSION = 3;

    protected static final String CLASSNAME = OportunidadeDiaDBHelperBase.class.getSimpleName();
    protected static final String[] COLS = new String[] { 
        "id_oportunidade_dia"
        ,"url_produto"
        ,"nome_produto"
        ,"data_inicio_preco_atual"
        ,"nome_marca"
        ,"url_afiliado"
        ,"data_ultima_preco_anterior"
        ,"imagem_local"
        ,"url_imagem"
        ,"posicao_produto"
        ,"preco_venda_anterior"
        ,"preco_venda_atual"
        ,"preco_boleto_anterior"
        ,"preco_boleto_atual"
        ,"preco_parcela_anterior"
        ,"preco_parcela_atual"
        ,"quantidade_parcela_anterior"
        ,"quantidade_parcela_atual"
        ,"percentual_ajuste_venda"
        ,"percentual_ajuste_boleto"
        ,"nome_loja_virtual"
		, "id_produto_ra"
	
		, "id_natureza_produto_pa"
	
    };

	protected static final String[] COLS_SINC = new String[] { 
        "id_oportunidade_dia"
        ,"url_produto"
        ,"nome_produto"
        ,"data_inicio_preco_atual"
        ,"nome_marca"
        ,"url_afiliado"
        ,"data_ultima_preco_anterior"
        ,"imagem_local"
        ,"url_imagem"
        ,"posicao_produto"
        ,"preco_venda_anterior"
        ,"preco_venda_atual"
        ,"preco_boleto_anterior"
        ,"preco_boleto_atual"
        ,"preco_parcela_anterior"
        ,"preco_parcela_atual"
        ,"quantidade_parcela_anterior"
        ,"quantidade_parcela_atual"
        ,"percentual_ajuste_venda"
        ,"percentual_ajuste_boleto"
        ,"nome_loja_virtual"
		, "id_produto_ra"
	
		, "id_natureza_produto_pa"
		, "operacao_sinc"
    };

    protected SQLiteDatabase db;
    protected final DBOpenHelper dbOpenHelper;

	@Override
	protected MontadorDaoI criaMontadorPadrao() {
		return new OportunidadeDiaMontador();
	}
	
	protected String getSqlIndices() {
		return "";
	}
	protected String getSqlCreate(){
		return  "CREATE TABLE "
        + OportunidadeDiaDBHelperBase.DB_TABLE + " ("
        + "  id_oportunidade_dia INTEGER PRIMARY KEY "
        + " , url_produto TEXT "
        + " , nome_produto TEXT "
        + " , data_inicio_preco_atual INTEGER "
        + " , nome_marca TEXT "
        + " , url_afiliado TEXT "
        + " , data_ultima_preco_anterior INTEGER "
        + " , imagem_local TEXT "
        + " , url_imagem TEXT "
        + " , posicao_produto INTEGER "
        + " , preco_venda_anterior REAL "
        + " , preco_venda_atual REAL "
        + " , preco_boleto_anterior REAL "
        + " , preco_boleto_atual REAL "
        + " , preco_parcela_anterior REAL "
        + " , preco_parcela_atual REAL "
        + " , quantidade_parcela_anterior INTEGER "
        + " , quantidade_parcela_atual INTEGER "
        + " , percentual_ajuste_venda REAL "
        + " , percentual_ajuste_boleto REAL "
        + " , nome_loja_virtual TEXT "
		+ " , id_produto_ra INTEGER "
		
		+ " , id_natureza_produto_pa INTEGER "
		
		+ getSqlIndices()
        + ");";
	}

	

	public static final String DB_CREATE_SINCRONIZADA = "CREATE TABLE IF NOT EXISTS "
        + OportunidadeDiaDBHelperBase.DB_TABLE_SINC + " ("
        + "  id_oportunidade_dia INTEGER "
        + " , url_produto TEXT "
        + " , nome_produto TEXT "
        + " , data_inicio_preco_atual INTEGER "
        + " , nome_marca TEXT "
        + " , url_afiliado TEXT "
        + " , data_ultima_preco_anterior INTEGER "
        + " , imagem_local TEXT "
        + " , url_imagem TEXT "
        + " , posicao_produto INTEGER "
        + " , preco_venda_anterior REAL "
        + " , preco_venda_atual REAL "
        + " , preco_boleto_anterior REAL "
        + " , preco_boleto_atual REAL "
        + " , preco_parcela_anterior REAL "
        + " , preco_parcela_atual REAL "
        + " , quantidade_parcela_anterior INTEGER "
        + " , quantidade_parcela_atual INTEGER "
        + " , percentual_ajuste_venda REAL "
        + " , percentual_ajuste_boleto REAL "
        + " , nome_loja_virtual TEXT "
		+ " , id_produto_ra INTEGER "
		
		+ " , id_natureza_produto_pa INTEGER "
		
        + ", operacao_sinc TEXT);";


    public static final String DB_CREATE = "CREATE TABLE IF NOT EXISTS "
        + OportunidadeDiaDBHelperBase.DB_TABLE + " ("
        + "  id_oportunidade_dia INTEGER PRIMARY KEY "
        + " , url_produto TEXT "
        + " , nome_produto TEXT "
        + " , data_inicio_preco_atual INTEGER "
        + " , nome_marca TEXT "
        + " , url_afiliado TEXT "
        + " , data_ultima_preco_anterior INTEGER "
        + " , imagem_local TEXT "
        + " , url_imagem TEXT "
        + " , posicao_produto INTEGER "
        + " , preco_venda_anterior REAL "
        + " , preco_venda_atual REAL "
        + " , preco_boleto_anterior REAL "
        + " , preco_boleto_atual REAL "
        + " , preco_parcela_anterior REAL "
        + " , preco_parcela_atual REAL "
        + " , quantidade_parcela_anterior INTEGER "
        + " , quantidade_parcela_atual INTEGER "
        + " , percentual_ajuste_venda REAL "
        + " , percentual_ajuste_boleto REAL "
        + " , nome_loja_virtual TEXT "
		+ " , id_produto_ra INTEGER "
		
		+ " , id_natureza_produto_pa INTEGER "
		
        + ");";
    
    private static final String DB_DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private static final String DB_DROP = "DROP TABLE IF EXISTS " + DB_TABLE;
    private static final String DB_DROP_SINCRONIZADA = "DROP TABLE IF EXISTS " + DB_TABLE_SINC;
    
    private static class DBOpenHelper extends SQLiteOpenHelper {

       

        public DBOpenHelper(final Context context) {
            super(context, OportunidadeDiaDBHelperBase.DB_NAME, null, OportunidadeDiaDBHelperBase.DB_VERSION);
        }

        @Override
        public void onCreate(final SQLiteDatabase db) {
            try {
                db.execSQL(DB_CREATE);
            } catch (SQLException e) {
                Log.e(Constants.LOGTAG, OportunidadeDiaDBHelperBase.CLASSNAME, e);
            }
        }

        @Override
        public void onOpen(final SQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + OportunidadeDiaDBHelperBase.DB_TABLE);
            onCreate(db);
        }
    }

    //
    // end inner classes
    //
    
    // Versao Nova
 	public OportunidadeDiaDBHelperBase() {
    	this.dbOpenHelper = null;
    	setDataSource(DataSourceAplicacao.getInstancia());
    }
    
   
	
	protected ContentValues montaValores(final DCIObjetoDominio valor) {
		OportunidadeDia item = (OportunidadeDia) valor;
		ContentValues valores = new ContentValues();
       	putValor(valores,"id_oportunidade_dia",item.getIdOportunidadeDia());
       	
       	putValor(valores,"url_produto",item.getUrlProduto());
       	
       	putValor(valores,"nome_produto",item.getNomeProduto());
       	
       	putValorData(valores,"data_inicio_preco_atual",item.getDataInicioPrecoAtual());
        		
       	putValor(valores,"nome_marca",item.getNomeMarca());
       	
       	putValor(valores,"url_afiliado",item.getUrlAfiliado());
       	
       	putValorData(valores,"data_ultima_preco_anterior",item.getDataUltimaPrecoAnterior());
        		
       	putValor(valores,"imagem_local",item.getImagemLocal());
       	
       	putValor(valores,"url_imagem",item.getUrlImagem());
       	
       	putValor(valores,"posicao_produto",item.getPosicaoProduto());
       	
       	putValor(valores,"preco_venda_anterior",item.getPrecoVendaAnterior());
       	
       	putValor(valores,"preco_venda_atual",item.getPrecoVendaAtual());
       	
       	putValor(valores,"preco_boleto_anterior",item.getPrecoBoletoAnterior());
       	
       	putValor(valores,"preco_boleto_atual",item.getPrecoBoletoAtual());
       	
       	putValor(valores,"preco_parcela_anterior",item.getPrecoParcelaAnterior());
       	
       	putValor(valores,"preco_parcela_atual",item.getPrecoParcelaAtual());
       	
       	putValor(valores,"quantidade_parcela_anterior",item.getQuantidadeParcelaAnterior());
       	
       	putValor(valores,"quantidade_parcela_atual",item.getQuantidadeParcelaAtual());
       	
       	putValor(valores,"percentual_ajuste_venda",item.getPercentualAjusteVenda());
       	
       	putValor(valores,"percentual_ajuste_boleto",item.getPercentualAjusteBoleto());
       	
       	putValor(valores,"nome_loja_virtual",item.getNomeLojaVirtual());
       	
       	putValor(valores,"id_produto_ra",item.getIdProdutoRa());
       	
       	putValor(valores,"id_natureza_produto_pa",item.getIdNaturezaProdutoPa());
       	
        return valores;
	}


    // **** Chamadas Diretas Objeto Banco de Dados
    private void registraErroChamadaDireta(Exception e) {
    	Log.e(Constants.LOGTAG, OportunidadeDiaDBHelperBase.CLASSNAME, e);
    }
   
    public final void insert(final OportunidadeDia item) {
	    try {
	        getDb().insert(OportunidadeDiaDBHelperBase.DB_TABLE, null, montaValores(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    public final void update(final OportunidadeDia item) {
	    try {
	        getDb().update(OportunidadeDiaDBHelperBase.DB_TABLE, montaValores(item), "id_oportunidade_dia=" + item.getIdOportunidadeDia(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     // Nao pode ser final o parametro para retornar o objeto com seu Id
    public final void insertSinc(OportunidadeDia item) {
        try {
        	item.setIdOportunidadeDia((int)getMaxId() + 1);
        	DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc: " + item.debug());
	        long id = getDb().insert(OportunidadeDiaDBHelperBase.DB_TABLE, null, montaValores(item));
    	    ((ObjetoSinc)item).setOperacaoSinc("I");
        	getDb().insert(OportunidadeDiaDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     public final void updateSinc(final OportunidadeDia item) {
        try {
	        DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc: " + item.debug());
	        getDb().update(OportunidadeDiaDBHelperBase.DB_TABLE, montaValores(item), "id_oportunidade_dia=" + item.getIdOportunidadeDia(), null);
	        ((ObjetoSinc)item).setOperacaoSinc("A");
	        OportunidadeDia atual = this.getSincById(item.getId());
	        if (atual==null) {
	        	getDb().insert(OportunidadeDiaDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	        } else {
	        	if ("I".equals(((ObjetoSinc)atual).getOperacaoSinc()))
	        		((ObjetoSinc)item).setOperacaoSinc("I");
	        	getDb().update(OportunidadeDiaDBHelperBase.DB_TABLE_SINC, montaValoresSinc(item), "id_oportunidade_dia=" + item.getIdOportunidadeDia(), null);
	        }
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    protected final void delete(final long id) {
        try {
			getDb().delete(OportunidadeDiaDBHelperBase.DB_TABLE, "id_oportunidade_dia=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    /*
    private void deleteSinc(final long id) {
        try {
			getDb().delete(OportunidadeDiaDBHelperBase.DB_TABLE_SINC, "id_oportunidade_dia=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
	*/
    public void limpaSinc(final OportunidadeDia item) {
    	try {
			getDb().delete(OportunidadeDiaDBHelperBase.DB_TABLE_SINC, "id_oportunidade_dia=" + item.getId(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    
    public void deleteSinc(final OportunidadeDia item) {
    	try {
	        DCLog.dStack(DCLog.DATABASE_ADM, this, "deleteSinc: " + item.debug());
	        delete(item.getId());
	        ((ObjetoSinc)item).setOperacaoSinc("D");
        	getDb().insert(OportunidadeDiaDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
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
    public OportunidadeDia getById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (OportunidadeDia) getItemQuery(true, OportunidadeDiaDBHelperBase.DB_TABLE, OportunidadeDiaDBHelperBase.COLS, "id_oportunidade_dia = " + id + "", null, null, null, null,null);
    }
    
    // Esta com orderBy que pode ser bom para exibicoes em tela
    // mas nao e bom para sincronizacao, pensar em ter um metodo para tela e outro para sinc.
    public List<OportunidadeDia> getAll() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, null);
    }
    public List<OportunidadeDia> getAllTela() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, orderByColuna());
    }
    
    private OportunidadeDia getByRowId(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (OportunidadeDia) getItemQuery(true, OportunidadeDiaDBHelperBase.DB_TABLE, OportunidadeDiaDBHelperBase.COLS, "ROWID = " + id + "", null, null, null, null,null);
    }
    private OportunidadeDia getSincById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (OportunidadeDia) getItemQuerySinc(true, OportunidadeDiaDBHelperBase.DB_TABLE_SINC, OportunidadeDiaDBHelperBase.COLS_SINC, "id_oportunidade_dia = " + id + "", null, null, null, null,null);
    }
    
    
    public long getMaxId() {
		String sql = "select max(id_oportunidade_dia) from " + DB_TABLE;
		return this.getNumeroSql(sql);
	}
	protected String orderByColuna() {
    	return null;
    }
	
	
	public List<OportunidadeDia> getPorReferenteAProduto(Context contexto, long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_produto_ra = " + id, null, null, null, null);
	}
	public List<OportunidadeDia> getPorReferenteAProduto(long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_produto_ra = " + id, null, null, null, null);
	}
	
	
	
	public List<OportunidadeDia> getPorPertenceANaturezaProduto(Context contexto, long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_natureza_produto_pa = " + id, null, null, null, null);
	}
	public List<OportunidadeDia> getPorPertenceANaturezaProduto(long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_natureza_produto_pa = " + id, null, null, null, null);
	}
	
	
  
  	
  
  
  	
	
	
	// Relacionamento onde esse objeto ? chave estrangeira de outro. ????
	
	private boolean _obtemProduto_ReferenteA = false;
	public void setObtemProduto_ReferenteA() {
		_obtemProduto_ReferenteA = true;
	}
	protected String outterJoinProduto_ReferenteA() {
		return " left outer join " + ProdutoDBHelperBase.DB_TABLE + " on " + ProdutoDBHelperBase.DB_TABLE + ".id_produto = " + DB_TABLE + ".id_produto_ra ";  
	}
	public boolean atualizaReferenteAProduto(long idOportunidadeDia, long idProdutoRa) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_produto_ra  = " + idProdutoRa +
        " where id_oportunidade_dia = " +  idOportunidadeDia;
       	//this.executaSql(sql);
       	return true;
	}
	public OportunidadeDia obtemPorIdsReferenteAProduto(long idOportunidadeDia, long idProdutoRa) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_produto_ra = " + idProdutoRa + " and " +
			  " id_oportunidade_dia = " + idOportunidadeDia;
		return (OportunidadeDia) this.geObjetoSql(sql);
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
	
 	
	private boolean _obtemNaturezaProduto_PertenceA = false;
	public void setObtemNaturezaProduto_PertenceA() {
		_obtemNaturezaProduto_PertenceA = true;
	}
	protected String outterJoinNaturezaProduto_PertenceA() {
		return " left outer join " + NaturezaProdutoDBHelperBase.DB_TABLE + " on " + NaturezaProdutoDBHelperBase.DB_TABLE + ".id_natureza_produto = " + DB_TABLE + ".id_natureza_produto_pa ";  
	}
	public boolean atualizaPertenceANaturezaProduto(long idOportunidadeDia, long idNaturezaProdutoPa) {
		String sql;
      	sql = "update " + DB_TABLE + 
      	" set id_natureza_produto_pa  = " + idNaturezaProdutoPa +
        " where id_oportunidade_dia = " +  idOportunidadeDia;
       	//this.executaSql(sql);
       	return true;
	}
	public OportunidadeDia obtemPorIdsPertenceANaturezaProduto(long idOportunidadeDia, long idNaturezaProdutoPa) {
       	String sql;
		sql = "select " + camposOrdenados() + " from " + DB_TABLE +
			  "where " +
			  " id_natureza_produto_pa = " + idNaturezaProdutoPa + " and " +
			  " id_oportunidade_dia = " + idOportunidadeDia;
		return (OportunidadeDia) this.geObjetoSql(sql);
	}
	
	
	public static String innerJoinNaturezaProduto_PertenceA() {
		return " inner join " + NaturezaProdutoDBHelperBase.DB_TABLE + " on " + NaturezaProdutoDBHelperBase.DB_TABLE + ".id_natureza_produto = " + DB_TABLE + ".id_natureza_produto_pa ";  
	}
	public static String outerJoinNaturezaProduto_PertenceA() {
		return " left outer join " + NaturezaProdutoDBHelperBase.DB_TABLE + " on " + NaturezaProdutoDBHelperBase.DB_TABLE + ".id_natureza_produto = " + DB_TABLE + ".id_natureza_produto_pa ";  
	}
	public static String innerJoinSincNaturezaProduto_PertenceA() {
		return " inner join " + NaturezaProdutoDBHelperBase.DB_TABLE_SINC + " on " + NaturezaProdutoDBHelperBase.DB_TABLE_SINC + ".id_natureza_produto = " + DB_TABLE_SINC + ".id_natureza_produto_pa ";  
	}
	public static String outerJoinSincNaturezaProduto_PertenceA() {
		return " left outer join " + NaturezaProdutoDBHelperBase.DB_TABLE_SINC + " on " + NaturezaProdutoDBHelperBase.DB_TABLE_SINC + ".id_natureza_produto = " + DB_TABLE_SINC + ".id_natureza_produto_pa ";  
	}
	
 	
	// ********************************************************************	
	
	
	public static String camposOrdenados() 
	{
		return " oportunidade_dia.id_oportunidade_dia " 
		+ " ,oportunidade_dia.url_produto " 
		+ " ,oportunidade_dia.nome_produto " 
		+ " ,oportunidade_dia.data_inicio_preco_atual " 
		+ " ,oportunidade_dia.nome_marca " 
		+ " ,oportunidade_dia.url_afiliado " 
		+ " ,oportunidade_dia.data_ultima_preco_anterior " 
		+ " ,oportunidade_dia.imagem_local " 
		+ " ,oportunidade_dia.url_imagem " 
		+ " ,oportunidade_dia.posicao_produto " 
		+ " ,oportunidade_dia.preco_venda_anterior " 
		+ " ,oportunidade_dia.preco_venda_atual " 
		+ " ,oportunidade_dia.preco_boleto_anterior " 
		+ " ,oportunidade_dia.preco_boleto_atual " 
		+ " ,oportunidade_dia.preco_parcela_anterior " 
		+ " ,oportunidade_dia.preco_parcela_atual " 
		+ " ,oportunidade_dia.quantidade_parcela_anterior " 
		+ " ,oportunidade_dia.quantidade_parcela_atual " 
		+ " ,oportunidade_dia.percentual_ajuste_venda " 
		+ " ,oportunidade_dia.percentual_ajuste_boleto " 
		+ " ,oportunidade_dia.nome_loja_virtual " 
		+ " ,oportunidade_dia.id_produto_ra " 
		+ " ,oportunidade_dia.id_natureza_produto_pa " 
		;
	}
	public static String camposOrdenadosSinc() 
	{
		return " oportunidade_dia_sinc.id_oportunidade_dia " 
		+ " ,oportunidade_dia_sinc.url_produto " 
		+ " ,oportunidade_dia_sinc.nome_produto " 
		+ " ,oportunidade_dia_sinc.data_inicio_preco_atual " 
		+ " ,oportunidade_dia_sinc.nome_marca " 
		+ " ,oportunidade_dia_sinc.url_afiliado " 
		+ " ,oportunidade_dia_sinc.data_ultima_preco_anterior " 
		+ " ,oportunidade_dia_sinc.imagem_local " 
		+ " ,oportunidade_dia_sinc.url_imagem " 
		+ " ,oportunidade_dia_sinc.posicao_produto " 
		+ " ,oportunidade_dia_sinc.preco_venda_anterior " 
		+ " ,oportunidade_dia_sinc.preco_venda_atual " 
		+ " ,oportunidade_dia_sinc.preco_boleto_anterior " 
		+ " ,oportunidade_dia_sinc.preco_boleto_atual " 
		+ " ,oportunidade_dia_sinc.preco_parcela_anterior " 
		+ " ,oportunidade_dia_sinc.preco_parcela_atual " 
		+ " ,oportunidade_dia_sinc.quantidade_parcela_anterior " 
		+ " ,oportunidade_dia_sinc.quantidade_parcela_atual " 
		+ " ,oportunidade_dia_sinc.percentual_ajuste_venda " 
		+ " ,oportunidade_dia_sinc.percentual_ajuste_boleto " 
		+ " ,oportunidade_dia_sinc.nome_loja_virtual " 
		+ " ,oportunidade_dia_sinc.id_produto_ra " 
		+ " ,oportunidade_dia_sinc.id_natureza_produto_pa " 
		
		+ " ,oportunidade_dia_sinc.operacao_sinc "
		;
	}
	public static String camposOrdenadosAlias(String nomeTabela) 
	{
		return  nomeTabela + ".id_oportunidade_dia " 
		+ " , " + nomeTabela + ".url_produto " 
		+ " , " + nomeTabela + ".nome_produto " 
		+ " , " +  "DATE_FORMAT(" + nomeTabela + ".data_inicio_preco_atual,'%d-%m-%Y') " 
		+ " , " + nomeTabela + ".nome_marca " 
		+ " , " + nomeTabela + ".url_afiliado " 
		+ " , " +  "DATE_FORMAT(" + nomeTabela + ".data_ultima_preco_anterior,'%d-%m-%Y') " 
		+ " , " + nomeTabela + ".imagem_local " 
		+ " , " + nomeTabela + ".url_imagem " 
		+ " , " + nomeTabela + ".posicao_produto " 
		+ " , " + nomeTabela + ".preco_venda_anterior " 
		+ " , " + nomeTabela + ".preco_venda_atual " 
		+ " , " + nomeTabela + ".preco_boleto_anterior " 
		+ " , " + nomeTabela + ".preco_boleto_atual " 
		+ " , " + nomeTabela + ".preco_parcela_anterior " 
		+ " , " + nomeTabela + ".preco_parcela_atual " 
		+ " , " + nomeTabela + ".quantidade_parcela_anterior " 
		+ " , " + nomeTabela + ".quantidade_parcela_atual " 
		+ " , " + nomeTabela + ".percentual_ajuste_venda " 
		+ " , " + nomeTabela + ".percentual_ajuste_boleto " 
		+ " , " + nomeTabela + ".nome_loja_virtual " 
		+ " , " + nomeTabela + ".id_produto_ra " 
		+ " , " + nomeTabela + ".id_natureza_produto_pa " 
		;
	}
	
	protected String camposOrdenadosJoin()
    {
        String saida = camposOrdenados();
		saida += (this._obtemProduto_ReferenteA?" , " +ProdutoDBHelperBase.camposOrdenados():"");
		saida += (this._obtemNaturezaProduto_PertenceA?" , " +NaturezaProdutoDBHelperBase.camposOrdenados():"");
        return saida;
    }
    
        
    public void limpaObtem()
    {
		_obtemProduto_ReferenteA = false;
		_obtemNaturezaProduto_PertenceA = false;
    }
    
	protected String outterJoinAgrupado()
    {
        String saida = " ";
		saida += (this._obtemProduto_ReferenteA? outterJoinProduto_ReferenteA() + " ":"");
		saida += (this._obtemNaturezaProduto_PertenceA? outterJoinNaturezaProduto_PertenceA() + " ":"");
        return saida;
    }
    protected MontadorDaoI getMontadorAgrupado()
    {
        MontadorDaoComposite montador = new MontadorDaoComposite();
        montador.adicionaMontador(new OportunidadeDiaMontador(), null);
		if (this._obtemProduto_ReferenteA)
            montador.adicionaMontador(new ProdutoMontador(), "Produto_ReferenteA");
		if (this._obtemNaturezaProduto_PertenceA)
            montador.adicionaMontador(new NaturezaProdutoMontador(), "NaturezaProduto_PertenceA");
         return montador;
    }
	
    
   	// Poderia passar para classe abstrata.
    public final List<OportunidadeDia> getAllSinc() throws DaoException {
    	this.setMontador(null);
    	List<OportunidadeDia> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<OportunidadeDia>();
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
    public final List<OportunidadeDia> getAllSincSoAlteracao() throws DaoException {
    	List<OportunidadeDia> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    		saida = null; // Melhorar aqui !!!!
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<OportunidadeDia>();
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
	
	public final List<OportunidadeDia> getAllSincImpl() throws DaoException {
   		String sql = "select " + camposOrdenadosSinc()   
   			+ " from " + this.DB_TABLE_SINC;
   		OportunidadeDiaMontador montador = new OportunidadeDiaMontador(true); // sinc ligado
   		this.setMontador(montador);
   		List<OportunidadeDia> saida = this.getListaSql(sql);
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
		this.insert((OportunidadeDia) obj);
	}
	@Override
	public final void dropCreate() {
		this.dropTable();
		this.criaTabela();
	}
}
