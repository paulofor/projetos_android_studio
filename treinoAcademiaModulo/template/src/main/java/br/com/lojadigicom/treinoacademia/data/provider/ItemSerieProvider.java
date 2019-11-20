
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

public abstract class ItemSerieProvider {

	private int qtdeLinhas = 0;
	
	public static final int ITEM_SERIE = 74500;
	public static final int ITEM_SERIE_ID = 74501;
	public static final int ITEM_SERIE_SINC = 74503;
	public static final int ITEM_SERIE_E_COMPLEMENTO = 74504;
	public static final int ITEM_SERIE_ID_E_COMPLEMENTO = 74505;
	//public static final int ITEM_SERIE_OPERACAO = 74502;
	
	// deletes
	public static final int ITEM_SERIE_DELETE_ALL_SINC = 74506;
	public static final int ITEM_SERIE_DELETE_RECREATE = 74507;
	public static final int ITEM_SERIE_DELETE_SINC = 74508;
	public static final int ITEM_SERIE_E_RETIRADA = 74509;
	
	private static final String sPorChaveSel = ItemSerieContract.TABLE_NAME + "." + ItemSerieContract.COLUNA_CHAVE + " = ? ";
	
	
	
	public static final int ITEM_SERIE_POR_EXERCICIO_EXECUCAODE = 74520;
	public static final int COM_EXERCICIO = 74521;
	private static final String sPorIdExercicioEdSelecao =
            ItemSerieContract.TABLE_NAME + ".id_exercicio_ed = ? ";
	
	public static final int ITEM_SERIE_POR_SERIE_TREINO_PERTENCENTEA = 74522;
	public static final int COM_SERIE_TREINO = 74523;
	private static final String sPorIdSerieTreinoPaSelecao =
            ItemSerieContract.TABLE_NAME + ".id_serie_treino_pa = ? ";
	

 	public static final int LISTAPORDIACOMEXECUCAO = 74524;
 	public static final int FINALIZAITEMSERIE = 74525;
 	public static final int CARREGACOMPLETO = 74526;
 	public static final int CARREGAULTIMASEXECUCOES = 74527;
 	public static final int ATUALIZACARGA = 74528;

	private ContentProvider mProvider = null;


	public void setContentProvider(ContentProvider valor) {
		mProvider = valor;
	}

	protected static final SQLiteQueryBuilder sQueryBuilder;
	static {
		sQueryBuilder = new SQLiteQueryBuilder();
		String tabelas = ItemSerieContract.TABLE_NAME;
		
		//tabelas += " inner join " + ExercicioContract.TABLE_NAME + " ";
		//tabelas += " on " + ExercicioContract.TABLE_NAME + "." + ExercicioContract.COLUNA_CHAVE + " = " + ItemSerieContract.TABLE_NAME + "." + ItemSerieContract.COLUNA_ID_EXERCICIO_ED; 
		
		//tabelas += " inner join " + SerieTreinoContract.TABLE_NAME + " ";
		//tabelas += " on " + SerieTreinoContract.TABLE_NAME + "." + SerieTreinoContract.COLUNA_CHAVE + " = " + ItemSerieContract.TABLE_NAME + "." + ItemSerieContract.COLUNA_ID_SERIE_TREINO_PA; 
		
		sQueryBuilder.setTables(tabelas);
	}
	private static final SQLiteQueryBuilder sQueryBuilderSinc;
	static {
		sQueryBuilderSinc = new SQLiteQueryBuilder();
		String tabelas = ItemSerieContract.TABLE_NAME_SINC;
		sQueryBuilderSinc.setTables(tabelas);
	}
	
	
	protected AplicacaoDbHelper mOpenHelper = null;
	
	public int getLinhas() {
		return qtdeLinhas;
	}
	
