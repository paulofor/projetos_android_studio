
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

public abstract class ProdutoProvider {

	private int qtdeLinhas = 0;
	
	public static final int PRODUTO = 39700;
	public static final int PRODUTO_ID = 39701;
	public static final int PRODUTO_SINC = 39703;
	public static final int PRODUTO_E_COMPLEMENTO = 39704;
	public static final int PRODUTO_ID_E_COMPLEMENTO = 39705;
	//public static final int PRODUTO_OPERACAO = 39702;
	
	// deletes
	public static final int PRODUTO_DELETE_ALL_SINC = 39706;
	public static final int PRODUTO_DELETE_RECREATE = 39707;
	public static final int PRODUTO_DELETE_SINC = 39708;
	public static final int PRODUTO_E_RETIRADA = 39709;
	
	private static final String sPorChaveSel = ProdutoContract.TABLE_NAME + "." + ProdutoContract.COLUNA_CHAVE + " = ? ";
	
	
	
	public static final int PRODUTO_POR_LINHA_PRODUTO_ESTAEM = 39720;
	public static final int COM_LINHA_PRODUTO = 39721;
	private static final String sPorIdLinhaProdutoEeSelecao =
            ProdutoContract.TABLE_NAME + ".id_linha_produto_ee = ? ";
	

 	public static final int LISTAPORIDCATEGORIA = 39722;
 	public static final int PESQUISATRECHONOME = 39723;

	private ContentProvider mProvider = null;


	public void setContentProvider(ContentProvider valor) {
		mProvider = valor;
	}

	protected static final SQLiteQueryBuilder sQueryBuilder;
	static {
		sQueryBuilder = new SQLiteQueryBuilder();
		String tabelas = ProdutoContract.TABLE_NAME;
		
		//tabelas += " inner join " + LinhaProdutoContract.TABLE_NAME + " ";
		//tabelas += " on " + LinhaProdutoContract.TABLE_NAME + "." + LinhaProdutoContract.COLUNA_CHAVE + " = " + ProdutoContract.TABLE_NAME + "." + ProdutoContract.COLUNA_ID_LINHA_PRODUTO_EE; 
		
		sQueryBuilder.setTables(tabelas);
	}
	private static final SQLiteQueryBuilder sQueryBuilderSinc;
	static {
		sQueryBuilderSinc = new SQLiteQueryBuilder();
		String tabelas = ProdutoContract.TABLE_NAME_SINC;
		sQueryBuilderSinc.setTables(tabelas);
	}
	
	
	protected AplicacaoDbHelper mOpenHelper = null;
	
	public int getLinhas() {
		return qtdeLinhas;
	}
	
	public static void buildUriMatcher(UriMatcher matcher) {
		matcher.addURI(ProdutoContract.getContentAuthority(), ProdutoContract.PATH, PRODUTO);
		matcher.addURI(ProdutoContract.getContentAuthority(), ProdutoContract.PATH + "/Sinc" , PRODUTO_SINC);
		matcher.addURI(ProdutoContract.getContentAuthority(), ProdutoContract.PATH + "/#"    , PRODUTO_ID);
	
		matcher.addURI(ProdutoContract.getContentAuthority(), ProdutoContract.PATH + "/#/" + ProdutoContract.COM_COMPLEMENTO + "/*" , PRODUTO_ID_E_COMPLEMENTO);
		matcher.addURI(ProdutoContract.getContentAuthority(), ProdutoContract.PATH + "/" + ProdutoContract.COM_COMPLEMENTO + "/*" , PRODUTO_E_COMPLEMENTO);
		matcher.addURI(ProdutoContract.getContentAuthority(), ProdutoContract.PATH + "/" + ProdutoContract.COM_RETIRADA + "/*" , PRODUTO_E_RETIRADA);
		
		
		//matcher.addURI(AplicacaoContract.CONTENT_AUTHORITY, ProdutoContract.PATH + "/operacao/*" , PRODUTO_OPERACAO);
		
		matcher.addURI(ProdutoContract.getContentAuthority(), ProdutoContract.PATH + "/#/" + LinhaProdutoContract.PATH, PRODUTO_POR_LINHA_PRODUTO_ESTAEM);
		matcher.addURI(ProdutoContract.getContentAuthority(), ProdutoContract.PATH + "/ComLinhaProdutoEstaEm/" , COM_LINHA_PRODUTO);
		
		
		
		matcher.addURI(ProdutoContract.getContentAuthority(), ProdutoContract.PATH + "/ListaPorIdCategoria/*", LISTAPORIDCATEGORIA);
		
		matcher.addURI(ProdutoContract.getContentAuthority(), ProdutoContract.PATH + "/PesquisaTrechoNome/*", PESQUISATRECHONOME);
		
		
		// Deletes
		matcher.addURI(ProdutoContract.getContentAuthority(), ProdutoContract.PATH + "/DeleteAllSinc" , 	PRODUTO_DELETE_ALL_SINC);
		matcher.addURI(ProdutoContract.getContentAuthority(), ProdutoContract.PATH + "/DeleteAllRecreate" , PRODUTO_DELETE_RECREATE);
		matcher.addURI(ProdutoContract.getContentAuthority(), ProdutoContract.PATH + "/DeleteSinc/#" , 		PRODUTO_DELETE_SINC);
	}
	
	
	
	
	public void setAplicacaoDbHelper(AplicacaoDbHelper db) {
		mOpenHelper = db;
	}
	
