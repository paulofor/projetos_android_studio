
package  br.com.lojadigicom.coletorprecocliente.data.provider;


import br.com.lojadigicom.coletorprecocliente.data.contract.*;
import br.com.lojadigicom.coletorprecocliente.data.helper.AplicacaoDbHelper;
import android.content.ContentValues;
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

public abstract class NaturezaProdutoProvider {

	private int qtdeLinhas = 0;
	
	public static final int NATUREZA_PRODUTO = 86200;
	public static final int NATUREZA_PRODUTO_ID = 86201;
	public static final int NATUREZA_PRODUTO_SINC = 86203;
	public static final int NATUREZA_PRODUTO_E_COMPLEMENTO = 86204;
	public static final int NATUREZA_PRODUTO_ID_E_COMPLEMENTO = 86205;
	//public static final int NATUREZA_PRODUTO_OPERACAO = 86202;
	
	// deletes
	public static final int NATUREZA_PRODUTO_DELETE_ALL_SINC = 86206;
	public static final int NATUREZA_PRODUTO_DELETE_RECREATE = 86207;
	public static final int NATUREZA_PRODUTO_DELETE_SINC = 86208;
	public static final int NATUREZA_PRODUTO_E_RETIRADA = 86209;
	
	private static final String sPorChaveSel = NaturezaProdutoContract.TABLE_NAME + "." + NaturezaProdutoContract.COLUNA_CHAVE + " = ? ";
	
	
	
	public static final int NATUREZA_PRODUTO_POR_APP_PRODUTO_ATENDIDOPOR = 86220;
	public static final int COM_APP_PRODUTO = 86221;
	private static final String sPorIdAppProdutoApSelecao =
            NaturezaProdutoContract.TABLE_NAME + ".id_app_produto_ap = ? ";
	

 	public static final int LISTAATIVO = 86222;

	private ContentProvider mProvider = null;


	public void setContentProvider(ContentProvider valor) {
		mProvider = valor;
	}

	protected static final SQLiteQueryBuilder sQueryBuilder;
	static {
		sQueryBuilder = new SQLiteQueryBuilder();
		String tabelas = NaturezaProdutoContract.TABLE_NAME;
		
		//tabelas += " inner join " + AppProdutoContract.TABLE_NAME + " ";
		//tabelas += " on " + AppProdutoContract.TABLE_NAME + "." + AppProdutoContract.COLUNA_CHAVE + " = " + NaturezaProdutoContract.TABLE_NAME + "." + NaturezaProdutoContract.COLUNA_ID_APP_PRODUTO_AP; 
		
		sQueryBuilder.setTables(tabelas);
	}
	private static final SQLiteQueryBuilder sQueryBuilderSinc;
	static {
		sQueryBuilderSinc = new SQLiteQueryBuilder();
		String tabelas = NaturezaProdutoContract.TABLE_NAME_SINC;
		sQueryBuilderSinc.setTables(tabelas);
	}
	
	
	protected AplicacaoDbHelper mOpenHelper = null;
	
	public int getLinhas() {
		return qtdeLinhas;
	}
	
