

package  br.com.lojadigicom.repcom.data.contract;



import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.content.ContentUris;

import java.util.ArrayList;
import java.util.List;

import br.com.lojadigicom.repcom.framework.dao.DaoException;
import br.com.lojadigicom.repcom.framework.dao.DaoItemRetorno;
import br.com.lojadigicom.repcom.framework.dao.MontadorDaoI;
import br.com.lojadigicom.repcom.framework.data.MontadorDaoComposite;
import br.com.lojadigicom.repcom.framework.log.DCLog;
import br.com.lojadigicom.repcom.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.repcom.modelo.PrecoProduto;
import br.com.lojadigicom.repcom.modelo.montador.PrecoProdutoMontador;


public final class PrecoProdutoContract implements BaseColumns {
	
	
	private static AplicacaoContract aplicacaoContract;
	public static void setAplicacaoContract(AplicacaoContract valor) {
		aplicacaoContract = valor;
	}
	public static String getContentAuthority() {
		return aplicacaoContract.getContentAuthority();
	}
	
	
	public static final String PATH = "preco_produto";
	public static final String COM_COMPLEMENTO = "ComComplemento";
	public static final String COM_RETIRADA = "ComRetirada";

	//public static final Uri CONTENT_URI = aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();

    //public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
    //public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;

    public static final String TABLE_NAME = "preco_produto";
    public static final String TABLE_NAME_SINC = "preco_produto_sinc";

	public static Uri getContentUri() {
		return aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();
	}
	public static String getContentType() {
		return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static String getContentItemType() {
		return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static Uri buildPrecoProdutoUri(long id) {
    	return ContentUris.withAppendedId(getContentUri(), id);
    }
    
	public static Uri buildGetPorPertenceAProdutoUri(long id) {
    	Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(""+id).build();
		saida = saida.buildUpon().appendPath(ProdutoContract.PATH).build();
    	return saida;
    }
	
	
	
    //public static final int COL_ID = 0;
   
    public static final String COLUNA_ID_PRECO_PRODUTO = "id_preco_produto";
    public static final int COL_ID_PRECO_PRODUTO = 0;
    public static final String COLUNA_VALOR_PRECO_AVISTA = "valor_preco_avista";
    public static final int COL_VALOR_PRECO_AVISTA = 1;
    public static final String COLUNA_DATA_INCLUSAO = "data_inclusao";
    public static final int COL_DATA_INCLUSAO = 2;
    public static final String COLUNA_QUANTIDADE_PARCELA = "quantidade_parcela";
    public static final int COL_QUANTIDADE_PARCELA = 3;
    public static final String COLUNA_VALOR_PARCELA = "valor_parcela";
    public static final int COL_VALOR_PARCELA = 4;
    public static final String COLUNA_DATA_EXCLUSAO = "data_exclusao";
    public static final int COL_DATA_EXCLUSAO = 5;
    public static final String COLUNA_ID_PRODUTO_PA = "id_produto_pa";
    public static final int COL_ID_PRODUTO_PA = 6;
	
	
 	public static final String COLUNA_CHAVE = COLUNA_ID_PRECO_PRODUTO;
 	public static final String COLUNA_OPERACAO_SINC = "operacao_sinc";
 	public static final int COL_OPERACAO_SINC = 7;
	
	public static final String[] COLS = new String[] { 
			TABLE_NAME + "." + COLUNA_CHAVE
        	, TABLE_NAME + "." +COLUNA_VALOR_PRECO_AVISTA
        	, TABLE_NAME + "." +COLUNA_DATA_INCLUSAO
        	, TABLE_NAME + "." +COLUNA_QUANTIDADE_PARCELA
        	, TABLE_NAME + "." +COLUNA_VALOR_PARCELA
        	, TABLE_NAME + "." +COLUNA_DATA_EXCLUSAO
			, TABLE_NAME + "." +COLUNA_ID_PRODUTO_PA
	
    };
    
    public static final String[] COLS_SINC = new String[] { 
			TABLE_NAME_SINC + "." + COLUNA_CHAVE
        	, TABLE_NAME_SINC + "." +COLUNA_VALOR_PRECO_AVISTA
        	, TABLE_NAME_SINC + "." +COLUNA_DATA_INCLUSAO
        	, TABLE_NAME_SINC + "." +COLUNA_QUANTIDADE_PARCELA
        	, TABLE_NAME_SINC + "." +COLUNA_VALOR_PARCELA
        	, TABLE_NAME_SINC + "." +COLUNA_DATA_EXCLUSAO
			, TABLE_NAME_SINC + "." +COLUNA_ID_PRODUTO_PA
	
			, TABLE_NAME_SINC + "." + COLUNA_OPERACAO_SINC
    };
    
    private static PrecoProdutoOperacao operacao = new PrecoProdutoOperacao();
    
	private static PrecoProdutoFiltro filtro = null;
	public static PrecoProdutoFiltro getFiltro() {
		if (filtro==null) {
			filtro = new PrecoProdutoFiltro();
		}
		return filtro;
	}
	
	
    public static Uri buildAllSinc() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath("Sinc").build();
		return saida;
	}
	
	public static Uri buildAll() {
		Uri saida = getContentUri();
		return saida;
	}
	
	 public static Uri buildInsereSinc() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath("InsereSinc").build();
		return saida;
	}
	
