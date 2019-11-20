
package  br.com.lojadigicom.coletorprecocliente.data.helper;

import br.com.lojadigicom.coletorprecocliente.data.contract.*;

public class NaturezaProdutoDbHelper {

	protected static String getSqlChavesEstrangeiras() {
		return  "ALTER TABLE " +
        NaturezaProdutoContract.TABLE_NAME + " " +
		" ADD FOREIGN KEY (" + NaturezaProdutoContract.COLUNA_ID_APP_PRODUTO_AP + ") REFERENCES " + AppProdutoContract.TABLE_NAME + " (" + AppProdutoContract.COLUNA_CHAVE + ")  " + 
        ";";
	}

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + NaturezaProdutoContract.TABLE_NAME + " (" +
        NaturezaProdutoContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
        + " , " + NaturezaProdutoContract.COLUNA_NOME_NATUREZA_PRODUTO + " TEXT "
		+ " , " + NaturezaProdutoContract.COLUNA_ID_APP_PRODUTO_AP + " INTEGER "
		
		//+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + NaturezaProdutoContract.TABLE_NAME_SINC + " (" +
        NaturezaProdutoContract.COLUNA_CHAVE + " INTEGER " 
        + " , " + NaturezaProdutoContract.COLUNA_NOME_NATUREZA_PRODUTO + " TEXT "
		+ " , " + NaturezaProdutoContract.COLUNA_ID_APP_PRODUTO_AP + " INTEGER "
		
        + ", operacao_sinc TEXT);";
	}
	
	
	private static String getSqlIndices() {
		return "";
	}
	
	private static String getSqlProcValor() {
		String saida = "";
		
		saida += " , " + NaturezaProdutoContract.COLUNA_QTDEOPORTUNIDADEDIA + " INTEGER ";
		
		saida += " , " + NaturezaProdutoContract.COLUNA_ATIVO + " NUMERIC ";
		
		return saida;
	}
	
	
	private static String getSqlChaveEstrangeira() {
		String saida = "";
		saida += " , FOREIGN KEY (" + NaturezaProdutoContract.COLUNA_ID_APP_PRODUTO_AP + ") REFERENCES " + AppProdutoContract.TABLE_NAME + " (" + AppProdutoContract.COLUNA_CHAVE + ") ";
		return saida;
	}
	
	public static String getSqlDrop() {
		return "DROP TABLE IF EXISTS " + NaturezaProdutoContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + NaturezaProdutoContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + NaturezaProdutoContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + NaturezaProdutoContract.TABLE_NAME_SINC;
    }
    
   
   
    
}