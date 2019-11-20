
package  br.com.lojadigicom.treinoacademia.data.provider;


import br.com.lojadigicom.treinoacademia.data.contract.*;
import br.com.lojadigicom.treinoacademia.data.helper.AplicacaoDbHelper;
import android.content.ContentValues;
import android.content.ContentResolver;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.database.SQLException;
import android.database.DatabaseUtils;
import android.net.Uri;
import br.com.lojadigicom.treinoacademia.framework.log.DCLog;
import java.util.List;
import android.content.ContentProvider;

public abstract class CargaPlanejadaProvider {

	private int qtdeLinhas = 0;
	
	public static final int CARGA_PLANEJADA = 74600;
	public static final int CARGA_PLANEJADA_ID = 74601;
	public static final int CARGA_PLANEJADA_SINC = 74603;
	public static final int CARGA_PLANEJADA_E_COMPLEMENTO = 74604;
	public static final int CARGA_PLANEJADA_ID_E_COMPLEMENTO = 74605;
	//public static final int CARGA_PLANEJADA_OPERACAO = 74602;
	
	// deletes
	public static final int CARGA_PLANEJADA_DELETE_ALL_SINC = 74606;
	public static final int CARGA_PLANEJADA_DELETE_RECREATE = 74607;
	public static final int CARGA_PLANEJADA_DELETE_SINC = 74608;
	public static final int CARGA_PLANEJADA_E_RETIRADA = 74609;
	
	private static final String sPorChaveSel = CargaPlanejadaContract.TABLE_NAME + "." + CargaPlanejadaContract.COLUNA_CHAVE + " = ? ";
	
	
	
	public static final int CARGA_PLANEJADA_POR_ITEM_SERIE_REFERENTEA = 74620;
	public static final int COM_ITEM_SERIE = 74621;
	private static final String sPorIdItemSerieRaSelecao =
            CargaPlanejadaContract.TABLE_NAME + ".id_item_serie_ra = ? ";
	

 	public static final int LISTACARGAPOREXERCICIO = 74622;
 	public static final int LISTACARGAATIVAPOREXERCICIO = 74623;

	private ContentProvider mProvider = null;


	public void setContentProvider(ContentProvider valor) {
		mProvider = valor;
	}

	protected static final SQLiteQueryBuilder sQueryBuilder;
	static {
		sQueryBuilder = new SQLiteQueryBuilder();
		String tabelas = CargaPlanejadaContract.TABLE_NAME;
		
		//tabelas += " inner join " + ItemSerieContract.TABLE_NAME + " ";
		//tabelas += " on " + ItemSerieContract.TABLE_NAME + "." + ItemSerieContract.COLUNA_CHAVE + " = " + CargaPlanejadaContract.TABLE_NAME + "." + CargaPlanejadaContract.COLUNA_ID_ITEM_SERIE_RA; 
		
		sQueryBuilder.setTables(tabelas);
	}
	private static final SQLiteQueryBuilder sQueryBuilderSinc;
	static {
		sQueryBuilderSinc = new SQLiteQueryBuilder();
		String tabelas = CargaPlanejadaContract.TABLE_NAME_SINC;
		sQueryBuilderSinc.setTables(tabelas);
	}
	
	
	protected AplicacaoDbHelper mOpenHelper = null;
	
	public int getLinhas() {
		return qtdeLinhas;
	}
	