	public static void buildUriMatcher(UriMatcher matcher) {
		matcher.addURI(NaturezaProdutoContract.getContentAuthority(), NaturezaProdutoContract.PATH, NATUREZA_PRODUTO);
		matcher.addURI(NaturezaProdutoContract.getContentAuthority(), NaturezaProdutoContract.PATH + "/Sinc" , NATUREZA_PRODUTO_SINC);
		matcher.addURI(NaturezaProdutoContract.getContentAuthority(), NaturezaProdutoContract.PATH + "/#"    , NATUREZA_PRODUTO_ID);
	
		matcher.addURI(NaturezaProdutoContract.getContentAuthority(), NaturezaProdutoContract.PATH + "/#/" + NaturezaProdutoContract.COM_COMPLEMENTO + "/*" , NATUREZA_PRODUTO_ID_E_COMPLEMENTO);
		matcher.addURI(NaturezaProdutoContract.getContentAuthority(), NaturezaProdutoContract.PATH + "/" + NaturezaProdutoContract.COM_COMPLEMENTO + "/*" , NATUREZA_PRODUTO_E_COMPLEMENTO);
		matcher.addURI(NaturezaProdutoContract.getContentAuthority(), NaturezaProdutoContract.PATH + "/" + NaturezaProdutoContract.COM_RETIRADA + "/*" , NATUREZA_PRODUTO_E_RETIRADA);
		
		
		//matcher.addURI(AplicacaoContract.CONTENT_AUTHORITY, NaturezaProdutoContract.PATH + "/operacao/*" , NATUREZA_PRODUTO_OPERACAO);
		
		matcher.addURI(NaturezaProdutoContract.getContentAuthority(), NaturezaProdutoContract.PATH + "/#/" + AppProdutoContract.PATH, NATUREZA_PRODUTO_POR_APP_PRODUTO_ATENDIDOPOR);
		matcher.addURI(NaturezaProdutoContract.getContentAuthority(), NaturezaProdutoContract.PATH + "/ComAppProdutoAtendidoPor/" , COM_APP_PRODUTO);
		
		
		
		matcher.addURI(NaturezaProdutoContract.getContentAuthority(), NaturezaProdutoContract.PATH + "/ListaAtivo/*", LISTAATIVO);
		
		
		// Deletes
		matcher.addURI(NaturezaProdutoContract.getContentAuthority(), NaturezaProdutoContract.PATH + "/DeleteAllSinc" , 	NATUREZA_PRODUTO_DELETE_ALL_SINC);
		matcher.addURI(NaturezaProdutoContract.getContentAuthority(), NaturezaProdutoContract.PATH + "/DeleteAllRecreate" , NATUREZA_PRODUTO_DELETE_RECREATE);
		matcher.addURI(NaturezaProdutoContract.getContentAuthority(), NaturezaProdutoContract.PATH + "/DeleteSinc/#" , 		NATUREZA_PRODUTO_DELETE_SINC);
	}
	
	
	
	
	public void setAplicacaoDbHelper(AplicacaoDbHelper db) {
		mOpenHelper = db;
	}
	
