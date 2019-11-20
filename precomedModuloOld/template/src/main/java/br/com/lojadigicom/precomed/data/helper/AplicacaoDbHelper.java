
package  br.com.lojadigicom.precomed.data.helper;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.lojadigicom.precomed.framework.log.DCLog;
import br.com.lojadigicom.precomed.data.DatabaseConst;

public class AplicacaoDbHelper extends SQLiteOpenHelper{

	/*
	private static final int DATABASE_VERSION = 1;
	public static String DATABASE_NAME = "precomed.db";
	*/
	//public static String DATABASE_NAME = "/mnt/extSdCard/digidata/precomed.db";

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
	
		CREATE_SQL = ProdutoDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = ProdutoDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = PrecoProdutoDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = PrecoProdutoDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = UsuarioDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = UsuarioDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = DispositivoUsuarioDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = DispositivoUsuarioDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = ProdutoPesquisaDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = ProdutoPesquisaDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = ModeloProdutoDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = ModeloProdutoDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = ModeloProdutoProdutoDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = ModeloProdutoProdutoDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = MarcaDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = MarcaDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = LojaVirtualDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = LojaVirtualDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = OportunidadeDiaDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = OportunidadeDiaDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
	}
	
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		DCLog.d(DCLog.DATABASE_ADM,this,"Upgrade database em " + databaseConst.getName());
		String UPDATE_SQL = null;
	
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
	
		//UPDATE_SQL = PrecoProdutoDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = PrecoProdutoDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = PrecoProdutoDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = PrecoProdutoDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = PrecoProdutoDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = PrecoProdutoDbHelper.getSqlCreateSinc();
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
	
		//UPDATE_SQL = ProdutoPesquisaDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = ProdutoPesquisaDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ProdutoPesquisaDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ProdutoPesquisaDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ProdutoPesquisaDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ProdutoPesquisaDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = ModeloProdutoDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = ModeloProdutoDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ModeloProdutoDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ModeloProdutoDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ModeloProdutoDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ModeloProdutoDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = ModeloProdutoProdutoDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = ModeloProdutoProdutoDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ModeloProdutoProdutoDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ModeloProdutoProdutoDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ModeloProdutoProdutoDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ModeloProdutoProdutoDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = MarcaDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = MarcaDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = MarcaDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = MarcaDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = MarcaDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = MarcaDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = LojaVirtualDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = LojaVirtualDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = LojaVirtualDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = LojaVirtualDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = LojaVirtualDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = LojaVirtualDbHelper.getSqlCreateSinc();
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
	
	
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = ProdutoDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = PrecoProdutoDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = UsuarioDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = DispositivoUsuarioDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = ProdutoPesquisaDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = ModeloProdutoDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = ModeloProdutoProdutoDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = MarcaDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = LojaVirtualDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
		//SQlite nao permite isso ?
		//UPDATE_SQL = OportunidadeDiaDbHelper.getSqlChavesEstrangeiras();
		//db.execSQL(UPDATE_SQL);
	
	}
	
}