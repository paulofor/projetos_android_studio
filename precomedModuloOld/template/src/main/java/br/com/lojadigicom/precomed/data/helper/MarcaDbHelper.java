
package  br.com.lojadigicom.precomed.data.helper;

import br.com.lojadigicom.precomed.data.contract.*;

public class MarcaDbHelper {

	protected static String getSqlChavesEstrangeiras() {
		return  "ALTER TABLE " +
        MarcaContract.TABLE_NAME + " " +
        ";";
	}

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + MarcaContract.TABLE_NAME + " (" +
        MarcaContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
        + " , " + MarcaContract.COLUNA_NOME_MARCA + " TEXT "
		//+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + MarcaContract.TABLE_NAME_SINC + " (" +
        MarcaContract.COLUNA_CHAVE + " INTEGER " 
        + " , " + MarcaContract.COLUNA_NOME_MARCA + " TEXT "
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
		return "DROP TABLE IF EXISTS " + MarcaContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + MarcaContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + MarcaContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + MarcaContract.TABLE_NAME_SINC;
    }
    
   
   
    
}