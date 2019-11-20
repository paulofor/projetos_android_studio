

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
import br.com.lojadigicom.coletorprecocliente.modelo.OportunidadeDia;
import br.com.lojadigicom.coletorprecocliente.modelo.montador.OportunidadeDiaMontador;


public final class OportunidadeDiaContract implements BaseColumns {
	
	
	private static AplicacaoContract aplicacaoContract;
	public static void setAplicacaoContract(AplicacaoContract valor) {
		aplicacaoContract = valor;
	}
	public static String getContentAuthority() {
		return aplicacaoContract.getContentAuthority();
	}
	
	
	public static final String PATH = "oportunidade_dia";
	public static final String COM_COMPLEMENTO = "ComComplemento";
	public static final String COM_RETIRADA = "ComRetirada";

	//public static final Uri CONTENT_URI = aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();

    //public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
    //public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;

    public static final String TABLE_NAME = "oportunidade_dia";
    public static final String TABLE_NAME_SINC = "oportunidade_dia_sinc";

	public static Uri getContentUri() {
		return aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();
	}
	public static String getContentType() {
		return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static String getContentItemType() {
		return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static Uri buildOportunidadeDiaUri(long id) {
    	return ContentUris.withAppendedId(getContentUri(), id);
    }
    
	public static Uri buildGetPorPertenceANaturezaProdutoUri(long id) {
    	Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(""+id).build();
		saida = saida.buildUpon().appendPath(NaturezaProdutoContract.PATH).build();
    	return saida;
    }
	
	
	
    //public static final int COL_ID = 0;
   
    public static final String COLUNA_ID_OPORTUNIDADE_DIA = "id_oportunidade_dia";
    public static final int COL_ID_OPORTUNIDADE_DIA = 0;
    public static final String COLUNA_NOME_PRODUTO = "nome_produto";
    public static final int COL_NOME_PRODUTO = 1;
    public static final String COLUNA_URL_PRODUTO = "url_produto";
    public static final int COL_URL_PRODUTO = 2;
    public static final String COLUNA_URL_IMAGEM = "url_imagem";
    public static final int COL_URL_IMAGEM = 3;
    public static final String COLUNA_PRECO_VENDA_ATUAL = "preco_venda_atual";
    public static final int COL_PRECO_VENDA_ATUAL = 4;
    public static final String COLUNA_PRECO_VENDA_ANTERIOR = "preco_venda_anterior";
    public static final int COL_PRECO_VENDA_ANTERIOR = 5;
    public static final String COLUNA_PERCENTUAL_AJUSTE_VENDA = "percentual_ajuste_venda";
    public static final int COL_PERCENTUAL_AJUSTE_VENDA = 6;
    public static final String COLUNA_QUANTIDADE_PARCELA_ATUAL = "quantidade_parcela_atual";
    public static final int COL_QUANTIDADE_PARCELA_ATUAL = 7;
    public static final String COLUNA_PRECO_PARCELA_ATUAL = "preco_parcela_atual";
    public static final int COL_PRECO_PARCELA_ATUAL = 8;
    public static final String COLUNA_PRECO_PARCELA_ANTERIOR = "preco_parcela_anterior";
    public static final int COL_PRECO_PARCELA_ANTERIOR = 9;
    public static final String COLUNA_QUANTIDADE_PARCELA_ANTERIOR = "quantidade_parcela_anterior";
    public static final int COL_QUANTIDADE_PARCELA_ANTERIOR = 10;
    public static final String COLUNA_NOME_LOJA_VIRTUAL = "nome_loja_virtual";
    public static final int COL_NOME_LOJA_VIRTUAL = 11;
    public static final String COLUNA_DATA_ULTIMA_PRECO_ANTERIOR = "data_ultima_preco_anterior";
    public static final int COL_DATA_ULTIMA_PRECO_ANTERIOR = 12;
    public static final String COLUNA_DATA_INICIO_PRECO_ATUAL = "data_inicio_preco_atual";
    public static final int COL_DATA_INICIO_PRECO_ATUAL = 13;
    public static final String COLUNA_PRECO_MEDIO = "preco_medio";
    public static final int COL_PRECO_MEDIO = 14;
    public static final String COLUNA_PRECO_MINIMO = "preco_minimo";
    public static final int COL_PRECO_MINIMO = 15;
    public static final String COLUNA_ID_NATUREZA_PRODUTO_PA = "id_natureza_produto_pa";
    public static final int COL_ID_NATUREZA_PRODUTO_PA = 16;
	
	
 	public static final String COLUNA_CHAVE = COLUNA_ID_OPORTUNIDADE_DIA;
 	public static final String COLUNA_OPERACAO_SINC = "operacao_sinc";
 	public static final int COL_OPERACAO_SINC = 17;
	
	public static final String[] COLS = new String[] { 
			TABLE_NAME + "." + COLUNA_CHAVE
        	, TABLE_NAME + "." +COLUNA_NOME_PRODUTO
        	, TABLE_NAME + "." +COLUNA_URL_PRODUTO
        	, TABLE_NAME + "." +COLUNA_URL_IMAGEM
        	, TABLE_NAME + "." +COLUNA_PRECO_VENDA_ATUAL
        	, TABLE_NAME + "." +COLUNA_PRECO_VENDA_ANTERIOR
        	, TABLE_NAME + "." +COLUNA_PERCENTUAL_AJUSTE_VENDA
        	, TABLE_NAME + "." +COLUNA_QUANTIDADE_PARCELA_ATUAL
        	, TABLE_NAME + "." +COLUNA_PRECO_PARCELA_ATUAL
        	, TABLE_NAME + "." +COLUNA_PRECO_PARCELA_ANTERIOR
        	, TABLE_NAME + "." +COLUNA_QUANTIDADE_PARCELA_ANTERIOR
        	, TABLE_NAME + "." +COLUNA_NOME_LOJA_VIRTUAL
        	, TABLE_NAME + "." +COLUNA_DATA_ULTIMA_PRECO_ANTERIOR
        	, TABLE_NAME + "." +COLUNA_DATA_INICIO_PRECO_ATUAL
        	, TABLE_NAME + "." +COLUNA_PRECO_MEDIO
        	, TABLE_NAME + "." +COLUNA_PRECO_MINIMO
			, TABLE_NAME + "." +COLUNA_ID_NATUREZA_PRODUTO_PA
	
    };
    
    public static final String[] COLS_SINC = new String[] { 
			TABLE_NAME_SINC + "." + COLUNA_CHAVE
        	, TABLE_NAME_SINC + "." +COLUNA_NOME_PRODUTO
        	, TABLE_NAME_SINC + "." +COLUNA_URL_PRODUTO
        	, TABLE_NAME_SINC + "." +COLUNA_URL_IMAGEM
        	, TABLE_NAME_SINC + "." +COLUNA_PRECO_VENDA_ATUAL
        	, TABLE_NAME_SINC + "." +COLUNA_PRECO_VENDA_ANTERIOR
        	, TABLE_NAME_SINC + "." +COLUNA_PERCENTUAL_AJUSTE_VENDA
        	, TABLE_NAME_SINC + "." +COLUNA_QUANTIDADE_PARCELA_ATUAL
        	, TABLE_NAME_SINC + "." +COLUNA_PRECO_PARCELA_ATUAL
        	, TABLE_NAME_SINC + "." +COLUNA_PRECO_PARCELA_ANTERIOR
        	, TABLE_NAME_SINC + "." +COLUNA_QUANTIDADE_PARCELA_ANTERIOR
        	, TABLE_NAME_SINC + "." +COLUNA_NOME_LOJA_VIRTUAL
        	, TABLE_NAME_SINC + "." +COLUNA_DATA_ULTIMA_PRECO_ANTERIOR
        	, TABLE_NAME_SINC + "." +COLUNA_DATA_INICIO_PRECO_ATUAL
        	, TABLE_NAME_SINC + "." +COLUNA_PRECO_MEDIO
        	, TABLE_NAME_SINC + "." +COLUNA_PRECO_MINIMO
			, TABLE_NAME_SINC + "." +COLUNA_ID_NATUREZA_PRODUTO_PA
	
			, TABLE_NAME_SINC + "." + COLUNA_OPERACAO_SINC
    };
    
    private static OportunidadeDiaOperacao operacao = new OportunidadeDiaOperacao();
    
	private static OportunidadeDiaFiltro filtro = null;
	public static OportunidadeDiaFiltro getFiltro() {
		if (filtro==null) {
			filtro = new OportunidadeDiaFiltro();
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
	
	
	public static String innerJoinNaturezaProduto_PertenceA() {
		return " inner join " + NaturezaProdutoContract.TABLE_NAME + " on " + NaturezaProdutoContract.TABLE_NAME + "." + NaturezaProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_NATUREZA_PRODUTO_PA + " ";  
	}
	public static String innerJoinSincNaturezaProduto_PertenceA() {
		return " inner join " + NaturezaProdutoContract.TABLE_NAME_SINC + " on " + NaturezaProdutoContract.TABLE_NAME_SINC + "." + NaturezaProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_NATUREZA_PRODUTO_PA + " ";  
	}
	public static String outerJoinNaturezaProduto_PertenceA() {
		return " left outer join " + NaturezaProdutoContract.TABLE_NAME + " on " + NaturezaProdutoContract.TABLE_NAME + "." + NaturezaProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_NATUREZA_PRODUTO_PA + " "; 
	}
	public static String outerJoinSincNaturezaProduto_PertenceA() {
		return " left outer join " + NaturezaProdutoContract.TABLE_NAME_SINC + " on " + NaturezaProdutoContract.TABLE_NAME_SINC + "." + NaturezaProdutoContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_NATUREZA_PRODUTO_PA + " ";   
	}
	
	
	public static MontadorDaoI adicionaMontadorNaturezaProdutoPertenceA (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(NaturezaProdutoContract.getMontador(), "NaturezaProduto_PertenceA");
		return montador;
	}
	public static Uri adicionaNaturezaProdutoPertenceA(Uri uri) {
		return uri.buildUpon().appendPath("ComNaturezaProdutoPertenceA").build();
	}
 	
	
	
	
	
	public static String camposOrdenados() 
	{
		return " " + TABLE_NAME + "." + COLUNA_ID_OPORTUNIDADE_DIA  
		+ " , " + TABLE_NAME + "." + COLUNA_NOME_PRODUTO 
		+ " , " + TABLE_NAME + "." + COLUNA_URL_PRODUTO 
		+ " , " + TABLE_NAME + "." + COLUNA_URL_IMAGEM 
		+ " , " + TABLE_NAME + "." + COLUNA_PRECO_VENDA_ATUAL 
		+ " , " + TABLE_NAME + "." + COLUNA_PRECO_VENDA_ANTERIOR 
		+ " , " + TABLE_NAME + "." + COLUNA_PERCENTUAL_AJUSTE_VENDA 
		+ " , " + TABLE_NAME + "." + COLUNA_QUANTIDADE_PARCELA_ATUAL 
		+ " , " + TABLE_NAME + "." + COLUNA_PRECO_PARCELA_ATUAL 
		+ " , " + TABLE_NAME + "." + COLUNA_PRECO_PARCELA_ANTERIOR 
		+ " , " + TABLE_NAME + "." + COLUNA_QUANTIDADE_PARCELA_ANTERIOR 
		+ " , " + TABLE_NAME + "." + COLUNA_NOME_LOJA_VIRTUAL 
		+ " , " + TABLE_NAME + "." + COLUNA_DATA_ULTIMA_PRECO_ANTERIOR 
		+ " , " + TABLE_NAME + "." + COLUNA_DATA_INICIO_PRECO_ATUAL 
		+ " , " + TABLE_NAME + "." + COLUNA_PRECO_MEDIO 
		+ " , " + TABLE_NAME + "." + COLUNA_PRECO_MINIMO 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_NATUREZA_PRODUTO_PA 
		
		
		;
	}
	
	
	
	public static String camposOrdenadosSinc() 
	{
		return " oportunidade_dia_sinc.id_oportunidade_dia " 
		+ " ,oportunidade_dia_sinc.nome_produto " 
		+ " ,oportunidade_dia_sinc.url_produto " 
		+ " ,oportunidade_dia_sinc.url_imagem " 
		+ " ,oportunidade_dia_sinc.preco_venda_atual " 
		+ " ,oportunidade_dia_sinc.preco_venda_anterior " 
		+ " ,oportunidade_dia_sinc.percentual_ajuste_venda " 
		+ " ,oportunidade_dia_sinc.quantidade_parcela_atual " 
		+ " ,oportunidade_dia_sinc.preco_parcela_atual " 
		+ " ,oportunidade_dia_sinc.preco_parcela_anterior " 
		+ " ,oportunidade_dia_sinc.quantidade_parcela_anterior " 
		+ " ,oportunidade_dia_sinc.nome_loja_virtual " 
		+ " ,oportunidade_dia_sinc.data_ultima_preco_anterior " 
		+ " ,oportunidade_dia_sinc.data_inicio_preco_atual " 
		+ " ,oportunidade_dia_sinc.preco_medio " 
		+ " ,oportunidade_dia_sinc.preco_minimo " 
		+ " ,oportunidade_dia_sinc.id_natureza_produto_pa " 
		
		
		+ " ,oportunidade_dia_sinc.operacao_sinc "
		;
	}
	
	
	public static MontadorDaoComposite getMontadorComposto() {
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(getMontador(),null);
		return montador;
	}
	public static MontadorDaoI getMontador() {
		return new OportunidadeDiaMontador();
	}
	
	
	// ComChaveEstrangeira
  	
	public static Uri buildAllComNaturezaProdutoPertenceA() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(OportunidadeDiaContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComNaturezaProdutoPertenceA").build();
		return saida;
	}
	//  Recoloquei o metodo abaixo pq existe referencia no Provider
	public static Uri buildAllSemNaturezaProdutoPertenceA() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(OportunidadeDiaContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemNaturezaProdutoPertenceA").build();
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
	
	
	public static List<OportunidadeDia> converteLista(Cursor data) {
		return converteLista(data, getMontador());
	}
	public static List<OportunidadeDia> converteLista(Cursor data, MontadorDaoI montador) {
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