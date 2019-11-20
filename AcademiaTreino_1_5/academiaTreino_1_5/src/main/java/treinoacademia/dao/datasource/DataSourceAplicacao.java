package treinoacademia.dao.datasource;

import treinoacademia.modelo.Usuario;
import treinoacademia.modelo.vo.FabricaVo;
import treinoacademia.servico.FabricaServico;
import treinoacademia.servico.UsuarioServico;


import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import br.com.digicom.dao.DataSource;
import br.com.digicom.storage.StorageUtil;

public class DataSourceAplicacao extends DataSource {

	public final static String PASTA_TRABALHO = "digidata";
	private String TAG = "DataSource";
	// COLOCAR !!!
	// <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
	
	//public static final String DB_NAME = Environment.getExternalStorageDirectory() + "/digidata/treinoletra.db";

	// Pattern Singleton
	private static DataSourceAplicacao instancia = null;
	
    // Objetos de BD
	private DBOpenHelper dbOpenHelper; // Classe interna
	
		
	/*
	public void inicializaPath() {
		UsuarioServico usuarioSrv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
		Usuario usuario = usuarioSrv.ObtemReferenciaAndroid(context);
		if (usuario==null) {
			usuario = FabricaVo.criaUsuario();
		}
		if (usuario.getMelhorPath()==null || (usuario.getMelhorPath().compareTo(usuarioPath)!=0)) {
			usuario.setMelhorPath(usuarioPath);
			usuarioSrv.alteraParaSincronizacao(usuario);
		}
	}
	*/
	
	public String getPathPadrao() {
		return usuarioPath;
	}

	public String getDBName() {
		return usuarioPath + "/" + this.PASTA_TRABALHO + "/treinoacademia.db";
	}
	
	public int getDbVersao() {
		return 1;
	}
	
	
	
	public void inicializa() {
		recuperaValorPath();
		if (usuarioPath==null) {
			Log.d(TAG, "Cria path storage novo");
			usuarioPath = StorageUtil.getPathMaisEspaco();
			salvaValorPath();
		} else {
			Log.d(TAG, "Mantem path storage");
		}
		dbOpenHelper = new DataSourceAplicacao.DBOpenHelper(context);
		Log.d(TAG , "Database:" + getDBName());
	}
	
	
	public static void criaInstanciaTeste(Context ctx) {
		context = ctx;
		instancia = new DataSourceAplicacaoTeste();
		instancia.inicializa();
	}
	
	public static void criaInstancia(Context ctx) {
		context = ctx;
		instancia = new DataSourceAplicacao();
		instancia.inicializa();
	}
	public static DataSource getInstancia() {
		return instancia;
	}
	
	// ??? Quando abre e quando fecha ????
	public static void fechaBanco() {
		instancia.getDb().close();
	}
	
	
	
	public SQLiteDatabase getDb() {
		if (db==null) {
			db = dbOpenHelper.getWritableDatabase();
		}
		return db;
	}

	
	
	

    private static class DBOpenHelper extends SQLiteOpenHelper {

       

        public DBOpenHelper(final Context context) {
            super(context, instancia.getDBName(), null, instancia.getDbVersao());
        }


		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
		
		}

       

     
    }

   
}
