
package  br.com.lojadigicom.precomed.data.provider;


import br.com.lojadigicom.precomed.data.contract.*;
import br.com.lojadigicom.precomed.data.helper.AplicacaoDbHelper;
import android.content.ContentValues;
import android.content.ContentResolver;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.database.SQLException;
import android.database.DatabaseUtils;
import android.net.Uri;
import br.com.lojadigicom.precomed.framework.log.DCLog;
import java.util.List;
import android.content.ContentProvider;

public abstract class LojaVirtualProvider {

	private int qtdeLinhas = 0;
	
	public static final int LOJA_VIRTUAL = 80100;
	public static final int LOJA_VIRTUAL_ID = 80101;
	public static final int LOJA_VIRTUAL_SINC = 80103;
	public static final int LOJA_VIRTUAL_E_COMPLEMENTO = 80104;
	public static final int LOJA_VIRTUAL_ID_E_COMPLEMENTO = 80105;
	//public static final int LOJA_VIRTUAL_OPERACAO = 80102;
	
	// deletes
	public static final int LOJA_VIRTUAL_DELETE_ALL_SINC = 80106;
	public static final int LOJA_VIRTUAL_DELETE_RECREATE = 80107;
	public static final int LOJA_VIRTUAL_DELETE_SINC = 80108;
	public static final int LOJA_VIRTUAL_E_RETIRADA = 80109;
	
	private static final String sPorChaveSel = LojaVirtualContract.TABLE_NAME + "." + LojaVirtualContract.COLUNA_CHAVE + " = ? ";
	
	
	


	private ContentProvider mProvider = null;


	public void setContentProvider(ContentProvider valor) {
		mProvider = valor;
	}

	protected static final SQLiteQueryBuilder sQueryBuilder;
	static {
		sQueryBuilder = new SQLiteQueryBuilder();
		String tabelas = LojaVirtualContract.TABLE_NAME;
		
		sQueryBuilder.setTables(tabelas);
	}
	private static final SQLiteQueryBuilder sQueryBuilderSinc;
	static {
		sQueryBuilderSinc = new SQLiteQueryBuilder();
		String tabelas = LojaVirtualContract.TABLE_NAME_SINC;
		sQueryBuilderSinc.setTables(tabelas);
	}
	
	
	protected AplicacaoDbHelper mOpenHelper = null;
	
	public int getLinhas() {
		return qtdeLinhas;
	}
	
	public static void buildUriMatcher(UriMatcher matcher) {
		matcher.addURI(LojaVirtualContract.getContentAuthority(), LojaVirtualContract.PATH, LOJA_VIRTUAL);
		matcher.addURI(LojaVirtualContract.getContentAuthority(), LojaVirtualContract.PATH + "/Sinc" , LOJA_VIRTUAL_SINC);
		matcher.addURI(LojaVirtualContract.getContentAuthority(), LojaVirtualContract.PATH + "/#"    , LOJA_VIRTUAL_ID);
	
		matcher.addURI(LojaVirtualContract.getContentAuthority(), LojaVirtualContract.PATH + "/#/" + LojaVirtualContract.COM_COMPLEMENTO + "/*" , LOJA_VIRTUAL_ID_E_COMPLEMENTO);
		matcher.addURI(LojaVirtualContract.getContentAuthority(), LojaVirtualContract.PATH + "/" + LojaVirtualContract.COM_COMPLEMENTO + "/*" , LOJA_VIRTUAL_E_COMPLEMENTO);
		matcher.addURI(LojaVirtualContract.getContentAuthority(), LojaVirtualContract.PATH + "/" + LojaVirtualContract.COM_RETIRADA + "/*" , LOJA_VIRTUAL_E_RETIRADA);
		
		
		//matcher.addURI(AplicacaoContract.CONTENT_AUTHORITY, LojaVirtualContract.PATH + "/operacao/*" , LOJA_VIRTUAL_OPERACAO);
		
		
		
		
		// Deletes
		matcher.addURI(LojaVirtualContract.getContentAuthority(), LojaVirtualContract.PATH + "/DeleteAllSinc" , 	LOJA_VIRTUAL_DELETE_ALL_SINC);
		matcher.addURI(LojaVirtualContract.getContentAuthority(), LojaVirtualContract.PATH + "/DeleteAllRecreate" , LOJA_VIRTUAL_DELETE_RECREATE);
		matcher.addURI(LojaVirtualContract.getContentAuthority(), LojaVirtualContract.PATH + "/DeleteSinc/#" , 		LOJA_VIRTUAL_DELETE_SINC);
	}
	
	
	
	
	public void setAplicacaoDbHelper(AplicacaoDbHelper db) {
		mOpenHelper = db;
	}
	
