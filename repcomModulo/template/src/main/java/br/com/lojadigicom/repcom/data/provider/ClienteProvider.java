
package  br.com.lojadigicom.repcom.data.provider;


import br.com.lojadigicom.repcom.data.contract.*;
import br.com.lojadigicom.repcom.data.helper.AplicacaoDbHelper;
import android.content.ContentValues;
import android.content.ContentResolver;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.database.SQLException;
import android.database.DatabaseUtils;
import android.net.Uri;
import br.com.lojadigicom.repcom.framework.log.DCLog;
import java.util.List;
import android.content.ContentProvider;

public abstract class ClienteProvider {

	private int qtdeLinhas = 0;
	
	public static final int CLIENTE = 39600;
	public static final int CLIENTE_ID = 39601;
	public static final int CLIENTE_SINC = 39603;
	public static final int CLIENTE_E_COMPLEMENTO = 39604;
	public static final int CLIENTE_ID_E_COMPLEMENTO = 39605;
	//public static final int CLIENTE_OPERACAO = 39602;
	
	// deletes
	public static final int CLIENTE_DELETE_ALL_SINC = 39606;
	public static final int CLIENTE_DELETE_RECREATE = 39607;
	public static final int CLIENTE_DELETE_SINC = 39608;
	public static final int CLIENTE_E_RETIRADA = 39609;
	
	private static final String sPorChaveSel = ClienteContract.TABLE_NAME + "." + ClienteContract.COLUNA_CHAVE + " = ? ";
	
	
	

 	public static final int OBTEMLISTAAGENDATEL = 39620;
 	public static final int SINCRONIZAAGENDATEL = 39621;
 	public static final int OBTEMPORIDAGENDATEL = 39622;
 	public static final int PREENCHEDERIVADAAGENDATEL = 39623;
 	public static final int DESATIVAPORID = 39624;
 	public static final int LISTAATIVOS = 39625;
 	public static final int PESQUISATRECHONOME = 39626;

	private ContentProvider mProvider = null;


	public void setContentProvider(ContentProvider valor) {
		mProvider = valor;
	}

	protected static final SQLiteQueryBuilder sQueryBuilder;
	static {
		sQueryBuilder = new SQLiteQueryBuilder();
		String tabelas = ClienteContract.TABLE_NAME;
		
		sQueryBuilder.setTables(tabelas);
	}
	private static final SQLiteQueryBuilder sQueryBuilderSinc;
	static {
		sQueryBuilderSinc = new SQLiteQueryBuilder();
		String tabelas = ClienteContract.TABLE_NAME_SINC;
		sQueryBuilderSinc.setTables(tabelas);
	}
	
	
	protected AplicacaoDbHelper mOpenHelper = null;
	
	public int getLinhas() {
		return qtdeLinhas;
	}
	
	public static void buildUriMatcher(UriMatcher matcher) {
		matcher.addURI(ClienteContract.getContentAuthority(), ClienteContract.PATH, CLIENTE);
		matcher.addURI(ClienteContract.getContentAuthority(), ClienteContract.PATH + "/Sinc" , CLIENTE_SINC);
		matcher.addURI(ClienteContract.getContentAuthority(), ClienteContract.PATH + "/#"    , CLIENTE_ID);
	
		matcher.addURI(ClienteContract.getContentAuthority(), ClienteContract.PATH + "/#/" + ClienteContract.COM_COMPLEMENTO + "/*" , CLIENTE_ID_E_COMPLEMENTO);
		matcher.addURI(ClienteContract.getContentAuthority(), ClienteContract.PATH + "/" + ClienteContract.COM_COMPLEMENTO + "/*" , CLIENTE_E_COMPLEMENTO);
		matcher.addURI(ClienteContract.getContentAuthority(), ClienteContract.PATH + "/" + ClienteContract.COM_RETIRADA + "/*" , CLIENTE_E_RETIRADA);
		
		
		//matcher.addURI(AplicacaoContract.CONTENT_AUTHORITY, ClienteContract.PATH + "/operacao/*" , CLIENTE_OPERACAO);
		
		
		
		matcher.addURI(ClienteContract.getContentAuthority(), ClienteContract.PATH + "/ObtemListaAgendaTel/*", OBTEMLISTAAGENDATEL);
		
		matcher.addURI(ClienteContract.getContentAuthority(), ClienteContract.PATH + "/SincronizaAgendaTel/*", SINCRONIZAAGENDATEL);
		
		matcher.addURI(ClienteContract.getContentAuthority(), ClienteContract.PATH + "/ObtemPorIdAgendaTel/*", OBTEMPORIDAGENDATEL);
		
		matcher.addURI(ClienteContract.getContentAuthority(), ClienteContract.PATH + "/PreencheDerivadaAgendaTel/*", PREENCHEDERIVADAAGENDATEL);
		
		matcher.addURI(ClienteContract.getContentAuthority(), ClienteContract.PATH + "/DesativaPorId/*", DESATIVAPORID);
		
		matcher.addURI(ClienteContract.getContentAuthority(), ClienteContract.PATH + "/ListaAtivos/*", LISTAATIVOS);
		
		matcher.addURI(ClienteContract.getContentAuthority(), ClienteContract.PATH + "/PesquisaTrechoNome/*", PESQUISATRECHONOME);
		
		
		// Deletes
		matcher.addURI(ClienteContract.getContentAuthority(), ClienteContract.PATH + "/DeleteAllSinc" , 	CLIENTE_DELETE_ALL_SINC);
		matcher.addURI(ClienteContract.getContentAuthority(), ClienteContract.PATH + "/DeleteAllRecreate" , CLIENTE_DELETE_RECREATE);
		matcher.addURI(ClienteContract.getContentAuthority(), ClienteContract.PATH + "/DeleteSinc/#" , 		CLIENTE_DELETE_SINC);
	}
	
	
	
	
	public void setAplicacaoDbHelper(AplicacaoDbHelper db) {
		mOpenHelper = db;
	}
	
