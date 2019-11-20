
package  br.com.lojadigicom.treinoacademia.data.helper;

import br.com.lojadigicom.treinoacademia.data.contract.*;

public class ExecucaoItemSerieDbHelper {

	protected static String getSqlChavesEstrangeiras() {
		return  "ALTER TABLE " +
        ExecucaoItemSerieContract.TABLE_NAME + " " +
		" ADD FOREIGN KEY (" + ExecucaoItemSerieContract.COLUNA_ID_ITEM_SERIE_RA + ") REFERENCES " + ItemSerieContract.TABLE_NAME + " (" + ItemSerieContract.COLUNA_CHAVE + ")  " + 
		" ADD FOREIGN KEY (" + ExecucaoItemSerieContract.COLUNA_ID_DIA_TREINO_E + ") REFERENCES " + DiaTreinoContract.TABLE_NAME + " (" + DiaTreinoContract.COLUNA_CHAVE + ")  " + 
		" ADD FOREIGN KEY (" + ExecucaoItemSerieContract.COLUNA_ID_EXERCICIO_RA + ") REFERENCES " + ExercicioContract.TABLE_NAME + " (" + ExercicioContract.COLUNA_CHAVE + ")  " + 
        ";";
	}

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + ExecucaoItemSerieContract.TABLE_NAME + " (" +
        ExecucaoItemSerieContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
        + " , " + ExecucaoItemSerieContract.COLUNA_DATA_HORA_INICIO + " INTEGER "
        + " , " + ExecucaoItemSerieContract.COLUNA_CARGA_UTILIZADA + " REAL "
        + " , " + ExecucaoItemSerieContract.COLUNA_SUCESSO_REPETICOES + " NUMERIC "
        + " , " + ExecucaoItemSerieContract.COLUNA_NUMERO_SERIE + " INTEGER "
        + " , " + ExecucaoItemSerieContract.COLUNA_DATA_HORA_FINALIZACAO + " INTEGER "
        + " , " + ExecucaoItemSerieContract.COLUNA_QUANTIDADE_REPETICAO + " INTEGER "
		+ " , " + ExecucaoItemSerieContract.COLUNA_ID_ITEM_SERIE_RA + " INTEGER "
		
		+ " , " + ExecucaoItemSerieContract.COLUNA_ID_DIA_TREINO_E + " INTEGER "
		
		+ " , " + ExecucaoItemSerieContract.COLUNA_ID_EXERCICIO_RA + " INTEGER "
		
		+ " , " + ExecucaoItemSerieContract.COLUNA_ID_USUARIO_S + " INTEGER "
		
		//+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + ExecucaoItemSerieContract.TABLE_NAME_SINC + " (" +
        ExecucaoItemSerieContract.COLUNA_CHAVE + " INTEGER " 
        + " , " + ExecucaoItemSerieContract.COLUNA_DATA_HORA_INICIO + " INTEGER "
        + " , " + ExecucaoItemSerieContract.COLUNA_CARGA_UTILIZADA + " REAL "
        + " , " + ExecucaoItemSerieContract.COLUNA_SUCESSO_REPETICOES + " NUMERIC "
        + " , " + ExecucaoItemSerieContract.COLUNA_NUMERO_SERIE + " INTEGER "
        + " , " + ExecucaoItemSerieContract.COLUNA_DATA_HORA_FINALIZACAO + " INTEGER "
        + " , " + ExecucaoItemSerieContract.COLUNA_QUANTIDADE_REPETICAO + " INTEGER "
		+ " , " + ExecucaoItemSerieContract.COLUNA_ID_ITEM_SERIE_RA + " INTEGER "
		
		+ " , " + ExecucaoItemSerieContract.COLUNA_ID_DIA_TREINO_E + " INTEGER "
		
		+ " , " + ExecucaoItemSerieContract.COLUNA_ID_EXERCICIO_RA + " INTEGER "
		
		+ " , " + ExecucaoItemSerieContract.COLUNA_ID_USUARIO_S + " INTEGER "
		
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
		saida += " , FOREIGN KEY (" + ExecucaoItemSerieContract.COLUNA_ID_ITEM_SERIE_RA + ") REFERENCES " + ItemSerieContract.TABLE_NAME + " (" + ItemSerieContract.COLUNA_CHAVE + ") ";
		saida += " , FOREIGN KEY (" + ExecucaoItemSerieContract.COLUNA_ID_DIA_TREINO_E + ") REFERENCES " + DiaTreinoContract.TABLE_NAME + " (" + DiaTreinoContract.COLUNA_CHAVE + ") ";
		saida += " , FOREIGN KEY (" + ExecucaoItemSerieContract.COLUNA_ID_EXERCICIO_RA + ") REFERENCES " + ExercicioContract.TABLE_NAME + " (" + ExercicioContract.COLUNA_CHAVE + ") ";
		return saida;
	}
	
	public static String getSqlDrop() {
		return "DROP TABLE IF EXISTS " + ExecucaoItemSerieContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + ExecucaoItemSerieContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + ExecucaoItemSerieContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + ExecucaoItemSerieContract.TABLE_NAME_SINC;
    }
    
   
   
    
}