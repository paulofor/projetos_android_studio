
package  br.com.lojadigicom.treinoacademia.data.helper;

import br.com.lojadigicom.treinoacademia.data.contract.*;

public class ExercicioDbHelper {

	protected static String getSqlChavesEstrangeiras() {
		return  "ALTER TABLE " +
        ExercicioContract.TABLE_NAME + " " +
		" ADD FOREIGN KEY (" + ExercicioContract.COLUNA_ID_GRUPO_MUSCULAR_P + ") REFERENCES " + GrupoMuscularContract.TABLE_NAME + " (" + GrupoMuscularContract.COLUNA_CHAVE + ")  " + 
        ";";
	}

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + ExercicioContract.TABLE_NAME + " (" +
        ExercicioContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
        + " , " + ExercicioContract.COLUNA_DESCRICAO_EXERCICIO + " TEXT "
        + " , " + ExercicioContract.COLUNA_IMAGEM + " TEXT "
        + " , " + ExercicioContract.COLUNA_TITULO + " TEXT "
        + " , " + ExercicioContract.COLUNA_SUBTITULO + " TEXT "
		+ " , " + ExercicioContract.COLUNA_ID_GRUPO_MUSCULAR_P + " INTEGER "
		
		+ " , " + ExercicioContract.COLUNA_ID_USUARIO_S + " INTEGER "
		
		//+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + ExercicioContract.TABLE_NAME_SINC + " (" +
        ExercicioContract.COLUNA_CHAVE + " INTEGER " 
        + " , " + ExercicioContract.COLUNA_DESCRICAO_EXERCICIO + " TEXT "
        + " , " + ExercicioContract.COLUNA_IMAGEM + " TEXT "
        + " , " + ExercicioContract.COLUNA_TITULO + " TEXT "
        + " , " + ExercicioContract.COLUNA_SUBTITULO + " TEXT "
		+ " , " + ExercicioContract.COLUNA_ID_GRUPO_MUSCULAR_P + " INTEGER "
		
		+ " , " + ExercicioContract.COLUNA_ID_USUARIO_S + " INTEGER "
		
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
		saida += " , FOREIGN KEY (" + ExercicioContract.COLUNA_ID_GRUPO_MUSCULAR_P + ") REFERENCES " + GrupoMuscularContract.TABLE_NAME + " (" + GrupoMuscularContract.COLUNA_CHAVE + ") ";
		return saida;
	}
	
	public static String getSqlDrop() {
		return "DROP TABLE IF EXISTS " + ExercicioContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + ExercicioContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + ExercicioContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + ExercicioContract.TABLE_NAME_SINC;
    }
    
   
   
    
}