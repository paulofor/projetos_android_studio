
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

public abstract class CategoriaProdutoProdutoProvider {

	private int qtdeLinhas = 0;
	
	public static final int CATEGORIA_PRODUTO_PRODUTO = 67800;
	public static final int CATEGORIA_PRODUTO_PRODUTO_ID = 67801;
	public static final int CATEGORIA_PRODUTO_PRODUTO_SINC = 67803;
	public static final int CATEGORIA_PRODUTO_PRODUTO_E_COMPLEMENTO = 67804;
	public static final int CATEGORIA_PRODUTO_PRODUTO_ID_E_COMPLEMENTO = 67805;
	//public static final int CATEGORIA_PRODUTO_PRODUTO_OPERACAO = 67802;
	
	// deletes
	public static final int CATEGORIA_PRODUTO_PRODUTO_DELETE_ALL_SINC = 67806;
	public static final int CATEGORIA_PRODUTO_PRODUTO_DELETE_RECREATE = 67807;
	public static final int CATEGORIA_PRODUTO_PRODUTO_DELETE_SINC = 67808;
	public static final int CATEGORIA_PRODUTO_PRODUTO_E_RETIRADA = 67809;
	
	private static final String sPorChaveSel = CategoriaProdutoProdutoContract.TABLE_NAME + "." + CategoriaProdutoProdutoContract.COLUNA_CHAVE + " = ? ";
	
	
	
	public static final int CATEGORIA_PRODUTO_PRODUTO_POR_CATEGORIA_PRODUTO_REFERENTEA = 67820;
	public static final int COM_CATEGORIA_PRODUTO = 67821;
	private static final String sPorIdCategoriaProdutoRaSelecao =
            CategoriaProdutoProdutoContract.TABLE_NAME + ".id_categoria_produto_ra = ? ";
	
	public static final int CATEGORIA_PRODUTO_PRODUTO_POR_PRODUTO_REFERENTEA = 67822;
	public static final int COM_PRODUTO = 67823;
	private static final String sPorIdProdutoRaSelecao =
            CategoriaProdutoProdutoContract.TABLE_NAME + ".id_produto_ra = ? ";
	


	private ContentProvider mProvider = null;


	public void setContentProvider(ContentProvider valor) {
		mProvider = valor;
	}

	protected static final SQLiteQueryBuilder sQueryBuilder;
	static {
		sQueryBuilder = new SQLiteQueryBuilder();
		String tabelas = CategoriaProdutoProdutoContract.TABLE_NAME;
		
		//tabelas += " inner join " + CategoriaProdutoContract.TABLE_NAME + " ";
		//tabelas += " on " + CategoriaProdutoContract.TABLE_NAME + "." + CategoriaProdutoContract.COLUNA_CHAVE + " = " + CategoriaProdutoProdutoContract.TABLE_NAME + "." + CategoriaProdutoProdutoContract.COLUNA_ID_CATEGORIA_PRODUTO_RA; 
		
		//tabelas += " inner join " + ProdutoContract.TABLE_NAME + " ";
		//tabelas += " on " + ProdutoContract.TABLE_NAME + "." + ProdutoContract.COLUNA_CHAVE + " = " + CategoriaProdutoProdutoContract.TABLE_NAME + "." + CategoriaProdutoProdutoContract.COLUNA_ID_PRODUTO_RA; 
		
		sQueryBuilder.setTables(tabelas);
	}
	private static final SQLiteQueryBuilder sQueryBuilderSinc;
	static {
		sQueryBuilderSinc = new SQLiteQueryBuilder();
		String tabelas = CategoriaProdutoProdutoContract.TABLE_NAME_SINC;
		sQueryBuilderSinc.setTables(tabelas);
	}
	
	
	protected AplicacaoDbHelper mOpenHelper = null;
	
	public int getLinhas() {
		return qtdeLinhas;
	}
	
