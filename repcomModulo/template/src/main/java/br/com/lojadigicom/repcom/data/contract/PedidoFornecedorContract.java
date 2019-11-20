

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
import br.com.lojadigicom.repcom.modelo.PedidoFornecedor;
import br.com.lojadigicom.repcom.modelo.montador.PedidoFornecedorMontador;


public final class PedidoFornecedorContract implements BaseColumns {
	
	
	private static AplicacaoContract aplicacaoContract;
	public static void setAplicacaoContract(AplicacaoContract valor) {
		aplicacaoContract = valor;
	}
	public static String getContentAuthority() {
		return aplicacaoContract.getContentAuthority();
	}
	
	
	public static final String PATH = "pedido_fornecedor";
	public static final String COM_COMPLEMENTO = "ComComplemento";
	public static final String COM_RETIRADA = "ComRetirada";

	//public static final Uri CONTENT_URI = aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();

    //public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
    //public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;

    public static final String TABLE_NAME = "pedido_fornecedor";
    public static final String TABLE_NAME_SINC = "pedido_fornecedor_sinc";

	public static Uri getContentUri() {
		return aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();
	}
	public static String getContentType() {
		return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static String getContentItemType() {
		return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static Uri buildPedidoFornecedorUri(long id) {
    	return ContentUris.withAppendedId(getContentUri(), id);
    }
    
	
	
    //public static final int COL_ID = 0;
   
    public static final String COLUNA_ID_PEDIDO_FORNECEDOR = "id_pedido_fornecedor";
    public static final int COL_ID_PEDIDO_FORNECEDOR = 0;
	
 	public static final String COLUNA_CHAVE = COLUNA_ID_PEDIDO_FORNECEDOR;
 	public static final String COLUNA_OPERACAO_SINC = "operacao_sinc";
 	public static final int COL_OPERACAO_SINC = 1;
	
	public static final String[] COLS = new String[] { 
			TABLE_NAME + "." + COLUNA_CHAVE
    };
    
    public static final String[] COLS_SINC = new String[] { 
			TABLE_NAME_SINC + "." + COLUNA_CHAVE
			, TABLE_NAME_SINC + "." + COLUNA_OPERACAO_SINC
    };
    
    private static PedidoFornecedorOperacao operacao = new PedidoFornecedorOperacao();
    
	private static PedidoFornecedorFiltro filtro = null;
	public static PedidoFornecedorFiltro getFiltro() {
		if (filtro==null) {
			filtro = new PedidoFornecedorFiltro();
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
	
	public static String innerJoinProdutoPedidoFornecedor_Associada() {
		return " inner join " + ProdutoPedidoFornecedorContract.TABLE_NAME + " on " + ProdutoPedidoFornecedorContract.TABLE_NAME + ".id_pedido_fornecedor_a = " + TABLE_NAME + "." + COLUNA_CHAVE + " ";  
	}
	public static String innerJoinSincProdutoPedidoFornecedor_Associada() {
		return " inner join " + ProdutoPedidoFornecedorContract.TABLE_NAME_SINC + " on " + ProdutoPedidoFornecedorContract.TABLE_NAME_SINC + ".id_pedido_fornecedor_a = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";  
	}
	public static String outerJoinProdutoPedidoFornecedor_Associada() {
		return " left outer join " + ProdutoPedidoFornecedorContract.TABLE_NAME + " on " + ProdutoPedidoFornecedorContract.TABLE_NAME + ".id_pedido_fornecedor_a = " + TABLE_NAME + "." + COLUNA_CHAVE + " "; 
	}
	public static String outerJoinSincProdutoPedidoFornecedor_Associada() {
		return " left outer join " + ProdutoPedidoFornecedorContract.TABLE_NAME_SINC + " on " + ProdutoPedidoFornecedorContract.TABLE_NAME_SINC + ".id_pedido_fornecedor_a = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";   
	}
	public static MontadorDaoI adicionaMontadorProdutoPedidoFornecedorAssociadaLista (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(ProdutoPedidoFornecedorContract.getMontador(), "ProdutoPedidoFornecedor_Associada");
		return montador;
	}
 	
	// Com chave
	
	
	
	
	
	public static String camposOrdenados() 
	{
		return " " + TABLE_NAME + "." + COLUNA_ID_PEDIDO_FORNECEDOR  
		
		
		;
	}
	
	
	
	public static String camposOrdenadosSinc() 
	{
		return " pedido_fornecedor_sinc.id_pedido_fornecedor " 
		
		
		+ " ,pedido_fornecedor_sinc.operacao_sinc "
		;
	}
	
	
	public static MontadorDaoComposite getMontadorComposto() {
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(getMontador(),null);
		return montador;
	}
	public static MontadorDaoI getMontador() {
		return new PedidoFornecedorMontador();
	}
	
	
	// ComChaveEstrangeira
  	
	
	// SemChaveEstrangeira
  	
	
	public static Uri buildAllComProdutoPedidoFornecedorAssociada() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(PedidoFornecedorContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComProdutoPedidoFornecedorAssociada").build();
		return saida;
	}	
	public static Uri buildAllSemProdutoPedidoFornecedorAssociada() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(PedidoFornecedorContract.COM_RETIRADA).build();
		saida = saida.buildUpon().appendPath("SemProdutoPedidoFornecedorAssociada").build();
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
	
	
	public static List<PedidoFornecedor> converteLista(Cursor data) {
		return converteLista(data, getMontador());
	}
	public static List<PedidoFornecedor> converteLista(Cursor data, MontadorDaoI montador) {
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