
package  br.com.lojadigicom.repcom.data.helper;

import br.com.lojadigicom.repcom.data.contract.*;

public class ProdutoPedidoFornecedorDbHelper {

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + ProdutoPedidoFornecedorContract.TABLE_NAME + " (" +
        ProdutoPedidoFornecedorContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
		+ " , " + ProdutoPedidoFornecedorContract.COLUNA_ID_PEDIDO_FORNECEDOR_A + " INTEGER "
		
		+ " , " + ProdutoPedidoFornecedorContract.COLUNA_ID_PRODUTO_A + " INTEGER "
		
		+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + ProdutoPedidoFornecedorContract.TABLE_NAME_SINC + " (" +
        ProdutoPedidoFornecedorContract.COLUNA_CHAVE + " INTEGER " 
		+ " , " + ProdutoPedidoFornecedorContract.COLUNA_ID_PEDIDO_FORNECEDOR_A + " INTEGER "
		
		+ " , " + ProdutoPedidoFornecedorContract.COLUNA_ID_PRODUTO_A + " INTEGER "
		
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
		saida += " , FOREIGN KEY (" + ProdutoPedidoFornecedorContract.COLUNA_ID_PEDIDO_FORNECEDOR_A + ") REFERENCES " + ProdutoPedidoFornecedorContract.TABLE_NAME + " (" + PedidoFornecedorContract.COLUNA_CHAVE + ") ";
		saida += " , FOREIGN KEY (" + ProdutoPedidoFornecedorContract.COLUNA_ID_PRODUTO_A + ") REFERENCES " + ProdutoPedidoFornecedorContract.TABLE_NAME + " (" + ProdutoContract.COLUNA_CHAVE + ") ";
		return saida;
	}
	
	public static String getSqlDrop() {
		return "DROP TABLE IF EXISTS " + ProdutoPedidoFornecedorContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + ProdutoPedidoFornecedorContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + ProdutoPedidoFornecedorContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + ProdutoPedidoFornecedorContract.TABLE_NAME_SINC;
    }
    
   
   
    
}