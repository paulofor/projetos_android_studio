

package  br.com.lojadigicom.capitalexterno.data.contract;



import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.content.ContentUris;

import java.util.ArrayList;
import java.util.List;

import br.com.lojadigicom.capitalexterno.framework.dao.DaoException;
import br.com.lojadigicom.capitalexterno.framework.dao.DaoItemRetorno;
import br.com.lojadigicom.capitalexterno.framework.dao.MontadorDaoI;
import br.com.lojadigicom.capitalexterno.framework.data.MontadorDaoComposite;
import br.com.lojadigicom.capitalexterno.framework.log.DCLog;
import br.com.lojadigicom.capitalexterno.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.capitalexterno.modelo.Produto;
import br.com.lojadigicom.capitalexterno.modelo.montador.ProdutoMontador;


public final class ProdutoContract implements BaseColumns {
	
	
	private static AplicacaoContract aplicacaoContract;
	public static void setAplicacaoContract(AplicacaoContract valor) {
		aplicacaoContract = valor;
	}
	public static String getContentAuthority() {
		return aplicacaoContract.getContentAuthority();
	}
	
	
	public static final String PATH = "produto";
	public static final String COM_COMPLEMENTO = "ComComplemento";
	public static final String COM_RETIRADA = "ComRetirada";

	//public static final Uri CONTENT_URI = aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();

    //public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
    //public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;

    public static final String TABLE_NAME = "produto";
    public static final String TABLE_NAME_SINC = "produto_sinc";

	public static Uri getContentUri() {
		return aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();
	}
	public static String getContentType() {
		return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static String getContentItemType() {
		return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static Uri buildProdutoUri(long id) {
    	return ContentUris.withAppendedId(getContentUri(), id);
    }
    
	public static Uri buildGetPorPertenceALinhaProdutoUri(long id) {
    	Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(""+id).build();
		saida = saida.buildUpon().appendPath(LinhaProdutoContract.PATH).build();
    	return saida;
    }
	
	
	
    //public static final int COL_ID = 0;
   
    public static final String COLUNA_ID_PRODUTO = "id_produto";
    public static final int COL_ID_PRODUTO = 0;
    public static final String COLUNA_NOME = "nome";
    public static final int COL_NOME = 1;
    public static final String COLUNA_ID_LINHA_PRODUTO_PA = "id_linha_produto_pa";
    public static final int COL_ID_LINHA_PRODUTO_PA = 2;
	
	
 	public static final String COLUNA_CHAVE = COLUNA_ID_PRODUTO;
 	public static final String COLUNA_OPERACAO_SINC = "operacao_sinc";
 	public static final int COL_OPERACAO_SINC = 3;
	
	public static final String[] COLS = new String[] { 
			TABLE_NAME + "." + COLUNA_CHAVE
        	, TABLE_NAME + "." +COLUNA_NOME
			, TABLE_NAME + "." +COLUNA_ID_LINHA_PRODUTO_PA
	
    };
    
    public static final String[] COLS_SINC = new String[] { 
			TABLE_NAME_SINC + "." + COLUNA_CHAVE
        	, TABLE_NAME_SINC + "." +COLUNA_NOME
			, TABLE_NAME_SINC + "." +COLUNA_ID_LINHA_PRODUTO_PA
	
			, TABLE_NAME_SINC + "." + COLUNA_OPERACAO_SINC
    };
    
    private static ProdutoOperacao operacao = new ProdutoOperacao();
    
	private static ProdutoFiltro filtro = null;
	public static ProdutoFiltro getFiltro() {
		if (filtro==null) {
			filtro = new ProdutoFiltro();
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
	
	
	public static String innerJoinLinhaProduto_PertenceA() {
		return " inner join " + LinhaProdutoContract.TABLE_NAME + " on " + LinhaProdutoContract.TABLE_NAME + "." + LinhaProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_LINHA_PRODUTO_PA + " ";  
	}
	public static String innerJoinSincLinhaProduto_PertenceA() {
		return " inner join " + LinhaProdutoContract.TABLE_NAME_SINC + " on " + LinhaProdutoContract.TABLE_NAME_SINC + "." + LinhaProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_LINHA_PRODUTO_PA + " ";  
	}
	public static String outerJoinLinhaProduto_PertenceA() {
		return " left outer join " + LinhaProdutoContract.TABLE_NAME + " on " + LinhaProdutoContract.TABLE_NAME + "." + LinhaProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_LINHA_PRODUTO_PA + " "; 
	}
	public static String outerJoinSincLinhaProduto_PertenceA() {
		return " left outer join " + LinhaProdutoContract.TABLE_NAME_SINC + " on " + LinhaProdutoContract.TABLE_NAME_SINC + "." + LinhaProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_LINHA_PRODUTO_PA + " ";   
	}
	
	
	public static MontadorDaoI adicionaMontadorLinhaProdutoPertenceA (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(LinhaProdutoContract.getMontador(), "LinhaProduto_PertenceA");
		return montador;
	}
	public static Uri adicionaLinhaProdutoPertenceA(Uri uri) {
		return uri.buildUpon().appendPath("ComLinhaProdutoPertenceA").build();
	}
 	
	
	
	
	
	public static String camposOrdenados() 
	{
		return " " + TABLE_NAME + "." + COLUNA_ID_PRODUTO  
		+ " , " + TABLE_NAME + "." + COLUNA_NOME 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_LINHA_PRODUTO_PA 
		
		
		;
	}
	
	
	
	public static String camposOrdenadosSinc() 
	{
		return " produto_sinc.id_produto " 
		+ " ,produto_sinc.nome " 
		+ " ,produto_sinc.id_linha_produto_pa " 
		
		
		+ " ,produto_sinc.operacao_sinc "
		;
	}
	
	
	public static MontadorDaoComposite getMontadorComposto() {
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(getMontador(),null);
		return montador;
	}
	public static MontadorDaoI getMontador() {
		return new ProdutoMontador();
	}
	
	
	// ComChaveEstrangeira
  	
	public static Uri buildAllComLinhaProdutoPertenceA() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComLinhaProdutoPertenceA").build();
		return saida;
	}
	//  Recoloquei o metodo abaixo pq existe referencia no Provider
	public static Uri buildAllSemLinhaProdutoPertenceA() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemLinhaProdutoPertenceA").build();
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
	
	
	public static List<Produto> converteLista(Cursor data) {
		return converteLista(data, getMontador());
	}
	public static List<Produto> converteLista(Cursor data, MontadorDaoI montador) {
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