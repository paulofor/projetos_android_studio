

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
import br.com.lojadigicom.treinoacademia.modelo.Exercicio;
import br.com.lojadigicom.treinoacademia.modelo.montador.ExercicioMontador;


public final class ExercicioContract implements BaseColumns {
	
	
	private static AplicacaoContract aplicacaoContract;
	public static void setAplicacaoContract(AplicacaoContract valor) {
		aplicacaoContract = valor;
	}
	public static String getContentAuthority() {
		return aplicacaoContract.getContentAuthority();
	}
	
	
	public static final String PATH = "exercicio";
	public static final String COM_COMPLEMENTO = "ComComplemento";
	public static final String COM_RETIRADA = "ComRetirada";

	//public static final Uri CONTENT_URI = aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();

    //public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
    //public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;

    public static final String TABLE_NAME = "exercicio";
    public static final String TABLE_NAME_SINC = "exercicio_sinc";

	public static Uri getContentUri() {
		return aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();
	}
	public static String getContentType() {
		return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static String getContentItemType() {
		return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static Uri buildExercicioUri(long id) {
    	return ContentUris.withAppendedId(getContentUri(), id);
    }
    
	public static Uri buildGetPorParaGrupoMuscularUri(long id) {
    	Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(""+id).build();
		saida = saida.buildUpon().appendPath(GrupoMuscularContract.PATH).build();
    	return saida;
    }
	
	
	
    //public static final int COL_ID = 0;
   
    public static final String COLUNA_ID_EXERCICIO = "id_exercicio";
    public static final int COL_ID_EXERCICIO = 0;
    public static final String COLUNA_DESCRICAO_EXERCICIO = "descricao_exercicio";
    public static final int COL_DESCRICAO_EXERCICIO = 1;
    public static final String COLUNA_IMAGEM = "imagem";
    public static final int COL_IMAGEM = 2;
    public static final String COLUNA_TITULO = "titulo";
    public static final int COL_TITULO = 3;
    public static final String COLUNA_SUBTITULO = "subtitulo";
    public static final int COL_SUBTITULO = 4;
    public static final String COLUNA_ID_GRUPO_MUSCULAR_P = "id_grupo_muscular_p";
    public static final int COL_ID_GRUPO_MUSCULAR_P = 5;
	
    public static final String COLUNA_ID_USUARIO_S = "id_usuario_s";
    public static final int COL_ID_USUARIO_S = 6;
	
	
 	public static final String COLUNA_CHAVE = COLUNA_ID_EXERCICIO;
 	public static final String COLUNA_OPERACAO_SINC = "operacao_sinc";
 	public static final int COL_OPERACAO_SINC = 7;
	
	public static final String[] COLS = new String[] { 
			TABLE_NAME + "." + COLUNA_CHAVE
        	, TABLE_NAME + "." +COLUNA_DESCRICAO_EXERCICIO
        	, TABLE_NAME + "." +COLUNA_IMAGEM
        	, TABLE_NAME + "." +COLUNA_TITULO
        	, TABLE_NAME + "." +COLUNA_SUBTITULO
			, TABLE_NAME + "." +COLUNA_ID_GRUPO_MUSCULAR_P
	
			, TABLE_NAME + "." +COLUNA_ID_USUARIO_S
	
    };
    
    public static final String[] COLS_SINC = new String[] { 
			TABLE_NAME_SINC + "." + COLUNA_CHAVE
        	, TABLE_NAME_SINC + "." +COLUNA_DESCRICAO_EXERCICIO
        	, TABLE_NAME_SINC + "." +COLUNA_IMAGEM
        	, TABLE_NAME_SINC + "." +COLUNA_TITULO
        	, TABLE_NAME_SINC + "." +COLUNA_SUBTITULO
			, TABLE_NAME_SINC + "." +COLUNA_ID_GRUPO_MUSCULAR_P
	
			, TABLE_NAME_SINC + "." +COLUNA_ID_USUARIO_S
	
			, TABLE_NAME_SINC + "." + COLUNA_OPERACAO_SINC
    };
    
    private static ExercicioOperacao operacao = new ExercicioOperacao();
 	public static Uri buildListaAtivosNoMomento() {
		return operacao.buildListaAtivosNoMomento(getFiltro());
    }
    private static MontadorDaoI _montadorListaAtivosNoMomento = null;
    public static MontadorDaoI getMontadorListaAtivosNoMomento() {
    	return _montadorListaAtivosNoMomento;
    }
    public static void setMontadorListaAtivosNoMomento(MontadorDaoI montador) {
    	_montadorListaAtivosNoMomento = montador;
    }
    
	private static ExercicioFiltro filtro = null;
	public static ExercicioFiltro getFiltro() {
		if (filtro==null) {
			filtro = new ExercicioFiltro();
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
	
	public static String innerJoinItemSerie_Gera() {
		return " inner join " + ItemSerieContract.TABLE_NAME + " on " + ItemSerieContract.TABLE_NAME + ".id_exercicio_ed = " + TABLE_NAME + "." + COLUNA_CHAVE + " ";  
	}
	public static String innerJoinSincItemSerie_Gera() {
		return " inner join " + ItemSerieContract.TABLE_NAME_SINC + " on " + ItemSerieContract.TABLE_NAME_SINC + ".id_exercicio_ed = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";  
	}
	public static String outerJoinItemSerie_Gera() {
		return " left outer join " + ItemSerieContract.TABLE_NAME + " on " + ItemSerieContract.TABLE_NAME + ".id_exercicio_ed = " + TABLE_NAME + "." + COLUNA_CHAVE + " "; 
	}
	public static String outerJoinSincItemSerie_Gera() {
		return " left outer join " + ItemSerieContract.TABLE_NAME_SINC + " on " + ItemSerieContract.TABLE_NAME_SINC + ".id_exercicio_ed = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";   
	}
	public static MontadorDaoI adicionaMontadorItemSerieGeraLista (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(ItemSerieContract.getMontador(), "ItemSerie_Gera");
		return montador;
	}
 	
	public static String innerJoinExecucaoItemSerie_Executado() {
		return " inner join " + ExecucaoItemSerieContract.TABLE_NAME + " on " + ExecucaoItemSerieContract.TABLE_NAME + ".id_exercicio_ra = " + TABLE_NAME + "." + COLUNA_CHAVE + " ";  
	}
	public static String innerJoinSincExecucaoItemSerie_Executado() {
		return " inner join " + ExecucaoItemSerieContract.TABLE_NAME_SINC + " on " + ExecucaoItemSerieContract.TABLE_NAME_SINC + ".id_exercicio_ra = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";  
	}
	public static String outerJoinExecucaoItemSerie_Executado() {
		return " left outer join " + ExecucaoItemSerieContract.TABLE_NAME + " on " + ExecucaoItemSerieContract.TABLE_NAME + ".id_exercicio_ra = " + TABLE_NAME + "." + COLUNA_CHAVE + " "; 
	}
	public static String outerJoinSincExecucaoItemSerie_Executado() {
		return " left outer join " + ExecucaoItemSerieContract.TABLE_NAME_SINC + " on " + ExecucaoItemSerieContract.TABLE_NAME_SINC + ".id_exercicio_ra = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";   
	}
	public static MontadorDaoI adicionaMontadorExecucaoItemSerieExecutadoLista (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(ExecucaoItemSerieContract.getMontador(), "ExecucaoItemSerie_Executado");
		return montador;
	}
 	
	// Com chave
	
	
	public static String innerJoinGrupoMuscular_Para() {
		return " inner join " + GrupoMuscularContract.TABLE_NAME + " on " + GrupoMuscularContract.TABLE_NAME + "." + GrupoMuscularContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_GRUPO_MUSCULAR_P + " ";  
	}
	public static String innerJoinSincGrupoMuscular_Para() {
		return " inner join " + GrupoMuscularContract.TABLE_NAME_SINC + " on " + GrupoMuscularContract.TABLE_NAME_SINC + "." + GrupoMuscularContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_GRUPO_MUSCULAR_P + " ";  
	}
	public static String outerJoinGrupoMuscular_Para() {
		return " left outer join " + GrupoMuscularContract.TABLE_NAME + " on " + GrupoMuscularContract.TABLE_NAME + "." + GrupoMuscularContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_GRUPO_MUSCULAR_P + " "; 
	}
	public static String outerJoinSincGrupoMuscular_Para() {
		return " left outer join " + GrupoMuscularContract.TABLE_NAME_SINC + " on " + GrupoMuscularContract.TABLE_NAME_SINC + "." + GrupoMuscularContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_GRUPO_MUSCULAR_P + " ";   
	}
	
	
	public static MontadorDaoI adicionaMontadorGrupoMuscularPara (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(GrupoMuscularContract.getMontador(), "GrupoMuscular_Para");
		return montador;
	}
	public static Uri adicionaGrupoMuscularPara(Uri uri) {
		return uri.buildUpon().appendPath("ComGrupoMuscularPara").build();
	}
 	
	
	
	
	
	public static String camposOrdenados() 
	{
		return " " + TABLE_NAME + "." + COLUNA_ID_EXERCICIO  
		+ " , " + TABLE_NAME + "." + COLUNA_DESCRICAO_EXERCICIO 
		+ " , " + TABLE_NAME + "." + COLUNA_IMAGEM 
		+ " , " + TABLE_NAME + "." + COLUNA_TITULO 
		+ " , " + TABLE_NAME + "." + COLUNA_SUBTITULO 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_GRUPO_MUSCULAR_P 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_USUARIO_S 
		
		
		;
	}
	
	
	
	public static String camposOrdenadosSinc() 
	{
		return " exercicio_sinc.id_exercicio " 
		+ " ,exercicio_sinc.descricao_exercicio " 
		+ " ,exercicio_sinc.imagem " 
		+ " ,exercicio_sinc.titulo " 
		+ " ,exercicio_sinc.subtitulo " 
		+ " ,exercicio_sinc.id_grupo_muscular_p " 
		+ " ,exercicio_sinc.id_usuario_s " 
		
		
		+ " ,exercicio_sinc.operacao_sinc "
		;
	}
	
	
	public static MontadorDaoComposite getMontadorComposto() {
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(getMontador(),null);
		return montador;
	}
	public static MontadorDaoI getMontador() {
		return new ExercicioMontador();
	}
	
	
	// ComChaveEstrangeira
  	
	public static Uri buildAllComGrupoMuscularPara() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ExercicioContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComGrupoMuscularPara").build();
		return saida;
	}
	//  Recoloquei o metodo abaixo pq existe referencia no Provider
	public static Uri buildAllSemGrupoMuscularPara() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ExercicioContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemGrupoMuscularPara").build();
		return saida;
	}
	
	
	public static Uri buildAllComUsuarioSincroniza() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ExercicioContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComUsuarioSincroniza").build();
		return saida;
	}
	//  Recoloquei o metodo abaixo pq existe referencia no Provider
	public static Uri buildAllSemUsuarioSincroniza() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ExercicioContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemUsuarioSincroniza").build();
		return saida;
	}
	
	
	
	// SemChaveEstrangeira
  	
	
	public static Uri buildAllComItemSerieGera() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ExercicioContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComItemSerieGera").build();
		return saida;
	}	
	public static Uri buildAllSemItemSerieGera() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ExercicioContract.COM_RETIRADA).build();
		saida = saida.buildUpon().appendPath("SemItemSerieGera").build();
		return saida;
	}	
	
	
	
	public static Uri buildAllComExecucaoItemSerieExecutado() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ExercicioContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComExecucaoItemSerieExecutado").build();
		return saida;
	}	
	public static Uri buildAllSemExecucaoItemSerieExecutado() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ExercicioContract.COM_RETIRADA).build();
		saida = saida.buildUpon().appendPath("SemExecucaoItemSerieExecutado").build();
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
	
	
	public static List<Exercicio> converteLista(Cursor data) {
		return converteLista(data, getMontador());
	}
	public static List<Exercicio> converteLista(Cursor data, MontadorDaoI montador) {
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