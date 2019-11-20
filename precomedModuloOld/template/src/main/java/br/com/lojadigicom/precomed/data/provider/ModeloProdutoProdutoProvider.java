
package  br.com.lojadigicom.precomed.data.provider;


import br.com.lojadigicom.precomed.data.contract.*;
import br.com.lojadigicom.precomed.data.helper.AplicacaoDbHelper;
import android.content.ContentValues;
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

public abstract class ModeloProdutoProdutoProvider {

	private int qtdeLinhas = 0;
	
	public static final int MODELO_PRODUTO_PRODUTO = 78800;
	public static final int MODELO_PRODUTO_PRODUTO_ID = 78801;
	public static final int MODELO_PRODUTO_PRODUTO_SINC = 78803;
	public static final int MODELO_PRODUTO_PRODUTO_E_COMPLEMENTO = 78804;
	public static final int MODELO_PRODUTO_PRODUTO_ID_E_COMPLEMENTO = 78805;
	//public static final int MODELO_PRODUTO_PRODUTO_OPERACAO = 78802;
	
	// deletes
	public static final int MODELO_PRODUTO_PRODUTO_DELETE_ALL_SINC = 78806;
	public static final int MODELO_PRODUTO_PRODUTO_DELETE_RECREATE = 78807;
	public static final int MODELO_PRODUTO_PRODUTO_DELETE_SINC = 78808;
	public static final int MODELO_PRODUTO_PRODUTO_E_RETIRADA = 78809;
	
	private static final String sPorChaveSel = ModeloProdutoProdutoContract.TABLE_NAME + "." + ModeloProdutoProdutoContract.COLUNA_CHAVE + " = ? ";
	
	
	
	public static final int MODELO_PRODUTO_PRODUTO_POR_MODELO_PRODUTO_REFERENTEA = 78820;
	public static final int COM_MODELO_PRODUTO = 78821;
	private static final String sPorIdModeloProdutoRaSelecao =
            ModeloProdutoProdutoContract.TABLE_NAME + ".id_modelo_produto_ra = ? ";
	
	public static final int MODELO_PRODUTO_PRODUTO_POR_PRODUTO_REFERENTEA = 78822;
	public static final int COM_PRODUTO = 78823;
	private static final String sPorIdProdutoRaSelecao =
            ModeloProdutoProdutoContract.TABLE_NAME + ".id_produto_ra = ? ";
	


	private ContentProvider mProvider = null;


	public void setContentProvider(ContentProvider valor) {
		mProvider = valor;
	}

	protected static final SQLiteQueryBuilder sQueryBuilder;
	static {
		sQueryBuilder = new SQLiteQueryBuilder();
		String tabelas = ModeloProdutoProdutoContract.TABLE_NAME;
		
		//tabelas += " inner join " + ModeloProdutoContract.TABLE_NAME + " ";
		//tabelas += " on " + ModeloProdutoContract.TABLE_NAME + "." + ModeloProdutoContract.COLUNA_CHAVE + " = " + ModeloProdutoProdutoContract.TABLE_NAME + "." + ModeloProdutoProdutoContract.COLUNA_ID_MODELO_PRODUTO_RA; 
		
		//tabelas += " inner join " + ProdutoContract.TABLE_NAME + " ";
		//tabelas += " on " + ProdutoContract.TABLE_NAME + "." + ProdutoContract.COLUNA_CHAVE + " = " + ModeloProdutoProdutoContract.TABLE_NAME + "." + ModeloProdutoProdutoContract.COLUNA_ID_PRODUTO_RA; 
		
		sQueryBuilder.setTables(tabelas);
	}
	private static final SQLiteQueryBuilder sQueryBuilderSinc;
	static {
		sQueryBuilderSinc = new SQLiteQueryBuilder();
		String tabelas = ModeloProdutoProdutoContract.TABLE_NAME_SINC;
		sQueryBuilderSinc.setTables(tabelas);
	}
	
	
	protected AplicacaoDbHelper mOpenHelper = null;
	
	public int getLinhas() {
		return qtdeLinhas;
	}
	
