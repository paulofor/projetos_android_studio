

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
import br.com.lojadigicom.coletorprecocliente.modelo.AppProduto;
import br.com.lojadigicom.coletorprecocliente.modelo.montador.AppProdutoMontador;


public final class AppProdutoContract implements BaseColumns {
	
	
	private static AplicacaoContract aplicacaoContract;
	public static void setAplicacaoContract(AplicacaoContract valor) {
		aplicacaoContract = valor;
	}
	public static String getContentAuthority() {
		return aplicacaoContract.getContentAuthority();
	}
	
	
	public static final String PATH = "app_produto";
	public static final String COM_COMPLEMENTO = "ComComplemento";
	public static final String COM_RETIRADA = "ComRetirada";

	//public static final Uri CONTENT_URI = aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();

    //public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
    //public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;

    public static final String TABLE_NAME = "app_produto";
    public static final String TABLE_NAME_SINC = "app_produto_sinc";

	public static Uri getContentUri() {
		return aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();
	}
	public static String getContentType() {
		return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static String getContentItemType() {
		return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static Uri buildAppProdutoUri(long id) {
    	return ContentUris.withAppendedId(getContentUri(), id);
    }
    
	
	
    //public static final int COL_ID = 0;
   
    public static final String COLUNA_ID_APP_PRODUTO = "id_app_produto";
    public static final int COL_ID_APP_PRODUTO = 0;
    public static final String COLUNA_NOME = "nome";
    public static final int COL_NOME = 1;
    public static final String COLUNA_URL_INSTALACAO = "url_instalacao";
    public static final int COL_URL_INSTALACAO = 2;
    public static final String COLUNA_POSSUI_VITRINE = "possui_vitrine";
    public static final int COL_POSSUI_VITRINE = 3;
    public static final String COLUNA_ATIVO = "ativo";
    public static final int COL_ATIVO = 4;
    public static final String COLUNA_STATUS = "status";
    public static final int COL_STATUS = 5;
    public static final String COLUNA_LIMITE_POSICIONADOR = "limite_posicionador";
    public static final int COL_LIMITE_POSICIONADOR = 6;
    public static final String COLUNA_POSSUI_PALAVRA_CHAVE = "possui_palavra_chave";
    public static final int COL_POSSUI_PALAVRA_CHAVE = 7;
    public static final String COLUNA_CODIGO_HASH = "codigo_hash";
    public static final int COL_CODIGO_HASH = 8;
	
 	public static final String COLUNA_CHAVE = COLUNA_ID_APP_PRODUTO;
 	public static final String COLUNA_OPERACAO_SINC = "operacao_sinc";
 	public static final int COL_OPERACAO_SINC = 9;
	
	public static final String[] COLS = new String[] { 
			TABLE_NAME + "." + COLUNA_CHAVE
        	, TABLE_NAME + "." +COLUNA_NOME
        	, TABLE_NAME + "." +COLUNA_URL_INSTALACAO
        	, TABLE_NAME + "." +COLUNA_POSSUI_VITRINE
        	, TABLE_NAME + "." +COLUNA_ATIVO
        	, TABLE_NAME + "." +COLUNA_STATUS
        	, TABLE_NAME + "." +COLUNA_LIMITE_POSICIONADOR
        	, TABLE_NAME + "." +COLUNA_POSSUI_PALAVRA_CHAVE
        	, TABLE_NAME + "." +COLUNA_CODIGO_HASH
    };
    
    public static final String[] COLS_SINC = new String[] { 
			TABLE_NAME_SINC + "." + COLUNA_CHAVE
        	, TABLE_NAME_SINC + "." +COLUNA_NOME
        	, TABLE_NAME_SINC + "." +COLUNA_URL_INSTALACAO
        	, TABLE_NAME_SINC + "." +COLUNA_POSSUI_VITRINE
        	, TABLE_NAME_SINC + "." +COLUNA_ATIVO
        	, TABLE_NAME_SINC + "." +COLUNA_STATUS
        	, TABLE_NAME_SINC + "." +COLUNA_LIMITE_POSICIONADOR
        	, TABLE_NAME_SINC + "." +COLUNA_POSSUI_PALAVRA_CHAVE
        	, TABLE_NAME_SINC + "." +COLUNA_CODIGO_HASH
			, TABLE_NAME_SINC + "." + COLUNA_OPERACAO_SINC
    };
    
    private static AppProdutoOperacao operacao = new AppProdutoOperacao();
    
	private static AppProdutoFiltro filtro = null;
	public static AppProdutoFiltro getFiltro() {
		if (filtro==null) {
			filtro = new AppProdutoFiltro();
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
	
	public static String innerJoinNaturezaProduto_Atende() {
		return " inner join " + NaturezaProdutoContract.TABLE_NAME + " on " + NaturezaProdutoContract.TABLE_NAME + ".id_app_produto_ap = " + TABLE_NAME + "." + COLUNA_CHAVE + " ";  
	}
	public static String innerJoinSincNaturezaProduto_Atende() {
		return " inner join " + NaturezaProdutoContract.TABLE_NAME_SINC + " on " + NaturezaProdutoContract.TABLE_NAME_SINC + ".id_app_produto_ap = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";  
	}
	public static String outerJoinNaturezaProduto_Atende() {
		return " left outer join " + NaturezaProdutoContract.TABLE_NAME + " on " + NaturezaProdutoContract.TABLE_NAME + ".id_app_produto_ap = " + TABLE_NAME + "." + COLUNA_CHAVE + " "; 
	}
	public static String outerJoinSincNaturezaProduto_Atende() {
		return " left outer join " + NaturezaProdutoContract.TABLE_NAME_SINC + " on " + NaturezaProdutoContract.TABLE_NAME_SINC + ".id_app_produto_ap = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";   
	}
 	
	public static String innerJoinDispositivoUsuario_UsadoPor() {
		return " inner join " + DispositivoUsuarioContract.TABLE_NAME + " on " + DispositivoUsuarioContract.TABLE_NAME + ".id_app_produto_u = " + TABLE_NAME + "." + COLUNA_CHAVE + " ";  
	}
	public static String innerJoinSincDispositivoUsuario_UsadoPor() {
		return " inner join " + DispositivoUsuarioContract.TABLE_NAME_SINC + " on " + DispositivoUsuarioContract.TABLE_NAME_SINC + ".id_app_produto_u = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";  
	}
	public static String outerJoinDispositivoUsuario_UsadoPor() {
		return " left outer join " + DispositivoUsuarioContract.TABLE_NAME + " on " + DispositivoUsuarioContract.TABLE_NAME + ".id_app_produto_u = " + TABLE_NAME + "." + COLUNA_CHAVE + " "; 
	}
	public static String outerJoinSincDispositivoUsuario_UsadoPor() {
		return " left outer join " + DispositivoUsuarioContract.TABLE_NAME_SINC + " on " + DispositivoUsuarioContract.TABLE_NAME_SINC + ".id_app_produto_u = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";   
	}
 	
	// Com chave
	
	
	
	
	
	public static String camposOrdenados() 
	{
		return " " + TABLE_NAME + "." + COLUNA_ID_APP_PRODUTO  
		+ " , " + TABLE_NAME + "." + COLUNA_NOME 
		+ " , " + TABLE_NAME + "." + COLUNA_URL_INSTALACAO 
		+ " , " + TABLE_NAME + "." + COLUNA_POSSUI_VITRINE 
		+ " , " + TABLE_NAME + "." + COLUNA_ATIVO 
		+ " , " + TABLE_NAME + "." + COLUNA_STATUS 
		+ " , " + TABLE_NAME + "." + COLUNA_LIMITE_POSICIONADOR 
		+ " , " + TABLE_NAME + "." + COLUNA_POSSUI_PALAVRA_CHAVE 
		+ " , " + TABLE_NAME + "." + COLUNA_CODIGO_HASH 
		
		
		;
	}
	
	
	
	public static String camposOrdenadosSinc() 
	{
		return " app_produto_sinc.id_app_produto " 
		+ " ,app_produto_sinc.nome " 
		+ " ,app_produto_sinc.url_instalacao " 
		+ " ,app_produto_sinc.possui_vitrine " 
		+ " ,app_produto_sinc.ativo " 
		+ " ,app_produto_sinc.status " 
		+ " ,app_produto_sinc.limite_posicionador " 
		+ " ,app_produto_sinc.possui_palavra_chave " 
		+ " ,app_produto_sinc.codigo_hash " 
		
		
		+ " ,app_produto_sinc.operacao_sinc "
		;
	}
	
	
	public static MontadorDaoComposite getMontadorComposto() {
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(getMontador(),null);
		return montador;
	}
	public static MontadorDaoI getMontador() {
		return new AppProdutoMontador();
	}
	
	
	// ComChaveEstrangeira
  	
	
	// SemChaveEstrangeira
  	
	
	public static Uri buildAllComNaturezaProdutoAtende() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(AppProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComNaturezaProdutoAtende").build();
		return saida;
	}	
	public static Uri buildAllSemNaturezaProdutoAtende() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(AppProdutoContract.COM_RETIRADA).build();
		saida = saida.buildUpon().appendPath("SemNaturezaProdutoAtende").build();
		return saida;
	}	
	
	
	
	public static Uri buildAllComDispositivoUsuarioUsadoPor() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(AppProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComDispositivoUsuarioUsadoPor").build();
		return saida;
	}	
	public static Uri buildAllSemDispositivoUsuarioUsadoPor() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(AppProdutoContract.COM_RETIRADA).build();
		saida = saida.buildUpon().appendPath("SemDispositivoUsuarioUsadoPor").build();
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
	
	
	public static List<AppProduto> converteLista(Cursor data) {
		return converteLista(data, getMontador());
	}
	public static List<AppProduto> converteLista(Cursor data, MontadorDaoI montador) {
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