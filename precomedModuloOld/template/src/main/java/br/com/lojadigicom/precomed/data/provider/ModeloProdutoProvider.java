
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

public abstract class ModeloProdutoProvider {

	private int qtdeLinhas = 0;
	
	public static final int MODELO_PRODUTO = 78700;
	public static final int MODELO_PRODUTO_ID = 78701;
	public static final int MODELO_PRODUTO_SINC = 78703;
	public static final int MODELO_PRODUTO_E_COMPLEMENTO = 78704;
	public static final int MODELO_PRODUTO_ID_E_COMPLEMENTO = 78705;
	//public static final int MODELO_PRODUTO_OPERACAO = 78702;
	
	// deletes
	public static final int MODELO_PRODUTO_DELETE_ALL_SINC = 78706;
	public static final int MODELO_PRODUTO_DELETE_RECREATE = 78707;
	public static final int MODELO_PRODUTO_DELETE_SINC = 78708;
	public static final int MODELO_PRODUTO_E_RETIRADA = 78709;
	
	private static final String sPorChaveSel = ModeloProdutoContract.TABLE_NAME + "." + ModeloProdutoContract.COLUNA_CHAVE + " = ? ";
	
	
	


	private ContentProvider mProvider = null;


	public void setContentProvider(ContentProvider valor) {
		mProvider = valor;
	}

	protected static final SQLiteQueryBuilder sQueryBuilder;
	static {
		sQueryBuilder = new SQLiteQueryBuilder();
		String tabelas = ModeloProdutoContract.TABLE_NAME;
		
		sQueryBuilder.setTables(tabelas);
	}
	private static final SQLiteQueryBuilder sQueryBuilderSinc;
	static {
		sQueryBuilderSinc = new SQLiteQueryBuilder();
		String tabelas = ModeloProdutoContract.TABLE_NAME_SINC;
		sQueryBuilderSinc.setTables(tabelas);
	}
	
	
	protected AplicacaoDbHelper mOpenHelper = null;
	
	public int getLinhas() {
		return qtdeLinhas;
	}
	
	public static void buildUriMatcher(UriMatcher matcher) {
		matcher.addURI(ModeloProdutoContract.getContentAuthority(), ModeloProdutoContract.PATH, MODELO_PRODUTO);
		matcher.addURI(ModeloProdutoContract.getContentAuthority(), ModeloProdutoContract.PATH + "/Sinc" , MODELO_PRODUTO_SINC);
		matcher.addURI(ModeloProdutoContract.getContentAuthority(), ModeloProdutoContract.PATH + "/#"    , MODELO_PRODUTO_ID);
	
		matcher.addURI(ModeloProdutoContract.getContentAuthority(), ModeloProdutoContract.PATH + "/#/" + ModeloProdutoContract.COM_COMPLEMENTO + "/*" , MODELO_PRODUTO_ID_E_COMPLEMENTO);
		matcher.addURI(ModeloProdutoContract.getContentAuthority(), ModeloProdutoContract.PATH + "/" + ModeloProdutoContract.COM_COMPLEMENTO + "/*" , MODELO_PRODUTO_E_COMPLEMENTO);
		matcher.addURI(ModeloProdutoContract.getContentAuthority(), ModeloProdutoContract.PATH + "/" + ModeloProdutoContract.COM_RETIRADA + "/*" , MODELO_PRODUTO_E_RETIRADA);
		
		
		//matcher.addURI(AplicacaoContract.CONTENT_AUTHORITY, ModeloProdutoContract.PATH + "/operacao/*" , MODELO_PRODUTO_OPERACAO);
		
		
		
		
		// Deletes
		matcher.addURI(ModeloProdutoContract.getContentAuthority(), ModeloProdutoContract.PATH + "/DeleteAllSinc" , 	MODELO_PRODUTO_DELETE_ALL_SINC);
		matcher.addURI(ModeloProdutoContract.getContentAuthority(), ModeloProdutoContract.PATH + "/DeleteAllRecreate" , MODELO_PRODUTO_DELETE_RECREATE);
		matcher.addURI(ModeloProdutoContract.getContentAuthority(), ModeloProdutoContract.PATH + "/DeleteSinc/#" , 		MODELO_PRODUTO_DELETE_SINC);
	}
	
	
	
	
	public void setAplicacaoDbHelper(AplicacaoDbHelper db) {
		mOpenHelper = db;
	}
	