	public static void buildUriMatcher(UriMatcher matcher) {
		matcher.addURI(ModeloProdutoProdutoContract.getContentAuthority(), ModeloProdutoProdutoContract.PATH, MODELO_PRODUTO_PRODUTO);
		matcher.addURI(ModeloProdutoProdutoContract.getContentAuthority(), ModeloProdutoProdutoContract.PATH + "/Sinc" , MODELO_PRODUTO_PRODUTO_SINC);
		matcher.addURI(ModeloProdutoProdutoContract.getContentAuthority(), ModeloProdutoProdutoContract.PATH + "/#"    , MODELO_PRODUTO_PRODUTO_ID);
	
		matcher.addURI(ModeloProdutoProdutoContract.getContentAuthority(), ModeloProdutoProdutoContract.PATH + "/#/" + ModeloProdutoProdutoContract.COM_COMPLEMENTO + "/*" , MODELO_PRODUTO_PRODUTO_ID_E_COMPLEMENTO);
		matcher.addURI(ModeloProdutoProdutoContract.getContentAuthority(), ModeloProdutoProdutoContract.PATH + "/" + ModeloProdutoProdutoContract.COM_COMPLEMENTO + "/*" , MODELO_PRODUTO_PRODUTO_E_COMPLEMENTO);
		matcher.addURI(ModeloProdutoProdutoContract.getContentAuthority(), ModeloProdutoProdutoContract.PATH + "/" + ModeloProdutoProdutoContract.COM_RETIRADA + "/*" , MODELO_PRODUTO_PRODUTO_E_RETIRADA);
		
		
		//matcher.addURI(AplicacaoContract.CONTENT_AUTHORITY, ModeloProdutoProdutoContract.PATH + "/operacao/*" , MODELO_PRODUTO_PRODUTO_OPERACAO);
		
		matcher.addURI(ModeloProdutoProdutoContract.getContentAuthority(), ModeloProdutoProdutoContract.PATH + "/#/" + ModeloProdutoContract.PATH, MODELO_PRODUTO_PRODUTO_POR_MODELO_PRODUTO_REFERENTEA);
		matcher.addURI(ModeloProdutoProdutoContract.getContentAuthority(), ModeloProdutoProdutoContract.PATH + "/ComModeloProdutoReferenteA/" , COM_MODELO_PRODUTO);
		
		matcher.addURI(ModeloProdutoProdutoContract.getContentAuthority(), ModeloProdutoProdutoContract.PATH + "/#/" + ProdutoContract.PATH, MODELO_PRODUTO_PRODUTO_POR_PRODUTO_REFERENTEA);
		matcher.addURI(ModeloProdutoProdutoContract.getContentAuthority(), ModeloProdutoProdutoContract.PATH + "/ComProdutoReferenteA/" , COM_PRODUTO);
		
		
		
		
		// Deletes
		matcher.addURI(ModeloProdutoProdutoContract.getContentAuthority(), ModeloProdutoProdutoContract.PATH + "/DeleteAllSinc" , 	MODELO_PRODUTO_PRODUTO_DELETE_ALL_SINC);
		matcher.addURI(ModeloProdutoProdutoContract.getContentAuthority(), ModeloProdutoProdutoContract.PATH + "/DeleteAllRecreate" , MODELO_PRODUTO_PRODUTO_DELETE_RECREATE);
		matcher.addURI(ModeloProdutoProdutoContract.getContentAuthority(), ModeloProdutoProdutoContract.PATH + "/DeleteSinc/#" , 		MODELO_PRODUTO_PRODUTO_DELETE_SINC);
	}
	
	
	
	
	public void setAplicacaoDbHelper(AplicacaoDbHelper db) {
		mOpenHelper = db;
	}
	
