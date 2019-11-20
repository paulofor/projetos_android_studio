
package  br.com.lojadigicom.coletorprecocliente.data.helper;

import br.com.lojadigicom.coletorprecocliente.data.contract.*;

public class PalavraChavePesquisaDbHelper {

	protected static String getSqlChavesEstrangeiras() {
		return  "ALTER TABLE " +
        PalavraChavePesquisaContract.TABLE_NAME + " " +
		" ADD FOREIGN KEY (" + PalavraChavePesquisaContract.COLUNA_ID_NATUREZA_PRODUTO_RA + ") REFERENCES " + NaturezaProdutoContract.TABLE_NAME + " (" + NaturezaProdutoContract.COLUNA_CHAVE + ")  " + 
        ";";
	}

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + PalavraChavePesquisaContract.TABLE_NAME + " (" +
        PalavraChavePesquisaContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
        + " , " + PalavraChavePesquisaContract.COLUNA_TEXTO_BUSCA + " TEXT "
        + " , " + PalavraChavePesquisaContract.COLUNA_DATA + " INTEGER "
		+ " , " + PalavraChavePesquisaContract.COLUNA_ID_USUARIO_S + " INTEGER "
		
		+ " , " + PalavraChavePesquisaContract.COLUNA_ID_NATUREZA_PRODUTO_RA + " INTEGER "
		
		//+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + PalavraChavePesquisaContract.TABLE_NAME_SINC + " (" +
        PalavraChavePesquisaContract.COLUNA_CHAVE + " INTEGER " 
        + " , " + PalavraChavePesquisaContract.COLUNA_TEXTO_BUSCA + " TEXT "
        + " , " + PalavraChavePesquisaContract.COLUNA_DATA + " INTEGER "
		+ " , " + PalavraChavePesquisaContract.COLUNA_ID_USUARIO_S + " INTEGER "
		
		+ " , " + PalavraChavePesquisaContract.COLUNA_ID_NATUREZA_PRODUTO_RA + " INTEGER "
		
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
		saida += " , FOREIGN KEY (" + PalavraChavePesquisaContract.COLUNA_ID_NATUREZA_PRODUTO_RA + ") REFERENCES " + NaturezaProdutoContract.TABLE_NAME + " (" + NaturezaProdutoContract.COLUNA_CHAVE + ") ";
		return saida;
	}
	
	public static String getSqlDrop() {
		return "DROP TABLE IF EXISTS " + PalavraChavePesquisaContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + PalavraChavePesquisaContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + PalavraChavePesquisaContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + PalavraChavePesquisaContract.TABLE_NAME_SINC;
    }
    
   
   
    
}