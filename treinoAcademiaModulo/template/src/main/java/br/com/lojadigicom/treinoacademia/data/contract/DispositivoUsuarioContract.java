

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
import br.com.lojadigicom.treinoacademia.modelo.DispositivoUsuario;
import br.com.lojadigicom.treinoacademia.modelo.montador.DispositivoUsuarioMontador;


public final class DispositivoUsuarioContract implements BaseColumns {
	
	
	private static AplicacaoContract aplicacaoContract;
	public static void setAplicacaoContract(AplicacaoContract valor) {
		aplicacaoContract = valor;
	}
	public static String getContentAuthority() {
		return aplicacaoContract.getContentAuthority();
	}
	
	
	public static final String PATH = "dispositivo_usuario";
	public static final String COM_COMPLEMENTO = "ComComplemento";
	public static final String COM_RETIRADA = "ComRetirada";

	//public static final Uri CONTENT_URI = aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();

    //public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
    //public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;

    public static final String TABLE_NAME = "dispositivo_usuario";
    public static final String TABLE_NAME_SINC = "dispositivo_usuario_sinc";

	public static Uri getContentUri() {
		return aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();
	}
	public static String getContentType() {
		return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static String getContentItemType() {
		return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static Uri buildDispositivoUsuarioUri(long id) {
    	return ContentUris.withAppendedId(getContentUri(), id);
    }
    
	public static Uri buildGetPorReferenteAUsuarioUri(long id) {
    	Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(""+id).build();
		saida = saida.buildUpon().appendPath(UsuarioContract.PATH).build();
    	return saida;
    }
	
	
	
    //public static final int COL_ID = 0;
   
    public static final String COLUNA_ID_DISPOSITIVO_USUARIO = "id_dispositivo_usuario";
    public static final int COL_ID_DISPOSITIVO_USUARIO = 0;
    public static final String COLUNA_NUMERO_CELULAR = "numero_celular";
    public static final int COL_NUMERO_CELULAR = 1;
    public static final String COLUNA_CODIGO_DISPOSITIVO = "codigo_dispositivo";
    public static final int COL_CODIGO_DISPOSITIVO = 2;
    public static final String COLUNA_TIPO_ACESSO = "tipo_acesso";
    public static final int COL_TIPO_ACESSO = 3;
    public static final String COLUNA_MELHOR_PATH = "melhor_path";
    public static final int COL_MELHOR_PATH = 4;
    public static final String COLUNA_ID_USUARIO_RA = "id_usuario_ra";
    public static final int COL_ID_USUARIO_RA = 5;
	
	
 	public static final String COLUNA_CHAVE = COLUNA_ID_DISPOSITIVO_USUARIO;
 	public static final String COLUNA_OPERACAO_SINC = "operacao_sinc";
 	public static final int COL_OPERACAO_SINC = 6;
	
	public static final String[] COLS = new String[] { 
			TABLE_NAME + "." + COLUNA_CHAVE
        	, TABLE_NAME + "." +COLUNA_NUMERO_CELULAR
        	, TABLE_NAME + "." +COLUNA_CODIGO_DISPOSITIVO
        	, TABLE_NAME + "." +COLUNA_TIPO_ACESSO
        	, TABLE_NAME + "." +COLUNA_MELHOR_PATH
			, TABLE_NAME + "." +COLUNA_ID_USUARIO_RA
	
    };
    
    public static final String[] COLS_SINC = new String[] { 
			TABLE_NAME_SINC + "." + COLUNA_CHAVE
        	, TABLE_NAME_SINC + "." +COLUNA_NUMERO_CELULAR
        	, TABLE_NAME_SINC + "." +COLUNA_CODIGO_DISPOSITIVO
        	, TABLE_NAME_SINC + "." +COLUNA_TIPO_ACESSO
        	, TABLE_NAME_SINC + "." +COLUNA_MELHOR_PATH
			, TABLE_NAME_SINC + "." +COLUNA_ID_USUARIO_RA
	
			, TABLE_NAME_SINC + "." + COLUNA_OPERACAO_SINC
    };
    
    private static DispositivoUsuarioOperacao operacao = new DispositivoUsuarioOperacao();
    
	private static DispositivoUsuarioFiltro filtro = null;
	public static DispositivoUsuarioFiltro getFiltro() {
		if (filtro==null) {
			filtro = new DispositivoUsuarioFiltro();
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
	
	
	public static String innerJoinUsuario_ReferenteA() {
		return " inner join " + UsuarioContract.TABLE_NAME + " on " + UsuarioContract.TABLE_NAME + "." + UsuarioContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_USUARIO_RA + " ";  
	}
	public static String innerJoinSincUsuario_ReferenteA() {
		return " inner join " + UsuarioContract.TABLE_NAME_SINC + " on " + UsuarioContract.TABLE_NAME_SINC + "." + UsuarioContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_USUARIO_RA + " ";  
	}
	public static String outerJoinUsuario_ReferenteA() {
		return " left outer join " + UsuarioContract.TABLE_NAME + " on " + UsuarioContract.TABLE_NAME + "." + UsuarioContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_USUARIO_RA + " "; 
	}
	public static String outerJoinSincUsuario_ReferenteA() {
		return " left outer join " + UsuarioContract.TABLE_NAME_SINC + " on " + UsuarioContract.TABLE_NAME_SINC + "." + UsuarioContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_USUARIO_RA + " ";   
	}
	
	
	public static MontadorDaoI adicionaMontadorUsuarioReferenteA (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(UsuarioContract.getMontador(), "Usuario_ReferenteA");
		return montador;
	}
	public static Uri adicionaUsuarioReferenteA(Uri uri) {
		return uri.buildUpon().appendPath("ComUsuarioReferenteA").build();
	}
 	
	
	
	
	
	public static String camposOrdenados() 
	{
		return " " + TABLE_NAME + "." + COLUNA_ID_DISPOSITIVO_USUARIO  
		+ " , " + TABLE_NAME + "." + COLUNA_NUMERO_CELULAR 
		+ " , " + TABLE_NAME + "." + COLUNA_CODIGO_DISPOSITIVO 
		+ " , " + TABLE_NAME + "." + COLUNA_TIPO_ACESSO 
		+ " , " + TABLE_NAME + "." + COLUNA_MELHOR_PATH 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_USUARIO_RA 
		
		
		;
	}
	
	
	
	public static String camposOrdenadosSinc() 
	{
		return " dispositivo_usuario_sinc.id_dispositivo_usuario " 
		+ " ,dispositivo_usuario_sinc.numero_celular " 
		+ " ,dispositivo_usuario_sinc.codigo_dispositivo " 
		+ " ,dispositivo_usuario_sinc.tipo_acesso " 
		+ " ,dispositivo_usuario_sinc.melhor_path " 
		+ " ,dispositivo_usuario_sinc.id_usuario_ra " 
		
		
		+ " ,dispositivo_usuario_sinc.operacao_sinc "
		;
	}
	
	
	public static MontadorDaoComposite getMontadorComposto() {
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(getMontador(),null);
		return montador;
	}
	public static MontadorDaoI getMontador() {
		return new DispositivoUsuarioMontador();
	}
	
	
	// ComChaveEstrangeira
  	
	public static Uri buildAllComUsuarioReferenteA() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(DispositivoUsuarioContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComUsuarioReferenteA").build();
		return saida;
	}
	//  Recoloquei o metodo abaixo pq existe referencia no Provider
	public static Uri buildAllSemUsuarioReferenteA() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(DispositivoUsuarioContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemUsuarioReferenteA").build();
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
	
	
	public static List<DispositivoUsuario> converteLista(Cursor data) {
		return converteLista(data, getMontador());
	}
	public static List<DispositivoUsuario> converteLista(Cursor data, MontadorDaoI montador) {
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