
package  br.com.lojadigicom.precomed.data.provider;

import br.com.lojadigicom.precomed.data.helper.AplicacaoDbHelper;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import java.util.List;

public abstract class AplicacaoProvider extends ContentProvider {

	private static final UriMatcher sUriMatcher = buildUriMatcher();
	
	private ProdutoProvider produtoProvider = criaProdutoProvider();
	protected abstract ProdutoProvider criaProdutoProvider();
		
	private PrecoProdutoProvider precoProdutoProvider = criaPrecoProdutoProvider();
	protected abstract PrecoProdutoProvider criaPrecoProdutoProvider();
		
	private UsuarioProvider usuarioProvider = criaUsuarioProvider();
	protected abstract UsuarioProvider criaUsuarioProvider();
		
	private DispositivoUsuarioProvider dispositivoUsuarioProvider = criaDispositivoUsuarioProvider();
	protected abstract DispositivoUsuarioProvider criaDispositivoUsuarioProvider();
		
	private ProdutoPesquisaProvider produtoPesquisaProvider = criaProdutoPesquisaProvider();
	protected abstract ProdutoPesquisaProvider criaProdutoPesquisaProvider();
		
	private ModeloProdutoProvider modeloProdutoProvider = criaModeloProdutoProvider();
	protected abstract ModeloProdutoProvider criaModeloProdutoProvider();
		
	private ModeloProdutoProdutoProvider modeloProdutoProdutoProvider = criaModeloProdutoProdutoProvider();
	protected abstract ModeloProdutoProdutoProvider criaModeloProdutoProdutoProvider();
		
	private MarcaProvider marcaProvider = criaMarcaProvider();
	protected abstract MarcaProvider criaMarcaProvider();
		
	private LojaVirtualProvider lojaVirtualProvider = criaLojaVirtualProvider();
	protected abstract LojaVirtualProvider criaLojaVirtualProvider();
		
	private OportunidadeDiaProvider oportunidadeDiaProvider = criaOportunidadeDiaProvider();
	protected abstract OportunidadeDiaProvider criaOportunidadeDiaProvider();
		

