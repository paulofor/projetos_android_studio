
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

public abstract class ProdutoClienteProvider {

	private int qtdeLinhas = 0;
	
	public static final int PRODUTO_CLIENTE = 88200;
	public static final int PRODUTO_CLIENTE_ID = 88201;
	public static final int PRODUTO_CLIENTE_SINC = 88203;
	public static final int PRODUTO_CLIENTE_E_COMPLEMENTO = 88204;
	public static final int PRODUTO_CLIENTE_ID_E_COMPLEMENTO = 88205;
	//public static final int PRODUTO_CLIENTE_OPERACAO = 88202;
	
	// deletes
	public static final int PRODUTO_CLIENTE_DELETE_ALL_SINC = 88206;
	public static final int PRODUTO_CLIENTE_DELETE_RECREATE = 88207;
	public static final int PRODUTO_CLIENTE_DELETE_SINC = 88208;
	public static final int PRODUTO_CLIENTE_E_RETIRADA = 88209;
	
	private static final String sPorChaveSel = ProdutoClienteContract.TABLE_NAME + "." + ProdutoClienteContract.COLUNA_CHAVE + " = ? ";
	
	
	
	public static final int PRODUTO_CLIENTE_POR_NATUREZA_PRODUTO_REFERENTEA = 88220;
	public static final int COM_NATUREZA_PRODUTO = 88221;
	private static final String sPorIdNaturezaProdutoRaSelecao =
            ProdutoClienteContract.TABLE_NAME + ".id_natureza_produto_ra = ? ";
	

 	public static final int LISTANAOESCOLHIDO = 88222;
 	public static final int OBTEMPROXIMONAOESCOLHIDO = 88223;

	private ContentProvider mProvider = null;


	public void setContentProvider(ContentProvider valor) {
		mProvider = valor;
	}

	protected static final SQLiteQueryBuilder sQueryBuilder;
	static {
		sQueryBuilder = new SQLiteQueryBuilder();
		String tabelas = ProdutoClienteContract.TABLE_NAME;
		
		//tabelas += " inner join " + NaturezaProdutoContract.TABLE_NAME + " ";
		//tabelas += " on " + NaturezaProdutoContract.TABLE_NAME + "." + NaturezaProdutoContract.COLUNA_CHAVE + " = " + ProdutoClienteContract.TABLE_NAME + "." + ProdutoClienteContract.COLUNA_ID_NATUREZA_PRODUTO_RA; 
		
		sQueryBuilder.setTables(tabelas);
	}
	private static final SQLiteQueryBuilder sQueryBuilderSinc;
	static {
		sQueryBuilderSinc = new SQLiteQueryBuilder();
		String tabelas = ProdutoClienteContract.TABLE_NAME_SINC;
		sQueryBuilderSinc.setTables(tabelas);
	}
	
	
	protected AplicacaoDbHelper mOpenHelper = null;
	
	public int getLinhas() {
		return qtdeLinhas;
	}
	
