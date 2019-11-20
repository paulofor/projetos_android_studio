

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
import br.com.lojadigicom.capitalexterno.modelo.LinhaProduto;
import br.com.lojadigicom.capitalexterno.modelo.montador.LinhaProdutoMontador;


public final class LinhaProdutoContract implements BaseColumns {
	
	
	private static AplicacaoContract aplicacaoContract;
	public static void setAplicacaoContract(AplicacaoContract valor) {
		aplicacaoContract = valor;
	}
	public static String getContentAuthority() {
		return aplicacaoContract.getContentAuthority();
	}
	
	
	public static final String PATH = "linha_produto";
	public static final String COM_COMPLEMENTO = "ComComplemento";
	public static final String COM_RETIRADA = "ComRetirada";

	//public static final Uri CONTENT_URI = aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();

    //public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
    //public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;

    public static final String TABLE_NAME = "linha_produto";
    public static final String TABLE_NAME_SINC = "linha_produto_sinc";

	public static Uri getContentUri() {
		return aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();
	}
	public static String getContentType() {
		return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static String getContentItemType() {
		return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static Uri buildLinhaProdutoUri(long id) {
    	return ContentUris.withAppendedId(getContentUri(), id);
    }
    
	
	
    //public static final int COL_ID = 0;
   
    public static final String COLUNA_ID_LINHA_PRODUTO = "id_linha_produto";
    public static final int COL_ID_LINHA_PRODUTO = 0;
    public static final String COLUNA_NOME = "nome";
    public static final int COL_NOME = 1;
	
 	public static final String COLUNA_CHAVE = COLUNA_ID_LINHA_PRODUTO;
 	public static final String COLUNA_OPERACAO_SINC = "operacao_sinc";
 	public static final int COL_OPERACAO_SINC = 2;
	
	public static final String[] COLS = new String[] { 
			TABLE_NAME + "." + COLUNA_CHAVE
        	, TABLE_NAME + "." +COLUNA_NOME
    };
    
    public static final String[] COLS_SINC = new String[] { 
			TABLE_NAME_SINC + "." + COLUNA_CHAVE
        	, TABLE_NAME_SINC + "." +COLUNA_NOME
			, TABLE_NAME_SINC + "." + COLUNA_OPERACAO_SINC
    };
    
    private static LinhaProdutoOperacao operacao = new LinhaProdutoOperacao();
    
	private static LinhaProdutoFiltro filtro = null;
	public static LinhaProdutoFiltro getFiltro() {
		if (filtro==null) {
			filtro = new LinhaProdutoFiltro();
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
	
	public static String innerJoinProduto_Possui() {
		return " inner join " + ProdutoContract.TABLE_NAME + " on " + ProdutoContract.TABLE_NAME + ".id_linha_produto_pa = " + TABLE_NAME + "." + COLUNA_CHAVE + " ";  
	}
	public static String innerJoinSincProduto_Possui() {
		return " inner join " + ProdutoContract.TABLE_NAME_SINC + " on " + ProdutoContract.TABLE_NAME_SINC + ".id_linha_produto_pa = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";  
	}
	public static String outerJoinProduto_Possui() {
		return " left outer join " + ProdutoContract.TABLE_NAME + " on " + ProdutoContract.TABLE_NAME + ".id_linha_produto_pa = " + TABLE_NAME + "." + COLUNA_CHAVE + " "; 
	}
	public static String outerJoinSincProduto_Possui() {
		return " left outer join " + ProdutoContract.TABLE_NAME_SINC + " on " + ProdutoContract.TABLE_NAME_SINC + ".id_linha_produto_pa = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";   
	}
	public static MontadorDaoI adicionaMontadorProdutoPossuiLista (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(ProdutoContract.getMontador(), "Produto_Possui");
		return montador;
	}
 	
	// Com chave
	
	
	
	
	
	public static String camposOrdenados() 
	{
		return " " + TABLE_NAME + "." + COLUNA_ID_LINHA_PRODUTO  
		+ " , " + TABLE_NAME + "." + COLUNA_NOME 
		
		
		;
	}
	
	
	
	public static String camposOrdenadosSinc() 
	{
		return " linha_produto_sinc.id_linha_produto " 
		+ " ,linha_produto_sinc.nome " 
		
		
		+ " ,linha_produto_sinc.operacao_sinc "
		;
	}
	
	
	public static MontadorDaoComposite getMontadorComposto() {
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(getMontador(),null);
		return montador;
	}
	public static MontadorDaoI getMontador() {
		return new LinhaProdutoMontador();
	}
	
	
	// ComChaveEstrangeira
  	
	
	// SemChaveEstrangeira
  	
	
	public static Uri buildAllComProdutoPossui() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(LinhaProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComProdutoPossui").build();
		return saida;
	}	
	public static Uri buildAllSemProdutoPossui() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(LinhaProdutoContract.COM_RETIRADA).build();
		saida = saida.buildUpon().appendPath("SemProdutoPossui").build();
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
	
	
	public static List<LinhaProduto> converteLista(Cursor data) {
		return converteLista(data, getMontador());
	}
	public static List<LinhaProduto> converteLista(Cursor data, MontadorDaoI montador) {
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