

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
import br.com.lojadigicom.repcom.modelo.ProdutoPedidoFornecedor;
import br.com.lojadigicom.repcom.modelo.montador.ProdutoPedidoFornecedorMontador;


public final class ProdutoPedidoFornecedorContract implements BaseColumns {
	
	
	private static AplicacaoContract aplicacaoContract;
	public static void setAplicacaoContract(AplicacaoContract valor) {
		aplicacaoContract = valor;
	}
	public static String getContentAuthority() {
		return aplicacaoContract.getContentAuthority();
	}
	
	
	public static final String PATH = "produto_pedido_fornecedor";
	public static final String COM_COMPLEMENTO = "ComComplemento";
	public static final String COM_RETIRADA = "ComRetirada";

	//public static final Uri CONTENT_URI = aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();

    //public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
    //public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;

    public static final String TABLE_NAME = "produto_pedido_fornecedor";
    public static final String TABLE_NAME_SINC = "produto_pedido_fornecedor_sinc";

	public static Uri getContentUri() {
		return aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();
	}
	public static String getContentType() {
		return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static String getContentItemType() {
		return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static Uri buildProdutoPedidoFornecedorUri(long id) {
    	return ContentUris.withAppendedId(getContentUri(), id);
    }
    
	public static Uri buildGetPorAssociadaPedidoFornecedorUri(long id) {
    	Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(""+id).build();
		saida = saida.buildUpon().appendPath(PedidoFornecedorContract.PATH).build();
    	return saida;
    }
	
	public static Uri buildGetPorAssociadaProdutoUri(long id) {
    	Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(""+id).build();
		saida = saida.buildUpon().appendPath(ProdutoContract.PATH).build();
    	return saida;
    }
	
	
	
    //public static final int COL_ID = 0;
   
    public static final String COLUNA_ID_PRODUTO_PEDIDO_FORNECEDOR = "id_produto_pedido_fornecedor";
    public static final int COL_ID_PRODUTO_PEDIDO_FORNECEDOR = 0;
    public static final String COLUNA_ID_PEDIDO_FORNECEDOR_A = "id_pedido_fornecedor_a";
    public static final int COL_ID_PEDIDO_FORNECEDOR_A = 1;
	
    public static final String COLUNA_ID_PRODUTO_A = "id_produto_a";
    public static final int COL_ID_PRODUTO_A = 2;
	
	
 	public static final String COLUNA_CHAVE = COLUNA_ID_PRODUTO_PEDIDO_FORNECEDOR;
 	public static final String COLUNA_OPERACAO_SINC = "operacao_sinc";
 	public static final int COL_OPERACAO_SINC = 3;
	
	public static final String[] COLS = new String[] { 
			TABLE_NAME + "." + COLUNA_CHAVE
			, TABLE_NAME + "." +COLUNA_ID_PEDIDO_FORNECEDOR_A
	
			, TABLE_NAME + "." +COLUNA_ID_PRODUTO_A
	
    };
    
    public static final String[] COLS_SINC = new String[] { 
			TABLE_NAME_SINC + "." + COLUNA_CHAVE
			, TABLE_NAME_SINC + "." +COLUNA_ID_PEDIDO_FORNECEDOR_A
	
			, TABLE_NAME_SINC + "." +COLUNA_ID_PRODUTO_A
	
			, TABLE_NAME_SINC + "." + COLUNA_OPERACAO_SINC
    };
    
    private static ProdutoPedidoFornecedorOperacao operacao = new ProdutoPedidoFornecedorOperacao();
    
	private static ProdutoPedidoFornecedorFiltro filtro = null;
	public static ProdutoPedidoFornecedorFiltro getFiltro() {
		if (filtro==null) {
			filtro = new ProdutoPedidoFornecedorFiltro();
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
	
	
	public static String innerJoinPedidoFornecedor_Associada() {
		return " inner join " + PedidoFornecedorContract.TABLE_NAME + " on " + PedidoFornecedorContract.TABLE_NAME + "." + PedidoFornecedorContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_PEDIDO_FORNECEDOR_A + " ";  
	}
	public static String innerJoinSincPedidoFornecedor_Associada() {
		return " inner join " + PedidoFornecedorContract.TABLE_NAME_SINC + " on " + PedidoFornecedorContract.TABLE_NAME_SINC + "." + PedidoFornecedorContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_PEDIDO_FORNECEDOR_A + " ";  
	}
	public static String outerJoinPedidoFornecedor_Associada() {
		return " left outer join " + PedidoFornecedorContract.TABLE_NAME + " on " + PedidoFornecedorContract.TABLE_NAME + "." + PedidoFornecedorContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_PEDIDO_FORNECEDOR_A + " "; 
	}
	public static String outerJoinSincPedidoFornecedor_Associada() {
		return " left outer join " + PedidoFornecedorContract.TABLE_NAME_SINC + " on " + PedidoFornecedorContract.TABLE_NAME_SINC + "." + PedidoFornecedorContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_PEDIDO_FORNECEDOR_A + " ";   
	}
	
	
	public static MontadorDaoI adicionaMontadorPedidoFornecedorAssociada (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(PedidoFornecedorContract.getMontador(), "PedidoFornecedor_Associada");
		return montador;
	}
	public static Uri adicionaPedidoFornecedorAssociada(Uri uri) {
		return uri.buildUpon().appendPath("ComPedidoFornecedorAssociada").build();
	}
 	
	
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
 	
	
	
	
	
	public static String camposOrdenados() 
	{
		return " " + TABLE_NAME + "." + COLUNA_ID_PRODUTO_PEDIDO_FORNECEDOR  
		+ " , " + TABLE_NAME + "." + COLUNA_ID_PEDIDO_FORNECEDOR_A 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_PRODUTO_A 
		
		
		;
	}
	
	
	
	public static String camposOrdenadosSinc() 
	{
		return " produto_pedido_fornecedor_sinc.id_produto_pedido_fornecedor " 
		+ " ,produto_pedido_fornecedor_sinc.id_pedido_fornecedor_a " 
		+ " ,produto_pedido_fornecedor_sinc.id_produto_a " 
		
		
		+ " ,produto_pedido_fornecedor_sinc.operacao_sinc "
		;
	}
	
	
	public static MontadorDaoComposite getMontadorComposto() {
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(getMontador(),null);
		return montador;
	}
	public static MontadorDaoI getMontador() {
		return new ProdutoPedidoFornecedorMontador();
	}
	
	
	// ComChaveEstrangeira
  	
	public static Uri buildAllComPedidoFornecedorAssociada() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ProdutoPedidoFornecedorContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComPedidoFornecedorAssociada").build();
		return saida;
	}
	/*
	public static Uri buildAllSemPedidoFornecedorAssociada() {
		Uri saida = CONTENT_URI;
		saida = saida.buildUpon().appendPath(ProdutoPedidoFornecedorContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemPedidoFornecedorAssociada").build();
		return saida;
	}
	*/	
	
	public static Uri buildAllComProdutoAssociada() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ProdutoPedidoFornecedorContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComProdutoAssociada").build();
		return saida;
	}
	/*
	public static Uri buildAllSemProdutoAssociada() {
		Uri saida = CONTENT_URI;
		saida = saida.buildUpon().appendPath(ProdutoPedidoFornecedorContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemProdutoAssociada").build();
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
	
	
	public static List<ProdutoPedidoFornecedor> converteLista(Cursor data) {
		return converteLista(data, getMontador());
	}
	public static List<ProdutoPedidoFornecedor> converteLista(Cursor data, MontadorDaoI montador) {
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