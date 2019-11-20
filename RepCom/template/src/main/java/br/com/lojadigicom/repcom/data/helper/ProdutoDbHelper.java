
package  br.com.lojadigicom.repcom.data.helper;

import br.com.lojadigicom.repcom.data.contract.*;

public class ProdutoDbHelper {

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + ProdutoContract.TABLE_NAME + " (" +
        ProdutoContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
        + " , " + ProdutoContract.COLUNA_NOME + " TEXT "
        + " , " + ProdutoContract.COLUNA_URL + " TEXT "
        + " , " + ProdutoContract.COLUNA_IMAGEM + " TEXT "
        + " , " + ProdutoContract.COLUNA_TAMANHO_IMAGEM + " INTEGER "
        + " , " + ProdutoContract.COLUNA_DATA_INCLUSAO + " INTEGER "
        + " , " + ProdutoContract.COLUNA_DATA_EXCLUSAO + " INTEGER "
		+ " , " + ProdutoContract.COLUNA_ID_LINHA_PRODUTO_EE + " INTEGER "
		
		+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + ProdutoContract.TABLE_NAME_SINC + " (" +
        ProdutoContract.COLUNA_CHAVE + " INTEGER " 
        + " , " + ProdutoContract.COLUNA_NOME + " TEXT "
        + " , " + ProdutoContract.COLUNA_URL + " TEXT "
        + " , " + ProdutoContract.COLUNA_IMAGEM + " TEXT "
        + " , " + ProdutoContract.COLUNA_TAMANHO_IMAGEM + " INTEGER "
        + " , " + ProdutoContract.COLUNA_DATA_INCLUSAO + " INTEGER "
        + " , " + ProdutoContract.COLUNA_DATA_EXCLUSAO + " INTEGER "
		+ " , " + ProdutoContract.COLUNA_ID_LINHA_PRODUTO_EE + " INTEGER "
		
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
		saida += " , FOREIGN KEY (" + ProdutoContract.COLUNA_ID_LINHA_PRODUTO_EE + ") REFERENCES " + ProdutoContract.TABLE_NAME + " (" + LinhaProdutoContract.COLUNA_CHAVE + ") ";
		return saida;
	}
	
	public static String getSqlDrop() {
		return "DROP TABLE IF EXISTS " + ProdutoContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + ProdutoContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + ProdutoContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + ProdutoContract.TABLE_NAME_SINC;
    }
    
   
   
    
}