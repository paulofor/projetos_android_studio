
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

public abstract class ContatoClienteProvider {

	private int qtdeLinhas = 0;
	
	public static final int CONTATO_CLIENTE = 40100;
	public static final int CONTATO_CLIENTE_ID = 40101;
	public static final int CONTATO_CLIENTE_SINC = 40103;
	public static final int CONTATO_CLIENTE_E_COMPLEMENTO = 40104;
	public static final int CONTATO_CLIENTE_ID_E_COMPLEMENTO = 40105;
	//public static final int CONTATO_CLIENTE_OPERACAO = 40102;
	
	// deletes
	public static final int CONTATO_CLIENTE_DELETE_ALL_SINC = 40106;
	public static final int CONTATO_CLIENTE_DELETE_RECREATE = 40107;
	public static final int CONTATO_CLIENTE_DELETE_SINC = 40108;
	public static final int CONTATO_CLIENTE_E_RETIRADA = 40109;
	
	private static final String sPorChaveSel = ContatoClienteContract.TABLE_NAME + "." + ContatoClienteContract.COLUNA_CHAVE + " = ? ";
	
	
	
	public static final int CONTATO_CLIENTE_POR_CLIENTE_REFERENTEA = 40120;
	public static final int COM_CLIENTE = 40121;
	private static final String sPorIdClienteRaSelecao =
            ContatoClienteContract.TABLE_NAME + ".id_cliente_ra = ? ";
	


	private ContentProvider mProvider = null;


	public void setContentProvider(ContentProvider valor) {
		mProvider = valor;
	}

	protected static final SQLiteQueryBuilder sQueryBuilder;
	static {
		sQueryBuilder = new SQLiteQueryBuilder();
		String tabelas = ContatoClienteContract.TABLE_NAME;
		
		//tabelas += " inner join " + ClienteContract.TABLE_NAME + " ";
		//tabelas += " on " + ClienteContract.TABLE_NAME + "." + ClienteContract.COLUNA_CHAVE + " = " + ContatoClienteContract.TABLE_NAME + "." + ContatoClienteContract.COLUNA_ID_CLIENTE_RA; 
		
		sQueryBuilder.setTables(tabelas);
	}
	private static final SQLiteQueryBuilder sQueryBuilderSinc;
	static {
		sQueryBuilderSinc = new SQLiteQueryBuilder();
		String tabelas = ContatoClienteContract.TABLE_NAME_SINC;
		sQueryBuilderSinc.setTables(tabelas);
	}
	
	
	protected AplicacaoDbHelper mOpenHelper = null;
	
	public int getLinhas() {
		return qtdeLinhas;
	}
	
	public static void buildUriMatcher(UriMatcher matcher) {
		matcher.addURI(ContatoClienteContract.getContentAuthority(), ContatoClienteContract.PATH, CONTATO_CLIENTE);
		matcher.addURI(ContatoClienteContract.getContentAuthority(), ContatoClienteContract.PATH + "/Sinc" , CONTATO_CLIENTE_SINC);
		matcher.addURI(ContatoClienteContract.getContentAuthority(), ContatoClienteContract.PATH + "/#"    , CONTATO_CLIENTE_ID);
	
		matcher.addURI(ContatoClienteContract.getContentAuthority(), ContatoClienteContract.PATH + "/#/" + ContatoClienteContract.COM_COMPLEMENTO + "/*" , CONTATO_CLIENTE_ID_E_COMPLEMENTO);
		matcher.addURI(ContatoClienteContract.getContentAuthority(), ContatoClienteContract.PATH + "/" + ContatoClienteContract.COM_COMPLEMENTO + "/*" , CONTATO_CLIENTE_E_COMPLEMENTO);
		matcher.addURI(ContatoClienteContract.getContentAuthority(), ContatoClienteContract.PATH + "/" + ContatoClienteContract.COM_RETIRADA + "/*" , CONTATO_CLIENTE_E_RETIRADA);
		
		
		//matcher.addURI(AplicacaoContract.CONTENT_AUTHORITY, ContatoClienteContract.PATH + "/operacao/*" , CONTATO_CLIENTE_OPERACAO);
		
		matcher.addURI(ContatoClienteContract.getContentAuthority(), ContatoClienteContract.PATH + "/#/" + ClienteContract.PATH, CONTATO_CLIENTE_POR_CLIENTE_REFERENTEA);
		matcher.addURI(ContatoClienteContract.getContentAuthority(), ContatoClienteContract.PATH + "/ComClienteReferenteA/" , COM_CLIENTE);
		
		
		
		
		// Deletes
		matcher.addURI(ContatoClienteContract.getContentAuthority(), ContatoClienteContract.PATH + "/DeleteAllSinc" , 	CONTATO_CLIENTE_DELETE_ALL_SINC);
		matcher.addURI(ContatoClienteContract.getContentAuthority(), ContatoClienteContract.PATH + "/DeleteAllRecreate" , CONTATO_CLIENTE_DELETE_RECREATE);
		matcher.addURI(ContatoClienteContract.getContentAuthority(), ContatoClienteContract.PATH + "/DeleteSinc/#" , 		CONTATO_CLIENTE_DELETE_SINC);
	}
	
	
	
	
	public void setAplicacaoDbHelper(AplicacaoDbHelper db) {
		mOpenHelper = db;
	}
	
