
package  br.com.lojadigicom.repcom.data.helper;

import br.com.lojadigicom.repcom.data.contract.*;

public class CategoriaProdutoDbHelper {

	protected static String getSqlChavesEstrangeiras() {
		return  "ALTER TABLE " +
        CategoriaProdutoContract.TABLE_NAME + " " +
		" ADD FOREIGN KEY (" + CategoriaProdutoContract.COLUNA_ID_CATEGORIA_PRODUTO_P + ") REFERENCES " + CategoriaProdutoContract.TABLE_NAME + " (" + CategoriaProdutoContract.COLUNA_CHAVE + ")  " + 
        ";";
	}

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + CategoriaProdutoContract.TABLE_NAME + " (" +
        CategoriaProdutoContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
        + " , " + CategoriaProdutoContract.COLUNA_NOME + " TEXT "
        + " , " + CategoriaProdutoContract.COLUNA_URL + " TEXT "
        + " , " + CategoriaProdutoContract.COLUNA_CODIGO_LINE_ID + " INTEGER "
        + " , " + CategoriaProdutoContract.COLUNA_DATA_INCLUSAO + " INTEGER "
		+ " , " + CategoriaProdutoContract.COLUNA_ID_CATEGORIA_PRODUTO_P + " INTEGER "
		
		//+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + CategoriaProdutoContract.TABLE_NAME_SINC + " (" +
        CategoriaProdutoContract.COLUNA_CHAVE + " INTEGER " 
        + " , " + CategoriaProdutoContract.COLUNA_NOME + " TEXT "
        + " , " + CategoriaProdutoContract.COLUNA_URL + " TEXT "
        + " , " + CategoriaProdutoContract.COLUNA_CODIGO_LINE_ID + " INTEGER "
        + " , " + CategoriaProdutoContract.COLUNA_DATA_INCLUSAO + " INTEGER "
		+ " , " + CategoriaProdutoContract.COLUNA_ID_CATEGORIA_PRODUTO_P + " INTEGER "
		
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
		saida += " , FOREIGN KEY (" + CategoriaProdutoContract.COLUNA_ID_CATEGORIA_PRODUTO_P + ") REFERENCES " + CategoriaProdutoContract.TABLE_NAME + " (" + CategoriaProdutoContract.COLUNA_CHAVE + ") ";
		return saida;
	}
	
	public static String getSqlDrop() {
		return "DROP TABLE IF EXISTS " + CategoriaProdutoContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + CategoriaProdutoContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + CategoriaProdutoContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + CategoriaProdutoContract.TABLE_NAME_SINC;
    }
    
   
   
    
}