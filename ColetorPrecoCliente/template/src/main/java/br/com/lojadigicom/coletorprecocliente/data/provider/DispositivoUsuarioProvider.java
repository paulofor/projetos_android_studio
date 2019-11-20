
package  br.com.lojadigicom.coletorprecocliente.data.provider;


import br.com.lojadigicom.coletorprecocliente.data.contract.*;
import br.com.lojadigicom.coletorprecocliente.data.helper.AplicacaoDbHelper;
import android.content.ContentValues;
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

public abstract class DispositivoUsuarioProvider {

	private int qtdeLinhas = 0;
	
	public static final int DISPOSITIVO_USUARIO = 87700;
	public static final int DISPOSITIVO_USUARIO_ID = 87701;
	public static final int DISPOSITIVO_USUARIO_SINC = 87703;
	public static final int DISPOSITIVO_USUARIO_E_COMPLEMENTO = 87704;
	public static final int DISPOSITIVO_USUARIO_ID_E_COMPLEMENTO = 87705;
	//public static final int DISPOSITIVO_USUARIO_OPERACAO = 87702;
	
	// deletes
	public static final int DISPOSITIVO_USUARIO_DELETE_ALL_SINC = 87706;
	public static final int DISPOSITIVO_USUARIO_DELETE_RECREATE = 87707;
	public static final int DISPOSITIVO_USUARIO_DELETE_SINC = 87708;
	public static final int DISPOSITIVO_USUARIO_E_RETIRADA = 87709;
	
	private static final String sPorChaveSel = DispositivoUsuarioContract.TABLE_NAME + "." + DispositivoUsuarioContract.COLUNA_CHAVE + " = ? ";
	
	
	
	public static final int DISPOSITIVO_USUARIO_POR_USUARIO_REFERENTEA = 87720;
	public static final int COM_USUARIO = 87721;
	private static final String sPorIdUsuarioRaSelecao =
            DispositivoUsuarioContract.TABLE_NAME + ".id_usuario_ra = ? ";
	
	public static final int DISPOSITIVO_USUARIO_POR_APP_PRODUTO_USA = 87722;
	public static final int COM_APP_PRODUTO = 87723;
	private static final String sPorIdAppProdutoUSelecao =
            DispositivoUsuarioContract.TABLE_NAME + ".id_app_produto_u = ? ";
	


	private ContentProvider mProvider = null;


	public void setContentProvider(ContentProvider valor) {
		mProvider = valor;
	}

	protected static final SQLiteQueryBuilder sQueryBuilder;
	static {
		sQueryBuilder = new SQLiteQueryBuilder();
		String tabelas = DispositivoUsuarioContract.TABLE_NAME;
		
		//tabelas += " inner join " + UsuarioContract.TABLE_NAME + " ";
		//tabelas += " on " + UsuarioContract.TABLE_NAME + "." + UsuarioContract.COLUNA_CHAVE + " = " + DispositivoUsuarioContract.TABLE_NAME + "." + DispositivoUsuarioContract.COLUNA_ID_USUARIO_RA; 
		
		//tabelas += " inner join " + AppProdutoContract.TABLE_NAME + " ";
		//tabelas += " on " + AppProdutoContract.TABLE_NAME + "." + AppProdutoContract.COLUNA_CHAVE + " = " + DispositivoUsuarioContract.TABLE_NAME + "." + DispositivoUsuarioContract.COLUNA_ID_APP_PRODUTO_U; 
		
		sQueryBuilder.setTables(tabelas);
	}
	private static final SQLiteQueryBuilder sQueryBuilderSinc;
	static {
		sQueryBuilderSinc = new SQLiteQueryBuilder();
		String tabelas = DispositivoUsuarioContract.TABLE_NAME_SINC;
		sQueryBuilderSinc.setTables(tabelas);
	}
	
	
	protected AplicacaoDbHelper mOpenHelper = null;
	
	public int getLinhas() {
		return qtdeLinhas;
	}
	
