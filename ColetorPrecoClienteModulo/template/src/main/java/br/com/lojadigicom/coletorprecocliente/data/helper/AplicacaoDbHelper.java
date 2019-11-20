
package  br.com.lojadigicom.coletorprecocliente.data.helper;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.data.DatabaseConst;
import br.com.lojadigicom.coletorprecocliente.framework.fcm.DCSincronizador;

public class AplicacaoDbHelper extends SQLiteOpenHelper{

	/*
	private static final int DATABASE_VERSION = 1;
	public static String DATABASE_NAME = "coletorprecocliente.db";
	*/
	//public static String DATABASE_NAME = "/mnt/extSdCard/digidata/coletorprecocliente.db";

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
	
		CREATE_SQL = NaturezaProdutoDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = NaturezaProdutoDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = OportunidadeDiaDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = OportunidadeDiaDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = PrecoDiarioClienteDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = PrecoDiarioClienteDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = UsuarioDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = UsuarioDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = DispositivoUsuarioDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = DispositivoUsuarioDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = UsuarioPesquisaDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = UsuarioPesquisaDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = ProdutoClienteDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = ProdutoClienteDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = InteresseProdutoDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = InteresseProdutoDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = PalavraChavePesquisaDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = PalavraChavePesquisaDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = AppProdutoDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = AppProdutoDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = OportunidadeInteresseClienteDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = OportunidadeInteresseClienteDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = CompartilhamentoProdutoDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = CompartilhamentoProdutoDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = LocalizacaoDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = LocalizacaoDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = AcaoClienteDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = AcaoClienteDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = MensagemDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = MensagemDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
	}
	
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		DCLog.d(DCLog.DATABASE_ADM,this,"Upgrade database em " + databaseConst.getName());
		String UPDATE_SQL = null;
	
		//UPDATE_SQL = NaturezaProdutoDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = NaturezaProdutoDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = NaturezaProdutoDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = NaturezaProdutoDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = NaturezaProdutoDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = NaturezaProdutoDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = OportunidadeDiaDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = OportunidadeDiaDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = OportunidadeDiaDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = OportunidadeDiaDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = OportunidadeDiaDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = OportunidadeDiaDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = PrecoDiarioClienteDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = PrecoDiarioClienteDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = PrecoDiarioClienteDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = PrecoDiarioClienteDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = PrecoDiarioClienteDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = PrecoDiarioClienteDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
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
	
		//UPDATE_SQL = UsuarioPesquisaDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = UsuarioPesquisaDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = UsuarioPesquisaDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = UsuarioPesquisaDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = UsuarioPesquisaDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = UsuarioPesquisaDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = ProdutoClienteDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = ProdutoClienteDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ProdutoClienteDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ProdutoClienteDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ProdutoClienteDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ProdutoClienteDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = InteresseProdutoDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = InteresseProdutoDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = InteresseProdutoDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = InteresseProdutoDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = InteresseProdutoDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = InteresseProdutoDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = PalavraChavePesquisaDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = PalavraChavePesquisaDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = PalavraChavePesquisaDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = PalavraChavePesquisaDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = PalavraChavePesquisaDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = PalavraChavePesquisaDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = AppProdutoDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = AppProdutoDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = AppProdutoDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = AppProdutoDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = AppProdutoDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = AppProdutoDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = OportunidadeInteresseClienteDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = OportunidadeInteresseClienteDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = OportunidadeInteresseClienteDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = OportunidadeInteresseClienteDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = OportunidadeInteresseClienteDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = OportunidadeInteresseClienteDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = CompartilhamentoProdutoDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = CompartilhamentoProdutoDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = CompartilhamentoProdutoDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = CompartilhamentoProdutoDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = CompartilhamentoProdutoDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = CompartilhamentoProdutoDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = LocalizacaoDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = LocalizacaoDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = LocalizacaoDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = LocalizacaoDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = LocalizacaoDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = LocalizacaoDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = AcaoClienteDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = AcaoClienteDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = AcaoClienteDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = AcaoClienteDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = AcaoClienteDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = AcaoClienteDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = MensagemDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = MensagemDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = MensagemDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = MensagemDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = MensagemDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = MensagemDbHelper.getSqlCreateSinc();
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
		//UPDATE_SQL = NaturezaProdutoDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = OportunidadeDiaDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = PrecoDiarioClienteDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = UsuarioDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = DispositivoUsuarioDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = UsuarioPesquisaDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = ProdutoClienteDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = InteresseProdutoDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = PalavraChavePesquisaDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = AppProdutoDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = OportunidadeInteresseClienteDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = CompartilhamentoProdutoDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = LocalizacaoDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = AcaoClienteDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = MensagemDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
	}
	
}