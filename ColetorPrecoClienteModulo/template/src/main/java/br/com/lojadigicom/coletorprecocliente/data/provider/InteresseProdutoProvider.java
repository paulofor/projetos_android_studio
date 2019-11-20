
package  br.com.lojadigicom.coletorprecocliente.data.provider;


import br.com.lojadigicom.coletorprecocliente.data.contract.*;
import br.com.lojadigicom.coletorprecocliente.data.helper.AplicacaoDbHelper;
import android.content.ContentValues;
import android.content.ContentResolver;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.database.SQLException;
import android.database.DatabaseUtils;
import android.net.Uri;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import java.util.List;
import android.content.ContentProvider;

public abstract class InteresseProdutoProvider {

	private int qtdeLinhas = 0;
	
	public static final int INTERESSE_PRODUTO = 88300;
	public static final int INTERESSE_PRODUTO_ID = 88301;
	public static final int INTERESSE_PRODUTO_SINC = 88303;
	public static final int INTERESSE_PRODUTO_E_COMPLEMENTO = 88304;
	public static final int INTERESSE_PRODUTO_ID_E_COMPLEMENTO = 88305;
	//public static final int INTERESSE_PRODUTO_OPERACAO = 88302;
	
	// deletes
	public static final int INTERESSE_PRODUTO_DELETE_ALL_SINC = 88306;
	public static final int INTERESSE_PRODUTO_DELETE_RECREATE = 88307;
	public static final int INTERESSE_PRODUTO_DELETE_SINC = 88308;
	public static final int INTERESSE_PRODUTO_E_RETIRADA = 88309;
	
	private static final String sPorChaveSel = InteresseProdutoContract.TABLE_NAME + "." + InteresseProdutoContract.COLUNA_CHAVE + " = ? ";
	
	
	
	public static final int INTERESSE_PRODUTO_POR_PRODUTO_CLIENTE_REFERENTEA = 88320;
	public static final int COM_PRODUTO_CLIENTE = 88321;
	private static final String sPorIdProdutoClienteRaSelecao =
            InteresseProdutoContract.TABLE_NAME + ".id_produto_cliente_ra = ? ";
	

 	public static final int LISTAESPERA = 88322;
 	public static final int LISTAMONITORA = 88323;
 	public static final int OBTEMCOMPRODUTO = 88324;
 	public static final int QUANTIDADEMONITORADO = 88325;
 	public static final int QUANTIDADEMUDANCA = 88326;

	private ContentProvider mProvider = null;


	public void setContentProvider(ContentProvider valor) {
		mProvider = valor;
	}

	protected static final SQLiteQueryBuilder sQueryBuilder;
	static {
		sQueryBuilder = new SQLiteQueryBuilder();
		String tabelas = InteresseProdutoContract.TABLE_NAME;
		
		//tabelas += " inner join " + ProdutoClienteContract.TABLE_NAME + " ";
		//tabelas += " on " + ProdutoClienteContract.TABLE_NAME + "." + ProdutoClienteContract.COLUNA_CHAVE + " = " + InteresseProdutoContract.TABLE_NAME + "." + InteresseProdutoContract.COLUNA_ID_PRODUTO_CLIENTE_RA; 
		
		sQueryBuilder.setTables(tabelas);
	}
	private static final SQLiteQueryBuilder sQueryBuilderSinc;
	static {
		sQueryBuilderSinc = new SQLiteQueryBuilder();
		String tabelas = InteresseProdutoContract.TABLE_NAME_SINC;
		sQueryBuilderSinc.setTables(tabelas);
	}
	
	
	protected AplicacaoDbHelper mOpenHelper = null;
	
	public int getLinhas() {
		return qtdeLinhas;
	}
	
