

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
import br.com.lojadigicom.repcom.modelo.CategoriaProdutoProduto;
import br.com.lojadigicom.repcom.modelo.montador.CategoriaProdutoProdutoMontador;


public final class CategoriaProdutoProdutoContract implements BaseColumns {
	
	
	private static AplicacaoContract aplicacaoContract;
	public static void setAplicacaoContract(AplicacaoContract valor) {
		aplicacaoContract = valor;
	}
	public static String getContentAuthority() {
		return aplicacaoContract.getContentAuthority();
	}
	
	
	public static final String PATH = "categoria_produto_produto";
	public static final String COM_COMPLEMENTO = "ComComplemento";
	public static final String COM_RETIRADA = "ComRetirada";

	//public static final Uri CONTENT_URI = aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();

    //public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
    //public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;

    public static final String TABLE_NAME = "categoria_produto_produto";
    public static final String TABLE_NAME_SINC = "categoria_produto_produto_sinc";

	public static Uri getContentUri() {
		return aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();
	}
	public static String getContentType() {
		return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static String getContentItemType() {
		return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static Uri buildCategoriaProdutoProdutoUri(long id) {
    	return ContentUris.withAppendedId(getContentUri(), id);
    }
    
	public static Uri buildGetPorReferenteACategoriaProdutoUri(long id) {
    	Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(""+id).build();
		saida = saida.buildUpon().appendPath(CategoriaProdutoContract.PATH).build();
    	return saida;
    }
	
	public static Uri buildGetPorReferenteAProdutoUri(long id) {
    	Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(""+id).build();
		saida = saida.buildUpon().appendPath(ProdutoContract.PATH).build();
    	return saida;
    }
	
	
	
    //public static final int COL_ID = 0;
   
    public static final String COLUNA_ID_CATEGORIA_PRODUTO_PRODUTO = "id_categoria_produto_produto";
    public static final int COL_ID_CATEGORIA_PRODUTO_PRODUTO = 0;
    public static final String COLUNA_DATA_INCLUSAO = "data_inclusao";
    public static final int COL_DATA_INCLUSAO = 1;
    public static final String COLUNA_ID_CATEGORIA_PRODUTO_RA = "id_categoria_produto_ra";
    public static final int COL_ID_CATEGORIA_PRODUTO_RA = 2;
	
    public static final String COLUNA_ID_PRODUTO_RA = "id_produto_ra";
    public static final int COL_ID_PRODUTO_RA = 3;
	
	
 	public static final String COLUNA_CHAVE = COLUNA_ID_CATEGORIA_PRODUTO_PRODUTO;
 	public static final String COLUNA_OPERACAO_SINC = "operacao_sinc";
 	public static final int COL_OPERACAO_SINC = 4;
	
	public static final String[] COLS = new String[] { 
			TABLE_NAME + "." + COLUNA_CHAVE
        	, TABLE_NAME + "." +COLUNA_DATA_INCLUSAO
			, TABLE_NAME + "." +COLUNA_ID_CATEGORIA_PRODUTO_RA
	
			, TABLE_NAME + "." +COLUNA_ID_PRODUTO_RA
	
    };
    
    public static final String[] COLS_SINC = new String[] { 
			TABLE_NAME_SINC + "." + COLUNA_CHAVE
        	, TABLE_NAME_SINC + "." +COLUNA_DATA_INCLUSAO
			, TABLE_NAME_SINC + "." +COLUNA_ID_CATEGORIA_PRODUTO_RA
	
			, TABLE_NAME_SINC + "." +COLUNA_ID_PRODUTO_RA
	
			, TABLE_NAME_SINC + "." + COLUNA_OPERACAO_SINC
    };
    
    private static CategoriaProdutoProdutoOperacao operacao = new CategoriaProdutoProdutoOperacao();
    
	private static CategoriaProdutoProdutoFiltro filtro = null;
	public static CategoriaProdutoProdutoFiltro getFiltro() {
		if (filtro==null) {
			filtro = new CategoriaProdutoProdutoFiltro();
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
	public static Uri buildAtualizaSinc() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath("AtualizaSinc").build();
		return saida;
	}
	
	// Sem chave
	
	// Com chave
	
	
	public static String innerJoinCategoriaProduto_ReferenteA() {
		return " inner join " + CategoriaProdutoContract.TABLE_NAME + " on " + CategoriaProdutoContract.TABLE_NAME + "." + CategoriaProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_CATEGORIA_PRODUTO_RA + " ";  
	}
	public static String innerJoinSincCategoriaProduto_ReferenteA() {
		return " inner join " + CategoriaProdutoContract.TABLE_NAME_SINC + " on " + CategoriaProdutoContract.TABLE_NAME_SINC + "." + CategoriaProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_CATEGORIA_PRODUTO_RA + " ";  
	}
	public static String outerJoinCategoriaProduto_ReferenteA() {
		return " left outer join " + CategoriaProdutoContract.TABLE_NAME + " on " + CategoriaProdutoContract.TABLE_NAME + "." + CategoriaProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_CATEGORIA_PRODUTO_RA + " "; 
	}
	public static String outerJoinSincCategoriaProduto_ReferenteA() {
		return " left outer join " + CategoriaProdutoContract.TABLE_NAME_SINC + " on " + CategoriaProdutoContract.TABLE_NAME_SINC + "." + CategoriaProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_CATEGORIA_PRODUTO_RA + " ";   
	}
	
	
	public static MontadorDaoI adicionaMontadorCategoriaProdutoReferenteA (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(CategoriaProdutoContract.getMontador(), "CategoriaProduto_ReferenteA");
		return montador;
	}
	public static Uri adicionaCategoriaProdutoReferenteA(Uri uri) {
		return uri.buildUpon().appendPath("ComCategoriaProdutoReferenteA").build();
	}
 	
	
	public static String innerJoinProduto_ReferenteA() {
		return " inner join " + ProdutoContract.TABLE_NAME + " on " + ProdutoContract.TABLE_NAME + "." + ProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_PRODUTO_RA + " ";  
	}
	public static String innerJoinSincProduto_ReferenteA() {
		return " inner join " + ProdutoContract.TABLE_NAME_SINC + " on " + ProdutoContract.TABLE_NAME_SINC + "." + ProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_PRODUTO_RA + " ";  
	}
	public static String outerJoinProduto_ReferenteA() {
		return " left outer join " + ProdutoContract.TABLE_NAME + " on " + ProdutoContract.TABLE_NAME + "." + ProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_PRODUTO_RA + " "; 
	}
	public static String outerJoinSincProduto_ReferenteA() {
		return " left outer join " + ProdutoContract.TABLE_NAME_SINC + " on " + ProdutoContract.TABLE_NAME_SINC + "." + ProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_PRODUTO_RA + " ";   
	}
	
	
	public static MontadorDaoI adicionaMontadorProdutoReferenteA (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(ProdutoContract.getMontador(), "Produto_ReferenteA");
		return montador;
	}
	public static Uri adicionaProdutoReferenteA(Uri uri) {
		return uri.buildUpon().appendPath("ComProdutoReferenteA").build();
	}
 	
	
	
	
	
	public static String camposOrdenados() 
	{
		return " " + TABLE_NAME + "." + COLUNA_ID_CATEGORIA_PRODUTO_PRODUTO  
		+ " , " + TABLE_NAME + "." + COLUNA_DATA_INCLUSAO 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_CATEGORIA_PRODUTO_RA 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_PRODUTO_RA 
		
		
		;
	}
	
	
	
	public static String camposOrdenadosSinc() 
	{
		return " categoria_produto_produto_sinc.id_categoria_produto_produto " 
		+ " ,categoria_produto_produto_sinc.data_inclusao " 
		+ " ,categoria_produto_produto_sinc.id_categoria_produto_ra " 
		+ " ,categoria_produto_produto_sinc.id_produto_ra " 
		
		
		+ " ,categoria_produto_produto_sinc.operacao_sinc "
		;
	}
	
	
	public static MontadorDaoComposite getMontadorComposto() {
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(getMontador(),null);
		return montador;
	}
	public static MontadorDaoI getMontador() {
		return new CategoriaProdutoProdutoMontador();
	}
	
	
	// ComChaveEstrangeira
  	
	public static Uri buildAllComCategoriaProdutoReferenteA() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(CategoriaProdutoProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComCategoriaProdutoReferenteA").build();
		return saida;
	}
	//  Recoloquei o metodo abaixo pq existe referencia no Provider
	public static Uri buildAllSemCategoriaProdutoReferenteA() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(CategoriaProdutoProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemCategoriaProdutoReferenteA").build();
		return saida;
	}
	
	
	public static Uri buildAllComProdutoReferenteA() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(CategoriaProdutoProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComProdutoReferenteA").build();
		return saida;
	}
	//  Recoloquei o metodo abaixo pq existe referencia no Provider
	public static Uri buildAllSemProdutoReferenteA() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(CategoriaProdutoProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemProdutoReferenteA").build();
		return saida;
	}
	
	
	
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
	
	
	public static List<CategoriaProdutoProduto> converteLista(Cursor data) {
		return converteLista(data, getMontador());
	}
	public static List<CategoriaProdutoProduto> converteLista(Cursor data, MontadorDaoI montador) {
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
	

	public static Uri buildAtualiza() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath("Atualiza").build();
		return saida;
	}
}