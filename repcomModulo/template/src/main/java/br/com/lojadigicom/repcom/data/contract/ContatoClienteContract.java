

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
import br.com.lojadigicom.repcom.modelo.ContatoCliente;
import br.com.lojadigicom.repcom.modelo.montador.ContatoClienteMontador;


public final class ContatoClienteContract implements BaseColumns {
	
	
	private static AplicacaoContract aplicacaoContract;
	public static void setAplicacaoContract(AplicacaoContract valor) {
		aplicacaoContract = valor;
	}
	public static String getContentAuthority() {
		return aplicacaoContract.getContentAuthority();
	}
	
	
	public static final String PATH = "contato_cliente";
	public static final String COM_COMPLEMENTO = "ComComplemento";
	public static final String COM_RETIRADA = "ComRetirada";

	//public static final Uri CONTENT_URI = aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();

    //public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
    //public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;

    public static final String TABLE_NAME = "contato_cliente";
    public static final String TABLE_NAME_SINC = "contato_cliente_sinc";

	public static Uri getContentUri() {
		return aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();
	}
	public static String getContentType() {
		return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static String getContentItemType() {
		return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static Uri buildContatoClienteUri(long id) {
    	return ContentUris.withAppendedId(getContentUri(), id);
    }
    
	public static Uri buildGetPorReferenteAClienteUri(long id) {
    	Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(""+id).build();
		saida = saida.buildUpon().appendPath(ClienteContract.PATH).build();
    	return saida;
    }
	
	
	
    //public static final int COL_ID = 0;
   
    public static final String COLUNA_ID_CONTATO_CLIENTE = "id_contato_cliente";
    public static final int COL_ID_CONTATO_CLIENTE = 0;
    public static final String COLUNA_DATA_CONTATO = "data_contato";
    public static final int COL_DATA_CONTATO = 1;
    public static final String COLUNA_ID_CLIENTE_RA = "id_cliente_ra";
    public static final int COL_ID_CLIENTE_RA = 2;
	
	
 	public static final String COLUNA_CHAVE = COLUNA_ID_CONTATO_CLIENTE;
 	public static final String COLUNA_OPERACAO_SINC = "operacao_sinc";
 	public static final int COL_OPERACAO_SINC = 3;
	
	public static final String[] COLS = new String[] { 
			TABLE_NAME + "." + COLUNA_CHAVE
        	, TABLE_NAME + "." +COLUNA_DATA_CONTATO
			, TABLE_NAME + "." +COLUNA_ID_CLIENTE_RA
	
    };
    
    public static final String[] COLS_SINC = new String[] { 
			TABLE_NAME_SINC + "." + COLUNA_CHAVE
        	, TABLE_NAME_SINC + "." +COLUNA_DATA_CONTATO
			, TABLE_NAME_SINC + "." +COLUNA_ID_CLIENTE_RA
	
			, TABLE_NAME_SINC + "." + COLUNA_OPERACAO_SINC
    };
    
    private static ContatoClienteOperacao operacao = new ContatoClienteOperacao();
    
	private static ContatoClienteFiltro filtro = null;
	public static ContatoClienteFiltro getFiltro() {
		if (filtro==null) {
			filtro = new ContatoClienteFiltro();
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
	
	
	public static String innerJoinCliente_ReferenteA() {
		return " inner join " + ClienteContract.TABLE_NAME + " on " + ClienteContract.TABLE_NAME + "." + ClienteContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_CLIENTE_RA + " ";  
	}
	public static String innerJoinSincCliente_ReferenteA() {
		return " inner join " + ClienteContract.TABLE_NAME_SINC + " on " + ClienteContract.TABLE_NAME_SINC + "." + ClienteContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_CLIENTE_RA + " ";  
	}
	public static String outerJoinCliente_ReferenteA() {
		return " left outer join " + ClienteContract.TABLE_NAME + " on " + ClienteContract.TABLE_NAME + "." + ClienteContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_CLIENTE_RA + " "; 
	}
	public static String outerJoinSincCliente_ReferenteA() {
		return " left outer join " + ClienteContract.TABLE_NAME_SINC + " on " + ClienteContract.TABLE_NAME_SINC + "." + ClienteContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_CLIENTE_RA + " ";   
	}
	
	
	public static MontadorDaoI adicionaMontadorClienteReferenteA (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(ClienteContract.getMontador(), "Cliente_ReferenteA");
		return montador;
	}
	public static Uri adicionaClienteReferenteA(Uri uri) {
		return uri.buildUpon().appendPath("ComClienteReferenteA").build();
	}
 	
	
	
	
	
	public static String camposOrdenados() 
	{
		return " " + TABLE_NAME + "." + COLUNA_ID_CONTATO_CLIENTE  
		+ " , " + TABLE_NAME + "." + COLUNA_DATA_CONTATO 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_CLIENTE_RA 
		
		
		;
	}
	
	
	
	public static String camposOrdenadosSinc() 
	{
		return " contato_cliente_sinc.id_contato_cliente " 
		+ " ,contato_cliente_sinc.data_contato " 
		+ " ,contato_cliente_sinc.id_cliente_ra " 
		
		
		+ " ,contato_cliente_sinc.operacao_sinc "
		;
	}
	
	
	public static MontadorDaoComposite getMontadorComposto() {
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(getMontador(),null);
		return montador;
	}
	public static MontadorDaoI getMontador() {
		return new ContatoClienteMontador();
	}
	
	
	// ComChaveEstrangeira
  	
	public static Uri buildAllComClienteReferenteA() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ContatoClienteContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComClienteReferenteA").build();
		return saida;
	}
	//  Recoloquei o metodo abaixo pq existe referencia no Provider
	public static Uri buildAllSemClienteReferenteA() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ContatoClienteContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemClienteReferenteA").build();
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
	
	
	public static List<ContatoCliente> converteLista(Cursor data) {
		return converteLista(data, getMontador());
	}
	public static List<ContatoCliente> converteLista(Cursor data, MontadorDaoI montador) {
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