	public Cursor query(UriMatcher sUriMatcher, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Query Uri:" + uri.toString());
		Cursor retCursor = null;
		switch (sUriMatcher.match(uri)) {
            case NATUREZA_PRODUTO:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: NATUREZA_PRODUTO");
                retCursor = query(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case NATUREZA_PRODUTO_SINC:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: NATUREZA_PRODUTO_SINC");
                retCursor = querySinc(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case NATUREZA_PRODUTO_ID:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: NATUREZA_PRODUTO_ID");
            	String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorChaveSel, args, sortOrder);
                break;
            }
            case NATUREZA_PRODUTO_ID_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: NATUREZA_PRODUTO_ID_E_COMPLEMENTO");
				String id = uri.getPathSegments().get(1);	
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri) +
						" where " + NaturezaProdutoContract.COLUNA_CHAVE + " = " + id;
				retCursor = queryRaw(sql);
				break;
			}
			case NATUREZA_PRODUTO_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: NATUREZA_PRODUTO_E_COMPLEMENTO");
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case NATUREZA_PRODUTO_E_RETIRADA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: NATUREZA_PRODUTO_E_RETIRADA");
				String sql = "select " +  NaturezaProdutoContract.camposOrdenados() +
						" from " + NaturezaProdutoContract.TABLE_NAME +
						sqlWhere(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case NATUREZA_PRODUTO_POR_APP_PRODUTO_ATENDIDOPOR:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: NATUREZA_PRODUTO_POR_APP_PRODUTO_ATENDIDOPOR");
	            String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorIdAppProdutoApSelecao, args, sortOrder);
                break;
            }
            case COM_APP_PRODUTO:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: COM_APP_PRODUTO");
            	String sql = "select " + NaturezaProdutoContract.camposOrdenados() + " , " +
						AppProdutoContract.camposOrdenados() +
						" from " + NaturezaProdutoContract.TABLE_NAME +
						NaturezaProdutoContract.innerJoinAppProduto_AtendidoPor() +
						getOrderBy();
                retCursor = queryRaw(sql);
				break;
            }
		
		
	 		case LISTAATIVO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: LISTAATIVO");
				retCursor = queryListaAtivo(uri,projection,sortOrder);
				break;
			}
		
		}	
        return retCursor;
	}
	
	private String sqlWhere(Uri uri) {
		String sql = " where ";
		// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("SemOportunidadeDiaPossui",uri.getPathSegments())) {
			sql += NaturezaProdutoContract.COLUNA_CHAVE + " not in (select " + 
					OportunidadeDiaContract.COLUNA_ID_NATUREZA_PRODUTO_PA + " from " +
					OportunidadeDiaContract.TABLE_NAME + ")";
		}
	
		if (existeItem("SemUsuarioPesquisaPesquisadoPor",uri.getPathSegments())) {
			sql += NaturezaProdutoContract.COLUNA_CHAVE + " not in (select " + 
					UsuarioPesquisaContract.COLUNA_ID_NATUREZA_PRODUTO_P + " from " +
					UsuarioPesquisaContract.TABLE_NAME + ")";
		}
	
		if (existeItem("SemPalavraChavePesquisaPossui",uri.getPathSegments())) {
			sql += NaturezaProdutoContract.COLUNA_CHAVE + " not in (select " + 
					PalavraChavePesquisaContract.COLUNA_ID_NATUREZA_PRODUTO_RA + " from " +
					PalavraChavePesquisaContract.TABLE_NAME + ")";
		}
	
		if (existeItem("SemProdutoClientePossui",uri.getPathSegments())) {
			sql += NaturezaProdutoContract.COLUNA_CHAVE + " not in (select " + 
					ProdutoClienteContract.COLUNA_ID_NATUREZA_PRODUTO_RA + " from " +
					ProdutoClienteContract.TABLE_NAME + ")";
		}
	
		
		return sql;
	}
	
	private String sqlSelect(Uri uri) {
		String sql = NaturezaProdutoContract.camposOrdenados();
		List<String> segmentos = uri.getPathSegments();
		// ComChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComAppProdutoAtendidoPor",uri.getPathSegments())) {
			sql += "," +  AppProdutoContract.camposOrdenados();
		}
	
	
	// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComOportunidadeDiaPossui",uri.getPathSegments())) {
			sql += "," +  OportunidadeDiaContract.camposOrdenados();
		}
	
		if (existeItem("ComUsuarioPesquisaPesquisadoPor",uri.getPathSegments())) {
			sql += "," +  UsuarioPesquisaContract.camposOrdenados();
		}
	
		if (existeItem("ComPalavraChavePesquisaPossui",uri.getPathSegments())) {
			sql += "," +  PalavraChavePesquisaContract.camposOrdenados();
		}
	
		if (existeItem("ComProdutoClientePossui",uri.getPathSegments())) {
			sql += "," +  ProdutoClienteContract.camposOrdenados();
		}
	
		
		
		
		return sql;
	}
	private String sqlFrom(Uri uri) {
		String sql = NaturezaProdutoContract.TABLE_NAME;
		List<String> segmentos = uri.getPathSegments();
		//if (existeItem("ComEpisodioReferenteA",uri.getPathSegments())) {
		//	sql += " " +  EpisodioUsuarioContract.innerJoinEpisodio_ReferenteA();
		//}
		
		
				// ComChaveEstrangeira
  	
		if (existeItem("ComAppProdutoAtendidoPor",uri.getPathSegments())) {
			sql += " " +  NaturezaProdutoContract.outerJoinAppProduto_AtendidoPor();
		}
	
	
	// SemChaveEstrangeira
  	
		if (existeItem("ComOportunidadeDiaPossui",uri.getPathSegments())) {
			sql += " " +  NaturezaProdutoContract.outerJoinOportunidadeDia_Possui();
		}
	
		if (existeItem("ComUsuarioPesquisaPesquisadoPor",uri.getPathSegments())) {
			sql += " " +  NaturezaProdutoContract.outerJoinUsuarioPesquisa_PesquisadoPor();
		}
	
		if (existeItem("ComPalavraChavePesquisaPossui",uri.getPathSegments())) {
			sql += " " +  NaturezaProdutoContract.outerJoinPalavraChavePesquisa_Possui();
		}
	
		if (existeItem("ComProdutoClientePossui",uri.getPathSegments())) {
			sql += " " +  NaturezaProdutoContract.outerJoinProdutoCliente_Possui();
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
	
	
	protected abstract Cursor queryListaAtivo(Uri uri, String[] projection, String sortOrder);
	
	
	
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
            case NATUREZA_PRODUTO:
            {
                return NaturezaProdutoContract.getContentType();
            }
            case NATUREZA_PRODUTO_ID:
            {
            	return NaturezaProdutoContract.getContentItemType();
            }
			case NATUREZA_PRODUTO_POR_APP_PRODUTO_ATENDIDOPOR:
            {
	            return NaturezaProdutoContract.getContentType();
            }
		
		}	
		return null;
	}
	
	public Uri insert(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Uri returnUri;
		long idNovo = getMaxId(db)+1;
		values.put(NaturezaProdutoContract.COLUNA_CHAVE, idNovo);
		long _id = db.insert(NaturezaProdutoContract.TABLE_NAME, null, values);
		if (_id > 0) {
			//DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + NaturezaProdutoContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
			DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + values.toString() + " (id:" + _id + ")");
			returnUri = NaturezaProdutoContract.buildNaturezaProdutoUri(idNovo);
			values.put(NaturezaProdutoContract.COLUNA_OPERACAO_SINC,"I");
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
			case NATUREZA_PRODUTO_DELETE_SINC: {
				String id = uri.getPathSegments().get(2);
				Cursor cursor = db.query(NaturezaProdutoContract.TABLE_NAME,null,NaturezaProdutoContract.COLUNA_CHAVE + " = ? ",new String[]{id},null,null,null);
				if (cursor.moveToFirst()) {
					ContentValues valores = new ContentValues();
					DatabaseUtils.cursorRowToContentValues(cursor, valores);
					linhaDelete = db.delete(NaturezaProdutoContract.TABLE_NAME, NaturezaProdutoContract.COLUNA_CHAVE + " = ? ", new String[]{id});
					DCLog.d(DCLog.DATABASE_CRUD,this,"delete " + NaturezaProdutoContract.TABLE_NAME + "(id:" + id + ")");
					valores.put(NaturezaProdutoContract.COLUNA_OPERACAO_SINC, "D");
					insereSinc(db,valores);
				}
				notificaUriRelacionadas();
				mProvider.getContext().getContentResolver().notifyChange(NaturezaProdutoContract.buildAll(), null);
				break;
			}
			case NATUREZA_PRODUTO_DELETE_ALL_SINC: {
				linhaDelete = db.delete(NaturezaProdutoContract.TABLE_NAME_SINC, null, null);
				break;
			}
			case NATUREZA_PRODUTO_DELETE_RECREATE: {
				linhaDelete = db.delete(NaturezaProdutoContract.TABLE_NAME, null, null);
				break;
			}
		}
		return true;
	}
	
	
	public boolean update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		linhaUpdate = db.update(NaturezaProdutoContract.TABLE_NAME, values, selection, selectionArgs);
		return true;
	}
	public boolean update(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		String selection = NaturezaProdutoContract.COLUNA_CHAVE + " = ? ";
		String dados[] = {values.get(NaturezaProdutoContract.COLUNA_CHAVE).toString()};
		linhaUpdate = db.update(NaturezaProdutoContract.TABLE_NAME, values, selection, dados);
		values.put(NaturezaProdutoContract.COLUNA_OPERACAO_SINC,"A");
		insereSinc(db,values);
		return true;
	}
	
	private void insereSinc(SQLiteDatabase db, ContentValues values) {
		db.insert(NaturezaProdutoContract.TABLE_NAME_SINC, null, values);
	}
	
	// Notificar todas as uris de entidades que possuem chave estrangeira dessa entidade.
	private void notificaUriRelacionadas() {
		// ComChaveEstrangeira
  	
		mProvider.getContext().getContentResolver().notifyChange(AppProdutoContract.buildAllComNaturezaProdutoAtende(), null);
		mProvider.getContext().getContentResolver().notifyChange(AppProdutoContract.buildAllSemNaturezaProdutoAtende(), null);
	
	}
	private void notificaUriOperacoes() {
	
		mProvider.getContext().getContentResolver().notifyChange(NaturezaProdutoContract.buildListaAtivo(), null);
	
	}
	private void notificaUriRaiz(){
		
	}
	
	
	
	public int bulkInsert(Uri uri, ContentValues[] values) {
    	final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        db.beginTransaction();
        int returnCount = 0;
        try {
        	for (ContentValues value : values) {
				String[] args = {(String)value.get(NaturezaProdutoContract.COLUNA_CHAVE)};
				Cursor retCursor = query(null, sPorChaveSel, args, null);
				if (retCursor.moveToFirst()) {
						db.update(NaturezaProdutoContract.TABLE_NAME,value,sPorChaveSel,args);
						//DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk " + NaturezaProdutoContract.TABLE_NAME + "  " + values.toString());
						DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk id:" + value.get(NaturezaProdutoContract.COLUNA_CHAVE) + ")" + values.toString());
				} else {
					long _id = db.insert(NaturezaProdutoContract.TABLE_NAME, null, value);
					if (_id != -1) {
						//DCLog.d(DCLog.DATABASE_CRUD,this,"insert (bulk)" + NaturezaProdutoContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
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
            	long _id = db.insert(NaturezaProdutoContract.TABLE_NAME, null, value);
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
		String sql = "select max(" + NaturezaProdutoContract.COLUNA_CHAVE + ") from " + NaturezaProdutoContract.TABLE_NAME;
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