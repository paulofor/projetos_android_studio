
package  br.com.lojadigicom.capitalexterno.data.helper;

import br.com.lojadigicom.capitalexterno.data.contract.*;

public class NegocioDbHelper {

	protected static String getSqlChavesEstrangeiras() {
		return  "ALTER TABLE " +
        NegocioContract.TABLE_NAME + " " +
        ";";
	}

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + NegocioContract.TABLE_NAME + " (" +
        NegocioContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
        + " , " + NegocioContract.COLUNA_DESCRICAO + " TEXT "
        + " , " + NegocioContract.COLUNA_DATA_CRIACAO + " INTEGER "
		//+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + NegocioContract.TABLE_NAME_SINC + " (" +
        NegocioContract.COLUNA_CHAVE + " INTEGER " 
        + " , " + NegocioContract.COLUNA_DESCRICAO + " TEXT "
        + " , " + NegocioContract.COLUNA_DATA_CRIACAO + " INTEGER "
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
		return "DROP TABLE IF EXISTS " + NegocioContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + NegocioContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + NegocioContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + NegocioContract.TABLE_NAME_SINC;
    }
    
   
   
    
}