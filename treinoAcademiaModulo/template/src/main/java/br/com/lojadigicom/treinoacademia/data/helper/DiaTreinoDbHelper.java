
package  br.com.lojadigicom.treinoacademia.data.helper;

import br.com.lojadigicom.treinoacademia.data.contract.*;

public class DiaTreinoDbHelper {

	protected static String getSqlChavesEstrangeiras() {
		return  "ALTER TABLE " +
        DiaTreinoContract.TABLE_NAME + " " +
		" ADD FOREIGN KEY (" + DiaTreinoContract.COLUNA_ID_SERIE_TREINO_SD + ") REFERENCES " + SerieTreinoContract.TABLE_NAME + " (" + SerieTreinoContract.COLUNA_CHAVE + ")  " + 
        ";";
	}

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + DiaTreinoContract.TABLE_NAME + " (" +
        DiaTreinoContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
        + " , " + DiaTreinoContract.COLUNA_DATA + " INTEGER "
        + " , " + DiaTreinoContract.COLUNA_CONCLUIDO + " NUMERIC "
		+ " , " + DiaTreinoContract.COLUNA_ID_USUARIO_S + " INTEGER "
		
		+ " , " + DiaTreinoContract.COLUNA_ID_SERIE_TREINO_SD + " INTEGER "
		
		//+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + DiaTreinoContract.TABLE_NAME_SINC + " (" +
        DiaTreinoContract.COLUNA_CHAVE + " INTEGER " 
        + " , " + DiaTreinoContract.COLUNA_DATA + " INTEGER "
        + " , " + DiaTreinoContract.COLUNA_CONCLUIDO + " NUMERIC "
		+ " , " + DiaTreinoContract.COLUNA_ID_USUARIO_S + " INTEGER "
		
		+ " , " + DiaTreinoContract.COLUNA_ID_SERIE_TREINO_SD + " INTEGER "
		
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
		saida += " , FOREIGN KEY (" + DiaTreinoContract.COLUNA_ID_SERIE_TREINO_SD + ") REFERENCES " + SerieTreinoContract.TABLE_NAME + " (" + SerieTreinoContract.COLUNA_CHAVE + ") ";
		return saida;
	}
	
	public static String getSqlDrop() {
		return "DROP TABLE IF EXISTS " + DiaTreinoContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + DiaTreinoContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + DiaTreinoContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + DiaTreinoContract.TABLE_NAME_SINC;
    }
    
   
   
    
}