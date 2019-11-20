
package  br.com.lojadigicom.treinoacademia.data.helper;

import br.com.lojadigicom.treinoacademia.data.contract.*;

public class CargaPlanejadaDbHelper {

	protected static String getSqlChavesEstrangeiras() {
		return  "ALTER TABLE " +
        CargaPlanejadaContract.TABLE_NAME + " " +
		" ADD FOREIGN KEY (" + CargaPlanejadaContract.COLUNA_ID_ITEM_SERIE_RA + ") REFERENCES " + ItemSerieContract.TABLE_NAME + " (" + ItemSerieContract.COLUNA_CHAVE + ")  " + 
        ";";
	}

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + CargaPlanejadaContract.TABLE_NAME + " (" +
        CargaPlanejadaContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
        + " , " + CargaPlanejadaContract.COLUNA_VALOR_CARGA + " REAL "
        + " , " + CargaPlanejadaContract.COLUNA_DATA_INICIO + " INTEGER "
        + " , " + CargaPlanejadaContract.COLUNA_DATA_FINAL + " INTEGER "
        + " , " + CargaPlanejadaContract.COLUNA_ATIVA + " NUMERIC "
        + " , " + CargaPlanejadaContract.COLUNA_QUANTIDADE_REPETICAO + " INTEGER "
        + " , " + CargaPlanejadaContract.COLUNA_ORDEM_REPETICAO + " INTEGER "
		+ " , " + CargaPlanejadaContract.COLUNA_ID_ITEM_SERIE_RA + " INTEGER "
		
		+ " , " + CargaPlanejadaContract.COLUNA_ID_USUARIO_S + " INTEGER "
		
		//+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + CargaPlanejadaContract.TABLE_NAME_SINC + " (" +
        CargaPlanejadaContract.COLUNA_CHAVE + " INTEGER " 
        + " , " + CargaPlanejadaContract.COLUNA_VALOR_CARGA + " REAL "
        + " , " + CargaPlanejadaContract.COLUNA_DATA_INICIO + " INTEGER "
        + " , " + CargaPlanejadaContract.COLUNA_DATA_FINAL + " INTEGER "
        + " , " + CargaPlanejadaContract.COLUNA_ATIVA + " NUMERIC "
        + " , " + CargaPlanejadaContract.COLUNA_QUANTIDADE_REPETICAO + " INTEGER "
        + " , " + CargaPlanejadaContract.COLUNA_ORDEM_REPETICAO + " INTEGER "
		+ " , " + CargaPlanejadaContract.COLUNA_ID_ITEM_SERIE_RA + " INTEGER "
		
		+ " , " + CargaPlanejadaContract.COLUNA_ID_USUARIO_S + " INTEGER "
		
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
		saida += " , FOREIGN KEY (" + CargaPlanejadaContract.COLUNA_ID_ITEM_SERIE_RA + ") REFERENCES " + ItemSerieContract.TABLE_NAME + " (" + ItemSerieContract.COLUNA_CHAVE + ") ";
		return saida;
	}
	
	public static String getSqlDrop() {
		return "DROP TABLE IF EXISTS " + CargaPlanejadaContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + CargaPlanejadaContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + CargaPlanejadaContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + CargaPlanejadaContract.TABLE_NAME_SINC;
    }
    
   
   
    
}