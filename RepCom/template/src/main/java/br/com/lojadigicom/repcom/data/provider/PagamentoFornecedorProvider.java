
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

public abstract class PagamentoFornecedorProvider {

	private int qtdeLinhas = 0;
	
	public static final int PAGAMENTO_FORNECEDOR = 40500;
	public static final int PAGAMENTO_FORNECEDOR_ID = 40501;
	public static final int PAGAMENTO_FORNECEDOR_SINC = 40503;
	public static final int PAGAMENTO_FORNECEDOR_E_COMPLEMENTO = 40504;
	public static final int PAGAMENTO_FORNECEDOR_ID_E_COMPLEMENTO = 40505;
	//public static final int PAGAMENTO_FORNECEDOR_OPERACAO = 40502;
	
	// deletes
	public static final int PAGAMENTO_FORNECEDOR_DELETE_ALL_SINC = 40506;
	public static final int PAGAMENTO_FORNECEDOR_DELETE_RECREATE = 40507;
	public static final int PAGAMENTO_FORNECEDOR_DELETE_SINC = 40508;
	public static final int PAGAMENTO_FORNECEDOR_E_RETIRADA = 40509;
	
	private static final String sPorChaveSel = PagamentoFornecedorContract.TABLE_NAME + "." + PagamentoFornecedorContract.COLUNA_CHAVE + " = ? ";
	
	
	


	private ContentProvider mProvider = null;


	public void setContentProvider(ContentProvider valor) {
		mProvider = valor;
	}

	protected static final SQLiteQueryBuilder sQueryBuilder;
	static {
		sQueryBuilder = new SQLiteQueryBuilder();
		String tabelas = PagamentoFornecedorContract.TABLE_NAME;
		
		sQueryBuilder.setTables(tabelas);
	}
	private static final SQLiteQueryBuilder sQueryBuilderSinc;
	static {
		sQueryBuilderSinc = new SQLiteQueryBuilder();
		String tabelas = PagamentoFornecedorContract.TABLE_NAME_SINC;
		sQueryBuilderSinc.setTables(tabelas);
	}
	
	
	protected AplicacaoDbHelper mOpenHelper = null;
	
	public int getLinhas() {
		return qtdeLinhas;
	}
	
	public static void buildUriMatcher(UriMatcher matcher) {
		matcher.addURI(PagamentoFornecedorContract.getContentAuthority(), PagamentoFornecedorContract.PATH, PAGAMENTO_FORNECEDOR);
		matcher.addURI(PagamentoFornecedorContract.getContentAuthority(), PagamentoFornecedorContract.PATH + "/Sinc" , PAGAMENTO_FORNECEDOR_SINC);
		matcher.addURI(PagamentoFornecedorContract.getContentAuthority(), PagamentoFornecedorContract.PATH + "/#"    , PAGAMENTO_FORNECEDOR_ID);
	
		matcher.addURI(PagamentoFornecedorContract.getContentAuthority(), PagamentoFornecedorContract.PATH + "/#/" + PagamentoFornecedorContract.COM_COMPLEMENTO + "/*" , PAGAMENTO_FORNECEDOR_ID_E_COMPLEMENTO);
		matcher.addURI(PagamentoFornecedorContract.getContentAuthority(), PagamentoFornecedorContract.PATH + "/" + PagamentoFornecedorContract.COM_COMPLEMENTO + "/*" , PAGAMENTO_FORNECEDOR_E_COMPLEMENTO);
		matcher.addURI(PagamentoFornecedorContract.getContentAuthority(), PagamentoFornecedorContract.PATH + "/" + PagamentoFornecedorContract.COM_RETIRADA + "/*" , PAGAMENTO_FORNECEDOR_E_RETIRADA);
		
		
		//matcher.addURI(AplicacaoContract.CONTENT_AUTHORITY, PagamentoFornecedorContract.PATH + "/operacao/*" , PAGAMENTO_FORNECEDOR_OPERACAO);
		
		
		
		
		// Deletes
		matcher.addURI(PagamentoFornecedorContract.getContentAuthority(), PagamentoFornecedorContract.PATH + "/DeleteAllSinc" , 	PAGAMENTO_FORNECEDOR_DELETE_ALL_SINC);
		matcher.addURI(PagamentoFornecedorContract.getContentAuthority(), PagamentoFornecedorContract.PATH + "/DeleteAllRecreate" , PAGAMENTO_FORNECEDOR_DELETE_RECREATE);
		matcher.addURI(PagamentoFornecedorContract.getContentAuthority(), PagamentoFornecedorContract.PATH + "/DeleteSinc/#" , 		PAGAMENTO_FORNECEDOR_DELETE_SINC);
	}
	
	
	
	
	public void setAplicacaoDbHelper(AplicacaoDbHelper db) {
		mOpenHelper = db;
	}
	
