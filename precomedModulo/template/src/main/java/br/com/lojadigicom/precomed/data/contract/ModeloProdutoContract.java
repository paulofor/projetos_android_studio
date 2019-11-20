

package  br.com.lojadigicom.precomed.data.contract;



import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.content.ContentUris;

import java.util.ArrayList;
import java.util.List;

import br.com.lojadigicom.precomed.framework.dao.DaoException;
import br.com.lojadigicom.precomed.framework.dao.DaoItemRetorno;
import br.com.lojadigicom.precomed.framework.dao.MontadorDaoI;
import br.com.lojadigicom.precomed.framework.data.MontadorDaoComposite;
import br.com.lojadigicom.precomed.framework.log.DCLog;
import br.com.lojadigicom.precomed.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.precomed.modelo.ModeloProduto;
import br.com.lojadigicom.precomed.modelo.montador.ModeloProdutoMontador;


public final class ModeloProdutoContract implements BaseColumns {
	
	
	private static AplicacaoContract aplicacaoContract;
	public static void setAplicacaoContract(AplicacaoContract valor) {
		aplicacaoContract = valor;
	}
	public static String getContentAuthority() {
		return aplicacaoContract.getContentAuthority();
	}
	
	
	public static final String PATH = "modelo_produto";
	public static final String COM_COMPLEMENTO = "ComComplemento";
	public static final String COM_RETIRADA = "ComRetirada";

	//public static final Uri CONTENT_URI = aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();

    //public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
    //public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;

    public static final String TABLE_NAME = "modelo_produto";
    public static final String TABLE_NAME_SINC = "modelo_produto_sinc";

