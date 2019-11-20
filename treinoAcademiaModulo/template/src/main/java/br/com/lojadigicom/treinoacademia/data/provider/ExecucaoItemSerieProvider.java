
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

public abstract class ExecucaoItemSerieProvider {

	private int qtdeLinhas = 0;
	
	public static final int EXECUCAO_ITEM_SERIE = 74700;
	public static final int EXECUCAO_ITEM_SERIE_ID = 74701;
	public static final int EXECUCAO_ITEM_SERIE_SINC = 74703;
	public static final int EXECUCAO_ITEM_SERIE_E_COMPLEMENTO = 74704;
	public static final int EXECUCAO_ITEM_SERIE_ID_E_COMPLEMENTO = 74705;
	//public static final int EXECUCAO_ITEM_SERIE_OPERACAO = 74702;
	
	// deletes
	public static final int EXECUCAO_ITEM_SERIE_DELETE_ALL_SINC = 74706;
	public static final int EXECUCAO_ITEM_SERIE_DELETE_RECREATE = 74707;
	public static final int EXECUCAO_ITEM_SERIE_DELETE_SINC = 74708;
	public static final int EXECUCAO_ITEM_SERIE_E_RETIRADA = 74709;
	
	private static final String sPorChaveSel = ExecucaoItemSerieContract.TABLE_NAME + "." + ExecucaoItemSerieContract.COLUNA_CHAVE + " = ? ";
	
	
	
	public static final int EXECUCAO_ITEM_SERIE_POR_ITEM_SERIE_REFERENTEA = 74720;
	public static final int COM_ITEM_SERIE = 74721;
	private static final String sPorIdItemSerieRaSelecao =
            ExecucaoItemSerieContract.TABLE_NAME + ".id_item_serie_ra = ? ";
	
	public static final int EXECUCAO_ITEM_SERIE_POR_DIA_TREINO_EM = 74722;
	public static final int COM_DIA_TREINO = 74723;
	private static final String sPorIdDiaTreinoESelecao =
            ExecucaoItemSerieContract.TABLE_NAME + ".id_dia_treino_e = ? ";
	
	public static final int EXECUCAO_ITEM_SERIE_POR_EXERCICIO_REFERENTEA = 74724;
	public static final int COM_EXERCICIO = 74725;
	private static final String sPorIdExercicioRaSelecao =
            ExecucaoItemSerieContract.TABLE_NAME + ".id_exercicio_ra = ? ";
	

 	public static final int OBTEMPORDIAITEMSERIE = 74726;
 	public static final int ULTIMASEXECUCOESUSUARIOEXERCICIO = 74727;
 	public static final int ULTIMASEXECUCOESITEMSERIE = 74728;
 	public static final int CARREGACOMPLETOPORDIA = 74729;
 	public static final int PRIMEIRAPORDIA = 74730;
 	public static final int ULTIMAPORDIA = 74731;
 	public static final int PRIMEIRAPORSERIE = 74732;

	private ContentProvider mProvider = null;


	public void setContentProvider(ContentProvider valor) {
		mProvider = valor;
	}

