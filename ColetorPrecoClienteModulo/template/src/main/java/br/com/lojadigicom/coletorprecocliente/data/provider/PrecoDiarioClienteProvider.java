
package  br.com.lojadigicom.coletorprecocliente.data.provider;


import br.com.lojadigicom.coletorprecocliente.data.contract.*;
import br.com.lojadigicom.coletorprecocliente.data.helper.AplicacaoDbHelper;
import android.content.ContentValues;
import android.content.ContentResolver;
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

public abstract class PrecoDiarioClienteProvider {

	private int qtdeLinhas = 0;
	
	public static final int PRECO_DIARIO_CLIENTE = 86400;
	public static final int PRECO_DIARIO_CLIENTE_ID = 86401;
	public static final int PRECO_DIARIO_CLIENTE_SINC = 86403;
	public static final int PRECO_DIARIO_CLIENTE_E_COMPLEMENTO = 86404;
	public static final int PRECO_DIARIO_CLIENTE_ID_E_COMPLEMENTO = 86405;
	//public static final int PRECO_DIARIO_CLIENTE_OPERACAO = 86402;
	
	// deletes
	public static final int PRECO_DIARIO_CLIENTE_DELETE_ALL_SINC = 86406;
	public static final int PRECO_DIARIO_CLIENTE_DELETE_RECREATE = 86407;
	public static final int PRECO_DIARIO_CLIENTE_DELETE_SINC = 86408;
	public static final int PRECO_DIARIO_CLIENTE_E_RETIRADA = 86409;
	
	private static final String sPorChaveSel = PrecoDiarioClienteContract.TABLE_NAME + "." + PrecoDiarioClienteContract.COLUNA_CHAVE + " = ? ";
	
	
	
	public static final int PRECO_DIARIO_CLIENTE_POR_PRODUTO_CLIENTE_PERTENCEA = 86420;
	public static final int COM_PRODUTO_CLIENTE = 86421;
	private static final String sPorIdProdutoClientePaSelecao =
            PrecoDiarioClienteContract.TABLE_NAME + ".id_produto_cliente_pa = ? ";
	

 	public static final int QUANTIDADEPORPRODUTO = 86422;

	private ContentProvider mProvider = null;


	public void setContentProvider(ContentProvider valor) {
		mProvider = valor;
	}

	protected static final SQLiteQueryBuilder sQueryBuilder;
	static {
		sQueryBuilder = new SQLiteQueryBuilder();
		String tabelas = PrecoDiarioClienteContract.TABLE_NAME;
		
		//tabelas += " inner join " + ProdutoClienteContract.TABLE_NAME + " ";
		//tabelas += " on " + ProdutoClienteContract.TABLE_NAME + "." + ProdutoClienteContract.COLUNA_CHAVE + " = " + PrecoDiarioClienteContract.TABLE_NAME + "." + PrecoDiarioClienteContract.COLUNA_ID_PRODUTO_CLIENTE_PA; 
		
		sQueryBuilder.setTables(tabelas);
	}
	private static final SQLiteQueryBuilder sQueryBuilderSinc;
	static {
		sQueryBuilderSinc = new SQLiteQueryBuilder();
		String tabelas = PrecoDiarioClienteContract.TABLE_NAME_SINC;
		sQueryBuilderSinc.setTables(tabelas);
	}
	
	
	protected AplicacaoDbHelper mOpenHelper = null;
	
	public int getLinhas() {
		return qtdeLinhas;
	}
	
