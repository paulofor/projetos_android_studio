
package  br.com.lojadigicom.repcom.data.helper;

import br.com.lojadigicom.repcom.data.contract.*;

public class VendaDbHelper {

	protected static String getSqlChavesEstrangeiras() {
		return  "ALTER TABLE " +
        VendaContract.TABLE_NAME + " " +
		" ADD FOREIGN KEY (" + VendaContract.COLUNA_ID_CLIENTE_FP + ") REFERENCES " + ClienteContract.TABLE_NAME + " (" + ClienteContract.COLUNA_CHAVE + ")  " + 
        ";";
	}

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + VendaContract.TABLE_NAME + " (" +
        VendaContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
        + " , " + VendaContract.COLUNA_DATA + " INTEGER "
        + " , " + VendaContract.COLUNA_VALOR_TOTAL + " REAL "
        + " , " + VendaContract.COLUNA_VENDA_FECHADA + " NUMERIC "
		+ " , " + VendaContract.COLUNA_ID_CLIENTE_FP + " INTEGER "
		
		+ " , " + VendaContract.COLUNA_ID_USUARIO_S + " INTEGER "
		
		//+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + VendaContract.TABLE_NAME_SINC + " (" +
        VendaContract.COLUNA_CHAVE + " INTEGER " 
        + " , " + VendaContract.COLUNA_DATA + " INTEGER "
        + " , " + VendaContract.COLUNA_VALOR_TOTAL + " REAL "
        + " , " + VendaContract.COLUNA_VENDA_FECHADA + " NUMERIC "
		+ " , " + VendaContract.COLUNA_ID_CLIENTE_FP + " INTEGER "
		
		+ " , " + VendaContract.COLUNA_ID_USUARIO_S + " INTEGER "
		
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
		saida += " , FOREIGN KEY (" + VendaContract.COLUNA_ID_CLIENTE_FP + ") REFERENCES " + ClienteContract.TABLE_NAME + " (" + ClienteContract.COLUNA_CHAVE + ") ";
		return saida;
	}
	
	public static String getSqlDrop() {
		return "DROP TABLE IF EXISTS " + VendaContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + VendaContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + VendaContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + VendaContract.TABLE_NAME_SINC;
    }
    
   
   
    
}