
package  br.com.lojadigicom.capitalexterno.data.provider;

import br.com.lojadigicom.capitalexterno.data.helper.AplicacaoDbHelper;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import java.util.List;

public abstract class AplicacaoProvider extends ContentProvider {

	private static final UriMatcher sUriMatcher = buildUriMatcher();
	
	private LinhaProdutoProvider linhaProdutoProvider = criaLinhaProdutoProvider();
	protected abstract LinhaProdutoProvider criaLinhaProdutoProvider();
		
	private ProdutoProvider produtoProvider = criaProdutoProvider();
	protected abstract ProdutoProvider criaProdutoProvider();
		
	private ItemCustoProdutoProvider itemCustoProdutoProvider = criaItemCustoProdutoProvider();
	protected abstract ItemCustoProdutoProvider criaItemCustoProdutoProvider();
		
	private CustoMensalProvider custoMensalProvider = criaCustoMensalProvider();
	protected abstract CustoMensalProvider criaCustoMensalProvider();
		
	private PrecoVendaProdutoProvider precoVendaProdutoProvider = criaPrecoVendaProdutoProvider();
	protected abstract PrecoVendaProdutoProvider criaPrecoVendaProdutoProvider();
		
	private CenarioProvider cenarioProvider = criaCenarioProvider();
	protected abstract CenarioProvider criaCenarioProvider();
		
	private PrevisaoVendaProvider previsaoVendaProvider = criaPrevisaoVendaProvider();
	protected abstract PrevisaoVendaProvider criaPrevisaoVendaProvider();
		
	private MesAnoProvider mesAnoProvider = criaMesAnoProvider();
	protected abstract MesAnoProvider criaMesAnoProvider();
		
	private NegocioProvider negocioProvider = criaNegocioProvider();
	protected abstract NegocioProvider criaNegocioProvider();
		
	private UsuarioProvider usuarioProvider = criaUsuarioProvider();
	protected abstract UsuarioProvider criaUsuarioProvider();
		
	private DispositivoUsuarioProvider dispositivoUsuarioProvider = criaDispositivoUsuarioProvider();
	protected abstract DispositivoUsuarioProvider criaDispositivoUsuarioProvider();
		
	private ComparacaoConcorrenteProvider comparacaoConcorrenteProvider = criaComparacaoConcorrenteProvider();
	protected abstract ComparacaoConcorrenteProvider criaComparacaoConcorrenteProvider();
		
	private ValorAgregadoProvider valorAgregadoProvider = criaValorAgregadoProvider();
	protected abstract ValorAgregadoProvider criaValorAgregadoProvider();
		
	private CaracteristicaMercadoProvider caracteristicaMercadoProvider = criaCaracteristicaMercadoProvider();
	protected abstract CaracteristicaMercadoProvider criaCaracteristicaMercadoProvider();
		

	public static UriMatcher buildUriMatcher() {

        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        //final String authority = AplicacaoContract.CONTENTI_AUTHORITY;
		
		
		LinhaProdutoProvider.buildUriMatcher(matcher);
		
		ProdutoProvider.buildUriMatcher(matcher);
		
		ItemCustoProdutoProvider.buildUriMatcher(matcher);
		
		CustoMensalProvider.buildUriMatcher(matcher);
		
		PrecoVendaProdutoProvider.buildUriMatcher(matcher);
		
		CenarioProvider.buildUriMatcher(matcher);
		
		PrevisaoVendaProvider.buildUriMatcher(matcher);
		
		MesAnoProvider.buildUriMatcher(matcher);
		
		NegocioProvider.buildUriMatcher(matcher);
		
		UsuarioProvider.buildUriMatcher(matcher);
		
		DispositivoUsuarioProvider.buildUriMatcher(matcher);
		
		ComparacaoConcorrenteProvider.buildUriMatcher(matcher);
		
		ValorAgregadoProvider.buildUriMatcher(matcher);
		
		CaracteristicaMercadoProvider.buildUriMatcher(matcher);
		
        //matcher.addURI(authority, WeatherContract.PATH_WEATHER, WEATHER);
        //matcher.addURI(authority, WeatherContract.PATH_WEATHER + "/*", WEATHER_WITH_LOCATION);
        //matcher.addURI(authority, WeatherContract.PATH_WEATHER + "/*/#", WEATHER_WITH_LOCATION_AND_DATE);
        //matcher.addURI(authority, WeatherContract.PATH_LOCATION, LOCATION);
        return matcher;
    }


	private AplicacaoDbHelper mOpenHelper;

