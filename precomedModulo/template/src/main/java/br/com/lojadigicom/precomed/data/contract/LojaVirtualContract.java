

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
import br.com.lojadigicom.precomed.modelo.LojaVirtual;
import br.com.lojadigicom.precomed.modelo.montador.LojaVirtualMontador;


public final class LojaVirtualContract implements BaseColumns {
	
	
	private static AplicacaoContract aplicacaoContract;
	public static void setAplicacaoContract(AplicacaoContract valor) {
		aplicacaoContract = valor;
	}
	public static String getContentAuthority() {
		return aplicacaoContract.getContentAuthority();
	}
	
	
	public static final String PATH = "loja_virtual";
	public static final String COM_COMPLEMENTO = "ComComplemento";
	public static final String COM_RETIRADA = "ComRetirada";

	//public static final Uri CONTENT_URI = aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();

    //public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
    //public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;

    public static final String TABLE_NAME = "loja_virtual";
    public static final String TABLE_NAME_SINC = "loja_virtual_sinc";

	public static Uri getContentUri() {
		return aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();
	}
	public static String getContentType() {
		return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static String getContentItemType() {
		return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static Uri buildLojaVirtualUri(long id) {
    	return ContentUris.withAppendedId(getContentUri(), id);
    }
    
	
	
    //public static final int COL_ID = 0;
   
    public static final String COLUNA_ID_LOJA_VIRTUAL = "id_loja_virtual";
    public static final int COL_ID_LOJA_VIRTUAL = 0;
    public static final String COLUNA_NOME_LOJA_VIRTUAL = "nome_loja_virtual";
    public static final int COL_NOME_LOJA_VIRTUAL = 1;
    public static final String COLUNA_URL_PRINCIPAL = "url_principal";
    public static final int COL_URL_PRINCIPAL = 2;
	
 	public static final String COLUNA_CHAVE = COLUNA_ID_LOJA_VIRTUAL;
 	public static final String COLUNA_OPERACAO_SINC = "operacao_sinc";
 	public static final int COL_OPERACAO_SINC = 3;
	
	public static final String[] COLS = new String[] { 
			TABLE_NAME + "." + COLUNA_CHAVE
        	, TABLE_NAME + "." +COLUNA_NOME_LOJA_VIRTUAL
        	, TABLE_NAME + "." +COLUNA_URL_PRINCIPAL
    };
    
    public static final String[] COLS_SINC = new String[] { 
			TABLE_NAME_SINC + "." + COLUNA_CHAVE
        	, TABLE_NAME_SINC + "." +COLUNA_NOME_LOJA_VIRTUAL
        	, TABLE_NAME_SINC + "." +COLUNA_URL_PRINCIPAL
			, TABLE_NAME_SINC + "." + COLUNA_OPERACAO_SINC
    };
    
    private static LojaVirtualOperacao operacao = new LojaVirtualOperacao();
    
	private static LojaVirtualFiltro filtro = null;
	public static LojaVirtualFiltro getFiltro() {
		if (filtro==null) {
			filtro = new LojaVirtualFiltro();
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
		return " inner join " + ProdutoContract.TABLE_NAME + " on " + ProdutoContract.TABLE_NAME + ".id_loja_virtual_le = " + TABLE_NAME + "." + COLUNA_CHAVE + " ";  
	}
	public static String innerJoinSincProduto_Possui() {
		return " inner join " + ProdutoContract.TABLE_NAME_SINC + " on " + ProdutoContract.TABLE_NAME_SINC + ".id_loja_virtual_le = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";  
	}
	public static String outerJoinProduto_Possui() {
		return " left outer join " + ProdutoContract.TABLE_NAME + " on " + ProdutoContract.TABLE_NAME + ".id_loja_virtual_le = " + TABLE_NAME + "." + COLUNA_CHAVE + " "; 
	}
	public static String outerJoinSincProduto_Possui() {
		return " left outer join " + ProdutoContract.TABLE_NAME_SINC + " on " + ProdutoContract.TABLE_NAME_SINC + ".id_loja_virtual_le = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";   
	}
	public static MontadorDaoI adicionaMontadorProdutoPossuiLista (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(ProdutoContract.getMontador(), "Produto_Possui");
		return montador;
	}
 	
	// Com chave
	
	
	
	
	
	public static String camposOrdenados() 
	{
		return " " + TABLE_NAME + "." + COLUNA_ID_LOJA_VIRTUAL  
		+ " , " + TABLE_NAME + "." + COLUNA_NOME_LOJA_VIRTUAL 
		+ " , " + TABLE_NAME + "." + COLUNA_URL_PRINCIPAL 
		
		
		;
	}
	
	
	
	public static String camposOrdenadosSinc() 
	{
		return " loja_virtual_sinc.id_loja_virtual " 
		+ " ,loja_virtual_sinc.nome_loja_virtual " 
		+ " ,loja_virtual_sinc.url_principal " 
		
		
		+ " ,loja_virtual_sinc.operacao_sinc "
		;
	}
	
	
	public static MontadorDaoComposite getMontadorComposto() {
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(getMontador(),null);
		return montador;
	}
	public static MontadorDaoI getMontador() {
		return new LojaVirtualMontador();
	}
	
	
	// ComChaveEstrangeira
  	
	
	// SemChaveEstrangeira
  	
	
	public static Uri buildAllComProdutoPossui() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(LojaVirtualContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComProdutoPossui").build();
		return saida;
	}	
	public static Uri buildAllSemProdutoPossui() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(LojaVirtualContract.COM_RETIRADA).build();
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
	
	
	public static List<LojaVirtual> converteLista(Cursor data) {
		return converteLista(data, getMontador());
	}
	public static List<LojaVirtual> converteLista(Cursor data, MontadorDaoI montador) {
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