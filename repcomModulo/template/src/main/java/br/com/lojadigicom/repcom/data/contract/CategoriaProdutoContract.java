

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
import br.com.lojadigicom.repcom.modelo.CategoriaProduto;
import br.com.lojadigicom.repcom.modelo.montador.CategoriaProdutoMontador;


public final class CategoriaProdutoContract implements BaseColumns {
	
	
	private static AplicacaoContract aplicacaoContract;
	public static void setAplicacaoContract(AplicacaoContract valor) {
		aplicacaoContract = valor;
	}
	public static String getContentAuthority() {
		return aplicacaoContract.getContentAuthority();
	}
	
	
	public static final String PATH = "categoria_produto";
	public static final String COM_COMPLEMENTO = "ComComplemento";
	public static final String COM_RETIRADA = "ComRetirada";

	//public static final Uri CONTENT_URI = aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();

    //public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
    //public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;

    public static final String TABLE_NAME = "categoria_produto";
    public static final String TABLE_NAME_SINC = "categoria_produto_sinc";

	public static Uri getContentUri() {
		return aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();
	}
	public static String getContentType() {
		return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static String getContentItemType() {
		return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static Uri buildCategoriaProdutoUri(long id) {
    	return ContentUris.withAppendedId(getContentUri(), id);
    }
    
	public static Uri buildGetPorPaiCategoriaProdutoUri(long id) {
    	Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(""+id).build();
		saida = saida.buildUpon().appendPath(CategoriaProdutoContract.PATH).build();
    	return saida;
    }
	
	
	
    //public static final int COL_ID = 0;
   
    public static final String COLUNA_ID_CATEGORIA_PRODUTO = "id_categoria_produto";
    public static final int COL_ID_CATEGORIA_PRODUTO = 0;
    public static final String COLUNA_NOME = "nome";
    public static final int COL_NOME = 1;
    public static final String COLUNA_URL = "url";
    public static final int COL_URL = 2;
    public static final String COLUNA_CODIGO_LINE_ID = "codigo_line_id";
    public static final int COL_CODIGO_LINE_ID = 3;
    public static final String COLUNA_DATA_INCLUSAO = "data_inclusao";
    public static final int COL_DATA_INCLUSAO = 4;
    public static final String COLUNA_ID_CATEGORIA_PRODUTO_P = "id_categoria_produto_p";
    public static final int COL_ID_CATEGORIA_PRODUTO_P = 5;
	
	
 	public static final String COLUNA_CHAVE = COLUNA_ID_CATEGORIA_PRODUTO;
 	public static final String COLUNA_OPERACAO_SINC = "operacao_sinc";
 	public static final int COL_OPERACAO_SINC = 6;
	
	public static final String[] COLS = new String[] { 
			TABLE_NAME + "." + COLUNA_CHAVE
        	, TABLE_NAME + "." +COLUNA_NOME
        	, TABLE_NAME + "." +COLUNA_URL
        	, TABLE_NAME + "." +COLUNA_CODIGO_LINE_ID
        	, TABLE_NAME + "." +COLUNA_DATA_INCLUSAO
			, TABLE_NAME + "." +COLUNA_ID_CATEGORIA_PRODUTO_P
	
    };
    
    public static final String[] COLS_SINC = new String[] { 
			TABLE_NAME_SINC + "." + COLUNA_CHAVE
        	, TABLE_NAME_SINC + "." +COLUNA_NOME
        	, TABLE_NAME_SINC + "." +COLUNA_URL
        	, TABLE_NAME_SINC + "." +COLUNA_CODIGO_LINE_ID
        	, TABLE_NAME_SINC + "." +COLUNA_DATA_INCLUSAO
			, TABLE_NAME_SINC + "." +COLUNA_ID_CATEGORIA_PRODUTO_P
	
			, TABLE_NAME_SINC + "." + COLUNA_OPERACAO_SINC
    };
    
    private static CategoriaProdutoOperacao operacao = new CategoriaProdutoOperacao();
 	public static Uri buildListaNivel0() {
		return operacao.buildListaNivel0(getFiltro());
    }
    private static MontadorDaoI _montadorListaNivel0 = null;
    public static MontadorDaoI getMontadorListaNivel0() {
    	return _montadorListaNivel0;
    }
    public static void setMontadorListaNivel0(MontadorDaoI montador) {
    	_montadorListaNivel0 = montador;
    }
    
	private static CategoriaProdutoFiltro filtro = null;
	public static CategoriaProdutoFiltro getFiltro() {
		if (filtro==null) {
			filtro = new CategoriaProdutoFiltro();
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
	
	public static String innerJoinClienteInteresseCategoria_Associada() {
		return " inner join " + ClienteInteresseCategoriaContract.TABLE_NAME + " on " + ClienteInteresseCategoriaContract.TABLE_NAME + ".id_categoria_produto_a = " + TABLE_NAME + "." + COLUNA_CHAVE + " ";  
	}
	public static String innerJoinSincClienteInteresseCategoria_Associada() {
		return " inner join " + ClienteInteresseCategoriaContract.TABLE_NAME_SINC + " on " + ClienteInteresseCategoriaContract.TABLE_NAME_SINC + ".id_categoria_produto_a = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";  
	}
	public static String outerJoinClienteInteresseCategoria_Associada() {
		return " left outer join " + ClienteInteresseCategoriaContract.TABLE_NAME + " on " + ClienteInteresseCategoriaContract.TABLE_NAME + ".id_categoria_produto_a = " + TABLE_NAME + "." + COLUNA_CHAVE + " "; 
	}
	public static String outerJoinSincClienteInteresseCategoria_Associada() {
		return " left outer join " + ClienteInteresseCategoriaContract.TABLE_NAME_SINC + " on " + ClienteInteresseCategoriaContract.TABLE_NAME_SINC + ".id_categoria_produto_a = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";   
	}
	public static MontadorDaoI adicionaMontadorClienteInteresseCategoriaAssociadaLista (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(ClienteInteresseCategoriaContract.getMontador(), "ClienteInteresseCategoria_Associada");
		return montador;
	}
 	
	public static String innerJoinCategoriaProdutoProduto_Possui() {
		return " inner join " + CategoriaProdutoProdutoContract.TABLE_NAME + " on " + CategoriaProdutoProdutoContract.TABLE_NAME + ".id_categoria_produto_ra = " + TABLE_NAME + "." + COLUNA_CHAVE + " ";  
	}
	public static String innerJoinSincCategoriaProdutoProduto_Possui() {
		return " inner join " + CategoriaProdutoProdutoContract.TABLE_NAME_SINC + " on " + CategoriaProdutoProdutoContract.TABLE_NAME_SINC + ".id_categoria_produto_ra = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";  
	}
	public static String outerJoinCategoriaProdutoProduto_Possui() {
		return " left outer join " + CategoriaProdutoProdutoContract.TABLE_NAME + " on " + CategoriaProdutoProdutoContract.TABLE_NAME + ".id_categoria_produto_ra = " + TABLE_NAME + "." + COLUNA_CHAVE + " "; 
	}
	public static String outerJoinSincCategoriaProdutoProduto_Possui() {
		return " left outer join " + CategoriaProdutoProdutoContract.TABLE_NAME_SINC + " on " + CategoriaProdutoProdutoContract.TABLE_NAME_SINC + ".id_categoria_produto_ra = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";   
	}
	public static MontadorDaoI adicionaMontadorCategoriaProdutoProdutoPossuiLista (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(CategoriaProdutoProdutoContract.getMontador(), "CategoriaProdutoProduto_Possui");
		return montador;
	}
 	
	public static String innerJoinCategoriaProduto_Pai() {
		return " inner join " + CategoriaProdutoContract.TABLE_NAME + " on " + CategoriaProdutoContract.TABLE_NAME + ".id_categoria_produto_p = " + TABLE_NAME + "." + COLUNA_CHAVE + " ";  
	}
	public static String innerJoinSincCategoriaProduto_Pai() {
		return " inner join " + CategoriaProdutoContract.TABLE_NAME_SINC + " on " + CategoriaProdutoContract.TABLE_NAME_SINC + ".id_categoria_produto_p = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";  
	}
	public static String outerJoinCategoriaProduto_Pai() {
		return " left outer join " + CategoriaProdutoContract.TABLE_NAME + " on " + CategoriaProdutoContract.TABLE_NAME + ".id_categoria_produto_p = " + TABLE_NAME + "." + COLUNA_CHAVE + " "; 
	}
	public static String outerJoinSincCategoriaProduto_Pai() {
		return " left outer join " + CategoriaProdutoContract.TABLE_NAME_SINC + " on " + CategoriaProdutoContract.TABLE_NAME_SINC + ".id_categoria_produto_p = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";   
	}
	public static MontadorDaoI adicionaMontadorCategoriaProdutoPaiLista (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(CategoriaProdutoContract.getMontador(), "CategoriaProduto_Pai");
		return montador;
	}
 	
	// Com chave
	
	
	
	public static MontadorDaoI adicionaMontadorCategoriaProdutoPai (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(CategoriaProdutoContract.getMontador(), "CategoriaProduto_Pai");
		return montador;
	}
	public static Uri adicionaCategoriaProdutoPai(Uri uri) {
		return uri.buildUpon().appendPath("ComCategoriaProdutoPai").build();
	}
 	
	
	
	
	
	public static String camposOrdenados() 
	{
		return " " + TABLE_NAME + "." + COLUNA_ID_CATEGORIA_PRODUTO  
		+ " , " + TABLE_NAME + "." + COLUNA_NOME 
		+ " , " + TABLE_NAME + "." + COLUNA_URL 
		+ " , " + TABLE_NAME + "." + COLUNA_CODIGO_LINE_ID 
		+ " , " + TABLE_NAME + "." + COLUNA_DATA_INCLUSAO 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_CATEGORIA_PRODUTO_P 
		
		
		;
	}
	
	
	
	public static String camposOrdenadosSinc() 
	{
		return " categoria_produto_sinc.id_categoria_produto " 
		+ " ,categoria_produto_sinc.nome " 
		+ " ,categoria_produto_sinc.url " 
		+ " ,categoria_produto_sinc.codigo_line_id " 
		+ " ,categoria_produto_sinc.data_inclusao " 
		+ " ,categoria_produto_sinc.id_categoria_produto_p " 
		
		
		+ " ,categoria_produto_sinc.operacao_sinc "
		;
	}
	
	
	public static MontadorDaoComposite getMontadorComposto() {
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(getMontador(),null);
		return montador;
	}
	public static MontadorDaoI getMontador() {
		return new CategoriaProdutoMontador();
	}
	
	
	// ComChaveEstrangeira
  	
	public static Uri buildAllComCategoriaProdutoPai() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(CategoriaProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComCategoriaProdutoPai").build();
		return saida;
	}
	//  Recoloquei o metodo abaixo pq existe referencia no Provider
	public static Uri buildAllSemCategoriaProdutoPai() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(CategoriaProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemCategoriaProdutoPai").build();
		return saida;
	}
	
	
	
	// SemChaveEstrangeira
  	
	
	public static Uri buildAllComClienteInteresseCategoriaAssociada() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(CategoriaProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComClienteInteresseCategoriaAssociada").build();
		return saida;
	}	
	public static Uri buildAllSemClienteInteresseCategoriaAssociada() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(CategoriaProdutoContract.COM_RETIRADA).build();
		saida = saida.buildUpon().appendPath("SemClienteInteresseCategoriaAssociada").build();
		return saida;
	}	
	
	
	
	public static Uri buildAllComCategoriaProdutoProdutoPossui() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(CategoriaProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComCategoriaProdutoProdutoPossui").build();
		return saida;
	}	
	public static Uri buildAllSemCategoriaProdutoProdutoPossui() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(CategoriaProdutoContract.COM_RETIRADA).build();
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
	
	
	public static List<CategoriaProduto> converteLista(Cursor data) {
		return converteLista(data, getMontador());
	}
	public static List<CategoriaProduto> converteLista(Cursor data, MontadorDaoI montador) {
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