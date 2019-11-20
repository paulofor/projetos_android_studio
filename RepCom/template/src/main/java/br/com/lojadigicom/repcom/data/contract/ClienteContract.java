

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
import br.com.lojadigicom.repcom.modelo.Cliente;
import br.com.lojadigicom.repcom.modelo.montador.ClienteMontador;


public final class ClienteContract implements BaseColumns {
	
	
	private static AplicacaoContract aplicacaoContract;
	public static void setAplicacaoContract(AplicacaoContract valor) {
		aplicacaoContract = valor;
	}
	public static String getContentAuthority() {
		return aplicacaoContract.getContentAuthority();
	}
	
	
	public static final String PATH = "cliente";
	public static final String COM_COMPLEMENTO = "ComComplemento";
	public static final String COM_RETIRADA = "ComRetirada";

	//public static final Uri CONTENT_URI = aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();

    //public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
    //public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;

    public static final String TABLE_NAME = "cliente";
    public static final String TABLE_NAME_SINC = "cliente_sinc";

	public static Uri getContentUri() {
		return aplicacaoContract.getBaseContentUri().buildUpon().appendPath(PATH).build();
	}
	public static String getContentType() {
		return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static String getContentItemType() {
		return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + aplicacaoContract.getContentAuthority() + "/" + PATH;
	}
	public static Uri buildClienteUri(long id) {
    	return ContentUris.withAppendedId(getContentUri(), id);
    }
    
	
	
    //public static final int COL_ID = 0;
   
    public static final String COLUNA_ID_CLIENTE = "id_cliente";
    public static final int COL_ID_CLIENTE = 0;
    public static final String COLUNA_ENDERECO_RUA = "endereco_rua";
    public static final int COL_ENDERECO_RUA = 1;
    public static final String COLUNA_ENDERECO_NUMERO = "endereco_numero";
    public static final int COL_ENDERECO_NUMERO = 2;
    public static final String COLUNA_ENDERECO_COMPLEMENTO = "endereco_complemento";
    public static final int COL_ENDERECO_COMPLEMENTO = 3;
    public static final String COLUNA_ENDERECO_CEP = "endereco_cep";
    public static final int COL_ENDERECO_CEP = 4;
    public static final String COLUNA_ENDERECO_BAIRRO = "endereco_bairro";
    public static final int COL_ENDERECO_BAIRRO = 5;
    public static final String COLUNA_ENDERECO_CIDADE = "endereco_cidade";
    public static final int COL_ENDERECO_CIDADE = 6;
    public static final String COLUNA_ENDERECO_UF = "endereco_uf";
    public static final int COL_ENDERECO_UF = 7;
    public static final String COLUNA_OBSERVACOES = "observacoes";
    public static final int COL_OBSERVACOES = 8;
    public static final String COLUNA_CODIGO_LISTA_CONTATO = "codigo_lista_contato";
    public static final int COL_CODIGO_LISTA_CONTATO = 9;
    public static final String COLUNA_NOME = "nome";
    public static final int COL_NOME = 10;
    public static final String COLUNA_ATIVO = "ativo";
    public static final int COL_ATIVO = 11;
    public static final String COLUNA_ID_USUARIO_S = "id_usuario_s";
    public static final int COL_ID_USUARIO_S = 12;
	
	
 	public static final String COLUNA_CHAVE = COLUNA_ID_CLIENTE;
 	public static final String COLUNA_OPERACAO_SINC = "operacao_sinc";
 	public static final int COL_OPERACAO_SINC = 13;
	
	public static final String[] COLS = new String[] { 
			TABLE_NAME + "." + COLUNA_CHAVE
        	, TABLE_NAME + "." +COLUNA_ENDERECO_RUA
        	, TABLE_NAME + "." +COLUNA_ENDERECO_NUMERO
        	, TABLE_NAME + "." +COLUNA_ENDERECO_COMPLEMENTO
        	, TABLE_NAME + "." +COLUNA_ENDERECO_CEP
        	, TABLE_NAME + "." +COLUNA_ENDERECO_BAIRRO
        	, TABLE_NAME + "." +COLUNA_ENDERECO_CIDADE
        	, TABLE_NAME + "." +COLUNA_ENDERECO_UF
        	, TABLE_NAME + "." +COLUNA_OBSERVACOES
        	, TABLE_NAME + "." +COLUNA_CODIGO_LISTA_CONTATO
        	, TABLE_NAME + "." +COLUNA_NOME
        	, TABLE_NAME + "." +COLUNA_ATIVO
			, TABLE_NAME + "." +COLUNA_ID_USUARIO_S
	
    };
    
    public static final String[] COLS_SINC = new String[] { 
			TABLE_NAME_SINC + "." + COLUNA_CHAVE
        	, TABLE_NAME_SINC + "." +COLUNA_ENDERECO_RUA
        	, TABLE_NAME_SINC + "." +COLUNA_ENDERECO_NUMERO
        	, TABLE_NAME_SINC + "." +COLUNA_ENDERECO_COMPLEMENTO
        	, TABLE_NAME_SINC + "." +COLUNA_ENDERECO_CEP
        	, TABLE_NAME_SINC + "." +COLUNA_ENDERECO_BAIRRO
        	, TABLE_NAME_SINC + "." +COLUNA_ENDERECO_CIDADE
        	, TABLE_NAME_SINC + "." +COLUNA_ENDERECO_UF
        	, TABLE_NAME_SINC + "." +COLUNA_OBSERVACOES
        	, TABLE_NAME_SINC + "." +COLUNA_CODIGO_LISTA_CONTATO
        	, TABLE_NAME_SINC + "." +COLUNA_NOME
        	, TABLE_NAME_SINC + "." +COLUNA_ATIVO
			, TABLE_NAME_SINC + "." +COLUNA_ID_USUARIO_S
	
			, TABLE_NAME_SINC + "." + COLUNA_OPERACAO_SINC
    };
    
    private static ClienteOperacao operacao = new ClienteOperacao();
 	public static Uri buildObtemListaAgendaTel() {
		return operacao.buildObtemListaAgendaTel(getFiltro());
    }
    private static MontadorDaoI _montadorObtemListaAgendaTel = null;
    public static MontadorDaoI getMontadorObtemListaAgendaTel() {
    	return _montadorObtemListaAgendaTel;
    }
    public static void setMontadorObtemListaAgendaTel(MontadorDaoI montador) {
    	_montadorObtemListaAgendaTel = montador;
    }
 	public static Uri buildSincronizaAgendaTel() {
		return operacao.buildSincronizaAgendaTel(getFiltro());
    }
    private static MontadorDaoI _montadorSincronizaAgendaTel = null;
    public static MontadorDaoI getMontadorSincronizaAgendaTel() {
    	return _montadorSincronizaAgendaTel;
    }
    public static void setMontadorSincronizaAgendaTel(MontadorDaoI montador) {
    	_montadorSincronizaAgendaTel = montador;
    }
 	public static Uri buildObtemPorIdAgendaTel() {
		return operacao.buildObtemPorIdAgendaTel(getFiltro());
    }
    private static MontadorDaoI _montadorObtemPorIdAgendaTel = null;
    public static MontadorDaoI getMontadorObtemPorIdAgendaTel() {
    	return _montadorObtemPorIdAgendaTel;
    }
    public static void setMontadorObtemPorIdAgendaTel(MontadorDaoI montador) {
    	_montadorObtemPorIdAgendaTel = montador;
    }
 	public static Uri buildPreencheDerivadaAgendaTel() {
		return operacao.buildPreencheDerivadaAgendaTel(getFiltro());
    }
    private static MontadorDaoI _montadorPreencheDerivadaAgendaTel = null;
    public static MontadorDaoI getMontadorPreencheDerivadaAgendaTel() {
    	return _montadorPreencheDerivadaAgendaTel;
    }
    public static void setMontadorPreencheDerivadaAgendaTel(MontadorDaoI montador) {
    	_montadorPreencheDerivadaAgendaTel = montador;
    }
 	public static Uri buildDesativaPorId() {
		return operacao.buildDesativaPorId(getFiltro());
    }
    private static MontadorDaoI _montadorDesativaPorId = null;
    public static MontadorDaoI getMontadorDesativaPorId() {
    	return _montadorDesativaPorId;
    }
    public static void setMontadorDesativaPorId(MontadorDaoI montador) {
    	_montadorDesativaPorId = montador;
    }
 	public static Uri buildListaAtivos() {
		return operacao.buildListaAtivos(getFiltro());
    }
    private static MontadorDaoI _montadorListaAtivos = null;
    public static MontadorDaoI getMontadorListaAtivos() {
    	return _montadorListaAtivos;
    }
    public static void setMontadorListaAtivos(MontadorDaoI montador) {
    	_montadorListaAtivos = montador;
    }
 	public static Uri buildPesquisaTrechoNome() {
		return operacao.buildPesquisaTrechoNome(getFiltro());
    }
    private static MontadorDaoI _montadorPesquisaTrechoNome = null;
    public static MontadorDaoI getMontadorPesquisaTrechoNome() {
    	return _montadorPesquisaTrechoNome;
    }
    public static void setMontadorPesquisaTrechoNome(MontadorDaoI montador) {
    	_montadorPesquisaTrechoNome = montador;
    }
    
	private static ClienteFiltro filtro = null;
	public static ClienteFiltro getFiltro() {
		if (filtro==null) {
			filtro = new ClienteFiltro();
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
	
	public static String innerJoinClienteInteresseCategoria_Associada() {
		return " inner join " + ClienteInteresseCategoriaContract.TABLE_NAME + " on " + ClienteInteresseCategoriaContract.TABLE_NAME + ".id_cliente_a = " + TABLE_NAME + "." + COLUNA_CHAVE + " ";  
	}
	public static String innerJoinSincClienteInteresseCategoria_Associada() {
		return " inner join " + ClienteInteresseCategoriaContract.TABLE_NAME_SINC + " on " + ClienteInteresseCategoriaContract.TABLE_NAME_SINC + ".id_cliente_a = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";  
	}
	public static String outerJoinClienteInteresseCategoria_Associada() {
		return " left outer join " + ClienteInteresseCategoriaContract.TABLE_NAME + " on " + ClienteInteresseCategoriaContract.TABLE_NAME + ".id_cliente_a = " + TABLE_NAME + "." + COLUNA_CHAVE + " "; 
	}
	public static String outerJoinSincClienteInteresseCategoria_Associada() {
		return " left outer join " + ClienteInteresseCategoriaContract.TABLE_NAME_SINC + " on " + ClienteInteresseCategoriaContract.TABLE_NAME_SINC + ".id_cliente_a = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";   
	}
 	
	public static String innerJoinContatoCliente_Estabelece() {
		return " inner join " + ContatoClienteContract.TABLE_NAME + " on " + ContatoClienteContract.TABLE_NAME + ".id_cliente_ra = " + TABLE_NAME + "." + COLUNA_CHAVE + " ";  
	}
	public static String innerJoinSincContatoCliente_Estabelece() {
		return " inner join " + ContatoClienteContract.TABLE_NAME_SINC + " on " + ContatoClienteContract.TABLE_NAME_SINC + ".id_cliente_ra = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";  
	}
	public static String outerJoinContatoCliente_Estabelece() {
		return " left outer join " + ContatoClienteContract.TABLE_NAME + " on " + ContatoClienteContract.TABLE_NAME + ".id_cliente_ra = " + TABLE_NAME + "." + COLUNA_CHAVE + " "; 
	}
	public static String outerJoinSincContatoCliente_Estabelece() {
		return " left outer join " + ContatoClienteContract.TABLE_NAME_SINC + " on " + ContatoClienteContract.TABLE_NAME_SINC + ".id_cliente_ra = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";   
	}
 	
	public static String innerJoinVenda_Comprou() {
		return " inner join " + VendaContract.TABLE_NAME + " on " + VendaContract.TABLE_NAME + ".id_cliente_fp = " + TABLE_NAME + "." + COLUNA_CHAVE + " ";  
	}
	public static String innerJoinSincVenda_Comprou() {
		return " inner join " + VendaContract.TABLE_NAME_SINC + " on " + VendaContract.TABLE_NAME_SINC + ".id_cliente_fp = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";  
	}
	public static String outerJoinVenda_Comprou() {
		return " left outer join " + VendaContract.TABLE_NAME + " on " + VendaContract.TABLE_NAME + ".id_cliente_fp = " + TABLE_NAME + "." + COLUNA_CHAVE + " "; 
	}
	public static String outerJoinSincVenda_Comprou() {
		return " left outer join " + VendaContract.TABLE_NAME_SINC + " on " + VendaContract.TABLE_NAME_SINC + ".id_cliente_fp = " + TABLE_NAME_SINC + "." + COLUNA_CHAVE + " ";   
	}
 	
	// Com chave
	
	
	
	
	
	public static String camposOrdenados() 
	{
		return " " + TABLE_NAME + "." + COLUNA_ID_CLIENTE  
		+ " , " + TABLE_NAME + "." + COLUNA_ENDERECO_RUA 
		+ " , " + TABLE_NAME + "." + COLUNA_ENDERECO_NUMERO 
		+ " , " + TABLE_NAME + "." + COLUNA_ENDERECO_COMPLEMENTO 
		+ " , " + TABLE_NAME + "." + COLUNA_ENDERECO_CEP 
		+ " , " + TABLE_NAME + "." + COLUNA_ENDERECO_BAIRRO 
		+ " , " + TABLE_NAME + "." + COLUNA_ENDERECO_CIDADE 
		+ " , " + TABLE_NAME + "." + COLUNA_ENDERECO_UF 
		+ " , " + TABLE_NAME + "." + COLUNA_OBSERVACOES 
		+ " , " + TABLE_NAME + "." + COLUNA_CODIGO_LISTA_CONTATO 
		+ " , " + TABLE_NAME + "." + COLUNA_NOME 
		+ " , " + TABLE_NAME + "." + COLUNA_ATIVO 
		+ " , " + TABLE_NAME + "." + COLUNA_ID_USUARIO_S 
		
		
		;
	}
	
	
	
	public static String camposOrdenadosSinc() 
	{
		return " cliente_sinc.id_cliente " 
		+ " ,cliente_sinc.endereco_rua " 
		+ " ,cliente_sinc.endereco_numero " 
		+ " ,cliente_sinc.endereco_complemento " 
		+ " ,cliente_sinc.endereco_cep " 
		+ " ,cliente_sinc.endereco_bairro " 
		+ " ,cliente_sinc.endereco_cidade " 
		+ " ,cliente_sinc.endereco_uf " 
		+ " ,cliente_sinc.observacoes " 
		+ " ,cliente_sinc.codigo_lista_contato " 
		+ " ,cliente_sinc.nome " 
		+ " ,cliente_sinc.ativo " 
		+ " ,cliente_sinc.id_usuario_s " 
		
		
		+ " ,cliente_sinc.operacao_sinc "
		;
	}
	
	
	public static MontadorDaoComposite getMontadorComposto() {
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(getMontador(),null);
		return montador;
	}
	public static MontadorDaoI getMontador() {
		return new ClienteMontador();
	}
	
	
	// ComChaveEstrangeira
  	
	public static Uri buildAllComUsuarioSincroniza() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ClienteContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComUsuarioSincroniza").build();
		return saida;
	}
	/*
	public static Uri buildAllSemUsuarioSincroniza() {
		Uri saida = CONTENT_URI;
		saida = saida.buildUpon().appendPath(ClienteContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("SemUsuarioSincroniza").build();
		return saida;
	}
	*/	
	
	
	// SemChaveEstrangeira
  	
	
	public static Uri buildAllComClienteInteresseCategoriaAssociada() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ClienteContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComClienteInteresseCategoriaAssociada").build();
		return saida;
	}	
	public static Uri buildAllSemClienteInteresseCategoriaAssociada() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ClienteContract.COM_RETIRADA).build();
		saida = saida.buildUpon().appendPath("SemClienteInteresseCategoriaAssociada").build();
		return saida;
	}	
	
	
	
	public static Uri buildAllComContatoClienteEstabelece() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ClienteContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComContatoClienteEstabelece").build();
		return saida;
	}	
	public static Uri buildAllSemContatoClienteEstabelece() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ClienteContract.COM_RETIRADA).build();
		saida = saida.buildUpon().appendPath("SemContatoClienteEstabelece").build();
		return saida;
	}	
	
	
	
	public static Uri buildAllComVendaComprou() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ClienteContract.COM_COMPLEMENTO).build();
		saida = saida.buildUpon().appendPath("ComVendaComprou").build();
		return saida;
	}	
	public static Uri buildAllSemVendaComprou() {
		Uri saida = getContentUri();
		saida = saida.buildUpon().appendPath(ClienteContract.COM_RETIRADA).build();
		saida = saida.buildUpon().appendPath("SemVendaComprou").build();
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
	
	
	public static List<Cliente> converteLista(Cursor data) {
		return converteLista(data, getMontador());
	}
	public static List<Cliente> converteLista(Cursor data, MontadorDaoI montador) {
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