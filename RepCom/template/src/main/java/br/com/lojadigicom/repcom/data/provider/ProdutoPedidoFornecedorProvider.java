
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

public abstract class ProdutoPedidoFornecedorProvider {

	private int qtdeLinhas = 0;
	
	public static final int PRODUTO_PEDIDO_FORNECEDOR = 40300;
	public static final int PRODUTO_PEDIDO_FORNECEDOR_ID = 40301;
	public static final int PRODUTO_PEDIDO_FORNECEDOR_SINC = 40303;
	public static final int PRODUTO_PEDIDO_FORNECEDOR_E_COMPLEMENTO = 40304;
	public static final int PRODUTO_PEDIDO_FORNECEDOR_ID_E_COMPLEMENTO = 40305;
	//public static final int PRODUTO_PEDIDO_FORNECEDOR_OPERACAO = 40302;
	
	// deletes
	public static final int PRODUTO_PEDIDO_FORNECEDOR_DELETE_ALL_SINC = 40306;
	public static final int PRODUTO_PEDIDO_FORNECEDOR_DELETE_RECREATE = 40307;
	public static final int PRODUTO_PEDIDO_FORNECEDOR_DELETE_SINC = 40308;
	public static final int PRODUTO_PEDIDO_FORNECEDOR_E_RETIRADA = 40309;
	
	private static final String sPorChaveSel = ProdutoPedidoFornecedorContract.TABLE_NAME + "." + ProdutoPedidoFornecedorContract.COLUNA_CHAVE + " = ? ";
	
	
	
	public static final int PRODUTO_PEDIDO_FORNECEDOR_POR_PEDIDO_FORNECEDOR_ASSOCIADA = 40320;
	public static final int COM_PEDIDO_FORNECEDOR = 40321;
	private static final String sPorIdPedidoFornecedorASelecao =
            ProdutoPedidoFornecedorContract.TABLE_NAME + ".id_pedido_fornecedor_a = ? ";
	
	public static final int PRODUTO_PEDIDO_FORNECEDOR_POR_PRODUTO_ASSOCIADA = 40322;
	public static final int COM_PRODUTO = 40323;
	private static final String sPorIdProdutoASelecao =
            ProdutoPedidoFornecedorContract.TABLE_NAME + ".id_produto_a = ? ";
	


	private ContentProvider mProvider = null;


	public void setContentProvider(ContentProvider valor) {
		mProvider = valor;
	}

	protected static final SQLiteQueryBuilder sQueryBuilder;
	static {
		sQueryBuilder = new SQLiteQueryBuilder();
		String tabelas = ProdutoPedidoFornecedorContract.TABLE_NAME;
		
		//tabelas += " inner join " + PedidoFornecedorContract.TABLE_NAME + " ";
		//tabelas += " on " + PedidoFornecedorContract.TABLE_NAME + "." + PedidoFornecedorContract.COLUNA_CHAVE + " = " + ProdutoPedidoFornecedorContract.TABLE_NAME + "." + ProdutoPedidoFornecedorContract.COLUNA_ID_PEDIDO_FORNECEDOR_A; 
		
		//tabelas += " inner join " + ProdutoContract.TABLE_NAME + " ";
		//tabelas += " on " + ProdutoContract.TABLE_NAME + "." + ProdutoContract.COLUNA_CHAVE + " = " + ProdutoPedidoFornecedorContract.TABLE_NAME + "." + ProdutoPedidoFornecedorContract.COLUNA_ID_PRODUTO_A; 
		
		sQueryBuilder.setTables(tabelas);
	}
	private static final SQLiteQueryBuilder sQueryBuilderSinc;
	static {
		sQueryBuilderSinc = new SQLiteQueryBuilder();
		String tabelas = ProdutoPedidoFornecedorContract.TABLE_NAME_SINC;
		sQueryBuilderSinc.setTables(tabelas);
	}
	
	
	protected AplicacaoDbHelper mOpenHelper = null;
	
	public int getLinhas() {
		return qtdeLinhas;
	}
	
