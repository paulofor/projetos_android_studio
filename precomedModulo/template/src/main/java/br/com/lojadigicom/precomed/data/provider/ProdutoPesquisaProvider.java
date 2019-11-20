
package  br.com.lojadigicom.precomed.data.provider;


import br.com.lojadigicom.precomed.data.contract.*;
import br.com.lojadigicom.precomed.data.helper.AplicacaoDbHelper;
import android.content.ContentValues;
import android.content.ContentResolver;
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

public abstract class ProdutoPesquisaProvider {

	private int qtdeLinhas = 0;
	
	public static final int PRODUTO_PESQUISA = 78600;
	public static final int PRODUTO_PESQUISA_ID = 78601;
	public static final int PRODUTO_PESQUISA_SINC = 78603;
	public static final int PRODUTO_PESQUISA_E_COMPLEMENTO = 78604;
	public static final int PRODUTO_PESQUISA_ID_E_COMPLEMENTO = 78605;
	//public static final int PRODUTO_PESQUISA_OPERACAO = 78602;
	
	// deletes
	public static final int PRODUTO_PESQUISA_DELETE_ALL_SINC = 78606;
	public static final int PRODUTO_PESQUISA_DELETE_RECREATE = 78607;
	public static final int PRODUTO_PESQUISA_DELETE_SINC = 78608;
	public static final int PRODUTO_PESQUISA_E_RETIRADA = 78609;
	
	private static final String sPorChaveSel = ProdutoPesquisaContract.TABLE_NAME + "." + ProdutoPesquisaContract.COLUNA_CHAVE + " = ? ";
	
	
	
	public static final int PRODUTO_PESQUISA_POR_MODELO_PRODUTO_REFERENTEA = 78620;
	public static final int COM_MODELO_PRODUTO = 78621;
	private static final String sPorIdModeloProdutoRaSelecao =
            ProdutoPesquisaContract.TABLE_NAME + ".id_modelo_produto_ra = ? ";
	


	private ContentProvider mProvider = null;


	public void setContentProvider(ContentProvider valor) {
		mProvider = valor;
	}

	protected static final SQLiteQueryBuilder sQueryBuilder;
	static {
		sQueryBuilder = new SQLiteQueryBuilder();
		String tabelas = ProdutoPesquisaContract.TABLE_NAME;
		
		//tabelas += " inner join " + ModeloProdutoContract.TABLE_NAME + " ";
		//tabelas += " on " + ModeloProdutoContract.TABLE_NAME + "." + ModeloProdutoContract.COLUNA_CHAVE + " = " + ProdutoPesquisaContract.TABLE_NAME + "." + ProdutoPesquisaContract.COLUNA_ID_MODELO_PRODUTO_RA; 
		
		sQueryBuilder.setTables(tabelas);
	}
	private static final SQLiteQueryBuilder sQueryBuilderSinc;
	static {
		sQueryBuilderSinc = new SQLiteQueryBuilder();
		String tabelas = ProdutoPesquisaContract.TABLE_NAME_SINC;
		sQueryBuilderSinc.setTables(tabelas);
	}
	
	
	protected AplicacaoDbHelper mOpenHelper = null;
	
	public int getLinhas() {
		return qtdeLinhas;
	}
	
	public static void buildUriMatcher(UriMatcher matcher) {
		matcher.addURI(ProdutoPesquisaContract.getContentAuthority(), ProdutoPesquisaContract.PATH, PRODUTO_PESQUISA);
		matcher.addURI(ProdutoPesquisaContract.getContentAuthority(), ProdutoPesquisaContract.PATH + "/Sinc" , PRODUTO_PESQUISA_SINC);
		matcher.addURI(ProdutoPesquisaContract.getContentAuthority(), ProdutoPesquisaContract.PATH + "/#"    , PRODUTO_PESQUISA_ID);
	
		matcher.addURI(ProdutoPesquisaContract.getContentAuthority(), ProdutoPesquisaContract.PATH + "/#/" + ProdutoPesquisaContract.COM_COMPLEMENTO + "/*" , PRODUTO_PESQUISA_ID_E_COMPLEMENTO);
		matcher.addURI(ProdutoPesquisaContract.getContentAuthority(), ProdutoPesquisaContract.PATH + "/" + ProdutoPesquisaContract.COM_COMPLEMENTO + "/*" , PRODUTO_PESQUISA_E_COMPLEMENTO);
		matcher.addURI(ProdutoPesquisaContract.getContentAuthority(), ProdutoPesquisaContract.PATH + "/" + ProdutoPesquisaContract.COM_RETIRADA + "/*" , PRODUTO_PESQUISA_E_RETIRADA);
		
		
		//matcher.addURI(AplicacaoContract.CONTENT_AUTHORITY, ProdutoPesquisaContract.PATH + "/operacao/*" , PRODUTO_PESQUISA_OPERACAO);
		
		matcher.addURI(ProdutoPesquisaContract.getContentAuthority(), ProdutoPesquisaContract.PATH + "/#/" + ModeloProdutoContract.PATH, PRODUTO_PESQUISA_POR_MODELO_PRODUTO_REFERENTEA);
		matcher.addURI(ProdutoPesquisaContract.getContentAuthority(), ProdutoPesquisaContract.PATH + "/ComModeloProdutoReferenteA/" , COM_MODELO_PRODUTO);
		
		
		
		
		// Deletes
		matcher.addURI(ProdutoPesquisaContract.getContentAuthority(), ProdutoPesquisaContract.PATH + "/DeleteAllSinc" , 	PRODUTO_PESQUISA_DELETE_ALL_SINC);
		matcher.addURI(ProdutoPesquisaContract.getContentAuthority(), ProdutoPesquisaContract.PATH + "/DeleteAllRecreate" , PRODUTO_PESQUISA_DELETE_RECREATE);
		matcher.addURI(ProdutoPesquisaContract.getContentAuthority(), ProdutoPesquisaContract.PATH + "/DeleteSinc/#" , 		PRODUTO_PESQUISA_DELETE_SINC);
	}
	
	
	
	
	public void setAplicacaoDbHelper(AplicacaoDbHelper db) {
		mOpenHelper = db;
	}
	
