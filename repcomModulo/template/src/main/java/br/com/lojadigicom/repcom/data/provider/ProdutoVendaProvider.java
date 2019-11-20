
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

public abstract class ProdutoVendaProvider {

	private int qtdeLinhas = 0;
	
	public static final int PRODUTO_VENDA = 40400;
	public static final int PRODUTO_VENDA_ID = 40401;
	public static final int PRODUTO_VENDA_SINC = 40403;
	public static final int PRODUTO_VENDA_E_COMPLEMENTO = 40404;
	public static final int PRODUTO_VENDA_ID_E_COMPLEMENTO = 40405;
	//public static final int PRODUTO_VENDA_OPERACAO = 40402;
	
	// deletes
	public static final int PRODUTO_VENDA_DELETE_ALL_SINC = 40406;
	public static final int PRODUTO_VENDA_DELETE_RECREATE = 40407;
	public static final int PRODUTO_VENDA_DELETE_SINC = 40408;
	public static final int PRODUTO_VENDA_E_RETIRADA = 40409;
	
	private static final String sPorChaveSel = ProdutoVendaContract.TABLE_NAME + "." + ProdutoVendaContract.COLUNA_CHAVE + " = ? ";
	
	
	
	public static final int PRODUTO_VENDA_POR_PRODUTO_ASSOCIADA = 40420;
	public static final int COM_PRODUTO = 40421;
	private static final String sPorIdProdutoASelecao =
            ProdutoVendaContract.TABLE_NAME + ".id_produto_a = ? ";
	
	public static final int PRODUTO_VENDA_POR_VENDA_ASSOCIADA = 40422;
	public static final int COM_VENDA = 40423;
	private static final String sPorIdVendaASelecao =
            ProdutoVendaContract.TABLE_NAME + ".id_venda_a = ? ";
	


	private ContentProvider mProvider = null;


	public void setContentProvider(ContentProvider valor) {
		mProvider = valor;
	}

	protected static final SQLiteQueryBuilder sQueryBuilder;
	static {
		sQueryBuilder = new SQLiteQueryBuilder();
		String tabelas = ProdutoVendaContract.TABLE_NAME;
		
		//tabelas += " inner join " + ProdutoContract.TABLE_NAME + " ";
		//tabelas += " on " + ProdutoContract.TABLE_NAME + "." + ProdutoContract.COLUNA_CHAVE + " = " + ProdutoVendaContract.TABLE_NAME + "." + ProdutoVendaContract.COLUNA_ID_PRODUTO_A; 
		
		//tabelas += " inner join " + VendaContract.TABLE_NAME + " ";
		//tabelas += " on " + VendaContract.TABLE_NAME + "." + VendaContract.COLUNA_CHAVE + " = " + ProdutoVendaContract.TABLE_NAME + "." + ProdutoVendaContract.COLUNA_ID_VENDA_A; 
		
		sQueryBuilder.setTables(tabelas);
	}
	private static final SQLiteQueryBuilder sQueryBuilderSinc;
	static {
		sQueryBuilderSinc = new SQLiteQueryBuilder();
		String tabelas = ProdutoVendaContract.TABLE_NAME_SINC;
		sQueryBuilderSinc.setTables(tabelas);
	}
	
	
	protected AplicacaoDbHelper mOpenHelper = null;
	
	public int getLinhas() {
		return qtdeLinhas;
	}
	
	public static void buildUriMatcher(UriMatcher matcher) {
		matcher.addURI(ProdutoVendaContract.getContentAuthority(), ProdutoVendaContract.PATH, PRODUTO_VENDA);
		matcher.addURI(ProdutoVendaContract.getContentAuthority(), ProdutoVendaContract.PATH + "/Sinc" , PRODUTO_VENDA_SINC);
		matcher.addURI(ProdutoVendaContract.getContentAuthority(), ProdutoVendaContract.PATH + "/#"    , PRODUTO_VENDA_ID);
	
		matcher.addURI(ProdutoVendaContract.getContentAuthority(), ProdutoVendaContract.PATH + "/#/" + ProdutoVendaContract.COM_COMPLEMENTO + "/*" , PRODUTO_VENDA_ID_E_COMPLEMENTO);
		matcher.addURI(ProdutoVendaContract.getContentAuthority(), ProdutoVendaContract.PATH + "/" + ProdutoVendaContract.COM_COMPLEMENTO + "/*" , PRODUTO_VENDA_E_COMPLEMENTO);
		matcher.addURI(ProdutoVendaContract.getContentAuthority(), ProdutoVendaContract.PATH + "/" + ProdutoVendaContract.COM_RETIRADA + "/*" , PRODUTO_VENDA_E_RETIRADA);
		
		
		//matcher.addURI(AplicacaoContract.CONTENT_AUTHORITY, ProdutoVendaContract.PATH + "/operacao/*" , PRODUTO_VENDA_OPERACAO);
		
		matcher.addURI(ProdutoVendaContract.getContentAuthority(), ProdutoVendaContract.PATH + "/#/" + ProdutoContract.PATH, PRODUTO_VENDA_POR_PRODUTO_ASSOCIADA);
		matcher.addURI(ProdutoVendaContract.getContentAuthority(), ProdutoVendaContract.PATH + "/ComProdutoAssociada/" , COM_PRODUTO);
		
		matcher.addURI(ProdutoVendaContract.getContentAuthority(), ProdutoVendaContract.PATH + "/#/" + VendaContract.PATH, PRODUTO_VENDA_POR_VENDA_ASSOCIADA);
		matcher.addURI(ProdutoVendaContract.getContentAuthority(), ProdutoVendaContract.PATH + "/ComVendaAssociada/" , COM_VENDA);
		
		
		
		
		// Deletes
		matcher.addURI(ProdutoVendaContract.getContentAuthority(), ProdutoVendaContract.PATH + "/DeleteAllSinc" , 	PRODUTO_VENDA_DELETE_ALL_SINC);
		matcher.addURI(ProdutoVendaContract.getContentAuthority(), ProdutoVendaContract.PATH + "/DeleteAllRecreate" , PRODUTO_VENDA_DELETE_RECREATE);
		matcher.addURI(ProdutoVendaContract.getContentAuthority(), ProdutoVendaContract.PATH + "/DeleteSinc/#" , 		PRODUTO_VENDA_DELETE_SINC);
	}
	
	
	
	
	public void setAplicacaoDbHelper(AplicacaoDbHelper db) {
		mOpenHelper = db;
	}
	
