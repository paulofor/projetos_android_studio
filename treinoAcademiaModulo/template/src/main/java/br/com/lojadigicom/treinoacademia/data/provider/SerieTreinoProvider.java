
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

public abstract class SerieTreinoProvider {

	private int qtdeLinhas = 0;
	
	public static final int SERIE_TREINO = 74400;
	public static final int SERIE_TREINO_ID = 74401;
	public static final int SERIE_TREINO_SINC = 74403;
	public static final int SERIE_TREINO_E_COMPLEMENTO = 74404;
	public static final int SERIE_TREINO_ID_E_COMPLEMENTO = 74405;
	//public static final int SERIE_TREINO_OPERACAO = 74402;
	
	// deletes
	public static final int SERIE_TREINO_DELETE_ALL_SINC = 74406;
	public static final int SERIE_TREINO_DELETE_RECREATE = 74407;
	public static final int SERIE_TREINO_DELETE_SINC = 74408;
	public static final int SERIE_TREINO_E_RETIRADA = 74409;
	
	private static final String sPorChaveSel = SerieTreinoContract.TABLE_NAME + "." + SerieTreinoContract.COLUNA_CHAVE + " = ? ";
	
	
	

 	public static final int OBTEMPROXIMA = 74420;
 	public static final int OBTEMMONTADAPORID = 74421;
 	public static final int CRIASERIECOMPLETA = 74422;
 	public static final int CARREGAITEMSERIE = 74423;

	private ContentProvider mProvider = null;


	public void setContentProvider(ContentProvider valor) {
		mProvider = valor;
	}

	protected static final SQLiteQueryBuilder sQueryBuilder;
	static {
		sQueryBuilder = new SQLiteQueryBuilder();
		String tabelas = SerieTreinoContract.TABLE_NAME;
		
		sQueryBuilder.setTables(tabelas);
	}
	private static final SQLiteQueryBuilder sQueryBuilderSinc;
	static {
		sQueryBuilderSinc = new SQLiteQueryBuilder();
		String tabelas = SerieTreinoContract.TABLE_NAME_SINC;
		sQueryBuilderSinc.setTables(tabelas);
	}
	
	
	protected AplicacaoDbHelper mOpenHelper = null;
	
	public int getLinhas() {
		return qtdeLinhas;
	}
	
	public static void buildUriMatcher(UriMatcher matcher) {
		matcher.addURI(SerieTreinoContract.getContentAuthority(), SerieTreinoContract.PATH, SERIE_TREINO);
		matcher.addURI(SerieTreinoContract.getContentAuthority(), SerieTreinoContract.PATH + "/Sinc" , SERIE_TREINO_SINC);
		matcher.addURI(SerieTreinoContract.getContentAuthority(), SerieTreinoContract.PATH + "/#"    , SERIE_TREINO_ID);
	
		matcher.addURI(SerieTreinoContract.getContentAuthority(), SerieTreinoContract.PATH + "/#/" + SerieTreinoContract.COM_COMPLEMENTO + "/*" , SERIE_TREINO_ID_E_COMPLEMENTO);
		matcher.addURI(SerieTreinoContract.getContentAuthority(), SerieTreinoContract.PATH + "/" + SerieTreinoContract.COM_COMPLEMENTO + "/*" , SERIE_TREINO_E_COMPLEMENTO);
		matcher.addURI(SerieTreinoContract.getContentAuthority(), SerieTreinoContract.PATH + "/" + SerieTreinoContract.COM_RETIRADA + "/*" , SERIE_TREINO_E_RETIRADA);
		
		
		//matcher.addURI(AplicacaoContract.CONTENT_AUTHORITY, SerieTreinoContract.PATH + "/operacao/*" , SERIE_TREINO_OPERACAO);
		
		
		
		matcher.addURI(SerieTreinoContract.getContentAuthority(), SerieTreinoContract.PATH + "/ObtemProxima/*", OBTEMPROXIMA);
		
		matcher.addURI(SerieTreinoContract.getContentAuthority(), SerieTreinoContract.PATH + "/ObtemMontadaPorId/*", OBTEMMONTADAPORID);
		
		matcher.addURI(SerieTreinoContract.getContentAuthority(), SerieTreinoContract.PATH + "/CriaSerieCompleta/*", CRIASERIECOMPLETA);
		
		matcher.addURI(SerieTreinoContract.getContentAuthority(), SerieTreinoContract.PATH + "/CarregaItemSerie/*", CARREGAITEMSERIE);
		
		
		// Deletes
		matcher.addURI(SerieTreinoContract.getContentAuthority(), SerieTreinoContract.PATH + "/DeleteAllSinc" , 	SERIE_TREINO_DELETE_ALL_SINC);
		matcher.addURI(SerieTreinoContract.getContentAuthority(), SerieTreinoContract.PATH + "/DeleteAllRecreate" , SERIE_TREINO_DELETE_RECREATE);
		matcher.addURI(SerieTreinoContract.getContentAuthority(), SerieTreinoContract.PATH + "/DeleteSinc/#" , 		SERIE_TREINO_DELETE_SINC);
	}
	
	
	
	
	public void setAplicacaoDbHelper(AplicacaoDbHelper db) {
		mOpenHelper = db;
	}
	
