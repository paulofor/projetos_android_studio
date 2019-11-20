
package  br.com.lojadigicom.precomed.data.helper;

import br.com.lojadigicom.precomed.data.contract.*;

public class LojaVirtualDbHelper {

	protected static String getSqlChavesEstrangeiras() {
		return  "ALTER TABLE " +
        LojaVirtualContract.TABLE_NAME + " " +
        ";";
	}

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + LojaVirtualContract.TABLE_NAME + " (" +
        LojaVirtualContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
        + " , " + LojaVirtualContract.COLUNA_NOME_LOJA_VIRTUAL + " TEXT "
        + " , " + LojaVirtualContract.COLUNA_URL_PRINCIPAL + " TEXT "
		//+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + LojaVirtualContract.TABLE_NAME_SINC + " (" +
        LojaVirtualContract.COLUNA_CHAVE + " INTEGER " 
        + " , " + LojaVirtualContract.COLUNA_NOME_LOJA_VIRTUAL + " TEXT "
        + " , " + LojaVirtualContract.COLUNA_URL_PRINCIPAL + " TEXT "
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
		return "DROP TABLE IF EXISTS " + LojaVirtualContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + LojaVirtualContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + LojaVirtualContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + LojaVirtualContract.TABLE_NAME_SINC;
    }
    
   
   
    
}