	public static void buildUriMatcher(UriMatcher matcher) {
		matcher.addURI(InteresseProdutoContract.getContentAuthority(), InteresseProdutoContract.PATH, INTERESSE_PRODUTO);
		matcher.addURI(InteresseProdutoContract.getContentAuthority(), InteresseProdutoContract.PATH + "/Sinc" , INTERESSE_PRODUTO_SINC);
		matcher.addURI(InteresseProdutoContract.getContentAuthority(), InteresseProdutoContract.PATH + "/#"    , INTERESSE_PRODUTO_ID);
	
		matcher.addURI(InteresseProdutoContract.getContentAuthority(), InteresseProdutoContract.PATH + "/#/" + InteresseProdutoContract.COM_COMPLEMENTO + "/*" , INTERESSE_PRODUTO_ID_E_COMPLEMENTO);
		matcher.addURI(InteresseProdutoContract.getContentAuthority(), InteresseProdutoContract.PATH + "/" + InteresseProdutoContract.COM_COMPLEMENTO + "/*" , INTERESSE_PRODUTO_E_COMPLEMENTO);
		matcher.addURI(InteresseProdutoContract.getContentAuthority(), InteresseProdutoContract.PATH + "/" + InteresseProdutoContract.COM_RETIRADA + "/*" , INTERESSE_PRODUTO_E_RETIRADA);
		
		
		//matcher.addURI(AplicacaoContract.CONTENT_AUTHORITY, InteresseProdutoContract.PATH + "/operacao/*" , INTERESSE_PRODUTO_OPERACAO);
		
		matcher.addURI(InteresseProdutoContract.getContentAuthority(), InteresseProdutoContract.PATH + "/#/" + ProdutoClienteContract.PATH, INTERESSE_PRODUTO_POR_PRODUTO_CLIENTE_REFERENTEA);
		matcher.addURI(InteresseProdutoContract.getContentAuthority(), InteresseProdutoContract.PATH + "/ComProdutoClienteReferenteA/" , COM_PRODUTO_CLIENTE);
		
		
		
		matcher.addURI(InteresseProdutoContract.getContentAuthority(), InteresseProdutoContract.PATH + "/ListaEspera/*", LISTAESPERA);
		
		matcher.addURI(InteresseProdutoContract.getContentAuthority(), InteresseProdutoContract.PATH + "/ListaMonitora/*", LISTAMONITORA);
		
		matcher.addURI(InteresseProdutoContract.getContentAuthority(), InteresseProdutoContract.PATH + "/ObtemComProduto/*", OBTEMCOMPRODUTO);
		
		matcher.addURI(InteresseProdutoContract.getContentAuthority(), InteresseProdutoContract.PATH + "/QuantidadeMonitorado/*", QUANTIDADEMONITORADO);
		
		matcher.addURI(InteresseProdutoContract.getContentAuthority(), InteresseProdutoContract.PATH + "/QuantidadeMudanca/*", QUANTIDADEMUDANCA);
		
		
		// Deletes
		matcher.addURI(InteresseProdutoContract.getContentAuthority(), InteresseProdutoContract.PATH + "/DeleteAllSinc" , 	INTERESSE_PRODUTO_DELETE_ALL_SINC);
		matcher.addURI(InteresseProdutoContract.getContentAuthority(), InteresseProdutoContract.PATH + "/DeleteAllRecreate" , INTERESSE_PRODUTO_DELETE_RECREATE);
		matcher.addURI(InteresseProdutoContract.getContentAuthority(), InteresseProdutoContract.PATH + "/DeleteSinc/#" , 		INTERESSE_PRODUTO_DELETE_SINC);
	}
	
	
	
	
	public void setAplicacaoDbHelper(AplicacaoDbHelper db) {
		mOpenHelper = db;
	}
	