	public static void buildUriMatcher(UriMatcher matcher) {
		matcher.addURI(PrecoDiarioClienteContract.getContentAuthority(), PrecoDiarioClienteContract.PATH, PRECO_DIARIO_CLIENTE);
		matcher.addURI(PrecoDiarioClienteContract.getContentAuthority(), PrecoDiarioClienteContract.PATH + "/Sinc" , PRECO_DIARIO_CLIENTE_SINC);
		matcher.addURI(PrecoDiarioClienteContract.getContentAuthority(), PrecoDiarioClienteContract.PATH + "/#"    , PRECO_DIARIO_CLIENTE_ID);
	
		matcher.addURI(PrecoDiarioClienteContract.getContentAuthority(), PrecoDiarioClienteContract.PATH + "/#/" + PrecoDiarioClienteContract.COM_COMPLEMENTO + "/*" , PRECO_DIARIO_CLIENTE_ID_E_COMPLEMENTO);
		matcher.addURI(PrecoDiarioClienteContract.getContentAuthority(), PrecoDiarioClienteContract.PATH + "/" + PrecoDiarioClienteContract.COM_COMPLEMENTO + "/*" , PRECO_DIARIO_CLIENTE_E_COMPLEMENTO);
		matcher.addURI(PrecoDiarioClienteContract.getContentAuthority(), PrecoDiarioClienteContract.PATH + "/" + PrecoDiarioClienteContract.COM_RETIRADA + "/*" , PRECO_DIARIO_CLIENTE_E_RETIRADA);
		
		
		//matcher.addURI(AplicacaoContract.CONTENT_AUTHORITY, PrecoDiarioClienteContract.PATH + "/operacao/*" , PRECO_DIARIO_CLIENTE_OPERACAO);
		
		matcher.addURI(PrecoDiarioClienteContract.getContentAuthority(), PrecoDiarioClienteContract.PATH + "/#/" + ProdutoClienteContract.PATH, PRECO_DIARIO_CLIENTE_POR_PRODUTO_CLIENTE_PERTENCEA);
		matcher.addURI(PrecoDiarioClienteContract.getContentAuthority(), PrecoDiarioClienteContract.PATH + "/ComProdutoClientePertenceA/" , COM_PRODUTO_CLIENTE);
		
		
		
		matcher.addURI(PrecoDiarioClienteContract.getContentAuthority(), PrecoDiarioClienteContract.PATH + "/QuantidadePorProduto/*", QUANTIDADEPORPRODUTO);
		
		
		// Deletes
		matcher.addURI(PrecoDiarioClienteContract.getContentAuthority(), PrecoDiarioClienteContract.PATH + "/DeleteAllSinc" , 	PRECO_DIARIO_CLIENTE_DELETE_ALL_SINC);
		matcher.addURI(PrecoDiarioClienteContract.getContentAuthority(), PrecoDiarioClienteContract.PATH + "/DeleteAllRecreate" , PRECO_DIARIO_CLIENTE_DELETE_RECREATE);
		matcher.addURI(PrecoDiarioClienteContract.getContentAuthority(), PrecoDiarioClienteContract.PATH + "/DeleteSinc/#" , 		PRECO_DIARIO_CLIENTE_DELETE_SINC);
	}
	
	
	
	
	public void setAplicacaoDbHelper(AplicacaoDbHelper db) {
		mOpenHelper = db;
	}
	