	public static void buildUriMatcher(UriMatcher matcher) {
		matcher.addURI(ProdutoClienteContract.getContentAuthority(), ProdutoClienteContract.PATH, PRODUTO_CLIENTE);
		matcher.addURI(ProdutoClienteContract.getContentAuthority(), ProdutoClienteContract.PATH + "/Sinc" , PRODUTO_CLIENTE_SINC);
		matcher.addURI(ProdutoClienteContract.getContentAuthority(), ProdutoClienteContract.PATH + "/#"    , PRODUTO_CLIENTE_ID);
	
		matcher.addURI(ProdutoClienteContract.getContentAuthority(), ProdutoClienteContract.PATH + "/#/" + ProdutoClienteContract.COM_COMPLEMENTO + "/*" , PRODUTO_CLIENTE_ID_E_COMPLEMENTO);
		matcher.addURI(ProdutoClienteContract.getContentAuthority(), ProdutoClienteContract.PATH + "/" + ProdutoClienteContract.COM_COMPLEMENTO + "/*" , PRODUTO_CLIENTE_E_COMPLEMENTO);
		matcher.addURI(ProdutoClienteContract.getContentAuthority(), ProdutoClienteContract.PATH + "/" + ProdutoClienteContract.COM_RETIRADA + "/*" , PRODUTO_CLIENTE_E_RETIRADA);
		
		
		//matcher.addURI(AplicacaoContract.CONTENT_AUTHORITY, ProdutoClienteContract.PATH + "/operacao/*" , PRODUTO_CLIENTE_OPERACAO);
		
		matcher.addURI(ProdutoClienteContract.getContentAuthority(), ProdutoClienteContract.PATH + "/#/" + NaturezaProdutoContract.PATH, PRODUTO_CLIENTE_POR_NATUREZA_PRODUTO_REFERENTEA);
		matcher.addURI(ProdutoClienteContract.getContentAuthority(), ProdutoClienteContract.PATH + "/ComNaturezaProdutoReferenteA/" , COM_NATUREZA_PRODUTO);
		
		
		
		matcher.addURI(ProdutoClienteContract.getContentAuthority(), ProdutoClienteContract.PATH + "/ListaNaoEscolhido/*", LISTANAOESCOLHIDO);
		
		matcher.addURI(ProdutoClienteContract.getContentAuthority(), ProdutoClienteContract.PATH + "/ObtemProximoNaoEscolhido/*", OBTEMPROXIMONAOESCOLHIDO);
		
		
		// Deletes
		matcher.addURI(ProdutoClienteContract.getContentAuthority(), ProdutoClienteContract.PATH + "/DeleteAllSinc" , 	PRODUTO_CLIENTE_DELETE_ALL_SINC);
		matcher.addURI(ProdutoClienteContract.getContentAuthority(), ProdutoClienteContract.PATH + "/DeleteAllRecreate" , PRODUTO_CLIENTE_DELETE_RECREATE);
		matcher.addURI(ProdutoClienteContract.getContentAuthority(), ProdutoClienteContract.PATH + "/DeleteSinc/#" , 		PRODUTO_CLIENTE_DELETE_SINC);
	}
	
	
	
	
	public void setAplicacaoDbHelper(AplicacaoDbHelper db) {
		mOpenHelper = db;
	}
	
