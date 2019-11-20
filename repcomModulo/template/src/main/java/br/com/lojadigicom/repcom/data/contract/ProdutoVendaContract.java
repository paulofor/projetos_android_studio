

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
import br.com.lojadigicom.repcom.modelo.ProdutoVenda;
import br.com.lojadigicom.repcom.modelo.montador.ProdutoVendaMontador;


public final class ProdutoVendaContract implements BaseColumns {
	
	
	private static AplicacaoContract aplicacaoContract;
	public static void setAplicacaoContract(AplicacaoContract valor) {
		aplicacaoContract = valor;
	}
	public static String getContentAuthority() {
		return aplicacaoContract.getContentAuthority();
	}
	
	
	public static final String PATH = "produto_venda";
	public static final String COM_COMPLEMENTO = "ComComplemento";
	public static final String COM_RETIRADA = "ComRetirada";

	//public static final Uri CONTENT_URI = aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();

    //public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
    //public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;

    public static final String TABLE_NAME = "produto_venda";
    public static final String TABLE_NAME_SINC = "produto_venda_sinc";

	public static Uri getContentUri() {
		return aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();
	}
	public static String getContentType() {
		return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static String getContentItemType() {
		return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static Uri buildProdutoVendaUri(long id) {
    	return ContentUris.withAppendedId(getContentUri(), id);
    }
    
	public static Uri buildGetPorAssociadaProdutoUri(long id) {
    	Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(""+id).build();
		saida = saida.buildUpon().appendPath(ProdutoContract.PATH).build();
    	return saida;
    }
	
	public static Uri buildGetPorAssociadaVendaUri(long id) {
    	Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(""+id).build();
		saida = saida.buildUpon().appendPath(VendaContract.PATH).build();
    	return saida;
    }
	
	
	
    //public static final int COL_ID = 0;
   
    public static final String COLUNA_ID_PRODUTO_VENDA = "id_produto_venda";
    public static final int COL_ID_PRODUTO_VENDA = 0;
    public static final String COLUNA_QUANTIDADE = "quantidade";
    public static final int COL_QUANTIDADE = 1;
    public static final String COLUNA_VALOR_TOTAL = "valor_total";
    public static final int COL_VALOR_TOTAL = 2;
    public static final String COLUNA_VALOR_ITEM = "valor_item";
    public static final int COL_VALOR_ITEM = 3;
    public static final String COLUNA_ID_PRODUTO_A = "id_produto_a";
    public static final int COL_ID_PRODUTO_A = 4;
	
    public static final String COLUNA_ID_VENDA_A = "id_venda_a";
    public static final int COL_ID_VENDA_A = 5;
	
	
 	public static final String COLUNA_CHAVE = COLUNA_ID_PRODUTO_VENDA;
 	public static final String COLUNA_OPERACAO_SINC = "operacao_sinc";
 	public static final int COL_OPERACAO_SINC = 6;
	
	public static final String[] COLS = new String[] { 
			TABLE_NAME + "." + COLUNA_CHAVE
        	, TABLE_NAME + "." +COLUNA_QUANTIDADE
        	, TABLE_NAME + "." +COLUNA_VALOR_TOTAL
        	, TABLE_NAME + "." +COLUNA_VALOR_ITEM
			, TABLE_NAME + "." +COLUNA_ID_PRODUTO_A
	
			, TABLE_NAME + "." +COLUNA_ID_VENDA_A
	
    };
    
    public static final String[] COLS_SINC = new String[] { 
			TABLE_NAME_SINC + "." + COLUNA_CHAVE
        	, TABLE_NAME_SINC + "." +COLUNA_QUANTIDADE
        	, TABLE_NAME_SINC + "." +COLUNA_VALOR_TOTAL
        	, TABLE_NAME_SINC + "." +COLUNA_VALOR_ITEM
			, TABLE_NAME_SINC + "." +COLUNA_ID_PRODUTO_A
	
			, TABLE_NAME_SINC + "." +COLUNA_ID_VENDA_A
	
			, TABLE_NAME_SINC + "." + COLUNA_OPERACAO_SINC
    };
    
    private static ProdutoVendaOperacao operacao = new ProdutoVendaOperacao();
    
	private static ProdutoVendaFiltro filtro = null;
	public static ProdutoVendaFiltro getFiltro() {
		if (filtro==null) {
			filtro = new ProdutoVendaFiltro();
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
	
	
	public static String innerJoinProduto_Associada() {
		return " inner join " + ProdutoContract.TABLE_NAME + " on " + ProdutoContract.TABLE_NAME + "." + ProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_PRODUTO_A + " ";  
	}
	public static String innerJoinSincProduto_Associada() {
		return " inner join " + ProdutoContract.TABLE_NAME_SINC + " on " + ProdutoContract.TABLE_NAME_SINC + "." + ProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_PRODUTO_A + " ";  
	}
	public static String outerJoinProduto_Associada() {
		return " left outer join " + ProdutoContract.TABLE_NAME + " on " + ProdutoContract.TABLE_NAME + "." + ProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_PRODUTO_A + " "; 
	}
	public static String outerJoinSincProduto_Associada() {
		return " left outer join " + ProdutoContract.TABLE_NAME_SINC + " on " + ProdutoContract.TABLE_NAME_SINC + "." + ProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_PRODUTO_A + " ";   
	}
	
	
	public static MontadorDaoI adicionaMontadorProdutoAssociada (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(ProdutoContract.getMontador(), "Produto_Associada");
		return montador;
	}
	public static Uri adicionaProdutoAssociada(Uri uri) {
		return uri.buildUpon().appendPath("ComProdutoAssociada").build();
	}
 	
	
	public static String innerJoinVenda_Associada() {
		return " inner join " + VendaContract.TABLE_NAME + " on " + VendaContract.TABLE_NAME + "." + VendaContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_VENDA_A + " ";  
	}
	public static String innerJoinSincVenda_Associada() {
		return " inner join " + VendaContract.TABLE_NAME_SINC + " on " + VendaContract.TABLE_NAME_SINC + "." + VendaContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_VENDA_A + " ";  
	}
	public static String outerJoinVenda_Associada() {
		return " left outer join " + VendaContract.TABLE_NAME + " on " + VendaContract.TABLE_NAME + "." + VendaContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_VENDA_A + " "; 
	}
	public static String outerJoinSincVenda_Associada() {
		return " left outer join " + VendaContract.TABLE_NAME_SINC + " on " + VendaContract.TABLE_NAME_SINC + "." + VendaContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_VENDA_A + " ";   
	}
	
	
	public static MontadorDaoI adicionaMontadorVendaAssociada (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(VendaContract.getMontador(), "Venda_Associada");
		return montador;
	}
	public static Uri adicionaVendaAssociada(Uri uri) {
		return uri.buildUpon().appendPath("ComVendaAssociada").build();
	}
 	
	
	
	
	
	public static String camposOrdenados() 
	{
		return " " + TABLE_NAME + "." + COLUNA_ID_PRODUTO_VENDA  
		+ " , " + TABLE_NAME + "." + COLUNA_QUANTIDADE 
		+ " , " + TABLE_NAME + "." + COLUNA_VALOR_TOTAL 
		+ " , " + TABLE_NAME + "." + COLUNA_VALOR_ITEM 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_PRODUTO_A 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_VENDA_A 
		
		
		;
	}
	
	
	
	public static String camposOrdenadosSinc() 
	{
		return " produto_venda_sinc.id_produto_venda " 
		+ " ,produto_venda_sinc.quantidade " 
		+ " ,produto_venda_sinc.valor_total " 
		+ " ,produto_venda_sinc.valor_item " 
		+ " ,produto_venda_sinc.id_produto_a " 
		+ " ,produto_venda_sinc.id_venda_a " 
		
		
		+ " ,produto_venda_sinc.operacao_sinc "
		;
	}
	
	
	public static MontadorDaoComposite getMontadorComposto() {
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(getMontador(),null);
		return montador;
	}
	public static MontadorDaoI getMontador() {
		return new ProdutoVendaMontador();
	}
	
	
	// ComChaveEstrangeira
  	
	public static Uri buildAllComProdutoAssociada() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ProdutoVendaContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComProdutoAssociada").build();
		return saida;
	}
	//  Recoloquei o metodo abaixo pq existe referencia no Provider
	public static Uri buildAllSemProdutoAssociada() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ProdutoVendaContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemProdutoAssociada").build();
		return saida;
	}
	
	
	public static Uri buildAllComVendaAssociada() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ProdutoVendaContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComVendaAssociada").build();
		return saida;
	}
	//  Recoloquei o metodo abaixo pq existe referencia no Provider
	public static Uri buildAllSemVendaAssociada() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ProdutoVendaContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemVendaAssociada").build();
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
	
	
	public static List<ProdutoVenda> converteLista(Cursor data) {
		return converteLista(data, getMontador());
	}
	public static List<ProdutoVenda> converteLista(Cursor data, MontadorDaoI montador) {
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