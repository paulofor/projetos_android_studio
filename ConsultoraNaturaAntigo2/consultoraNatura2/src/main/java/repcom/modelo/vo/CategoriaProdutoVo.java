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
import repcom.modelo.agregado.CategoriaProdutoAgregado;
import repcom.modelo.derivada.impl.CategoriaProdutoDerivada;
import repcom.modelo.display.impl.CategoriaProdutoDisplay;

public class CategoriaProdutoVo implements CategoriaProduto , ObjetoSinc{ 
  
  
  	public long getIdObj()
    {
       return idCategoriaProduto;
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
  	
  
  	public CategoriaProdutoVo() {
  	}
   	// -----  Inicio JSON -----
  	private String JsonAtributosOld() {
		return 
		" \"IdCategoriaProduto\" : \"" + idCategoriaProduto + "\" "
		+ ", \"Nome\" : \"" + nome + "\" "
		+ ", \"Url\" : \"" + url + "\" "
		+ ", \"CodigoLineId\" : \"" + codigoLineId + "\" "
		+ ", \"DataInclusao\" : \"" + dataInclusao + "\" "
		+ ", \"IdCategoriaProdutoP\" : \"" + idCategoriaProdutoP + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdCategoriaProduto",idCategoriaProduto);
			json.put("Nome",nome);
			json.put("Url",url);
			json.put("CodigoLineId",codigoLineId);
			json.put("DataInclusao",dataInclusao);
			json.put("IdCategoriaProdutoP",idCategoriaProdutoP);
		
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
		JSONArray listaCategoriaProdutoProduto_Possui = JSonListaCategoriaProdutoProduto_Possui();
		if (listaCategoriaProdutoProduto_Possui!=null) {
			json.put("ListaCategoriaProdutoProdutoVo_Possui",listaCategoriaProdutoProduto_Possui);
		} 
		JSONArray listaCategoriaProduto_Pai = JSonListaCategoriaProduto_Pai();
		if (listaCategoriaProduto_Pai!=null) {
			json.put("ListaCategoriaProdutoVo_Pai",listaCategoriaProduto_Pai);
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
	
	private JSONArray JSonListaCategoriaProdutoProduto_Possui() throws JSONException{
		if (getListaCategoriaProdutoProduto_Possui()==null) return null;
		JSONArray lista = new JSONArray();
		for (CategoriaProdutoProduto item:this.getListaCategoriaProdutoProduto_PossuiOriginal()) {
			lista.put(((ObjetoSinc)item).JSonSinc());
			//lista.put(item.JSon());
		}
		return lista;
	}
	
	private JSONArray JSonListaCategoriaProduto_Pai() throws JSONException{
		if (getListaCategoriaProduto_Pai()==null) return null;
		JSONArray lista = new JSONArray();
		for (CategoriaProduto item:this.getListaCategoriaProduto_PaiOriginal()) {
			lista.put(((ObjetoSinc)item).JSonSinc());
			//lista.put(item.JSon());
		}
		return lista;
	}
	
  	// -----  Final JSon -----------
 
 
 	public String debug() {
		return 
		 " IdCategoriaProduto=" + getIdCategoriaProduto() 
		+  " Nome=" + getNome() 
		+  " Url=" + getUrl() 
		+  " CodigoLineId=" + getCodigoLineId() 
		+  " DataInclusao=" + getDataInclusao() 
		+  " IdCategoriaProdutoP=" + getIdCategoriaProdutoP() 
		
	;
	}
 
 
 	// ---------  Metodos DCIObjeto ----------------
 	
 	public long getId() {
 		return idCategoriaProduto;
 	}
 	public String getNomeClasse() {
 		return "CategoriaProduto";
 	}
 	// ---------------------------------------------
 
 
    // ----- INICIO DISPLAY -----------------
	private CategoriaProdutoDisplay display = null;
	private CategoriaProdutoDisplay getObjetoDisplay() {
		if (display==null) {
			display = new CategoriaProdutoDisplay(this);
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
	private CategoriaProdutoAgregado agregado = null;
	private CategoriaProdutoAgregado getAgregado() {
		if (agregado==null) {
			agregado = new CategoriaProdutoAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------
	
	// ----- INICIO DERIVADA -----------------
	private CategoriaProdutoDerivada derivada = null;
	private CategoriaProdutoDerivada getDerivada() {
		if (derivada==null) {
			derivada = new CategoriaProdutoDerivada(this);
		}
		return derivada;
	}
	public String getTituloTela()
	{
		return getDerivada().getTituloTela(); 
	} 
	
	
	// ----- FINAL DERIVADA -----------------
	
	
	
 
  
  
  	// ------ AGREGADOS 2-------------------
  	// ComChaveEstrangeira
  	
		public CategoriaProduto getCategoriaProduto_Pai() {
			return getAgregado().getCategoriaProduto_Pai();
		}
		public void setCategoriaProduto_Pai(CategoriaProduto item) {
			getAgregado().setCategoriaProduto_Pai(item);
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
 		
		public CategoriaProdutoProduto getCorrenteCategoriaProdutoProduto_Possui() {
			return getAgregado().getCorrenteCategoriaProdutoProduto_Possui();
		}
		public void addListaCategoriaProdutoProduto_Possui(CategoriaProdutoProduto item) {
			getAgregado().addListaCategoriaProdutoProduto_Possui(item);
		}
		public List<CategoriaProdutoProduto> getListaCategoriaProdutoProduto_Possui() {
			return getAgregado().getListaCategoriaProdutoProduto_Possui();
		}
		public List<CategoriaProdutoProduto> getListaCategoriaProdutoProduto_PossuiOriginal() {
			return getAgregado().getListaCategoriaProdutoProduto_PossuiOriginal();
		}
		public List<CategoriaProdutoProduto> getListaCategoriaProdutoProduto_Possui(int qtde) {
			return getAgregado().getListaCategoriaProdutoProduto_Possui(qtde);
		}
		public void setListaCategoriaProdutoProduto_Possui(List<CategoriaProdutoProduto> lista) {
			getAgregado().setListaCategoriaProdutoProduto_Possui(lista);
		}
		public void setListaCategoriaProdutoProduto_PossuiByDao(List<CategoriaProdutoProduto> lista) {
			getAgregado().setListaCategoriaProdutoProduto_PossuiByDao(lista);
		}
		public void criaVaziaListaCategoriaProdutoProduto_Possui() {
			getAgregado().criaVaziaListaCategoriaProdutoProduto_Possui();
		}
		
		public boolean existeListaCategoriaProdutoProduto_Possui() {
			return getAgregado().existeListaCategoriaProdutoProduto_Possui();
		}
 		
		public CategoriaProduto getCorrenteCategoriaProduto_Pai() {
			return getAgregado().getCorrenteCategoriaProduto_Pai();
		}
		public void addListaCategoriaProduto_Pai(CategoriaProduto item) {
			getAgregado().addListaCategoriaProduto_Pai(item);
		}
		public List<CategoriaProduto> getListaCategoriaProduto_Pai() {
			return getAgregado().getListaCategoriaProduto_Pai();
		}
		public List<CategoriaProduto> getListaCategoriaProduto_PaiOriginal() {
			return getAgregado().getListaCategoriaProduto_PaiOriginal();
		}
		public List<CategoriaProduto> getListaCategoriaProduto_Pai(int qtde) {
			return getAgregado().getListaCategoriaProduto_Pai(qtde);
		}
		public void setListaCategoriaProduto_Pai(List<CategoriaProduto> lista) {
			getAgregado().setListaCategoriaProduto_Pai(lista);
		}
		public void setListaCategoriaProduto_PaiByDao(List<CategoriaProduto> lista) {
			getAgregado().setListaCategoriaProduto_PaiByDao(lista);
		}
		public void criaVaziaListaCategoriaProduto_Pai() {
			getAgregado().criaVaziaListaCategoriaProduto_Pai();
		}
		
		public boolean existeListaCategoriaProduto_Pai() {
			return getAgregado().existeListaCategoriaProduto_Pai();
		}
 		
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  
  
  	public CategoriaProdutoVo(JSONObject json) throws JSONException{
		idCategoriaProduto =  ConversorJson.getInteger(json, "IdCategoriaProduto");
		nome =  ConversorJson.getString(json, "Nome");
		url =  ConversorJson.getString(json, "Url");
		codigoLineId =  ConversorJson.getInteger(json, "CodigoLineId");
		dataInclusao =  ConversorJson.getTimestampData(json, "DataInclusao");
		idCategoriaProdutoP =  ConversorJson.getInteger(json, "IdCategoriaProdutoP");
  	}
  	public String toString() {
  		return "" + nome;
  	}
	private long idCategoriaProduto;
	public long getIdCategoriaProduto() {
		return idCategoriaProduto;
	}
	public void setIdCategoriaProduto(long _valor) {
		idCategoriaProduto = _valor;
	}


	private String nome;
	public String getNome() {
		return nome;
	}
	public void setNome(String _valor) {
		nome = _valor;
	}


	private String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String _valor) {
		url = _valor;
	}


	private long codigoLineId;
	public long getCodigoLineId() {
		return codigoLineId;
	}
	public void setCodigoLineId(long _valor) {
		codigoLineId = _valor;
	}


	private Timestamp dataInclusao;
	public Timestamp getDataInclusao() {
		return dataInclusao;
	}
	public void setDataInclusao(Timestamp _valor) {
		dataInclusao = _valor;
	}


	public String getDataInclusaoDDMMAAAA() {
		if (dataInclusao==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataInclusao);
    }
    public void setDataInclusaoDDMMAAAAComBarra(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataInclusao = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }



	
	private long idCategoriaProdutoP;
	public long getIdCategoriaProdutoP() {
		// relacionamento
		if (idCategoriaProdutoP==0 && this.getCategoriaProduto_Pai()!=null) 
			return getCategoriaProduto_Pai().getId();
		else
			return idCategoriaProdutoP;
	}
	public void setIdCategoriaProdutoP(long _valor) {
		idCategoriaProdutoP = _valor;
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
  	
	public long getIdCategoriaProdutoPLazyLoader() {
		return idCategoriaProdutoP;
	} 
		
}