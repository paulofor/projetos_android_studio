
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

public abstract class ClienteInteresseCategoriaProvider {

	private int qtdeLinhas = 0;
	
	public static final int CLIENTE_INTERESSE_CATEGORIA = 40800;
	public static final int CLIENTE_INTERESSE_CATEGORIA_ID = 40801;
	public static final int CLIENTE_INTERESSE_CATEGORIA_SINC = 40803;
	public static final int CLIENTE_INTERESSE_CATEGORIA_E_COMPLEMENTO = 40804;
	public static final int CLIENTE_INTERESSE_CATEGORIA_ID_E_COMPLEMENTO = 40805;
	//public static final int CLIENTE_INTERESSE_CATEGORIA_OPERACAO = 40802;
	
	// deletes
	public static final int CLIENTE_INTERESSE_CATEGORIA_DELETE_ALL_SINC = 40806;
	public static final int CLIENTE_INTERESSE_CATEGORIA_DELETE_RECREATE = 40807;
	public static final int CLIENTE_INTERESSE_CATEGORIA_DELETE_SINC = 40808;
	public static final int CLIENTE_INTERESSE_CATEGORIA_E_RETIRADA = 40809;
	
	private static final String sPorChaveSel = ClienteInteresseCategoriaContract.TABLE_NAME + "." + ClienteInteresseCategoriaContract.COLUNA_CHAVE + " = ? ";
	
	
	
	public static final int CLIENTE_INTERESSE_CATEGORIA_POR_CLIENTE_ASSOCIADA = 40820;
	public static final int COM_CLIENTE = 40821;
	private static final String sPorIdClienteASelecao =
            ClienteInteresseCategoriaContract.TABLE_NAME + ".id_cliente_a = ? ";
	
	public static final int CLIENTE_INTERESSE_CATEGORIA_POR_CATEGORIA_PRODUTO_ASSOCIADA = 40822;
	public static final int COM_CATEGORIA_PRODUTO = 40823;
	private static final String sPorIdCategoriaProdutoASelecao =
            ClienteInteresseCategoriaContract.TABLE_NAME + ".id_categoria_produto_a = ? ";
	


	private ContentProvider mProvider = null;


	public void setContentProvider(ContentProvider valor) {
		mProvider = valor;
	}

	protected static final SQLiteQueryBuilder sQueryBuilder;
	static {
		sQueryBuilder = new SQLiteQueryBuilder();
		String tabelas = ClienteInteresseCategoriaContract.TABLE_NAME;
		
		//tabelas += " inner join " + ClienteContract.TABLE_NAME + " ";
		//tabelas += " on " + ClienteContract.TABLE_NAME + "." + ClienteContract.COLUNA_CHAVE + " = " + ClienteInteresseCategoriaContract.TABLE_NAME + "." + ClienteInteresseCategoriaContract.COLUNA_ID_CLIENTE_A; 
		
		//tabelas += " inner join " + CategoriaProdutoContract.TABLE_NAME + " ";
		//tabelas += " on " + CategoriaProdutoContract.TABLE_NAME + "." + CategoriaProdutoContract.COLUNA_CHAVE + " = " + ClienteInteresseCategoriaContract.TABLE_NAME + "." + ClienteInteresseCategoriaContract.COLUNA_ID_CATEGORIA_PRODUTO_A; 
		
		sQueryBuilder.setTables(tabelas);
	}
	private static final SQLiteQueryBuilder sQueryBuilderSinc;
	static {
		sQueryBuilderSinc = new SQLiteQueryBuilder();
		String tabelas = ClienteInteresseCategoriaContract.TABLE_NAME_SINC;
		sQueryBuilderSinc.setTables(tabelas);
	}
	
	
	protected AplicacaoDbHelper mOpenHelper = null;
	
	public int getLinhas() {
		return qtdeLinhas;
	}
	