	public Cursor query(UriMatcher sUriMatcher, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Query Uri:" + uri.toString());
		Cursor retCursor = null;
		switch (sUriMatcher.match(uri)) {
            case MODELO_PRODUTO:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: MODELO_PRODUTO");
                retCursor = query(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case MODELO_PRODUTO_SINC:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: MODELO_PRODUTO_SINC");
                retCursor = querySinc(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case MODELO_PRODUTO_ID:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: MODELO_PRODUTO_ID");
            	String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorChaveSel, args, sortOrder);
                break;
            }
            case MODELO_PRODUTO_ID_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: MODELO_PRODUTO_ID_E_COMPLEMENTO");
				String id = uri.getPathSegments().get(1);	
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri) +
						" where " + ModeloProdutoContract.COLUNA_CHAVE + " = " + id;
				retCursor = queryRaw(sql);
				break;
			}
			case MODELO_PRODUTO_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: MODELO_PRODUTO_E_COMPLEMENTO");
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case MODELO_PRODUTO_E_RETIRADA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: MODELO_PRODUTO_E_RETIRADA");
				String sql = "select " +  ModeloProdutoContract.camposOrdenados() +
						" from " + ModeloProdutoContract.TABLE_NAME +
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
  	
		if (existeItem("SemModeloProdutoProdutoReferenteA",uri.getPathSegments())) {
			sql += ModeloProdutoContract.COLUNA_CHAVE + " not in (select " + 
					ModeloProdutoProdutoContract.COLUNA_ID_MODELO_PRODUTO_RA + " from " +
					ModeloProdutoProdutoContract.TABLE_NAME + ")";
		}
	
