

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
import br.com.lojadigicom.treinoacademia.modelo.ItemSerie;
import br.com.lojadigicom.treinoacademia.modelo.montador.ItemSerieMontador;


public final class ItemSerieContract implements BaseColumns {
	
	
	private static AplicacaoContract aplicacaoContract;
	public static void setAplicacaoContract(AplicacaoContract valor) {
		aplicacaoContract = valor;
	}
	public static String getContentAuthority() {
		return aplicacaoContract.getContentAuthority();
	}
	
	
	public static final String PATH = "item_serie";
	public static final String COM_COMPLEMENTO = "ComComplemento";
	public static final String COM_RETIRADA = "ComRetirada";

	//public static final Uri CONTENT_URI = aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();

    //public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
    //public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;

    public static final String TABLE_NAME = "item_serie";
    public static final String TABLE_NAME_SINC = "item_serie_sinc";

	public static Uri getContentUri() {
		return aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();
	}
	public static String getContentType() {
		return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static String getContentItemType() {
		return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static Uri buildItemSerieUri(long id) {
    	return ContentUris.withAppendedId(getContentUri(), id);
    }
    
	public static Uri buildGetPorExecucaoDeExercicioUri(long id) {
    	Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(""+id).build();
		saida = saida.buildUpon().appendPath(ExercicioContract.PATH).build();
    	return saida;
    }
	
	public static Uri buildGetPorPertencenteASerieTreinoUri(long id) {
    	Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(""+id).build();
		saida = saida.buildUpon().appendPath(SerieTreinoContract.PATH).build();
    	return saida;
    }
	
	
	
    //public static final int COL_ID = 0;
   
    public static final String COLUNA_ID_ITEM_SERIE = "id_item_serie";
    public static final int COL_ID_ITEM_SERIE = 0;
    public static final String COLUNA_REPETICOES = "repeticoes";
    public static final int COL_REPETICOES = 1;
    public static final String COLUNA_SERIE = "serie";
    public static final int COL_SERIE = 2;
    public static final String COLUNA_PARAMETROS = "parametros";
    public static final int COL_PARAMETROS = 3;
    public static final String COLUNA_ORDEM_EXECUCAO = "ordem_execucao";
    public static final int COL_ORDEM_EXECUCAO = 4;
    public static final String COLUNA_ID_EXERCICIO_ED = "id_exercicio_ed";
    public static final int COL_ID_EXERCICIO_ED = 5;
	
    public static final String COLUNA_ID_SERIE_TREINO_PA = "id_serie_treino_pa";
    public static final int COL_ID_SERIE_TREINO_PA = 6;
	
    public static final String COLUNA_ID_USUARIO_S = "id_usuario_s";
    public static final int COL_ID_USUARIO_S = 7;
	
	
 	public static final String COLUNA_CHAVE = COLUNA_ID_ITEM_SERIE;
 	public static final String COLUNA_OPERACAO_SINC = "operacao_sinc";
 	public static final int COL_OPERACAO_SINC = 8;
	
	public static final String[] COLS = new String[] { 
			TABLE_NAME + "." + COLUNA_CHAVE
        	, TABLE_NAME + "." +COLUNA_REPETICOES
        	, TABLE_NAME + "." +COLUNA_SERIE
        	, TABLE_NAME + "." +COLUNA_PARAMETROS
        	, TABLE_NAME + "." +COLUNA_ORDEM_EXECUCAO
			, TABLE_NAME + "." +COLUNA_ID_EXERCICIO_ED
	
			, TABLE_NAME + "." +COLUNA_ID_SERIE_TREINO_PA
	
			, TABLE_NAME + "." +COLUNA_ID_USUARIO_S
	
    };
    
    public static final String[] COLS_SINC = new String[] { 
			TABLE_NAME_SINC + "." + COLUNA_CHAVE
        	, TABLE_NAME_SINC + "." +COLUNA_REPETICOES
        	, TABLE_NAME_SINC + "." +COLUNA_SERIE
        	, TABLE_NAME_SINC + "." +COLUNA_PARAMETROS
        	, TABLE_NAME_SINC + "." +COLUNA_ORDEM_EXECUCAO
			, TABLE_NAME_SINC + "." +COLUNA_ID_EXERCICIO_ED
	
			, TABLE_NAME_SINC + "." +COLUNA_ID_SERIE_TREINO_PA
	
			, TABLE_NAME_SINC + "." +COLUNA_ID_USUARIO_S
	
			, TABLE_NAME_SINC + "." + COLUNA_OPERACAO_SINC
    };
    
    private static ItemSerieOperacao operacao = new ItemSerieOperacao();
 	public static Uri buildListaPorDiaComExecucao() {
		return operacao.buildListaPorDiaComExecucao(getFiltro());
    }
    private static MontadorDaoI _montadorListaPorDiaComExecucao = null;
    public static MontadorDaoI getMontadorListaPorDiaComExecucao() {
    	return _montadorListaPorDiaComExecucao;
    }
    public static void setMontadorListaPorDiaComExecucao(MontadorDaoI montador) {
    	_montadorListaPorDiaComExecucao = montador;
    }
 	public static Uri buildFinalizaItemSerie() {
		return operacao.buildFinalizaItemSerie(getFiltro());
    }
    private static MontadorDaoI _montadorFinalizaItemSerie = null;
    public static MontadorDaoI getMontadorFinalizaItemSerie() {
    	return _montadorFinalizaItemSerie;
    }
    public static void setMontadorFinalizaItemSerie(MontadorDaoI montador) {
    	_montadorFinalizaItemSerie = montador;
    }
 	public static Uri buildCarregaCompleto() {
		return operacao.buildCarregaCompleto(getFiltro());
    }
    private static MontadorDaoI _montadorCarregaCompleto = null;
    public static MontadorDaoI getMontadorCarregaCompleto() {
    	return _montadorCarregaCompleto;
    }
    public static void setMontadorCarregaCompleto(MontadorDaoI montador) {
    	_montadorCarregaCompleto = montador;
    }
 	public static Uri buildCarregaUltimasExecucoes() {
		return operacao.buildCarregaUltimasExecucoes(getFiltro());
    }
    private static MontadorDaoI _montadorCarregaUltimasExecucoes = null;
    public static MontadorDaoI getMontadorCarregaUltimasExecucoes() {
    	return _montadorCarregaUltimasExecucoes;
    }
    public static void setMontadorCarregaUltimasExecucoes(MontadorDaoI montador) {
    	_montadorCarregaUltimasExecucoes = montador;
    }
 	public static Uri buildAtualizaCarga() {
		return operacao.buildAtualizaCarga(getFiltro());
    }
    private static MontadorDaoI _montadorAtualizaCarga = null;
    public static MontadorDaoI getMontadorAtualizaCarga() {
    	return _montadorAtualizaCarga;
    }
    public static void setMontadorAtualizaCarga(MontadorDaoI montador) {
    	_montadorAtualizaCarga = montador;
    }
    
	private static ItemSerieFiltro filtro = null;
	public static ItemSerieFiltro getFiltro() {
		if (filtro==null) {
			filtro = new ItemSerieFiltro();
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
	
	public static String innerJoinCargaPlanejada_Possui() {
		return " inner join " + CargaPlanejadaContract.TABLE_NAME + " on " + CargaPlanejadaContract.TABLE_NAME + ".id_item_serie_ra = " + TABLE_NAME + "." + COLUNA_CHAVE + " ";  
	}
	public static String innerJoinSincCargaPlanejada_Possui() {
		return " inner join " + CargaPlanejadaContract.TABLE_NAME_SINC + " on " + CargaPlanejadaContract.TABLE_NAME_SINC + ".id_item_serie_ra = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";  
	}
	public static String outerJoinCargaPlanejada_Possui() {
		return " left outer join " + CargaPlanejadaContract.TABLE_NAME + " on " + CargaPlanejadaContract.TABLE_NAME + ".id_item_serie_ra = " + TABLE_NAME + "." + COLUNA_CHAVE + " "; 
	}
	public static String outerJoinSincCargaPlanejada_Possui() {
		return " left outer join " + CargaPlanejadaContract.TABLE_NAME_SINC + " on " + CargaPlanejadaContract.TABLE_NAME_SINC + ".id_item_serie_ra = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";   
	}
	public static MontadorDaoI adicionaMontadorCargaPlanejadaPossuiLista (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(CargaPlanejadaContract.getMontador(), "CargaPlanejada_Possui");
		return montador;
	}
 	
	public static String innerJoinExecucaoItemSerie_Gera() {
		return " inner join " + ExecucaoItemSerieContract.TABLE_NAME + " on " + ExecucaoItemSerieContract.TABLE_NAME + ".id_item_serie_ra = " + TABLE_NAME + "." + COLUNA_CHAVE + " ";  
	}
	public static String innerJoinSincExecucaoItemSerie_Gera() {
		return " inner join " + ExecucaoItemSerieContract.TABLE_NAME_SINC + " on " + ExecucaoItemSerieContract.TABLE_NAME_SINC + ".id_item_serie_ra = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";  
	}
	public static String outerJoinExecucaoItemSerie_Gera() {
		return " left outer join " + ExecucaoItemSerieContract.TABLE_NAME + " on " + ExecucaoItemSerieContract.TABLE_NAME + ".id_item_serie_ra = " + TABLE_NAME + "." + COLUNA_CHAVE + " "; 
	}
	public static String outerJoinSincExecucaoItemSerie_Gera() {
		return " left outer join " + ExecucaoItemSerieContract.TABLE_NAME_SINC + " on " + ExecucaoItemSerieContract.TABLE_NAME_SINC + ".id_item_serie_ra = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";   
	}
	public static MontadorDaoI adicionaMontadorExecucaoItemSerieGeraLista (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(ExecucaoItemSerieContract.getMontador(), "ExecucaoItemSerie_Gera");
		return montador;
	}
 	
	// Com chave
	
	
	public static String innerJoinExercicio_ExecucaoDe() {
		return " inner join " + ExercicioContract.TABLE_NAME + " on " + ExercicioContract.TABLE_NAME + "." + ExercicioContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_EXERCICIO_ED + " ";  
	}
	public static String innerJoinSincExercicio_ExecucaoDe() {
		return " inner join " + ExercicioContract.TABLE_NAME_SINC + " on " + ExercicioContract.TABLE_NAME_SINC + "." + ExercicioContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_EXERCICIO_ED + " ";  
	}
	public static String outerJoinExercicio_ExecucaoDe() {
		return " left outer join " + ExercicioContract.TABLE_NAME + " on " + ExercicioContract.TABLE_NAME + "." + ExercicioContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_EXERCICIO_ED + " "; 
	}
	public static String outerJoinSincExercicio_ExecucaoDe() {
		return " left outer join " + ExercicioContract.TABLE_NAME_SINC + " on " + ExercicioContract.TABLE_NAME_SINC + "." + ExercicioContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_EXERCICIO_ED + " ";   
	}
	
	
	public static MontadorDaoI adicionaMontadorExercicioExecucaoDe (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(ExercicioContract.getMontador(), "Exercicio_ExecucaoDe");
		return montador;
	}
	public static Uri adicionaExercicioExecucaoDe(Uri uri) {
		return uri.buildUpon().appendPath("ComExercicioExecucaoDe").build();
	}
 	
	
	public static String innerJoinSerieTreino_PertencenteA() {
		return " inner join " + SerieTreinoContract.TABLE_NAME + " on " + SerieTreinoContract.TABLE_NAME + "." + SerieTreinoContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_SERIE_TREINO_PA + " ";  
	}
	public static String innerJoinSincSerieTreino_PertencenteA() {
		return " inner join " + SerieTreinoContract.TABLE_NAME_SINC + " on " + SerieTreinoContract.TABLE_NAME_SINC + "." + SerieTreinoContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_SERIE_TREINO_PA + " ";  
	}
	public static String outerJoinSerieTreino_PertencenteA() {
		return " left outer join " + SerieTreinoContract.TABLE_NAME + " on " + SerieTreinoContract.TABLE_NAME + "." + SerieTreinoContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_SERIE_TREINO_PA + " "; 
	}
	public static String outerJoinSincSerieTreino_PertencenteA() {
		return " left outer join " + SerieTreinoContract.TABLE_NAME_SINC + " on " + SerieTreinoContract.TABLE_NAME_SINC + "." + SerieTreinoContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_SERIE_TREINO_PA + " ";   
	}
	
	
	public static MontadorDaoI adicionaMontadorSerieTreinoPertencenteA (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(SerieTreinoContract.getMontador(), "SerieTreino_PertencenteA");
		return montador;
	}
	public static Uri adicionaSerieTreinoPertencenteA(Uri uri) {
		return uri.buildUpon().appendPath("ComSerieTreinoPertencenteA").build();
	}
 	
	
	
	
	
	public static String camposOrdenados() 
	{
		return " " + TABLE_NAME + "." + COLUNA_ID_ITEM_SERIE  
		+ " , " + TABLE_NAME + "." + COLUNA_REPETICOES 
		+ " , " + TABLE_NAME + "." + COLUNA_SERIE 
		+ " , " + TABLE_NAME + "." + COLUNA_PARAMETROS 
		+ " , " + TABLE_NAME + "." + COLUNA_ORDEM_EXECUCAO 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_EXERCICIO_ED 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_SERIE_TREINO_PA 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_USUARIO_S 
		
		
		;
	}
	
	
	
	public static String camposOrdenadosSinc() 
	{
		return " item_serie_sinc.id_item_serie " 
		+ " ,item_serie_sinc.repeticoes " 
		+ " ,item_serie_sinc.serie " 
		+ " ,item_serie_sinc.parametros " 
		+ " ,item_serie_sinc.ordem_execucao " 
		+ " ,item_serie_sinc.id_exercicio_ed " 
		+ " ,item_serie_sinc.id_serie_treino_pa " 
		+ " ,item_serie_sinc.id_usuario_s " 
		
		
		+ " ,item_serie_sinc.operacao_sinc "
		;
	}
	
	
	public static MontadorDaoComposite getMontadorComposto() {
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(getMontador(),null);
		return montador;
	}
	public static MontadorDaoI getMontador() {
		return new ItemSerieMontador();
	}
	
	
	// ComChaveEstrangeira
  	
	public static Uri buildAllComExercicioExecucaoDe() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ItemSerieContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComExercicioExecucaoDe").build();
		return saida;
	}
	//  Recoloquei o metodo abaixo pq existe referencia no Provider
	public static Uri buildAllSemExercicioExecucaoDe() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ItemSerieContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemExercicioExecucaoDe").build();
		return saida;
	}
	
	
	public static Uri buildAllComSerieTreinoPertencenteA() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ItemSerieContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComSerieTreinoPertencenteA").build();
		return saida;
	}
	//  Recoloquei o metodo abaixo pq existe referencia no Provider
	public static Uri buildAllSemSerieTreinoPertencenteA() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ItemSerieContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemSerieTreinoPertencenteA").build();
		return saida;
	}
	
	
	public static Uri buildAllComUsuarioSincroniza() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ItemSerieContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComUsuarioSincroniza").build();
		return saida;
	}
	//  Recoloquei o metodo abaixo pq existe referencia no Provider
	public static Uri buildAllSemUsuarioSincroniza() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ItemSerieContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemUsuarioSincroniza").build();
		return saida;
	}
	
	
	
	// SemChaveEstrangeira
  	
	
	public static Uri buildAllComCargaPlanejadaPossui() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ItemSerieContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComCargaPlanejadaPossui").build();
		return saida;
	}	
	public static Uri buildAllSemCargaPlanejadaPossui() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ItemSerieContract.COM_RETIRADA).build();
		saida = saida.buildUpon().appendPath("SemCargaPlanejadaPossui").build();
		return saida;
	}	
	
	
	
	public static Uri buildAllComExecucaoItemSerieGera() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ItemSerieContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComExecucaoItemSerieGera").build();
		return saida;
	}	
	public static Uri buildAllSemExecucaoItemSerieGera() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ItemSerieContract.COM_RETIRADA).build();
		saida = saida.buildUpon().appendPath("SemExecucaoItemSerieGera").build();
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
	
	
	public static List<ItemSerie> converteLista(Cursor data) {
		return converteLista(data, getMontador());
	}
	public static List<ItemSerie> converteLista(Cursor data, MontadorDaoI montador) {
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