	public static void buildUriMatcher(UriMatcher matcher) {
		matcher.addURI(ItemSerieContract.getContentAuthority(), ItemSerieContract.PATH, ITEM_SERIE);
		matcher.addURI(ItemSerieContract.getContentAuthority(), ItemSerieContract.PATH + "/Sinc" , ITEM_SERIE_SINC);
		matcher.addURI(ItemSerieContract.getContentAuthority(), ItemSerieContract.PATH + "/#"    , ITEM_SERIE_ID);
	
		matcher.addURI(ItemSerieContract.getContentAuthority(), ItemSerieContract.PATH + "/#/" + ItemSerieContract.COM_COMPLEMENTO + "/*" , ITEM_SERIE_ID_E_COMPLEMENTO);
		matcher.addURI(ItemSerieContract.getContentAuthority(), ItemSerieContract.PATH + "/" + ItemSerieContract.COM_COMPLEMENTO + "/*" , ITEM_SERIE_E_COMPLEMENTO);
		matcher.addURI(ItemSerieContract.getContentAuthority(), ItemSerieContract.PATH + "/" + ItemSerieContract.COM_RETIRADA + "/*" , ITEM_SERIE_E_RETIRADA);
		
		
		//matcher.addURI(AplicacaoContract.CONTENT_AUTHORITY, ItemSerieContract.PATH + "/operacao/*" , ITEM_SERIE_OPERACAO);
		
		matcher.addURI(ItemSerieContract.getContentAuthority(), ItemSerieContract.PATH + "/#/" + ExercicioContract.PATH, ITEM_SERIE_POR_EXERCICIO_EXECUCAODE);
		matcher.addURI(ItemSerieContract.getContentAuthority(), ItemSerieContract.PATH + "/ComExercicioExecucaoDe/" , COM_EXERCICIO);
		
		matcher.addURI(ItemSerieContract.getContentAuthority(), ItemSerieContract.PATH + "/#/" + SerieTreinoContract.PATH, ITEM_SERIE_POR_SERIE_TREINO_PERTENCENTEA);
		matcher.addURI(ItemSerieContract.getContentAuthority(), ItemSerieContract.PATH + "/ComSerieTreinoPertencenteA/" , COM_SERIE_TREINO);
		
		
		
		matcher.addURI(ItemSerieContract.getContentAuthority(), ItemSerieContract.PATH + "/ListaPorDiaComExecucao/*", LISTAPORDIACOMEXECUCAO);
		
		matcher.addURI(ItemSerieContract.getContentAuthority(), ItemSerieContract.PATH + "/FinalizaItemSerie/*", FINALIZAITEMSERIE);
		
		matcher.addURI(ItemSerieContract.getContentAuthority(), ItemSerieContract.PATH + "/CarregaCompleto/*", CARREGACOMPLETO);
		
		matcher.addURI(ItemSerieContract.getContentAuthority(), ItemSerieContract.PATH + "/CarregaUltimasExecucoes/*", CARREGAULTIMASEXECUCOES);
		
		matcher.addURI(ItemSerieContract.getContentAuthority(), ItemSerieContract.PATH + "/AtualizaCarga/*", ATUALIZACARGA);
		
		
		// Deletes
		matcher.addURI(ItemSerieContract.getContentAuthority(), ItemSerieContract.PATH + "/DeleteAllSinc" , 	ITEM_SERIE_DELETE_ALL_SINC);
		matcher.addURI(ItemSerieContract.getContentAuthority(), ItemSerieContract.PATH + "/DeleteAllRecreate" , ITEM_SERIE_DELETE_RECREATE);
		matcher.addURI(ItemSerieContract.getContentAuthority(), ItemSerieContract.PATH + "/DeleteSinc/#" , 		ITEM_SERIE_DELETE_SINC);
	}
	
	
	
	
	public void setAplicacaoDbHelper(AplicacaoDbHelper db) {
		mOpenHelper = db;
	}
	
