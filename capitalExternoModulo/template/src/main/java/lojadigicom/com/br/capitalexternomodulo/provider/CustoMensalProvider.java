
package lojadigicom.com.br.capitalexternomodulo.provider;


import br.com.lojadigicom.capitalexterno.data.contract.*;
import br.com.lojadigicom.capitalexterno.data.helper.AplicacaoDbHelper;
import android.content.ContentValues;
import android.content.ContentResolver;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.database.SQLException;
import android.database.DatabaseUtils;
import android.net.Uri;
import br.com.lojadigicom.capitalexterno.framework.log.DCLog;
import java.util.List;
import android.content.ContentProvider;

public abstract class CustoMensalProvider {

	private int qtdeLinhas = 0;
	
	public static final int CUSTO_MENSAL = 89500;
	public static final int CUSTO_MENSAL_ID = 89501;
	public static final int CUSTO_MENSAL_SINC = 89503;
	public static final int CUSTO_MENSAL_E_COMPLEMENTO = 89504;
	public static final int CUSTO_MENSAL_ID_E_COMPLEMENTO = 89505;
	//public static final int CUSTO_MENSAL_OPERACAO = 89502;
	
	// deletes
	public static final int CUSTO_MENSAL_DELETE_ALL_SINC = 89506;
	public static final int CUSTO_MENSAL_DELETE_RECREATE = 89507;
	public static final int CUSTO_MENSAL_DELETE_SINC = 89508;
	public static final int CUSTO_MENSAL_E_RETIRADA = 89509;
	
	private static final String sPorChaveSel = CustoMensalContract.TABLE_NAME + "." + CustoMensalContract.COLUNA_CHAVE + " = ? ";
	
	
	


	private ContentProvider mProvider = null;


	public void setContentProvider(ContentProvider valor) {
		mProvider = valor;
	}

	protected static final SQLiteQueryBuilder sQueryBuilder;
	static {
		sQueryBuilder = new SQLiteQueryBuilder();
		String tabelas = CustoMensalContract.TABLE_NAME;
		
		sQueryBuilder.setTables(tabelas);
	}
	private static final SQLiteQueryBuilder sQueryBuilderSinc;
	static {
		sQueryBuilderSinc = new SQLiteQueryBuilder();
		String tabelas = CustoMensalContract.TABLE_NAME_SINC;
		sQueryBuilderSinc.setTables(tabelas);
	}
	
	
	protected AplicacaoDbHelper mOpenHelper = null;
	
	public int getLinhas() {
		return qtdeLinhas;
	}
	
	public static void buildUriMatcher(UriMatcher matcher) {
		matcher.addURI(CustoMensalContract.getContentAuthority(), CustoMensalContract.PATH, CUSTO_MENSAL);
		matcher.addURI(CustoMensalContract.getContentAuthority(), CustoMensalContract.PATH + "/Sinc" , CUSTO_MENSAL_SINC);
		matcher.addURI(CustoMensalContract.getContentAuthority(), CustoMensalContract.PATH + "/#"    , CUSTO_MENSAL_ID);
	
		matcher.addURI(CustoMensalContract.getContentAuthority(), CustoMensalContract.PATH + "/#/" + CustoMensalContract.COM_COMPLEMENTO + "/*" , CUSTO_MENSAL_ID_E_COMPLEMENTO);
		matcher.addURI(CustoMensalContract.getContentAuthority(), CustoMensalContract.PATH + "/" + CustoMensalContract.COM_COMPLEMENTO + "/*" , CUSTO_MENSAL_E_COMPLEMENTO);
		matcher.addURI(CustoMensalContract.getContentAuthority(), CustoMensalContract.PATH + "/" + CustoMensalContract.COM_RETIRADA + "/*" , CUSTO_MENSAL_E_RETIRADA);
		
		
		//matcher.addURI(AplicacaoContract.CONTENT_AUTHORITY, CustoMensalContract.PATH + "/operacao/*" , CUSTO_MENSAL_OPERACAO);
		
		
		
		
		// Deletes
		matcher.addURI(CustoMensalContract.getContentAuthority(), CustoMensalContract.PATH + "/DeleteAllSinc" , 	CUSTO_MENSAL_DELETE_ALL_SINC);
		matcher.addURI(CustoMensalContract.getContentAuthority(), CustoMensalContract.PATH + "/DeleteAllRecreate" , CUSTO_MENSAL_DELETE_RECREATE);
		matcher.addURI(CustoMensalContract.getContentAuthority(), CustoMensalContract.PATH + "/DeleteSinc/#" , 		CUSTO_MENSAL_DELETE_SINC);
	}
	
	
	
	
	public void setAplicacaoDbHelper(AplicacaoDbHelper db) {
		mOpenHelper = db;
	}
	
