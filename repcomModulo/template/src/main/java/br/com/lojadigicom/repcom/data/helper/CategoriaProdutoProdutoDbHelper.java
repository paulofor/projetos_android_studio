
package  br.com.lojadigicom.repcom.data.helper;

import br.com.lojadigicom.repcom.data.contract.*;

public class CategoriaProdutoProdutoDbHelper {

	protected static String getSqlChavesEstrangeiras() {
		return  "ALTER TABLE " +
        CategoriaProdutoProdutoContract.TABLE_NAME + " " +
		" ADD FOREIGN KEY (" + CategoriaProdutoProdutoContract.COLUNA_ID_CATEGORIA_PRODUTO_RA + ") REFERENCES " + CategoriaProdutoContract.TABLE_NAME + " (" + CategoriaProdutoContract.COLUNA_CHAVE + ")  " + 
		" ADD FOREIGN KEY (" + CategoriaProdutoProdutoContract.COLUNA_ID_PRODUTO_RA + ") REFERENCES " + ProdutoContract.TABLE_NAME + " (" + ProdutoContract.COLUNA_CHAVE + ")  " + 
        ";";
	}

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + CategoriaProdutoProdutoContract.TABLE_NAME + " (" +
        CategoriaProdutoProdutoContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
        + " , " + CategoriaProdutoProdutoContract.COLUNA_DATA_INCLUSAO + " INTEGER "
		+ " , " + CategoriaProdutoProdutoContract.COLUNA_ID_CATEGORIA_PRODUTO_RA + " INTEGER "
		
		+ " , " + CategoriaProdutoProdutoContract.COLUNA_ID_PRODUTO_RA + " INTEGER "
		
		//+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + CategoriaProdutoProdutoContract.TABLE_NAME_SINC + " (" +
        CategoriaProdutoProdutoContract.COLUNA_CHAVE + " INTEGER " 
        + " , " + CategoriaProdutoProdutoContract.COLUNA_DATA_INCLUSAO + " INTEGER "
		+ " , " + CategoriaProdutoProdutoContract.COLUNA_ID_CATEGORIA_PRODUTO_RA + " INTEGER "
		
		+ " , " + CategoriaProdutoProdutoContract.COLUNA_ID_PRODUTO_RA + " INTEGER "
		
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
		saida += " , FOREIGN KEY (" + CategoriaProdutoProdutoContract.COLUNA_ID_CATEGORIA_PRODUTO_RA + ") REFERENCES " + CategoriaProdutoContract.TABLE_NAME + " (" + CategoriaProdutoContract.COLUNA_CHAVE + ") ";
		saida += " , FOREIGN KEY (" + CategoriaProdutoProdutoContract.COLUNA_ID_PRODUTO_RA + ") REFERENCES " + ProdutoContract.TABLE_NAME + " (" + ProdutoContract.COLUNA_CHAVE + ") ";
		return saida;
	}
	
	public static String getSqlDrop() {
		return "DROP TABLE IF EXISTS " + CategoriaProdutoProdutoContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + CategoriaProdutoProdutoContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + CategoriaProdutoProdutoContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + CategoriaProdutoProdutoContract.TABLE_NAME_SINC;
    }
    
   
   
    
}