	public Cursor query(UriMatcher sUriMatcher, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Query Uri:" + uri.toString());
		Cursor retCursor = null;
		switch (sUriMatcher.match(uri)) {
            case LOJA_VIRTUAL:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: LOJA_VIRTUAL");
                retCursor = query(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case LOJA_VIRTUAL_SINC:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: LOJA_VIRTUAL_SINC");
                retCursor = querySinc(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case LOJA_VIRTUAL_ID:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: LOJA_VIRTUAL_ID");
            	String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorChaveSel, args, sortOrder);
                break;
            }
            case LOJA_VIRTUAL_ID_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: LOJA_VIRTUAL_ID_E_COMPLEMENTO");
				String id = uri.getPathSegments().get(1);	
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri) +
						" where " + LojaVirtualContract.COLUNA_CHAVE + " = " + id;
				retCursor = queryRaw(sql);
				break;
			}
			case LOJA_VIRTUAL_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: LOJA_VIRTUAL_E_COMPLEMENTO");
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case LOJA_VIRTUAL_E_RETIRADA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: LOJA_VIRTUAL_E_RETIRADA");
				String sql = "select " +  LojaVirtualContract.camposOrdenados() +
						" from " + LojaVirtualContract.TABLE_NAME +
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
  	
		if (existeItem("SemProdutoPossui",uri.getPathSegments())) {
			sql += LojaVirtualContract.COLUNA_CHAVE + " not in (select " + 
					ProdutoContract.COLUNA_ID_LOJA_VIRTUAL_LE + " from " +
					ProdutoContract.TABLE_NAME + ")";
		}
	
		
		return sql;
	}
	
	private String sqlSelect(Uri uri) {
		String sql = LojaVirtualContract.camposOrdenados();
		List<String> segmentos = uri.getPathSegments();
		// ComChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
	
	// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComProdutoPossui",uri.getPathSegments())) {
			sql += "," +  ProdutoContract.camposOrdenados();
		}
	
		
		
		
		return sql;
	}
	private String sqlFrom(Uri uri) {
		String sql = LojaVirtualContract.TABLE_NAME;
		List<String> segmentos = uri.getPathSegments();
		//if (existeItem("ComEpisodioReferenteA",uri.getPathSegments())) {
		//	sql += " " +  EpisodioUsuarioContract.innerJoinEpisodio_ReferenteA();
		//}
		
		
				// ComChaveEstrangeira
  	
	
	// SemChaveEstrangeira
  	
		if (existeItem("ComProdutoPossui",uri.getPathSegments())) {
			sql += " " +  LojaVirtualContract.outerJoinProduto_Possui();
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
		DCLog.d(DCLog.DATABASE_ADM,this,sql);
		final SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		return db.rawQuery(sql,null);
	}
	
	public String getType(UriMatcher sUriMatcher, Uri uri) {
		switch (sUriMatcher.match(uri)) {
            case LOJA_VIRTUAL:
            {
                return LojaVirtualContract.getContentType();
            }
            case LOJA_VIRTUAL_ID:
            {
            	return LojaVirtualContract.getContentItemType();
            }
		}	
		return null;
	}
	
	public Uri insert(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Uri returnUri;
		long idNovo = getMaxId(db)+1;
		values.put(LojaVirtualContract.COLUNA_CHAVE, idNovo);
		long _id = db.insert(LojaVirtualContract.TABLE_NAME, null, values);
		if (_id > 0) {
			//DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + LojaVirtualContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
			DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + values.toString() + " (id:" + _id + ")");
			returnUri = LojaVirtualContract.buildLojaVirtualUri(idNovo);
			values.put(LojaVirtualContract.COLUNA_OPERACAO_SINC,"I");
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
			case LOJA_VIRTUAL_DELETE_SINC: {
				String id = uri.getPathSegments().get(2);
				Cursor cursor = db.query(LojaVirtualContract.TABLE_NAME,null,LojaVirtualContract.COLUNA_CHAVE + " = ? ",new String[]{id},null,null,null);
				if (cursor.moveToFirst()) {
					ContentValues valores = new ContentValues();
					DatabaseUtils.cursorRowToContentValues(cursor, valores);
					linhaDelete = db.delete(LojaVirtualContract.TABLE_NAME, LojaVirtualContract.COLUNA_CHAVE + " = ? ", new String[]{id});
					DCLog.d(DCLog.DATABASE_CRUD,this,"delete " + LojaVirtualContract.TABLE_NAME + "(id:" + id + ")");
					valores.put(LojaVirtualContract.COLUNA_OPERACAO_SINC, "D");
					insereSinc(db,valores);
				}
				notificaUriRelacionadas();
				mProvider.getContext().getContentResolver().notifyChange(LojaVirtualContract.buildAll(), null);
				break;
			}
			case LOJA_VIRTUAL_DELETE_ALL_SINC: {
				linhaDelete = db.delete(LojaVirtualContract.TABLE_NAME_SINC, null, null);
				break;
			}
			case LOJA_VIRTUAL_DELETE_RECREATE: {
				linhaDelete = db.delete(LojaVirtualContract.TABLE_NAME, null, null);
				break;
			}
		}
		return true;
	}
	
	
	public boolean update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		DCLog.d(DCLog.DATABASE_CRUD,this,"update2 " + values.toString() );
		linhaUpdate = db.update(LojaVirtualContract.TABLE_NAME, values, selection, selectionArgs);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	public boolean update(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		String selection = LojaVirtualContract.COLUNA_CHAVE + " = ? ";
		String dados[] = {values.get(LojaVirtualContract.COLUNA_CHAVE).toString()};
		DCLog.d(DCLog.DATABASE_CRUD,this,"update1 " + values.toString() );
		linhaUpdate = db.update(LojaVirtualContract.TABLE_NAME, values, selection, dados);
		values.put(LojaVirtualContract.COLUNA_OPERACAO_SINC,"A");
		insereSinc(db,values);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	
	private void insereSinc(SQLiteDatabase db, ContentValues values) {
		db.insert(LojaVirtualContract.TABLE_NAME_SINC, null, values);
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
				String[] args = {(String)value.get(LojaVirtualContract.COLUNA_CHAVE)};
				Cursor retCursor = query(null, sPorChaveSel, args, null);
				if (retCursor.moveToFirst()) {
						db.update(LojaVirtualContract.TABLE_NAME,value,sPorChaveSel,args);
						//DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk " + LojaVirtualContract.TABLE_NAME + "  " + values.toString());
						DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk id:" + value.get(LojaVirtualContract.COLUNA_CHAVE) + ")" + values.toString());
				} else {
					long _id = db.insert(LojaVirtualContract.TABLE_NAME, null, value);
					if (_id != -1) {
						//DCLog.d(DCLog.DATABASE_CRUD,this,"insert (bulk)" + LojaVirtualContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
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
            	long _id = db.insert(LojaVirtualContract.TABLE_NAME, null, value);
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
		String sql = "select max(" + LojaVirtualContract.COLUNA_CHAVE + ") from " + LojaVirtualContract.TABLE_NAME;
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