	public static void buildUriMatcher(UriMatcher matcher) {
		matcher.addURI(CargaPlanejadaContract.getContentAuthority(), CargaPlanejadaContract.PATH, CARGA_PLANEJADA);
		matcher.addURI(CargaPlanejadaContract.getContentAuthority(), CargaPlanejadaContract.PATH + "/Sinc" , CARGA_PLANEJADA_SINC);
		matcher.addURI(CargaPlanejadaContract.getContentAuthority(), CargaPlanejadaContract.PATH + "/#"    , CARGA_PLANEJADA_ID);
	
		matcher.addURI(CargaPlanejadaContract.getContentAuthority(), CargaPlanejadaContract.PATH + "/#/" + CargaPlanejadaContract.COM_COMPLEMENTO + "/*" , CARGA_PLANEJADA_ID_E_COMPLEMENTO);
		matcher.addURI(CargaPlanejadaContract.getContentAuthority(), CargaPlanejadaContract.PATH + "/" + CargaPlanejadaContract.COM_COMPLEMENTO + "/*" , CARGA_PLANEJADA_E_COMPLEMENTO);
		matcher.addURI(CargaPlanejadaContract.getContentAuthority(), CargaPlanejadaContract.PATH + "/" + CargaPlanejadaContract.COM_RETIRADA + "/*" , CARGA_PLANEJADA_E_RETIRADA);
		
		
		//matcher.addURI(AplicacaoContract.CONTENT_AUTHORITY, CargaPlanejadaContract.PATH + "/operacao/*" , CARGA_PLANEJADA_OPERACAO);
		
		matcher.addURI(CargaPlanejadaContract.getContentAuthority(), CargaPlanejadaContract.PATH + "/#/" + ItemSerieContract.PATH, CARGA_PLANEJADA_POR_ITEM_SERIE_REFERENTEA);
		matcher.addURI(CargaPlanejadaContract.getContentAuthority(), CargaPlanejadaContract.PATH + "/ComItemSerieReferenteA/" , COM_ITEM_SERIE);
		
		
		
		matcher.addURI(CargaPlanejadaContract.getContentAuthority(), CargaPlanejadaContract.PATH + "/ListaCargaPorExercicio/*", LISTACARGAPOREXERCICIO);
		
		matcher.addURI(CargaPlanejadaContract.getContentAuthority(), CargaPlanejadaContract.PATH + "/ListaCargaAtivaPorExercicio/*", LISTACARGAATIVAPOREXERCICIO);
		
		
		// Deletes
		matcher.addURI(CargaPlanejadaContract.getContentAuthority(), CargaPlanejadaContract.PATH + "/DeleteAllSinc" , 	CARGA_PLANEJADA_DELETE_ALL_SINC);
		matcher.addURI(CargaPlanejadaContract.getContentAuthority(), CargaPlanejadaContract.PATH + "/DeleteAllRecreate" , CARGA_PLANEJADA_DELETE_RECREATE);
		matcher.addURI(CargaPlanejadaContract.getContentAuthority(), CargaPlanejadaContract.PATH + "/DeleteSinc/#" , 		CARGA_PLANEJADA_DELETE_SINC);
	}
	
	
	
	
	public void setAplicacaoDbHelper(AplicacaoDbHelper db) {
		mOpenHelper = db;
	}
	
