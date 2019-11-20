
package  br.com.lojadigicom.coletorprecocliente.data.helper;

import br.com.lojadigicom.coletorprecocliente.data.contract.*;

public class AppProdutoDbHelper {

	protected static String getSqlChavesEstrangeiras() {
		return  "ALTER TABLE " +
        AppProdutoContract.TABLE_NAME + " " +
        ";";
	}

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + AppProdutoContract.TABLE_NAME + " (" +
        AppProdutoContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
        + " , " + AppProdutoContract.COLUNA_NOME + " TEXT "
        + " , " + AppProdutoContract.COLUNA_URL_INSTALACAO + " TEXT "
        + " , " + AppProdutoContract.COLUNA_POSSUI_VITRINE + " NUMERIC "
        + " , " + AppProdutoContract.COLUNA_ATIVO + " NUMERIC "
        + " , " + AppProdutoContract.COLUNA_STATUS + " TEXT "
        + " , " + AppProdutoContract.COLUNA_LIMITE_POSICIONADOR + " INTEGER "
        + " , " + AppProdutoContract.COLUNA_POSSUI_PALAVRA_CHAVE + " NUMERIC "
        + " , " + AppProdutoContract.COLUNA_CODIGO_HASH + " TEXT "
		//+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + AppProdutoContract.TABLE_NAME_SINC + " (" +
        AppProdutoContract.COLUNA_CHAVE + " INTEGER " 
        + " , " + AppProdutoContract.COLUNA_NOME + " TEXT "
        + " , " + AppProdutoContract.COLUNA_URL_INSTALACAO + " TEXT "
        + " , " + AppProdutoContract.COLUNA_POSSUI_VITRINE + " NUMERIC "
        + " , " + AppProdutoContract.COLUNA_ATIVO + " NUMERIC "
        + " , " + AppProdutoContract.COLUNA_STATUS + " TEXT "
        + " , " + AppProdutoContract.COLUNA_LIMITE_POSICIONADOR + " INTEGER "
        + " , " + AppProdutoContract.COLUNA_POSSUI_PALAVRA_CHAVE + " NUMERIC "
        + " , " + AppProdutoContract.COLUNA_CODIGO_HASH + " TEXT "
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
		return saida;
	}
	
	public static String getSqlDrop() {
		return "DROP TABLE IF EXISTS " + AppProdutoContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + AppProdutoContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + AppProdutoContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + AppProdutoContract.TABLE_NAME_SINC;
    }
    
   
   
    
}