	public Cursor query(UriMatcher sUriMatcher, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Query Uri:" + uri.toString());
		Cursor retCursor = null;
		switch (sUriMatcher.match(uri)) {
            case CLIENTE:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CLIENTE");
                retCursor = query(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case CLIENTE_SINC:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CLIENTE_SINC");
                retCursor = querySinc(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case CLIENTE_ID:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CLIENTE_ID");
            	String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorChaveSel, args, sortOrder);
                break;
            }
            case CLIENTE_ID_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CLIENTE_ID_E_COMPLEMENTO");
				String id = uri.getPathSegments().get(1);	
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri) +
						" where " + ClienteContract.COLUNA_CHAVE + " = " + id;
				retCursor = queryRaw(sql);
				break;
			}
			case CLIENTE_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CLIENTE_E_COMPLEMENTO");
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case CLIENTE_E_RETIRADA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CLIENTE_E_RETIRADA");
				String sql = "select " +  ClienteContract.camposOrdenados() +
						" from " + ClienteContract.TABLE_NAME +
						sqlWhere(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
		
	 		case OBTEMLISTAAGENDATEL:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: OBTEMLISTAAGENDATEL");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryObtemListaAgendaTel()");
				//retCursor = queryObtemListaAgendaTel(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryObtemListaAgendaTel(param) + (sortOrder!=null?" order by " + sortOrder:"");
				DCLog.d(DCLog.TRACE_LISTA,this,"SQL: " + sql);
				retCursor = this.queryRaw(sql);
				DCLog.d(DCLog.TRACE_LISTA,this,"Cursor: " + retCursor.getCount() + " linhas");
				break;
			}
		
	 		case SINCRONIZAAGENDATEL:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: SINCRONIZAAGENDATEL");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar querySincronizaAgendaTel()");
				//retCursor = querySincronizaAgendaTel(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = querySincronizaAgendaTel(param) + (sortOrder!=null?" order by " + sortOrder:"");
				DCLog.d(DCLog.TRACE_LISTA,this,"SQL: " + sql);
				retCursor = this.queryRaw(sql);
				DCLog.d(DCLog.TRACE_LISTA,this,"Cursor: " + retCursor.getCount() + " linhas");
				break;
			}
		
	 		case OBTEMPORIDAGENDATEL:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: OBTEMPORIDAGENDATEL");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryObtemPorIdAgendaTel()");
				//retCursor = queryObtemPorIdAgendaTel(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryObtemPorIdAgendaTel(param) + (sortOrder!=null?" order by " + sortOrder:"");
				DCLog.d(DCLog.TRACE_LISTA,this,"SQL: " + sql);
				retCursor = this.queryRaw(sql);
				DCLog.d(DCLog.TRACE_LISTA,this,"Cursor: " + retCursor.getCount() + " linhas");
				break;
			}
		
	 		case PREENCHEDERIVADAAGENDATEL:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PREENCHEDERIVADAAGENDATEL");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryPreencheDerivadaAgendaTel()");
				//retCursor = queryPreencheDerivadaAgendaTel(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryPreencheDerivadaAgendaTel(param) + (sortOrder!=null?" order by " + sortOrder:"");
				DCLog.d(DCLog.TRACE_LISTA,this,"SQL: " + sql);
				retCursor = this.queryRaw(sql);
				DCLog.d(DCLog.TRACE_LISTA,this,"Cursor: " + retCursor.getCount() + " linhas");
				break;
			}
		
	 		case DESATIVAPORID:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: DESATIVAPORID");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryDesativaPorId()");
				//retCursor = queryDesativaPorId(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryDesativaPorId(param) + (sortOrder!=null?" order by " + sortOrder:"");
				DCLog.d(DCLog.TRACE_LISTA,this,"SQL: " + sql);
				retCursor = this.queryRaw(sql);
				DCLog.d(DCLog.TRACE_LISTA,this,"Cursor: " + retCursor.getCount() + " linhas");
				break;
			}
		
	 		case LISTAATIVOS:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: LISTAATIVOS");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryListaAtivos()");
				//retCursor = queryListaAtivos(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryListaAtivos(param) + (sortOrder!=null?" order by " + sortOrder:"");
				DCLog.d(DCLog.TRACE_LISTA,this,"SQL: " + sql);
				retCursor = this.queryRaw(sql);
				DCLog.d(DCLog.TRACE_LISTA,this,"Cursor: " + retCursor.getCount() + " linhas");
				break;
			}
		
	 		case PESQUISATRECHONOME:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PESQUISATRECHONOME");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryPesquisaTrechoNome()");
				//retCursor = queryPesquisaTrechoNome(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryPesquisaTrechoNome(param) + (sortOrder!=null?" order by " + sortOrder:"");
				DCLog.d(DCLog.TRACE_LISTA,this,"SQL: " + sql);
				retCursor = this.queryRaw(sql);
				DCLog.d(DCLog.TRACE_LISTA,this,"Cursor: " + retCursor.getCount() + " linhas");
				break;
			}
		
		}	
        return retCursor;
	}
	
	private String sqlWhere(Uri uri) {
		String sql = " where ";
		// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("SemClienteInteresseCategoriaAssociada",uri.getPathSegments())) {
			sql += ClienteContract.COLUNA_CHAVE + " not in (select " + 
					ClienteInteresseCategoriaContract.COLUNA_ID_CLIENTE_A + " from " +
					ClienteInteresseCategoriaContract.TABLE_NAME + ")";
		}
	
		if (existeItem("SemContatoClienteEstabelece",uri.getPathSegments())) {
			sql += ClienteContract.COLUNA_CHAVE + " not in (select " + 
					ContatoClienteContract.COLUNA_ID_CLIENTE_RA + " from " +
					ContatoClienteContract.TABLE_NAME + ")";
		}
	
		if (existeItem("SemVendaComprou",uri.getPathSegments())) {
			sql += ClienteContract.COLUNA_CHAVE + " not in (select " + 
					VendaContract.COLUNA_ID_CLIENTE_FP + " from " +
					VendaContract.TABLE_NAME + ")";
		}
	
		
		return sql;
	}
	
	private String sqlSelect(Uri uri) {
		String sql = ClienteContract.camposOrdenados();
		List<String> segmentos = uri.getPathSegments();
		// ComChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
	
	// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComClienteInteresseCategoriaAssociada",uri.getPathSegments())) {
			sql += "," +  ClienteInteresseCategoriaContract.camposOrdenados();
		}
	
		if (existeItem("ComContatoClienteEstabelece",uri.getPathSegments())) {
			sql += "," +  ContatoClienteContract.camposOrdenados();
		}
	
		if (existeItem("ComVendaComprou",uri.getPathSegments())) {
			sql += "," +  VendaContract.camposOrdenados();
		}
	
		
		
		
		return sql;
	}
	private String sqlFrom(Uri uri) {
		String sql = ClienteContract.TABLE_NAME;
		List<String> segmentos = uri.getPathSegments();
		//if (existeItem("ComEpisodioReferenteA",uri.getPathSegments())) {
		//	sql += " " +  EpisodioUsuarioContract.innerJoinEpisodio_ReferenteA();
		//}
		
		
				// ComChaveEstrangeira
  	
	
	// SemChaveEstrangeira
  	
		if (existeItem("ComClienteInteresseCategoriaAssociada",uri.getPathSegments())) {
			sql += " " +  ClienteContract.outerJoinClienteInteresseCategoria_Associada();
		}
	
		if (existeItem("ComContatoClienteEstabelece",uri.getPathSegments())) {
			sql += " " +  ClienteContract.outerJoinContatoCliente_Estabelece();
		}
	
		if (existeItem("ComVendaComprou",uri.getPathSegments())) {
			sql += " " +  ClienteContract.outerJoinVenda_Comprou();
		}
	
		
		return sql;
	}

	private boolean existeItem(String ref, List<String> lista) {
		for (String item : lista) {
			if (ref.compareToIgnoreCase(item)==0) return true;
		}
		return false;
	}
	
	
	
	private String getOrderBy() {
		String campoOrder = getCampoOrder();
		if (campoOrder==null) return "";
		return " order by " + campoOrder;
	}
	protected String getCampoOrder() {
		return null;
	}
	
	
	protected abstract String queryObtemListaAgendaTel(String param);
	
	protected abstract String querySincronizaAgendaTel(String param);
	
	protected abstract String queryObtemPorIdAgendaTel(String param);
	
	protected abstract String queryPreencheDerivadaAgendaTel(String param);
	
	protected abstract String queryDesativaPorId(String param);
	
	protected abstract String queryListaAtivos(String param);
	
	protected abstract String queryPesquisaTrechoNome(String param);
	
	
	
	protected Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		 return sQueryBuilder.query(mOpenHelper.getReadableDatabase(),
	                projection,
	                selection,
	                selectionArgs,
	                null,
	                null,
	                sortOrder
	        );
	}
	protected Cursor querySinc(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		 return sQueryBuilderSinc.query(mOpenHelper.getReadableDatabase(),
	                projection,
	                selection,
	                selectionArgs,
	                null,
	                null,
	                sortOrder
	        );
	}
	protected Cursor queryRaw(String sql) {
		//DCLog.d(DCLog.DATABASE_ADM,this,sql);
		DCLog.d(DCLog.QUERY_SQL,this,sql);
		final SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		return db.rawQuery(sql,null);
	}
	
	public String getType(UriMatcher sUriMatcher, Uri uri) {
		switch (sUriMatcher.match(uri)) {
            case CLIENTE:
            {
                return ClienteContract.getContentType();
            }
            case CLIENTE_ID:
            {
            	return ClienteContract.getContentItemType();
            }
		}	
		return null;
	}
	
	public Uri insert(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Uri returnUri;
		long idNovo = getMaxId(db)+1;
		values.put(ClienteContract.COLUNA_CHAVE, idNovo);
		long _id = db.insert(ClienteContract.TABLE_NAME, null, values);
		if (_id > 0) {
			//DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + ClienteContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
			DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + values.toString() + " (id:" + _id + ")");
			returnUri = ClienteContract.buildClienteUri(idNovo);
			values.put(ClienteContract.COLUNA_OPERACAO_SINC,"I");
			insereSinc(db,values);
			notificaUriRelacionadas();
			notificaOutrasUri(mProvider.getContext().getContentResolver());
		} else {
			throw new android.database.SQLException("Falha no insert em " + uri);
		}
		return returnUri;
	}
	
	
	// considerando com o mesmo princ?pio do insert.
	// delete na tabela atual e verific
	public boolean delete(UriMatcher sUriMatcher, Uri uri, String selection, String[] selectionArgs) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaDelete =0;
		switch (sUriMatcher.match(uri)) {
			case CLIENTE_DELETE_SINC: {
				String id = uri.getPathSegments().get(2);
				Cursor cursor = db.query(ClienteContract.TABLE_NAME,null,ClienteContract.COLUNA_CHAVE + " = ? ",new String[]{id},null,null,null);
				if (cursor.moveToFirst()) {
					ContentValues valores = new ContentValues();
					DatabaseUtils.cursorRowToContentValues(cursor, valores);
					linhaDelete = db.delete(ClienteContract.TABLE_NAME, ClienteContract.COLUNA_CHAVE + " = ? ", new String[]{id});
					DCLog.d(DCLog.DATABASE_CRUD,this,"delete " + ClienteContract.TABLE_NAME + "(id:" + id + ")");
					valores.put(ClienteContract.COLUNA_OPERACAO_SINC, "D");
					insereSinc(db,valores);
				}
				notificaUriRelacionadas();
				mProvider.getContext().getContentResolver().notifyChange(ClienteContract.buildAll(), null);
				break;
			}
			case CLIENTE_DELETE_ALL_SINC: {
				linhaDelete = db.delete(ClienteContract.TABLE_NAME_SINC, null, null);
				break;
			}
			case CLIENTE_DELETE_RECREATE: {
				linhaDelete = db.delete(ClienteContract.TABLE_NAME, null, null);
				break;
			}
		}
		return true;
	}
	
	
	public boolean update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		DCLog.d(DCLog.DATABASE_CRUD,this,"update2 " + values.toString() );
		linhaUpdate = db.update(ClienteContract.TABLE_NAME, values, selection, selectionArgs);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	public boolean update(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		String selection = ClienteContract.COLUNA_CHAVE + " = ? ";
		String dados[] = {values.get(ClienteContract.COLUNA_CHAVE).toString()};
		DCLog.d(DCLog.DATABASE_CRUD,this,"update1 " + values.toString() );
		linhaUpdate = db.update(ClienteContract.TABLE_NAME, values, selection, dados);
		values.put(ClienteContract.COLUNA_OPERACAO_SINC,"A");
		insereSinc(db,values);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	
	private void insereSinc(SQLiteDatabase db, ContentValues values) {
		db.insert(ClienteContract.TABLE_NAME_SINC, null, values);
		notificaUriOperacoes();
	}
	
	protected abstract void notificaOutrasUri(ContentResolver resolver);
	
	
	// Notificar todas as uris de entidades que possuem chave estrangeira dessa entidade.
	private void notificaUriRelacionadas() {
		// ComChaveEstrangeira
  	
	}
	private void notificaUriOperacoes() {
	
		mProvider.getContext().getContentResolver().notifyChange(ClienteContract.buildObtemListaAgendaTel(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(ClienteContract.buildSincronizaAgendaTel(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(ClienteContract.buildObtemPorIdAgendaTel(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(ClienteContract.buildPreencheDerivadaAgendaTel(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(ClienteContract.buildDesativaPorId(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(ClienteContract.buildListaAtivos(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(ClienteContract.buildPesquisaTrechoNome(), null);
	
	}
	private void notificaUriRaiz(){
		
	}
	
	
	
	public int bulkInsert(Uri uri, ContentValues[] values) {
    	final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        db.beginTransaction();
        int returnCount = 0;
        try {
        	for (ContentValues value : values) {
				String[] args = {(String)value.get(ClienteContract.COLUNA_CHAVE)};
				Cursor retCursor = query(null, sPorChaveSel, args, null);
				if (retCursor.moveToFirst()) {
						db.update(ClienteContract.TABLE_NAME,value,sPorChaveSel,args);
						//DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk " + ClienteContract.TABLE_NAME + "  " + values.toString());
						DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk id:" + value.get(ClienteContract.COLUNA_CHAVE) + ")" + values.toString());
				} else {
					long _id = db.insert(ClienteContract.TABLE_NAME, null, value);
					if (_id != -1) {
						//DCLog.d(DCLog.DATABASE_CRUD,this,"insert (bulk)" + ClienteContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
						DCLog.d(DCLog.DATABASE_CRUD,this,"insert (bulk)" + values.toString() + " (id:" + _id + ")");
						returnCount++;
					}
				}
            }
            db.setTransactionSuccessful();
            notificaUriOperacoes();
   		} finally {
 			db.endTransaction();
        }
        return returnCount;
    }
	public int bulkInsertOld(Uri uri, ContentValues[] values) {
    	final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        db.beginTransaction();
        int returnCount = 0;
        try {
        	for (ContentValues value : values) {
            	long _id = db.insert(ClienteContract.TABLE_NAME, null, value);
            	if (_id != -1) {
            		returnCount++;
            	}
            }
            db.setTransactionSuccessful();
   		} finally {
 			db.endTransaction();
        }
        return returnCount;
    }
    
    
    public long getMaxId(SQLiteDatabase db) {
		String sql = "select max(" + ClienteContract.COLUNA_CHAVE + ") from " + ClienteContract.TABLE_NAME;
		return this.getNumeroSql(sql,db);
	}
	
	// Arquitetura - Padrao
	protected long getNumeroSql(String sql,SQLiteDatabase db) {
		long saida = 0;
		Cursor c = null;
		try {
			c =db.rawQuery(sql, null);
			c.moveToFirst();
			saida = c.getLong(0);
		} catch (SQLException e) {
			DCLog.e(DCLog.DATABASE_ERRO_CORE, this, e);
		} finally {
			if (c != null && !c.isClosed()) {
				c.close();
			}
		}
		return saida;
	}
    
}