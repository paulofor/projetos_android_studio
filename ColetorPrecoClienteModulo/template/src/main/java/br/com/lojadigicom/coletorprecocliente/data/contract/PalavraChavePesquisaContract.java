

package  br.com.lojadigicom.coletorprecocliente.data.contract;



import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.content.ContentUris;

import java.util.ArrayList;
import java.util.List;

import br.com.lojadigicom.coletorprecocliente.framework.dao.DaoException;
import br.com.lojadigicom.coletorprecocliente.framework.dao.DaoItemRetorno;
import br.com.lojadigicom.coletorprecocliente.framework.dao.MontadorDaoI;
import br.com.lojadigicom.coletorprecocliente.framework.data.MontadorDaoComposite;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.coletorprecocliente.modelo.PalavraChavePesquisa;
import br.com.lojadigicom.coletorprecocliente.modelo.montador.PalavraChavePesquisaMontador;


public final class PalavraChavePesquisaContract implements BaseColumns {
	
	
	private static AplicacaoContract aplicacaoContract;
	public static void setAplicacaoContract(AplicacaoContract valor) {
		aplicacaoContract = valor;
	}
	public static String getContentAuthority() {
		return aplicacaoContract.getContentAuthority();
	}
	
	
	public static final String PATH = "palavra_chave_pesquisa";
	public static final String COM_COMPLEMENTO = "ComComplemento";
	public static final String COM_RETIRADA = "ComRetirada";

	//public static final Uri CONTENT_URI = aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();

    //public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
    //public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;

    public static final String TABLE_NAME = "palavra_chave_pesquisa";
    public static final String TABLE_NAME_SINC = "palavra_chave_pesquisa_sinc";