	public static void buildUriMatcher(UriMatcher matcher) {
		matcher.addURI(ProdutoPedidoFornecedorContract.getContentAuthority(), ProdutoPedidoFornecedorContract.PATH, PRODUTO_PEDIDO_FORNECEDOR);
		matcher.addURI(ProdutoPedidoFornecedorContract.getContentAuthority(), ProdutoPedidoFornecedorContract.PATH + "/Sinc" , PRODUTO_PEDIDO_FORNECEDOR_SINC);
		matcher.addURI(ProdutoPedidoFornecedorContract.getContentAuthority(), ProdutoPedidoFornecedorContract.PATH + "/#"    , PRODUTO_PEDIDO_FORNECEDOR_ID);
	
		matcher.addURI(ProdutoPedidoFornecedorContract.getContentAuthority(), ProdutoPedidoFornecedorContract.PATH + "/#/" + ProdutoPedidoFornecedorContract.COM_COMPLEMENTO + "/*" , PRODUTO_PEDIDO_FORNECEDOR_ID_E_COMPLEMENTO);
		matcher.addURI(ProdutoPedidoFornecedorContract.getContentAuthority(), ProdutoPedidoFornecedorContract.PATH + "/" + ProdutoPedidoFornecedorContract.COM_COMPLEMENTO + "/*" , PRODUTO_PEDIDO_FORNECEDOR_E_COMPLEMENTO);
		matcher.addURI(ProdutoPedidoFornecedorContract.getContentAuthority(), ProdutoPedidoFornecedorContract.PATH + "/" + ProdutoPedidoFornecedorContract.COM_RETIRADA + "/*" , PRODUTO_PEDIDO_FORNECEDOR_E_RETIRADA);
		
		
		//matcher.addURI(AplicacaoContract.CONTENT_AUTHORITY, ProdutoPedidoFornecedorContract.PATH + "/operacao/*" , PRODUTO_PEDIDO_FORNECEDOR_OPERACAO);
		
		matcher.addURI(ProdutoPedidoFornecedorContract.getContentAuthority(), ProdutoPedidoFornecedorContract.PATH + "/#/" + PedidoFornecedorContract.PATH, PRODUTO_PEDIDO_FORNECEDOR_POR_PEDIDO_FORNECEDOR_ASSOCIADA);
		matcher.addURI(ProdutoPedidoFornecedorContract.getContentAuthority(), ProdutoPedidoFornecedorContract.PATH + "/ComPedidoFornecedorAssociada/" , COM_PEDIDO_FORNECEDOR);
		
		matcher.addURI(ProdutoPedidoFornecedorContract.getContentAuthority(), ProdutoPedidoFornecedorContract.PATH + "/#/" + ProdutoContract.PATH, PRODUTO_PEDIDO_FORNECEDOR_POR_PRODUTO_ASSOCIADA);
		matcher.addURI(ProdutoPedidoFornecedorContract.getContentAuthority(), ProdutoPedidoFornecedorContract.PATH + "/ComProdutoAssociada/" , COM_PRODUTO);
		
		
		
		
		// Deletes
		matcher.addURI(ProdutoPedidoFornecedorContract.getContentAuthority(), ProdutoPedidoFornecedorContract.PATH + "/DeleteAllSinc" , 	PRODUTO_PEDIDO_FORNECEDOR_DELETE_ALL_SINC);
		matcher.addURI(ProdutoPedidoFornecedorContract.getContentAuthority(), ProdutoPedidoFornecedorContract.PATH + "/DeleteAllRecreate" , PRODUTO_PEDIDO_FORNECEDOR_DELETE_RECREATE);
		matcher.addURI(ProdutoPedidoFornecedorContract.getContentAuthority(), ProdutoPedidoFornecedorContract.PATH + "/DeleteSinc/#" , 		PRODUTO_PEDIDO_FORNECEDOR_DELETE_SINC);
	}
	
	
	
	
	public void setAplicacaoDbHelper(AplicacaoDbHelper db) {
		mOpenHelper = db;
	}
	
