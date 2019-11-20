
package  br.com.lojadigicom.repcom.data.provider;


import br.com.lojadigicom.repcom.data.contract.*;
import br.com.lojadigicom.repcom.data.helper.AplicacaoDbHelper;
import android.content.ContentValues;
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

public abstract class ErroExceptionProvider {

	private int qtdeLinhas = 0;
	
	public static final int ERRO_EXCEPTION = 85400;
	public static final int ERRO_EXCEPTION_ID = 85401;
	public static final int ERRO_EXCEPTION_SINC = 85403;
	public static final int ERRO_EXCEPTION_E_COMPLEMENTO = 85404;
	public static final int ERRO_EXCEPTION_ID_E_COMPLEMENTO = 85405;
	//public static final int ERRO_EXCEPTION_OPERACAO = 85402;
	
	// deletes
	public static final int ERRO_EXCEPTION_DELETE_ALL_SINC = 85406;
	public static final int ERRO_EXCEPTION_DELETE_RECREATE = 85407;
	public static final int ERRO_EXCEPTION_DELETE_SINC = 85408;
	public static final int ERRO_EXCEPTION_E_RETIRADA = 85409;
	
	private static final String sPorChaveSel = ErroExceptionContract.TABLE_NAME + "." + ErroExceptionContract.COLUNA_CHAVE + " = ? ";
	
	
	


	private ContentProvider mProvider = null;


	public void setContentProvider(ContentProvider valor) {
		mProvider = valor;
	}

	protected static final SQLiteQueryBuilder sQueryBuilder;
	static {
		sQueryBuilder = new SQLiteQueryBuilder();
		String tabelas = ErroExceptionContract.TABLE_NAME;
		
		sQueryBuilder.setTables(tabelas);
	}
	private static final SQLiteQueryBuilder sQueryBuilderSinc;
	static {
		sQueryBuilderSinc = new SQLiteQueryBuilder();
		String tabelas = ErroExceptionContract.TABLE_NAME_SINC;
		sQueryBuilderSinc.setTables(tabelas);
	}
	
	
	protected AplicacaoDbHelper mOpenHelper = null;
	
	public int getLinhas() {
		return qtdeLinhas;
	}
	
	public static void buildUriMatcher(UriMatcher matcher) {
		matcher.addURI(ErroExceptionContract.getContentAuthority(), ErroExceptionContract.PATH, ERRO_EXCEPTION);
		matcher.addURI(ErroExceptionContract.getContentAuthority(), ErroExceptionContract.PATH + "/Sinc" , ERRO_EXCEPTION_SINC);
		matcher.addURI(ErroExceptionContract.getContentAuthority(), ErroExceptionContract.PATH + "/#"    , ERRO_EXCEPTION_ID);
	
		matcher.addURI(ErroExceptionContract.getContentAuthority(), ErroExceptionContract.PATH + "/#/" + ErroExceptionContract.COM_COMPLEMENTO + "/*" , ERRO_EXCEPTION_ID_E_COMPLEMENTO);
		matcher.addURI(ErroExceptionContract.getContentAuthority(), ErroExceptionContract.PATH + "/" + ErroExceptionContract.COM_COMPLEMENTO + "/*" , ERRO_EXCEPTION_E_COMPLEMENTO);
		matcher.addURI(ErroExceptionContract.getContentAuthority(), ErroExceptionContract.PATH + "/" + ErroExceptionContract.COM_RETIRADA + "/*" , ERRO_EXCEPTION_E_RETIRADA);
		
		
		//matcher.addURI(AplicacaoContract.CONTENT_AUTHORITY, ErroExceptionContract.PATH + "/operacao/*" , ERRO_EXCEPTION_OPERACAO);
		
		
		
		
		// Deletes
		matcher.addURI(ErroExceptionContract.getContentAuthority(), ErroExceptionContract.PATH + "/DeleteAllSinc" , 	ERRO_EXCEPTION_DELETE_ALL_SINC);
		matcher.addURI(ErroExceptionContract.getContentAuthority(), ErroExceptionContract.PATH + "/DeleteAllRecreate" , ERRO_EXCEPTION_DELETE_RECREATE);
		matcher.addURI(ErroExceptionContract.getContentAuthority(), ErroExceptionContract.PATH + "/DeleteSinc/#" , 		ERRO_EXCEPTION_DELETE_SINC);
	}
	
	
	
	
	public void setAplicacaoDbHelper(AplicacaoDbHelper db) {
		mOpenHelper = db;
	}
	
	public Cursor query(UriMatcher sUriMatcher, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Query Uri:" + uri.toString());
		Cursor retCursor = null;
		switch (sUriMatcher.match(uri)) {
            case ERRO_EXCEPTION:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: ERRO_EXCEPTION");
                retCursor = query(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case ERRO_EXCEPTION_SINC:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: ERRO_EXCEPTION_SINC");
                retCursor = querySinc(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case ERRO_EXCEPTION_ID:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: ERRO_EXCEPTION_ID");
            	String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorChaveSel, args, sortOrder);
                break;
            }
            case ERRO_EXCEPTION_ID_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: ERRO_EXCEPTION_ID_E_COMPLEMENTO");
				String id = uri.getPathSegments().get(1);	
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri) +
						" where " + ErroExceptionContract.COLUNA_CHAVE + " = " + id;
				retCursor = queryRaw(sql);
				break;
			}
			case ERRO_EXCEPTION_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: ERRO_EXCEPTION_E_COMPLEMENTO");
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case ERRO_EXCEPTION_E_RETIRADA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: ERRO_EXCEPTION_E_RETIRADA");
				String sql = "select " +  ErroExceptionContract.camposOrdenados() +
						" from " + ErroExceptionContract.TABLE_NAME +
						sqlWhere(uri);
						// colocar 
				retCursor = queryRaw(sql);
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
		String sql = ErroExceptionContract.camposOrdenados();
		List<String> segmentos = uri.getPathSegments();
		// ComChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
	
	// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		
		
		
		return sql;
	}
	private String sqlFrom(Uri uri) {
		String sql = ErroExceptionContract.TABLE_NAME;
		List<String> segmentos = uri.getPathSegments();
		//if (existeItem("ComEpisodioReferenteA",uri.getPathSegments())) {
		//	sql += " " +  EpisodioUsuarioContract.innerJoinEpisodio_ReferenteA();
		//}
		
		
				// ComChaveEstrangeira
  	
	
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
		DCLog.d(DCLog.DATABASE_ADM,this,sql);
		final SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		return db.rawQuery(sql,null);
	}
	
	public String getType(UriMatcher sUriMatcher, Uri uri) {
		switch (sUriMatcher.match(uri)) {
            case ERRO_EXCEPTION:
            {
                return ErroExceptionContract.getContentType();
            }
            case ERRO_EXCEPTION_ID:
            {
            	return ErroExceptionContract.getContentItemType();
            }
		}	
		return null;
	}
	
	public Uri insert(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Uri returnUri;
		long idNovo = getMaxId(db)+1;
		values.put(ErroExceptionContract.COLUNA_CHAVE, idNovo);
		long _id = db.insert(ErroExceptionContract.TABLE_NAME, null, values);
		if (_id > 0) {
			//DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + ErroExceptionContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
			DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + values.toString() + " (id:" + _id + ")");
			returnUri = ErroExceptionContract.buildErroExceptionUri(idNovo);
			values.put(ErroExceptionContract.COLUNA_OPERACAO_SINC,"I");
			insereSinc(db,values);
			notificaUriRelacionadas();
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
			case ERRO_EXCEPTION_DELETE_SINC: {
				String id = uri.getPathSegments().get(2);
				Cursor cursor = db.query(ErroExceptionContract.TABLE_NAME,null,ErroExceptionContract.COLUNA_CHAVE + " = ? ",new String[]{id},null,null,null);
				if (cursor.moveToFirst()) {
					ContentValues valores = new ContentValues();
					DatabaseUtils.cursorRowToContentValues(cursor, valores);
					linhaDelete = db.delete(ErroExceptionContract.TABLE_NAME, ErroExceptionContract.COLUNA_CHAVE + " = ? ", new String[]{id});
					DCLog.d(DCLog.DATABASE_CRUD,this,"delete " + ErroExceptionContract.TABLE_NAME + "(id:" + id + ")");
					valores.put(ErroExceptionContract.COLUNA_OPERACAO_SINC, "D");
					insereSinc(db,valores);
				}
				notificaUriRelacionadas();
				mProvider.getContext().getContentResolver().notifyChange(ErroExceptionContract.buildAll(), null);
				break;
			}
			case ERRO_EXCEPTION_DELETE_ALL_SINC: {
				linhaDelete = db.delete(ErroExceptionContract.TABLE_NAME_SINC, null, null);
				break;
			}
			case ERRO_EXCEPTION_DELETE_RECREATE: {
				linhaDelete = db.delete(ErroExceptionContract.TABLE_NAME, null, null);
				break;
			}
		}
		return true;
	}
	
	
	public boolean update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		linhaUpdate = db.update(ErroExceptionContract.TABLE_NAME, values, selection, selectionArgs);
		return true;
	}
	public boolean update(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		String selection = ErroExceptionContract.COLUNA_CHAVE + " = ? ";
		String dados[] = {values.get(ErroExceptionContract.COLUNA_CHAVE).toString()};
		linhaUpdate = db.update(ErroExceptionContract.TABLE_NAME, values, selection, dados);
		values.put(ErroExceptionContract.COLUNA_OPERACAO_SINC,"A");
		insereSinc(db,values);
		return true;
	}
	
	private void insereSinc(SQLiteDatabase db, ContentValues values) {
		db.insert(ErroExceptionContract.TABLE_NAME_SINC, null, values);
	}
	
	// Notificar todas as uris de entidades que possuem chave estrangeira dessa entidade.
	private void notificaUriRelacionadas() {
		// ComChaveEstrangeira
  	
	}
	private void notificaUriOperacoes() {
	
	}
	private void notificaUriRaiz(){
		
	}
	
	
	
	public int bulkInsert(Uri uri, ContentValues[] values) {
    	final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        db.beginTransaction();
        int returnCount = 0;
        try {
        	for (ContentValues value : values) {
				String[] args = {(String)value.get(ErroExceptionContract.COLUNA_CHAVE)};
				Cursor retCursor = query(null, sPorChaveSel, args, null);
				if (retCursor.moveToFirst()) {
						db.update(ErroExceptionContract.TABLE_NAME,value,sPorChaveSel,args);
						//DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk " + ErroExceptionContract.TABLE_NAME + "  " + values.toString());
						DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk id:" + value.get(ErroExceptionContract.COLUNA_CHAVE) + ")" + values.toString());
				} else {
					long _id = db.insert(ErroExceptionContract.TABLE_NAME, null, value);
					if (_id != -1) {
						//DCLog.d(DCLog.DATABASE_CRUD,this,"insert (bulk)" + ErroExceptionContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
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
            	long _id = db.insert(ErroExceptionContract.TABLE_NAME, null, value);
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
		String sql = "select max(" + ErroExceptionContract.COLUNA_CHAVE + ") from " + ErroExceptionContract.TABLE_NAME;
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