	public Cursor query(UriMatcher sUriMatcher, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Query Uri:" + uri.toString());
		Cursor retCursor = null;
		switch (sUriMatcher.match(uri)) {
            case CUSTO_MENSAL:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CUSTO_MENSAL");
                retCursor = query(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case CUSTO_MENSAL_SINC:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CUSTO_MENSAL_SINC");
                retCursor = querySinc(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case CUSTO_MENSAL_ID:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CUSTO_MENSAL_ID");
            	String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorChaveSel, args, sortOrder);
                break;
            }
            case CUSTO_MENSAL_ID_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CUSTO_MENSAL_ID_E_COMPLEMENTO");
				String id = uri.getPathSegments().get(1);	
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri) +
						" where " + CustoMensalContract.COLUNA_CHAVE + " = " + id;
				retCursor = queryRaw(sql);
				break;
			}
			case CUSTO_MENSAL_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CUSTO_MENSAL_E_COMPLEMENTO");
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case CUSTO_MENSAL_E_RETIRADA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CUSTO_MENSAL_E_RETIRADA");
				String sql = "select " +  CustoMensalContract.camposOrdenados() +
						" from " + CustoMensalContract.TABLE_NAME +
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
		String sql = CustoMensalContract.camposOrdenados();
		List<String> segmentos = uri.getPathSegments();
		// ComChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
	
	// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		
		
		
		return sql;
	}
	private String sqlFrom(Uri uri) {
		String sql = CustoMensalContract.TABLE_NAME;
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
		//DCLog.d(DCLog.DATABASE_ADM,this,sql);
		DCLog.d(DCLog.QUERY_SQL,this,sql);
		final SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		return db.rawQuery(sql,null);
	}
	
	public String getType(UriMatcher sUriMatcher, Uri uri) {
		switch (sUriMatcher.match(uri)) {
            case CUSTO_MENSAL:
            {
                return CustoMensalContract.getContentType();
            }
            case CUSTO_MENSAL_ID:
            {
            	return CustoMensalContract.getContentItemType();
            }
		}	
		return null;
	}
	
	public Uri insert(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Uri returnUri;
		long idNovo = getMaxId(db)+1;
		values.put(CustoMensalContract.COLUNA_CHAVE, idNovo);
		long _id = db.insert(CustoMensalContract.TABLE_NAME, null, values);
		if (_id > 0) {
			//DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + CustoMensalContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
			DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + values.toString() + " (id:" + _id + ")");
			returnUri = CustoMensalContract.buildCustoMensalUri(idNovo);
			values.put(CustoMensalContract.COLUNA_OPERACAO_SINC,"I");
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
			case CUSTO_MENSAL_DELETE_SINC: {
				String id = uri.getPathSegments().get(2);
				Cursor cursor = db.query(CustoMensalContract.TABLE_NAME,null,CustoMensalContract.COLUNA_CHAVE + " = ? ",new String[]{id},null,null,null);
				if (cursor.moveToFirst()) {
					ContentValues valores = new ContentValues();
					DatabaseUtils.cursorRowToContentValues(cursor, valores);
					linhaDelete = db.delete(CustoMensalContract.TABLE_NAME, CustoMensalContract.COLUNA_CHAVE + " = ? ", new String[]{id});
					DCLog.d(DCLog.DATABASE_CRUD,this,"delete " + CustoMensalContract.TABLE_NAME + "(id:" + id + ")");
					valores.put(CustoMensalContract.COLUNA_OPERACAO_SINC, "D");
					insereSinc(db,valores);
				}
				notificaUriRelacionadas();
				mProvider.getContext().getContentResolver().notifyChange(CustoMensalContract.buildAll(), null);
				break;
			}
			case CUSTO_MENSAL_DELETE_ALL_SINC: {
				linhaDelete = db.delete(CustoMensalContract.TABLE_NAME_SINC, null, null);
				break;
			}
			case CUSTO_MENSAL_DELETE_RECREATE: {
				linhaDelete = db.delete(CustoMensalContract.TABLE_NAME, null, null);
				break;
			}
		}
		return true;
	}
	
	
	public boolean update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		DCLog.d(DCLog.DATABASE_CRUD,this,"update2 " + values.toString() );
		linhaUpdate = db.update(CustoMensalContract.TABLE_NAME, values, selection, selectionArgs);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	public boolean update(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		String selection = CustoMensalContract.COLUNA_CHAVE + " = ? ";
		String dados[] = {values.get(CustoMensalContract.COLUNA_CHAVE).toString()};
		DCLog.d(DCLog.DATABASE_CRUD,this,"update1 " + values.toString() );
		linhaUpdate = db.update(CustoMensalContract.TABLE_NAME, values, selection, dados);
		values.put(CustoMensalContract.COLUNA_OPERACAO_SINC,"A");
		insereSinc(db,values);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	
	private void insereSinc(SQLiteDatabase db, ContentValues values) {
		db.insert(CustoMensalContract.TABLE_NAME_SINC, null, values);
	}
	
	protected abstract void notificaOutrasUri(ContentResolver resolver);
	
	
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
				String[] args = {(String)value.get(CustoMensalContract.COLUNA_CHAVE)};
				Cursor retCursor = query(null, sPorChaveSel, args, null);
				if (retCursor.moveToFirst()) {
						db.update(CustoMensalContract.TABLE_NAME,value,sPorChaveSel,args);
						//DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk " + CustoMensalContract.TABLE_NAME + "  " + values.toString());
						DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk id:" + value.get(CustoMensalContract.COLUNA_CHAVE) + ")" + values.toString());
				} else {
					long _id = db.insert(CustoMensalContract.TABLE_NAME, null, value);
					if (_id != -1) {
						//DCLog.d(DCLog.DATABASE_CRUD,this,"insert (bulk)" + CustoMensalContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
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
            	long _id = db.insert(CustoMensalContract.TABLE_NAME, null, value);
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
		String sql = "select max(" + CustoMensalContract.COLUNA_CHAVE + ") from " + CustoMensalContract.TABLE_NAME;
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