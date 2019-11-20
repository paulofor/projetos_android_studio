
package  br.com.lojadigicom.coletorprecocliente.data.helper;

import br.com.lojadigicom.coletorprecocliente.data.contract.*;

public class ProdutoClienteDbHelper {

	protected static String getSqlChavesEstrangeiras() {
		return  "ALTER TABLE " +
        ProdutoClienteContract.TABLE_NAME + " " +
		" ADD FOREIGN KEY (" + ProdutoClienteContract.COLUNA_ID_NATUREZA_PRODUTO_RA + ") REFERENCES " + NaturezaProdutoContract.TABLE_NAME + " (" + NaturezaProdutoContract.COLUNA_CHAVE + ")  " + 
        ";";
	}

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + ProdutoClienteContract.TABLE_NAME + " (" +
        ProdutoClienteContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
        + " , " + ProdutoClienteContract.COLUNA_NOME + " TEXT "
        + " , " + ProdutoClienteContract.COLUNA_POSICAO_PRODUTO + " INTEGER "
        + " , " + ProdutoClienteContract.COLUNA_IMAGEM + " TEXT "
        + " , " + ProdutoClienteContract.COLUNA_PRECO_ATUAL + " REAL "
        + " , " + ProdutoClienteContract.COLUNA_MARCA + " TEXT "
        + " , " + ProdutoClienteContract.COLUNA_LOJA + " TEXT "
        + " , " + ProdutoClienteContract.COLUNA_DATA + " INTEGER "
        + " , " + ProdutoClienteContract.COLUNA_URL + " TEXT "
        + " , " + ProdutoClienteContract.COLUNA_DETALHE + " TEXT "
		+ " , " + ProdutoClienteContract.COLUNA_ID_NATUREZA_PRODUTO_RA + " INTEGER "
		
		+ " , " + ProdutoClienteContract.COLUNA_ID_USUARIO_S + " INTEGER "
		
		//+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + ProdutoClienteContract.TABLE_NAME_SINC + " (" +
        ProdutoClienteContract.COLUNA_CHAVE + " INTEGER " 
        + " , " + ProdutoClienteContract.COLUNA_NOME + " TEXT "
        + " , " + ProdutoClienteContract.COLUNA_POSICAO_PRODUTO + " INTEGER "
        + " , " + ProdutoClienteContract.COLUNA_IMAGEM + " TEXT "
        + " , " + ProdutoClienteContract.COLUNA_PRECO_ATUAL + " REAL "
        + " , " + ProdutoClienteContract.COLUNA_MARCA + " TEXT "
        + " , " + ProdutoClienteContract.COLUNA_LOJA + " TEXT "
        + " , " + ProdutoClienteContract.COLUNA_DATA + " INTEGER "
        + " , " + ProdutoClienteContract.COLUNA_URL + " TEXT "
        + " , " + ProdutoClienteContract.COLUNA_DETALHE + " TEXT "
		+ " , " + ProdutoClienteContract.COLUNA_ID_NATUREZA_PRODUTO_RA + " INTEGER "
		
		+ " , " + ProdutoClienteContract.COLUNA_ID_USUARIO_S + " INTEGER "
		
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
		saida += " , FOREIGN KEY (" + ProdutoClienteContract.COLUNA_ID_NATUREZA_PRODUTO_RA + ") REFERENCES " + NaturezaProdutoContract.TABLE_NAME + " (" + NaturezaProdutoContract.COLUNA_CHAVE + ") ";
		return saida;
	}
	
	public static String getSqlDrop() {
		return "DROP TABLE IF EXISTS " + ProdutoClienteContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + ProdutoClienteContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + ProdutoClienteContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + ProdutoClienteContract.TABLE_NAME_SINC;
    }
    
   
   
    
}