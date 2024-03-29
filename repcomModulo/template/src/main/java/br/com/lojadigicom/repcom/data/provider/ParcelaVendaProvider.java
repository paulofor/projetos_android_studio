
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

public abstract class ParcelaVendaProvider {

	private int qtdeLinhas = 0;
	
	public static final int PARCELA_VENDA = 40600;
	public static final int PARCELA_VENDA_ID = 40601;
	public static final int PARCELA_VENDA_SINC = 40603;
	public static final int PARCELA_VENDA_E_COMPLEMENTO = 40604;
	public static final int PARCELA_VENDA_ID_E_COMPLEMENTO = 40605;
	//public static final int PARCELA_VENDA_OPERACAO = 40602;
	
	// deletes
	public static final int PARCELA_VENDA_DELETE_ALL_SINC = 40606;
	public static final int PARCELA_VENDA_DELETE_RECREATE = 40607;
	public static final int PARCELA_VENDA_DELETE_SINC = 40608;
	public static final int PARCELA_VENDA_E_RETIRADA = 40609;
	
	private static final String sPorChaveSel = ParcelaVendaContract.TABLE_NAME + "." + ParcelaVendaContract.COLUNA_CHAVE + " = ? ";
	
	
	
	public static final int PARCELA_VENDA_POR_VENDA_PERTENCEA = 40620;
	public static final int COM_VENDA = 40621;
	private static final String sPorIdVendaPaSelecao =
            ParcelaVendaContract.TABLE_NAME + ".id_venda_pa = ? ";
	

 	public static final int CALCULAPARCELASVENDA = 40622;

	private ContentProvider mProvider = null;


	public void setContentProvider(ContentProvider valor) {
		mProvider = valor;
	}

	protected static final SQLiteQueryBuilder sQueryBuilder;
	static {
		sQueryBuilder = new SQLiteQueryBuilder();
		String tabelas = ParcelaVendaContract.TABLE_NAME;
		
		//tabelas += " inner join " + VendaContract.TABLE_NAME + " ";
		//tabelas += " on " + VendaContract.TABLE_NAME + "." + VendaContract.COLUNA_CHAVE + " = " + ParcelaVendaContract.TABLE_NAME + "." + ParcelaVendaContract.COLUNA_ID_VENDA_PA; 
		
		sQueryBuilder.setTables(tabelas);
	}
	private static final SQLiteQueryBuilder sQueryBuilderSinc;
	static {
		sQueryBuilderSinc = new SQLiteQueryBuilder();
		String tabelas = ParcelaVendaContract.TABLE_NAME_SINC;
		sQueryBuilderSinc.setTables(tabelas);
	}
	
	
	protected AplicacaoDbHelper mOpenHelper = null;
	
	public int getLinhas() {
		return qtdeLinhas;
	}
	
	public static void buildUriMatcher(UriMatcher matcher) {
		matcher.addURI(ParcelaVendaContract.getContentAuthority(), ParcelaVendaContract.PATH, PARCELA_VENDA);
		matcher.addURI(ParcelaVendaContract.getContentAuthority(), ParcelaVendaContract.PATH + "/Sinc" , PARCELA_VENDA_SINC);
		matcher.addURI(ParcelaVendaContract.getContentAuthority(), ParcelaVendaContract.PATH + "/#"    , PARCELA_VENDA_ID);
	
		matcher.addURI(ParcelaVendaContract.getContentAuthority(), ParcelaVendaContract.PATH + "/#/" + ParcelaVendaContract.COM_COMPLEMENTO + "/*" , PARCELA_VENDA_ID_E_COMPLEMENTO);
		matcher.addURI(ParcelaVendaContract.getContentAuthority(), ParcelaVendaContract.PATH + "/" + ParcelaVendaContract.COM_COMPLEMENTO + "/*" , PARCELA_VENDA_E_COMPLEMENTO);
		matcher.addURI(ParcelaVendaContract.getContentAuthority(), ParcelaVendaContract.PATH + "/" + ParcelaVendaContract.COM_RETIRADA + "/*" , PARCELA_VENDA_E_RETIRADA);
		
		
		//matcher.addURI(AplicacaoContract.CONTENT_AUTHORITY, ParcelaVendaContract.PATH + "/operacao/*" , PARCELA_VENDA_OPERACAO);
		
		matcher.addURI(ParcelaVendaContract.getContentAuthority(), ParcelaVendaContract.PATH + "/#/" + VendaContract.PATH, PARCELA_VENDA_POR_VENDA_PERTENCEA);
		matcher.addURI(ParcelaVendaContract.getContentAuthority(), ParcelaVendaContract.PATH + "/ComVendaPertenceA/" , COM_VENDA);
		
		
		
		matcher.addURI(ParcelaVendaContract.getContentAuthority(), ParcelaVendaContract.PATH + "/CalculaParcelasVenda/*", CALCULAPARCELASVENDA);
		
		
		// Deletes
		matcher.addURI(ParcelaVendaContract.getContentAuthority(), ParcelaVendaContract.PATH + "/DeleteAllSinc" , 	PARCELA_VENDA_DELETE_ALL_SINC);
		matcher.addURI(ParcelaVendaContract.getContentAuthority(), ParcelaVendaContract.PATH + "/DeleteAllRecreate" , PARCELA_VENDA_DELETE_RECREATE);
		matcher.addURI(ParcelaVendaContract.getContentAuthority(), ParcelaVendaContract.PATH + "/DeleteSinc/#" , 		PARCELA_VENDA_DELETE_SINC);
	}
	
	
	
	
	public void setAplicacaoDbHelper(AplicacaoDbHelper db) {
		mOpenHelper = db;
	}
	