	public static Uri getContentUri() {
		return aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();
	}
	public static String getContentType() {
		return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static String getContentItemType() {
		return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static Uri buildPalavraChavePesquisaUri(long id) {
    	return ContentUris.withAppendedId(getContentUri(), id);
    }
    
	public static Uri buildGetPorReferenteANaturezaProdutoUri(long id) {
    	Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(""+id).build();
		saida = saida.buildUpon().appendPath(NaturezaProdutoContract.PATH).build();
    	return saida;
    }
	
	
	
    //public static final int COL_ID = 0;
   
    public static final String COLUNA_ID_PALAVRA_CHAVE_PESQUISA = "id_palavra_chave_pesquisa";
    public static final int COL_ID_PALAVRA_CHAVE_PESQUISA = 0;
    public static final String COLUNA_TEXTO_BUSCA = "texto_busca";
    public static final int COL_TEXTO_BUSCA = 1;
    public static final String COLUNA_DATA = "data";
    public static final int COL_DATA = 2;
    public static final String COLUNA_ID_USUARIO_S = "id_usuario_s";
    public static final int COL_ID_USUARIO_S = 3;
	
    public static final String COLUNA_ID_NATUREZA_PRODUTO_RA = "id_natureza_produto_ra";
    public static final int COL_ID_NATUREZA_PRODUTO_RA = 4;
	
	
 	public static final String COLUNA_CHAVE = COLUNA_ID_PALAVRA_CHAVE_PESQUISA;
 	public static final String COLUNA_OPERACAO_SINC = "operacao_sinc";
 	public static final int COL_OPERACAO_SINC = 5;
	
	public static final String[] COLS = new String[] { 
			TABLE_NAME + "." + COLUNA_CHAVE
        	, TABLE_NAME + "." +COLUNA_TEXTO_BUSCA
        	, TABLE_NAME + "." +COLUNA_DATA
			, TABLE_NAME + "." +COLUNA_ID_USUARIO_S
	
			, TABLE_NAME + "." +COLUNA_ID_NATUREZA_PRODUTO_RA
	
    };
    
    public static final String[] COLS_SINC = new String[] { 
			TABLE_NAME_SINC + "." + COLUNA_CHAVE
        	, TABLE_NAME_SINC + "." +COLUNA_TEXTO_BUSCA
        	, TABLE_NAME_SINC + "." +COLUNA_DATA
			, TABLE_NAME_SINC + "." +COLUNA_ID_USUARIO_S
	
			, TABLE_NAME_SINC + "." +COLUNA_ID_NATUREZA_PRODUTO_RA
	
			, TABLE_NAME_SINC + "." + COLUNA_OPERACAO_SINC
    };
    
    private static PalavraChavePesquisaOperacao operacao = new PalavraChavePesquisaOperacao();
    
	private static PalavraChavePesquisaFiltro filtro = null;
	public static PalavraChavePesquisaFiltro getFiltro() {
		if (filtro==null) {
			filtro = new PalavraChavePesquisaFiltro();
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
	
	
	public static String innerJoinNaturezaProduto_ReferenteA() {
		return " inner join " + NaturezaProdutoContract.TABLE_NAME + " on " + NaturezaProdutoContract.TABLE_NAME + "." + NaturezaProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_NATUREZA_PRODUTO_RA + " ";  
	}
	public static String innerJoinSincNaturezaProduto_ReferenteA() {
		return " inner join " + NaturezaProdutoContract.TABLE_NAME_SINC + " on " + NaturezaProdutoContract.TABLE_NAME_SINC + "." + NaturezaProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_NATUREZA_PRODUTO_RA + " ";  
	}
	public static String outerJoinNaturezaProduto_ReferenteA() {
		return " left outer join " + NaturezaProdutoContract.TABLE_NAME + " on " + NaturezaProdutoContract.TABLE_NAME + "." + NaturezaProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_NATUREZA_PRODUTO_RA + " "; 
	}
	public static String outerJoinSincNaturezaProduto_ReferenteA() {
		return " left outer join " + NaturezaProdutoContract.TABLE_NAME_SINC + " on " + NaturezaProdutoContract.TABLE_NAME_SINC + "." + NaturezaProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_NATUREZA_PRODUTO_RA + " ";   
	}
	
	
	public static MontadorDaoI adicionaMontadorNaturezaProdutoReferenteA (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(NaturezaProdutoContract.getMontador(), "NaturezaProduto_ReferenteA");
		return montador;
	}
	public static Uri adicionaNaturezaProdutoReferenteA(Uri uri) {
		return uri.buildUpon().appendPath("ComNaturezaProdutoReferenteA").build();
	}
 	
	
	
	
	
	public static String camposOrdenados() 
	{
		return " " + TABLE_NAME + "." + COLUNA_ID_PALAVRA_CHAVE_PESQUISA  
		+ " , " + TABLE_NAME + "." + COLUNA_TEXTO_BUSCA 
		+ " , " + TABLE_NAME + "." + COLUNA_DATA 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_USUARIO_S 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_NATUREZA_PRODUTO_RA 
		
		
		;
	}
	
	
	
	public static String camposOrdenadosSinc() 
	{
		return " palavra_chave_pesquisa_sinc.id_palavra_chave_pesquisa " 
		+ " ,palavra_chave_pesquisa_sinc.texto_busca " 
		+ " ,palavra_chave_pesquisa_sinc.data " 
		+ " ,palavra_chave_pesquisa_sinc.id_usuario_s " 
		+ " ,palavra_chave_pesquisa_sinc.id_natureza_produto_ra " 
		
		
		+ " ,palavra_chave_pesquisa_sinc.operacao_sinc "
		;
	}
	
	
	public static MontadorDaoComposite getMontadorComposto() {
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(getMontador(),null);
		return montador;
	}
	public static MontadorDaoI getMontador() {
		return new PalavraChavePesquisaMontador();
	}
	
	
	// ComChaveEstrangeira
  	
	public static Uri buildAllComUsuarioSincroniza() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(PalavraChavePesquisaContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComUsuarioSincroniza").build();
		return saida;
	}
	//  Recoloquei o metodo abaixo pq existe referencia no Provider
	public static Uri buildAllSemUsuarioSincroniza() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(PalavraChavePesquisaContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemUsuarioSincroniza").build();
		return saida;
	}
	
	
	public static Uri buildAllComNaturezaProdutoReferenteA() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(PalavraChavePesquisaContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComNaturezaProdutoReferenteA").build();
		return saida;
	}
	//  Recoloquei o metodo abaixo pq existe referencia no Provider
	public static Uri buildAllSemNaturezaProdutoReferenteA() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(PalavraChavePesquisaContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemNaturezaProdutoReferenteA").build();
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
	
	
	public static List<PalavraChavePesquisa> converteLista(Cursor data) {
		return converteLista(data, getMontador());
	}
	public static List<PalavraChavePesquisa> converteLista(Cursor data, MontadorDaoI montador) {
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