	public Cursor query(UriMatcher sUriMatcher, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Query Uri:" + uri.toString());
		Cursor retCursor = null;
		switch (sUriMatcher.match(uri)) {
            case PRODUTO_PEDIDO_FORNECEDOR:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_PEDIDO_FORNECEDOR");
                retCursor = query(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case PRODUTO_PEDIDO_FORNECEDOR_SINC:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_PEDIDO_FORNECEDOR_SINC");
                retCursor = querySinc(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case PRODUTO_PEDIDO_FORNECEDOR_ID:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_PEDIDO_FORNECEDOR_ID");
            	String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorChaveSel, args, sortOrder);
                break;
            }
            case PRODUTO_PEDIDO_FORNECEDOR_ID_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_PEDIDO_FORNECEDOR_ID_E_COMPLEMENTO");
				String id = uri.getPathSegments().get(1);	
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri) +
						" where " + ProdutoPedidoFornecedorContract.COLUNA_CHAVE + " = " + id;
				retCursor = queryRaw(sql);
				break;
			}
			case PRODUTO_PEDIDO_FORNECEDOR_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_PEDIDO_FORNECEDOR_E_COMPLEMENTO");
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case PRODUTO_PEDIDO_FORNECEDOR_E_RETIRADA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_PEDIDO_FORNECEDOR_E_RETIRADA");
				String sql = "select " +  ProdutoPedidoFornecedorContract.camposOrdenados() +
						" from " + ProdutoPedidoFornecedorContract.TABLE_NAME +
						sqlWhere(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case PRODUTO_PEDIDO_FORNECEDOR_POR_PEDIDO_FORNECEDOR_ASSOCIADA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_PEDIDO_FORNECEDOR_POR_PEDIDO_FORNECEDOR_ASSOCIADA");
	            String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorIdPedidoFornecedorASelecao, args, sortOrder);
                break;
            }
            case COM_PEDIDO_FORNECEDOR:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: COM_PEDIDO_FORNECEDOR");
            	String sql = "select " + ProdutoPedidoFornecedorContract.camposOrdenados() + " , " +
						PedidoFornecedorContract.camposOrdenados() +
						" from " + ProdutoPedidoFornecedorContract.TABLE_NAME +
						ProdutoPedidoFornecedorContract.innerJoinPedidoFornecedor_Associada() +
						getOrderBy();
                retCursor = queryRaw(sql);
				break;
            }
		
			case PRODUTO_PEDIDO_FORNECEDOR_POR_PRODUTO_ASSOCIADA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_PEDIDO_FORNECEDOR_POR_PRODUTO_ASSOCIADA");
	            String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorIdProdutoASelecao, args, sortOrder);
                break;
            }
            case COM_PRODUTO:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: COM_PRODUTO");
            	String sql = "select " + ProdutoPedidoFornecedorContract.camposOrdenados() + " , " +
						ProdutoContract.camposOrdenados() +
						" from " + ProdutoPedidoFornecedorContract.TABLE_NAME +
						ProdutoPedidoFornecedorContract.innerJoinProduto_Associada() +
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
		String sql = ProdutoPedidoFornecedorContract.camposOrdenados();
		List<String> segmentos = uri.getPathSegments();
		// ComChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComPedidoFornecedorAssociada",uri.getPathSegments())) {
			sql += "," +  PedidoFornecedorContract.camposOrdenados();
		}
	
		if (existeItem("ComProdutoAssociada",uri.getPathSegments())) {
			sql += "," +  ProdutoContract.camposOrdenados();
		}
	
	
	// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		
		
		
		return sql;
	}
	private String sqlFrom(Uri uri) {
		String sql = ProdutoPedidoFornecedorContract.TABLE_NAME;
		List<String> segmentos = uri.getPathSegments();
		//if (existeItem("ComEpisodioReferenteA",uri.getPathSegments())) {
		//	sql += " " +  EpisodioUsuarioContract.innerJoinEpisodio_ReferenteA();
		//}
		
		
				// ComChaveEstrangeira
  	
		if (existeItem("ComPedidoFornecedorAssociada",uri.getPathSegments())) {
			sql += " " +  ProdutoPedidoFornecedorContract.outerJoinPedidoFornecedor_Associada();
		}
	
		if (existeItem("ComProdutoAssociada",uri.getPathSegments())) {
			sql += " " +  ProdutoPedidoFornecedorContract.outerJoinProduto_Associada();
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
            case PRODUTO_PEDIDO_FORNECEDOR:
            {
                return ProdutoPedidoFornecedorContract.getContentType();
            }
            case PRODUTO_PEDIDO_FORNECEDOR_ID:
            {
            	return ProdutoPedidoFornecedorContract.getContentItemType();
            }
			case PRODUTO_PEDIDO_FORNECEDOR_POR_PEDIDO_FORNECEDOR_ASSOCIADA:
            {
	            return ProdutoPedidoFornecedorContract.getContentType();
            }
		
			case PRODUTO_PEDIDO_FORNECEDOR_POR_PRODUTO_ASSOCIADA:
            {
	            return ProdutoPedidoFornecedorContract.getContentType();
            }
		
		}	
		return null;
	}
	
	public Uri insert(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Uri returnUri;
		long idNovo = getMaxId(db)+1;
		values.put(ProdutoPedidoFornecedorContract.COLUNA_CHAVE, idNovo);
		long _id = db.insert(ProdutoPedidoFornecedorContract.TABLE_NAME, null, values);
		if (_id > 0) {
			//DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + ProdutoPedidoFornecedorContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
			DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + values.toString() + " (id:" + _id + ")");
			returnUri = ProdutoPedidoFornecedorContract.buildProdutoPedidoFornecedorUri(idNovo);
			values.put(ProdutoPedidoFornecedorContract.COLUNA_OPERACAO_SINC,"I");
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
			case PRODUTO_PEDIDO_FORNECEDOR_DELETE_SINC: {
				String id = uri.getPathSegments().get(2);
				Cursor cursor = db.query(ProdutoPedidoFornecedorContract.TABLE_NAME,null,ProdutoPedidoFornecedorContract.COLUNA_CHAVE + " = ? ",new String[]{id},null,null,null);
				if (cursor.moveToFirst()) {
					ContentValues valores = new ContentValues();
					DatabaseUtils.cursorRowToContentValues(cursor, valores);
					linhaDelete = db.delete(ProdutoPedidoFornecedorContract.TABLE_NAME, ProdutoPedidoFornecedorContract.COLUNA_CHAVE + " = ? ", new String[]{id});
					DCLog.d(DCLog.DATABASE_CRUD,this,"delete " + ProdutoPedidoFornecedorContract.TABLE_NAME + "(id:" + id + ")");
					valores.put(ProdutoPedidoFornecedorContract.COLUNA_OPERACAO_SINC, "D");
					insereSinc(db,valores);
				}
				notificaUriRelacionadas();
				mProvider.getContext().getContentResolver().notifyChange(ProdutoPedidoFornecedorContract.buildAll(), null);
				break;
			}
			case PRODUTO_PEDIDO_FORNECEDOR_DELETE_ALL_SINC: {
				linhaDelete = db.delete(ProdutoPedidoFornecedorContract.TABLE_NAME_SINC, null, null);
				break;
			}
			case PRODUTO_PEDIDO_FORNECEDOR_DELETE_RECREATE: {
				linhaDelete = db.delete(ProdutoPedidoFornecedorContract.TABLE_NAME, null, null);
				break;
			}
		}
		return true;
	}
	
	
	public boolean update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		linhaUpdate = db.update(ProdutoPedidoFornecedorContract.TABLE_NAME, values, selection, selectionArgs);
		return true;
	}
	public boolean update(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		String selection = ProdutoPedidoFornecedorContract.COLUNA_CHAVE + " = ? ";
		String dados[] = {values.get(ProdutoPedidoFornecedorContract.COLUNA_CHAVE).toString()};
		linhaUpdate = db.update(ProdutoPedidoFornecedorContract.TABLE_NAME, values, selection, dados);
		values.put(ProdutoPedidoFornecedorContract.COLUNA_OPERACAO_SINC,"A");
		insereSinc(db,values);
		return true;
	}
	
	private void insereSinc(SQLiteDatabase db, ContentValues values) {
		db.insert(ProdutoPedidoFornecedorContract.TABLE_NAME_SINC, null, values);
	}
	
	// Notificar todas as uris de entidades que possuem chave estrangeira dessa entidade.
	private void notificaUriRelacionadas() {
		// ComChaveEstrangeira
  	
		mProvider.getContext().getContentResolver().notifyChange(PedidoFornecedorContract.buildAllComProdutoPedidoFornecedorAssociada(), null);
		mProvider.getContext().getContentResolver().notifyChange(PedidoFornecedorContract.buildAllSemProdutoPedidoFornecedorAssociada(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(ProdutoContract.buildAllComProdutoPedidoFornecedorAssociada(), null);
		mProvider.getContext().getContentResolver().notifyChange(ProdutoContract.buildAllSemProdutoPedidoFornecedorAssociada(), null);
	
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
				String[] args = {(String)value.get(ProdutoPedidoFornecedorContract.COLUNA_CHAVE)};
				Cursor retCursor = query(null, sPorChaveSel, args, null);
				if (retCursor.moveToFirst()) {
						db.update(ProdutoPedidoFornecedorContract.TABLE_NAME,value,sPorChaveSel,args);
						//DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk " + ProdutoPedidoFornecedorContract.TABLE_NAME + "  " + values.toString());
						DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk id:" + value.get(ProdutoPedidoFornecedorContract.COLUNA_CHAVE) + ")" + values.toString());
				} else {
					long _id = db.insert(ProdutoPedidoFornecedorContract.TABLE_NAME, null, value);
					if (_id != -1) {
						//DCLog.d(DCLog.DATABASE_CRUD,this,"insert (bulk)" + ProdutoPedidoFornecedorContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
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
            	long _id = db.insert(ProdutoPedidoFornecedorContract.TABLE_NAME, null, value);
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
		String sql = "select max(" + ProdutoPedidoFornecedorContract.COLUNA_CHAVE + ") from " + ProdutoPedidoFornecedorContract.TABLE_NAME;
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