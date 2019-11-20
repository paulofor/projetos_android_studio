
package  br.com.lojadigicom.treinoacademia.data.provider;


import br.com.lojadigicom.treinoacademia.data.contract.*;
import br.com.lojadigicom.treinoacademia.data.helper.AplicacaoDbHelper;
import android.content.ContentValues;
import android.content.ContentResolver;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.database.SQLException;
import android.database.DatabaseUtils;
import android.net.Uri;
import br.com.lojadigicom.treinoacademia.framework.log.DCLog;
import java.util.List;
import android.content.ContentProvider;

public abstract class GrupoMuscularProvider {

	private int qtdeLinhas = 0;
	
	public static final int GRUPO_MUSCULAR = 84800;
	public static final int GRUPO_MUSCULAR_ID = 84801;
	public static final int GRUPO_MUSCULAR_SINC = 84803;
	public static final int GRUPO_MUSCULAR_E_COMPLEMENTO = 84804;
	public static final int GRUPO_MUSCULAR_ID_E_COMPLEMENTO = 84805;
	//public static final int GRUPO_MUSCULAR_OPERACAO = 84802;
	
	// deletes
	public static final int GRUPO_MUSCULAR_DELETE_ALL_SINC = 84806;
	public static final int GRUPO_MUSCULAR_DELETE_RECREATE = 84807;
	public static final int GRUPO_MUSCULAR_DELETE_SINC = 84808;
	public static final int GRUPO_MUSCULAR_E_RETIRADA = 84809;
	
	private static final String sPorChaveSel = GrupoMuscularContract.TABLE_NAME + "." + GrupoMuscularContract.COLUNA_CHAVE + " = ? ";
	
	
	


	private ContentProvider mProvider = null;


	public void setContentProvider(ContentProvider valor) {
		mProvider = valor;
	}

	protected static final SQLiteQueryBuilder sQueryBuilder;
	static {
		sQueryBuilder = new SQLiteQueryBuilder();
		String tabelas = GrupoMuscularContract.TABLE_NAME;
		
		sQueryBuilder.setTables(tabelas);
	}
	private static final SQLiteQueryBuilder sQueryBuilderSinc;
	static {
		sQueryBuilderSinc = new SQLiteQueryBuilder();
		String tabelas = GrupoMuscularContract.TABLE_NAME_SINC;
		sQueryBuilderSinc.setTables(tabelas);
	}
	
	
	protected AplicacaoDbHelper mOpenHelper = null;
	
	public int getLinhas() {
		return qtdeLinhas;
	}
	
	public static void buildUriMatcher(UriMatcher matcher) {
		matcher.addURI(GrupoMuscularContract.getContentAuthority(), GrupoMuscularContract.PATH, GRUPO_MUSCULAR);
		matcher.addURI(GrupoMuscularContract.getContentAuthority(), GrupoMuscularContract.PATH + "/Sinc" , GRUPO_MUSCULAR_SINC);
		matcher.addURI(GrupoMuscularContract.getContentAuthority(), GrupoMuscularContract.PATH + "/#"    , GRUPO_MUSCULAR_ID);
	
		matcher.addURI(GrupoMuscularContract.getContentAuthority(), GrupoMuscularContract.PATH + "/#/" + GrupoMuscularContract.COM_COMPLEMENTO + "/*" , GRUPO_MUSCULAR_ID_E_COMPLEMENTO);
		matcher.addURI(GrupoMuscularContract.getContentAuthority(), GrupoMuscularContract.PATH + "/" + GrupoMuscularContract.COM_COMPLEMENTO + "/*" , GRUPO_MUSCULAR_E_COMPLEMENTO);
		matcher.addURI(GrupoMuscularContract.getContentAuthority(), GrupoMuscularContract.PATH + "/" + GrupoMuscularContract.COM_RETIRADA + "/*" , GRUPO_MUSCULAR_E_RETIRADA);
		
		
		//matcher.addURI(AplicacaoContract.CONTENT_AUTHORITY, GrupoMuscularContract.PATH + "/operacao/*" , GRUPO_MUSCULAR_OPERACAO);
		
		
		
		
		// Deletes
		matcher.addURI(GrupoMuscularContract.getContentAuthority(), GrupoMuscularContract.PATH + "/DeleteAllSinc" , 	GRUPO_MUSCULAR_DELETE_ALL_SINC);
		matcher.addURI(GrupoMuscularContract.getContentAuthority(), GrupoMuscularContract.PATH + "/DeleteAllRecreate" , GRUPO_MUSCULAR_DELETE_RECREATE);
		matcher.addURI(GrupoMuscularContract.getContentAuthority(), GrupoMuscularContract.PATH + "/DeleteSinc/#" , 		GRUPO_MUSCULAR_DELETE_SINC);
	}
	
	
	
	
	public void setAplicacaoDbHelper(AplicacaoDbHelper db) {
		mOpenHelper = db;
	}
	