	protected static final SQLiteQueryBuilder sQueryBuilder;
	static {
		sQueryBuilder = new SQLiteQueryBuilder();
		String tabelas = ExecucaoItemSerieContract.TABLE_NAME;
		
		//tabelas += " inner join " + ItemSerieContract.TABLE_NAME + " ";
		//tabelas += " on " + ItemSerieContract.TABLE_NAME + "." + ItemSerieContract.COLUNA_CHAVE + " = " + ExecucaoItemSerieContract.TABLE_NAME + "." + ExecucaoItemSerieContract.COLUNA_ID_ITEM_SERIE_RA; 
		
		//tabelas += " inner join " + DiaTreinoContract.TABLE_NAME + " ";
		//tabelas += " on " + DiaTreinoContract.TABLE_NAME + "." + DiaTreinoContract.COLUNA_CHAVE + " = " + ExecucaoItemSerieContract.TABLE_NAME + "." + ExecucaoItemSerieContract.COLUNA_ID_DIA_TREINO_E; 
		
		//tabelas += " inner join " + ExercicioContract.TABLE_NAME + " ";
		//tabelas += " on " + ExercicioContract.TABLE_NAME + "." + ExercicioContract.COLUNA_CHAVE + " = " + ExecucaoItemSerieContract.TABLE_NAME + "." + ExecucaoItemSerieContract.COLUNA_ID_EXERCICIO_RA; 
		
		sQueryBuilder.setTables(tabelas);
	}
	private static final SQLiteQueryBuilder sQueryBuilderSinc;
	static {
		sQueryBuilderSinc = new SQLiteQueryBuilder();
		String tabelas = ExecucaoItemSerieContract.TABLE_NAME_SINC;
		sQueryBuilderSinc.setTables(tabelas);
	}
	
	
	protected AplicacaoDbHelper mOpenHelper = null;
	
	public int getLinhas() {
		return qtdeLinhas;
	}
	
	public static void buildUriMatcher(UriMatcher matcher) {
		matcher.addURI(ExecucaoItemSerieContract.getContentAuthority(), ExecucaoItemSerieContract.PATH, EXECUCAO_ITEM_SERIE);
		matcher.addURI(ExecucaoItemSerieContract.getContentAuthority(), ExecucaoItemSerieContract.PATH + "/Sinc" , EXECUCAO_ITEM_SERIE_SINC);
		matcher.addURI(ExecucaoItemSerieContract.getContentAuthority(), ExecucaoItemSerieContract.PATH + "/#"    , EXECUCAO_ITEM_SERIE_ID);
	
		matcher.addURI(ExecucaoItemSerieContract.getContentAuthority(), ExecucaoItemSerieContract.PATH + "/#/" + ExecucaoItemSerieContract.COM_COMPLEMENTO + "/*" , EXECUCAO_ITEM_SERIE_ID_E_COMPLEMENTO);
		matcher.addURI(ExecucaoItemSerieContract.getContentAuthority(), ExecucaoItemSerieContract.PATH + "/" + ExecucaoItemSerieContract.COM_COMPLEMENTO + "/*" , EXECUCAO_ITEM_SERIE_E_COMPLEMENTO);
		matcher.addURI(ExecucaoItemSerieContract.getContentAuthority(), ExecucaoItemSerieContract.PATH + "/" + ExecucaoItemSerieContract.COM_RETIRADA + "/*" , EXECUCAO_ITEM_SERIE_E_RETIRADA);
		
		
		//matcher.addURI(AplicacaoContract.CONTENT_AUTHORITY, ExecucaoItemSerieContract.PATH + "/operacao/*" , EXECUCAO_ITEM_SERIE_OPERACAO);
		
		matcher.addURI(ExecucaoItemSerieContract.getContentAuthority(), ExecucaoItemSerieContract.PATH + "/#/" + ItemSerieContract.PATH, EXECUCAO_ITEM_SERIE_POR_ITEM_SERIE_REFERENTEA);
		matcher.addURI(ExecucaoItemSerieContract.getContentAuthority(), ExecucaoItemSerieContract.PATH + "/ComItemSerieReferenteA/" , COM_ITEM_SERIE);
		
		matcher.addURI(ExecucaoItemSerieContract.getContentAuthority(), ExecucaoItemSerieContract.PATH + "/#/" + DiaTreinoContract.PATH, EXECUCAO_ITEM_SERIE_POR_DIA_TREINO_EM);
		matcher.addURI(ExecucaoItemSerieContract.getContentAuthority(), ExecucaoItemSerieContract.PATH + "/ComDiaTreinoEm/" , COM_DIA_TREINO);
		
		matcher.addURI(ExecucaoItemSerieContract.getContentAuthority(), ExecucaoItemSerieContract.PATH + "/#/" + ExercicioContract.PATH, EXECUCAO_ITEM_SERIE_POR_EXERCICIO_REFERENTEA);
		matcher.addURI(ExecucaoItemSerieContract.getContentAuthority(), ExecucaoItemSerieContract.PATH + "/ComExercicioReferenteA/" , COM_EXERCICIO);
		
		
		
		matcher.addURI(ExecucaoItemSerieContract.getContentAuthority(), ExecucaoItemSerieContract.PATH + "/ObtemPorDiaItemSerie/*", OBTEMPORDIAITEMSERIE);
		
		matcher.addURI(ExecucaoItemSerieContract.getContentAuthority(), ExecucaoItemSerieContract.PATH + "/UltimasExecucoesUsuarioExercicio/*", ULTIMASEXECUCOESUSUARIOEXERCICIO);
		
		matcher.addURI(ExecucaoItemSerieContract.getContentAuthority(), ExecucaoItemSerieContract.PATH + "/UltimasExecucoesItemSerie/*", ULTIMASEXECUCOESITEMSERIE);
		
		matcher.addURI(ExecucaoItemSerieContract.getContentAuthority(), ExecucaoItemSerieContract.PATH + "/CarregaCompletoPorDia/*", CARREGACOMPLETOPORDIA);
		
		matcher.addURI(ExecucaoItemSerieContract.getContentAuthority(), ExecucaoItemSerieContract.PATH + "/PrimeiraPorDia/*", PRIMEIRAPORDIA);
		
		matcher.addURI(ExecucaoItemSerieContract.getContentAuthority(), ExecucaoItemSerieContract.PATH + "/UltimaPorDia/*", ULTIMAPORDIA);
		
		matcher.addURI(ExecucaoItemSerieContract.getContentAuthority(), ExecucaoItemSerieContract.PATH + "/PrimeiraPorSerie/*", PRIMEIRAPORSERIE);
		
		
		// Deletes
		matcher.addURI(ExecucaoItemSerieContract.getContentAuthority(), ExecucaoItemSerieContract.PATH + "/DeleteAllSinc" , 	EXECUCAO_ITEM_SERIE_DELETE_ALL_SINC);
		matcher.addURI(ExecucaoItemSerieContract.getContentAuthority(), ExecucaoItemSerieContract.PATH + "/DeleteAllRecreate" , EXECUCAO_ITEM_SERIE_DELETE_RECREATE);
		matcher.addURI(ExecucaoItemSerieContract.getContentAuthority(), ExecucaoItemSerieContract.PATH + "/DeleteSinc/#" , 		EXECUCAO_ITEM_SERIE_DELETE_SINC);
	}
	
	
	
	
	public void setAplicacaoDbHelper(AplicacaoDbHelper db) {
		mOpenHelper = db;
	}
	
