package repcom.modelo.vo;

import android.view.View;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import br.com.digicom.log.DCLog;
import br.com.digicom.modelo.ObjetoSinc;
import br.com.digicom.util.ConversorJson;
import br.com.digicom.activity.DigicomContexto;
import repcom.modelo.*;
import repcom.modelo.agregado.ClienteAgregado;
import repcom.modelo.derivada.impl.ClienteDerivada;
import repcom.modelo.display.impl.ClienteDisplay;

public class ClienteVo implements Cliente , ObjetoSinc{ 
  
  
  	public long getIdObj()
    {
       return idCliente;
    }
  
  
  	private DigicomContexto _contexto = null;
    public DigicomContexto getContexto() {
    	if (_contexto==null) 
    		throw new RuntimeException("DigicomContexto n?o inicializado");
    	return _contexto;
    }
  	public void setContexto(DigicomContexto ctx) {
  		_contexto = ctx;
  	}
  	
  
  	public ClienteVo() {
  	}
   	// -----  Inicio JSON -----
  	private String JsonAtributosOld() {
		return 
		" \"IdCliente\" : \"" + idCliente + "\" "
		+ ", \"EnderecoRua\" : \"" + enderecoRua + "\" "
		+ ", \"EnderecoNumero\" : \"" + enderecoNumero + "\" "
		+ ", \"EnderecoComplemento\" : \"" + enderecoComplemento + "\" "
		+ ", \"EnderecoCep\" : \"" + enderecoCep + "\" "
		+ ", \"EnderecoBairro\" : \"" + enderecoBairro + "\" "
		+ ", \"EnderecoCidade\" : \"" + enderecoCidade + "\" "
		+ ", \"EnderecoUf\" : \"" + enderecoUf + "\" "
		+ ", \"Observacoes\" : \"" + observacoes + "\" "
		+ ", \"CodigoListaContato\" : \"" + codigoListaContato + "\" "
		+ ", \"Nome\" : \"" + nome + "\" "
		+ ", \"Ativo\" : \"" + ativo + "\" "
		+ ", \"IdUsuarioS\" : \"" + idUsuarioS + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdCliente",idCliente);
			json.put("EnderecoRua",enderecoRua);
			json.put("EnderecoNumero",enderecoNumero);
			json.put("EnderecoComplemento",enderecoComplemento);
			json.put("EnderecoCep",enderecoCep);
			json.put("EnderecoBairro",enderecoBairro);
			json.put("EnderecoCidade",enderecoCidade);
			json.put("EnderecoUf",enderecoUf);
			json.put("Observacoes",observacoes);
			json.put("CodigoListaContato",codigoListaContato);
			json.put("Nome",nome);
			json.put("Ativo",ativo);
			json.put("IdUsuarioS",idUsuarioS);
		
		//} catch (JSONException e) {
		//	DCLog.e("JSONTag", this, e);
		//}
		return json;
	}

	public JSONObject JSonSimples() throws JSONException{
		return JSonAtributos();
	}

	// apagar em 04-05-2015
	/**
 	* @deprecated  Substituir por JSonSimples()
 	*/
	@Deprecated
	public JSONObject JSon() throws JSONException{
		JSONObject json = JSonAtributos();
		//try {
		JSONArray listaClienteInteresseCategoria_Associada = JSonListaClienteInteresseCategoria_Associada();
		if (listaClienteInteresseCategoria_Associada!=null) {
			json.put("ListaClienteInteresseCategoriaVo_Associada",listaClienteInteresseCategoria_Associada);
		} 
		JSONArray listaContatoCliente_Estabelece = JSonListaContatoCliente_Estabelece();
		if (listaContatoCliente_Estabelece!=null) {
			json.put("ListaContatoClienteVo_Estabelece",listaContatoCliente_Estabelece);
		} 
		JSONArray listaVenda_Comprou = JSonListaVenda_Comprou();
		if (listaVenda_Comprou!=null) {
			json.put("ListaVendaVo_Comprou",listaVenda_Comprou);
		} 
	
		//} catch (JSONException e) {
		//	DCLog.e("JSONTag", this, e);
		//}
		return json;
	}


	// SemChaveEstrangeira
	
	private JSONArray JSonListaClienteInteresseCategoria_Associada() throws JSONException{
		if (getListaClienteInteresseCategoria_Associada()==null) return null;
		JSONArray lista = new JSONArray();
		for (ClienteInteresseCategoria item:this.getListaClienteInteresseCategoria_AssociadaOriginal()) {
			lista.put(((ObjetoSinc)item).JSonSinc());
			//lista.put(item.JSon());
		}
		return lista;
	}
	
	private JSONArray JSonListaContatoCliente_Estabelece() throws JSONException{
		if (getListaContatoCliente_Estabelece()==null) return null;
		JSONArray lista = new JSONArray();
		for (ContatoCliente item:this.getListaContatoCliente_EstabeleceOriginal()) {
			lista.put(((ObjetoSinc)item).JSonSinc());
			//lista.put(item.JSon());
		}
		return lista;
	}
	