	// Sem chave
	
	// Com chave
	
	
	public static String innerJoinProduto_PertenceA() {
		return " inner join " + ProdutoContract.TABLE_NAME + " on " + ProdutoContract.TABLE_NAME + "." + ProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_PRODUTO_PA + " ";  
	}
	public static String innerJoinSincProduto_PertenceA() {
		return " inner join " + ProdutoContract.TABLE_NAME_SINC + " on " + ProdutoContract.TABLE_NAME_SINC + "." + ProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_PRODUTO_PA + " ";  
	}
	public static String outerJoinProduto_PertenceA() {
		return " left outer join " + ProdutoContract.TABLE_NAME + " on " + ProdutoContract.TABLE_NAME + "." + ProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_PRODUTO_PA + " "; 
	}
	public static String outerJoinSincProduto_PertenceA() {
		return " left outer join " + ProdutoContract.TABLE_NAME_SINC + " on " + ProdutoContract.TABLE_NAME_SINC + "." + ProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_PRODUTO_PA + " ";   
	}
	
	
	public static MontadorDaoI adicionaMontadorProdutoPertenceA (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(ProdutoContract.getMontador(), "Produto_PertenceA");
		return montador;
	}
	public static Uri adicionaProdutoPertenceA(Uri uri) {
		return uri.buildUpon().appendPath("ComProdutoPertenceA").build();
	}
 	
	
	
	
	
	public static String camposOrdenados() 
	{
		return " " + TABLE_NAME + "." + COLUNA_ID_PRECO_PRODUTO  
		+ " , " + TABLE_NAME + "." + COLUNA_VALOR_PRECO_AVISTA 
		+ " , " + TABLE_NAME + "." + COLUNA_DATA_INCLUSAO 
		+ " , " + TABLE_NAME + "." + COLUNA_QUANTIDADE_PARCELA 
		+ " , " + TABLE_NAME + "." + COLUNA_VALOR_PARCELA 
		+ " , " + TABLE_NAME + "." + COLUNA_DATA_EXCLUSAO 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_PRODUTO_PA 
		
		
		;
	}
	
	
	
	public static String camposOrdenadosSinc() 
	{
		return " preco_produto_sinc.id_preco_produto " 
		+ " ,preco_produto_sinc.valor_preco_avista " 
		+ " ,preco_produto_sinc.data_inclusao " 
		+ " ,preco_produto_sinc.quantidade_parcela " 
		+ " ,preco_produto_sinc.valor_parcela " 
		+ " ,preco_produto_sinc.data_exclusao " 
		+ " ,preco_produto_sinc.id_produto_pa " 
		
		
		+ " ,preco_produto_sinc.operacao_sinc "
		;
	}
	
	
	public static MontadorDaoComposite getMontadorComposto() {
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(getMontador(),null);
		return montador;
	}
	public static MontadorDaoI getMontador() {
		return new PrecoProdutoMontador();
	}
	
	
	// ComChaveEstrangeira
  	
	public static Uri buildAllComProdutoPertenceA() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(PrecoProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComProdutoPertenceA").build();
		return saida;
	}
	/*
	public static Uri buildAllSemProdutoPertenceA() {
		Uri saida = CONTENT_URI;
		saida = saida.buildUpon().appendPath(PrecoProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemProdutoPertenceA").build();
		return saida;
	}
	*/	
	
	
	// SemChaveEstrangeira
  	
	
	public static Uri buildDeleteAllSinc() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath("DeleteAllSinc").build();
		return saida;
	}
	public static Uri buildDeleteAllRecreate() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath("DeleteAllRecreate").build();
		return saida;
	}
	public static Uri buildDeleteSinc(int id) {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath("DeleteSinc").build();
		return ContentUris.withAppendedId(saida, id);
	}
	
	
	public static List<PrecoProduto> converteLista(Cursor data) {
		return converteLista(data, getMontador());
	}
	public static List<PrecoProduto> converteLista(Cursor data, MontadorDaoI montador) {
        //MontadorDaoI montador = getMontador();
        List lista = new ArrayList();
        try {
            lista = getListaSqlListaInterna(data, montador);
        } catch (Exception e) {
            lista = new ArrayList();
        }
        return lista;
    }
    private static List getListaSqlListaInterna(Cursor c, MontadorDaoI montador) throws DaoException {
        ArrayList<DCIObjetoDominio> listaSaida = new ArrayList<DCIObjetoDominio>();
        int numRows = c.getCount();
        boolean insere = false;
        DCIObjetoDominio objeto = null;
        DaoItemRetorno retorno = null;
        while (c.moveToNext()) {
            try {
                retorno = montador.extraiRegistroListaInterna(c, objeto);
                insere = retorno.getInsere();
                objeto = retorno.getObjeto();
            } catch (Exception e) {
                DCLog.e(DCLog.DATABASE_ERRO_CORE, null, e);
            }
            if (insere) {
                listaSaida.add(objeto);
            }
        }
        return listaSaida;
    }
	
}