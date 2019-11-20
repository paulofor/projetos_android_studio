
package  br.com.lojadigicom.repcom.data.helper;

import br.com.lojadigicom.repcom.data.contract.*;

public class ContatoClienteDbHelper {

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + ContatoClienteContract.TABLE_NAME + " (" +
        ContatoClienteContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
        + " , " + ContatoClienteContract.COLUNA_DATA_CONTATO + " INTEGER "
		+ " , " + ContatoClienteContract.COLUNA_ID_CLIENTE_RA + " INTEGER "
		
		+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + ContatoClienteContract.TABLE_NAME_SINC + " (" +
        ContatoClienteContract.COLUNA_CHAVE + " INTEGER " 
        + " , " + ContatoClienteContract.COLUNA_DATA_CONTATO + " INTEGER "
		+ " , " + ContatoClienteContract.COLUNA_ID_CLIENTE_RA + " INTEGER "
		
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
		saida += " , FOREIGN KEY (" + ContatoClienteContract.COLUNA_ID_CLIENTE_RA + ") REFERENCES " + ContatoClienteContract.TABLE_NAME + " (" + ClienteContract.COLUNA_CHAVE + ") ";
		return saida;
	}
	
	public static String getSqlDrop() {
		return "DROP TABLE IF EXISTS " + ContatoClienteContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + ContatoClienteContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + ContatoClienteContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + ContatoClienteContract.TABLE_NAME_SINC;
    }
    
   
   
    
}