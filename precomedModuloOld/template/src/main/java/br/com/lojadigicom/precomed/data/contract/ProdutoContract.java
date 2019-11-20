

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
import br.com.lojadigicom.precomed.modelo.Produto;
import br.com.lojadigicom.precomed.modelo.montador.ProdutoMontador;


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
    
	public static Uri buildGetPorLidoEmLojaVirtualUri(long id) {
    	Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(""+id).build();
		saida = saida.buildUpon().appendPath(LojaVirtualContract.PATH).build();
    	return saida;
    }
	
	public static Uri buildGetPorPossuiMarcaUri(long id) {
    	Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(""+id).build();
		saida = saida.buildUpon().appendPath(MarcaContract.PATH).build();
    	return saida;
    }
	
	
	
    //public static final int COL_ID = 0;
   
    public static final String COLUNA_ID_PRODUTO = "id_produto";
    public static final int COL_ID_PRODUTO = 0;
    public static final String COLUNA_URL_ORIGEM = "url_origem";
    public static final int COL_URL_ORIGEM = 1;
    public static final String COLUNA_IMAGEM_LOCAL = "imagem_local";
    public static final int COL_IMAGEM_LOCAL = 2;
    public static final String COLUNA_DATA_INCLUSAO = "data_inclusao";
    public static final int COL_DATA_INCLUSAO = 3;
    public static final String COLUNA_URL = "url";
    public static final int COL_URL = 4;
    public static final String COLUNA_NOME = "nome";
    public static final int COL_NOME = 5;
    public static final String COLUNA_POSICAO_PRODUTO = "posicao_produto";
    public static final int COL_POSICAO_PRODUTO = 6;
    public static final String COLUNA_IMAGEM = "imagem";
    public static final int COL_IMAGEM = 7;
    public static final String COLUNA_CODIGO_MS = "codigo_ms";
    public static final int COL_CODIGO_MS = 8;
    public static final String COLUNA_PRINCIPIO_ATIVO = "principio_ativo";
    public static final int COL_PRINCIPIO_ATIVO = 9;
    public static final String COLUNA_POSSUI_ESTOQUE = "possui_estoque";
    public static final int COL_POSSUI_ESTOQUE = 10;
    public static final String COLUNA_ID_LOJA_VIRTUAL_LE = "id_loja_virtual_le";
    public static final int COL_ID_LOJA_VIRTUAL_LE = 11;
	
    public static final String COLUNA_ID_MARCA_P = "id_marca_p";
    public static final int COL_ID_MARCA_P = 12;
	
	
 	public static final String COLUNA_CHAVE = COLUNA_ID_PRODUTO;
 	public static final String COLUNA_OPERACAO_SINC = "operacao_sinc";
 	public static final int COL_OPERACAO_SINC = 13;
	
	public static final String[] COLS = new String[] { 
			TABLE_NAME + "." + COLUNA_CHAVE
        	, TABLE_NAME + "." +COLUNA_URL_ORIGEM
        	, TABLE_NAME + "." +COLUNA_IMAGEM_LOCAL
        	, TABLE_NAME + "." +COLUNA_DATA_INCLUSAO
        	, TABLE_NAME + "." +COLUNA_URL
        	, TABLE_NAME + "." +COLUNA_NOME
        	, TABLE_NAME + "." +COLUNA_POSICAO_PRODUTO
        	, TABLE_NAME + "." +COLUNA_IMAGEM
        	, TABLE_NAME + "." +COLUNA_CODIGO_MS
        	, TABLE_NAME + "." +COLUNA_PRINCIPIO_ATIVO
        	, TABLE_NAME + "." +COLUNA_POSSUI_ESTOQUE
			, TABLE_NAME + "." +COLUNA_ID_LOJA_VIRTUAL_LE
	
			, TABLE_NAME + "." +COLUNA_ID_MARCA_P
	
    };
    
    public static final String[] COLS_SINC = new String[] { 
			TABLE_NAME_SINC + "." + COLUNA_CHAVE
        	, TABLE_NAME_SINC + "." +COLUNA_URL_ORIGEM
        	, TABLE_NAME_SINC + "." +COLUNA_IMAGEM_LOCAL
        	, TABLE_NAME_SINC + "." +COLUNA_DATA_INCLUSAO
        	, TABLE_NAME_SINC + "." +COLUNA_URL
        	, TABLE_NAME_SINC + "." +COLUNA_NOME
        	, TABLE_NAME_SINC + "." +COLUNA_POSICAO_PRODUTO
        	, TABLE_NAME_SINC + "." +COLUNA_IMAGEM
        	, TABLE_NAME_SINC + "." +COLUNA_CODIGO_MS
        	, TABLE_NAME_SINC + "." +COLUNA_PRINCIPIO_ATIVO
        	, TABLE_NAME_SINC + "." +COLUNA_POSSUI_ESTOQUE
			, TABLE_NAME_SINC + "." +COLUNA_ID_LOJA_VIRTUAL_LE
	
			, TABLE_NAME_SINC + "." +COLUNA_ID_MARCA_P
	
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
	
	// Sem chave
	
	public static String innerJoinModeloProdutoProduto_ReferenteA() {
		return " inner join " + ModeloProdutoProdutoContract.TABLE_NAME + " on " + ModeloProdutoProdutoContract.TABLE_NAME + ".id_produto_ra = " + TABLE_NAME + "." + COLUNA_CHAVE + " ";  
	}
	public static String innerJoinSincModeloProdutoProduto_ReferenteA() {
		return " inner join " + ModeloProdutoProdutoContract.TABLE_NAME_SINC + " on " + ModeloProdutoProdutoContract.TABLE_NAME_SINC + ".id_produto_ra = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";  
	}
	public static String outerJoinModeloProdutoProduto_ReferenteA() {
		return " left outer join " + ModeloProdutoProdutoContract.TABLE_NAME + " on " + ModeloProdutoProdutoContract.TABLE_NAME + ".id_produto_ra = " + TABLE_NAME + "." + COLUNA_CHAVE + " "; 
	}
	public static String outerJoinSincModeloProdutoProduto_ReferenteA() {
		return " left outer join " + ModeloProdutoProdutoContract.TABLE_NAME_SINC + " on " + ModeloProdutoProdutoContract.TABLE_NAME_SINC + ".id_produto_ra = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";   
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
 	
	// Com chave
	
	
	public static String innerJoinLojaVirtual_LidoEm() {
		return " inner join " + LojaVirtualContract.TABLE_NAME + " on " + LojaVirtualContract.TABLE_NAME + "." + LojaVirtualContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_LOJA_VIRTUAL_LE + " ";  
	}
	public static String innerJoinSincLojaVirtual_LidoEm() {
		return " inner join " + LojaVirtualContract.TABLE_NAME_SINC + " on " + LojaVirtualContract.TABLE_NAME_SINC + "." + LojaVirtualContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_LOJA_VIRTUAL_LE + " ";  
	}
	public static String outerJoinLojaVirtual_LidoEm() {
		return " left outer join " + LojaVirtualContract.TABLE_NAME + " on " + LojaVirtualContract.TABLE_NAME + "." + LojaVirtualContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_LOJA_VIRTUAL_LE + " "; 
	}
	public static String outerJoinSincLojaVirtual_LidoEm() {
		return " left outer join " + LojaVirtualContract.TABLE_NAME_SINC + " on " + LojaVirtualContract.TABLE_NAME_SINC + "." + LojaVirtualContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_LOJA_VIRTUAL_LE + " ";   
	}
	
	
	public static MontadorDaoI adicionaMontadorLojaVirtualLidoEm (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(LojaVirtualContract.getMontador(), "LojaVirtual_LidoEm");
		return montador;
	}
	public static Uri adicionaLojaVirtualLidoEm(Uri uri) {
		return uri.buildUpon().appendPath("ComLojaVirtualLidoEm").build();
	}
 	
	
	public static String innerJoinMarca_Possui() {
		return " inner join " + MarcaContract.TABLE_NAME + " on " + MarcaContract.TABLE_NAME + "." + MarcaContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_MARCA_P + " ";  
	}
	public static String innerJoinSincMarca_Possui() {
		return " inner join " + MarcaContract.TABLE_NAME_SINC + " on " + MarcaContract.TABLE_NAME_SINC + "." + MarcaContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_MARCA_P + " ";  
	}
	public static String outerJoinMarca_Possui() {
		return " left outer join " + MarcaContract.TABLE_NAME + " on " + MarcaContract.TABLE_NAME + "." + MarcaContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_MARCA_P + " "; 
	}
	public static String outerJoinSincMarca_Possui() {
		return " left outer join " + MarcaContract.TABLE_NAME_SINC + " on " + MarcaContract.TABLE_NAME_SINC + "." + MarcaContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_MARCA_P + " ";   
	}
	
	
	public static MontadorDaoI adicionaMontadorMarcaPossui (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(MarcaContract.getMontador(), "Marca_Possui");
		return montador;
	}
	public static Uri adicionaMarcaPossui(Uri uri) {
		return uri.buildUpon().appendPath("ComMarcaPossui").build();
	}
 	
	
	
	
	
	public static String camposOrdenados() 
	{
		return " " + TABLE_NAME + "." + COLUNA_ID_PRODUTO  
		+ " , " + TABLE_NAME + "." + COLUNA_URL_ORIGEM 
		+ " , " + TABLE_NAME + "." + COLUNA_IMAGEM_LOCAL 
		+ " , " + TABLE_NAME + "." + COLUNA_DATA_INCLUSAO 
		+ " , " + TABLE_NAME + "." + COLUNA_URL 
		+ " , " + TABLE_NAME + "." + COLUNA_NOME 
		+ " , " + TABLE_NAME + "." + COLUNA_POSICAO_PRODUTO 
		+ " , " + TABLE_NAME + "." + COLUNA_IMAGEM 
		+ " , " + TABLE_NAME + "." + COLUNA_CODIGO_MS 
		+ " , " + TABLE_NAME + "." + COLUNA_PRINCIPIO_ATIVO 
		+ " , " + TABLE_NAME + "." + COLUNA_POSSUI_ESTOQUE 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_LOJA_VIRTUAL_LE 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_MARCA_P 
		
		
		;
	}
	
	
	
	public static String camposOrdenadosSinc() 
	{
		return " produto_sinc.id_produto " 
		+ " ,produto_sinc.url_origem " 
		+ " ,produto_sinc.imagem_local " 
		+ " ,produto_sinc.data_inclusao " 
		+ " ,produto_sinc.url " 
		+ " ,produto_sinc.nome " 
		+ " ,produto_sinc.posicao_produto " 
		+ " ,produto_sinc.imagem " 
		+ " ,produto_sinc.codigo_ms " 
		+ " ,produto_sinc.principio_ativo " 
		+ " ,produto_sinc.possui_estoque " 
		+ " ,produto_sinc.id_loja_virtual_le " 
		+ " ,produto_sinc.id_marca_p " 
		
		
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
  	
	public static Uri buildAllComLojaVirtualLidoEm() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComLojaVirtualLidoEm").build();
		return saida;
	}
	/*
	public static Uri buildAllSemLojaVirtualLidoEm() {
		Uri saida = CONTENT_URI;
		saida = saida.buildUpon().appendPath(ProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemLojaVirtualLidoEm").build();
		return saida;
	}
	*/	
	
	public static Uri buildAllComMarcaPossui() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComMarcaPossui").build();
		return saida;
	}
	/*
	public static Uri buildAllSemMarcaPossui() {
		Uri saida = CONTENT_URI;
		saida = saida.buildUpon().appendPath(ProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemMarcaPossui").build();
		return saida;
	}
	*/	
	
	
	// SemChaveEstrangeira
  	
	
	public static Uri buildAllComModeloProdutoProdutoReferenteA() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComModeloProdutoProdutoReferenteA").build();
		return saida;
	}	
	public static Uri buildAllSemModeloProdutoProdutoReferenteA() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ProdutoContract.COM_RETIRADA).build();
		saida = saida.buildUpon().appendPath("SemModeloProdutoProdutoReferenteA").build();
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