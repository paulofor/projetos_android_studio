
package br.com.lojadigicom.repcom.modelo;


import android.view.View;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import android.content.ContentValues;

import br.com.lojadigicom.repcom.framework.util.UtilDatas;
import br.com.lojadigicom.repcom.framework.log.DCLog;
import br.com.lojadigicom.repcom.modelo.agregado.ClienteAgregado;
import br.com.lojadigicom.repcom.data.contract.ClienteContract;

public class ClienteVo implements Cliente  {

	public ClienteVo() {
  	}
  	
  	public long getIdObj()
    {
       return idCliente;
    }

	 // ----- INICIO AGREGADO -----------------
	private ClienteAgregado agregado = null;
	private ClienteAgregado getAgregado() {
		if (agregado==null) {
			agregado = new ClienteAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idCliente;
	public long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(long _valor) {
		idCliente = _valor;
	}


	private String enderecoRua;
	public String getEnderecoRua() {
		return enderecoRua;
	}
	public void setEnderecoRua(String _valor) {
		enderecoRua = _valor;
	}


	private String enderecoNumero;
	public String getEnderecoNumero() {
		return enderecoNumero;
	}
	public void setEnderecoNumero(String _valor) {
		enderecoNumero = _valor;
	}


	private String enderecoComplemento;
	public String getEnderecoComplemento() {
		return enderecoComplemento;
	}
	public void setEnderecoComplemento(String _valor) {
		enderecoComplemento = _valor;
	}


	private String enderecoCep;
	public String getEnderecoCep() {
		return enderecoCep;
	}
	public void setEnderecoCep(String _valor) {
		enderecoCep = _valor;
	}


	private String enderecoBairro;
	public String getEnderecoBairro() {
		return enderecoBairro;
	}
	public void setEnderecoBairro(String _valor) {
		enderecoBairro = _valor;
	}


	private String enderecoCidade;
	public String getEnderecoCidade() {
		return enderecoCidade;
	}
	public void setEnderecoCidade(String _valor) {
		enderecoCidade = _valor;
	}


	private String enderecoUf;
	public String getEnderecoUf() {
		return enderecoUf;
	}
	public void setEnderecoUf(String _valor) {
		enderecoUf = _valor;
	}


	private String observacoes;
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String _valor) {
		observacoes = _valor;
	}


	private String codigoListaContato;
	public String getCodigoListaContato() {
		return codigoListaContato;
	}
	public void setCodigoListaContato(String _valor) {
		codigoListaContato = _valor;
	}


	private String nome;
	public String getNome() {
		return nome;
	}
	public void setNome(String _valor) {
		nome = _valor;
	}


	private boolean ativo;
	public boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(boolean _valor) {
		ativo = _valor;
	}

	
	private long idUsuarioS;
	public long getIdUsuarioS() {
		// relacionamento - Nao adianta pq na hora de gravar no banco pega o valor da variavel e nao desse 
		// metodo.
		//if (this.getUsuario_Sincroniza()!=null) 
		//	return getUsuario_Sincroniza().getIdObj();
		//else
			return idUsuarioS;
	}
	public void setIdUsuarioS(long _valor) {
		idUsuarioS = _valor;
	}





	private String operacaoSinc = null;

	public String getOperacaoSinc() {
		return operacaoSinc;
	}

	public void setOperacaoSinc(String valor) {
		operacaoSinc = valor;
	}

	/*
	public JSONObject JSonSinc() throws JSONException {
		JSONObject json = JSonSimples();
		json.put("OperacaoSinc", operacaoSinc);
		return json;
	}
	*/
	
	
	private boolean salvaPreliminar = false;

	public void setSalvaPreliminar(boolean valor) {
		salvaPreliminar = valor;
	}

	public boolean getSalvaPreliminar() {
		return salvaPreliminar;
	}
	
	// ComChaveEstrangeira
  	
	public long getIdUsuarioSLazyLoader() {
		return idUsuarioS;
	} 
		
	
	
	private boolean somenteMemoria = true;

	public boolean getSomenteMemoria() {
		return somenteMemoria;
	}

	public void setSomenteMemoria(boolean dado) {
		somenteMemoria = dado;
	} 
	
	
	// ------ AGREGADOS 2-------------------
  	// ComChaveEstrangeira
  	
		public Usuario getUsuario_Sincroniza() {
			return getAgregado().getUsuario_Sincroniza();
		}
		public void setUsuario_Sincroniza(Usuario item) {
			// Coloquei em 10-11-2016
			idUsuarioS = item.getIdObj();
			getAgregado().setUsuario_Sincroniza(item);
		}
		
		public void addListaUsuario_Sincroniza(Usuario item) {
			getAgregado().addListaUsuario_Sincroniza(item);
		}
		public Usuario getCorrenteUsuario_Sincroniza() {
			return getAgregado().getCorrenteUsuario_Sincroniza();
		}
		
		
	
	// SemChaveEstrangeira
	
		public ClienteInteresseCategoria getCorrenteClienteInteresseCategoria_Associada() {
			return getAgregado().getCorrenteClienteInteresseCategoria_Associada();
		}
		public void addListaClienteInteresseCategoria_Associada(ClienteInteresseCategoria item) {
			getAgregado().addListaClienteInteresseCategoria_Associada(item);
		}
		public List<ClienteInteresseCategoria> getListaClienteInteresseCategoria_Associada() {
			return getAgregado().getListaClienteInteresseCategoria_Associada();
		}
		public List<ClienteInteresseCategoria> getListaClienteInteresseCategoria_AssociadaOriginal() {
			return getAgregado().getListaClienteInteresseCategoria_AssociadaOriginal();
		}
		public List<ClienteInteresseCategoria> getListaClienteInteresseCategoria_Associada(int qtde) {
			return getAgregado().getListaClienteInteresseCategoria_Associada(qtde);
		}
		public void setListaClienteInteresseCategoria_Associada(List<ClienteInteresseCategoria> lista) {
			getAgregado().setListaClienteInteresseCategoria_Associada(lista);
		}
		public void setListaClienteInteresseCategoria_AssociadaByDao(List<ClienteInteresseCategoria> lista) {
			getAgregado().setListaClienteInteresseCategoria_AssociadaByDao(lista);
		}
		public void criaVaziaListaClienteInteresseCategoria_Associada() {
			getAgregado().criaVaziaListaClienteInteresseCategoria_Associada();
		}
		
		public boolean existeListaClienteInteresseCategoria_Associada() {
			return getAgregado().existeListaClienteInteresseCategoria_Associada();
		}
 		
		public ContatoCliente getCorrenteContatoCliente_Estabelece() {
			return getAgregado().getCorrenteContatoCliente_Estabelece();
		}
		public void addListaContatoCliente_Estabelece(ContatoCliente item) {
			getAgregado().addListaContatoCliente_Estabelece(item);
		}
		public List<ContatoCliente> getListaContatoCliente_Estabelece() {
			return getAgregado().getListaContatoCliente_Estabelece();
		}
		public List<ContatoCliente> getListaContatoCliente_EstabeleceOriginal() {
			return getAgregado().getListaContatoCliente_EstabeleceOriginal();
		}
		public List<ContatoCliente> getListaContatoCliente_Estabelece(int qtde) {
			return getAgregado().getListaContatoCliente_Estabelece(qtde);
		}
		public void setListaContatoCliente_Estabelece(List<ContatoCliente> lista) {
			getAgregado().setListaContatoCliente_Estabelece(lista);
		}
		public void setListaContatoCliente_EstabeleceByDao(List<ContatoCliente> lista) {
			getAgregado().setListaContatoCliente_EstabeleceByDao(lista);
		}
		public void criaVaziaListaContatoCliente_Estabelece() {
			getAgregado().criaVaziaListaContatoCliente_Estabelece();
		}
		
		public boolean existeListaContatoCliente_Estabelece() {
			return getAgregado().existeListaContatoCliente_Estabelece();
		}
 		
		public Venda getCorrenteVenda_Comprou() {
			return getAgregado().getCorrenteVenda_Comprou();
		}
		public void addListaVenda_Comprou(Venda item) {
			getAgregado().addListaVenda_Comprou(item);
		}
		public List<Venda> getListaVenda_Comprou() {
			return getAgregado().getListaVenda_Comprou();
		}
		public List<Venda> getListaVenda_ComprouOriginal() {
			return getAgregado().getListaVenda_ComprouOriginal();
		}
		public List<Venda> getListaVenda_Comprou(int qtde) {
			return getAgregado().getListaVenda_Comprou(qtde);
		}
		public void setListaVenda_Comprou(List<Venda> lista) {
			getAgregado().setListaVenda_Comprou(lista);
		}
		public void setListaVenda_ComprouByDao(List<Venda> lista) {
			getAgregado().setListaVenda_ComprouByDao(lista);
		}
		public void criaVaziaListaVenda_Comprou() {
			getAgregado().criaVaziaListaVenda_Comprou();
		}
		
		public boolean existeListaVenda_Comprou() {
			return getAgregado().existeListaVenda_Comprou();
		}
 		
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  	
  
	public ContentValues getContentValues() {
		ContentValues valores = new ContentValues();
	
    	valores.put(ClienteContract.COLUNA_ID_CLIENTE, idCliente);
    	valores.put(ClienteContract.COLUNA_ENDERECO_RUA, enderecoRua);
    	valores.put(ClienteContract.COLUNA_ENDERECO_NUMERO, enderecoNumero);
    	valores.put(ClienteContract.COLUNA_ENDERECO_COMPLEMENTO, enderecoComplemento);
    	valores.put(ClienteContract.COLUNA_ENDERECO_CEP, enderecoCep);
    	valores.put(ClienteContract.COLUNA_ENDERECO_BAIRRO, enderecoBairro);
    	valores.put(ClienteContract.COLUNA_ENDERECO_CIDADE, enderecoCidade);
    	valores.put(ClienteContract.COLUNA_ENDERECO_UF, enderecoUf);
    	valores.put(ClienteContract.COLUNA_OBSERVACOES, observacoes);
    	valores.put(ClienteContract.COLUNA_CODIGO_LISTA_CONTATO, codigoListaContato);
    	valores.put(ClienteContract.COLUNA_NOME, nome);
    	valores.put(ClienteContract.COLUNA_ATIVO, ativo);
		valores.put(ClienteContract.COLUNA_ID_USUARIO_S, idUsuarioS);
	
		return valores;
  	}
}