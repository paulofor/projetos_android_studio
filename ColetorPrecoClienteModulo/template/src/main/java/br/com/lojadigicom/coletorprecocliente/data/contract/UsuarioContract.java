

package  br.com.lojadigicom.coletorprecocliente.data.contract;



import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.content.ContentUris;

import java.util.ArrayList;
import java.util.List;

import br.com.lojadigicom.coletorprecocliente.framework.dao.DaoException;
import br.com.lojadigicom.coletorprecocliente.framework.dao.DaoItemRetorno;
import br.com.lojadigicom.coletorprecocliente.framework.dao.MontadorDaoI;
import br.com.lojadigicom.coletorprecocliente.framework.data.MontadorDaoComposite;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.coletorprecocliente.modelo.Usuario;
import br.com.lojadigicom.coletorprecocliente.modelo.montador.UsuarioMontador;


public final class UsuarioContract implements BaseColumns {
	
	
	private static AplicacaoContract aplicacaoContract;
	public static void setAplicacaoContract(AplicacaoContract valor) {
		aplicacaoContract = valor;
	}
	public static String getContentAuthority() {
		return aplicacaoContract.getContentAuthority();
	}
	
	
	public static final String PATH = "usuario";
	public static final String COM_COMPLEMENTO = "ComComplemento";
	public static final String COM_RETIRADA = "ComRetirada";

	//public static final Uri CONTENT_URI = aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();

    //public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
    //public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;

    public static final String TABLE_NAME = "usuario";
    public static final String TABLE_NAME_SINC = "usuario_sinc";

