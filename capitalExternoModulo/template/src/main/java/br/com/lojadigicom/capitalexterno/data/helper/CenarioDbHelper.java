
package  br.com.lojadigicom.capitalexterno.data.helper;

import br.com.lojadigicom.capitalexterno.data.contract.*;

public class CenarioDbHelper {

	protected static String getSqlChavesEstrangeiras() {
		return  "ALTER TABLE " +
        CenarioContract.TABLE_NAME + " " +
        ";";
	}

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + CenarioContract.TABLE_NAME + " (" +
        CenarioContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
        + " , " + CenarioContract.COLUNA_DESCRICAO_CENARIO + " TEXT "
		//+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + CenarioContract.TABLE_NAME_SINC + " (" +
        CenarioContract.COLUNA_CHAVE + " INTEGER " 
        + " , " + CenarioContract.COLUNA_DESCRICAO_CENARIO + " TEXT "
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
		return "DROP TABLE IF EXISTS " + CenarioContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + CenarioContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + CenarioContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + CenarioContract.TABLE_NAME_SINC;
    }
    
   
   
    
}