	public Cursor query(UriMatcher sUriMatcher, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Query Uri:" + uri.toString());
		Cursor retCursor = null;
		switch (sUriMatcher.match(uri)) {
            case CARGA_PLANEJADA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CARGA_PLANEJADA");
                retCursor = query(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case CARGA_PLANEJADA_SINC:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CARGA_PLANEJADA_SINC");
                retCursor = querySinc(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case CARGA_PLANEJADA_ID:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CARGA_PLANEJADA_ID");
            	String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorChaveSel, args, sortOrder);
                break;
            }
            case CARGA_PLANEJADA_ID_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CARGA_PLANEJADA_ID_E_COMPLEMENTO");
				String id = uri.getPathSegments().get(1);	
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri) +
						" where " + CargaPlanejadaContract.COLUNA_CHAVE + " = " + id;
				retCursor = queryRaw(sql);
				break;
			}
			case CARGA_PLANEJADA_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CARGA_PLANEJADA_E_COMPLEMENTO");
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case CARGA_PLANEJADA_E_RETIRADA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CARGA_PLANEJADA_E_RETIRADA");
				String sql = "select " +  CargaPlanejadaContract.camposOrdenados() +
						" from " + CargaPlanejadaContract.TABLE_NAME +
						sqlWhere(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case CARGA_PLANEJADA_POR_ITEM_SERIE_REFERENTEA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CARGA_PLANEJADA_POR_ITEM_SERIE_REFERENTEA");
	            String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorIdItemSerieRaSelecao, args, sortOrder);
                break;
            }
            case COM_ITEM_SERIE:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: COM_ITEM_SERIE");
            	String sql = "select " + CargaPlanejadaContract.camposOrdenados() + " , " +
						ItemSerieContract.camposOrdenados() +
						" from " + CargaPlanejadaContract.TABLE_NAME +
						CargaPlanejadaContract.innerJoinItemSerie_ReferenteA() +
						getOrderBy();
                retCursor = queryRaw(sql);
				break;
            }
		
		
	 		case LISTACARGAPOREXERCICIO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: LISTACARGAPOREXERCICIO");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryListaCargaPorExercicio()");
				//retCursor = queryListaCargaPorExercicio(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryListaCargaPorExercicio(param) + (sortOrder!=null?" order by " + sortOrder:"");
				DCLog.d(DCLog.TRACE_LISTA,this,"SQL: " + sql);
				retCursor = this.queryRaw(sql);
				DCLog.d(DCLog.TRACE_LISTA,this,"Cursor: " + retCursor.getCount() + " linhas");
				break;
			}
		
	 		case LISTACARGAATIVAPOREXERCICIO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: LISTACARGAATIVAPOREXERCICIO");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryListaCargaAtivaPorExercicio()");
				//retCursor = queryListaCargaAtivaPorExercicio(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryListaCargaAtivaPorExercicio(param) + (sortOrder!=null?" order by " + sortOrder:"");
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
		String sql = CargaPlanejadaContract.camposOrdenados();
		List<String> segmentos = uri.getPathSegments();
		// ComChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComItemSerieReferenteA",uri.getPathSegments())) {
			sql += "," +  ItemSerieContract.camposOrdenados();
		}
	
	
	// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		
		
		
		return sql;
	}
	private String sqlFrom(Uri uri) {
		String sql = CargaPlanejadaContract.TABLE_NAME;
		List<String> segmentos = uri.getPathSegments();
		//if (existeItem("ComEpisodioReferenteA",uri.getPathSegments())) {
		//	sql += " " +  EpisodioUsuarioContract.innerJoinEpisodio_ReferenteA();
		//}
		
		
				// ComChaveEstrangeira
  	
		if (existeItem("ComItemSerieReferenteA",uri.getPathSegments())) {
			sql += " " +  CargaPlanejadaContract.outerJoinItemSerie_ReferenteA();
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
	
	
	protected abstract String queryListaCargaPorExercicio(String param);
	
	protected abstract String queryListaCargaAtivaPorExercicio(String param);
	
	
	
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
            case CARGA_PLANEJADA:
            {
                return CargaPlanejadaContract.getContentType();
            }
            case CARGA_PLANEJADA_ID:
            {
            	return CargaPlanejadaContract.getContentItemType();
            }
			case CARGA_PLANEJADA_POR_ITEM_SERIE_REFERENTEA:
            {
	            return CargaPlanejadaContract.getContentType();
            }
		
		}	
		return null;
	}
	
	public Uri insert(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Uri returnUri;
		long idNovo = getMaxId(db)+1;
		values.put(CargaPlanejadaContract.COLUNA_CHAVE, idNovo);
		long _id = db.insert(CargaPlanejadaContract.TABLE_NAME, null, values);
		if (_id > 0) {
			//DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + CargaPlanejadaContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
			DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + values.toString() + " (id:" + _id + ")");
			returnUri = CargaPlanejadaContract.buildCargaPlanejadaUri(idNovo);
			values.put(CargaPlanejadaContract.COLUNA_OPERACAO_SINC,"I");
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
			case CARGA_PLANEJADA_DELETE_SINC: {
				String id = uri.getPathSegments().get(2);
				Cursor cursor = db.query(CargaPlanejadaContract.TABLE_NAME,null,CargaPlanejadaContract.COLUNA_CHAVE + " = ? ",new String[]{id},null,null,null);
				if (cursor.moveToFirst()) {
					ContentValues valores = new ContentValues();
					DatabaseUtils.cursorRowToContentValues(cursor, valores);
					linhaDelete = db.delete(CargaPlanejadaContract.TABLE_NAME, CargaPlanejadaContract.COLUNA_CHAVE + " = ? ", new String[]{id});
					DCLog.d(DCLog.DATABASE_CRUD,this,"delete " + CargaPlanejadaContract.TABLE_NAME + "(id:" + id + ")");
					valores.put(CargaPlanejadaContract.COLUNA_OPERACAO_SINC, "D");
					insereSinc(db,valores);
				}
				notificaUriRelacionadas();
				mProvider.getContext().getContentResolver().notifyChange(CargaPlanejadaContract.buildAll(), null);
				break;
			}
			case CARGA_PLANEJADA_DELETE_ALL_SINC: {
				linhaDelete = db.delete(CargaPlanejadaContract.TABLE_NAME_SINC, null, null);
				break;
			}
			case CARGA_PLANEJADA_DELETE_RECREATE: {
				linhaDelete = db.delete(CargaPlanejadaContract.TABLE_NAME, null, null);
				break;
			}
		}
		return true;
	}
	
	
	public boolean update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		DCLog.d(DCLog.DATABASE_CRUD,this,"update2 " + values.toString() );
		linhaUpdate = db.update(CargaPlanejadaContract.TABLE_NAME, values, selection, selectionArgs);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	public boolean update(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		String selection = CargaPlanejadaContract.COLUNA_CHAVE + " = ? ";
		String dados[] = {values.get(CargaPlanejadaContract.COLUNA_CHAVE).toString()};
		DCLog.d(DCLog.DATABASE_CRUD,this,"update1 " + values.toString() );
		linhaUpdate = db.update(CargaPlanejadaContract.TABLE_NAME, values, selection, dados);
		values.put(CargaPlanejadaContract.COLUNA_OPERACAO_SINC,"A");
		insereSinc(db,values);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	
	private void insereSinc(SQLiteDatabase db, ContentValues values) {
		db.insert(CargaPlanejadaContract.TABLE_NAME_SINC, null, values);
		notificaUriOperacoes();
	}
	
	protected abstract void notificaOutrasUri(ContentResolver resolver);
	
	
	// Notificar todas as uris de entidades que possuem chave estrangeira dessa entidade.
	private void notificaUriRelacionadas() {
		// ComChaveEstrangeira
  	
		mProvider.getContext().getContentResolver().notifyChange(ItemSerieContract.buildAllComCargaPlanejadaPossui(), null);
		mProvider.getContext().getContentResolver().notifyChange(ItemSerieContract.buildAllSemCargaPlanejadaPossui(), null);
	
	}
	private void notificaUriOperacoes() {
	
		mProvider.getContext().getContentResolver().notifyChange(CargaPlanejadaContract.buildListaCargaPorExercicio(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(CargaPlanejadaContract.buildListaCargaAtivaPorExercicio(), null);
	
	}
	private void notificaUriRaiz(){
		
	}
	
	
	
	public int bulkInsert(Uri uri, ContentValues[] values) {
    	final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        db.beginTransaction();
        int returnCount = 0;
        try {
        	for (ContentValues value : values) {
				String[] args = {(String)value.get(CargaPlanejadaContract.COLUNA_CHAVE)};
				Cursor retCursor = query(null, sPorChaveSel, args, null);
				if (retCursor.moveToFirst()) {
						db.update(CargaPlanejadaContract.TABLE_NAME,value,sPorChaveSel,args);
						//DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk " + CargaPlanejadaContract.TABLE_NAME + "  " + values.toString());
						DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk id:" + value.get(CargaPlanejadaContract.COLUNA_CHAVE) + ")" + values.toString());
				} else {
					long _id = db.insert(CargaPlanejadaContract.TABLE_NAME, null, value);
					if (_id != -1) {
						//DCLog.d(DCLog.DATABASE_CRUD,this,"insert (bulk)" + CargaPlanejadaContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
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
            	long _id = db.insert(CargaPlanejadaContract.TABLE_NAME, null, value);
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
		String sql = "select max(" + CargaPlanejadaContract.COLUNA_CHAVE + ") from " + CargaPlanejadaContract.TABLE_NAME;
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