	private JSONArray JSonListaVenda_Comprou() throws JSONException{
		if (getListaVenda_Comprou()==null) return null;
		JSONArray lista = new JSONArray();
		for (Venda item:this.getListaVenda_ComprouOriginal()) {
			lista.put(((ObjetoSinc)item).JSonSinc());
			//lista.put(item.JSon());
		}
		return lista;
	}
	
  	// -----  Final JSon -----------
 
 
 	public String debug() {
		return 
		 " IdCliente=" + getIdCliente() 
		+  " EnderecoRua=" + getEnderecoRua() 
		+  " EnderecoNumero=" + getEnderecoNumero() 
		+  " EnderecoComplemento=" + getEnderecoComplemento() 
		+  " EnderecoCep=" + getEnderecoCep() 
		+  " EnderecoBairro=" + getEnderecoBairro() 
		+  " EnderecoCidade=" + getEnderecoCidade() 
		+  " EnderecoUf=" + getEnderecoUf() 
		+  " Observacoes=" + getObservacoes() 
		+  " CodigoListaContato=" + getCodigoListaContato() 
		+  " Nome=" + getNome() 
		+  " Ativo=" + getAtivo() 
		+  " IdUsuarioS=" + getIdUsuarioS() 
		
	;
	}
 
 
 	// ---------  Metodos DCIObjeto ----------------
 	
 	public long getId() {
 		return idCliente;
 	}
 	public String getNomeClasse() {
 		return "Cliente";
 	}
 	// ---------------------------------------------
 
 
    // ----- INICIO DISPLAY -----------------
	private ClienteDisplay display = null;
	private ClienteDisplay getObjetoDisplay() {
		if (display==null) {
			display = new ClienteDisplay(this);
		}
		return display;
	}
	@Override
	public View getItemListaView() {
		return getObjetoDisplay().getItemListaView();
	}
	@Override
	public String getItemListaTexto() {
		return getObjetoDisplay().getItemListaTexto();
	}
	// ----- FINAL DISPLAY -----------------
 
    // ----- INICIO AGREGADO -----------------
	private ClienteAgregado agregado = null;
	private ClienteAgregado getAgregado() {
		if (agregado==null) {
			agregado = new ClienteAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------
	
	// ----- INICIO DERIVADA -----------------
	private ClienteDerivada derivada = null;
	private ClienteDerivada getDerivada() {
		if (derivada==null) {
			derivada = new ClienteDerivada(this);
		}
		return derivada;
	}
	public String getTituloTela()
	{
		return getDerivada().getTituloTela(); 
	} 
	
	public String getTelefoneAgenda()
	{
		return getDerivada().getTelefoneAgenda(); 
	} 
	public void setTelefoneAgenda(String value)
	{
		getDerivada().setTelefoneAgenda(value); 
	} 
	
	
	// ----- FINAL DERIVADA -----------------
	
	
	
 
  
  
  	// ------ AGREGADOS 2-------------------
  	// ComChaveEstrangeira
  	
		public Usuario getUsuario_Sincroniza() {
			return getAgregado().getUsuario_Sincroniza();
		}
		public void setUsuario_Sincroniza(Usuario item) {
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
  
  
  	public ClienteVo(JSONObject json) throws JSONException{
		idCliente =  ConversorJson.getInteger(json, "IdCliente");
		enderecoRua =  ConversorJson.getString(json, "EnderecoRua");
		enderecoNumero =  ConversorJson.getString(json, "EnderecoNumero");
		enderecoComplemento =  ConversorJson.getString(json, "EnderecoComplemento");
		enderecoCep =  ConversorJson.getString(json, "EnderecoCep");
		enderecoBairro =  ConversorJson.getString(json, "EnderecoBairro");
		enderecoCidade =  ConversorJson.getString(json, "EnderecoCidade");
		enderecoUf =  ConversorJson.getString(json, "EnderecoUf");
		observacoes =  ConversorJson.getString(json, "Observacoes");
		codigoListaContato =  ConversorJson.getString(json, "CodigoListaContato");
		nome =  ConversorJson.getString(json, "Nome");
		ativo =  ConversorJson.getLogic(json, "Ativo");
		idUsuarioS =  ConversorJson.getInteger(json, "IdUsuarioS");
  	}
  	public String toString() {
  		return "" + idCliente;
  	}
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
		// relacionamento
		if (idUsuarioS==0 && this.getUsuario_Sincroniza()!=null) 
			return getUsuario_Sincroniza().getId();
		else
			return idUsuarioS;
	}
	public void setIdUsuarioS(long _valor) {
		idUsuarioS = _valor;
	}





	private String operacaoSinc = null;
	@Override
	public String getOperacaoSinc() {
		return operacaoSinc;
	}
	@Override
	public void setOperacaoSinc(String valor) {
		operacaoSinc = valor;
	}
	@Override
	public JSONObject JSonSinc() throws JSONException {
		JSONObject json = JSonSimples();
		json.put("OperacaoSinc", operacaoSinc);
		return json;
	}
	
	
	private boolean salvaPreliminar = false;
	@Override
	public void setSalvaPreliminar(boolean valor) {
		salvaPreliminar = valor;
	}
	@Override
	public boolean getSalvaPreliminar() {
		return salvaPreliminar;
	}
	
	// ComChaveEstrangeira
  	
	public long getIdUsuarioSLazyLoader() {
		return idUsuarioS;
	} 
		
}