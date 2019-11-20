
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

public abstract class ExercicioProvider {

	private int qtdeLinhas = 0;
	
	public static final int EXERCICIO = 74300;
	public static final int EXERCICIO_ID = 74301;
	public static final int EXERCICIO_SINC = 74303;
	public static final int EXERCICIO_E_COMPLEMENTO = 74304;
	public static final int EXERCICIO_ID_E_COMPLEMENTO = 74305;
	//public static final int EXERCICIO_OPERACAO = 74302;
	
	// deletes
	public static final int EXERCICIO_DELETE_ALL_SINC = 74306;
	public static final int EXERCICIO_DELETE_RECREATE = 74307;
	public static final int EXERCICIO_DELETE_SINC = 74308;
	public static final int EXERCICIO_E_RETIRADA = 74309;
	
	private static final String sPorChaveSel = ExercicioContract.TABLE_NAME + "." + ExercicioContract.COLUNA_CHAVE + " = ? ";
	
	
	
	public static final int EXERCICIO_POR_GRUPO_MUSCULAR_PARA = 74320;
	public static final int COM_GRUPO_MUSCULAR = 74321;
	private static final String sPorIdGrupoMuscularPSelecao =
            ExercicioContract.TABLE_NAME + ".id_grupo_muscular_p = ? ";
	

 	public static final int LISTAATIVOSNOMOMENTO = 74322;

	private ContentProvider mProvider = null;


	public void setContentProvider(ContentProvider valor) {
		mProvider = valor;
	}

	protected static final SQLiteQueryBuilder sQueryBuilder;
	static {
		sQueryBuilder = new SQLiteQueryBuilder();
		String tabelas = ExercicioContract.TABLE_NAME;
		
		//tabelas += " inner join " + GrupoMuscularContract.TABLE_NAME + " ";
		//tabelas += " on " + GrupoMuscularContract.TABLE_NAME + "." + GrupoMuscularContract.COLUNA_CHAVE + " = " + ExercicioContract.TABLE_NAME + "." + ExercicioContract.COLUNA_ID_GRUPO_MUSCULAR_P; 
		
		sQueryBuilder.setTables(tabelas);
	}
	private static final SQLiteQueryBuilder sQueryBuilderSinc;
	static {
		sQueryBuilderSinc = new SQLiteQueryBuilder();
		String tabelas = ExercicioContract.TABLE_NAME_SINC;
		sQueryBuilderSinc.setTables(tabelas);
	}
	
	
	protected AplicacaoDbHelper mOpenHelper = null;
	
	public int getLinhas() {
		return qtdeLinhas;
	}
	
	public static void buildUriMatcher(UriMatcher matcher) {
		matcher.addURI(ExercicioContract.getContentAuthority(), ExercicioContract.PATH, EXERCICIO);
		matcher.addURI(ExercicioContract.getContentAuthority(), ExercicioContract.PATH + "/Sinc" , EXERCICIO_SINC);
		matcher.addURI(ExercicioContract.getContentAuthority(), ExercicioContract.PATH + "/#"    , EXERCICIO_ID);
	
		matcher.addURI(ExercicioContract.getContentAuthority(), ExercicioContract.PATH + "/#/" + ExercicioContract.COM_COMPLEMENTO + "/*" , EXERCICIO_ID_E_COMPLEMENTO);
		matcher.addURI(ExercicioContract.getContentAuthority(), ExercicioContract.PATH + "/" + ExercicioContract.COM_COMPLEMENTO + "/*" , EXERCICIO_E_COMPLEMENTO);
		matcher.addURI(ExercicioContract.getContentAuthority(), ExercicioContract.PATH + "/" + ExercicioContract.COM_RETIRADA + "/*" , EXERCICIO_E_RETIRADA);
		
		
		//matcher.addURI(AplicacaoContract.CONTENT_AUTHORITY, ExercicioContract.PATH + "/operacao/*" , EXERCICIO_OPERACAO);
		
		matcher.addURI(ExercicioContract.getContentAuthority(), ExercicioContract.PATH + "/#/" + GrupoMuscularContract.PATH, EXERCICIO_POR_GRUPO_MUSCULAR_PARA);
		matcher.addURI(ExercicioContract.getContentAuthority(), ExercicioContract.PATH + "/ComGrupoMuscularPara/" , COM_GRUPO_MUSCULAR);
		
		
		
		matcher.addURI(ExercicioContract.getContentAuthority(), ExercicioContract.PATH + "/ListaAtivosNoMomento/*", LISTAATIVOSNOMOMENTO);
		
		
		// Deletes
		matcher.addURI(ExercicioContract.getContentAuthority(), ExercicioContract.PATH + "/DeleteAllSinc" , 	EXERCICIO_DELETE_ALL_SINC);
		matcher.addURI(ExercicioContract.getContentAuthority(), ExercicioContract.PATH + "/DeleteAllRecreate" , EXERCICIO_DELETE_RECREATE);
		matcher.addURI(ExercicioContract.getContentAuthority(), ExercicioContract.PATH + "/DeleteSinc/#" , 		EXERCICIO_DELETE_SINC);
	}
	
	
	
	
	public void setAplicacaoDbHelper(AplicacaoDbHelper db) {
		mOpenHelper = db;
	}
	