	public Cursor query(UriMatcher sUriMatcher, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Query Uri:" + uri.toString());
		Cursor retCursor = null;
		switch (sUriMatcher.match(uri)) {
            case ITEM_SERIE:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: ITEM_SERIE");
                retCursor = query(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case ITEM_SERIE_SINC:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: ITEM_SERIE_SINC");
                retCursor = querySinc(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case ITEM_SERIE_ID:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: ITEM_SERIE_ID");
            	String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorChaveSel, args, sortOrder);
                break;
            }
            case ITEM_SERIE_ID_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: ITEM_SERIE_ID_E_COMPLEMENTO");
				String id = uri.getPathSegments().get(1);	
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri) +
						" where " + ItemSerieContract.COLUNA_CHAVE + " = " + id;
				retCursor = queryRaw(sql);
				break;
			}
			case ITEM_SERIE_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: ITEM_SERIE_E_COMPLEMENTO");
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case ITEM_SERIE_E_RETIRADA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: ITEM_SERIE_E_RETIRADA");
				String sql = "select " +  ItemSerieContract.camposOrdenados() +
						" from " + ItemSerieContract.TABLE_NAME +
						sqlWhere(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case ITEM_SERIE_POR_EXERCICIO_EXECUCAODE:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: ITEM_SERIE_POR_EXERCICIO_EXECUCAODE");
	            String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorIdExercicioEdSelecao, args, sortOrder);
                break;
            }
            case COM_EXERCICIO:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: COM_EXERCICIO");
            	String sql = "select " + ItemSerieContract.camposOrdenados() + " , " +
						ExercicioContract.camposOrdenados() +
						" from " + ItemSerieContract.TABLE_NAME +
						ItemSerieContract.innerJoinExercicio_ExecucaoDe() +
						getOrderBy();
                retCursor = queryRaw(sql);
				break;
            }
		
			case ITEM_SERIE_POR_SERIE_TREINO_PERTENCENTEA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: ITEM_SERIE_POR_SERIE_TREINO_PERTENCENTEA");
	            String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorIdSerieTreinoPaSelecao, args, sortOrder);
                break;
            }
            case COM_SERIE_TREINO:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: COM_SERIE_TREINO");
            	String sql = "select " + ItemSerieContract.camposOrdenados() + " , " +
						SerieTreinoContract.camposOrdenados() +
						" from " + ItemSerieContract.TABLE_NAME +
						ItemSerieContract.innerJoinSerieTreino_PertencenteA() +
						getOrderBy();
                retCursor = queryRaw(sql);
				break;
            }
		
		
	 		case LISTAPORDIACOMEXECUCAO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: LISTAPORDIACOMEXECUCAO");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryListaPorDiaComExecucao()");
				//retCursor = queryListaPorDiaComExecucao(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryListaPorDiaComExecucao(param) + (sortOrder!=null?" order by " + sortOrder:"");
				DCLog.d(DCLog.TRACE_LISTA,this,"SQL: " + sql);
				retCursor = this.queryRaw(sql);
				DCLog.d(DCLog.TRACE_LISTA,this,"Cursor: " + retCursor.getCount() + " linhas");
				break;
			}
		
	 		case FINALIZAITEMSERIE:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: FINALIZAITEMSERIE");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryFinalizaItemSerie()");
				//retCursor = queryFinalizaItemSerie(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryFinalizaItemSerie(param) + (sortOrder!=null?" order by " + sortOrder:"");
				DCLog.d(DCLog.TRACE_LISTA,this,"SQL: " + sql);
				retCursor = this.queryRaw(sql);
				DCLog.d(DCLog.TRACE_LISTA,this,"Cursor: " + retCursor.getCount() + " linhas");
				break;
			}
		
	 		case CARREGACOMPLETO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CARREGACOMPLETO");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryCarregaCompleto()");
				//retCursor = queryCarregaCompleto(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryCarregaCompleto(param) + (sortOrder!=null?" order by " + sortOrder:"");
				DCLog.d(DCLog.TRACE_LISTA,this,"SQL: " + sql);
				retCursor = this.queryRaw(sql);
				DCLog.d(DCLog.TRACE_LISTA,this,"Cursor: " + retCursor.getCount() + " linhas");
				break;
			}
		
	 		case CARREGAULTIMASEXECUCOES:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CARREGAULTIMASEXECUCOES");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryCarregaUltimasExecucoes()");
				//retCursor = queryCarregaUltimasExecucoes(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryCarregaUltimasExecucoes(param) + (sortOrder!=null?" order by " + sortOrder:"");
				DCLog.d(DCLog.TRACE_LISTA,this,"SQL: " + sql);
				retCursor = this.queryRaw(sql);
				DCLog.d(DCLog.TRACE_LISTA,this,"Cursor: " + retCursor.getCount() + " linhas");
				break;
			}
		
	 		case ATUALIZACARGA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: ATUALIZACARGA");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryAtualizaCarga()");
				//retCursor = queryAtualizaCarga(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryAtualizaCarga(param) + (sortOrder!=null?" order by " + sortOrder:"");
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
  	
		if (existeItem("SemCargaPlanejadaPossui",uri.getPathSegments())) {
			sql += ItemSerieContract.COLUNA_CHAVE + " not in (select " + 
					CargaPlanejadaContract.COLUNA_ID_ITEM_SERIE_RA + " from " +
					CargaPlanejadaContract.TABLE_NAME + ")";
		}
	
		if (existeItem("SemExecucaoItemSerieGera",uri.getPathSegments())) {
			sql += ItemSerieContract.COLUNA_CHAVE + " not in (select " + 
					ExecucaoItemSerieContract.COLUNA_ID_ITEM_SERIE_RA + " from " +
					ExecucaoItemSerieContract.TABLE_NAME + ")";
		}
	
		
		return sql;
	}
	
	private String sqlSelect(Uri uri) {
		String sql = ItemSerieContract.camposOrdenados();
		List<String> segmentos = uri.getPathSegments();
		// ComChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComExercicioExecucaoDe",uri.getPathSegments())) {
			sql += "," +  ExercicioContract.camposOrdenados();
		}
	
		if (existeItem("ComSerieTreinoPertencenteA",uri.getPathSegments())) {
			sql += "," +  SerieTreinoContract.camposOrdenados();
		}
	
	
	// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComCargaPlanejadaPossui",uri.getPathSegments())) {
			sql += "," +  CargaPlanejadaContract.camposOrdenados();
		}
	
		if (existeItem("ComExecucaoItemSerieGera",uri.getPathSegments())) {
			sql += "," +  ExecucaoItemSerieContract.camposOrdenados();
		}
	
		
		
		
		return sql;
	}
	private String sqlFrom(Uri uri) {
		String sql = ItemSerieContract.TABLE_NAME;
		List<String> segmentos = uri.getPathSegments();
		//if (existeItem("ComEpisodioReferenteA",uri.getPathSegments())) {
		//	sql += " " +  EpisodioUsuarioContract.innerJoinEpisodio_ReferenteA();
		//}
		
		
				// ComChaveEstrangeira
  	
		if (existeItem("ComExercicioExecucaoDe",uri.getPathSegments())) {
			sql += " " +  ItemSerieContract.outerJoinExercicio_ExecucaoDe();
		}
	
		if (existeItem("ComSerieTreinoPertencenteA",uri.getPathSegments())) {
			sql += " " +  ItemSerieContract.outerJoinSerieTreino_PertencenteA();
		}
	
	
	// SemChaveEstrangeira
  	
		if (existeItem("ComCargaPlanejadaPossui",uri.getPathSegments())) {
			sql += " " +  ItemSerieContract.outerJoinCargaPlanejada_Possui();
		}
	
		if (existeItem("ComExecucaoItemSerieGera",uri.getPathSegments())) {
			sql += " " +  ItemSerieContract.outerJoinExecucaoItemSerie_Gera();
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
	
	
	protected abstract String queryListaPorDiaComExecucao(String param);
	
	protected abstract String queryFinalizaItemSerie(String param);
	
	protected abstract String queryCarregaCompleto(String param);
	
	protected abstract String queryCarregaUltimasExecucoes(String param);
	
	protected abstract String queryAtualizaCarga(String param);
	
	
	
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
            case ITEM_SERIE:
            {
                return ItemSerieContract.getContentType();
            }
            case ITEM_SERIE_ID:
            {
            	return ItemSerieContract.getContentItemType();
            }
			case ITEM_SERIE_POR_EXERCICIO_EXECUCAODE:
            {
	            return ItemSerieContract.getContentType();
            }
		
			case ITEM_SERIE_POR_SERIE_TREINO_PERTENCENTEA:
            {
	            return ItemSerieContract.getContentType();
            }
		
		}	
		return null;
	}
	
	public Uri insert(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Uri returnUri;
		long idNovo = getMaxId(db)+1;
		values.put(ItemSerieContract.COLUNA_CHAVE, idNovo);
		long _id = db.insert(ItemSerieContract.TABLE_NAME, null, values);
		if (_id > 0) {
			//DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + ItemSerieContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
			DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + values.toString() + " (id:" + _id + ")");
			returnUri = ItemSerieContract.buildItemSerieUri(idNovo);
			values.put(ItemSerieContract.COLUNA_OPERACAO_SINC,"I");
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
			case ITEM_SERIE_DELETE_SINC: {
				String id = uri.getPathSegments().get(2);
				Cursor cursor = db.query(ItemSerieContract.TABLE_NAME,null,ItemSerieContract.COLUNA_CHAVE + " = ? ",new String[]{id},null,null,null);
				if (cursor.moveToFirst()) {
					ContentValues valores = new ContentValues();
					DatabaseUtils.cursorRowToContentValues(cursor, valores);
					linhaDelete = db.delete(ItemSerieContract.TABLE_NAME, ItemSerieContract.COLUNA_CHAVE + " = ? ", new String[]{id});
					DCLog.d(DCLog.DATABASE_CRUD,this,"delete " + ItemSerieContract.TABLE_NAME + "(id:" + id + ")");
					valores.put(ItemSerieContract.COLUNA_OPERACAO_SINC, "D");
					insereSinc(db,valores);
				}
				notificaUriRelacionadas();
				mProvider.getContext().getContentResolver().notifyChange(ItemSerieContract.buildAll(), null);
				break;
			}
			case ITEM_SERIE_DELETE_ALL_SINC: {
				linhaDelete = db.delete(ItemSerieContract.TABLE_NAME_SINC, null, null);
				break;
			}
			case ITEM_SERIE_DELETE_RECREATE: {
				linhaDelete = db.delete(ItemSerieContract.TABLE_NAME, null, null);
				break;
			}
		}
		return true;
	}
	
	
	public boolean update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		DCLog.d(DCLog.DATABASE_CRUD,this,"update2 " + values.toString() );
		linhaUpdate = db.update(ItemSerieContract.TABLE_NAME, values, selection, selectionArgs);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	public boolean update(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		String selection = ItemSerieContract.COLUNA_CHAVE + " = ? ";
		String dados[] = {values.get(ItemSerieContract.COLUNA_CHAVE).toString()};
		DCLog.d(DCLog.DATABASE_CRUD,this,"update1 " + values.toString() );
		linhaUpdate = db.update(ItemSerieContract.TABLE_NAME, values, selection, dados);
		values.put(ItemSerieContract.COLUNA_OPERACAO_SINC,"A");
		insereSinc(db,values);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	
	private void insereSinc(SQLiteDatabase db, ContentValues values) {
		db.insert(ItemSerieContract.TABLE_NAME_SINC, null, values);
		notificaUriOperacoes();
	}
	
	protected abstract void notificaOutrasUri(ContentResolver resolver);
	
	
	// Notificar todas as uris de entidades que possuem chave estrangeira dessa entidade.
	private void notificaUriRelacionadas() {
		// ComChaveEstrangeira
  	
		mProvider.getContext().getContentResolver().notifyChange(ExercicioContract.buildAllComItemSerieGera(), null);
		mProvider.getContext().getContentResolver().notifyChange(ExercicioContract.buildAllSemItemSerieGera(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(SerieTreinoContract.buildAllComItemSeriePossui(), null);
		mProvider.getContext().getContentResolver().notifyChange(SerieTreinoContract.buildAllSemItemSeriePossui(), null);
	
	}
	private void notificaUriOperacoes() {
	
		mProvider.getContext().getContentResolver().notifyChange(ItemSerieContract.buildListaPorDiaComExecucao(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(ItemSerieContract.buildFinalizaItemSerie(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(ItemSerieContract.buildCarregaCompleto(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(ItemSerieContract.buildCarregaUltimasExecucoes(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(ItemSerieContract.buildAtualizaCarga(), null);
	
	}
	private void notificaUriRaiz(){
		
	}
	
	
	
	public int bulkInsert(Uri uri, ContentValues[] values) {
    	final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        db.beginTransaction();
        int returnCount = 0;
        try {
        	for (ContentValues value : values) {
				String[] args = {(String)value.get(ItemSerieContract.COLUNA_CHAVE)};
				Cursor retCursor = query(null, sPorChaveSel, args, null);
				if (retCursor.moveToFirst()) {
						db.update(ItemSerieContract.TABLE_NAME,value,sPorChaveSel,args);
						//DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk " + ItemSerieContract.TABLE_NAME + "  " + values.toString());
						DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk id:" + value.get(ItemSerieContract.COLUNA_CHAVE) + ")" + values.toString());
				} else {
					long _id = db.insert(ItemSerieContract.TABLE_NAME, null, value);
					if (_id != -1) {
						//DCLog.d(DCLog.DATABASE_CRUD,this,"insert (bulk)" + ItemSerieContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
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
            	long _id = db.insert(ItemSerieContract.TABLE_NAME, null, value);
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
		String sql = "select max(" + ItemSerieContract.COLUNA_CHAVE + ") from " + ItemSerieContract.TABLE_NAME;
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