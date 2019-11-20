
package  br.com.lojadigicom.precomed.data.helper;

import br.com.lojadigicom.precomed.data.contract.*;

public class ProdutoPesquisaDbHelper {

	protected static String getSqlChavesEstrangeiras() {
		return  "ALTER TABLE " +
        ProdutoPesquisaContract.TABLE_NAME + " " +
		" ADD FOREIGN KEY (" + ProdutoPesquisaContract.COLUNA_ID_MODELO_PRODUTO_RA + ") REFERENCES " + ModeloProdutoContract.TABLE_NAME + " (" + ModeloProdutoContract.COLUNA_CHAVE + ")  " + 
        ";";
	}

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + ProdutoPesquisaContract.TABLE_NAME + " (" +
        ProdutoPesquisaContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
        + " , " + ProdutoPesquisaContract.COLUNA_DATA_INCLUSAO + " INTEGER "
        + " , " + ProdutoPesquisaContract.COLUNA_ATIVO + " NUMERIC "
        + " , " + ProdutoPesquisaContract.COLUNA_NOME_PRODUTO_PESQUISA + " TEXT "
		+ " , " + ProdutoPesquisaContract.COLUNA_ID_USUARIO_S + " INTEGER "
		
		+ " , " + ProdutoPesquisaContract.COLUNA_ID_MODELO_PRODUTO_RA + " INTEGER "
		
		//+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + ProdutoPesquisaContract.TABLE_NAME_SINC + " (" +
        ProdutoPesquisaContract.COLUNA_CHAVE + " INTEGER " 
        + " , " + ProdutoPesquisaContract.COLUNA_DATA_INCLUSAO + " INTEGER "
        + " , " + ProdutoPesquisaContract.COLUNA_ATIVO + " NUMERIC "
        + " , " + ProdutoPesquisaContract.COLUNA_NOME_PRODUTO_PESQUISA + " TEXT "
		+ " , " + ProdutoPesquisaContract.COLUNA_ID_USUARIO_S + " INTEGER "
		
		+ " , " + ProdutoPesquisaContract.COLUNA_ID_MODELO_PRODUTO_RA + " INTEGER "
		
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
		saida += " , FOREIGN KEY (" + ProdutoPesquisaContract.COLUNA_ID_MODELO_PRODUTO_RA + ") REFERENCES " + ModeloProdutoContract.TABLE_NAME + " (" + ModeloProdutoContract.COLUNA_CHAVE + ") ";
		return saida;
	}
	
	public static String getSqlDrop() {
		return "DROP TABLE IF EXISTS " + ProdutoPesquisaContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + ProdutoPesquisaContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + ProdutoPesquisaContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + ProdutoPesquisaContract.TABLE_NAME_SINC;
    }
    
   
   
    
}