	public static void buildUriMatcher(UriMatcher matcher) {
		matcher.addURI(CategoriaProdutoProdutoContract.getContentAuthority(), CategoriaProdutoProdutoContract.PATH, CATEGORIA_PRODUTO_PRODUTO);
		matcher.addURI(CategoriaProdutoProdutoContract.getContentAuthority(), CategoriaProdutoProdutoContract.PATH + "/Sinc" , CATEGORIA_PRODUTO_PRODUTO_SINC);
		matcher.addURI(CategoriaProdutoProdutoContract.getContentAuthority(), CategoriaProdutoProdutoContract.PATH + "/#"    , CATEGORIA_PRODUTO_PRODUTO_ID);
	
		matcher.addURI(CategoriaProdutoProdutoContract.getContentAuthority(), CategoriaProdutoProdutoContract.PATH + "/#/" + CategoriaProdutoProdutoContract.COM_COMPLEMENTO + "/*" , CATEGORIA_PRODUTO_PRODUTO_ID_E_COMPLEMENTO);
		matcher.addURI(CategoriaProdutoProdutoContract.getContentAuthority(), CategoriaProdutoProdutoContract.PATH + "/" + CategoriaProdutoProdutoContract.COM_COMPLEMENTO + "/*" , CATEGORIA_PRODUTO_PRODUTO_E_COMPLEMENTO);
		matcher.addURI(CategoriaProdutoProdutoContract.getContentAuthority(), CategoriaProdutoProdutoContract.PATH + "/" + CategoriaProdutoProdutoContract.COM_RETIRADA + "/*" , CATEGORIA_PRODUTO_PRODUTO_E_RETIRADA);
		
		
		//matcher.addURI(AplicacaoContract.CONTENT_AUTHORITY, CategoriaProdutoProdutoContract.PATH + "/operacao/*" , CATEGORIA_PRODUTO_PRODUTO_OPERACAO);
		
		matcher.addURI(CategoriaProdutoProdutoContract.getContentAuthority(), CategoriaProdutoProdutoContract.PATH + "/#/" + CategoriaProdutoContract.PATH, CATEGORIA_PRODUTO_PRODUTO_POR_CATEGORIA_PRODUTO_REFERENTEA);
		matcher.addURI(CategoriaProdutoProdutoContract.getContentAuthority(), CategoriaProdutoProdutoContract.PATH + "/ComCategoriaProdutoReferenteA/" , COM_CATEGORIA_PRODUTO);
		
		matcher.addURI(CategoriaProdutoProdutoContract.getContentAuthority(), CategoriaProdutoProdutoContract.PATH + "/#/" + ProdutoContract.PATH, CATEGORIA_PRODUTO_PRODUTO_POR_PRODUTO_REFERENTEA);
		matcher.addURI(CategoriaProdutoProdutoContract.getContentAuthority(), CategoriaProdutoProdutoContract.PATH + "/ComProdutoReferenteA/" , COM_PRODUTO);
		
		
		
		
		// Deletes
		matcher.addURI(CategoriaProdutoProdutoContract.getContentAuthority(), CategoriaProdutoProdutoContract.PATH + "/DeleteAllSinc" , 	CATEGORIA_PRODUTO_PRODUTO_DELETE_ALL_SINC);
		matcher.addURI(CategoriaProdutoProdutoContract.getContentAuthority(), CategoriaProdutoProdutoContract.PATH + "/DeleteAllRecreate" , CATEGORIA_PRODUTO_PRODUTO_DELETE_RECREATE);
		matcher.addURI(CategoriaProdutoProdutoContract.getContentAuthority(), CategoriaProdutoProdutoContract.PATH + "/DeleteSinc/#" , 		CATEGORIA_PRODUTO_PRODUTO_DELETE_SINC);
	}
	
	
	
	
	public void setAplicacaoDbHelper(AplicacaoDbHelper db) {
		mOpenHelper = db;
	}
	