	public static Uri getContentUri() {
		return aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();
	}
	public static String getContentType() {
		return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static String getContentItemType() {
		return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static Uri buildModeloProdutoUri(long id) {
    	return ContentUris.withAppendedId(getContentUri(), id);
    }
    
	
	
    //public static final int COL_ID = 0;
   
    public static final String COLUNA_ID_MODELO_PRODUTO = "id_modelo_produto";
    public static final int COL_ID_MODELO_PRODUTO = 0;
    public static final String COLUNA_NOME_MODELO_PRODUTO = "nome_modelo_produto";
    public static final int COL_NOME_MODELO_PRODUTO = 1;
	
 	public static final String COLUNA_CHAVE = COLUNA_ID_MODELO_PRODUTO;
 	public static final String COLUNA_OPERACAO_SINC = "operacao_sinc";
 	public static final int COL_OPERACAO_SINC = 2;
	
	public static final String[] COLS = new String[] { 
			TABLE_NAME + "." + COLUNA_CHAVE
        	, TABLE_NAME + "." +COLUNA_NOME_MODELO_PRODUTO
    };
    
    public static final String[] COLS_SINC = new String[] { 
			TABLE_NAME_SINC + "." + COLUNA_CHAVE
        	, TABLE_NAME_SINC + "." +COLUNA_NOME_MODELO_PRODUTO
			, TABLE_NAME_SINC + "." + COLUNA_OPERACAO_SINC
    };
    
    private static ModeloProdutoOperacao operacao = new ModeloProdutoOperacao();
    
	private static ModeloProdutoFiltro filtro = null;
	public static ModeloProdutoFiltro getFiltro() {
		if (filtro==null) {
			filtro = new ModeloProdutoFiltro();
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
	
	public static String innerJoinModeloProdutoProduto_ReferenteA() {
		return " inner join " + ModeloProdutoProdutoContract.TABLE_NAME + " on " + ModeloProdutoProdutoContract.TABLE_NAME + ".id_modelo_produto_ra = " + TABLE_NAME + "." + COLUNA_CHAVE + " ";  
	}
	public static String innerJoinSincModeloProdutoProduto_ReferenteA() {
		return " inner join " + ModeloProdutoProdutoContract.TABLE_NAME_SINC + " on " + ModeloProdutoProdutoContract.TABLE_NAME_SINC + ".id_modelo_produto_ra = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";  
	}
	public static String outerJoinModeloProdutoProduto_ReferenteA() {
		return " left outer join " + ModeloProdutoProdutoContract.TABLE_NAME + " on " + ModeloProdutoProdutoContract.TABLE_NAME + ".id_modelo_produto_ra = " + TABLE_NAME + "." + COLUNA_CHAVE + " "; 
	}
	public static String outerJoinSincModeloProdutoProduto_ReferenteA() {
		return " left outer join " + ModeloProdutoProdutoContract.TABLE_NAME_SINC + " on " + ModeloProdutoProdutoContract.TABLE_NAME_SINC + ".id_modelo_produto_ra = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";   
	}
	public static MontadorDaoI adicionaMontadorModeloProdutoProdutoReferenteALista (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(ModeloProdutoProdutoContract.getMontador(), "ModeloProdutoProduto_ReferenteA");
		return montador;
	}
 	
	public static String innerJoinProdutoPesquisa_Viabiliza() {
		return " inner join " + ProdutoPesquisaContract.TABLE_NAME + " on " + ProdutoPesquisaContract.TABLE_NAME + ".id_modelo_produto_ra = " + TABLE_NAME + "." + COLUNA_CHAVE + " ";  
	}
	public static String innerJoinSincProdutoPesquisa_Viabiliza() {
		return " inner join " + ProdutoPesquisaContract.TABLE_NAME_SINC + " on " + ProdutoPesquisaContract.TABLE_NAME_SINC + ".id_modelo_produto_ra = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";  
	}
	public static String outerJoinProdutoPesquisa_Viabiliza() {
		return " left outer join " + ProdutoPesquisaContract.TABLE_NAME + " on " + ProdutoPesquisaContract.TABLE_NAME + ".id_modelo_produto_ra = " + TABLE_NAME + "." + COLUNA_CHAVE + " "; 
	}
	public static String outerJoinSincProdutoPesquisa_Viabiliza() {
		return " left outer join " + ProdutoPesquisaContract.TABLE_NAME_SINC + " on " + ProdutoPesquisaContract.TABLE_NAME_SINC + ".id_modelo_produto_ra = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";   
	}
	public static MontadorDaoI adicionaMontadorProdutoPesquisaViabilizaLista (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(ProdutoPesquisaContract.getMontador(), "ProdutoPesquisa_Viabiliza");
		return montador;
	}
 	
	// Com chave
	
	
	
	
	
	public static String camposOrdenados() 
	{
		return " " + TABLE_NAME + "." + COLUNA_ID_MODELO_PRODUTO  
		+ " , " + TABLE_NAME + "." + COLUNA_NOME_MODELO_PRODUTO 
		
		
		;
	}
	
	
	
	public static String camposOrdenadosSinc() 
	{
		return " modelo_produto_sinc.id_modelo_produto " 
		+ " ,modelo_produto_sinc.nome_modelo_produto " 
		
		
		+ " ,modelo_produto_sinc.operacao_sinc "
		;
	}
	
	
	public static MontadorDaoComposite getMontadorComposto() {
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(getMontador(),null);
		return montador;
	}
	public static MontadorDaoI getMontador() {
		return new ModeloProdutoMontador();
	}
	
	
	// ComChaveEstrangeira
  	
	
	// SemChaveEstrangeira
  	
	
	public static Uri buildAllComModeloProdutoProdutoReferenteA() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ModeloProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComModeloProdutoProdutoReferenteA").build();
		return saida;
	}	
	public static Uri buildAllSemModeloProdutoProdutoReferenteA() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ModeloProdutoContract.COM_RETIRADA).build();
		saida = saida.buildUpon().appendPath("SemModeloProdutoProdutoReferenteA").build();
		return saida;
	}	
	
	
	
	public static Uri buildAllComProdutoPesquisaViabiliza() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ModeloProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComProdutoPesquisaViabiliza").build();
		return saida;
	}	
	public static Uri buildAllSemProdutoPesquisaViabiliza() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ModeloProdutoContract.COM_RETIRADA).build();
		saida = saida.buildUpon().appendPath("SemProdutoPesquisaViabiliza").build();
		return saida;
	}	
	
	
	
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
	
	
	public static List<ModeloProduto> converteLista(Cursor data) {
		return converteLista(data, getMontador());
	}
	public static List<ModeloProduto> converteLista(Cursor data, MontadorDaoI montador) {
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