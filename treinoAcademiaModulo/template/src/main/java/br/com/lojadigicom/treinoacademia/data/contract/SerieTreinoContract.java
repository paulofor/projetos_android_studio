

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
import br.com.lojadigicom.treinoacademia.modelo.SerieTreino;
import br.com.lojadigicom.treinoacademia.modelo.montador.SerieTreinoMontador;


public final class SerieTreinoContract implements BaseColumns {
	
	
	private static AplicacaoContract aplicacaoContract;
	public static void setAplicacaoContract(AplicacaoContract valor) {
		aplicacaoContract = valor;
	}
	public static String getContentAuthority() {
		return aplicacaoContract.getContentAuthority();
	}
	
	
	public static final String PATH = "serie_treino";
	public static final String COM_COMPLEMENTO = "ComComplemento";
	public static final String COM_RETIRADA = "ComRetirada";

	//public static final Uri CONTENT_URI = aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();

    //public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
    //public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;

    public static final String TABLE_NAME = "serie_treino";
    public static final String TABLE_NAME_SINC = "serie_treino_sinc";

	public static Uri getContentUri() {
		return aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();
	}
	public static String getContentType() {
		return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static String getContentItemType() {
		return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static Uri buildSerieTreinoUri(long id) {
    	return ContentUris.withAppendedId(getContentUri(), id);
    }
    
	
	
    //public static final int COL_ID = 0;
   
    public static final String COLUNA_ID_SERIE_TREINO = "id_serie_treino";
    public static final int COL_ID_SERIE_TREINO = 0;
    public static final String COLUNA_QTDE_EXECUCAO = "qtde_execucao";
    public static final int COL_QTDE_EXECUCAO = 1;
    public static final String COLUNA_ATIVA = "ativa";
    public static final int COL_ATIVA = 2;
    public static final String COLUNA_DATA_CRIACAO = "data_criacao";
    public static final int COL_DATA_CRIACAO = 3;
    public static final String COLUNA_DATA_ULTIMA_EXECUCAO = "data_ultima_execucao";
    public static final int COL_DATA_ULTIMA_EXECUCAO = 4;
    public static final String COLUNA_ID_USUARIO_S = "id_usuario_s";
    public static final int COL_ID_USUARIO_S = 5;
	
	
 	public static final String COLUNA_CHAVE = COLUNA_ID_SERIE_TREINO;
 	public static final String COLUNA_OPERACAO_SINC = "operacao_sinc";
 	public static final int COL_OPERACAO_SINC = 6;
	
	public static final String[] COLS = new String[] { 
			TABLE_NAME + "." + COLUNA_CHAVE
        	, TABLE_NAME + "." +COLUNA_QTDE_EXECUCAO
        	, TABLE_NAME + "." +COLUNA_ATIVA
        	, TABLE_NAME + "." +COLUNA_DATA_CRIACAO
        	, TABLE_NAME + "." +COLUNA_DATA_ULTIMA_EXECUCAO
			, TABLE_NAME + "." +COLUNA_ID_USUARIO_S
	
    };
    
    public static final String[] COLS_SINC = new String[] { 
			TABLE_NAME_SINC + "." + COLUNA_CHAVE
        	, TABLE_NAME_SINC + "." +COLUNA_QTDE_EXECUCAO
        	, TABLE_NAME_SINC + "." +COLUNA_ATIVA
        	, TABLE_NAME_SINC + "." +COLUNA_DATA_CRIACAO
        	, TABLE_NAME_SINC + "." +COLUNA_DATA_ULTIMA_EXECUCAO
			, TABLE_NAME_SINC + "." +COLUNA_ID_USUARIO_S
	
			, TABLE_NAME_SINC + "." + COLUNA_OPERACAO_SINC
    };
    
    private static SerieTreinoOperacao operacao = new SerieTreinoOperacao();
 	public static Uri buildObtemProxima() {
		return operacao.buildObtemProxima(getFiltro());
    }
    private static MontadorDaoI _montadorObtemProxima = null;
    public static MontadorDaoI getMontadorObtemProxima() {
    	return _montadorObtemProxima;
    }
    public static void setMontadorObtemProxima(MontadorDaoI montador) {
    	_montadorObtemProxima = montador;
    }
 	public static Uri buildObtemMontadaPorId() {
		return operacao.buildObtemMontadaPorId(getFiltro());
    }
    private static MontadorDaoI _montadorObtemMontadaPorId = null;
    public static MontadorDaoI getMontadorObtemMontadaPorId() {
    	return _montadorObtemMontadaPorId;
    }
    public static void setMontadorObtemMontadaPorId(MontadorDaoI montador) {
    	_montadorObtemMontadaPorId = montador;
    }
 	public static Uri buildCriaSerieCompleta() {
		return operacao.buildCriaSerieCompleta(getFiltro());
    }
    private static MontadorDaoI _montadorCriaSerieCompleta = null;
    public static MontadorDaoI getMontadorCriaSerieCompleta() {
    	return _montadorCriaSerieCompleta;
    }
    public static void setMontadorCriaSerieCompleta(MontadorDaoI montador) {
    	_montadorCriaSerieCompleta = montador;
    }
 	public static Uri buildCarregaItemSerie() {
		return operacao.buildCarregaItemSerie(getFiltro());
    }
    private static MontadorDaoI _montadorCarregaItemSerie = null;
    public static MontadorDaoI getMontadorCarregaItemSerie() {
    	return _montadorCarregaItemSerie;
    }
    public static void setMontadorCarregaItemSerie(MontadorDaoI montador) {
    	_montadorCarregaItemSerie = montador;
    }
    
	private static SerieTreinoFiltro filtro = null;
	public static SerieTreinoFiltro getFiltro() {
		if (filtro==null) {
			filtro = new SerieTreinoFiltro();
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
	
	public static String innerJoinItemSerie_Possui() {
		return " inner join " + ItemSerieContract.TABLE_NAME + " on " + ItemSerieContract.TABLE_NAME + ".id_serie_treino_pa = " + TABLE_NAME + "." + COLUNA_CHAVE + " ";  
	}
	public static String innerJoinSincItemSerie_Possui() {
		return " inner join " + ItemSerieContract.TABLE_NAME_SINC + " on " + ItemSerieContract.TABLE_NAME_SINC + ".id_serie_treino_pa = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";  
	}
	public static String outerJoinItemSerie_Possui() {
		return " left outer join " + ItemSerieContract.TABLE_NAME + " on " + ItemSerieContract.TABLE_NAME + ".id_serie_treino_pa = " + TABLE_NAME + "." + COLUNA_CHAVE + " "; 
	}
	public static String outerJoinSincItemSerie_Possui() {
		return " left outer join " + ItemSerieContract.TABLE_NAME_SINC + " on " + ItemSerieContract.TABLE_NAME_SINC + ".id_serie_treino_pa = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";   
	}
	public static MontadorDaoI adicionaMontadorItemSeriePossuiLista (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(ItemSerieContract.getMontador(), "ItemSerie_Possui");
		return montador;
	}
 	
	public static String innerJoinDiaTreino_SerieDia() {
		return " inner join " + DiaTreinoContract.TABLE_NAME + " on " + DiaTreinoContract.TABLE_NAME + ".id_serie_treino_sd = " + TABLE_NAME + "." + COLUNA_CHAVE + " ";  
	}
	public static String innerJoinSincDiaTreino_SerieDia() {
		return " inner join " + DiaTreinoContract.TABLE_NAME_SINC + " on " + DiaTreinoContract.TABLE_NAME_SINC + ".id_serie_treino_sd = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";  
	}
	public static String outerJoinDiaTreino_SerieDia() {
		return " left outer join " + DiaTreinoContract.TABLE_NAME + " on " + DiaTreinoContract.TABLE_NAME + ".id_serie_treino_sd = " + TABLE_NAME + "." + COLUNA_CHAVE + " "; 
	}
	public static String outerJoinSincDiaTreino_SerieDia() {
		return " left outer join " + DiaTreinoContract.TABLE_NAME_SINC + " on " + DiaTreinoContract.TABLE_NAME_SINC + ".id_serie_treino_sd = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";   
	}
	public static MontadorDaoI adicionaMontadorDiaTreinoSerieDiaLista (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(DiaTreinoContract.getMontador(), "DiaTreino_SerieDia");
		return montador;
	}
 	
	// Com chave
	
	
	
	
	
	public static String camposOrdenados() 
	{
		return " " + TABLE_NAME + "." + COLUNA_ID_SERIE_TREINO  
		+ " , " + TABLE_NAME + "." + COLUNA_QTDE_EXECUCAO 
		+ " , " + TABLE_NAME + "." + COLUNA_ATIVA 
		+ " , " + TABLE_NAME + "." + COLUNA_DATA_CRIACAO 
		+ " , " + TABLE_NAME + "." + COLUNA_DATA_ULTIMA_EXECUCAO 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_USUARIO_S 
		
		
		;
	}
	
	
	
	public static String camposOrdenadosSinc() 
	{
		return " serie_treino_sinc.id_serie_treino " 
		+ " ,serie_treino_sinc.qtde_execucao " 
		+ " ,serie_treino_sinc.ativa " 
		+ " ,serie_treino_sinc.data_criacao " 
		+ " ,serie_treino_sinc.data_ultima_execucao " 
		+ " ,serie_treino_sinc.id_usuario_s " 
		
		
		+ " ,serie_treino_sinc.operacao_sinc "
		;
	}
	
	
	public static MontadorDaoComposite getMontadorComposto() {
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(getMontador(),null);
		return montador;
	}
	public static MontadorDaoI getMontador() {
		return new SerieTreinoMontador();
	}
	
	
	// ComChaveEstrangeira
  	
	public static Uri buildAllComUsuarioSincroniza() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(SerieTreinoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComUsuarioSincroniza").build();
		return saida;
	}
	//  Recoloquei o metodo abaixo pq existe referencia no Provider
	public static Uri buildAllSemUsuarioSincroniza() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(SerieTreinoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemUsuarioSincroniza").build();
		return saida;
	}
	
	
	
	// SemChaveEstrangeira
  	
	
	public static Uri buildAllComItemSeriePossui() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(SerieTreinoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComItemSeriePossui").build();
		return saida;
	}	
	public static Uri buildAllSemItemSeriePossui() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(SerieTreinoContract.COM_RETIRADA).build();
		saida = saida.buildUpon().appendPath("SemItemSeriePossui").build();
		return saida;
	}	
	
	
	
	public static Uri buildAllComDiaTreinoSerieDia() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(SerieTreinoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComDiaTreinoSerieDia").build();
		return saida;
	}	
	public static Uri buildAllSemDiaTreinoSerieDia() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(SerieTreinoContract.COM_RETIRADA).build();
		saida = saida.buildUpon().appendPath("SemDiaTreinoSerieDia").build();
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
	
	
	public static List<SerieTreino> converteLista(Cursor data) {
		return converteLista(data, getMontador());
	}
	public static List<SerieTreino> converteLista(Cursor data, MontadorDaoI montador) {
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