	public Cursor query(UriMatcher sUriMatcher, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Query Uri:" + uri.toString());
		Cursor retCursor = null;
		switch (sUriMatcher.match(uri)) {
            case SERIE_TREINO:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: SERIE_TREINO");
                retCursor = query(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case SERIE_TREINO_SINC:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: SERIE_TREINO_SINC");
                retCursor = querySinc(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case SERIE_TREINO_ID:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: SERIE_TREINO_ID");
            	String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorChaveSel, args, sortOrder);
                break;
            }
            case SERIE_TREINO_ID_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: SERIE_TREINO_ID_E_COMPLEMENTO");
				String id = uri.getPathSegments().get(1);	
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri) +
						" where " + SerieTreinoContract.COLUNA_CHAVE + " = " + id;
				retCursor = queryRaw(sql);
				break;
			}
			case SERIE_TREINO_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: SERIE_TREINO_E_COMPLEMENTO");
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case SERIE_TREINO_E_RETIRADA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: SERIE_TREINO_E_RETIRADA");
				String sql = "select " +  SerieTreinoContract.camposOrdenados() +
						" from " + SerieTreinoContract.TABLE_NAME +
						sqlWhere(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
		
	 		case OBTEMPROXIMA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: OBTEMPROXIMA");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryObtemProxima()");
				//retCursor = queryObtemProxima(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryObtemProxima(param) + (sortOrder!=null?" order by " + sortOrder:"");
				DCLog.d(DCLog.TRACE_LISTA,this,"SQL: " + sql);
				retCursor = this.queryRaw(sql);
				DCLog.d(DCLog.TRACE_LISTA,this,"Cursor: " + retCursor.getCount() + " linhas");
				break;
			}
		
	 		case OBTEMMONTADAPORID:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: OBTEMMONTADAPORID");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryObtemMontadaPorId()");
				//retCursor = queryObtemMontadaPorId(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryObtemMontadaPorId(param) + (sortOrder!=null?" order by " + sortOrder:"");
				DCLog.d(DCLog.TRACE_LISTA,this,"SQL: " + sql);
				retCursor = this.queryRaw(sql);
				DCLog.d(DCLog.TRACE_LISTA,this,"Cursor: " + retCursor.getCount() + " linhas");
				break;
			}
		
	 		case CRIASERIECOMPLETA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CRIASERIECOMPLETA");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryCriaSerieCompleta()");
				//retCursor = queryCriaSerieCompleta(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryCriaSerieCompleta(param) + (sortOrder!=null?" order by " + sortOrder:"");
				DCLog.d(DCLog.TRACE_LISTA,this,"SQL: " + sql);
				retCursor = this.queryRaw(sql);
				DCLog.d(DCLog.TRACE_LISTA,this,"Cursor: " + retCursor.getCount() + " linhas");
				break;
			}
		
	 		case CARREGAITEMSERIE:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CARREGAITEMSERIE");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryCarregaItemSerie()");
				//retCursor = queryCarregaItemSerie(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryCarregaItemSerie(param) + (sortOrder!=null?" order by " + sortOrder:"");
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
  	
		if (existeItem("SemItemSeriePossui",uri.getPathSegments())) {
			sql += SerieTreinoContract.COLUNA_CHAVE + " not in (select " + 
					ItemSerieContract.COLUNA_ID_SERIE_TREINO_PA + " from " +
					ItemSerieContract.TABLE_NAME + ")";
		}
	
		if (existeItem("SemDiaTreinoSerieDia",uri.getPathSegments())) {
			sql += SerieTreinoContract.COLUNA_CHAVE + " not in (select " + 
					DiaTreinoContract.COLUNA_ID_SERIE_TREINO_SD + " from " +
					DiaTreinoContract.TABLE_NAME + ")";
		}
	
		
		return sql;
	}
	
	private String sqlSelect(Uri uri) {
		String sql = SerieTreinoContract.camposOrdenados();
		List<String> segmentos = uri.getPathSegments();
		// ComChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
	
	// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComItemSeriePossui",uri.getPathSegments())) {
			sql += "," +  ItemSerieContract.camposOrdenados();
		}
	
		if (existeItem("ComDiaTreinoSerieDia",uri.getPathSegments())) {
			sql += "," +  DiaTreinoContract.camposOrdenados();
		}
	
		
		
		
		return sql;
	}
	private String sqlFrom(Uri uri) {
		String sql = SerieTreinoContract.TABLE_NAME;
		List<String> segmentos = uri.getPathSegments();
		//if (existeItem("ComEpisodioReferenteA",uri.getPathSegments())) {
		//	sql += " " +  EpisodioUsuarioContract.innerJoinEpisodio_ReferenteA();
		//}
		
		
				// ComChaveEstrangeira
  	
	
	// SemChaveEstrangeira
  	
		if (existeItem("ComItemSeriePossui",uri.getPathSegments())) {
			sql += " " +  SerieTreinoContract.outerJoinItemSerie_Possui();
		}
	
		if (existeItem("ComDiaTreinoSerieDia",uri.getPathSegments())) {
			sql += " " +  SerieTreinoContract.outerJoinDiaTreino_SerieDia();
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
	
	
	protected abstract String queryObtemProxima(String param);
	
	protected abstract String queryObtemMontadaPorId(String param);
	
	protected abstract String queryCriaSerieCompleta(String param);
	
	protected abstract String queryCarregaItemSerie(String param);
	
	
	
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
            case SERIE_TREINO:
            {
                return SerieTreinoContract.getContentType();
            }
            case SERIE_TREINO_ID:
            {
            	return SerieTreinoContract.getContentItemType();
            }
		}	
		return null;
	}
	
	public Uri insert(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Uri returnUri;
		long idNovo = getMaxId(db)+1;
		values.put(SerieTreinoContract.COLUNA_CHAVE, idNovo);
		long _id = db.insert(SerieTreinoContract.TABLE_NAME, null, values);
		if (_id > 0) {
			//DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + SerieTreinoContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
			DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + values.toString() + " (id:" + _id + ")");
			returnUri = SerieTreinoContract.buildSerieTreinoUri(idNovo);
			values.put(SerieTreinoContract.COLUNA_OPERACAO_SINC,"I");
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
			case SERIE_TREINO_DELETE_SINC: {
				String id = uri.getPathSegments().get(2);
				Cursor cursor = db.query(SerieTreinoContract.TABLE_NAME,null,SerieTreinoContract.COLUNA_CHAVE + " = ? ",new String[]{id},null,null,null);
				if (cursor.moveToFirst()) {
					ContentValues valores = new ContentValues();
					DatabaseUtils.cursorRowToContentValues(cursor, valores);
					linhaDelete = db.delete(SerieTreinoContract.TABLE_NAME, SerieTreinoContract.COLUNA_CHAVE + " = ? ", new String[]{id});
					DCLog.d(DCLog.DATABASE_CRUD,this,"delete " + SerieTreinoContract.TABLE_NAME + "(id:" + id + ")");
					valores.put(SerieTreinoContract.COLUNA_OPERACAO_SINC, "D");
					insereSinc(db,valores);
				}
				notificaUriRelacionadas();
				mProvider.getContext().getContentResolver().notifyChange(SerieTreinoContract.buildAll(), null);
				break;
			}
			case SERIE_TREINO_DELETE_ALL_SINC: {
				linhaDelete = db.delete(SerieTreinoContract.TABLE_NAME_SINC, null, null);
				break;
			}
			case SERIE_TREINO_DELETE_RECREATE: {
				linhaDelete = db.delete(SerieTreinoContract.TABLE_NAME, null, null);
				break;
			}
		}
		return true;
	}
	
	
	public boolean update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		DCLog.d(DCLog.DATABASE_CRUD,this,"update2 " + values.toString() );
		linhaUpdate = db.update(SerieTreinoContract.TABLE_NAME, values, selection, selectionArgs);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	public boolean update(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		String selection = SerieTreinoContract.COLUNA_CHAVE + " = ? ";
		String dados[] = {values.get(SerieTreinoContract.COLUNA_CHAVE).toString()};
		DCLog.d(DCLog.DATABASE_CRUD,this,"update1 " + values.toString() );
		linhaUpdate = db.update(SerieTreinoContract.TABLE_NAME, values, selection, dados);
		values.put(SerieTreinoContract.COLUNA_OPERACAO_SINC,"A");
		insereSinc(db,values);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	
	private void insereSinc(SQLiteDatabase db, ContentValues values) {
		db.insert(SerieTreinoContract.TABLE_NAME_SINC, null, values);
		notificaUriOperacoes();
	}
	
	protected abstract void notificaOutrasUri(ContentResolver resolver);
	
	
	// Notificar todas as uris de entidades que possuem chave estrangeira dessa entidade.
	private void notificaUriRelacionadas() {
		// ComChaveEstrangeira
  	
	}
	private void notificaUriOperacoes() {
	
		mProvider.getContext().getContentResolver().notifyChange(SerieTreinoContract.buildObtemProxima(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(SerieTreinoContract.buildObtemMontadaPorId(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(SerieTreinoContract.buildCriaSerieCompleta(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(SerieTreinoContract.buildCarregaItemSerie(), null);
	
	}
	private void notificaUriRaiz(){
		
	}
	
	
	
	public int bulkInsert(Uri uri, ContentValues[] values) {
    	final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        db.beginTransaction();
        int returnCount = 0;
        try {
        	for (ContentValues value : values) {
				String[] args = {(String)value.get(SerieTreinoContract.COLUNA_CHAVE)};
				Cursor retCursor = query(null, sPorChaveSel, args, null);
				if (retCursor.moveToFirst()) {
						db.update(SerieTreinoContract.TABLE_NAME,value,sPorChaveSel,args);
						//DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk " + SerieTreinoContract.TABLE_NAME + "  " + values.toString());
						DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk id:" + value.get(SerieTreinoContract.COLUNA_CHAVE) + ")" + values.toString());
				} else {
					long _id = db.insert(SerieTreinoContract.TABLE_NAME, null, value);
					if (_id != -1) {
						//DCLog.d(DCLog.DATABASE_CRUD,this,"insert (bulk)" + SerieTreinoContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
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
            	long _id = db.insert(SerieTreinoContract.TABLE_NAME, null, value);
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
		String sql = "select max(" + SerieTreinoContract.COLUNA_CHAVE + ") from " + SerieTreinoContract.TABLE_NAME;
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