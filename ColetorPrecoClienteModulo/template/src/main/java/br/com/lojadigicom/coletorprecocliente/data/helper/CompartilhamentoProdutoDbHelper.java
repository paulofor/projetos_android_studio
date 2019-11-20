
package  br.com.lojadigicom.coletorprecocliente.data.helper;

import br.com.lojadigicom.coletorprecocliente.data.contract.*;

public class CompartilhamentoProdutoDbHelper {

	protected static String getSqlChavesEstrangeiras() {
		return  "ALTER TABLE " +
        CompartilhamentoProdutoContract.TABLE_NAME + " " +
        ";";
	}

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + CompartilhamentoProdutoContract.TABLE_NAME + " (" +
        CompartilhamentoProdutoContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
        + " , " + CompartilhamentoProdutoContract.COLUNA_ID_PRODUTO_RA + " INTEGER "
        + " , " + CompartilhamentoProdutoContract.COLUNA_DATA_HORA + " INTEGER "
		+ " , " + CompartilhamentoProdutoContract.COLUNA_ID_USUARIO_S + " INTEGER "
		
		//+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + CompartilhamentoProdutoContract.TABLE_NAME_SINC + " (" +
        CompartilhamentoProdutoContract.COLUNA_CHAVE + " INTEGER " 
        + " , " + CompartilhamentoProdutoContract.COLUNA_ID_PRODUTO_RA + " INTEGER "
        + " , " + CompartilhamentoProdutoContract.COLUNA_DATA_HORA + " INTEGER "
		+ " , " + CompartilhamentoProdutoContract.COLUNA_ID_USUARIO_S + " INTEGER "
		
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
		return "DROP TABLE IF EXISTS " + CompartilhamentoProdutoContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + CompartilhamentoProdutoContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + CompartilhamentoProdutoContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + CompartilhamentoProdutoContract.TABLE_NAME_SINC;
    }
    
   
   
    
}