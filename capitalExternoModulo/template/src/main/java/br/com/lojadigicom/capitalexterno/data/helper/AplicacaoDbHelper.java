
package  br.com.lojadigicom.capitalexterno.data.helper;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.lojadigicom.capitalexterno.framework.log.DCLog;
import br.com.lojadigicom.capitalexterno.data.DatabaseConst;
import br.com.lojadigicom.capitalexterno.framework.fcm.DCSincronizador;

public class AplicacaoDbHelper extends SQLiteOpenHelper{

	/*
	private static final int DATABASE_VERSION = 1;
	public static String DATABASE_NAME = "capitalexterno.db";
	*/
	//public static String DATABASE_NAME = "/mnt/extSdCard/digidata/capitalexterno.db";

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
	
		CREATE_SQL = LinhaProdutoDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = LinhaProdutoDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = ProdutoDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = ProdutoDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = ItemCustoProdutoDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = ItemCustoProdutoDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = CustoMensalDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = CustoMensalDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = PrecoVendaProdutoDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = PrecoVendaProdutoDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = CenarioDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = CenarioDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = PrevisaoVendaDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = PrevisaoVendaDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = MesAnoDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = MesAnoDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = NegocioDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = NegocioDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = UsuarioDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = UsuarioDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = DispositivoUsuarioDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = DispositivoUsuarioDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = ComparacaoConcorrenteDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = ComparacaoConcorrenteDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = ValorAgregadoDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = ValorAgregadoDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = CaracteristicaMercadoDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = CaracteristicaMercadoDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
	}
	
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		DCLog.d(DCLog.DATABASE_ADM,this,"Upgrade database em " + databaseConst.getName());
		String UPDATE_SQL = null;
	
		//UPDATE_SQL = LinhaProdutoDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = LinhaProdutoDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = LinhaProdutoDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = LinhaProdutoDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = LinhaProdutoDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = LinhaProdutoDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = ProdutoDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = ProdutoDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ProdutoDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ProdutoDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ProdutoDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ProdutoDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = ItemCustoProdutoDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = ItemCustoProdutoDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ItemCustoProdutoDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ItemCustoProdutoDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ItemCustoProdutoDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ItemCustoProdutoDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = CustoMensalDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = CustoMensalDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = CustoMensalDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = CustoMensalDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = CustoMensalDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = CustoMensalDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = PrecoVendaProdutoDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = PrecoVendaProdutoDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = PrecoVendaProdutoDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = PrecoVendaProdutoDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = PrecoVendaProdutoDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = PrecoVendaProdutoDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = CenarioDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = CenarioDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = CenarioDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = CenarioDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = CenarioDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = CenarioDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = PrevisaoVendaDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = PrevisaoVendaDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = PrevisaoVendaDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = PrevisaoVendaDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = PrevisaoVendaDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = PrevisaoVendaDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = MesAnoDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = MesAnoDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = MesAnoDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = MesAnoDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = MesAnoDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = MesAnoDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = NegocioDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = NegocioDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = NegocioDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = NegocioDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = NegocioDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = NegocioDbHelper.getSqlCreateSinc();
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
	
		//UPDATE_SQL = ComparacaoConcorrenteDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = ComparacaoConcorrenteDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ComparacaoConcorrenteDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ComparacaoConcorrenteDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ComparacaoConcorrenteDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ComparacaoConcorrenteDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = ValorAgregadoDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = ValorAgregadoDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ValorAgregadoDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ValorAgregadoDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ValorAgregadoDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ValorAgregadoDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = CaracteristicaMercadoDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = CaracteristicaMercadoDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = CaracteristicaMercadoDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = CaracteristicaMercadoDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = CaracteristicaMercadoDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = CaracteristicaMercadoDbHelper.getSqlCreateSinc();
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
		//UPDATE_SQL = LinhaProdutoDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = ProdutoDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = ItemCustoProdutoDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = CustoMensalDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = PrecoVendaProdutoDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = CenarioDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = PrevisaoVendaDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = MesAnoDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = NegocioDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = UsuarioDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = DispositivoUsuarioDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = ComparacaoConcorrenteDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = ValorAgregadoDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = CaracteristicaMercadoDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
	}
	
}