	public Cursor query(UriMatcher sUriMatcher, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Query Uri:" + uri.toString());
		Cursor retCursor = null;
		switch (sUriMatcher.match(uri)) {
            case PRODUTO_CLIENTE:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_CLIENTE");
                retCursor = query(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case PRODUTO_CLIENTE_SINC:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_CLIENTE_SINC");
                retCursor = querySinc(projection, selection, selectionArgs, sortOrder);
                break;
            }
            case PRODUTO_CLIENTE_ID:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_CLIENTE_ID");
            	String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorChaveSel, args, sortOrder);
                break;
            }
            case PRODUTO_CLIENTE_ID_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_CLIENTE_ID_E_COMPLEMENTO");
				String id = uri.getPathSegments().get(1);	
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri) +
						" where " + ProdutoClienteContract.COLUNA_CHAVE + " = " + id;
				retCursor = queryRaw(sql);
				break;
			}
			case PRODUTO_CLIENTE_E_COMPLEMENTO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_CLIENTE_E_COMPLEMENTO");
				String sql = "select " + sqlSelect(uri) +
						" from " + sqlFrom(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case PRODUTO_CLIENTE_E_RETIRADA:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_CLIENTE_E_RETIRADA");
				String sql = "select " +  ProdutoClienteContract.camposOrdenados() +
						" from " + ProdutoClienteContract.TABLE_NAME +
						sqlWhere(uri);
						// colocar 
				retCursor = queryRaw(sql);
				break;
			}
			case PRODUTO_CLIENTE_POR_NATUREZA_PRODUTO_REFERENTEA:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: PRODUTO_CLIENTE_POR_NATUREZA_PRODUTO_REFERENTEA");
	            String[] args = {uri.getPathSegments().get(1)};
                retCursor = query(projection, sPorIdNaturezaProdutoRaSelecao, args, sortOrder);
                break;
            }
            case COM_NATUREZA_PRODUTO:
            {
            	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: COM_NATUREZA_PRODUTO");
            	String sql = "select " + ProdutoClienteContract.camposOrdenados() + " , " +
						NaturezaProdutoContract.camposOrdenados() +
						" from " + ProdutoClienteContract.TABLE_NAME +
						ProdutoClienteContract.innerJoinNaturezaProduto_ReferenteA() +
						getOrderBy();
                retCursor = queryRaw(sql);
				break;
            }
		
		
	 		case LISTANAOESCOLHIDO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: LISTANAOESCOLHIDO");
				retCursor = queryListaNaoEscolhido(uri,projection,sortOrder);
				break;
			}
		
	 		case OBTEMPROXIMONAOESCOLHIDO:
			{
				DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Match Provider: OBTEMPROXIMONAOESCOLHIDO");
				retCursor = queryObtemProximoNaoEscolhido(uri,projection,sortOrder);
				break;
			}
		
		}	
        return retCursor;
	}
	
	private String sqlWhere(Uri uri) {
		String sql = " where ";
		// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("SemInteresseProdutoPossui",uri.getPathSegments())) {
			sql += ProdutoClienteContract.COLUNA_CHAVE + " not in (select " + 
					InteresseProdutoContract.COLUNA_ID_PRODUTO_CLIENTE_RA + " from " +
					InteresseProdutoContract.TABLE_NAME + ")";
		}
	
		
		return sql;
	}
	
	private String sqlSelect(Uri uri) {
		String sql = ProdutoClienteContract.camposOrdenados();
		List<String> segmentos = uri.getPathSegments();
		// ComChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComNaturezaProdutoReferenteA",uri.getPathSegments())) {
			sql += "," +  NaturezaProdutoContract.camposOrdenados();
		}
	
	
	// SemChaveEstrangeira - Sem Usuario (sempre vai ser o mesmo, redundante)
  	
		if (existeItem("ComInteresseProdutoPossui",uri.getPathSegments())) {
			sql += "," +  InteresseProdutoContract.camposOrdenados();
		}
	
		
		
		
		return sql;
	}
	private String sqlFrom(Uri uri) {
		String sql = ProdutoClienteContract.TABLE_NAME;
		List<String> segmentos = uri.getPathSegments();
		//if (existeItem("ComEpisodioReferenteA",uri.getPathSegments())) {
		//	sql += " " +  EpisodioUsuarioContract.innerJoinEpisodio_ReferenteA();
		//}
		
		
				// ComChaveEstrangeira
  	
		if (existeItem("ComNaturezaProdutoReferenteA",uri.getPathSegments())) {
			sql += " " +  ProdutoClienteContract.outerJoinNaturezaProduto_ReferenteA();
		}
	
	
	// SemChaveEstrangeira
  	
		if (existeItem("ComInteresseProdutoPossui",uri.getPathSegments())) {
			sql += " " +  ProdutoClienteContract.outerJoinInteresseProduto_Possui();
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
	
	
	protected abstract Cursor queryListaNaoEscolhido(Uri uri, String[] projection, String sortOrder);
	
	protected abstract Cursor queryObtemProximoNaoEscolhido(Uri uri, String[] projection, String sortOrder);
	
	
	
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
            case PRODUTO_CLIENTE:
            {
                return ProdutoClienteContract.getContentType();
            }
            case PRODUTO_CLIENTE_ID:
            {
            	return ProdutoClienteContract.getContentItemType();
            }
			case PRODUTO_CLIENTE_POR_NATUREZA_PRODUTO_REFERENTEA:
            {
	            return ProdutoClienteContract.getContentType();
            }
		
		}	
		return null;
	}
	
	public Uri insert(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Uri returnUri;
		long idNovo = getMaxId(db)+1;
		values.put(ProdutoClienteContract.COLUNA_CHAVE, idNovo);
		long _id = db.insert(ProdutoClienteContract.TABLE_NAME, null, values);
		if (_id > 0) {
			//DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + ProdutoClienteContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
			DCLog.d(DCLog.DATABASE_CRUD,this,"insert " + values.toString() + " (id:" + _id + ")");
			returnUri = ProdutoClienteContract.buildProdutoClienteUri(idNovo);
			values.put(ProdutoClienteContract.COLUNA_OPERACAO_SINC,"I");
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
			case PRODUTO_CLIENTE_DELETE_SINC: {
				String id = uri.getPathSegments().get(2);
				Cursor cursor = db.query(ProdutoClienteContract.TABLE_NAME,null,ProdutoClienteContract.COLUNA_CHAVE + " = ? ",new String[]{id},null,null,null);
				if (cursor.moveToFirst()) {
					ContentValues valores = new ContentValues();
					DatabaseUtils.cursorRowToContentValues(cursor, valores);
					linhaDelete = db.delete(ProdutoClienteContract.TABLE_NAME, ProdutoClienteContract.COLUNA_CHAVE + " = ? ", new String[]{id});
					DCLog.d(DCLog.DATABASE_CRUD,this,"delete " + ProdutoClienteContract.TABLE_NAME + "(id:" + id + ")");
					valores.put(ProdutoClienteContract.COLUNA_OPERACAO_SINC, "D");
					insereSinc(db,valores);
				}
				notificaUriRelacionadas();
				mProvider.getContext().getContentResolver().notifyChange(ProdutoClienteContract.buildAll(), null);
				break;
			}
			case PRODUTO_CLIENTE_DELETE_ALL_SINC: {
				linhaDelete = db.delete(ProdutoClienteContract.TABLE_NAME_SINC, null, null);
				break;
			}
			case PRODUTO_CLIENTE_DELETE_RECREATE: {
				linhaDelete = db.delete(ProdutoClienteContract.TABLE_NAME, null, null);
				break;
			}
		}
		return true;
	}
	
	
	public boolean update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		linhaUpdate = db.update(ProdutoClienteContract.TABLE_NAME, values, selection, selectionArgs);
		return true;
	}
	public boolean update(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int linhaUpdate =0;
		String selection = ProdutoClienteContract.COLUNA_CHAVE + " = ? ";
		String dados[] = {values.get(ProdutoClienteContract.COLUNA_CHAVE).toString()};
		linhaUpdate = db.update(ProdutoClienteContract.TABLE_NAME, values, selection, dados);
		values.put(ProdutoClienteContract.COLUNA_OPERACAO_SINC,"A");
		insereSinc(db,values);
		return true;
	}
	
	private void insereSinc(SQLiteDatabase db, ContentValues values) {
		db.insert(ProdutoClienteContract.TABLE_NAME_SINC, null, values);
	}
	
	// Notificar todas as uris de entidades que possuem chave estrangeira dessa entidade.
	private void notificaUriRelacionadas() {
		// ComChaveEstrangeira
  	
		mProvider.getContext().getContentResolver().notifyChange(NaturezaProdutoContract.buildAllComProdutoClientePossui(), null);
		mProvider.getContext().getContentResolver().notifyChange(NaturezaProdutoContract.buildAllSemProdutoClientePossui(), null);
	
	}
	private void notificaUriOperacoes() {
	
		mProvider.getContext().getContentResolver().notifyChange(ProdutoClienteContract.buildListaNaoEscolhido(), null);
	
		mProvider.getContext().getContentResolver().notifyChange(ProdutoClienteContract.buildObtemProximoNaoEscolhido(), null);
	
	}
	private void notificaUriRaiz(){
		
	}
	
	
	
	public int bulkInsert(Uri uri, ContentValues[] values) {
    	final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        db.beginTransaction();
        int returnCount = 0;
        try {
        	for (ContentValues value : values) {
				String[] args = {(String)value.get(ProdutoClienteContract.COLUNA_CHAVE)};
				Cursor retCursor = query(null, sPorChaveSel, args, null);
				if (retCursor.moveToFirst()) {
						db.update(ProdutoClienteContract.TABLE_NAME,value,sPorChaveSel,args);
						//DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk " + ProdutoClienteContract.TABLE_NAME + "  " + values.toString());
						DCLog.d(DCLog.DATABASE_CRUD,this,"update (bulk id:" + value.get(ProdutoClienteContract.COLUNA_CHAVE) + ")" + values.toString());
				} else {
					long _id = db.insert(ProdutoClienteContract.TABLE_NAME, null, value);
					if (_id != -1) {
						//DCLog.d(DCLog.DATABASE_CRUD,this,"insert (bulk)" + ProdutoClienteContract.TABLE_NAME + "  " + values.toString() + " (id:" + _id + ")");
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
            	long _id = db.insert(ProdutoClienteContract.TABLE_NAME, null, value);
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
		String sql = "select max(" + ProdutoClienteContract.COLUNA_CHAVE + ") from " + ProdutoClienteContract.TABLE_NAME;
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