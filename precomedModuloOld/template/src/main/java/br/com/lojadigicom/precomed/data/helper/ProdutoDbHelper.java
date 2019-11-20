
package  br.com.lojadigicom.precomed.data.helper;

import br.com.lojadigicom.precomed.data.contract.*;

public class ProdutoDbHelper {

	protected static String getSqlChavesEstrangeiras() {
		return  "ALTER TABLE " +
        ProdutoContract.TABLE_NAME + " " +
		" ADD FOREIGN KEY (" + ProdutoContract.COLUNA_ID_LOJA_VIRTUAL_LE + ") REFERENCES " + LojaVirtualContract.TABLE_NAME + " (" + LojaVirtualContract.COLUNA_CHAVE + ")  " + 
		" ADD FOREIGN KEY (" + ProdutoContract.COLUNA_ID_MARCA_P + ") REFERENCES " + MarcaContract.TABLE_NAME + " (" + MarcaContract.COLUNA_CHAVE + ")  " + 
        ";";
	}

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + ProdutoContract.TABLE_NAME + " (" +
        ProdutoContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
        + " , " + ProdutoContract.COLUNA_URL_ORIGEM + " TEXT "
        + " , " + ProdutoContract.COLUNA_IMAGEM_LOCAL + " TEXT "
        + " , " + ProdutoContract.COLUNA_DATA_INCLUSAO + " INTEGER "
        + " , " + ProdutoContract.COLUNA_URL + " TEXT "
        + " , " + ProdutoContract.COLUNA_NOME + " TEXT "
        + " , " + ProdutoContract.COLUNA_POSICAO_PRODUTO + " INTEGER "
        + " , " + ProdutoContract.COLUNA_IMAGEM + " TEXT "
        + " , " + ProdutoContract.COLUNA_CODIGO_MS + " TEXT "
        + " , " + ProdutoContract.COLUNA_PRINCIPIO_ATIVO + " TEXT "
        + " , " + ProdutoContract.COLUNA_POSSUI_ESTOQUE + " NUMERIC "
		+ " , " + ProdutoContract.COLUNA_ID_LOJA_VIRTUAL_LE + " INTEGER "
		
		+ " , " + ProdutoContract.COLUNA_ID_MARCA_P + " INTEGER "
		
		//+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + ProdutoContract.TABLE_NAME_SINC + " (" +
        ProdutoContract.COLUNA_CHAVE + " INTEGER " 
        + " , " + ProdutoContract.COLUNA_URL_ORIGEM + " TEXT "
        + " , " + ProdutoContract.COLUNA_IMAGEM_LOCAL + " TEXT "
        + " , " + ProdutoContract.COLUNA_DATA_INCLUSAO + " INTEGER "
        + " , " + ProdutoContract.COLUNA_URL + " TEXT "
        + " , " + ProdutoContract.COLUNA_NOME + " TEXT "
        + " , " + ProdutoContract.COLUNA_POSICAO_PRODUTO + " INTEGER "
        + " , " + ProdutoContract.COLUNA_IMAGEM + " TEXT "
        + " , " + ProdutoContract.COLUNA_CODIGO_MS + " TEXT "
        + " , " + ProdutoContract.COLUNA_PRINCIPIO_ATIVO + " TEXT "
        + " , " + ProdutoContract.COLUNA_POSSUI_ESTOQUE + " NUMERIC "
		+ " , " + ProdutoContract.COLUNA_ID_LOJA_VIRTUAL_LE + " INTEGER "
		
		+ " , " + ProdutoContract.COLUNA_ID_MARCA_P + " INTEGER "
		
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
		saida += " , FOREIGN KEY (" + ProdutoContract.COLUNA_ID_LOJA_VIRTUAL_LE + ") REFERENCES " + LojaVirtualContract.TABLE_NAME + " (" + LojaVirtualContract.COLUNA_CHAVE + ") ";
		saida += " , FOREIGN KEY (" + ProdutoContract.COLUNA_ID_MARCA_P + ") REFERENCES " + MarcaContract.TABLE_NAME + " (" + MarcaContract.COLUNA_CHAVE + ") ";
		return saida;
	}
	
	public static String getSqlDrop() {
		return "DROP TABLE IF EXISTS " + ProdutoContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + ProdutoContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + ProdutoContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + ProdutoContract.TABLE_NAME_SINC;
    }
    
   
   
    
}