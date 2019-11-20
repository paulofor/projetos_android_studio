
package  br.com.lojadigicom.coletorprecocliente.data.helper;

import br.com.lojadigicom.coletorprecocliente.data.contract.*;

public class InteresseProdutoDbHelper {

	protected static String getSqlChavesEstrangeiras() {
		return  "ALTER TABLE " +
        InteresseProdutoContract.TABLE_NAME + " " +
		" ADD FOREIGN KEY (" + InteresseProdutoContract.COLUNA_ID_PRODUTO_CLIENTE_RA + ") REFERENCES " + ProdutoClienteContract.TABLE_NAME + " (" + ProdutoClienteContract.COLUNA_CHAVE + ")  " + 
        ";";
	}

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + InteresseProdutoContract.TABLE_NAME + " (" +
        InteresseProdutoContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
        + " , " + InteresseProdutoContract.COLUNA_NOTA + " INTEGER "
        + " , " + InteresseProdutoContract.COLUNA_DATA + " INTEGER "
        + " , " + InteresseProdutoContract.COLUNA_VALOR + " REAL "
        + " , " + InteresseProdutoContract.COLUNA_ESPERA + " NUMERIC "
        + " , " + InteresseProdutoContract.COLUNA_MONITORA + " NUMERIC "
        + " , " + InteresseProdutoContract.COLUNA_VISUALIZACAO_CONCLUIDA + " NUMERIC "
        + " , " + InteresseProdutoContract.COLUNA_PRECO_MEDIO + " REAL "
        + " , " + InteresseProdutoContract.COLUNA_PRECO_MINIMO + " REAL "
        + " , " + InteresseProdutoContract.COLUNA_NOVO + " NUMERIC "
        + " , " + InteresseProdutoContract.COLUNA_MUDANCA + " NUMERIC "
        + " , " + InteresseProdutoContract.COLUNA_DIFERENCA_ANTERIOR + " REAL "
        + " , " + InteresseProdutoContract.COLUNA_MINHA_AVALIACAO + " INTEGER "
        + " , " + InteresseProdutoContract.COLUNA_DATA_ULTIMA_MUDANCA + " INTEGER "
        + " , " + InteresseProdutoContract.COLUNA_PRECO_ANTERIOR + " REAL "
        + " , " + InteresseProdutoContract.COLUNA_DATA_ULTIMA_VERIFICACAO + " INTEGER "
		+ " , " + InteresseProdutoContract.COLUNA_ID_PRODUTO_CLIENTE_RA + " INTEGER "
		
		+ " , " + InteresseProdutoContract.COLUNA_ID_USUARIO_S + " INTEGER "
		
		//+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + InteresseProdutoContract.TABLE_NAME_SINC + " (" +
        InteresseProdutoContract.COLUNA_CHAVE + " INTEGER " 
        + " , " + InteresseProdutoContract.COLUNA_NOTA + " INTEGER "
        + " , " + InteresseProdutoContract.COLUNA_DATA + " INTEGER "
        + " , " + InteresseProdutoContract.COLUNA_VALOR + " REAL "
        + " , " + InteresseProdutoContract.COLUNA_ESPERA + " NUMERIC "
        + " , " + InteresseProdutoContract.COLUNA_MONITORA + " NUMERIC "
        + " , " + InteresseProdutoContract.COLUNA_VISUALIZACAO_CONCLUIDA + " NUMERIC "
        + " , " + InteresseProdutoContract.COLUNA_PRECO_MEDIO + " REAL "
        + " , " + InteresseProdutoContract.COLUNA_PRECO_MINIMO + " REAL "
        + " , " + InteresseProdutoContract.COLUNA_NOVO + " NUMERIC "
        + " , " + InteresseProdutoContract.COLUNA_MUDANCA + " NUMERIC "
        + " , " + InteresseProdutoContract.COLUNA_DIFERENCA_ANTERIOR + " REAL "
        + " , " + InteresseProdutoContract.COLUNA_MINHA_AVALIACAO + " INTEGER "
        + " , " + InteresseProdutoContract.COLUNA_DATA_ULTIMA_MUDANCA + " INTEGER "
        + " , " + InteresseProdutoContract.COLUNA_PRECO_ANTERIOR + " REAL "
        + " , " + InteresseProdutoContract.COLUNA_DATA_ULTIMA_VERIFICACAO + " INTEGER "
		+ " , " + InteresseProdutoContract.COLUNA_ID_PRODUTO_CLIENTE_RA + " INTEGER "
		
		+ " , " + InteresseProdutoContract.COLUNA_ID_USUARIO_S + " INTEGER "
		
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
		saida += " , FOREIGN KEY (" + InteresseProdutoContract.COLUNA_ID_PRODUTO_CLIENTE_RA + ") REFERENCES " + ProdutoClienteContract.TABLE_NAME + " (" + ProdutoClienteContract.COLUNA_CHAVE + ") ";
		return saida;
	}
	
	public static String getSqlDrop() {
		return "DROP TABLE IF EXISTS " + InteresseProdutoContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + InteresseProdutoContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + InteresseProdutoContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + InteresseProdutoContract.TABLE_NAME_SINC;
    }
    
   
   
    
}