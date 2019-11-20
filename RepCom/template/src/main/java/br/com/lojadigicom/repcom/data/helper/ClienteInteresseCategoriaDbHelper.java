
package  br.com.lojadigicom.repcom.data.helper;

import br.com.lojadigicom.repcom.data.contract.*;

public class ClienteInteresseCategoriaDbHelper {

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + ClienteInteresseCategoriaContract.TABLE_NAME + " (" +
        ClienteInteresseCategoriaContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
		+ " , " + ClienteInteresseCategoriaContract.COLUNA_ID_CLIENTE_A + " INTEGER "
		
		+ " , " + ClienteInteresseCategoriaContract.COLUNA_ID_CATEGORIA_PRODUTO_A + " INTEGER "
		
		+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + ClienteInteresseCategoriaContract.TABLE_NAME_SINC + " (" +
        ClienteInteresseCategoriaContract.COLUNA_CHAVE + " INTEGER " 
		+ " , " + ClienteInteresseCategoriaContract.COLUNA_ID_CLIENTE_A + " INTEGER "
		
		+ " , " + ClienteInteresseCategoriaContract.COLUNA_ID_CATEGORIA_PRODUTO_A + " INTEGER "
		
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
		saida += " , FOREIGN KEY (" + ClienteInteresseCategoriaContract.COLUNA_ID_CLIENTE_A + ") REFERENCES " + ClienteInteresseCategoriaContract.TABLE_NAME + " (" + ClienteContract.COLUNA_CHAVE + ") ";
		saida += " , FOREIGN KEY (" + ClienteInteresseCategoriaContract.COLUNA_ID_CATEGORIA_PRODUTO_A + ") REFERENCES " + ClienteInteresseCategoriaContract.TABLE_NAME + " (" + CategoriaProdutoContract.COLUNA_CHAVE + ") ";
		return saida;
	}
	
	public static String getSqlDrop() {
		return "DROP TABLE IF EXISTS " + ClienteInteresseCategoriaContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + ClienteInteresseCategoriaContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + ClienteInteresseCategoriaContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + ClienteInteresseCategoriaContract.TABLE_NAME_SINC;
    }
    
   
   
    
}