
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

public abstract class ProdutoProvider {

	private int qtdeLinhas = 0;
	
	public static final int PRODUTO = 78200;
	public static final int PRODUTO_ID = 78201;
	public static final int PRODUTO_SINC = 78203;
	public static final int PRODUTO_E_COMPLEMENTO = 78204;
	public static final int PRODUTO_ID_E_COMPLEMENTO = 78205;
	//public static final int PRODUTO_OPERACAO = 78202;
	
	// deletes
	public static final int PRODUTO_DELETE_ALL_SINC = 78206;
	public static final int PRODUTO_DELETE_RECREATE = 78207;
	public static final int PRODUTO_DELETE_SINC = 78208;
	public static final int PRODUTO_E_RETIRADA = 78209;
	
	private static final String sPorChaveSel = ProdutoContract.TABLE_NAME + "." + ProdutoContract.COLUNA_CHAVE + " = ? ";
	
	
	
	public static final int PRODUTO_POR_LOJA_VIRTUAL_LIDOEM = 78220;
	public static final int COM_LOJA_VIRTUAL = 78221;
	private static final String sPorIdLojaVirtualLeSelecao =
            ProdutoContract.TABLE_NAME + ".id_loja_virtual_le = ? ";
	
	public static final int PRODUTO_POR_MARCA_POSSUI = 78222;
	public static final int COM_MARCA = 78223;
	private static final String sPorIdMarcaPSelecao =
            ProdutoContract.TABLE_NAME + ".id_marca_p = ? ";
	


	private ContentProvider mProvider = null;


	public void setContentProvider(ContentProvider valor) {
		mProvider = valor;
	}

