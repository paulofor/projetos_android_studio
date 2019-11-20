

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
import br.com.lojadigicom.coletorprecocliente.modelo.UsuarioPesquisa;
import br.com.lojadigicom.coletorprecocliente.modelo.montador.UsuarioPesquisaMontador;


public final class UsuarioPesquisaContract implements BaseColumns {
	
	
	private static AplicacaoContract aplicacaoContract;
	public static void setAplicacaoContract(AplicacaoContract valor) {
		aplicacaoContract = valor;
	}
	public static String getContentAuthority() {
		return aplicacaoContract.getContentAuthority();
	}
	
	
	public static final String PATH = "usuario_pesquisa";
	public static final String COM_COMPLEMENTO = "ComComplemento";
	public static final String COM_RETIRADA = "ComRetirada";

	//public static final Uri CONTENT_URI = aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();

    //public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
    //public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;

    public static final String TABLE_NAME = "usuario_pesquisa";
    public static final String TABLE_NAME_SINC = "usuario_pesquisa_sinc";

	public static Uri getContentUri() {
		return aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();
	}
	public static String getContentType() {
		return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static String getContentItemType() {
		return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static Uri buildUsuarioPesquisaUri(long id) {
    	return ContentUris.withAppendedId(getContentUri(), id);
    }
    
	public static Uri buildGetPorPesquisaNaturezaProdutoUri(long id) {
    	Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(""+id).build();
		saida = saida.buildUpon().appendPath(NaturezaProdutoContract.PATH).build();
    	return saida;
    }
	
	
	
    //public static final int COL_ID = 0;
   
    public static final String COLUNA_ID_USUARIO_PESQUISA = "id_usuario_pesquisa";
    public static final int COL_ID_USUARIO_PESQUISA = 0;
    public static final String COLUNA_ID_USUARIO_S = "id_usuario_s";
    public static final int COL_ID_USUARIO_S = 1;
	
    public static final String COLUNA_ID_NATUREZA_PRODUTO_P = "id_natureza_produto_p";
    public static final int COL_ID_NATUREZA_PRODUTO_P = 2;
	
	
 	public static final String COLUNA_CHAVE = COLUNA_ID_USUARIO_PESQUISA;
 	public static final String COLUNA_OPERACAO_SINC = "operacao_sinc";
 	public static final int COL_OPERACAO_SINC = 3;
	
	public static final String[] COLS = new String[] { 
			TABLE_NAME + "." + COLUNA_CHAVE
			, TABLE_NAME + "." +COLUNA_ID_USUARIO_S
	
			, TABLE_NAME + "." +COLUNA_ID_NATUREZA_PRODUTO_P
	
    };
    
    public static final String[] COLS_SINC = new String[] { 
			TABLE_NAME_SINC + "." + COLUNA_CHAVE
			, TABLE_NAME_SINC + "." +COLUNA_ID_USUARIO_S
	
			, TABLE_NAME_SINC + "." +COLUNA_ID_NATUREZA_PRODUTO_P
	
			, TABLE_NAME_SINC + "." + COLUNA_OPERACAO_SINC
    };
    
    private static UsuarioPesquisaOperacao operacao = new UsuarioPesquisaOperacao();
    
	private static UsuarioPesquisaFiltro filtro = null;
	public static UsuarioPesquisaFiltro getFiltro() {
		if (filtro==null) {
			filtro = new UsuarioPesquisaFiltro();
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
	
	
	public static String innerJoinNaturezaProduto_Pesquisa() {
		return " inner join " + NaturezaProdutoContract.TABLE_NAME + " on " + NaturezaProdutoContract.TABLE_NAME + "." + NaturezaProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_NATUREZA_PRODUTO_P + " ";  
	}
	public static String innerJoinSincNaturezaProduto_Pesquisa() {
		return " inner join " + NaturezaProdutoContract.TABLE_NAME_SINC + " on " + NaturezaProdutoContract.TABLE_NAME_SINC + "." + NaturezaProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_NATUREZA_PRODUTO_P + " ";  
	}
	public static String outerJoinNaturezaProduto_Pesquisa() {
		return " left outer join " + NaturezaProdutoContract.TABLE_NAME + " on " + NaturezaProdutoContract.TABLE_NAME + "." + NaturezaProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_NATUREZA_PRODUTO_P + " "; 
	}
	public static String outerJoinSincNaturezaProduto_Pesquisa() {
		return " left outer join " + NaturezaProdutoContract.TABLE_NAME_SINC + " on " + NaturezaProdutoContract.TABLE_NAME_SINC + "." + NaturezaProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_NATUREZA_PRODUTO_P + " ";   
	}
	
	
	public static MontadorDaoI adicionaMontadorNaturezaProdutoPesquisa (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(NaturezaProdutoContract.getMontador(), "NaturezaProduto_Pesquisa");
		return montador;
	}
	public static Uri adicionaNaturezaProdutoPesquisa(Uri uri) {
		return uri.buildUpon().appendPath("ComNaturezaProdutoPesquisa").build();
	}
 	
	
	
	
	
	public static String camposOrdenados() 
	{
		return " " + TABLE_NAME + "." + COLUNA_ID_USUARIO_PESQUISA  
		+ " , " + TABLE_NAME + "." + COLUNA_ID_USUARIO_S 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_NATUREZA_PRODUTO_P 
		
		
		;
	}
	
	
	
	public static String camposOrdenadosSinc() 
	{
		return " usuario_pesquisa_sinc.id_usuario_pesquisa " 
		+ " ,usuario_pesquisa_sinc.id_usuario_s " 
		+ " ,usuario_pesquisa_sinc.id_natureza_produto_p " 
		
		
		+ " ,usuario_pesquisa_sinc.operacao_sinc "
		;
	}
	
	
	public static MontadorDaoComposite getMontadorComposto() {
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(getMontador(),null);
		return montador;
	}
	public static MontadorDaoI getMontador() {
		return new UsuarioPesquisaMontador();
	}
	
	
	// ComChaveEstrangeira
  	
	public static Uri buildAllComUsuarioSincroniza() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(UsuarioPesquisaContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComUsuarioSincroniza").build();
		return saida;
	}
	/*
	public static Uri buildAllSemUsuarioSincroniza() {
		Uri saida = CONTENT_URI;
		saida = saida.buildUpon().appendPath(UsuarioPesquisaContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemUsuarioSincroniza").build();
		return saida;
	}
	*/	
	
	public static Uri buildAllComNaturezaProdutoPesquisa() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(UsuarioPesquisaContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComNaturezaProdutoPesquisa").build();
		return saida;
	}
	/*
	public static Uri buildAllSemNaturezaProdutoPesquisa() {
		Uri saida = CONTENT_URI;
		saida = saida.buildUpon().appendPath(UsuarioPesquisaContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemNaturezaProdutoPesquisa").build();
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
	
	
	public static List<UsuarioPesquisa> converteLista(Cursor data) {
		return converteLista(data, getMontador());
	}
	public static List<UsuarioPesquisa> converteLista(Cursor data, MontadorDaoI montador) {
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