	public static void buildUriMatcher(UriMatcher matcher) {
		matcher.addURI(DispositivoUsuarioContract.getContentAuthority(), DispositivoUsuarioContract.PATH, DISPOSITIVO_USUARIO);
		matcher.addURI(DispositivoUsuarioContract.getContentAuthority(), DispositivoUsuarioContract.PATH + "/Sinc" , DISPOSITIVO_USUARIO_SINC);
		matcher.addURI(DispositivoUsuarioContract.getContentAuthority(), DispositivoUsuarioContract.PATH + "/#"    , DISPOSITIVO_USUARIO_ID);
	
		matcher.addURI(DispositivoUsuarioContract.getContentAuthority(), DispositivoUsuarioContract.PATH + "/#/" + DispositivoUsuarioContract.COM_COMPLEMENTO + "/*" , DISPOSITIVO_USUARIO_ID_E_COMPLEMENTO);
		matcher.addURI(DispositivoUsuarioContract.getContentAuthority(), DispositivoUsuarioContract.PATH + "/" + DispositivoUsuarioContract.COM_COMPLEMENTO + "/*" , DISPOSITIVO_USUARIO_E_COMPLEMENTO);
		matcher.addURI(DispositivoUsuarioContract.getContentAuthority(), DispositivoUsuarioContract.PATH + "/" + DispositivoUsuarioContract.COM_RETIRADA + "/*" , DISPOSITIVO_USUARIO_E_RETIRADA);
		
		
		//matcher.addURI(AplicacaoContract.CONTENT_AUTHORITY, DispositivoUsuarioContract.PATH + "/operacao/*" , DISPOSITIVO_USUARIO_OPERACAO);
		
		matcher.addURI(DispositivoUsuarioContract.getContentAuthority(), DispositivoUsuarioContract.PATH + "/#/" + UsuarioContract.PATH, DISPOSITIVO_USUARIO_POR_USUARIO_REFERENTEA);
		matcher.addURI(DispositivoUsuarioContract.getContentAuthority(), DispositivoUsuarioContract.PATH + "/ComUsuarioReferenteA/" , COM_USUARIO);
		
		matcher.addURI(DispositivoUsuarioContract.getContentAuthority(), DispositivoUsuarioContract.PATH + "/#/" + AppProdutoContract.PATH, DISPOSITIVO_USUARIO_POR_APP_PRODUTO_USA);
		matcher.addURI(DispositivoUsuarioContract.getContentAuthority(), DispositivoUsuarioContract.PATH + "/ComAppProdutoUsa/" , COM_APP_PRODUTO);
		
		
		
		
		// Deletes
		matcher.addURI(DispositivoUsuarioContract.getContentAuthority(), DispositivoUsuarioContract.PATH + "/DeleteAllSinc" , 	DISPOSITIVO_USUARIO_DELETE_ALL_SINC);
		matcher.addURI(DispositivoUsuarioContract.getContentAuthority(), DispositivoUsuarioContract.PATH + "/DeleteAllRecreate" , DISPOSITIVO_USUARIO_DELETE_RECREATE);
		matcher.addURI(DispositivoUsuarioContract.getContentAuthority(), DispositivoUsuarioContract.PATH + "/DeleteSinc/#" , 		DISPOSITIVO_USUARIO_DELETE_SINC);
	}
	
	
	
	
	public void setAplicacaoDbHelper(AplicacaoDbHelper db) {
		mOpenHelper = db;
	}
	