	public Cursor query(UriMatcher sUriMatcher, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Query Uri:" + uri.toString());
		Cursor retCursor = null;
		switch (sUriMatcher.match(uri)) {
            case PAGAMENTO_FORNECEDOR:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PAGAMENTO_FORNECEDOR");
                retCursor = query(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case PAGAMENTO_FORNECEDOR_SINC:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PAGAMENTO_FORNECEDOR_SINC");
                retCursor = querySinc(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case PAGAMENTO_FORNECEDOR_ID:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PAGAMENTO_FORNECEDOR_ID");
            	String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorChaveSel, args, sortOrder);
                break;
            }
            case PAGAMENTO_FORNECEDOR_ID_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PAGAMENTO_FORNECEDOR_ID_E_COMPLEMENTO");
				String id = uri.getPathSegments().get(1);	
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri) +
						" where " + PagamentoFornecedorContract.COLUNA_CHAVE + " = " + id;
				retCursor = queryRaw(sql);
				break;
			}
			case PAGAMENTO_FORNECEDOR_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PAGAMENTO_FORNECEDOR_E_COMPLEMENTO");
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case PAGAMENTO_FORNECEDOR_E_RETIRADA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PAGAMENTO_FORNECEDOR_E_RETIRADA");
				String sql = "select " +  PagamentoFornecedorContract.camposOrdenados() +
						" from " + PagamentoFornecedorContract.TABLE_NAME +
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
		String sql = PagamentoFornecedorContract.camposOrdenados();
		List<String> segmentos = uri.getPathSegments();
		// ComChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
	
	// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		
		
		
		return sql;
	}
	private String sqlFrom(Uri uri) {
		String sql = PagamentoFornecedorContract.TABLE_NAME;
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
            case PAGAMENTO_FORNECEDOR:
            {
                return PagamentoFornecedorContract.getContentType();
            }
            case PAGAMENTO_FORNECEDOR_ID:
            {
            	return PagamentoFornecedorContract.getContentItemType();
            }
		}	
		return null;
	}
	
	public Uri insert(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Uri returnUri;
		long idNovo = getMaxId(db)+1;
		values.put(PagamentoFornecedorContract.COLUNA_CHAVE, idNovo);
		long _id = db.insert(PagamentoFornecedorContract.TABLE_NAME, null, values);
		if (_id > 0) {
			//DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + PagamentoFornecedorContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
			DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + values.toString() + " (id:" + _id + ")");
			returnUri = PagamentoFornecedorContract.buildPagamentoFornecedorUri(idNovo);
			values.put(PagamentoFornecedorContract.COLUNA_OPERACAO_SINC,"I");
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
			case PAGAMENTO_FORNECEDOR_DELETE_SINC: {
				String id = uri.getPathSegments().get(2);
				Cursor cursor = db.query(PagamentoFornecedorContract.TABLE_NAME,null,PagamentoFornecedorContract.COLUNA_CHAVE + " = ? ",new String[]{id},null,null,null);
				if (cursor.moveToFirst()) {
					ContentValues valores = new ContentValues();
					DatabaseUtils.cursorRowToContentValues(cursor, valores);
					linhaDelete = db.delete(PagamentoFornecedorContract.TABLE_NAME, PagamentoFornecedorContract.COLUNA_CHAVE + " = ? ", new String[]{id});
					DCLog.d(DCLog.DATABASE_CRUD,this,"delete " + PagamentoFornecedorContract.TABLE_NAME + "(id:" + id + ")");
					valores.put(PagamentoFornecedorContract.COLUNA_OPERACAO_SINC, "D");
					insereSinc(db,valores);
				}
				notificaUriRelacionadas();
				mProvider.getContext().getContentResolver().notifyChange(PagamentoFornecedorContract.buildAll(), null);
				break;
			}
			case PAGAMENTO_FORNECEDOR_DELETE_ALL_SINC: {
				linhaDelete = db.delete(PagamentoFornecedorContract.TABLE_NAME_SINC, null, null);
				break;
			}
			case PAGAMENTO_FORNECEDOR_DELETE_RECREATE: {
				linhaDelete = db.delete(PagamentoFornecedorContract.TABLE_NAME, null, null);
				break;
			}
		}
		return true;
	}
	
	
	public boolean update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		linhaUpdate = db.update(PagamentoFornecedorContract.TABLE_NAME, values, selection, selectionArgs);
		return true;
	}
	public boolean update(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		String selection = PagamentoFornecedorContract.COLUNA_CHAVE + " = ? ";
		String dados[] = {values.get(PagamentoFornecedorContract.COLUNA_CHAVE).toString()};
		linhaUpdate = db.update(PagamentoFornecedorContract.TABLE_NAME, values, selection, dados);
		values.put(PagamentoFornecedorContract.COLUNA_OPERACAO_SINC,"A");
		insereSinc(db,values);
		return true;
	}
	
	private void insereSinc(SQLiteDatabase db, ContentValues values) {
		db.insert(PagamentoFornecedorContract.TABLE_NAME_SINC, null, values);
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
				String[] args = {(String)value.get(PagamentoFornecedorContract.COLUNA_CHAVE)};
				Cursor retCursor = query(null, sPorChaveSel, args, null);
				if (retCursor.moveToFirst()) {
						db.update(PagamentoFornecedorContract.TABLE_NAME,value,sPorChaveSel,args);
						//DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk " + PagamentoFornecedorContract.TABLE_NAME + "  " + values.toString());
						DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk id:" + value.get(PagamentoFornecedorContract.COLUNA_CHAVE) + ")" + values.toString());
				} else {
					long _id = db.insert(PagamentoFornecedorContract.TABLE_NAME, null, value);
					if (_id != -1) {
						//DCLog.d(DCLog.DATABASE_CRUD,this,"insert (bulk)" + PagamentoFornecedorContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
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
            	long _id = db.insert(PagamentoFornecedorContract.TABLE_NAME, null, value);
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
		String sql = "select max(" + PagamentoFornecedorContract.COLUNA_CHAVE + ") from " + PagamentoFornecedorContract.TABLE_NAME;
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