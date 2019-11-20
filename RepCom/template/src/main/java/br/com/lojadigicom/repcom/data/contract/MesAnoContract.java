

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
import br.com.lojadigicom.repcom.modelo.MesAno;
import br.com.lojadigicom.repcom.modelo.montador.MesAnoMontador;


public final class MesAnoContract implements BaseColumns {
	
	
	private static AplicacaoContract aplicacaoContract;
	public static void setAplicacaoContract(AplicacaoContract valor) {
		aplicacaoContract = valor;
	}
	public static String getContentAuthority() {
		return aplicacaoContract.getContentAuthority();
	}
	
	
	public static final String PATH = "mesano";
	public static final String COM_COMPLEMENTO = "ComComplemento";
	public static final String COM_RETIRADA = "ComRetirada";

	//public static final Uri CONTENT_URI = aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();

    //public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
    //public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;

    public static final String TABLE_NAME = "mesano";
    public static final String TABLE_NAME_SINC = "mesano_sinc";

	public static Uri getContentUri() {
		return aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();
	}
	public static String getContentType() {
		return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static String getContentItemType() {
		return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static Uri buildMesAnoUri(long id) {
    	return ContentUris.withAppendedId(getContentUri(), id);
    }
    
	
	
    //public static final int COL_ID = 0;
   
    public static final String COLUNA_ID_MES_ANO = "id_mes_ano";
    public static final int COL_ID_MES_ANO = 0;
    public static final String COLUNA_MES = "mes";
    public static final int COL_MES = 1;
    public static final String COLUNA_ANO = "ano";
    public static final int COL_ANO = 2;
    public static final String COLUNA_DESCRICAO_TELA = "descricao_tela";
    public static final int COL_DESCRICAO_TELA = 3;
    public static final String COLUNA_DATA_REFERENCIA = "data_referencia";
    public static final int COL_DATA_REFERENCIA = 4;
	
 	public static final String COLUNA_CHAVE = COLUNA_ID_MES_ANO;
 	public static final String COLUNA_OPERACAO_SINC = "operacao_sinc";
 	public static final int COL_OPERACAO_SINC = 5;
	
	public static final String[] COLS = new String[] { 
			TABLE_NAME + "." + COLUNA_CHAVE
        	, TABLE_NAME + "." +COLUNA_MES
        	, TABLE_NAME + "." +COLUNA_ANO
        	, TABLE_NAME + "." +COLUNA_DESCRICAO_TELA
        	, TABLE_NAME + "." +COLUNA_DATA_REFERENCIA
    };
    
    public static final String[] COLS_SINC = new String[] { 
			TABLE_NAME_SINC + "." + COLUNA_CHAVE
        	, TABLE_NAME_SINC + "." +COLUNA_MES
        	, TABLE_NAME_SINC + "." +COLUNA_ANO
        	, TABLE_NAME_SINC + "." +COLUNA_DESCRICAO_TELA
        	, TABLE_NAME_SINC + "." +COLUNA_DATA_REFERENCIA
			, TABLE_NAME_SINC + "." + COLUNA_OPERACAO_SINC
    };
    
    private static MesAnoOperacao operacao = new MesAnoOperacao();
 	public static Uri buildObtemMesCorrente() {
		return operacao.buildObtemMesCorrente(getFiltro());
    }
    private static MontadorDaoI _montadorObtemMesCorrente = null;
    public static MontadorDaoI getMontadorObtemMesCorrente() {
    	return _montadorObtemMesCorrente;
    }
    public static void setMontadorObtemMesCorrente(MontadorDaoI montador) {
    	_montadorObtemMesCorrente = montador;
    }
    
	private static MesAnoFiltro filtro = null;
	public static MesAnoFiltro getFiltro() {
		if (filtro==null) {
			filtro = new MesAnoFiltro();
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
	
	
	
	
	
	public static String camposOrdenados() 
	{
		return " " + TABLE_NAME + "." + COLUNA_ID_MES_ANO  
		+ " , " + TABLE_NAME + "." + COLUNA_MES 
		+ " , " + TABLE_NAME + "." + COLUNA_ANO 
		+ " , " + TABLE_NAME + "." + COLUNA_DESCRICAO_TELA 
		+ " , " + TABLE_NAME + "." + COLUNA_DATA_REFERENCIA 
		
		
		;
	}
	
	
	
	public static String camposOrdenadosSinc() 
	{
		return " mesano_sinc.id_mes_ano " 
		+ " ,mesano_sinc.mes " 
		+ " ,mesano_sinc.ano " 
		+ " ,mesano_sinc.descricao_tela " 
		+ " ,mesano_sinc.data_referencia " 
		
		
		+ " ,mesano_sinc.operacao_sinc "
		;
	}
	
	
	public static MontadorDaoComposite getMontadorComposto() {
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(getMontador(),null);
		return montador;
	}
	public static MontadorDaoI getMontador() {
		return new MesAnoMontador();
	}
	
	
	// ComChaveEstrangeira
  	
	
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
	
	
	public static List<MesAno> converteLista(Cursor data) {
		return converteLista(data, getMontador());
	}
	public static List<MesAno> converteLista(Cursor data, MontadorDaoI montador) {
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