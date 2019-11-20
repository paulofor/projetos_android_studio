
package  br.com.lojadigicom.treinoacademia.data.provider;

import br.com.lojadigicom.treinoacademia.data.helper.AplicacaoDbHelper;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import java.util.List;

public abstract class AplicacaoProvider extends ContentProvider {

	private static final UriMatcher sUriMatcher = buildUriMatcher();
	
	private UsuarioProvider usuarioProvider = criaUsuarioProvider();
	protected abstract UsuarioProvider criaUsuarioProvider();
		
	private ExercicioProvider exercicioProvider = criaExercicioProvider();
	protected abstract ExercicioProvider criaExercicioProvider();
		
	private SerieTreinoProvider serieTreinoProvider = criaSerieTreinoProvider();
	protected abstract SerieTreinoProvider criaSerieTreinoProvider();
		
	private ItemSerieProvider itemSerieProvider = criaItemSerieProvider();
	protected abstract ItemSerieProvider criaItemSerieProvider();
		
	private CargaPlanejadaProvider cargaPlanejadaProvider = criaCargaPlanejadaProvider();
	protected abstract CargaPlanejadaProvider criaCargaPlanejadaProvider();
		
	private ExecucaoItemSerieProvider execucaoItemSerieProvider = criaExecucaoItemSerieProvider();
	protected abstract ExecucaoItemSerieProvider criaExecucaoItemSerieProvider();
		
	private DiaTreinoProvider diaTreinoProvider = criaDiaTreinoProvider();
	protected abstract DiaTreinoProvider criaDiaTreinoProvider();
		
	private DispositivoUsuarioProvider dispositivoUsuarioProvider = criaDispositivoUsuarioProvider();
	protected abstract DispositivoUsuarioProvider criaDispositivoUsuarioProvider();
		
	private GrupoMuscularProvider grupoMuscularProvider = criaGrupoMuscularProvider();
	protected abstract GrupoMuscularProvider criaGrupoMuscularProvider();
		
	private ErroExceptionProvider erroExceptionProvider = criaErroExceptionProvider();
	protected abstract ErroExceptionProvider criaErroExceptionProvider();
		

