
package  br.com.lojadigicom.coletorprecocliente.data.helper;

import br.com.lojadigicom.coletorprecocliente.data.contract.*;

public class UsuarioPesquisaDbHelper {

	protected static String getSqlChavesEstrangeiras() {
		return  "ALTER TABLE " +
        UsuarioPesquisaContract.TABLE_NAME + " " +
		" ADD FOREIGN KEY (" + UsuarioPesquisaContract.COLUNA_ID_NATUREZA_PRODUTO_P + ") REFERENCES " + NaturezaProdutoContract.TABLE_NAME + " (" + NaturezaProdutoContract.COLUNA_CHAVE + ")  " + 
        ";";
	}

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + UsuarioPesquisaContract.TABLE_NAME + " (" +
        UsuarioPesquisaContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
		+ " , " + UsuarioPesquisaContract.COLUNA_ID_USUARIO_S + " INTEGER "
		
		+ " , " + UsuarioPesquisaContract.COLUNA_ID_NATUREZA_PRODUTO_P + " INTEGER "
		
		//+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + UsuarioPesquisaContract.TABLE_NAME_SINC + " (" +
        UsuarioPesquisaContract.COLUNA_CHAVE + " INTEGER " 
		+ " , " + UsuarioPesquisaContract.COLUNA_ID_USUARIO_S + " INTEGER "
		
		+ " , " + UsuarioPesquisaContract.COLUNA_ID_NATUREZA_PRODUTO_P + " INTEGER "
		
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
		saida += " , FOREIGN KEY (" + UsuarioPesquisaContract.COLUNA_ID_NATUREZA_PRODUTO_P + ") REFERENCES " + NaturezaProdutoContract.TABLE_NAME + " (" + NaturezaProdutoContract.COLUNA_CHAVE + ") ";
		return saida;
	}
	
	public static String getSqlDrop() {
		return "DROP TABLE IF EXISTS " + UsuarioPesquisaContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + UsuarioPesquisaContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + UsuarioPesquisaContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + UsuarioPesquisaContract.TABLE_NAME_SINC;
    }
    
   
   
    
}