	public Cursor query(UriMatcher sUriMatcher, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Query Uri:" + uri.toString());
		Cursor retCursor = null;
		switch (sUriMatcher.match(uri)) {
            case PARCELA_VENDA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PARCELA_VENDA");
                retCursor = query(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case PARCELA_VENDA_SINC:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PARCELA_VENDA_SINC");
                retCursor = querySinc(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case PARCELA_VENDA_ID:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PARCELA_VENDA_ID");
            	String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorChaveSel, args, sortOrder);
                break;
            }
            case PARCELA_VENDA_ID_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PARCELA_VENDA_ID_E_COMPLEMENTO");
				String id = uri.getPathSegments().get(1);	
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri) +
						" where " + ParcelaVendaContract.COLUNA_CHAVE + " = " + id;
				retCursor = queryRaw(sql);
				break;
			}
			case PARCELA_VENDA_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PARCELA_VENDA_E_COMPLEMENTO");
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case PARCELA_VENDA_E_RETIRADA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PARCELA_VENDA_E_RETIRADA");
				String sql = "select " +  ParcelaVendaContract.camposOrdenados() +
						" from " + ParcelaVendaContract.TABLE_NAME +
						sqlWhere(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case PARCELA_VENDA_POR_VENDA_PERTENCEA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PARCELA_VENDA_POR_VENDA_PERTENCEA");
	            String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorIdVendaPaSelecao, args, sortOrder);
                break;
            }
            case COM_VENDA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: COM_VENDA");
            	String sql = "select " + ParcelaVendaContract.camposOrdenados() + " , " +
						VendaContract.camposOrdenados() +
						" from " + ParcelaVendaContract.TABLE_NAME +
						ParcelaVendaContract.innerJoinVenda_PertenceA() +
						getOrderBy();
                retCursor = queryRaw(sql);
				break;
            }
		
		
	 		case CALCULAPARCELASVENDA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CALCULAPARCELASVENDA");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryCalculaParcelasVenda()");
				//retCursor = queryCalculaParcelasVenda(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryCalculaParcelasVenda(param) + (sortOrder!=null?" order by " + sortOrder:"");
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
		String sql = ParcelaVendaContract.camposOrdenados();
		List<String> segmentos = uri.getPathSegments();
		// ComChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComVendaPertenceA",uri.getPathSegments())) {
			sql += "," +  VendaContract.camposOrdenados();
		}
	
	
	// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		
		
		
		return sql;
	}
	private String sqlFrom(Uri uri) {
		String sql = ParcelaVendaContract.TABLE_NAME;
		List<String> segmentos = uri.getPathSegments();
		//if (existeItem("ComEpisodioReferenteA",uri.getPathSegments())) {
		//	sql += " " +  EpisodioUsuarioContract.innerJoinEpisodio_ReferenteA();
		//}
		
		
				// ComChaveEstrangeira
  	
		if (existeItem("ComVendaPertenceA",uri.getPathSegments())) {
			sql += " " +  ParcelaVendaContract.outerJoinVenda_PertenceA();
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
	
	
	protected abstract String queryCalculaParcelasVenda(String param);
	
	
	
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
            case PARCELA_VENDA:
            {
                return ParcelaVendaContract.getContentType();
            }
            case PARCELA_VENDA_ID:
            {
            	return ParcelaVendaContract.getContentItemType();
            }
			case PARCELA_VENDA_POR_VENDA_PERTENCEA:
            {
	            return ParcelaVendaContract.getContentType();
            }
		
		}	
		return null;
	}
	
	public Uri insert(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Uri returnUri;
		long idNovo = getMaxId(db)+1;
		values.put(ParcelaVendaContract.COLUNA_CHAVE, idNovo);
		long _id = db.insert(ParcelaVendaContract.TABLE_NAME, null, values);
		if (_id > 0) {
			//DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + ParcelaVendaContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
			DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + values.toString() + " (id:" + _id + ")");
			returnUri = ParcelaVendaContract.buildParcelaVendaUri(idNovo);
			values.put(ParcelaVendaContract.COLUNA_OPERACAO_SINC,"I");
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
			case PARCELA_VENDA_DELETE_SINC: {
				String id = uri.getPathSegments().get(2);
				Cursor cursor = db.query(ParcelaVendaContract.TABLE_NAME,null,ParcelaVendaContract.COLUNA_CHAVE + " = ? ",new String[]{id},null,null,null);
				if (cursor.moveToFirst()) {
					ContentValues valores = new ContentValues();
					DatabaseUtils.cursorRowToContentValues(cursor, valores);
					linhaDelete = db.delete(ParcelaVendaContract.TABLE_NAME, ParcelaVendaContract.COLUNA_CHAVE + " = ? ", new String[]{id});
					DCLog.d(DCLog.DATABASE_CRUD,this,"delete " + ParcelaVendaContract.TABLE_NAME + "(id:" + id + ")");
					valores.put(ParcelaVendaContract.COLUNA_OPERACAO_SINC, "D");
					insereSinc(db,valores);
				}
				notificaUriRelacionadas();
				mProvider.getContext().getContentResolver().notifyChange(ParcelaVendaContract.buildAll(), null);
				break;
			}
			case PARCELA_VENDA_DELETE_ALL_SINC: {
				linhaDelete = db.delete(ParcelaVendaContract.TABLE_NAME_SINC, null, null);
				break;
			}
			case PARCELA_VENDA_DELETE_RECREATE: {
				linhaDelete = db.delete(ParcelaVendaContract.TABLE_NAME, null, null);
				break;
			}
		}
		return true;
	}
	
	
	public boolean update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		DCLog.d(DCLog.DATABASE_CRUD,this,"update2 " + values.toString() );
		linhaUpdate = db.update(ParcelaVendaContract.TABLE_NAME, values, selection, selectionArgs);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	public boolean update(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		String selection = ParcelaVendaContract.COLUNA_CHAVE + " = ? ";
		String dados[] = {values.get(ParcelaVendaContract.COLUNA_CHAVE).toString()};
		DCLog.d(DCLog.DATABASE_CRUD,this,"update1 " + values.toString() );
		linhaUpdate = db.update(ParcelaVendaContract.TABLE_NAME, values, selection, dados);
		values.put(ParcelaVendaContract.COLUNA_OPERACAO_SINC,"A");
		insereSinc(db,values);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	
	private void insereSinc(SQLiteDatabase db, ContentValues values) {
		db.insert(ParcelaVendaContract.TABLE_NAME_SINC, null, values);
		notificaUriOperacoes();
	}
	
	protected abstract void notificaOutrasUri(ContentResolver resolver);
	
	
	// Notificar todas as uris de entidades que possuem chave estrangeira dessa entidade.
	private void notificaUriRelacionadas() {
		// ComChaveEstrangeira
  	
		mProvider.getContext().getContentResolver().notifyChange(VendaContract.buildAllComParcelaVendaPossui(), null);
		mProvider.getContext().getContentResolver().notifyChange(VendaContract.buildAllSemParcelaVendaPossui(), null);
	
	}
	private void notificaUriOperacoes() {
	
		mProvider.getContext().getContentResolver().notifyChange(ParcelaVendaContract.buildCalculaParcelasVenda(), null);
	
	}
	private void notificaUriRaiz(){
		
	}
	
	
	
	public int bulkInsert(Uri uri, ContentValues[] values) {
    	final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        db.beginTransaction();
        int returnCount = 0;
        try {
        	for (ContentValues value : values) {
				String[] args = {(String)value.get(ParcelaVendaContract.COLUNA_CHAVE)};
				Cursor retCursor = query(null, sPorChaveSel, args, null);
				if (retCursor.moveToFirst()) {
						db.update(ParcelaVendaContract.TABLE_NAME,value,sPorChaveSel,args);
						//DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk " + ParcelaVendaContract.TABLE_NAME + "  " + values.toString());
						DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk id:" + value.get(ParcelaVendaContract.COLUNA_CHAVE) + ")" + values.toString());
				} else {
					long _id = db.insert(ParcelaVendaContract.TABLE_NAME, null, value);
					if (_id != -1) {
						//DCLog.d(DCLog.DATABASE_CRUD,this,"insert (bulk)" + ParcelaVendaContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
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
            	long _id = db.insert(ParcelaVendaContract.TABLE_NAME, null, value);
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
		String sql = "select max(" + ParcelaVendaContract.COLUNA_CHAVE + ") from " + ParcelaVendaContract.TABLE_NAME;
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