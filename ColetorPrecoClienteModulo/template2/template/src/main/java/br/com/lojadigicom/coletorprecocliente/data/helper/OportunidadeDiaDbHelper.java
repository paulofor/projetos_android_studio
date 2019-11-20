
package  br.com.lojadigicom.coletorprecocliente.data.helper;

import br.com.lojadigicom.coletorprecocliente.data.contract.*;

public class OportunidadeDiaDbHelper {

	protected static String getSqlChavesEstrangeiras() {
		return  "ALTER TABLE " +
        OportunidadeDiaContract.TABLE_NAME + " " +
		" ADD FOREIGN KEY (" + OportunidadeDiaContract.COLUNA_ID_NATUREZA_PRODUTO_PA + ") REFERENCES " + NaturezaProdutoContract.TABLE_NAME + " (" + NaturezaProdutoContract.COLUNA_CHAVE + ")  " + 
        ";";
	}

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + OportunidadeDiaContract.TABLE_NAME + " (" +
        OportunidadeDiaContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
        + " , " + OportunidadeDiaContract.COLUNA_NOME_PRODUTO + " TEXT "
        + " , " + OportunidadeDiaContract.COLUNA_URL_PRODUTO + " TEXT "
        + " , " + OportunidadeDiaContract.COLUNA_URL_IMAGEM + " TEXT "
        + " , " + OportunidadeDiaContract.COLUNA_PRECO_VENDA_ATUAL + " REAL "
        + " , " + OportunidadeDiaContract.COLUNA_PRECO_VENDA_ANTERIOR + " REAL "
        + " , " + OportunidadeDiaContract.COLUNA_PERCENTUAL_AJUSTE_VENDA + " REAL "
        + " , " + OportunidadeDiaContract.COLUNA_QUANTIDADE_PARCELA_ATUAL + " INTEGER "
        + " , " + OportunidadeDiaContract.COLUNA_PRECO_PARCELA_ATUAL + " REAL "
        + " , " + OportunidadeDiaContract.COLUNA_PRECO_PARCELA_ANTERIOR + " REAL "
        + " , " + OportunidadeDiaContract.COLUNA_QUANTIDADE_PARCELA_ANTERIOR + " INTEGER "
        + " , " + OportunidadeDiaContract.COLUNA_NOME_LOJA_VIRTUAL + " TEXT "
        + " , " + OportunidadeDiaContract.COLUNA_DATA_ULTIMA_PRECO_ANTERIOR + " INTEGER "
        + " , " + OportunidadeDiaContract.COLUNA_DATA_INICIO_PRECO_ATUAL + " INTEGER "
        + " , " + OportunidadeDiaContract.COLUNA_PRECO_MEDIO + " REAL "
        + " , " + OportunidadeDiaContract.COLUNA_PRECO_MINIMO + " REAL "
		+ " , " + OportunidadeDiaContract.COLUNA_ID_NATUREZA_PRODUTO_PA + " INTEGER "
		
		//+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + OportunidadeDiaContract.TABLE_NAME_SINC + " (" +
        OportunidadeDiaContract.COLUNA_CHAVE + " INTEGER " 
        + " , " + OportunidadeDiaContract.COLUNA_NOME_PRODUTO + " TEXT "
        + " , " + OportunidadeDiaContract.COLUNA_URL_PRODUTO + " TEXT "
        + " , " + OportunidadeDiaContract.COLUNA_URL_IMAGEM + " TEXT "
        + " , " + OportunidadeDiaContract.COLUNA_PRECO_VENDA_ATUAL + " REAL "
        + " , " + OportunidadeDiaContract.COLUNA_PRECO_VENDA_ANTERIOR + " REAL "
        + " , " + OportunidadeDiaContract.COLUNA_PERCENTUAL_AJUSTE_VENDA + " REAL "
        + " , " + OportunidadeDiaContract.COLUNA_QUANTIDADE_PARCELA_ATUAL + " INTEGER "
        + " , " + OportunidadeDiaContract.COLUNA_PRECO_PARCELA_ATUAL + " REAL "
        + " , " + OportunidadeDiaContract.COLUNA_PRECO_PARCELA_ANTERIOR + " REAL "
        + " , " + OportunidadeDiaContract.COLUNA_QUANTIDADE_PARCELA_ANTERIOR + " INTEGER "
        + " , " + OportunidadeDiaContract.COLUNA_NOME_LOJA_VIRTUAL + " TEXT "
        + " , " + OportunidadeDiaContract.COLUNA_DATA_ULTIMA_PRECO_ANTERIOR + " INTEGER "
        + " , " + OportunidadeDiaContract.COLUNA_DATA_INICIO_PRECO_ATUAL + " INTEGER "
        + " , " + OportunidadeDiaContract.COLUNA_PRECO_MEDIO + " REAL "
        + " , " + OportunidadeDiaContract.COLUNA_PRECO_MINIMO + " REAL "
		+ " , " + OportunidadeDiaContract.COLUNA_ID_NATUREZA_PRODUTO_PA + " INTEGER "
		
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
		saida += " , FOREIGN KEY (" + OportunidadeDiaContract.COLUNA_ID_NATUREZA_PRODUTO_PA + ") REFERENCES " + NaturezaProdutoContract.TABLE_NAME + " (" + NaturezaProdutoContract.COLUNA_CHAVE + ") ";
		return saida;
	}
	
	public static String getSqlDrop() {
		return "DROP TABLE IF EXISTS " + OportunidadeDiaContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + OportunidadeDiaContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + OportunidadeDiaContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + OportunidadeDiaContract.TABLE_NAME_SINC;
    }
    
   
   
    
}