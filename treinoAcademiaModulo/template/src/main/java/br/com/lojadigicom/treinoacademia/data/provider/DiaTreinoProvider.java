
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

public abstract class DiaTreinoProvider {

	private int qtdeLinhas = 0;
	
	public static final int DIA_TREINO = 74800;
	public static final int DIA_TREINO_ID = 74801;
	public static final int DIA_TREINO_SINC = 74803;
	public static final int DIA_TREINO_E_COMPLEMENTO = 74804;
	public static final int DIA_TREINO_ID_E_COMPLEMENTO = 74805;
	//public static final int DIA_TREINO_OPERACAO = 74802;
	
	// deletes
	public static final int DIA_TREINO_DELETE_ALL_SINC = 74806;
	public static final int DIA_TREINO_DELETE_RECREATE = 74807;
	public static final int DIA_TREINO_DELETE_SINC = 74808;
	public static final int DIA_TREINO_E_RETIRADA = 74809;
	
	private static final String sPorChaveSel = DiaTreinoContract.TABLE_NAME + "." + DiaTreinoContract.COLUNA_CHAVE + " = ? ";
	
	
	
	public static final int DIA_TREINO_POR_SERIE_TREINO_SERIEDIA = 74820;
	public static final int COM_SERIE_TREINO = 74821;
	private static final String sPorIdSerieTreinoSdSelecao =
            DiaTreinoContract.TABLE_NAME + ".id_serie_treino_sd = ? ";
	

 	public static final int ENCERRADIATREINO = 74822;
 	public static final int OBTEMPORDATA = 74823;
 	public static final int LIMPEZABASE = 74824;
 	public static final int HISTORICOEXECUCAOPORIDEXERCICIO = 74825;
 	public static final int TREINOSPOSDATAPESQUISA = 74826;

	private ContentProvider mProvider = null;


	public void setContentProvider(ContentProvider valor) {
		mProvider = valor;
	}

	protected static final SQLiteQueryBuilder sQueryBuilder;
	static {
		sQueryBuilder = new SQLiteQueryBuilder();
		String tabelas = DiaTreinoContract.TABLE_NAME;
		
		//tabelas += " inner join " + SerieTreinoContract.TABLE_NAME + " ";
		//tabelas += " on " + SerieTreinoContract.TABLE_NAME + "." + SerieTreinoContract.COLUNA_CHAVE + " = " + DiaTreinoContract.TABLE_NAME + "." + DiaTreinoContract.COLUNA_ID_SERIE_TREINO_SD; 
		
		sQueryBuilder.setTables(tabelas);
	}
	private static final SQLiteQueryBuilder sQueryBuilderSinc;
	static {
		sQueryBuilderSinc = new SQLiteQueryBuilder();
		String tabelas = DiaTreinoContract.TABLE_NAME_SINC;
		sQueryBuilderSinc.setTables(tabelas);
	}
	
	
	protected AplicacaoDbHelper mOpenHelper = null;
	
	public int getLinhas() {
		return qtdeLinhas;
	}
	