	public Cursor query(UriMatcher sUriMatcher, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Query Uri:" + uri.toString());
		Cursor retCursor = null;
		switch (sUriMatcher.match(uri)) {
            case PRODUTO:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO");
                retCursor = query(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case PRODUTO_SINC:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_SINC");
                retCursor = querySinc(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case PRODUTO_ID:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_ID");
            	String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorChaveSel, args, sortOrder);
                break;
            }
            case PRODUTO_ID_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_ID_E_COMPLEMENTO");
				String id = uri.getPathSegments().get(1);	
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri) +
						" where " + ProdutoContract.COLUNA_CHAVE + " = " + id;
				retCursor = queryRaw(sql);
				break;
			}
			case PRODUTO_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_E_COMPLEMENTO");
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case PRODUTO_E_RETIRADA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_E_RETIRADA");
				String sql = "select " +  ProdutoContract.camposOrdenados() +
						" from " + ProdutoContract.TABLE_NAME +
						sqlWhere(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case PRODUTO_POR_LINHA_PRODUTO_ESTAEM:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_POR_LINHA_PRODUTO_ESTAEM");
	            String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorIdLinhaProdutoEeSelecao, args, sortOrder);
                break;
            }
            case COM_LINHA_PRODUTO:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: COM_LINHA_PRODUTO");
            	String sql = "select " + ProdutoContract.camposOrdenados() + " , " +
						LinhaProdutoContract.camposOrdenados() +
						" from " + ProdutoContract.TABLE_NAME +
						ProdutoContract.innerJoinLinhaProduto_EstaEm() +
						getOrderBy();
                retCursor = queryRaw(sql);
				break;
            }
		
		
	 		case LISTAPORIDCATEGORIA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: LISTAPORIDCATEGORIA");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryListaPorIdCategoria()");
				//retCursor = queryListaPorIdCategoria(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryListaPorIdCategoria(param) + (sortOrder!=null?" order by " + sortOrder:"");
				DCLog.d(DCLog.TRACE_LISTA,this,"SQL: " + sql);
				retCursor = this.queryRaw(sql);
				DCLog.d(DCLog.TRACE_LISTA,this,"Cursor: " + retCursor.getCount() + " linhas");
				break;
			}
		
	 		case PESQUISATRECHONOME:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PESQUISATRECHONOME");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryPesquisaTrechoNome()");
				//retCursor = queryPesquisaTrechoNome(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryPesquisaTrechoNome(param) + (sortOrder!=null?" order by " + sortOrder:"");
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
  	
		if (existeItem("SemProdutoPedidoFornecedorAssociada",uri.getPathSegments())) {
			sql += ProdutoContract.COLUNA_CHAVE + " not in (select " + 
					ProdutoPedidoFornecedorContract.COLUNA_ID_PRODUTO_A + " from " +
					ProdutoPedidoFornecedorContract.TABLE_NAME + ")";
		}
	
		if (existeItem("SemProdutoVendaAssociada",uri.getPathSegments())) {
			sql += ProdutoContract.COLUNA_CHAVE + " not in (select " + 
					ProdutoVendaContract.COLUNA_ID_PRODUTO_A + " from " +
					ProdutoVendaContract.TABLE_NAME + ")";
		}
	
		if (existeItem("SemPrecoProdutoPossui",uri.getPathSegments())) {
			sql += ProdutoContract.COLUNA_CHAVE + " not in (select " + 
					PrecoProdutoContract.COLUNA_ID_PRODUTO_PA + " from " +
					PrecoProdutoContract.TABLE_NAME + ")";
		}
	
		if (existeItem("SemCategoriaProdutoProdutoPossui",uri.getPathSegments())) {
			sql += ProdutoContract.COLUNA_CHAVE + " not in (select " + 
					CategoriaProdutoProdutoContract.COLUNA_ID_PRODUTO_RA + " from " +
					CategoriaProdutoProdutoContract.TABLE_NAME + ")";
		}
	
		
		return sql;
	}
	
	private String sqlSelect(Uri uri) {
		String sql = ProdutoContract.camposOrdenados();
		List<String> segmentos = uri.getPathSegments();
		// ComChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComLinhaProdutoEstaEm",uri.getPathSegments())) {
			sql += "," +  LinhaProdutoContract.camposOrdenados();
		}
	
	
	// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComProdutoPedidoFornecedorAssociada",uri.getPathSegments())) {
			sql += "," +  ProdutoPedidoFornecedorContract.camposOrdenados();
		}
	
		if (existeItem("ComProdutoVendaAssociada",uri.getPathSegments())) {
			sql += "," +  ProdutoVendaContract.camposOrdenados();
		}
	
		if (existeItem("ComPrecoProdutoPossui",uri.getPathSegments())) {
			sql += "," +  PrecoProdutoContract.camposOrdenados();
		}
	
		if (existeItem("ComCategoriaProdutoProdutoPossui",uri.getPathSegments())) {
			sql += "," +  CategoriaProdutoProdutoContract.camposOrdenados();
		}
	
		
		
		
		return sql;
	}
	private String sqlFrom(Uri uri) {
		String sql = ProdutoContract.TABLE_NAME;
		List<String> segmentos = uri.getPathSegments();
		//if (existeItem("ComEpisodioReferenteA",uri.getPathSegments())) {
		//	sql += " " +  EpisodioUsuarioContract.innerJoinEpisodio_ReferenteA();
		//}
		
		
				// ComChaveEstrangeira
  	
		if (existeItem("ComLinhaProdutoEstaEm",uri.getPathSegments())) {
			sql += " " +  ProdutoContract.outerJoinLinhaProduto_EstaEm();
		}
	
	
	// SemChaveEstrangeira
  	
		if (existeItem("ComProdutoPedidoFornecedorAssociada",uri.getPathSegments())) {
			sql += " " +  ProdutoContract.outerJoinProdutoPedidoFornecedor_Associada();
		}
	
		if (existeItem("ComProdutoVendaAssociada",uri.getPathSegments())) {
			sql += " " +  ProdutoContract.outerJoinProdutoVenda_Associada();
		}
	
		if (existeItem("ComPrecoProdutoPossui",uri.getPathSegments())) {
			sql += " " +  ProdutoContract.outerJoinPrecoProduto_Possui();
		}
	
		if (existeItem("ComCategoriaProdutoProdutoPossui",uri.getPathSegments())) {
			sql += " " +  ProdutoContract.outerJoinCategoriaProdutoProduto_Possui();
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
	
	
	protected abstract String queryListaPorIdCategoria(String param);
	
	protected abstract String queryPesquisaTrechoNome(String param);
	
	
	
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
            case PRODUTO:
            {
                return ProdutoContract.getContentType();
            }
            case PRODUTO_ID:
            {
            	return ProdutoContract.getContentItemType();
            }
			case PRODUTO_POR_LINHA_PRODUTO_ESTAEM:
            {
	            return ProdutoContract.getContentType();
            }
		
		}	
		return null;
	}
	
	public Uri insert(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Uri returnUri;
		long idNovo = getMaxId(db)+1;
		values.put(ProdutoContract.COLUNA_CHAVE, idNovo);
		long _id = db.insert(ProdutoContract.TABLE_NAME, null, values);
		if (_id > 0) {
			//DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + ProdutoContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
			DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + values.toString() + " (id:" + _id + ")");
			returnUri = ProdutoContract.buildProdutoUri(idNovo);
			values.put(ProdutoContract.COLUNA_OPERACAO_SINC,"I");
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
			case PRODUTO_DELETE_SINC: {
				String id = uri.getPathSegments().get(2);
				Cursor cursor = db.query(ProdutoContract.TABLE_NAME,null,ProdutoContract.COLUNA_CHAVE + " = ? ",new String[]{id},null,null,null);
				if (cursor.moveToFirst()) {
					ContentValues valores = new ContentValues();
					DatabaseUtils.cursorRowToContentValues(cursor, valores);
					linhaDelete = db.delete(ProdutoContract.TABLE_NAME, ProdutoContract.COLUNA_CHAVE + " = ? ", new String[]{id});
					DCLog.d(DCLog.DATABASE_CRUD,this,"delete " + ProdutoContract.TABLE_NAME + "(id:" + id + ")");
					valores.put(ProdutoContract.COLUNA_OPERACAO_SINC, "D");
					insereSinc(db,valores);
				}
				notificaUriRelacionadas();
				mProvider.getContext().getContentResolver().notifyChange(ProdutoContract.buildAll(), null);
				break;
			}
			case PRODUTO_DELETE_ALL_SINC: {
				linhaDelete = db.delete(ProdutoContract.TABLE_NAME_SINC, null, null);
				break;
			}
			case PRODUTO_DELETE_RECREATE: {
				linhaDelete = db.delete(ProdutoContract.TABLE_NAME, null, null);
				break;
			}
		}
		return true;
	}
	
	
	public boolean update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		DCLog.d(DCLog.DATABASE_CRUD,this,"update2 " + values.toString() );
		linhaUpdate = db.update(ProdutoContract.TABLE_NAME, values, selection, selectionArgs);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	public boolean update(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		String selection = ProdutoContract.COLUNA_CHAVE + " = ? ";
		String dados[] = {values.get(ProdutoContract.COLUNA_CHAVE).toString()};
		DCLog.d(DCLog.DATABASE_CRUD,this,"update1 " + values.toString() );
		linhaUpdate = db.update(ProdutoContract.TABLE_NAME, values, selection, dados);
		values.put(ProdutoContract.COLUNA_OPERACAO_SINC,"A");
		insereSinc(db,values);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	
	private void insereSinc(SQLiteDatabase db, ContentValues values) {
		db.insert(ProdutoContract.TABLE_NAME_SINC, null, values);
		notificaUriOperacoes();
	}
	
	protected abstract void notificaOutrasUri(ContentResolver resolver);
	
	
	// Notificar todas as uris de entidades que possuem chave estrangeira dessa entidade.
	private void notificaUriRelacionadas() {
		// ComChaveEstrangeira
  	
		mProvider.getContext().getContentResolver().notifyChange(LinhaProdutoContract.buildAllComProdutoPossui(), null);
		mProvider.getContext().getContentResolver().notifyChange(LinhaProdutoContract.buildAllSemProdutoPossui(), null);
	
	}
	private void notificaUriOperacoes() {
	
		mProvider.getContext().getContentResolver().notifyChange(ProdutoContract.buildListaPorIdCategoria(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(ProdutoContract.buildPesquisaTrechoNome(), null);
	
	}
	private void notificaUriRaiz(){
		
	}
	
	
	
	public int bulkInsert(Uri uri, ContentValues[] values) {
    	final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        db.beginTransaction();
        int returnCount = 0;
        try {
        	for (ContentValues value : values) {
				String[] args = {(String)value.get(ProdutoContract.COLUNA_CHAVE)};
				Cursor retCursor = query(null, sPorChaveSel, args, null);
				if (retCursor.moveToFirst()) {
						db.update(ProdutoContract.TABLE_NAME,value,sPorChaveSel,args);
						//DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk " + ProdutoContract.TABLE_NAME + "  " + values.toString());
						DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk id:" + value.get(ProdutoContract.COLUNA_CHAVE) + ")" + values.toString());
				} else {
					long _id = db.insert(ProdutoContract.TABLE_NAME, null, value);
					if (_id != -1) {
						//DCLog.d(DCLog.DATABASE_CRUD,this,"insert (bulk)" + ProdutoContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
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
            	long _id = db.insert(ProdutoContract.TABLE_NAME, null, value);
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
		String sql = "select max(" + ProdutoContract.COLUNA_CHAVE + ") from " + ProdutoContract.TABLE_NAME;
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