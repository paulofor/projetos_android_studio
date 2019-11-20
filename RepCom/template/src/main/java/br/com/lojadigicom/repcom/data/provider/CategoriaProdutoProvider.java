
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

public abstract class CategoriaProdutoProvider {

	private int qtdeLinhas = 0;
	
	public static final int CATEGORIA_PRODUTO = 39800;
	public static final int CATEGORIA_PRODUTO_ID = 39801;
	public static final int CATEGORIA_PRODUTO_SINC = 39803;
	public static final int CATEGORIA_PRODUTO_E_COMPLEMENTO = 39804;
	public static final int CATEGORIA_PRODUTO_ID_E_COMPLEMENTO = 39805;
	//public static final int CATEGORIA_PRODUTO_OPERACAO = 39802;
	
	// deletes
	public static final int CATEGORIA_PRODUTO_DELETE_ALL_SINC = 39806;
	public static final int CATEGORIA_PRODUTO_DELETE_RECREATE = 39807;
	public static final int CATEGORIA_PRODUTO_DELETE_SINC = 39808;
	public static final int CATEGORIA_PRODUTO_E_RETIRADA = 39809;
	
	private static final String sPorChaveSel = CategoriaProdutoContract.TABLE_NAME + "." + CategoriaProdutoContract.COLUNA_CHAVE + " = ? ";
	
	
	
	public static final int CATEGORIA_PRODUTO_POR_CATEGORIA_PRODUTO_PAI = 39820;
	public static final int COM_CATEGORIA_PRODUTO = 39821;
	private static final String sPorIdCategoriaProdutoPSelecao =
            CategoriaProdutoContract.TABLE_NAME + ".id_categoria_produto_p = ? ";
	

 	public static final int LISTANIVEL0 = 39822;

	private ContentProvider mProvider = null;


	public void setContentProvider(ContentProvider valor) {
		mProvider = valor;
	}

	protected static final SQLiteQueryBuilder sQueryBuilder;
	static {
		sQueryBuilder = new SQLiteQueryBuilder();
		String tabelas = CategoriaProdutoContract.TABLE_NAME;
		
		//tabelas += " inner join " + CategoriaProdutoContract.TABLE_NAME + " ";
		//tabelas += " on " + CategoriaProdutoContract.TABLE_NAME + "." + CategoriaProdutoContract.COLUNA_CHAVE + " = " + CategoriaProdutoContract.TABLE_NAME + "." + CategoriaProdutoContract.COLUNA_ID_CATEGORIA_PRODUTO_P; 
		
		sQueryBuilder.setTables(tabelas);
	}
	private static final SQLiteQueryBuilder sQueryBuilderSinc;
	static {
		sQueryBuilderSinc = new SQLiteQueryBuilder();
		String tabelas = CategoriaProdutoContract.TABLE_NAME_SINC;
		sQueryBuilderSinc.setTables(tabelas);
	}
	
	
	protected AplicacaoDbHelper mOpenHelper = null;
	
	public int getLinhas() {
		return qtdeLinhas;
	}
	