	public static void buildUriMatcher(UriMatcher matcher) {
		matcher.addURI(ClienteInteresseCategoriaContract.getContentAuthority(), ClienteInteresseCategoriaContract.PATH, CLIENTE_INTERESSE_CATEGORIA);
		matcher.addURI(ClienteInteresseCategoriaContract.getContentAuthority(), ClienteInteresseCategoriaContract.PATH + "/Sinc" , CLIENTE_INTERESSE_CATEGORIA_SINC);
		matcher.addURI(ClienteInteresseCategoriaContract.getContentAuthority(), ClienteInteresseCategoriaContract.PATH + "/#"    , CLIENTE_INTERESSE_CATEGORIA_ID);
	
		matcher.addURI(ClienteInteresseCategoriaContract.getContentAuthority(), ClienteInteresseCategoriaContract.PATH + "/#/" + ClienteInteresseCategoriaContract.COM_COMPLEMENTO + "/*" , CLIENTE_INTERESSE_CATEGORIA_ID_E_COMPLEMENTO);
		matcher.addURI(ClienteInteresseCategoriaContract.getContentAuthority(), ClienteInteresseCategoriaContract.PATH + "/" + ClienteInteresseCategoriaContract.COM_COMPLEMENTO + "/*" , CLIENTE_INTERESSE_CATEGORIA_E_COMPLEMENTO);
		matcher.addURI(ClienteInteresseCategoriaContract.getContentAuthority(), ClienteInteresseCategoriaContract.PATH + "/" + ClienteInteresseCategoriaContract.COM_RETIRADA + "/*" , CLIENTE_INTERESSE_CATEGORIA_E_RETIRADA);
		
		
		//matcher.addURI(AplicacaoContract.CONTENT_AUTHORITY, ClienteInteresseCategoriaContract.PATH + "/operacao/*" , CLIENTE_INTERESSE_CATEGORIA_OPERACAO);
		
		matcher.addURI(ClienteInteresseCategoriaContract.getContentAuthority(), ClienteInteresseCategoriaContract.PATH + "/#/" + ClienteContract.PATH, CLIENTE_INTERESSE_CATEGORIA_POR_CLIENTE_ASSOCIADA);
		matcher.addURI(ClienteInteresseCategoriaContract.getContentAuthority(), ClienteInteresseCategoriaContract.PATH + "/ComClienteAssociada/" , COM_CLIENTE);
		
		matcher.addURI(ClienteInteresseCategoriaContract.getContentAuthority(), ClienteInteresseCategoriaContract.PATH + "/#/" + CategoriaProdutoContract.PATH, CLIENTE_INTERESSE_CATEGORIA_POR_CATEGORIA_PRODUTO_ASSOCIADA);
		matcher.addURI(ClienteInteresseCategoriaContract.getContentAuthority(), ClienteInteresseCategoriaContract.PATH + "/ComCategoriaProdutoAssociada/" , COM_CATEGORIA_PRODUTO);
		
		
		
		
		// Deletes
		matcher.addURI(ClienteInteresseCategoriaContract.getContentAuthority(), ClienteInteresseCategoriaContract.PATH + "/DeleteAllSinc" , 	CLIENTE_INTERESSE_CATEGORIA_DELETE_ALL_SINC);
		matcher.addURI(ClienteInteresseCategoriaContract.getContentAuthority(), ClienteInteresseCategoriaContract.PATH + "/DeleteAllRecreate" , CLIENTE_INTERESSE_CATEGORIA_DELETE_RECREATE);
		matcher.addURI(ClienteInteresseCategoriaContract.getContentAuthority(), ClienteInteresseCategoriaContract.PATH + "/DeleteSinc/#" , 		CLIENTE_INTERESSE_CATEGORIA_DELETE_SINC);
	}
	
	
	
	
	public void setAplicacaoDbHelper(AplicacaoDbHelper db) {
		mOpenHelper = db;
	}
	
