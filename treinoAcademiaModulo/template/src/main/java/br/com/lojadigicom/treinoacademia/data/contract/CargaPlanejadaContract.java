

package  br.com.lojadigicom.treinoacademia.data.contract;



import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.content.ContentUris;

import java.util.ArrayList;
import java.util.List;

import br.com.lojadigicom.treinoacademia.framework.dao.DaoException;
import br.com.lojadigicom.treinoacademia.framework.dao.DaoItemRetorno;
import br.com.lojadigicom.treinoacademia.framework.dao.MontadorDaoI;
import br.com.lojadigicom.treinoacademia.framework.data.MontadorDaoComposite;
import br.com.lojadigicom.treinoacademia.framework.log.DCLog;
import br.com.lojadigicom.treinoacademia.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.treinoacademia.modelo.CargaPlanejada;
import br.com.lojadigicom.treinoacademia.modelo.montador.CargaPlanejadaMontador;


public final class CargaPlanejadaContract implements BaseColumns {
	
	
	private static AplicacaoContract aplicacaoContract;
	public static void setAplicacaoContract(AplicacaoContract valor) {
		aplicacaoContract = valor;
	}
	public static String getContentAuthority() {
		return aplicacaoContract.getContentAuthority();
	}
	
	
	public static final String PATH = "carga_planejada";
	public static final String COM_COMPLEMENTO = "ComComplemento";
	public static final String COM_RETIRADA = "ComRetirada";

	//public static final Uri CONTENT_URI = aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();

    //public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
    //public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;

    public static final String TABLE_NAME = "carga_planejada";
    public static final String TABLE_NAME_SINC = "carga_planejada_sinc";

