
package  br.com.lojadigicom.treinoacademia.data.helper;

import br.com.lojadigicom.treinoacademia.data.contract.*;

public class ItemSerieDbHelper {

	protected static String getSqlChavesEstrangeiras() {
		return  "ALTER TABLE " +
        ItemSerieContract.TABLE_NAME + " " +
		" ADD FOREIGN KEY (" + ItemSerieContract.COLUNA_ID_EXERCICIO_ED + ") REFERENCES " + ExercicioContract.TABLE_NAME + " (" + ExercicioContract.COLUNA_CHAVE + ")  " + 
		" ADD FOREIGN KEY (" + ItemSerieContract.COLUNA_ID_SERIE_TREINO_PA + ") REFERENCES " + SerieTreinoContract.TABLE_NAME + " (" + SerieTreinoContract.COLUNA_CHAVE + ")  " + 
        ";";
	}

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + ItemSerieContract.TABLE_NAME + " (" +
        ItemSerieContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
        + " , " + ItemSerieContract.COLUNA_REPETICOES + " INTEGER "
        + " , " + ItemSerieContract.COLUNA_SERIE + " INTEGER "
        + " , " + ItemSerieContract.COLUNA_PARAMETROS + " TEXT "
        + " , " + ItemSerieContract.COLUNA_ORDEM_EXECUCAO + " INTEGER "
		+ " , " + ItemSerieContract.COLUNA_ID_EXERCICIO_ED + " INTEGER "
		
		+ " , " + ItemSerieContract.COLUNA_ID_SERIE_TREINO_PA + " INTEGER "
		
		+ " , " + ItemSerieContract.COLUNA_ID_USUARIO_S + " INTEGER "
		
		//+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + ItemSerieContract.TABLE_NAME_SINC + " (" +
        ItemSerieContract.COLUNA_CHAVE + " INTEGER " 
        + " , " + ItemSerieContract.COLUNA_REPETICOES + " INTEGER "
        + " , " + ItemSerieContract.COLUNA_SERIE + " INTEGER "
        + " , " + ItemSerieContract.COLUNA_PARAMETROS + " TEXT "
        + " , " + ItemSerieContract.COLUNA_ORDEM_EXECUCAO + " INTEGER "
		+ " , " + ItemSerieContract.COLUNA_ID_EXERCICIO_ED + " INTEGER "
		
		+ " , " + ItemSerieContract.COLUNA_ID_SERIE_TREINO_PA + " INTEGER "
		
		+ " , " + ItemSerieContract.COLUNA_ID_USUARIO_S + " INTEGER "
		
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
		saida += " , FOREIGN KEY (" + ItemSerieContract.COLUNA_ID_EXERCICIO_ED + ") REFERENCES " + ExercicioContract.TABLE_NAME + " (" + ExercicioContract.COLUNA_CHAVE + ") ";
		saida += " , FOREIGN KEY (" + ItemSerieContract.COLUNA_ID_SERIE_TREINO_PA + ") REFERENCES " + SerieTreinoContract.TABLE_NAME + " (" + SerieTreinoContract.COLUNA_CHAVE + ") ";
		return saida;
	}
	
	public static String getSqlDrop() {
		return "DROP TABLE IF EXISTS " + ItemSerieContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + ItemSerieContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + ItemSerieContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + ItemSerieContract.TABLE_NAME_SINC;
    }
    
   
   
    
}