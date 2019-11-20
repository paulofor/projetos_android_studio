
package  br.com.lojadigicom.repcom.data.helper;

import br.com.lojadigicom.repcom.data.contract.*;

public class ProdutoVendaDbHelper {

	protected static String getSqlChavesEstrangeiras() {
		return  "ALTER TABLE " +
        ProdutoVendaContract.TABLE_NAME + " " +
		" ADD FOREIGN KEY (" + ProdutoVendaContract.COLUNA_ID_PRODUTO_A + ") REFERENCES " + ProdutoContract.TABLE_NAME + " (" + ProdutoContract.COLUNA_CHAVE + ")  " + 
		" ADD FOREIGN KEY (" + ProdutoVendaContract.COLUNA_ID_VENDA_A + ") REFERENCES " + VendaContract.TABLE_NAME + " (" + VendaContract.COLUNA_CHAVE + ")  " + 
        ";";
	}

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + ProdutoVendaContract.TABLE_NAME + " (" +
        ProdutoVendaContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
        + " , " + ProdutoVendaContract.COLUNA_QUANTIDADE + " INTEGER "
        + " , " + ProdutoVendaContract.COLUNA_VALOR_TOTAL + " REAL "
        + " , " + ProdutoVendaContract.COLUNA_VALOR_ITEM + " REAL "
		+ " , " + ProdutoVendaContract.COLUNA_ID_PRODUTO_A + " INTEGER "
		
		+ " , " + ProdutoVendaContract.COLUNA_ID_VENDA_A + " INTEGER "
		
		//+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + ProdutoVendaContract.TABLE_NAME_SINC + " (" +
        ProdutoVendaContract.COLUNA_CHAVE + " INTEGER " 
        + " , " + ProdutoVendaContract.COLUNA_QUANTIDADE + " INTEGER "
        + " , " + ProdutoVendaContract.COLUNA_VALOR_TOTAL + " REAL "
        + " , " + ProdutoVendaContract.COLUNA_VALOR_ITEM + " REAL "
		+ " , " + ProdutoVendaContract.COLUNA_ID_PRODUTO_A + " INTEGER "
		
		+ " , " + ProdutoVendaContract.COLUNA_ID_VENDA_A + " INTEGER "
		
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
		saida += " , FOREIGN KEY (" + ProdutoVendaContract.COLUNA_ID_PRODUTO_A + ") REFERENCES " + ProdutoContract.TABLE_NAME + " (" + ProdutoContract.COLUNA_CHAVE + ") ";
		saida += " , FOREIGN KEY (" + ProdutoVendaContract.COLUNA_ID_VENDA_A + ") REFERENCES " + VendaContract.TABLE_NAME + " (" + VendaContract.COLUNA_CHAVE + ") ";
		return saida;
	}
	
	public static String getSqlDrop() {
		return "DROP TABLE IF EXISTS " + ProdutoVendaContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + ProdutoVendaContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + ProdutoVendaContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + ProdutoVendaContract.TABLE_NAME_SINC;
    }
    
   
   
    
}