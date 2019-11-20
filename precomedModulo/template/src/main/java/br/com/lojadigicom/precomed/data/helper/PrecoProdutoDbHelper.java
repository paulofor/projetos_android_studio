
package  br.com.lojadigicom.precomed.data.helper;

import br.com.lojadigicom.precomed.data.contract.*;

public class PrecoProdutoDbHelper {

	protected static String getSqlChavesEstrangeiras() {
		return  "ALTER TABLE " +
        PrecoProdutoContract.TABLE_NAME + " " +
		" ADD FOREIGN KEY (" + PrecoProdutoContract.COLUNA_ID_PRODUTO_PA + ") REFERENCES " + ProdutoContract.TABLE_NAME + " (" + ProdutoContract.COLUNA_CHAVE + ")  " + 
        ";";
	}

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + PrecoProdutoContract.TABLE_NAME + " (" +
        PrecoProdutoContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
        + " , " + PrecoProdutoContract.COLUNA_PRECO_VENDA + " REAL "
        + " , " + PrecoProdutoContract.COLUNA_DATA_VISITA_INICIAL + " INTEGER "
        + " , " + PrecoProdutoContract.COLUNA_QUANTIDADE_PARCELA + " INTEGER "
        + " , " + PrecoProdutoContract.COLUNA_PRECO_PARCELA + " REAL "
        + " , " + PrecoProdutoContract.COLUNA_PRECO_BOLETO + " REAL "
        + " , " + PrecoProdutoContract.COLUNA_PRECO_REGULAR + " REAL "
        + " , " + PrecoProdutoContract.COLUNA_DATA_ULTIMA_VISITA + " INTEGER "
        + " , " + PrecoProdutoContract.COLUNA_PERCENTUAL_AJUSTE + " REAL "
        + " , " + PrecoProdutoContract.COLUNA_PRECO_QUANTIDADE_DESCONTO + " REAL "
        + " , " + PrecoProdutoContract.COLUNA_QUANTIDADE_DESCONTO + " INTEGER "
		+ " , " + PrecoProdutoContract.COLUNA_ID_PRODUTO_PA + " INTEGER "
		
		//+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + PrecoProdutoContract.TABLE_NAME_SINC + " (" +
        PrecoProdutoContract.COLUNA_CHAVE + " INTEGER " 
        + " , " + PrecoProdutoContract.COLUNA_PRECO_VENDA + " REAL "
        + " , " + PrecoProdutoContract.COLUNA_DATA_VISITA_INICIAL + " INTEGER "
        + " , " + PrecoProdutoContract.COLUNA_QUANTIDADE_PARCELA + " INTEGER "
        + " , " + PrecoProdutoContract.COLUNA_PRECO_PARCELA + " REAL "
        + " , " + PrecoProdutoContract.COLUNA_PRECO_BOLETO + " REAL "
        + " , " + PrecoProdutoContract.COLUNA_PRECO_REGULAR + " REAL "
        + " , " + PrecoProdutoContract.COLUNA_DATA_ULTIMA_VISITA + " INTEGER "
        + " , " + PrecoProdutoContract.COLUNA_PERCENTUAL_AJUSTE + " REAL "
        + " , " + PrecoProdutoContract.COLUNA_PRECO_QUANTIDADE_DESCONTO + " REAL "
        + " , " + PrecoProdutoContract.COLUNA_QUANTIDADE_DESCONTO + " INTEGER "
		+ " , " + PrecoProdutoContract.COLUNA_ID_PRODUTO_PA + " INTEGER "
		
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
		saida += " , FOREIGN KEY (" + PrecoProdutoContract.COLUNA_ID_PRODUTO_PA + ") REFERENCES " + ProdutoContract.TABLE_NAME + " (" + ProdutoContract.COLUNA_CHAVE + ") ";
		return saida;
	}
	
	public static String getSqlDrop() {
		return "DROP TABLE IF EXISTS " + PrecoProdutoContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + PrecoProdutoContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + PrecoProdutoContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + PrecoProdutoContract.TABLE_NAME_SINC;
    }
    
   
   
    
}