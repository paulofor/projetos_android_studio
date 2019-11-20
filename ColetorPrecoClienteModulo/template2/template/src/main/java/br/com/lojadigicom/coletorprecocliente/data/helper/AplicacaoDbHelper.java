
package  br.com.lojadigicom.coletorprecocliente.data.helper;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.data.DatabaseConst;

public class AplicacaoDbHelper extends SQLiteOpenHelper{

	/*
	private static final int DATABASE_VERSION = 1;
	public static String DATABASE_NAME = "coletorprecocliente.db";
	*/
	//public static String DATABASE_NAME = "/mnt/extSdCard/digidata/coletorprecocliente.db";

	private static DatabaseConst databaseConst = null;

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
	
		CREATE_SQL = PrecoDiarioDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = PrecoDiarioDbHelper.getSqlCreateSinc();
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
	
		//UPDATE_SQL = PrecoDiarioDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = PrecoDiarioDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = PrecoDiarioDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = PrecoDiarioDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = PrecoDiarioDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = PrecoDiarioDbHelper.getSqlCreateSinc();
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
	
	
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = NaturezaProdutoDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = OportunidadeDiaDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = PrecoDiarioDbHelper.getSqlChavesEstrangeiras();
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
	
	}
	
}