	public static void buildUriMatcher(UriMatcher matcher) {
		matcher.addURI(DiaTreinoContract.getContentAuthority(), DiaTreinoContract.PATH, DIA_TREINO);
		matcher.addURI(DiaTreinoContract.getContentAuthority(), DiaTreinoContract.PATH + "/Sinc" , DIA_TREINO_SINC);
		matcher.addURI(DiaTreinoContract.getContentAuthority(), DiaTreinoContract.PATH + "/#"    , DIA_TREINO_ID);
	
		matcher.addURI(DiaTreinoContract.getContentAuthority(), DiaTreinoContract.PATH + "/#/" + DiaTreinoContract.COM_COMPLEMENTO + "/*" , DIA_TREINO_ID_E_COMPLEMENTO);
		matcher.addURI(DiaTreinoContract.getContentAuthority(), DiaTreinoContract.PATH + "/" + DiaTreinoContract.COM_COMPLEMENTO + "/*" , DIA_TREINO_E_COMPLEMENTO);
		matcher.addURI(DiaTreinoContract.getContentAuthority(), DiaTreinoContract.PATH + "/" + DiaTreinoContract.COM_RETIRADA + "/*" , DIA_TREINO_E_RETIRADA);
		
		
		//matcher.addURI(AplicacaoContract.CONTENT_AUTHORITY, DiaTreinoContract.PATH + "/operacao/*" , DIA_TREINO_OPERACAO);
		
		matcher.addURI(DiaTreinoContract.getContentAuthority(), DiaTreinoContract.PATH + "/#/" + SerieTreinoContract.PATH, DIA_TREINO_POR_SERIE_TREINO_SERIEDIA);
		matcher.addURI(DiaTreinoContract.getContentAuthority(), DiaTreinoContract.PATH + "/ComSerieTreinoSerieDia/" , COM_SERIE_TREINO);
		
		
		
		matcher.addURI(DiaTreinoContract.getContentAuthority(), DiaTreinoContract.PATH + "/EncerraDiaTreino/*", ENCERRADIATREINO);
		
		matcher.addURI(DiaTreinoContract.getContentAuthority(), DiaTreinoContract.PATH + "/ObtemPorData/*", OBTEMPORDATA);
		
		matcher.addURI(DiaTreinoContract.getContentAuthority(), DiaTreinoContract.PATH + "/LimpezaBase/*", LIMPEZABASE);
		
		matcher.addURI(DiaTreinoContract.getContentAuthority(), DiaTreinoContract.PATH + "/HistoricoExecucaoPorIdExercicio/*", HISTORICOEXECUCAOPORIDEXERCICIO);
		
		matcher.addURI(DiaTreinoContract.getContentAuthority(), DiaTreinoContract.PATH + "/TreinosPosDataPesquisa/*", TREINOSPOSDATAPESQUISA);
		
		
		// Deletes
		matcher.addURI(DiaTreinoContract.getContentAuthority(), DiaTreinoContract.PATH + "/DeleteAllSinc" , 	DIA_TREINO_DELETE_ALL_SINC);
		matcher.addURI(DiaTreinoContract.getContentAuthority(), DiaTreinoContract.PATH + "/DeleteAllRecreate" , DIA_TREINO_DELETE_RECREATE);
		matcher.addURI(DiaTreinoContract.getContentAuthority(), DiaTreinoContract.PATH + "/DeleteSinc/#" , 		DIA_TREINO_DELETE_SINC);
	}
	
	
	
	
	public void setAplicacaoDbHelper(AplicacaoDbHelper db) {
		mOpenHelper = db;
	}
	