	public static UriMatcher buildUriMatcher() {

        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        //final String authority = AplicacaoContract.CONTENTI_AUTHORITY;
		
		
		ProdutoProvider.buildUriMatcher(matcher);
		
		PrecoProdutoProvider.buildUriMatcher(matcher);
		
		UsuarioProvider.buildUriMatcher(matcher);
		
		DispositivoUsuarioProvider.buildUriMatcher(matcher);
		
		ProdutoPesquisaProvider.buildUriMatcher(matcher);
		
		ModeloProdutoProvider.buildUriMatcher(matcher);
		
		ModeloProdutoProdutoProvider.buildUriMatcher(matcher);
		
		MarcaProvider.buildUriMatcher(matcher);
		
		LojaVirtualProvider.buildUriMatcher(matcher);
		
		OportunidadeDiaProvider.buildUriMatcher(matcher);
		
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
		
		produtoProvider.setAplicacaoDbHelper(mOpenHelper);
		produtoProvider.setContentProvider(this);
		
		precoProdutoProvider.setAplicacaoDbHelper(mOpenHelper);
		precoProdutoProvider.setContentProvider(this);
		
		usuarioProvider.setAplicacaoDbHelper(mOpenHelper);
		usuarioProvider.setContentProvider(this);
		
		dispositivoUsuarioProvider.setAplicacaoDbHelper(mOpenHelper);
		dispositivoUsuarioProvider.setContentProvider(this);
		
		produtoPesquisaProvider.setAplicacaoDbHelper(mOpenHelper);
		produtoPesquisaProvider.setContentProvider(this);
		
		modeloProdutoProvider.setAplicacaoDbHelper(mOpenHelper);
		modeloProdutoProvider.setContentProvider(this);
		
		modeloProdutoProdutoProvider.setAplicacaoDbHelper(mOpenHelper);
		modeloProdutoProdutoProvider.setContentProvider(this);
		
		marcaProvider.setAplicacaoDbHelper(mOpenHelper);
		marcaProvider.setContentProvider(this);
		
		lojaVirtualProvider.setAplicacaoDbHelper(mOpenHelper);
		lojaVirtualProvider.setContentProvider(this);
		
		oportunidadeDiaProvider.setAplicacaoDbHelper(mOpenHelper);
		oportunidadeDiaProvider.setContentProvider(this);
		
	    return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		List<String> lista = uri.getPathSegments();
		Cursor retCursor = null;
		
		if ("produto".equals(lista.get(0)))
			retCursor = produtoProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("preco_produto".equals(lista.get(0)))
			retCursor = precoProdutoProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("usuario".equals(lista.get(0)))
			retCursor = usuarioProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("dispositivo_usuario".equals(lista.get(0)))
			retCursor = dispositivoUsuarioProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("produto_pesquisa".equals(lista.get(0)))
			retCursor = produtoPesquisaProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("modelo_produto".equals(lista.get(0)))
			retCursor = modeloProdutoProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("modelo_produto_produto".equals(lista.get(0)))
			retCursor = modeloProdutoProdutoProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("marca".equals(lista.get(0)))
			retCursor = marcaProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("loja_virtual".equals(lista.get(0)))
			retCursor = lojaVirtualProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("oportunidade_dia".equals(lista.get(0)))
			retCursor = oportunidadeDiaProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
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
			tipo = produtoProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = precoProdutoProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = usuarioProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = dispositivoUsuarioProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = produtoPesquisaProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = modeloProdutoProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = modeloProdutoProdutoProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = marcaProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = lojaVirtualProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = oportunidadeDiaProvider.getType(sUriMatcher, uri);
		
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
		
		if ("produto".equals(lista.get(0)))
			returnUri = produtoProvider.insert(uri, values);
		
		if ("preco_produto".equals(lista.get(0)))
			returnUri = precoProdutoProvider.insert(uri, values);
		
		if ("usuario".equals(lista.get(0)))
			returnUri = usuarioProvider.insert(uri, values);
		
		if ("dispositivo_usuario".equals(lista.get(0)))
			returnUri = dispositivoUsuarioProvider.insert(uri, values);
		
		if ("produto_pesquisa".equals(lista.get(0)))
			returnUri = produtoPesquisaProvider.insert(uri, values);
		
		if ("modelo_produto".equals(lista.get(0)))
			returnUri = modeloProdutoProvider.insert(uri, values);
		
		if ("modelo_produto_produto".equals(lista.get(0)))
			returnUri = modeloProdutoProdutoProvider.insert(uri, values);
		
		if ("marca".equals(lista.get(0)))
			returnUri = marcaProvider.insert(uri, values);
		
		if ("loja_virtual".equals(lista.get(0)))
			returnUri = lojaVirtualProvider.insert(uri, values);
		
		if ("oportunidade_dia".equals(lista.get(0)))
			returnUri = oportunidadeDiaProvider.insert(uri, values);
		
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
		
		if ("produto".equals(lista.get(0))) 
			matchOk = produtoProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = produtoProvider.getLinhas();
		
		if ("preco_produto".equals(lista.get(0))) 
			matchOk = precoProdutoProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = precoProdutoProvider.getLinhas();
		
		if ("usuario".equals(lista.get(0))) 
			matchOk = usuarioProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = usuarioProvider.getLinhas();
		
		if ("dispositivo_usuario".equals(lista.get(0))) 
			matchOk = dispositivoUsuarioProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = dispositivoUsuarioProvider.getLinhas();
		
		if ("produto_pesquisa".equals(lista.get(0))) 
			matchOk = produtoPesquisaProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = produtoPesquisaProvider.getLinhas();
		
		if ("modelo_produto".equals(lista.get(0))) 
			matchOk = modeloProdutoProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = modeloProdutoProvider.getLinhas();
		
		if ("modelo_produto_produto".equals(lista.get(0))) 
			matchOk = modeloProdutoProdutoProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = modeloProdutoProdutoProvider.getLinhas();
		
		if ("marca".equals(lista.get(0))) 
			matchOk = marcaProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = marcaProvider.getLinhas();
		
		if ("loja_virtual".equals(lista.get(0))) 
			matchOk = lojaVirtualProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = lojaVirtualProvider.getLinhas();
		
		if ("oportunidade_dia".equals(lista.get(0))) 
			matchOk = oportunidadeDiaProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = oportunidadeDiaProvider.getLinhas();
		
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
		
		if ("produto".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = produtoProvider.update(uri, values);
			} else {
				matchOk = produtoProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = produtoProvider.getLinhas();
		}
		
		if ("preco_produto".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = precoProdutoProvider.update(uri, values);
			} else {
				matchOk = precoProdutoProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = precoProdutoProvider.getLinhas();
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
		
		if ("produto_pesquisa".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = produtoPesquisaProvider.update(uri, values);
			} else {
				matchOk = produtoPesquisaProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = produtoPesquisaProvider.getLinhas();
		}
		
		if ("modelo_produto".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = modeloProdutoProvider.update(uri, values);
			} else {
				matchOk = modeloProdutoProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = modeloProdutoProvider.getLinhas();
		}
		
		if ("modelo_produto_produto".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = modeloProdutoProdutoProvider.update(uri, values);
			} else {
				matchOk = modeloProdutoProdutoProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = modeloProdutoProdutoProvider.getLinhas();
		}
		
		if ("marca".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = marcaProvider.update(uri, values);
			} else {
				matchOk = marcaProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = marcaProvider.getLinhas();
		}
		
		if ("loja_virtual".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = lojaVirtualProvider.update(uri, values);
			} else {
				matchOk = lojaVirtualProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = lojaVirtualProvider.getLinhas();
		}
		
		if ("oportunidade_dia".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = oportunidadeDiaProvider.update(uri, values);
			} else {
				matchOk = oportunidadeDiaProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = oportunidadeDiaProvider.getLinhas();
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
		if ("produto".equals(lista.get(0)))
			rowsUpdated =  produtoProvider.bulkInsert(uri, values);
		
		if ("preco_produto".equals(lista.get(0)))
			rowsUpdated =  precoProdutoProvider.bulkInsert(uri, values);
		
		if ("usuario".equals(lista.get(0)))
			rowsUpdated =  usuarioProvider.bulkInsert(uri, values);
		
		if ("dispositivo_usuario".equals(lista.get(0)))
			rowsUpdated =  dispositivoUsuarioProvider.bulkInsert(uri, values);
		
		if ("produto_pesquisa".equals(lista.get(0)))
			rowsUpdated =  produtoPesquisaProvider.bulkInsert(uri, values);
		
		if ("modelo_produto".equals(lista.get(0)))
			rowsUpdated =  modeloProdutoProvider.bulkInsert(uri, values);
		
		if ("modelo_produto_produto".equals(lista.get(0)))
			rowsUpdated =  modeloProdutoProdutoProvider.bulkInsert(uri, values);
		
		if ("marca".equals(lista.get(0)))
			rowsUpdated =  marcaProvider.bulkInsert(uri, values);
		
		if ("loja_virtual".equals(lista.get(0)))
			rowsUpdated =  lojaVirtualProvider.bulkInsert(uri, values);
		
		if ("oportunidade_dia".equals(lista.get(0)))
			rowsUpdated =  oportunidadeDiaProvider.bulkInsert(uri, values);
		
		return rowsUpdated;
	}
	
}