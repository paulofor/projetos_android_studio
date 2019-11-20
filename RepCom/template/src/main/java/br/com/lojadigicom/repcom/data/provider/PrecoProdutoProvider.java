
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

public abstract class PrecoProdutoProvider {

	private int qtdeLinhas = 0;
	
	public static final int PRECO_PRODUTO = 67700;
	public static final int PRECO_PRODUTO_ID = 67701;
	public static final int PRECO_PRODUTO_SINC = 67703;
	public static final int PRECO_PRODUTO_E_COMPLEMENTO = 67704;
	public static final int PRECO_PRODUTO_ID_E_COMPLEMENTO = 67705;
	//public static final int PRECO_PRODUTO_OPERACAO = 67702;
	
	// deletes
	public static final int PRECO_PRODUTO_DELETE_ALL_SINC = 67706;
	public static final int PRECO_PRODUTO_DELETE_RECREATE = 67707;
	public static final int PRECO_PRODUTO_DELETE_SINC = 67708;
	public static final int PRECO_PRODUTO_E_RETIRADA = 67709;
	
	private static final String sPorChaveSel = PrecoProdutoContract.TABLE_NAME + "." + PrecoProdutoContract.COLUNA_CHAVE + " = ? ";
	
	
	
	public static final int PRECO_PRODUTO_POR_PRODUTO_PERTENCEA = 67720;
	public static final int COM_PRODUTO = 67721;
	private static final String sPorIdProdutoPaSelecao =
            PrecoProdutoContract.TABLE_NAME + ".id_produto_pa = ? ";
	


	private ContentProvider mProvider = null;


	public void setContentProvider(ContentProvider valor) {
		mProvider = valor;
	}

	protected static final SQLiteQueryBuilder sQueryBuilder;
	static {
		sQueryBuilder = new SQLiteQueryBuilder();
		String tabelas = PrecoProdutoContract.TABLE_NAME;
		
		//tabelas += " inner join " + ProdutoContract.TABLE_NAME + " ";
		//tabelas += " on " + ProdutoContract.TABLE_NAME + "." + ProdutoContract.COLUNA_CHAVE + " = " + PrecoProdutoContract.TABLE_NAME + "." + PrecoProdutoContract.COLUNA_ID_PRODUTO_PA; 
		
		sQueryBuilder.setTables(tabelas);
	}
	private static final SQLiteQueryBuilder sQueryBuilderSinc;
	static {
		sQueryBuilderSinc = new SQLiteQueryBuilder();
		String tabelas = PrecoProdutoContract.TABLE_NAME_SINC;
		sQueryBuilderSinc.setTables(tabelas);
	}
	
	
	protected AplicacaoDbHelper mOpenHelper = null;
	
	public int getLinhas() {
		return qtdeLinhas;
	}
	
	public static void buildUriMatcher(UriMatcher matcher) {
		matcher.addURI(PrecoProdutoContract.getContentAuthority(), PrecoProdutoContract.PATH, PRECO_PRODUTO);
		matcher.addURI(PrecoProdutoContract.getContentAuthority(), PrecoProdutoContract.PATH + "/Sinc" , PRECO_PRODUTO_SINC);
		matcher.addURI(PrecoProdutoContract.getContentAuthority(), PrecoProdutoContract.PATH + "/#"    , PRECO_PRODUTO_ID);
	
		matcher.addURI(PrecoProdutoContract.getContentAuthority(), PrecoProdutoContract.PATH + "/#/" + PrecoProdutoContract.COM_COMPLEMENTO + "/*" , PRECO_PRODUTO_ID_E_COMPLEMENTO);
		matcher.addURI(PrecoProdutoContract.getContentAuthority(), PrecoProdutoContract.PATH + "/" + PrecoProdutoContract.COM_COMPLEMENTO + "/*" , PRECO_PRODUTO_E_COMPLEMENTO);
		matcher.addURI(PrecoProdutoContract.getContentAuthority(), PrecoProdutoContract.PATH + "/" + PrecoProdutoContract.COM_RETIRADA + "/*" , PRECO_PRODUTO_E_RETIRADA);
		
		
		//matcher.addURI(AplicacaoContract.CONTENT_AUTHORITY, PrecoProdutoContract.PATH + "/operacao/*" , PRECO_PRODUTO_OPERACAO);
		
		matcher.addURI(PrecoProdutoContract.getContentAuthority(), PrecoProdutoContract.PATH + "/#/" + ProdutoContract.PATH, PRECO_PRODUTO_POR_PRODUTO_PERTENCEA);
		matcher.addURI(PrecoProdutoContract.getContentAuthority(), PrecoProdutoContract.PATH + "/ComProdutoPertenceA/" , COM_PRODUTO);
		
		
		
		
		// Deletes
		matcher.addURI(PrecoProdutoContract.getContentAuthority(), PrecoProdutoContract.PATH + "/DeleteAllSinc" , 	PRECO_PRODUTO_DELETE_ALL_SINC);
		matcher.addURI(PrecoProdutoContract.getContentAuthority(), PrecoProdutoContract.PATH + "/DeleteAllRecreate" , PRECO_PRODUTO_DELETE_RECREATE);
		matcher.addURI(PrecoProdutoContract.getContentAuthority(), PrecoProdutoContract.PATH + "/DeleteSinc/#" , 		PRECO_PRODUTO_DELETE_SINC);
	}
	
	
	
	
	public void setAplicacaoDbHelper(AplicacaoDbHelper db) {
		mOpenHelper = db;
	}
	
