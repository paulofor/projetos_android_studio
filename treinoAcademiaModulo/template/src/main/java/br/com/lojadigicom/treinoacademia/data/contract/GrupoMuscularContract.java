

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
import br.com.lojadigicom.treinoacademia.modelo.GrupoMuscular;
import br.com.lojadigicom.treinoacademia.modelo.montador.GrupoMuscularMontador;


public final class GrupoMuscularContract implements BaseColumns {
	
	
	private static AplicacaoContract aplicacaoContract;
	public static void setAplicacaoContract(AplicacaoContract valor) {
		aplicacaoContract = valor;
	}
	public static String getContentAuthority() {
		return aplicacaoContract.getContentAuthority();
	}
	
	
	public static final String PATH = "grupo_muscular";
	public static final String COM_COMPLEMENTO = "ComComplemento";
	public static final String COM_RETIRADA = "ComRetirada";

	//public static final Uri CONTENT_URI = aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();

    //public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
    //public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;

    public static final String TABLE_NAME = "grupo_muscular";
    public static final String TABLE_NAME_SINC = "grupo_muscular_sinc";

	public static Uri getContentUri() {
		return aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();
	}
	public static String getContentType() {
		return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static String getContentItemType() {
		return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static Uri buildGrupoMuscularUri(long id) {
    	return ContentUris.withAppendedId(getContentUri(), id);
    }
    
	
	
    //public static final int COL_ID = 0;
   
    public static final String COLUNA_ID_GRUPO_MUSCULAR = "id_grupo_muscular";
    public static final int COL_ID_GRUPO_MUSCULAR = 0;
    public static final String COLUNA_NOME_GRUPO = "nome_grupo";
    public static final int COL_NOME_GRUPO = 1;
    public static final String COLUNA_IMAGEM_GRUPO = "imagem_grupo";
    public static final int COL_IMAGEM_GRUPO = 2;
	
 	public static final String COLUNA_CHAVE = COLUNA_ID_GRUPO_MUSCULAR;
 	public static final String COLUNA_OPERACAO_SINC = "operacao_sinc";
 	public static final int COL_OPERACAO_SINC = 3;
	
	public static final String[] COLS = new String[] { 
			TABLE_NAME + "." + COLUNA_CHAVE
        	, TABLE_NAME + "." +COLUNA_NOME_GRUPO
        	, TABLE_NAME + "." +COLUNA_IMAGEM_GRUPO
    };
    
    public static final String[] COLS_SINC = new String[] { 
			TABLE_NAME_SINC + "." + COLUNA_CHAVE
        	, TABLE_NAME_SINC + "." +COLUNA_NOME_GRUPO
        	, TABLE_NAME_SINC + "." +COLUNA_IMAGEM_GRUPO
			, TABLE_NAME_SINC + "." + COLUNA_OPERACAO_SINC
    };
    
    private static GrupoMuscularOperacao operacao = new GrupoMuscularOperacao();
    
	private static GrupoMuscularFiltro filtro = null;
	public static GrupoMuscularFiltro getFiltro() {
		if (filtro==null) {
			filtro = new GrupoMuscularFiltro();
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
	
	public static String innerJoinExercicio_ReferenteA() {
		return " inner join " + ExercicioContract.TABLE_NAME + " on " + ExercicioContract.TABLE_NAME + ".id_grupo_muscular_p = " + TABLE_NAME + "." + COLUNA_CHAVE + " ";  
	}
	public static String innerJoinSincExercicio_ReferenteA() {
		return " inner join " + ExercicioContract.TABLE_NAME_SINC + " on " + ExercicioContract.TABLE_NAME_SINC + ".id_grupo_muscular_p = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";  
	}
	public static String outerJoinExercicio_ReferenteA() {
		return " left outer join " + ExercicioContract.TABLE_NAME + " on " + ExercicioContract.TABLE_NAME + ".id_grupo_muscular_p = " + TABLE_NAME + "." + COLUNA_CHAVE + " "; 
	}
	public static String outerJoinSincExercicio_ReferenteA() {
		return " left outer join " + ExercicioContract.TABLE_NAME_SINC + " on " + ExercicioContract.TABLE_NAME_SINC + ".id_grupo_muscular_p = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";   
	}
	public static MontadorDaoI adicionaMontadorExercicioReferenteALista (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(ExercicioContract.getMontador(), "Exercicio_ReferenteA");
		return montador;
	}
 	
	// Com chave
	
	
	
	
	
	public static String camposOrdenados() 
	{
		return " " + TABLE_NAME + "." + COLUNA_ID_GRUPO_MUSCULAR  
		+ " , " + TABLE_NAME + "." + COLUNA_NOME_GRUPO 
		+ " , " + TABLE_NAME + "." + COLUNA_IMAGEM_GRUPO 
		
		
		;
	}
	
	
	
	public static String camposOrdenadosSinc() 
	{
		return " grupo_muscular_sinc.id_grupo_muscular " 
		+ " ,grupo_muscular_sinc.nome_grupo " 
		+ " ,grupo_muscular_sinc.imagem_grupo " 
		
		
		+ " ,grupo_muscular_sinc.operacao_sinc "
		;
	}
	
	
	public static MontadorDaoComposite getMontadorComposto() {
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(getMontador(),null);
		return montador;
	}
	public static MontadorDaoI getMontador() {
		return new GrupoMuscularMontador();
	}
	
	
	// ComChaveEstrangeira
  	
	
	// SemChaveEstrangeira
  	
	
	public static Uri buildAllComExercicioReferenteA() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(GrupoMuscularContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComExercicioReferenteA").build();
		return saida;
	}	
	public static Uri buildAllSemExercicioReferenteA() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(GrupoMuscularContract.COM_RETIRADA).build();
		saida = saida.buildUpon().appendPath("SemExercicioReferenteA").build();
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
	
	
	public static List<GrupoMuscular> converteLista(Cursor data) {
		return converteLista(data, getMontador());
	}
	public static List<GrupoMuscular> converteLista(Cursor data, MontadorDaoI montador) {
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