	@Override
	public boolean onCreate() {
		mOpenHelper = new AplicacaoDbHelper(getContext());
		
		linhaProdutoProvider.setAplicacaoDbHelper(mOpenHelper);
		linhaProdutoProvider.setContentProvider(this);
		
		produtoProvider.setAplicacaoDbHelper(mOpenHelper);
		produtoProvider.setContentProvider(this);
		
		itemCustoProdutoProvider.setAplicacaoDbHelper(mOpenHelper);
		itemCustoProdutoProvider.setContentProvider(this);
		
		custoMensalProvider.setAplicacaoDbHelper(mOpenHelper);
		custoMensalProvider.setContentProvider(this);
		
		precoVendaProdutoProvider.setAplicacaoDbHelper(mOpenHelper);
		precoVendaProdutoProvider.setContentProvider(this);
		
		cenarioProvider.setAplicacaoDbHelper(mOpenHelper);
		cenarioProvider.setContentProvider(this);
		
		previsaoVendaProvider.setAplicacaoDbHelper(mOpenHelper);
		previsaoVendaProvider.setContentProvider(this);
		
		mesAnoProvider.setAplicacaoDbHelper(mOpenHelper);
		mesAnoProvider.setContentProvider(this);
		
		negocioProvider.setAplicacaoDbHelper(mOpenHelper);
		negocioProvider.setContentProvider(this);
		
		usuarioProvider.setAplicacaoDbHelper(mOpenHelper);
		usuarioProvider.setContentProvider(this);
		
		dispositivoUsuarioProvider.setAplicacaoDbHelper(mOpenHelper);
		dispositivoUsuarioProvider.setContentProvider(this);
		
		comparacaoConcorrenteProvider.setAplicacaoDbHelper(mOpenHelper);
		comparacaoConcorrenteProvider.setContentProvider(this);
		
		valorAgregadoProvider.setAplicacaoDbHelper(mOpenHelper);
		valorAgregadoProvider.setContentProvider(this);
		
		caracteristicaMercadoProvider.setAplicacaoDbHelper(mOpenHelper);
		caracteristicaMercadoProvider.setContentProvider(this);
		
	    return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		List<String> lista = uri.getPathSegments();
		Cursor retCursor = null;
		
		if ("linha_produto".equals(lista.get(0)))
			retCursor = linhaProdutoProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("produto".equals(lista.get(0)))
			retCursor = produtoProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("item_custo_produto".equals(lista.get(0)))
			retCursor = itemCustoProdutoProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("custo_mensal".equals(lista.get(0)))
			retCursor = custoMensalProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("preco_venda_produto".equals(lista.get(0)))
			retCursor = precoVendaProdutoProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("cenario".equals(lista.get(0)))
			retCursor = cenarioProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("previsao_venda".equals(lista.get(0)))
			retCursor = previsaoVendaProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("mes_ano".equals(lista.get(0)))
			retCursor = mesAnoProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("negocio".equals(lista.get(0)))
			retCursor = negocioProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("usuario".equals(lista.get(0)))
			retCursor = usuarioProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("dispositivo_usuario".equals(lista.get(0)))
			retCursor = dispositivoUsuarioProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("comparacao_concorrente".equals(lista.get(0)))
			retCursor = comparacaoConcorrenteProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("valor_agregado".equals(lista.get(0)))
			retCursor = valorAgregadoProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("caracteristica_mercado".equals(lista.get(0)))
			retCursor = caracteristicaMercadoProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if (retCursor==null)
			 throw new UnsupportedOperationException("Uri desconhecida: " + uri);
		
		
		retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
	}