	public Cursor query(UriMatcher sUriMatcher, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Query Uri:" + uri.toString());
		Cursor retCursor = null;
		switch (sUriMatcher.match(uri)) {
            case INTERESSE_PRODUTO:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: INTERESSE_PRODUTO");
                retCursor = query(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case INTERESSE_PRODUTO_SINC:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: INTERESSE_PRODUTO_SINC");
                retCursor = querySinc(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case INTERESSE_PRODUTO_ID:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: INTERESSE_PRODUTO_ID");
            	String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorChaveSel, args, sortOrder);
                break;
            }
            case INTERESSE_PRODUTO_ID_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: INTERESSE_PRODUTO_ID_E_COMPLEMENTO");
				String id = uri.getPathSegments().get(1);	
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri) +
						" where " + InteresseProdutoContract.COLUNA_CHAVE + " = " + id;
				retCursor = queryRaw(sql);
				break;
			}
			case INTERESSE_PRODUTO_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: INTERESSE_PRODUTO_E_COMPLEMENTO");
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case INTERESSE_PRODUTO_E_RETIRADA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: INTERESSE_PRODUTO_E_RETIRADA");
				String sql = "select " +  InteresseProdutoContract.camposOrdenados() +
						" from " + InteresseProdutoContract.TABLE_NAME +
						sqlWhere(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case INTERESSE_PRODUTO_POR_PRODUTO_CLIENTE_REFERENTEA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: INTERESSE_PRODUTO_POR_PRODUTO_CLIENTE_REFERENTEA");
	            String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorIdProdutoClienteRaSelecao, args, sortOrder);
                break;
            }
            case COM_PRODUTO_CLIENTE:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: COM_PRODUTO_CLIENTE");
            	String sql = "select " + InteresseProdutoContract.camposOrdenados() + " , " +
						ProdutoClienteContract.camposOrdenados() +
						" from " + InteresseProdutoContract.TABLE_NAME +
						InteresseProdutoContract.innerJoinProdutoCliente_ReferenteA() +
						getOrderBy();
                retCursor = queryRaw(sql);
				break;
            }
		
		
	 		case LISTAESPERA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: LISTAESPERA");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryListaEspera()");
				//retCursor = queryListaEspera(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryListaEspera(param) + (sortOrder!=null?" order by " + sortOrder:"");
				DCLog.d(DCLog.TRACE_LISTA,this,"SQL: " + sql);
				retCursor = this.queryRaw(sql);
				DCLog.d(DCLog.TRACE_LISTA,this,"Cursor: " + retCursor.getCount() + " linhas");
				break;
			}
		
	 		case LISTAMONITORA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: LISTAMONITORA");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryListaMonitora()");
				//retCursor = queryListaMonitora(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryListaMonitora(param) + (sortOrder!=null?" order by " + sortOrder:"");
				DCLog.d(DCLog.TRACE_LISTA,this,"SQL: " + sql);
				retCursor = this.queryRaw(sql);
				DCLog.d(DCLog.TRACE_LISTA,this,"Cursor: " + retCursor.getCount() + " linhas");
				break;
			}
		
	 		case OBTEMCOMPRODUTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: OBTEMCOMPRODUTO");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryObtemComProduto()");
				//retCursor = queryObtemComProduto(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryObtemComProduto(param) + (sortOrder!=null?" order by " + sortOrder:"");
				DCLog.d(DCLog.TRACE_LISTA,this,"SQL: " + sql);
				retCursor = this.queryRaw(sql);
				DCLog.d(DCLog.TRACE_LISTA,this,"Cursor: " + retCursor.getCount() + " linhas");
				break;
			}
		
	 		case QUANTIDADEMONITORADO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: QUANTIDADEMONITORADO");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryQuantidadeMonitorado()");
				//retCursor = queryQuantidadeMonitorado(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryQuantidadeMonitorado(param) + (sortOrder!=null?" order by " + sortOrder:"");
				DCLog.d(DCLog.TRACE_LISTA,this,"SQL: " + sql);
				retCursor = this.queryRaw(sql);
				DCLog.d(DCLog.TRACE_LISTA,this,"Cursor: " + retCursor.getCount() + " linhas");
				break;
			}
		
	 		case QUANTIDADEMUDANCA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: QUANTIDADEMUDANCA");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryQuantidadeMudanca()");
				//retCursor = queryQuantidadeMudanca(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryQuantidadeMudanca(param) + (sortOrder!=null?" order by " + sortOrder:"");
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
  	
		
		return sql;
	}
	
	private String sqlSelect(Uri uri) {
		String sql = InteresseProdutoContract.camposOrdenados();
		List<String> segmentos = uri.getPathSegments();
		// ComChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComProdutoClienteReferenteA",uri.getPathSegments())) {
			sql += "," +  ProdutoClienteContract.camposOrdenados();
		}
	
	
	// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		
		
		
		return sql;
	}
	private String sqlFrom(Uri uri) {
		String sql = InteresseProdutoContract.TABLE_NAME;
		List<String> segmentos = uri.getPathSegments();
		//if (existeItem("ComEpisodioReferenteA",uri.getPathSegments())) {
		//	sql += " " +  EpisodioUsuarioContract.innerJoinEpisodio_ReferenteA();
		//}
		
		
				// ComChaveEstrangeira
  	
		if (existeItem("ComProdutoClienteReferenteA",uri.getPathSegments())) {
			sql += " " +  InteresseProdutoContract.outerJoinProdutoCliente_ReferenteA();
		}
	
	
	// SemChaveEstrangeira
  	
		
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
	
	
	protected abstract String queryListaEspera(String param);
	
	protected abstract String queryListaMonitora(String param);
	
	protected abstract String queryObtemComProduto(String param);
	
	protected abstract String queryQuantidadeMonitorado(String param);
	
	protected abstract String queryQuantidadeMudanca(String param);
	
	
	
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
            case INTERESSE_PRODUTO:
            {
                return InteresseProdutoContract.getContentType();
            }
            case INTERESSE_PRODUTO_ID:
            {
            	return InteresseProdutoContract.getContentItemType();
            }
			case INTERESSE_PRODUTO_POR_PRODUTO_CLIENTE_REFERENTEA:
            {
	            return InteresseProdutoContract.getContentType();
            }
		
		}	
		return null;
	}
	
	public Uri insert(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Uri returnUri;
		long idNovo = getMaxId(db)+1;
		values.put(InteresseProdutoContract.COLUNA_CHAVE, idNovo);
		long _id = db.insert(InteresseProdutoContract.TABLE_NAME, null, values);
		if (_id > 0) {
			//DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + InteresseProdutoContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
			DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + values.toString() + " (id:" + _id + ")");
			returnUri = InteresseProdutoContract.buildInteresseProdutoUri(idNovo);
			values.put(InteresseProdutoContract.COLUNA_OPERACAO_SINC,"I");
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
			case INTERESSE_PRODUTO_DELETE_SINC: {
				String id = uri.getPathSegments().get(2);
				Cursor cursor = db.query(InteresseProdutoContract.TABLE_NAME,null,InteresseProdutoContract.COLUNA_CHAVE + " = ? ",new String[]{id},null,null,null);
				if (cursor.moveToFirst()) {
					ContentValues valores = new ContentValues();
					DatabaseUtils.cursorRowToContentValues(cursor, valores);
					linhaDelete = db.delete(InteresseProdutoContract.TABLE_NAME, InteresseProdutoContract.COLUNA_CHAVE + " = ? ", new String[]{id});
					DCLog.d(DCLog.DATABASE_CRUD,this,"delete " + InteresseProdutoContract.TABLE_NAME + "(id:" + id + ")");
					valores.put(InteresseProdutoContract.COLUNA_OPERACAO_SINC, "D");
					insereSinc(db,valores);
				}
				notificaUriRelacionadas();
				mProvider.getContext().getContentResolver().notifyChange(InteresseProdutoContract.buildAll(), null);
				break;
			}
			case INTERESSE_PRODUTO_DELETE_ALL_SINC: {
				linhaDelete = db.delete(InteresseProdutoContract.TABLE_NAME_SINC, null, null);
				break;
			}
			case INTERESSE_PRODUTO_DELETE_RECREATE: {
				linhaDelete = db.delete(InteresseProdutoContract.TABLE_NAME, null, null);
				break;
			}
		}
		return true;
	}
	
	
	public boolean update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		DCLog.d(DCLog.DATABASE_CRUD,this,"update2 " + values.toString() );
		linhaUpdate = db.update(InteresseProdutoContract.TABLE_NAME, values, selection, selectionArgs);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	public boolean update(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		String selection = InteresseProdutoContract.COLUNA_CHAVE + " = ? ";
		String dados[] = {values.get(InteresseProdutoContract.COLUNA_CHAVE).toString()};
		DCLog.d(DCLog.DATABASE_CRUD,this,"update1 " + values.toString() );
		linhaUpdate = db.update(InteresseProdutoContract.TABLE_NAME, values, selection, dados);
		values.put(InteresseProdutoContract.COLUNA_OPERACAO_SINC,"A");
		insereSinc(db,values);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	
	private void insereSinc(SQLiteDatabase db, ContentValues values) {
		db.insert(InteresseProdutoContract.TABLE_NAME_SINC, null, values);
		notificaUriOperacoes();
	}
	
	protected abstract void notificaOutrasUri(ContentResolver resolver);
	
	
	// Notificar todas as uris de entidades que possuem chave estrangeira dessa entidade.
	private void notificaUriRelacionadas() {
		// ComChaveEstrangeira
  	
		mProvider.getContext().getContentResolver().notifyChange(ProdutoClienteContract.buildAllComInteresseProdutoPossui(), null);
		mProvider.getContext().getContentResolver().notifyChange(ProdutoClienteContract.buildAllSemInteresseProdutoPossui(), null);
	
	}
	private void notificaUriOperacoes() {
	
		mProvider.getContext().getContentResolver().notifyChange(InteresseProdutoContract.buildListaEspera(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(InteresseProdutoContract.buildListaMonitora(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(InteresseProdutoContract.buildObtemComProduto(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(InteresseProdutoContract.buildQuantidadeMonitorado(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(InteresseProdutoContract.buildQuantidadeMudanca(), null);
	
	}
	private void notificaUriRaiz(){
		
	}
	
	
	
	public int bulkInsert(Uri uri, ContentValues[] values) {
    	final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        db.beginTransaction();
        int returnCount = 0;
        try {
        	for (ContentValues value : values) {
				String[] args = {(String)value.get(InteresseProdutoContract.COLUNA_CHAVE)};
				Cursor retCursor = query(null, sPorChaveSel, args, null);
				if (retCursor.moveToFirst()) {
						db.update(InteresseProdutoContract.TABLE_NAME,value,sPorChaveSel,args);
						//DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk " + InteresseProdutoContract.TABLE_NAME + "  " + values.toString());
						DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk id:" + value.get(InteresseProdutoContract.COLUNA_CHAVE) + ")" + values.toString());
				} else {
					long _id = db.insert(InteresseProdutoContract.TABLE_NAME, null, value);
					if (_id != -1) {
						//DCLog.d(DCLog.DATABASE_CRUD,this,"insert (bulk)" + InteresseProdutoContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
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
            	long _id = db.insert(InteresseProdutoContract.TABLE_NAME, null, value);
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
		String sql = "select max(" + InteresseProdutoContract.COLUNA_CHAVE + ") from " + InteresseProdutoContract.TABLE_NAME;
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