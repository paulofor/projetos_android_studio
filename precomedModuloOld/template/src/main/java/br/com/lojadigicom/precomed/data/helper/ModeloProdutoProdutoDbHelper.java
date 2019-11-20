
package  br.com.lojadigicom.precomed.data.helper;

import br.com.lojadigicom.precomed.data.contract.*;

public class ModeloProdutoProdutoDbHelper {

	protected static String getSqlChavesEstrangeiras() {
		return  "ALTER TABLE " +
        ModeloProdutoProdutoContract.TABLE_NAME + " " +
		" ADD FOREIGN KEY (" + ModeloProdutoProdutoContract.COLUNA_ID_MODELO_PRODUTO_RA + ") REFERENCES " + ModeloProdutoContract.TABLE_NAME + " (" + ModeloProdutoContract.COLUNA_CHAVE + ")  " + 
		" ADD FOREIGN KEY (" + ModeloProdutoProdutoContract.COLUNA_ID_PRODUTO_RA + ") REFERENCES " + ProdutoContract.TABLE_NAME + " (" + ProdutoContract.COLUNA_CHAVE + ")  " + 
        ";";
	}

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + ModeloProdutoProdutoContract.TABLE_NAME + " (" +
        ModeloProdutoProdutoContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
		+ " , " + ModeloProdutoProdutoContract.COLUNA_ID_MODELO_PRODUTO_RA + " INTEGER "
		
		+ " , " + ModeloProdutoProdutoContract.COLUNA_ID_PRODUTO_RA + " INTEGER "
		
		//+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + ModeloProdutoProdutoContract.TABLE_NAME_SINC + " (" +
        ModeloProdutoProdutoContract.COLUNA_CHAVE + " INTEGER " 
		+ " , " + ModeloProdutoProdutoContract.COLUNA_ID_MODELO_PRODUTO_RA + " INTEGER "
		
		+ " , " + ModeloProdutoProdutoContract.COLUNA_ID_PRODUTO_RA + " INTEGER "
		
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
		saida += " , FOREIGN KEY (" + ModeloProdutoProdutoContract.COLUNA_ID_MODELO_PRODUTO_RA + ") REFERENCES " + ModeloProdutoContract.TABLE_NAME + " (" + ModeloProdutoContract.COLUNA_CHAVE + ") ";
		saida += " , FOREIGN KEY (" + ModeloProdutoProdutoContract.COLUNA_ID_PRODUTO_RA + ") REFERENCES " + ProdutoContract.TABLE_NAME + " (" + ProdutoContract.COLUNA_CHAVE + ") ";
		return saida;
	}
	
	public static String getSqlDrop() {
		return "DROP TABLE IF EXISTS " + ModeloProdutoProdutoContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + ModeloProdutoProdutoContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + ModeloProdutoProdutoContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + ModeloProdutoProdutoContract.TABLE_NAME_SINC;
    }
    
   
   
    
}