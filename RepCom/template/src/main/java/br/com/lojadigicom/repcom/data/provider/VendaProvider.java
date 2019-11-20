
package  br.com.lojadigicom.repcom.data.provider;


import br.com.lojadigicom.repcom.data.contract.*;
import br.com.lojadigicom.repcom.data.helper.AplicacaoDbHelper;
import android.content.ContentValues;
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

public abstract class VendaProvider {

	private int qtdeLinhas = 0;
	
	public static final int VENDA = 40000;
	public static final int VENDA_ID = 40001;
	public static final int VENDA_SINC = 40003;
	public static final int VENDA_E_COMPLEMENTO = 40004;
	public static final int VENDA_ID_E_COMPLEMENTO = 40005;
	//public static final int VENDA_OPERACAO = 40002;
	
	// deletes
	public static final int VENDA_DELETE_ALL_SINC = 40006;
	public static final int VENDA_DELETE_RECREATE = 40007;
	public static final int VENDA_DELETE_SINC = 40008;
	public static final int VENDA_E_RETIRADA = 40009;
	
	private static final String sPorChaveSel = VendaContract.TABLE_NAME + "." + VendaContract.COLUNA_CHAVE + " = ? ";
	
	
	
	public static final int VENDA_POR_CLIENTE_FEITAPARA = 40020;
	public static final int COM_CLIENTE = 40021;
	private static final String sPorIdClienteFpSelecao =
            VendaContract.TABLE_NAME + ".id_cliente_fp = ? ";
	

 	public static final int ATUALIZATOTAL = 40022;
 	public static final int CRIAPARCELAMENTO = 40023;

	private ContentProvider mProvider = null;


	public void setContentProvider(ContentProvider valor) {
		mProvider = valor;
	}

	protected static final SQLiteQueryBuilder sQueryBuilder;
	static {
		sQueryBuilder = new SQLiteQueryBuilder();
		String tabelas = VendaContract.TABLE_NAME;
		
		//tabelas += " inner join " + ClienteContract.TABLE_NAME + " ";
		//tabelas += " on " + ClienteContract.TABLE_NAME + "." + ClienteContract.COLUNA_CHAVE + " = " + VendaContract.TABLE_NAME + "." + VendaContract.COLUNA_ID_CLIENTE_FP; 
		
		sQueryBuilder.setTables(tabelas);
	}
	private static final SQLiteQueryBuilder sQueryBuilderSinc;
	static {
		sQueryBuilderSinc = new SQLiteQueryBuilder();
		String tabelas = VendaContract.TABLE_NAME_SINC;
		sQueryBuilderSinc.setTables(tabelas);
	}
	
	
	protected AplicacaoDbHelper mOpenHelper = null;
	
	public int getLinhas() {
		return qtdeLinhas;
	}
	
	public static void buildUriMatcher(UriMatcher matcher) {
		matcher.addURI(VendaContract.getContentAuthority(), VendaContract.PATH, VENDA);
		matcher.addURI(VendaContract.getContentAuthority(), VendaContract.PATH + "/Sinc" , VENDA_SINC);
		matcher.addURI(VendaContract.getContentAuthority(), VendaContract.PATH + "/#"    , VENDA_ID);
	
		matcher.addURI(VendaContract.getContentAuthority(), VendaContract.PATH + "/#/" + VendaContract.COM_COMPLEMENTO + "/*" , VENDA_ID_E_COMPLEMENTO);
		matcher.addURI(VendaContract.getContentAuthority(), VendaContract.PATH + "/" + VendaContract.COM_COMPLEMENTO + "/*" , VENDA_E_COMPLEMENTO);
		matcher.addURI(VendaContract.getContentAuthority(), VendaContract.PATH + "/" + VendaContract.COM_RETIRADA + "/*" , VENDA_E_RETIRADA);
		
		
		//matcher.addURI(AplicacaoContract.CONTENT_AUTHORITY, VendaContract.PATH + "/operacao/*" , VENDA_OPERACAO);
		
		matcher.addURI(VendaContract.getContentAuthority(), VendaContract.PATH + "/#/" + ClienteContract.PATH, VENDA_POR_CLIENTE_FEITAPARA);
		matcher.addURI(VendaContract.getContentAuthority(), VendaContract.PATH + "/ComClienteFeitaPara/" , COM_CLIENTE);
		
		
		
		matcher.addURI(VendaContract.getContentAuthority(), VendaContract.PATH + "/AtualizaTotal/*", ATUALIZATOTAL);
		
		matcher.addURI(VendaContract.getContentAuthority(), VendaContract.PATH + "/CriaParcelamento/*", CRIAPARCELAMENTO);
		
		
		// Deletes
		matcher.addURI(VendaContract.getContentAuthority(), VendaContract.PATH + "/DeleteAllSinc" , 	VENDA_DELETE_ALL_SINC);
		matcher.addURI(VendaContract.getContentAuthority(), VendaContract.PATH + "/DeleteAllRecreate" , VENDA_DELETE_RECREATE);
		matcher.addURI(VendaContract.getContentAuthority(), VendaContract.PATH + "/DeleteSinc/#" , 		VENDA_DELETE_SINC);
	}
	
	
	
	
	public void setAplicacaoDbHelper(AplicacaoDbHelper db) {
		mOpenHelper = db;
	}
	
