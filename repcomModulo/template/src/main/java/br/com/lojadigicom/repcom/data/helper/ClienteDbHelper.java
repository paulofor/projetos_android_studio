
package  br.com.lojadigicom.repcom.data.helper;

import br.com.lojadigicom.repcom.data.contract.*;

public class ClienteDbHelper {

	protected static String getSqlChavesEstrangeiras() {
		return  "ALTER TABLE " +
        ClienteContract.TABLE_NAME + " " +
        ";";
	}

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + ClienteContract.TABLE_NAME + " (" +
        ClienteContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
        + " , " + ClienteContract.COLUNA_ENDERECO_RUA + " TEXT "
        + " , " + ClienteContract.COLUNA_ENDERECO_NUMERO + " TEXT "
        + " , " + ClienteContract.COLUNA_ENDERECO_COMPLEMENTO + " TEXT "
        + " , " + ClienteContract.COLUNA_ENDERECO_CEP + " TEXT "
        + " , " + ClienteContract.COLUNA_ENDERECO_BAIRRO + " TEXT "
        + " , " + ClienteContract.COLUNA_ENDERECO_CIDADE + " TEXT "
        + " , " + ClienteContract.COLUNA_ENDERECO_UF + " TEXT "
        + " , " + ClienteContract.COLUNA_OBSERVACOES + " TEXT "
        + " , " + ClienteContract.COLUNA_CODIGO_LISTA_CONTATO + " TEXT "
        + " , " + ClienteContract.COLUNA_NOME + " TEXT "
        + " , " + ClienteContract.COLUNA_ATIVO + " NUMERIC "
		+ " , " + ClienteContract.COLUNA_ID_USUARIO_S + " INTEGER "
		
		//+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + ClienteContract.TABLE_NAME_SINC + " (" +
        ClienteContract.COLUNA_CHAVE + " INTEGER " 
        + " , " + ClienteContract.COLUNA_ENDERECO_RUA + " TEXT "
        + " , " + ClienteContract.COLUNA_ENDERECO_NUMERO + " TEXT "
        + " , " + ClienteContract.COLUNA_ENDERECO_COMPLEMENTO + " TEXT "
        + " , " + ClienteContract.COLUNA_ENDERECO_CEP + " TEXT "
        + " , " + ClienteContract.COLUNA_ENDERECO_BAIRRO + " TEXT "
        + " , " + ClienteContract.COLUNA_ENDERECO_CIDADE + " TEXT "
        + " , " + ClienteContract.COLUNA_ENDERECO_UF + " TEXT "
        + " , " + ClienteContract.COLUNA_OBSERVACOES + " TEXT "
        + " , " + ClienteContract.COLUNA_CODIGO_LISTA_CONTATO + " TEXT "
        + " , " + ClienteContract.COLUNA_NOME + " TEXT "
        + " , " + ClienteContract.COLUNA_ATIVO + " NUMERIC "
		+ " , " + ClienteContract.COLUNA_ID_USUARIO_S + " INTEGER "
		
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
		return "DROP TABLE IF EXISTS " + ClienteContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + ClienteContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + ClienteContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + ClienteContract.TABLE_NAME_SINC;
    }
    
   
   
    
}