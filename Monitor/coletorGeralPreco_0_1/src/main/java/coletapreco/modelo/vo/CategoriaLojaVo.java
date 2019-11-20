package coletapreco.modelo.vo;

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
import coletapreco.modelo.*;
import coletapreco.modelo.agregado.CategoriaLojaAgregado;
import coletapreco.modelo.derivada.impl.CategoriaLojaDerivada;
import coletapreco.modelo.display.impl.CategoriaLojaDisplay;

public class CategoriaLojaVo implements CategoriaLoja , ObjetoSinc{ 
  
  
  	public long getIdObj()
    {
       return idCategoriaLoja;
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
  	
  
  	public CategoriaLojaVo() {
  	}
   	// -----  Inicio JSON -----
  	private String JsonAtributosOld() {
		return 
		" \"IdCategoriaLoja\" : \"" + idCategoriaLoja + "\" "
		+ ", \"Nome\" : \"" + nome + "\" "
		+ ", \"Url\" : \"" + url + "\" "
		+ ", \"DataInclusao\" : \"" + dataInclusao + "\" "
		+ ", \"IdCategoriaLojaF\" : \"" + idCategoriaLojaF + "\" "
		+ ", \"IdNaturezaProdutoRa\" : \"" + idNaturezaProdutoRa + "\" "
		+ ", \"IdLojaVirtualPa\" : \"" + idLojaVirtualPa + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdCategoriaLoja",idCategoriaLoja);
			json.put("Nome",nome);
			json.put("Url",url);
			json.put("DataInclusao",dataInclusao);
			json.put("IdCategoriaLojaF",idCategoriaLojaF);
			json.put("IdNaturezaProdutoRa",idNaturezaProdutoRa);
			json.put("IdLojaVirtualPa",idLojaVirtualPa);
		
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
		JSONArray listaCategoriaLojaProduto_Possui = JSonListaCategoriaLojaProduto_Possui();
		if (listaCategoriaLojaProduto_Possui!=null) {
			json.put("ListaCategoriaLojaProdutoVo_Possui",listaCategoriaLojaProduto_Possui);
		} 
		JSONArray listaCategoriaLoja_Filho = JSonListaCategoriaLoja_Filho();
		if (listaCategoriaLoja_Filho!=null) {
			json.put("ListaCategoriaLojaVo_Filho",listaCategoriaLoja_Filho);
		} 
	
		//} catch (JSONException e) {
		//	DCLog.e("JSONTag", this, e);
		//}
		return json;
	}


	// SemChaveEstrangeira
	
	private JSONArray JSonListaCategoriaLojaProduto_Possui() throws JSONException{
		if (getListaCategoriaLojaProduto_Possui()==null) return null;
		JSONArray lista = new JSONArray();
		for (CategoriaLojaProduto item:this.getListaCategoriaLojaProduto_PossuiOriginal()) {
			lista.put(((ObjetoSinc)item).JSonSinc());
			//lista.put(item.JSon());
		}
		return lista;
	}
	
	private JSONArray JSonListaCategoriaLoja_Filho() throws JSONException{
		if (getListaCategoriaLoja_Filho()==null) return null;
		JSONArray lista = new JSONArray();
		for (CategoriaLoja item:this.getListaCategoriaLoja_FilhoOriginal()) {
			lista.put(((ObjetoSinc)item).JSonSinc());
			//lista.put(item.JSon());
		}
		return lista;
	}
	
  	// -----  Final JSon -----------
 
 
 	public String debug() {
		return 
		 " IdCategoriaLoja=" + getIdCategoriaLoja() 
		+  " Nome=" + getNome() 
		+  " Url=" + getUrl() 
		+  " DataInclusao=" + getDataInclusao() 
		+  " IdCategoriaLojaF=" + getIdCategoriaLojaF() 
		+  " IdNaturezaProdutoRa=" + getIdNaturezaProdutoRa() 
		+  " IdLojaVirtualPa=" + getIdLojaVirtualPa() 
		
	;
	}
 
 
 	// ---------  Metodos DCIObjeto ----------------
 	
 	public long getId() {
 		return idCategoriaLoja;
 	}
 	public String getNomeClasse() {
 		return "CategoriaLoja";
 	}
 	// ---------------------------------------------
 
 
    // ----- INICIO DISPLAY -----------------
	private CategoriaLojaDisplay display = null;
	private CategoriaLojaDisplay getObjetoDisplay() {
		if (display==null) {
			display = new CategoriaLojaDisplay(this);
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
	private CategoriaLojaAgregado agregado = null;
	private CategoriaLojaAgregado getAgregado() {
		if (agregado==null) {
			agregado = new CategoriaLojaAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------
	
	// ----- INICIO DERIVADA -----------------
	private CategoriaLojaDerivada derivada = null;
	private CategoriaLojaDerivada getDerivada() {
		if (derivada==null) {
			derivada = new CategoriaLojaDerivada(this);
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
  	
		public CategoriaLoja getCategoriaLoja_Filho() {
			return getAgregado().getCategoriaLoja_Filho();
		}
		public void setCategoriaLoja_Filho(CategoriaLoja item) {
			getAgregado().setCategoriaLoja_Filho(item);
		}
		
		
		public NaturezaProduto getNaturezaProduto_ReferenteA() {
			return getAgregado().getNaturezaProduto_ReferenteA();
		}
		public void setNaturezaProduto_ReferenteA(NaturezaProduto item) {
			getAgregado().setNaturezaProduto_ReferenteA(item);
		}
		
		public void addListaNaturezaProduto_ReferenteA(NaturezaProduto item) {
			getAgregado().addListaNaturezaProduto_ReferenteA(item);
		}
		public NaturezaProduto getCorrenteNaturezaProduto_ReferenteA() {
			return getAgregado().getCorrenteNaturezaProduto_ReferenteA();
		}
		
		
		public LojaVirtual getLojaVirtual_PertenceA() {
			return getAgregado().getLojaVirtual_PertenceA();
		}
		public void setLojaVirtual_PertenceA(LojaVirtual item) {
			getAgregado().setLojaVirtual_PertenceA(item);
		}
		
		public void addListaLojaVirtual_PertenceA(LojaVirtual item) {
			getAgregado().addListaLojaVirtual_PertenceA(item);
		}
		public LojaVirtual getCorrenteLojaVirtual_PertenceA() {
			return getAgregado().getCorrenteLojaVirtual_PertenceA();
		}
		
		
	
	// SemChaveEstrangeira
	
		public CategoriaLojaProduto getCorrenteCategoriaLojaProduto_Possui() {
			return getAgregado().getCorrenteCategoriaLojaProduto_Possui();
		}
		public void addListaCategoriaLojaProduto_Possui(CategoriaLojaProduto item) {
			getAgregado().addListaCategoriaLojaProduto_Possui(item);
		}
		public List<CategoriaLojaProduto> getListaCategoriaLojaProduto_Possui() {
			return getAgregado().getListaCategoriaLojaProduto_Possui();
		}
		public List<CategoriaLojaProduto> getListaCategoriaLojaProduto_PossuiOriginal() {
			return getAgregado().getListaCategoriaLojaProduto_PossuiOriginal();
		}
		public List<CategoriaLojaProduto> getListaCategoriaLojaProduto_Possui(int qtde) {
			return getAgregado().getListaCategoriaLojaProduto_Possui(qtde);
		}
		public void setListaCategoriaLojaProduto_Possui(List<CategoriaLojaProduto> lista) {
			getAgregado().setListaCategoriaLojaProduto_Possui(lista);
		}
		public void setListaCategoriaLojaProduto_PossuiByDao(List<CategoriaLojaProduto> lista) {
			getAgregado().setListaCategoriaLojaProduto_PossuiByDao(lista);
		}
		public void criaVaziaListaCategoriaLojaProduto_Possui() {
			getAgregado().criaVaziaListaCategoriaLojaProduto_Possui();
		}
		
		public boolean existeListaCategoriaLojaProduto_Possui() {
			return getAgregado().existeListaCategoriaLojaProduto_Possui();
		}
 		
		public CategoriaLoja getCorrenteCategoriaLoja_Filho() {
			return getAgregado().getCorrenteCategoriaLoja_Filho();
		}
		public void addListaCategoriaLoja_Filho(CategoriaLoja item) {
			getAgregado().addListaCategoriaLoja_Filho(item);
		}
		public List<CategoriaLoja> getListaCategoriaLoja_Filho() {
			return getAgregado().getListaCategoriaLoja_Filho();
		}
		public List<CategoriaLoja> getListaCategoriaLoja_FilhoOriginal() {
			return getAgregado().getListaCategoriaLoja_FilhoOriginal();
		}
		public List<CategoriaLoja> getListaCategoriaLoja_Filho(int qtde) {
			return getAgregado().getListaCategoriaLoja_Filho(qtde);
		}
		public void setListaCategoriaLoja_Filho(List<CategoriaLoja> lista) {
			getAgregado().setListaCategoriaLoja_Filho(lista);
		}
		public void setListaCategoriaLoja_FilhoByDao(List<CategoriaLoja> lista) {
			getAgregado().setListaCategoriaLoja_FilhoByDao(lista);
		}
		public void criaVaziaListaCategoriaLoja_Filho() {
			getAgregado().criaVaziaListaCategoriaLoja_Filho();
		}
		
		public boolean existeListaCategoriaLoja_Filho() {
			return getAgregado().existeListaCategoriaLoja_Filho();
		}
 		
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  
  
  	public CategoriaLojaVo(JSONObject json) throws JSONException{
		idCategoriaLoja =  ConversorJson.getInteger(json, "IdCategoriaLoja");
		nome =  ConversorJson.getString(json, "Nome");
		url =  ConversorJson.getString(json, "Url");
		dataInclusao =  ConversorJson.getTimestampData(json, "DataInclusao");
		idCategoriaLojaF =  ConversorJson.getInteger(json, "IdCategoriaLojaF");
		idNaturezaProdutoRa =  ConversorJson.getInteger(json, "IdNaturezaProdutoRa");
		idLojaVirtualPa =  ConversorJson.getInteger(json, "IdLojaVirtualPa");
  	}
  	public String toString() {
  		return "" + nome;
  	}
	private long idCategoriaLoja;
	public long getIdCategoriaLoja() {
		return idCategoriaLoja;
	}
	public void setIdCategoriaLoja(long _valor) {
		idCategoriaLoja = _valor;
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



	
	private long idCategoriaLojaF;
	public long getIdCategoriaLojaF() {
		// relacionamento
		if (idCategoriaLojaF==0 && this.getCategoriaLoja_Filho()!=null) 
			return getCategoriaLoja_Filho().getId();
		else
			return idCategoriaLojaF;
	}
	public void setIdCategoriaLojaF(long _valor) {
		idCategoriaLojaF = _valor;
	}

	
	private long idNaturezaProdutoRa;
	public long getIdNaturezaProdutoRa() {
		// relacionamento
		if (idNaturezaProdutoRa==0 && this.getNaturezaProduto_ReferenteA()!=null) 
			return getNaturezaProduto_ReferenteA().getId();
		else
			return idNaturezaProdutoRa;
	}
	public void setIdNaturezaProdutoRa(long _valor) {
		idNaturezaProdutoRa = _valor;
	}

	
	private long idLojaVirtualPa;
	public long getIdLojaVirtualPa() {
		// relacionamento
		if (idLojaVirtualPa==0 && this.getLojaVirtual_PertenceA()!=null) 
			return getLojaVirtual_PertenceA().getId();
		else
			return idLojaVirtualPa;
	}
	public void setIdLojaVirtualPa(long _valor) {
		idLojaVirtualPa = _valor;
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
  	
	public long getIdCategoriaLojaFLazyLoader() {
		return idCategoriaLojaF;
	} 
		
	public long getIdNaturezaProdutoRaLazyLoader() {
		return idNaturezaProdutoRa;
	} 
		
	public long getIdLojaVirtualPaLazyLoader() {
		return idLojaVirtualPa;
	} 
		
}