	public Cursor query(UriMatcher sUriMatcher, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Query Uri:" + uri.toString());
		Cursor retCursor = null;
		switch (sUriMatcher.match(uri)) {
            case DISPOSITIVO_USUARIO:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: DISPOSITIVO_USUARIO");
                retCursor = query(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case DISPOSITIVO_USUARIO_SINC:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: DISPOSITIVO_USUARIO_SINC");
                retCursor = querySinc(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case DISPOSITIVO_USUARIO_ID:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: DISPOSITIVO_USUARIO_ID");
            	String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorChaveSel, args, sortOrder);
                break;
            }
            case DISPOSITIVO_USUARIO_ID_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: DISPOSITIVO_USUARIO_ID_E_COMPLEMENTO");
				String id = uri.getPathSegments().get(1);	
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri) +
						" where " + DispositivoUsuarioContract.COLUNA_CHAVE + " = " + id;
				retCursor = queryRaw(sql);
				break;
			}
			case DISPOSITIVO_USUARIO_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: DISPOSITIVO_USUARIO_E_COMPLEMENTO");
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case DISPOSITIVO_USUARIO_E_RETIRADA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: DISPOSITIVO_USUARIO_E_RETIRADA");
				String sql = "select " +  DispositivoUsuarioContract.camposOrdenados() +
						" from " + DispositivoUsuarioContract.TABLE_NAME +
						sqlWhere(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case DISPOSITIVO_USUARIO_POR_APP_PRODUTO_USA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: DISPOSITIVO_USUARIO_POR_APP_PRODUTO_USA");
	            String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorIdAppProdutoUSelecao, args, sortOrder);
                break;
            }
            case COM_APP_PRODUTO:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: COM_APP_PRODUTO");
            	String sql = "select " + DispositivoUsuarioContract.camposOrdenados() + " , " +
						AppProdutoContract.camposOrdenados() +
						" from " + DispositivoUsuarioContract.TABLE_NAME +
						DispositivoUsuarioContract.innerJoinAppProduto_Usa() +
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
		String sql = DispositivoUsuarioContract.camposOrdenados();
		List<String> segmentos = uri.getPathSegments();
		// ComChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComAppProdutoUsa",uri.getPathSegments())) {
			sql += "," +  AppProdutoContract.camposOrdenados();
		}
	
	
	// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		
		
		
		return sql;
	}
	private String sqlFrom(Uri uri) {
		String sql = DispositivoUsuarioContract.TABLE_NAME;
		List<String> segmentos = uri.getPathSegments();
		//if (existeItem("ComEpisodioReferenteA",uri.getPathSegments())) {
		//	sql += " " +  EpisodioUsuarioContract.innerJoinEpisodio_ReferenteA();
		//}
		
		
				// ComChaveEstrangeira
  	
		if (existeItem("ComAppProdutoUsa",uri.getPathSegments())) {
			sql += " " +  DispositivoUsuarioContract.outerJoinAppProduto_Usa();
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
		DCLog.d(DCLog.DATABASE_ADM,this,sql);
		final SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		return db.rawQuery(sql,null);
	}
	
	public String getType(UriMatcher sUriMatcher, Uri uri) {
		switch (sUriMatcher.match(uri)) {
            case DISPOSITIVO_USUARIO:
            {
                return DispositivoUsuarioContract.getContentType();
            }
            case DISPOSITIVO_USUARIO_ID:
            {
            	return DispositivoUsuarioContract.getContentItemType();
            }
			case DISPOSITIVO_USUARIO_POR_USUARIO_REFERENTEA:
            {
	            return DispositivoUsuarioContract.getContentType();
            }
		
			case DISPOSITIVO_USUARIO_POR_APP_PRODUTO_USA:
            {
	            return DispositivoUsuarioContract.getContentType();
            }
		
		}	
		return null;
	}
	
	public Uri insert(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Uri returnUri;
		long idNovo = getMaxId(db)+1;
		values.put(DispositivoUsuarioContract.COLUNA_CHAVE, idNovo);
		long _id = db.insert(DispositivoUsuarioContract.TABLE_NAME, null, values);
		if (_id > 0) {
			//DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + DispositivoUsuarioContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
			DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + values.toString() + " (id:" + _id + ")");
			returnUri = DispositivoUsuarioContract.buildDispositivoUsuarioUri(idNovo);
			values.put(DispositivoUsuarioContract.COLUNA_OPERACAO_SINC,"I");
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
			case DISPOSITIVO_USUARIO_DELETE_SINC: {
				String id = uri.getPathSegments().get(2);
				Cursor cursor = db.query(DispositivoUsuarioContract.TABLE_NAME,null,DispositivoUsuarioContract.COLUNA_CHAVE + " = ? ",new String[]{id},null,null,null);
				if (cursor.moveToFirst()) {
					ContentValues valores = new ContentValues();
					DatabaseUtils.cursorRowToContentValues(cursor, valores);
					linhaDelete = db.delete(DispositivoUsuarioContract.TABLE_NAME, DispositivoUsuarioContract.COLUNA_CHAVE + " = ? ", new String[]{id});
					DCLog.d(DCLog.DATABASE_CRUD,this,"delete " + DispositivoUsuarioContract.TABLE_NAME + "(id:" + id + ")");
					valores.put(DispositivoUsuarioContract.COLUNA_OPERACAO_SINC, "D");
					insereSinc(db,valores);
				}
				notificaUriRelacionadas();
				mProvider.getContext().getContentResolver().notifyChange(DispositivoUsuarioContract.buildAll(), null);
				break;
			}
			case DISPOSITIVO_USUARIO_DELETE_ALL_SINC: {
				linhaDelete = db.delete(DispositivoUsuarioContract.TABLE_NAME_SINC, null, null);
				break;
			}
			case DISPOSITIVO_USUARIO_DELETE_RECREATE: {
				linhaDelete = db.delete(DispositivoUsuarioContract.TABLE_NAME, null, null);
				break;
			}
		}
		return true;
	}
	
	
	public boolean update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		linhaUpdate = db.update(DispositivoUsuarioContract.TABLE_NAME, values, selection, selectionArgs);
		return true;
	}
	public boolean update(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		String selection = DispositivoUsuarioContract.COLUNA_CHAVE + " = ? ";
		String dados[] = {values.get(DispositivoUsuarioContract.COLUNA_CHAVE).toString()};
		linhaUpdate = db.update(DispositivoUsuarioContract.TABLE_NAME, values, selection, dados);
		values.put(DispositivoUsuarioContract.COLUNA_OPERACAO_SINC,"A");
		insereSinc(db,values);
		return true;
	}
	
	private void insereSinc(SQLiteDatabase db, ContentValues values) {
		db.insert(DispositivoUsuarioContract.TABLE_NAME_SINC, null, values);
	}
	
	// Notificar todas as uris de entidades que possuem chave estrangeira dessa entidade.
	private void notificaUriRelacionadas() {
		// ComChaveEstrangeira
  	
		mProvider.getContext().getContentResolver().notifyChange(AppProdutoContract.buildAllComDispositivoUsuarioUsadoPor(), null);
		mProvider.getContext().getContentResolver().notifyChange(AppProdutoContract.buildAllSemDispositivoUsuarioUsadoPor(), null);
	
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
				String[] args = {(String)value.get(DispositivoUsuarioContract.COLUNA_CHAVE)};
				Cursor retCursor = query(null, sPorChaveSel, args, null);
				if (retCursor.moveToFirst()) {
						db.update(DispositivoUsuarioContract.TABLE_NAME,value,sPorChaveSel,args);
						//DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk " + DispositivoUsuarioContract.TABLE_NAME + "  " + values.toString());
						DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk id:" + value.get(DispositivoUsuarioContract.COLUNA_CHAVE) + ")" + values.toString());
				} else {
					long _id = db.insert(DispositivoUsuarioContract.TABLE_NAME, null, value);
					if (_id != -1) {
						//DCLog.d(DCLog.DATABASE_CRUD,this,"insert (bulk)" + DispositivoUsuarioContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
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
            	long _id = db.insert(DispositivoUsuarioContract.TABLE_NAME, null, value);
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
		String sql = "select max(" + DispositivoUsuarioContract.COLUNA_CHAVE + ") from " + DispositivoUsuarioContract.TABLE_NAME;
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