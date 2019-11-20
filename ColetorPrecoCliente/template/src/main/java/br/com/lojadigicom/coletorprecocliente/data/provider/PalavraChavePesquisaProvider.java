
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

public abstract class PalavraChavePesquisaProvider {

	private int qtdeLinhas = 0;
	
	public static final int PALAVRA_CHAVE_PESQUISA = 89000;
	public static final int PALAVRA_CHAVE_PESQUISA_ID = 89001;
	public static final int PALAVRA_CHAVE_PESQUISA_SINC = 89003;
	public static final int PALAVRA_CHAVE_PESQUISA_E_COMPLEMENTO = 89004;
	public static final int PALAVRA_CHAVE_PESQUISA_ID_E_COMPLEMENTO = 89005;
	//public static final int PALAVRA_CHAVE_PESQUISA_OPERACAO = 89002;
	
	// deletes
	public static final int PALAVRA_CHAVE_PESQUISA_DELETE_ALL_SINC = 89006;
	public static final int PALAVRA_CHAVE_PESQUISA_DELETE_RECREATE = 89007;
	public static final int PALAVRA_CHAVE_PESQUISA_DELETE_SINC = 89008;
	public static final int PALAVRA_CHAVE_PESQUISA_E_RETIRADA = 89009;
	
	private static final String sPorChaveSel = PalavraChavePesquisaContract.TABLE_NAME + "." + PalavraChavePesquisaContract.COLUNA_CHAVE + " = ? ";
	
	
	
	public static final int PALAVRA_CHAVE_PESQUISA_POR_NATUREZA_PRODUTO_REFERENTEA = 89020;
	public static final int COM_NATUREZA_PRODUTO = 89021;
	private static final String sPorIdNaturezaProdutoRaSelecao =
            PalavraChavePesquisaContract.TABLE_NAME + ".id_natureza_produto_ra = ? ";
	


	private ContentProvider mProvider = null;


	public void setContentProvider(ContentProvider valor) {
		mProvider = valor;
	}

	protected static final SQLiteQueryBuilder sQueryBuilder;
	static {
		sQueryBuilder = new SQLiteQueryBuilder();
		String tabelas = PalavraChavePesquisaContract.TABLE_NAME;
		
		//tabelas += " inner join " + NaturezaProdutoContract.TABLE_NAME + " ";
		//tabelas += " on " + NaturezaProdutoContract.TABLE_NAME + "." + NaturezaProdutoContract.COLUNA_CHAVE + " = " + PalavraChavePesquisaContract.TABLE_NAME + "." + PalavraChavePesquisaContract.COLUNA_ID_NATUREZA_PRODUTO_RA; 
		
		sQueryBuilder.setTables(tabelas);
	}
	private static final SQLiteQueryBuilder sQueryBuilderSinc;
	static {
		sQueryBuilderSinc = new SQLiteQueryBuilder();
		String tabelas = PalavraChavePesquisaContract.TABLE_NAME_SINC;
		sQueryBuilderSinc.setTables(tabelas);
	}
	
	
	protected AplicacaoDbHelper mOpenHelper = null;
	
	public int getLinhas() {
		return qtdeLinhas;
	}
	
	public static void buildUriMatcher(UriMatcher matcher) {
		matcher.addURI(PalavraChavePesquisaContract.getContentAuthority(), PalavraChavePesquisaContract.PATH, PALAVRA_CHAVE_PESQUISA);
		matcher.addURI(PalavraChavePesquisaContract.getContentAuthority(), PalavraChavePesquisaContract.PATH + "/Sinc" , PALAVRA_CHAVE_PESQUISA_SINC);
		matcher.addURI(PalavraChavePesquisaContract.getContentAuthority(), PalavraChavePesquisaContract.PATH + "/#"    , PALAVRA_CHAVE_PESQUISA_ID);
	
		matcher.addURI(PalavraChavePesquisaContract.getContentAuthority(), PalavraChavePesquisaContract.PATH + "/#/" + PalavraChavePesquisaContract.COM_COMPLEMENTO + "/*" , PALAVRA_CHAVE_PESQUISA_ID_E_COMPLEMENTO);
		matcher.addURI(PalavraChavePesquisaContract.getContentAuthority(), PalavraChavePesquisaContract.PATH + "/" + PalavraChavePesquisaContract.COM_COMPLEMENTO + "/*" , PALAVRA_CHAVE_PESQUISA_E_COMPLEMENTO);
		matcher.addURI(PalavraChavePesquisaContract.getContentAuthority(), PalavraChavePesquisaContract.PATH + "/" + PalavraChavePesquisaContract.COM_RETIRADA + "/*" , PALAVRA_CHAVE_PESQUISA_E_RETIRADA);
		
		
		//matcher.addURI(AplicacaoContract.CONTENT_AUTHORITY, PalavraChavePesquisaContract.PATH + "/operacao/*" , PALAVRA_CHAVE_PESQUISA_OPERACAO);
		
		matcher.addURI(PalavraChavePesquisaContract.getContentAuthority(), PalavraChavePesquisaContract.PATH + "/#/" + NaturezaProdutoContract.PATH, PALAVRA_CHAVE_PESQUISA_POR_NATUREZA_PRODUTO_REFERENTEA);
		matcher.addURI(PalavraChavePesquisaContract.getContentAuthority(), PalavraChavePesquisaContract.PATH + "/ComNaturezaProdutoReferenteA/" , COM_NATUREZA_PRODUTO);
		
		
		
		
		// Deletes
		matcher.addURI(PalavraChavePesquisaContract.getContentAuthority(), PalavraChavePesquisaContract.PATH + "/DeleteAllSinc" , 	PALAVRA_CHAVE_PESQUISA_DELETE_ALL_SINC);
		matcher.addURI(PalavraChavePesquisaContract.getContentAuthority(), PalavraChavePesquisaContract.PATH + "/DeleteAllRecreate" , PALAVRA_CHAVE_PESQUISA_DELETE_RECREATE);
		matcher.addURI(PalavraChavePesquisaContract.getContentAuthority(), PalavraChavePesquisaContract.PATH + "/DeleteSinc/#" , 		PALAVRA_CHAVE_PESQUISA_DELETE_SINC);
	}
	
	
	
	
	public void setAplicacaoDbHelper(AplicacaoDbHelper db) {
		mOpenHelper = db;
	}
	
