
package  br.com.lojadigicom.coletorprecocliente.data.provider;

import br.com.lojadigicom.coletorprecocliente.data.helper.AplicacaoDbHelper;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import java.util.List;

public abstract class AplicacaoProvider extends ContentProvider {

	private static final UriMatcher sUriMatcher = buildUriMatcher();
	
	private NaturezaProdutoProvider naturezaProdutoProvider = criaNaturezaProdutoProvider();
	protected abstract NaturezaProdutoProvider criaNaturezaProdutoProvider();
		
	private OportunidadeDiaProvider oportunidadeDiaProvider = criaOportunidadeDiaProvider();
	protected abstract OportunidadeDiaProvider criaOportunidadeDiaProvider();
		
	private PrecoDiarioProvider precoDiarioProvider = criaPrecoDiarioProvider();
	protected abstract PrecoDiarioProvider criaPrecoDiarioProvider();
		
	private UsuarioProvider usuarioProvider = criaUsuarioProvider();
	protected abstract UsuarioProvider criaUsuarioProvider();
		
	private DispositivoUsuarioProvider dispositivoUsuarioProvider = criaDispositivoUsuarioProvider();
	protected abstract DispositivoUsuarioProvider criaDispositivoUsuarioProvider();
		
	private UsuarioPesquisaProvider usuarioPesquisaProvider = criaUsuarioPesquisaProvider();
	protected abstract UsuarioPesquisaProvider criaUsuarioPesquisaProvider();
		
	private ProdutoClienteProvider produtoClienteProvider = criaProdutoClienteProvider();
	protected abstract ProdutoClienteProvider criaProdutoClienteProvider();
		
	private InteresseProdutoProvider interesseProdutoProvider = criaInteresseProdutoProvider();
	protected abstract InteresseProdutoProvider criaInteresseProdutoProvider();
		
	private PalavraChavePesquisaProvider palavraChavePesquisaProvider = criaPalavraChavePesquisaProvider();
	protected abstract PalavraChavePesquisaProvider criaPalavraChavePesquisaProvider();
		
	private AppProdutoProvider appProdutoProvider = criaAppProdutoProvider();
	protected abstract AppProdutoProvider criaAppProdutoProvider();
		

