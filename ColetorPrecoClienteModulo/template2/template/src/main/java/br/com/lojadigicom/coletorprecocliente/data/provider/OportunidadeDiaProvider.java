
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

public abstract class OportunidadeDiaProvider {

	private int qtdeLinhas = 0;
	
	public static final int OPORTUNIDADE_DIA = 86300;
	public static final int OPORTUNIDADE_DIA_ID = 86301;
	public static final int OPORTUNIDADE_DIA_SINC = 86303;
	public static final int OPORTUNIDADE_DIA_E_COMPLEMENTO = 86304;
	public static final int OPORTUNIDADE_DIA_ID_E_COMPLEMENTO = 86305;
	//public static final int OPORTUNIDADE_DIA_OPERACAO = 86302;
	
	// deletes
	public static final int OPORTUNIDADE_DIA_DELETE_ALL_SINC = 86306;
	public static final int OPORTUNIDADE_DIA_DELETE_RECREATE = 86307;
	public static final int OPORTUNIDADE_DIA_DELETE_SINC = 86308;
	public static final int OPORTUNIDADE_DIA_E_RETIRADA = 86309;
	
	private static final String sPorChaveSel = OportunidadeDiaContract.TABLE_NAME + "." + OportunidadeDiaContract.COLUNA_CHAVE + " = ? ";
	
	
	
	public static final int OPORTUNIDADE_DIA_POR_NATUREZA_PRODUTO_PERTENCEA = 86320;
	public static final int COM_NATUREZA_PRODUTO = 86321;
	private static final String sPorIdNaturezaProdutoPaSelecao =
            OportunidadeDiaContract.TABLE_NAME + ".id_natureza_produto_pa = ? ";
	


	private ContentProvider mProvider = null;


	public void setContentProvider(ContentProvider valor) {
		mProvider = valor;
	}

	protected static final SQLiteQueryBuilder sQueryBuilder;
	static {
		sQueryBuilder = new SQLiteQueryBuilder();
		String tabelas = OportunidadeDiaContract.TABLE_NAME;
		
		//tabelas += " inner join " + NaturezaProdutoContract.TABLE_NAME + " ";
		//tabelas += " on " + NaturezaProdutoContract.TABLE_NAME + "." + NaturezaProdutoContract.COLUNA_CHAVE + " = " + OportunidadeDiaContract.TABLE_NAME + "." + OportunidadeDiaContract.COLUNA_ID_NATUREZA_PRODUTO_PA; 
		
		sQueryBuilder.setTables(tabelas);
	}
	private static final SQLiteQueryBuilder sQueryBuilderSinc;
	static {
		sQueryBuilderSinc = new SQLiteQueryBuilder();
		String tabelas = OportunidadeDiaContract.TABLE_NAME_SINC;
		sQueryBuilderSinc.setTables(tabelas);
	}
	
	
	protected AplicacaoDbHelper mOpenHelper = null;
	
	public int getLinhas() {
		return qtdeLinhas;
	}
	
	public static void buildUriMatcher(UriMatcher matcher) {
		matcher.addURI(OportunidadeDiaContract.getContentAuthority(), OportunidadeDiaContract.PATH, OPORTUNIDADE_DIA);
		matcher.addURI(OportunidadeDiaContract.getContentAuthority(), OportunidadeDiaContract.PATH + "/Sinc" , OPORTUNIDADE_DIA_SINC);
		matcher.addURI(OportunidadeDiaContract.getContentAuthority(), OportunidadeDiaContract.PATH + "/#"    , OPORTUNIDADE_DIA_ID);
	
		matcher.addURI(OportunidadeDiaContract.getContentAuthority(), OportunidadeDiaContract.PATH + "/#/" + OportunidadeDiaContract.COM_COMPLEMENTO + "/*" , OPORTUNIDADE_DIA_ID_E_COMPLEMENTO);
		matcher.addURI(OportunidadeDiaContract.getContentAuthority(), OportunidadeDiaContract.PATH + "/" + OportunidadeDiaContract.COM_COMPLEMENTO + "/*" , OPORTUNIDADE_DIA_E_COMPLEMENTO);
		matcher.addURI(OportunidadeDiaContract.getContentAuthority(), OportunidadeDiaContract.PATH + "/" + OportunidadeDiaContract.COM_RETIRADA + "/*" , OPORTUNIDADE_DIA_E_RETIRADA);
		
		
		//matcher.addURI(AplicacaoContract.CONTENT_AUTHORITY, OportunidadeDiaContract.PATH + "/operacao/*" , OPORTUNIDADE_DIA_OPERACAO);
		
		matcher.addURI(OportunidadeDiaContract.getContentAuthority(), OportunidadeDiaContract.PATH + "/#/" + NaturezaProdutoContract.PATH, OPORTUNIDADE_DIA_POR_NATUREZA_PRODUTO_PERTENCEA);
		matcher.addURI(OportunidadeDiaContract.getContentAuthority(), OportunidadeDiaContract.PATH + "/ComNaturezaProdutoPertenceA/" , COM_NATUREZA_PRODUTO);
		
		
		
		
		// Deletes
		matcher.addURI(OportunidadeDiaContract.getContentAuthority(), OportunidadeDiaContract.PATH + "/DeleteAllSinc" , 	OPORTUNIDADE_DIA_DELETE_ALL_SINC);
		matcher.addURI(OportunidadeDiaContract.getContentAuthority(), OportunidadeDiaContract.PATH + "/DeleteAllRecreate" , OPORTUNIDADE_DIA_DELETE_RECREATE);
		matcher.addURI(OportunidadeDiaContract.getContentAuthority(), OportunidadeDiaContract.PATH + "/DeleteSinc/#" , 		OPORTUNIDADE_DIA_DELETE_SINC);
	}
	
	
	
	
	public void setAplicacaoDbHelper(AplicacaoDbHelper db) {
		mOpenHelper = db;
	}
	
