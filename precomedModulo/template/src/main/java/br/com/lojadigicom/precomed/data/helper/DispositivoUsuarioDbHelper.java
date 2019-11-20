
package  br.com.lojadigicom.precomed.data.helper;

import br.com.lojadigicom.precomed.data.contract.*;

public class DispositivoUsuarioDbHelper {

	protected static String getSqlChavesEstrangeiras() {
		return  "ALTER TABLE " +
        DispositivoUsuarioContract.TABLE_NAME + " " +
		" ADD FOREIGN KEY (" + DispositivoUsuarioContract.COLUNA_ID_USUARIO_RA + ") REFERENCES " + UsuarioContract.TABLE_NAME + " (" + UsuarioContract.COLUNA_CHAVE + ")  " + 
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
		+ " , " + DispositivoUsuarioContract.COLUNA_ID_USUARIO_RA + " INTEGER "
		
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
		+ " , " + DispositivoUsuarioContract.COLUNA_ID_USUARIO_RA + " INTEGER "
		
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