	public Cursor query(UriMatcher sUriMatcher, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Query Uri:" + uri.toString());
		Cursor retCursor = null;
		switch (sUriMatcher.match(uri)) {
            case PRODUTO_PESQUISA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_PESQUISA");
                retCursor = query(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case PRODUTO_PESQUISA_SINC:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_PESQUISA_SINC");
                retCursor = querySinc(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case PRODUTO_PESQUISA_ID:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_PESQUISA_ID");
            	String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorChaveSel, args, sortOrder);
                break;
            }
            case PRODUTO_PESQUISA_ID_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_PESQUISA_ID_E_COMPLEMENTO");
				String id = uri.getPathSegments().get(1);	
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri) +
						" where " + ProdutoPesquisaContract.COLUNA_CHAVE + " = " + id;
				retCursor = queryRaw(sql);
				break;
			}
			case PRODUTO_PESQUISA_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_PESQUISA_E_COMPLEMENTO");
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case PRODUTO_PESQUISA_E_RETIRADA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_PESQUISA_E_RETIRADA");
				String sql = "select " +  ProdutoPesquisaContract.camposOrdenados() +
						" from " + ProdutoPesquisaContract.TABLE_NAME +
						sqlWhere(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case PRODUTO_PESQUISA_POR_MODELO_PRODUTO_REFERENTEA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_PESQUISA_POR_MODELO_PRODUTO_REFERENTEA");
	            String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorIdModeloProdutoRaSelecao, args, sortOrder);
                break;
            }
            case COM_MODELO_PRODUTO:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: COM_MODELO_PRODUTO");
            	String sql = "select " + ProdutoPesquisaContract.camposOrdenados() + " , " +
						ModeloProdutoContract.camposOrdenados() +
						" from " + ProdutoPesquisaContract.TABLE_NAME +
						ProdutoPesquisaContract.innerJoinModeloProduto_ReferenteA() +
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
		String sql = ProdutoPesquisaContract.camposOrdenados();
		List<String> segmentos = uri.getPathSegments();
		// ComChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComModeloProdutoReferenteA",uri.getPathSegments())) {
			sql += "," +  ModeloProdutoContract.camposOrdenados();
		}
	
	
	// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		
		
		
		return sql;
	}
	private String sqlFrom(Uri uri) {
		String sql = ProdutoPesquisaContract.TABLE_NAME;
		List<String> segmentos = uri.getPathSegments();
		//if (existeItem("ComEpisodioReferenteA",uri.getPathSegments())) {
		//	sql += " " +  EpisodioUsuarioContract.innerJoinEpisodio_ReferenteA();
		//}
		
		
				// ComChaveEstrangeira
  	
		if (existeItem("ComModeloProdutoReferenteA",uri.getPathSegments())) {
			sql += " " +  ProdutoPesquisaContract.outerJoinModeloProduto_ReferenteA();
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
            case PRODUTO_PESQUISA:
            {
                return ProdutoPesquisaContract.getContentType();
            }
            case PRODUTO_PESQUISA_ID:
            {
            	return ProdutoPesquisaContract.getContentItemType();
            }
			case PRODUTO_PESQUISA_POR_MODELO_PRODUTO_REFERENTEA:
            {
	            return ProdutoPesquisaContract.getContentType();
            }
		
		}	
		return null;
	}
	
	public Uri insert(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Uri returnUri;
		long idNovo = getMaxId(db)+1;
		values.put(ProdutoPesquisaContract.COLUNA_CHAVE, idNovo);
		long _id = db.insert(ProdutoPesquisaContract.TABLE_NAME, null, values);
		if (_id > 0) {
			//DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + ProdutoPesquisaContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
			DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + values.toString() + " (id:" + _id + ")");
			returnUri = ProdutoPesquisaContract.buildProdutoPesquisaUri(idNovo);
			values.put(ProdutoPesquisaContract.COLUNA_OPERACAO_SINC,"I");
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
			case PRODUTO_PESQUISA_DELETE_SINC: {
				String id = uri.getPathSegments().get(2);
				Cursor cursor = db.query(ProdutoPesquisaContract.TABLE_NAME,null,ProdutoPesquisaContract.COLUNA_CHAVE + " = ? ",new String[]{id},null,null,null);
				if (cursor.moveToFirst()) {
					ContentValues valores = new ContentValues();
					DatabaseUtils.cursorRowToContentValues(cursor, valores);
					linhaDelete = db.delete(ProdutoPesquisaContract.TABLE_NAME, ProdutoPesquisaContract.COLUNA_CHAVE + " = ? ", new String[]{id});
					DCLog.d(DCLog.DATABASE_CRUD,this,"delete " + ProdutoPesquisaContract.TABLE_NAME + "(id:" + id + ")");
					valores.put(ProdutoPesquisaContract.COLUNA_OPERACAO_SINC, "D");
					insereSinc(db,valores);
				}
				notificaUriRelacionadas();
				mProvider.getContext().getContentResolver().notifyChange(ProdutoPesquisaContract.buildAll(), null);
				break;
			}
			case PRODUTO_PESQUISA_DELETE_ALL_SINC: {
				linhaDelete = db.delete(ProdutoPesquisaContract.TABLE_NAME_SINC, null, null);
				break;
			}
			case PRODUTO_PESQUISA_DELETE_RECREATE: {
				linhaDelete = db.delete(ProdutoPesquisaContract.TABLE_NAME, null, null);
				break;
			}
		}
		return true;
	}
	
	
	public boolean update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		DCLog.d(DCLog.DATABASE_CRUD,this,"update2 " + values.toString() );
		linhaUpdate = db.update(ProdutoPesquisaContract.TABLE_NAME, values, selection, selectionArgs);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	public boolean update(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		String selection = ProdutoPesquisaContract.COLUNA_CHAVE + " = ? ";
		String dados[] = {values.get(ProdutoPesquisaContract.COLUNA_CHAVE).toString()};
		DCLog.d(DCLog.DATABASE_CRUD,this,"update1 " + values.toString() );
		linhaUpdate = db.update(ProdutoPesquisaContract.TABLE_NAME, values, selection, dados);
		values.put(ProdutoPesquisaContract.COLUNA_OPERACAO_SINC,"A");
		insereSinc(db,values);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	
	private void insereSinc(SQLiteDatabase db, ContentValues values) {
		db.insert(ProdutoPesquisaContract.TABLE_NAME_SINC, null, values);
	}
	
	protected abstract void notificaOutrasUri(ContentResolver resolver);
	
	
	// Notificar todas as uris de entidades que possuem chave estrangeira dessa entidade.
	private void notificaUriRelacionadas() {
		// ComChaveEstrangeira
  	
		mProvider.getContext().getContentResolver().notifyChange(ModeloProdutoContract.buildAllComProdutoPesquisaViabiliza(), null);
		mProvider.getContext().getContentResolver().notifyChange(ModeloProdutoContract.buildAllSemProdutoPesquisaViabiliza(), null);
	
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
				String[] args = {(String)value.get(ProdutoPesquisaContract.COLUNA_CHAVE)};
				Cursor retCursor = query(null, sPorChaveSel, args, null);
				if (retCursor.moveToFirst()) {
						db.update(ProdutoPesquisaContract.TABLE_NAME,value,sPorChaveSel,args);
						//DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk " + ProdutoPesquisaContract.TABLE_NAME + "  " + values.toString());
						DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk id:" + value.get(ProdutoPesquisaContract.COLUNA_CHAVE) + ")" + values.toString());
				} else {
					long _id = db.insert(ProdutoPesquisaContract.TABLE_NAME, null, value);
					if (_id != -1) {
						//DCLog.d(DCLog.DATABASE_CRUD,this,"insert (bulk)" + ProdutoPesquisaContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
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
            	long _id = db.insert(ProdutoPesquisaContract.TABLE_NAME, null, value);
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
		String sql = "select max(" + ProdutoPesquisaContract.COLUNA_CHAVE + ") from " + ProdutoPesquisaContract.TABLE_NAME;
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