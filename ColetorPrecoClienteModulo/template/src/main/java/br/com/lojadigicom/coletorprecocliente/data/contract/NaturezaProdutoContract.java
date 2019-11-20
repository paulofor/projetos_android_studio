

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
import br.com.lojadigicom.coletorprecocliente.modelo.NaturezaProduto;
import br.com.lojadigicom.coletorprecocliente.modelo.montador.NaturezaProdutoMontador;


public final class NaturezaProdutoContract implements BaseColumns {
	
	
	private static AplicacaoContract aplicacaoContract;
	public static void setAplicacaoContract(AplicacaoContract valor) {
		aplicacaoContract = valor;
	}
	public static String getContentAuthority() {
		return aplicacaoContract.getContentAuthority();
	}
	
	
	public static final String PATH = "natureza_produto";
	public static final String COM_COMPLEMENTO = "ComComplemento";
	public static final String COM_RETIRADA = "ComRetirada";

	//public static final Uri CONTENT_URI = aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();

    //public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
    //public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;

    public static final String TABLE_NAME = "natureza_produto";
    public static final String TABLE_NAME_SINC = "natureza_produto_sinc";

	public static Uri getContentUri() {
		return aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();
	}
	public static String getContentType() {
		return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static String getContentItemType() {
		return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static Uri buildNaturezaProdutoUri(long id) {
    	return ContentUris.withAppendedId(getContentUri(), id);
    }
    
	public static Uri buildGetPorAtendidoPorAppProdutoUri(long id) {
    	Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(""+id).build();
		saida = saida.buildUpon().appendPath(AppProdutoContract.PATH).build();
    	return saida;
    }
	
	
	
    //public static final int COL_ID = 0;
   
    public static final String COLUNA_ID_NATUREZA_PRODUTO = "id_natureza_produto";
    public static final int COL_ID_NATUREZA_PRODUTO = 0;
    public static final String COLUNA_NOME_NATUREZA_PRODUTO = "nome_natureza_produto";
    public static final int COL_NOME_NATUREZA_PRODUTO = 1;
    public static final String COLUNA_ID_APP_PRODUTO_AP = "id_app_produto_ap";
    public static final int COL_ID_APP_PRODUTO_AP = 2;
	
    public static final String COLUNA_QTDEOPORTUNIDADEDIA = "qtde_oportunidade_dia";
    public static final int COL_QTDEOPORTUNIDADEDIA = 3;
	
    public static final String COLUNA_ATIVO = "ativo";
    public static final int COL_ATIVO = 4;
	
	
 	public static final String COLUNA_CHAVE = COLUNA_ID_NATUREZA_PRODUTO;
 	public static final String COLUNA_OPERACAO_SINC = "operacao_sinc";
 	public static final int COL_OPERACAO_SINC = 5;
	
	public static final String[] COLS = new String[] { 
			TABLE_NAME + "." + COLUNA_CHAVE
        	, TABLE_NAME + "." +COLUNA_NOME_NATUREZA_PRODUTO
			, TABLE_NAME + "." +COLUNA_ID_APP_PRODUTO_AP
	
    };
    
    public static final String[] COLS_SINC = new String[] { 
			TABLE_NAME_SINC + "." + COLUNA_CHAVE
        	, TABLE_NAME_SINC + "." +COLUNA_NOME_NATUREZA_PRODUTO
			, TABLE_NAME_SINC + "." +COLUNA_ID_APP_PRODUTO_AP
	
			, TABLE_NAME_SINC + "." + COLUNA_OPERACAO_SINC
    };
    
    private static NaturezaProdutoOperacao operacao = new NaturezaProdutoOperacao();
 	public static Uri buildListaAtivo() {
		return operacao.buildListaAtivo(getFiltro());
    }
    private static MontadorDaoI _montadorListaAtivo = null;
    public static MontadorDaoI getMontadorListaAtivo() {
    	return _montadorListaAtivo;
    }
    public static void setMontadorListaAtivo(MontadorDaoI montador) {
    	_montadorListaAtivo = montador;
    }
    
	private static NaturezaProdutoFiltro filtro = null;
	public static NaturezaProdutoFiltro getFiltro() {
		if (filtro==null) {
			filtro = new NaturezaProdutoFiltro();
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
	
	public static String innerJoinOportunidadeDia_Possui() {
		return " inner join " + OportunidadeDiaContract.TABLE_NAME + " on " + OportunidadeDiaContract.TABLE_NAME + ".id_natureza_produto_pa = " + TABLE_NAME + "." + COLUNA_CHAVE + " ";  
	}
	public static String innerJoinSincOportunidadeDia_Possui() {
		return " inner join " + OportunidadeDiaContract.TABLE_NAME_SINC + " on " + OportunidadeDiaContract.TABLE_NAME_SINC + ".id_natureza_produto_pa = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";  
	}
	public static String outerJoinOportunidadeDia_Possui() {
		return " left outer join " + OportunidadeDiaContract.TABLE_NAME + " on " + OportunidadeDiaContract.TABLE_NAME + ".id_natureza_produto_pa = " + TABLE_NAME + "." + COLUNA_CHAVE + " "; 
	}
	public static String outerJoinSincOportunidadeDia_Possui() {
		return " left outer join " + OportunidadeDiaContract.TABLE_NAME_SINC + " on " + OportunidadeDiaContract.TABLE_NAME_SINC + ".id_natureza_produto_pa = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";   
	}
	public static MontadorDaoI adicionaMontadorOportunidadeDiaPossuiLista (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(OportunidadeDiaContract.getMontador(), "OportunidadeDia_Possui");
		return montador;
	}
 	
	public static String innerJoinUsuarioPesquisa_PesquisadoPor() {
		return " inner join " + UsuarioPesquisaContract.TABLE_NAME + " on " + UsuarioPesquisaContract.TABLE_NAME + ".id_natureza_produto_p = " + TABLE_NAME + "." + COLUNA_CHAVE + " ";  
	}
	public static String innerJoinSincUsuarioPesquisa_PesquisadoPor() {
		return " inner join " + UsuarioPesquisaContract.TABLE_NAME_SINC + " on " + UsuarioPesquisaContract.TABLE_NAME_SINC + ".id_natureza_produto_p = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";  
	}
	public static String outerJoinUsuarioPesquisa_PesquisadoPor() {
		return " left outer join " + UsuarioPesquisaContract.TABLE_NAME + " on " + UsuarioPesquisaContract.TABLE_NAME + ".id_natureza_produto_p = " + TABLE_NAME + "." + COLUNA_CHAVE + " "; 
	}
	public static String outerJoinSincUsuarioPesquisa_PesquisadoPor() {
		return " left outer join " + UsuarioPesquisaContract.TABLE_NAME_SINC + " on " + UsuarioPesquisaContract.TABLE_NAME_SINC + ".id_natureza_produto_p = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";   
	}
	public static MontadorDaoI adicionaMontadorUsuarioPesquisaPesquisadoPorLista (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(UsuarioPesquisaContract.getMontador(), "UsuarioPesquisa_PesquisadoPor");
		return montador;
	}
 	
	public static String innerJoinPalavraChavePesquisa_Possui() {
		return " inner join " + PalavraChavePesquisaContract.TABLE_NAME + " on " + PalavraChavePesquisaContract.TABLE_NAME + ".id_natureza_produto_ra = " + TABLE_NAME + "." + COLUNA_CHAVE + " ";  
	}
	public static String innerJoinSincPalavraChavePesquisa_Possui() {
		return " inner join " + PalavraChavePesquisaContract.TABLE_NAME_SINC + " on " + PalavraChavePesquisaContract.TABLE_NAME_SINC + ".id_natureza_produto_ra = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";  
	}
	public static String outerJoinPalavraChavePesquisa_Possui() {
		return " left outer join " + PalavraChavePesquisaContract.TABLE_NAME + " on " + PalavraChavePesquisaContract.TABLE_NAME + ".id_natureza_produto_ra = " + TABLE_NAME + "." + COLUNA_CHAVE + " "; 
	}
	public static String outerJoinSincPalavraChavePesquisa_Possui() {
		return " left outer join " + PalavraChavePesquisaContract.TABLE_NAME_SINC + " on " + PalavraChavePesquisaContract.TABLE_NAME_SINC + ".id_natureza_produto_ra = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";   
	}
	public static MontadorDaoI adicionaMontadorPalavraChavePesquisaPossuiLista (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(PalavraChavePesquisaContract.getMontador(), "PalavraChavePesquisa_Possui");
		return montador;
	}
 	
	public static String innerJoinProdutoCliente_Possui() {
		return " inner join " + ProdutoClienteContract.TABLE_NAME + " on " + ProdutoClienteContract.TABLE_NAME + ".id_natureza_produto_ra = " + TABLE_NAME + "." + COLUNA_CHAVE + " ";  
	}
	public static String innerJoinSincProdutoCliente_Possui() {
		return " inner join " + ProdutoClienteContract.TABLE_NAME_SINC + " on " + ProdutoClienteContract.TABLE_NAME_SINC + ".id_natureza_produto_ra = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";  
	}
	public static String outerJoinProdutoCliente_Possui() {
		return " left outer join " + ProdutoClienteContract.TABLE_NAME + " on " + ProdutoClienteContract.TABLE_NAME + ".id_natureza_produto_ra = " + TABLE_NAME + "." + COLUNA_CHAVE + " "; 
	}
	public static String outerJoinSincProdutoCliente_Possui() {
		return " left outer join " + ProdutoClienteContract.TABLE_NAME_SINC + " on " + ProdutoClienteContract.TABLE_NAME_SINC + ".id_natureza_produto_ra = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";   
	}
	public static MontadorDaoI adicionaMontadorProdutoClientePossuiLista (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(ProdutoClienteContract.getMontador(), "ProdutoCliente_Possui");
		return montador;
	}
 	
	// Com chave
	
	
	public static String innerJoinAppProduto_AtendidoPor() {
		return " inner join " + AppProdutoContract.TABLE_NAME + " on " + AppProdutoContract.TABLE_NAME + "." + AppProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_APP_PRODUTO_AP + " ";  
	}
	public static String innerJoinSincAppProduto_AtendidoPor() {
		return " inner join " + AppProdutoContract.TABLE_NAME_SINC + " on " + AppProdutoContract.TABLE_NAME_SINC + "." + AppProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_APP_PRODUTO_AP + " ";  
	}
	public static String outerJoinAppProduto_AtendidoPor() {
		return " left outer join " + AppProdutoContract.TABLE_NAME + " on " + AppProdutoContract.TABLE_NAME + "." + AppProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_APP_PRODUTO_AP + " "; 
	}
	public static String outerJoinSincAppProduto_AtendidoPor() {
		return " left outer join " + AppProdutoContract.TABLE_NAME_SINC + " on " + AppProdutoContract.TABLE_NAME_SINC + "." + AppProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_APP_PRODUTO_AP + " ";   
	}
	
	
	public static MontadorDaoI adicionaMontadorAppProdutoAtendidoPor (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(AppProdutoContract.getMontador(), "AppProduto_AtendidoPor");
		return montador;
	}
	public static Uri adicionaAppProdutoAtendidoPor(Uri uri) {
		return uri.buildUpon().appendPath("ComAppProdutoAtendidoPor").build();
	}
 	
	
	
	
	
	public static String camposOrdenados() 
	{
		return " " + TABLE_NAME + "." + COLUNA_ID_NATUREZA_PRODUTO  
		+ " , " + TABLE_NAME + "." + COLUNA_NOME_NATUREZA_PRODUTO 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_APP_PRODUTO_AP 
		
		
		+ " , " + TABLE_NAME + "." + COLUNA_QTDEOPORTUNIDADEDIA 
		
		+ " , " + TABLE_NAME + "." + COLUNA_ATIVO 
		
		;
	}
	
	
	
	public static String camposOrdenadosSinc() 
	{
		return " natureza_produto_sinc.id_natureza_produto " 
		+ " ,natureza_produto_sinc.nome_natureza_produto " 
		+ " ,natureza_produto_sinc.id_app_produto_ap " 
		
		
		+ " , " + TABLE_NAME_SINC + "." + COLUNA_QTDEOPORTUNIDADEDIA 
		
		+ " , " + TABLE_NAME_SINC + "." + COLUNA_ATIVO 
		
		+ " ,natureza_produto_sinc.operacao_sinc "
		;
	}
	
	
	public static MontadorDaoComposite getMontadorComposto() {
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(getMontador(),null);
		return montador;
	}
	public static MontadorDaoI getMontador() {
		return new NaturezaProdutoMontador();
	}
	
	
	// ComChaveEstrangeira
  	
	public static Uri buildAllComAppProdutoAtendidoPor() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(NaturezaProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComAppProdutoAtendidoPor").build();
		return saida;
	}
	//  Recoloquei o metodo abaixo pq existe referencia no Provider
	public static Uri buildAllSemAppProdutoAtendidoPor() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(NaturezaProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemAppProdutoAtendidoPor").build();
		return saida;
	}
	
	
	
	// SemChaveEstrangeira
  	
	
	public static Uri buildAllComOportunidadeDiaPossui() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(NaturezaProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComOportunidadeDiaPossui").build();
		return saida;
	}	
	public static Uri buildAllSemOportunidadeDiaPossui() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(NaturezaProdutoContract.COM_RETIRADA).build();
		saida = saida.buildUpon().appendPath("SemOportunidadeDiaPossui").build();
		return saida;
	}	
	
	
	
	public static Uri buildAllComUsuarioPesquisaPesquisadoPor() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(NaturezaProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComUsuarioPesquisaPesquisadoPor").build();
		return saida;
	}	
	public static Uri buildAllSemUsuarioPesquisaPesquisadoPor() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(NaturezaProdutoContract.COM_RETIRADA).build();
		saida = saida.buildUpon().appendPath("SemUsuarioPesquisaPesquisadoPor").build();
		return saida;
	}	
	
	
	
	public static Uri buildAllComPalavraChavePesquisaPossui() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(NaturezaProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComPalavraChavePesquisaPossui").build();
		return saida;
	}	
	public static Uri buildAllSemPalavraChavePesquisaPossui() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(NaturezaProdutoContract.COM_RETIRADA).build();
		saida = saida.buildUpon().appendPath("SemPalavraChavePesquisaPossui").build();
		return saida;
	}	
	
	
	
	public static Uri buildAllComProdutoClientePossui() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(NaturezaProdutoContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComProdutoClientePossui").build();
		return saida;
	}	
	public static Uri buildAllSemProdutoClientePossui() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(NaturezaProdutoContract.COM_RETIRADA).build();
		saida = saida.buildUpon().appendPath("SemProdutoClientePossui").build();
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
	
	
	public static List<NaturezaProduto> converteLista(Cursor data) {
		return converteLista(data, getMontador());
	}
	public static List<NaturezaProduto> converteLista(Cursor data, MontadorDaoI montador) {
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