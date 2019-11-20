
package  br.com.lojadigicom.capitalexterno.data.provider;


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

public abstract class NegocioProvider {

	private int qtdeLinhas = 0;
	
	public static final int NEGOCIO = 90600;
	public static final int NEGOCIO_ID = 90601;
	public static final int NEGOCIO_SINC = 90603;
	public static final int NEGOCIO_E_COMPLEMENTO = 90604;
	public static final int NEGOCIO_ID_E_COMPLEMENTO = 90605;
	//public static final int NEGOCIO_OPERACAO = 90602;
	
	// deletes
	public static final int NEGOCIO_DELETE_ALL_SINC = 90606;
	public static final int NEGOCIO_DELETE_RECREATE = 90607;
	public static final int NEGOCIO_DELETE_SINC = 90608;
	public static final int NEGOCIO_E_RETIRADA = 90609;
	
	private static final String sPorChaveSel = NegocioContract.TABLE_NAME + "." + NegocioContract.COLUNA_CHAVE + " = ? ";
	
	
	


	private ContentProvider mProvider = null;


	public void setContentProvider(ContentProvider valor) {
		mProvider = valor;
	}

	protected static final SQLiteQueryBuilder sQueryBuilder;
	static {
		sQueryBuilder = new SQLiteQueryBuilder();
		String tabelas = NegocioContract.TABLE_NAME;
		
		sQueryBuilder.setTables(tabelas);
	}
	private static final SQLiteQueryBuilder sQueryBuilderSinc;
	static {
		sQueryBuilderSinc = new SQLiteQueryBuilder();
		String tabelas = NegocioContract.TABLE_NAME_SINC;
		sQueryBuilderSinc.setTables(tabelas);
	}
	
	
	protected AplicacaoDbHelper mOpenHelper = null;
	
	public int getLinhas() {
		return qtdeLinhas;
	}
	
	public static void buildUriMatcher(UriMatcher matcher) {
		matcher.addURI(NegocioContract.getContentAuthority(), NegocioContract.PATH, NEGOCIO);
		matcher.addURI(NegocioContract.getContentAuthority(), NegocioContract.PATH + "/Sinc" , NEGOCIO_SINC);
		matcher.addURI(NegocioContract.getContentAuthority(), NegocioContract.PATH + "/#"    , NEGOCIO_ID);
	
		matcher.addURI(NegocioContract.getContentAuthority(), NegocioContract.PATH + "/#/" + NegocioContract.COM_COMPLEMENTO + "/*" , NEGOCIO_ID_E_COMPLEMENTO);
		matcher.addURI(NegocioContract.getContentAuthority(), NegocioContract.PATH + "/" + NegocioContract.COM_COMPLEMENTO + "/*" , NEGOCIO_E_COMPLEMENTO);
		matcher.addURI(NegocioContract.getContentAuthority(), NegocioContract.PATH + "/" + NegocioContract.COM_RETIRADA + "/*" , NEGOCIO_E_RETIRADA);
		
		
		//matcher.addURI(AplicacaoContract.CONTENT_AUTHORITY, NegocioContract.PATH + "/operacao/*" , NEGOCIO_OPERACAO);
		
		
		
		
		// Deletes
		matcher.addURI(NegocioContract.getContentAuthority(), NegocioContract.PATH + "/DeleteAllSinc" , 	NEGOCIO_DELETE_ALL_SINC);
		matcher.addURI(NegocioContract.getContentAuthority(), NegocioContract.PATH + "/DeleteAllRecreate" , NEGOCIO_DELETE_RECREATE);
		matcher.addURI(NegocioContract.getContentAuthority(), NegocioContract.PATH + "/DeleteSinc/#" , 		NEGOCIO_DELETE_SINC);
	}
	
	
	
	
	public void setAplicacaoDbHelper(AplicacaoDbHelper db) {
		mOpenHelper = db;
	}
	
	public Cursor query(UriMatcher sUriMatcher, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Query Uri:" + uri.toString());
		Cursor retCursor = null;
		switch (sUriMatcher.match(uri)) {
            case NEGOCIO:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: NEGOCIO");
                retCursor = query(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case NEGOCIO_SINC:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: NEGOCIO_SINC");
                retCursor = querySinc(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case NEGOCIO_ID:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: NEGOCIO_ID");
            	String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorChaveSel, args, sortOrder);
                break;
            }
            case NEGOCIO_ID_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: NEGOCIO_ID_E_COMPLEMENTO");
				String id = uri.getPathSegments().get(1);	
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri) +
						" where " + NegocioContract.COLUNA_CHAVE + " = " + id;
				retCursor = queryRaw(sql);
				break;
			}
			case NEGOCIO_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: NEGOCIO_E_COMPLEMENTO");
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case NEGOCIO_E_RETIRADA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: NEGOCIO_E_RETIRADA");
				String sql = "select " +  NegocioContract.camposOrdenados() +
						" from " + NegocioContract.TABLE_NAME +
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
  	