	public static void buildUriMatcher(UriMatcher matcher) {
		matcher.addURI(CategoriaProdutoContract.getContentAuthority(), CategoriaProdutoContract.PATH, CATEGORIA_PRODUTO);
		matcher.addURI(CategoriaProdutoContract.getContentAuthority(), CategoriaProdutoContract.PATH + "/Sinc" , CATEGORIA_PRODUTO_SINC);
		matcher.addURI(CategoriaProdutoContract.getContentAuthority(), CategoriaProdutoContract.PATH + "/#"    , CATEGORIA_PRODUTO_ID);
	
		matcher.addURI(CategoriaProdutoContract.getContentAuthority(), CategoriaProdutoContract.PATH + "/#/" + CategoriaProdutoContract.COM_COMPLEMENTO + "/*" , CATEGORIA_PRODUTO_ID_E_COMPLEMENTO);
		matcher.addURI(CategoriaProdutoContract.getContentAuthority(), CategoriaProdutoContract.PATH + "/" + CategoriaProdutoContract.COM_COMPLEMENTO + "/*" , CATEGORIA_PRODUTO_E_COMPLEMENTO);
		matcher.addURI(CategoriaProdutoContract.getContentAuthority(), CategoriaProdutoContract.PATH + "/" + CategoriaProdutoContract.COM_RETIRADA + "/*" , CATEGORIA_PRODUTO_E_RETIRADA);
		
		
		//matcher.addURI(AplicacaoContract.CONTENT_AUTHORITY, CategoriaProdutoContract.PATH + "/operacao/*" , CATEGORIA_PRODUTO_OPERACAO);
		
		matcher.addURI(CategoriaProdutoContract.getContentAuthority(), CategoriaProdutoContract.PATH + "/#/" + CategoriaProdutoContract.PATH, CATEGORIA_PRODUTO_POR_CATEGORIA_PRODUTO_PAI);
		matcher.addURI(CategoriaProdutoContract.getContentAuthority(), CategoriaProdutoContract.PATH + "/ComCategoriaProdutoPai/" , COM_CATEGORIA_PRODUTO);
		
		
		
		matcher.addURI(CategoriaProdutoContract.getContentAuthority(), CategoriaProdutoContract.PATH + "/ListaNivel0/*", LISTANIVEL0);
		
		
		// Deletes
		matcher.addURI(CategoriaProdutoContract.getContentAuthority(), CategoriaProdutoContract.PATH + "/DeleteAllSinc" , 	CATEGORIA_PRODUTO_DELETE_ALL_SINC);
		matcher.addURI(CategoriaProdutoContract.getContentAuthority(), CategoriaProdutoContract.PATH + "/DeleteAllRecreate" , CATEGORIA_PRODUTO_DELETE_RECREATE);
		matcher.addURI(CategoriaProdutoContract.getContentAuthority(), CategoriaProdutoContract.PATH + "/DeleteSinc/#" , 		CATEGORIA_PRODUTO_DELETE_SINC);
	}
	
	
	
	
	public void setAplicacaoDbHelper(AplicacaoDbHelper db) {
		mOpenHelper = db;
	}
	