	public Cursor query(UriMatcher sUriMatcher, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Query Uri:" + uri.toString());
		Cursor retCursor = null;
		switch (sUriMatcher.match(uri)) {
            case VENDA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: VENDA");
                retCursor = query(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case VENDA_SINC:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: VENDA_SINC");
                retCursor = querySinc(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case VENDA_ID:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: VENDA_ID");
            	String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorChaveSel, args, sortOrder);
                break;
            }
            case VENDA_ID_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: VENDA_ID_E_COMPLEMENTO");
				String id = uri.getPathSegments().get(1);	
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri) +
						" where " + VendaContract.COLUNA_CHAVE + " = " + id;
				retCursor = queryRaw(sql);
				break;
			}
			case VENDA_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: VENDA_E_COMPLEMENTO");
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case VENDA_E_RETIRADA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: VENDA_E_RETIRADA");
				String sql = "select " +  VendaContract.camposOrdenados() +
						" from " + VendaContract.TABLE_NAME +
						sqlWhere(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case VENDA_POR_CLIENTE_FEITAPARA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: VENDA_POR_CLIENTE_FEITAPARA");
	            String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorIdClienteFpSelecao, args, sortOrder);
                break;
            }
            case COM_CLIENTE:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: COM_CLIENTE");
            	String sql = "select " + VendaContract.camposOrdenados() + " , " +
						ClienteContract.camposOrdenados() +
						" from " + VendaContract.TABLE_NAME +
						VendaContract.innerJoinCliente_FeitaPara() +
						getOrderBy();
                retCursor = queryRaw(sql);
				break;
            }
		
		
	 		case ATUALIZATOTAL:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: ATUALIZATOTAL");
				retCursor = queryAtualizaTotal(uri,projection,sortOrder);
				break;
			}
		
	 		case CRIAPARCELAMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: CRIAPARCELAMENTO");
				retCursor = queryCriaParcelamento(uri,projection,sortOrder);
				break;
			}
		
		}	
        return retCursor;
	}
	
	private String sqlWhere(Uri uri) {
		String sql = " where ";
		// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("SemProdutoVendaAssociada",uri.getPathSegments())) {
			sql += VendaContract.COLUNA_CHAVE + " not in (select " + 
					ProdutoVendaContract.COLUNA_ID_VENDA_A + " from " +
					ProdutoVendaContract.TABLE_NAME + ")";
		}
	
		if (existeItem("SemParcelaVendaPossui",uri.getPathSegments())) {
			sql += VendaContract.COLUNA_CHAVE + " not in (select " + 
					ParcelaVendaContract.COLUNA_ID_VENDA_PA + " from " +
					ParcelaVendaContract.TABLE_NAME + ")";
		}
	
		
		return sql;
	}
	
	private String sqlSelect(Uri uri) {
		String sql = VendaContract.camposOrdenados();
		List<String> segmentos = uri.getPathSegments();
		// ComChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComClienteFeitaPara",uri.getPathSegments())) {
			sql += "," +  ClienteContract.camposOrdenados();
		}
	
	
	// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComProdutoVendaAssociada",uri.getPathSegments())) {
			sql += "," +  ProdutoVendaContract.camposOrdenados();
		}
	
		if (existeItem("ComParcelaVendaPossui",uri.getPathSegments())) {
			sql += "," +  ParcelaVendaContract.camposOrdenados();
		}
	
		
		
		
		return sql;
	}
	private String sqlFrom(Uri uri) {
		String sql = VendaContract.TABLE_NAME;
		List<String> segmentos = uri.getPathSegments();
		//if (existeItem("ComEpisodioReferenteA",uri.getPathSegments())) {
		//	sql += " " +  EpisodioUsuarioContract.innerJoinEpisodio_ReferenteA();
		//}
		
		
				// ComChaveEstrangeira
  	
		if (existeItem("ComClienteFeitaPara",uri.getPathSegments())) {
			sql += " " +  VendaContract.outerJoinCliente_FeitaPara();
		}
	
	
	// SemChaveEstrangeira
  	
		if (existeItem("ComProdutoVendaAssociada",uri.getPathSegments())) {
			sql += " " +  VendaContract.outerJoinProdutoVenda_Associada();
		}
	
		if (existeItem("ComParcelaVendaPossui",uri.getPathSegments())) {
			sql += " " +  VendaContract.outerJoinParcelaVenda_Possui();
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
	
	
	protected abstract Cursor queryAtualizaTotal(Uri uri, String[] projection, String sortOrder);
	
	protected abstract Cursor queryCriaParcelamento(Uri uri, String[] projection, String sortOrder);
	
	
	
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
            case VENDA:
            {
                return VendaContract.getContentType();
            }
            case VENDA_ID:
            {
            	return VendaContract.getContentItemType();
            }
			case VENDA_POR_CLIENTE_FEITAPARA:
            {
	            return VendaContract.getContentType();
            }
		
		}	
		return null;
	}
	
	public Uri insert(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Uri returnUri;
		long idNovo = getMaxId(db)+1;
		values.put(VendaContract.COLUNA_CHAVE, idNovo);
		long _id = db.insert(VendaContract.TABLE_NAME, null, values);
		if (_id > 0) {
			//DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + VendaContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
			DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + values.toString() + " (id:" + _id + ")");
			returnUri = VendaContract.buildVendaUri(idNovo);
			values.put(VendaContract.COLUNA_OPERACAO_SINC,"I");
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
			case VENDA_DELETE_SINC: {
				String id = uri.getPathSegments().get(2);
				Cursor cursor = db.query(VendaContract.TABLE_NAME,null,VendaContract.COLUNA_CHAVE + " = ? ",new String[]{id},null,null,null);
				if (cursor.moveToFirst()) {
					ContentValues valores = new ContentValues();
					DatabaseUtils.cursorRowToContentValues(cursor, valores);
					linhaDelete = db.delete(VendaContract.TABLE_NAME, VendaContract.COLUNA_CHAVE + " = ? ", new String[]{id});
					DCLog.d(DCLog.DATABASE_CRUD,this,"delete " + VendaContract.TABLE_NAME + "(id:" + id + ")");
					valores.put(VendaContract.COLUNA_OPERACAO_SINC, "D");
					insereSinc(db,valores);
				}
				notificaUriRelacionadas();
				mProvider.getContext().getContentResolver().notifyChange(VendaContract.buildAll(), null);
				break;
			}
			case VENDA_DELETE_ALL_SINC: {
				linhaDelete = db.delete(VendaContract.TABLE_NAME_SINC, null, null);
				break;
			}
			case VENDA_DELETE_RECREATE: {
				linhaDelete = db.delete(VendaContract.TABLE_NAME, null, null);
				break;
			}
		}
		return true;
	}
	
	
	public boolean update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		linhaUpdate = db.update(VendaContract.TABLE_NAME, values, selection, selectionArgs);
		return true;
	}
	public boolean update(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		String selection = VendaContract.COLUNA_CHAVE + " = ? ";
		String dados[] = {values.get(VendaContract.COLUNA_CHAVE).toString()};
		linhaUpdate = db.update(VendaContract.TABLE_NAME, values, selection, dados);
		values.put(VendaContract.COLUNA_OPERACAO_SINC,"A");
		insereSinc(db,values);
		return true;
	}
	
	private void insereSinc(SQLiteDatabase db, ContentValues values) {
		db.insert(VendaContract.TABLE_NAME_SINC, null, values);
	}
	
	// Notificar todas as uris de entidades que possuem chave estrangeira dessa entidade.
	private void notificaUriRelacionadas() {
		// ComChaveEstrangeira
  	
		mProvider.getContext().getContentResolver().notifyChange(ClienteContract.buildAllComVendaComprou(), null);
		mProvider.getContext().getContentResolver().notifyChange(ClienteContract.buildAllSemVendaComprou(), null);
	
	}
	private void notificaUriOperacoes() {
	
		mProvider.getContext().getContentResolver().notifyChange(VendaContract.buildAtualizaTotal(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(VendaContract.buildCriaParcelamento(), null);
	
	}
	private void notificaUriRaiz(){
		
	}
	
	
	
	public int bulkInsert(Uri uri, ContentValues[] values) {
    	final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        db.beginTransaction();
        int returnCount = 0;
        try {
        	for (ContentValues value : values) {
				String[] args = {(String)value.get(VendaContract.COLUNA_CHAVE)};
				Cursor retCursor = query(null, sPorChaveSel, args, null);
				if (retCursor.moveToFirst()) {
						db.update(VendaContract.TABLE_NAME,value,sPorChaveSel,args);
						//DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk " + VendaContract.TABLE_NAME + "  " + values.toString());
						DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk id:" + value.get(VendaContract.COLUNA_CHAVE) + ")" + values.toString());
				} else {
					long _id = db.insert(VendaContract.TABLE_NAME, null, value);
					if (_id != -1) {
						//DCLog.d(DCLog.DATABASE_CRUD,this,"insert (bulk)" + VendaContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
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
            	long _id = db.insert(VendaContract.TABLE_NAME, null, value);
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
		String sql = "select max(" + VendaContract.COLUNA_CHAVE + ") from " + VendaContract.TABLE_NAME;
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