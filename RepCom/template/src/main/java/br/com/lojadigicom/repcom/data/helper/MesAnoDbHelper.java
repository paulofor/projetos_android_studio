
package  br.com.lojadigicom.repcom.data.helper;

import br.com.lojadigicom.repcom.data.contract.*;

public class MesAnoDbHelper {

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + MesAnoContract.TABLE_NAME + " (" +
        MesAnoContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
        + " , " + MesAnoContract.COLUNA_MES + " INTEGER "
        + " , " + MesAnoContract.COLUNA_ANO + " INTEGER "
        + " , " + MesAnoContract.COLUNA_DESCRICAO_TELA + " TEXT "
        + " , " + MesAnoContract.COLUNA_DATA_REFERENCIA + " INTEGER "
		+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + MesAnoContract.TABLE_NAME_SINC + " (" +
        MesAnoContract.COLUNA_CHAVE + " INTEGER " 
        + " , " + MesAnoContract.COLUNA_MES + " INTEGER "
        + " , " + MesAnoContract.COLUNA_ANO + " INTEGER "
        + " , " + MesAnoContract.COLUNA_DESCRICAO_TELA + " TEXT "
        + " , " + MesAnoContract.COLUNA_DATA_REFERENCIA + " INTEGER "
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
		return "DROP TABLE IF EXISTS " + MesAnoContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + MesAnoContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + MesAnoContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + MesAnoContract.TABLE_NAME_SINC;
    }
    
   
   
    
}