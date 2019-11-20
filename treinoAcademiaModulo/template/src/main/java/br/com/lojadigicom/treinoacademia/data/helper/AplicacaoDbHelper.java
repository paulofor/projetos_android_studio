
package  br.com.lojadigicom.treinoacademia.data.helper;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.lojadigicom.treinoacademia.framework.log.DCLog;
import br.com.lojadigicom.treinoacademia.data.DatabaseConst;
import br.com.lojadigicom.treinoacademia.framework.fcm.DCSincronizador;

public class AplicacaoDbHelper extends SQLiteOpenHelper{

	/*
	private static final int DATABASE_VERSION = 1;
	public static String DATABASE_NAME = "treinoacademia.db";
	*/
	//public static String DATABASE_NAME = "/mnt/extSdCard/digidata/treinoacademia.db";

	private static DatabaseConst databaseConst = null;
	private static DCSincronizador sincronizador = null;

	public static void setSincronizador(DCSincronizador valor) {
		sincronizador = valor;
	}

	public static void setDatabaseConst(DatabaseConst dbConst) {
		databaseConst = dbConst;
	}

	public static String getNomeBanco() {
		return databaseConst.getName();
	}
	
	public AplicacaoDbHelper(Context context) {
		//super(context, DATABASE_NAME , null, DATABASE_VERSION);
		super(context,databaseConst.getName(),null,databaseConst.getVersion());
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		DCLog.d(DCLog.DATABASE_ADM,this,"Create database em " + databaseConst.getName());
	
		String CREATE_SQL = null;
	
		CREATE_SQL = UsuarioDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = UsuarioDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = ExercicioDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = ExercicioDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = SerieTreinoDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = SerieTreinoDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = ItemSerieDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = ItemSerieDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = CargaPlanejadaDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = CargaPlanejadaDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = ExecucaoItemSerieDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = ExecucaoItemSerieDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = DiaTreinoDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = DiaTreinoDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = DispositivoUsuarioDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = DispositivoUsuarioDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = GrupoMuscularDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = GrupoMuscularDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = ErroExceptionDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = ErroExceptionDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
	}
	
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		DCLog.d(DCLog.DATABASE_ADM,this,"Upgrade database em " + databaseConst.getName());
		String UPDATE_SQL = null;
	
		//UPDATE_SQL = UsuarioDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = UsuarioDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = UsuarioDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = UsuarioDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = UsuarioDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = UsuarioDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = ExercicioDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = ExercicioDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ExercicioDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ExercicioDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ExercicioDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ExercicioDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = SerieTreinoDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = SerieTreinoDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = SerieTreinoDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = SerieTreinoDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = SerieTreinoDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = SerieTreinoDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = ItemSerieDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = ItemSerieDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ItemSerieDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ItemSerieDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ItemSerieDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ItemSerieDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = CargaPlanejadaDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = CargaPlanejadaDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = CargaPlanejadaDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = CargaPlanejadaDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = CargaPlanejadaDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = CargaPlanejadaDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = ExecucaoItemSerieDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = ExecucaoItemSerieDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ExecucaoItemSerieDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ExecucaoItemSerieDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ExecucaoItemSerieDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ExecucaoItemSerieDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = DiaTreinoDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = DiaTreinoDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = DiaTreinoDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = DiaTreinoDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = DiaTreinoDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = DiaTreinoDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = DispositivoUsuarioDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = DispositivoUsuarioDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = DispositivoUsuarioDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = DispositivoUsuarioDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = DispositivoUsuarioDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = DispositivoUsuarioDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = GrupoMuscularDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = GrupoMuscularDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = GrupoMuscularDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = GrupoMuscularDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = GrupoMuscularDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = GrupoMuscularDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = ErroExceptionDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = ErroExceptionDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ErroExceptionDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ErroExceptionDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ErroExceptionDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ErroExceptionDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
	
