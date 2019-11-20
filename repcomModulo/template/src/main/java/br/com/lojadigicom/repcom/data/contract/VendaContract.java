

package  br.com.lojadigicom.repcom.data.contract;



import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.content.ContentUris;

import java.util.ArrayList;
import java.util.List;

import br.com.lojadigicom.repcom.framework.dao.DaoException;
import br.com.lojadigicom.repcom.framework.dao.DaoItemRetorno;
import br.com.lojadigicom.repcom.framework.dao.MontadorDaoI;
import br.com.lojadigicom.repcom.framework.data.MontadorDaoComposite;
import br.com.lojadigicom.repcom.framework.log.DCLog;
import br.com.lojadigicom.repcom.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.repcom.modelo.Venda;
import br.com.lojadigicom.repcom.modelo.montador.VendaMontador;


public final class VendaContract implements BaseColumns {
	
	
	private static AplicacaoContract aplicacaoContract;
	public static void setAplicacaoContract(AplicacaoContract valor) {
		aplicacaoContract = valor;
	}
	public static String getContentAuthority() {
		return aplicacaoContract.getContentAuthority();
	}
	
	
	public static final String PATH = "venda";
	public static final String COM_COMPLEMENTO = "ComComplemento";
	public static final String COM_RETIRADA = "ComRetirada";

	//public static final Uri CONTENT_URI = aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();

    //public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
    //public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;

    public static final String TABLE_NAME = "venda";
    public static final String TABLE_NAME_SINC = "venda_sinc";