	public Cursor query(UriMatcher sUriMatcher, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Query Uri:" + uri.toString());
		Cursor retCursor = null;
		switch (sUriMatcher.match(uri)) {
            case CLIENTE_INTERESSE_CATEGORIA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CLIENTE_INTERESSE_CATEGORIA");
                retCursor = query(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case CLIENTE_INTERESSE_CATEGORIA_SINC:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CLIENTE_INTERESSE_CATEGORIA_SINC");
                retCursor = querySinc(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case CLIENTE_INTERESSE_CATEGORIA_ID:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CLIENTE_INTERESSE_CATEGORIA_ID");
            	String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorChaveSel, args, sortOrder);
                break;
            }
            case CLIENTE_INTERESSE_CATEGORIA_ID_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CLIENTE_INTERESSE_CATEGORIA_ID_E_COMPLEMENTO");
				String id = uri.getPathSegments().get(1);	
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri) +
						" where " + ClienteInteresseCategoriaContract.COLUNA_CHAVE + " = " + id;
				retCursor = queryRaw(sql);
				break;
			}
			case CLIENTE_INTERESSE_CATEGORIA_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CLIENTE_INTERESSE_CATEGORIA_E_COMPLEMENTO");
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case CLIENTE_INTERESSE_CATEGORIA_E_RETIRADA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CLIENTE_INTERESSE_CATEGORIA_E_RETIRADA");
				String sql = "select " +  ClienteInteresseCategoriaContract.camposOrdenados() +
						" from " + ClienteInteresseCategoriaContract.TABLE_NAME +
						sqlWhere(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case CLIENTE_INTERESSE_CATEGORIA_POR_CLIENTE_ASSOCIADA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CLIENTE_INTERESSE_CATEGORIA_POR_CLIENTE_ASSOCIADA");
	            String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorIdClienteASelecao, args, sortOrder);
                break;
            }
            case COM_CLIENTE:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: COM_CLIENTE");
            	String sql = "select " + ClienteInteresseCategoriaContract.camposOrdenados() + " , " +
						ClienteContract.camposOrdenados() +
						" from " + ClienteInteresseCategoriaContract.TABLE_NAME +
						ClienteInteresseCategoriaContract.innerJoinCliente_Associada() +
						getOrderBy();
                retCursor = queryRaw(sql);
				break;
            }
		
			case CLIENTE_INTERESSE_CATEGORIA_POR_CATEGORIA_PRODUTO_ASSOCIADA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CLIENTE_INTERESSE_CATEGORIA_POR_CATEGORIA_PRODUTO_ASSOCIADA");
	            String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorIdCategoriaProdutoASelecao, args, sortOrder);
                break;
            }
            case COM_CATEGORIA_PRODUTO:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: COM_CATEGORIA_PRODUTO");
            	String sql = "select " + ClienteInteresseCategoriaContract.camposOrdenados() + " , " +
						CategoriaProdutoContract.camposOrdenados() +
						" from " + ClienteInteresseCategoriaContract.TABLE_NAME +
						ClienteInteresseCategoriaContract.innerJoinCategoriaProduto_Associada() +
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
		String sql = ClienteInteresseCategoriaContract.camposOrdenados();
		List<String> segmentos = uri.getPathSegments();
		// ComChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComClienteAssociada",uri.getPathSegments())) {
			sql += "," +  ClienteContract.camposOrdenados();
		}
	
		if (existeItem("ComCategoriaProdutoAssociada",uri.getPathSegments())) {
			sql += "," +  CategoriaProdutoContract.camposOrdenados();
		}
	
	
	// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		
		
		
		return sql;
	}
	private String sqlFrom(Uri uri) {
		String sql = ClienteInteresseCategoriaContract.TABLE_NAME;
		List<String> segmentos = uri.getPathSegments();
		//if (existeItem("ComEpisodioReferenteA",uri.getPathSegments())) {
		//	sql += " " +  EpisodioUsuarioContract.innerJoinEpisodio_ReferenteA();
		//}
		
		
				// ComChaveEstrangeira
  	
		if (existeItem("ComClienteAssociada",uri.getPathSegments())) {
			sql += " " +  ClienteInteresseCategoriaContract.outerJoinCliente_Associada();
		}
	
		if (existeItem("ComCategoriaProdutoAssociada",uri.getPathSegments())) {
			sql += " " +  ClienteInteresseCategoriaContract.outerJoinCategoriaProduto_Associada();
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
            case CLIENTE_INTERESSE_CATEGORIA:
            {
                return ClienteInteresseCategoriaContract.getContentType();
            }
            case CLIENTE_INTERESSE_CATEGORIA_ID:
            {
            	return ClienteInteresseCategoriaContract.getContentItemType();
            }
			case CLIENTE_INTERESSE_CATEGORIA_POR_CLIENTE_ASSOCIADA:
            {
	            return ClienteInteresseCategoriaContract.getContentType();
            }
		
			case CLIENTE_INTERESSE_CATEGORIA_POR_CATEGORIA_PRODUTO_ASSOCIADA:
            {
	            return ClienteInteresseCategoriaContract.getContentType();
            }
		
		}	
		return null;
	}
	
	public Uri insert(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Uri returnUri;
		long idNovo = getMaxId(db)+1;
		values.put(ClienteInteresseCategoriaContract.COLUNA_CHAVE, idNovo);
		long _id = db.insert(ClienteInteresseCategoriaContract.TABLE_NAME, null, values);
		if (_id > 0) {
			//DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + ClienteInteresseCategoriaContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
			DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + values.toString() + " (id:" + _id + ")");
			returnUri = ClienteInteresseCategoriaContract.buildClienteInteresseCategoriaUri(idNovo);
			values.put(ClienteInteresseCategoriaContract.COLUNA_OPERACAO_SINC,"I");
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
			case CLIENTE_INTERESSE_CATEGORIA_DELETE_SINC: {
				String id = uri.getPathSegments().get(2);
				Cursor cursor = db.query(ClienteInteresseCategoriaContract.TABLE_NAME,null,ClienteInteresseCategoriaContract.COLUNA_CHAVE + " = ? ",new String[]{id},null,null,null);
				if (cursor.moveToFirst()) {
					ContentValues valores = new ContentValues();
					DatabaseUtils.cursorRowToContentValues(cursor, valores);
					linhaDelete = db.delete(ClienteInteresseCategoriaContract.TABLE_NAME, ClienteInteresseCategoriaContract.COLUNA_CHAVE + " = ? ", new String[]{id});
					DCLog.d(DCLog.DATABASE_CRUD,this,"delete " + ClienteInteresseCategoriaContract.TABLE_NAME + "(id:" + id + ")");
					valores.put(ClienteInteresseCategoriaContract.COLUNA_OPERACAO_SINC, "D");
					insereSinc(db,valores);
				}
				notificaUriRelacionadas();
				mProvider.getContext().getContentResolver().notifyChange(ClienteInteresseCategoriaContract.buildAll(), null);
				break;
			}
			case CLIENTE_INTERESSE_CATEGORIA_DELETE_ALL_SINC: {
				linhaDelete = db.delete(ClienteInteresseCategoriaContract.TABLE_NAME_SINC, null, null);
				break;
			}
			case CLIENTE_INTERESSE_CATEGORIA_DELETE_RECREATE: {
				linhaDelete = db.delete(ClienteInteresseCategoriaContract.TABLE_NAME, null, null);
				break;
			}
		}
		return true;
	}
	
	
	public boolean update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		DCLog.d(DCLog.DATABASE_CRUD,this,"update2 " + values.toString() );
		linhaUpdate = db.update(ClienteInteresseCategoriaContract.TABLE_NAME, values, selection, selectionArgs);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	public boolean update(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		String selection = ClienteInteresseCategoriaContract.COLUNA_CHAVE + " = ? ";
		String dados[] = {values.get(ClienteInteresseCategoriaContract.COLUNA_CHAVE).toString()};
		DCLog.d(DCLog.DATABASE_CRUD,this,"update1 " + values.toString() );
		linhaUpdate = db.update(ClienteInteresseCategoriaContract.TABLE_NAME, values, selection, dados);
		values.put(ClienteInteresseCategoriaContract.COLUNA_OPERACAO_SINC,"A");
		insereSinc(db,values);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	
	private void insereSinc(SQLiteDatabase db, ContentValues values) {
		db.insert(ClienteInteresseCategoriaContract.TABLE_NAME_SINC, null, values);
		notificaUriOperacoes();
	}
	
	protected abstract void notificaOutrasUri(ContentResolver resolver);
	
	
	// Notificar todas as uris de entidades que possuem chave estrangeira dessa entidade.
	private void notificaUriRelacionadas() {
		// ComChaveEstrangeira
  	
		mProvider.getContext().getContentResolver().notifyChange(ClienteContract.buildAllComClienteInteresseCategoriaAssociada(), null);
		mProvider.getContext().getContentResolver().notifyChange(ClienteContract.buildAllSemClienteInteresseCategoriaAssociada(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(CategoriaProdutoContract.buildAllComClienteInteresseCategoriaAssociada(), null);
		mProvider.getContext().getContentResolver().notifyChange(CategoriaProdutoContract.buildAllSemClienteInteresseCategoriaAssociada(), null);
	
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
				String[] args = {(String)value.get(ClienteInteresseCategoriaContract.COLUNA_CHAVE)};
				Cursor retCursor = query(null, sPorChaveSel, args, null);
				if (retCursor.moveToFirst()) {
						db.update(ClienteInteresseCategoriaContract.TABLE_NAME,value,sPorChaveSel,args);
						//DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk " + ClienteInteresseCategoriaContract.TABLE_NAME + "  " + values.toString());
						DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk id:" + value.get(ClienteInteresseCategoriaContract.COLUNA_CHAVE) + ")" + values.toString());
				} else {
					long _id = db.insert(ClienteInteresseCategoriaContract.TABLE_NAME, null, value);
					if (_id != -1) {
						//DCLog.d(DCLog.DATABASE_CRUD,this,"insert (bulk)" + ClienteInteresseCategoriaContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
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
            	long _id = db.insert(ClienteInteresseCategoriaContract.TABLE_NAME, null, value);
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
		String sql = "select max(" + ClienteInteresseCategoriaContract.COLUNA_CHAVE + ") from " + ClienteInteresseCategoriaContract.TABLE_NAME;
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