	public static UriMatcher buildUriMatcher() {

        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        //final String authority = AplicacaoContract.CONTENTI_AUTHORITY;
		
		
		UsuarioProvider.buildUriMatcher(matcher);
		
		ExercicioProvider.buildUriMatcher(matcher);
		
		SerieTreinoProvider.buildUriMatcher(matcher);
		
		ItemSerieProvider.buildUriMatcher(matcher);
		
		CargaPlanejadaProvider.buildUriMatcher(matcher);
		
		ExecucaoItemSerieProvider.buildUriMatcher(matcher);
		
		DiaTreinoProvider.buildUriMatcher(matcher);
		
		DispositivoUsuarioProvider.buildUriMatcher(matcher);
		
		GrupoMuscularProvider.buildUriMatcher(matcher);
		
		ErroExceptionProvider.buildUriMatcher(matcher);
		
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
		
		usuarioProvider.setAplicacaoDbHelper(mOpenHelper);
		usuarioProvider.setContentProvider(this);
		
		exercicioProvider.setAplicacaoDbHelper(mOpenHelper);
		exercicioProvider.setContentProvider(this);
		
		serieTreinoProvider.setAplicacaoDbHelper(mOpenHelper);
		serieTreinoProvider.setContentProvider(this);
		
		itemSerieProvider.setAplicacaoDbHelper(mOpenHelper);
		itemSerieProvider.setContentProvider(this);
		
		cargaPlanejadaProvider.setAplicacaoDbHelper(mOpenHelper);
		cargaPlanejadaProvider.setContentProvider(this);
		
		execucaoItemSerieProvider.setAplicacaoDbHelper(mOpenHelper);
		execucaoItemSerieProvider.setContentProvider(this);
		
		diaTreinoProvider.setAplicacaoDbHelper(mOpenHelper);
		diaTreinoProvider.setContentProvider(this);
		
		dispositivoUsuarioProvider.setAplicacaoDbHelper(mOpenHelper);
		dispositivoUsuarioProvider.setContentProvider(this);
		
		grupoMuscularProvider.setAplicacaoDbHelper(mOpenHelper);
		grupoMuscularProvider.setContentProvider(this);
		
		erroExceptionProvider.setAplicacaoDbHelper(mOpenHelper);
		erroExceptionProvider.setContentProvider(this);
		
	    return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		List<String> lista = uri.getPathSegments();
		Cursor retCursor = null;
		
		if ("usuario".equals(lista.get(0)))
			retCursor = usuarioProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("exercicio".equals(lista.get(0)))
			retCursor = exercicioProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("serie_treino".equals(lista.get(0)))
			retCursor = serieTreinoProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("item_serie".equals(lista.get(0)))
			retCursor = itemSerieProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("carga_planejada".equals(lista.get(0)))
			retCursor = cargaPlanejadaProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("execucao_item_serie".equals(lista.get(0)))
			retCursor = execucaoItemSerieProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("dia_treino".equals(lista.get(0)))
			retCursor = diaTreinoProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("dispositivo_usuario".equals(lista.get(0)))
			retCursor = dispositivoUsuarioProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("grupo_muscular".equals(lista.get(0)))
			retCursor = grupoMuscularProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("erro_exception".equals(lista.get(0)))
			retCursor = erroExceptionProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
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
			tipo = usuarioProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = exercicioProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = serieTreinoProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = itemSerieProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = cargaPlanejadaProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = execucaoItemSerieProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = diaTreinoProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = dispositivoUsuarioProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = grupoMuscularProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = erroExceptionProvider.getType(sUriMatcher, uri);
		
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
		
		if ("usuario".equals(lista.get(0)))
			returnUri = usuarioProvider.insert(uri, values);
		
		if ("exercicio".equals(lista.get(0)))
			returnUri = exercicioProvider.insert(uri, values);
		
		if ("serie_treino".equals(lista.get(0)))
			returnUri = serieTreinoProvider.insert(uri, values);
		
		if ("item_serie".equals(lista.get(0)))
			returnUri = itemSerieProvider.insert(uri, values);
		
		if ("carga_planejada".equals(lista.get(0)))
			returnUri = cargaPlanejadaProvider.insert(uri, values);
		
		if ("execucao_item_serie".equals(lista.get(0)))
			returnUri = execucaoItemSerieProvider.insert(uri, values);
		
		if ("dia_treino".equals(lista.get(0)))
			returnUri = diaTreinoProvider.insert(uri, values);
		
		if ("dispositivo_usuario".equals(lista.get(0)))
			returnUri = dispositivoUsuarioProvider.insert(uri, values);
		
		if ("grupo_muscular".equals(lista.get(0)))
			returnUri = grupoMuscularProvider.insert(uri, values);
		
		if ("erro_exception".equals(lista.get(0)))
			returnUri = erroExceptionProvider.insert(uri, values);
		
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
		
		if ("usuario".equals(lista.get(0))) 
			matchOk = usuarioProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = usuarioProvider.getLinhas();
		
		if ("exercicio".equals(lista.get(0))) 
			matchOk = exercicioProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = exercicioProvider.getLinhas();
		
		if ("serie_treino".equals(lista.get(0))) 
			matchOk = serieTreinoProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = serieTreinoProvider.getLinhas();
		
		if ("item_serie".equals(lista.get(0))) 
			matchOk = itemSerieProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = itemSerieProvider.getLinhas();
		
		if ("carga_planejada".equals(lista.get(0))) 
			matchOk = cargaPlanejadaProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = cargaPlanejadaProvider.getLinhas();
		
		if ("execucao_item_serie".equals(lista.get(0))) 
			matchOk = execucaoItemSerieProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = execucaoItemSerieProvider.getLinhas();
		
		if ("dia_treino".equals(lista.get(0))) 
			matchOk = diaTreinoProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = diaTreinoProvider.getLinhas();
		
		if ("dispositivo_usuario".equals(lista.get(0))) 
			matchOk = dispositivoUsuarioProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = dispositivoUsuarioProvider.getLinhas();
		
		if ("grupo_muscular".equals(lista.get(0))) 
			matchOk = grupoMuscularProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = grupoMuscularProvider.getLinhas();
		
		if ("erro_exception".equals(lista.get(0))) 
			matchOk = erroExceptionProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = erroExceptionProvider.getLinhas();
		
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
		
		if ("usuario".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = usuarioProvider.update(uri, values);
			} else {
				matchOk = usuarioProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = usuarioProvider.getLinhas();
		}
		
		if ("exercicio".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = exercicioProvider.update(uri, values);
			} else {
				matchOk = exercicioProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = exercicioProvider.getLinhas();
		}
		
		if ("serie_treino".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = serieTreinoProvider.update(uri, values);
			} else {
				matchOk = serieTreinoProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = serieTreinoProvider.getLinhas();
		}
		
		if ("item_serie".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = itemSerieProvider.update(uri, values);
			} else {
				matchOk = itemSerieProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = itemSerieProvider.getLinhas();
		}
		
		if ("carga_planejada".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = cargaPlanejadaProvider.update(uri, values);
			} else {
				matchOk = cargaPlanejadaProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = cargaPlanejadaProvider.getLinhas();
		}
		
		if ("execucao_item_serie".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = execucaoItemSerieProvider.update(uri, values);
			} else {
				matchOk = execucaoItemSerieProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = execucaoItemSerieProvider.getLinhas();
		}
		
		if ("dia_treino".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = diaTreinoProvider.update(uri, values);
			} else {
				matchOk = diaTreinoProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = diaTreinoProvider.getLinhas();
		}
		
		if ("dispositivo_usuario".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = dispositivoUsuarioProvider.update(uri, values);
			} else {
				matchOk = dispositivoUsuarioProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = dispositivoUsuarioProvider.getLinhas();
		}
		
		if ("grupo_muscular".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = grupoMuscularProvider.update(uri, values);
			} else {
				matchOk = grupoMuscularProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = grupoMuscularProvider.getLinhas();
		}
		
		if ("erro_exception".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = erroExceptionProvider.update(uri, values);
			} else {
				matchOk = erroExceptionProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = erroExceptionProvider.getLinhas();
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
		if ("usuario".equals(lista.get(0)))
			rowsUpdated =  usuarioProvider.bulkInsert(uri, values);
		
		if ("exercicio".equals(lista.get(0)))
			rowsUpdated =  exercicioProvider.bulkInsert(uri, values);
		
		if ("serie_treino".equals(lista.get(0)))
			rowsUpdated =  serieTreinoProvider.bulkInsert(uri, values);
		
		if ("item_serie".equals(lista.get(0)))
			rowsUpdated =  itemSerieProvider.bulkInsert(uri, values);
		
		if ("carga_planejada".equals(lista.get(0)))
			rowsUpdated =  cargaPlanejadaProvider.bulkInsert(uri, values);
		
		if ("execucao_item_serie".equals(lista.get(0)))
			rowsUpdated =  execucaoItemSerieProvider.bulkInsert(uri, values);
		
		if ("dia_treino".equals(lista.get(0)))
			rowsUpdated =  diaTreinoProvider.bulkInsert(uri, values);
		
		if ("dispositivo_usuario".equals(lista.get(0)))
			rowsUpdated =  dispositivoUsuarioProvider.bulkInsert(uri, values);
		
		if ("grupo_muscular".equals(lista.get(0)))
			rowsUpdated =  grupoMuscularProvider.bulkInsert(uri, values);
		
		if ("erro_exception".equals(lista.get(0)))
			rowsUpdated =  erroExceptionProvider.bulkInsert(uri, values);
		
		return rowsUpdated;
	}
	
}