		if (existeItem("SemCaracteristicaMercadoPossui",uri.getPathSegments())) {
			sql += NegocioContract.COLUNA_CHAVE + " not in (select " + 
					CaracteristicaMercadoContract.COLUNA_ID_NEGOCIO_PA + " from " +
					CaracteristicaMercadoContract.TABLE_NAME + ")";
		}
	
		
		return sql;
	}
	
	private String sqlSelect(Uri uri) {
		String sql = NegocioContract.camposOrdenados();
		List<String> segmentos = uri.getPathSegments();
		// ComChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
	
	// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComCaracteristicaMercadoPossui",uri.getPathSegments())) {
			sql += "," +  CaracteristicaMercadoContract.camposOrdenados();
		}
	
		
		
		
		return sql;
	}
	private String sqlFrom(Uri uri) {
		String sql = NegocioContract.TABLE_NAME;
		List<String> segmentos = uri.getPathSegments();
		//if (existeItem("ComEpisodioReferenteA",uri.getPathSegments())) {
		//	sql += " " +  EpisodioUsuarioContract.innerJoinEpisodio_ReferenteA();
		//}
		
		
				// ComChaveEstrangeira
  	
	
	// SemChaveEstrangeira
  	
		if (existeItem("ComCaracteristicaMercadoPossui",uri.getPathSegments())) {
			sql += " " +  NegocioContract.outerJoinCaracteristicaMercado_Possui();
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
            case NEGOCIO:
            {
                return NegocioContract.getContentType();
            }
            case NEGOCIO_ID:
            {
            	return NegocioContract.getContentItemType();
            }
		}	
		return null;
	}
	
	public Uri insert(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Uri returnUri;
		long idNovo = getMaxId(db)+1;
		values.put(NegocioContract.COLUNA_CHAVE, idNovo);
		long _id = db.insert(NegocioContract.TABLE_NAME, null, values);
		if (_id > 0) {
			//DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + NegocioContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
			DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + values.toString() + " (id:" + _id + ")");
			returnUri = NegocioContract.buildNegocioUri(idNovo);
			values.put(NegocioContract.COLUNA_OPERACAO_SINC,"I");
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
			case NEGOCIO_DELETE_SINC: {
				String id = uri.getPathSegments().get(2);
				Cursor cursor = db.query(NegocioContract.TABLE_NAME,null,NegocioContract.COLUNA_CHAVE + " = ? ",new String[]{id},null,null,null);
				if (cursor.moveToFirst()) {
					ContentValues valores = new ContentValues();
					DatabaseUtils.cursorRowToContentValues(cursor, valores);
					linhaDelete = db.delete(NegocioContract.TABLE_NAME, NegocioContract.COLUNA_CHAVE + " = ? ", new String[]{id});
					DCLog.d(DCLog.DATABASE_CRUD,this,"delete " + NegocioContract.TABLE_NAME + "(id:" + id + ")");
					valores.put(NegocioContract.COLUNA_OPERACAO_SINC, "D");
					insereSinc(db,valores);
				}
				notificaUriRelacionadas();
				mProvider.getContext().getContentResolver().notifyChange(NegocioContract.buildAll(), null);
				break;
			}
			case NEGOCIO_DELETE_ALL_SINC: {
				linhaDelete = db.delete(NegocioContract.TABLE_NAME_SINC, null, null);
				break;
			}
			case NEGOCIO_DELETE_RECREATE: {
				linhaDelete = db.delete(NegocioContract.TABLE_NAME, null, null);
				break;
			}
		}
		return true;
	}
	
	
	public boolean update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		DCLog.d(DCLog.DATABASE_CRUD,this,"update2 " + values.toString() );
		linhaUpdate = db.update(NegocioContract.TABLE_NAME, values, selection, selectionArgs);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	public boolean update(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		String selection = NegocioContract.COLUNA_CHAVE + " = ? ";
		String dados[] = {values.get(NegocioContract.COLUNA_CHAVE).toString()};
		DCLog.d(DCLog.DATABASE_CRUD,this,"update1 " + values.toString() );
		linhaUpdate = db.update(NegocioContract.TABLE_NAME, values, selection, dados);
		values.put(NegocioContract.COLUNA_OPERACAO_SINC,"A");
		insereSinc(db,values);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	
	private void insereSinc(SQLiteDatabase db, ContentValues values) {
		db.insert(NegocioContract.TABLE_NAME_SINC, null, values);
		notificaUriOperacoes();
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
				String[] args = {(String)value.get(NegocioContract.COLUNA_CHAVE)};
				Cursor retCursor = query(null, sPorChaveSel, args, null);
				if (retCursor.moveToFirst()) {
						db.update(NegocioContract.TABLE_NAME,value,sPorChaveSel,args);
						//DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk " + NegocioContract.TABLE_NAME + "  " + values.toString());
						DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk id:" + value.get(NegocioContract.COLUNA_CHAVE) + ")" + values.toString());
				} else {
					long _id = db.insert(NegocioContract.TABLE_NAME, null, value);
					if (_id != -1) {
						//DCLog.d(DCLog.DATABASE_CRUD,this,"insert (bulk)" + NegocioContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
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
            	long _id = db.insert(NegocioContract.TABLE_NAME, null, value);
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
		String sql = "select max(" + NegocioContract.COLUNA_CHAVE + ") from " + NegocioContract.TABLE_NAME;
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