	@Override
	public String getType(Uri uri) {
		List<String> lista = uri.getPathSegments();
		final int match = sUriMatcher.match(uri);
        String tipo = null;
		
		if (tipo==null) 
			tipo = linhaProdutoProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = produtoProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = itemCustoProdutoProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = custoMensalProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = precoVendaProdutoProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = cenarioProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = previsaoVendaProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = mesAnoProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = negocioProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = usuarioProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = dispositivoUsuarioProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = comparacaoConcorrenteProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = valorAgregadoProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = caracteristicaMercadoProvider.getType(sUriMatcher, uri);
		
		if (tipo==null)
			 throw new UnsupportedOperationException("Uri desconhecida: " + uri);
        return tipo;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		List<String> lista = uri.getPathSegments();
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri = null;
		
		if ("linha_produto".equals(lista.get(0)))
			returnUri = linhaProdutoProvider.insert(uri, values);
		
		if ("produto".equals(lista.get(0)))
			returnUri = produtoProvider.insert(uri, values);
		
		if ("item_custo_produto".equals(lista.get(0)))
			returnUri = itemCustoProdutoProvider.insert(uri, values);
		
		if ("custo_mensal".equals(lista.get(0)))
			returnUri = custoMensalProvider.insert(uri, values);
		
		if ("preco_venda_produto".equals(lista.get(0)))
			returnUri = precoVendaProdutoProvider.insert(uri, values);
		
		if ("cenario".equals(lista.get(0)))
			returnUri = cenarioProvider.insert(uri, values);
		
		if ("previsao_venda".equals(lista.get(0)))
			returnUri = previsaoVendaProvider.insert(uri, values);
		
		if ("mes_ano".equals(lista.get(0)))
			returnUri = mesAnoProvider.insert(uri, values);
		
		if ("negocio".equals(lista.get(0)))
			returnUri = negocioProvider.insert(uri, values);
		
		if ("usuario".equals(lista.get(0)))
			returnUri = usuarioProvider.insert(uri, values);
		
		if ("dispositivo_usuario".equals(lista.get(0)))
			returnUri = dispositivoUsuarioProvider.insert(uri, values);
		
		if ("comparacao_concorrente".equals(lista.get(0)))
			returnUri = comparacaoConcorrenteProvider.insert(uri, values);
		
		if ("valor_agregado".equals(lista.get(0)))
			returnUri = valorAgregadoProvider.insert(uri, values);
		
		if ("caracteristica_mercado".equals(lista.get(0)))
			returnUri = caracteristicaMercadoProvider.insert(uri, values);
		
		if (returnUri==null)
			 throw new UnsupportedOperationException("Uri desconhecida: " + uri);
		// Como essa notifica??o funciona ?
		getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		List<String> lista = uri.getPathSegments();
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsDeleted = 0;
        boolean matchOk = false;
		
		if ("linha_produto".equals(lista.get(0))) 
			matchOk = linhaProdutoProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = linhaProdutoProvider.getLinhas();
		
		if ("produto".equals(lista.get(0))) 
			matchOk = produtoProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = produtoProvider.getLinhas();
		
		if ("item_custo_produto".equals(lista.get(0))) 
			matchOk = itemCustoProdutoProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = itemCustoProdutoProvider.getLinhas();
		
		if ("custo_mensal".equals(lista.get(0))) 
			matchOk = custoMensalProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = custoMensalProvider.getLinhas();
		
		if ("preco_venda_produto".equals(lista.get(0))) 
			matchOk = precoVendaProdutoProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = precoVendaProdutoProvider.getLinhas();
		
		if ("cenario".equals(lista.get(0))) 
			matchOk = cenarioProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = cenarioProvider.getLinhas();
		
		if ("previsao_venda".equals(lista.get(0))) 
			matchOk = previsaoVendaProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = previsaoVendaProvider.getLinhas();
		
		if ("mes_ano".equals(lista.get(0))) 
			matchOk = mesAnoProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = mesAnoProvider.getLinhas();
		
		if ("negocio".equals(lista.get(0))) 
			matchOk = negocioProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = negocioProvider.getLinhas();
		
		if ("usuario".equals(lista.get(0))) 
			matchOk = usuarioProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = usuarioProvider.getLinhas();
		
		if ("dispositivo_usuario".equals(lista.get(0))) 
			matchOk = dispositivoUsuarioProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = dispositivoUsuarioProvider.getLinhas();
		
		if ("comparacao_concorrente".equals(lista.get(0))) 
			matchOk = comparacaoConcorrenteProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = comparacaoConcorrenteProvider.getLinhas();
		
		if ("valor_agregado".equals(lista.get(0))) 
			matchOk = valorAgregadoProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = valorAgregadoProvider.getLinhas();
		
		if ("caracteristica_mercado".equals(lista.get(0))) 
			matchOk = caracteristicaMercadoProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = caracteristicaMercadoProvider.getLinhas();
		
		if (!matchOk)
			 throw new UnsupportedOperationException("Uri desconhecida: " + uri);
		// Rever
		if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		List<String> lista = uri.getPathSegments();
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsUpdated = 0;
        boolean matchOk = false;
		
		if ("linha_produto".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = linhaProdutoProvider.update(uri, values);
			} else {
				matchOk = linhaProdutoProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = linhaProdutoProvider.getLinhas();
		}
		
		if ("produto".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = produtoProvider.update(uri, values);
			} else {
				matchOk = produtoProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = produtoProvider.getLinhas();
		}
		
		if ("item_custo_produto".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = itemCustoProdutoProvider.update(uri, values);
			} else {
				matchOk = itemCustoProdutoProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = itemCustoProdutoProvider.getLinhas();
		}
		
		if ("custo_mensal".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = custoMensalProvider.update(uri, values);
			} else {
				matchOk = custoMensalProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = custoMensalProvider.getLinhas();
		}
		
		if ("preco_venda_produto".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = precoVendaProdutoProvider.update(uri, values);
			} else {
				matchOk = precoVendaProdutoProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = precoVendaProdutoProvider.getLinhas();
		}
		
		if ("cenario".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = cenarioProvider.update(uri, values);
			} else {
				matchOk = cenarioProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = cenarioProvider.getLinhas();
		}
		
		if ("previsao_venda".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = previsaoVendaProvider.update(uri, values);
			} else {
				matchOk = previsaoVendaProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = previsaoVendaProvider.getLinhas();
		}
		
		if ("mes_ano".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = mesAnoProvider.update(uri, values);
			} else {
				matchOk = mesAnoProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = mesAnoProvider.getLinhas();
		}
		
		if ("negocio".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = negocioProvider.update(uri, values);
			} else {
				matchOk = negocioProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = negocioProvider.getLinhas();
		}
		
		if ("usuario".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = usuarioProvider.update(uri, values);
			} else {
				matchOk = usuarioProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = usuarioProvider.getLinhas();
		}
		
		if ("dispositivo_usuario".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = dispositivoUsuarioProvider.update(uri, values);
			} else {
				matchOk = dispositivoUsuarioProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = dispositivoUsuarioProvider.getLinhas();
		}
		
		if ("comparacao_concorrente".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = comparacaoConcorrenteProvider.update(uri, values);
			} else {
				matchOk = comparacaoConcorrenteProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = comparacaoConcorrenteProvider.getLinhas();
		}
		
		if ("valor_agregado".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = valorAgregadoProvider.update(uri, values);
			} else {
				matchOk = valorAgregadoProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = valorAgregadoProvider.getLinhas();
		}
		
		if ("caracteristica_mercado".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = caracteristicaMercadoProvider.update(uri, values);
			} else {
				matchOk = caracteristicaMercadoProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = caracteristicaMercadoProvider.getLinhas();
		}
		
		if (!matchOk)
			 throw new UnsupportedOperationException("Uri desconhecida: " + uri);
		// Rever
		if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
	}
	@Override
	public int bulkInsert(Uri uri, ContentValues[] values) {
		List<String> lista = uri.getPathSegments();
		String path = uri.getPath();
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		final int match = sUriMatcher.match(uri);
		int rowsUpdated = 0;
		boolean matchOk = false;
		if ("linha_produto".equals(lista.get(0)))
			rowsUpdated =  linhaProdutoProvider.bulkInsert(uri, values);
		
		if ("produto".equals(lista.get(0)))
			rowsUpdated =  produtoProvider.bulkInsert(uri, values);
		
		if ("item_custo_produto".equals(lista.get(0)))
			rowsUpdated =  itemCustoProdutoProvider.bulkInsert(uri, values);
		
		if ("custo_mensal".equals(lista.get(0)))
			rowsUpdated =  custoMensalProvider.bulkInsert(uri, values);
		
		if ("preco_venda_produto".equals(lista.get(0)))
			rowsUpdated =  precoVendaProdutoProvider.bulkInsert(uri, values);
		
		if ("cenario".equals(lista.get(0)))
			rowsUpdated =  cenarioProvider.bulkInsert(uri, values);
		
		if ("previsao_venda".equals(lista.get(0)))
			rowsUpdated =  previsaoVendaProvider.bulkInsert(uri, values);
		
		if ("mes_ano".equals(lista.get(0)))
			rowsUpdated =  mesAnoProvider.bulkInsert(uri, values);
		
		if ("negocio".equals(lista.get(0)))
			rowsUpdated =  negocioProvider.bulkInsert(uri, values);
		
		if ("usuario".equals(lista.get(0)))
			rowsUpdated =  usuarioProvider.bulkInsert(uri, values);
		
		if ("dispositivo_usuario".equals(lista.get(0)))
			rowsUpdated =  dispositivoUsuarioProvider.bulkInsert(uri, values);
		
		if ("comparacao_concorrente".equals(lista.get(0)))
			rowsUpdated =  comparacaoConcorrenteProvider.bulkInsert(uri, values);
		
		if ("valor_agregado".equals(lista.get(0)))
			rowsUpdated =  valorAgregadoProvider.bulkInsert(uri, values);
		
		if ("caracteristica_mercado".equals(lista.get(0)))
			rowsUpdated =  caracteristicaMercadoProvider.bulkInsert(uri, values);
		
		return rowsUpdated;
	}
	
}