	public Cursor query(UriMatcher sUriMatcher, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Query Uri:" + uri.toString());
		Cursor retCursor = null;
		switch (sUriMatcher.match(uri)) {
            case PRECO_PRODUTO:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRECO_PRODUTO");
                retCursor = query(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case PRECO_PRODUTO_SINC:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRECO_PRODUTO_SINC");
                retCursor = querySinc(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case PRECO_PRODUTO_ID:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRECO_PRODUTO_ID");
            	String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorChaveSel, args, sortOrder);
                break;
            }
            case PRECO_PRODUTO_ID_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRECO_PRODUTO_ID_E_COMPLEMENTO");
				String id = uri.getPathSegments().get(1);	
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri) +
						" where " + PrecoProdutoContract.COLUNA_CHAVE + " = " + id;
				retCursor = queryRaw(sql);
				break;
			}
			case PRECO_PRODUTO_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRECO_PRODUTO_E_COMPLEMENTO");
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case PRECO_PRODUTO_E_RETIRADA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRECO_PRODUTO_E_RETIRADA");
				String sql = "select " +  PrecoProdutoContract.camposOrdenados() +
						" from " + PrecoProdutoContract.TABLE_NAME +
						sqlWhere(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case PRECO_PRODUTO_POR_PRODUTO_PERTENCEA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRECO_PRODUTO_POR_PRODUTO_PERTENCEA");
	            String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorIdProdutoPaSelecao, args, sortOrder);
                break;
            }
            case COM_PRODUTO:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: COM_PRODUTO");
            	String sql = "select " + PrecoProdutoContract.camposOrdenados() + " , " +
						ProdutoContract.camposOrdenados() +
						" from " + PrecoProdutoContract.TABLE_NAME +
						PrecoProdutoContract.innerJoinProduto_PertenceA() +
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
		String sql = PrecoProdutoContract.camposOrdenados();
		List<String> segmentos = uri.getPathSegments();
		// ComChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComProdutoPertenceA",uri.getPathSegments())) {
			sql += "," +  ProdutoContract.camposOrdenados();
		}
	
	
	// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		
		
		
		return sql;
	}
	private String sqlFrom(Uri uri) {
		String sql = PrecoProdutoContract.TABLE_NAME;
		List<String> segmentos = uri.getPathSegments();
		//if (existeItem("ComEpisodioReferenteA",uri.getPathSegments())) {
		//	sql += " " +  EpisodioUsuarioContract.innerJoinEpisodio_ReferenteA();
		//}
		
		
				// ComChaveEstrangeira
  	
		if (existeItem("ComProdutoPertenceA",uri.getPathSegments())) {
			sql += " " +  PrecoProdutoContract.outerJoinProduto_PertenceA();
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
            case PRECO_PRODUTO:
            {
                return PrecoProdutoContract.getContentType();
            }
            case PRECO_PRODUTO_ID:
            {
            	return PrecoProdutoContract.getContentItemType();
            }
			case PRECO_PRODUTO_POR_PRODUTO_PERTENCEA:
            {
	            return PrecoProdutoContract.getContentType();
            }
		
		}	
		return null;
	}
	
	public Uri insert(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Uri returnUri;
		long idNovo = getMaxId(db)+1;
		values.put(PrecoProdutoContract.COLUNA_CHAVE, idNovo);
		long _id = db.insert(PrecoProdutoContract.TABLE_NAME, null, values);
		if (_id > 0) {
			//DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + PrecoProdutoContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
			DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + values.toString() + " (id:" + _id + ")");
			returnUri = PrecoProdutoContract.buildPrecoProdutoUri(idNovo);
			values.put(PrecoProdutoContract.COLUNA_OPERACAO_SINC,"I");
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
			case PRECO_PRODUTO_DELETE_SINC: {
				String id = uri.getPathSegments().get(2);
				Cursor cursor = db.query(PrecoProdutoContract.TABLE_NAME,null,PrecoProdutoContract.COLUNA_CHAVE + " = ? ",new String[]{id},null,null,null);
				if (cursor.moveToFirst()) {
					ContentValues valores = new ContentValues();
					DatabaseUtils.cursorRowToContentValues(cursor, valores);
					linhaDelete = db.delete(PrecoProdutoContract.TABLE_NAME, PrecoProdutoContract.COLUNA_CHAVE + " = ? ", new String[]{id});
					DCLog.d(DCLog.DATABASE_CRUD,this,"delete " + PrecoProdutoContract.TABLE_NAME + "(id:" + id + ")");
					valores.put(PrecoProdutoContract.COLUNA_OPERACAO_SINC, "D");
					insereSinc(db,valores);
				}
				notificaUriRelacionadas();
				mProvider.getContext().getContentResolver().notifyChange(PrecoProdutoContract.buildAll(), null);
				break;
			}
			case PRECO_PRODUTO_DELETE_ALL_SINC: {
				linhaDelete = db.delete(PrecoProdutoContract.TABLE_NAME_SINC, null, null);
				break;
			}
			case PRECO_PRODUTO_DELETE_RECREATE: {
				linhaDelete = db.delete(PrecoProdutoContract.TABLE_NAME, null, null);
				break;
			}
		}
		return true;
	}
	
	
	public boolean update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		linhaUpdate = db.update(PrecoProdutoContract.TABLE_NAME, values, selection, selectionArgs);
		return true;
	}
	public boolean update(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		String selection = PrecoProdutoContract.COLUNA_CHAVE + " = ? ";
		String dados[] = {values.get(PrecoProdutoContract.COLUNA_CHAVE).toString()};
		linhaUpdate = db.update(PrecoProdutoContract.TABLE_NAME, values, selection, dados);
		values.put(PrecoProdutoContract.COLUNA_OPERACAO_SINC,"A");
		insereSinc(db,values);
		return true;
	}
	
	private void insereSinc(SQLiteDatabase db, ContentValues values) {
		db.insert(PrecoProdutoContract.TABLE_NAME_SINC, null, values);
	}
	
	// Notificar todas as uris de entidades que possuem chave estrangeira dessa entidade.
	private void notificaUriRelacionadas() {
		// ComChaveEstrangeira
  	
		mProvider.getContext().getContentResolver().notifyChange(ProdutoContract.buildAllComPrecoProdutoPossui(), null);
		mProvider.getContext().getContentResolver().notifyChange(ProdutoContract.buildAllSemPrecoProdutoPossui(), null);
	
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
				String[] args = {(String)value.get(PrecoProdutoContract.COLUNA_CHAVE)};
				Cursor retCursor = query(null, sPorChaveSel, args, null);
				if (retCursor.moveToFirst()) {
						db.update(PrecoProdutoContract.TABLE_NAME,value,sPorChaveSel,args);
						//DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk " + PrecoProdutoContract.TABLE_NAME + "  " + values.toString());
						DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk id:" + value.get(PrecoProdutoContract.COLUNA_CHAVE) + ")" + values.toString());
				} else {
					long _id = db.insert(PrecoProdutoContract.TABLE_NAME, null, value);
					if (_id != -1) {
						//DCLog.d(DCLog.DATABASE_CRUD,this,"insert (bulk)" + PrecoProdutoContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
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
            	long _id = db.insert(PrecoProdutoContract.TABLE_NAME, null, value);
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
		String sql = "select max(" + PrecoProdutoContract.COLUNA_CHAVE + ") from " + PrecoProdutoContract.TABLE_NAME;
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