
package  br.com.lojadigicom.treinoacademia.data.helper;

import br.com.lojadigicom.treinoacademia.data.contract.*;

public class SerieTreinoDbHelper {

	protected static String getSqlChavesEstrangeiras() {
		return  "ALTER TABLE " +
        SerieTreinoContract.TABLE_NAME + " " +
        ";";
	}

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + SerieTreinoContract.TABLE_NAME + " (" +
        SerieTreinoContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
        + " , " + SerieTreinoContract.COLUNA_QTDE_EXECUCAO + " INTEGER "
        + " , " + SerieTreinoContract.COLUNA_ATIVA + " NUMERIC "
        + " , " + SerieTreinoContract.COLUNA_DATA_CRIACAO + " INTEGER "
        + " , " + SerieTreinoContract.COLUNA_DATA_ULTIMA_EXECUCAO + " INTEGER "
		+ " , " + SerieTreinoContract.COLUNA_ID_USUARIO_S + " INTEGER "
		
		//+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + SerieTreinoContract.TABLE_NAME_SINC + " (" +
        SerieTreinoContract.COLUNA_CHAVE + " INTEGER " 
        + " , " + SerieTreinoContract.COLUNA_QTDE_EXECUCAO + " INTEGER "
        + " , " + SerieTreinoContract.COLUNA_ATIVA + " NUMERIC "
        + " , " + SerieTreinoContract.COLUNA_DATA_CRIACAO + " INTEGER "
        + " , " + SerieTreinoContract.COLUNA_DATA_ULTIMA_EXECUCAO + " INTEGER "
		+ " , " + SerieTreinoContract.COLUNA_ID_USUARIO_S + " INTEGER "
		
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
		return "DROP TABLE IF EXISTS " + SerieTreinoContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + SerieTreinoContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + SerieTreinoContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + SerieTreinoContract.TABLE_NAME_SINC;
    }
    
   
   
    
}