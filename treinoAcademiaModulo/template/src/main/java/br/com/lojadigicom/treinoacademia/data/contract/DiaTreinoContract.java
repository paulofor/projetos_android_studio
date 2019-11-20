

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
import br.com.lojadigicom.treinoacademia.modelo.DiaTreino;
import br.com.lojadigicom.treinoacademia.modelo.montador.DiaTreinoMontador;


public final class DiaTreinoContract implements BaseColumns {
	
	
	private static AplicacaoContract aplicacaoContract;
	public static void setAplicacaoContract(AplicacaoContract valor) {
		aplicacaoContract = valor;
	}
	public static String getContentAuthority() {
		return aplicacaoContract.getContentAuthority();
	}
	
	
	public static final String PATH = "dia_treino";
	public static final String COM_COMPLEMENTO = "ComComplemento";
	public static final String COM_RETIRADA = "ComRetirada";

	//public static final Uri CONTENT_URI = aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();

    //public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
    //public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;

    public static final String TABLE_NAME = "dia_treino";
    public static final String TABLE_NAME_SINC = "dia_treino_sinc";

	public static Uri getContentUri() {
		return aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();
	}
	public static String getContentType() {
		return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static String getContentItemType() {
		return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static Uri buildDiaTreinoUri(long id) {
    	return ContentUris.withAppendedId(getContentUri(), id);
    }
    
	public static Uri buildGetPorSerieDiaSerieTreinoUri(long id) {
    	Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(""+id).build();
		saida = saida.buildUpon().appendPath(SerieTreinoContract.PATH).build();
    	return saida;
    }
	
	
	
    //public static final int COL_ID = 0;
   
    public static final String COLUNA_ID_DIA_TREINO = "id_dia_treino";
    public static final int COL_ID_DIA_TREINO = 0;
    public static final String COLUNA_DATA = "data";
    public static final int COL_DATA = 1;
    public static final String COLUNA_CONCLUIDO = "concluido";
    public static final int COL_CONCLUIDO = 2;
    public static final String COLUNA_ID_USUARIO_S = "id_usuario_s";
    public static final int COL_ID_USUARIO_S = 3;
	
    public static final String COLUNA_ID_SERIE_TREINO_SD = "id_serie_treino_sd";
    public static final int COL_ID_SERIE_TREINO_SD = 4;
	
	
 	public static final String COLUNA_CHAVE = COLUNA_ID_DIA_TREINO;
 	public static final String COLUNA_OPERACAO_SINC = "operacao_sinc";
 	public static final int COL_OPERACAO_SINC = 5;
	
	public static final String[] COLS = new String[] { 
			TABLE_NAME + "." + COLUNA_CHAVE
        	, TABLE_NAME + "." +COLUNA_DATA
        	, TABLE_NAME + "." +COLUNA_CONCLUIDO
			, TABLE_NAME + "." +COLUNA_ID_USUARIO_S
	
			, TABLE_NAME + "." +COLUNA_ID_SERIE_TREINO_SD
	
    };
    
    public static final String[] COLS_SINC = new String[] { 
			TABLE_NAME_SINC + "." + COLUNA_CHAVE
        	, TABLE_NAME_SINC + "." +COLUNA_DATA
        	, TABLE_NAME_SINC + "." +COLUNA_CONCLUIDO
			, TABLE_NAME_SINC + "." +COLUNA_ID_USUARIO_S
	
			, TABLE_NAME_SINC + "." +COLUNA_ID_SERIE_TREINO_SD
	
			, TABLE_NAME_SINC + "." + COLUNA_OPERACAO_SINC
    };
    
    private static DiaTreinoOperacao operacao = new DiaTreinoOperacao();
 	public static Uri buildEncerraDiaTreino() {
		return operacao.buildEncerraDiaTreino(getFiltro());
    }
    private static MontadorDaoI _montadorEncerraDiaTreino = null;
    public static MontadorDaoI getMontadorEncerraDiaTreino() {
    	return _montadorEncerraDiaTreino;
    }
    public static void setMontadorEncerraDiaTreino(MontadorDaoI montador) {
    	_montadorEncerraDiaTreino = montador;
    }
 	public static Uri buildObtemPorData() {
		return operacao.buildObtemPorData(getFiltro());
    }
    private static MontadorDaoI _montadorObtemPorData = null;
    public static MontadorDaoI getMontadorObtemPorData() {
    	return _montadorObtemPorData;
    }
    public static void setMontadorObtemPorData(MontadorDaoI montador) {
    	_montadorObtemPorData = montador;
    }
 	public static Uri buildLimpezaBase() {
		return operacao.buildLimpezaBase(getFiltro());
    }
    private static MontadorDaoI _montadorLimpezaBase = null;
    public static MontadorDaoI getMontadorLimpezaBase() {
    	return _montadorLimpezaBase;
    }
    public static void setMontadorLimpezaBase(MontadorDaoI montador) {
    	_montadorLimpezaBase = montador;
    }
 	public static Uri buildHistoricoExecucaoPorIdExercicio() {
		return operacao.buildHistoricoExecucaoPorIdExercicio(getFiltro());
    }
    private static MontadorDaoI _montadorHistoricoExecucaoPorIdExercicio = null;
    public static MontadorDaoI getMontadorHistoricoExecucaoPorIdExercicio() {
    	return _montadorHistoricoExecucaoPorIdExercicio;
    }
    public static void setMontadorHistoricoExecucaoPorIdExercicio(MontadorDaoI montador) {
    	_montadorHistoricoExecucaoPorIdExercicio = montador;
    }
 	public static Uri buildTreinosPosDataPesquisa() {
		return operacao.buildTreinosPosDataPesquisa(getFiltro());
    }
    private static MontadorDaoI _montadorTreinosPosDataPesquisa = null;
    public static MontadorDaoI getMontadorTreinosPosDataPesquisa() {
    	return _montadorTreinosPosDataPesquisa;
    }
    public static void setMontadorTreinosPosDataPesquisa(MontadorDaoI montador) {
    	_montadorTreinosPosDataPesquisa = montador;
    }
    
	private static DiaTreinoFiltro filtro = null;
	public static DiaTreinoFiltro getFiltro() {
		if (filtro==null) {
			filtro = new DiaTreinoFiltro();
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
	
	public static String innerJoinExecucaoItemSerie_FoiRealizado() {
		return " inner join " + ExecucaoItemSerieContract.TABLE_NAME + " on " + ExecucaoItemSerieContract.TABLE_NAME + ".id_dia_treino_e = " + TABLE_NAME + "." + COLUNA_CHAVE + " ";  
	}
	public static String innerJoinSincExecucaoItemSerie_FoiRealizado() {
		return " inner join " + ExecucaoItemSerieContract.TABLE_NAME_SINC + " on " + ExecucaoItemSerieContract.TABLE_NAME_SINC + ".id_dia_treino_e = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";  
	}
	public static String outerJoinExecucaoItemSerie_FoiRealizado() {
		return " left outer join " + ExecucaoItemSerieContract.TABLE_NAME + " on " + ExecucaoItemSerieContract.TABLE_NAME + ".id_dia_treino_e = " + TABLE_NAME + "." + COLUNA_CHAVE + " "; 
	}
	public static String outerJoinSincExecucaoItemSerie_FoiRealizado() {
		return " left outer join " + ExecucaoItemSerieContract.TABLE_NAME_SINC + " on " + ExecucaoItemSerieContract.TABLE_NAME_SINC + ".id_dia_treino_e = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";   
	}
	public static MontadorDaoI adicionaMontadorExecucaoItemSerieFoiRealizadoLista (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(ExecucaoItemSerieContract.getMontador(), "ExecucaoItemSerie_FoiRealizado");
		return montador;
	}
 	
	// Com chave
	
	
	public static String innerJoinSerieTreino_SerieDia() {
		return " inner join " + SerieTreinoContract.TABLE_NAME + " on " + SerieTreinoContract.TABLE_NAME + "." + SerieTreinoContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_SERIE_TREINO_SD + " ";  
	}
	public static String innerJoinSincSerieTreino_SerieDia() {
		return " inner join " + SerieTreinoContract.TABLE_NAME_SINC + " on " + SerieTreinoContract.TABLE_NAME_SINC + "." + SerieTreinoContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_SERIE_TREINO_SD + " ";  
	}
	public static String outerJoinSerieTreino_SerieDia() {
		return " left outer join " + SerieTreinoContract.TABLE_NAME + " on " + SerieTreinoContract.TABLE_NAME + "." + SerieTreinoContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_SERIE_TREINO_SD + " "; 
	}
	public static String outerJoinSincSerieTreino_SerieDia() {
		return " left outer join " + SerieTreinoContract.TABLE_NAME_SINC + " on " + SerieTreinoContract.TABLE_NAME_SINC + "." + SerieTreinoContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_SERIE_TREINO_SD + " ";   
	}
	
	
	public static MontadorDaoI adicionaMontadorSerieTreinoSerieDia (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(SerieTreinoContract.getMontador(), "SerieTreino_SerieDia");
		return montador;
	}
	public static Uri adicionaSerieTreinoSerieDia(Uri uri) {
		return uri.buildUpon().appendPath("ComSerieTreinoSerieDia").build();
	}
 	
	
	
	
	
	public static String camposOrdenados() 
	{
		return " " + TABLE_NAME + "." + COLUNA_ID_DIA_TREINO  
		+ " , " + TABLE_NAME + "." + COLUNA_DATA 
		+ " , " + TABLE_NAME + "." + COLUNA_CONCLUIDO 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_USUARIO_S 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_SERIE_TREINO_SD 
		
		
		;
	}
	
	
	
	public static String camposOrdenadosSinc() 
	{
		return " dia_treino_sinc.id_dia_treino " 
		+ " ,dia_treino_sinc.data " 
		+ " ,dia_treino_sinc.concluido " 
		+ " ,dia_treino_sinc.id_usuario_s " 
		+ " ,dia_treino_sinc.id_serie_treino_sd " 
		
		
		+ " ,dia_treino_sinc.operacao_sinc "
		;
	}
	
	
	public static MontadorDaoComposite getMontadorComposto() {
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(getMontador(),null);
		return montador;
	}
	public static MontadorDaoI getMontador() {
		return new DiaTreinoMontador();
	}
	
	
	// ComChaveEstrangeira
  	
	public static Uri buildAllComUsuarioSincroniza() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(DiaTreinoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComUsuarioSincroniza").build();
		return saida;
	}
	//  Recoloquei o metodo abaixo pq existe referencia no Provider
	public static Uri buildAllSemUsuarioSincroniza() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(DiaTreinoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemUsuarioSincroniza").build();
		return saida;
	}
	
	
	public static Uri buildAllComSerieTreinoSerieDia() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(DiaTreinoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComSerieTreinoSerieDia").build();
		return saida;
	}
	//  Recoloquei o metodo abaixo pq existe referencia no Provider
	public static Uri buildAllSemSerieTreinoSerieDia() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(DiaTreinoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemSerieTreinoSerieDia").build();
		return saida;
	}
	
	
	
	// SemChaveEstrangeira
  	
	
	public static Uri buildAllComExecucaoItemSerieFoiRealizado() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(DiaTreinoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComExecucaoItemSerieFoiRealizado").build();
		return saida;
	}	
	public static Uri buildAllSemExecucaoItemSerieFoiRealizado() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(DiaTreinoContract.COM_RETIRADA).build();
		saida = saida.buildUpon().appendPath("SemExecucaoItemSerieFoiRealizado").build();
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
	
	
	public static List<DiaTreino> converteLista(Cursor data) {
		return converteLista(data, getMontador());
	}
	public static List<DiaTreino> converteLista(Cursor data, MontadorDaoI montador) {
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