	public Cursor query(UriMatcher sUriMatcher, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Query Uri:" + uri.toString());
		Cursor retCursor = null;
		switch (sUriMatcher.match(uri)) {
            case EXECUCAO_ITEM_SERIE:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: EXECUCAO_ITEM_SERIE");
                retCursor = query(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case EXECUCAO_ITEM_SERIE_SINC:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: EXECUCAO_ITEM_SERIE_SINC");
                retCursor = querySinc(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case EXECUCAO_ITEM_SERIE_ID:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: EXECUCAO_ITEM_SERIE_ID");
            	String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorChaveSel, args, sortOrder);
                break;
            }
            case EXECUCAO_ITEM_SERIE_ID_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: EXECUCAO_ITEM_SERIE_ID_E_COMPLEMENTO");
				String id = uri.getPathSegments().get(1);	
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri) +
						" where " + ExecucaoItemSerieContract.COLUNA_CHAVE + " = " + id;
				retCursor = queryRaw(sql);
				break;
			}
			case EXECUCAO_ITEM_SERIE_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: EXECUCAO_ITEM_SERIE_E_COMPLEMENTO");
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case EXECUCAO_ITEM_SERIE_E_RETIRADA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: EXECUCAO_ITEM_SERIE_E_RETIRADA");
				String sql = "select " +  ExecucaoItemSerieContract.camposOrdenados() +
						" from " + ExecucaoItemSerieContract.TABLE_NAME +
						sqlWhere(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case EXECUCAO_ITEM_SERIE_POR_ITEM_SERIE_REFERENTEA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: EXECUCAO_ITEM_SERIE_POR_ITEM_SERIE_REFERENTEA");
	            String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorIdItemSerieRaSelecao, args, sortOrder);
                break;
            }
            case COM_ITEM_SERIE:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: COM_ITEM_SERIE");
            	String sql = "select " + ExecucaoItemSerieContract.camposOrdenados() + " , " +
						ItemSerieContract.camposOrdenados() +
						" from " + ExecucaoItemSerieContract.TABLE_NAME +
						ExecucaoItemSerieContract.innerJoinItemSerie_ReferenteA() +
						getOrderBy();
                retCursor = queryRaw(sql);
				break;
            }
		
			case EXECUCAO_ITEM_SERIE_POR_DIA_TREINO_EM:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: EXECUCAO_ITEM_SERIE_POR_DIA_TREINO_EM");
	            String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorIdDiaTreinoESelecao, args, sortOrder);
                break;
            }
            case COM_DIA_TREINO:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: COM_DIA_TREINO");
            	String sql = "select " + ExecucaoItemSerieContract.camposOrdenados() + " , " +
						DiaTreinoContract.camposOrdenados() +
						" from " + ExecucaoItemSerieContract.TABLE_NAME +
						ExecucaoItemSerieContract.innerJoinDiaTreino_Em() +
						getOrderBy();
                retCursor = queryRaw(sql);
				break;
            }
		
			case EXECUCAO_ITEM_SERIE_POR_EXERCICIO_REFERENTEA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: EXECUCAO_ITEM_SERIE_POR_EXERCICIO_REFERENTEA");
	            String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorIdExercicioRaSelecao, args, sortOrder);
                break;
            }
            case COM_EXERCICIO:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: COM_EXERCICIO");
            	String sql = "select " + ExecucaoItemSerieContract.camposOrdenados() + " , " +
						ExercicioContract.camposOrdenados() +
						" from " + ExecucaoItemSerieContract.TABLE_NAME +
						ExecucaoItemSerieContract.innerJoinExercicio_ReferenteA() +
						getOrderBy();
                retCursor = queryRaw(sql);
				break;
            }
		
		
	 		case OBTEMPORDIAITEMSERIE:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: OBTEMPORDIAITEMSERIE");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryObtemPorDiaItemSerie()");
				//retCursor = queryObtemPorDiaItemSerie(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryObtemPorDiaItemSerie(param) + (sortOrder!=null?" order by " + sortOrder:"");
				DCLog.d(DCLog.TRACE_LISTA,this,"SQL: " + sql);
				retCursor = this.queryRaw(sql);
				DCLog.d(DCLog.TRACE_LISTA,this,"Cursor: " + retCursor.getCount() + " linhas");
				break;
			}
		
	 		case ULTIMASEXECUCOESUSUARIOEXERCICIO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: ULTIMASEXECUCOESUSUARIOEXERCICIO");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryUltimasExecucoesUsuarioExercicio()");
				//retCursor = queryUltimasExecucoesUsuarioExercicio(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryUltimasExecucoesUsuarioExercicio(param) + (sortOrder!=null?" order by " + sortOrder:"");
				DCLog.d(DCLog.TRACE_LISTA,this,"SQL: " + sql);
				retCursor = this.queryRaw(sql);
				DCLog.d(DCLog.TRACE_LISTA,this,"Cursor: " + retCursor.getCount() + " linhas");
				break;
			}
		
	 		case ULTIMASEXECUCOESITEMSERIE:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: ULTIMASEXECUCOESITEMSERIE");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryUltimasExecucoesItemSerie()");
				//retCursor = queryUltimasExecucoesItemSerie(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryUltimasExecucoesItemSerie(param) + (sortOrder!=null?" order by " + sortOrder:"");
				DCLog.d(DCLog.TRACE_LISTA,this,"SQL: " + sql);
				retCursor = this.queryRaw(sql);
				DCLog.d(DCLog.TRACE_LISTA,this,"Cursor: " + retCursor.getCount() + " linhas");
				break;
			}
		
	 		case CARREGACOMPLETOPORDIA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CARREGACOMPLETOPORDIA");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryCarregaCompletoPorDia()");
				//retCursor = queryCarregaCompletoPorDia(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryCarregaCompletoPorDia(param) + (sortOrder!=null?" order by " + sortOrder:"");
				DCLog.d(DCLog.TRACE_LISTA,this,"SQL: " + sql);
				retCursor = this.queryRaw(sql);
				DCLog.d(DCLog.TRACE_LISTA,this,"Cursor: " + retCursor.getCount() + " linhas");
				break;
			}
		
	 		case PRIMEIRAPORDIA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRIMEIRAPORDIA");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryPrimeiraPorDia()");
				//retCursor = queryPrimeiraPorDia(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryPrimeiraPorDia(param) + (sortOrder!=null?" order by " + sortOrder:"");
				DCLog.d(DCLog.TRACE_LISTA,this,"SQL: " + sql);
				retCursor = this.queryRaw(sql);
				DCLog.d(DCLog.TRACE_LISTA,this,"Cursor: " + retCursor.getCount() + " linhas");
				break;
			}
		
	 		case ULTIMAPORDIA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: ULTIMAPORDIA");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryUltimaPorDia()");
				//retCursor = queryUltimaPorDia(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryUltimaPorDia(param) + (sortOrder!=null?" order by " + sortOrder:"");
				DCLog.d(DCLog.TRACE_LISTA,this,"SQL: " + sql);
				retCursor = this.queryRaw(sql);
				DCLog.d(DCLog.TRACE_LISTA,this,"Cursor: " + retCursor.getCount() + " linhas");
				break;
			}
		
	 		case PRIMEIRAPORSERIE:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRIMEIRAPORSERIE");
				DCLog.d(DCLog.TRACE_LISTA,this,"chamar queryPrimeiraPorSerie()");
				//retCursor = queryPrimeiraPorSerie(uri,projection,sortOrder);
				String param = uri.getQuery();
				String sql = queryPrimeiraPorSerie(param) + (sortOrder!=null?" order by " + sortOrder:"");
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
		String sql = ExecucaoItemSerieContract.camposOrdenados();
		List<String> segmentos = uri.getPathSegments();
		// ComChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComItemSerieReferenteA",uri.getPathSegments())) {
			sql += "," +  ItemSerieContract.camposOrdenados();
		}
	
		if (existeItem("ComDiaTreinoEm",uri.getPathSegments())) {
			sql += "," +  DiaTreinoContract.camposOrdenados();
		}
	
		if (existeItem("ComExercicioReferenteA",uri.getPathSegments())) {
			sql += "," +  ExercicioContract.camposOrdenados();
		}
	
	
	// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		
		
		
		return sql;
	}
	private String sqlFrom(Uri uri) {
		String sql = ExecucaoItemSerieContract.TABLE_NAME;
		List<String> segmentos = uri.getPathSegments();
		//if (existeItem("ComEpisodioReferenteA",uri.getPathSegments())) {
		//	sql += " " +  EpisodioUsuarioContract.innerJoinEpisodio_ReferenteA();
		//}
		
		
				// ComChaveEstrangeira
  	
		if (existeItem("ComItemSerieReferenteA",uri.getPathSegments())) {
			sql += " " +  ExecucaoItemSerieContract.outerJoinItemSerie_ReferenteA();
		}
	
		if (existeItem("ComDiaTreinoEm",uri.getPathSegments())) {
			sql += " " +  ExecucaoItemSerieContract.outerJoinDiaTreino_Em();
		}
	
		if (existeItem("ComExercicioReferenteA",uri.getPathSegments())) {
			sql += " " +  ExecucaoItemSerieContract.outerJoinExercicio_ReferenteA();
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
	
	
	protected abstract String queryObtemPorDiaItemSerie(String param);
	
	protected abstract String queryUltimasExecucoesUsuarioExercicio(String param);
	
	protected abstract String queryUltimasExecucoesItemSerie(String param);
	
	protected abstract String queryCarregaCompletoPorDia(String param);
	
	protected abstract String queryPrimeiraPorDia(String param);
	
	protected abstract String queryUltimaPorDia(String param);
	
	protected abstract String queryPrimeiraPorSerie(String param);
	
	
	
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
            case EXECUCAO_ITEM_SERIE:
            {
                return ExecucaoItemSerieContract.getContentType();
            }
            case EXECUCAO_ITEM_SERIE_ID:
            {
            	return ExecucaoItemSerieContract.getContentItemType();
            }
			case EXECUCAO_ITEM_SERIE_POR_ITEM_SERIE_REFERENTEA:
            {
	            return ExecucaoItemSerieContract.getContentType();
            }
		
			case EXECUCAO_ITEM_SERIE_POR_DIA_TREINO_EM:
            {
	            return ExecucaoItemSerieContract.getContentType();
            }
		
			case EXECUCAO_ITEM_SERIE_POR_EXERCICIO_REFERENTEA:
            {
	            return ExecucaoItemSerieContract.getContentType();
            }
		
		}	
		return null;
	}
	
	public Uri insert(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Uri returnUri;
		long idNovo = getMaxId(db)+1;
		values.put(ExecucaoItemSerieContract.COLUNA_CHAVE, idNovo);
		long _id = db.insert(ExecucaoItemSerieContract.TABLE_NAME, null, values);
		if (_id > 0) {
			//DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + ExecucaoItemSerieContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
			DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + values.toString() + " (id:" + _id + ")");
			returnUri = ExecucaoItemSerieContract.buildExecucaoItemSerieUri(idNovo);
			values.put(ExecucaoItemSerieContract.COLUNA_OPERACAO_SINC,"I");
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
			case EXECUCAO_ITEM_SERIE_DELETE_SINC: {
				String id = uri.getPathSegments().get(2);
				Cursor cursor = db.query(ExecucaoItemSerieContract.TABLE_NAME,null,ExecucaoItemSerieContract.COLUNA_CHAVE + " = ? ",new String[]{id},null,null,null);
				if (cursor.moveToFirst()) {
					ContentValues valores = new ContentValues();
					DatabaseUtils.cursorRowToContentValues(cursor, valores);
					linhaDelete = db.delete(ExecucaoItemSerieContract.TABLE_NAME, ExecucaoItemSerieContract.COLUNA_CHAVE + " = ? ", new String[]{id});
					DCLog.d(DCLog.DATABASE_CRUD,this,"delete " + ExecucaoItemSerieContract.TABLE_NAME + "(id:" + id + ")");
					valores.put(ExecucaoItemSerieContract.COLUNA_OPERACAO_SINC, "D");
					insereSinc(db,valores);
				}
				notificaUriRelacionadas();
				mProvider.getContext().getContentResolver().notifyChange(ExecucaoItemSerieContract.buildAll(), null);
				break;
			}
			case EXECUCAO_ITEM_SERIE_DELETE_ALL_SINC: {
				linhaDelete = db.delete(ExecucaoItemSerieContract.TABLE_NAME_SINC, null, null);
				break;
			}
			case EXECUCAO_ITEM_SERIE_DELETE_RECREATE: {
				linhaDelete = db.delete(ExecucaoItemSerieContract.TABLE_NAME, null, null);
				break;
			}
		}
		return true;
	}
	
	
	public boolean update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		DCLog.d(DCLog.DATABASE_CRUD,this,"update2 " + values.toString() );
		linhaUpdate = db.update(ExecucaoItemSerieContract.TABLE_NAME, values, selection, selectionArgs);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	public boolean update(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		String selection = ExecucaoItemSerieContract.COLUNA_CHAVE + " = ? ";
		String dados[] = {values.get(ExecucaoItemSerieContract.COLUNA_CHAVE).toString()};
		DCLog.d(DCLog.DATABASE_CRUD,this,"update1 " + values.toString() );
		linhaUpdate = db.update(ExecucaoItemSerieContract.TABLE_NAME, values, selection, dados);
		values.put(ExecucaoItemSerieContract.COLUNA_OPERACAO_SINC,"A");
		insereSinc(db,values);
		notificaOutrasUri(mProvider.getContext().getContentResolver());
		return true;
	}
	
	private void insereSinc(SQLiteDatabase db, ContentValues values) {
		db.insert(ExecucaoItemSerieContract.TABLE_NAME_SINC, null, values);
		notificaUriOperacoes();
	}
	
	protected abstract void notificaOutrasUri(ContentResolver resolver);
	
	
	// Notificar todas as uris de entidades que possuem chave estrangeira dessa entidade.
	private void notificaUriRelacionadas() {
		// ComChaveEstrangeira
  	
		mProvider.getContext().getContentResolver().notifyChange(ItemSerieContract.buildAllComExecucaoItemSerieGera(), null);
		mProvider.getContext().getContentResolver().notifyChange(ItemSerieContract.buildAllSemExecucaoItemSerieGera(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(DiaTreinoContract.buildAllComExecucaoItemSerieFoiRealizado(), null);
		mProvider.getContext().getContentResolver().notifyChange(DiaTreinoContract.buildAllSemExecucaoItemSerieFoiRealizado(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(ExercicioContract.buildAllComExecucaoItemSerieExecutado(), null);
		mProvider.getContext().getContentResolver().notifyChange(ExercicioContract.buildAllSemExecucaoItemSerieExecutado(), null);
	
	}
	private void notificaUriOperacoes() {
	
		mProvider.getContext().getContentResolver().notifyChange(ExecucaoItemSerieContract.buildObtemPorDiaItemSerie(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(ExecucaoItemSerieContract.buildUltimasExecucoesUsuarioExercicio(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(ExecucaoItemSerieContract.buildUltimasExecucoesItemSerie(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(ExecucaoItemSerieContract.buildCarregaCompletoPorDia(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(ExecucaoItemSerieContract.buildPrimeiraPorDia(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(ExecucaoItemSerieContract.buildUltimaPorDia(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(ExecucaoItemSerieContract.buildPrimeiraPorSerie(), null);
	
	}
	private void notificaUriRaiz(){
		
	}
	
	
	
	public int bulkInsert(Uri uri, ContentValues[] values) {
    	final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        db.beginTransaction();
        int returnCount = 0;
        try {
        	for (ContentValues value : values) {
				String[] args = {(String)value.get(ExecucaoItemSerieContract.COLUNA_CHAVE)};
				Cursor retCursor = query(null, sPorChaveSel, args, null);
				if (retCursor.moveToFirst()) {
						db.update(ExecucaoItemSerieContract.TABLE_NAME,value,sPorChaveSel,args);
						//DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk " + ExecucaoItemSerieContract.TABLE_NAME + "  " + values.toString());
						DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk id:" + value.get(ExecucaoItemSerieContract.COLUNA_CHAVE) + ")" + values.toString());
				} else {
					long _id = db.insert(ExecucaoItemSerieContract.TABLE_NAME, null, value);
					if (_id != -1) {
						//DCLog.d(DCLog.DATABASE_CRUD,this,"insert (bulk)" + ExecucaoItemSerieContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
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
            	long _id = db.insert(ExecucaoItemSerieContract.TABLE_NAME, null, value);
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
		String sql = "select max(" + ExecucaoItemSerieContract.COLUNA_CHAVE + ") from " + ExecucaoItemSerieContract.TABLE_NAME;
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