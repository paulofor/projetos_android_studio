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
import repcom.modelo.vo.ClienteVo;
import repcom.modelo.vo.FabricaVo;
import repcom.dao.*;
import repcom.dao.montador.*;
import repcom.dao.datasource.DataSourceAplicacao;

public abstract class ClienteDBHelperBase extends DBHelperBase
	implements DaoSincronismo // coloquei aqui para evitar impacto de arquitetura
{

    private static final String DB_NAME = "w_alert";
    public static final String DB_TABLE = "cliente";
    public static final String DB_TABLE_SINC = "cliente_sinc";
    public static final int DB_VERSION = 3;

    protected static final String CLASSNAME = ClienteDBHelperBase.class.getSimpleName();
    protected static final String[] COLS = new String[] { 
        "id_cliente"
        ,"endereco_rua"
        ,"endereco_numero"
        ,"endereco_complemento"
        ,"endereco_cep"
        ,"endereco_bairro"
        ,"endereco_cidade"
        ,"endereco_uf"
        ,"observacoes"
        ,"codigo_lista_contato"
        ,"nome"
        ,"ativo"
		, "id_usuario_s"
	
    };

	protected static final String[] COLS_SINC = new String[] { 
        "id_cliente"
        ,"endereco_rua"
        ,"endereco_numero"
        ,"endereco_complemento"
        ,"endereco_cep"
        ,"endereco_bairro"
        ,"endereco_cidade"
        ,"endereco_uf"
        ,"observacoes"
        ,"codigo_lista_contato"
        ,"nome"
        ,"ativo"
		, "id_usuario_s"
		, "operacao_sinc"
    };

    protected SQLiteDatabase db;
    protected final DBOpenHelper dbOpenHelper;

	@Override
	protected MontadorDaoI criaMontadorPadrao() {
		return new ClienteMontador();
	}
	
	protected String getSqlIndices() {
		return "";
	}
	protected String getSqlCreate(){
		return  "CREATE TABLE "
        + ClienteDBHelperBase.DB_TABLE + " ("
        + "  id_cliente INTEGER PRIMARY KEY "
        + " , endereco_rua TEXT "
        + " , endereco_numero TEXT "
        + " , endereco_complemento TEXT "
        + " , endereco_cep TEXT "
        + " , endereco_bairro TEXT "
        + " , endereco_cidade TEXT "
        + " , endereco_uf TEXT "
        + " , observacoes TEXT "
        + " , codigo_lista_contato TEXT "
        + " , nome TEXT "
        + " , ativo NUMERIC "
		+ " , id_usuario_s INTEGER "
		
		+ getSqlIndices()
        + ");";
	}

	

	public static final String DB_CREATE_SINCRONIZADA = "CREATE TABLE IF NOT EXISTS "
        + ClienteDBHelperBase.DB_TABLE_SINC + " ("
        + "  id_cliente INTEGER "
        + " , endereco_rua TEXT "
        + " , endereco_numero TEXT "
        + " , endereco_complemento TEXT "
        + " , endereco_cep TEXT "
        + " , endereco_bairro TEXT "
        + " , endereco_cidade TEXT "
        + " , endereco_uf TEXT "
        + " , observacoes TEXT "
        + " , codigo_lista_contato TEXT "
        + " , nome TEXT "
        + " , ativo NUMERIC "
		+ " , id_usuario_s INTEGER "
		
        + ", operacao_sinc TEXT);";


    public static final String DB_CREATE = "CREATE TABLE IF NOT EXISTS "
        + ClienteDBHelperBase.DB_TABLE + " ("
        + "  id_cliente INTEGER PRIMARY KEY "
        + " , endereco_rua TEXT "
        + " , endereco_numero TEXT "
        + " , endereco_complemento TEXT "
        + " , endereco_cep TEXT "
        + " , endereco_bairro TEXT "
        + " , endereco_cidade TEXT "
        + " , endereco_uf TEXT "
        + " , observacoes TEXT "
        + " , codigo_lista_contato TEXT "
        + " , nome TEXT "
        + " , ativo NUMERIC "
		+ " , id_usuario_s INTEGER "
		
        + ");";
    
    private static final String DB_DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private static final String DB_DROP = "DROP TABLE IF EXISTS " + DB_TABLE;
    private static final String DB_DROP_SINCRONIZADA = "DROP TABLE IF EXISTS " + DB_TABLE_SINC;
    
    private static class DBOpenHelper extends SQLiteOpenHelper {

       

        public DBOpenHelper(final Context context) {
            super(context, ClienteDBHelperBase.DB_NAME, null, ClienteDBHelperBase.DB_VERSION);
        }

        @Override
        public void onCreate(final SQLiteDatabase db) {
            try {
                db.execSQL(DB_CREATE);
            } catch (SQLException e) {
                Log.e(Constants.LOGTAG, ClienteDBHelperBase.CLASSNAME, e);
            }
        }

        @Override
        public void onOpen(final SQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + ClienteDBHelperBase.DB_TABLE);
            onCreate(db);
        }
    }

    //
    // end inner classes
    //
    
    // Versao Nova
 	public ClienteDBHelperBase() {
    	this.dbOpenHelper = null;
    	setDataSource(DataSourceAplicacao.getInstancia());
    }
    
   
	
	protected ContentValues montaValores(final DCIObjetoDominio valor) {
		Cliente item = (Cliente) valor;
		ContentValues valores = new ContentValues();
       	putValor(valores,"id_cliente",item.getIdCliente());
       	
       	putValor(valores,"endereco_rua",item.getEnderecoRua());
       	
       	putValor(valores,"endereco_numero",item.getEnderecoNumero());
       	
       	putValor(valores,"endereco_complemento",item.getEnderecoComplemento());
       	
       	putValor(valores,"endereco_cep",item.getEnderecoCep());
       	
       	putValor(valores,"endereco_bairro",item.getEnderecoBairro());
       	
       	putValor(valores,"endereco_cidade",item.getEnderecoCidade());
       	
       	putValor(valores,"endereco_uf",item.getEnderecoUf());
       	
       	putValor(valores,"observacoes",item.getObservacoes());
       	
       	putValor(valores,"codigo_lista_contato",item.getCodigoListaContato());
       	
       	putValor(valores,"nome",item.getNome());
       	
       	putValor(valores,"ativo",item.getAtivo());
       	
       	putValor(valores,"id_usuario_s",item.getIdUsuarioS());
       	
        return valores;
	}


    // **** Chamadas Diretas Objeto Banco de Dados
    private void registraErroChamadaDireta(Exception e) {
    	Log.e(Constants.LOGTAG, ClienteDBHelperBase.CLASSNAME, e);
    }
   
    public final void insert(final Cliente item) {
	    try {
	        getDb().insert(ClienteDBHelperBase.DB_TABLE, null, montaValores(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    public final void update(final Cliente item) {
	    try {
	        getDb().update(ClienteDBHelperBase.DB_TABLE, montaValores(item), "id_cliente=" + item.getIdCliente(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     // Nao pode ser final o parametro para retornar o objeto com seu Id
    public final void insertSinc(Cliente item) {
        try {
        	item.setIdCliente((int)getMaxId() + 1);
        	DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc: " + item.debug());
	        long id = getDb().insert(ClienteDBHelperBase.DB_TABLE, null, montaValores(item));
    	    ((ObjetoSinc)item).setOperacaoSinc("I");
        	getDb().insert(ClienteDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
     public final void updateSinc(final Cliente item) {
        try {
	        DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc: " + item.debug());
	        getDb().update(ClienteDBHelperBase.DB_TABLE, montaValores(item), "id_cliente=" + item.getIdCliente(), null);
	        ((ObjetoSinc)item).setOperacaoSinc("A");
	        Cliente atual = this.getSincById(item.getId());
	        if (atual==null) {
	        	getDb().insert(ClienteDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
	        } else {
	        	if ("I".equals(((ObjetoSinc)atual).getOperacaoSinc()))
	        		((ObjetoSinc)item).setOperacaoSinc("I");
	        	getDb().update(ClienteDBHelperBase.DB_TABLE_SINC, montaValoresSinc(item), "id_cliente=" + item.getIdCliente(), null);
	        }
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    protected final void delete(final long id) {
        try {
			getDb().delete(ClienteDBHelperBase.DB_TABLE, "id_cliente=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    /*
    private void deleteSinc(final long id) {
        try {
			getDb().delete(ClienteDBHelperBase.DB_TABLE_SINC, "id_cliente=" + id, null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
	*/
    public void limpaSinc(final Cliente item) {
    	try {
			getDb().delete(ClienteDBHelperBase.DB_TABLE_SINC, "id_cliente=" + item.getId(), null);
	    } catch (SQLiteCantOpenDatabaseException e) {
	    	registraErroChamadaDireta(e);
	    }
    }
    
    public void deleteSinc(final Cliente item) {
    	try {
	        DCLog.dStack(DCLog.DATABASE_ADM, this, "deleteSinc: " + item.debug());
	        delete(item.getId());
	        ((ObjetoSinc)item).setOperacaoSinc("D");
        	getDb().insert(ClienteDBHelperBase.DB_TABLE_SINC, null, montaValoresSinc(item));
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
    public Cliente getById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (Cliente) getItemQuery(true, ClienteDBHelperBase.DB_TABLE, ClienteDBHelperBase.COLS, "id_cliente = " + id + "", null, null, null, null,null);
    }
    
    // Esta com orderBy que pode ser bom para exibicoes em tela
    // mas nao e bom para sincronizacao, pensar em ter um metodo para tela e outro para sinc.
    public List<Cliente> getAll() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, null);
    }
    public List<Cliente> getAllTela() {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return getListaQuery(DB_TABLE, COLS, null, null, null, null, orderByColuna());
    }
    
    private Cliente getByRowId(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (Cliente) getItemQuery(true, ClienteDBHelperBase.DB_TABLE, ClienteDBHelperBase.COLS, "ROWID = " + id + "", null, null, null, null,null);
    }
    private Cliente getSincById(final long id) {
    	setMontador(null); // A consulta anterior pode ter usado um MontadorDaoComposite
    	return (Cliente) getItemQuerySinc(true, ClienteDBHelperBase.DB_TABLE_SINC, ClienteDBHelperBase.COLS_SINC, "id_cliente = " + id + "", null, null, null, null,null);
    }
    
    
    public long getMaxId() {
		String sql = "select max(id_cliente) from " + DB_TABLE;
		return this.getNumeroSql(sql);
	}
	protected String orderByColuna() {
    	return null;
    }
	
  
  	
  
  
  	
	/*
	public Cliente obtemPorClienteInteresseCategoriaAssociada(long id) {
		string sql;
		sql = "select " + camposOrdenados() +
			" from " + tabelaSelect() +
			innerJoinClienteInteresseCategoria_Associada() + 
			" where id_cliente_interesse_categoria = " + id;
		return (Cliente) getObjeto(sql);
	}
	*/
	public static String innerJoinClienteInteresseCategoria_Associada() {
		return " inner join " + ClienteInteresseCategoriaDBHelperBase.DB_TABLE + " on " + ClienteInteresseCategoriaDBHelperBase.DB_TABLE + ".id_cliente_a = " + DB_TABLE + ".id_cliente ";  
	}
	public static String innerJoinSincClienteInteresseCategoria_Associada() {
		return " inner join " + ClienteInteresseCategoriaDBHelperBase.DB_TABLE_SINC + " on " + ClienteInteresseCategoriaDBHelperBase.DB_TABLE_SINC + ".id_cliente_a = " + DB_TABLE_SINC + ".id_cliente ";  
	}
	public static String outerJoinClienteInteresseCategoria_Associada() {
		return " left outer join " + ClienteInteresseCategoriaDBHelperBase.DB_TABLE + " on " + ClienteInteresseCategoriaDBHelperBase.DB_TABLE + ".id_cliente_a = " + DB_TABLE + ".id_cliente ";  
	}
	public static String outerJoinSincClienteInteresseCategoria_Associada() {
		return " left outer join " + ClienteInteresseCategoriaDBHelperBase.DB_TABLE_SINC + " on " + ClienteInteresseCategoriaDBHelperBase.DB_TABLE_SINC + ".id_cliente_a = " + DB_TABLE_SINC + ".id_cliente ";  
	}
 	
	/*
	public Cliente obtemPorContatoClienteEstabelece(long id) {
		string sql;
		sql = "select " + camposOrdenados() +
			" from " + tabelaSelect() +
			innerJoinContatoCliente_Estabelece() + 
			" where id_contato_cliente = " + id;
		return (Cliente) getObjeto(sql);
	}
	*/
	public static String innerJoinContatoCliente_Estabelece() {
		return " inner join " + ContatoClienteDBHelperBase.DB_TABLE + " on " + ContatoClienteDBHelperBase.DB_TABLE + ".id_cliente_ra = " + DB_TABLE + ".id_cliente ";  
	}
	public static String innerJoinSincContatoCliente_Estabelece() {
		return " inner join " + ContatoClienteDBHelperBase.DB_TABLE_SINC + " on " + ContatoClienteDBHelperBase.DB_TABLE_SINC + ".id_cliente_ra = " + DB_TABLE_SINC + ".id_cliente ";  
	}
	public static String outerJoinContatoCliente_Estabelece() {
		return " left outer join " + ContatoClienteDBHelperBase.DB_TABLE + " on " + ContatoClienteDBHelperBase.DB_TABLE + ".id_cliente_ra = " + DB_TABLE + ".id_cliente ";  
	}
	public static String outerJoinSincContatoCliente_Estabelece() {
		return " left outer join " + ContatoClienteDBHelperBase.DB_TABLE_SINC + " on " + ContatoClienteDBHelperBase.DB_TABLE_SINC + ".id_cliente_ra = " + DB_TABLE_SINC + ".id_cliente ";  
	}
 	
	/*
	public Cliente obtemPorVendaComprou(long id) {
		string sql;
		sql = "select " + camposOrdenados() +
			" from " + tabelaSelect() +
			innerJoinVenda_Comprou() + 
			" where id_venda = " + id;
		return (Cliente) getObjeto(sql);
	}
	*/
	public static String innerJoinVenda_Comprou() {
		return " inner join " + VendaDBHelperBase.DB_TABLE + " on " + VendaDBHelperBase.DB_TABLE + ".id_cliente_fp = " + DB_TABLE + ".id_cliente ";  
	}
	public static String innerJoinSincVenda_Comprou() {
		return " inner join " + VendaDBHelperBase.DB_TABLE_SINC + " on " + VendaDBHelperBase.DB_TABLE_SINC + ".id_cliente_fp = " + DB_TABLE_SINC + ".id_cliente ";  
	}
	public static String outerJoinVenda_Comprou() {
		return " left outer join " + VendaDBHelperBase.DB_TABLE + " on " + VendaDBHelperBase.DB_TABLE + ".id_cliente_fp = " + DB_TABLE + ".id_cliente ";  
	}
	public static String outerJoinSincVenda_Comprou() {
		return " left outer join " + VendaDBHelperBase.DB_TABLE_SINC + " on " + VendaDBHelperBase.DB_TABLE_SINC + ".id_cliente_fp = " + DB_TABLE_SINC + ".id_cliente ";  
	}
 	
	
	
	// Relacionamento onde esse objeto ? chave estrangeira de outro. ????
	
	// ********************************************************************	
	
	
	public static String camposOrdenados() 
	{
		return " cliente.id_cliente " 
		+ " ,cliente.endereco_rua " 
		+ " ,cliente.endereco_numero " 
		+ " ,cliente.endereco_complemento " 
		+ " ,cliente.endereco_cep " 
		+ " ,cliente.endereco_bairro " 
		+ " ,cliente.endereco_cidade " 
		+ " ,cliente.endereco_uf " 
		+ " ,cliente.observacoes " 
		+ " ,cliente.codigo_lista_contato " 
		+ " ,cliente.nome " 
		+ " ,cliente.ativo " 
		+ " ,cliente.id_usuario_s " 
		;
	}
	public static String camposOrdenadosSinc() 
	{
		return " cliente_sinc.id_cliente " 
		+ " ,cliente_sinc.endereco_rua " 
		+ " ,cliente_sinc.endereco_numero " 
		+ " ,cliente_sinc.endereco_complemento " 
		+ " ,cliente_sinc.endereco_cep " 
		+ " ,cliente_sinc.endereco_bairro " 
		+ " ,cliente_sinc.endereco_cidade " 
		+ " ,cliente_sinc.endereco_uf " 
		+ " ,cliente_sinc.observacoes " 
		+ " ,cliente_sinc.codigo_lista_contato " 
		+ " ,cliente_sinc.nome " 
		+ " ,cliente_sinc.ativo " 
		+ " ,cliente_sinc.id_usuario_s " 
		
		+ " ,cliente_sinc.operacao_sinc "
		;
	}
	public static String camposOrdenadosAlias(String nomeTabela) 
	{
		return  nomeTabela + ".id_cliente " 
		+ " , " + nomeTabela + ".endereco_rua " 
		+ " , " + nomeTabela + ".endereco_numero " 
		+ " , " + nomeTabela + ".endereco_complemento " 
		+ " , " + nomeTabela + ".endereco_cep " 
		+ " , " + nomeTabela + ".endereco_bairro " 
		+ " , " + nomeTabela + ".endereco_cidade " 
		+ " , " + nomeTabela + ".endereco_uf " 
		+ " , " + nomeTabela + ".observacoes " 
		+ " , " + nomeTabela + ".codigo_lista_contato " 
		+ " , " + nomeTabela + ".nome " 
		+ " , " + nomeTabela + ".ativo " 
		+ " , " + nomeTabela + ".id_usuario_s " 
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
        montador.adicionaMontador(new ClienteMontador(), null);
         return montador;
    }
	
    
   	// Poderia passar para classe abstrata.
    public final List<Cliente> getAllSinc() throws DaoException {
    	this.setMontador(null);
    	List<Cliente> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<Cliente>();
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
    public final List<Cliente> getAllSincSoAlteracao() throws DaoException {
    	List<Cliente> saida = null;
    	try {
    		saida = this.getAllSincImpl();
    		saida = null; // Melhorar aqui !!!!
    	} catch (SQLException e) {
    		if (e.getMessage().indexOf("")!=-1) {
    			saida = new ArrayList<Cliente>();
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
	
	public final List<Cliente> getAllSincImpl() throws DaoException {
   		String sql = "select " + camposOrdenadosSinc()   
   			+ " from " + this.DB_TABLE_SINC;
   		ClienteMontador montador = new ClienteMontador(true); // sinc ligado
   		this.setMontador(montador);
   		List<Cliente> saida = this.getListaSql(sql);
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
		this.insert((Cliente) obj);
	}
	@Override
	public final void dropCreate() {
		this.dropTable();
		this.criaTabela();
	}
}