	protected static final SQLiteQueryBuilder sQueryBuilder;
	static {
		sQueryBuilder = new SQLiteQueryBuilder();
		String tabelas = ProdutoContract.TABLE_NAME;
		
		//tabelas += " inner join " + LojaVirtualContract.TABLE_NAME + " ";
		//tabelas += " on " + LojaVirtualContract.TABLE_NAME + "." + LojaVirtualContract.COLUNA_CHAVE + " = " + ProdutoContract.TABLE_NAME + "." + ProdutoContract.COLUNA_ID_LOJA_VIRTUAL_LE; 
		
		//tabelas += " inner join " + MarcaContract.TABLE_NAME + " ";
		//tabelas += " on " + MarcaContract.TABLE_NAME + "." + MarcaContract.COLUNA_CHAVE + " = " + ProdutoContract.TABLE_NAME + "." + ProdutoContract.COLUNA_ID_MARCA_P; 
		
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
		
		matcher.addURI(ProdutoContract.getContentAuthority(), ProdutoContract.PATH + "/#/" + LojaVirtualContract.PATH, PRODUTO_POR_LOJA_VIRTUAL_LIDOEM);
		matcher.addURI(ProdutoContract.getContentAuthority(), ProdutoContract.PATH + "/ComLojaVirtualLidoEm/" , COM_LOJA_VIRTUAL);
		
		matcher.addURI(ProdutoContract.getContentAuthority(), ProdutoContract.PATH + "/#/" + MarcaContract.PATH, PRODUTO_POR_MARCA_POSSUI);
		matcher.addURI(ProdutoContract.getContentAuthority(), ProdutoContract.PATH + "/ComMarcaPossui/" , COM_MARCA);
		
		
		
		
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
			case PRODUTO_POR_LOJA_VIRTUAL_LIDOEM:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_POR_LOJA_VIRTUAL_LIDOEM");
	            String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorIdLojaVirtualLeSelecao, args, sortOrder);
                break;
            }
            case COM_LOJA_VIRTUAL:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: COM_LOJA_VIRTUAL");
            	String sql = "select " + ProdutoContract.camposOrdenados() + " , " +
						LojaVirtualContract.camposOrdenados() +
						" from " + ProdutoContract.TABLE_NAME +
						ProdutoContract.innerJoinLojaVirtual_LidoEm() +
						getOrderBy();
                retCursor = queryRaw(sql);
				break;
            }
		
			case PRODUTO_POR_MARCA_POSSUI:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_POR_MARCA_POSSUI");
	            String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorIdMarcaPSelecao, args, sortOrder);
                break;
            }
            case COM_MARCA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: COM_MARCA");
            	String sql = "select " + ProdutoContract.camposOrdenados() + " , " +
						MarcaContract.camposOrdenados() +
						" from " + ProdutoContract.TABLE_NAME +
						ProdutoContract.innerJoinMarca_Possui() +
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
  	
		if (existeItem("SemModeloProdutoProdutoReferenteA",uri.getPathSegments())) {
			sql += ProdutoContract.COLUNA_CHAVE + " not in (select " + 
					ModeloProdutoProdutoContract.COLUNA_ID_PRODUTO_RA + " from " +
					ModeloProdutoProdutoContract.TABLE_NAME + ")";
		}
	
		if (existeItem("SemPrecoProdutoPossui",uri.getPathSegments())) {
			sql += ProdutoContract.COLUNA_CHAVE + " not in (select " + 
					PrecoProdutoContract.COLUNA_ID_PRODUTO_PA + " from " +
					PrecoProdutoContract.TABLE_NAME + ")";
		}
	
		
		return sql;
	}
	
	private String sqlSelect(Uri uri) {
		String sql = ProdutoContract.camposOrdenados();
		List<String> segmentos = uri.getPathSegments();
		// ComChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComLojaVirtualLidoEm",uri.getPathSegments())) {
			sql += "," +  LojaVirtualContract.camposOrdenados();
		}
	
		if (existeItem("ComMarcaPossui",uri.getPathSegments())) {
			sql += "," +  MarcaContract.camposOrdenados();
		}
	
	
	// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComModeloProdutoProdutoReferenteA",uri.getPathSegments())) {
			sql += "," +  ModeloProdutoProdutoContract.camposOrdenados();
		}
	
		if (existeItem("ComPrecoProdutoPossui",uri.getPathSegments())) {
			sql += "," +  PrecoProdutoContract.camposOrdenados();
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
  	
		if (existeItem("ComLojaVirtualLidoEm",uri.getPathSegments())) {
			sql += " " +  ProdutoContract.outerJoinLojaVirtual_LidoEm();
		}
	
		if (existeItem("ComMarcaPossui",uri.getPathSegments())) {
			sql += " " +  ProdutoContract.outerJoinMarca_Possui();
		}
	
	
	// SemChaveEstrangeira
  	
		if (existeItem("ComModeloProdutoProdutoReferenteA",uri.getPathSegments())) {
			sql += " " +  ProdutoContract.outerJoinModeloProdutoProduto_ReferenteA();
		}
	
		if (existeItem("ComPrecoProdutoPossui",uri.getPathSegments())) {
			sql += " " +  ProdutoContract.outerJoinPrecoProduto_Possui();
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
            case PRODUTO:
            {
                return ProdutoContract.getContentType();
            }
            case PRODUTO_ID:
            {
            	return ProdutoContract.getContentItemType();
            }
			case PRODUTO_POR_LOJA_VIRTUAL_LIDOEM:
            {
	            return ProdutoContract.getContentType();
            }
		
			case PRODUTO_POR_MARCA_POSSUI:
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
		linhaUpdate = db.update(ProdutoContract.TABLE_NAME, values, selection, selectionArgs);
		return true;
	}
	public boolean update(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		String selection = ProdutoContract.COLUNA_CHAVE + " = ? ";
		String dados[] = {values.get(ProdutoContract.COLUNA_CHAVE).toString()};
		linhaUpdate = db.update(ProdutoContract.TABLE_NAME, values, selection, dados);
		values.put(ProdutoContract.COLUNA_OPERACAO_SINC,"A");
		insereSinc(db,values);
		return true;
	}
	
	private void insereSinc(SQLiteDatabase db, ContentValues values) {
		db.insert(ProdutoContract.TABLE_NAME_SINC, null, values);
	}
	
	// Notificar todas as uris de entidades que possuem chave estrangeira dessa entidade.
	private void notificaUriRelacionadas() {
		// ComChaveEstrangeira
  	
		mProvider.getContext().getContentResolver().notifyChange(LojaVirtualContract.buildAllComProdutoPossui(), null);
		mProvider.getContext().getContentResolver().notifyChange(LojaVirtualContract.buildAllSemProdutoPossui(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(MarcaContract.buildAllComProdutoReferenteA(), null);
		mProvider.getContext().getContentResolver().notifyChange(MarcaContract.buildAllSemProdutoReferenteA(), null);
	
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