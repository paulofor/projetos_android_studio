

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
import br.com.lojadigicom.repcom.modelo.ParcelaVenda;
import br.com.lojadigicom.repcom.modelo.montador.ParcelaVendaMontador;


public final class ParcelaVendaContract implements BaseColumns {
	
	
	private static AplicacaoContract aplicacaoContract;
	public static void setAplicacaoContract(AplicacaoContract valor) {
		aplicacaoContract = valor;
	}
	public static String getContentAuthority() {
		return aplicacaoContract.getContentAuthority();
	}
	
	
	public static final String PATH = "parcela_venda";
	public static final String COM_COMPLEMENTO = "ComComplemento";
	public static final String COM_RETIRADA = "ComRetirada";

	//public static final Uri CONTENT_URI = aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();

    //public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
    //public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;

    public static final String TABLE_NAME = "parcela_venda";
    public static final String TABLE_NAME_SINC = "parcela_venda_sinc";

	public static Uri getContentUri() {
		return aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();
	}
	public static String getContentType() {
		return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static String getContentItemType() {
		return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static Uri buildParcelaVendaUri(long id) {
    	return ContentUris.withAppendedId(getContentUri(), id);
    }
    
	public static Uri buildGetPorPertenceAVendaUri(long id) {
    	Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(""+id).build();
		saida = saida.buildUpon().appendPath(VendaContract.PATH).build();
    	return saida;
    }
	
	
	
    //public static final int COL_ID = 0;
   
    public static final String COLUNA_ID_PARCELA_VENDA = "id_parcela_venda";
    public static final int COL_ID_PARCELA_VENDA = 0;
    public static final String COLUNA_VALOR_PARCELA = "valor_parcela";
    public static final int COL_VALOR_PARCELA = 1;
    public static final String COLUNA_DATA_VENCIMENTO = "data_vencimento";
    public static final int COL_DATA_VENCIMENTO = 2;
    public static final String COLUNA_NUMERO_PARCELA = "numero_parcela";
    public static final int COL_NUMERO_PARCELA = 3;
    public static final String COLUNA_PAGA = "paga";
    public static final int COL_PAGA = 4;
    public static final String COLUNA_ID_VENDA_PA = "id_venda_pa";
    public static final int COL_ID_VENDA_PA = 5;
	
    public static final String COLUNA_ID_USUARIO_S = "id_usuario_s";
    public static final int COL_ID_USUARIO_S = 6;
	
	
 	public static final String COLUNA_CHAVE = COLUNA_ID_PARCELA_VENDA;
 	public static final String COLUNA_OPERACAO_SINC = "operacao_sinc";
 	public static final int COL_OPERACAO_SINC = 7;
	
	public static final String[] COLS = new String[] { 
			TABLE_NAME + "." + COLUNA_CHAVE
        	, TABLE_NAME + "." +COLUNA_VALOR_PARCELA
        	, TABLE_NAME + "." +COLUNA_DATA_VENCIMENTO
        	, TABLE_NAME + "." +COLUNA_NUMERO_PARCELA
        	, TABLE_NAME + "." +COLUNA_PAGA
			, TABLE_NAME + "." +COLUNA_ID_VENDA_PA
	
			, TABLE_NAME + "." +COLUNA_ID_USUARIO_S
	
    };
    
    public static final String[] COLS_SINC = new String[] { 
			TABLE_NAME_SINC + "." + COLUNA_CHAVE
        	, TABLE_NAME_SINC + "." +COLUNA_VALOR_PARCELA
        	, TABLE_NAME_SINC + "." +COLUNA_DATA_VENCIMENTO
        	, TABLE_NAME_SINC + "." +COLUNA_NUMERO_PARCELA
        	, TABLE_NAME_SINC + "." +COLUNA_PAGA
			, TABLE_NAME_SINC + "." +COLUNA_ID_VENDA_PA
	
			, TABLE_NAME_SINC + "." +COLUNA_ID_USUARIO_S
	
			, TABLE_NAME_SINC + "." + COLUNA_OPERACAO_SINC
    };
    
    private static ParcelaVendaOperacao operacao = new ParcelaVendaOperacao();
 	public static Uri buildCalculaParcelasVenda() {
		return operacao.buildCalculaParcelasVenda(getFiltro());
    }
    private static MontadorDaoI _montadorCalculaParcelasVenda = null;
    public static MontadorDaoI getMontadorCalculaParcelasVenda() {
    	return _montadorCalculaParcelasVenda;
    }
    public static void setMontadorCalculaParcelasVenda(MontadorDaoI montador) {
    	_montadorCalculaParcelasVenda = montador;
    }
    
	private static ParcelaVendaFiltro filtro = null;
	public static ParcelaVendaFiltro getFiltro() {
		if (filtro==null) {
			filtro = new ParcelaVendaFiltro();
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
	
	
	public static String innerJoinVenda_PertenceA() {
		return " inner join " + VendaContract.TABLE_NAME + " on " + VendaContract.TABLE_NAME + "." + VendaContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_VENDA_PA + " ";  
	}
	public static String innerJoinSincVenda_PertenceA() {
		return " inner join " + VendaContract.TABLE_NAME_SINC + " on " + VendaContract.TABLE_NAME_SINC + "." + VendaContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_VENDA_PA + " ";  
	}
	public static String outerJoinVenda_PertenceA() {
		return " left outer join " + VendaContract.TABLE_NAME + " on " + VendaContract.TABLE_NAME + "." + VendaContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_VENDA_PA + " "; 
	}
	public static String outerJoinSincVenda_PertenceA() {
		return " left outer join " + VendaContract.TABLE_NAME_SINC + " on " + VendaContract.TABLE_NAME_SINC + "." + VendaContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_VENDA_PA + " ";   
	}
	
	
	public static MontadorDaoI adicionaMontadorVendaPertenceA (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(VendaContract.getMontador(), "Venda_PertenceA");
		return montador;
	}
	public static Uri adicionaVendaPertenceA(Uri uri) {
		return uri.buildUpon().appendPath("ComVendaPertenceA").build();
	}
 	
	
	
	
	
	public static String camposOrdenados() 
	{
		return " " + TABLE_NAME + "." + COLUNA_ID_PARCELA_VENDA  
		+ " , " + TABLE_NAME + "." + COLUNA_VALOR_PARCELA 
		+ " , " + TABLE_NAME + "." + COLUNA_DATA_VENCIMENTO 
		+ " , " + TABLE_NAME + "." + COLUNA_NUMERO_PARCELA 
		+ " , " + TABLE_NAME + "." + COLUNA_PAGA 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_VENDA_PA 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_USUARIO_S 
		
		
		;
	}
	
	
	
	public static String camposOrdenadosSinc() 
	{
		return " parcela_venda_sinc.id_parcela_venda " 
		+ " ,parcela_venda_sinc.valor_parcela " 
		+ " ,parcela_venda_sinc.data_vencimento " 
		+ " ,parcela_venda_sinc.numero_parcela " 
		+ " ,parcela_venda_sinc.paga " 
		+ " ,parcela_venda_sinc.id_venda_pa " 
		+ " ,parcela_venda_sinc.id_usuario_s " 
		
		
		+ " ,parcela_venda_sinc.operacao_sinc "
		;
	}
	
	
	public static MontadorDaoComposite getMontadorComposto() {
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(getMontador(),null);
		return montador;
	}
	public static MontadorDaoI getMontador() {
		return new ParcelaVendaMontador();
	}
	
	
	// ComChaveEstrangeira
  	
	public static Uri buildAllComVendaPertenceA() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ParcelaVendaContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComVendaPertenceA").build();
		return saida;
	}
	/*
	public static Uri buildAllSemVendaPertenceA() {
		Uri saida = CONTENT_URI;
		saida = saida.buildUpon().appendPath(ParcelaVendaContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemVendaPertenceA").build();
		return saida;
	}
	*/	
	
	public static Uri buildAllComUsuarioSincroniza() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ParcelaVendaContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComUsuarioSincroniza").build();
		return saida;
	}
	/*
	public static Uri buildAllSemUsuarioSincroniza() {
		Uri saida = CONTENT_URI;
		saida = saida.buildUpon().appendPath(ParcelaVendaContract.COM_COMPLEMENTO).build();
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
	
	
	public static List<ParcelaVenda> converteLista(Cursor data) {
		return converteLista(data, getMontador());
	}
	public static List<ParcelaVenda> converteLista(Cursor data, MontadorDaoI montador) {
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