	public Cursor query(UriMatcher sUriMatcher, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Query Uri:" + uri.toString());
		Cursor retCursor = null;
		switch (sUriMatcher.match(uri)) {
            case MODELO_PRODUTO_PRODUTO:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: MODELO_PRODUTO_PRODUTO");
                retCursor = query(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case MODELO_PRODUTO_PRODUTO_SINC:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: MODELO_PRODUTO_PRODUTO_SINC");
                retCursor = querySinc(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case MODELO_PRODUTO_PRODUTO_ID:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: MODELO_PRODUTO_PRODUTO_ID");
            	String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorChaveSel, args, sortOrder);
                break;
            }
            case MODELO_PRODUTO_PRODUTO_ID_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: MODELO_PRODUTO_PRODUTO_ID_E_COMPLEMENTO");
				String id = uri.getPathSegments().get(1);	
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri) +
						" where " + ModeloProdutoProdutoContract.COLUNA_CHAVE + " = " + id;
				retCursor = queryRaw(sql);
				break;
			}
			case MODELO_PRODUTO_PRODUTO_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: MODELO_PRODUTO_PRODUTO_E_COMPLEMENTO");
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case MODELO_PRODUTO_PRODUTO_E_RETIRADA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: MODELO_PRODUTO_PRODUTO_E_RETIRADA");
				String sql = "select " +  ModeloProdutoProdutoContract.camposOrdenados() +
						" from " + ModeloProdutoProdutoContract.TABLE_NAME +
						sqlWhere(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case MODELO_PRODUTO_PRODUTO_POR_MODELO_PRODUTO_REFERENTEA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: MODELO_PRODUTO_PRODUTO_POR_MODELO_PRODUTO_REFERENTEA");
	            String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorIdModeloProdutoRaSelecao, args, sortOrder);
                break;
            }
            case COM_MODELO_PRODUTO:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: COM_MODELO_PRODUTO");
            	String sql = "select " + ModeloProdutoProdutoContract.camposOrdenados() + " , " +
						ModeloProdutoContract.camposOrdenados() +
						" from " + ModeloProdutoProdutoContract.TABLE_NAME +
						ModeloProdutoProdutoContract.innerJoinModeloProduto_ReferenteA() +
						getOrderBy();
                retCursor = queryRaw(sql);
				break;
            }
		
			case MODELO_PRODUTO_PRODUTO_POR_PRODUTO_REFERENTEA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: MODELO_PRODUTO_PRODUTO_POR_PRODUTO_REFERENTEA");
	            String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorIdProdutoRaSelecao, args, sortOrder);
                break;
            }
            case COM_PRODUTO:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: COM_PRODUTO");
            	String sql = "select " + ModeloProdutoProdutoContract.camposOrdenados() + " , " +
						ProdutoContract.camposOrdenados() +
						" from " + ModeloProdutoProdutoContract.TABLE_NAME +
						ModeloProdutoProdutoContract.innerJoinProduto_ReferenteA() +
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
		String sql = ModeloProdutoProdutoContract.camposOrdenados();
		List<String> segmentos = uri.getPathSegments();
		// ComChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComModeloProdutoReferenteA",uri.getPathSegments())) {
			sql += "," +  ModeloProdutoContract.camposOrdenados();
		}
	
		if (existeItem("ComProdutoReferenteA",uri.getPathSegments())) {
			sql += "," +  ProdutoContract.camposOrdenados();
		}
	
	
	// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		
		
		
		return sql;
	}
	private String sqlFrom(Uri uri) {
		String sql = ModeloProdutoProdutoContract.TABLE_NAME;
		List<String> segmentos = uri.getPathSegments();
		//if (existeItem("ComEpisodioReferenteA",uri.getPathSegments())) {
		//	sql += " " +  EpisodioUsuarioContract.innerJoinEpisodio_ReferenteA();
		//}
		
		
				// ComChaveEstrangeira
  	
		if (existeItem("ComModeloProdutoReferenteA",uri.getPathSegments())) {
			sql += " " +  ModeloProdutoProdutoContract.outerJoinModeloProduto_ReferenteA();
		}
	
		if (existeItem("ComProdutoReferenteA",uri.getPathSegments())) {
			sql += " " +  ModeloProdutoProdutoContract.outerJoinProduto_ReferenteA();
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
            case MODELO_PRODUTO_PRODUTO:
            {
                return ModeloProdutoProdutoContract.getContentType();
            }
            case MODELO_PRODUTO_PRODUTO_ID:
            {
            	return ModeloProdutoProdutoContract.getContentItemType();
            }
			case MODELO_PRODUTO_PRODUTO_POR_MODELO_PRODUTO_REFERENTEA:
            {
	            return ModeloProdutoProdutoContract.getContentType();
            }
		
			case MODELO_PRODUTO_PRODUTO_POR_PRODUTO_REFERENTEA:
            {
	            return ModeloProdutoProdutoContract.getContentType();
            }
		
		}	
		return null;
	}
	
	public Uri insert(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Uri returnUri;
		long idNovo = getMaxId(db)+1;
		values.put(ModeloProdutoProdutoContract.COLUNA_CHAVE, idNovo);
		long _id = db.insert(ModeloProdutoProdutoContract.TABLE_NAME, null, values);
		if (_id > 0) {
			//DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + ModeloProdutoProdutoContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
			DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + values.toString() + " (id:" + _id + ")");
			returnUri = ModeloProdutoProdutoContract.buildModeloProdutoProdutoUri(idNovo);
			values.put(ModeloProdutoProdutoContract.COLUNA_OPERACAO_SINC,"I");
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
			case MODELO_PRODUTO_PRODUTO_DELETE_SINC: {
				String id = uri.getPathSegments().get(2);
				Cursor cursor = db.query(ModeloProdutoProdutoContract.TABLE_NAME,null,ModeloProdutoProdutoContract.COLUNA_CHAVE + " = ? ",new String[]{id},null,null,null);
				if (cursor.moveToFirst()) {
					ContentValues valores = new ContentValues();
					DatabaseUtils.cursorRowToContentValues(cursor, valores);
					linhaDelete = db.delete(ModeloProdutoProdutoContract.TABLE_NAME, ModeloProdutoProdutoContract.COLUNA_CHAVE + " = ? ", new String[]{id});
					DCLog.d(DCLog.DATABASE_CRUD,this,"delete " + ModeloProdutoProdutoContract.TABLE_NAME + "(id:" + id + ")");
					valores.put(ModeloProdutoProdutoContract.COLUNA_OPERACAO_SINC, "D");
					insereSinc(db,valores);
				}
				notificaUriRelacionadas();
				mProvider.getContext().getContentResolver().notifyChange(ModeloProdutoProdutoContract.buildAll(), null);
				break;
			}
			case MODELO_PRODUTO_PRODUTO_DELETE_ALL_SINC: {
				linhaDelete = db.delete(ModeloProdutoProdutoContract.TABLE_NAME_SINC, null, null);
				break;
			}
			case MODELO_PRODUTO_PRODUTO_DELETE_RECREATE: {
				linhaDelete = db.delete(ModeloProdutoProdutoContract.TABLE_NAME, null, null);
				break;
			}
		}
		return true;
	}
	
	
	public boolean update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		linhaUpdate = db.update(ModeloProdutoProdutoContract.TABLE_NAME, values, selection, selectionArgs);
		return true;
	}
	public boolean update(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		String selection = ModeloProdutoProdutoContract.COLUNA_CHAVE + " = ? ";
		String dados[] = {values.get(ModeloProdutoProdutoContract.COLUNA_CHAVE).toString()};
		linhaUpdate = db.update(ModeloProdutoProdutoContract.TABLE_NAME, values, selection, dados);
		values.put(ModeloProdutoProdutoContract.COLUNA_OPERACAO_SINC,"A");
		insereSinc(db,values);
		return true;
	}
	
	private void insereSinc(SQLiteDatabase db, ContentValues values) {
		db.insert(ModeloProdutoProdutoContract.TABLE_NAME_SINC, null, values);
	}
	
	// Notificar todas as uris de entidades que possuem chave estrangeira dessa entidade.
	private void notificaUriRelacionadas() {
		// ComChaveEstrangeira
  	
		mProvider.getContext().getContentResolver().notifyChange(ModeloProdutoContract.buildAllComModeloProdutoProdutoReferenteA(), null);
		mProvider.getContext().getContentResolver().notifyChange(ModeloProdutoContract.buildAllSemModeloProdutoProdutoReferenteA(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(ProdutoContract.buildAllComModeloProdutoProdutoReferenteA(), null);
		mProvider.getContext().getContentResolver().notifyChange(ProdutoContract.buildAllSemModeloProdutoProdutoReferenteA(), null);
	
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
				String[] args = {(String)value.get(ModeloProdutoProdutoContract.COLUNA_CHAVE)};
				Cursor retCursor = query(null, sPorChaveSel, args, null);
				if (retCursor.moveToFirst()) {
						db.update(ModeloProdutoProdutoContract.TABLE_NAME,value,sPorChaveSel,args);
						//DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk " + ModeloProdutoProdutoContract.TABLE_NAME + "  " + values.toString());
						DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk id:" + value.get(ModeloProdutoProdutoContract.COLUNA_CHAVE) + ")" + values.toString());
				} else {
					long _id = db.insert(ModeloProdutoProdutoContract.TABLE_NAME, null, value);
					if (_id != -1) {
						//DCLog.d(DCLog.DATABASE_CRUD,this,"insert (bulk)" + ModeloProdutoProdutoContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
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
            	long _id = db.insert(ModeloProdutoProdutoContract.TABLE_NAME, null, value);
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
		String sql = "select max(" + ModeloProdutoProdutoContract.COLUNA_CHAVE + ") from " + ModeloProdutoProdutoContract.TABLE_NAME;
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