	public Cursor query(UriMatcher sUriMatcher, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Query Uri:" + uri.toString());
		Cursor retCursor = null;
		switch (sUriMatcher.match(uri)) {
            case PALAVRA_CHAVE_PESQUISA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PALAVRA_CHAVE_PESQUISA");
                retCursor = query(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case PALAVRA_CHAVE_PESQUISA_SINC:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PALAVRA_CHAVE_PESQUISA_SINC");
                retCursor = querySinc(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case PALAVRA_CHAVE_PESQUISA_ID:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PALAVRA_CHAVE_PESQUISA_ID");
            	String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorChaveSel, args, sortOrder);
                break;
            }
            case PALAVRA_CHAVE_PESQUISA_ID_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PALAVRA_CHAVE_PESQUISA_ID_E_COMPLEMENTO");
				String id = uri.getPathSegments().get(1);	
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri) +
						" where " + PalavraChavePesquisaContract.COLUNA_CHAVE + " = " + id;
				retCursor = queryRaw(sql);
				break;
			}
			case PALAVRA_CHAVE_PESQUISA_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PALAVRA_CHAVE_PESQUISA_E_COMPLEMENTO");
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case PALAVRA_CHAVE_PESQUISA_E_RETIRADA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PALAVRA_CHAVE_PESQUISA_E_RETIRADA");
				String sql = "select " +  PalavraChavePesquisaContract.camposOrdenados() +
						" from " + PalavraChavePesquisaContract.TABLE_NAME +
						sqlWhere(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case PALAVRA_CHAVE_PESQUISA_POR_NATUREZA_PRODUTO_REFERENTEA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PALAVRA_CHAVE_PESQUISA_POR_NATUREZA_PRODUTO_REFERENTEA");
	            String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorIdNaturezaProdutoRaSelecao, args, sortOrder);
                break;
            }
            case COM_NATUREZA_PRODUTO:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: COM_NATUREZA_PRODUTO");
            	String sql = "select " + PalavraChavePesquisaContract.camposOrdenados() + " , " +
						NaturezaProdutoContract.camposOrdenados() +
						" from " + PalavraChavePesquisaContract.TABLE_NAME +
						PalavraChavePesquisaContract.innerJoinNaturezaProduto_ReferenteA() +
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
		String sql = PalavraChavePesquisaContract.camposOrdenados();
		List<String> segmentos = uri.getPathSegments();
		// ComChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComNaturezaProdutoReferenteA",uri.getPathSegments())) {
			sql += "," +  NaturezaProdutoContract.camposOrdenados();
		}
	
	
	// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		
		
		
		return sql;
	}
	private String sqlFrom(Uri uri) {
		String sql = PalavraChavePesquisaContract.TABLE_NAME;
		List<String> segmentos = uri.getPathSegments();
		//if (existeItem("ComEpisodioReferenteA",uri.getPathSegments())) {
		//	sql += " " +  EpisodioUsuarioContract.innerJoinEpisodio_ReferenteA();
		//}
		
		
				// ComChaveEstrangeira
  	
		if (existeItem("ComNaturezaProdutoReferenteA",uri.getPathSegments())) {
			sql += " " +  PalavraChavePesquisaContract.outerJoinNaturezaProduto_ReferenteA();
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
            case PALAVRA_CHAVE_PESQUISA:
            {
                return PalavraChavePesquisaContract.getContentType();
            }
            case PALAVRA_CHAVE_PESQUISA_ID:
            {
            	return PalavraChavePesquisaContract.getContentItemType();
            }
			case PALAVRA_CHAVE_PESQUISA_POR_NATUREZA_PRODUTO_REFERENTEA:
            {
	            return PalavraChavePesquisaContract.getContentType();
            }
		
		}	
		return null;
	}
	
	public Uri insert(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Uri returnUri;
		long idNovo = getMaxId(db)+1;
		values.put(PalavraChavePesquisaContract.COLUNA_CHAVE, idNovo);
		long _id = db.insert(PalavraChavePesquisaContract.TABLE_NAME, null, values);
		if (_id > 0) {
			//DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + PalavraChavePesquisaContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
			DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + values.toString() + " (id:" + _id + ")");
			returnUri = PalavraChavePesquisaContract.buildPalavraChavePesquisaUri(idNovo);
			values.put(PalavraChavePesquisaContract.COLUNA_OPERACAO_SINC,"I");
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
			case PALAVRA_CHAVE_PESQUISA_DELETE_SINC: {
				String id = uri.getPathSegments().get(2);
				Cursor cursor = db.query(PalavraChavePesquisaContract.TABLE_NAME,null,PalavraChavePesquisaContract.COLUNA_CHAVE + " = ? ",new String[]{id},null,null,null);
				if (cursor.moveToFirst()) {
					ContentValues valores = new ContentValues();
					DatabaseUtils.cursorRowToContentValues(cursor, valores);
					linhaDelete = db.delete(PalavraChavePesquisaContract.TABLE_NAME, PalavraChavePesquisaContract.COLUNA_CHAVE + " = ? ", new String[]{id});
					DCLog.d(DCLog.DATABASE_CRUD,this,"delete " + PalavraChavePesquisaContract.TABLE_NAME + "(id:" + id + ")");
					valores.put(PalavraChavePesquisaContract.COLUNA_OPERACAO_SINC, "D");
					insereSinc(db,valores);
				}
				notificaUriRelacionadas();
				mProvider.getContext().getContentResolver().notifyChange(PalavraChavePesquisaContract.buildAll(), null);
				break;
			}
			case PALAVRA_CHAVE_PESQUISA_DELETE_ALL_SINC: {
				linhaDelete = db.delete(PalavraChavePesquisaContract.TABLE_NAME_SINC, null, null);
				break;
			}
			case PALAVRA_CHAVE_PESQUISA_DELETE_RECREATE: {
				linhaDelete = db.delete(PalavraChavePesquisaContract.TABLE_NAME, null, null);
				break;
			}
		}
		return true;
	}
	
	
	public boolean update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		linhaUpdate = db.update(PalavraChavePesquisaContract.TABLE_NAME, values, selection, selectionArgs);
		return true;
	}
	public boolean update(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		String selection = PalavraChavePesquisaContract.COLUNA_CHAVE + " = ? ";
		String dados[] = {values.get(PalavraChavePesquisaContract.COLUNA_CHAVE).toString()};
		linhaUpdate = db.update(PalavraChavePesquisaContract.TABLE_NAME, values, selection, dados);
		values.put(PalavraChavePesquisaContract.COLUNA_OPERACAO_SINC,"A");
		insereSinc(db,values);
		return true;
	}
	
	private void insereSinc(SQLiteDatabase db, ContentValues values) {
		db.insert(PalavraChavePesquisaContract.TABLE_NAME_SINC, null, values);
	}
	
	// Notificar todas as uris de entidades que possuem chave estrangeira dessa entidade.
	private void notificaUriRelacionadas() {
		// ComChaveEstrangeira
  	
		mProvider.getContext().getContentResolver().notifyChange(NaturezaProdutoContract.buildAllComPalavraChavePesquisaPossui(), null);
		mProvider.getContext().getContentResolver().notifyChange(NaturezaProdutoContract.buildAllSemPalavraChavePesquisaPossui(), null);
	
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
				String[] args = {(String)value.get(PalavraChavePesquisaContract.COLUNA_CHAVE)};
				Cursor retCursor = query(null, sPorChaveSel, args, null);
				if (retCursor.moveToFirst()) {
						db.update(PalavraChavePesquisaContract.TABLE_NAME,value,sPorChaveSel,args);
						//DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk " + PalavraChavePesquisaContract.TABLE_NAME + "  " + values.toString());
						DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk id:" + value.get(PalavraChavePesquisaContract.COLUNA_CHAVE) + ")" + values.toString());
				} else {
					long _id = db.insert(PalavraChavePesquisaContract.TABLE_NAME, null, value);
					if (_id != -1) {
						//DCLog.d(DCLog.DATABASE_CRUD,this,"insert (bulk)" + PalavraChavePesquisaContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
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
            	long _id = db.insert(PalavraChavePesquisaContract.TABLE_NAME, null, value);
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
		String sql = "select max(" + PalavraChavePesquisaContract.COLUNA_CHAVE + ") from " + PalavraChavePesquisaContract.TABLE_NAME;
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