
package  br.com.lojadigicom.capitalexterno.data.helper;

import br.com.lojadigicom.capitalexterno.data.contract.*;

public class ItemCustoProdutoDbHelper {

	protected static String getSqlChavesEstrangeiras() {
		return  "ALTER TABLE " +
        ItemCustoProdutoContract.TABLE_NAME + " " +
        ";";
	}

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + ItemCustoProdutoContract.TABLE_NAME + " (" +
        ItemCustoProdutoContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
        + " , " + ItemCustoProdutoContract.COLUNA_VALOR + " REAL "
        + " , " + ItemCustoProdutoContract.COLUNA_DESCRICAO + " TEXT "
		//+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + ItemCustoProdutoContract.TABLE_NAME_SINC + " (" +
        ItemCustoProdutoContract.COLUNA_CHAVE + " INTEGER " 
        + " , " + ItemCustoProdutoContract.COLUNA_VALOR + " REAL "
        + " , " + ItemCustoProdutoContract.COLUNA_DESCRICAO + " TEXT "
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
		return "DROP TABLE IF EXISTS " + ItemCustoProdutoContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + ItemCustoProdutoContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + ItemCustoProdutoContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + ItemCustoProdutoContract.TABLE_NAME_SINC;
    }
    
   
   
    
}