	public static Uri getContentUri() {
		return aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();
	}
	public static String getContentType() {
		return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static String getContentItemType() {
		return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static Uri buildVendaUri(long id) {
    	return ContentUris.withAppendedId(getContentUri(), id);
    }
    
	public static Uri buildGetPorFeitaParaClienteUri(long id) {
    	Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(""+id).build();
		saida = saida.buildUpon().appendPath(ClienteContract.PATH).build();
    	return saida;
    }
	
	
	
    //public static final int COL_ID = 0;
   
    public static final String COLUNA_ID_VENDA = "id_venda";
    public static final int COL_ID_VENDA = 0;
    public static final String COLUNA_DATA = "data";
    public static final int COL_DATA = 1;
    public static final String COLUNA_VALOR_TOTAL = "valor_total";
    public static final int COL_VALOR_TOTAL = 2;
    public static final String COLUNA_VENDA_FECHADA = "venda_fechada";
    public static final int COL_VENDA_FECHADA = 3;
    public static final String COLUNA_ID_CLIENTE_FP = "id_cliente_fp";
    public static final int COL_ID_CLIENTE_FP = 4;
	
    public static final String COLUNA_ID_USUARIO_S = "id_usuario_s";
    public static final int COL_ID_USUARIO_S = 5;
	
	
 	public static final String COLUNA_CHAVE = COLUNA_ID_VENDA;
 	public static final String COLUNA_OPERACAO_SINC = "operacao_sinc";
 	public static final int COL_OPERACAO_SINC = 6;
	
	public static final String[] COLS = new String[] { 
			TABLE_NAME + "." + COLUNA_CHAVE
        	, TABLE_NAME + "." +COLUNA_DATA
        	, TABLE_NAME + "." +COLUNA_VALOR_TOTAL
        	, TABLE_NAME + "." +COLUNA_VENDA_FECHADA
			, TABLE_NAME + "." +COLUNA_ID_CLIENTE_FP
	
			, TABLE_NAME + "." +COLUNA_ID_USUARIO_S
	
    };
    
    public static final String[] COLS_SINC = new String[] { 
			TABLE_NAME_SINC + "." + COLUNA_CHAVE
        	, TABLE_NAME_SINC + "." +COLUNA_DATA
        	, TABLE_NAME_SINC + "." +COLUNA_VALOR_TOTAL
        	, TABLE_NAME_SINC + "." +COLUNA_VENDA_FECHADA
			, TABLE_NAME_SINC + "." +COLUNA_ID_CLIENTE_FP
	
			, TABLE_NAME_SINC + "." +COLUNA_ID_USUARIO_S
	
			, TABLE_NAME_SINC + "." + COLUNA_OPERACAO_SINC
    };
    
    private static VendaOperacao operacao = new VendaOperacao();
 	public static Uri buildAtualizaTotal() {
		return operacao.buildAtualizaTotal(getFiltro());
    }
    private static MontadorDaoI _montadorAtualizaTotal = null;
    public static MontadorDaoI getMontadorAtualizaTotal() {
    	return _montadorAtualizaTotal;
    }
    public static void setMontadorAtualizaTotal(MontadorDaoI montador) {
    	_montadorAtualizaTotal = montador;
    }
 	public static Uri buildCriaParcelamento() {
		return operacao.buildCriaParcelamento(getFiltro());
    }
    private static MontadorDaoI _montadorCriaParcelamento = null;
    public static MontadorDaoI getMontadorCriaParcelamento() {
    	return _montadorCriaParcelamento;
    }
    public static void setMontadorCriaParcelamento(MontadorDaoI montador) {
    	_montadorCriaParcelamento = montador;
    }
    
	private static VendaFiltro filtro = null;
	public static VendaFiltro getFiltro() {
		if (filtro==null) {
			filtro = new VendaFiltro();
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
	
	public static String innerJoinProdutoVenda_Associada() {
		return " inner join " + ProdutoVendaContract.TABLE_NAME + " on " + ProdutoVendaContract.TABLE_NAME + ".id_venda_a = " + TABLE_NAME + "." + COLUNA_CHAVE + " ";  
	}
	public static String innerJoinSincProdutoVenda_Associada() {
		return " inner join " + ProdutoVendaContract.TABLE_NAME_SINC + " on " + ProdutoVendaContract.TABLE_NAME_SINC + ".id_venda_a = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";  
	}
	public static String outerJoinProdutoVenda_Associada() {
		return " left outer join " + ProdutoVendaContract.TABLE_NAME + " on " + ProdutoVendaContract.TABLE_NAME + ".id_venda_a = " + TABLE_NAME + "." + COLUNA_CHAVE + " "; 
	}
	public static String outerJoinSincProdutoVenda_Associada() {
		return " left outer join " + ProdutoVendaContract.TABLE_NAME_SINC + " on " + ProdutoVendaContract.TABLE_NAME_SINC + ".id_venda_a = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";   
	}
	public static MontadorDaoI adicionaMontadorProdutoVendaAssociadaLista (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(ProdutoVendaContract.getMontador(), "ProdutoVenda_Associada");
		return montador;
	}
 	
	public static String innerJoinParcelaVenda_Possui() {
		return " inner join " + ParcelaVendaContract.TABLE_NAME + " on " + ParcelaVendaContract.TABLE_NAME + ".id_venda_pa = " + TABLE_NAME + "." + COLUNA_CHAVE + " ";  
	}
	public static String innerJoinSincParcelaVenda_Possui() {
		return " inner join " + ParcelaVendaContract.TABLE_NAME_SINC + " on " + ParcelaVendaContract.TABLE_NAME_SINC + ".id_venda_pa = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";  
	}
	public static String outerJoinParcelaVenda_Possui() {
		return " left outer join " + ParcelaVendaContract.TABLE_NAME + " on " + ParcelaVendaContract.TABLE_NAME + ".id_venda_pa = " + TABLE_NAME + "." + COLUNA_CHAVE + " "; 
	}
	public static String outerJoinSincParcelaVenda_Possui() {
		return " left outer join " + ParcelaVendaContract.TABLE_NAME_SINC + " on " + ParcelaVendaContract.TABLE_NAME_SINC + ".id_venda_pa = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";   
	}
	public static MontadorDaoI adicionaMontadorParcelaVendaPossuiLista (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(ParcelaVendaContract.getMontador(), "ParcelaVenda_Possui");
		return montador;
	}
 	
	// Com chave
	
	
	public static String innerJoinCliente_FeitaPara() {
		return " inner join " + ClienteContract.TABLE_NAME + " on " + ClienteContract.TABLE_NAME + "." + ClienteContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_CLIENTE_FP + " ";  
	}
	public static String innerJoinSincCliente_FeitaPara() {
		return " inner join " + ClienteContract.TABLE_NAME_SINC + " on " + ClienteContract.TABLE_NAME_SINC + "." + ClienteContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_CLIENTE_FP + " ";  
	}
	public static String outerJoinCliente_FeitaPara() {
		return " left outer join " + ClienteContract.TABLE_NAME + " on " + ClienteContract.TABLE_NAME + "." + ClienteContract.COLUNA_CHAVE + " = " + TABLE_NAME + "." + COLUNA_ID_CLIENTE_FP + " "; 
	}
	public static String outerJoinSincCliente_FeitaPara() {
		return " left outer join " + ClienteContract.TABLE_NAME_SINC + " on " + ClienteContract.TABLE_NAME_SINC + "." + ClienteContract.COLUNA_CHAVE + " = " + TABLE_NAME_SINC + "." + COLUNA_ID_CLIENTE_FP + " ";   
	}
	
	
	public static MontadorDaoI adicionaMontadorClienteFeitaPara (MontadorDaoI montador) {
		((MontadorDaoComposite)montador).adicionaMontador(ClienteContract.getMontador(), "Cliente_FeitaPara");
		return montador;
	}
	public static Uri adicionaClienteFeitaPara(Uri uri) {
		return uri.buildUpon().appendPath("ComClienteFeitaPara").build();
	}
 	
	
	
	
	
	public static String camposOrdenados() 
	{
		return " " + TABLE_NAME + "." + COLUNA_ID_VENDA  
		+ " , " + TABLE_NAME + "." + COLUNA_DATA 
		+ " , " + TABLE_NAME + "." + COLUNA_VALOR_TOTAL 
		+ " , " + TABLE_NAME + "." + COLUNA_VENDA_FECHADA 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_CLIENTE_FP 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_USUARIO_S 
		
		
		;
	}
	
	
	
	public static String camposOrdenadosSinc() 
	{
		return " venda_sinc.id_venda " 
		+ " ,venda_sinc.data " 
		+ " ,venda_sinc.valor_total " 
		+ " ,venda_sinc.venda_fechada " 
		+ " ,venda_sinc.id_cliente_fp " 
		+ " ,venda_sinc.id_usuario_s " 
		
		
		+ " ,venda_sinc.operacao_sinc "
		;
	}
	
	
	public static MontadorDaoComposite getMontadorComposto() {
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(getMontador(),null);
		return montador;
	}
	public static MontadorDaoI getMontador() {
		return new VendaMontador();
	}
	
	
	// ComChaveEstrangeira
  	
	public static Uri buildAllComClienteFeitaPara() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(VendaContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComClienteFeitaPara").build();
		return saida;
	}
	//  Recoloquei o metodo abaixo pq existe referencia no Provider
	public static Uri buildAllSemClienteFeitaPara() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(VendaContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemClienteFeitaPara").build();
		return saida;
	}
	
	
	public static Uri buildAllComUsuarioSincroniza() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(VendaContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComUsuarioSincroniza").build();
		return saida;
	}
	//  Recoloquei o metodo abaixo pq existe referencia no Provider
	public static Uri buildAllSemUsuarioSincroniza() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(VendaContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemUsuarioSincroniza").build();
		return saida;
	}
	
	
	
	// SemChaveEstrangeira
  	
	
	public static Uri buildAllComProdutoVendaAssociada() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(VendaContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComProdutoVendaAssociada").build();
		return saida;
	}	
	public static Uri buildAllSemProdutoVendaAssociada() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(VendaContract.COM_RETIRADA).build();
		saida = saida.buildUpon().appendPath("SemProdutoVendaAssociada").build();
		return saida;
	}	
	
	
	
	public static Uri buildAllComParcelaVendaPossui() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(VendaContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComParcelaVendaPossui").build();
		return saida;
	}	
	public static Uri buildAllSemParcelaVendaPossui() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(VendaContract.COM_RETIRADA).build();
		saida = saida.buildUpon().appendPath("SemParcelaVendaPossui").build();
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
	
	
	public static List<Venda> converteLista(Cursor data) {
		return converteLista(data, getMontador());
	}
	public static List<Venda> converteLista(Cursor data, MontadorDaoI montador) {
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