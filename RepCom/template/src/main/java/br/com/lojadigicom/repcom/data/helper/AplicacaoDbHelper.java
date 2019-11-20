
package  br.com.lojadigicom.repcom.data.helper;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.lojadigicom.repcom.data.DatabaseConst;
import br.com.lojadigicom.repcom.framework.log.DCLog;

public class AplicacaoDbHelper extends SQLiteOpenHelper{

	/*
	private static final int DATABASE_VERSION = 1;
	public static String DATABASE_NAME = "repcom.db";
	*/
	//public static String DATABASE_NAME = "/mnt/extSdCard/digidata/repcom.db";

	private static DatabaseConst databaseConst = null;

	public static void setDatabaseConst(DatabaseConst dbConst) {
		databaseConst = dbConst;
	}


	public AplicacaoDbHelper(Context context) {
		//super(context, DATABASE_NAME , null, DATABASE_VERSION);
		super(context,databaseConst.getName(),null,databaseConst.getVersion());
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		DCLog.d(DCLog.DATABASE_ADM,this,"Create database em " + databaseConst.getName());
	
		String CREATE_SQL = null;
	
		CREATE_SQL = ClienteDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = ClienteDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = ProdutoDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = ProdutoDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = CategoriaProdutoDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = CategoriaProdutoDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = PedidoFornecedorDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = PedidoFornecedorDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = VendaDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = VendaDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = ContatoClienteDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = ContatoClienteDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = LinhaProdutoDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = LinhaProdutoDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = ProdutoPedidoFornecedorDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = ProdutoPedidoFornecedorDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = ProdutoVendaDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = ProdutoVendaDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = PagamentoFornecedorDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = PagamentoFornecedorDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = ParcelaVendaDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = ParcelaVendaDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = ClienteInteresseCategoriaDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = ClienteInteresseCategoriaDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = EstoqueDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = EstoqueDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = UsuarioDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = UsuarioDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = PrecoProdutoDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = PrecoProdutoDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = CategoriaProdutoProdutoDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = CategoriaProdutoProdutoDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = MesAnoDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = MesAnoDbHelper.getSqlCreateSinc();
		db.execSQL(CREATE_SQL);
	
		CREATE_SQL = DispositivoUsuarioDbHelper.getSqlCreate();
		db.execSQL(CREATE_SQL);
		CREATE_SQL = DispositivoUsuarioDbHelper.getSqlCreateSinc();
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
	
		//UPDATE_SQL = ClienteDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = ClienteDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ClienteDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ClienteDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ClienteDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ClienteDbHelper.getSqlCreateSinc();
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
	
		//UPDATE_SQL = CategoriaProdutoDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = CategoriaProdutoDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = CategoriaProdutoDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = CategoriaProdutoDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = CategoriaProdutoDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = CategoriaProdutoDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = PedidoFornecedorDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = PedidoFornecedorDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = PedidoFornecedorDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = PedidoFornecedorDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = PedidoFornecedorDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = PedidoFornecedorDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = VendaDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = VendaDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = VendaDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = VendaDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = VendaDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = VendaDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = ContatoClienteDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = ContatoClienteDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ContatoClienteDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ContatoClienteDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ContatoClienteDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ContatoClienteDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
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
	
		//UPDATE_SQL = ProdutoPedidoFornecedorDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = ProdutoPedidoFornecedorDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ProdutoPedidoFornecedorDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ProdutoPedidoFornecedorDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ProdutoPedidoFornecedorDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ProdutoPedidoFornecedorDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = ProdutoVendaDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = ProdutoVendaDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ProdutoVendaDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ProdutoVendaDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ProdutoVendaDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ProdutoVendaDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = PagamentoFornecedorDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = PagamentoFornecedorDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = PagamentoFornecedorDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = PagamentoFornecedorDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = PagamentoFornecedorDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = PagamentoFornecedorDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = ParcelaVendaDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = ParcelaVendaDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ParcelaVendaDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ParcelaVendaDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ParcelaVendaDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ParcelaVendaDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = ClienteInteresseCategoriaDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = ClienteInteresseCategoriaDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ClienteInteresseCategoriaDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ClienteInteresseCategoriaDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ClienteInteresseCategoriaDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = ClienteInteresseCategoriaDbHelper.getSqlCreateSinc();
		db.execSQL(UPDATE_SQL);
	
		//UPDATE_SQL = EstoqueDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = EstoqueDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = EstoqueDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = EstoqueDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = EstoqueDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = EstoqueDbHelper.getSqlCreateSinc();
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
	
		//UPDATE_SQL = CategoriaProdutoProdutoDbHelper.onUpgrade(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		//UPDATE_SQL = CategoriaProdutoProdutoDbHelper.onUpgradeSinc(oldVersion,newVersion);
		//db.execSQL(UPDATE_SQL);
		UPDATE_SQL = CategoriaProdutoProdutoDbHelper.getSqlDrop();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = CategoriaProdutoProdutoDbHelper.getSqlCreate();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = CategoriaProdutoProdutoDbHelper.getSqlDropSinc();
		db.execSQL(UPDATE_SQL);
		UPDATE_SQL = CategoriaProdutoProdutoDbHelper.getSqlCreateSinc();
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
	
	}
	
}