	public Cursor query(UriMatcher sUriMatcher, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Query Uri:" + uri.toString());
		Cursor retCursor = null;
		switch (sUriMatcher.match(uri)) {
            case CATEGORIA_PRODUTO_PRODUTO:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CATEGORIA_PRODUTO_PRODUTO");
                retCursor = query(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case CATEGORIA_PRODUTO_PRODUTO_SINC:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CATEGORIA_PRODUTO_PRODUTO_SINC");
                retCursor = querySinc(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case CATEGORIA_PRODUTO_PRODUTO_ID:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CATEGORIA_PRODUTO_PRODUTO_ID");
            	String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorChaveSel, args, sortOrder);
                break;
            }
            case CATEGORIA_PRODUTO_PRODUTO_ID_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CATEGORIA_PRODUTO_PRODUTO_ID_E_COMPLEMENTO");
				String id = uri.getPathSegments().get(1);	
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri) +
						" where " + CategoriaProdutoProdutoContract.COLUNA_CHAVE + " = " + id;
				retCursor = queryRaw(sql);
				break;
			}
			case CATEGORIA_PRODUTO_PRODUTO_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CATEGORIA_PRODUTO_PRODUTO_E_COMPLEMENTO");
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case CATEGORIA_PRODUTO_PRODUTO_E_RETIRADA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CATEGORIA_PRODUTO_PRODUTO_E_RETIRADA");
				String sql = "select " +  CategoriaProdutoProdutoContract.camposOrdenados() +
						" from " + CategoriaProdutoProdutoContract.TABLE_NAME +
						sqlWhere(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case CATEGORIA_PRODUTO_PRODUTO_POR_CATEGORIA_PRODUTO_REFERENTEA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CATEGORIA_PRODUTO_PRODUTO_POR_CATEGORIA_PRODUTO_REFERENTEA");
	            String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorIdCategoriaProdutoRaSelecao, args, sortOrder);
                break;
            }
            case COM_CATEGORIA_PRODUTO:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: COM_CATEGORIA_PRODUTO");
            	String sql = "select " + CategoriaProdutoProdutoContract.camposOrdenados() + " , " +
						CategoriaProdutoContract.camposOrdenados() +
						" from " + CategoriaProdutoProdutoContract.TABLE_NAME +
						CategoriaProdutoProdutoContract.innerJoinCategoriaProduto_ReferenteA() +
						getOrderBy();
                retCursor = queryRaw(sql);
				break;
            }
		
			case CATEGORIA_PRODUTO_PRODUTO_POR_PRODUTO_REFERENTEA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CATEGORIA_PRODUTO_PRODUTO_POR_PRODUTO_REFERENTEA");
	            String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorIdProdutoRaSelecao, args, sortOrder);
                break;
            }
            case COM_PRODUTO:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: COM_PRODUTO");
            	String sql = "select " + CategoriaProdutoProdutoContract.camposOrdenados() + " , " +
						ProdutoContract.camposOrdenados() +
						" from " + CategoriaProdutoProdutoContract.TABLE_NAME +
						CategoriaProdutoProdutoContract.innerJoinProduto_ReferenteA() +
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
		String sql = CategoriaProdutoProdutoContract.camposOrdenados();
		List<String> segmentos = uri.getPathSegments();
		// ComChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComCategoriaProdutoReferenteA",uri.getPathSegments())) {
			sql += "," +  CategoriaProdutoContract.camposOrdenados();
		}
	
		if (existeItem("ComProdutoReferenteA",uri.getPathSegments())) {
			sql += "," +  ProdutoContract.camposOrdenados();
		}
	
	
	// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		
		
		
		return sql;
	}
	private String sqlFrom(Uri uri) {
		String sql = CategoriaProdutoProdutoContract.TABLE_NAME;
		List<String> segmentos = uri.getPathSegments();
		//if (existeItem("ComEpisodioReferenteA",uri.getPathSegments())) {
		//	sql += " " +  EpisodioUsuarioContract.innerJoinEpisodio_ReferenteA();
		//}
		
		
				// ComChaveEstrangeira
  	
		if (existeItem("ComCategoriaProdutoReferenteA",uri.getPathSegments())) {
			sql += " " +  CategoriaProdutoProdutoContract.outerJoinCategoriaProduto_ReferenteA();
		}
	
		if (existeItem("ComProdutoReferenteA",uri.getPathSegments())) {
			sql += " " +  CategoriaProdutoProdutoContract.outerJoinProduto_ReferenteA();
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
            case CATEGORIA_PRODUTO_PRODUTO:
            {
                return CategoriaProdutoProdutoContract.getContentType();
            }
            case CATEGORIA_PRODUTO_PRODUTO_ID:
            {
            	return CategoriaProdutoProdutoContract.getContentItemType();
            }
			case CATEGORIA_PRODUTO_PRODUTO_POR_CATEGORIA_PRODUTO_REFERENTEA:
            {
	            return CategoriaProdutoProdutoContract.getContentType();
            }
		
			case CATEGORIA_PRODUTO_PRODUTO_POR_PRODUTO_REFERENTEA:
            {
	            return CategoriaProdutoProdutoContract.getContentType();
            }
		
		}	
		return null;
	}
	
	public Uri insert(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Uri returnUri;
		long idNovo = getMaxId(db)+1;
		values.put(CategoriaProdutoProdutoContract.COLUNA_CHAVE, idNovo);
		long _id = db.insert(CategoriaProdutoProdutoContract.TABLE_NAME, null, values);
		if (_id > 0) {
			//DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + CategoriaProdutoProdutoContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
			DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + values.toString() + " (id:" + _id + ")");
			returnUri = CategoriaProdutoProdutoContract.buildCategoriaProdutoProdutoUri(idNovo);
			values.put(CategoriaProdutoProdutoContract.COLUNA_OPERACAO_SINC,"I");
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
			case CATEGORIA_PRODUTO_PRODUTO_DELETE_SINC: {
				String id = uri.getPathSegments().get(2);
				Cursor cursor = db.query(CategoriaProdutoProdutoContract.TABLE_NAME,null,CategoriaProdutoProdutoContract.COLUNA_CHAVE + " = ? ",new String[]{id},null,null,null);
				if (cursor.moveToFirst()) {
					ContentValues valores = new ContentValues();
					DatabaseUtils.cursorRowToContentValues(cursor, valores);
					linhaDelete = db.delete(CategoriaProdutoProdutoContract.TABLE_NAME, CategoriaProdutoProdutoContract.COLUNA_CHAVE + " = ? ", new String[]{id});
					DCLog.d(DCLog.DATABASE_CRUD,this,"delete " + CategoriaProdutoProdutoContract.TABLE_NAME + "(id:" + id + ")");
					valores.put(CategoriaProdutoProdutoContract.COLUNA_OPERACAO_SINC, "D");
					insereSinc(db,valores);
				}
				notificaUriRelacionadas();
				mProvider.getContext().getContentResolver().notifyChange(CategoriaProdutoProdutoContract.buildAll(), null);
				break;
			}
			case CATEGORIA_PRODUTO_PRODUTO_DELETE_ALL_SINC: {
				linhaDelete = db.delete(CategoriaProdutoProdutoContract.TABLE_NAME_SINC, null, null);
				break;
			}
			case CATEGORIA_PRODUTO_PRODUTO_DELETE_RECREATE: {
				linhaDelete = db.delete(CategoriaProdutoProdutoContract.TABLE_NAME, null, null);
				break;
			}
		}
		return true;
	}
	
	
	public boolean update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		DCLog.d(DCLog.DATABASE_CRUD,this,"update2 " + values.toString() );
		linhaUpdate = db.update(CategoriaProdutoProdutoContract.TABLE_NAME, values, selection, selectionArgs);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	public boolean update(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		String selection = CategoriaProdutoProdutoContract.COLUNA_CHAVE + " = ? ";
		String dados[] = {values.get(CategoriaProdutoProdutoContract.COLUNA_CHAVE).toString()};
		DCLog.d(DCLog.DATABASE_CRUD,this,"update1 " + values.toString() );
		linhaUpdate = db.update(CategoriaProdutoProdutoContract.TABLE_NAME, values, selection, dados);
		values.put(CategoriaProdutoProdutoContract.COLUNA_OPERACAO_SINC,"A");
		insereSinc(db,values);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	
	private void insereSinc(SQLiteDatabase db, ContentValues values) {
		db.insert(CategoriaProdutoProdutoContract.TABLE_NAME_SINC, null, values);
		notificaUriOperacoes();
	}
	
	protected abstract void notificaOutrasUri(ContentResolver resolver);
	
	
	// Notificar todas as uris de entidades que possuem chave estrangeira dessa entidade.
	private void notificaUriRelacionadas() {
		// ComChaveEstrangeira
  	
		mProvider.getContext().getContentResolver().notifyChange(CategoriaProdutoContract.buildAllComCategoriaProdutoProdutoPossui(), null);
		mProvider.getContext().getContentResolver().notifyChange(CategoriaProdutoContract.buildAllSemCategoriaProdutoProdutoPossui(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(ProdutoContract.buildAllComCategoriaProdutoProdutoPossui(), null);
		mProvider.getContext().getContentResolver().notifyChange(ProdutoContract.buildAllSemCategoriaProdutoProdutoPossui(), null);
	
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
				String[] args = {(String)value.get(CategoriaProdutoProdutoContract.COLUNA_CHAVE)};
				Cursor retCursor = query(null, sPorChaveSel, args, null);
				if (retCursor.moveToFirst()) {
						db.update(CategoriaProdutoProdutoContract.TABLE_NAME,value,sPorChaveSel,args);
						//DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk " + CategoriaProdutoProdutoContract.TABLE_NAME + "  " + values.toString());
						DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk id:" + value.get(CategoriaProdutoProdutoContract.COLUNA_CHAVE) + ")" + values.toString());
				} else {
					long _id = db.insert(CategoriaProdutoProdutoContract.TABLE_NAME, null, value);
					if (_id != -1) {
						//DCLog.d(DCLog.DATABASE_CRUD,this,"insert (bulk)" + CategoriaProdutoProdutoContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
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
            	long _id = db.insert(CategoriaProdutoProdutoContract.TABLE_NAME, null, value);
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
		String sql = "select max(" + CategoriaProdutoProdutoContract.COLUNA_CHAVE + ") from " + CategoriaProdutoProdutoContract.TABLE_NAME;
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