	public static Uri getContentUri() {
		return aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();
	}
	public static String getContentType() {
		return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static String getContentItemType() {
		return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static Uri buildUsuarioUri(long id) {
    	return ContentUris.withAppendedId(getContentUri(), id);
    }
    
	
	
    //public static final int COL_ID = 0;
   
    public static final String COLUNA_ID_USUARIO = "id_usuario";
    public static final int COL_ID_USUARIO = 0;
    public static final String COLUNA_NOME_USUARIO = "nome_usuario";
    public static final int COL_NOME_USUARIO = 1;
    public static final String COLUNA_EMAIL = "email";
    public static final int COL_EMAIL = 2;
    public static final String COLUNA_PLANO01 = "plano01";
    public static final int COL_PLANO01 = 3;
    public static final String COLUNA_PLANO02 = "plano02";
    public static final int COL_PLANO02 = 4;
    public static final String COLUNA_PLANO03 = "plano03";
    public static final int COL_PLANO03 = 5;
    public static final String COLUNA_PLANO04 = "plano04";
    public static final int COL_PLANO04 = 6;
    public static final String COLUNA_PLANO05 = "plano05";
    public static final int COL_PLANO05 = 7;
    public static final String COLUNA_DATA_ULTIMA_ALTERACAO = "data_ultima_alteracao";
    public static final int COL_DATA_ULTIMA_ALTERACAO = 8;
    public static final String COLUNA_PERMITE_SINCRONIZAR = "permite_sincronizar";
    public static final int COL_PERMITE_SINCRONIZAR = 9;
    public static final String COLUNA_CODIGO_EXTERNO = "codigo_externo";
    public static final int COL_CODIGO_EXTERNO = 10;
	
 	public static final String COLUNA_CHAVE = COLUNA_ID_USUARIO;
 	public static final String COLUNA_OPERACAO_SINC = "operacao_sinc";
 	public static final int COL_OPERACAO_SINC = 11;
	
	public static final String[] COLS = new String[] { 
			TABLE_NAME + "." + COLUNA_CHAVE
        	, TABLE_NAME + "." +COLUNA_NOME_USUARIO
        	, TABLE_NAME + "." +COLUNA_EMAIL
        	, TABLE_NAME + "." +COLUNA_PLANO01
        	, TABLE_NAME + "." +COLUNA_PLANO02
        	, TABLE_NAME + "." +COLUNA_PLANO03
        	, TABLE_NAME + "." +COLUNA_PLANO04
        	, TABLE_NAME + "." +COLUNA_PLANO05
        	, TABLE_NAME + "." +COLUNA_DATA_ULTIMA_ALTERACAO
        	, TABLE_NAME + "." +COLUNA_PERMITE_SINCRONIZAR
        	, TABLE_NAME + "." +COLUNA_CODIGO_EXTERNO
    };
    
    public static final String[] COLS_SINC = new String[] { 
			TABLE_NAME_SINC + "." + COLUNA_CHAVE
        	, TABLE_NAME_SINC + "." +COLUNA_NOME_USUARIO
        	, TABLE_NAME_SINC + "." +COLUNA_EMAIL
        	, TABLE_NAME_SINC + "." +COLUNA_PLANO01
        	, TABLE_NAME_SINC + "." +COLUNA_PLANO02
        	, TABLE_NAME_SINC + "." +COLUNA_PLANO03
        	, TABLE_NAME_SINC + "." +COLUNA_PLANO04
        	, TABLE_NAME_SINC + "." +COLUNA_PLANO05
        	, TABLE_NAME_SINC + "." +COLUNA_DATA_ULTIMA_ALTERACAO
        	, TABLE_NAME_SINC + "." +COLUNA_PERMITE_SINCRONIZAR
        	, TABLE_NAME_SINC + "." +COLUNA_CODIGO_EXTERNO
			, TABLE_NAME_SINC + "." + COLUNA_OPERACAO_SINC
    };
    
    private static UsuarioOperacao operacao = new UsuarioOperacao();
    
	private static UsuarioFiltro filtro = null;
	public static UsuarioFiltro getFiltro() {
		if (filtro==null) {
			filtro = new UsuarioFiltro();
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
	
	
	
	
	
	public static String camposOrdenados() 
	{
		return " " + TABLE_NAME + "." + COLUNA_ID_USUARIO  
		+ " , " + TABLE_NAME + "." + COLUNA_NOME_USUARIO 
		+ " , " + TABLE_NAME + "." + COLUNA_EMAIL 
		+ " , " + TABLE_NAME + "." + COLUNA_PLANO01 
		+ " , " + TABLE_NAME + "." + COLUNA_PLANO02 
		+ " , " + TABLE_NAME + "." + COLUNA_PLANO03 
		+ " , " + TABLE_NAME + "." + COLUNA_PLANO04 
		+ " , " + TABLE_NAME + "." + COLUNA_PLANO05 
		+ " , " + TABLE_NAME + "." + COLUNA_DATA_ULTIMA_ALTERACAO 
		+ " , " + TABLE_NAME + "." + COLUNA_PERMITE_SINCRONIZAR 
		+ " , " + TABLE_NAME + "." + COLUNA_CODIGO_EXTERNO 
		
		
		;
	}
	
	
	
	public static String camposOrdenadosSinc() 
	{
		return " usuario_sinc.id_usuario " 
		+ " ,usuario_sinc.nome_usuario " 
		+ " ,usuario_sinc.email " 
		+ " ,usuario_sinc.plano01 " 
		+ " ,usuario_sinc.plano02 " 
		+ " ,usuario_sinc.plano03 " 
		+ " ,usuario_sinc.plano04 " 
		+ " ,usuario_sinc.plano05 " 
		+ " ,usuario_sinc.data_ultima_alteracao " 
		+ " ,usuario_sinc.permite_sincronizar " 
		+ " ,usuario_sinc.codigo_externo " 
		
		
		+ " ,usuario_sinc.operacao_sinc "
		;
	}
	
	
	public static MontadorDaoComposite getMontadorComposto() {
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(getMontador(),null);
		return montador;
	}
	public static MontadorDaoI getMontador() {
		return new UsuarioMontador();
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
	
	
	public static List<Usuario> converteLista(Cursor data) {
		return converteLista(data, getMontador());
	}
	public static List<Usuario> converteLista(Cursor data, MontadorDaoI montador) {
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