	public Cursor query(UriMatcher sUriMatcher, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Query Uri:" + uri.toString());
		Cursor retCursor = null;
		switch (sUriMatcher.match(uri)) {
            case EXERCICIO:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: EXERCICIO");
                retCursor = query(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case EXERCICIO_SINC:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: EXERCICIO_SINC");
                retCursor = querySinc(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case EXERCICIO_ID:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: EXERCICIO_ID");
            	String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorChaveSel, args, sortOrder);
                break;
            }
            case EXERCICIO_ID_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: EXERCICIO_ID_E_COMPLEMENTO");
				String id = uri.getPathSegments().get(1);	
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri) +
						" where " + ExercicioContract.COLUNA_CHAVE + " = " + id;
				retCursor = queryRaw(sql);
				break;
			}
			case EXERCICIO_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: EXERCICIO_E_COMPLEMENTO");
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case EXERCICIO_E_RETIRADA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: EXERCICIO_E_RETIRADA");
				String sql = "select " +  ExercicioContract.camposOrdenados() +
						" from " + ExercicioContract.TABLE_NAME +
						sqlWhere(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case EXERCICIO_POR_GRUPO_MUSCULAR_PARA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: EXERCICIO_POR_GRUPO_MUSCULAR_PARA");
	            String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorIdGrupoMuscularPSelecao, args, sortOrder);
                break;
            }
            case COM_GRUPO_MUSCULAR:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: COM_GRUPO_MUSCULAR");
            	String sql = "select " + ExercicioContract.camposOrdenados() + " , " +
						GrupoMuscularContract.camposOrdenados() +
						" from " + ExercicioContract.TABLE_NAME +
						ExercicioContract.innerJoinGrupoMuscular_Para() +
						getOrderBy();
                retCursor = queryRaw(sql);
				break;
            }
		
		
	 		case LISTAATIVOSNOMOMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: LISTAATIVOSNOMOMENTO");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryListaAtivosNoMomento()");
				//retCursor = queryListaAtivosNoMomento(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryListaAtivosNoMomento(param) + (sortOrder!=null?" order by " + sortOrder:"");
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
  	
		if (existeItem("SemItemSerieGera",uri.getPathSegments())) {
			sql += ExercicioContract.COLUNA_CHAVE + " not in (select " + 
					ItemSerieContract.COLUNA_ID_EXERCICIO_ED + " from " +
					ItemSerieContract.TABLE_NAME + ")";
		}
	
		if (existeItem("SemExecucaoItemSerieExecutado",uri.getPathSegments())) {
			sql += ExercicioContract.COLUNA_CHAVE + " not in (select " + 
					ExecucaoItemSerieContract.COLUNA_ID_EXERCICIO_RA + " from " +
					ExecucaoItemSerieContract.TABLE_NAME + ")";
		}
	
		
		return sql;
	}
	
	private String sqlSelect(Uri uri) {
		String sql = ExercicioContract.camposOrdenados();
		List<String> segmentos = uri.getPathSegments();
		// ComChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComGrupoMuscularPara",uri.getPathSegments())) {
			sql += "," +  GrupoMuscularContract.camposOrdenados();
		}
	
	
	// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComItemSerieGera",uri.getPathSegments())) {
			sql += "," +  ItemSerieContract.camposOrdenados();
		}
	
		if (existeItem("ComExecucaoItemSerieExecutado",uri.getPathSegments())) {
			sql += "," +  ExecucaoItemSerieContract.camposOrdenados();
		}
	
		
		
		
		return sql;
	}
	private String sqlFrom(Uri uri) {
		String sql = ExercicioContract.TABLE_NAME;
		List<String> segmentos = uri.getPathSegments();
		//if (existeItem("ComEpisodioReferenteA",uri.getPathSegments())) {
		//	sql += " " +  EpisodioUsuarioContract.innerJoinEpisodio_ReferenteA();
		//}
		
		
				// ComChaveEstrangeira
  	
		if (existeItem("ComGrupoMuscularPara",uri.getPathSegments())) {
			sql += " " +  ExercicioContract.outerJoinGrupoMuscular_Para();
		}
	
	
	// SemChaveEstrangeira
  	
		if (existeItem("ComItemSerieGera",uri.getPathSegments())) {
			sql += " " +  ExercicioContract.outerJoinItemSerie_Gera();
		}
	
		if (existeItem("ComExecucaoItemSerieExecutado",uri.getPathSegments())) {
			sql += " " +  ExercicioContract.outerJoinExecucaoItemSerie_Executado();
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
	
	
	protected abstract String queryListaAtivosNoMomento(String param);
	
	
	
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
            case EXERCICIO:
            {
                return ExercicioContract.getContentType();
            }
            case EXERCICIO_ID:
            {
            	return ExercicioContract.getContentItemType();
            }
			case EXERCICIO_POR_GRUPO_MUSCULAR_PARA:
            {
	            return ExercicioContract.getContentType();
            }
		
		}	
		return null;
	}
	
	public Uri insert(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Uri returnUri;
		long idNovo = getMaxId(db)+1;
		values.put(ExercicioContract.COLUNA_CHAVE, idNovo);
		long _id = db.insert(ExercicioContract.TABLE_NAME, null, values);
		if (_id > 0) {
			//DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + ExercicioContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
			DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + values.toString() + " (id:" + _id + ")");
			returnUri = ExercicioContract.buildExercicioUri(idNovo);
			values.put(ExercicioContract.COLUNA_OPERACAO_SINC,"I");
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
			case EXERCICIO_DELETE_SINC: {
				String id = uri.getPathSegments().get(2);
				Cursor cursor = db.query(ExercicioContract.TABLE_NAME,null,ExercicioContract.COLUNA_CHAVE + " = ? ",new String[]{id},null,null,null);
				if (cursor.moveToFirst()) {
					ContentValues valores = new ContentValues();
					DatabaseUtils.cursorRowToContentValues(cursor, valores);
					linhaDelete = db.delete(ExercicioContract.TABLE_NAME, ExercicioContract.COLUNA_CHAVE + " = ? ", new String[]{id});
					DCLog.d(DCLog.DATABASE_CRUD,this,"delete " + ExercicioContract.TABLE_NAME + "(id:" + id + ")");
					valores.put(ExercicioContract.COLUNA_OPERACAO_SINC, "D");
					insereSinc(db,valores);
				}
				notificaUriRelacionadas();
				mProvider.getContext().getContentResolver().notifyChange(ExercicioContract.buildAll(), null);
				break;
			}
			case EXERCICIO_DELETE_ALL_SINC: {
				linhaDelete = db.delete(ExercicioContract.TABLE_NAME_SINC, null, null);
				break;
			}
			case EXERCICIO_DELETE_RECREATE: {
				linhaDelete = db.delete(ExercicioContract.TABLE_NAME, null, null);
				break;
			}
		}
		return true;
	}
	
	
	public boolean update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		DCLog.d(DCLog.DATABASE_CRUD,this,"update2 " + values.toString() );
		linhaUpdate = db.update(ExercicioContract.TABLE_NAME, values, selection, selectionArgs);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	public boolean update(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		String selection = ExercicioContract.COLUNA_CHAVE + " = ? ";
		String dados[] = {values.get(ExercicioContract.COLUNA_CHAVE).toString()};
		DCLog.d(DCLog.DATABASE_CRUD,this,"update1 " + values.toString() );
		linhaUpdate = db.update(ExercicioContract.TABLE_NAME, values, selection, dados);
		values.put(ExercicioContract.COLUNA_OPERACAO_SINC,"A");
		insereSinc(db,values);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	
	private void insereSinc(SQLiteDatabase db, ContentValues values) {
		db.insert(ExercicioContract.TABLE_NAME_SINC, null, values);
		notificaUriOperacoes();
	}
	
	protected abstract void notificaOutrasUri(ContentResolver resolver);
	
	
	// Notificar todas as uris de entidades que possuem chave estrangeira dessa entidade.
	private void notificaUriRelacionadas() {
		// ComChaveEstrangeira
  	
		mProvider.getContext().getContentResolver().notifyChange(GrupoMuscularContract.buildAllComExercicioReferenteA(), null);
		mProvider.getContext().getContentResolver().notifyChange(GrupoMuscularContract.buildAllSemExercicioReferenteA(), null);
	
	}
	private void notificaUriOperacoes() {
	
		mProvider.getContext().getContentResolver().notifyChange(ExercicioContract.buildListaAtivosNoMomento(), null);
	
	}
	private void notificaUriRaiz(){
		
	}
	
	
	
	public int bulkInsert(Uri uri, ContentValues[] values) {
    	final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        db.beginTransaction();
        int returnCount = 0;
        try {
        	for (ContentValues value : values) {
				String[] args = {(String)value.get(ExercicioContract.COLUNA_CHAVE)};
				Cursor retCursor = query(null, sPorChaveSel, args, null);
				if (retCursor.moveToFirst()) {
						db.update(ExercicioContract.TABLE_NAME,value,sPorChaveSel,args);
						//DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk " + ExercicioContract.TABLE_NAME + "  " + values.toString());
						DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk id:" + value.get(ExercicioContract.COLUNA_CHAVE) + ")" + values.toString());
				} else {
					long _id = db.insert(ExercicioContract.TABLE_NAME, null, value);
					if (_id != -1) {
						//DCLog.d(DCLog.DATABASE_CRUD,this,"insert (bulk)" + ExercicioContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
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
            	long _id = db.insert(ExercicioContract.TABLE_NAME, null, value);
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
		String sql = "select max(" + ExercicioContract.COLUNA_CHAVE + ") from " + ExercicioContract.TABLE_NAME;
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