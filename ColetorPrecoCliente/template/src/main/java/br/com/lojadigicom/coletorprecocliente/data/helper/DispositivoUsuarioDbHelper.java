
package  br.com.lojadigicom.coletorprecocliente.data.helper;

import br.com.lojadigicom.coletorprecocliente.data.contract.*;

public class DispositivoUsuarioDbHelper {

	protected static String getSqlChavesEstrangeiras() {
		return  "ALTER TABLE " +
        DispositivoUsuarioContract.TABLE_NAME + " " +
		" ADD FOREIGN KEY (" + DispositivoUsuarioContract.COLUNA_ID_USUARIO_RA + ") REFERENCES " + UsuarioContract.TABLE_NAME + " (" + UsuarioContract.COLUNA_CHAVE + ")  " + 
		" ADD FOREIGN KEY (" + DispositivoUsuarioContract.COLUNA_ID_APP_PRODUTO_U + ") REFERENCES " + AppProdutoContract.TABLE_NAME + " (" + AppProdutoContract.COLUNA_CHAVE + ")  " + 
        ";";
	}

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + DispositivoUsuarioContract.TABLE_NAME + " (" +
        DispositivoUsuarioContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
        + " , " + DispositivoUsuarioContract.COLUNA_NUMERO_CELULAR + " TEXT "
        + " , " + DispositivoUsuarioContract.COLUNA_CODIGO_DISPOSITIVO + " TEXT "
        + " , " + DispositivoUsuarioContract.COLUNA_TIPO_ACESSO + " TEXT "
        + " , " + DispositivoUsuarioContract.COLUNA_MELHOR_PATH + " TEXT "
        + " , " + DispositivoUsuarioContract.COLUNA_TOKEN_GCM + " TEXT "
        + " , " + DispositivoUsuarioContract.COLUNA_MICRO_SD + " INTEGER "
        + " , " + DispositivoUsuarioContract.COLUNA_DATA_CRIACAO + " INTEGER "
        + " , " + DispositivoUsuarioContract.COLUNA_DATA_ULTIMO_ACESSO + " INTEGER "
		+ " , " + DispositivoUsuarioContract.COLUNA_ID_USUARIO_RA + " INTEGER "
		
		+ " , " + DispositivoUsuarioContract.COLUNA_ID_APP_PRODUTO_U + " INTEGER "
		
		//+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + DispositivoUsuarioContract.TABLE_NAME_SINC + " (" +
        DispositivoUsuarioContract.COLUNA_CHAVE + " INTEGER " 
        + " , " + DispositivoUsuarioContract.COLUNA_NUMERO_CELULAR + " TEXT "
        + " , " + DispositivoUsuarioContract.COLUNA_CODIGO_DISPOSITIVO + " TEXT "
        + " , " + DispositivoUsuarioContract.COLUNA_TIPO_ACESSO + " TEXT "
        + " , " + DispositivoUsuarioContract.COLUNA_MELHOR_PATH + " TEXT "
        + " , " + DispositivoUsuarioContract.COLUNA_TOKEN_GCM + " TEXT "
        + " , " + DispositivoUsuarioContract.COLUNA_MICRO_SD + " INTEGER "
        + " , " + DispositivoUsuarioContract.COLUNA_DATA_CRIACAO + " INTEGER "
        + " , " + DispositivoUsuarioContract.COLUNA_DATA_ULTIMO_ACESSO + " INTEGER "
		+ " , " + DispositivoUsuarioContract.COLUNA_ID_USUARIO_RA + " INTEGER "
		
		+ " , " + DispositivoUsuarioContract.COLUNA_ID_APP_PRODUTO_U + " INTEGER "
		
        + ", operacao_sinc TEXT);";
	}
	
	
	private static String getSqlIndices() {
		return "";
	}
	
	private static String getSqlProcValor() {
		String saida = "";
		
		return saida;
	}
	
	
	private static String getSqlChaveEstrangeira() {
		String saida = "";
		saida += " , FOREIGN KEY (" + DispositivoUsuarioContract.COLUNA_ID_USUARIO_RA + ") REFERENCES " + UsuarioContract.TABLE_NAME + " (" + UsuarioContract.COLUNA_CHAVE + ") ";
		saida += " , FOREIGN KEY (" + DispositivoUsuarioContract.COLUNA_ID_APP_PRODUTO_U + ") REFERENCES " + AppProdutoContract.TABLE_NAME + " (" + AppProdutoContract.COLUNA_CHAVE + ") ";
		return saida;
	}
	
	public static String getSqlDrop() {
		return "DROP TABLE IF EXISTS " + DispositivoUsuarioContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + DispositivoUsuarioContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + DispositivoUsuarioContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + DispositivoUsuarioContract.TABLE_NAME_SINC;
    }
    
   
   
    
}