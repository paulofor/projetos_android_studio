

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
import br.com.lojadigicom.repcom.modelo.Produto;
import br.com.lojadigicom.repcom.modelo.montador.ProdutoMontador;


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
    
	public static Uri buildGetPorEstaEmLinhaProdutoUri(long id) {
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
    public static final String COLUNA_URL = "url";
    public static final int COL_URL = 2;
    public static final String COLUNA_IMAGEM = "imagem";
    public static final int COL_IMAGEM = 3;
    public static final String COLUNA_TAMANHO_IMAGEM = "tamanho_imagem";
    public static final int COL_TAMANHO_IMAGEM = 4;
    public static final String COLUNA_DATA_INCLUSAO = "data_inclusao";
    public static final int COL_DATA_INCLUSAO = 5;
    public static final String COLUNA_DATA_EXCLUSAO = "data_exclusao";
    public static final int COL_DATA_EXCLUSAO = 6;
    public static final String COLUNA_ID_LINHA_PRODUTO_EE = "id_linha_produto_ee";
    public static final int COL_ID_LINHA_PRODUTO_EE = 7;
	
	
 	public static final String COLUNA_CHAVE = COLUNA_ID_PRODUTO;
 	public static final String COLUNA_OPERACAO_SINC = "operacao_sinc";
 	public static final int COL_OPERACAO_SINC = 8;
	
	public static final String[] COLS = new String[] { 
			TABLE_NAME + "." + COLUNA_CHAVE
        	, TABLE_NAME + "." +COLUNA_NOME
        	, TABLE_NAME + "." +COLUNA_URL
        	, TABLE_NAME + "." +COLUNA_IMAGEM
        	, TABLE_NAME + "." +COLUNA_TAMANHO_IMAGEM
        	, TABLE_NAME + "." +COLUNA_DATA_INCLUSAO
        	, TABLE_NAME + "." +COLUNA_DATA_EXCLUSAO
			, TABLE_NAME + "." +COLUNA_ID_LINHA_PRODUTO_EE
	
    };
    
    public static final String[] COLS_SINC = new String[] { 
			TABLE_NAME_SINC + "." + COLUNA_CHAVE
        	, TABLE_NAME_SINC + "." +COLUNA_NOME
        	, TABLE_NAME_SINC + "." +COLUNA_URL
        	, TABLE_NAME_SINC + "." +COLUNA_IMAGEM
        	, TABLE_NAME_SINC + "." +COLUNA_TAMANHO_IMAGEM
        	, TABLE_NAME_SINC + "." +COLUNA_DATA_INCLUSAO
        	, TABLE_NAME_SINC + "." +COLUNA_DATA_EXCLUSAO
			, TABLE_NAME_SINC + "." +COLUNA_ID_LINHA_PRODUTO_EE
	
			, TABLE_NAME_SINC + "." + COLUNA_OPERACAO_SINC
    };
    
    private static ProdutoOperacao operacao = new ProdutoOperacao();
 	public static Uri buildListaPorIdCategoria() {
		return operacao.buildListaPorIdCategoria(getFiltro());
    }
    private static MontadorDaoI _montadorListaPorIdCategoria = null;
    public static MontadorDaoI getMontadorListaPorIdCategoria() {
    	return _montadorListaPorIdCategoria;
    }
    public static void setMontadorListaPorIdCategoria(MontadorDaoI montador) {
    	_montadorListaPorIdCategoria = montador;
    }
 	public static Uri buildPesquisaTrechoNome() {
		return operacao.buildPesquisaTrechoNome(getFiltro());
    }
    private static MontadorDaoI _montadorPesquisaTrechoNome = null;
    public static MontadorDaoI getMontadorPesquisaTrechoNome() {
    	return _montadorPesquisaTrechoNome;
    }
    public static void setMontadorPesquisaTrechoNome(MontadorDaoI montador) {
    	_montadorPesquisaTrechoNome = montador;
    }
    
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
	
	// Sem chave
	
	public static String innerJoinProdutoPedidoFornecedor_Associada() {
		return " inner join " + ProdutoPedidoFornecedorContract.TABLE_NAME + " on " + ProdutoPedidoFornecedorContract.TABLE_NAME + ".id_produto_a = " + TABLE_NAME + "." + COLUNA_CHAVE + " ";  
	}
	public static String innerJoinSincProdutoPedidoFornecedor_Associada() {
		return " inner join " + ProdutoPedidoFornecedorContract.TABLE_NAME_SINC + " on " + ProdutoPedidoFornecedorContract.TABLE_NAME_SINC + ".id_produto_a = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";  
	}
	public static String outerJoinProdutoPedidoFornecedor_Associada() {
		return " left outer join " + ProdutoPedidoFornecedorContract.TABLE_NAME + " on " + ProdutoPedidoFornecedorContract.TABLE_NAME + ".id_produto_a = " + TABLE_NAME + "." + COLUNA_CHAVE + " "; 
	}
	public static String outerJoinSincProdutoPedidoFornecedor_Associada() {
		return " left outer join " + ProdutoPedidoFornecedorContract.TABLE_NAME_SINC + " on " + ProdutoPedidoFornecedorContract.TABLE_NAME_SINC + ".id_produto_a = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";   
	}
 	
	public static String innerJoinProdutoVenda_Associada() {
		return " inner join " + ProdutoVendaContract.TABLE_NAME + " on " + ProdutoVendaContract.TABLE_NAME + ".id_produto_a = " + TABLE_NAME + "." + COLUNA_CHAVE + " ";  
	}
	public static String innerJoinSincProdutoVenda_Associada() {
		return " inner join " + ProdutoVendaContract.TABLE_NAME_SINC + " on " + ProdutoVendaContract.TABLE_NAME_SINC + ".id_produto_a = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";  
	}
	public static String outerJoinProdutoVenda_Associada() {
		return " left outer join " + ProdutoVendaContract.TABLE_NAME + " on " + ProdutoVendaContract.TABLE_NAME + ".id_produto_a = " + TABLE_NAME + "." + COLUNA_CHAVE + " "; 
	}
	public static String outerJoinSincProdutoVenda_Associada() {
		return " left outer join " + ProdutoVendaContract.TABLE_NAME_SINC + " on " + ProdutoVendaContract.TABLE_NAME_SINC + ".id_produto_a = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";   
	}
 	
	public static String innerJoinPrecoProduto_Possui() {
		return " inner join " + PrecoProdutoContract.TABLE_NAME + " on " + PrecoProdutoContract.TABLE_NAME + ".id_produto_pa = " + TABLE_NAME + "." + COLUNA_CHAVE + " ";  
	}
	public static String innerJoinSincPrecoProduto_Possui() {
		return " inner join " + PrecoProdutoContract.TABLE_NAME_SINC + " on " + PrecoProdutoContract.TABLE_NAME_SINC + ".id_produto_pa = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";  
	}
	public static String outerJoinPrecoProduto_Possui() {
		return " left outer join " + PrecoProdutoContract.TABLE_NAME + " on " + PrecoProdutoContract.TABLE_NAME + ".id_produto_pa = " + TABLE_NAME + "." + COLUNA_CHAVE + " "; 
	}
	public static String outerJoinSincPrecoProduto_Possui() {
		return " left outer join " + PrecoProdutoContract.TABLE_NAME_SINC + " on " + PrecoProdutoContract.TABLE_NAME_SINC + ".id_produto_pa = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";   
	}
 	
	public static String innerJoinCategoriaProdutoProduto_Possui() {
		return " inner join " + CategoriaProdutoProdutoContract.TABLE_NAME + " on " + CategoriaProdutoProdutoContract.TABLE_NAME + ".id_produto_ra = " + TABLE_NAME + "." + COLUNA_CHAVE + " ";  
	}
	public static String innerJoinSincCategoriaProdutoProduto_Possui() {
		return " inner join " + CategoriaProdutoProdutoContract.TABLE_NAME_SINC + " on " + CategoriaProdutoProdutoContract.TABLE_NAME_SINC + ".id_produto_ra = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";  
	}
	public static String outerJoinCategoriaProdutoProduto_Possui() {
		return " left outer join " + CategoriaProdutoProdutoContract.TABLE_NAME + " on " + CategoriaProdutoProdutoContract.TABLE_NAME + ".id_produto_ra = " + TABLE_NAME + "." + COLUNA_CHAVE + " "; 
	}
	public static String outerJoinSincCategoriaProdutoProduto_Possui() {
		return " left outer join " + CategoriaProdutoProdutoContract.TABLE_NAME_SINC + " on " + CategoriaProdutoProdutoContract.TABLE_NAME_SINC + ".id_produto_ra = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";   
	}
 	
	// Com chave
	
	
	public static String innerJoinLinhaProduto_EstaEm() {
		return " inner join " + LinhaProdutoContract.TABLE_NAME + " on " + LinhaProdutoContract.TABLE_NAME + "." + LinhaProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_LINHA_PRODUTO_EE + " ";  
	}
	public static String innerJoinSincLinhaProduto_EstaEm() {
		return " inner join " + LinhaProdutoContract.TABLE_NAME_SINC + " on " + LinhaProdutoContract.TABLE_NAME_SINC + "." + LinhaProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_LINHA_PRODUTO_EE + " ";  
	}
	public static String outerJoinLinhaProduto_EstaEm() {
		return " left outer join " + LinhaProdutoContract.TABLE_NAME + " on " + LinhaProdutoContract.TABLE_NAME + "." + LinhaProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_LINHA_PRODUTO_EE + " "; 
	}
	public static String outerJoinSincLinhaProduto_EstaEm() {
		return " left outer join " + LinhaProdutoContract.TABLE_NAME_SINC + " on " + LinhaProdutoContract.TABLE_NAME_SINC + "." + LinhaProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_LINHA_PRODUTO_EE + " ";   
	}
	
	
	public static MontadorDaoI adicionaMontadorLinhaProdutoEstaEm (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(LinhaProdutoContract.getMontador(), "LinhaProduto_EstaEm");
		return montador;
	}
	public static Uri adicionaLinhaProdutoEstaEm(Uri uri) {
		return uri.buildUpon().appendPath("ComLinhaProdutoEstaEm").build();
	}
 	
	
	
	
	
	public static String camposOrdenados() 
	{
		return " " + TABLE_NAME + "." + COLUNA_ID_PRODUTO  
		+ " , " + TABLE_NAME + "." + COLUNA_NOME 
		+ " , " + TABLE_NAME + "." + COLUNA_URL 
		+ " , " + TABLE_NAME + "." + COLUNA_IMAGEM 
		+ " , " + TABLE_NAME + "." + COLUNA_TAMANHO_IMAGEM 
		+ " , " + TABLE_NAME + "." + COLUNA_DATA_INCLUSAO 
		+ " , " + TABLE_NAME + "." + COLUNA_DATA_EXCLUSAO 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_LINHA_PRODUTO_EE 
		
		
		;
	}
	
	
	
	public static String camposOrdenadosSinc() 
	{
		return " produto_sinc.id_produto " 
		+ " ,produto_sinc.nome " 
		+ " ,produto_sinc.url " 
		+ " ,produto_sinc.imagem " 
		+ " ,produto_sinc.tamanho_imagem " 
		+ " ,produto_sinc.data_inclusao " 
		+ " ,produto_sinc.data_exclusao " 
		+ " ,produto_sinc.id_linha_produto_ee " 
		
		
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
  	
	public static Uri buildAllComLinhaProdutoEstaEm() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComLinhaProdutoEstaEm").build();
		return saida;
	}
	/*
	public static Uri buildAllSemLinhaProdutoEstaEm() {
		Uri saida = CONTENT_URI;
		saida = saida.buildUpon().appendPath(ProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemLinhaProdutoEstaEm").build();
		return saida;
	}
	*/	
	
	
	// SemChaveEstrangeira
  	
	
	public static Uri buildAllComProdutoPedidoFornecedorAssociada() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComProdutoPedidoFornecedorAssociada").build();
		return saida;
	}	
	public static Uri buildAllSemProdutoPedidoFornecedorAssociada() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ProdutoContract.COM_RETIRADA).build();
		saida = saida.buildUpon().appendPath("SemProdutoPedidoFornecedorAssociada").build();
		return saida;
	}	
	
	
	
	public static Uri buildAllComProdutoVendaAssociada() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComProdutoVendaAssociada").build();
		return saida;
	}	
	public static Uri buildAllSemProdutoVendaAssociada() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ProdutoContract.COM_RETIRADA).build();
		saida = saida.buildUpon().appendPath("SemProdutoVendaAssociada").build();
		return saida;
	}	
	
	
	
	public static Uri buildAllComPrecoProdutoPossui() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComPrecoProdutoPossui").build();
		return saida;
	}	
	public static Uri buildAllSemPrecoProdutoPossui() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ProdutoContract.COM_RETIRADA).build();
		saida = saida.buildUpon().appendPath("SemPrecoProdutoPossui").build();
		return saida;
	}	
	
	
	
	public static Uri buildAllComCategoriaProdutoProdutoPossui() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComCategoriaProdutoProdutoPossui").build();
		return saida;
	}	
	public static Uri buildAllSemCategoriaProdutoProdutoPossui() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ProdutoContract.COM_RETIRADA).build();
		saida = saida.buildUpon().appendPath("SemCategoriaProdutoProdutoPossui").build();
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
	
}