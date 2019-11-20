
package  br.com.lojadigicom.repcom.data.helper;

import br.com.lojadigicom.repcom.data.contract.*;

public class ParcelaVendaDbHelper {

	protected static String getSqlChavesEstrangeiras() {
		return  "ALTER TABLE " +
        ParcelaVendaContract.TABLE_NAME + " " +
		" ADD FOREIGN KEY (" + ParcelaVendaContract.COLUNA_ID_VENDA_PA + ") REFERENCES " + VendaContract.TABLE_NAME + " (" + VendaContract.COLUNA_CHAVE + ")  " + 
        ";";
	}

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + ParcelaVendaContract.TABLE_NAME + " (" +
        ParcelaVendaContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
        + " , " + ParcelaVendaContract.COLUNA_VALOR_PARCELA + " REAL "
        + " , " + ParcelaVendaContract.COLUNA_DATA_VENCIMENTO + " INTEGER "
        + " , " + ParcelaVendaContract.COLUNA_NUMERO_PARCELA + " INTEGER "
        + " , " + ParcelaVendaContract.COLUNA_PAGA + " NUMERIC "
		+ " , " + ParcelaVendaContract.COLUNA_ID_VENDA_PA + " INTEGER "
		
		+ " , " + ParcelaVendaContract.COLUNA_ID_USUARIO_S + " INTEGER "
		
		//+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + ParcelaVendaContract.TABLE_NAME_SINC + " (" +
        ParcelaVendaContract.COLUNA_CHAVE + " INTEGER " 
        + " , " + ParcelaVendaContract.COLUNA_VALOR_PARCELA + " REAL "
        + " , " + ParcelaVendaContract.COLUNA_DATA_VENCIMENTO + " INTEGER "
        + " , " + ParcelaVendaContract.COLUNA_NUMERO_PARCELA + " INTEGER "
        + " , " + ParcelaVendaContract.COLUNA_PAGA + " NUMERIC "
		+ " , " + ParcelaVendaContract.COLUNA_ID_VENDA_PA + " INTEGER "
		
		+ " , " + ParcelaVendaContract.COLUNA_ID_USUARIO_S + " INTEGER "
		
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
		saida += " , FOREIGN KEY (" + ParcelaVendaContract.COLUNA_ID_VENDA_PA + ") REFERENCES " + VendaContract.TABLE_NAME + " (" + VendaContract.COLUNA_CHAVE + ") ";
		return saida;
	}
	
	public static String getSqlDrop() {
		return "DROP TABLE IF EXISTS " + ParcelaVendaContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + ParcelaVendaContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + ParcelaVendaContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + ParcelaVendaContract.TABLE_NAME_SINC;
    }
    
   
   
    
}