	public static Uri getContentUri() {
		return aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();
	}
	public static String getContentType() {
		return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static String getContentItemType() {
		return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static Uri buildCargaPlanejadaUri(long id) {
    	return ContentUris.withAppendedId(getContentUri(), id);
    }
    
	public static Uri buildGetPorReferenteAItemSerieUri(long id) {
    	Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(""+id).build();
		saida = saida.buildUpon().appendPath(ItemSerieContract.PATH).build();
    	return saida;
    }
	
	
	
    //public static final int COL_ID = 0;
   
    public static final String COLUNA_ID_CARGA_PLANEJADA = "id_carga_planejada";
    public static final int COL_ID_CARGA_PLANEJADA = 0;
    public static final String COLUNA_VALOR_CARGA = "valor_carga";
    public static final int COL_VALOR_CARGA = 1;
    public static final String COLUNA_DATA_INICIO = "data_inicio";
    public static final int COL_DATA_INICIO = 2;
    public static final String COLUNA_DATA_FINAL = "data_final";
    public static final int COL_DATA_FINAL = 3;
    public static final String COLUNA_ATIVA = "ativa";
    public static final int COL_ATIVA = 4;
    public static final String COLUNA_QUANTIDADE_REPETICAO = "quantidade_repeticao";
    public static final int COL_QUANTIDADE_REPETICAO = 5;
    public static final String COLUNA_ORDEM_REPETICAO = "ordem_repeticao";
    public static final int COL_ORDEM_REPETICAO = 6;
    public static final String COLUNA_ID_ITEM_SERIE_RA = "id_item_serie_ra";
    public static final int COL_ID_ITEM_SERIE_RA = 7;
	
    public static final String COLUNA_ID_USUARIO_S = "id_usuario_s";
    public static final int COL_ID_USUARIO_S = 8;
	
	
 	public static final String COLUNA_CHAVE = COLUNA_ID_CARGA_PLANEJADA;
 	public static final String COLUNA_OPERACAO_SINC = "operacao_sinc";
 	public static final int COL_OPERACAO_SINC = 9;
	
	public static final String[] COLS = new String[] { 
			TABLE_NAME + "." + COLUNA_CHAVE
        	, TABLE_NAME + "." +COLUNA_VALOR_CARGA
        	, TABLE_NAME + "." +COLUNA_DATA_INICIO
        	, TABLE_NAME + "." +COLUNA_DATA_FINAL
        	, TABLE_NAME + "." +COLUNA_ATIVA
        	, TABLE_NAME + "." +COLUNA_QUANTIDADE_REPETICAO
        	, TABLE_NAME + "." +COLUNA_ORDEM_REPETICAO
			, TABLE_NAME + "." +COLUNA_ID_ITEM_SERIE_RA
	
			, TABLE_NAME + "." +COLUNA_ID_USUARIO_S
	
    };
    
    public static final String[] COLS_SINC = new String[] { 
			TABLE_NAME_SINC + "." + COLUNA_CHAVE
        	, TABLE_NAME_SINC + "." +COLUNA_VALOR_CARGA
        	, TABLE_NAME_SINC + "." +COLUNA_DATA_INICIO
        	, TABLE_NAME_SINC + "." +COLUNA_DATA_FINAL
        	, TABLE_NAME_SINC + "." +COLUNA_ATIVA
        	, TABLE_NAME_SINC + "." +COLUNA_QUANTIDADE_REPETICAO
        	, TABLE_NAME_SINC + "." +COLUNA_ORDEM_REPETICAO
			, TABLE_NAME_SINC + "." +COLUNA_ID_ITEM_SERIE_RA
	
			, TABLE_NAME_SINC + "." +COLUNA_ID_USUARIO_S
	
			, TABLE_NAME_SINC + "." + COLUNA_OPERACAO_SINC
    };
    
    private static CargaPlanejadaOperacao operacao = new CargaPlanejadaOperacao();
 	public static Uri buildListaCargaPorExercicio() {
		return operacao.buildListaCargaPorExercicio(getFiltro());
    }
    private static MontadorDaoI _montadorListaCargaPorExercicio = null;
    public static MontadorDaoI getMontadorListaCargaPorExercicio() {
    	return _montadorListaCargaPorExercicio;
    }
    public static void setMontadorListaCargaPorExercicio(MontadorDaoI montador) {
    	_montadorListaCargaPorExercicio = montador;
    }
 	public static Uri buildListaCargaAtivaPorExercicio() {
		return operacao.buildListaCargaAtivaPorExercicio(getFiltro());
    }
    private static MontadorDaoI _montadorListaCargaAtivaPorExercicio = null;
    public static MontadorDaoI getMontadorListaCargaAtivaPorExercicio() {
    	return _montadorListaCargaAtivaPorExercicio;
    }
    public static void setMontadorListaCargaAtivaPorExercicio(MontadorDaoI montador) {
    	_montadorListaCargaAtivaPorExercicio = montador;
    }
    
	private static CargaPlanejadaFiltro filtro = null;
	public static CargaPlanejadaFiltro getFiltro() {
		if (filtro==null) {
			filtro = new CargaPlanejadaFiltro();
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
	
	
	public static String innerJoinItemSerie_ReferenteA() {
		return " inner join " + ItemSerieContract.TABLE_NAME + " on " + ItemSerieContract.TABLE_NAME + "." + ItemSerieContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_ITEM_SERIE_RA + " ";  
	}
	public static String innerJoinSincItemSerie_ReferenteA() {
		return " inner join " + ItemSerieContract.TABLE_NAME_SINC + " on " + ItemSerieContract.TABLE_NAME_SINC + "." + ItemSerieContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_ITEM_SERIE_RA + " ";  
	}
	public static String outerJoinItemSerie_ReferenteA() {
		return " left outer join " + ItemSerieContract.TABLE_NAME + " on " + ItemSerieContract.TABLE_NAME + "." + ItemSerieContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_ITEM_SERIE_RA + " "; 
	}
	public static String outerJoinSincItemSerie_ReferenteA() {
		return " left outer join " + ItemSerieContract.TABLE_NAME_SINC + " on " + ItemSerieContract.TABLE_NAME_SINC + "." + ItemSerieContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_ITEM_SERIE_RA + " ";   
	}
	
	
	public static MontadorDaoI adicionaMontadorItemSerieReferenteA (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(ItemSerieContract.getMontador(), "ItemSerie_ReferenteA");
		return montador;
	}
	public static Uri adicionaItemSerieReferenteA(Uri uri) {
		return uri.buildUpon().appendPath("ComItemSerieReferenteA").build();
	}
 	
	
	
	
	
	public static String camposOrdenados() 
	{
		return " " + TABLE_NAME + "." + COLUNA_ID_CARGA_PLANEJADA  
		+ " , " + TABLE_NAME + "." + COLUNA_VALOR_CARGA 
		+ " , " + TABLE_NAME + "." + COLUNA_DATA_INICIO 
		+ " , " + TABLE_NAME + "." + COLUNA_DATA_FINAL 
		+ " , " + TABLE_NAME + "." + COLUNA_ATIVA 
		+ " , " + TABLE_NAME + "." + COLUNA_QUANTIDADE_REPETICAO 
		+ " , " + TABLE_NAME + "." + COLUNA_ORDEM_REPETICAO 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_ITEM_SERIE_RA 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_USUARIO_S 
		
		
		;
	}
	
	
	
	public static String camposOrdenadosSinc() 
	{
		return " carga_planejada_sinc.id_carga_planejada " 
		+ " ,carga_planejada_sinc.valor_carga " 
		+ " ,carga_planejada_sinc.data_inicio " 
		+ " ,carga_planejada_sinc.data_final " 
		+ " ,carga_planejada_sinc.ativa " 
		+ " ,carga_planejada_sinc.quantidade_repeticao " 
		+ " ,carga_planejada_sinc.ordem_repeticao " 
		+ " ,carga_planejada_sinc.id_item_serie_ra " 
		+ " ,carga_planejada_sinc.id_usuario_s " 
		
		
		+ " ,carga_planejada_sinc.operacao_sinc "
		;
	}
	
	
	public static MontadorDaoComposite getMontadorComposto() {
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(getMontador(),null);
		return montador;
	}
	public static MontadorDaoI getMontador() {
		return new CargaPlanejadaMontador();
	}
	
	
	// ComChaveEstrangeira
  	
	public static Uri buildAllComItemSerieReferenteA() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(CargaPlanejadaContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComItemSerieReferenteA").build();
		return saida;
	}
	//  Recoloquei o metodo abaixo pq existe referencia no Provider
	public static Uri buildAllSemItemSerieReferenteA() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(CargaPlanejadaContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemItemSerieReferenteA").build();
		return saida;
	}
	
	
	public static Uri buildAllComUsuarioSincroniza() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(CargaPlanejadaContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComUsuarioSincroniza").build();
		return saida;
	}
	//  Recoloquei o metodo abaixo pq existe referencia no Provider
	public static Uri buildAllSemUsuarioSincroniza() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(CargaPlanejadaContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemUsuarioSincroniza").build();
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
	
	
	public static List<CargaPlanejada> converteLista(Cursor data) {
		return converteLista(data, getMontador());
	}
	public static List<CargaPlanejada> converteLista(Cursor data, MontadorDaoI montador) {
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