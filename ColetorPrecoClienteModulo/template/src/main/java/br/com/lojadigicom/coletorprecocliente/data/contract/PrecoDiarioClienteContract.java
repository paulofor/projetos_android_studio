

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
import br.com.lojadigicom.coletorprecocliente.modelo.PrecoDiarioCliente;
import br.com.lojadigicom.coletorprecocliente.modelo.montador.PrecoDiarioClienteMontador;


public final class PrecoDiarioClienteContract implements BaseColumns {
	
	
	private static AplicacaoContract aplicacaoContract;
	public static void setAplicacaoContract(AplicacaoContract valor) {
		aplicacaoContract = valor;
	}
	public static String getContentAuthority() {
		return aplicacaoContract.getContentAuthority();
	}
	
	
	public static final String PATH = "preco_diario_cliente";
	public static final String COM_COMPLEMENTO = "ComComplemento";
	public static final String COM_RETIRADA = "ComRetirada";

	//public static final Uri CONTENT_URI = aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();

    //public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
    //public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;

    public static final String TABLE_NAME = "preco_diario_cliente";
    public static final String TABLE_NAME_SINC = "preco_diario_cliente_sinc";

	public static Uri getContentUri() {
		return aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();
	}
	public static String getContentType() {
		return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static String getContentItemType() {
		return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static Uri buildPrecoDiarioClienteUri(long id) {
    	return ContentUris.withAppendedId(getContentUri(), id);
    }
    
	public static Uri buildGetPorPertenceAProdutoClienteUri(long id) {
    	Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(""+id).build();
		saida = saida.buildUpon().appendPath(ProdutoClienteContract.PATH).build();
    	return saida;
    }
	
	
	
    //public static final int COL_ID = 0;
   
    public static final String COLUNA_ID_PRECO_DIARIO_CLIENTTE = "id_preco_diario_clientte";
    public static final int COL_ID_PRECO_DIARIO_CLIENTTE = 0;
    public static final String COLUNA_DATA_HORA = "data_hora";
    public static final int COL_DATA_HORA = 1;
    public static final String COLUNA_PRECO_VENDA = "preco_venda";
    public static final int COL_PRECO_VENDA = 2;
    public static final String COLUNA_ID_PRODUTO_CLIENTE_PA = "id_produto_cliente_pa";
    public static final int COL_ID_PRODUTO_CLIENTE_PA = 3;
	
    public static final String COLUNA_ID_USUARIO_S = "id_usuario_s";
    public static final int COL_ID_USUARIO_S = 4;
	
	
 	public static final String COLUNA_CHAVE = COLUNA_ID_PRECO_DIARIO_CLIENTTE;
 	public static final String COLUNA_OPERACAO_SINC = "operacao_sinc";
 	public static final int COL_OPERACAO_SINC = 5;
	
	public static final String[] COLS = new String[] { 
			TABLE_NAME + "." + COLUNA_CHAVE
        	, TABLE_NAME + "." +COLUNA_DATA_HORA
        	, TABLE_NAME + "." +COLUNA_PRECO_VENDA
			, TABLE_NAME + "." +COLUNA_ID_PRODUTO_CLIENTE_PA
	
			, TABLE_NAME + "." +COLUNA_ID_USUARIO_S
	
    };
    
    public static final String[] COLS_SINC = new String[] { 
			TABLE_NAME_SINC + "." + COLUNA_CHAVE
        	, TABLE_NAME_SINC + "." +COLUNA_DATA_HORA
        	, TABLE_NAME_SINC + "." +COLUNA_PRECO_VENDA
			, TABLE_NAME_SINC + "." +COLUNA_ID_PRODUTO_CLIENTE_PA
	
			, TABLE_NAME_SINC + "." +COLUNA_ID_USUARIO_S
	
			, TABLE_NAME_SINC + "." + COLUNA_OPERACAO_SINC
    };
    
    private static PrecoDiarioClienteOperacao operacao = new PrecoDiarioClienteOperacao();
 	public static Uri buildQuantidadePorProduto() {
		return operacao.buildQuantidadePorProduto(getFiltro());
    }
    private static MontadorDaoI _montadorQuantidadePorProduto = null;
    public static MontadorDaoI getMontadorQuantidadePorProduto() {
    	return _montadorQuantidadePorProduto;
    }
    public static void setMontadorQuantidadePorProduto(MontadorDaoI montador) {
    	_montadorQuantidadePorProduto = montador;
    }
    
	private static PrecoDiarioClienteFiltro filtro = null;
	public static PrecoDiarioClienteFiltro getFiltro() {
		if (filtro==null) {
			filtro = new PrecoDiarioClienteFiltro();
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
	
	
	public static String innerJoinProdutoCliente_PertenceA() {
		return " inner join " + ProdutoClienteContract.TABLE_NAME + " on " + ProdutoClienteContract.TABLE_NAME + "." + ProdutoClienteContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_PRODUTO_CLIENTE_PA + " ";  
	}
	public static String innerJoinSincProdutoCliente_PertenceA() {
		return " inner join " + ProdutoClienteContract.TABLE_NAME_SINC + " on " + ProdutoClienteContract.TABLE_NAME_SINC + "." + ProdutoClienteContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_PRODUTO_CLIENTE_PA + " ";  
	}
	public static String outerJoinProdutoCliente_PertenceA() {
		return " left outer join " + ProdutoClienteContract.TABLE_NAME + " on " + ProdutoClienteContract.TABLE_NAME + "." + ProdutoClienteContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_PRODUTO_CLIENTE_PA + " "; 
	}
	public static String outerJoinSincProdutoCliente_PertenceA() {
		return " left outer join " + ProdutoClienteContract.TABLE_NAME_SINC + " on " + ProdutoClienteContract.TABLE_NAME_SINC + "." + ProdutoClienteContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_PRODUTO_CLIENTE_PA + " ";   
	}
	
	
	public static MontadorDaoI adicionaMontadorProdutoClientePertenceA (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(ProdutoClienteContract.getMontador(), "ProdutoCliente_PertenceA");
		return montador;
	}
	public static Uri adicionaProdutoClientePertenceA(Uri uri) {
		return uri.buildUpon().appendPath("ComProdutoClientePertenceA").build();
	}
 	
	
	
	
	
	public static String camposOrdenados() 
	{
		return " " + TABLE_NAME + "." + COLUNA_ID_PRECO_DIARIO_CLIENTTE  
		+ " , " + TABLE_NAME + "." + COLUNA_DATA_HORA 
		+ " , " + TABLE_NAME + "." + COLUNA_PRECO_VENDA 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_PRODUTO_CLIENTE_PA 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_USUARIO_S 
		
		
		;
	}
	
	
	
	public static String camposOrdenadosSinc() 
	{
		return " preco_diario_cliente_sinc.id_preco_diario_clientte " 
		+ " ,preco_diario_cliente_sinc.data_hora " 
		+ " ,preco_diario_cliente_sinc.preco_venda " 
		+ " ,preco_diario_cliente_sinc.id_produto_cliente_pa " 
		+ " ,preco_diario_cliente_sinc.id_usuario_s " 
		
		
		+ " ,preco_diario_cliente_sinc.operacao_sinc "
		;
	}
	
	
	public static MontadorDaoComposite getMontadorComposto() {
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(getMontador(),null);
		return montador;
	}
	public static MontadorDaoI getMontador() {
		return new PrecoDiarioClienteMontador();
	}
	
	
	// ComChaveEstrangeira
  	
	public static Uri buildAllComProdutoClientePertenceA() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(PrecoDiarioClienteContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComProdutoClientePertenceA").build();
		return saida;
	}
	//  Recoloquei o metodo abaixo pq existe referencia no Provider
	public static Uri buildAllSemProdutoClientePertenceA() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(PrecoDiarioClienteContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemProdutoClientePertenceA").build();
		return saida;
	}
	
	
	public static Uri buildAllComUsuarioSincroniza() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(PrecoDiarioClienteContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComUsuarioSincroniza").build();
		return saida;
	}
	//  Recoloquei o metodo abaixo pq existe referencia no Provider
	public static Uri buildAllSemUsuarioSincroniza() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(PrecoDiarioClienteContract.COM_COMPLEMENTO).build();
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
	
	
	public static List<PrecoDiarioCliente> converteLista(Cursor data) {
		return converteLista(data, getMontador());
	}
	public static List<PrecoDiarioCliente> converteLista(Cursor data, MontadorDaoI montador) {
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