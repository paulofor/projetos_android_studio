

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
import br.com.lojadigicom.treinoacademia.modelo.ExecucaoItemSerie;
import br.com.lojadigicom.treinoacademia.modelo.montador.ExecucaoItemSerieMontador;


public final class ExecucaoItemSerieContract implements BaseColumns {
	
	
	private static AplicacaoContract aplicacaoContract;
	public static void setAplicacaoContract(AplicacaoContract valor) {
		aplicacaoContract = valor;
	}
	public static String getContentAuthority() {
		return aplicacaoContract.getContentAuthority();
	}
	
	
	public static final String PATH = "execucao_item_serie";
	public static final String COM_COMPLEMENTO = "ComComplemento";
	public static final String COM_RETIRADA = "ComRetirada";

	//public static final Uri CONTENT_URI = aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();

    //public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
    //public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;

    public static final String TABLE_NAME = "execucao_item_serie";
    public static final String TABLE_NAME_SINC = "execucao_item_serie_sinc";

	public static Uri getContentUri() {
		return aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();
	}
	public static String getContentType() {
		return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static String getContentItemType() {
		return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static Uri buildExecucaoItemSerieUri(long id) {
    	return ContentUris.withAppendedId(getContentUri(), id);
    }
    
	public static Uri buildGetPorReferenteAItemSerieUri(long id) {
    	Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(""+id).build();
		saida = saida.buildUpon().appendPath(ItemSerieContract.PATH).build();
    	return saida;
    }
	
	public static Uri buildGetPorEmDiaTreinoUri(long id) {
    	Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(""+id).build();
		saida = saida.buildUpon().appendPath(DiaTreinoContract.PATH).build();
    	return saida;
    }
	
	public static Uri buildGetPorReferenteAExercicioUri(long id) {
    	Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(""+id).build();
		saida = saida.buildUpon().appendPath(ExercicioContract.PATH).build();
    	return saida;
    }
	
	
	
    //public static final int COL_ID = 0;
   
    public static final String COLUNA_ID_EXECUCAO_ITEM_SERIE = "id_execucao_item_serie";
    public static final int COL_ID_EXECUCAO_ITEM_SERIE = 0;
    public static final String COLUNA_DATA_HORA_INICIO = "data_hora_inicio";
    public static final int COL_DATA_HORA_INICIO = 1;
    public static final String COLUNA_CARGA_UTILIZADA = "carga_utilizada";
    public static final int COL_CARGA_UTILIZADA = 2;
    public static final String COLUNA_SUCESSO_REPETICOES = "sucesso_repeticoes";
    public static final int COL_SUCESSO_REPETICOES = 3;
    public static final String COLUNA_NUMERO_SERIE = "numero_serie";
    public static final int COL_NUMERO_SERIE = 4;
    public static final String COLUNA_DATA_HORA_FINALIZACAO = "data_hora_finalizacao";
    public static final int COL_DATA_HORA_FINALIZACAO = 5;
    public static final String COLUNA_QUANTIDADE_REPETICAO = "quantidade_repeticao";
    public static final int COL_QUANTIDADE_REPETICAO = 6;
    public static final String COLUNA_ID_ITEM_SERIE_RA = "id_item_serie_ra";
    public static final int COL_ID_ITEM_SERIE_RA = 7;
	
    public static final String COLUNA_ID_DIA_TREINO_E = "id_dia_treino_e";
    public static final int COL_ID_DIA_TREINO_E = 8;
	
    public static final String COLUNA_ID_EXERCICIO_RA = "id_exercicio_ra";
    public static final int COL_ID_EXERCICIO_RA = 9;
	
    public static final String COLUNA_ID_USUARIO_S = "id_usuario_s";
    public static final int COL_ID_USUARIO_S = 10;
	
	
 	public static final String COLUNA_CHAVE = COLUNA_ID_EXECUCAO_ITEM_SERIE;
 	public static final String COLUNA_OPERACAO_SINC = "operacao_sinc";
 	public static final int COL_OPERACAO_SINC = 11;
	
	public static final String[] COLS = new String[] { 
			TABLE_NAME + "." + COLUNA_CHAVE
        	, TABLE_NAME + "." +COLUNA_DATA_HORA_INICIO
        	, TABLE_NAME + "." +COLUNA_CARGA_UTILIZADA
        	, TABLE_NAME + "." +COLUNA_SUCESSO_REPETICOES
        	, TABLE_NAME + "." +COLUNA_NUMERO_SERIE
        	, TABLE_NAME + "." +COLUNA_DATA_HORA_FINALIZACAO
        	, TABLE_NAME + "." +COLUNA_QUANTIDADE_REPETICAO
			, TABLE_NAME + "." +COLUNA_ID_ITEM_SERIE_RA
	
			, TABLE_NAME + "." +COLUNA_ID_DIA_TREINO_E
	
			, TABLE_NAME + "." +COLUNA_ID_EXERCICIO_RA
	
			, TABLE_NAME + "." +COLUNA_ID_USUARIO_S
	
    };
    
    public static final String[] COLS_SINC = new String[] { 
			TABLE_NAME_SINC + "." + COLUNA_CHAVE
        	, TABLE_NAME_SINC + "." +COLUNA_DATA_HORA_INICIO
        	, TABLE_NAME_SINC + "." +COLUNA_CARGA_UTILIZADA
        	, TABLE_NAME_SINC + "." +COLUNA_SUCESSO_REPETICOES
        	, TABLE_NAME_SINC + "." +COLUNA_NUMERO_SERIE
        	, TABLE_NAME_SINC + "." +COLUNA_DATA_HORA_FINALIZACAO
        	, TABLE_NAME_SINC + "." +COLUNA_QUANTIDADE_REPETICAO
			, TABLE_NAME_SINC + "." +COLUNA_ID_ITEM_SERIE_RA
	
			, TABLE_NAME_SINC + "." +COLUNA_ID_DIA_TREINO_E
	
			, TABLE_NAME_SINC + "." +COLUNA_ID_EXERCICIO_RA
	
			, TABLE_NAME_SINC + "." +COLUNA_ID_USUARIO_S
	
			, TABLE_NAME_SINC + "." + COLUNA_OPERACAO_SINC
    };
    
    private static ExecucaoItemSerieOperacao operacao = new ExecucaoItemSerieOperacao();
 	public static Uri buildObtemPorDiaItemSerie() {
		return operacao.buildObtemPorDiaItemSerie(getFiltro());
    }
    private static MontadorDaoI _montadorObtemPorDiaItemSerie = null;
    public static MontadorDaoI getMontadorObtemPorDiaItemSerie() {
    	return _montadorObtemPorDiaItemSerie;
    }
    public static void setMontadorObtemPorDiaItemSerie(MontadorDaoI montador) {
    	_montadorObtemPorDiaItemSerie = montador;
    }
 	public static Uri buildUltimasExecucoesUsuarioExercicio() {
		return operacao.buildUltimasExecucoesUsuarioExercicio(getFiltro());
    }
    private static MontadorDaoI _montadorUltimasExecucoesUsuarioExercicio = null;
    public static MontadorDaoI getMontadorUltimasExecucoesUsuarioExercicio() {
    	return _montadorUltimasExecucoesUsuarioExercicio;
    }
    public static void setMontadorUltimasExecucoesUsuarioExercicio(MontadorDaoI montador) {
    	_montadorUltimasExecucoesUsuarioExercicio = montador;
    }
 	public static Uri buildUltimasExecucoesItemSerie() {
		return operacao.buildUltimasExecucoesItemSerie(getFiltro());
    }
    private static MontadorDaoI _montadorUltimasExecucoesItemSerie = null;
    public static MontadorDaoI getMontadorUltimasExecucoesItemSerie() {
    	return _montadorUltimasExecucoesItemSerie;
    }
    public static void setMontadorUltimasExecucoesItemSerie(MontadorDaoI montador) {
    	_montadorUltimasExecucoesItemSerie = montador;
    }
 	public static Uri buildCarregaCompletoPorDia() {
		return operacao.buildCarregaCompletoPorDia(getFiltro());
    }
    private static MontadorDaoI _montadorCarregaCompletoPorDia = null;
    public static MontadorDaoI getMontadorCarregaCompletoPorDia() {
    	return _montadorCarregaCompletoPorDia;
    }
    public static void setMontadorCarregaCompletoPorDia(MontadorDaoI montador) {
    	_montadorCarregaCompletoPorDia = montador;
    }
 	public static Uri buildPrimeiraPorDia() {
		return operacao.buildPrimeiraPorDia(getFiltro());
    }
    private static MontadorDaoI _montadorPrimeiraPorDia = null;
    public static MontadorDaoI getMontadorPrimeiraPorDia() {
    	return _montadorPrimeiraPorDia;
    }
    public static void setMontadorPrimeiraPorDia(MontadorDaoI montador) {
    	_montadorPrimeiraPorDia = montador;
    }
 	public static Uri buildUltimaPorDia() {
		return operacao.buildUltimaPorDia(getFiltro());
    }
    private static MontadorDaoI _montadorUltimaPorDia = null;
    public static MontadorDaoI getMontadorUltimaPorDia() {
    	return _montadorUltimaPorDia;
    }
    public static void setMontadorUltimaPorDia(MontadorDaoI montador) {
    	_montadorUltimaPorDia = montador;
    }
 	public static Uri buildPrimeiraPorSerie() {
		return operacao.buildPrimeiraPorSerie(getFiltro());
    }
    private static MontadorDaoI _montadorPrimeiraPorSerie = null;
    public static MontadorDaoI getMontadorPrimeiraPorSerie() {
    	return _montadorPrimeiraPorSerie;
    }
    public static void setMontadorPrimeiraPorSerie(MontadorDaoI montador) {
    	_montadorPrimeiraPorSerie = montador;
    }
    
	private static ExecucaoItemSerieFiltro filtro = null;
	public static ExecucaoItemSerieFiltro getFiltro() {
		if (filtro==null) {
			filtro = new ExecucaoItemSerieFiltro();
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
 	
	
	public static String innerJoinDiaTreino_Em() {
		return " inner join " + DiaTreinoContract.TABLE_NAME + " on " + DiaTreinoContract.TABLE_NAME + "." + DiaTreinoContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_DIA_TREINO_E + " ";  
	}
	public static String innerJoinSincDiaTreino_Em() {
		return " inner join " + DiaTreinoContract.TABLE_NAME_SINC + " on " + DiaTreinoContract.TABLE_NAME_SINC + "." + DiaTreinoContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_DIA_TREINO_E + " ";  
	}
	public static String outerJoinDiaTreino_Em() {
		return " left outer join " + DiaTreinoContract.TABLE_NAME + " on " + DiaTreinoContract.TABLE_NAME + "." + DiaTreinoContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_DIA_TREINO_E + " "; 
	}
	public static String outerJoinSincDiaTreino_Em() {
		return " left outer join " + DiaTreinoContract.TABLE_NAME_SINC + " on " + DiaTreinoContract.TABLE_NAME_SINC + "." + DiaTreinoContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_DIA_TREINO_E + " ";   
	}
	
	
	public static MontadorDaoI adicionaMontadorDiaTreinoEm (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(DiaTreinoContract.getMontador(), "DiaTreino_Em");
		return montador;
	}
	public static Uri adicionaDiaTreinoEm(Uri uri) {
		return uri.buildUpon().appendPath("ComDiaTreinoEm").build();
	}
 	
	
	public static String innerJoinExercicio_ReferenteA() {
		return " inner join " + ExercicioContract.TABLE_NAME + " on " + ExercicioContract.TABLE_NAME + "." + ExercicioContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_EXERCICIO_RA + " ";  
	}
	public static String innerJoinSincExercicio_ReferenteA() {
		return " inner join " + ExercicioContract.TABLE_NAME_SINC + " on " + ExercicioContract.TABLE_NAME_SINC + "." + ExercicioContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_EXERCICIO_RA + " ";  
	}
	public static String outerJoinExercicio_ReferenteA() {
		return " left outer join " + ExercicioContract.TABLE_NAME + " on " + ExercicioContract.TABLE_NAME + "." + ExercicioContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_EXERCICIO_RA + " "; 
	}
	public static String outerJoinSincExercicio_ReferenteA() {
		return " left outer join " + ExercicioContract.TABLE_NAME_SINC + " on " + ExercicioContract.TABLE_NAME_SINC + "." + ExercicioContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_EXERCICIO_RA + " ";   
	}
	
	
	public static MontadorDaoI adicionaMontadorExercicioReferenteA (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(ExercicioContract.getMontador(), "Exercicio_ReferenteA");
		return montador;
	}
	public static Uri adicionaExercicioReferenteA(Uri uri) {
		return uri.buildUpon().appendPath("ComExercicioReferenteA").build();
	}
 	
	
	
	
	
	public static String camposOrdenados() 
	{
		return " " + TABLE_NAME + "." + COLUNA_ID_EXECUCAO_ITEM_SERIE  
		+ " , " + TABLE_NAME + "." + COLUNA_DATA_HORA_INICIO 
		+ " , " + TABLE_NAME + "." + COLUNA_CARGA_UTILIZADA 
		+ " , " + TABLE_NAME + "." + COLUNA_SUCESSO_REPETICOES 
		+ " , " + TABLE_NAME + "." + COLUNA_NUMERO_SERIE 
		+ " , " + TABLE_NAME + "." + COLUNA_DATA_HORA_FINALIZACAO 
		+ " , " + TABLE_NAME + "." + COLUNA_QUANTIDADE_REPETICAO 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_ITEM_SERIE_RA 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_DIA_TREINO_E 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_EXERCICIO_RA 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_USUARIO_S 
		
		
		;
	}
	
	
	
	public static String camposOrdenadosSinc() 
	{
		return " execucao_item_serie_sinc.id_execucao_item_serie " 
		+ " ,execucao_item_serie_sinc.data_hora_inicio " 
		+ " ,execucao_item_serie_sinc.carga_utilizada " 
		+ " ,execucao_item_serie_sinc.sucesso_repeticoes " 
		+ " ,execucao_item_serie_sinc.numero_serie " 
		+ " ,execucao_item_serie_sinc.data_hora_finalizacao " 
		+ " ,execucao_item_serie_sinc.quantidade_repeticao " 
		+ " ,execucao_item_serie_sinc.id_item_serie_ra " 
		+ " ,execucao_item_serie_sinc.id_dia_treino_e " 
		+ " ,execucao_item_serie_sinc.id_exercicio_ra " 
		+ " ,execucao_item_serie_sinc.id_usuario_s " 
		
		
		+ " ,execucao_item_serie_sinc.operacao_sinc "
		;
	}
	
	
	public static MontadorDaoComposite getMontadorComposto() {
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(getMontador(),null);
		return montador;
	}
	public static MontadorDaoI getMontador() {
		return new ExecucaoItemSerieMontador();
	}
	
	
	// ComChaveEstrangeira
  	
	public static Uri buildAllComItemSerieReferenteA() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ExecucaoItemSerieContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComItemSerieReferenteA").build();
		return saida;
	}
	//  Recoloquei o metodo abaixo pq existe referencia no Provider
	public static Uri buildAllSemItemSerieReferenteA() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ExecucaoItemSerieContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemItemSerieReferenteA").build();
		return saida;
	}
	
	
	public static Uri buildAllComDiaTreinoEm() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ExecucaoItemSerieContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComDiaTreinoEm").build();
		return saida;
	}
	//  Recoloquei o metodo abaixo pq existe referencia no Provider
	public static Uri buildAllSemDiaTreinoEm() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ExecucaoItemSerieContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemDiaTreinoEm").build();
		return saida;
	}
	
	
	public static Uri buildAllComExercicioReferenteA() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ExecucaoItemSerieContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComExercicioReferenteA").build();
		return saida;
	}
	//  Recoloquei o metodo abaixo pq existe referencia no Provider
	public static Uri buildAllSemExercicioReferenteA() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ExecucaoItemSerieContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemExercicioReferenteA").build();
		return saida;
	}
	
	
	public static Uri buildAllComUsuarioSincroniza() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ExecucaoItemSerieContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComUsuarioSincroniza").build();
		return saida;
	}
	//  Recoloquei o metodo abaixo pq existe referencia no Provider
	public static Uri buildAllSemUsuarioSincroniza() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ExecucaoItemSerieContract.COM_COMPLEMENTO).build();
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
	
	
	public static List<ExecucaoItemSerie> converteLista(Cursor data) {
		return converteLista(data, getMontador());
	}
	public static List<ExecucaoItemSerie> converteLista(Cursor data, MontadorDaoI montador) {
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