		//sincronizador.sincronizacaoUpgradeBd();
		/*Fica dando erro:                                                                                    java.lang.IllegalStateException: getDatabase called recursively
                           at android.database.sqlite.SQLiteOpenHelper.getDatabaseLocked(SQLiteOpenHelper.java:203)
                           at android.database.sqlite.SQLiteOpenHelper.getReadableDatabase(SQLiteOpenHelper.java:187)
                           at br.com.lojadigicom.coletorprecocliente.data.provider.OportunidadeDiaProvider.querySinc(OportunidadeDiaProvider.java:264)
                           at br.com.lojadigicom.coletorprecocliente.data.provider.OportunidadeDiaProvider.query(OportunidadeDiaProvider.java:123)
                           at br.com.lojadigicom.coletorprecocliente.data.provider.AplicacaoProvider.query(AplicacaoProvider.java:146)
                           at android.content.ContentProvider.query(ContentProvider.java:1017)
                           at android.content.ContentProvider$Transport.query(ContentProvider.java:238)
                           at android.content.ContentResolver.query(ContentResolver.java:491)
                           at android.content.ContentResolver.query(ContentResolver.java:434)
                           at br.com.lojadigicom.coletorprecocliente.remoto.OportunidadeDiaSincronismo.sincroniza(OportunidadeDiaSincronismo.java:90)
                           at br.com.lojadigicom.coletorprecocliente.sync.Sincronizador.sincronizacaoUpgradeBd(Sincronizador.java:113)
                           at br.com.lojadigicom.coletorprecocliente.data.helper.AplicacaoDbHelper.onUpgrade(AplicacaoDbHelper.java:272)
                           at android.database.sqlite.SQLiteOpenHelper.getDatabaseLocked(SQLiteOpenHelper.java:256)
                           at android.database.sqlite.SQLiteOpenHelper.getReadableDatabase(SQLiteOpenHelper.java:187)
                           at br.com.lojadigicom.coletorprecocliente.data.provider.ProdutoClienteProvider.queryRaw(ProdutoClienteProvider.java:338)
                           at br.com.lojadigicom.coletorprecocliente.data.provider.ProdutoClienteProvider.query(ProdutoClienteProvider.java:207)
                           at br.com.lojadigicom.coletorprecocliente.data.provider.AplicacaoProvider.query(AplicacaoProvider.java:161)
                           at android.content.ContentProvider.query(ContentProvider.java:1017)
                           at android.content.ContentProvider$Transport.query(ContentProvider.java:238)
                           at android.content.ContentResolver.query(ContentResolver.java:491)
                           at android.support.v4.content.ContentResolverCompatJellybean.query(ContentResolverCompatJellybean.java:29)
                           at android.support.v4.content.ContentResolverCompat$ContentResolverCompatImplJB.query(ContentResolverCompat.java:57)
                           at android.support.v4.content.ContentResolverCompat.query(ContentResolverCompat.java:125)
                           at android.support.v4.content.CursorLoader.loadInBackground(CursorLoader.java:59)
                           at android.support.v4.content.CursorLoader.loadInBackground(CursorLoader.java:37)
                           at android.support.v4.content.AsyncTaskLoader.onLoadInBackground(AsyncTaskLoader.java:299)
                           at android.support.v4.content.AsyncTaskLoader$LoadTask.doInBackground(AsyncTaskLoader.java:57)
                           at android.support.v4.content.AsyncTaskLoader$LoadTask.doInBackground(AsyncTaskLoader.java:45)
                           at android.support.v4.content.ModernAsyncTask$2.call(ModernAsyncTask.java:138)
                           at java.util.concurrent.FutureTask.run(FutureTask.java:237)
                           at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1113)
                           at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:588)
                           at java.lang.Thread.run(Thread.java:818) */
		//SQlite nao permite isso ?
		//UPDATE_SQL = UsuarioDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = ExercicioDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = SerieTreinoDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = ItemSerieDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = CargaPlanejadaDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = ExecucaoItemSerieDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = DiaTreinoDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = DispositivoUsuarioDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = GrupoMuscularDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = ErroExceptionDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
	}
	
}