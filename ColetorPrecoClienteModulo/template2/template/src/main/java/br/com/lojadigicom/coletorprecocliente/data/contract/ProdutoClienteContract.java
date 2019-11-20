

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
import br.com.lojadigicom.coletorprecocliente.modelo.ProdutoCliente;
import br.com.lojadigicom.coletorprecocliente.modelo.montador.ProdutoClienteMontador;


public final class ProdutoClienteContract implements BaseColumns {
	
	
	private static AplicacaoContract aplicacaoContract;
	public static void setAplicacaoContract(AplicacaoContract valor) {
		aplicacaoContract = valor;
	}
	public static String getContentAuthority() {
		return aplicacaoContract.getContentAuthority();
	}
	
	
	public static final String PATH = "produto_cliente";
	public static final String COM_COMPLEMENTO = "ComComplemento";
	public static final String COM_RETIRADA = "ComRetirada";

	//public static final Uri CONTENT_URI = aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();

    //public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
    //public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;

    public static final String TABLE_NAME = "produto_cliente";
    public static final String TABLE_NAME_SINC = "produto_cliente_sinc";

	public static Uri getContentUri() {
		return aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();
	}
	public static String getContentType() {
		return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static String getContentItemType() {
		return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static Uri buildProdutoClienteUri(long id) {
    	return ContentUris.withAppendedId(getContentUri(), id);
    }
    
	public static Uri buildGetPorReferenteANaturezaProdutoUri(long id) {
    	Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(""+id).build();
		saida = saida.buildUpon().appendPath(NaturezaProdutoContract.PATH).build();
    	return saida;
    }
	
	
	
    //public static final int COL_ID = 0;
   
    public static final String COLUNA_ID_PRODUTO_CLIENTE = "id_produto_cliente";
    public static final int COL_ID_PRODUTO_CLIENTE = 0;
    public static final String COLUNA_NOME = "nome";
    public static final int COL_NOME = 1;
    public static final String COLUNA_POSICAO_PRODUTO = "posicao_produto";
    public static final int COL_POSICAO_PRODUTO = 2;
    public static final String COLUNA_IMAGEM = "imagem";
    public static final int COL_IMAGEM = 3;
    public static final String COLUNA_PRECO_ATUAL = "preco_atual";
    public static final int COL_PRECO_ATUAL = 4;
    public static final String COLUNA_MARCA = "marca";
    public static final int COL_MARCA = 5;
    public static final String COLUNA_LOJA = "loja";
    public static final int COL_LOJA = 6;
    public static final String COLUNA_DATA = "data";
    public static final int COL_DATA = 7;
    public static final String COLUNA_ID_NATUREZA_PRODUTO_RA = "id_natureza_produto_ra";
    public static final int COL_ID_NATUREZA_PRODUTO_RA = 8;
	
	
 	public static final String COLUNA_CHAVE = COLUNA_ID_PRODUTO_CLIENTE;
 	public static final String COLUNA_OPERACAO_SINC = "operacao_sinc";
 	public static final int COL_OPERACAO_SINC = 9;
	
	public static final String[] COLS = new String[] { 
			TABLE_NAME + "." + COLUNA_CHAVE
        	, TABLE_NAME + "." +COLUNA_NOME
        	, TABLE_NAME + "." +COLUNA_POSICAO_PRODUTO
        	, TABLE_NAME + "." +COLUNA_IMAGEM
        	, TABLE_NAME + "." +COLUNA_PRECO_ATUAL
        	, TABLE_NAME + "." +COLUNA_MARCA
        	, TABLE_NAME + "." +COLUNA_LOJA
        	, TABLE_NAME + "." +COLUNA_DATA
			, TABLE_NAME + "." +COLUNA_ID_NATUREZA_PRODUTO_RA
	
    };
    
    public static final String[] COLS_SINC = new String[] { 
			TABLE_NAME_SINC + "." + COLUNA_CHAVE
        	, TABLE_NAME_SINC + "." +COLUNA_NOME
        	, TABLE_NAME_SINC + "." +COLUNA_POSICAO_PRODUTO
        	, TABLE_NAME_SINC + "." +COLUNA_IMAGEM
        	, TABLE_NAME_SINC + "." +COLUNA_PRECO_ATUAL
        	, TABLE_NAME_SINC + "." +COLUNA_MARCA
        	, TABLE_NAME_SINC + "." +COLUNA_LOJA
        	, TABLE_NAME_SINC + "." +COLUNA_DATA
			, TABLE_NAME_SINC + "." +COLUNA_ID_NATUREZA_PRODUTO_RA
	
			, TABLE_NAME_SINC + "." + COLUNA_OPERACAO_SINC
    };
    
    private static ProdutoClienteOperacao operacao = new ProdutoClienteOperacao();
 	public static Uri buildListaNaoEscolhido() {
		return operacao.buildListaNaoEscolhido(getFiltro());
    }
    private static MontadorDaoI _montadorListaNaoEscolhido = null;
    public static MontadorDaoI getMontadorListaNaoEscolhido() {
    	return _montadorListaNaoEscolhido;
    }
    public static void setMontadorListaNaoEscolhido(MontadorDaoI montador) {
    	_montadorListaNaoEscolhido = montador;
    }
 	public static Uri buildObtemProximoNaoEscolhido() {
		return operacao.buildObtemProximoNaoEscolhido(getFiltro());
    }
    private static MontadorDaoI _montadorObtemProximoNaoEscolhido = null;
    public static MontadorDaoI getMontadorObtemProximoNaoEscolhido() {
    	return _montadorObtemProximoNaoEscolhido;
    }
    public static void setMontadorObtemProximoNaoEscolhido(MontadorDaoI montador) {
    	_montadorObtemProximoNaoEscolhido = montador;
    }
    
	private static ProdutoClienteFiltro filtro = null;
	public static ProdutoClienteFiltro getFiltro() {
		if (filtro==null) {
			filtro = new ProdutoClienteFiltro();
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
	
	public static String innerJoinInteresseProduto_Possui() {
		return " inner join " + InteresseProdutoContract.TABLE_NAME + " on " + InteresseProdutoContract.TABLE_NAME + ".id_produto_cliente_ra = " + TABLE_NAME + "." + COLUNA_CHAVE + " ";  
	}
	public static String innerJoinSincInteresseProduto_Possui() {
		return " inner join " + InteresseProdutoContract.TABLE_NAME_SINC + " on " + InteresseProdutoContract.TABLE_NAME_SINC + ".id_produto_cliente_ra = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";  
	}
	public static String outerJoinInteresseProduto_Possui() {
		return " left outer join " + InteresseProdutoContract.TABLE_NAME + " on " + InteresseProdutoContract.TABLE_NAME + ".id_produto_cliente_ra = " + TABLE_NAME + "." + COLUNA_CHAVE + " "; 
	}
	public static String outerJoinSincInteresseProduto_Possui() {
		return " left outer join " + InteresseProdutoContract.TABLE_NAME_SINC + " on " + InteresseProdutoContract.TABLE_NAME_SINC + ".id_produto_cliente_ra = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";   
	}
 	
	// Com chave
	
	
	public static String innerJoinNaturezaProduto_ReferenteA() {
		return " inner join " + NaturezaProdutoContract.TABLE_NAME + " on " + NaturezaProdutoContract.TABLE_NAME + "." + NaturezaProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_NATUREZA_PRODUTO_RA + " ";  
	}
	public static String innerJoinSincNaturezaProduto_ReferenteA() {
		return " inner join " + NaturezaProdutoContract.TABLE_NAME_SINC + " on " + NaturezaProdutoContract.TABLE_NAME_SINC + "." + NaturezaProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_NATUREZA_PRODUTO_RA + " ";  
	}
	public static String outerJoinNaturezaProduto_ReferenteA() {
		return " left outer join " + NaturezaProdutoContract.TABLE_NAME + " on " + NaturezaProdutoContract.TABLE_NAME + "." + NaturezaProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_NATUREZA_PRODUTO_RA + " "; 
	}
	public static String outerJoinSincNaturezaProduto_ReferenteA() {
		return " left outer join " + NaturezaProdutoContract.TABLE_NAME_SINC + " on " + NaturezaProdutoContract.TABLE_NAME_SINC + "." + NaturezaProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_NATUREZA_PRODUTO_RA + " ";   
	}
	
	
	public static MontadorDaoI adicionaMontadorNaturezaProdutoReferenteA (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(NaturezaProdutoContract.getMontador(), "NaturezaProduto_ReferenteA");
		return montador;
	}
	public static Uri adicionaNaturezaProdutoReferenteA(Uri uri) {
		return uri.buildUpon().appendPath("ComNaturezaProdutoReferenteA").build();
	}
 	
	
	
	
	
	public static String camposOrdenados() 
	{
		return " " + TABLE_NAME + "." + COLUNA_ID_PRODUTO_CLIENTE  
		+ " , " + TABLE_NAME + "." + COLUNA_NOME 
		+ " , " + TABLE_NAME + "." + COLUNA_POSICAO_PRODUTO 
		+ " , " + TABLE_NAME + "." + COLUNA_IMAGEM 
		+ " , " + TABLE_NAME + "." + COLUNA_PRECO_ATUAL 
		+ " , " + TABLE_NAME + "." + COLUNA_MARCA 
		+ " , " + TABLE_NAME + "." + COLUNA_LOJA 
		+ " , " + TABLE_NAME + "." + COLUNA_DATA 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_NATUREZA_PRODUTO_RA 
		
		
		;
	}
	
	
	
	public static String camposOrdenadosSinc() 
	{
		return " produto_cliente_sinc.id_produto_cliente " 
		+ " ,produto_cliente_sinc.nome " 
		+ " ,produto_cliente_sinc.posicao_produto " 
		+ " ,produto_cliente_sinc.imagem " 
		+ " ,produto_cliente_sinc.preco_atual " 
		+ " ,produto_cliente_sinc.marca " 
		+ " ,produto_cliente_sinc.loja " 
		+ " ,produto_cliente_sinc.data " 
		+ " ,produto_cliente_sinc.id_natureza_produto_ra " 
		
		
		+ " ,produto_cliente_sinc.operacao_sinc "
		;
	}
	
	
	public static MontadorDaoComposite getMontadorComposto() {
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(getMontador(),null);
		return montador;
	}
	public static MontadorDaoI getMontador() {
		return new ProdutoClienteMontador();
	}
	
	
	// ComChaveEstrangeira
  	
	public static Uri buildAllComNaturezaProdutoReferenteA() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ProdutoClienteContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComNaturezaProdutoReferenteA").build();
		return saida;
	}
	/*
	public static Uri buildAllSemNaturezaProdutoReferenteA() {
		Uri saida = CONTENT_URI;
		saida = saida.buildUpon().appendPath(ProdutoClienteContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemNaturezaProdutoReferenteA").build();
		return saida;
	}
	*/	
	
	
	// SemChaveEstrangeira
  	
	
	public static Uri buildAllComInteresseProdutoPossui() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ProdutoClienteContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComInteresseProdutoPossui").build();
		return saida;
	}	
	public static Uri buildAllSemInteresseProdutoPossui() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ProdutoClienteContract.COM_RETIRADA).build();
		saida = saida.buildUpon().appendPath("SemInteresseProdutoPossui").build();
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
	
	
	public static List<ProdutoCliente> converteLista(Cursor data) {
		return converteLista(data, getMontador());
	}
	public static List<ProdutoCliente> converteLista(Cursor data, MontadorDaoI montador) {
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