	public Cursor query(UriMatcher sUriMatcher, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Query Uri:" + uri.toString());
		Cursor retCursor = null;
		switch (sUriMatcher.match(uri)) {
            case PRODUTO_VENDA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_VENDA");
                retCursor = query(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case PRODUTO_VENDA_SINC:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_VENDA_SINC");
                retCursor = querySinc(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case PRODUTO_VENDA_ID:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_VENDA_ID");
            	String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorChaveSel, args, sortOrder);
                break;
            }
            case PRODUTO_VENDA_ID_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_VENDA_ID_E_COMPLEMENTO");
				String id = uri.getPathSegments().get(1);	
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri) +
						" where " + ProdutoVendaContract.COLUNA_CHAVE + " = " + id;
				retCursor = queryRaw(sql);
				break;
			}
			case PRODUTO_VENDA_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_VENDA_E_COMPLEMENTO");
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case PRODUTO_VENDA_E_RETIRADA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_VENDA_E_RETIRADA");
				String sql = "select " +  ProdutoVendaContract.camposOrdenados() +
						" from " + ProdutoVendaContract.TABLE_NAME +
						sqlWhere(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case PRODUTO_VENDA_POR_PRODUTO_ASSOCIADA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_VENDA_POR_PRODUTO_ASSOCIADA");
	            String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorIdProdutoASelecao, args, sortOrder);
                break;
            }
            case COM_PRODUTO:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: COM_PRODUTO");
            	String sql = "select " + ProdutoVendaContract.camposOrdenados() + " , " +
						ProdutoContract.camposOrdenados() +
						" from " + ProdutoVendaContract.TABLE_NAME +
						ProdutoVendaContract.innerJoinProduto_Associada() +
						getOrderBy();
                retCursor = queryRaw(sql);
				break;
            }
		
			case PRODUTO_VENDA_POR_VENDA_ASSOCIADA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_VENDA_POR_VENDA_ASSOCIADA");
	            String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorIdVendaASelecao, args, sortOrder);
                break;
            }
            case COM_VENDA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: COM_VENDA");
            	String sql = "select " + ProdutoVendaContract.camposOrdenados() + " , " +
						VendaContract.camposOrdenados() +
						" from " + ProdutoVendaContract.TABLE_NAME +
						ProdutoVendaContract.innerJoinVenda_Associada() +
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
		String sql = ProdutoVendaContract.camposOrdenados();
		List<String> segmentos = uri.getPathSegments();
		// ComChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComProdutoAssociada",uri.getPathSegments())) {
			sql += "," +  ProdutoContract.camposOrdenados();
		}
	
		if (existeItem("ComVendaAssociada",uri.getPathSegments())) {
			sql += "," +  VendaContract.camposOrdenados();
		}
	
	
	// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		
		
		
		return sql;
	}
	private String sqlFrom(Uri uri) {
		String sql = ProdutoVendaContract.TABLE_NAME;
		List<String> segmentos = uri.getPathSegments();
		//if (existeItem("ComEpisodioReferenteA",uri.getPathSegments())) {
		//	sql += " " +  EpisodioUsuarioContract.innerJoinEpisodio_ReferenteA();
		//}
		
		
				// ComChaveEstrangeira
  	
		if (existeItem("ComProdutoAssociada",uri.getPathSegments())) {
			sql += " " +  ProdutoVendaContract.outerJoinProduto_Associada();
		}
	
		if (existeItem("ComVendaAssociada",uri.getPathSegments())) {
			sql += " " +  ProdutoVendaContract.outerJoinVenda_Associada();
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
            case PRODUTO_VENDA:
            {
                return ProdutoVendaContract.getContentType();
            }
            case PRODUTO_VENDA_ID:
            {
            	return ProdutoVendaContract.getContentItemType();
            }
			case PRODUTO_VENDA_POR_PRODUTO_ASSOCIADA:
            {
	            return ProdutoVendaContract.getContentType();
            }
		
			case PRODUTO_VENDA_POR_VENDA_ASSOCIADA:
            {
	            return ProdutoVendaContract.getContentType();
            }
		
		}	
		return null;
	}
	
	public Uri insert(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Uri returnUri;
		long idNovo = getMaxId(db)+1;
		values.put(ProdutoVendaContract.COLUNA_CHAVE, idNovo);
		long _id = db.insert(ProdutoVendaContract.TABLE_NAME, null, values);
		if (_id > 0) {
			//DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + ProdutoVendaContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
			DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + values.toString() + " (id:" + _id + ")");
			returnUri = ProdutoVendaContract.buildProdutoVendaUri(idNovo);
			values.put(ProdutoVendaContract.COLUNA_OPERACAO_SINC,"I");
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
			case PRODUTO_VENDA_DELETE_SINC: {
				String id = uri.getPathSegments().get(2);
				Cursor cursor = db.query(ProdutoVendaContract.TABLE_NAME,null,ProdutoVendaContract.COLUNA_CHAVE + " = ? ",new String[]{id},null,null,null);
				if (cursor.moveToFirst()) {
					ContentValues valores = new ContentValues();
					DatabaseUtils.cursorRowToContentValues(cursor, valores);
					linhaDelete = db.delete(ProdutoVendaContract.TABLE_NAME, ProdutoVendaContract.COLUNA_CHAVE + " = ? ", new String[]{id});
					DCLog.d(DCLog.DATABASE_CRUD,this,"delete " + ProdutoVendaContract.TABLE_NAME + "(id:" + id + ")");
					valores.put(ProdutoVendaContract.COLUNA_OPERACAO_SINC, "D");
					insereSinc(db,valores);
				}
				notificaUriRelacionadas();
				mProvider.getContext().getContentResolver().notifyChange(ProdutoVendaContract.buildAll(), null);
				break;
			}
			case PRODUTO_VENDA_DELETE_ALL_SINC: {
				linhaDelete = db.delete(ProdutoVendaContract.TABLE_NAME_SINC, null, null);
				break;
			}
			case PRODUTO_VENDA_DELETE_RECREATE: {
				linhaDelete = db.delete(ProdutoVendaContract.TABLE_NAME, null, null);
				break;
			}
		}
		return true;
	}
	
	
	public boolean update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		DCLog.d(DCLog.DATABASE_CRUD,this,"update2 " + values.toString() );
		linhaUpdate = db.update(ProdutoVendaContract.TABLE_NAME, values, selection, selectionArgs);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	public boolean update(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		String selection = ProdutoVendaContract.COLUNA_CHAVE + " = ? ";
		String dados[] = {values.get(ProdutoVendaContract.COLUNA_CHAVE).toString()};
		DCLog.d(DCLog.DATABASE_CRUD,this,"update1 " + values.toString() );
		linhaUpdate = db.update(ProdutoVendaContract.TABLE_NAME, values, selection, dados);
		values.put(ProdutoVendaContract.COLUNA_OPERACAO_SINC,"A");
		insereSinc(db,values);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	
	private void insereSinc(SQLiteDatabase db, ContentValues values) {
		db.insert(ProdutoVendaContract.TABLE_NAME_SINC, null, values);
		notificaUriOperacoes();
	}
	
	protected abstract void notificaOutrasUri(ContentResolver resolver);
	
	
	// Notificar todas as uris de entidades que possuem chave estrangeira dessa entidade.
	private void notificaUriRelacionadas() {
		// ComChaveEstrangeira
  	
		mProvider.getContext().getContentResolver().notifyChange(ProdutoContract.buildAllComProdutoVendaAssociada(), null);
		mProvider.getContext().getContentResolver().notifyChange(ProdutoContract.buildAllSemProdutoVendaAssociada(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(VendaContract.buildAllComProdutoVendaAssociada(), null);
		mProvider.getContext().getContentResolver().notifyChange(VendaContract.buildAllSemProdutoVendaAssociada(), null);
	
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
				String[] args = {(String)value.get(ProdutoVendaContract.COLUNA_CHAVE)};
				Cursor retCursor = query(null, sPorChaveSel, args, null);
				if (retCursor.moveToFirst()) {
						db.update(ProdutoVendaContract.TABLE_NAME,value,sPorChaveSel,args);
						//DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk " + ProdutoVendaContract.TABLE_NAME + "  " + values.toString());
						DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk id:" + value.get(ProdutoVendaContract.COLUNA_CHAVE) + ")" + values.toString());
				} else {
					long _id = db.insert(ProdutoVendaContract.TABLE_NAME, null, value);
					if (_id != -1) {
						//DCLog.d(DCLog.DATABASE_CRUD,this,"insert (bulk)" + ProdutoVendaContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
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
            	long _id = db.insert(ProdutoVendaContract.TABLE_NAME, null, value);
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
		String sql = "select max(" + ProdutoVendaContract.COLUNA_CHAVE + ") from " + ProdutoVendaContract.TABLE_NAME;
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