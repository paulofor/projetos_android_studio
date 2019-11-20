

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
import br.com.lojadigicom.precomed.modelo.ProdutoPesquisa;
import br.com.lojadigicom.precomed.modelo.montador.ProdutoPesquisaMontador;


public final class ProdutoPesquisaContract implements BaseColumns {
	
	
	private static AplicacaoContract aplicacaoContract;
	public static void setAplicacaoContract(AplicacaoContract valor) {
		aplicacaoContract = valor;
	}
	public static String getContentAuthority() {
		return aplicacaoContract.getContentAuthority();
	}
	
	
	public static final String PATH = "produto_pesquisa";
	public static final String COM_COMPLEMENTO = "ComComplemento";
	public static final String COM_RETIRADA = "ComRetirada";

	//public static final Uri CONTENT_URI = aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();

    //public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
    //public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;

    public static final String TABLE_NAME = "produto_pesquisa";
    public static final String TABLE_NAME_SINC = "produto_pesquisa_sinc";

	public static Uri getContentUri() {
		return aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();
	}
	public static String getContentType() {
		return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static String getContentItemType() {
		return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static Uri buildProdutoPesquisaUri(long id) {
    	return ContentUris.withAppendedId(getContentUri(), id);
    }
    
	public static Uri buildGetPorReferenteAModeloProdutoUri(long id) {
    	Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(""+id).build();
		saida = saida.buildUpon().appendPath(ModeloProdutoContract.PATH).build();
    	return saida;
    }
	
	
	
    //public static final int COL_ID = 0;
   
    public static final String COLUNA_ID_PRODUTO_PESQUISA = "id_produto_pesquisa";
    public static final int COL_ID_PRODUTO_PESQUISA = 0;
    public static final String COLUNA_DATA_INCLUSAO = "data_inclusao";
    public static final int COL_DATA_INCLUSAO = 1;
    public static final String COLUNA_ATIVO = "ativo";
    public static final int COL_ATIVO = 2;
    public static final String COLUNA_NOME_PRODUTO_PESQUISA = "nome_produto_pesquisa";
    public static final int COL_NOME_PRODUTO_PESQUISA = 3;
    public static final String COLUNA_ID_USUARIO_S = "id_usuario_s";
    public static final int COL_ID_USUARIO_S = 4;
	
    public static final String COLUNA_ID_MODELO_PRODUTO_RA = "id_modelo_produto_ra";
    public static final int COL_ID_MODELO_PRODUTO_RA = 5;
	
	
 	public static final String COLUNA_CHAVE = COLUNA_ID_PRODUTO_PESQUISA;
 	public static final String COLUNA_OPERACAO_SINC = "operacao_sinc";
 	public static final int COL_OPERACAO_SINC = 6;
	
	public static final String[] COLS = new String[] { 
			TABLE_NAME + "." + COLUNA_CHAVE
        	, TABLE_NAME + "." +COLUNA_DATA_INCLUSAO
        	, TABLE_NAME + "." +COLUNA_ATIVO
        	, TABLE_NAME + "." +COLUNA_NOME_PRODUTO_PESQUISA
			, TABLE_NAME + "." +COLUNA_ID_USUARIO_S
	
			, TABLE_NAME + "." +COLUNA_ID_MODELO_PRODUTO_RA
	
    };
    
    public static final String[] COLS_SINC = new String[] { 
			TABLE_NAME_SINC + "." + COLUNA_CHAVE
        	, TABLE_NAME_SINC + "." +COLUNA_DATA_INCLUSAO
        	, TABLE_NAME_SINC + "." +COLUNA_ATIVO
        	, TABLE_NAME_SINC + "." +COLUNA_NOME_PRODUTO_PESQUISA
			, TABLE_NAME_SINC + "." +COLUNA_ID_USUARIO_S
	
			, TABLE_NAME_SINC + "." +COLUNA_ID_MODELO_PRODUTO_RA
	
			, TABLE_NAME_SINC + "." + COLUNA_OPERACAO_SINC
    };
    
    private static ProdutoPesquisaOperacao operacao = new ProdutoPesquisaOperacao();
    
	private static ProdutoPesquisaFiltro filtro = null;
	public static ProdutoPesquisaFiltro getFiltro() {
		if (filtro==null) {
			filtro = new ProdutoPesquisaFiltro();
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
	
	
	public static String innerJoinModeloProduto_ReferenteA() {
		return " inner join " + ModeloProdutoContract.TABLE_NAME + " on " + ModeloProdutoContract.TABLE_NAME + "." + ModeloProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_MODELO_PRODUTO_RA + " ";  
	}
	public static String innerJoinSincModeloProduto_ReferenteA() {
		return " inner join " + ModeloProdutoContract.TABLE_NAME_SINC + " on " + ModeloProdutoContract.TABLE_NAME_SINC + "." + ModeloProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_MODELO_PRODUTO_RA + " ";  
	}
	public static String outerJoinModeloProduto_ReferenteA() {
		return " left outer join " + ModeloProdutoContract.TABLE_NAME + " on " + ModeloProdutoContract.TABLE_NAME + "." + ModeloProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_MODELO_PRODUTO_RA + " "; 
	}
	public static String outerJoinSincModeloProduto_ReferenteA() {
		return " left outer join " + ModeloProdutoContract.TABLE_NAME_SINC + " on " + ModeloProdutoContract.TABLE_NAME_SINC + "." + ModeloProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_MODELO_PRODUTO_RA + " ";   
	}
	
	
	public static MontadorDaoI adicionaMontadorModeloProdutoReferenteA (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(ModeloProdutoContract.getMontador(), "ModeloProduto_ReferenteA");
		return montador;
	}
	public static Uri adicionaModeloProdutoReferenteA(Uri uri) {
		return uri.buildUpon().appendPath("ComModeloProdutoReferenteA").build();
	}
 	
	
	
	
	
	public static String camposOrdenados() 
	{
		return " " + TABLE_NAME + "." + COLUNA_ID_PRODUTO_PESQUISA  
		+ " , " + TABLE_NAME + "." + COLUNA_DATA_INCLUSAO 
		+ " , " + TABLE_NAME + "." + COLUNA_ATIVO 
		+ " , " + TABLE_NAME + "." + COLUNA_NOME_PRODUTO_PESQUISA 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_USUARIO_S 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_MODELO_PRODUTO_RA 
		
		
		;
	}
	
	
	
	public static String camposOrdenadosSinc() 
	{
		return " produto_pesquisa_sinc.id_produto_pesquisa " 
		+ " ,produto_pesquisa_sinc.data_inclusao " 
		+ " ,produto_pesquisa_sinc.ativo " 
		+ " ,produto_pesquisa_sinc.nome_produto_pesquisa " 
		+ " ,produto_pesquisa_sinc.id_usuario_s " 
		+ " ,produto_pesquisa_sinc.id_modelo_produto_ra " 
		
		
		+ " ,produto_pesquisa_sinc.operacao_sinc "
		;
	}
	
	
	public static MontadorDaoComposite getMontadorComposto() {
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(getMontador(),null);
		return montador;
	}
	public static MontadorDaoI getMontador() {
		return new ProdutoPesquisaMontador();
	}
	
	
	// ComChaveEstrangeira
  	
	public static Uri buildAllComUsuarioSincroniza() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ProdutoPesquisaContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComUsuarioSincroniza").build();
		return saida;
	}
	/*
	public static Uri buildAllSemUsuarioSincroniza() {
		Uri saida = CONTENT_URI;
		saida = saida.buildUpon().appendPath(ProdutoPesquisaContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemUsuarioSincroniza").build();
		return saida;
	}
	*/	
	
	public static Uri buildAllComModeloProdutoReferenteA() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ProdutoPesquisaContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComModeloProdutoReferenteA").build();
		return saida;
	}
	/*
	public static Uri buildAllSemModeloProdutoReferenteA() {
		Uri saida = CONTENT_URI;
		saida = saida.buildUpon().appendPath(ProdutoPesquisaContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemModeloProdutoReferenteA").build();
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
	
	
	public static List<ProdutoPesquisa> converteLista(Cursor data) {
		return converteLista(data, getMontador());
	}
	public static List<ProdutoPesquisa> converteLista(Cursor data, MontadorDaoI montador) {
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