	public Cursor query(UriMatcher sUriMatcher, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Query Uri:" + uri.toString());
		Cursor retCursor = null;
		switch (sUriMatcher.match(uri)) {
            case CATEGORIA_PRODUTO:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CATEGORIA_PRODUTO");
                retCursor = query(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case CATEGORIA_PRODUTO_SINC:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CATEGORIA_PRODUTO_SINC");
                retCursor = querySinc(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case CATEGORIA_PRODUTO_ID:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CATEGORIA_PRODUTO_ID");
            	String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorChaveSel, args, sortOrder);
                break;
            }
            case CATEGORIA_PRODUTO_ID_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CATEGORIA_PRODUTO_ID_E_COMPLEMENTO");
				String id = uri.getPathSegments().get(1);	
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri) +
						" where " + CategoriaProdutoContract.COLUNA_CHAVE + " = " + id;
				retCursor = queryRaw(sql);
				break;
			}
			case CATEGORIA_PRODUTO_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CATEGORIA_PRODUTO_E_COMPLEMENTO");
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case CATEGORIA_PRODUTO_E_RETIRADA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CATEGORIA_PRODUTO_E_RETIRADA");
				String sql = "select " +  CategoriaProdutoContract.camposOrdenados() +
						" from " + CategoriaProdutoContract.TABLE_NAME +
						sqlWhere(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case CATEGORIA_PRODUTO_POR_CATEGORIA_PRODUTO_PAI:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CATEGORIA_PRODUTO_POR_CATEGORIA_PRODUTO_PAI");
	            String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorIdCategoriaProdutoPSelecao, args, sortOrder);
                break;
            }
            case COM_CATEGORIA_PRODUTO:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: COM_CATEGORIA_PRODUTO");
            	String sql = "select " + CategoriaProdutoContract.camposOrdenados() + " , " +
						CategoriaProdutoContract.camposOrdenados() +
						" from " + CategoriaProdutoContract.TABLE_NAME +
						CategoriaProdutoContract.innerJoinCategoriaProduto_Pai() +
						getOrderBy();
                retCursor = queryRaw(sql);
				break;
            }
		
		
	 		case LISTANIVEL0:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: LISTANIVEL0");
				retCursor = queryListaNivel0(uri,projection,sortOrder);
				break;
			}
		
		}	
        return retCursor;
	}
	
	private String sqlWhere(Uri uri) {
		String sql = " where ";
		// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("SemClienteInteresseCategoriaAssociada",uri.getPathSegments())) {
			sql += CategoriaProdutoContract.COLUNA_CHAVE + " not in (select " + 
					ClienteInteresseCategoriaContract.COLUNA_ID_CATEGORIA_PRODUTO_A + " from " +
					ClienteInteresseCategoriaContract.TABLE_NAME + ")";
		}
	
		if (existeItem("SemCategoriaProdutoProdutoPossui",uri.getPathSegments())) {
			sql += CategoriaProdutoContract.COLUNA_CHAVE + " not in (select " + 
					CategoriaProdutoProdutoContract.COLUNA_ID_CATEGORIA_PRODUTO_RA + " from " +
					CategoriaProdutoProdutoContract.TABLE_NAME + ")";
		}
	
		if (existeItem("SemCategoriaProdutoPai",uri.getPathSegments())) {
			sql += CategoriaProdutoContract.COLUNA_CHAVE + " not in (select " + 
					CategoriaProdutoContract.COLUNA_ID_CATEGORIA_PRODUTO_P + " from " +
					CategoriaProdutoContract.TABLE_NAME + ")";
		}
	
		
		return sql;
	}
	
	private String sqlSelect(Uri uri) {
		String sql = CategoriaProdutoContract.camposOrdenados();
		List<String> segmentos = uri.getPathSegments();
		// ComChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComCategoriaProdutoPai",uri.getPathSegments())) {
			sql += "," +  CategoriaProdutoContract.camposOrdenados();
		}
	
	
	// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComClienteInteresseCategoriaAssociada",uri.getPathSegments())) {
			sql += "," +  ClienteInteresseCategoriaContract.camposOrdenados();
		}
	
		if (existeItem("ComCategoriaProdutoProdutoPossui",uri.getPathSegments())) {
			sql += "," +  CategoriaProdutoProdutoContract.camposOrdenados();
		}
	
		if (existeItem("ComCategoriaProdutoPai",uri.getPathSegments())) {
			sql += "," +  CategoriaProdutoContract.camposOrdenados();
		}
	
		
		
		
		return sql;
	}
	private String sqlFrom(Uri uri) {
		String sql = CategoriaProdutoContract.TABLE_NAME;
		List<String> segmentos = uri.getPathSegments();
		//if (existeItem("ComEpisodioReferenteA",uri.getPathSegments())) {
		//	sql += " " +  EpisodioUsuarioContract.innerJoinEpisodio_ReferenteA();
		//}
		
		
				// ComChaveEstrangeira
  	
		if (existeItem("ComCategoriaProdutoPai",uri.getPathSegments())) {
			sql += " " +  CategoriaProdutoContract.outerJoinCategoriaProduto_Pai();
		}
	
	
	// SemChaveEstrangeira
  	
		if (existeItem("ComClienteInteresseCategoriaAssociada",uri.getPathSegments())) {
			sql += " " +  CategoriaProdutoContract.outerJoinClienteInteresseCategoria_Associada();
		}
	
		if (existeItem("ComCategoriaProdutoProdutoPossui",uri.getPathSegments())) {
			sql += " " +  CategoriaProdutoContract.outerJoinCategoriaProdutoProduto_Possui();
		}
	
		if (existeItem("ComCategoriaProdutoPai",uri.getPathSegments())) {
			sql += " " +  CategoriaProdutoContract.outerJoinCategoriaProduto_Pai();
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
	
	
	protected abstract Cursor queryListaNivel0(Uri uri, String[] projection, String sortOrder);
	
	
	
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
            case CATEGORIA_PRODUTO:
            {
                return CategoriaProdutoContract.getContentType();
            }
            case CATEGORIA_PRODUTO_ID:
            {
            	return CategoriaProdutoContract.getContentItemType();
            }
			case CATEGORIA_PRODUTO_POR_CATEGORIA_PRODUTO_PAI:
            {
	            return CategoriaProdutoContract.getContentType();
            }
		
		}	
		return null;
	}
	
	public Uri insert(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Uri returnUri;
		long idNovo = getMaxId(db)+1;
		values.put(CategoriaProdutoContract.COLUNA_CHAVE, idNovo);
		long _id = db.insert(CategoriaProdutoContract.TABLE_NAME, null, values);
		if (_id > 0) {
			//DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + CategoriaProdutoContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
			DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + values.toString() + " (id:" + _id + ")");
			returnUri = CategoriaProdutoContract.buildCategoriaProdutoUri(idNovo);
			values.put(CategoriaProdutoContract.COLUNA_OPERACAO_SINC,"I");
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
			case CATEGORIA_PRODUTO_DELETE_SINC: {
				String id = uri.getPathSegments().get(2);
				Cursor cursor = db.query(CategoriaProdutoContract.TABLE_NAME,null,CategoriaProdutoContract.COLUNA_CHAVE + " = ? ",new String[]{id},null,null,null);
				if (cursor.moveToFirst()) {
					ContentValues valores = new ContentValues();
					DatabaseUtils.cursorRowToContentValues(cursor, valores);
					linhaDelete = db.delete(CategoriaProdutoContract.TABLE_NAME, CategoriaProdutoContract.COLUNA_CHAVE + " = ? ", new String[]{id});
					DCLog.d(DCLog.DATABASE_CRUD,this,"delete " + CategoriaProdutoContract.TABLE_NAME + "(id:" + id + ")");
					valores.put(CategoriaProdutoContract.COLUNA_OPERACAO_SINC, "D");
					insereSinc(db,valores);
				}
				notificaUriRelacionadas();
				mProvider.getContext().getContentResolver().notifyChange(CategoriaProdutoContract.buildAll(), null);
				break;
			}
			case CATEGORIA_PRODUTO_DELETE_ALL_SINC: {
				linhaDelete = db.delete(CategoriaProdutoContract.TABLE_NAME_SINC, null, null);
				break;
			}
			case CATEGORIA_PRODUTO_DELETE_RECREATE: {
				linhaDelete = db.delete(CategoriaProdutoContract.TABLE_NAME, null, null);
				break;
			}
		}
		return true;
	}
	
	
	public boolean update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		linhaUpdate = db.update(CategoriaProdutoContract.TABLE_NAME, values, selection, selectionArgs);
		return true;
	}
	public boolean update(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		String selection = CategoriaProdutoContract.COLUNA_CHAVE + " = ? ";
		String dados[] = {values.get(CategoriaProdutoContract.COLUNA_CHAVE).toString()};
		linhaUpdate = db.update(CategoriaProdutoContract.TABLE_NAME, values, selection, dados);
		values.put(CategoriaProdutoContract.COLUNA_OPERACAO_SINC,"A");
		insereSinc(db,values);
		return true;
	}
	
	private void insereSinc(SQLiteDatabase db, ContentValues values) {
		db.insert(CategoriaProdutoContract.TABLE_NAME_SINC, null, values);
	}
	
	// Notificar todas as uris de entidades que possuem chave estrangeira dessa entidade.
	private void notificaUriRelacionadas() {
		// ComChaveEstrangeira
  	
		mProvider.getContext().getContentResolver().notifyChange(CategoriaProdutoContract.buildAllComCategoriaProdutoPai(), null);
		//mProvider.getContext().getContentResolver().notifyChange(CategoriaProdutoContract.buildAllSemCategoriaProdutoPai(), null);
	
	}
	private void notificaUriOperacoes() {
	
		mProvider.getContext().getContentResolver().notifyChange(CategoriaProdutoContract.buildListaNivel0(), null);
	
	}
	private void notificaUriRaiz(){
		
	}
	
	
	
	public int bulkInsert(Uri uri, ContentValues[] values) {
    	final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        db.beginTransaction();
        int returnCount = 0;
        try {
        	for (ContentValues value : values) {
				String[] args = {(String)value.get(CategoriaProdutoContract.COLUNA_CHAVE)};
				Cursor retCursor = query(null, sPorChaveSel, args, null);
				if (retCursor.moveToFirst()) {
						db.update(CategoriaProdutoContract.TABLE_NAME,value,sPorChaveSel,args);
						//DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk " + CategoriaProdutoContract.TABLE_NAME + "  " + values.toString());
						DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk id:" + value.get(CategoriaProdutoContract.COLUNA_CHAVE) + ")" + values.toString());
				} else {
					long _id = db.insert(CategoriaProdutoContract.TABLE_NAME, null, value);
					if (_id != -1) {
						//DCLog.d(DCLog.DATABASE_CRUD,this,"insert (bulk)" + CategoriaProdutoContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
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
            	long _id = db.insert(CategoriaProdutoContract.TABLE_NAME, null, value);
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
		String sql = "select max(" + CategoriaProdutoContract.COLUNA_CHAVE + ") from " + CategoriaProdutoContract.TABLE_NAME;
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