	public Cursor query(UriMatcher sUriMatcher, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Query Uri:" + uri.toString());
		Cursor retCursor = null;
		switch (sUriMatcher.match(uri)) {
            case CONTATO_CLIENTE:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CONTATO_CLIENTE");
                retCursor = query(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case CONTATO_CLIENTE_SINC:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CONTATO_CLIENTE_SINC");
                retCursor = querySinc(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case CONTATO_CLIENTE_ID:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CONTATO_CLIENTE_ID");
            	String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorChaveSel, args, sortOrder);
                break;
            }
            case CONTATO_CLIENTE_ID_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CONTATO_CLIENTE_ID_E_COMPLEMENTO");
				String id = uri.getPathSegments().get(1);	
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri) +
						" where " + ContatoClienteContract.COLUNA_CHAVE + " = " + id;
				retCursor = queryRaw(sql);
				break;
			}
			case CONTATO_CLIENTE_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CONTATO_CLIENTE_E_COMPLEMENTO");
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case CONTATO_CLIENTE_E_RETIRADA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CONTATO_CLIENTE_E_RETIRADA");
				String sql = "select " +  ContatoClienteContract.camposOrdenados() +
						" from " + ContatoClienteContract.TABLE_NAME +
						sqlWhere(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case CONTATO_CLIENTE_POR_CLIENTE_REFERENTEA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CONTATO_CLIENTE_POR_CLIENTE_REFERENTEA");
	            String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorIdClienteRaSelecao, args, sortOrder);
                break;
            }
            case COM_CLIENTE:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: COM_CLIENTE");
            	String sql = "select " + ContatoClienteContract.camposOrdenados() + " , " +
						ClienteContract.camposOrdenados() +
						" from " + ContatoClienteContract.TABLE_NAME +
						ContatoClienteContract.innerJoinCliente_ReferenteA() +
						getOrderBy();
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
		String sql = ContatoClienteContract.camposOrdenados();
		List<String> segmentos = uri.getPathSegments();
		// ComChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComClienteReferenteA",uri.getPathSegments())) {
			sql += "," +  ClienteContract.camposOrdenados();
		}
	
	
	// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		
		
		
		return sql;
	}
	private String sqlFrom(Uri uri) {
		String sql = ContatoClienteContract.TABLE_NAME;
		List<String> segmentos = uri.getPathSegments();
		//if (existeItem("ComEpisodioReferenteA",uri.getPathSegments())) {
		//	sql += " " +  EpisodioUsuarioContract.innerJoinEpisodio_ReferenteA();
		//}
		
		
				// ComChaveEstrangeira
  	
		if (existeItem("ComClienteReferenteA",uri.getPathSegments())) {
			sql += " " +  ContatoClienteContract.outerJoinCliente_ReferenteA();
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
            case CONTATO_CLIENTE:
            {
                return ContatoClienteContract.getContentType();
            }
            case CONTATO_CLIENTE_ID:
            {
            	return ContatoClienteContract.getContentItemType();
            }
			case CONTATO_CLIENTE_POR_CLIENTE_REFERENTEA:
            {
	            return ContatoClienteContract.getContentType();
            }
		
		}	
		return null;
	}
	
	public Uri insert(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Uri returnUri;
		long idNovo = getMaxId(db)+1;
		values.put(ContatoClienteContract.COLUNA_CHAVE, idNovo);
		long _id = db.insert(ContatoClienteContract.TABLE_NAME, null, values);
		if (_id > 0) {
			//DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + ContatoClienteContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
			DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + values.toString() + " (id:" + _id + ")");
			returnUri = ContatoClienteContract.buildContatoClienteUri(idNovo);
			values.put(ContatoClienteContract.COLUNA_OPERACAO_SINC,"I");
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
			case CONTATO_CLIENTE_DELETE_SINC: {
				String id = uri.getPathSegments().get(2);
				Cursor cursor = db.query(ContatoClienteContract.TABLE_NAME,null,ContatoClienteContract.COLUNA_CHAVE + " = ? ",new String[]{id},null,null,null);
				if (cursor.moveToFirst()) {
					ContentValues valores = new ContentValues();
					DatabaseUtils.cursorRowToContentValues(cursor, valores);
					linhaDelete = db.delete(ContatoClienteContract.TABLE_NAME, ContatoClienteContract.COLUNA_CHAVE + " = ? ", new String[]{id});
					DCLog.d(DCLog.DATABASE_CRUD,this,"delete " + ContatoClienteContract.TABLE_NAME + "(id:" + id + ")");
					valores.put(ContatoClienteContract.COLUNA_OPERACAO_SINC, "D");
					insereSinc(db,valores);
				}
				notificaUriRelacionadas();
				mProvider.getContext().getContentResolver().notifyChange(ContatoClienteContract.buildAll(), null);
				break;
			}
			case CONTATO_CLIENTE_DELETE_ALL_SINC: {
				linhaDelete = db.delete(ContatoClienteContract.TABLE_NAME_SINC, null, null);
				break;
			}
			case CONTATO_CLIENTE_DELETE_RECREATE: {
				linhaDelete = db.delete(ContatoClienteContract.TABLE_NAME, null, null);
				break;
			}
		}
		return true;
	}
	
	
	public boolean update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		DCLog.d(DCLog.DATABASE_CRUD,this,"update2 " + values.toString() );
		linhaUpdate = db.update(ContatoClienteContract.TABLE_NAME, values, selection, selectionArgs);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	public boolean update(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		String selection = ContatoClienteContract.COLUNA_CHAVE + " = ? ";
		String dados[] = {values.get(ContatoClienteContract.COLUNA_CHAVE).toString()};
		DCLog.d(DCLog.DATABASE_CRUD,this,"update1 " + values.toString() );
		linhaUpdate = db.update(ContatoClienteContract.TABLE_NAME, values, selection, dados);
		values.put(ContatoClienteContract.COLUNA_OPERACAO_SINC,"A");
		insereSinc(db,values);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	
	private void insereSinc(SQLiteDatabase db, ContentValues values) {
		db.insert(ContatoClienteContract.TABLE_NAME_SINC, null, values);
		notificaUriOperacoes();
	}
	
	protected abstract void notificaOutrasUri(ContentResolver resolver);
	
	
	// Notificar todas as uris de entidades que possuem chave estrangeira dessa entidade.
	private void notificaUriRelacionadas() {
		// ComChaveEstrangeira
  	
		mProvider.getContext().getContentResolver().notifyChange(ClienteContract.buildAllComContatoClienteEstabelece(), null);
		mProvider.getContext().getContentResolver().notifyChange(ClienteContract.buildAllSemContatoClienteEstabelece(), null);
	
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
				String[] args = {(String)value.get(ContatoClienteContract.COLUNA_CHAVE)};
				Cursor retCursor = query(null, sPorChaveSel, args, null);
				if (retCursor.moveToFirst()) {
						db.update(ContatoClienteContract.TABLE_NAME,value,sPorChaveSel,args);
						//DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk " + ContatoClienteContract.TABLE_NAME + "  " + values.toString());
						DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk id:" + value.get(ContatoClienteContract.COLUNA_CHAVE) + ")" + values.toString());
				} else {
					long _id = db.insert(ContatoClienteContract.TABLE_NAME, null, value);
					if (_id != -1) {
						//DCLog.d(DCLog.DATABASE_CRUD,this,"insert (bulk)" + ContatoClienteContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
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
            	long _id = db.insert(ContatoClienteContract.TABLE_NAME, null, value);
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
		String sql = "select max(" + ContatoClienteContract.COLUNA_CHAVE + ") from " + ContatoClienteContract.TABLE_NAME;
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