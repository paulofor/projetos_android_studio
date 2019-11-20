
package  br.com.lojadigicom.capitalexterno.data.helper;

import br.com.lojadigicom.capitalexterno.data.contract.*;

public class PrevisaoVendaDbHelper {

	protected static String getSqlChavesEstrangeiras() {
		return  "ALTER TABLE " +
        PrevisaoVendaContract.TABLE_NAME + " " +
        ";";
	}

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + PrevisaoVendaContract.TABLE_NAME + " (" +
        PrevisaoVendaContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
		//+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + PrevisaoVendaContract.TABLE_NAME_SINC + " (" +
        PrevisaoVendaContract.COLUNA_CHAVE + " INTEGER " 
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
		return "DROP TABLE IF EXISTS " + PrevisaoVendaContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + PrevisaoVendaContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + PrevisaoVendaContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + PrevisaoVendaContract.TABLE_NAME_SINC;
    }
    
   
   
    
}