	public Cursor query(UriMatcher sUriMatcher, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Query Uri:" + uri.toString());
		Cursor retCursor = null;
		switch (sUriMatcher.match(uri)) {
            case PRECO_DIARIO_CLIENTE:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRECO_DIARIO_CLIENTE");
                retCursor = query(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case PRECO_DIARIO_CLIENTE_SINC:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRECO_DIARIO_CLIENTE_SINC");
                retCursor = querySinc(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case PRECO_DIARIO_CLIENTE_ID:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRECO_DIARIO_CLIENTE_ID");
            	String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorChaveSel, args, sortOrder);
                break;
            }
            case PRECO_DIARIO_CLIENTE_ID_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRECO_DIARIO_CLIENTE_ID_E_COMPLEMENTO");
				String id = uri.getPathSegments().get(1);	
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri) +
						" where " + PrecoDiarioClienteContract.COLUNA_CHAVE + " = " + id;
				retCursor = queryRaw(sql);
				break;
			}
			case PRECO_DIARIO_CLIENTE_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRECO_DIARIO_CLIENTE_E_COMPLEMENTO");
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case PRECO_DIARIO_CLIENTE_E_RETIRADA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRECO_DIARIO_CLIENTE_E_RETIRADA");
				String sql = "select " +  PrecoDiarioClienteContract.camposOrdenados() +
						" from " + PrecoDiarioClienteContract.TABLE_NAME +
						sqlWhere(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case PRECO_DIARIO_CLIENTE_POR_PRODUTO_CLIENTE_PERTENCEA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRECO_DIARIO_CLIENTE_POR_PRODUTO_CLIENTE_PERTENCEA");
	            String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorIdProdutoClientePaSelecao, args, sortOrder);
                break;
            }
            case COM_PRODUTO_CLIENTE:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: COM_PRODUTO_CLIENTE");
            	String sql = "select " + PrecoDiarioClienteContract.camposOrdenados() + " , " +
						ProdutoClienteContract.camposOrdenados() +
						" from " + PrecoDiarioClienteContract.TABLE_NAME +
						PrecoDiarioClienteContract.innerJoinProdutoCliente_PertenceA() +
						getOrderBy();
                retCursor = queryRaw(sql);
				break;
            }
		
		
	 		case QUANTIDADEPORPRODUTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: QUANTIDADEPORPRODUTO");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryQuantidadePorProduto()");
				//retCursor = queryQuantidadePorProduto(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryQuantidadePorProduto(param) + (sortOrder!=null?" order by " + sortOrder:"");
				DCLog.d(DCLog.TRACE_LISTA,this,"SQL: " + sql);
				retCursor = this.queryRaw(sql);
				DCLog.d(DCLog.TRACE_LISTA,this,"Cursor: " + retCursor.getCount() + " linhas");
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
		String sql = PrecoDiarioClienteContract.camposOrdenados();
		List<String> segmentos = uri.getPathSegments();
		// ComChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComProdutoClientePertenceA",uri.getPathSegments())) {
			sql += "," +  ProdutoClienteContract.camposOrdenados();
		}
	
	
	// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		
		
		
		return sql;
	}
	private String sqlFrom(Uri uri) {
		String sql = PrecoDiarioClienteContract.TABLE_NAME;
		List<String> segmentos = uri.getPathSegments();
		//if (existeItem("ComEpisodioReferenteA",uri.getPathSegments())) {
		//	sql += " " +  EpisodioUsuarioContract.innerJoinEpisodio_ReferenteA();
		//}
		
		
				// ComChaveEstrangeira
  	
		if (existeItem("ComProdutoClientePertenceA",uri.getPathSegments())) {
			sql += " " +  PrecoDiarioClienteContract.outerJoinProdutoCliente_PertenceA();
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
	
	
	protected abstract String queryQuantidadePorProduto(String param);
	
	
	
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
            case PRECO_DIARIO_CLIENTE:
            {
                return PrecoDiarioClienteContract.getContentType();
            }
            case PRECO_DIARIO_CLIENTE_ID:
            {
            	return PrecoDiarioClienteContract.getContentItemType();
            }
			case PRECO_DIARIO_CLIENTE_POR_PRODUTO_CLIENTE_PERTENCEA:
            {
	            return PrecoDiarioClienteContract.getContentType();
            }
		
		}	
		return null;
	}
	
	public Uri insert(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Uri returnUri;
		long idNovo = getMaxId(db)+1;
		values.put(PrecoDiarioClienteContract.COLUNA_CHAVE, idNovo);
		long _id = db.insert(PrecoDiarioClienteContract.TABLE_NAME, null, values);
		if (_id > 0) {
			//DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + PrecoDiarioClienteContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
			DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + values.toString() + " (id:" + _id + ")");
			returnUri = PrecoDiarioClienteContract.buildPrecoDiarioClienteUri(idNovo);
			values.put(PrecoDiarioClienteContract.COLUNA_OPERACAO_SINC,"I");
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
			case PRECO_DIARIO_CLIENTE_DELETE_SINC: {
				String id = uri.getPathSegments().get(2);
				Cursor cursor = db.query(PrecoDiarioClienteContract.TABLE_NAME,null,PrecoDiarioClienteContract.COLUNA_CHAVE + " = ? ",new String[]{id},null,null,null);
				if (cursor.moveToFirst()) {
					ContentValues valores = new ContentValues();
					DatabaseUtils.cursorRowToContentValues(cursor, valores);
					linhaDelete = db.delete(PrecoDiarioClienteContract.TABLE_NAME, PrecoDiarioClienteContract.COLUNA_CHAVE + " = ? ", new String[]{id});
					DCLog.d(DCLog.DATABASE_CRUD,this,"delete " + PrecoDiarioClienteContract.TABLE_NAME + "(id:" + id + ")");
					valores.put(PrecoDiarioClienteContract.COLUNA_OPERACAO_SINC, "D");
					insereSinc(db,valores);
				}
				notificaUriRelacionadas();
				mProvider.getContext().getContentResolver().notifyChange(PrecoDiarioClienteContract.buildAll(), null);
				break;
			}
			case PRECO_DIARIO_CLIENTE_DELETE_ALL_SINC: {
				linhaDelete = db.delete(PrecoDiarioClienteContract.TABLE_NAME_SINC, null, null);
				break;
			}
			case PRECO_DIARIO_CLIENTE_DELETE_RECREATE: {
				linhaDelete = db.delete(PrecoDiarioClienteContract.TABLE_NAME, null, null);
				break;
			}
		}
		return true;
	}
	
	
	public boolean update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		DCLog.d(DCLog.DATABASE_CRUD,this,"update2 " + values.toString() );
		linhaUpdate = db.update(PrecoDiarioClienteContract.TABLE_NAME, values, selection, selectionArgs);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	public boolean update(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		String selection = PrecoDiarioClienteContract.COLUNA_CHAVE + " = ? ";
		String dados[] = {values.get(PrecoDiarioClienteContract.COLUNA_CHAVE).toString()};
		DCLog.d(DCLog.DATABASE_CRUD,this,"update1 " + values.toString() );
		linhaUpdate = db.update(PrecoDiarioClienteContract.TABLE_NAME, values, selection, dados);
		values.put(PrecoDiarioClienteContract.COLUNA_OPERACAO_SINC,"A");
		insereSinc(db,values);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	
	private void insereSinc(SQLiteDatabase db, ContentValues values) {
		db.insert(PrecoDiarioClienteContract.TABLE_NAME_SINC, null, values);
		notificaUriOperacoes();
	}
	
	protected abstract void notificaOutrasUri(ContentResolver resolver);
	
	
	// Notificar todas as uris de entidades que possuem chave estrangeira dessa entidade.
	private void notificaUriRelacionadas() {
		// ComChaveEstrangeira
  	
		mProvider.getContext().getContentResolver().notifyChange(ProdutoClienteContract.buildAllComPrecoDiarioClientePossui(), null);
		mProvider.getContext().getContentResolver().notifyChange(ProdutoClienteContract.buildAllSemPrecoDiarioClientePossui(), null);
	
	}
	private void notificaUriOperacoes() {
	
		mProvider.getContext().getContentResolver().notifyChange(PrecoDiarioClienteContract.buildQuantidadePorProduto(), null);
	
	}
	private void notificaUriRaiz(){
		
	}
	
	
	
	public int bulkInsert(Uri uri, ContentValues[] values) {
    	final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        db.beginTransaction();
        int returnCount = 0;
        try {
        	for (ContentValues value : values) {
				String[] args = {(String)value.get(PrecoDiarioClienteContract.COLUNA_CHAVE)};
				Cursor retCursor = query(null, sPorChaveSel, args, null);
				if (retCursor.moveToFirst()) {
						db.update(PrecoDiarioClienteContract.TABLE_NAME,value,sPorChaveSel,args);
						//DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk " + PrecoDiarioClienteContract.TABLE_NAME + "  " + values.toString());
						DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk id:" + value.get(PrecoDiarioClienteContract.COLUNA_CHAVE) + ")" + values.toString());
				} else {
					long _id = db.insert(PrecoDiarioClienteContract.TABLE_NAME, null, value);
					if (_id != -1) {
						//DCLog.d(DCLog.DATABASE_CRUD,this,"insert (bulk)" + PrecoDiarioClienteContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
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
            	long _id = db.insert(PrecoDiarioClienteContract.TABLE_NAME, null, value);
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
		String sql = "select max(" + PrecoDiarioClienteContract.COLUNA_CHAVE + ") from " + PrecoDiarioClienteContract.TABLE_NAME;
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