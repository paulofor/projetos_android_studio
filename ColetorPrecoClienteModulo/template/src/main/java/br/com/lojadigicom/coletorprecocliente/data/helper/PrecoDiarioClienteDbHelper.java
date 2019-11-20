
package  br.com.lojadigicom.coletorprecocliente.data.helper;

import br.com.lojadigicom.coletorprecocliente.data.contract.*;

public class PrecoDiarioClienteDbHelper {

	protected static String getSqlChavesEstrangeiras() {
		return  "ALTER TABLE " +
        PrecoDiarioClienteContract.TABLE_NAME + " " +
		" ADD FOREIGN KEY (" + PrecoDiarioClienteContract.COLUNA_ID_PRODUTO_CLIENTE_PA + ") REFERENCES " + ProdutoClienteContract.TABLE_NAME + " (" + ProdutoClienteContract.COLUNA_CHAVE + ")  " + 
        ";";
	}

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + PrecoDiarioClienteContract.TABLE_NAME + " (" +
        PrecoDiarioClienteContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
        + " , " + PrecoDiarioClienteContract.COLUNA_DATA_HORA + " INTEGER "
        + " , " + PrecoDiarioClienteContract.COLUNA_PRECO_VENDA + " REAL "
		+ " , " + PrecoDiarioClienteContract.COLUNA_ID_PRODUTO_CLIENTE_PA + " INTEGER "
		
		+ " , " + PrecoDiarioClienteContract.COLUNA_ID_USUARIO_S + " INTEGER "
		
		//+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + PrecoDiarioClienteContract.TABLE_NAME_SINC + " (" +
        PrecoDiarioClienteContract.COLUNA_CHAVE + " INTEGER " 
        + " , " + PrecoDiarioClienteContract.COLUNA_DATA_HORA + " INTEGER "
        + " , " + PrecoDiarioClienteContract.COLUNA_PRECO_VENDA + " REAL "
		+ " , " + PrecoDiarioClienteContract.COLUNA_ID_PRODUTO_CLIENTE_PA + " INTEGER "
		
		+ " , " + PrecoDiarioClienteContract.COLUNA_ID_USUARIO_S + " INTEGER "
		
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
		saida += " , FOREIGN KEY (" + PrecoDiarioClienteContract.COLUNA_ID_PRODUTO_CLIENTE_PA + ") REFERENCES " + ProdutoClienteContract.TABLE_NAME + " (" + ProdutoClienteContract.COLUNA_CHAVE + ") ";
		return saida;
	}
	
	public static String getSqlDrop() {
		return "DROP TABLE IF EXISTS " + PrecoDiarioClienteContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + PrecoDiarioClienteContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + PrecoDiarioClienteContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + PrecoDiarioClienteContract.TABLE_NAME_SINC;
    }
    
   
   
    
}