	public Cursor query(UriMatcher sUriMatcher, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Query Uri:" + uri.toString());
		Cursor retCursor = null;
		switch (sUriMatcher.match(uri)) {
            case GRUPO_MUSCULAR:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: GRUPO_MUSCULAR");
                retCursor = query(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case GRUPO_MUSCULAR_SINC:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: GRUPO_MUSCULAR_SINC");
                retCursor = querySinc(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case GRUPO_MUSCULAR_ID:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: GRUPO_MUSCULAR_ID");
            	String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorChaveSel, args, sortOrder);
                break;
            }
            case GRUPO_MUSCULAR_ID_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: GRUPO_MUSCULAR_ID_E_COMPLEMENTO");
				String id = uri.getPathSegments().get(1);	
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri) +
						" where " + GrupoMuscularContract.COLUNA_CHAVE + " = " + id;
				retCursor = queryRaw(sql);
				break;
			}
			case GRUPO_MUSCULAR_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: GRUPO_MUSCULAR_E_COMPLEMENTO");
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case GRUPO_MUSCULAR_E_RETIRADA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: GRUPO_MUSCULAR_E_RETIRADA");
				String sql = "select " +  GrupoMuscularContract.camposOrdenados() +
						" from " + GrupoMuscularContract.TABLE_NAME +
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
  	
