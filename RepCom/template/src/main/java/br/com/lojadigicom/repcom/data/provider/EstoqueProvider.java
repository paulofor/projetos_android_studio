
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

public abstract class EstoqueProvider {

	private int qtdeLinhas = 0;
	
	public static final int ESTOQUE = 47000;
	public static final int ESTOQUE_ID = 47001;
	public static final int ESTOQUE_SINC = 47003;
	public static final int ESTOQUE_E_COMPLEMENTO = 47004;
	public static final int ESTOQUE_ID_E_COMPLEMENTO = 47005;
	//public static final int ESTOQUE_OPERACAO = 47002;
	
	// deletes
	public static final int ESTOQUE_DELETE_ALL_SINC = 47006;
	public static final int ESTOQUE_DELETE_RECREATE = 47007;
	public static final int ESTOQUE_DELETE_SINC = 47008;
	public static final int ESTOQUE_E_RETIRADA = 47009;
	
	private static final String sPorChaveSel = EstoqueContract.TABLE_NAME + "." + EstoqueContract.COLUNA_CHAVE + " = ? ";
	
	
	


	private ContentProvider mProvider = null;


	public void setContentProvider(ContentProvider valor) {
		mProvider = valor;
	}

	protected static final SQLiteQueryBuilder sQueryBuilder;
	static {
		sQueryBuilder = new SQLiteQueryBuilder();
		String tabelas = EstoqueContract.TABLE_NAME;
		
		sQueryBuilder.setTables(tabelas);
	}
	private static final SQLiteQueryBuilder sQueryBuilderSinc;
	static {
		sQueryBuilderSinc = new SQLiteQueryBuilder();
		String tabelas = EstoqueContract.TABLE_NAME_SINC;
		sQueryBuilderSinc.setTables(tabelas);
	}
	
	
	protected AplicacaoDbHelper mOpenHelper = null;
	
	public int getLinhas() {
		return qtdeLinhas;
	}
	
	public static void buildUriMatcher(UriMatcher matcher) {
		matcher.addURI(EstoqueContract.getContentAuthority(), EstoqueContract.PATH, ESTOQUE);
		matcher.addURI(EstoqueContract.getContentAuthority(), EstoqueContract.PATH + "/Sinc" , ESTOQUE_SINC);
		matcher.addURI(EstoqueContract.getContentAuthority(), EstoqueContract.PATH + "/#"    , ESTOQUE_ID);
	
		matcher.addURI(EstoqueContract.getContentAuthority(), EstoqueContract.PATH + "/#/" + EstoqueContract.COM_COMPLEMENTO + "/*" , ESTOQUE_ID_E_COMPLEMENTO);
		matcher.addURI(EstoqueContract.getContentAuthority(), EstoqueContract.PATH + "/" + EstoqueContract.COM_COMPLEMENTO + "/*" , ESTOQUE_E_COMPLEMENTO);
		matcher.addURI(EstoqueContract.getContentAuthority(), EstoqueContract.PATH + "/" + EstoqueContract.COM_RETIRADA + "/*" , ESTOQUE_E_RETIRADA);
		
		
		//matcher.addURI(AplicacaoContract.CONTENT_AUTHORITY, EstoqueContract.PATH + "/operacao/*" , ESTOQUE_OPERACAO);
		
		
		
		
		// Deletes
		matcher.addURI(EstoqueContract.getContentAuthority(), EstoqueContract.PATH + "/DeleteAllSinc" , 	ESTOQUE_DELETE_ALL_SINC);
		matcher.addURI(EstoqueContract.getContentAuthority(), EstoqueContract.PATH + "/DeleteAllRecreate" , ESTOQUE_DELETE_RECREATE);
		matcher.addURI(EstoqueContract.getContentAuthority(), EstoqueContract.PATH + "/DeleteSinc/#" , 		ESTOQUE_DELETE_SINC);
	}
	
	
	
	
	public void setAplicacaoDbHelper(AplicacaoDbHelper db) {
		mOpenHelper = db;
	}
	
	public Cursor query(UriMatcher sUriMatcher, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Query Uri:" + uri.toString());
		Cursor retCursor = null;
		switch (sUriMatcher.match(uri)) {
            case ESTOQUE:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: ESTOQUE");
                retCursor = query(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case ESTOQUE_SINC:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: ESTOQUE_SINC");
                retCursor = querySinc(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case ESTOQUE_ID:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: ESTOQUE_ID");
            	String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorChaveSel, args, sortOrder);
                break;
            }
            case ESTOQUE_ID_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: ESTOQUE_ID_E_COMPLEMENTO");
				String id = uri.getPathSegments().get(1);	
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri) +
						" where " + EstoqueContract.COLUNA_CHAVE + " = " + id;
				retCursor = queryRaw(sql);
				break;
			}
			case ESTOQUE_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: ESTOQUE_E_COMPLEMENTO");
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case ESTOQUE_E_RETIRADA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: ESTOQUE_E_RETIRADA");
				String sql = "select " +  EstoqueContract.camposOrdenados() +
						" from " + EstoqueContract.TABLE_NAME +
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
		String sql = EstoqueContract.camposOrdenados();
		List<String> segmentos = uri.getPathSegments();
		// ComChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
	
	// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		
		
		
		return sql;
	}
	private String sqlFrom(Uri uri) {
		String sql = EstoqueContract.TABLE_NAME;
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
            case ESTOQUE:
            {
                return EstoqueContract.getContentType();
            }
            case ESTOQUE_ID:
            {
            	return EstoqueContract.getContentItemType();
            }
		}	
		return null;
	}
	
	public Uri insert(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Uri returnUri;
		long idNovo = getMaxId(db)+1;
		values.put(EstoqueContract.COLUNA_CHAVE, idNovo);
		long _id = db.insert(EstoqueContract.TABLE_NAME, null, values);
		if (_id > 0) {
			//DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + EstoqueContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
			DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + values.toString() + " (id:" + _id + ")");
			returnUri = EstoqueContract.buildEstoqueUri(idNovo);
			values.put(EstoqueContract.COLUNA_OPERACAO_SINC,"I");
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
			case ESTOQUE_DELETE_SINC: {
				String id = uri.getPathSegments().get(2);
				Cursor cursor = db.query(EstoqueContract.TABLE_NAME,null,EstoqueContract.COLUNA_CHAVE + " = ? ",new String[]{id},null,null,null);
				if (cursor.moveToFirst()) {
					ContentValues valores = new ContentValues();
					DatabaseUtils.cursorRowToContentValues(cursor, valores);
					linhaDelete = db.delete(EstoqueContract.TABLE_NAME, EstoqueContract.COLUNA_CHAVE + " = ? ", new String[]{id});
					DCLog.d(DCLog.DATABASE_CRUD,this,"delete " + EstoqueContract.TABLE_NAME + "(id:" + id + ")");
					valores.put(EstoqueContract.COLUNA_OPERACAO_SINC, "D");
					insereSinc(db,valores);
				}
				notificaUriRelacionadas();
				mProvider.getContext().getContentResolver().notifyChange(EstoqueContract.buildAll(), null);
				break;
			}
			case ESTOQUE_DELETE_ALL_SINC: {
				linhaDelete = db.delete(EstoqueContract.TABLE_NAME_SINC, null, null);
				break;
			}
			case ESTOQUE_DELETE_RECREATE: {
				linhaDelete = db.delete(EstoqueContract.TABLE_NAME, null, null);
				break;
			}
		}
		return true;
	}
	
	
	public boolean update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		linhaUpdate = db.update(EstoqueContract.TABLE_NAME, values, selection, selectionArgs);
		return true;
	}
	public boolean update(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		String selection = EstoqueContract.COLUNA_CHAVE + " = ? ";
		String dados[] = {values.get(EstoqueContract.COLUNA_CHAVE).toString()};
		linhaUpdate = db.update(EstoqueContract.TABLE_NAME, values, selection, dados);
		values.put(EstoqueContract.COLUNA_OPERACAO_SINC,"A");
		insereSinc(db,values);
		return true;
	}
	
	private void insereSinc(SQLiteDatabase db, ContentValues values) {
		db.insert(EstoqueContract.TABLE_NAME_SINC, null, values);
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
				String[] args = {(String)value.get(EstoqueContract.COLUNA_CHAVE)};
				Cursor retCursor = query(null, sPorChaveSel, args, null);
				if (retCursor.moveToFirst()) {
						db.update(EstoqueContract.TABLE_NAME,value,sPorChaveSel,args);
						//DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk " + EstoqueContract.TABLE_NAME + "  " + values.toString());
						DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk id:" + value.get(EstoqueContract.COLUNA_CHAVE) + ")" + values.toString());
				} else {
					long _id = db.insert(EstoqueContract.TABLE_NAME, null, value);
					if (_id != -1) {
						//DCLog.d(DCLog.DATABASE_CRUD,this,"insert (bulk)" + EstoqueContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
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
            	long _id = db.insert(EstoqueContract.TABLE_NAME, null, value);
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
		String sql = "select max(" + EstoqueContract.COLUNA_CHAVE + ") from " + EstoqueContract.TABLE_NAME;
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