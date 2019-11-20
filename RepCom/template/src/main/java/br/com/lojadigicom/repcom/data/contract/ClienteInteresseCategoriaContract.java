

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
import br.com.lojadigicom.repcom.modelo.ClienteInteresseCategoria;
import br.com.lojadigicom.repcom.modelo.montador.ClienteInteresseCategoriaMontador;


public final class ClienteInteresseCategoriaContract implements BaseColumns {
	
	
	private static AplicacaoContract aplicacaoContract;
	public static void setAplicacaoContract(AplicacaoContract valor) {
		aplicacaoContract = valor;
	}
	public static String getContentAuthority() {
		return aplicacaoContract.getContentAuthority();
	}
	
	
	public static final String PATH = "cliente_interesse_categoria";
	public static final String COM_COMPLEMENTO = "ComComplemento";
	public static final String COM_RETIRADA = "ComRetirada";

	//public static final Uri CONTENT_URI = aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();

    //public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
    //public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;

    public static final String TABLE_NAME = "cliente_interesse_categoria";
    public static final String TABLE_NAME_SINC = "cliente_interesse_categoria_sinc";

	public static Uri getContentUri() {
		return aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();
	}
	public static String getContentType() {
		return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static String getContentItemType() {
		return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static Uri buildClienteInteresseCategoriaUri(long id) {
    	return ContentUris.withAppendedId(getContentUri(), id);
    }
    
	public static Uri buildGetPorAssociadaClienteUri(long id) {
    	Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(""+id).build();
		saida = saida.buildUpon().appendPath(ClienteContract.PATH).build();
    	return saida;
    }
	
	public static Uri buildGetPorAssociadaCategoriaProdutoUri(long id) {
    	Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(""+id).build();
		saida = saida.buildUpon().appendPath(CategoriaProdutoContract.PATH).build();
    	return saida;
    }
	
	
	
    //public static final int COL_ID = 0;
   
    public static final String COLUNA_ID_CLIENTE_INTERESSE_CATEGORIA = "id_cliente_interesse_categoria";
    public static final int COL_ID_CLIENTE_INTERESSE_CATEGORIA = 0;
    public static final String COLUNA_ID_CLIENTE_A = "id_cliente_a";
    public static final int COL_ID_CLIENTE_A = 1;
	
    public static final String COLUNA_ID_CATEGORIA_PRODUTO_A = "id_categoria_produto_a";
    public static final int COL_ID_CATEGORIA_PRODUTO_A = 2;
	
	
 	public static final String COLUNA_CHAVE = COLUNA_ID_CLIENTE_INTERESSE_CATEGORIA;
 	public static final String COLUNA_OPERACAO_SINC = "operacao_sinc";
 	public static final int COL_OPERACAO_SINC = 3;
	
	public static final String[] COLS = new String[] { 
			TABLE_NAME + "." + COLUNA_CHAVE
			, TABLE_NAME + "." +COLUNA_ID_CLIENTE_A
	
			, TABLE_NAME + "." +COLUNA_ID_CATEGORIA_PRODUTO_A
	
    };
    
    public static final String[] COLS_SINC = new String[] { 
			TABLE_NAME_SINC + "." + COLUNA_CHAVE
			, TABLE_NAME_SINC + "." +COLUNA_ID_CLIENTE_A
	
			, TABLE_NAME_SINC + "." +COLUNA_ID_CATEGORIA_PRODUTO_A
	
			, TABLE_NAME_SINC + "." + COLUNA_OPERACAO_SINC
    };
    
    private static ClienteInteresseCategoriaOperacao operacao = new ClienteInteresseCategoriaOperacao();
    
	private static ClienteInteresseCategoriaFiltro filtro = null;
	public static ClienteInteresseCategoriaFiltro getFiltro() {
		if (filtro==null) {
			filtro = new ClienteInteresseCategoriaFiltro();
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
	
	
	public static String innerJoinCliente_Associada() {
		return " inner join " + ClienteContract.TABLE_NAME + " on " + ClienteContract.TABLE_NAME + "." + ClienteContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_CLIENTE_A + " ";  
	}
	public static String innerJoinSincCliente_Associada() {
		return " inner join " + ClienteContract.TABLE_NAME_SINC + " on " + ClienteContract.TABLE_NAME_SINC + "." + ClienteContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_CLIENTE_A + " ";  
	}
	public static String outerJoinCliente_Associada() {
		return " left outer join " + ClienteContract.TABLE_NAME + " on " + ClienteContract.TABLE_NAME + "." + ClienteContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_CLIENTE_A + " "; 
	}
	public static String outerJoinSincCliente_Associada() {
		return " left outer join " + ClienteContract.TABLE_NAME_SINC + " on " + ClienteContract.TABLE_NAME_SINC + "." + ClienteContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_CLIENTE_A + " ";   
	}
	
	
	public static MontadorDaoI adicionaMontadorClienteAssociada (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(ClienteContract.getMontador(), "Cliente_Associada");
		return montador;
	}
	public static Uri adicionaClienteAssociada(Uri uri) {
		return uri.buildUpon().appendPath("ComClienteAssociada").build();
	}
 	
	
	public static String innerJoinCategoriaProduto_Associada() {
		return " inner join " + CategoriaProdutoContract.TABLE_NAME + " on " + CategoriaProdutoContract.TABLE_NAME + "." + CategoriaProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_CATEGORIA_PRODUTO_A + " ";  
	}
	public static String innerJoinSincCategoriaProduto_Associada() {
		return " inner join " + CategoriaProdutoContract.TABLE_NAME_SINC + " on " + CategoriaProdutoContract.TABLE_NAME_SINC + "." + CategoriaProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_CATEGORIA_PRODUTO_A + " ";  
	}
	public static String outerJoinCategoriaProduto_Associada() {
		return " left outer join " + CategoriaProdutoContract.TABLE_NAME + " on " + CategoriaProdutoContract.TABLE_NAME + "." + CategoriaProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_CATEGORIA_PRODUTO_A + " "; 
	}
	public static String outerJoinSincCategoriaProduto_Associada() {
		return " left outer join " + CategoriaProdutoContract.TABLE_NAME_SINC + " on " + CategoriaProdutoContract.TABLE_NAME_SINC + "." + CategoriaProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_CATEGORIA_PRODUTO_A + " ";   
	}
	
	
	public static MontadorDaoI adicionaMontadorCategoriaProdutoAssociada (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(CategoriaProdutoContract.getMontador(), "CategoriaProduto_Associada");
		return montador;
	}
	public static Uri adicionaCategoriaProdutoAssociada(Uri uri) {
		return uri.buildUpon().appendPath("ComCategoriaProdutoAssociada").build();
	}
 	
	
	
	
	
	public static String camposOrdenados() 
	{
		return " " + TABLE_NAME + "." + COLUNA_ID_CLIENTE_INTERESSE_CATEGORIA  
		+ " , " + TABLE_NAME + "." + COLUNA_ID_CLIENTE_A 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_CATEGORIA_PRODUTO_A 
		
		
		;
	}
	
	
	
	public static String camposOrdenadosSinc() 
	{
		return " cliente_interesse_categoria_sinc.id_cliente_interesse_categoria " 
		+ " ,cliente_interesse_categoria_sinc.id_cliente_a " 
		+ " ,cliente_interesse_categoria_sinc.id_categoria_produto_a " 
		
		
		+ " ,cliente_interesse_categoria_sinc.operacao_sinc "
		;
	}
	
	
	public static MontadorDaoComposite getMontadorComposto() {
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(getMontador(),null);
		return montador;
	}
	public static MontadorDaoI getMontador() {
		return new ClienteInteresseCategoriaMontador();
	}
	
	
	// ComChaveEstrangeira
  	
	public static Uri buildAllComClienteAssociada() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ClienteInteresseCategoriaContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComClienteAssociada").build();
		return saida;
	}
	/*
	public static Uri buildAllSemClienteAssociada() {
		Uri saida = CONTENT_URI;
		saida = saida.buildUpon().appendPath(ClienteInteresseCategoriaContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemClienteAssociada").build();
		return saida;
	}
	*/	
	
	public static Uri buildAllComCategoriaProdutoAssociada() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ClienteInteresseCategoriaContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComCategoriaProdutoAssociada").build();
		return saida;
	}
	/*
	public static Uri buildAllSemCategoriaProdutoAssociada() {
		Uri saida = CONTENT_URI;
		saida = saida.buildUpon().appendPath(ClienteInteresseCategoriaContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemCategoriaProdutoAssociada").build();
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
	
	
	public static List<ClienteInteresseCategoria> converteLista(Cursor data) {
		return converteLista(data, getMontador());
	}
	public static List<ClienteInteresseCategoria> converteLista(Cursor data, MontadorDaoI montador) {
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