		if (existeItem("SemExercicioReferenteA",uri.getPathSegments())) {
			sql += GrupoMuscularContract.COLUNA_CHAVE + " not in (select " + 
					ExercicioContract.COLUNA_ID_GRUPO_MUSCULAR_P + " from " +
					ExercicioContract.TABLE_NAME + ")";
		}
	
		
		return sql;
	}
	
	private String sqlSelect(Uri uri) {
		String sql = GrupoMuscularContract.camposOrdenados();
		List<String> segmentos = uri.getPathSegments();
		// ComChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
	
	// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComExercicioReferenteA",uri.getPathSegments())) {
			sql += "," +  ExercicioContract.camposOrdenados();
		}
	
		
		
		
		return sql;
	}
	private String sqlFrom(Uri uri) {
		String sql = GrupoMuscularContract.TABLE_NAME;
		List<String> segmentos = uri.getPathSegments();
		//if (existeItem("ComEpisodioReferenteA",uri.getPathSegments())) {
		//	sql += " " +  EpisodioUsuarioContract.innerJoinEpisodio_ReferenteA();
		//}
		
		
				// ComChaveEstrangeira
  	
	
	// SemChaveEstrangeira
  	
		if (existeItem("ComExercicioReferenteA",uri.getPathSegments())) {
			sql += " " +  GrupoMuscularContract.outerJoinExercicio_ReferenteA();
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
            case GRUPO_MUSCULAR:
            {
                return GrupoMuscularContract.getContentType();
            }
            case GRUPO_MUSCULAR_ID:
            {
            	return GrupoMuscularContract.getContentItemType();
            }
		}	
		return null;
	}
	
	public Uri insert(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Uri returnUri;
		long idNovo = getMaxId(db)+1;
		values.put(GrupoMuscularContract.COLUNA_CHAVE, idNovo);
		long _id = db.insert(GrupoMuscularContract.TABLE_NAME, null, values);
		if (_id > 0) {
			//DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + GrupoMuscularContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
			DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + values.toString() + " (id:" + _id + ")");
			returnUri = GrupoMuscularContract.buildGrupoMuscularUri(idNovo);
			values.put(GrupoMuscularContract.COLUNA_OPERACAO_SINC,"I");
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
			case GRUPO_MUSCULAR_DELETE_SINC: {
				String id = uri.getPathSegments().get(2);
				Cursor cursor = db.query(GrupoMuscularContract.TABLE_NAME,null,GrupoMuscularContract.COLUNA_CHAVE + " = ? ",new String[]{id},null,null,null);
				if (cursor.moveToFirst()) {
					ContentValues valores = new ContentValues();
					DatabaseUtils.cursorRowToContentValues(cursor, valores);
					linhaDelete = db.delete(GrupoMuscularContract.TABLE_NAME, GrupoMuscularContract.COLUNA_CHAVE + " = ? ", new String[]{id});
					DCLog.d(DCLog.DATABASE_CRUD,this,"delete " + GrupoMuscularContract.TABLE_NAME + "(id:" + id + ")");
					valores.put(GrupoMuscularContract.COLUNA_OPERACAO_SINC, "D");
					insereSinc(db,valores);
				}
				notificaUriRelacionadas();
				mProvider.getContext().getContentResolver().notifyChange(GrupoMuscularContract.buildAll(), null);
				break;
			}
			case GRUPO_MUSCULAR_DELETE_ALL_SINC: {
				linhaDelete = db.delete(GrupoMuscularContract.TABLE_NAME_SINC, null, null);
				break;
			}
			case GRUPO_MUSCULAR_DELETE_RECREATE: {
				linhaDelete = db.delete(GrupoMuscularContract.TABLE_NAME, null, null);
				break;
			}
		}
		return true;
	}
	
	
	public boolean update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		DCLog.d(DCLog.DATABASE_CRUD,this,"update2 " + values.toString() );
		linhaUpdate = db.update(GrupoMuscularContract.TABLE_NAME, values, selection, selectionArgs);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	public boolean update(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		String selection = GrupoMuscularContract.COLUNA_CHAVE + " = ? ";
		String dados[] = {values.get(GrupoMuscularContract.COLUNA_CHAVE).toString()};
		DCLog.d(DCLog.DATABASE_CRUD,this,"update1 " + values.toString() );
		linhaUpdate = db.update(GrupoMuscularContract.TABLE_NAME, values, selection, dados);
		values.put(GrupoMuscularContract.COLUNA_OPERACAO_SINC,"A");
		insereSinc(db,values);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	
	private void insereSinc(SQLiteDatabase db, ContentValues values) {
		db.insert(GrupoMuscularContract.TABLE_NAME_SINC, null, values);
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
				String[] args = {(String)value.get(GrupoMuscularContract.COLUNA_CHAVE)};
				Cursor retCursor = query(null, sPorChaveSel, args, null);
				if (retCursor.moveToFirst()) {
						db.update(GrupoMuscularContract.TABLE_NAME,value,sPorChaveSel,args);
						//DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk " + GrupoMuscularContract.TABLE_NAME + "  " + values.toString());
						DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk id:" + value.get(GrupoMuscularContract.COLUNA_CHAVE) + ")" + values.toString());
				} else {
					long _id = db.insert(GrupoMuscularContract.TABLE_NAME, null, value);
					if (_id != -1) {
						//DCLog.d(DCLog.DATABASE_CRUD,this,"insert (bulk)" + GrupoMuscularContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
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
            	long _id = db.insert(GrupoMuscularContract.TABLE_NAME, null, value);
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
		String sql = "select max(" + GrupoMuscularContract.COLUNA_CHAVE + ") from " + GrupoMuscularContract.TABLE_NAME;
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