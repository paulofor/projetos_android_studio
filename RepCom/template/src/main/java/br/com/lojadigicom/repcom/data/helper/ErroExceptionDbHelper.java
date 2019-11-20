
package  br.com.lojadigicom.repcom.data.helper;

import br.com.lojadigicom.repcom.data.contract.*;

public class ErroExceptionDbHelper {

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + ErroExceptionContract.TABLE_NAME + " (" +
        ErroExceptionContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
        + " , " + ErroExceptionContract.COLUNA_STACK + " TEXT "
        + " , " + ErroExceptionContract.COLUNA_APLICACAO + " TEXT "
		+ " , " + ErroExceptionContract.COLUNA_ID_USUARIO_S + " INTEGER "
		
		+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + ErroExceptionContract.TABLE_NAME_SINC + " (" +
        ErroExceptionContract.COLUNA_CHAVE + " INTEGER " 
        + " , " + ErroExceptionContract.COLUNA_STACK + " TEXT "
        + " , " + ErroExceptionContract.COLUNA_APLICACAO + " TEXT "
		+ " , " + ErroExceptionContract.COLUNA_ID_USUARIO_S + " INTEGER "
		
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
		return saida;
	}
	
	public static String getSqlDrop() {
		return "DROP TABLE IF EXISTS " + ErroExceptionContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + ErroExceptionContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + ErroExceptionContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + ErroExceptionContract.TABLE_NAME_SINC;
    }
    
   
   
    
}