	public Cursor query(UriMatcher sUriMatcher, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Query Uri:" + uri.toString());
		Cursor retCursor = null;
		switch (sUriMatcher.match(uri)) {
            case DIA_TREINO:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: DIA_TREINO");
                retCursor = query(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case DIA_TREINO_SINC:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: DIA_TREINO_SINC");
                retCursor = querySinc(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case DIA_TREINO_ID:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: DIA_TREINO_ID");
            	String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorChaveSel, args, sortOrder);
                break;
            }
            case DIA_TREINO_ID_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: DIA_TREINO_ID_E_COMPLEMENTO");
				String id = uri.getPathSegments().get(1);	
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri) +
						" where " + DiaTreinoContract.COLUNA_CHAVE + " = " + id;
				retCursor = queryRaw(sql);
				break;
			}
			case DIA_TREINO_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: DIA_TREINO_E_COMPLEMENTO");
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case DIA_TREINO_E_RETIRADA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: DIA_TREINO_E_RETIRADA");
				String sql = "select " +  DiaTreinoContract.camposOrdenados() +
						" from " + DiaTreinoContract.TABLE_NAME +
						sqlWhere(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case DIA_TREINO_POR_SERIE_TREINO_SERIEDIA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: DIA_TREINO_POR_SERIE_TREINO_SERIEDIA");
	            String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorIdSerieTreinoSdSelecao, args, sortOrder);
                break;
            }
            case COM_SERIE_TREINO:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: COM_SERIE_TREINO");
            	String sql = "select " + DiaTreinoContract.camposOrdenados() + " , " +
						SerieTreinoContract.camposOrdenados() +
						" from " + DiaTreinoContract.TABLE_NAME +
						DiaTreinoContract.innerJoinSerieTreino_SerieDia() +
						getOrderBy();
                retCursor = queryRaw(sql);
				break;
            }
		
		
	 		case ENCERRADIATREINO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: ENCERRADIATREINO");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryEncerraDiaTreino()");
				//retCursor = queryEncerraDiaTreino(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryEncerraDiaTreino(param) + (sortOrder!=null?" order by " + sortOrder:"");
				DCLog.d(DCLog.TRACE_LISTA,this,"SQL: " + sql);
				retCursor = this.queryRaw(sql);
				DCLog.d(DCLog.TRACE_LISTA,this,"Cursor: " + retCursor.getCount() + " linhas");
				break;
			}
		
	 		case OBTEMPORDATA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: OBTEMPORDATA");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryObtemPorData()");
				//retCursor = queryObtemPorData(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryObtemPorData(param) + (sortOrder!=null?" order by " + sortOrder:"");
				DCLog.d(DCLog.TRACE_LISTA,this,"SQL: " + sql);
				retCursor = this.queryRaw(sql);
				DCLog.d(DCLog.TRACE_LISTA,this,"Cursor: " + retCursor.getCount() + " linhas");
				break;
			}
		
	 		case LIMPEZABASE:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: LIMPEZABASE");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryLimpezaBase()");
				//retCursor = queryLimpezaBase(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryLimpezaBase(param) + (sortOrder!=null?" order by " + sortOrder:"");
				DCLog.d(DCLog.TRACE_LISTA,this,"SQL: " + sql);
				retCursor = this.queryRaw(sql);
				DCLog.d(DCLog.TRACE_LISTA,this,"Cursor: " + retCursor.getCount() + " linhas");
				break;
			}
		
	 		case HISTORICOEXECUCAOPORIDEXERCICIO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: HISTORICOEXECUCAOPORIDEXERCICIO");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryHistoricoExecucaoPorIdExercicio()");
				//retCursor = queryHistoricoExecucaoPorIdExercicio(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryHistoricoExecucaoPorIdExercicio(param) + (sortOrder!=null?" order by " + sortOrder:"");
				DCLog.d(DCLog.TRACE_LISTA,this,"SQL: " + sql);
				retCursor = this.queryRaw(sql);
				DCLog.d(DCLog.TRACE_LISTA,this,"Cursor: " + retCursor.getCount() + " linhas");
				break;
			}
		
	 		case TREINOSPOSDATAPESQUISA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: TREINOSPOSDATAPESQUISA");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryTreinosPosDataPesquisa()");
				//retCursor = queryTreinosPosDataPesquisa(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryTreinosPosDataPesquisa(param) + (sortOrder!=null?" order by " + sortOrder:"");
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
  	
		if (existeItem("SemExecucaoItemSerieFoiRealizado",uri.getPathSegments())) {
			sql += DiaTreinoContract.COLUNA_CHAVE + " not in (select " + 
					ExecucaoItemSerieContract.COLUNA_ID_DIA_TREINO_E + " from " +
					ExecucaoItemSerieContract.TABLE_NAME + ")";
		}
	
		
		return sql;
	}
	
	private String sqlSelect(Uri uri) {
		String sql = DiaTreinoContract.camposOrdenados();
		List<String> segmentos = uri.getPathSegments();
		// ComChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComSerieTreinoSerieDia",uri.getPathSegments())) {
			sql += "," +  SerieTreinoContract.camposOrdenados();
		}
	
	
	// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComExecucaoItemSerieFoiRealizado",uri.getPathSegments())) {
			sql += "," +  ExecucaoItemSerieContract.camposOrdenados();
		}
	
		
		
		
		return sql;
	}
	private String sqlFrom(Uri uri) {
		String sql = DiaTreinoContract.TABLE_NAME;
		List<String> segmentos = uri.getPathSegments();
		//if (existeItem("ComEpisodioReferenteA",uri.getPathSegments())) {
		//	sql += " " +  EpisodioUsuarioContract.innerJoinEpisodio_ReferenteA();
		//}
		
		
				// ComChaveEstrangeira
  	
		if (existeItem("ComSerieTreinoSerieDia",uri.getPathSegments())) {
			sql += " " +  DiaTreinoContract.outerJoinSerieTreino_SerieDia();
		}
	
	
	// SemChaveEstrangeira
  	
		if (existeItem("ComExecucaoItemSerieFoiRealizado",uri.getPathSegments())) {
			sql += " " +  DiaTreinoContract.outerJoinExecucaoItemSerie_FoiRealizado();
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
	
	
	protected abstract String queryEncerraDiaTreino(String param);
	
	protected abstract String queryObtemPorData(String param);
	
	protected abstract String queryLimpezaBase(String param);
	
	protected abstract String queryHistoricoExecucaoPorIdExercicio(String param);
	
	protected abstract String queryTreinosPosDataPesquisa(String param);
	
	
	
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
            case DIA_TREINO:
            {
                return DiaTreinoContract.getContentType();
            }
            case DIA_TREINO_ID:
            {
            	return DiaTreinoContract.getContentItemType();
            }
			case DIA_TREINO_POR_SERIE_TREINO_SERIEDIA:
            {
	            return DiaTreinoContract.getContentType();
            }
		
		}	
		return null;
	}
	
	public Uri insert(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Uri returnUri;
		long idNovo = getMaxId(db)+1;
		values.put(DiaTreinoContract.COLUNA_CHAVE, idNovo);
		long _id = db.insert(DiaTreinoContract.TABLE_NAME, null, values);
		if (_id > 0) {
			//DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + DiaTreinoContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
			DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + values.toString() + " (id:" + _id + ")");
			returnUri = DiaTreinoContract.buildDiaTreinoUri(idNovo);
			values.put(DiaTreinoContract.COLUNA_OPERACAO_SINC,"I");
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
			case DIA_TREINO_DELETE_SINC: {
				String id = uri.getPathSegments().get(2);
				Cursor cursor = db.query(DiaTreinoContract.TABLE_NAME,null,DiaTreinoContract.COLUNA_CHAVE + " = ? ",new String[]{id},null,null,null);
				if (cursor.moveToFirst()) {
					ContentValues valores = new ContentValues();
					DatabaseUtils.cursorRowToContentValues(cursor, valores);
					linhaDelete = db.delete(DiaTreinoContract.TABLE_NAME, DiaTreinoContract.COLUNA_CHAVE + " = ? ", new String[]{id});
					DCLog.d(DCLog.DATABASE_CRUD,this,"delete " + DiaTreinoContract.TABLE_NAME + "(id:" + id + ")");
					valores.put(DiaTreinoContract.COLUNA_OPERACAO_SINC, "D");
					insereSinc(db,valores);
				}
				notificaUriRelacionadas();
				mProvider.getContext().getContentResolver().notifyChange(DiaTreinoContract.buildAll(), null);
				break;
			}
			case DIA_TREINO_DELETE_ALL_SINC: {
				linhaDelete = db.delete(DiaTreinoContract.TABLE_NAME_SINC, null, null);
				break;
			}
			case DIA_TREINO_DELETE_RECREATE: {
				linhaDelete = db.delete(DiaTreinoContract.TABLE_NAME, null, null);
				break;
			}
		}
		return true;
	}
	
	
	public boolean update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		DCLog.d(DCLog.DATABASE_CRUD,this,"update2 " + values.toString() );
		linhaUpdate = db.update(DiaTreinoContract.TABLE_NAME, values, selection, selectionArgs);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	public boolean update(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		String selection = DiaTreinoContract.COLUNA_CHAVE + " = ? ";
		String dados[] = {values.get(DiaTreinoContract.COLUNA_CHAVE).toString()};
		DCLog.d(DCLog.DATABASE_CRUD,this,"update1 " + values.toString() );
		linhaUpdate = db.update(DiaTreinoContract.TABLE_NAME, values, selection, dados);
		values.put(DiaTreinoContract.COLUNA_OPERACAO_SINC,"A");
		insereSinc(db,values);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	
	private void insereSinc(SQLiteDatabase db, ContentValues values) {
		db.insert(DiaTreinoContract.TABLE_NAME_SINC, null, values);
		notificaUriOperacoes();
	}
	
	protected abstract void notificaOutrasUri(ContentResolver resolver);
	
	
	// Notificar todas as uris de entidades que possuem chave estrangeira dessa entidade.
	private void notificaUriRelacionadas() {
		// ComChaveEstrangeira
  	
		mProvider.getContext().getContentResolver().notifyChange(SerieTreinoContract.buildAllComDiaTreinoSerieDia(), null);
		mProvider.getContext().getContentResolver().notifyChange(SerieTreinoContract.buildAllSemDiaTreinoSerieDia(), null);
	
	}
	private void notificaUriOperacoes() {
	
		mProvider.getContext().getContentResolver().notifyChange(DiaTreinoContract.buildEncerraDiaTreino(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(DiaTreinoContract.buildObtemPorData(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(DiaTreinoContract.buildLimpezaBase(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(DiaTreinoContract.buildHistoricoExecucaoPorIdExercicio(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(DiaTreinoContract.buildTreinosPosDataPesquisa(), null);
	
	}
	private void notificaUriRaiz(){
		
	}
	
	
	
	public int bulkInsert(Uri uri, ContentValues[] values) {
    	final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        db.beginTransaction();
        int returnCount = 0;
        try {
        	for (ContentValues value : values) {
				String[] args = {(String)value.get(DiaTreinoContract.COLUNA_CHAVE)};
				Cursor retCursor = query(null, sPorChaveSel, args, null);
				if (retCursor.moveToFirst()) {
						db.update(DiaTreinoContract.TABLE_NAME,value,sPorChaveSel,args);
						//DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk " + DiaTreinoContract.TABLE_NAME + "  " + values.toString());
						DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk id:" + value.get(DiaTreinoContract.COLUNA_CHAVE) + ")" + values.toString());
				} else {
					long _id = db.insert(DiaTreinoContract.TABLE_NAME, null, value);
					if (_id != -1) {
						//DCLog.d(DCLog.DATABASE_CRUD,this,"insert (bulk)" + DiaTreinoContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
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
            	long _id = db.insert(DiaTreinoContract.TABLE_NAME, null, value);
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
		String sql = "select max(" + DiaTreinoContract.COLUNA_CHAVE + ") from " + DiaTreinoContract.TABLE_NAME;
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