	public Cursor query(UriMatcher sUriMatcher, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Query Uri:" + uri.toString());
		Cursor retCursor = null;
		switch (sUriMatcher.match(uri)) {
            case OPORTUNIDADE_DIA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: OPORTUNIDADE_DIA");
                retCursor = query(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case OPORTUNIDADE_DIA_SINC:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: OPORTUNIDADE_DIA_SINC");
                retCursor = querySinc(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case OPORTUNIDADE_DIA_ID:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: OPORTUNIDADE_DIA_ID");
            	String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorChaveSel, args, sortOrder);
                break;
            }
            case OPORTUNIDADE_DIA_ID_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: OPORTUNIDADE_DIA_ID_E_COMPLEMENTO");
				String id = uri.getPathSegments().get(1);	
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri) +
						" where " + OportunidadeDiaContract.COLUNA_CHAVE + " = " + id;
				retCursor = queryRaw(sql);
				break;
			}
			case OPORTUNIDADE_DIA_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: OPORTUNIDADE_DIA_E_COMPLEMENTO");
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case OPORTUNIDADE_DIA_E_RETIRADA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: OPORTUNIDADE_DIA_E_RETIRADA");
				String sql = "select " +  OportunidadeDiaContract.camposOrdenados() +
						" from " + OportunidadeDiaContract.TABLE_NAME +
						sqlWhere(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case OPORTUNIDADE_DIA_POR_NATUREZA_PRODUTO_PERTENCEA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: OPORTUNIDADE_DIA_POR_NATUREZA_PRODUTO_PERTENCEA");
	            String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorIdNaturezaProdutoPaSelecao, args, sortOrder);
                break;
            }
            case COM_NATUREZA_PRODUTO:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: COM_NATUREZA_PRODUTO");
            	String sql = "select " + OportunidadeDiaContract.camposOrdenados() + " , " +
						NaturezaProdutoContract.camposOrdenados() +
						" from " + OportunidadeDiaContract.TABLE_NAME +
						OportunidadeDiaContract.innerJoinNaturezaProduto_PertenceA() +
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
		String sql = OportunidadeDiaContract.camposOrdenados();
		List<String> segmentos = uri.getPathSegments();
		// ComChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComNaturezaProdutoPertenceA",uri.getPathSegments())) {
			sql += "," +  NaturezaProdutoContract.camposOrdenados();
		}
	
	
	// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		
		
		
		return sql;
	}
	private String sqlFrom(Uri uri) {
		String sql = OportunidadeDiaContract.TABLE_NAME;
		List<String> segmentos = uri.getPathSegments();
		//if (existeItem("ComEpisodioReferenteA",uri.getPathSegments())) {
		//	sql += " " +  EpisodioUsuarioContract.innerJoinEpisodio_ReferenteA();
		//}
		
		
				// ComChaveEstrangeira
  	
		if (existeItem("ComNaturezaProdutoPertenceA",uri.getPathSegments())) {
			sql += " " +  OportunidadeDiaContract.outerJoinNaturezaProduto_PertenceA();
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
            case OPORTUNIDADE_DIA:
            {
                return OportunidadeDiaContract.getContentType();
            }
            case OPORTUNIDADE_DIA_ID:
            {
            	return OportunidadeDiaContract.getContentItemType();
            }
			case OPORTUNIDADE_DIA_POR_NATUREZA_PRODUTO_PERTENCEA:
            {
	            return OportunidadeDiaContract.getContentType();
            }
		
		}	
		return null;
	}
	
	public Uri insert(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Uri returnUri;
		long idNovo = getMaxId(db)+1;
		values.put(OportunidadeDiaContract.COLUNA_CHAVE, idNovo);
		long _id = db.insert(OportunidadeDiaContract.TABLE_NAME, null, values);
		if (_id > 0) {
			//DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + OportunidadeDiaContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
			DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + values.toString() + " (id:" + _id + ")");
			returnUri = OportunidadeDiaContract.buildOportunidadeDiaUri(idNovo);
			values.put(OportunidadeDiaContract.COLUNA_OPERACAO_SINC,"I");
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
			case OPORTUNIDADE_DIA_DELETE_SINC: {
				String id = uri.getPathSegments().get(2);
				Cursor cursor = db.query(OportunidadeDiaContract.TABLE_NAME,null,OportunidadeDiaContract.COLUNA_CHAVE + " = ? ",new String[]{id},null,null,null);
				if (cursor.moveToFirst()) {
					ContentValues valores = new ContentValues();
					DatabaseUtils.cursorRowToContentValues(cursor, valores);
					linhaDelete = db.delete(OportunidadeDiaContract.TABLE_NAME, OportunidadeDiaContract.COLUNA_CHAVE + " = ? ", new String[]{id});
					DCLog.d(DCLog.DATABASE_CRUD,this,"delete " + OportunidadeDiaContract.TABLE_NAME + "(id:" + id + ")");
					valores.put(OportunidadeDiaContract.COLUNA_OPERACAO_SINC, "D");
					insereSinc(db,valores);
				}
				notificaUriRelacionadas();
				mProvider.getContext().getContentResolver().notifyChange(OportunidadeDiaContract.buildAll(), null);
				break;
			}
			case OPORTUNIDADE_DIA_DELETE_ALL_SINC: {
				linhaDelete = db.delete(OportunidadeDiaContract.TABLE_NAME_SINC, null, null);
				break;
			}
			case OPORTUNIDADE_DIA_DELETE_RECREATE: {
				linhaDelete = db.delete(OportunidadeDiaContract.TABLE_NAME, null, null);
				break;
			}
		}
		return true;
	}
	
	
	public boolean update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		linhaUpdate = db.update(OportunidadeDiaContract.TABLE_NAME, values, selection, selectionArgs);
		return true;
	}
	public boolean update(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		String selection = OportunidadeDiaContract.COLUNA_CHAVE + " = ? ";
		String dados[] = {values.get(OportunidadeDiaContract.COLUNA_CHAVE).toString()};
		linhaUpdate = db.update(OportunidadeDiaContract.TABLE_NAME, values, selection, dados);
		values.put(OportunidadeDiaContract.COLUNA_OPERACAO_SINC,"A");
		insereSinc(db,values);
		return true;
	}
	
	private void insereSinc(SQLiteDatabase db, ContentValues values) {
		db.insert(OportunidadeDiaContract.TABLE_NAME_SINC, null, values);
	}
	
	// Notificar todas as uris de entidades que possuem chave estrangeira dessa entidade.
	private void notificaUriRelacionadas() {
		// ComChaveEstrangeira
  	
		mProvider.getContext().getContentResolver().notifyChange(NaturezaProdutoContract.buildAllComOportunidadeDiaPossui(), null);
		mProvider.getContext().getContentResolver().notifyChange(NaturezaProdutoContract.buildAllSemOportunidadeDiaPossui(), null);
	
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
				String[] args = {(String)value.get(OportunidadeDiaContract.COLUNA_CHAVE)};
				Cursor retCursor = query(null, sPorChaveSel, args, null);
				if (retCursor.moveToFirst()) {
						db.update(OportunidadeDiaContract.TABLE_NAME,value,sPorChaveSel,args);
						//DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk " + OportunidadeDiaContract.TABLE_NAME + "  " + values.toString());
						DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk id:" + value.get(OportunidadeDiaContract.COLUNA_CHAVE) + ")" + values.toString());
				} else {
					long _id = db.insert(OportunidadeDiaContract.TABLE_NAME, null, value);
					if (_id != -1) {
						//DCLog.d(DCLog.DATABASE_CRUD,this,"insert (bulk)" + OportunidadeDiaContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
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
            	long _id = db.insert(OportunidadeDiaContract.TABLE_NAME, null, value);
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
		String sql = "select max(" + OportunidadeDiaContract.COLUNA_CHAVE + ") from " + OportunidadeDiaContract.TABLE_NAME;
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