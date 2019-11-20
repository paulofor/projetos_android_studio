
package  br.com.lojadigicom.treinoacademia.data.helper;

import br.com.lojadigicom.treinoacademia.data.contract.*;

public class GrupoMuscularDbHelper {

	protected static String getSqlChavesEstrangeiras() {
		return  "ALTER TABLE " +
        GrupoMuscularContract.TABLE_NAME + " " +
        ";";
	}

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + GrupoMuscularContract.TABLE_NAME + " (" +
        GrupoMuscularContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
        + " , " + GrupoMuscularContract.COLUNA_NOME_GRUPO + " TEXT "
        + " , " + GrupoMuscularContract.COLUNA_IMAGEM_GRUPO + " TEXT "
		//+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + GrupoMuscularContract.TABLE_NAME_SINC + " (" +
        GrupoMuscularContract.COLUNA_CHAVE + " INTEGER " 
        + " , " + GrupoMuscularContract.COLUNA_NOME_GRUPO + " TEXT "
        + " , " + GrupoMuscularContract.COLUNA_IMAGEM_GRUPO + " TEXT "
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
		return "DROP TABLE IF EXISTS " + GrupoMuscularContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + GrupoMuscularContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + GrupoMuscularContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + GrupoMuscularContract.TABLE_NAME_SINC;
    }
    
   
   
    
}