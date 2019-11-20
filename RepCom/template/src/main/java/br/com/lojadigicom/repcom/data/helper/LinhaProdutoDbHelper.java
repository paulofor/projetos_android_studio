
package  br.com.lojadigicom.repcom.data.helper;

import br.com.lojadigicom.repcom.data.contract.*;

public class LinhaProdutoDbHelper {

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + LinhaProdutoContract.TABLE_NAME + " (" +
        LinhaProdutoContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
        + " , " + LinhaProdutoContract.COLUNA_NOME + " TEXT "
        + " , " + LinhaProdutoContract.COLUNA_URL + " TEXT "
        + " , " + LinhaProdutoContract.COLUNA_CODIGO_LINE_ID + " INTEGER "
        + " , " + LinhaProdutoContract.COLUNA_DATA_INCLUSAO + " INTEGER "
		+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + LinhaProdutoContract.TABLE_NAME_SINC + " (" +
        LinhaProdutoContract.COLUNA_CHAVE + " INTEGER " 
        + " , " + LinhaProdutoContract.COLUNA_NOME + " TEXT "
        + " , " + LinhaProdutoContract.COLUNA_URL + " TEXT "
        + " , " + LinhaProdutoContract.COLUNA_CODIGO_LINE_ID + " INTEGER "
        + " , " + LinhaProdutoContract.COLUNA_DATA_INCLUSAO + " INTEGER "
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
		return "DROP TABLE IF EXISTS " + LinhaProdutoContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + LinhaProdutoContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + LinhaProdutoContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + LinhaProdutoContract.TABLE_NAME_SINC;
    }
    
   
   
    
}