		if (existeItem("SemProdutoPesquisaViabiliza",uri.getPathSegments())) {
			sql += ModeloProdutoContract.COLUNA_CHAVE + " not in (select " + 
					ProdutoPesquisaContract.COLUNA_ID_MODELO_PRODUTO_RA + " from " +
					ProdutoPesquisaContract.TABLE_NAME + ")";
		}
	
		
		return sql;
	}
	
	private String sqlSelect(Uri uri) {
		String sql = ModeloProdutoContract.camposOrdenados();
		List<String> segmentos = uri.getPathSegments();
		// ComChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
	
	// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComModeloProdutoProdutoReferenteA",uri.getPathSegments())) {
			sql += "," +  ModeloProdutoProdutoContract.camposOrdenados();
		}
	
		if (existeItem("ComProdutoPesquisaViabiliza",uri.getPathSegments())) {
			sql += "," +  ProdutoPesquisaContract.camposOrdenados();
		}
	
		
		
		
		return sql;
	}
	private String sqlFrom(Uri uri) {
		String sql = ModeloProdutoContract.TABLE_NAME;
		List<String> segmentos = uri.getPathSegments();
		//if (existeItem("ComEpisodioReferenteA",uri.getPathSegments())) {
		//	sql += " " +  EpisodioUsuarioContract.innerJoinEpisodio_ReferenteA();
		//}
		
		
				// ComChaveEstrangeira
  	
	
	// SemChaveEstrangeira
  	
		if (existeItem("ComModeloProdutoProdutoReferenteA",uri.getPathSegments())) {
			sql += " " +  ModeloProdutoContract.outerJoinModeloProdutoProduto_ReferenteA();
		}
	
		if (existeItem("ComProdutoPesquisaViabiliza",uri.getPathSegments())) {
			sql += " " +  ModeloProdutoContract.outerJoinProdutoPesquisa_Viabiliza();
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
            case MODELO_PRODUTO:
            {
                return ModeloProdutoContract.getContentType();
            }
            case MODELO_PRODUTO_ID:
            {
            	return ModeloProdutoContract.getContentItemType();
            }
		}	
		return null;
	}
	
	public Uri insert(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Uri returnUri;
		long idNovo = getMaxId(db)+1;
		values.put(ModeloProdutoContract.COLUNA_CHAVE, idNovo);
		long _id = db.insert(ModeloProdutoContract.TABLE_NAME, null, values);
		if (_id > 0) {
			//DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + ModeloProdutoContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
			DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + values.toString() + " (id:" + _id + ")");
			returnUri = ModeloProdutoContract.buildModeloProdutoUri(idNovo);
			values.put(ModeloProdutoContract.COLUNA_OPERACAO_SINC,"I");
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
			case MODELO_PRODUTO_DELETE_SINC: {
				String id = uri.getPathSegments().get(2);
				Cursor cursor = db.query(ModeloProdutoContract.TABLE_NAME,null,ModeloProdutoContract.COLUNA_CHAVE + " = ? ",new String[]{id},null,null,null);
				if (cursor.moveToFirst()) {
					ContentValues valores = new ContentValues();
					DatabaseUtils.cursorRowToContentValues(cursor, valores);
					linhaDelete = db.delete(ModeloProdutoContract.TABLE_NAME, ModeloProdutoContract.COLUNA_CHAVE + " = ? ", new String[]{id});
					DCLog.d(DCLog.DATABASE_CRUD,this,"delete " + ModeloProdutoContract.TABLE_NAME + "(id:" + id + ")");
					valores.put(ModeloProdutoContract.COLUNA_OPERACAO_SINC, "D");
					insereSinc(db,valores);
				}
				notificaUriRelacionadas();
				mProvider.getContext().getContentResolver().notifyChange(ModeloProdutoContract.buildAll(), null);
				break;
			}
			case MODELO_PRODUTO_DELETE_ALL_SINC: {
				linhaDelete = db.delete(ModeloProdutoContract.TABLE_NAME_SINC, null, null);
				break;
			}
			case MODELO_PRODUTO_DELETE_RECREATE: {
				linhaDelete = db.delete(ModeloProdutoContract.TABLE_NAME, null, null);
				break;
			}
		}
		return true;
	}
	
	
	public boolean update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		linhaUpdate = db.update(ModeloProdutoContract.TABLE_NAME, values, selection, selectionArgs);
		return true;
	}
	public boolean update(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		String selection = ModeloProdutoContract.COLUNA_CHAVE + " = ? ";
		String dados[] = {values.get(ModeloProdutoContract.COLUNA_CHAVE).toString()};
		linhaUpdate = db.update(ModeloProdutoContract.TABLE_NAME, values, selection, dados);
		values.put(ModeloProdutoContract.COLUNA_OPERACAO_SINC,"A");
		insereSinc(db,values);
		return true;
	}
	
	private void insereSinc(SQLiteDatabase db, ContentValues values) {
		db.insert(ModeloProdutoContract.TABLE_NAME_SINC, null, values);
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
				String[] args = {(String)value.get(ModeloProdutoContract.COLUNA_CHAVE)};
				Cursor retCursor = query(null, sPorChaveSel, args, null);
				if (retCursor.moveToFirst()) {
						db.update(ModeloProdutoContract.TABLE_NAME,value,sPorChaveSel,args);
						//DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk " + ModeloProdutoContract.TABLE_NAME + "  " + values.toString());
						DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk id:" + value.get(ModeloProdutoContract.COLUNA_CHAVE) + ")" + values.toString());
				} else {
					long _id = db.insert(ModeloProdutoContract.TABLE_NAME, null, value);
					if (_id != -1) {
						//DCLog.d(DCLog.DATABASE_CRUD,this,"insert (bulk)" + ModeloProdutoContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
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
            	long _id = db.insert(ModeloProdutoContract.TABLE_NAME, null, value);
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
		String sql = "select max(" + ModeloProdutoContract.COLUNA_CHAVE + ") from " + ModeloProdutoContract.TABLE_NAME;
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