	public static UriMatcher buildUriMatcher() {

        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        //final String authority = AplicacaoContract.CONTENTI_AUTHORITY;
		
		
		NaturezaProdutoProvider.buildUriMatcher(matcher);
		
		OportunidadeDiaProvider.buildUriMatcher(matcher);
		
		PrecoDiarioProvider.buildUriMatcher(matcher);
		
		UsuarioProvider.buildUriMatcher(matcher);
		
		DispositivoUsuarioProvider.buildUriMatcher(matcher);
		
		UsuarioPesquisaProvider.buildUriMatcher(matcher);
		
		ProdutoClienteProvider.buildUriMatcher(matcher);
		
		InteresseProdutoProvider.buildUriMatcher(matcher);
		
		PalavraChavePesquisaProvider.buildUriMatcher(matcher);
		
		AppProdutoProvider.buildUriMatcher(matcher);
		
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
		
		naturezaProdutoProvider.setAplicacaoDbHelper(mOpenHelper);
		naturezaProdutoProvider.setContentProvider(this);
		
		oportunidadeDiaProvider.setAplicacaoDbHelper(mOpenHelper);
		oportunidadeDiaProvider.setContentProvider(this);
		
		precoDiarioProvider.setAplicacaoDbHelper(mOpenHelper);
		precoDiarioProvider.setContentProvider(this);
		
		usuarioProvider.setAplicacaoDbHelper(mOpenHelper);
		usuarioProvider.setContentProvider(this);
		
		dispositivoUsuarioProvider.setAplicacaoDbHelper(mOpenHelper);
		dispositivoUsuarioProvider.setContentProvider(this);
		
		usuarioPesquisaProvider.setAplicacaoDbHelper(mOpenHelper);
		usuarioPesquisaProvider.setContentProvider(this);
		
		produtoClienteProvider.setAplicacaoDbHelper(mOpenHelper);
		produtoClienteProvider.setContentProvider(this);
		
		interesseProdutoProvider.setAplicacaoDbHelper(mOpenHelper);
		interesseProdutoProvider.setContentProvider(this);
		
		palavraChavePesquisaProvider.setAplicacaoDbHelper(mOpenHelper);
		palavraChavePesquisaProvider.setContentProvider(this);
		
		appProdutoProvider.setAplicacaoDbHelper(mOpenHelper);
		appProdutoProvider.setContentProvider(this);
		
	    return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		List<String> lista = uri.getPathSegments();
		Cursor retCursor = null;
		
		if ("natureza_produto".equals(lista.get(0)))
			retCursor = naturezaProdutoProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("oportunidade_dia".equals(lista.get(0)))
			retCursor = oportunidadeDiaProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("preco_diario".equals(lista.get(0)))
			retCursor = precoDiarioProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("usuario".equals(lista.get(0)))
			retCursor = usuarioProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("dispositivo_usuario".equals(lista.get(0)))
			retCursor = dispositivoUsuarioProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("usuario_pesquisa".equals(lista.get(0)))
			retCursor = usuarioPesquisaProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("produto_cliente".equals(lista.get(0)))
			retCursor = produtoClienteProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("interesse_produto".equals(lista.get(0)))
			retCursor = interesseProdutoProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("palavra_chave_pesquisa".equals(lista.get(0)))
			retCursor = palavraChavePesquisaProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("app_produto".equals(lista.get(0)))
			retCursor = appProdutoProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
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
			tipo = naturezaProdutoProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = oportunidadeDiaProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = precoDiarioProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = usuarioProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = dispositivoUsuarioProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = usuarioPesquisaProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = produtoClienteProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = interesseProdutoProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = palavraChavePesquisaProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = appProdutoProvider.getType(sUriMatcher, uri);
		
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
		
		if ("natureza_produto".equals(lista.get(0)))
			returnUri = naturezaProdutoProvider.insert(uri, values);
		
		if ("oportunidade_dia".equals(lista.get(0)))
			returnUri = oportunidadeDiaProvider.insert(uri, values);
		
		if ("preco_diario".equals(lista.get(0)))
			returnUri = precoDiarioProvider.insert(uri, values);
		
		if ("usuario".equals(lista.get(0)))
			returnUri = usuarioProvider.insert(uri, values);
		
		if ("dispositivo_usuario".equals(lista.get(0)))
			returnUri = dispositivoUsuarioProvider.insert(uri, values);
		
		if ("usuario_pesquisa".equals(lista.get(0)))
			returnUri = usuarioPesquisaProvider.insert(uri, values);
		
		if ("produto_cliente".equals(lista.get(0)))
			returnUri = produtoClienteProvider.insert(uri, values);
		
		if ("interesse_produto".equals(lista.get(0)))
			returnUri = interesseProdutoProvider.insert(uri, values);
		
		if ("palavra_chave_pesquisa".equals(lista.get(0)))
			returnUri = palavraChavePesquisaProvider.insert(uri, values);
		
		if ("app_produto".equals(lista.get(0)))
			returnUri = appProdutoProvider.insert(uri, values);
		
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
		
		if ("natureza_produto".equals(lista.get(0))) 
			matchOk = naturezaProdutoProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = naturezaProdutoProvider.getLinhas();
		
		if ("oportunidade_dia".equals(lista.get(0))) 
			matchOk = oportunidadeDiaProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = oportunidadeDiaProvider.getLinhas();
		
		if ("preco_diario".equals(lista.get(0))) 
			matchOk = precoDiarioProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = precoDiarioProvider.getLinhas();
		
		if ("usuario".equals(lista.get(0))) 
			matchOk = usuarioProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = usuarioProvider.getLinhas();
		
		if ("dispositivo_usuario".equals(lista.get(0))) 
			matchOk = dispositivoUsuarioProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = dispositivoUsuarioProvider.getLinhas();
		
		if ("usuario_pesquisa".equals(lista.get(0))) 
			matchOk = usuarioPesquisaProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = usuarioPesquisaProvider.getLinhas();
		
		if ("produto_cliente".equals(lista.get(0))) 
			matchOk = produtoClienteProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = produtoClienteProvider.getLinhas();
		
		if ("interesse_produto".equals(lista.get(0))) 
			matchOk = interesseProdutoProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = interesseProdutoProvider.getLinhas();
		
		if ("palavra_chave_pesquisa".equals(lista.get(0))) 
			matchOk = palavraChavePesquisaProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = palavraChavePesquisaProvider.getLinhas();
		
		if ("app_produto".equals(lista.get(0))) 
			matchOk = appProdutoProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = appProdutoProvider.getLinhas();
		
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
		
		if ("natureza_produto".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = naturezaProdutoProvider.update(uri, values);
			} else {
				matchOk = naturezaProdutoProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = naturezaProdutoProvider.getLinhas();
		}
		
		if ("oportunidade_dia".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = oportunidadeDiaProvider.update(uri, values);
			} else {
				matchOk = oportunidadeDiaProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = oportunidadeDiaProvider.getLinhas();
		}
		
		if ("preco_diario".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = precoDiarioProvider.update(uri, values);
			} else {
				matchOk = precoDiarioProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = precoDiarioProvider.getLinhas();
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
		
		if ("usuario_pesquisa".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = usuarioPesquisaProvider.update(uri, values);
			} else {
				matchOk = usuarioPesquisaProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = usuarioPesquisaProvider.getLinhas();
		}
		
		if ("produto_cliente".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = produtoClienteProvider.update(uri, values);
			} else {
				matchOk = produtoClienteProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = produtoClienteProvider.getLinhas();
		}
		
		if ("interesse_produto".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = interesseProdutoProvider.update(uri, values);
			} else {
				matchOk = interesseProdutoProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = interesseProdutoProvider.getLinhas();
		}
		
		if ("palavra_chave_pesquisa".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = palavraChavePesquisaProvider.update(uri, values);
			} else {
				matchOk = palavraChavePesquisaProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = palavraChavePesquisaProvider.getLinhas();
		}
		
		if ("app_produto".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = appProdutoProvider.update(uri, values);
			} else {
				matchOk = appProdutoProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = appProdutoProvider.getLinhas();
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
		if ("natureza_produto".equals(lista.get(0)))
			rowsUpdated =  naturezaProdutoProvider.bulkInsert(uri, values);
		
		if ("oportunidade_dia".equals(lista.get(0)))
			rowsUpdated =  oportunidadeDiaProvider.bulkInsert(uri, values);
		
		if ("preco_diario".equals(lista.get(0)))
			rowsUpdated =  precoDiarioProvider.bulkInsert(uri, values);
		
		if ("usuario".equals(lista.get(0)))
			rowsUpdated =  usuarioProvider.bulkInsert(uri, values);
		
		if ("dispositivo_usuario".equals(lista.get(0)))
			rowsUpdated =  dispositivoUsuarioProvider.bulkInsert(uri, values);
		
		if ("usuario_pesquisa".equals(lista.get(0)))
			rowsUpdated =  usuarioPesquisaProvider.bulkInsert(uri, values);
		
		if ("produto_cliente".equals(lista.get(0)))
			rowsUpdated =  produtoClienteProvider.bulkInsert(uri, values);
		
		if ("interesse_produto".equals(lista.get(0)))
			rowsUpdated =  interesseProdutoProvider.bulkInsert(uri, values);
		
		if ("palavra_chave_pesquisa".equals(lista.get(0)))
			rowsUpdated =  palavraChavePesquisaProvider.bulkInsert(uri, values);
		
		if ("app_produto".equals(lista.get(0)))
			rowsUpdated =  appProdutoProvider.bulkInsert(uri, values);
		
		return rowsUpdated;
	}
	
}