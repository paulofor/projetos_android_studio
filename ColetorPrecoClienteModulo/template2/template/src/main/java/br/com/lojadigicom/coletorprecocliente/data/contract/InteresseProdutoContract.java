

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
import br.com.lojadigicom.coletorprecocliente.modelo.InteresseProduto;
import br.com.lojadigicom.coletorprecocliente.modelo.montador.InteresseProdutoMontador;


public final class InteresseProdutoContract implements BaseColumns {
	
	
	private static AplicacaoContract aplicacaoContract;
	public static void setAplicacaoContract(AplicacaoContract valor) {
		aplicacaoContract = valor;
	}
	public static String getContentAuthority() {
		return aplicacaoContract.getContentAuthority();
	}
	
	
	public static final String PATH = "interesse_produto";
	public static final String COM_COMPLEMENTO = "ComComplemento";
	public static final String COM_RETIRADA = "ComRetirada";

	//public static final Uri CONTENT_URI = aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();

    //public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
    //public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;

    public static final String TABLE_NAME = "interesse_produto";
    public static final String TABLE_NAME_SINC = "interesse_produto_sinc";

	public static Uri getContentUri() {
		return aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();
	}
	public static String getContentType() {
		return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static String getContentItemType() {
		return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static Uri buildInteresseProdutoUri(long id) {
    	return ContentUris.withAppendedId(getContentUri(), id);
    }
    
	public static Uri buildGetPorReferenteAProdutoClienteUri(long id) {
    	Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(""+id).build();
		saida = saida.buildUpon().appendPath(ProdutoClienteContract.PATH).build();
    	return saida;
    }
	
	
	
    //public static final int COL_ID = 0;
   
    public static final String COLUNA_ID_INTERESSE_PRODUTO = "id_interesse_produto";
    public static final int COL_ID_INTERESSE_PRODUTO = 0;
    public static final String COLUNA_NOTA = "nota";
    public static final int COL_NOTA = 1;
    public static final String COLUNA_DATA = "data";
    public static final int COL_DATA = 2;
    public static final String COLUNA_VALOR = "valor";
    public static final int COL_VALOR = 3;
    public static final String COLUNA_ID_PRODUTO_CLIENTE_RA = "id_produto_cliente_ra";
    public static final int COL_ID_PRODUTO_CLIENTE_RA = 4;
	
    public static final String COLUNA_ID_USUARIO_S = "id_usuario_s";
    public static final int COL_ID_USUARIO_S = 5;
	
	
 	public static final String COLUNA_CHAVE = COLUNA_ID_INTERESSE_PRODUTO;
 	public static final String COLUNA_OPERACAO_SINC = "operacao_sinc";
 	public static final int COL_OPERACAO_SINC = 6;
	
	public static final String[] COLS = new String[] { 
			TABLE_NAME + "." + COLUNA_CHAVE
        	, TABLE_NAME + "." +COLUNA_NOTA
        	, TABLE_NAME + "." +COLUNA_DATA
        	, TABLE_NAME + "." +COLUNA_VALOR
			, TABLE_NAME + "." +COLUNA_ID_PRODUTO_CLIENTE_RA
	
			, TABLE_NAME + "." +COLUNA_ID_USUARIO_S
	
    };
    
    public static final String[] COLS_SINC = new String[] { 
			TABLE_NAME_SINC + "." + COLUNA_CHAVE
        	, TABLE_NAME_SINC + "." +COLUNA_NOTA
        	, TABLE_NAME_SINC + "." +COLUNA_DATA
        	, TABLE_NAME_SINC + "." +COLUNA_VALOR
			, TABLE_NAME_SINC + "." +COLUNA_ID_PRODUTO_CLIENTE_RA
	
			, TABLE_NAME_SINC + "." +COLUNA_ID_USUARIO_S
	
			, TABLE_NAME_SINC + "." + COLUNA_OPERACAO_SINC
    };
    
    private static InteresseProdutoOperacao operacao = new InteresseProdutoOperacao();
    
	private static InteresseProdutoFiltro filtro = null;
	public static InteresseProdutoFiltro getFiltro() {
		if (filtro==null) {
			filtro = new InteresseProdutoFiltro();
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
	
	
	public static String innerJoinProdutoCliente_ReferenteA() {
		return " inner join " + ProdutoClienteContract.TABLE_NAME + " on " + ProdutoClienteContract.TABLE_NAME + "." + ProdutoClienteContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_PRODUTO_CLIENTE_RA + " ";  
	}
	public static String innerJoinSincProdutoCliente_ReferenteA() {
		return " inner join " + ProdutoClienteContract.TABLE_NAME_SINC + " on " + ProdutoClienteContract.TABLE_NAME_SINC + "." + ProdutoClienteContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_PRODUTO_CLIENTE_RA + " ";  
	}
	public static String outerJoinProdutoCliente_ReferenteA() {
		return " left outer join " + ProdutoClienteContract.TABLE_NAME + " on " + ProdutoClienteContract.TABLE_NAME + "." + ProdutoClienteContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_PRODUTO_CLIENTE_RA + " "; 
	}
	public static String outerJoinSincProdutoCliente_ReferenteA() {
		return " left outer join " + ProdutoClienteContract.TABLE_NAME_SINC + " on " + ProdutoClienteContract.TABLE_NAME_SINC + "." + ProdutoClienteContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_PRODUTO_CLIENTE_RA + " ";   
	}
	
	
	public static MontadorDaoI adicionaMontadorProdutoClienteReferenteA (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(ProdutoClienteContract.getMontador(), "ProdutoCliente_ReferenteA");
		return montador;
	}
	public static Uri adicionaProdutoClienteReferenteA(Uri uri) {
		return uri.buildUpon().appendPath("ComProdutoClienteReferenteA").build();
	}
 	
	
	
	
	
	public static String camposOrdenados() 
	{
		return " " + TABLE_NAME + "." + COLUNA_ID_INTERESSE_PRODUTO  
		+ " , " + TABLE_NAME + "." + COLUNA_NOTA 
		+ " , " + TABLE_NAME + "." + COLUNA_DATA 
		+ " , " + TABLE_NAME + "." + COLUNA_VALOR 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_PRODUTO_CLIENTE_RA 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_USUARIO_S 
		
		
		;
	}
	
	
	
	public static String camposOrdenadosSinc() 
	{
		return " interesse_produto_sinc.id_interesse_produto " 
		+ " ,interesse_produto_sinc.nota " 
		+ " ,interesse_produto_sinc.data " 
		+ " ,interesse_produto_sinc.valor " 
		+ " ,interesse_produto_sinc.id_produto_cliente_ra " 
		+ " ,interesse_produto_sinc.id_usuario_s " 
		
		
		+ " ,interesse_produto_sinc.operacao_sinc "
		;
	}
	
	
	public static MontadorDaoComposite getMontadorComposto() {
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(getMontador(),null);
		return montador;
	}
	public static MontadorDaoI getMontador() {
		return new InteresseProdutoMontador();
	}
	
	
	// ComChaveEstrangeira
  	
	public static Uri buildAllComProdutoClienteReferenteA() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(InteresseProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComProdutoClienteReferenteA").build();
		return saida;
	}
	/*
	public static Uri buildAllSemProdutoClienteReferenteA() {
		Uri saida = CONTENT_URI;
		saida = saida.buildUpon().appendPath(InteresseProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemProdutoClienteReferenteA").build();
		return saida;
	}
	*/	
	
	public static Uri buildAllComUsuarioSincroniza() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(InteresseProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComUsuarioSincroniza").build();
		return saida;
	}
	/*
	public static Uri buildAllSemUsuarioSincroniza() {
		Uri saida = CONTENT_URI;
		saida = saida.buildUpon().appendPath(InteresseProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemUsuarioSincroniza").build();
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
	
	
	public static List<InteresseProduto> converteLista(Cursor data) {
		return converteLista(data, getMontador());
	}
	public static List<InteresseProduto> converteLista(Cursor data, MontadorDaoI montador) {
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